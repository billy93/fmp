(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CityGroupController', CityGroupController);

    CityGroupController.$inject = ['$state', 'CityGroup', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function CityGroupController($state, CityGroup, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;

        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;

        loadAll();

        
        
        function loadAll () {
            CityGroup.query({
            	"code" : vm.cityGroupCode,
            	"description" : vm.cityGroupDescription,
            	"cities.cityCode" : vm.cityCode,
            	"operator" : vm.operator,
            	"cities.countryCode" : vm.countryCode,
            	
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
                vm.cityGroups = data;
                vm.page = pagingParams.page;
                if(vm.operator == null){
                	vm.operator = "or";
                }
            }
            function onError(error) {
                AlertService.error(error.data.message);
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
                search: vm.currentSearch
            });
        }
        
        vm.queryFilter = function (){
        	loadAll();
        }
        
        vm.clearFilter = function (){
        	//TODO
        }
        
        vm.resetFilter = function (){
        	vm.cityGroupCode = null;
        	vm.cityGroupDescription = null;
        	vm.cityCode = null;
        	vm.operator = null;
        	vm.countryCode = null;
        }
    }
})();
