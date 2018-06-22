(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CcfCarrierDialogController', CcfCarrierDialogController);

    CcfCarrierDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CcfCarrier'];

    function CcfCarrierDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CcfCarrier) {
        var vm = this;

        vm.ccfCarrier = entity;
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
            if (vm.ccfCarrier.id !== null) {
                CcfCarrier.update(vm.ccfCarrier, onSaveSuccess, onSaveError);
            } else {
                CcfCarrier.save(vm.ccfCarrier, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:ccfCarrierUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
