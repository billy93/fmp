(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CityCodestoAirportCodesDeleteController',CityCodestoAirportCodesDeleteController);

    CityCodestoAirportCodesDeleteController.$inject = ['$uibModalInstance', 'entity', 'CityCodestoAirportCodes'];

    function CityCodestoAirportCodesDeleteController($uibModalInstance, entity, CityCodestoAirportCodes) {
        var vm = this;

        vm.airportMapingDataFeed = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
        	CityCodestoAirportCodes.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
