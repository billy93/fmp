(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FbrQueryController', FbrQueryController);

    FbrQueryController.$inject = ['$state', 'FbrQuery', 'ParseLinks', 'AlertService', 'paginationConstants', '$uibModal', 'DateUtils', 'AtpcoMasterFareType', 'TariffNumber', 'Passenger'];

    function FbrQueryController($state, FbrQuery, ParseLinks, AlertService, paginationConstants, $uibModal, DateUtils, AtpcoMasterFareType, TariffNumber, Passenger) {

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
        vm.getRules = getRules;
        vm.checkValidParameters = checkValidParameters;
        vm.showInfoModal = showInfoModal;
        vm.showCategoryDetail = showCategoryDetail;
        vm.showCarrierModal = showCarrierModal;
        vm.showTariffModal = showTariffModal;
        vm.showLegend = showLegend;
        vm.viewFullText = viewFullText;
        
        vm.fbrQueries = [];
        vm.reset = reset;
        vm.page = 0;
        vm.disableInfiniteScroll = true;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "dd/MM/yyyy";
        vm.openCalendar = openCalendar;
        
        vm.fareTypes = AtpcoMasterFareType.getAll();
        vm.globalIndicators = TariffNumber.getAllGlobal();
        vm.paxTypes = Passenger.getAll();
        
        vm.publicPrivate = [
        	{key: "", value: "Select Public/Private"},
        	{key: "Public", value: "Public"},
        	{key: "Private", value: "Private"}
        ]
        
        vm.cabins = [
        	{key: "F", value: "First"},
        	{key: "J", value: "Business"},
        	{key: "W", value: "Premium Economy"},
        	{key: "Y", value: "Economy"}
        ];
        
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
            	vm.fbrQueryCategories = null;
            	
            	vm.queryParams.carrier = vm.paramCarrier;
            	vm.queryParams.fbrTariff = vm.paramTarNo;
            	
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
        
        function getRules(fbrQuery) {
        	vm.isLoadingRule = true;
        	FbrQuery.getRules(fbrQuery, function(data) {
        		vm.fbrQueryCategories = data;
        		vm.isLoadingRule = false;
        	}, function(error) {
        		console.log(error);
        		vm.isLoadingRule = false;
        	});
        }   
        
        function checkValidParameters() {
        	if (vm.paramCarrier != null) {
        		return 'Valid';
        	} else {
        		return "Error: One set of the following is required.\n\Carrier\n";
        	}
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
        	
        	vm.paramCarrier = null;
        	vm.paramTarNo = null;
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
        
        function showCategoryDetail(category) {
			$uibModal.open({
				templateUrl : 'app/pages/modals/category-modal.html',
				controller : 'CategoryModalController',
				controllerAs : 'vm',
				backdrop : 'static',
				size : 'lg',
				windowClass : 'full',
				resolve : {
					entity : category
				}
			}).result.then(function() {
				$state.go('afd-query', {}, {
					reload : false
				});
			}, function() {
				$state.go('afd-query');
			});
		}
        
        function showCarrierModal() {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/carrier-modal.html',
                controller: 'MasterCarrierModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                	entity: vm
                }
            }).result.then(function() {
                $state.go('afd-query', {}, { reload: false });
            }, function() {
                $state.go('afd-query');
            });
        }
        
        function showTariffModal() {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/tariff-modal.html',
                controller: 'MasterTariffModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                	entity: vm
                }
            }).result.then(function() {
                $state.go('afd-query', {}, { reload: false });
            }, function() {
                $state.go('afd-query');
            });
        }
        
        function showLegend() {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/legend-modal.html',
                controller: 'LegendModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'md'
            }).result.then(function() {
                $state.go('afd-query', {}, { reload: false });
            }, function() {
                $state.go('afd-query');
            });
        }
        
        function viewFullText() {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/full-text-modal.html',
                controller: 'FullTextModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                    entity: {
                    	categories: vm.fbrQueryCategories
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
