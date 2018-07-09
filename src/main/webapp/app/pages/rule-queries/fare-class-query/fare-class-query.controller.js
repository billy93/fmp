(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareClassQueryController', FareClassQueryController);

    FareClassQueryController.$inject = ['$state', '$stateParams', 'FareClassQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function FareClassQueryController($state, $stateParams, FareClassQuery, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;
        
        vm.loadAll = loadAll;
        vm.clearFilter = clearFilter;
        vm.resetFilter = resetFilter;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.getFareClassGroups = getFareClassGroups;
        vm.fareClassGroupText = fareClassGroupText;
        vm.constructDetails = constructDetails;
        vm.getFareClassDetails = getFareClassDetails;
        
        if($stateParams.fareClasssQueryFilter != null){
        	vm.queryParams = $stateParams.fareClasssQueryFilter;
        	vm.itemsPerPage = vm.queryParams.size;
        } else {
        	vm.clearFilter();
        	vm.itemsPerPage = "10";
        }
        
        function loadAll() {
        	vm.queryParams.page = pagingParams.page - 1;
			vm.queryParams.size = vm.itemsPerPage;
			
			FareClassQuery.query(vm.queryParams, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                //vm.fareClassQueries = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function clearFilter() {
        	vm.queryParams = {
        			carrier: null,
        			ruleNumber: null,
        			fareClass: null,
        			tariffNumber: null,
        			passengerType: null,
        			fareType: null,
        			bookingClass: null
        	}
        }
        
        function resetFilter() {
        	vm.clearFilter();
        	vm.fareClassQueries = null;
        }
        
        function getFareClassGroups() {
        	console.log("getFareClassGroups");
        }
        
        function fareClassGroupText() {
        	console.log("fareClassGroupText");
        }
        
        function constructDetails() {
        	console.log("constructDetails");
        }
        
        function getFareClassDetails() {
        	console.log("getFareClassDetails");
        }
    }
})();
