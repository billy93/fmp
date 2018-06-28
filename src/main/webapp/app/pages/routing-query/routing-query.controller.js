(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RoutingqueryController', RoutingqueryController);

    RoutingqueryController.$inject = ['$state', '$stateParams', 'Routingquery', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function RoutingqueryController($state, $stateParams, Routingquery, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;
        
        vm.loadAll = loadAll;
        vm.loadPage = loadPage;
        vm.clearFilter = clearFilter;
        vm.resetFilter = resetFilter;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.getDetails = getDetails;
        vm.getFullDetails = getFullDetails;
        vm.changeItemsPerPage = changeItemsPerPage;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        
        if($stateParams.routingQueryFilter != null){
        	vm.queryParams = $stateParams.routingQueryFilter;
        	vm.itemsPerPage = vm.queryParams.size;
        } else {
        	vm.clearFilter();
        	vm.itemsPerPage = "10";
        }
        
        vm.loadAll(false);

        function loadAll(isQueryClick) {
        	vm.queryParams.page = pagingParams.page - 1;
			vm.queryParams.size = vm.itemsPerPage;
			vm.queryParams.sort = sort();
			
            Routingquery.query(vm.queryParams, onSuccess, onError);
            
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.routingqueries = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        	
        	if(isQueryClick) {
        		vm.page = 1;
        		vm.transition();
        	}
        }

        function loadPage(page) {
            vm.page = page;
            vm.transition();
        }

        function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch,
                routingQueryFilter : vm.queryParams
            });
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
        
        function clearFilter() {
        	vm.queryParams = {
        			src: null,
        			tarNo: null,
        			carrier: null,
        			routingNo: null,
        			entryPoint: null,
        			exitPoint: null,
        			effectiveDateFrom: null,
        			effectiveDateTo: null,
        			showRoutesMaps: false
        	}
        }
        
        function resetFilter() {
        	vm.clearFilter();
        	vm.loadAll(false);
        }
        
        function getDetails(routingQuery) {
        	Routingquery.getDetails(routingQuery, function(data) {
        		vm.routingDetails = data;
        	}, function(error) {
        		console.log(error);
        	});
        }
        
        function getFullDetails(routingQuery) {
        	Routingquery.getFullDetails(routingQuery, function(data) {
        		vm.routingFullDetails = data;
        	}, function(error) {
        		console.log(error);
        	});
        }
        
        function changeItemsPerPage() {
        	vm.loadAll();
        }
    }
})();
