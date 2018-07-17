(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RoutingqueryController', RoutingqueryController);

    RoutingqueryController.$inject = ['$state', '$stateParams', 'Routingquery', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', '$uibModal'];

    function RoutingqueryController($state, $stateParams, Routingquery, ParseLinks, AlertService, paginationConstants, pagingParams, $uibModal) {

        var vm = this;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.loadAll = loadAll;
        vm.changeItemsPerPage = changeItemsPerPage;
        vm.clearFilter = clearFilter;
        vm.resetFilter = resetFilter;
        vm.page = 1;
        vm.getDetails = getDetails;
        vm.getFullDetails = getFullDetails;
        vm.showTariffModal = showTariffModal;
        vm.showCarrierModal = showCarrierModal;
        vm.showRoutingDetailModal = showRoutingDetailModal;
        
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

        function loadAll() {
        	vm.queryParams.page = vm.page - 1;
			vm.queryParams.size = vm.itemsPerPage;
			
			vm.queryParams.tarNo = vm.paramTarNo;
			vm.queryParams.carrier = vm.paramCarrier;
			
            Routingquery.query(vm.queryParams, onSuccess, onError);
            
            function onSuccess(data, headers) {
            	vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.routingqueries = data;
            }
            
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus = {};
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
        	};
        	vm.paramTarNo = null;
        }
        
        function resetFilter() {
        	vm.clearFilter();
        	vm.routingqueries = null;
        	vm.routingDetails = null;
        	vm.routingFullDetails = null;
        }
        
        function getDetails(routingQuery) {
        	Routingquery.getDetails(routingQuery, function(data) {
        		vm.routingDetails = data;
        	}, function(error) {
        		AlertService.error(error.data.message);
        	});
        }
        
        function getFullDetails(routingQuery) {
        	Routingquery.getFullDetails(routingQuery, function(data) {
        		vm.routingFullDetails = data;
        	}, function(error) {
        		AlertService.error(error.data.message);
        	});
        }
        
        function changeItemsPerPage() {
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
                $state.go('routingquery', {}, { reload: false });
            }, function() {
                $state.go('routingquery');
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
                $state.go('routingquery', {}, { reload: false });
            }, function() {
                $state.go('routingquery');
            });
        }
        
        function showRoutingDetailModal(routingQuery) {
        	$uibModal.open({
        		templateUrl: 'app/pages/routing-query/routing-query-detail.html',
                controller: 'RoutingqueryDetailController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                	entity: Routingquery.get({id : routingQuery.id}).$promise
                }
            }).result.then(function() {
                $state.go('routingquery', {}, { reload: false });
            }, function() {
                $state.go('routingquery');
            });
        }
    }
})();
