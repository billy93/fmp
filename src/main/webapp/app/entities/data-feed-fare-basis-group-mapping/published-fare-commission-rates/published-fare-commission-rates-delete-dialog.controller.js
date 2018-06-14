(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('PublishedFareCommissionRatesDeleteController',PublishedFareCommissionRatesDeleteController);

    PublishedFareCommissionRatesDeleteController.$inject = ['$uibModalInstance', 'entity', 'PublishedFareCommissionRates'];

    function PublishedFareCommissionRatesDeleteController($uibModalInstance, entity, PublishedFareCommissionRates) {
        var vm = this;

        vm.publishedFareCommissionRates = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
        	PublishedFareCommissionRates.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
