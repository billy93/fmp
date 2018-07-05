(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('Rec8FareByRuleController', Rec8FareByRuleController);

    Rec8FareByRuleController.$inject = ['$state', '$stateParams', 'Rec8FareByRule', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', '$uibModal'];

    function Rec8FareByRuleController($state, $stateParams, Rec8FareByRule, ParseLinks, AlertService, paginationConstants, pagingParams, $uibModal) {

        var vm = this;
        
        vm.loadAll = loadAll;
        vm.clearFilter = clearFilter;
        vm.resetFilter = resetFilter;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.showCarrierModal = showCarrierModal;
        vm.showTariffModal = showTariffModal;
        
        if($stateParams.rec8FareByRuleFilter != null){
        	vm.queryParams = $stateParams.rec8FareByRuleFilter;
        	vm.itemsPerPage = vm.queryParams.size;
        } else {
        	vm.clearFilter();
        	vm.itemsPerPage = "10";
        }
        
        function loadAll() {
        	vm.queryParams.page = vm.page - 1;
        	vm.queryParams.size = vm.itemsPerPage;
        	
        	vm.queryParams.ruleTarNo = vm.paramTarNo;
        	vm.queryParams.cxr = vm.paramCarrier;
        	
        	Rec8FareByRule.query(vm.queryParams, onSuccess, onError);
        	
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.record8 = data;
            }
            
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function clearFilter() {
        	vm.queryParams = {
        			cxr: null,
        			ruleNo: null,
        			ruleTarNo: null,
            		accountCode: null,
            		includeDisc : null
        	}
        	vm.paramCarrier = null;
        	vm.paramTarNo = null;
        }
        
        function resetFilter() {
        	vm.clearFilter();
        	vm.record8 = null;
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
                $state.go('rule-queries', {}, { reload: false });
            }, function() {
                $state.go('rule-queries');
            });
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
                $state.go('rule-queries', {}, { reload: false });
            }, function() {
                $state.go('rule-queries');
            });
        }
    }
})();
