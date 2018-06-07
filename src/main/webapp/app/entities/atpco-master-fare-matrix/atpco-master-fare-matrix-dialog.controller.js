(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AtpcoMasterFareMatrixDialogController', AtpcoMasterFareMatrixDialogController);

    AtpcoMasterFareMatrixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AtpcoMasterFareMatrix', 'fareTypes'];

    function AtpcoMasterFareMatrixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AtpcoMasterFareMatrix, fareTypes) {
        var vm = this;

        vm.atpcoMasterFareMatrix = entity;
        vm.fareTypes = fareTypes;
        console.log('test');
        console.log(vm.fareTypes);
        
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
            if (vm.atpcoMasterFareMatrix.id !== null) {
                AtpcoMasterFareMatrix.update(vm.atpcoMasterFareMatrix, onSaveSuccess, onSaveError);
            } else {
                AtpcoMasterFareMatrix.save(vm.atpcoMasterFareMatrix, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:atpcoMasterFareMatrixUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
