(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('BusinessAreaDialogController', BusinessAreaDialogController);

    BusinessAreaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'BusinessArea'];

    function BusinessAreaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, BusinessArea) {
        var vm = this;

        vm.businessArea = entity;
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
            if (vm.businessArea.id !== null) {
                BusinessArea.update(vm.businessArea, onSaveSuccess, onSaveError);
            } else {
                BusinessArea.save(vm.businessArea, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:businessAreaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
