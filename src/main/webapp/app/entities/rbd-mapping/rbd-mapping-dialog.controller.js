(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdMappingDialogController', RbdMappingDialogController);

    RbdMappingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RbdMapping'];

    function RbdMappingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RbdMapping) {
        var vm = this;

        vm.rbdMapping = entity;
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
            if (vm.rbdMapping.id !== null) {
                RbdMapping.update(vm.rbdMapping, onSaveSuccess, onSaveError);
            } else {
                RbdMapping.save(vm.rbdMapping, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:rbdMappingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
