(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CityGroupDialogController', CityGroupDialogController);

    CityGroupDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CityGroup'];

    function CityGroupDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CityGroup) {
        var vm = this;

        vm.cityGroup = entity;
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
            if (vm.cityGroup.id !== null) {
                CityGroup.update(vm.cityGroup, onSaveSuccess, onSaveError);
            } else {
                CityGroup.save(vm.cityGroup, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:cityGroupUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
