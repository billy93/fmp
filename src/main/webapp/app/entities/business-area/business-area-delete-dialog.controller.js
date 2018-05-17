(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('BusinessAreaDeleteController',BusinessAreaDeleteController);

    BusinessAreaDeleteController.$inject = ['$uibModalInstance', 'entity', 'BusinessArea'];

    function BusinessAreaDeleteController($uibModalInstance, entity, BusinessArea) {
        var vm = this;

        vm.businessArea = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            BusinessArea.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
