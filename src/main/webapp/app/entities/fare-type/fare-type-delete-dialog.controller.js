(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareTypeDeleteController',FareTypeDeleteController);

    FareTypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'FareType'];

    function FareTypeDeleteController($uibModalInstance, entity, FareType) {
        var vm = this;

        vm.fareType = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            FareType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
