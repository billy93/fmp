(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CityGroupDeleteController',CityGroupDeleteController);

    CityGroupDeleteController.$inject = ['$uibModalInstance', 'entity', 'CityGroup'];

    function CityGroupDeleteController($uibModalInstance, entity, CityGroup) {
        var vm = this;

        vm.cityGroup = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CityGroup.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
