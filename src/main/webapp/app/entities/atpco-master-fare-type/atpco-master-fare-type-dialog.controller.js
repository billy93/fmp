(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AtpcoMasterFareTypeDialogController', AtpcoMasterFareTypeDialogController);

    AtpcoMasterFareTypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AtpcoMasterFareType'];

    function AtpcoMasterFareTypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AtpcoMasterFareType) {
        var vm = this;

        vm.atpcoMasterFareType = entity;
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
            if (vm.atpcoMasterFareType.id !== null) {
                AtpcoMasterFareType.update(vm.atpcoMasterFareType, onSaveSuccess, onSaveError);
            } else {
                AtpcoMasterFareType.save(vm.atpcoMasterFareType, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:atpcoMasterFareTypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
