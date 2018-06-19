(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('TariffNumberAddOnDialogController', TariffNumberAddOnDialogController);

    TariffNumberAddOnDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TariffNumberAddOn'];

    function TariffNumberAddOnDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TariffNumberAddOn) {
        var vm = this;

        vm.tariffNumberAddOn = entity;
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
            if (vm.tariffNumberAddOn.id !== null) {
                TariffNumberAddOn.update(vm.tariffNumberAddOn, onSaveSuccess, onSaveError);
            } else {
                TariffNumberAddOn.save(vm.tariffNumberAddOn, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:tariffNumberAddOnUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
