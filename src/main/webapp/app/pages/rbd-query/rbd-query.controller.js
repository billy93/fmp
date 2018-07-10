(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdqueryController', RbdqueryController);

    RbdqueryController.$inject = ['$state', 'Rbdquery', 'ParseLinks', 'AlertService', 'paginationConstants', 'params', 'pagingParams', '$uibModal'];

    function RbdqueryController($state, Rbdquery, ParseLinks, AlertService, paginationConstants, params, pagingParams, $uibModal) {

        var vm = this;

        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;

        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = 1;
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        vm.showTariffModal = showTariffModal;
        vm.showCarrierModal = showCarrierModal;
        vm.clearFilter = clearFilter;
        vm.resetFilter = resetFilter;
        vm.paramCarrier = null;
        vm.paramTarNo = null;
                
        vm.params = params;
        vm.loadAll = loadAll;         
//        vm.loadAll();

        function loadAll () {
        	vm.params.page = vm.page - 1;
        	vm.params.size = vm.itemsPerPage;
        	
//        	vm.queryParams.ruleTarNo = vm.paramTarNo;
//        	vm.queryParams.cxr = vm.paramCarrier;
        	vm.params.cxr = vm.paramCarrier;
        	vm.params.ruleTarNo = vm.paramTarNo;
        	console.log(vm.params);
            Rbdquery.query(vm.params, onSuccess, onError);
            
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
                vm.rbdqueries = data;
//                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
        
        function clearFilter() {
        	vm.params = {
        			cxr: null,
            		ruleTarNo: null,
            		ruleNo: null,
            		effectiveDateFrom: null,
            		chart1: null,
            		chart2: null
        	}
        	vm.paramCarrier = null;
        	vm.params.ruleTarNo = null;
        }
        
        function resetFilter() {
        	vm.clearFilter();
        	vm.loadAll();
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
                $state.go('rbdquery', {}, { reload: false });
            }, function() {
                $state.go('rbdquery');
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
                $state.go('rbdquery', {}, { reload: false });
            }, function() {
                $state.go('rbdquery');
            });
        }
    }
})();
