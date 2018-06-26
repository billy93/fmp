(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFFareBasisGroupMappingController', DFFareBasisGroupMappingController);

    DFFareBasisGroupMappingController.$inject = ['$state', 'DFFareBasisGroupMapping', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function DFFareBasisGroupMappingController($state, DFFareBasisGroupMapping, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;
        
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = pagingParams.page;
        vm.loadAll = loadAll;
        
        loadAll();

        function loadAll () {
            DFFareBasisGroupMapping.query({
                page: vm.page - 1,
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
                vm.dFFareBasisGroupMappings = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
    }
})();
