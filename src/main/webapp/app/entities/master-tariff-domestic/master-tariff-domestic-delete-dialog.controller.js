(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('MasterTariffDomesticDeleteController',MasterTariffDomesticDeleteController);

    MasterTariffDomesticDeleteController.$inject = ['$uibModalInstance', 'entity', 'MasterTariffDomestic'];

    function MasterTariffDomesticDeleteController($uibModalInstance, entity, MasterTariffDomestic) {
        var vm = this;

        vm.masterTariffDomestic = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
        	MasterTariffDomestic.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
