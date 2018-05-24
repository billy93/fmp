(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageAddSheetDialogController', WorkPackageAddSheetDialogController);

    WorkPackageAddSheetDialogController.$inject = ['workPackage', '$scope', '$uibModalInstance', '$state', 'fareTypes'];

    function WorkPackageAddSheetDialogController(workPackage, $scope, $uibModalInstance, $state, fareTypes) {

        var vm = this;     
        vm.clear = clear;
        vm.workPackage = workPackage;
        vm.types = ["Fares", "Add-Ons"];
        vm.fareType = {};
        for(var x=0;x<fareTypes.length;x++){
        	vm.fareType[fareTypes[x].name] = fareTypes[x].name;
        }
        
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
