(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFFareBasisGroupMappingDeleteController',DFFareBasisGroupMappingDeleteController);

    DFFareBasisGroupMappingDeleteController.$inject = ['$uibModalInstance', 'entity', 'DFFareBasisGroupMapping'];

    function DFFareBasisGroupMappingDeleteController($uibModalInstance, entity, DFFareBasisGroupMapping) {
        var vm = this;

        vm.dFFareBasisGroupMapping = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DFFareBasisGroupMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
