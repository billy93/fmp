(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageWaiverRateSheetDialogController', WorkPackageWaiverRateSheetDialogController);

    WorkPackageWaiverRateSheetDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'entity', 'index', '$stateParams', '$window'];

    function WorkPackageWaiverRateSheetDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, entity, index, $stateParams, $window) {

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

        vm.exportRateSheetCSV = function(){
            var wprs = {
                wp : vm.workPackage,
                ruleText : vm.ruleText,
                index : vm.index,
                header : vm.title
            }
            WorkPackage.exportRateSheetCSVWaiver(wprs, onExportSuccess, onExportFailure);
            function onExportSuccess(result){
                var fileType = "data:text/csv;charset=utf-8,";
                var templateFilename = "RateSheetWaiver.csv";
                var blob = b64toBlob(result.file, fileType);
                FileSaver.saveAs(blob, templateFilename);
            }

            function onExportFailure(){
                alert('Export to CSV failed');
            }
        }

        vm.exportRateSheetScreen = function(data){
            var wprs = {
                wp : vm.workPackage,
                sheetData: data,
                ruleText: vm.ruleText,
                index: vm.index,
                header: vm.title
            }
            var window = $window.open("#/work-package-detail/screen", "_blank");
            window.variable = wprs;
        }

        vm.exportRateSheet  = function(){
      	  console.log(vm.title);
          var wprs = {
        		  wp : vm.workPackage,
        		  ruleText : vm.ruleText,
        		  index : vm.index,
                  header : vm.title
          }
  	  	  WorkPackage.exportRateSheetWaiver(wprs, onExportSuccess, onExportFailure);
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
  	  	  WorkPackage.exportRateSheetExcelWaiver(wprs, onExportSuccess, onExportFailure);
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
    	  	  WorkPackage.exportRateSheetExcelWaiver(wprs, onExportSuccess, onExportFailure);
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
  	  	  WorkPackage.exportRateSheetWordWaiver(wprs, onExportSuccess, onExportFailure);
    	  function onExportSuccess(result){
    		var fileType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
  			var templateFilename = "RateSheet.docx";
  			var blob = b64toBlob(result.file, fileType);
  			FileSaver.saveAs(blob, templateFilename);
    	  }

    	  function onExportFailure(){
    		  alert('Export to word failed');
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
	    	console.log(vm.workPackage.waiverFareSheet[vm.index].fares);
	    	vm.allFares =[]
			for(var x= 0; x<vm.workPackage.waiverFareSheet[vm.index].fares.length; x++){
				vm.allFares.push(vm.workPackage.waiverFareSheet[vm.index].fares[x]);
			}
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
        	vm.selectedHeader = ["type",
        		"fullPartial",
        		"pnr",
        		"tktFrom",
        		"tktTo",
        		"ori",
        		"dest",
        		"originalItinerary",
        		"newItinerary",
        		"originalBasicFare",
        		"newBasicFare",
        		"approvedFares",
        		"fareLost",
        		"calculatedPN",
        		"originalPN",
        		"approvedPN",
        		"penaltyLostPercent",
        		"penaltyLostAmount",
        		"currency",
        		"totalPax",
        		"totalLost",
        		"approver",
        		"remark"];
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
        	case "type" :
        		vm.fares.push(vm.allFares[y].waiverType);
        	    break;
        	case "fullPartial":
        		vm.fares.push(vm.allFares[y].waiverFullPartial);
        	    break;
        	case "pnr" :
        		vm.fares.push(vm.allFares[y].waiverPnr);
        	    break;
        	case "tktFrom" :
        		vm.fares.push(vm.allFares[y].waiverTktFrom);
        	    break;
        	case "tktTo" :
        		vm.fares.push(vm.allFares[y].waiverTktTo);
        	    break;
        	case "ori" :
        		vm.fares.push(vm.allFares[y].waiverOri);
        	    break;
        	case "dest" :
        		vm.fares.push(vm.allFares[y].waiverDest);
        	    break;
        	case "originalItinerary" :
        		vm.fares.push(vm.allFares[y].waiverOriginalItinerary);
        	    break;
        	case "newItinerary" :
        		vm.fares.push(vm.allFares[y].waiverNewItinerary);
        	    break;
        	case "originalBasicFare":
        		vm.fares.push(vm.allFares[y].waiverOriginalBasicFare);
        	    break;
        	case "newBasicFare" :
        		vm.fares.push(vm.allFares[y].waiverNewBasicFare);
        	    break;
        	case "approvedFares":
        		vm.fares.push(vm.allFares[y].waiverApprovedFare);
        	    break;
        	case "fareLost":
        		vm.fares.push(vm.allFares[y].waiverFareLost);
        	    break;
        	case "calculatedPN" :
        		vm.fares.push(vm.allFares[y].waiverCalculatedPn);
        	    break;
        	case "originalPN" :
        		vm.fares.push(vm.allFares[y].waiverOriginalPn);
        	    break;
        	case "approvedPN" :
        		vm.fares.push(vm.allFares[y].waiverApprovedPn);
        	    break;
        	case "penaltyLostPercent" :
        		vm.fares.push(vm.allFares[y].waiverPenaltyLostPercent);
        	    break;
        	case "penaltyLostAmount" :
        		vm.fares.push(vm.allFares[y].waiverPenaltyLostAmount);
        		break;
        	case "currency" :
        		vm.fares.push(vm.allFares[y].waiverCurrency);
        		break;
        	case "totalPax" :
        		vm.fares.push(vm.allFares[y].waiverTotalPax);
        		break;
        	case "totalLost" :
        		vm.fares.push(vm.allFares[y].waiverTotalLost);
        		break;
        	case "approver" :
        		vm.fares.push(vm.allFares[y].waiverApprover);
        		break;
        	case "remark" :
        		vm.fares.push(vm.allFares[y].waiverRemark);
        		break;
        	default:
        		break;
			}
        }

        function setTitle(data){
        	console.log(data);
        	switch (data) {
        	case "type" :
        		vm.title.push("Type");
        	    break;
        	case "fullPartial":
        		vm.title.push("Full/Partial");
        	    break;
        	case "pnr" :
        		vm.title.push("PNR");
        	    break;
        	case "tktFrom" :
        		vm.title.push("Tkt From");
        	    break;
        	case "tktTo" :
        		vm.title.push("Tkt To");
        	    break;
        	case "ori" :
        		vm.title.push("Ori");
        	    break;
        	case "dest" :
        		vm.title.push("Dest");
        	    break;
        	case "originalItinerary" :
        		vm.title.push("Original Itinerary");
        	    break;
        	case "newItinerary" :
        		vm.title.push("New Itinerary");
        	    break;
        	case "originalBasicFare":
        		vm.title.push("Original Basic Fare");
        	    break;
        	case "newBasicFare" :
        		vm.title.push("New Basic Fare");
        	    break;
        	case "approvedFares":
        		vm.title.push("Approved Fares");
        	    break;
        	case "fareLost":
        		vm.title.push("Fare Lost");
        	    break;
        	case "calculatedPN" :
        		vm.title.push("Calculated PN");
        	    break;
        	case "originalPN" :
        		vm.title.push("Original PN");
        	    break;
        	case "approvedPN" :
        		vm.title.push("Approved PN");
        	    break;
        	case "penaltyLostPercent" :
        		vm.title.push("Penalty Lost %");
        	    break;
        	case "penaltyLostAmount" :
        		vm.title.push("Penalty Lost Amount");
        		break;
        	case "currency" :
        		vm.title.push("Currency");
        		break;
        	case "totalPax" :
        		vm.title.push("Total Pax");
        		break;
        	case "totalLost" :
        		vm.title.push("Total Lost");
        		break;
        	case "approver" :
        		vm.title.push("Approver");
        		break;
        	case "remark" :
        		vm.title.push("Remark");
        	default:
        		break;
			}
        }

    }
})();
