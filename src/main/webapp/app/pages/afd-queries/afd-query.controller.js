(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AfdQueryController', AfdQueryController);

    AfdQueryController.$inject = ['$state', 'AfdQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'queryParams', 'tariffNumbers', 'cities', '$uibModal'];

    function AfdQueryController($state, AfdQuery, ParseLinks, AlertService, paginationConstants, queryParams, tariffNumbers, cities, $uibModal) {

        var vm = this;
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.queryParams = queryParams;
        vm.loadAll = loadAll;
        vm.getRules = getRules;
        vm.showCategoryDetail = showCategoryDetail;
        
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

        function loadPage(page) {
            vm.page = page;
        }

        function getRules(afdQuery) {
        	if (vm.currentAfdQuery == undefined || vm.currentAfdQuery == null || vm.currentAfdQuery != afdQuery) {
        		AfdQuery.getRules(afdQuery, function(data) {
            		vm.categoryRules = data;
            		vm.currentAfdQuery = afdQuery;
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
        	}
        	
        	vm.loadAll();
        }
        
        function showCategoryDetail(category) {
        	$uibModal.open({
                templateUrl: 'app/pages/category-modals/category-modal.html',
                controller: 'CategoryModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                    entity: category
                }
            }).result.then(function() {
                $state.go('afd-query', {}, { reload: false });
            }, function() {
                $state.go('afd-query');
            });
        }
    }
})();
