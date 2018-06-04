(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageDiscountRateSheetDialogController', WorkPackageDiscountRateSheetDialogController);

    WorkPackageDiscountRateSheetDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'entity', 'index'];

    function WorkPackageDiscountRateSheetDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, entity, index) {

        var vm = this;
        vm.workPackage = entity;
        vm.allFares =[];
        vm.clear = clear;
        vm.selectedHeader = [];
        vm.header = "";
        vm.removedHeader ="";
        vm.ruleText = "";
        vm.selectHeader = true;
        vm.title = [];
        vm.faresData = [];
        vm.fares = [];
        vm.index = index;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        vm.exportRateSheet  = function(){
      	  console.log(vm.title);
          var wprs = {
        		  wp : vm.workPackage,
        		  ruleText : vm.ruleText,
        		  index : vm.index,
                  header : vm.title
          }
  	  	  WorkPackage.exportRateSheetDiscount(wprs, onExportSuccess, onExportFailure);
    	  function onExportSuccess(result){
    		var fileType = "application/pdf";
  			var templateFilename = "RateSheet.pdf";
  			var blob = b64toBlob(result.file, fileType);
  			FileSaver.saveAs(blob, templateFilename);
    	  }
    	  
    	  function onExportFailure(){
    		  alert('Export to PDF failed');
    	  }    	  
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        vm.exportRateSheetExcel  = function(){
      	  console.log(vm.title);
          var wprs = {
        		  wp : vm.workPackage,
        		  ruleText : vm.ruleText,
        		  index : vm.index,
                  header : vm.title
          }
  	  	  WorkPackage.exportRateSheetExcelDiscount(wprs, onExportSuccess, onExportFailure);
    	  function onExportSuccess(result){
    		var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  			var templateFilename = "RateSheet.xlsx";
  			var blob = b64toBlob(result.file, fileType);
  			FileSaver.saveAs(blob, templateFilename);
    	  }
    	  
    	  function onExportFailure(){
    		  alert('Export to Excel failed');
    	  }    	  
        }
        
        vm.exportRateSheetExcels  = function(){
        	  console.log(vm.title);
            var wprs = {
          		  wp : vm.workPackage,
          		  ruleText : vm.ruleText,
          		  index : vm.index,
                    header : vm.title
            }
    	  	  WorkPackage.exportRateSheetExcelDiscount(wprs, onExportSuccess, onExportFailure);
      	  function onExportSuccess(result){
      		var fileType = "application/vnd.ms-excel";
    			var templateFilename = "RateSheet.xls";
    			var blob = b64toBlob(result.file, fileType);
    			FileSaver.saveAs(blob, templateFilename);
      	  }
      	  
      	  function onExportFailure(){
      		  alert('Export to Excel failed');
      	  }    	  
          }
        
        vm.exportRateSheetWord  = function(){
        	  console.log(vm.title);
            var wprs = {
          		  wp : vm.workPackage,
          		  ruleText : vm.ruleText,
          		  index : vm.index,
                    header : vm.title
            }
    	  	  WorkPackage.exportRateSheetWordDiscount(wprs, onExportSuccess, onExportFailure);
      	  function onExportSuccess(result){
      		var fileType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    			var templateFilename = "RateSheet.docx";
    			var blob = b64toBlob(result.file, fileType);
    			FileSaver.saveAs(blob, templateFilename);
      	  }
      	  
      	  function onExportFailure(){
      		  alert('Export to Excel failed');
      	  }    	  
          }
        
        function b64toBlob(b64Data, contentType, sliceSize) {
        	contentType = contentType || '';
  		  	sliceSize = sliceSize || 512;

  		  	var byteCharacters = atob(b64Data);
  		  	var byteArrays = [];

  		  	for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
  		  		var slice = byteCharacters.slice(offset, offset + sliceSize);

  		  		var byteNumbers = new Array(slice.length);
  		  		for (var i = 0; i < slice.length; i++) {
  		  			byteNumbers[i] = slice.charCodeAt(i);
  		  		}

  		  		var byteArray = new Uint8Array(byteNumbers);

  		  		byteArrays.push(byteArray);
  		  	}

  		  	var blob = new Blob(byteArrays, {type: contentType});

  		  return blob;
        }
                
        vm.submit = function(){
			vm.selectHeader = false;
	
	    	if (vm.title.length > 0){
	        	vm.isNull = false;
	        }else{
	        	vm.isNull = true;
	        }
	    	
	    	vm.allFares =[]
			for(var x= 0; x<vm.workPackage.discountFareSheet[vm.index].fares.length; x++){
				vm.allFares.push(vm.workPackage.discountFareSheet[vm.index].fares[x]);
			}
	    	console.log(vm.allFares);
	
			for(var y=0; y<vm.allFares.length;y++){
				vm.fares = [];
				for (var j = 0; j < vm.selectedHeader.length; j++) {
	            	getFares(vm.selectedHeader[j],y);
				}
				vm.faresData.push(vm.fares);
			}      	
        	
        }
        
        vm.addOriginalAuthorities = function () {       	
        	if (vm.header[0] != undefined) {
        		if(vm.header.length>1){
        			for(var l=0; l<vm.header.length;l++){
        				vm.selectedHeader.push(vm.header[l]);
        				setTitle(vm.header[l]);
        			}
        		}else{
        			vm.selectedHeader.push(vm.header[0]);
    				setTitle(vm.header[0]);
        		}
			}
        }
        vm.addAllOriginalAuthorities = function (){
        	vm.title=[];
        	vm.selectedHeader = [];
        	vm.selectedHeader = ["status",
        		"fbrTariffCode",
        		"loc1Type",
        		"loc1",
        		"loc2Type",
        		"loc2",
        		"baseFareCls",
        		"baseRuleNo",
        		"baseTariffCode",
        		"calcType",
        		"percentOfBaseFare",
        		"currency",
        		"specifiedAmount",
        		"pAXType",
        		"fareType",
        		"tktCode",
        		"tktDes",
        		"baseFareOWRT",
        		"global",
        		"rtgNo",
        		"rtgNoTarno",
        		"newOWRT",
        		"newBkgCd",
        		"travelStart",
        		"travelEnd",
        		"saleStart",
        		"saleEnd",
        		"comment",
        		"travelComplete",
        		"travelCompleteIndicator"];
        	for(var i = 0; i < vm.selectedHeader.length; i++){
            	setTitle(vm.selectedHeader[i]);
        	}
        }
        
        vm.removeAllSelectedAuthorities = function(){
        	vm.selectedHeader = [];
        	vm.title = [];
        }
        
        vm.removeSelectedAuthorities = function () { 
        	var index = vm.title.indexOf(vm.removedHeader[0])
        	console.log(vm.removedHeader[0], index);
        	
        	if (index > -1) {
        		vm.title.splice(index, 1);            
        		vm.selectedHeader.splice(index, 1);   
        		console.log(vm.title, vm.selectedHeader);
        	}         	
        }
        
        function getFares(data,y){
        	switch (data) {
        	case "status" :
        		vm.fares.push(vm.allFares[y].status);
        	    break;
        	case "fbrTariffCode":
        		vm.fares.push(vm.allFares[y].tarcd);            	
        	    break;
        	case "loc1Type" :
        		vm.fares.push(vm.allFares[y].loc1Type);
        	    break;
        	case "loc1" :
        		vm.fares.push(vm.allFares[y].loc1);
        	    break;
        	case "loc2Type" :
        		vm.fares.push(vm.allFares[y].loc2Type);
        	    break;
        	case "loc2" :
        		vm.fares.push(vm.allFares[y].loc2);
        	    break;
        	case "baseFareCls":
        		vm.fares.push(vm.allFares[y].baseFareBasis);
        	    break;
        	case "baseRuleNo" :
        		vm.fares.push(vm.allFares[y].baseRuleNo);
        	    break;
        	case "baseTariffCode":
        		vm.fares.push(vm.allFares[y].baseTarcd);
        	    break;
        	case "calcType" :
        		vm.fares.push(vm.allFares[y].calcType);
        	    break;
        	case "percentOfBaseFare" :
        		vm.fares.push(vm.allFares[y].percentBaseFare);
        	    break;
        	case "currency" :
        		vm.fares.push(vm.allFares[y].ruleno);
        	    break;
        	case "specifiedAmount" :
        		vm.fares.push(vm.allFares[y].discountSpecifiedAmount);
        	    break;
        	case "pAXType" :
        		vm.fares.push(vm.allFares[y].passengerType);
        		break;
        	case "fareType" :
        		vm.fares.push(vm.allFares[y].fareType);
        		break;
        	case "tktCode" :
        		vm.fares.push(vm.allFares[y].ticketCode);
        		break;
        	case "tktDes" :
        		vm.fares.push(vm.allFares[y].ticketDesignator);
        		break;
        	case "baseFareOWRT" :
        		vm.fares.push(vm.allFares[y].typeOfJourney);        	    
        		break;
        	case "global" :
        		vm.fares.push(vm.allFares[y].global);
        		break;
        	case "rtgNo" :
        		vm.fares.push(vm.allFares[y].rtgno);
        		break;
        	case "rtgNoTarno" :
        		vm.fares.push(vm.allFares[y].rtgnoTarno);
        		break;
        	case "newFareCls" :
        		vm.fares.push(vm.allFares[y].newFareBasis);
        		break;
        	case "newOWRT" :
        		vm.fares.push(vm.allFares[y].newTypeOfJourney);
        		break;
        	case "newBkgCd" :
        		vm.fares.push(vm.allFares[y].newBookingCode); 
        		break;
        	case "travelStart" :
        		vm.fares.push(vm.allFares[y].travelStart);
        		break;
        	case "travelEnd" :
        		vm.fares.push(vm.allFares[y].travelEnd);              		
        		break;
        	case "saleStart" :
        		vm.fares.push(vm.allFares[y].saleStart);
        		break;
        	case "saleEnd" :
        		vm.fares.push(vm.allFares[y].saleEnd);
        		break;
        	case "comment" :
        		vm.fares.push(vm.allFares[y].comment);              		
        		break;
        	case "travelComplete" :
        		vm.fares.push(vm.allFares[y].travelComplete);               		
        		break;
        	case "travelCompleteIndicator" :
        		vm.fares.push(vm.allFares[y].travelCompleteIndicator);	               		
        		break;
        	default:
        		break;
			}
        }
        
        function setTitle(data){
        	console.log(data);
        	switch (data) {
        	case "status" :
        		vm.title.push("Status");
        	    break;
        	case "fbrTariffCode":
        		vm.title.push("FBR Tariff Code");            	
        	    break;
        	case "loc1Type" :
        		vm.title.push("Loc 1 Type");
        	    break;
        	case "loc1" :
        		vm.title.push("Loc 1");
        	    break;
        	case "loc2Type" :
        		vm.title.push("Loc 2 Type");
        	    break;
        	case "loc2" :
        		vm.title.push("loc2");
        	    break;
        	case "baseFareCls" :
        		vm.title.push("Base FareCls");
        	    break;
        	case "baseRuleNo" :
        		vm.title.push("Base Rule No");
        	    break;
        	case "baseTariffCode" :
        		vm.title.push("Base Tariff Code");
        	    break;
        	case "calcType":
        		vm.title.push("Calc Type");
        	    break;
        	case "percentOfBaseFare" :
        		vm.title.push("% of Base Fare");
        	    break;
        	case "currency":
        		vm.title.push("currency");
        	    break;
        	case "specifiedAmount":
        		vm.title.push("Specified Amount");
        	    break;
        	case "pAXType" :
        		vm.title.push("PAX Type");
        	    break;
        	case "fareType" :
        		vm.title.push("Fare Type");
        	    break;
        	case "tktCode" :
        		vm.title.push("Tkt Code");
        	    break;
        	case "tktDes" :
        		vm.title.push("Tkt Des");
        	    break;
        	case "baseFareOWRT" :
        		vm.title.push("Base Fare OW/RT");
        		break;
        	case "global" :
        		vm.title.push("Global");
        		break;
        	case "rtgNo" :
        		vm.title.push("Rtg No");
        		break;
        	case "rtgNoTarno" :
        		vm.title.push("Rtg No Tarno");
        		break;
        	case "newFareCls" :
        		vm.title.push("New FareCls");        	    
        		break;
        	case "newOWRT" :
        		vm.title.push("New OW/RT");
        		break;
        	case "newBkgCd" :
        		vm.title.push("New BkgCd");
        		break;
        	case "travelStart" :
        		vm.title.push("Travel Start");
        		break;
        	case "travelEnd" :
        		vm.title.push("Travel End");   
        		break;
        	case "saleStart" :
        		vm.title.push("Sales Start");
        		break;
        	case "saleEnd" :
        		vm.title.push("Sales End");
        		break;
        	case "comment" :
        		vm.title.push("Comment");  
        		break;
        	case "travelComplete" :
        		vm.title.push("Travel Complete"); 
        		break;
        	case "travelCompleteIndicator" :
        		vm.title.push("Travel Complete Indicator");  
        		break;
        	default:
        		break;
			}
        }

    }
})();
