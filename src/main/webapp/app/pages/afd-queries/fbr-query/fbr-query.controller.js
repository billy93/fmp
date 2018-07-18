(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FbrQueryController', FbrQueryController);

    FbrQueryController.$inject = ['$state', 'FbrQuery', 'ParseLinks', 'AlertService', 'paginationConstants', '$uibModal', 'DateUtils'];

    function FbrQueryController($state, FbrQuery, ParseLinks, AlertService, paginationConstants, $uibModal, DateUtils) {

    	var vm = this;
    	 
    	vm.queryParams = {
    			ruleTittle: null,
        		carrier: null,
        		source: null,
        		publicPrivate: null,
        		fbrTariff: null,
        		fbrRule: null,
        		bookingClass: null,
        		cat25FareBasis: null,
        		tourCode: null,
        		accountCode: null,
        		tktDesignator: null,
        		paxAge: null,
        		accountCodeBlank: false,
        		tktDesignatorBlank: false,
        		appendResults: false,
        		
        		origin: null,
        		destination: null,
        		effectiveDateFrom: null,
        		effectiveDateTo: null,
        		effectiveDateOption: null,
        		paxType: null,
        		biDirectional: false,
        		saleDateFrom: null,
        		saleDateTo: null,
        		saleDateOption: null,
        		travelDateFrom: null,
        		travelDateTo: null,
        		travelDateOption: null,
        		
        		cabin: null,
        		fareBasis: null,
        		fareType: null,
        		globalIndicator: null,
        		owrt: null
        	};
    	
    	vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.loadAll = loadAll;
        vm.query = query;
        vm.checkValidParameters = checkValidParameters;
        vm.showInfoModal = showInfoModal;
        
        vm.fbrQueries = [];
        vm.reset = reset;
        vm.page = 0;
        vm.disableInfiniteScroll = true;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "dd/MM/yyyy";
        vm.openCalendar = openCalendar;
        
        vm.sources = [
        	{key: "A", value: "A - ATPCO"},
        	{key: "M", value: "M - Market"}
        ]
        
        vm.publicPrivate = [
        	{key: "", value: "Select Public/Private"},
        	{key: "Public", value: "Public"},
        	{key: "Private", value: "Private"}
        ]
        
        vm.owrts = [
        	{key: "", value: "Select OW/RT"},
    		{key: "1", value: "1 - One Way"},
    		{key: "2", value: "2 - Rount Trip"},
    		{key: "3", value: "3 - One Way Only"}
        ]
        
        vm.dateOptions = [
        	{key: "A", value: "Active In"},
        	{key: "E", value: "Exact Match"}
        ]
        
        function query() {
        	vm.fbrQueries = [];
        	vm.page = 0;
        	vm.lastIndex = 0;
        	vm.isLastPage = false;
        	vm.loadAll();
        }
        
        function loadAll () {
        	if (!vm.isLastPage) {
        		vm.infoMessage = vm.checkValidParameters();
        		
        		if (vm.infoMessage != 'Valid') {
            		vm.showInfoModal(vm.infoMessage);
            		return;
            	}
        		
        		vm.isLoading = true;
            	vm.isLastPage = false;
            	vm.noDataAvailable = false;
            	vm.currentAddOn = null;
            	
            	vm.queryParams.carrier = vm.paramCarrier;
            	
            	vm.queryParams.page = vm.page;
            	vm.queryParams.size = vm.itemsPerPage;
            	vm.queryParams.lastIndex = vm.lastIndex;
            	
            	vm.queryParams.effectiveDateFrom = DateUtils.convertLocalDateToServer(vm.effectiveDateFrom);
            	vm.queryParams.effectiveDateTo = DateUtils.convertLocalDateToServer(vm.effectiveDateTo);
            	vm.queryParams.travelDateFrom = DateUtils.convertLocalDateToServer(vm.travelDateFrom);
            	vm.queryParams.travelDateTo = DateUtils.convertLocalDateToServer(vm.travelDateTo);
            	vm.queryParams.saleDateFrom = DateUtils.convertLocalDateToServer(vm.saleDateFrom);
            	vm.queryParams.saleDateTo = DateUtils.convertLocalDateToServer(vm.saleDateTo);
            	
            	FbrQuery.query(vm.queryParams, onSuccess, onError);
            	
                function onSuccess(data) {
                	vm.isLastPage = data.lastPage;
                	vm.lastIndex = data.lastIndex;
                	
                    for (var i = 0; i < data.fbrQueries.length; i++) {
                    	vm.fbrQueries.push(data.fbrQueries[i]);
                    }
                    
                    vm.isLoading = false;
                    vm.disableInfiniteScroll = false;
                    
                    if (vm.fbrQueries.length == 0) {
                    	vm.noDataAvailable = true;
                    } else {
                    	$(document).ready(function(){
                    		var _parents = $('.table-afd').find('thead');
                    		var _th = _parents.find('.th-fixed');
                    		var _tr = _parents.siblings('tbody').find('tr:first-child');
                    		var _td = _tr.find('td');
                    		var _length = _th.length;
                    		_th.last().css('border-right','none');
                    		for(var i=0;i<_length;i++){
                    			var _width = _th.eq(i).outerWidth();
                    			var _width2 = _td.eq(i).outerWidth();
                    			if(_width > _width2){
                    				_td.eq(i).css('min-width', _width);
                    				_td.eq(i).css('width', _width);
                    			}
                    			else{
                    				_th.eq(i).css('min-width', _width2);
                    				_th.eq(i).css('width', _width2);
                    			}
                    		}
                    	});
                    }
                }
                
                function onError(error) {
                    AlertService.error(error.data.message);
                    vm.isLoading = false;
                }
        	}
        }
        
        function checkValidParameters() {
//        	if ((vm.queryParams.origin != null && vm.queryParams.origin != '' && vm.queryParams.destination != null && vm.queryParams.destination != '') ||
//        			(vm.queryParams.origin != null && vm.queryParams.origin != '' && vm.queryParams.bucket != null && vm.queryParams.bucket != '') ||
//        			(vm.queryParams.destination != null && vm.queryParams.destination != '' && vm.queryParams.bucket != null && vm.queryParams.bucket != '') ||
//        			(vm.queryParams.carrier != null && vm.queryParams.carrier != '' && vm.queryParams.bucket != null && vm.queryParams.bucket != '') ||
//        			(vm.queryParams.woId != null && vm.queryParams.woId != '')) {
//        		return 'Valid';
//        	} else {
//        		return "Error: One set of the following is required.\n\tOrigin and Destination\n\tOrigin and Add-On Bucket\n\tDestination and Add-On Bucket\n\tCarrier and Add-On Bucket\n\tWork Order ID\n";
//        	}
        	return 'Valid';
        }

        function loadPage(page) {
    		vm.page = page;
    		vm.loadAll();
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus = {};
            vm.datePickerOpenStatus[date] = true;
        }
        
        function reset() {
        	vm.queryParams = {
    			ruleTittle: null,
        		carrier: null,
        		source: vm.sources[0].key,
        		publicPrivate: null,
        		fbrTariff: null,
        		fbrRule: null,
        		bookingClass: null,
        		cat25FareBasis: null,
        		tourCode: null,
        		accountCode: null,
        		tktDesignator: null,
        		paxAge: null,
        		accountCodeBlank: false,
        		tktDesignatorBlank: false,
        		appendResults: false,
        		
        		origin: null,
        		destination: null,
        		effectiveDateFrom: null,
        		effectiveDateTo: null,
        		effectiveDateOption: vm.dateOptions[0].key,
        		paxType: null,
        		biDirectional: false,
        		saleDateFrom: null,
        		saleDateTo: null,
        		saleDateOption: vm.dateOptions[0].key,
        		travelDateFrom: null,
        		travelDateTo: null,
        		travelDateOption: vm.dateOptions[0].key,
        		
        		cabin: null,
        		fareBasis: null,
        		fareType: null,
        		globalIndicator: null,
        		owrt: null
        	}
        	vm.effectiveDateFrom = null;
        	vm.effectiveDateTo = null;
        	vm.saleDateFrom = null;
        	vm.saleDateTo = null;
        	vm.travelDateFrom = null;
        	vm.travelDateTo = null;
        }
        
        function showInfoModal(message) {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/info-modal.html',
                controller: 'InfoModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'md',
                resolve: {
                    entity: {
                    	message: message
                    }	
                }
            }).result.then(function() {
                $state.go('afd-query', {}, { reload: false });
            }, function() {
                $state.go('afd-query');
            });
        }
    }
})();
