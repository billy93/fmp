(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AfdQueryController', AfdQueryController);

    AfdQueryController.$inject = ['$state', 'AfdQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'queryParams', 'tariffNumbers', 'cities', '$uibModal', 'Clipboard', 'Timezone'];

    function AfdQueryController($state, AfdQuery, ParseLinks, AlertService, paginationConstants, queryParams, tariffNumbers, cities, $uibModal, Clipboard, Timezone) {

        var vm = this;
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.queryParams = queryParams;
        vm.loadAll = loadAll;
        vm.checkValidParameters = checkValidParameters;
        vm.setSelectedRow = setSelectedRow;
        vm.getRules = getRules;
        vm.showCategoryDetail = showCategoryDetail;
        vm.copyAfdQueryFares = copyAfdQueryFares;
        vm.selectAll = selectAll;
        vm.showLegend = showLegend;
        vm.viewFullText = viewFullText;
        vm.showErrorModal = showErrorModal;
        vm.selectedRows = [];
        vm.timezone = Timezone.GMT7;
        
        vm.reset = reset;
        vm.page = 1;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        
        vm.sources = [
        	{key: "", value: "Select Source"},
        	{key: "A", value: "A - ATPCO"},
        	{key: "M", value: "M - Market"},
        	{key: "W", value: "W - Web"},
        	{key: "C", value: "C - Competitor Private"}
        ]
        
        vm.publicPrivate = [
        	{key: "", value: "Select Public/Private"},
        	{key: "Public", value: "Public"},
        	{key: "Private", value: "Private"},
        ]
        
        vm.owrts = [
        	{key: "", value: "Select OW/RT"},
    		{key: "1", value: "1 - One Way"},
    		{key: "2", value: "2 - Rount Trip"},
    		{key: "3", value: "3 - One Way Only"},
        ]
        
        vm.dateOptions = [
        	{key: "A", value: "Active In"},
        	{key: "E", value: "Exact Match"}
        ]
        
        vm.tariffs = tariffNumbers;
        
        vm.cities = cities;
        
        vm.globalIndicators = "?";
        
        vm.gaFareTypes = "?";
        
        vm.fareTypes = "?";
        
        vm.paxTypes = "?";
        
        vm.cabins = "?";
        
        vm.loadAll();

        function loadAll () {
        	if (!vm.checkValidParameters()) {
        		vm.showErrorModal();
        		return;
        	}
        	
        	vm.categoryRules = null;
        	vm.currentAfdQuery = null;
        	
        	vm.queryParams.page = vm.page - 1;
        	vm.queryParams.size = vm.itemsPerPage;
        	
        	AfdQuery.query(vm.queryParams, onSuccess, onError);
        	
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.afdQueries = data;
            }
            
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function checkValidParameters() {
        	console.log(vm.queryParams);
        	
        	//Effective to must be minimum of tommorrow when effective from is not filled
        	
        	return true;
        }

        function loadPage(page) {
            vm.page = page;
        }

        function getRules(afdQuery) {
        	if (vm.currentAfdQuery == undefined || vm.currentAfdQuery == null || vm.currentAfdQuery != afdQuery) {
        		AfdQuery.getRules(afdQuery, function(data) {
            		vm.categoryRules = data;
            		vm.currentAfdQuery = afdQuery;
            		console.log(vm.categoryRules);
            	}, function(error) {
            		console.log(error);
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
        	
        	vm.loadAll();
        }
        
        function setSelectedRow(index, afdQuery) {
        	if (vm.selectedRows[index] != null) {
        		vm.selectedRows[index] = null;
        	} else {
        		vm.selectedRows[index] = afdQuery;
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
            	}
        		vm.allSelected = true;
        	} else {
        		for (var i = 0; i < vm.selectedRows.length; i++) {
        			vm.selectedRows[i] = null;
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
        			content: {
        				'ATPCO_FARE': selectedFares
        			}
        		}
        		
        		Clipboard.copy(clipboard, function(data) {
        			console.log(data);
        		}, function(error) {
        			console.log(error);
        		})
        	}
        }
        
        function showFareDetail(category) {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/fare-detail-modal.html',
                controller: 'FareDetailModalController',
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
        
        function showErrorModal() {
//        	$uibModal.open({
//                templateUrl: 'app/pages/category-modals/legend-modal.html',
//                controller: 'LegendModalController',
//                controllerAs: 'vm',
//                backdrop: 'static',
//                size: 'md'
//            }).result.then(function() {
//                $state.go('afd-query', {}, { reload: false });
//            }, function() {
//                $state.go('afd-query');
//            });
        }
    }
})();
