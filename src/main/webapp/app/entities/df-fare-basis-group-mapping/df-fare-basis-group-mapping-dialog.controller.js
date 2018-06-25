(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFFareBasisGroupMappingDialogController', DFFareBasisGroupMappingDialogController);

    DFFareBasisGroupMappingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DFFareBasisGroupMapping'];

    function DFFareBasisGroupMappingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DFFareBasisGroupMapping) {
        var vm = this;

        vm.dFFareBasisGroupMapping = entity;
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
            if (vm.dFFareBasisGroupMapping.id !== null) {
                DFFareBasisGroupMapping.update(vm.dFFareBasisGroupMapping, onSaveSuccess, onSaveError);
            } else {
                DFFareBasisGroupMapping.save(vm.dFFareBasisGroupMapping, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:dFFareBasisGroupMappingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
