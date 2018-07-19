(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdColorMappingDeleteController',RbdColorMappingDeleteController);

    RbdColorMappingDeleteController.$inject = ['$uibModalInstance', 'entity', 'RbdColorMapping'];

    function RbdColorMappingDeleteController($uibModalInstance, entity, RbdColorMapping) {
        var vm = this;

        vm.rbdColorMapping = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RbdColorMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
