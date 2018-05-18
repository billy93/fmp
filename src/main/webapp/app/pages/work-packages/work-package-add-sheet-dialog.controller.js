(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageAddSheetDialogController', WorkPackageAddSheetDialogController);

    WorkPackageAddSheetDialogController.$inject = ['workPackage', '$scope', '$uibModalInstance', '$state'];

    function WorkPackageAddSheetDialogController(workPackage, $scope, $uibModalInstance, $state) {

        var vm = this;     
        vm.clear = clear;
        vm.workPackage = workPackage;
        vm.types = ["Fares", "Add-Ons"];
        
        if(!vm.workPackage.attachment){
        	vm.types.push("Attachment");
        }
        if(!vm.workPackage.filingInstruction){
        	vm.types.push("Filing Instruction");
        }
        
        vm.save = function(){
        	$uibModalInstance.close(vm.option);
        }
        vm.loadAll = function(){
        		
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();
