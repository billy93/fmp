(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFOriginDestinationDeleteController',DFOriginDestinationDeleteController);

    DFOriginDestinationDeleteController.$inject = ['$uibModalInstance', 'entity', 'DFOriginDestination'];

    function DFOriginDestinationDeleteController($uibModalInstance, entity, DFOriginDestination) {
        var vm = this;

        vm.dFOriginDestination = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DFOriginDestination.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
