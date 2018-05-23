(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageDetailController', WorkPackageDetailController);
    
    /**
     * @param $uibModal
     * @param DataUtils
     * @param Account
     * @param $scope
     * @param $state
     * @param $rootScope
     * @param $stateParams
     * @param previousState
     * @param entity
     * @param WorkPackage
     * @param ProfileService
     * @param DerivedFares
     * @param Clipboard
     * @returns
     */
    WorkPackageDetailController.$inject = ['currencies','tariffNumber', 'cities', 'FileSaver', '$uibModal', 'DateUtils', 'DataUtils', 'Account', '$scope', '$state', '$rootScope', '$stateParams', 'previousState', 'entity', 'WorkPackage', 'ProfileService', 'user', 'fareTypes', 'GlobalService', 'businessAreas'];
    function WorkPackageDetailController(currencies,tariffNumber, cities, FileSaver, $uibModal, DateUtils, DataUtils, Account, $scope, $state, $rootScope, $stateParams, previousState, entity, WorkPackage, ProfileService, user, fareTypes, GlobalService, businessAreas) {
    	var vm = this;
       
        vm.currentTab = [];
        vm.currentAddonTab = [];
        vm.currentDiscountTab = [];
        vm.currentMarketTab = [];
        vm.currentWaiverTab = [];
         
        vm.rulesMenu = true;
        vm.user = user;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        
        vm.importData = {};
        
        vm.openFile = DataUtils.openFile;
        vm.account = null;
        vm.workPackage = entity;
        vm.tariffNumber = tariffNumber;
        vm.cities = cities;
        vm.currencies = currencies;
        vm.indexSelectedTab = 0;
        
        vm.fareType = {};
        for(var x=0;x<fareTypes.length;x++){
        	vm.fareType[fareTypes[x].name] = fareTypes[x].name;
        }
        
        vm.businessArea = {};
        for(var x=0;x<businessAreas.length;x++){
        	vm.businessArea[businessAreas[x]] = businessAreas[x];
        }
        
//        vm.fareType = {
//    		"":"Select Fare Type", 
//    		"Yearly":"Yearly", 
//    		"Promotion":"Promotion", 
//    		"Ad-hoc":"Ad-hoc", 
//    		"Corporate":"Corporate", 
//    		"SPA & Code-share":"SPA & Code-share",
//    		"Miles":"Miles"
//        };
        
        vm.typeOfJourney = {
    		"":"Select OW/RT", 
    		"OW":"One Way", 
    		"RT":"Return", 
    		"OO":"One Way Only"
        };
        
        vm.cabin = {
    		"":"Select Cabin", 
    		"Y":"Y - Economy", 
    		"F":"F - First Class", 
    		"C":"C - Business", 
    		"R":"R - Premium Economy"
        };
        
        vm.status = {
        	"":"Select Priority",
        	"PENDING":"Pending",
        	"APPROVED":"Approve",
        	"REJECTED":"Reject"
        };
        
        vm.travelCompleteIndicator = {
        	"": "Select Travel Complete Indicator",
        	"P" : "Trip Completed",
        	"c" : "Trip Commence"
        };
        
        vm.locationType = {
        	"": "Select Location Type",
        	"C" : "City",
        	"N" : "Country",
    		"S" : "State",
        	"A" : "Area",
    		"G" : "City Group"
        };
        
        vm.calculationType = {
        	"": "Select Calculation Type",
        	"C" : "Calculated",
        	"S" : "Specified",
        	"M" : "Substract Specified from Calculated"
        };
        
        vm.ssn = {
        	"": "Select SSN Type",
        	"C" : "Christmas",
        	"E" : "Easter",
        	"F" : "4th Level",
            "H" : "High Peak",
            "J" : "Basic",
            "L" : "Low/Off-Peak",
            "N" : "Chinese New Year",
            "O" : "Shoulder",
            "P" : "Peak Of Peak",
            "Q" : "Holiday Surcharge",
            "T" : "5th Level",
            "Y" : "7th Level",
            "Z" : "Second Winter"
        };
        
        vm.fields = [
        	// WORKSHEET HEADER
        	{
        		name:"businessArea",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"status",
        		editable:[],
        		mandatory:[]
        	},
        	{
        		name:"reviewLevel",
        		editable:[],
        		mandatory:[]
        	},
        	{
        		name:"workpackageName",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"filingDate",
        		editable:["LSO", "HO"],
        		mandatory:["HO"]
        	},
        	{
        		name:"saleDate",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"ioComment",
        		editable:["HO"],
        		mandatory:["HO"]
        	},
        	
        	// REGULAR HEADER FARES FIELD
        	{
        		name:"description",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"fareType",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"approvalReference",
        		editable:["HO"],
        		mandatory:["HO"]
        	},
        	
        	//REGULAR FARES FIELD
        	{
        		name:"status",
        		editable:["HO"],
        		mandatory:["HO"]
        	},
        	{
        		name:"carrier",
        		editable:[],
        		mandatory:[]
        	},
        	{
        		name:"tarno",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["HO", "Distribution"]
        	},
        	{
        		name:"tarcd",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["HO", "Distribution"]
        	},
        	{
        		name:"origin",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"destination",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"farebasis",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["HO", "Distribution"]
        	},
        	{
        		name:"bookingClass",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["HO"]
        	},
        	{
        		name:"cabin",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	{
        		name:"typeOfJourney",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	{
        		name:"footnote",
        		editable:["HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"rtgno",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["Distribution"]
        	},
        	{
        		name:"ruleno",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["Distribution"]
        	},
        	{
        		name:"currency",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	{
        		name:"amount",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"aif",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"travelStartDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"travelEndDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"saleStartDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"saleEndDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"comment",
        		editable:["LSO", "HO", "Distribution", "Route Management"],
        		mandatory:[]
        	},
        	{
        		name:"travelComplete",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"travelIndicator",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"ratesheetComment",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	//END REGULAR FARES FIELD
        	
        	// ADDON HEADER FARES FIELD
        	{
        		name:"addonFaresName",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	
        	//ADDON FARES FIELD
        	{
        		name:"addonFareStatus",
        		editable:["HO"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareCarrier",
        		editable:[],
        		mandatory:[]
        	},
        	{
        		name:"addonFareTarno",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["Distribution"]
        	},
        	{
        		name:"addonFareTarcd",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["Distribution"]
        	},
        	{
        		name:"addonFareOrigin",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	{
        		name:"addonFareDestination",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	{
        		name:"addonFareBucket",
        		editable:["HO", "Distribution"],
        		mandatory:["Distribution"]
        	},
        	{
        		name:"addonFareTypeOfJourney",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO","Distribution"]
        	},
        	{
        		name:"addonFareFootnote",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareZone",
        		editable:["HO", "Distribution"],
        		mandatory:["Distribution"]
        	},
        	{
        		name:"addonFareRtgno",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["Distribution"]
        	},
        	{
        		name:"addonFareCurrency",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	{
        		name:"addonFareAmount",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"addonFareTravelStartDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareTravelEndDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareSaleStartDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareSaleEndDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareComment",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareTravelComplete",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareTravelIndicator",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	
        	//DISCOUNT FARE HEADER
        	{
        		name:"discountFaresName",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"discountApprovalReference",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"discountFareType",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"discountAccountCode",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	
        	//DISCOUNT FARE
        	{
        		name:"discountStatus",
        		editable:["HO"],
        		mandatory:[]
        	},
        	{
        		name:"discountTariffCode",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["HO", "Distribution"]
        	},
        	{
        		name:"discountLoc1Type",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	
        	{
        		name:"discountLoc1",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	{
        		name:"discountLoc2Type",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	
        	{
        		name:"discountLoc2",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["LSO", "HO", "Distribution"]
        	},
        	
        	{
        		name:"discountBaseFareBasis",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:["HO", "Distribution"]
        	},
        	{
        		name:"discountBaseRuleno",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountBaseTariffCode",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountCalcType",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"discountPercentageOfBaseFare",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountFareCurrency",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountSpecifiedAmount",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountPaxType",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:["LSO", "HO",  "Distribution"]
        	},
        	{
        		name:"discountFareFareType",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountTicketCode",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	
        	{
        		name:"discountTicketDesignator",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountBaseFareOwRt",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountNewBookingCode",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountTravelStartDate",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountTravelEndDate",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountSaleStartDate",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountSaleEndDate",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountComment",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountTravelComplete",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"discountTravelCompleteIndicator",
        		editable:["LSO", "HO",  "Distribution"],
        		mandatory:[]
        	},
        	
        	// MARKET FARE HEADER
        	{
        		name:"marketFareDescription",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"marketApprovalReference",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareType",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	
        	
        	//MARKET FARE
        	{
        		name:"marketFareStatus",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareOrigin",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareDestination",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareFarebasis",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareBookingClass",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareSsn",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	
        	{
        		name:"marketFareCabin",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	
        	{
        		name:"marketFareTypeOfJourney",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareRuleno",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareCurrency",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareBaseAmt",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},        	        	
        	{
        		name:"marketFareTravelStartDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareTravelEndDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareSaleStartDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareSaleEndDate",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareTravelComplete",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareTravelIndicator",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareRatesheetComment",
        		editable:["LSO", "HO", "Distribution"],
        		mandatory:[]
        	},
        	
        ];
        
        vm.isRequired = function(field){
        	return vm.checkField(field, 'mandatory');        	 
        };
        
        vm.isEditable = function(field){
        	return vm.checkField(field, 'editable');    
        };
        
        vm.checkField = function(field, type){
        	var result = false;
        	if(type == 'mandatory'){
        		for(var x=0;x<vm.fields.length;x++){
        			if(vm.fields[x].name == field){
        				var reviewLevels = vm.fields[x].mandatory;
        				if(reviewLevels.indexOf(vm.workPackage.reviewLevel) > -1){
        					result = true;
        					break;
        				}
        			}
        		}
        	}
        	else if(type == 'editable'){
        		for(var x=0;x<vm.fields.length;x++){
        			if(vm.fields[x].name == field){
        				var reviewLevels = vm.fields[x].editable;
        				if(reviewLevels.indexOf(vm.workPackage.reviewLevel) > -1){
        					result = true;
        					break;
        				}
        			}
        		}
        	}
	  		return result;
	    }
        
        //GENERATE TOUR CODE
        vm.generateTourCode = function(){
        	$uibModal.open({
                templateUrl: 'app/pages/work-packages/work-package-generate-tourcode-dialog.html',
                controller: 'WorkPackageGenerateTourcodeDialogController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full-page-modal',
                resolve: {
                	workPackage: function(){
                		return vm.workPackage;
                	}
                }
			}).result.then(function(option) {
				var findTab = false;
	        	
	        	if(!findTab){
		        	for(var x=0;x<vm.currentTab.length;x++){
		        		if(vm.currentTab[x]){
		        			console.log('Active Fare Tab '+x);
		        			findTab = true;
		        			vm.workPackage.fareSheet[x].approvalReference = option.tourcode;		        			
		        			break;
		        		}
		        	}
	        	}
	        	
	        	if(!findTab){
		        	for(var x=0;x<vm.currentAddonTab.length;x++){
		        		if(vm.currentAddonTab[x]){
		        			console.log('Active Addon Tab '+x);
		        			findTab = true;
		        			vm.workPackage.addonFareSheet[x].approvalReference = option.tourcode;		        			
		        			break;
		        		}
		        	}
	        	}
	        	
	        	if(!findTab){
		        	for(var x=0;x<vm.currentDiscountTab.length;x++){
		        		if(vm.currentAddonTab[x]){
		        			console.log('Active Discount Tab '+x);
		        			findTab = true;
		        			vm.workPackage.discountFareSheet[x].approvalReference = option.tourcode;		        					        			
		        			break;
		        		}
		        	}
	        	}
	        	
	        	if(!findTab){
		        	for(var x=0;x<vm.currentMarketTab.length;x++){
		        		if(vm.currentMarketTab[x]){
		        			console.log('Active Market Tab '+x);
		        			findTab = true;
		        			vm.workPackage.marketFareSheet[x].approvalReference = option.tourcode;		        					        					        			
		        			break;
		        		}
		        	}
	        	}
	        	
	        	
	        	if(!findTab){
		        	for(var x=0;x<vm.currentWaiverTab.length;x++){
		        		if(vm.currentWaiverTab[x]){
		        			console.log('Active Waiver Tab '+x);
		        			findTab = true;
		        			vm.workPackage.waiverFareSheet[x].waiverApprovalReference = option.tourcode;		        					        					        			
		        			break;
		        		}
		        	}
	        	}
            }, function() {
        			
            });
        };
        //END GENERATE TOUR CODE
        
        //Comment TAB
        vm.currentTabComment = true;
        vm.selectCommentTab = function(tab){
        	if(tab == 'comment'){
        		vm.currentTabComment = true;
        		vm.currentTabInterofficeComment = false;
        		vm.currentTabRatesheetComment = false;
        	}
        	else if(tab == 'interofficeComment'){
        		vm.currentTabInterofficeComment = true;
        		vm.currentTabComment = false;
        		vm.currentTabRatesheetComment = false;
           	}
        	else if(tab == 'ratesheetComment'){
        		vm.currentTabRatesheetComment = true;
        		vm.currentTabInterofficeComment = false;
        		vm.currentTabComment = false;
        	}
        }
        
        //END COMMENT TAB
        
        //FARES TAB
        vm.selectedTab = 0;  
        vm.currentTab[0] = true;
        
        vm.selectTab = function(index){
        	vm.resetTab();        	
        	vm.currentTab[index] = true;
        	vm.selectedTab = index;
        	vm.indexSelectedTab = index;
        };
        
        vm.addTab = function(option){
        	if(option.type == 'Fares'){
        		vm.workPackage.specifiedFares = true;
        		vm.workPackage.fareSheet.push({specifiedFaresName:option.name});
        	}
        	else if(option.type == 'Add-Ons'){
        		vm.workPackage.addon = true;
        		vm.workPackage.addonFareSheet.push({addonFaresName:option.name});
        	}
        	else if(option.type == 'Attachment'){
        		vm.workPackage.attachment = true;        		
        	}
        	else if(option.type == 'Filing Instruction'){
        		vm.workPackage.filingInstruction = true;
        		
        	}
        };
        
        vm.removeTab = function(){
        	var findTab = false;
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentTab.length;x++){
	        		if(vm.currentTab[x]){
	        			console.log('Active Fare Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.fareSheet.indexOf(x);
	                	vm.workPackage.fareSheet.splice(index, 1); 
	                	vm.selectedTab = 0;
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentAddonTab.length;x++){
	        		if(vm.currentAddonTab[x]){
	        			console.log('Active Addon Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.addonFareSheet.indexOf(x);
	                	vm.workPackage.addonFareSheet.splice(index, 1); 
	                	vm.selectedAddonTab = 0;
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentDiscountTab.length;x++){
	        		if(vm.currentAddonTab[x]){
	        			console.log('Active Discount Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.discountFareSheet.indexOf(x);
	                	vm.workPackage.discountFareSheet.splice(index, 1); 
	                	vm.selectedDiscountTab = 0;
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentMarketTab.length;x++){
	        		if(vm.currentMarketTab[x]){
	        			console.log('Active Market Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.marketFareSheet.indexOf(x);
	                	vm.workPackage.marketFareSheet.splice(index, 1); 
	                	vm.selectedMarketTab = 0;
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentWavierTab.length;x++){
	        		if(vm.currentWaiverTab[x]){
	        			console.log('Active Waiver Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.waiverFareSheet.indexOf(x);
	                	vm.workPackage.waiverFareSheet.splice(index, 1); 
	                	vm.selectedWaiverTab = 0;
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab && vm.currentTabAttachment){
        		vm.workPackage.attachment = false;
        		findTab = true;
        	}
        	if(!findTab && vm.currentTabFilingInstruction){
        		vm.workPackage.filingInstruction = false;
        		findTab = true;
        	}
        	return findTab;        	
        }
        //END FARES TAB
        
        
        vm.stripFormat = function ($html) {
        	console.log("called");
            return $html.replace(/<img[^>]*>/g/n,"");
          };
        
        //ADDON TAB
        vm.selectedAddonTab = 0;     
//        vm.currentAddonTab[0] = true;
        
        vm.selectAddonTab = function(index){
        	vm.resetTab();        	
        	vm.currentAddonTab[index] = true;
        	vm.selectedAddonTab = index;
        };
        
        vm.addAddonTab = function(){
        	vm.workPackage.addonFareSheet.push({});
        }
        
        vm.removeAddonTab = function(){
        	var index = vm.workPackage.addonFareSheet.indexOf(vm.selectedAddonTab);
        	vm.workPackage.addonFareSheet.splice(index, 1); 
        	vm.selectedAddonTab = 0;
        }
        
        vm.addAddonSheet = function(){
        	vm.addAddonTab();
        };
        
        vm.copyAddonSheet = function(){
          	vm.workPackage.addonFareSheet.push(vm.workPackage.addonFareSheet[vm.selectedAdddonTab]);
        };
        
        vm.removeAddonSheet = function(){
        	vm.removeAddonTab();
        };
        //END ADDON TAB
        
        //DISCOUNT TAB
        vm.selectedDiscountTab = 0; 
        vm.currentDiscountTab[0] = true;
        
        vm.selectDiscountTab = function(index){
        	vm.resetTab();        	
        	vm.currentDiscountTab[index] = true;
        	vm.selectedDiscountTab = index;
        };
        
        vm.addDiscountTab = function(){
        	vm.workPackage.discountFareSheet.push({});
        }
        
        vm.removeDiscountTab = function(){
        	var index = vm.workPackage.discountFareSheet.indexOf(vm.selectedDiscountTab);
        	vm.workPackage.discountFareSheet.splice(index, 1); 
        	vm.selectedDiscountTab = 0;
        }
        
        vm.addDiscountSheet = function(){
        	vm.addDiscountTab();
        };
        
        vm.copyDiscountSheet = function(){
          	vm.workPackage.discountFareSheet.push(vm.workPackage.discountFareSheet[vm.selecteDiscountTab]);
        };
        
        vm.removeDiscountSheet = function(){
        	vm.removeDiscountTab();
        };
        //END DISCOUNT TAB

        //MARKET TAB
        vm.selectedMarketTab = 0;       
        vm.selectMarketTab = function(index){
        	vm.resetTab();        	
        	vm.currentMarketTab[index] = true;
        	vm.selectedMarketTab = index;
        };
        
        vm.addMarketTab = function(){
        	vm.workPackage.marketFareSheet.push({});
        }
        
        vm.removeMarketTab = function(){
        	var index = vm.workPackage.marketFareSheet.indexOf(vm.selectedMarketTab);
        	vm.workPackage.marketFareSheet.splice(index, 1); 
        	vm.selectedMarketTab = 0;
        }
        
        vm.addMarketSheet = function(){
        	vm.addMarketTab();
        };
        
        vm.copyMarketSheet = function(){
          	vm.workPackage.marketFareSheet.push(vm.workPackage.marketFareSheet[vm.selectedMarketTab]);
        };
        
        vm.removeMarketSheet = function(){
        	vm.removeMarketTab();
        };
        //END MARKET TAB
        
        //WAIVER TAB
        vm.selectedWaiverTab = 0;       
        vm.selectWaiverTab = function(index){
        	vm.resetTab();        	
        	vm.currentWaiverTab[index] = true;
        	vm.selectedWaiverTab = index;
        };
        
        vm.addWaiverTab = function(){
        	vm.workPackage.waiverFareSheet.push({});
        }
        
        vm.removeWaiverTab = function(){
        	var index = vm.workPackage.waiverFareSheet.indexOf(vm.selectedWaiverTab);
        	vm.workPackage.waiverFareSheet.splice(index, 1); 
        	vm.selectedWaiverTab = 0;
        }
        
        vm.addWaiverSheet = function(){
        	vm.addWaiverTab();
        };
        
        vm.copyWaiverSheet = function(){
          	vm.workPackage.waiverFareSheet.push(vm.workPackage.waiverFareSheet[vm.selectedWaiverTab]);
        };
        
        vm.removeWaiverSheet = function(){
        	vm.removeWaiverTab();
        };
        //END WAIVER TAB
        
        //OTHER TAB
        vm.selectOtherTab = function(tabName){
        	vm.resetTab();
        	if(tabName == 'filingInstruction'){
        		vm.currentTabFilingInstruction = true;
        	} else if(tabName == 'attachment'){
        		vm.currentTabAttachment = true;
        	} else if(tabName == 'filingDetail'){
        		vm.currentTabFilingDetail = true;
        	} else if(tabName == 'marketRules'){
        		vm.currentTabMarketRules = true;
        	} else if(tabName == 'waiverRules'){
        		vm.currentTabWaiverRules = true;
        	}
        };
        //END OTHER TAB
        
        //RESET TAB FUNCTION
        vm.resetTab = function(){
        	for(var x=0;x<vm.currentTab.length;x++){
        		vm.currentTab[x]=false;
        	}
        	for(var x=0;x<vm.currentAddonTab.length;x++){
        		vm.currentAddonTab[x]=false;
        	}
        	for(var x=0;x<vm.currentDiscountTab.length;x++){
        		vm.currentDiscountTab[x]=false;
        	}
        	for(var x=0;x<vm.currentMarketTab.length;x++){
        		vm.currentMarketTab[x]=false;
        	}
        	for(var x=0;x<vm.currentWaiverTab.length;x++){
        		vm.currentWaiverTab[x]=false;
        	}
        	vm.currentTabFilingInstruction = false;
        	vm.currentTabAttachment = false;
        	vm.currentTabFilingDetail = false;
        	vm.currentTabMarketRules = false;
        	vm.currentTabWaiverRules = false;
        };
        //END RESET TAB FUNCTION
        
        //SHEET FUNCTION
        vm.addSheet = function(){
        	$uibModal.open({
                templateUrl: 'app/pages/work-packages/work-package-add-sheet-dialog.html',
                controller: 'WorkPackageAddSheetDialogController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full-page-modal',
                resolve: {
                	workPackage: function(){
                		return vm.workPackage;
                	}
                }
			}).result.then(function(option) {
				console.log(option);
				vm.addTab(option);
            }, function() {
        			
            });
        };
        
        vm.copySheet = function(){
        	var findTab = false;
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentTab.length;x++){
	        		if(vm.currentTab[x]){
	        			console.log('Active Fare Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.fareSheet.indexOf(x);
	        			vm.workPackage.fareSheet.push(vm.workPackage.fareSheet[x]);
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentAddonTab.length;x++){
	        		if(vm.currentAddonTab[x]){
	        			console.log('Active Addon Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.addonFareSheet.indexOf(x);
	        			vm.workPackage.addonFareSheet.push(vm.workPackage.addonFareSheet[x]);
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentMarketTab.length;x++){
	        		if(vm.currentMarketTab[x]){
	        			console.log('Active Market Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.marketFareSheet.indexOf(x);
	        			vm.workPackage.marketFareSheet.push(vm.workPackage.marketFareSheet[x]);
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
        		alert('Sheet cannot be copied');
        	}
        };
        
        vm.removeSheet = function(){
        	var removed = vm.removeTab();
        	if(!removed){
        		alert('Sheet cannot be deleted');
        	}
        };
        //END SHEET FUNCTION
        
        vm.faresActionButton = [];
        vm.marketFaresActionButton = [];
        vm.discountFaresActionButton = [];
        vm.waiverFaresActionButton = [];
        vm.rowTableClick = function(e, index, type){
        	if(type == 'fares'){
        		vm.faresActionButton[index] = !vm.faresActionButton[index];
        	}
        	else if(type == 'market'){
        		vm.marketFaresActionButton[index] = !vm.marketFaresActionButton[index];
        	}
        	else if(type == 'discount'){
        		vm.discountFaresActionButton[index] = !vm.discountFaresActionButton[index];
        	}
        	else if(type == 'waiver'){
        		vm.waiverFaresActionButton[index] = !vm.waiverFaresActionButton[index];
        	}
        }
        
        vm.errorStyle = { 'background-color':'red'};
        vm.selectedFare = null;
        
        vm.derivedFares  = function() {
        	if (vm.selectedFare) {
        		DerivedFares.get(vm.selectedFare, onSuccess, function(error) {
        			console.log(error);
        		});
        		
        		function onSuccess(result) {
        			$uibModal.open({
    	                templateUrl: 'app/pages/derived-fares/derived-fares.html',
    	                controller: 'DerivedFaresController',
    	                controllerAs: 'vm',
    	                backdrop: 'static',
    	                size: 'lg',
    	                windowClass: 'full-page-modal',
    	                resolve: {
    	                    entity: result.$promise,
    	                    fareSelected: vm.selectedFare
    	                }
        			});
        		}
        	}
        }
        
        vm.paste = function(){
        		Clipboard.findCurrent({}, onFindSuccess, onFindError);
        		
        		function onFindSuccess(result){
        			console.log(result);
        			
        			for(var i=0;i<result.fares.length;i++){
            			if(vm.workPackage.targetDistribution == 'ATPCO' && vm.workPackage.type == 'REGULAR'){
            				vm.workPackage.fares.push({
            					status:"PENDING",
            					carrier:"GA",
            					action:"New",
            					tarno:"008",
            					tarcd:"EUAS",
            					origin:result.fares[i].origin,
            					destination:result.fares[i].destination,
            					fareBasis:result.fares[i].fareBasis,
            					bookingClass:result.fares[i].bookingClass,
            					cabin:result.fares[i].cabinClass,
            					typeOfJourney:result.fares[i].typeOfJourney,
            					rtgno:result.fares[i].routingNo,
            					ruleno:result.fares[i].ruleId,
            					currency:result.fares[i].currency,
            					amount:result.fares[i].amount,
            					aif:result.fares[i].aif,
            					travelStart:result.fares[i].travelStart,
            					travelEnd:result.fares[i].travelEnd,
            					saleStart:result.fares[i].saleStart,
            					saleEnd:result.fares[i].saleEnd,
            					effDt:result.fares[i].effectiveDate,
            					travelComplete:result.fares[i].travelComplete,
            				});
            			}        				
        			}
        		}
        		
        		function onFindError(error){
        			
        		}
        }
        
        vm.pasteAsCancel = function(){
	        	Clipboard.findCurrent({}, onFindSuccess, onFindError);
	    		
	    		function onFindSuccess(result){
	    			console.log(result);
	    			
	    			for(var i=0;i<result.fares.length;i++){
	        			if(vm.workPackage.targetDistribution == 'ATPCO' && vm.workPackage.type == 'REGULAR'){
	        				vm.workPackage.fares.push({
	        					status:"PENDING",
	        					carrier:"GA",
	        					action:"Cancel",
	        					tarno:"008",
	        					tarcd:"EUAS",
	        					origin:result.fares[i].origin,
	        					destination:result.fares[i].destination,
	        					fareBasis:result.fares[i].fareBasis,
	        					bookingClass:result.fares[i].bookingClass,
	        					cabin:result.fares[i].cabinClass,
	        					typeOfJourney:result.fares[i].typeOfJourney,
	        					rtgno:result.fares[i].routingNo,
	        					ruleno:result.fares[i].ruleId,
	        					currency:result.fares[i].currency,
	        					amount:result.fares[i].amount,
	        					aif:result.fares[i].aif,
	        					travelStart:result.fares[i].travelStart,
	        					travelEnd:result.fares[i].travelEnd,
	        					saleStart:result.fares[i].saleStart,
	        					saleEnd:result.fares[i].saleEnd,
	        					effDt:result.fares[i].effectiveDate,
	        					travelComplete:result.fares[i].travelComplete,
	        				});
	        			}        				
	    			}
	    		}
    		
	    		function onFindError(error){
	    			
	    		}
        }
        
 	    //Specific Fares Function
 	    vm.addFares = function(){ 	    	
 	    	if(vm.workPackage.fareSheet[vm.selectedTab].fares == null){
 	    		vm.workPackage.fareSheet[vm.selectedTab].fares = [];
       	  	}
 	    	
    		vm.workPackage.fareSheet[vm.selectedTab].fares.push({
    			status:"PENDING",
    			action:"New",
  	 	      	carrier:"GA"
    		});
 	    }
 	    
        vm.rowFaresSelected = function(workPackageFare){
        	vm.selectedFare = workPackageFare;
	    }
        
        vm.deleteFaresSelected = function(idx){
    		vm.workPackage.fareSheet[vm.selectedTab].fares.splice(idx, 1);  
    		vm.faresActionButton[idx] = false;
	    }
        
        vm.duplicateFaresSelected = function(){
    		vm.workPackage.fares.push(JSON.parse(JSON.stringify(vm.selectedFare)));
	    }
        
        function swap(input, index_A, index_B) {
    	    var temp = input[index_A];

    	    input[index_A] = input[index_B];
    	    input[index_B] = temp;
    	}
        
        vm.moveUpFare = function(idx){
        	if(idx != 0){
        		swap(vm.workPackage.fareSheet[vm.selectedTab].fares, idx, idx-1);
        	}
        }
        vm.moveDownFare = function(idx){
        	if(idx != vm.workPackage.fareSheet[vm.selectedTab].fares.length-1){
        		swap(vm.workPackage.fareSheet[vm.selectedTab].fares, idx, idx+1);
        	}
        }
        
        vm.clearSelection = function(){
        	for(var x=0;x<vm.faresActionButton.length;x++){
        		vm.faresActionButton[x] = false;
        	}
        	for(var x=0;x<vm.addonFaresActionButton.length;x++){
        		vm.addonFaresActionButton[x] = false;
        	}
        	for(var x=0;x<vm.discountFaresActionButton.length;x++){
        		vm.discountFaresActionButton[x] = false;
        	}
        	for(var x=0;x<vm.marketFaresActionButton.length;x++){
        		vm.marketFaresActionButton[x] = false;
        	}
        	for(var x=0;x<vm.waiverFaresActionButton.length;x++){
        		vm.waiverFaresActionButton[x] = false;
        	}
        };
        //End Specific Fares Function
        
        //Addon Fares Function
        vm.addAddon = function(){
        	if(vm.workPackage.addonFareSheet[vm.selectedAddonTab].fares == null){
        		vm.workPackage.addonFareSheet[vm.selectedAddonTab].fares = [];	      	  	
        	}
        	
        	vm.workPackage.addonFareSheet[vm.selectedAddonTab].fares.push({
      	  		status:"PENDING",
      	  		action:"New",
      	  		carrier:"GA",
      	  	});
        }
        
        vm.rowAddonFaresSelected = function(workPackageFare){
	    	vm.selectedFareAddon = workPackageFare;
	    }
	    
	    vm.deleteAddonFaresSelected = function(){
    		var index = vm.workPackage.addonFares.indexOf(vm.selectedFareAddon);
    		vm.workPackage.addonFares.splice(index, 1);  
	    }
	    
	    vm.duplicateAddonFaresSelected = function(){
    		vm.workPackage.addonFares.push(JSON.parse(JSON.stringify(vm.selectedFareAddon)));
	    }
        //End Addon Fares Function
        
	    //Market Fares Function
	    vm.addMarketFares = function(){
	    	if(vm.workPackage.marketFareSheet[vm.selectedMarketTab].fares == null){
        		vm.workPackage.marketFareSheet[vm.selectedMarketTab].fares = [];	      	  	
        	}
        	
        	vm.workPackage.marketFareSheet[vm.selectedMarketTab].fares.push({
      	  		status:"PENDING",
      	  		action:"New",
      	  		carrier:"GA",
      	  	});
	    };
	    
	    vm.rowMarketFaresSelected = function(workPackageFare){
    		vm.selectedFareMarket = workPackageFare;
    		for(var x=0;x<vm.workPackage.marketRulesData.length;x++){
    			if(workPackageFare.ruleno == vm.workPackage.marketRulesData[x].ruleid){
    				vm.selectedFareMarket.fareRule = vm.workPackage.marketRulesData[x].fareRule;
    				break;
    			}	    			
    		}
	    }
	    
	    vm.deleteMarketFaresSelected = function(){
    		var index = vm.workPackage.marketFares.indexOf(vm.selectedFareMarket);
    		vm.workPackage.marketFares.splice(index, 1);  
	    }
	    
	    vm.duplicateMarketFaresSelected = function(){
    		vm.workPackage.marketFares.push(JSON.parse(JSON.stringify(vm.selectedFareMarket)));
	    }
	    //End Market Fares Function
	    
	    
	    //Waiver Fares Function
	    vm.addWaiverFares = function(){
	    	if(vm.workPackage.waiverFareSheet[vm.selectedWaiverTab].fares == null){
        		vm.workPackage.waiverFareSheet[vm.selectedWaiverTab].fares = [];	      	  	
        	}
        	
        	vm.workPackage.waiverFareSheet[vm.selectedWaiverTab].fares.push({
      	  		status:"PENDING",
      	  		action:"New",
      	  		carrier:"GA",
      	  	});
	    };
	    
	    vm.rowWaiverFaresSelected = function(workPackageFare){
    		vm.selectedFareWaiver = workPackageFare;
    		for(var x=0;x<vm.workPackage.waiverRulesData.length;x++){
    			if(workPackageFare.ruleno == vm.workPackage.waiverRulesData[x].ruleid){
    				vm.selectedFareWaiver.fareRule = vm.workPackage.waiverRulesData[x].fareRule;
    				break;
    			}	    			
    		}
	    }
	    
	    vm.deleteWaiverFaresSelected = function(){
    		var index = vm.workPackage.waiverFares.indexOf(vm.selectedFareWaiver);
    		vm.workPackage.waiverFares.splice(index, 1);  
	    }
	    
	    vm.duplicateWaiverFaresSelected = function(){
    		vm.workPackage.waiverFares.push(JSON.parse(JSON.stringify(vm.selectedFareWaiver)));
	    }
	    //End Waiver Fares Function
	    
	    //Filing Details
	    vm.workPackage.tarif = [{},{}];
	    vm.rowTarifSelected = function(tarif){
	    		vm.selectedTarif = tarif;
	    }
	    
	    vm.addBatch = function(){
	    		vm.batch.push({no:"", gfsref:"", gfsdate:""});
	    };
	    
	    vm.removeBatch = function(batch){
	    		 var index = vm.batch.indexOf(batch);
	    		 vm.batch.splice(index, 1);  
	    };
	    //End Filing Details
	    
	    
	    //Discount Fares
	    vm.addDiscountFares = function(){
	    	if(vm.workPackage.discountFareSheet[vm.selectedDiscountTab].fares == null){
 	    		console.log("WORKPACKAGE DISCOUNT FARES NULL");
 	    		vm.workPackage.discountFareSheet[vm.selectedDiscountTab].fares = [];
	       	  }
 	    	
 	    		vm.workPackage.discountFareSheet[vm.selectedDiscountTab].fares.push({
	 			  status:"PENDING",
	 			  action:"New",
      	 	      carrier:"GA"
	 		  });
 	    		
//	 		  if(vm.workPackage.discountFares == null){
//	       		vm.workPackage.discountFares = [];
//	       	  }
//	 		  vm.workPackage.discountFares.push({
//	 			  status:"PENDING",
//	 			  action:"NEW"
//	 		  });
	    }
	    
	    vm.rowFaresDiscountSelected = function(workPackageFare){
	    		vm.selectedFareDiscount = workPackageFare;
		}
	      
	    vm.deleteFaresDiscountSelected = function(){
      		var index = vm.workPackage.discountFares.indexOf(vm.selectedFareDiscount);
      		vm.workPackage.discountFares.splice(index, 1);  
	    }
	      
	    vm.duplicateFaresDiscountSelected = function(){
      		vm.workPackage.discountFares.push(JSON.parse(JSON.stringify(vm.selectedFareDiscount)));
		}
	    
        vm.derivedFaresDiscount  = function() {
        	    WorkPackage.findDiscountDerivedFares(vm.selectedFareDiscount, onFindSuccess, onFindFailure);
        	    
        	    function onFindSuccess(result){
	        		$uibModal.open({
	                templateUrl: 'app/entities/work-package/work-package-derived-fares-dialog.html',
	                controller: 'WorkPackageDerivedFaresDialogController',
	                controllerAs: 'vm',
	                backdrop: 'static',
	                size: 'lg',
	                windowClass: 'full-page-modal',
	                resolve: {
	                    entity: result.$promise,
	                    fareSelected: vm.selectedFareDiscount
	                }
	            }).result.then(function(workPackage) {
	            	    
	            }, function() {
	        			
	            });
        	    }
        	    
        	    function onFindFailure(error){
        	    	
        	    }
        }

	    //End Discount Fares
	    
        vm.rowSelected = function(workPackageFare){
	    		vm.selectedFare = workPackageFare;
//	    		for(var x=0;x<vm.workPackage.marketRulesData.length;x++){
//	    			if(workPackageFare.ruleno == vm.workPackage.marketRulesData[x].ruleid){
//	    				vm.selectedFare.fareRule = vm.workPackage.marketRulesData[x].fareRule;
//	    				break;
//	    			}	    			
//	    		}
	    }
        
        if(vm.workPackage.addonFares == null){
        		vm.workPackage.addonFares = [];
    		}
    
        //vm.workPackage.businessArea = 'JKT';
       //vm.workPackage.status = 'NEW';
       // vm.workPackage.fareCarrier = 'GA';
        vm.previousState = previousState.name;

        //console.log(vm.workPackage);
//        vm.workPackage.createdDate = vm.workPackage.filingDate;
        //vm.workPackage.targetDistribution = "ATPCO";
        ///
        vm.workflowButton = {isopen:false};
        vm.actionButton = {isopen:false};
        vm.editButton = {isopen:false};
        vm.editDiscountButton = {isopen:false};
        
        ///
        vm.batch = [];
        vm.attachment = [
    		
        ];
        vm.marketRules = [];
        vm.currentBatch = 1;
        
        vm.save = save;
        
        var unsubscribe = $rootScope.$on('fmpApp:workPackageUpdate', function(event, result) {
            vm.workPackage = result;
        });
        $scope.$on('$destroy', unsubscribe);
      
        Account.get().$promise
        .then(function(account){
        	vm.account = account;
        })
        .catch(function(err){
        	console.log(err);
        });
       
        vm.datePickerOpenStatus.createdDate = false;
        vm.datePickerOpenStatus.filingDate = false;
        vm.datePickerOpenStatus.distributionDate = false;
        vm.datePickerOpenStatus.discExpiryDate = false;
        
        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
        
       
        
        vm.addFiling = function(){
	        	if(vm.workPackage.filingInstructionData == null){
	        		vm.workPackage.filingInstructionData = [];
	        	}
        		vm.workPackage.filingInstructionData.push({status:"PENDING", tarno:"", cxr:"GA", comment:"", file:"", fileContentType:""});
        }
        
        vm.removeFiling = function(filing){
	   		 var index = vm.workPackage.filingInstructionData.indexOf(filing);
	   		 vm.workPackage.filingInstructionData.splice(index, 1);  
	   };
   
	   vm.addAttachment = function(){
		 	if(vm.workPackage.attachmentData == null){
	        		vm.workPackage.attachmentData = [];
	        	}
	   		vm.workPackage.attachmentData.push({comment:""});
	   }
	   
	   vm.removeAttachment = function(attachment){
	  		 var index = vm.workPackage.attachmentData.indexOf(attachment);
	  		vm.workPackage.attachmentData.splice(index, 1);  
	  };
	  
	  vm.addMarketRules = function(){
		  if(vm.workPackage.marketRulesData == null){
      		vm.workPackage.marketRulesData = [];
      	}
		  vm.workPackage.marketRulesData.push({status:"PENDING", file:"", comment:""});
	   }
	  
	  
	   vm.removeMarketRules = function(marketRules){
	  		 var index = vm.workPackage.marketRulesData.indexOf(marketRules);
	  		 vm.workPackage.marketRulesData.splice(index, 1);  
	  };
	  
	  vm.passUp = function(){
		    if (confirm("Are you sure to Pass up this workorder?")) {
		    		WorkPackage.passup(vm.workPackage, function(){
		    			alert('Pass Up Success');
		    			$state.go('work-package');
		    		}, function(){
		    			alert('Pass Up Failed');
		    		});
		    } else {
		    }
	  };
	  
	  vm.passDown = function(){
		    if (confirm("Are you sure to Pass down this workorder?")) {
		    		WorkPackage.passdown(vm.workPackage, function(){
		    			alert('Pass Down Success');
		    			$state.go('work-package');
		    		}, function(){
		    			alert('Pass Down Failed');
		    		});
		    } else {
		    }
	  };
	  
	  vm.passSideway = function(){
		    if (confirm("Are you sure to Pass sideway this workorder?")) {
		    		WorkPackage.passsideway(vm.workPackage, function(){
		    			alert('Pass Sideway Success');
		    			$state.go('work-package');
		    		}, function(){
		    			alert('Pass Sideway Failed');
		    		});
		    } else {
		    }
	  };
	  
	  vm.approve = function(){
		  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-approve-email-dialog.html',
              controller: 'WorkPackageApproveEmailDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              resolve: {
                  workPackage: vm.workPackage,
                  email : function(){
                	  return ["billyfebram@gmail.com", "laurensius.sakti@atibusinessgroup.com"];
              	  }
              }
          }).result.then(function(config) {
        	  vm.workPackage.approveConfig = config;
        	  console.log(config);
        	  WorkPackage.approve(vm.workPackage, function(){
	    			alert('Approve Success');
	    			$state.go('work-package');
    		}, function(){
    			alert('Approve Failed');
    		});
//	  		    if (confirm("Are you sure to Approve this workorder?")) {
	  		    		
//	  		    } else {
//	  		    }
          }, function() {
      			
          });
	  };
	  
	  vm.referback = function(){
		    if (confirm("Are you sure to Referback this workorder?")) {
		    		WorkPackage.referback(vm.workPackage, function(){
		    			alert('Refer Back Success');
		    			$state.go('work-package');
		    		}, function(){
		    			alert('Refer Back Failed');
		    		});
		    } else {
		    }
	  };
	  
	  vm.createBatches = function(){
		  if (confirm("Are you sure to create Batches this workorder?")) {
	    		WorkPackage.createbatch(vm.workPackage, function(wo){
	    			alert('Create Batches Success');
	    			alert(wo.batchString);
	    			$state.go('work-package');
	    		}, function(){
	    			alert('Create Batches Failed');
	    		});
	    } else {
	    }
	  }
	  
	  vm.reviseBatches = function(){
		  if (confirm("Are you sure to revise Batches this workorder?")) {
	    		WorkPackage.revisebatch(vm.workPackage, function(){
	    			alert('Revise Batches Success');
	    			$state.go('work-package');
	    		}, function(){
	    			alert('Revise Batches Failed');
	    		});
	    } else {
	    }
	  }
	  
	  vm.completeBatches = function(){
		  if (confirm("Are you sure to complete Batches this workorder?")) {
	    		WorkPackage.completebatch(vm.workPackage, function(){
	    			alert('Complete Batches Success');
	    			$state.go('work-package');
	    		}, function(){
	    			alert('Complete Batches Failed');
	    		});
	    } else {
	    }
	  }
	  function save () {
          vm.isSaving = true;
          if (vm.workPackage.id !== null) {
              WorkPackage.update(vm.workPackage, onSaveSuccess, onSaveError);
          } else {
              WorkPackage.save(vm.workPackage, onSaveSuccess, onSaveError);
          }
      }

      function onSaveSuccess (result) {
	      $scope.$emit('fmpApp:workPackageUpdate', result);
	      var data = result;
    	      
    	  data.filingDate = DateUtils.convertDateTimeFromServer(data.filingDate);
    	  data.saleDate = DateUtils.convertDateTimeFromServer(data.saleDate);
          data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
          data.distributionDate = DateUtils.convertDateTimeFromServer(data.distributionDate);
          data.discExpiryDate = DateUtils.convertDateTimeFromServer(data.discExpiryDate);
          data.queuedDate = DateUtils.convertDateTimeFromServer(data.queuedDate);
          data.lockedSince = DateUtils.convertDateTimeFromServer(data.lockedSince);
          
          vm.workPackage = data;
          vm.isSaving = false;
          alert('Save success');
      }

      function onSaveError () {
          vm.isSaving = false;
      }
      
      vm.isFieldEditable = function(fare, field) {
	  		var currentReviewLevel = [vm.workPackage.reviewLevel];
	  		
	  		if(currentReviewLevel != "Distribution"){
    	        if(fare.status == 'APPROVED' || fare.status == 'REJECTED'){	
    	        	if(field == 'status'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["LSO1", "LSO2", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	} else{
	        			return false;
	        		}
    	        }
    	        else {	    	  		
	  	    		if(field == 'status'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["LSO", "Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'carrier'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'tarno'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'tarcd'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'origin'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'destination'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'farebasis'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'bookingClass'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'cabin'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'typeOfJourney'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		
	  	    		else if(field == 'footnote'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["LSO1", "LSO2", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		
	  	    		else if(field == 'rtgno'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		
	  	    		else if(field == 'rtgno'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		
	  	    		else if(field == 'ruleno'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		
	  	    		else if(field == 'currency'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		
	  	    		else if(field == 'amount'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'aif'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'travelStartDate'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'travelEndDate'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'saleStartDate'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'saleEndDate'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'effDate'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'travelComplete'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'travelIndicator'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'ratesheetComment'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'dealCode'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = ["Distribution", "Route Management"];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		
	  	    		//ADDON FARE
	  	    		else if(field == 'addonFareStatus'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = [];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'addonFareCarrier'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = [];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'addonFareTarno'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = [];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'addonFareTarcd'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = [];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'addonFareOrigin'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = [];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'addonFareDestination'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = [];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		else if(field == 'addonFareBucket'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = [];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}		  	    		
	  	    		else if(field == 'addonFareCarrier'){
		    	  	    //cannot be edited by
		    	  		var reviewLevel = [];
		    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
		    	  	}
	  	    		
	  	    		//DISCOUNT FARE
	  	    	  	else if(field == 'discountStatus'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["LSO","Distribution","Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    	  	else if(field == 'discountTariffCode'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["LSO","Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    	  	else if(field == 'discountLoc1Type'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    	  	else if(field == 'discountLoc2Type'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    	  	else if(field == 'discountLoc1'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    	  	else if(field == 'discountLoc2'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountBaseFareBasis'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountBaseRuleno'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountBaseTariffCode'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountCalcType'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Distribution", "Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountPercentageOfBaseFare'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Distribution", "Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountCurrency'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Distribution", "Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountSpecifiedAmount'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Distribution", "Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountPaxType'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountFareFareType'){
	  	    	  		//not required by
	  	    	  		var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountTicketCode'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountTicketDesignator'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountBaseFareOwRt'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountGlobal'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountRtgno'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountRtgnoTarno'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountNewFarebasis'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountNewBaseFareOwRt'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountNewBookingCode'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountTravelStartDate'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountTravelEndDate'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountSaleStartDate'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountSaleEndDate'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountComment'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountTravelComplete'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    		else if(field == 'discountTravelCompleteIndicator'){
	  	    	  		//not required by
	  	    			var reviewLevel = ["Route Management"];
	  	    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
	  	    	  	}
	  	    	  	//END DISCOUNT FARE
    	        }
	  		}
	  		else{
	  			return true;
	  		}
      };
      
      
      vm.isFieldDisable = function(field){
    	    var currentReviewLevel = [vm.workPackage.reviewLevel];
    	    
    	  	if(field == 'priority'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];    	  		
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	if(field == 'saleDate'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];    	  		
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	
    	  	else if(field == 'name'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'filingDate'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'distributionDate'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'fareCarrier'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'description'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'approvalReference'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	
    	  	//DISCOUNT  
    	  	else if(field == 'discountFareType'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountApprovalReference'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountAccountCode'){
    	  	    //not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountFaresName'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	//DISCOUNT 
    	  	
    	  	/////// COMMENT
    	  	else if(field == 'ioComment'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["LSO", "Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'ratesheetComment'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	
    	  	/////// END COMMENT
    	  	
    	  	/////// FARES
    	  	else if(field == 'status'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["LSO", "Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'carrier'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["LSO", "HO","Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'tarno'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'tarcd'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'origin'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'destination'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'farebasis'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'bookingClass'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'cabin'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'typeOfJourney'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'footnote'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["LSO", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'rtgno'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'ruleno'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'currency'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'amount'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'aif'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'travelStartDate'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'travelEndDate'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'saleStartDate'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'travelComplete'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'travelIndicator'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'ratesheetComment'){
    	  	    //cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	
    	  	//ADDON FARES
    	  	else if(field == 'addonFaresName'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareStatus'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareCarrier'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareAction'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTarno'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTarcd'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareOrigin'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareDestination'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareFootnote'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareZone'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["LSO", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareRtgno'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareCurrency'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareAmount'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTravelStart'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTravelEnd'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareSaleStart'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareSaleEnd'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareComment'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTravelComplete'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTravelIndicator'){
    	  		//cannot be edited by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	
    	  	//DISCOUNT FARE
    	  	else if(field == 'discountStatus'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","HO","Distribution","Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountTariffCode'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountLoc1Type'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountLoc2Type'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountLoc1'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountLoc2'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountBaseFareBasis'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountBaseRuleno'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountBaseTariffCode'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountCalcType'){
    	  		//not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountPercentageOfBaseFare'){
    	  		//not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountCurrency'){
    	  		//not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountSpecifiedAmount'){
    	  		//not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountPaxType'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountFareFareType'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTicketCode'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTicketDesignator'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountBaseFareOwRt'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountGlobal'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountRtgno'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountRtgnoTarno'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountNewFarebasis'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountNewBaseFareOwRt'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountNewBookingCode'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTravelStartDate'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTravelEndDate'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountSaleStartDate'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountSaleEndDate'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountComment'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTravelComplete'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTravelCompleteIndicator'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	//END DISCOUNT FARE
      };
      
      vm.getFieldDisable = function(currentReviewLevel, reviewLevel){
  		var editable = false;
  		
  		for(var x=0; x<currentReviewLevel.length; x++){
  			if(reviewLevel.indexOf(currentReviewLevel[x]) > -1){
  				editable = true;
  				break;
  			}
  		}
  		
  		return editable;
      }
      
      vm.checkRequiredField = function(field){
    	  	var currentReviewLevel = [vm.workPackage.reviewLevel];
    	  	
    	  	if(field == 'workpackageName'){
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}  
    	  	else if(field == 'saleDate'){
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}  
    	  	else if(field == 'businessArea'){
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'filingDate'){
    	  		var reviewLevel = ["LSO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}  
//    	  	else if(field == 'name'){
//    	  		var reviewLevel = ["Distribution", "Route Management"];
//    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
//    	  	}  

    	  	else if(field == 'fareType'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'description'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'approvalReference'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	
    	  	//DISCOUNT FARE HEADER
    	  	else if(field == 'discountFareType'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountApprovalReference'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountAccountCode'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountFaresName'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	//END DISCOUNT FARE HEADER
    	  	
    	  	//////FARE
    	  	else if(field == 'status'){
    	  		var reviewLevel = ["LSO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'carrier'){
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'tarno'){
    	  		var reviewLevel = ["LSO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'tarcd'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'origin'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'destination'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'farebasis'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'bookingClass'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'cabin'){
    	  	    //not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'typeOfJourney'){
    	  	    //not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'footnote'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'rtgno'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO","HO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'ruleno'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'currency'){
    	  	    //not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'amount'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'aif'){
    	  	    //not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'saleStartDate'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'saleEndDate'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'travelStartDate'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'travelEndDate'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'travelComplete'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'travelIndicator'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'ratesheetComment'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	//END FARE
    	  	
    	  	//ADDON FARE
    	  	else if(field == 'addonFaresName'){
    	  		//not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareStatus'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareCarrier'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareAction'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTarno'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTarcd'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareOrigin'){
    	  	    //not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareDestination'){
    	  	    //not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareBucket'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTypeOfJourney'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareFootnote'){
    	  	    //not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareZone'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareRtgno'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareCurrency'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareAmount'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTravelStart'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","HO","Distribution","Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTravelEnd'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","HO","Distribution","Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareSaleStart'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","HO","Distribution","Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareSaleEnd'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","HO","Distribution","Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareComment'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","HO","Distribution","Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTravelComplete'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","HO","Distribution","Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'addonFareTravelIndicator'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","HO","Distribution","Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	//END ADDON FARE
    	  	
    	  	//DISCOUNT FARE
    	  	else if(field == 'discountStatus'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","HO","Distribution","Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountTariffCode'){
    	  		//not required by
    	  		var reviewLevel = ["LSO","Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountLoc1Type'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountLoc2Type'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountLoc1'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	else if(field == 'discountLoc2'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountBaseFareBasis'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountBaseRuleno'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountBaseTariffCode'){
    	  		//not required by
    	  		var reviewLevel = ["LSO", "HO", "Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountCalcType'){
    	  		//not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountPercentageOfBaseFare'){
    	  		//not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountCurrency'){
    	  		//not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountSpecifiedAmount'){
    	  		//not required by
    	  		var reviewLevel = ["Distribution", "Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountPaxType'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountFareFareType'){
    	  		//not required by
    	  		var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTicketDesignator'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTicketCode'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountBaseFareOwRt'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountGlobal'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountRtgno'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountRtgnoTarno'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountNewFarebasis'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountNewBaseFareOwRt'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountNewBookingCode'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTravelStartDate'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTravelEndDate'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountSaleStartDate'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountSaleEndDate'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountComment'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTravelComplete'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    		else if(field == 'discountTravelCompleteIndicator'){
    	  		//not required by
    			var reviewLevel = ["Route Management"];
    	  		return !vm.getFieldDisable(currentReviewLevel, reviewLevel);
    	  	}
    	  	//END DISCOUNT FARE
      }
      
      vm.importFare = function ($file) {
          if ($file) {
              DataUtils.toBase64($file, function(base64Data) {
                  $scope.$apply(function() {
                      var testing = { 
                    		  file : base64Data,
                    		  fileContentType : $file.type
                      };
                      
                      vm.workPackage.importFares = testing;
  
                      //send to backend
                      WorkPackage.importFares(vm.workPackage, onImportSuccess, onImportFailure);
                      
                      function onImportSuccess(result){
                    	  	alert('Import Success');
                    	  	vm.workPackage = result;
                    	  	vm.workPackage.filingDate = DateUtils.convertDateTimeFromServer(vm.workPackage.filingDate);
                    	  	vm.workPackage.createdDate = DateUtils.convertDateTimeFromServer(vm.workPackage.createdDate);
                    	 // 	vm.workPackage.distributionDate = DateUtils.convertDateTimeFromServer(vm.workPackage.distributionDate);
                         //vm.workPackage.discExpiryDate = DateUtils.convertDateTimeFromServer(vm.workPackage.discExpiryDate);
                         //vm.workPackage.queuedDate = DateUtils.convertDateTimeFromServer(vm.workPackage.queuedDate);
                         //vm.workPackage.lockedSince = DateUtils.convertDateTimeFromServer(vm.workPackage.lockedSince);
                      }
                      
                      function onImportFailure(){
                    	    alert('Import Failed');
                      }
                  });
              });
          }
      };
      
      vm.importFareDiscount = function ($file) {
          if ($file) {
              DataUtils.toBase64($file, function(base64Data) {
                  $scope.$apply(function() {
                      var testing = { 
                    		  file : base64Data,
                    		  fileContentType : $file.type
                      };
                      
                      vm.workPackage.importFares = testing;
                      
                      //send to backend
                      WorkPackage.importFaresDiscount(vm.workPackage, onImportSuccess, onImportFailure);
                      
                      function onImportSuccess(result){
                    	  	alert('Import Success');
                    	  	vm.workPackage = result;
                    	  	vm.workPackage.filingDate = DateUtils.convertDateTimeFromServer(vm.workPackage.filingDate);
                    	  	vm.workPackage.createdDate = DateUtils.convertDateTimeFromServer(vm.workPackage.createdDate);
                    	  	vm.workPackage.distributionDate = DateUtils.convertDateTimeFromServer(vm.workPackage.distributionDate);
                         vm.workPackage.discExpiryDate = DateUtils.convertDateTimeFromServer(vm.workPackage.discExpiryDate);
                         vm.workPackage.queuedDate = DateUtils.convertDateTimeFromServer(vm.workPackage.queuedDate);
                         vm.workPackage.lockedSince = DateUtils.convertDateTimeFromServer(vm.workPackage.lockedSince);
                      }
                      
                      function onImportFailure(){
                    	    alert('Import Failed');
                      }
                  });
              });
          }
      };
      vm.importFareMarket = function ($file) {
          if ($file) {
              DataUtils.toBase64($file, function(base64Data) {
                  $scope.$apply(function() {
                      var testing = { 
                    		  file : base64Data,
                    		  fileContentType : $file.type
                      };
                      
                      vm.workPackage.importFares = testing;
                      
                      //send to backend
                      WorkPackage.importFaresMarket(vm.workPackage, onImportSuccess, onImportFailure);
                      
                      function onImportSuccess(result){
                    	  	alert('Import Success');
                    	  	vm.workPackage = result;
                    	  	vm.workPackage.filingDate = DateUtils.convertDateTimeFromServer(vm.workPackage.filingDate);
                    	  	vm.workPackage.createdDate = DateUtils.convertDateTimeFromServer(vm.workPackage.createdDate);
                    	  	vm.workPackage.distributionDate = DateUtils.convertDateTimeFromServer(vm.workPackage.distributionDate);
                        // vm.workPackage.discExpiryDate = DateUtils.convertDateTimeFromServer(vm.workPackage.discExpiryDate);
                        // vm.workPackage.queuedDate = DateUtils.convertDateTimeFromServer(vm.workPackage.queuedDate);
                        // vm.workPackage.lockedSince = DateUtils.convertDateTimeFromServer(vm.workPackage.lockedSince);
                      }
                      
                      function onImportFailure(){
                    	    alert('Import Failed');
                      }
                  });
              });
          }
      };
      
      vm.exportFares = function(){
    	  	  WorkPackage.exportFares(vm.workPackage, onExportSuccess, onExportFailure);
	    	  function onExportSuccess(result){
	    		  var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
      			var templateFilename = "Workorder_Fare.xlsx";
      			var blob = b64toBlob(result.file, fileType);
      			FileSaver.saveAs(blob, templateFilename);
	    	  }
	    	  function onExportFailure(){
	    		  
	    	  }    	  
      }
      
      vm.exportFaresMarket = function(){
	  	  WorkPackage.exportFaresMarket(vm.workPackage, onExportSuccess, onExportFailure);
	    	  function onExportSuccess(result){
	    		  var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  			var templateFilename = "Workorder_MarketFare.xlsx";
	  			var blob = b64toBlob(result.file, fileType);
	  			FileSaver.saveAs(blob, templateFilename);
	    	  }
	    	  function onExportFailure(){
	    		  
	    	  }    	  
	  }
      
      vm.exportFaresDiscount = function(){
	  	  WorkPackage.exportFaresDiscount(vm.workPackage, onExportSuccess, onExportFailure);
	    	  function onExportSuccess(result){
	    		  var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  			var templateFilename = "Workorder_DiscountFare.xlsx";
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
      
      vm.setFile = function ($file, testing) {
          if ($file) {
              DataUtils.toBase64($file, function(base64Data) {
                  $scope.$apply(function() {
                      testing.file = base64Data;
                      testing.fileContentType = $file.type;
                  });
              });
          }
      };
      
      vm.setFileMarketRules = function ($file, testing) {
          if ($file) {
              DataUtils.toBase64($file, function(base64Data) {
                  $scope.$apply(function() {
                      testing.file = base64Data;
                      testing.fileContentType = $file.type;
                  });
              });
          }
      };
      
      vm.addComment = function(commentString){
	  	 if(commentString != null){
	    	  if(vm.workPackage.comment == null){
	      		vm.workPackage.comment = [];
	      }
	    	  
    	  	vm.workPackage.comment.push({
    	  		comment:commentString
    	  	});
    	  	vm.save();
    	  	vm.commentString = null;
    	 }
      }
      
      vm.addInterOffice = function(ioString){
 	  	 if(ioString != null){
 	    	  if(vm.workPackage.interofficeComment == null){
 	      		vm.workPackage.interofficeComment = [];
 	      }
 	    	  
     	  	vm.workPackage.interofficeComment.push({
     	  		comment:ioString
     	  	});
     	  	vm.save();
     	  	vm.addInterOffice = null;
     	 }
       }
      
      
      vm.export = function(){
    	  	alert('EXPORT');
      }
      
      vm.ratesheet = function(){
    	  	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-rate-sheet-dialog.html',
              controller: 'WorkPackageRateSheetDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              resolve: {
                  entity: vm.workPackage,
                  index : vm.indexSelectedTab
              }
          }).result.then(function(ratesheet) {
          	  
          }, function() {
      			
          });
      }
      
      vm.agent = function(){
	    	  	var object = {
	    			agents: vm.workPackage.agent
	    	 	}
    	  
	  	  $uibModal.open({
	          templateUrl: 'app/pages/work-packages/work-package-agent-dialog.html',
	          controller: 'WorkPackageAgentDialogController',
	          controllerAs: 'vm',
	          backdrop: 'static',
	          size: 'lg',
	          resolve: {
	              entity: object
	          }
	      }).result.then(function(agent) {
	      	  vm.workPackage.agent = agent;
	      	  vm.save();
	      }, function() {
	  			
	      });
	  }
      
      vm.publish = function(){
    	      WorkPackage.publish(vm.workPackage, onPublishSuccess, onPublishError);
    	      
    	      function onPublishSuccess(result){
    	    	  	alert("PUBLISH SUCCESS");
    	    	  	console.log(result);
    	      }
    	      
    	      function onPublishError(error){
    	    	  
    	      }
      }
      
      vm.previousVersion = function(){
    	  vm.workPackage.changeType = 'previous';
    	  WorkPackage.changeVersion(vm.workPackage, onChangeSuccess, onChangeError);
    	  
    	  function onChangeSuccess(result){
//    		  alert('Change Version success');
//    		  console.log(result.version);    		  
//    		  vm.workPackage = result;
    		  var data = result;
    	      
        	  data.filingDate = DateUtils.convertDateTimeFromServer(data.filingDate);
              data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
              data.distributionDate = DateUtils.convertDateTimeFromServer(data.distributionDate);
              data.discExpiryDate = DateUtils.convertDateTimeFromServer(data.discExpiryDate);
              data.queuedDate = DateUtils.convertDateTimeFromServer(data.queuedDate);
              data.lockedSince = DateUtils.convertDateTimeFromServer(data.lockedSince);
              data.id =  vm.workPackage.id;
              vm.workPackage = data;
    	  }
    	  
    	  function onChangeError(error){
    		  
    	  }
      };
      
      vm.nextVersion = function(){
    	  vm.workPackage.changeType = 'next';
    	  WorkPackage.changeVersion(vm.workPackage, onChangeSuccess, onChangeError);
    	  
    	  function onChangeSuccess(result){
    		  var data = result;
    	      
        	  data.filingDate = DateUtils.convertDateTimeFromServer(data.filingDate);
              data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
              data.distributionDate = DateUtils.convertDateTimeFromServer(data.distributionDate);
              data.discExpiryDate = DateUtils.convertDateTimeFromServer(data.discExpiryDate);
              data.queuedDate = DateUtils.convertDateTimeFromServer(data.queuedDate);
              data.lockedSince = DateUtils.convertDateTimeFromServer(data.lockedSince);
              data.id =  vm.workPackage.id;
              vm.workPackage = data;
    	  }
    	  
    	  function onChangeError(error){
    		  
    	  }
      };
      
      vm.checkDisabledField = function(){
    	  var isDisabled = false;
    	  if(vm.workPackage.version != 'current'){
    		  isDisabled = true;
    	  }
    	  else{
    		  var containsReviewLevel = vm.user.reviewLevels.includes(vm.workPackage.reviewLevel);
    		  if(!containsReviewLevel){
    			  isDisabled = true;
    		  }
    	  }
    	  
    	  return isDisabled;
      };
      
      vm.fareAmountChange = function(fare){
    	  	 fare.aif = parseInt(fare.amount) + parseInt(fare.tfc);
      };
      
      vm.fareAifChange = function(fare){
    	  	fare.amount = parseInt(fare.aif) - parseInt(fare.tfc);
      }
      $(window).click(function(e) {
	    	  if (e.target.id != 'comment-button') {
	    		  if ($('#comment-section').attr('class') != undefined && $('#comment-section').attr('class').includes('show')) {
	    			  $('#comment-section').removeClass('show');
	    		  }
	    	  } else {
	    		  $('#comment-section').addClass('show');
	    	  }
      });
      
      GlobalService.sayHello();
    }
})();
