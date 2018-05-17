(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('ReviewLevelDialogController', ReviewLevelDialogController);

    ReviewLevelDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ReviewLevel'];

    function ReviewLevelDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ReviewLevel) {
        var vm = this;

        vm.reviewLevel = entity;
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
            if (vm.reviewLevel.id !== null) {
                ReviewLevel.update(vm.reviewLevel, onSaveSuccess, onSaveError);
            } else {
                ReviewLevel.save(vm.reviewLevel, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:reviewLevelUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
