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
        
        if(vm.workPackage.reviewLevel != 'DISTRIBUTION'){
	        if(vm.workPackage.type == 'REGULAR' && vm.workPackage.targetDistribution == 'MARKET'){
	        	 vm.types = ["Market Fares", "Add-Ons"];
	        	 if(!vm.workPackage.filingInstruction){
	     	         	vm.types.push("Filing Instruction");
	     	         }
	        }
	        else if(vm.workPackage.type == 'DISCOUNT'){
	        	 vm.types = ["Discount Fares"];
	        	 if(vm.workPackage.targetDistribution == 'ATPCO'){
	        		 if(!vm.workPackage.filingInstruction){
	     	         	vm.types.push("Filing Instruction");
	     	         }
	        	 }
	        }
	        else if(vm.workPackage.type == 'REGULAR'){
	        	 vm.types = ["Fares", "Add-Ons"];    
	        	 if(vm.workPackage.targetDistribution == 'ATPCO'){
	        		 if(!vm.workPackage.filingInstruction){
	     	         	vm.types.push("Filing Instruction");
	     	         }
	        	 }
	        }
	        else if(vm.workPackage.type == 'WAIVER'){
	        	 vm.types = ["Waiver Fares"];
	        }
        }
        	if(!vm.workPackage.attachment){
        		 if(vm.workPackage.type != 'WAIVER'){
        			 vm.types.push("Attachment");
        		 }
	         }
	         
        
        vm.save = function(){
        	var saveSuccess = true;
        	if(sheet != null){
            	if(sheet.type == 'fares'){
            		if(vm.workPackage.fareSheet.length > 0){
                      	for(var x=0;x<vm.workPackage.fareSheet.length;x++){
                      		if(vm.workPackage.fareSheet[x].specifiedFaresName == vm.option.name){
                      			alert('ERROR: Work sheet name is not unique. Please select another one');
                      			saveSuccess = false;
                      			break;
                      		}
                      	}
                      }
            	}
            	else if(sheet.type == 'addon-fares'){
            		if(vm.workPackage.addonFareSheet.length > 0){
            			for(var x=0;x<vm.workPackage.addonFareSheet.length;x++){
                      		if(vm.workPackage.addonFareSheet[x].addonFaresName == vm.option.name){
                      			alert('ERROR: Work sheet name is not unique. Please select another one');
                      			saveSuccess = false;
                      			break;
                      		}
                      	}
            		}
            	}
            	else if(sheet.type == 'discount'){
            		if(vm.workPackage.discountFareSheet.length > 0){
            			for(var x=0;x<vm.workPackage.discountFareSheet.length;x++){
                      		if(vm.workPackage.discountFareSheet[x].discountFaresName == vm.option.name){
                      			alert('ERROR: Work sheet name is not unique. Please select another one');
                      			saveSuccess = false;
                      			break;
                      		}
                      	}
            		}
            	}
            	else if(sheet.type == 'market'){
            		if(vm.workPackage.marketFareSheet.length > 0){
            			for(var x=0;x<vm.workPackage.marketFareSheet.length;x++){
                      		if(vm.workPackage.marketFareSheet[x].marketFaresName == vm.option.name){
                      			alert('ERROR: Work sheet name is not unique. Please select another one');
                      			saveSuccess = false;
                      			break;
                      		}
                      	}
            		}
            	}
            	else if(sheet.type == 'waiver'){
            		if(vm.workPackage.waiverFareSheet.length > 0){
            			for(var x=0;x<vm.workPackage.waiverFareSheet.length;x++){
                      		if(vm.workPackage.waiverFareSheet[x].waiverFaresName == vm.option.name){
                      			alert('ERROR: Work sheet name is not unique. Please select another one');
                      			saveSuccess = false;
                      			break;
                      		}
                      	}
            		}
            	}
            }
            else{
            	//vm.sheet = null;
            }
        	
//        	console.log(vm.option);
        	if(saveSuccess){
        		$uibModalInstance.close(vm.option);
        	}
        }
        vm.loadAll = function(){
        		
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        if(sheet != null){
        	if(sheet.type == 'fares'){
        		vm.option.type = "Fares";
        		vm.option.name = "Copy of "+sheet.sheet.specifiedFaresName;
        		vm.option.fareType = sheet.sheet.fareType;

        		vm.sheet = sheet;
        	}
        	else if(sheet.type == 'addon-fares'){
        		vm.option.type = "Add-Ons";
        		vm.option.name = "Copy of "+sheet.sheet.addonFaresName;
        		vm.sheet = sheet;
        	}
        	else if(sheet.type == 'discount'){
        		vm.option.type = "Discount Fares";
        		vm.option.name = "Copy of "+sheet.sheet.discountFaresName;
        		vm.option.fareType = sheet.sheet.fareType;

        		vm.sheet = sheet;
        	}
        	else if(sheet.type == 'market'){
        		vm.option.type = "Market Fares";
        		vm.option.name = "Copy of "+sheet.sheet.marketFaresName;
        		vm.sheet = sheet;
        	}
        	else if(sheet.type == 'waiver'){
        		vm.option.type = "Waiver Fares";
        		vm.option.name = "Copy of "+sheet.sheet.waiverFaresName;        		
        		vm.sheet = sheet;
        	}
        }
        else{
        	vm.sheet = null;
        }
    }
})();
