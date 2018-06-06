(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AtpcoMasterFareMatrixDeleteController',AtpcoMasterFareMatrixDeleteController);

    AtpcoMasterFareMatrixDeleteController.$inject = ['$uibModalInstance', 'entity', 'AtpcoMasterFareMatrix'];

    function AtpcoMasterFareMatrixDeleteController($uibModalInstance, entity, AtpcoMasterFareMatrix) {
        var vm = this;

        vm.atpcoMasterFareMatrix = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AtpcoMasterFareMatrix.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
