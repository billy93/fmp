(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdMappingDeleteController',RbdMappingDeleteController);

    RbdMappingDeleteController.$inject = ['$uibModalInstance', 'entity', 'RbdMapping'];

    function RbdMappingDeleteController($uibModalInstance, entity, RbdMapping) {
        var vm = this;

        vm.rbdMapping = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RbdMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
