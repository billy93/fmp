(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('TariffNumberDeleteController',TariffNumberDeleteController);

    TariffNumberDeleteController.$inject = ['$uibModalInstance', 'entity', 'TariffNumber'];

    function TariffNumberDeleteController($uibModalInstance, entity, TariffNumber) {
        var vm = this;

        vm.tariffNumber = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TariffNumber.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
