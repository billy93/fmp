(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AtpcoMasterTariffDialogController', AtpcoMasterTariffDialogController);

    AtpcoMasterTariffDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TariffNumber'];

    function AtpcoMasterTariffDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TariffNumber) {
        var vm = this;

        vm.tariffNumber = entity;
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
            if (vm.tariffNumber.id !== null) {
                TariffNumber.update(vm.tariffNumber, onSaveSuccess, onSaveError);
            } else {
                TariffNumber.save(vm.tariffNumber, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:tariffNumberUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
