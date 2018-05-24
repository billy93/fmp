(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareTypeDialogController', FareTypeDialogController);

    FareTypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FareType'];

    function FareTypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FareType) {
        var vm = this;

        vm.fareType = entity;
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
            if (vm.fareType.id !== null) {
                FareType.update(vm.fareType, onSaveSuccess, onSaveError);
            } else {
                FareType.save(vm.fareType, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:fareTypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
