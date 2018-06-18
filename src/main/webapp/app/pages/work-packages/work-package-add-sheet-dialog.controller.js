(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageAddSheetDialogController', WorkPackageAddSheetDialogController);

    WorkPackageAddSheetDialogController.$inject = ['workPackage', '$scope', '$uibModalInstance', '$state', 'fareTypes', 'sheet'];

    function WorkPackageAddSheetDialogController(workPackage, $scope, $uibModalInstance, $state, fareTypes, sheet) {

        var vm = this;     
        vm.clear = clear;
        vm.workPackage = workPackage;
        vm.option = {
        		fareType:"",
        };
        vm.types = [];
        
        vm.fareType = [];
        
        for(var x=0;x<fareTypes.length;x++){
        	vm.fareType.push({
        		name:fareTypes[x].name
        	});
        }
        

        if(vm.workPackage.type == 'REGULAR' && vm.workPackage.targetDistribution == 'MARKET'){
        	 vm.types = ["Market Fares", "Add-Ons"];
        }
        else if(vm.workPackage.type == 'DISCOUNT'){
        	 vm.types = ["Discount Fares"];
        }
        else if(vm.workPackage.type == 'REGULAR'){
        	 vm.types = ["Fares", "Add-Ons"];
        	 
        }
        else if(vm.workPackage.type == 'WAIVER'){
        	 vm.types = ["Waiver Fares"];
        }
        
        if(!vm.workPackage.attachment){
         	vm.types.push("Attachment");
         }
         if(!vm.workPackage.filingInstruction){
         	vm.types.push("Filing Instruction");
         }
        
        vm.save = function(){
        	console.log(vm.option);
        	$uibModalInstance.close(vm.option);
        }
        vm.loadAll = function(){
        		
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        if(sheet != null){
        	if(sheet.type == 'fares'){
        		vm.option.type = "Fares";
        		vm.option.name = sheet.sheet.specifiedFaresName;
        		vm.sheet = sheet;
        	}
        	else if(sheet.type == 'market'){
        		vm.option.type = "Market Fares";
        		vm.option.name = sheet.sheet.marketFaresName;
        		vm.sheet = sheet;
        	}
        }
        else{
        	vm.sheet = null;
        }
    }
})();
