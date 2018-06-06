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
        vm.option = {
        		fareType:"",
        };
        vm.types = [];
        
        console.log(fareTypes);
        vm.fareType = [];
        
//        vm.fareType[""] = "Select Fare Type";
//        vm.fareType["1"] = "Select Fare Type1";
//        vm.fareType["2"] = "Select Fare Type2";
        for(var x=0;x<fareTypes.length;x++){
        	vm.fareType.push({
        		name:fareTypes[x].name
        	});
//        	vm.fareType[fareTypes[x].name+""+x] = fareTypes[x].name;
        }

        if(vm.workPackage.type == 'REGULAR' && vm.workPackage.targetDistribution == 'MARKET'){
        	 vm.types = ["Market Fares", "Add-Ons"];
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
