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
        vm.types = [];
        
        vm.fareType = {};
        for(var x=0;x<fareTypes.length;x++){
        	vm.fareType[fareTypes[x].name] = fareTypes[x].name;
        }
        console.log(vm.fareType);
        if(vm.workPackage.type == 'REGULAR' && vm.workPackage.targetDistribution == 'MARKET'){
        	 vm.types = ["Market Fares"];
        }
        else if(vm.workPackage.type == 'DISCOUNT'){
        	 vm.types = ["Discount Fares"];
        }
        else if(vm.workPackage.type == 'REGULAR'){
        	 vm.types = ["Fares", "Add-Ons"];
        	 if(!vm.workPackage.attachment){
             	vm.types.push("Attachment");
             }
             if(!vm.workPackage.filingInstruction){
             	vm.types.push("Filing Instruction");
             }
        }
        else if(vm.workPackage.type == 'WAIVER'){
        	 vm.types = ["Waiver Fares"];
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
