(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CcfCarrierDeleteController',CcfCarrierDeleteController);

    CcfCarrierDeleteController.$inject = ['$uibModalInstance', 'entity', 'CcfCarrier'];

    function CcfCarrierDeleteController($uibModalInstance, entity, CcfCarrier) {
        var vm = this;

        vm.ccfCarrier = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CcfCarrier.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
