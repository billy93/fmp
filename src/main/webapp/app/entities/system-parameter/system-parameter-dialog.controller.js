(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('SystemParameterDialogController', SystemParameterDialogController);

    SystemParameterDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'SystemParameter'];

    function SystemParameterDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, SystemParameter) {
        var vm = this;

        vm.systemParameter = entity;
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
            if (vm.systemParameter.id !== null) {
                SystemParameter.update(vm.systemParameter, onSaveSuccess, onSaveError);
            } else {
                SystemParameter.save(vm.systemParameter, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:systemParameterUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
