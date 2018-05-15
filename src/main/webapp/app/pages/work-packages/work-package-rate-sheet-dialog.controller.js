(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageRateSheetDialogController', WorkPackageRateSheetDialogController);

    WorkPackageRateSheetDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'entity'];

    function WorkPackageRateSheetDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, entity) {

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
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        vm.exportRateSheet  = function(){
  	  	  WorkPackage.exportRateSheet(vm.workPackage, onExportSuccess, onExportFailure);
    	  function onExportSuccess(result){
    		  var fileType = "application/pdf";
  			var templateFilename = "RateSheet.pdf";
  			var blob = b64toBlob(result.file, fileType);
  			FileSaver.saveAs(blob, templateFilename);
    	  }
    	  
    	  function onExportFailure(){
    		  
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
        	
        	for (var i = 0; i < vm.workPackage.fares.length; i++) {
        		vm.fares = [];
        		vm.allFares = vm.workPackage.fares[i];
            	for (var j = 0; j < vm.selectedHeader.length; j++) {
                	getFares(vm.selectedHeader[j]);
    			}
            	console.log(vm.fares)
            	vm.faresData.push(vm.fares);
			}
        	console.log(vm.faresData)

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
        
        function getFares(data){
        	switch (data) {
        	case "status" :
        		vm.fares.push(vm.allFares.status);
        	    break;
        	case "carrier":
        		vm.fares.push(vm.allFares.carrier);            	
        	    break;
        	case "action" :
        		vm.fares.push(vm.allFares.action);
        	    break;
        	case "tarno" :
        		vm.fares.push(vm.allFares.tarno);
        	    break;
        	case "tarcd" :
        		vm.fares.push(vm.allFares.tarcd);
        	    break;
        	case "global" :
        		vm.fares.push(vm.allFares.global);
        	    break;
        	case "origin" :
        		vm.fares.push(vm.allFares.origin);
        	    break;
        	case "destination" :
        		vm.fares.push(vm.allFares.destination);
        	    break;
        	case "fareBasis" :
        		vm.fares.push(vm.allFares.fareBasis);
        	    break;
        	case "bookingClass":
        		vm.fares.push(vm.allFares.bookingClass);
        	    break;
        	case "cabin" :
        		vm.fares.push(vm.allFares.cabin);
        	    break;
        	case "typeOfJourney":
        		vm.fares.push(vm.allFares.typeOfJourney);
        	    break;
        	case "footnote" :
        		vm.fares.push(vm.allFares.footnote);
        	    break;
        	case "rtgno" :
        		vm.fares.push(vm.allFares.rtgno);
        	    break;
        	case "ruleno" :
        		vm.fares.push(vm.allFares.ruleno);
        	    break;
        	case "currency" :
        		vm.fares.push(vm.allFares.currency);
        	    break;
        	case "amount" :
        		vm.fares.push(vm.allFares.amount);
        		break;
        	case "amtDiff" :
        		var amtDiff = 0;
        		amtDiff = vm.allFares.recommendedAmount - vm.allFares.amount;
        		vm.fares.push(amtDiff);
        		break;
        	case "percentAmtDiff" :
        		var percentAmtDiff = ((vm.allFares.recommendedAmount - vm.allFares.amount)  * 100 / vm.allFares.amount).toFixed(2);
        		vm.fares.push(percentAmtDiff.toString()+" %");

        		break;
        	case "yqyr" :
        		vm.fares.push(vm.allFares.yqyr);

        		break;
        	case "cat12" :
        		vm.fares.push(vm.allFares.cat12);
        	    
        		break;
        	case "totalTaxes" :
        		vm.fares.push(vm.allFares.totalTaxes);

        		break;
        	case "tfc" :
        		vm.fares.push(vm.allFares.tfc);

        		break;
        	case "aif" :
        		vm.fares.push(vm.allFares.aif);

        		break;
        	case "itinerary" :
        		vm.fares.push(vm.allFares.itinerary);

        		break;
        	case "overideIndicator" :
        		vm.fares.push(vm.allFares.overideIndicator);

        		break;
        	case "travelStart" :
        		vm.fares.push(vm.allFares.travelStart);

        		break;
        	case "travelEnd" :
        		vm.fares.push(vm.allFares.travelEnd);	               		

        		break;
        	case "saleStart" :
        		vm.fares.push(vm.allFares.saleStart);

        		break;
        	case "saleEnd" :
        		vm.fares.push(vm.allFares.saleEnd);

        		break;
        	case "effDt" :
        		vm.fares.push(vm.allFares.effDt);	 

        		break;
        	case "comment" :
        		vm.fares.push(vm.allFares.comment);               		

        		break;
        	case "travelComplete" :
        		vm.fares.push(vm.allFares.travelComplete);               		

        		break;
        	case "travelCompleteIndicator" :
        		vm.fares.push(vm.allFares.travelCompleteIndicator);	               		
 
        		break;
        	case "ratesheetComment" :
        		vm.fares.push(vm.allFares.ratesheetComment);	               		
  
        		break;
        	case "dealCode" :
        		vm.fares.push(vm.allFares.dealCode);

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
