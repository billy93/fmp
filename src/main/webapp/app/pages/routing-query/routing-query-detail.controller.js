(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RoutingqueryDetailController', RoutingqueryDetailController);

    RoutingqueryDetailController.$inject = ['$scope', '$uibModalInstance', 'entity'];

    function RoutingqueryDetailController($scope, $uibModalInstance, entity) {
        var vm = this;

        vm.routingquery = entity;
        vm.selectTab = selectTab;
        vm.print = print;
        vm.exportData = exportData;
        vm.clear = clear;
        
        vm.currentTab = [true, false, false];

        function selectTab(index) {
        	vm.currentTab = [false, false, false];
        	vm.currentTab[index] = true;
        }
        
        function print() {
        }
        
        function exportData() {
        }
        
        function clear() {
            $uibModalInstance.dismiss('cancel');
        }
    }
    
})();
