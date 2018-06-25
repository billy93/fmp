(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFAirportMappingDeleteController',DFAirportMappingDeleteController);

    DFAirportMappingDeleteController.$inject = ['$uibModalInstance', 'entity', 'DFAirportMapping'];

    function DFAirportMappingDeleteController($uibModalInstance, entity, DFAirportMapping) {
        var vm = this;

        vm.dFAirportMapping = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DFAirportMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
