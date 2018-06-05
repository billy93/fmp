(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageReuseReplaceConfirmDialogController', WorkPackageReuseReplaceConfirmDialogController);

    WorkPackageReuseReplaceConfirmDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'workPackage', 'businessAreas'];

    function WorkPackageReuseReplaceConfirmDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, workPackage, businessAreas) {

        var vm = this;
        vm.clear = clear;
        vm.workPackage = workPackage;
        vm.businessAreas = businessAreas;
        vm.option = {
        	attachment:false
        };
        
        
        vm.save = function(){
        	vm.workPackage.reuseReplaceConfig.attachment = vm.option.attachment;
        	$uibModalInstance.close(vm.workPackage);
        }
       
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
    }
})();
