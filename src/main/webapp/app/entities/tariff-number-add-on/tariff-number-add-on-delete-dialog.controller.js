(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('TariffNumberAddOnDeleteController',TariffNumberAddOnDeleteController);

    TariffNumberAddOnDeleteController.$inject = ['$uibModalInstance', 'entity', 'TariffNumberAddOn'];

    function TariffNumberAddOnDeleteController($uibModalInstance, entity, TariffNumberAddOn) {
        var vm = this;

        vm.tariffNumberAddOn = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TariffNumberAddOn.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
