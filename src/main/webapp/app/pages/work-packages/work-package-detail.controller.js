(function() {

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
    WorkPackageDetailController.$inject = ['$window', '$sce', 'currencies','tariffNumber','tariffNumberAddOn', 'cities', 'FileSaver', '$uibModal', 'DateUtils', 'DataUtils', 'Account', '$scope', '$state', '$rootScope', '$stateParams', 'previousState', 'entity', 'WorkPackage', 'ProfileService', 'user', 'fareTypes', 'businessAreas', 'passengers', 'priorities', 'states', 'cityGroups', 'Currency', 'atpcoFareTypes', 'ClipboardSheet', 'Clipboard'];
    function WorkPackageDetailController($window, $sce, currencies,tariffNumber,tariffNumberAddOn, cities, FileSaver, $uibModal, DateUtils, DataUtils, Account, $scope, $state, $rootScope, $stateParams, previousState, entity, WorkPackage, ProfileService, user, fareTypes, businessAreas, passengers, priorities, states, cityGroups, Currency, atpcoFareTypes, ClipboardSheet, Clipboard) {
    	var vm = this;

    	window.onbeforeunload = function () {
    		   // handle the exit event
    		return false;
    	};

    	vm.editorConfig = {
		    sanitize: false,
		    toolbar: [
			{ name: 'basicStyling', items: ['bold', 'italic', 'underline', 'strikethrough', 'subscript', 'superscript', '-', 'leftAlign', 'centerAlign', 'rightAlign', 'blockJustify', '-'] },
			{ name: 'doers', items: ['removeFormatting', 'undo', 'redo', '-'] },
			{ name: 'colors', items: ['fontColor', 'backgroundColor', '-'] },
			{ name: 'styling', items: ['font', 'size', 'format'] },
		    ]
		};
    	
    	
        vm.currentTab = [];
        vm.currentAddonTab = [];
        vm.currentDiscountTab = [];
        vm.currentMarketTab = [];
        vm.currentWaiverTab = [];
         
        vm.rulesMenu = true;
        vm.user = user;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.openCalendarRow = openCalendarRow;
        vm.getCalendar = getCalendar;
        vm.importData = {};
        
        vm.ioString =null;
        vm.openFile = DataUtils.openFile;
        vm.account = null;
        vm.workPackage = entity;
        vm.tariffNumber = tariffNumber;
        vm.tariffNumberAddOn = tariffNumberAddOn;
        vm.cities = cities;
        vm.states = states;
        vm.cityGroups = cityGroups;
        vm.areas = [{"code":"1"}, {"code":"2"}, {"code":"3"}];
        vm.passengers = passengers;
        vm.currencies = currencies;
        vm.indexSelectedTab = 0;
        $scope.dateformat = "yyyy-MM-dd";
        vm.optionFare = fareTypes;
        vm.atpcoFareTypes = atpcoFareTypes;
                           
        
        vm.businessArea = {};
        for(var x=0;x<businessAreas.length;x++){
        	vm.businessArea[businessAreas[x]] = businessAreas[x];
        }
        
        vm.priority = {};
        for(var x=0;x<priorities.length;x++){
        	vm.priority[priorities[x].name] = priorities[x].name;
        }
        
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
        		name:"distributionDate",
        		editable:["LSO", "HO"],
        		mandatory:[]
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
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["HO", "DISTRIBUTION"]
        	},
        	{
        		name:"tarcd",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["HO", "DISTRIBUTION"]
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
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["HO", "DISTRIBUTION"]
        	},
        	{
        		name:"bookingClass",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["HO"]
        	},
        	{
        		name:"cabin",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	{
        		name:"typeOfJourney",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	{
        		name:"footnote",
        		editable:["HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"rtgno",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["DISTRIBUTION"]
        	},
        	{
        		name:"ruleno",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["DISTRIBUTION"]
        	},
        	{
        		name:"currency",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	{
        		name:"amount",
        		editable:["LSO", "HO"],
        		mandatory:[        			
        			"LSO", "HO"
        		],
        		mandatoryExtraCondition:[
        			{
        				field:"aif",
        				isEmpty:true
        			}
        		]
        	},
        	{
        		name:"aif",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"],
        		mandatoryExtraCondition:[
        			{
        				field:"amount",
        				isEmpty:true
        			}
        		]
        	},
        	{
        		name:"travelStartDate",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"travelEndDate",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"saleStartDate",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"saleEndDate",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"comment",
        		editable:["LSO", "HO", "DISTRIBUTION", "ROUTE_MANAGEMENT"],
        		mandatory:[]
        	},
        	{
        		name:"travelComplete",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"travelIndicator",
        		editable:["LSO", "HO", "DISTRIBUTION"],
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
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["DISTRIBUTION"]
        	},
        	{
        		name:"addonFareTarcd",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["DISTRIBUTION"]
        	},
        	{
        		name:"addonFareOrigin",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	{
        		name:"addonFareDestination",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	{
        		name:"addonFareBucket",
        		editable:["HO", "DISTRIBUTION"],
        		mandatory:["DISTRIBUTION"]
        	},
        	{
        		name:"addonFareTypeOfJourney",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO","DISTRIBUTION"]
        	},
        	{
        		name:"addonFareFootnote",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareZone",
        		editable:["HO", "DISTRIBUTION"],
        		mandatory:["DISTRIBUTION"]
        	},
        	{
        		name:"addonFareRtgno",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["DISTRIBUTION"]
        	},
        	{
        		name:"addonFareCurrency",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	{
        		name:"addonFareAmount",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"addonFareTravelStartDate",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareTravelEndDate",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareSaleStartDate",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareSaleEndDate",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareComment",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareTravelComplete",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"addonFareTravelIndicator",
        		editable:["LSO", "HO", "DISTRIBUTION"],
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
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	
        	//DISCOUNT FARE
        	{
        		name:"discountStatus",
        		editable:["HO"],
        		mandatory:[]
        	},
        	{
        		name:"discountTariffCode",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["HO", "DISTRIBUTION"]
        	},
        	{
        		name:"discountLoc1Type",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	
        	{
        		name:"discountLoc1",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	{
        		name:"discountLoc2Type",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	
        	{
        		name:"discountLoc2",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["LSO", "HO", "DISTRIBUTION"]
        	},
        	
        	{
        		name:"discountBaseFareBasis",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:["HO", "DISTRIBUTION"]
        	},
        	{
        		name:"discountBaseRuleno",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountBaseTariffCode",
        		editable:["LSO", "HO", "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountCalcType",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]        		
        	},
        	{
        		name:"discountPercentageOfBaseFare",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[],
        		mandatoryExtraCondition:[
        			{
        				field:"calcType",
        				isEqual:"M"
        			},
        			{
        				field:"calcType",
        				isEqual:"C"
        			}
        		]
        	},
        	{
        		name:"discountFareCurrency",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountSpecifiedAmount",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[],
        		mandatoryExtraCondition:[
        			{
        				field:"calcType",
        				isEqual:"S"
        			}
        		]
        	},
        	{
        		name:"discountPaxType",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:["LSO", "HO",  "DISTRIBUTION"]
        	},
        	{
        		name:"discountFareFareType",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountTicketCode",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	
        	{
        		name:"discountTicketDesignator",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountBaseFareOwRt",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountGlobal",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountRtgno",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountRtgnoTarno",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountNewFarebasis",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountNewBaseFareOwRt",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountNewBookingCode",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountTravelStartDate",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountTravelEndDate",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountSaleStartDate",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountSaleEndDate",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountComment",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountTravelComplete",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[]
        	},
        	{
        		name:"discountTravelCompleteIndicator",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
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
        	{
        		name:"marketGroupFares",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	
        	//MARKET FARE
        	{
        		name:"marketFareStatus",
        		editable:["HO"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareCarrier",
        		editable:[],
        		mandatory:[]
        	},        	
        	{
        		name:"marketFareOrigin",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"marketFareDestination",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"marketFareFarebasis",
        		editable:["LSO", "HO"],
        		mandatory:["HO"]
        	},
        	{
        		name:"marketFareBookingClass",
        		editable:["LSO", "HO"],
        		mandatory:["HO"]
        	},
        	{
        		name:"marketFareSsn",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	
        	{
        		name:"marketFareCabin",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	
        	{
        		name:"marketFareTypeOfJourney",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"marketFareRuleno",
        		editable:["LSO", "HO"],
        		mandatory:["HO"]
        	},
        	{
        		name:"marketFareCurrency",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"marketFareBaseAmt",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},        	        	
        	{
        		name:"marketFareTravelStartDate",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"marketFareTravelEndDate",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"marketFareSaleStartDate",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"marketFareSaleEndDate",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"marketFareTravelComplete",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareTravelIndicator",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"marketFareRatesheetComment",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	
        	//WAIVER FARE HEADER
        	{
        		name:"waiverFareDescription",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverApprovalReference",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"waiverFareType",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"waiverAgentName",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	
        	{
        		name:"waiverIataNo",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverIocNumber",
        		editable:[],
        		mandatory:[]
        	},
        	{
        		name:"waiverApprovalDate",
        		editable:[],
        		mandatory:[]
        	},
        	
        	
        	//WAIVER FARE
        	{
        		name:"waiverType",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverFullPartial",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverPnr",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverTktFrom",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverTktTo",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"waiverOri",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverDest",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverOriginalItinerary",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverNewItinerary",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"waiverOriginalBasicFare",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverNewBasicFare",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"waiverApprovedFare",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"waiverFareLost",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"waiverCalculatedPn",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverOriginalPn",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverApprovedPn",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverPenaltyLostPercent",
        		editable:[],
        		mandatory:[]
        	},
        	{
        		name:"waiverPenaltyLostAmount",
        		editable:[],
        		mandatory:[]
        	},
        	{
        		name:"waiverCurrency",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"waiverTotalPax",
        		editable:["LSO", "HO"],
        		mandatory:["LSO", "HO"]
        	},
        	{
        		name:"waiverTotalLost",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	},
        	{
        		name:"waiverApprover",
        		editable:[],
        		mandatory:[]
        	},
        	{
        		name:"waiverRemark",
        		editable:["LSO", "HO"],
        		mandatory:[]
        	}
        ];
        
        vm.isRequired = function(field, fare){
        	return vm.checkField(field, 'mandatory', fare);        	 
        };
        
        vm.isEditable = function(field){
        	return vm.checkField(field, 'editable');    
        };
        
        vm.checkField = function(field, type, fare){
        	var result = false;
        	if(type == 'mandatory'){
        		for(var x=0;x<vm.fields.length;x++){
        			if(vm.fields[x].name == field){
        				var reviewLevels = vm.fields[x].mandatory;
        				var extraCondition = vm.fields[x].mandatoryExtraCondition;
        				
        				if(extraCondition != null && extraCondition.length > 0){
//        					console.log("EXTRA CONDITION")
        					if(reviewLevels.indexOf(vm.workPackage.reviewLevel) > -1){
	        					for(var y=0;y<extraCondition.length;y++){
	        						if(fare != null){
	        							//Check extra condition here
	        							var field = extraCondition[y].field;
	        							
	        							//Check other field empty condition
	        							var isEmpty = extraCondition[y].isEmpty;
	        							if(isEmpty){
	        								if(fare[field] == null || fare[field] == ''){
	        									result = true;
	        									break;
	        								}
	        							} 
	        							//End check other field empty condition
	        							
	        							//Check other field value condition
	        							var otherField = extraCondition[y].field;
	        							if(fare != null){
	        								if(fare[otherField] == extraCondition[y].isEqual){
	        									result = true;
	        									break;
	        								}
	        							}
	        							else{
	        								
	        							}    
	        							//End check other field value condition
	        						}
	        					}
	        					break;
        					}
        					else{
        						for(var y=0;y<extraCondition.length;y++){
        							var otherField = extraCondition[y].field;
        							if(fare != null){
        								if(fare[otherField] == extraCondition[y].isEqual){
        									result = true;
        									break;
        								}
        							}
        							else{
        								
        							}        							
        						}
        					}
        				}
        				else{
	        				if(reviewLevels.indexOf(vm.workPackage.reviewLevel) > -1){
	        					result = true;
	        					break;
	        				}
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
        vm.generateTourCode = function(workPackageSheet, type){
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
				workPackageSheet.approvalReference = option.tourcode;	
            }, function() {
        			
            });
        };
        
        vm.generateTourCodeAttachment = function(){
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
				vm.workPackage.attachmentApprovalReference = option.tourcode;		 
				//workPackageSheet.approvalReference = option.tourcode;		        			    			
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
        if(vm.workPackage.specifiedFares){
        	vm.currentTab[0] = true;
        }
        vm.selectedTab = 0;
        vm.selectTab = function(index){
        	vm.resetTab(); 
        	if(vm.workPackage.fareSheet.length > 0){
        		vm.currentTab[index] = true;
        	}
        };
        
        vm.addTab = function(option, fares){
        	if(option.type == 'Fares'){
        		if(!vm.workPackage.specifiedFares){
        			vm.workPackage.fareSheet = [];
        		}
        		vm.workPackage.specifiedFares = true;
        		vm.workPackage.fareSheet.push({specifiedFaresName:option.name, fareType:option.fareType, fares:fares});
        		vm.selectTab(vm.workPackage.fareSheet.length-1);
        	}
        	else if(option.type == 'Market Fares'){
        		vm.workPackage.marketFares = true;
        		vm.workPackage.marketFareSheet.push({marketFaresName:option.name, fareType:option.fareType, fares:fares});
        		vm.selectMarketTab(vm.workPackage.marketFareSheet.length-1);
        	}
        	else if(option.type == 'Waiver Fares'){
        		vm.workPackage.waiverFares = true;
        		vm.workPackage.waiverFareSheet.push({waiverFaresName:option.name, waiverFareType:option.fareType, fares:fares});
        		vm.selectWaiverTab(vm.workPackage.waiverFareSheet.length-1);
        	}
        	else if(option.type == 'Discount Fares'){
        		vm.workPackage.discount = true;
        		vm.workPackage.discountFareSheet.push({discountFaresName:option.name, discountFareType:option.fareType, fares:fares});
        		vm.selectDiscountTab(vm.workPackage.discountFareSheet.length-1);
        	}
        	else if(option.type == 'Add-Ons'){
        		vm.workPackage.addon = true;
        		vm.workPackage.addonFareSheet.push({addonFaresName:option.name, fares:fares});
        		vm.selectAddonTab(vm.workPackage.addonFareSheet.length-1);
        	}
        	else if(option.type == 'Attachment'){
        		vm.workPackage.attachment = true;    
        		vm.selectOtherTab('attachment');
        	}
        	else if(option.type == 'Filing Instruction'){
        		vm.workPackage.filingInstruction = true;
        		vm.selectOtherTab('filingInstruction');
        	}
        	
        	

            if(vm.workPackage.fareSheet.length > 0){
              	for(var x=0;x<vm.workPackage.fareSheet.length;x++){
              		vm.changeVersion(vm.workPackage.fareSheet[x], 'current'); 
              	}
              }
              
              if(vm.workPackage.addonFareSheet.length > 0){
              	for(var x=0;x<vm.workPackage.addonFareSheet.length;x++){
              		vm.changeVersion(vm.workPackage.addonFareSheet[x], 'current'); 
              	}
              }
              
              if(vm.workPackage.marketFareSheet.length > 0){
              	for(var x=0;x<vm.workPackage.marketFareSheet.length;x++){
              		vm.changeVersion(vm.workPackage.marketFareSheet[x], 'current');                	
              	}
              }
              
              
              if(vm.workPackage.discountFareSheet.length > 0){
              	for(var x=0;x<vm.workPackage.discountFareSheet.length;x++){
              		vm.changeVersion(vm.workPackage.discountFareSheet[x], 'current');
              	}
              }
              
              if(vm.workPackage.waiverFareSheet.length > 0){
              	for(var x=0;x<vm.workPackage.waiverFareSheet.length;x++){
              		vm.changeVersion(vm.workPackage.waiverFareSheet[x], 'current');
              	}
              }
        };
        
        vm.removeTab = function(){
        	var findTab = false;
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentTab.length;x++){
	        		if(vm.currentTab[x]){
	        			console.log('Active Fare Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.fareSheet.indexOf(vm.workPackage.fareSheet[x]);
	                	vm.workPackage.fareSheet.splice(x, 1); 
	                	//vm.selectedTab = 0;
	                	//vm.selectTab(0);
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentAddonTab.length;x++){
	        		if(vm.currentAddonTab[x]){
	        			console.log('Active Addon Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.addonFareSheet.indexOf(vm.workPackage.addonFareSheet[x]);
	                	vm.workPackage.addonFareSheet.splice(index, 1); 
	                	//vm.selectedAddonTab = 0;
	                	//vm.selectAddonTab(0);
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentDiscountTab.length;x++){
	        		if(vm.currentDiscountTab[x]){
	        			console.log('Active Discount Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.discountFareSheet.indexOf(vm.workPackage.discountFareSheet[x]);
	                	vm.workPackage.discountFareSheet.splice(index, 1); 
//	                	vm.selectedDiscountTab = 0;
	                	//vm.selectDiscountTab(0);
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentMarketTab.length;x++){
	        		if(vm.currentMarketTab[x]){
	        			console.log('Active Market Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.marketFareSheet.indexOf(vm.workPackage.marketFareSheet[x]);
	                	vm.workPackage.marketFareSheet.splice(index, 1); 
//	                	vm.selectedMarketTab = 0;
	                	//vm.selectMarketTab(0);
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentWaiverTab.length;x++){
	        		if(vm.currentWaiverTab[x]){
	        			console.log('Active Waiver Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.waiverFareSheet.indexOf(vm.workPackage.waiverFareSheet[x]);
	                	vm.workPackage.waiverFareSheet.splice(index, 1); 
//	                	vm.selectedWaiverTab = 0;
//	                	vm.selectWaiverTab(0);
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
        
        
        //ADDON TAB
        vm.selectedAddonTab = 0;     
//        if(vm.workPackage.addon){
//        	vm.currentAddonTab[0] = true;
//        }
        vm.selectAddonTab = function(index){
        	vm.resetTab(); 
        	if(vm.workPackage.addonFareSheet.length > 0){
        		vm.currentAddonTab[index] = true;
        	}
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
        if(vm.workPackage.discount){
        	vm.currentDiscountTab[0] = true;
        }
        
//        vm.selectTab = function(index){
//        	vm.resetTab(); 
//        	if(vm.workPackage.fareSheet.length > 0){
//        		vm.currentTab[index] = true;
//        	}
//        };
        vm.selectDiscountTab = function(index){
        	vm.resetTab();    
        	if(vm.workPackage.discountFareSheet.length > 0){
        		vm.currentDiscountTab[index] = true;
        	}
//        	vm.currentDiscountTab[index] = true;
//        	vm.selectedDiscountTab = index;
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
        if(vm.workPackage.marketFares){
        	vm.currentMarketTab[0] = true;
        }
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
        if(vm.workPackage.waiverFares){
        	vm.currentWaiverTab[0] = true;
        }
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
                	},
                    fareTypes: ['FareType', function(FareType) {
                        return FareType.getAll().$promise;
                    }],
                    sheet: function(){
                    	return null;
                    }
                }
			}).result.then(function(option) {
				vm.addTab(option);
				
				
            }, function() {
        			
            });
        };
        
        vm.copySheet = function(){
        	var findTab = false;
        	var clipboardSheet = {
    			//sheet: angular.copy(vm.workPackage.fareSheet[x])
    		};
        	if(!findTab){
	        	for(var x=0;x<vm.currentTab.length;x++){
	        		if(vm.currentTab[x]){
	        			console.log('Active Fare Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.fareSheet.indexOf(x);
//	        			vm.workPackage.fareSheet.push(angular.copy(vm.workPackage.fareSheet[x]));
	        			console.log(vm.workPackage.fareSheet[x]);
	        			if(vm.workPackage.fareSheet[x].fares != undefined && vm.workPackage.fareSheet[x].fares.length > 0){
		        			clipboardSheet.sheet = angular.copy(vm.workPackage.fareSheet[x]);
		        			clipboardSheet.type = "fares";
	        			}
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
//	        			vm.workPackage.addonFareSheet.push(angular.copy(vm.workPackage.addonFareSheet[x]));
	        			if(vm.workPackage.addonFareSheet[x].fares.length > 0){
		        			clipboardSheet.sheet = angular.copy(vm.workPackage.addonFareSheet[x]);
		        			clipboardSheet.type = "addon";
	        			}
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
//	        			vm.workPackage.marketFareSheet.push(angular.copy(vm.workPackage.marketFareSheet[x]));
	        			if(vm.workPackage.marketFareSheet[x].fares.length > 0){
		        			clipboardSheet.sheet = angular.copy(vm.workPackage.marketFareSheet[x]);
		        			clipboardSheet.type = "market";
	        			}
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentDiscountTab.length;x++){
	        		if(vm.currentDiscountTab[x]){
	        			console.log('Active Discount Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.discountFareSheet.indexOf(x);
//	        			vm.workPackage.discountFareSheet.push(angular.copy(vm.workPackage.discountFareSheet[x]));
	        			if(vm.workPackage.discountFareSheet[x].fares.length > 0){			        		
		        			clipboardSheet.sheet = angular.copy(vm.workPackage.discountFareSheet[x]);
		        			clipboardSheet.type = "discount";
	        			}
	        			break;
	        		}
	        	}
        	}
        	
        	if(!findTab){
	        	for(var x=0;x<vm.currentWaiverTab.length;x++){
	        		if(vm.currentWaiverTab[x]){
	        			console.log('Active Waiver Tab '+x);
	        			findTab = true;
	        			
	        			var index = vm.workPackage.waiverFareSheet.indexOf(x);
//	        			vm.workPackage.waiverFareSheet.push(angular.copy(vm.workPackage.waiverFareSheet[x]));
	        			if(vm.workPackage.waiverFareSheet[x].fares.length > 0){		
		        			clipboardSheet.sheet = angular.copy(vm.workPackage.waiverFareSheet[x]);
		        			clipboardSheet.type = "waiver";
	        			}
	        			break;
	        		}
	        	}
        	}
        	
        	
        	if(!findTab){
        		alert('Sheet cannot be copied');
        	}
        	else{
        		if(clipboardSheet.sheet != null){
	    			ClipboardSheet.copy(clipboardSheet, function(result){
	    				alert('Sheet copied');
	    			}, function(error){
	    				alert('Error occured');
	    			});
        		}
        		else{
        			alert('Sheet cannot be copied');
        		}
        	}
        };
        
        vm.removeSheet = function(){
        	if(vm.workPackage.reviewLevel != 'DISTRIBUTION'){
	        	var removed = vm.removeTab();
	        	if(!removed){
	        		alert('Sheet cannot be deleted');
	        	}
	        	else{
	        		alert('Sheet deleted');
	        	}
        	}
        };
        //END SHEET FUNCTION
        
        vm.pasteSheet = function(){
//        	alert('Paste Sheet');
        	if(vm.workPackage.reviewLevel != 'DISTRIBUTION'){
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
	                	},
	                    fareTypes: ['FareType', function(FareType) {
	                        return FareType.getAll().$promise;
	                    }],
	                    sheet: function(){
	                    	return ClipboardSheet.findByCurrentUsername({id : $stateParams.id}).$promise;
	                    }
	                }
				}).result.then(function(option) {
					var clipboardSheet = ClipboardSheet.findByCurrentUsername({id : $stateParams.id}).$promise;
					clipboardSheet.then(function(result){
						vm.addTab(option, result.sheet.fares);
						alert('Paste Sheet Success');
					});
	            }, function() {
	        			
	            });
        	}
        };
        
        vm.faresActionButton = [];
        vm.addonFaresActionButton = [];
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
        	else if(type == 'addon'){
        		vm.addonFaresActionButton[index] = !vm.addonFaresActionButton[index];
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
 	    vm.addFares = function(fareSheet){ 	 
 	    	console.log("ADD FARES");
 	    	if(fareSheet.fares == null){
 	    		fareSheet.fares = [];
       	  	}
 	    	
    		fareSheet.fares.push({
    			no:fareSheet.fares.length+1,
    			status:"PENDING",
    			action:"AUTO",
  	 	      	carrier:"GA"
    		});
 	    }
 	    
 	   function getDescendantProp (obj, desc) {
 		  var arr = desc.split('.');
 		  while (arr.length && (obj = obj[arr.shift()]));
 		  return obj;
 		}
 	   
 	   function sortBy(field, type) {
 		   if(type == 'asc'){
	 		    return function(a, b) {
	 		    	if(getDescendantProp(a, field) === null){
 			          return -1;
 			        }
 			        else if(getDescendantProp(b, field) === null){
 			          return 1;
 			        }
 			        else if (getDescendantProp(a, field) > getDescendantProp(b, field)) {
	 		            return 1;
	 		        } else if (getDescendantProp(a, field) < getDescendantProp(b, field)) {
	 		            return -1;
	 		        }
	 		    	
	 		        return 1;
	 		    };
 		   } else if(type='desc'){
 			  return function(a, b) {
 				  	if(getDescendantProp(a, field) === null){
 			          return 1;
 			        }
 			        else if(getDescendantProp(b, field) === null){
 			          return -1;
 			        }
 			        else if (getDescendantProp(a, field) > getDescendantProp(b, field)) {
	 		            return -1;
	 		        } else if (getDescendantProp(a, field) < getDescendantProp(b, field)) {
	 		            return 1;
	 		        }
	 		        return 1;
	 		    };
 		   }
 		}
 	  
 	    vm.sort = function(workPackageSheet, field){
 	    	if(workPackageSheet.sort == undefined){
 	 	    	workPackageSheet.sort = []; 	 	    		
 	    	}
 	    	if(workPackageSheet.sort[field] == undefined){
 	    		workPackageSheet.sort[field] = {asc:true}; 	    	
 	    	}
 	    	else{
 	    		workPackageSheet.sort[field].asc = !workPackageSheet.sort[field].asc;
 	    	} 	    
 	    	
 	    	workPackageSheet.currentSort = {field:field, asc:workPackageSheet.sort[field].asc};

 	    	if(field == '#'){
 	    		if(workPackageSheet.sort[field].asc){ 
 	    			workPackageSheet.fares.sort(sortBy('no', 'asc'));
 	    		}
 	    		else{
 	    			workPackageSheet.fares.sort(sortBy('no', 'desc'));
 	    		}
 	    	}
 	    	else{
 	    		if(workPackageSheet.sort[field].asc){ 	    			
 	    			workPackageSheet.fares.sort(sortBy(field, 'asc'));
 	    		}
 	    		else{
 	    			workPackageSheet.fares.sort(sortBy(field, 'desc')); 
 	    		}
 	    	}
 	    }
 	    
 	    vm.searchReplace = function(fareSheet, filter){
 	    	$uibModal.open({
	            templateUrl: 'app/pages/work-packages/work-package-search-replace-dialog.html',
	            controller: 'WorkPackageSearchReplaceDialogController',
	            controllerAs: 'vm',
	            backdrop: 'static',
	            size: 'lg',
	            windowClass: 'full-page-modal',
	            resolve: {
	            	fareSheet: function(){
	            		return fareSheet;
	            	},
	            	filter: function(){
	            		if(filter != null)
	            			return filter;
	            		return null;
	            	}
	            }
 	    	}).result.then(function(workPackageFareFilter) {
 	    		
 	    		function checkField(workPackageFareFilter, type, fare){
 	    			var listField = [
    	    			workPackageFareFilter.no.check && workPackageFareFilter.no.search != null ? 'no' : null,
    	    			workPackageFareFilter.status.check && workPackageFareFilter.status.search != null ? 'status' : null,
    	    			workPackageFareFilter.tariffNumber.tarNo.check && workPackageFareFilter.tariffNumber.tarNo.search != null ? 'tariffNumber.tarNo' : null,
    	    	    	workPackageFareFilter.tariffNumber.tarCd.check && workPackageFareFilter.tariffNumber.tarCd.search != null ? 'tariffNumber.tarCd' : null,
    	    	    	workPackageFareFilter.tariffNumber.global.check && workPackageFareFilter.tariffNumber.global.search != null ? 'tariffNumber.global' : null,
    	    			workPackageFareFilter.origin.check && workPackageFareFilter.origin.search != null ? 'origin' : null,
    	    			workPackageFareFilter.destination.check && workPackageFareFilter.destination.search != null ? 'destination' : null,
    	    			workPackageFareFilter.fareBasis.check && workPackageFareFilter.fareBasis.search != null ? 'fareBasis' : null,
    	    			workPackageFareFilter.bookingClass.check && workPackageFareFilter.bookingClass.search != null ? 'bookingClass' : null,
    	    	    	workPackageFareFilter.cabin.check && workPackageFareFilter.cabin.search != null ? 'cabin' : null,
    	    	    	workPackageFareFilter.typeOfJourney.check && workPackageFareFilter.typeOfJourney.search != null ? 'typeOfJourney' : null,
    	    	    	workPackageFareFilter.rtgno.check && workPackageFareFilter.rtgno.search != null ? 'rtgno' : null,
    	    	    	workPackageFareFilter.ruleno.check && workPackageFareFilter.ruleno.search != null ? 'ruleno' : null,
    	    	    	workPackageFareFilter.currency.check && workPackageFareFilter.currency.search != null ? 'currency' : null,
    	    	    	workPackageFareFilter.amount.check && workPackageFareFilter.amount.search != null ? 'amount' : null,
    	    	    	workPackageFareFilter.aif.check && workPackageFareFilter.aif.search != null ? 'aif' : null,
    	    	    	workPackageFareFilter.travelStart.check && workPackageFareFilter.travelStart.search != null ? 'travelStart' : null,
    	    	    	workPackageFareFilter.travelEnd.check && workPackageFareFilter.travelEnd.search != null ? 'travelEnd' : null,
    	    	    	workPackageFareFilter.saleStart.check && workPackageFareFilter.saleStart.search != null ? 'saleStart' : null,
    	    	    	workPackageFareFilter.saleEnd.check && workPackageFareFilter.saleEnd.search != null ? 'saleEnd' : null,
    	    	    	workPackageFareFilter.travelComplete.check && workPackageFareFilter.travelComplete.search != null ? 'travelComplete' : null,
    	    	    	workPackageFareFilter.travelCompleteIndicator.check && workPackageFareFilter.travelCompleteIndicator.search != null ? 'travelCompleteIndicator' : null,
    	    	    	workPackageFareFilter.comment.check && workPackageFareFilter.comment.search != null ? 'comment' : null,
    	    	    	workPackageFareFilter.ratesheetComment.check && workPackageFareFilter.ratesheetComment.search != null ? 'ratesheetComment' : null,
    	    		];
 	    			
 	    			var found = false;
 	    			if(type == 'and'){
	 	    			found = true;
	 	    			for(var x=0;x<listField.length;x++){
	 	    				if(listField[x] != null){
	 	    					if(getDescendantProp(fare, listField[x]) != getDescendantProp(workPackageFareFilter, listField[x]+'.search')){
//	 	    					if(fare[listField[x]] != workPackageFareFilter[listField[x]].search){
	 	    						found = false;
	 	    					}
	 	    				}
	 	    			}
 	    			}
 	    			else if(type == 'or'){
 	    				for(var x=0;x<listField.length;x++){
	 	    				if(listField[x] != null){
	 	    					if(getDescendantProp(fare, listField[x]) != getDescendantProp(workPackageFareFilter, listField[x]+'.search')){
//	 	    					if(fare[listField[x]] == workPackageFareFilter[listField[x]].search){
	 	    						found = true;
	 	    					}
	 	    				}
	 	    			}
 	    			}
 	    			return found;
 	    		}
 	    		
 	    		function replaceField(workPackageFareFilter, fare){
 	    			var listField = [
    	    			workPackageFareFilter.status.replace.check && workPackageFareFilter.status.replace.value != null ? 'status' : null
    	    		];
 	    			
 	    			for(var x=0;x<listField.length;x++){
 	    				if(listField[x] != null){
 	    					fare[listField[x]] = workPackageFareFilter.status.replace.value;
 	    				}
 	    			}
 	    		}
 	    		
    	    	var index = 0;
    	    	if(!workPackageFareFilter.replaceAll){
		    		if(workPackageFareFilter.index != null){
		    			index = workPackageFareFilter.index;
		    		}
    	    	}
	    		
	    		for(var i = 0; i < fareSheet.fares.length; i++){
	    			if(fareSheet.fares[i].field == null || fareSheet.fares[i].field == undefined){
    					fareSheet.fares[i].field = {};
    		    	}
	    			fareSheet.fares[i].field['no'] =  false;
	    		}
	    		
	    		var find = false;
	    		
	    		for(var i = index; i < fareSheet.fares.length; i++){
	    			
	    			if(fareSheet.fares[i].field == null || fareSheet.fares[i].field == undefined){
    					fareSheet.fares[i].field = {};
    		    	}
	    			
	    			fareSheet.fares[i].field['no'] =  false;
	    			
	    			if(checkField(workPackageFareFilter, workPackageFareFilter.andor, fareSheet.fares[i])){
    					find = true;
    					fareSheet.fares[i].field['no'] =  true;
    					
    					if(i+1 == fareSheet.fares.length){
    						workPackageFareFilter.index = 0;
    					}
    					else{
    						workPackageFareFilter.index = i+1;
    					}
    					
    					if(workPackageFareFilter.replace){
    						replaceField(workPackageFareFilter, fareSheet.fares[i]);
    						break;
    					}
    					else if(workPackageFareFilter.replaceAll){
    						replaceField(workPackageFareFilter, fareSheet.fares[i]);
    					}
    					else{
	    					break;	    						
    					}
    				}    			
	    		}
	    		if(!find){
	    			if(workPackageFareFilter.message == null){
	    				workPackageFareFilter.message = "No Matches found, continue search at the beginning?";
	    			}
//	    			else{
//	    				workPackageFareFilter.message = "No matches found";
//	    			}
	    		}
	    		
    	    	vm.searchReplace(fareSheet, workPackageFareFilter);        	    
            }, function() {
        			
            });
 	    };
        vm.rowFaresSelected = function(workPackageFare){
        	vm.selectedFare = workPackageFare;
	    }
        
        vm.deleteFaresSelected = function(type, workPackageFare, parentIdx, idx){
    		if(type == 'fares'){
    			vm.faresActionButton[parentIdx+''+idx] = false;
    		}
    		else if(type == 'market'){
    			vm.marketFaresActionButton[parentIdx+''+idx] = false;
    		}
    		else if(type == 'addon'){
    			vm.addonFaresActionButton[parentIdx+''+idx] = false;
    		}
    		else if(type == 'discount'){
    			vm.discountFaresActionButton[parentIdx+''+idx] = false;
    		}
    		else if(type == 'waiver'){
    			vm.waiverFaresActionButton[parentIdx+''+idx] = false;
    		}
        	workPackageFare.fares.splice(idx, 1);  
	    }
        
        vm.deleteDiscountFaresSelected = function(idx){
    		vm.workPackage.discountFareSheet[vm.selectedDiscountTab].fares.splice(idx, 1);  
        }
        
        vm.deleteMarketFaresSelected = function(idx){
    		vm.workPackage.marketFareSheet[vm.selectedMarketTab].fares.splice(idx, 1);  
        }
        
        vm.deleteWaiverFaresSelected = function(idx){
    		vm.workPackage.waiverFareSheet[vm.selectedWaiverTab].fares.splice(idx, 1);  
        }
        
        vm.duplicateFaresSelected = function(idx){
    		vm.workPackage.fares.push(JSON.parse(JSON.stringify(vm.workPackage.fares[vm.selectedTab])));
	    }
        
        function swap(input, index_A, index_B) {
    	    var temp = input[index_A];

    	    input[index_A] = input[index_B];
    	    input[index_B] = temp;
    	}
        vm.moveUpFare = function(workPackageSheet, idx){
        	if(idx != 0){
        		swap(workPackageSheet.fares, idx, idx-1);
        	}
        }
        vm.moveDownFare = function(workPackageSheet, idx){
        	if(idx != workPackageSheet.fares.length-1){
        		swap(workPackageSheet.fares, idx, idx+1);
        	}
        }
        
        vm.clearSelection = function(workPackageSheet){
        	
        	for(var x=0;x<workPackageSheet.fares.length;x++){
        		console.log(workPackageSheet.fares[x].field);
        		if(workPackageSheet.fares[x].field != undefined){
       			  Object.keys(workPackageSheet.fares[x].field).forEach(function(key,index) {
       				  
       				  if(workPackageSheet.fares[x].field[key]){
       					  if(key == 'tarno' || key == 'tarcd' || key == 'global'){
       						 workPackageSheet.fares[x].tariffNumber = null;
	   					  }
	   					  else{
	   						  workPackageSheet.fares[x][key] = null;       		
	   					  }
       				  }
       			  });
       		  }
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
        
//        var unsubscribe = $rootScope.$on('fmpApp:workPackageUpdate', function(event, result) {
//            vm.workPackage = result;
//        });
        $scope.$on('$destroy', function(){
        	window.onbeforeunload = null;
        });
      
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
        	console.log(date);
            vm.datePickerOpenStatus[date] = true;
        }
        function openCalendarRow(variable, sheet, row) {
        	//console.log("SHEET : "+sheet+" | ROW : "+row);
        	vm.datePickerOpenStatus[variable][sheet+row] = true;
            //vm.datePickerOpenStatus[date] = true;
        }
        
        function getCalendar (date){
        	return vm.datePickerOpenStatus[date];
        }
       
        
        vm.addFiling = function(){
	        	if(vm.workPackage.filingInstructionData == null){
	        		vm.workPackage.filingInstructionData = [];
	        	}
        		vm.workPackage.filingInstructionData.push({status:"PENDING", tarno:"", cxr:"GA", comment:"", file:"", fileContentType:""});
        }
        
         
	   vm.addAttachment = function(){
		 	if(vm.workPackage.attachmentData == null){
	        	vm.workPackage.attachmentData = [];
	        }
	   		vm.workPackage.attachmentData.push({comment:""});
	   }
	   
	   vm.removeAttachment = function(attachment){
		   if(vm.workPackage.status != "NEW"){
			   attachment.inOnly = false;
			   attachment.isDeleted = true;
		   }else{
			 var index = vm.workPackage.attachmentData.indexOf(attachment);
		  	 vm.workPackage.attachmentData.splice(index, 1); 
		   }
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
		    	WorkPackage.update(vm.workPackage, function onSaveSuccess(result){
		    		WorkPackage.passup(result, function(wp){
		    			console.log(wp);
		    			if(wp.validation != null && ((wp.validation.errorsCount > 0) || (wp.validation.warningsCount > 0))){
		    				vm.workPackage.validation = wp.validation;
		    				alert('There is '+wp.validation.errorsCount+' error(s) and '+wp.validation.warningsCount+' warning(s)');		    				
		    			}
		    			else{
			    			alert('Pass Up Success');
			    			$state.go('work-package');
		    			}
		    		}, function(){
		    			alert('Pass Up Failed');
		    		});
		    	}, function onSaveError(){
		    		alert('An error occured, please try again');
		    	});
		    } else {
		    }
	  };
	  
	  vm.passDown = function(){
		    if (confirm("Are you sure to Pass down this workorder?")) {
		    	WorkPackage.update(vm.workPackage, function onSaveSuccess(result){
		    		WorkPackage.passdown(result, function(){
		    			alert('Pass Down Success');
		    			$state.go('work-package');
		    		}, function(){
		    			alert('Pass Up Failed');
		    		});
		    	}, function onSaveError(){
		    		alert('An error occured, please try again');
		    	});
		    } else {
		    }
	  };
	  
	  vm.passSideway = function(){
		    if (confirm("Are you sure to Pass sideway this workorder?")) {
			    	WorkPackage.update(vm.workPackage, function onSaveSuccess(result){
			    		WorkPackage.passsideway(vm.workPackage, function(){
			    			alert('Pass Sideway Success');
			    			$state.go('work-package');
			    		}, function(){
			    			alert('Pass Sideway Failed');
			    		});
			    	}, function onSaveError(){
			    		alert('An error occured, please try again');
			    	});
		    		
		    } else {
		    }
	  };
	  
	  vm.resendApprove = function(){
		  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-approve-email-dialog.html',
              controller: 'WorkPackageApproveEmailDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              resolve: {
                  workPackage: vm.workPackage,              	  
	              email: ['SystemParameter', function(SystemParameter) {
	                   return vm.workPackage.approveConfig.email
	              }],
	              ccEmail: ['SystemParameter', function(SystemParameter) {
	                   return vm.workPackage.approveConfig.ccEmail
	              }],
	              statusResend : true
              }
          }).result.then(function(config) {
        	  vm.workPackage.approveConfig = config;
        	  	WorkPackage.resendApprove(vm.workPackage, function(){
	    			alert('Resend Success');
	    			$state.go('work-package');
    		}, function(){
    			alert('Approve Failed');
    		});
          }, function() {
      			
          });
	  };
	  
	  vm.viewRecipients = function(){
		  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-view-email-dialog.html',
              controller: 'WorkPackageViewEmailDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              resolve: {
                  workPackage: vm.workPackage,              	  
	              email: ['SystemParameter', function(SystemParameter) {
	                   return vm.workPackage.approveConfig.email
	              }],
	              ccEmail: ['SystemParameter', function(SystemParameter) {
	                   return vm.workPackage.approveConfig.ccEmail
	              }],
	              statusResend : true
              }
          }).result.then(function(config) {
        	  vm.workPackage.approveConfig = config;
        	  	WorkPackage.resendApprove(vm.workPackage, function(){
	    			$state.go('work-package');
    		}, function(){
    			console.log("fail");
    		});
          }, function() {
      			
          });
	  };
	  
	  vm.approve = function(){
		  var validated = true;
		  var cekStatus = "";
		  var counterApprove = false;

		  if(vm.workPackage.fareSheet != null && vm.workPackage.fareSheet.length > 0){
			  for(var x=0;x<vm.workPackage.fareSheet.length;x++){
				  if(vm.workPackage.fareSheet[x].fares != null && vm.workPackage.fareSheet[x].fares.length > 0){
					  //vm.expandCityGroup(vm.workPackage.fareSheet[x]);
					  for(var y=0;y<vm.workPackage.fareSheet[x].fares.length;y++){
						  if(vm.workPackage.fareSheet[x].fares[y].status == "APPROVED"){
							  counterApprove = true;
						  }
						  if(vm.workPackage.fareSheet[x].fares[y].status == "" || vm.workPackage.fareSheet[x].fares[y].status == "PENDING" || !counterApprove){
							  cekStatus = "Can not approve because status fare is : "+vm.workPackage.fareSheet[x].fares[y].status;
							  validated = false;
							  break;
						  }
					  }
				  }
			  }
		  }
		  
		  if(vm.workPackage.discountFareSheet != null && vm.workPackage.discountFareSheet.length > 0){
			  for(var x=0;x<vm.workPackage.discountFareSheet.length;x++){
				  if(vm.workPackage.discountFareSheet[x].fares != null && vm.workPackage.discountFareSheet[x].fares.length > 0){
					  for(var y=0;y<vm.workPackage.discountFareSheet[x].fares.length;y++){
						  if(vm.workPackage.discountFareSheet[x].fares[y].status == "APPROVED"){
							  counterApprove =true;
						  }
						  if(vm.workPackage.discountFareSheet[x].fares[y].status == "" || vm.workPackage.discountFareSheet[x].fares[y].status == "PENDING" || !counterApprove){
							  cekStatus = "Can not approve because status fare is : "+vm.workPackage.discountFareSheet[x].fares[y].status;
							  validated = false;
							  break;
						  }
					  }
				  }
			  }
		  }	
		  
		  if(vm.workPackage.addonFareSheet != null && vm.workPackage.addonFareSheet.length > 0){
			  for(var x=0;x<vm.workPackage.addonFareSheet.length;x++){
				  if(vm.workPackage.addonFareSheet[x].fares != null && vm.workPackage.addonFareSheet[x].fares.length > 0){
					  //vm.expandCityGroup(vm.workPackage.addonFareSheet[x]);
					  for(var y=0;y<vm.workPackage.addonFareSheet[x].fares.length;y++){
						  if(vm.workPackage.addonFareSheet[x].fares[y].status == "APPROVED"){
							  counterApprove =true;
						  }
						  if(vm.workPackage.addonFareSheet[x].fares[y].status == "" || vm.workPackage.addonFareSheet[x].fares[y].status == "PENDING" || !counterApprove){
							  cekStatus = "Can not approve because status fare is : "+vm.workPackage.addonFareSheet[x].fares[y].status;
							  validated = false;
							  break;
						  }
					  }
				  }
			  } 
		  }	
		  
		  if(vm.workPackage.marketFareSheet != null && vm.workPackage.marketFareSheet.length > 0){
			  for(var x=0;x<vm.workPackage.marketFareSheet.length;x++){
				  if(vm.workPackage.marketFareSheet[x].fares != null && vm.workPackage.marketFareSheet[x].fares.length > 0){
					  //vm.expandCityGroup(vm.workPackage.marketFareSheet[x]);
					  for(var y=0;y<vm.workPackage.marketFareSheet[x].fares.length;y++){
						  if(vm.workPackage.marketFareSheet[x].fares[y].status == "APPROVED"){
							  counterApprove =true;
						  }
						  if(vm.workPackage.marketFareSheet[x].fares[y].status == "" || vm.workPackage.marketFareSheet[x].fares[y].status == "PENDING" || !counterApprove){
							  cekStatus = "Can not approve because status fare is : "+vm.workPackage.marketFareSheet[x].fares[y].status;
							  validated = false;
							  break;
						  }
					  }
				  }
			  }
		  }	
		  
		  if(vm.workPackage.waiverFareSheet != null && vm.workPackage.waiverFareSheet.length > 0){
			  for(var x=0;x<vm.workPackage.waiverFareSheet.length;x++){
				  if(vm.workPackage.waiverFareSheet[x].fares != null && vm.workPackage.waiverFareSheet[x].fares.length > 0){
					  //vm.expandCityGroup(vm.workPackage.waiverFareSheet[x]);
					  for(var y=0;y<vm.workPackage.waiverFareSheet[x].fares.length;y++){
						  if(vm.workPackage.waiverFareSheet[x].fares[y].status == "APPROVED"){
							  counterApprove =true;
						  }
						  if(vm.workPackage.waiverFareSheet[x].fares[y].status == "" || vm.workPackage.waiverFareSheet[x].fares[y].status == "PENDING" || !counterApprove){
							  cekStatus = "Can not approve because status fare is : "+vm.workPackage.waiverFareSheet[x].fares[y].status;
							  validated = false;
							  break;
						  }
					  }
				  }
			  }
		  }	
		 
		  if(validated){
			  
			  if(vm.workPackage.fareSheet != null && vm.workPackage.fareSheet.length > 0){
				  for(var x=0;x<vm.workPackage.fareSheet.length;x++){
					  if(vm.workPackage.fareSheet[x].fares != null && vm.workPackage.fareSheet[x].fares.length > 0){
						  vm.expandCityGroup(vm.workPackage.fareSheet[x]);
					  }
				  }
			  }
			  
			  if(vm.workPackage.discountFareSheet != null && vm.workPackage.discountFareSheet.length > 0){
				  for(var x=0;x<vm.workPackage.discountFareSheet.length;x++){
					  if(vm.workPackage.discountFareSheet[x].fares != null && vm.workPackage.discountFareSheet[x].fares.length > 0){
						 
					  }
				  }
			  }	
			  
			  if(vm.workPackage.addonFareSheet != null && vm.workPackage.addonFareSheet.length > 0){
				  for(var x=0;x<vm.workPackage.addonFareSheet.length;x++){
					  if(vm.workPackage.addonFareSheet[x].fares != null && vm.workPackage.addonFareSheet[x].fares.length > 0){
						  vm.expandCityGroup(vm.workPackage.addonFareSheet[x]);
					  }
				  } 
			  }	
			  
			  if(vm.workPackage.marketFareSheet != null && vm.workPackage.marketFareSheet.length > 0){
				  for(var x=0;x<vm.workPackage.marketFareSheet.length;x++){
					  if(vm.workPackage.marketFareSheet[x].fares != null && vm.workPackage.marketFareSheet[x].fares.length > 0){
						  vm.expandCityGroup(vm.workPackage.marketFareSheet[x]);
					  }
				  }
			  }	
			  
			  if(vm.workPackage.waiverFareSheet != null && vm.workPackage.waiverFareSheet.length > 0){
				  for(var x=0;x<vm.workPackage.waiverFareSheet.length;x++){
					  if(vm.workPackage.waiverFareSheet[x].fares != null && vm.workPackage.waiverFareSheet[x].fares.length > 0){
						  vm.expandCityGroup(vm.workPackage.waiverFareSheet[x]);
					  }
				  }
			  }	
			  
			  $uibModal.open({
	              templateUrl: 'app/pages/work-packages/work-package-approve-email-dialog.html',
	              controller: 'WorkPackageApproveEmailDialogController',
	              controllerAs: 'vm',
	              backdrop: 'static',
	              size: 'lg',
	              resolve: {
	                  workPackage: vm.workPackage,              	  
		              email: ['SystemParameter', function(SystemParameter) {
		                   return SystemParameter.getSystemParameterByName({name : 'APPROVE_EMAIL'}).$promise;
		              }],
		              ccEmail: ['SystemParameter', function(SystemParameter) {
		                   return SystemParameter.getSystemParameterByName({name : 'APPROVE_CC_EMAIL'}).$promise;
		              }],
		              statusResend : false
	              }
	          }).result.then(function(config) {
	        	  vm.workPackage.approveConfig = config;
	        	  
	        	  WorkPackage.update(vm.workPackage, function onSaveSuccess(result){		        	  
		        	  WorkPackage.approve(vm.workPackage, function(){
		        		  alert('Approve Success');
		        		  $state.go('work-package');
		    		  }, function(){
		    			  alert('Approve Failed');
		    		  });
	        	  }, function onSaveError(){
	        		  alert('An error occured, please try again');
			      });
	          }, function() {
	      			
	          });
		  } else{
			  if(cekStatus.length != 0){
				  alert(cekStatus);
			  }else{
				 alert('Work Package cannot be approved, please check the workorder');  
			  }			  
		  }
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
	  
	  vm.complete = function(){
		    if (confirm("Are you sure to Complete this workorder?")) {
		    		WorkPackage.complete(vm.workPackage, function(){
		    			alert('Complete Success');
		    			$state.go('work-package');
		    		}, function(){
		    			alert('Complete Failed');
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
	  
	  function save (isValidate) {
          vm.isSaving = true;
          if (vm.workPackage.id !== null) {
        	  if(isValidate){
        		  vm.workPackage.validate = true;
        	  }else{
        		  vm.workPackage.validate = false;
        	  }
              WorkPackage.update(vm.workPackage, onSaveSuccess, onSaveError);
          } else {
              WorkPackage.save(vm.workPackage, onSaveSuccess, onSaveError);
          }
      }

      function onSaveSuccess (result) {
    	  alert("Save Success");
	      $scope.$emit('fmpApp:workPackageUpdate', result);
	      var data = result;
    	      
	      data.filingDate = DateUtils.convertDateTimeFromServer(data.filingDate);
          data.newCreatedDate = DateUtils.convertDateTimeFromServer(data.createdDate);
          data.distributionDate = DateUtils.convertDateTimeFromServer(data.distributionDate);
          data.discExpiryDate = DateUtils.convertDateTimeFromServer(data.discExpiryDate);
          data.queuedDate = DateUtils.convertDateTimeFromServer(data.queuedDate);
          data.lockedSince = DateUtils.convertDateTimeFromServer(data.lockedSince);
          data.saleDate = DateUtils.convertDateTimeFromServer(data.saleDate);
          
          if(data.fareSheet.length > 0){
          	for(var x=0;x<data.fareSheet.length;x++){          		
          		var fares = data.fareSheet[x].fares;
          		
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateTimeFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateTimeFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateTimeFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateTimeFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateTimeFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }
          
          if(data.addonFareSheet.length > 0){
          	for(var x=0;x<data.addonFareSheet.length;x++){
          		var fares = data.addonFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateTimeFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateTimeFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateTimeFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateTimeFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateTimeFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }
          
          if(data.marketFareSheet.length > 0){
          	for(var x=0;x<data.marketFareSheet.length;x++){
          		var fares = data.marketFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateTimeFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateTimeFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateTimeFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateTimeFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateTimeFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }
          
          
          if(data.discountFareSheet.length > 0){
          	for(var x=0;x<data.discountFareSheet.length;x++){
          		var fares = data.discountFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateTimeFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateTimeFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateTimeFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateTimeFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateTimeFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }
          vm.workPackage = data;
          vm.isSaving = false;
          
          if(data.validation != null && ((data.validation.errorsCount > 0) || (data.validation.warningsCount > 0))){
				alert('There is '+data.validation.errorsCount+' error(s) and '+data.validation.warningsCount+' warning(s)');		    				
		  }       
          
          
          
          if(vm.workPackage.fareSheet.length > 0){
            	for(var x=0;x<vm.workPackage.fareSheet.length;x++){
            		vm.changeVersion(vm.workPackage.fareSheet[x], vm.workPackage.fareSheet[x].version); 
            	}
            }
            
            if(vm.workPackage.addonFareSheet.length > 0){
            	for(var x=0;x<vm.workPackage.addonFareSheet.length;x++){
            		vm.changeVersion(vm.workPackage.addonFareSheet[x], vm.workPackage.addonFareSheet[x].version); 
            	}
            }
            
            if(vm.workPackage.marketFareSheet.length > 0){
            	for(var x=0;x<vm.workPackage.marketFareSheet.length;x++){
            		vm.changeVersion(vm.workPackage.marketFareSheet[x], vm.workPackage.marketFareSheet[x].version);                	
            	}
            }
            
            
            if(vm.workPackage.discountFareSheet.length > 0){
            	for(var x=0;x<vm.workPackage.discountFareSheet.length;x++){
            		vm.changeVersion(vm.workPackage.discountFareSheet[x], vm.workPackage.discountFareSheet[x].version);
            	}
            }
            
            if(vm.workPackage.waiverFareSheet.length > 0){
            	for(var x=0;x<vm.workPackage.waiverFareSheet.length;x++){
            		vm.changeVersion(vm.workPackage.waiverFareSheet[x], vm.workPackage.waiverFareSheet[x].version);
            	}
            }
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
      
      vm.importFare = function ($file, index) {
          if ($file) {
              DataUtils.toBase64($file, function(base64Data) {
                  $scope.$apply(function() {
                      var testing = { 
                    		  file : base64Data,
                    		  fileContentType : $file.type
                      };
                      
                      vm.workPackage.importFares = testing;
                      vm.workPackage.importIndex = index;
                      //send to backend
                      
                      WorkPackage.importFares(vm.workPackage, onImportSuccess, onImportFailure);
                      
                      function onImportSuccess(result){
                    	  	alert('Import Success');
                    	  	vm.mapWorkpackage(result);
                      }
                      
                      function onImportFailure(){
                    	    alert('Import Failed');
                      }
                  });
              });
          }
      };
      
      vm.importFareAddon = function ($file, index) {
          if ($file) {
              DataUtils.toBase64($file, function(base64Data) {
                  $scope.$apply(function() {
                      var testing = { 
                    		  file : base64Data,
                    		  fileContentType : $file.type
                      };
                      
                      vm.workPackage.importFares = testing;
                      vm.workPackage.importIndex = index;
                      //send to backend
                      
                      WorkPackage.importFaresAddon(vm.workPackage, onImportSuccess, onImportFailure);
                      
                      function onImportSuccess(result){
                  	  		alert('Import Success');
                  	  		vm.mapWorkpackage(result);
                      }
                      
                      function onImportFailure(){
                    	    alert('Import Failed');
                      }
                  });
              });
          }
      };
      
      vm.importFareWaiver = function ($file, index) {
          if ($file) {
              DataUtils.toBase64($file, function(base64Data) {
                  $scope.$apply(function() {
                      var testing = { 
                    		  file : base64Data,
                    		  fileContentType : $file.type
                      };
                      
                      vm.workPackage.importFares = testing;
                      vm.workPackage.importIndex = index;
                      //send to backend
                      
                      WorkPackage.importFaresWaiver(vm.workPackage, onImportSuccess, onImportFailure);
                      
                      function onImportSuccess(result){
                    	  	alert('Import Success');
                    	  	vm.mapWorkpackage(result);
                      }
                      
                      function onImportFailure(){
                    	    alert('Import Failed');
                      }
                  });
              });
          }
      };
      
      vm.mapWorkpackage = function(result){
    	  data = result;
  	  	  data.filingDate = DateUtils.convertDateTimeFromServer(data.filingDate);
          data.newCreatedDate = DateUtils.convertDateTimeFromServer(data.createdDate);
          data.distributionDate = DateUtils.convertDateTimeFromServer(data.distributionDate);
          data.discExpiryDate = DateUtils.convertDateTimeFromServer(data.discExpiryDate);
          data.queuedDate = DateUtils.convertDateTimeFromServer(data.queuedDate);
          data.lockedSince = DateUtils.convertDateTimeFromServer(data.lockedSince);
          data.saleDate = DateUtils.convertDateTimeFromServer(data.saleDate);
          
          if(data.fareSheet.length > 0){
          	for(var x=0;x<data.fareSheet.length;x++){
          		var fares = data.fareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateTimeFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateTimeFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateTimeFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateTimeFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateTimeFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }
          
          if(data.addonFareSheet.length > 0){
          	for(var x=0;x<data.addonFareSheet.length;x++){
          		var fares = data.addonFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateTimeFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateTimeFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateTimeFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateTimeFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateTimeFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }
          
          if(data.marketFareSheet.length > 0){
          	for(var x=0;x<data.marketFareSheet.length;x++){
          		var fares = data.marketFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateTimeFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateTimeFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateTimeFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateTimeFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateTimeFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }
          
          
          if(data.discountFareSheet.length > 0){
          	for(var x=0;x<data.discountFareSheet.length;x++){
          		var fares = data.discountFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateTimeFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateTimeFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateTimeFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateTimeFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateTimeFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }
          vm.workPackage = data;
      }
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
                    	  	vm.mapWorkpackage(result);
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
                    	  	vm.mapWorkpackage(result);
                      }
                      
                      function onImportFailure(){
                    	    alert('Import Failed');
                      }
                  });
              });
          }
      };
      
      vm.exportFares = function(index){
    	  /*
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-export-dialog.html',
              controller: 'WorkPackageExportDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              resolve: {
              }
          }).result.then(function(workPackage) {
//              	var params = {
//              			id: workPackage.id
//              	};
//            	$state.go('work-package-detail', params);
              //$state.go('work-package', null, { reload: 'work-package' });
          }, function() {
//              $state.go('work-package');
          });
    	  */
    	  
    	  vm.workPackage.exportIndex = index;
    	  WorkPackage.update(vm.workPackage, function onSaveSuccess(result){
    		  WorkPackage.exportFares(vm.workPackage, onExportSuccess, onExportFailure);
	    	  function onExportSuccess(result){
	    		  var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
      			var templateFilename = "Workorder_Fare.xlsx";
      			var blob = b64toBlob(result.file, fileType);
      			FileSaver.saveAs(blob, templateFilename);
	    	  }
	    	  function onExportFailure(){
	    		  
	    	  }    	 
	    	}, function onSaveError(){
	    		alert('An error occured, please try again');
	    	}); 
	       
      }
      
      vm.exportFaresWaiver = function(index){
    	  vm.workPackage.exportIndex = index;
    	  WorkPackage.update(vm.workPackage, function onSaveSuccess(result){
    		  WorkPackage.exportFaresWaiver(vm.workPackage, onExportSuccess, onExportFailure);
	    	  function onExportSuccess(result){
	    		  var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
      			var templateFilename = "Workorder_Waiver_Fare.xlsx";
      			var blob = b64toBlob(result.file, fileType);
      			FileSaver.saveAs(blob, templateFilename);
	    	  }
	    	  function onExportFailure(error){
	    		  console.log(error);
	    	  }    	 
	    	}, function onSaveError(){
	    		alert('An error occured, please try again');
	    	}); 
	       
      }
      vm.exportFaresAddon = function(index){
    	  /*
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-export-dialog.html',
              controller: 'WorkPackageExportDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              resolve: {
              }
          }).result.then(function(workPackage) {
//              	var params = {
//              			id: workPackage.id
//              	};
//            	$state.go('work-package-detail', params);
              //$state.go('work-package', null, { reload: 'work-package' });
          }, function() {
//              $state.go('work-package');
          });
    	  */
    	  
    	  vm.workPackage.exportIndex = index;
    	  WorkPackage.update(vm.workPackage, function onSaveSuccess(result){
    		  WorkPackage.exportFaresAddon(vm.workPackage, onExportSuccess, onExportFailure);
	    	  function onExportSuccess(result){
	    		  var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
      			var templateFilename = "Workorder_Addon_Fare.xlsx";
      			var blob = b64toBlob(result.file, fileType);
      			FileSaver.saveAs(blob, templateFilename);
	    	  }
	    	  function onExportFailure(){
	    		  
	    	  }    	 
	    	}, function onSaveError(){
	    		alert('An error occured, please try again');
	    	}); 
	       
      }
      
      vm.exportFaresMarket = function(index){
    	  vm.workPackage.exportIndex = index;
    	  WorkPackage.update(vm.workPackage, function onSaveSuccess(result){
    		  WorkPackage.exportFaresMarket(vm.workPackage, onExportSuccess, onExportFailure);
	    	  function onExportSuccess(result){
	    		  var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  			var templateFilename = "Workorder_MarketFare.xlsx";
	  			var blob = b64toBlob(result.file, fileType);
	  			FileSaver.saveAs(blob, templateFilename);
	    	  }
	    	  function onExportFailure(){
	    		  
	    	  }    	  
	       }, function onSaveError(){
	    		alert('An error occured, please try again');
	    	}); 
	  }
      
      vm.exportFaresDiscount = function(index){
    	  vm.workPackage.exportIndex = index;
    	  WorkPackage.update(vm.workPackage, function onSaveSuccess(result){    		
		  	  WorkPackage.exportFaresDiscount(vm.workPackage, onExportSuccess, onExportFailure);
		    	  function onExportSuccess(result){
		    		  var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		  			var templateFilename = "Workorder_DiscountFare.xlsx";
		  			var blob = b64toBlob(result.file, fileType);
		  			FileSaver.saveAs(blob, templateFilename);
		    	  }
		    	  function onExportFailure(){
		    		  
		    	  }    
	    	   }, function onSaveError(){
	    		alert('An error occured, please try again');
	    	}); 
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
                	  testing.fileName = $file.name;
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
      
      vm.addComment = function(){
	  	 if(vm.commentString != null){
	    	  if(vm.workPackage.comment == null){
	      		vm.workPackage.comment = [];
	      }
	    	  
    	  	vm.workPackage.comment.push({
    	  		comment:vm.commentString
    	  	});
    	  	vm.save();
    	  	vm.commentString = null;
    	  	$(document).ready(function(){
                var _width = $('.comment-wrapper').outerWidth();
	              $('.comment-list').css('min-width',_width)
	        });
    	 }
	  	
	
      }
      
      $scope.trustAsHtml = function(string) {
    	    return $sce.trustAsHtml(string);
     };
      
      
     
      
     vm.isFilingInstructionCollapse = true;
     
      vm.expandCommentFillingInstruction = function(){
    	  vm.isFilingInstructionCollapse = false;
      }
      
      vm.collapseCommentFillingInstruction = function(){
    	  vm.isFilingInstructionCollapse = true;
      }
      vm.addCommentFillingInstruction = function() {
	  	 	if (vm.commentStringFillingInstruction != null) {
	 	  		 if (vm.workPackage.filingInstructionData == null) {
	 	      		vm.workPackage.filingInstructionData = [];
	 	  		 }
	 	    	  
	 	  		 vm.workPackage.filingInstructionData.push({ 
	     	  		status:"PENDING", tarno:"", cxr:"GA", comment:vm.commentStringFillingInstruction, file:"", fileContentType:"", isDeleted:false, username :vm.user.login,
	     	  		createdTime :new Date()
	 	  		 });
	 	  		 vm.save();
	 	  		 vm.commentStringFillingInstruction = null;	 	  		
	 	  		 	 	  		
	 	  		 $(document).ready(function(){
	                var _width = $('.comment-wrapper').outerWidth();
		              $('.comment-list').css('min-width',_width)
		        });
	  	 	}
     }
     
      vm.deleteCommentFillingInstruction = function(){
    	 for(var l=0; l<vm.tempFIC.length; l++){
    		 vm.tempFIC[l].isDeleted = true;
    	 }
    	 vm.save();
      }
      
      vm.tempFIC = [];
      vm.selectCommentFillingInstruction = function(data){
    	  if(vm.tempFIC.indexOf(data) < 0){
    		  vm.tempFIC.push(data);
    	  }else{
    		  for(var x = 0; x<vm.tempFIC.length; x++){
    			  if(vm.tempFIC.indexOf(data) > -1){
        			  vm.tempFIC.splice(vm.tempFIC.indexOf(data),1);    				  
    			  }
    		  }    		  
    	  }
       }
      
      vm.removeFiling = function(filing){
	   		var index = vm.workPackage.filingInstructionData.indexOf(filing); 
	   		console.log(filing.isDeleted);
	   };
	         
      vm.addInterOffice = function(){
 	  	 if(vm.ioString != null){
 	    	  if(vm.workPackage.interofficeComment == null){
 	      		vm.workPackage.interofficeComment = [];
 	      }
 	    	  
     	  	vm.workPackage.interofficeComment.push({
     	  		comment:vm.ioString
     	  	});
     	  	vm.save();
     	  	vm.ioString = null;
     	  	$(document).ready(function(){
                var _width = $('.comment-wrapper').outerWidth();
	              $('.comment-list').css('min-width',_width)
	        });
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
      
      vm.ratesheetDiscount = function(){
	  	  $uibModal.open({
          templateUrl: 'app/pages/work-packages/work-package-discount-rate-sheet-dialog.html',
          controller: 'WorkPackageDiscountRateSheetDialogController',
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
      
      vm.ratesheetWaiver = function(){
	  	  $uibModal.open({
          templateUrl: 'app/pages/work-packages/work-package-waiver-rate-sheet-dialog.html',
          controller: 'WorkPackageWaiverRateSheetDialogController',
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
              data.newCreatedDate = DateUtils.convertDateTimeFromServer(data.createdDate);
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
              data.newCreatedDate = DateUtils.convertDateTimeFromServer(data.createdDate);
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
      
      //Waiver Function
      vm.calculateFareLost = function(fare){
    	  if(fare.waiverApprovedFare != null && fare.waiverNewBasicFare != null){
    		  fare.waiverFareLost = parseInt(fare.waiverApprovedFare) - parseInt(fare.waiverNewBasicFare);
    		  if(fare.waiverTotalPax !=null && fare.waiverPenaltyLostAmount != null){
        		  fare.waiverTotalLost = (parseInt(fare.waiverFareLost)+parseInt(fare.waiverPenaltyLostAmount))*parseInt(fare.waiverTotalPax);
        	  }
    	  }
      }
      vm.calculatePenaltyLost = function(fare){
    	  if(fare.waiverApprovedPn != null && fare.waiverOriginalPn != null){
    		  fare.waiverPenaltyLostPercent = (parseInt(fare.waiverApprovedPn) - parseInt(fare.waiverOriginalPn))/parseInt(fare.waiverApprovedPn)*100;
    		  fare.waiverPenaltyLostAmount = parseInt(fare.waiverApprovedPn) - parseInt(fare.waiverOriginalPn);
    		  if(fare.waiverTotalPax !=null && fare.waiverFareLost != null){
        		  fare.waiverTotalLost = (parseInt(fare.waiverFareLost)+parseInt(fare.waiverPenaltyLostAmount))*parseInt(fare.waiverTotalPax);
        	  }
    	  }
      }
      
      vm.calculateTotalLost = function(fare){
    	  if(fare.waiverTotalPax !=null && fare.waiverFareLost != null && fare.waiverFareLost != null){
    		  fare.waiverTotalLost = (parseInt(fare.waiverFareLost)+parseInt(fare.waiverPenaltyLostAmount))*parseInt(fare.waiverTotalPax);
    	  }
      }
      
      vm.routemap = function(){
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-routemap-dialog.html',
              controller: 'WorkPackageRoutemapDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              windowClass: 'full-page-modal',
              resolve: {
//              	workPackage: function(){
//              		return vm.workPackage;
//              	}
              }
			}).result.then(function(option) {
//				console.log(option);
//				vm.addTab(option);
          }, function() {
      			
          });
      }
      
      vm.downloadTemplate = function(){
    	  WorkPackage.downloadMarketRules(vm.workPackage, onDownloadSuccess, onDownloadFailure);
    	  function onDownloadSuccess(result){
    		  var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  			var templateFilename = "MarketRules.xlsx";
  			var blob = b64toBlob(result.file, fileType);
  			FileSaver.saveAs(blob, templateFilename);
    	  }
    	  function onDownloadFailure(err){
    		  console.log(err);
    	  }    	  
      };
      vm.close = function(){
    	  console.log(vm.disabledField(vm.workPackage));
    	  if(vm.disabledField(vm.workPackage)){
    		  $state.go("work-package");
    	  }else{
        	  WorkPackage.unlock(vm.workPackage, onUnlockedSuccess, onUnlockedFailure);
        	  function onUnlockedSuccess (result) {
        		  $state.go("work-package");
        	  }
        	  function onUnlockedFailure (error) {
        		  
        	  } 
    	  }    	  
      }
      
      vm.isAllSelected = {};
      vm.toggleAll = function(fares, idx) {
	     var toggleStatus = vm.isAllSelected[idx];
	     angular.forEach(fares, function(itm){ itm.selected = toggleStatus; });
	   
	  }
      
      vm.fillDown = function(workPackageSheet){
    	  var fares = [];
    	  for(var x=0;x<workPackageSheet.fares.length;x++){
    		  if(workPackageSheet.fares[x].field != undefined){
    			  var selected = false;
    			  Object.keys(workPackageSheet.fares[x].field).forEach(function(key,index) {
    				  if(workPackageSheet.fares[x].field[key]){
    					  selected = true;
    				  }
    			 });
    			  if(selected){
    				  fares.push({index:x, fare:workPackageSheet.fares[x]});
    				  break;
    			  }
    		  }
    	  }
    	  
    	  for(var x=0;x<workPackageSheet.fares.length;x++){
    		  if(fares[0].index != x){
    			  if(x > fares[0].index){
	    			  Object.keys(fares[0].fare.field).forEach(function(key,index) {
	    				  if(fares[0].fare.field[key]){
	    					  if(key == 'tarno' || key == 'tarcd' || key == 'global'){
	    						  workPackageSheet.fares[x].tariffNumber = fares[0].fare.tariffNumber;
	    					  }
	    					  else{
	    						  workPackageSheet.fares[x][key] = fares[0].fare[key];
	    					  }
	    				  }
	    			  });
    			  }
    		  }
    		  else{
    			  //console.log("Fare equal");
    		  }
    		  
    	  }
      }
      
      vm.duplicateSelectedFares = function(workPackageSheet){
    	  for(var x=0;x<workPackageSheet.fares.length;x++){
    		  if(workPackageSheet.fares[x].field != undefined){
    			  var selected = false;
    			  Object.keys(workPackageSheet.fares[x].field).forEach(function(key,index) {
    				  if(workPackageSheet.fares[x].field[key]){
    					  selected = true;
    				  }
    			 });
    			  if(selected){
    				  var copiedFare = angular.copy(workPackageSheet.fares[x]);
    				  copiedFare.no = workPackageSheet.fares.length+1;
    				  copiedFare.status = "PENDING";
    				  copiedFare.field = null;
    				  workPackageSheet.fares.push(copiedFare);
    			  }
    		  }
    	  }    	  
      }
      
      vm.copySelectedFares = function(workPackageSheet, currentPage){
    	  var fares = [];
    	  for(var x=0;x<workPackageSheet.fares.length;x++){
    		  if(workPackageSheet.fares[x].field != undefined){
    			  var selected = false;
    			  Object.keys(workPackageSheet.fares[x].field).forEach(function(key,index) {
    				  if(workPackageSheet.fares[x].field[key]){
    					  selected = true;
    				  }
    			 });
    			  
    			  if(selected){
    				  var copiedFare = angular.copy(workPackageSheet.fares[x]);
//    				  copiedFare.no = workPackageSheet.fares.length+1;
    				  copiedFare.status = "PENDING";
    				  copiedFare.field = null;
//    				  workPackageSheet.fares.push(copiedFare);
    				  fares.push(copiedFare);
    			  }
    		  }
    	  }
    	  var clipboard = {
    	      content:fares,
			  page:currentPage
		  }
		  
		  	Clipboard.copy(clipboard, function(result){
				alert('Fare copied');
			}, function(error){
				alert('Error occured');
			});
      }
      
      vm.pasteFares = function(workPackageSheet, currentPage){
    	  var clipboard = Clipboard.findByCurrentUsername({id : $stateParams.id}).$promise;
    	  
    	  clipboard.then(function(result){
    		  if(result.page == currentPage){
	    		  for(var x=0;x<result.content.length;x++){
	    			  result.content[x].no = workPackageSheet.fares.length+1;
	    			  
	    			  result.content[x].travelStart = DateUtils.convertDateTimeFromServer(result.content[x].travelStart);
	    			  result.content[x].travelEnd = DateUtils.convertDateTimeFromServer(result.content[x].travelEnd);
	    			  result.content[x].saleStart = DateUtils.convertDateTimeFromServer(result.content[x].saleStart);
	    			  result.content[x].saleEnd = DateUtils.convertDateTimeFromServer(result.content[x].saleEnd);
	    			  result.content[x].travelComplete = DateUtils.convertDateTimeFromServer(result.content[x].travelComplete);
	        		  workPackageSheet.fares.push(result.content[x]);    			  
	    		  }    
    		  }
    		  else{
    			  alert('Nothing to paste');
    		  }
    	  });
      }
      
      vm.deleteSelectedFares = function(workPackageSheet){
    	  var fares = [];
    	  for(var x=0;x<workPackageSheet.fares.length;x++){
    		  if(workPackageSheet.fares[x].field != undefined){
    			  var selected = false;
    			  Object.keys(workPackageSheet.fares[x].field).forEach(function(key,index) {
    				  if(workPackageSheet.fares[x].field[key]){
    					  selected = true;
    				  }
    			 });
    			 if(selected){
    				  if(workPackageSheet.fares[x].status != "APPROVED"){
    					  fares.push(workPackageSheet.fares[x]);
    				  }
//    				  var index = workPackageSheet.fares.indexOf(workPackageSheet.fares[x]);
//    				  workPackageSheet.fares.splice(index, 1); 
    				  
//    				  console.log("SELECTED : "+selected);
//    				  var copiedFare = angular.copy(workPackageSheet.fares[x]);
//    				  copiedFare.field = null;
//    				  workPackageSheet.fares.push(copiedFare);
    			  }
//    			  console.log("X : "+x);
    		  }
    	  }
    	  
    	  for(var y=0;y<fares.length;y++){
    		  for(var x=0;x<workPackageSheet.fares.length;x++){
    			  if(workPackageSheet.fares[x] == fares[y]){
    				  workPackageSheet.fares.splice(x, 1); 
    				  break;
    			  }
    		  }
    	  }
    	  
    	  //reset number
    	  workPackageSheet.fares.sort(sortBy('no', 'asc'));
    	  for(var i=0;i<workPackageSheet.fares.length; i++){
    		  workPackageSheet.fares[i].no = i+1;
    	  }
    	  
    	  if(workPackageSheet.currentSort.field == '#'){
    		  if(workPackageSheet.currentSort.asc){
        		  workPackageSheet.fares.sort(sortBy('no', 'asc'));
    		  }
    		  else{
        		  workPackageSheet.fares.sort(sortBy('no', 'desc'));
    		  }
    	  }
    	  else{
    		  if(workPackageSheet.currentSort.asc){
    			  workPackageSheet.fares.sort(sortBy(workPackageSheet.currentSort.field, 'asc'));
    		  }
    		  else{
    			  workPackageSheet.fares.sort(sortBy(workPackageSheet.currentSort.field, 'desc'));    			  
    		  }
    	  }
      }      
      
      vm.tbodyClick = function(workPackageSheet){
//    	  for(var x=0;x<workPackageSheet.fares.length;x++){
//    		  
//    	  }
      }
      
      
      vm.tdClick = function(workPackageSheet, fare, f, event){
    	  if (event.shiftKey){

    	  }else{
    		  for(var x=0;x<workPackageSheet.fares.length;x++){
				  workPackageSheet.fares[x].field = {};
        	  }
    	  }
    	  
    	  if(fare.field == null || fare.field == undefined){
    		  fare.field = {};
    	  }
    	  fare.field[f] = !fare.field[f];    	  
      }
      
      $scope.optionToggled = function(fares){
	    $scope.isAllSelected = fares.every(function(itm){ return itm.selected; })
	  }
      
      vm.getKey = function(obj, index){
    	  return Object.keys(obj)[index];
      }
      
      vm.selectTariff = function(fare, field){
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-select-tariff-dialog.html',
              controller: 'WorkPackageSelectTariffDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              windowClass: 'full-page-modal',
              resolve: {
	              	fare: function(){
	              		return fare;
	              	},
                  tariffNumber: ['TariffNumber', function(TariffNumber) {
                      return TariffNumber.getAll().$promise;
                  }],
                  AddOn : false,
              }
			}).result.then(function(option) {
				if(option != null){
					if(field=='tarcd'){
						fare[field] = option.tarCd;
					}else{
						fare[field] = option;
					}					
				}
          }, function() {
      			
          });
      }
      
      vm.selectTariffAddOn = function(fare, field){
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-select-tariff-dialog.html',
              controller: 'WorkPackageSelectTariffDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              windowClass: 'full-page-modal',
              resolve: {
	              	fare: function(){
	              		return fare;
	              	},
                  tariffNumber: ['TariffNumberAddOn', function(TariffNumberAddOn) {
                      return TariffNumberAddOn.getAll().$promise;
                  }],
                  AddOn : true,
              }
			}).result.then(function(option) {
				if(option != null){
					fare[field] = option;
				}
          }, function() {
      			
          });
      }
    
    	  
      vm.selectCity = function(fare, field){
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-select-city-dialog.html',
              controller: 'WorkPackageSelectCityDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              windowClass: 'full-page-modal',
              resolve: {
	              	fare: function(){
	              		return fare;
	              	},
                  cities: ['City', function(City) {
                      return City.getAll().$promise;
                  }],
                  cityGroup: ['CityGroup', function(CityGroup) {
                      return CityGroup.getAll().$promise;
                  }],
              }
			}).result.then(function(option) {
				if(option != null){
					if(option.type == 'city'){
						fare[field] = option.cityCode;					
					}
					else if(option.type == 'cityGroup'){
						fare[field] = option.code;	
					}
				}
          }, function() {
      			
          });
      }
      
      vm.selectCityATPCODiscount = function(fare, field){
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-select-city-dialog.html',
              controller: 'WorkPackageSelectCityDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              windowClass: 'full-page-modal',
              resolve: {
	              	fare: function(){
	              		return fare;
	              	},
                  cities: ['City', function(City) {
                      return City.getAll().$promise;
                  }],
                  cityGroup: ['CityGroup', function(CityGroup) {
                      return null;
                  }],
              }
			}).result.then(function(option) {
				if(option != null){
					if(field == 'loc1'){
						if(fare.loc1Type == 'C'){
							fare[field] = option.cityCode;				
						}else if(fare.loc1Type == 'N'){
							fare[field] = option.countryCode;
						}
					}else if(field == 'loc2'){
						if(fare.loc2Type == 'C'){
							fare[field] = option.cityCode;				
						}else if(fare.loc2Type == 'N'){
							fare[field] = option.countryCode;
						}
					}					
				}
					
          }, function() {
      			
          });
      }
      
      vm.selectState = function(fare, field){
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-select-state-dialog.html',
              controller: 'WorkPackageSelectStateDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              windowClass: 'full-page-modal',
              resolve: {
	              	fare: function(){
	              		return fare;
	              	},
              	 states: ['State', function(State) {
                     return State.getAll().$promise;
                 }],
                 header : function(){ return "States"}
              }
			}).result.then(function(option) {
				if(option != null){
					fare[field] = option.code;	
				}
					
          }, function() {
      			
          });
      }
      
      vm.selectCityGroup = function(fare, field){
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-select-state-dialog.html',
              controller: 'WorkPackageSelectStateDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              windowClass: 'full-page-modal',
              resolve: {
	              	fare: function(){
	              		return fare;
	              	},
              	 states: ['CityGroup', function(CityGroup) {
                     return CityGroup.getAll().$promise;
                 }],
                 header : function(){ return "City Group"}
              }
			}).result.then(function(option) {
				if(option != null){
					fare[field] = option.code;	
				}
					
          }, function() {
      			
          });
      }
      
      vm.selectPax = function(fare, field){
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-select-state-dialog.html',
              controller: 'WorkPackageSelectStateDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              windowClass: 'full-page-modal',
              resolve: {
	              	fare: function(){
	              		return fare;
	              	},
              	 states: ['Passenger', function(Passenger) {
                     return Passenger.getAll().$promise;
                 }],
                 header : function(){ return "Passenger"}
              }
			}).result.then(function(option) {
				if(option != null){
					fare[field] = option.code;	
				}
					
          }, function() {
      			
          });
      }
      
      vm.selectCurrency = function(fare, field){
    	  $uibModal.open({
              templateUrl: 'app/pages/work-packages/work-package-select-currency-dialog.html',
              controller: 'WorkPackageSelectCurrencyDialogController',
              controllerAs: 'vm',
              backdrop: 'static',
              size: 'lg',
              windowClass: 'full-page-modal',
              resolve: {
	              	fare: function(){
	              		return fare;
	              	},
                  currencies: ['Currency', function(City) {
                      return Currency.getAll().$promise;
                  }],
              }
			}).result.then(function(option) {
				if(option != null)
					fare[field] = option.currencyCode;
          }, function() {
      			
          });
      }
      
      vm.checkCurrency = function(fare, field){
    	  if(fare[field] != null || fare[field] != ''){
	    	  var exist = false;
	    	  for(var x=0;x<vm.currencies.length;x++){
	    		  if(vm.currencies[x].currencyCode.toUpperCase() == fare[field].toUpperCase()){
	    			  exist = true;
	    			  break;
	    		  }
	    	  }
	    	  
	    	  if(!exist){
	    		  alert("Currency code '"+fare[field]+"' is invalid. Please select a correct code");
	    		  fare[field] = null;
	    		  return;
	    	  }
    	  }
      }
      
      vm.checkpassengerType = function(fare, field){
    	  if(fare[field] != null || fare[field] != ''){
	    	  var exist = false;
	    	  for(var x=0;x<vm.passengers.length;x++){
	    		  if(vm.passengers[x].code.toUpperCase() == fare[field].toUpperCase()){
	    			  exist = true;
	    			  break;
	    		  }
	    	  }
	    	  
	    	  if(!exist){
	    		  alert("Passenger code '"+fare[field]+"' is invalid. Please select a correct code");
	    		  fare[field] = null;
	    		  return;
	    	  }
    	  }
      }
      
      vm.checkCity = function(fare, field){
    	  if(fare[field] != null && fare[field] != '' && fare[field] != undefined){
	    	  var exist = false;
	    	  for(var x=0;x<vm.cities.length;x++){
	    		  
	    		  if(vm.cities[x].cityCode.toUpperCase() == fare[field].toUpperCase()){
	    			  exist = true;
	    			  break;
	    		  }
	    	  }
	    	  for(var x=0;x<vm.cityGroups.length;x++){
	    		  if(vm.cityGroups[x].code.toUpperCase() == fare[field].toUpperCase()){
	    			  exist = true;
	    			  break;
	    		  }
	    	  }
	    	  
	    	  if(!exist){
	    		  alert("City code '"+fare[field]+"' is invalid. Please select a correct code");
	    		  fare[field] = null;
	    		  return;
	    	  }
    	  }
      }
      
      vm.checkLoc = function(fare, field, type){
    	  if(fare[field] != null || fare[field] != ''){
	    	  var exist = false;
	    	  if(type== 'C'){
	    		  for(var x=0;x<vm.cities.length;x++){
		    		  if(vm.cities[x].cityCode.toUpperCase() == fare[field].toUpperCase()){
		    			  exist = true;
		    			  break;
		    		  }
		    	  }				
				}else if(type== 'N'){
					 for(var x=0;x<vm.cities.length;x++){
			    		  if(vm.cities[x].countryCode.toUpperCase() == fare[field].toUpperCase()){
			    			  exist = true;
			    			  break;
			    		  }
			    	  }
				}else if(type== 'S'){
					 for(var x=0;x<vm.states.length;x++){
			    		  if(vm.states[x].code.toUpperCase() == fare[field].toUpperCase()){
			    			  exist = true;
			    			  break;
			    		  }
			    	  }
				}else if(type== 'A'){
					 for(var x=0;x<vm.areas.length;x++){
			    		  if(vm.areas[x].code == fare[field]){
			    			  exist = true;
			    			  break;
			    		  }
			    	  }
				}else if(type== 'G'){
					 for(var x=0;x<vm.cityGroups.length;x++){
			    		  if(vm.cityGroups[x].code.toUpperCase() == fare[field].toUpperCase()){
			    			  exist = true;
			    			  break;
			    		  }
			    	  }
				}		    	 
	    	  
	    	  if(!exist){
	    		  if(type== 'C'){
	    		  alert("City code '"+fare[field]+"' is invalid. Please select a correct code");
	    		  }else if(type== 'N'){
	    		  alert("Country code '"+fare[field]+"' is invalid. Please select a correct code");
	    		  }else if(type== 'S'){
	    		  alert("State code '"+fare[field]+"' is invalid. Please select a correct code");
	    		  }else if(type== 'A'){
	    		  alert("Area code '"+fare[field]+"' is invalid. Please select a correct code");
	    		  }else if(type== 'G'){
	    		  alert("City Group code '"+fare[field]+"' is invalid. Please select a correct code");
	    		  }
	    		  fare[field] = null;
	    		  return;
	    	  }
    	  }
      }
          
      vm.checkTariff = function(fare, field, inputField){
    	  var tariff = null;
    	  if(fare[field][inputField] != undefined && fare[field][inputField] != null && fare[field][inputField] != ""){
	    	  var exist = false;
	    	  for(var x=0;x<vm.tariffNumber.length;x++){	   
	    		  if(vm.tariffNumber[x][inputField] == fare[field][inputField]){
	    			  tariff = angular.copy(vm.tariffNumber[x]);
	    			  exist = true;
	    			  break;
	    		  }
	    	  }
	    	  
	    	  if(!exist){
	    		  alert("Tariff number is invalid. Please select a correct code");
	    		  fare[field] = null;
	    		  return;
	    	  }
	    	  else{
	    		  fare[field] = tariff;
	    	  }
    	  }
    	  else{
    		  fare[field] = null;
    		  return;
    	  }
      } 
      
      vm.checkTariffDiscount = function(fare, inputField){
    	  var tariff = null;
    	  if(fare[inputField] != undefined && fare[inputField] != null && fare[inputField] != ""){
	    	  var exist = false;
	    	  for(var x=0;x<vm.tariffNumber.length;x++){
	    		  if(vm.tariffNumber[x].tarCd == fare[inputField].toUpperCase()){
	    			  tariff = angular.copy(vm.tariffNumber[x].tarCd);
	    			  exist = true;
	    			  break;
	    		  }
	    	  }
	    	  
	    	  if(!exist){
	    		  alert("Tariff number is invalid. Please select a correct code");
	    		  fare[inputField] = null;
	    		  return;
	    	  }
	    	  else{
	    		  fare[inputField] = tariff;
	    	  }
    	  }
    	  else{
    		  fare[inputField] = null;
    		  return;
    	  }
      } 
      
      vm.keypress = function(event, regexp){
    	  
    	  if (event.charCode!=0) {
    			var regex = new RegExp(regexp);
    			var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    			if (!regex.test(key)) {
    				event.preventDefault();
    				return false;
    			}
    		}
      }
      
      vm.changeVersion = function(workPackageSheet, index){    	  
    	  workPackageSheet.version = index;
    	  if(index == 'current'){
    		  if(workPackageSheet.fares == null){
    			  workPackageSheet.fares = [];
    		  }
    		  workPackageSheet.currentFares = workPackageSheet.fares;
    	  }
    	  else{
    		  if(index != null){
    			  workPackageSheet.currentFares = workPackageSheet.fareVersion[index].fares;
    		  }
    	  }
      }
      

      vm.checkValidateCityGroupFares = function(workPackageSheet){
    	  var fares = workPackageSheet.fares;
    	  for(var x=0;x<fares.length;x++){
    		  var origin = false;
    		  var destination = false;
    		  
    		  for(var y=0;y<vm.cityGroups.length;y++){
    			  if(fares[x] != undefined){    				  	    			  
    				  if((vm.cityGroups[y].code != null && vm.cityGroups[y].code.toUpperCase()) == (fares[x].origin != null && fares[x].origin.toUpperCase())){	    				  
    					  origin = true;
	    			  }
	    			  if((vm.cityGroups[y].code != null && vm.cityGroups[y].code.toUpperCase()) == (fares[x].destination != null && fares[x].destination.toUpperCase())){
	      				  destination = true;
	    			  }	    			   
    			  }
    		  }
    		  
    		  if(origin){
				  return false;
			  }else if(destination){
				  return false;
			  }
    	  }
    	  return true;
      }
      vm.expandCityGroup = function(workPackageSheet){
    	  var fares = workPackageSheet.fares;
    	  
    	  var faresCityGroupOrigin = [];
    	  var faresCityGroupDestination = [];
    	  var faresCityGroupOriginDestination = [];

    	  for(var x=0;x<fares.length;x++){
    		  var origin = false;
    		  var destination = false;
    		  
    		  for(var y=0;y<vm.cityGroups.length;y++){
    			  if(fares[x] != undefined){    				  	    			  
    				  if((vm.cityGroups[y].code != null && vm.cityGroups[y].code.toUpperCase()) == (fares[x].origin != null && fares[x].origin.toUpperCase())){	    				  
    					  origin = true;
	    			  }
	    			  if((vm.cityGroups[y].code != null && vm.cityGroups[y].code.toUpperCase()) == (fares[x].destination != null && fares[x].destination.toUpperCase())){
	      				  destination = true;
	    			  }	    			   
    			  }
    		  }
    		  

			  if(origin && destination){
				  faresCityGroupOriginDestination.push(angular.copy(fares[x]));
				  fares.splice(x, 1);
			  }else if(origin){
				  faresCityGroupOrigin.push(angular.copy(fares[x]));
				  fares.splice(x, 1);
			  }else if(destination){
				  faresCityGroupDestination.push(angular.copy(fares[x]));
				  fares.splice(x, 1);
			  }
    	  }
    	  
    	  if(faresCityGroupOriginDestination.length > 0){
    		  for(var x=0;x<faresCityGroupOriginDestination.length;x++){
    			  var listCitiesOrigin = [];
        		  var listCitiesDestination = [];
        		 
        		  for(var y=0;y<vm.cityGroups.length;y++){
	    			  if((vm.cityGroups[y].code != null && vm.cityGroups[y].code.toUpperCase()) == (faresCityGroupOriginDestination[x].origin != null && faresCityGroupOriginDestination[x].origin.toUpperCase())){
	    				  listCitiesOrigin = vm.cityGroups[y].cities;
					  }
	    			  if((vm.cityGroups[y].code != null && vm.cityGroups[y].code.toUpperCase()) == (faresCityGroupOriginDestination[x].destination != null && faresCityGroupOriginDestination[x].destination.toUpperCase())){
	    				  listCitiesDestination = vm.cityGroups[y].cities;
					  }
        		  }
        		  
    			  for(var a=0;a<listCitiesOrigin.length;a++){
    				  for(var b=0;b<listCitiesDestination.length;b++){
    					  var f = angular.copy(faresCityGroupOriginDestination[x]);
        				  f.origin = listCitiesOrigin[a].cityCode;
        				  f.destination = listCitiesDestination[b].cityCode;
        				  f.no = fares.length+1;
        				  fares.push(f);
    				  }
    			  }
    		  }
    	  }    	  
    	  else if(faresCityGroupOrigin.length > 0){
    		  for(var x=0;x<faresCityGroupOrigin.length;x++){
    			  var listCities = [];
    			  for(var y=0;y<vm.cityGroups.length;y++){
    				  if((vm.cityGroups[y].code != null && vm.cityGroups[y].code.toUpperCase()) == (faresCityGroupOrigin[x].origin != null && faresCityGroupOrigin[x].origin.toUpperCase())){
    					  listCities = vm.cityGroups[y].cities;
    					  break;
    				  }
    			  }
    			  
    			  for(var z=0;z<listCities.length;z++){
    				  var f = angular.copy(faresCityGroupOrigin[x]);
    				  f.origin = listCities[z].cityCode;
    				  f.no = fares.length+1;
    				  fares.push(f);
    			  }
    		  }
    	  }
    	  else if(faresCityGroupDestination.length > 0){
    		  for(var x=0;x<faresCityGroupDestination.length;x++){
    			  var listCities = [];
    			  for(var y=0;y<vm.cityGroups.length;y++){
    				  if((vm.cityGroups[y].code != null && vm.cityGroups[y].code.toUpperCase()) == (faresCityGroupDestination[x].destination != null && faresCityGroupDestination[x].destination.toUpperCase())){
    					  listCities = vm.cityGroups[y].cities;
    					  break;
    				  }
    			  }
    			  
    			  for(var z=0;z<listCities.length;z++){
    				  var f = angular.copy(faresCityGroupDestination[x]);
    				  f.destination = listCities[z].cityCode;
    				  f.no = fares.length+1;
    				  fares.push(f);
    			  }
    		  }
    	  }
      };
      
      vm.disabledField = function(wp){
    	  var disabled = false;
    	  if(wp.locked == true && wp.locked !=null){
    		  if( wp.lockedBy == vm.user.login){
    			  disabled = false;
    		  }else{
    			  disabled = true;
    		  }    		  
    	  }
    	  return disabled;
      }
      
      vm.getTooltip = function(value){
    	  var listCity = [];
    	  for(var y=0;y<vm.cityGroups.length;y++){
			  if((vm.cityGroups[y].code != null && vm.cityGroups[y].code.toUpperCase()) == (value != null && value.toUpperCase())){
				  for(var x=0;x<vm.cityGroups[y].cities.length;x++){
					  //message += "<li>"+vm.cityGroups[y].cities[x].code+"</li>";
					  listCity.push(vm.cityGroups[y].cities[x].cityCode);
				  }
				  break;
			  }
		  }
    	  
    	  var message = "";
    	  if(listCity.length > 0){
    		  message += listCity.join(', ');
    	  }
    	  return message;
      }
      
      vm.dateNgModelOpts = {
    		  timezone : '+07:00'
	  };
    }
})();
