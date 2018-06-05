(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageExportSelectColumnDialogController', WorkPackageExportSelectColumnDialogController);

    WorkPackageExportSelectColumnDialogController.$inject = ['$uibModalInstance', '$uibModal'];

    function WorkPackageExportSelectColumnDialogController ($uibModalInstance, $uibModal) {
        var vm = this;

        vm.clear = clear;
        vm.save = save;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.workPackage.id !== null) {
                WorkPackage.update(vm.workPackage, onSaveSuccess, onSaveError);
            } else {
                WorkPackage.save(vm.workPackage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:workPackageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    }
})();
