(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AtpcoMasterFareTypeDeleteController',AtpcoMasterFareTypeDeleteController);

    AtpcoMasterFareTypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'AtpcoMasterFareType'];

    function AtpcoMasterFareTypeDeleteController($uibModalInstance, entity, AtpcoMasterFareType) {
        var vm = this;

        vm.atpcoMasterFareType = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AtpcoMasterFareType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
