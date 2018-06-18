(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DataFeedFareBasisGroupMappingDialogController', DataFeedFareBasisGroupMappingDialogController);

    DataFeedFareBasisGroupMappingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DataFeedFareBasisGroupMapping'];

    function DataFeedFareBasisGroupMappingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DataFeedFareBasisGroupMapping) {
        var vm = this;

        vm.dataFeedFareBasisGroupMapping = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.dataFeedFareBasisGroupMapping.id !== null) {
                DataFeedFareBasisGroupMapping.update(vm.dataFeedFareBasisGroupMapping, onSaveSuccess, onSaveError);
            } else {
                DataFeedFareBasisGroupMapping.save(vm.dataFeedFareBasisGroupMapping, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:dataFeedFareBasisGroupMappingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
