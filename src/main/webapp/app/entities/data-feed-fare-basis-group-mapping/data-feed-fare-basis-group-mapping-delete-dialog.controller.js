(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DataFeedFareBasisGroupMappingDeleteController',DataFeedFareBasisGroupMappingDeleteController);

    DataFeedFareBasisGroupMappingDeleteController.$inject = ['$uibModalInstance', 'entity', 'DataFeedFareBasisGroupMapping'];

    function DataFeedFareBasisGroupMappingDeleteController($uibModalInstance, entity, DataFeedFareBasisGroupMapping) {
        var vm = this;

        vm.dataFeedFareBasisGroupMapping = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DataFeedFareBasisGroupMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
