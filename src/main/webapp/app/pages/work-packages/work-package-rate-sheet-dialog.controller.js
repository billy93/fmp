(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageRateSheetDialogController', WorkPackageRateSheetDialogController);

    WorkPackageRateSheetDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'entity', 'index'];

    function WorkPackageRateSheetDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, entity, index) {

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
  	  	  WorkPackage.exportRateSheet(wprs, onExportSuccess, onExportFailure);
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
  	  	  WorkPackage.exportRateSheetExcel(wprs, onExportSuccess, onExportFailure);
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
        
//        vm.submit = function(){
//        	vm.selectHeader = false;
//
//        	if (vm.title.length > 0){
//            	vm.isNull = false;
//            }else{
//            	vm.isNull = true;
//            }
//        	        	
//        	vm.allFares =[]
//    		for(var x= 0; x<vm.workPackage.fareSheet[vm.index].fares.length; x++){
//    			vm.allFares.push(vm.workPackage.fareSheet[vm.index].fares[x]);
//    		}
//
//    		for(var y=0; y<vm.allFares.length;y++){
//    			vm.fares = [];
//    			for (var j = 0; j < vm.selectedHeader.length; j++) {
//                	getFares(vm.selectedHeader[j],y);
//    			}
//    			vm.faresData.push(vm.fares);
//    		}
//        	
//        }
        
        vm.submit = function(){
        	if(vm.workPackage.targetDistribution == "ATPCO"){
        		vm.selectHeader = false;

            	if (vm.title.length > 0){
                	vm.isNull = false;
                }else{
                	vm.isNull = true;
                }
            	        	
            	vm.allFares =[]
        		for(var x= 0; x<vm.workPackage.fareSheet[vm.index].fares.length; x++){
        			vm.allFares.push(vm.workPackage.fareSheet[vm.index].fares[x]);
        		}

        		for(var y=0; y<vm.allFares.length;y++){
        			vm.fares = [];
        			for (var j = 0; j < vm.selectedHeader.length; j++) {
                    	getFares(vm.selectedHeader[j],y);
        			}
        			vm.faresData.push(vm.fares);
        		}
        	}else if(vm.workPackage.targetDistribution == "MARKET"){
        		vm.selectHeader = false;

            	if (vm.title.length > 0){
                	vm.isNull = false;
                }else{
                	vm.isNull = true;
                }
            	console.log(vm.title.length); 	
            	vm.allFares =[]
        		for(var x= 0; x<vm.workPackage.marketFareSheet[vm.index].fares.length; x++){
        			vm.allFares.push(vm.workPackage.marketFareSheet[vm.index].fares[x]);
        		}

        		for(var y=0; y<vm.allFares.length;y++){
        			vm.fares = [];
        			for (var j = 0; j < vm.selectedHeader.length; j++) {
                    	getFares(vm.selectedHeader[j],y);
        			}
        			vm.faresData.push(vm.fares);
        		}
        	}        	
        	
        }
        
        vm.addOriginalAuthorities = function () {       	
        	if (vm.header[0] != undefined) {
        		if (!vm.selectedHeader.includes(vm.header[0])) {
                	vm.selectedHeader.push(vm.header[0]);
                	setTitle(vm.header[0]);

                } 
			}
        }
        vm.addAllOriginalAuthorities = function (){
        	vm.title=[];
        	vm.selectedHeader = [];
        	vm.selectedHeader = ["status",
        		"carrier",
        		"action",
        		"tarno",
        		"tarcd",
        		"global",
        		"origin",
        		"destination",
        		"fareBasis",
        		"bookingClass",
        		"cabin",
        		"typeOfJourney",
        		"footnote",
        		"rtgno",
        		"ruleno",
        		"currency",
        		"amount",
        		"amtDiff",
        		"percentAmtDiff",
        		"yqyr",
        		"cat12",
        		"totalTaxes",
        		"tfc",
        		"aif",
        		"itinerary",
        		"overideIndicator",
        		"travelStart",
        		"travelEnd",
        		"saleStart",
        		"saleEnd",
        		"effDt",
        		"comment",
        		"travelComplete",
        		"travelCompleteIndicator",
        		"ratesheetComment",
        		"dealCode"];
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
        	case "carrier":
        		vm.fares.push(vm.allFares[y].carrier);            	
        	    break;
        	case "action" :
        		vm.fares.push(vm.allFares[y].action);
        	    break;
        	case "tarno" :
        		if(vm.allFares[y].tariffNumber != null){
            		vm.fares.push(vm.allFares[y].tariffNumber.tarNo);
            	    break;        			
        		}else{
        			vm.fares.push(null);
        			break;
        		}
        	case "tarcd" :
        		if(vm.allFares[y].tariffNumber != null){
            		vm.fares.push(vm.allFares[y].tariffNumber.tarCd);
            	    break;        			
        		}else{
        			vm.fares.push(null);
        			break;
        		}
        	case "global" :
        		if(vm.allFares[y].tariffNumber != null){
            		vm.fares.push(vm.allFares[y].tariffNumber.global);
            	    break;        			
        		}else{
        			vm.fares.push(null);
        			break;
        		}
        	case "origin" :
        		vm.fares.push(vm.allFares[y].origin);
        	    break;
        	case "destination" :
        		vm.fares.push(vm.allFares[y].destination);
        	    break;
        	case "fareBasis" :
        		vm.fares.push(vm.allFares[y].fareBasis);
        	    break;
        	case "bookingClass":
        		vm.fares.push(vm.allFares[y].bookingClass);
        	    break;
        	case "cabin" :
        		vm.fares.push(vm.allFares[y].cabin);
        	    break;
        	case "typeOfJourney":
        		vm.fares.push(vm.allFares[y].typeOfJourney);
        	    break;
        	case "footnote" :
        		vm.fares.push(vm.allFares[y].footnote);
        	    break;
        	case "rtgno" :
        		vm.fares.push(vm.allFares[y].rtgno);
        	    break;
        	case "ruleno" :
        		vm.fares.push(vm.allFares[y].ruleno);
        	    break;
        	case "currency" :
        		vm.fares.push(vm.allFares[y].currency);
        	    break;
        	case "amount" :
        		vm.fares.push(vm.allFares[y].amount);
        		break;
        	case "amtDiff" :
        		var amtDiff = 0;
        		amtDiff = vm.allFares[y].recommendedAmount - vm.allFares[y].amount;
        		vm.fares.push(amtDiff);
        		break;
        	case "percentAmtDiff" :
        		var percentAmtDiff = ((vm.allFares[y].recommendedAmount - vm.allFares[y].amount)  * 100 / vm.allFares[y].amount).toFixed(2);
        		vm.fares.push(percentAmtDiff.toString()+" %");

        		break;
        	case "yqyr" :
        		vm.fares.push(vm.allFares[y].yqyr);

        		break;
        	case "cat12" :
        		vm.fares.push(vm.allFares[y].cat12);
        	    
        		break;
        	case "totalTaxes" :
        		vm.fares.push(vm.allFares[y].totalTaxes);

        		break;
        	case "tfc" :
        		vm.fares.push(vm.allFares[y].tfc);

        		break;
        	case "aif" :
        		vm.fares.push(vm.allFares[y].aif);

        		break;
        	case "itinerary" :
        		vm.fares.push(vm.allFares[y].itinerary);

        		break;
        	case "overideIndicator" :
        		vm.fares.push(vm.allFares[y].overideIndicator);

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
        	case "effDt" :
        		vm.fares.push(vm.allFares[y].effDt);	 

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
        	case "ratesheetComment" :
        		vm.fares.push(vm.allFares[y].ratesheetComment);	               		
  
        		break;
        	case "dealCode" :
        		vm.fares.push(vm.allFares[y].dealCode);

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
        	case "carrier":
        		vm.title.push("Carrier");            	
        	    break;
        	case "action" :
        		vm.title.push("Action");
        	    break;
        	case "tarno" :
        		vm.title.push("Tar No");
        	    break;
        	case "tarcd" :
        		vm.title.push("Tar Cd");
        	    break;
        	case "global" :
        		vm.title.push("Global");
        	    break;
        	case "origin" :
        		vm.title.push("Origin");
        	    break;
        	case "destination" :
        		vm.title.push("Destination");
        	    break;
        	case "fareBasis" :
        		vm.title.push("Fare Class");
        	    break;
        	case "bookingClass":
        		vm.title.push("Booking Class");
        	    break;
        	case "cabin" :
        		vm.title.push("Cabin");
        	    break;
        	case "typeOfJourney":
        		vm.title.push("OW/RT");
        	    break;
        	case "footnote" :
        		vm.title.push("Footnote");
        	    break;
        	case "rtgno" :
        		vm.title.push("Routing No");
        	    break;
        	case "ruleno" :
        		vm.title.push("Rule No");
        	    break;
        	case "currency" :
        		vm.title.push("Currency");
        	    break;
        	case "amount" :
        		vm.title.push("Base Amt");
        		break;
        	case "amtDiff" :
        		vm.title.push("Amt Different");
        		break;
        	case "percentAmtDiff" :
        		vm.title.push("% Amt Different");

        		break;
        	case "yqyr" :
        		vm.title.push("YQYR");

        		break;
        	case "cat12" :
        		vm.title.push("Cat 12");
        	    
        		break;
        	case "totalTaxes" :
        		vm.title.push("Taxes");

        		break;
        	case "tfc" :
        		vm.title.push("TFC");

        		break;
        	case "aif" :
        		vm.title.push("Target AIF");

        		break;
        	case "itinerary" :
        		vm.title.push("Itinerary");

        		break;
        	case "overideIndicator" :
        		vm.title.push("Override Indicator");

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
        	case "effDt" :
        		vm.title.push("EffDt");	 

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
        	case "ratesheetComment" :
        		vm.title.push("RateSheet Comment");	               		
  
        		break;
        	case "dealCode" :
        		vm.title.push("Deal Code");

        		break;
        	default:
        		break;
			}
        }

    }
})();
