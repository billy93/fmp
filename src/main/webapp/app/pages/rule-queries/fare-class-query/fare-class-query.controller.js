(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareClassQueryController', FareClassQueryController);

    FareClassQueryController.$inject = ['$state', '$stateParams', 'FareClassQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function FareClassQueryController($state, $stateParams, FareClassQuery, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.loadAll = loadAll;
        vm.clearFilter = clearFilter;
        vm.resetFilter = resetFilter;
        vm.page = 1;
        vm.getFareClassQueries = getFareClassQueries;
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
        	vm.queryParams.page = vm.page - 1;
			vm.queryParams.size = vm.itemsPerPage;
			
			FareClassQuery.query(vm.queryParams, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                
                vm.fareClassGroups = data;
                if(vm.fareClassGroups.length > 0) {
                	vm.selectedRow = vm.fareClassGroups[0];
                    getFareClassQueries(vm.selectedRow);
                } else {
                	vm.fareClassQueries = null;
                	vm.fareClassSelectedRow = null;
                	vm.fareClassDetails = null;
                }
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function clearFilter() {
        	vm.queryParams = {
        			cxr: null,
        			ruleNo: null,
        			fareClass: null,
        			tarNo: null,
        			psgrType: null,
        			fareType: null,
        			bookingClass: null
        	};
        }
        
        function resetFilter() {
        	vm.clearFilter();
        	vm.fareClassGroups = null;
        	vm.fareClassQueries = null;
        	vm.fareClassSelectedRow = null;
        	vm.fareClassDetails = null;
        }
        
        function getFareClassQueries(fareClassGroup) {
        	fareClassGroup.fareClass = vm.queryParams.fareClass;
        	fareClassGroup.psgrType = vm.queryParams.psgrType;
        	fareClassGroup.fareType = vm.queryParams.fareType;
        	fareClassGroup.bookingClass = vm.queryParams.bookingClass;
        	
        	FareClassQuery.getFareClassGroups(fareClassGroup, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.fareClassQueries = data;
                vm.fareClassSelectedRow = vm.fareClassQueries[0];
            	vm.fareClassDetails = [vm.fareClassSelectedRow];
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
            
            vm.fareClassDetails = null;
        }
        
        function fareClassGroupText() {
        	console.log("fareClassGroupText");
        }
        
        function constructDetails() {
        	console.log("constructDetails");
        }
        
        function getFareClassDetails(fareClassQuery) {
        	console.log(fareClassQuery);
        	vm.fareClassDetails = [fareClassQuery];
        }
    }
})();
