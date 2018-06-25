(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFPublishedFareRatesDeleteController',DFPublishedFareRatesDeleteController);

    DFPublishedFareRatesDeleteController.$inject = ['$uibModalInstance', 'entity', 'DFPublishedFareRates'];

    function DFPublishedFareRatesDeleteController($uibModalInstance, entity, DFPublishedFareRates) {
        var vm = this;

        vm.dFPublishedFareRates = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DFPublishedFareRates.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
