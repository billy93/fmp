(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('YqyrQueryController', YqyrQueryController);

    YqyrQueryController.$inject = ['$state', 'YqyrQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function YqyrQueryController($state, YqyrQuery, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;

        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;

        loadAll();

        function loadAll () {
        	YqyrQuery.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);
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
                vm.yqyrQueries = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function loadPage(page) {
            vm.page = page;
            vm.transition();
        }
        
        vm.datePickerOpenStatus.travelDate = false;
        vm.datePickerOpenStatus.ticketingDate = false;
        
        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }

        function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
    }
})();
