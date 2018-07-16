(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('SpecifiedConstructedController', SpecifiedConstructedController);

    SpecifiedConstructedController.$inject = ['$state', 'SpecifiedConstructed', 'ParseLinks', 'AlertService', 'paginationConstants', 'TariffNumber', 'City', 'CityGroup', '$uibModal', 'Clipboard', 'Timezone', 'AtpcoMasterFareType', 'Passenger', 'DateUtils', 'FareType'];

    function SpecifiedConstructedController($state, SpecifiedConstructed, ParseLinks, AlertService, paginationConstants, TariffNumber, City, CityGroup, $uibModal, Clipboard, Timezone, AtpcoMasterFareType, Passenger, DateUtils, FareType) {

    	var vm = this;
    	 
    	vm.queryParams = {
    		carrier: null,
    		source: null,
    		publicPrivate: null,
    		tariff: null,
    		globalIndicator: null,
    		gaFareType: null,
    		fareType: null,
    		fareBasis: null,
    		origin: null,
    		destination: null,
    		owrt: null,
    		footnote: null,
    		ruleNo: null,
    		routingNo: null,
    		woId: null,
    		effectiveDateFrom: null,
    		effectiveDateTo: null,
    		effectiveDateOption: null,
    		saleDateFrom: null,
    		saleDateTo: null,
    		saleDateOption: null,
    		travelDateFrom: null,
    		travelDateTo: null,
    		travelDateOption: null,
    		seasonDateFrom: null,
    		seasonDateTo: null,
    		seasonDateOption: null,
    		amountRange: null,
    		tourCode: null,
    		paxType: null,
    		cabin: null,
    		bookingClass: null,
    		advancePurchase: null,
    		minStay: null,
    		maxStay: null,
    		includeConstructed: false,
    		appendResults: false,
    		biDirectional: false,
    		calculateTfc: false
    	};
    	
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
//        vm.itemsPerPage = 100;
        vm.loadAll = loadAll;
        vm.query = query;
        vm.checkValidParameters = checkValidParameters;
        vm.setSelectedRow = setSelectedRow;
        vm.getRules = getRules;
        vm.showCategoryDetail = showCategoryDetail;
        vm.showFareDetail = showFareDetail;
        vm.copyAfdQueryFares = copyAfdQueryFares;
        vm.selectAll = selectAll;
        vm.showLegend = showLegend;
        vm.viewFullText = viewFullText;
        vm.showInfoModal = showInfoModal;
        vm.selectCity = selectCity;
        vm.selectedRows = [];
        vm.selectedFares = [];
        vm.timezone = Timezone.GMT7;
        vm.infoMessage = null;
        
        vm.afdQueries = [];
        vm.reset = reset;
        vm.page = 0;
        vm.disableInfiniteScroll = true;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "dd/MM/yyyy";
        vm.openCalendar = openCalendar;
        
        vm.tariffs = TariffNumber.getAll();
        console.log(vm.tariffs);
        vm.fareTypes = AtpcoMasterFareType.getAll();
        vm.globalIndicators = TariffNumber.getAllGlobal();
        vm.paxTypes = Passenger.getAll();
        vm.cities = City.getAll();
        
        vm.sources = [
        	{key: "A", value: "A - ATPCO"},
        	{key: "M", value: "M - Market"},
        	{key: "W", value: "W - Web"},
        	{key: "C", value: "C - Competitor Private"}
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
        
        vm.cabins = [
        	{key: "F", value: "First"},
        	{key: "J", value: "Business"},
        	{key: "W", value: "Premium Economy"},
        	{key: "Y", value: "Economy"}
        ];
       
        vm.gaFareTypes = FareType.getAll();
        
        function query() {
        	vm.afdQueries = [];
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
            	vm.currentAfdQuery = null;
            	
            	vm.queryParams.page = vm.page;
            	vm.queryParams.size = vm.itemsPerPage;
            	vm.queryParams.lastIndex = vm.lastIndex;
            	
            	vm.queryParams.effectiveDateFrom = DateUtils.convertLocalDateToServer(vm.effectiveDateFrom);
            	vm.queryParams.effectiveDateTo = DateUtils.convertLocalDateToServer(vm.effectiveDateTo);
            	vm.queryParams.travelDateFrom = DateUtils.convertLocalDateToServer(vm.travelDateFrom);
            	vm.queryParams.travelDateTo = DateUtils.convertLocalDateToServer(vm.travelDateTo);
            	vm.queryParams.saleDateFrom = DateUtils.convertLocalDateToServer(vm.saleDateFrom);
            	vm.queryParams.saleDateTo = DateUtils.convertLocalDateToServer(vm.saleDateTo);
            	vm.queryParams.seasonDateFrom = DateUtils.convertLocalDateToServer(vm.seasonDateFrom);
            	vm.queryParams.seasonDateTo = DateUtils.convertLocalDateToServer(vm.seasonDateTo);
            	
            	console.log(vm.queryParams);
            	
            	SpecifiedConstructed.query(vm.queryParams, onSuccess, onError);
            	
                function onSuccess(data) {
                	vm.isLastPage = data.lastPage;
                	vm.lastIndex = data.lastIndex;
                	
                    for (var i = 0; i < data.specifiedConstructed.length; i++) {
                    	vm.afdQueries.push(data.specifiedConstructed[i]);
                    }
                    
                    console.log(data);
                    
                    vm.isLoading = false;
                    vm.disableInfiniteScroll = false;
                    
                    if (vm.afdQueries.length == 0) {
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
        	if ((vm.queryParams.carrier != null && vm.queryParams.carrier != '' && vm.queryParams.origin != null && vm.queryParams.origin != '') ||
        			(vm.queryParams.carrier != null && vm.queryParams.destination != null && vm.queryParams.carrier != '' && vm.queryParams.destination != '') ||
        			(vm.queryParams.origin != null && vm.queryParams.destination != null && vm.queryParams.origin != '' && vm.queryParams.destination != '') ||
        			(vm.queryParams.origin != null && vm.queryParams.fareBasis != null && vm.queryParams.origin != '' && vm.queryParams.fareBasis != '') ||
        			(vm.queryParams.destination != null && vm.queryParams.fareBasis != null && vm.queryParams.destination != '' && vm.queryParams.fareBasis != '') || 
        			(vm.queryParams.carrier != null && vm.queryParams.fareBasis != null && vm.queryParams.carrier != '' && vm.queryParams.fareBasis != '') || 
        			(vm.queryParams.woId != null && vm.queryParams.woId != '')) {
        		return 'Valid';
        	} else {
        		return "Error: One set of the following is required.\n\tCarrier and Origin\n\tCarrier and Destination\n\tCarrier and Fare Basis\n\tOrigin and Destination\n\tOrigin and Fare Basis\n\tDestination and Fare Basis\n\tWork Order ID\n";
        	}
        	
//        	if (vm.effectiveDateFrom == null) {
//        		
//        	}
        	
        	//Effective to must be minimum of tommorrow when effective from is not filled
        }

        function loadPage(page) {
    		vm.page = page;
    		vm.loadAll();
        }

        function getRules(afdQuery) {
        	if (vm.currentAfdQuery == undefined || vm.currentAfdQuery == null || vm.currentAfdQuery != afdQuery) {
        		vm.isLoadingRule = true;
        		vm.categoryRules = null;
        		SpecifiedConstructed.getRules(afdQuery, function(data) {
            		vm.categoryRules = data;
            		vm.currentAfdQuery = afdQuery;
            		console.log(vm.categoryRules);
            		vm.isLoadingRule = false;
            	}, function(error) {
            		console.log(error);
            		vm.isLoadingRule = false;
            	});
        	} 
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
        
        function reset() {
        	vm.queryParams = {
        		carrier: null,
        		source: vm.sources[0].key,
        		publicPrivate: null,
        		tariff: null,
        		globalIndicator: null,
        		gaFareType: null,
        		fareType: null,
        		fareBasis: null,
        		origin: null,
        		destination: null,
        		owrt: null,
        		footnote: null,
        		ruleNo: null,
        		routingNo: null,
        		woId: null,
        		effectiveDateFrom: null,
        		effectiveDateTo: null,
        		effectiveDateOption: vm.dateOptions[0].key,
        		saleDateFrom: null,
        		saleDateTo: null,
        		saleDateOption: vm.dateOptions[0].key,
        		travelDateFrom: null,
        		travelDateTo: null,
        		travelDateOption: vm.dateOptions[0].key,
        		seasonDateFrom: null,
        		seasonDateTo: null,
        		seasonDateOption: vm.dateOptions[0].key,
        		amountRange: null,
        		tourCode: null,
        		paxType: null,
        		cabin: null,
        		bookingClass: null,
        		advancePurchase: null,
        		minStay: null,
        		maxStay: null,
        		includeConstructed: false,
        		appendResults: false,
        		biDirectional: false,
        		calculateTfc: false
        	}
        	vm.effectiveDateFrom = null;
        	vm.effectiveDateTo = null;
        	vm.saleDateFrom = null;
        	vm.saleDateTo = null;
        	vm.travelDateFrom = null;
        	vm.travelDateTo = null;
        	vm.seasonDateFrom = null;
        	vm.seasonDateTo = null;
        }
        
        function setSelectedRow(index, afdQuery) {
        	if (vm.selectedRows[index] != null) {
        		vm.selectedRows[index] = null;
        	} else {
        		vm.selectedRows[index] = afdQuery;
        	}
        	
        	if (vm.selectedFares.indexOf(afdQuery) != -1) {
        		vm.selectedFares.splice(vm.selectedFares.indexOf(afdQuery), 1);
        	} else {
        		vm.selectedFares.push(afdQuery);
        	}
        }
        
        function selectAll() {
        	vm.allSelected = false;
        	var isEmpty = true;
        	
        	for (var i = 0; i < vm.selectedRows.length; i++) {
    			if (vm.selectedRows[i] != null) {
    				isEmpty = false;
    			}
    		}
        	
        	if (isEmpty) {
        		for (var i = 0; i < vm.afdQueries.length; i++) {
            		vm.selectedRows[i] = vm.afdQueries[i];
            		vm.selectedFares.push(vm.afdQueries[i]);
            	}
        		vm.allSelected = true;
        	} else {
        		for (var i = 0; i < vm.selectedRows.length; i++) {
        			vm.selectedRows[i] = null;
        			vm.selectedFares = [];
        		}
        	}
        }
        
        function copyAfdQueryFares() {
        	if (vm.selectedRows.length > 0) {
        		var selectedFares = [];
        		
        		vm.selectedRows.forEach(function(row) {
        			if (row != null) {
        				selectedFares.push(row);
        			}
        		})
        		
        		var clipboard = {
        			page: 'AFD_QUERY',
        			content: selectedFares        			
        		}
        		
        		Clipboard.copy(clipboard, function(data) {
        			console.log(data);
        		}, function(error) {
        			console.log(error);
        		})
        	}
        }
        
        function showFareDetail() {
        	if (vm.selectedFares.length > 0) {
        		$uibModal.open({
                    templateUrl: 'app/pages/modals/fare-detail-modal.html',
                    controller: 'FareDetailModalController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        fare: vm.selectedFares[0],
                        rules: {
                        	categories: vm.categoryRules
                        }
                    },
                    windowClass: 'full'
                }).result.then(function() {
                    $state.go('afd-query', {}, { reload: false });
                }, function() {
                    $state.go('afd-query');
                });
        	}
        }
        
        function showCategoryDetail(category) {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/category-modal.html',
                controller: 'CategoryModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                resolve: {
                    entity: category
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
                    	categories: vm.categoryRules
                    }
                }
            }).result.then(function() {
                $state.go('afd-query', {}, { reload: false });
            }, function() {
                $state.go('afd-query');
            });
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
        
        function selectCity(model) {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/select-city-modal.html',
                controller: 'SelectCityModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'md',
            }).result.then(function(value) {
            	if (model == 'Origin') {
            		vm.queryParams.origin = value;
            	} else if (model == 'Destination') {
            		vm.queryParams.destination = value;
            	}
            	
                $state.go('afd-query', {}, { reload: false });
            }, function() {
                $state.go('afd-query');
            });
        }
    }
})();
