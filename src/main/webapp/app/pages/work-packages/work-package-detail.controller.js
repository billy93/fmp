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
    WorkPackageDetailController.$inject = ['$window', '$sce', 'currencies','tariffNumber','tariffNumberAddOn', 'cities', 'FileSaver', '$uibModal', 'DateUtils', 'DataUtils', 'Account', '$scope', '$state', '$rootScope', '$stateParams', 'previousState', 'entity', 'WorkPackage', 'ProfileService', 'user', 'fareTypes', 'businessAreas', 'passengers', 'priorities', 'states', 'cityGroups', 'Currency', 'atpcoFareTypes', 'ClipboardSheet', 'Clipboard', '$timeout', 'viewOnly', 'AtpcoMasterTariff'];
    function WorkPackageDetailController($window, $sce, currencies,tariffNumber,tariffNumberAddOn, cities, FileSaver, $uibModal, DateUtils, DataUtils, Account, $scope, $state, $rootScope, $stateParams, previousState, entity, WorkPackage, ProfileService, user, fareTypes, businessAreas, passengers, priorities, states, cityGroups, Currency, atpcoFareTypes, ClipboardSheet, Clipboard, $timeout, viewOnly, AtpcoMasterTariff) {
    	var vm = this;

    	window.onbeforeunload = function (e) {
    		   // handle the exit event
    		return "Please click 'Stay on this Page' if you did this unintentionally";
    	};

    	window.addEventListener("beforeunload", function() {
    		console.log(event);
    		console.log(event.srcElement.URL);
    		console.log(event.target.URL);
    	});

    	vm.editorConfig = {
		    sanitize: false,
		    toolbar: [
			{ name: 'basicStyling', items: ['bold', 'italic', 'underline', 'strikethrough', 'subscript', 'superscript', '-', 'leftAlign', 'centerAlign', 'rightAlign', 'blockJustify', '-'] },
			{ name: 'doers', items: ['removeFormatting', 'undo', 'redo', '-'] },
			{ name: 'colors', items: ['fontColor', 'backgroundColor', '-'] },
			{ name: 'styling', items: ['font', 'size', 'format'] },
		    ]
		};

    	$scope.$on("$destroy", function() {
	    	WorkPackage.closeEditor(vm.workPackage, closedSuccess, closedFailure);
	      	  function closedSuccess (result) {
	      	  }
	      	  function closedFailure (error) {
	      	  }
    	   });

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
//        $scope.dateformat = "yyyy-MM-dd";
        $scope.dateformat = "dd/MM/yyyy";
        vm.optionFare = fareTypes;
        vm.atpcoFareTypes = atpcoFareTypes;

        vm.isViewOnly = viewOnly;

        if (vm.isViewOnly) {
        	$(document).ready(function() {
        		$('.input-group-addon, iframe').addClass('disabled poiv-none');
        		$('input, i, select:not(#version), iframe, textarea').attr('disabled', 'disabled');
        		$('.view-only').addClass('disabled', 'disabled');
        	});
        }

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
    		"1":"One Way",
    		"2":"Round Trip",
    		"3":"One Way Only"
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
        	{
        		name:"expPax",
        		editable:["HO", "LSO"],
        		mandatory:["HO", "LSO"]
        	},
        	{
        		name:"expRev",
        		editable:["HO", "LSO"],
        		mandatory:["HO", "LSO"]
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
        		editable:["LSO", "HO"],
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
        		mandatory:[]
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
        		editable:["LSO", "HO"],
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
        		],
	        	editableExtraCondition:[
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
        		mandatory:[],
        		mandatoryExtraCondition:[
        			{
        				field:"calcType",
        				isEqual:"M"
        			},
        			{
        				field:"calcType",
        				isEqual:"S"
        			}
        		],
        		editableExtraCondition:[
	    			{
	    				field:"calcType",
	    				isEqual:"M"
	    			},
	    			{
	    				field:"calcType",
	    				isEqual:"S"
	    			}
	    		]
        	},
        	{
        		name:"discountSpecifiedAmount",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[],
        		mandatoryExtraCondition:[
        			{
        				field:"calcType",
        				isEqual:"M"
        			},
        			{
        				field:"calcType",
        				isEqual:"S"
        			}
        		],
        		editableExtraCondition:[
	    			{
	    				field:"calcType",
	    				isEqual:"M"
	    			},
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
        		mandatory:[],
        		mandatoryExtraCondition:[
        			{
        				field:"calcType",
        				isEqual:"S"
        			}
        		],
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
        		mandatory:[],
        		mandatoryExtraCondition:[
        			{
        				field:"calcType",
        				isEqual:"M"
        			},
        			{
        				field:"calcType",
        				isEqual:"S"
        			}
        		],
        		editableExtraCondition:[
	    			{
	    				field:"calcType",
	    				isEqual:"M"
	    			},
	    			{
	    				field:"calcType",
	    				isEqual:"S"
	    			}
	    		]
        	},
        	{
        		name:"discountGlobal",
        		editable:["LSO", "HO",  "DISTRIBUTION"],
        		mandatory:[],
        		mandatoryExtraCondition:[
        			{
        				field:"calcType",
        				isEqual:"M"
        			},
        			{
        				field:"calcType",
        				isEqual:"S"
        			}
        		],
        		editableExtraCondition:[
	    			{
	    				field:"calcType",
	    				isEqual:"M"
	    			},
	    			{
	    				field:"calcType",
	    				isEqual:"S"
	    			}
	    		]
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
        		editable:["LSO", "HO",  "DISTRIBUTION", "ROUTE_MANAGEMENT"],
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

        vm.isEditable = function(field, fare){
        	return vm.checkField(field, 'editable', fare);
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
        				var extraCondition = vm.fields[x].editableExtraCondition;

        				if(extraCondition != null && extraCondition.length > 0){

        					if(reviewLevels.indexOf(vm.workPackage.reviewLevel) > -1){
	        					for(var y=0;y<extraCondition.length;y++){
	        						if(fare != null){
	        							//Check extra condition here
	        							var field = extraCondition[y].field;

	        							//Check other field empty condition
	        							var isEmpty = extraCondition[y].isEmpty;
	        							if(isEmpty){
	        								if(fare[field] == null || fare[field] == ''){
	        									result = false;
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
//        						for(var y=0;y<extraCondition.length;y++){
//        							var otherField = extraCondition[y].field;
//        							if(fare != null){
//        								if(fare[otherField] == extraCondition[y].isEqual){
//        									result = true;
//        									break;
//        								}
//        							}
//        							else{
//
//        							}
//        						}
        					}
        				} else {
	        				if(reviewLevels.indexOf(vm.workPackage.reviewLevel) > -1){
	        					result = true;
	        					break;
	        				}
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
        		vm.workPackage.waiverFareSheet.push({waiverFaresName:option.name, waiverFareType:option.fareType, fareType:option.fareType, fares:fares});
        		vm.selectWaiverTab(vm.workPackage.waiverFareSheet.length-1);
        	}
        	else if(option.type == 'Discount Fares'){
        		vm.workPackage.discount = true;
        		vm.workPackage.discountFareSheet.push({discountFaresName:option.name, discountFareType:option.fareType, fareType:option.fareType, fares:fares});
        		vm.selectDiscountTab(vm.workPackage.discountFareSheet.length-1);
        	}
        	else if(option.type == 'Add-Ons'){
        		vm.workPackage.addon = true;
        		vm.workPackage.addonFareSheet.push({addonFaresName:option.name, fareType:option.fareType, fares:fares});
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
//	        			console.log('Active Fare Tab '+x);
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
//	        			console.log('Active Addon Tab '+x);
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
//	        			console.log('Active Discount Tab '+x);
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
//	        			console.log('Active Market Tab '+x);
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
//	        			console.log('Active Waiver Tab '+x);
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
//	        			console.log('Active Fare Tab '+x);
	        			findTab = true;

	        			var index = vm.workPackage.fareSheet.indexOf(x);
//	        			vm.workPackage.fareSheet.push(angular.copy(vm.workPackage.fareSheet[x]));
//	        			console.log(vm.workPackage.fareSheet[x]);
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
//	        			console.log('Active Addon Tab '+x);
	        			findTab = true;

	        			var index = vm.workPackage.addonFareSheet.indexOf(x);
//	        			vm.workPackage.addonFareSheet.push(angular.copy(vm.workPackage.addonFareSheet[x]));
	        			if(vm.workPackage.addonFareSheet[x].fares.length > 0){
		        			clipboardSheet.sheet = angular.copy(vm.workPackage.addonFareSheet[x]);

		        			if(vm.workPackage.type == 'REGULAR'){
		        				clipboardSheet.type = "addon-fares";
		        			}
		        			else if(vm.workPackage.type == 'MARKET'){
		        				clipboardSheet.type = "addon-market";
		        			}
	        			}
	        			break;
	        		}
	        	}
        	}

        	if(!findTab){
	        	for(var x=0;x<vm.currentMarketTab.length;x++){
	        		if(vm.currentMarketTab[x]){
//	        			console.log('Active Market Tab '+x);
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
//	        			console.log('Active Discount Tab '+x);
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
//	        			console.log('Active Waiver Tab '+x);
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
        	if(vm.workPackage.reviewLevel != 'DISTRIBUTION'){
        		ClipboardSheet.findByCurrentUsername({id : $stateParams.id}).$promise.then(function(sheet){
        			if(sheet != null){
        	        	if(vm.workPackage.type == 'REGULAR'){
        		        	if(sheet.type == 'fares' || sheet.type == 'addon-fares'){
        		        		vm.openAddSheetDialog();
        		        	}
        		        	else{
        		        		alert('No sheet copied');
        		        	}
        	        	}
        	        	else if(vm.workPackage.type == 'MARKET'){
        	        		if(sheet.type == 'market' || sheet.type == 'addon-market'){
        		        		vm.openAddSheetDialog();
        		        	}
        	        		else{
        		        		alert('No sheet copied');
        		        	}
        	        	}
        	        	else if(vm.workPackage.type == 'DISCOUNT'){
        	        		if(sheet.type == 'discount'){
        		        		vm.openAddSheetDialog();
        		        	}
        	        		else{
        		        		alert('No sheet copied');
        		        	}
        	        	}
        	        	else if(vm.workPackage.type == 'WAIVER'){
        	        		if(sheet.type == 'waiver'){
        		        		vm.openAddSheetDialog();
        		        	}
        	        		else{
        		        		alert('No sheet copied');
        		        	}
        	        	}
        	        	else{
        	        		alert('No sheet copied');
        	        	}
        	        }
        	        else{
        	        	alert('No sheet copied');
        	        }
        		});
        	}
        };

        vm.openAddSheetDialog = function(){
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

					for(var l=0; l<result.sheet.fares.length; l++){
						try {
							result.sheet.fares[l].status="PENDING";
						} catch (e) {
						}

					}

					vm.addTab(option, result.sheet.fares);
					alert('Paste Sheet Success');
				});
            }, function() {

            });
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
//        			console.log(error);
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
//            				console.log("BOOKING CLASS : "+result.fares[i].bookingClass);
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
//	    			console.log(result);

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
// 	    	console.log("ADD FARES");
 	    	if(fareSheet.fares == null){
 	    		fareSheet.fares = [];
       	  	}

    		fareSheet.fares.push({
    			no:fareSheet.fares.length+1,
    			status:"PENDING",
    			action:"A",
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

 	    vm.searchReplace = function(fareType, table, fareSheet, filter){
 	    	$uibModal.open({
	            templateUrl: 'app/pages/work-packages/work-package-search-replace-dialog.html',
	            controller: 'WorkPackageSearchReplaceDialogController',
	            controllerAs: 'vm',
	            backdrop: 'static',
	            size: 'lg',
	            windowClass: 'full-page-modal',
	            resolve: {
	            	workPackage: function(){
	            		return vm.workPackage;
	            	},
	            	fareSheet: function(){
	            		return fareSheet;
	            	},
	            	filter: function(){
	            		if(filter != null)
	            			return filter;
	            		return null;
	            	},
	            	fareType: function(){
	            		return fareType;
	            	}
	            }
 	    	}).result.then(function(workPackageFareFilter) {
 	    		function checkField(workPackageFareFilter, type, fare){
 	    			//regular
 	    			var listField = [
    	    			workPackageFareFilter.no.check && workPackageFareFilter.no.search != null && workPackageFareFilter.no.search != '' ? 'no' : null,
    	    			workPackageFareFilter.status.check && workPackageFareFilter.status.search != null && workPackageFareFilter.status.search != '' ? 'status' : null,
    	    			workPackageFareFilter.action.check && workPackageFareFilter.action.search != null && workPackageFareFilter.action.search != '' ? 'action' : null,
    	    			workPackageFareFilter.tariffNumber.tarNo.check && workPackageFareFilter.tariffNumber.tarNo.search != null ? 'tariffNumber.tarNo' : null,
    	    	    	workPackageFareFilter.tariffNumber.tarCd.check && workPackageFareFilter.tariffNumber.tarCd.search != null && workPackageFareFilter.tariffNumber.tarCd.search != '' ? 'tariffNumber.tarCd' : null,
    	    	    	workPackageFareFilter.tariffNumber.global.check && workPackageFareFilter.tariffNumber.global.search != null && workPackageFareFilter.tariffNumber.global.search != '' ? 'tariffNumber.global' : null,
    	    			workPackageFareFilter.origin.check && workPackageFareFilter.origin.search != null && workPackageFareFilter.origin.search != '' ? 'origin' : null,
    	    			workPackageFareFilter.destination.check && workPackageFareFilter.destination.search != null && workPackageFareFilter.destination.search != '' ? 'destination' : null,
    	    			workPackageFareFilter.fareBasis.check && workPackageFareFilter.fareBasis.search != null && workPackageFareFilter.fareBasis.search != '' ? 'fareBasis' : null,
    	    			workPackageFareFilter.bookingClass.check && workPackageFareFilter.bookingClass.search != null && workPackageFareFilter.bookingClass.search != '' ? 'bookingClass' : null,
    					workPackageFareFilter.ssn.check && workPackageFareFilter.ssn.search != null && workPackageFareFilter.ssn.search != '' ? 'ssn' : null,
    	    	    	workPackageFareFilter.cabin.check && workPackageFareFilter.cabin.search != null && workPackageFareFilter.cabin.search != '' ? 'cabin' : null,
    	    	    	workPackageFareFilter.footnote1.check && workPackageFareFilter.footnote1.search != null && workPackageFareFilter.footnote1.search != '' ? 'footnote1' : null,
    	    	    	workPackageFareFilter.typeOfJourney.check && workPackageFareFilter.typeOfJourney.search != null && workPackageFareFilter.typeOfJourney.search != '' ? 'typeOfJourney' : null,
    	    	    	workPackageFareFilter.rtgno.check && workPackageFareFilter.rtgno.search != null && workPackageFareFilter.rtgno.search != '' ? 'rtgno' : null,
    	    	    	workPackageFareFilter.ruleno.check && workPackageFareFilter.ruleno.search != null && workPackageFareFilter.ruleno.search != '' ? 'ruleno' : null,
    	    	    	workPackageFareFilter.currency.check && workPackageFareFilter.currency.search != null && workPackageFareFilter.currency.search != '' ? 'currency' : null,
    	    	    	workPackageFareFilter.amount.check && workPackageFareFilter.amount.search != null && workPackageFareFilter.amount.search != '' ? 'amount' : null,
    	    	    	workPackageFareFilter.aif.check && workPackageFareFilter.aif.search != null && workPackageFareFilter.aif.search != '' ? 'aif' : null,
    	    	    	workPackageFareFilter.travelStart.check && workPackageFareFilter.travelStart.search != null && workPackageFareFilter.travelStart.search != '' ? 'travelStart' : null,
    	    	    	workPackageFareFilter.travelEnd.check && workPackageFareFilter.travelEnd.search != null && workPackageFareFilter.travelEnd.search != '' ? 'travelEnd' : null,
    	    	    	workPackageFareFilter.saleStart.check && workPackageFareFilter.saleStart.search != null && workPackageFareFilter.saleStart.search != '' ? 'saleStart' : null,
    	    	    	workPackageFareFilter.saleEnd.check && workPackageFareFilter.saleEnd.search != null && workPackageFareFilter.saleEnd.search != '' ? 'saleEnd' : null,
    	    	    	workPackageFareFilter.travelComplete.check && workPackageFareFilter.travelComplete.search != null && workPackageFareFilter.travelComplete.search != '' ? 'travelComplete' : null,
    	    	    	workPackageFareFilter.travelCompleteIndicator.check && workPackageFareFilter.travelCompleteIndicator.search != null && workPackageFareFilter.travelCompleteIndicator.search != '' ? 'travelCompleteIndicator' : null,
    	    	    	workPackageFareFilter.comment.check && workPackageFareFilter.comment.search != null && workPackageFareFilter.comment.search != '' ? 'comment' : null,
    	    	    	workPackageFareFilter.ratesheetComment.check && workPackageFareFilter.ratesheetComment.search != null && workPackageFareFilter.ratesheetComment.search != '' ? 'ratesheetComment' : null,

    	    	    	//addon
    	    	    	workPackageFareFilter.bucket.check && workPackageFareFilter.bucket.search != null && workPackageFareFilter.bucket.search != '' ? 'bucket' : null,

    	    	    	//discount
    	    	    	workPackageFareFilter.loc1Type.check && workPackageFareFilter.loc1Type.search != null && workPackageFareFilter.loc1Type.search != '' ? 'loc1Type' : null,
    	    	    	workPackageFareFilter.loc1.check && workPackageFareFilter.loc1.search != null && workPackageFareFilter.loc1.search != '' ? 'loc1' : null,
    	    	    	workPackageFareFilter.loc2Type.check && workPackageFareFilter.loc2Type.search != null && workPackageFareFilter.loc2Type.search != '' ? 'loc2Type' : null,
    	    			workPackageFareFilter.loc2.check && workPackageFareFilter.loc2.search != null && workPackageFareFilter.loc2.search != '' ? 'loc2' : null,
    	    	    	workPackageFareFilter.baseFareBasis.check && workPackageFareFilter.baseFareBasis.search != null && workPackageFareFilter.baseFareBasis.search != '' ? 'baseFareBasis' : null,
    	    	    	workPackageFareFilter.baseRuleNo.check && workPackageFareFilter.baseRuleNo.search != null && workPackageFareFilter.baseRuleNo.search != '' ? 'baseRuleNo' : null,
    	    	    	workPackageFareFilter.baseTarcd.check && workPackageFareFilter.baseTarcd.search != null && workPackageFareFilter.baseTarcd.search != '' ? 'baseTarcd' : null,
    	    	    	workPackageFareFilter.calcType.check && workPackageFareFilter.calcType.search != null && workPackageFareFilter.calcType.search != '' ? 'calcType' : null,
    	    	    	workPackageFareFilter.percentBaseFare.check && workPackageFareFilter.percentBaseFare.search != null && workPackageFareFilter.percentBaseFare.search != '' ? 'percentBaseFare' : null,
    	    	    	workPackageFareFilter.discountSpecifiedAmount.check && workPackageFareFilter.discountSpecifiedAmount.search != null && workPackageFareFilter.discountSpecifiedAmount.search != '' ? 'discountSpecifiedAmount' : null,
    	    	    	workPackageFareFilter.passengerType.check && workPackageFareFilter.passengerType.search != null && workPackageFareFilter.passengerType.search != '' ? 'passengerType' : null,
    	    	    	workPackageFareFilter.rtgnoTarno.check && workPackageFareFilter.rtgnoTarno.search != null && workPackageFareFilter.rtgnoTarno.search != '' ? 'rtgnoTarno' : null,
    	    	    	workPackageFareFilter.newFareBasis.check && workPackageFareFilter.newFareBasis.search != null && workPackageFareFilter.newFareBasis.search != '' ? 'newFareBasis' : null,
    	    	    	workPackageFareFilter.newTypeOfJourney.check && workPackageFareFilter.newTypeOfJourney.search != null && workPackageFareFilter.newTypeOfJourney.search != '' ? 'newTypeOfJourney' : null,
    	    	    	workPackageFareFilter.newBookingCode.check && workPackageFareFilter.newBookingCode.search != null && workPackageFareFilter.newBookingCode.search != '' ? 'newBookingCode' : null,

    	    	    	//waiver
    	    			workPackageFareFilter.waiverAgentName.check && workPackageFareFilter.waiverAgentName.search != null && workPackageFareFilter.waiverAgentName.search != '' ? 'waiverAgentName' : null,
    	    			workPackageFareFilter.waiverIataNo.check && workPackageFareFilter.waiverIataNo.search != null && workPackageFareFilter.waiverIataNo.search != '' ? 'waiverIataNo' : null,
    	    			workPackageFareFilter.waiverIocNo.check && workPackageFareFilter.waiverIocNo.search != null && workPackageFareFilter.waiverIocNo.search != '' ? 'waiverIocNo' : null,
    	    			workPackageFareFilter.waiverApprovalDate.check && workPackageFareFilter.waiverApprovalDate.search != null && workPackageFareFilter.waiverApprovalDate.search != '' ? 'waiverApprovalDate' : null,
    	    			workPackageFareFilter.waiverType.check && workPackageFareFilter.waiverType.search != null && workPackageFareFilter.waiverType.search != '' ? 'waiverType' : null,
    	    			workPackageFareFilter.waiverFullPartial.check && workPackageFareFilter.waiverFullPartial.search != null && workPackageFareFilter.waiverFullPartial.search != '' ? 'waiverFullPartial' : null,
    	    			workPackageFareFilter.waiverPnr.check && workPackageFareFilter.waiverPnr.search != null && workPackageFareFilter.waiverPnr.search != '' ? 'waiverPnr' : null,
    	    			workPackageFareFilter.waiverTktFrom.check && workPackageFareFilter.waiverTktFrom.search != null && workPackageFareFilter.waiverTktFrom.search != '' ? 'waiverTktFrom' : null,
    	    			workPackageFareFilter.waiverTktTo.check && workPackageFareFilter.waiverTktTo.search != null && workPackageFareFilter.waiverTktTo.search != '' ? 'waiverTktTo' : null,
    					workPackageFareFilter.waiverOri.check && workPackageFareFilter.waiverOri.search != null && workPackageFareFilter.waiverOri.search != '' ? 'waiverOri' : null,
    					workPackageFareFilter.waiverDest.check && workPackageFareFilter.waiverDest.search != null && workPackageFareFilter.waiverDest.search != '' ? 'waiverDest' : null,
    	    			workPackageFareFilter.waiverOriginalItinerary.check && workPackageFareFilter.waiverOriginalItinerary.search != null && workPackageFareFilter.waiverOriginalItinerary.search != '' ? 'waiverOriginalItinerary' : null,
    	    			workPackageFareFilter.waiverNewItinerary.check && workPackageFareFilter.waiverNewItinerary.search != null && workPackageFareFilter.waiverNewItinerary.search != '' ? 'waiverNewItinerary' : null,

    	    			workPackageFareFilter.waiverOriginalBasicFare.check && workPackageFareFilter.waiverOriginalBasicFare.search != null && workPackageFareFilter.waiverOriginalBasicFare.search != '' ? 'waiverOriginalBasicFare' : null,
    	    			workPackageFareFilter.waiverNewBasicFare.check && workPackageFareFilter.waiverNewBasicFare.search != null && workPackageFareFilter.waiverNewBasicFare.search != '' ? 'waiverNewBasicFare' : null,
    	    			workPackageFareFilter.waiverApprovedFare.check && workPackageFareFilter.waiverApprovedFare.search != null && workPackageFareFilter.waiverApprovedFare.search != '' ? 'waiverApprovedFare' : null,
    	    			workPackageFareFilter.waiverFareLost.check && workPackageFareFilter.waiverFareLost.search != null && workPackageFareFilter.waiverFareLost.search != '' ? 'waiverFareLost' : null,
    	    			workPackageFareFilter.waiverCalculatedPn.check && workPackageFareFilter.waiverCalculatedPn.search != null && workPackageFareFilter.waiverCalculatedPn.search != '' ? 'waiverCalculatedPn' : null,
    	    			workPackageFareFilter.waiverOriginalPn.check && workPackageFareFilter.waiverOriginalPn.search != null && workPackageFareFilter.waiverOriginalPn.search != '' ? 'waiverOriginalPn' : null,
    	    	    	workPackageFareFilter.waiverApprovedPn.check && workPackageFareFilter.waiverApprovedPn.search != null && workPackageFareFilter.waiverApprovedPn.search != '' ? 'waiverApprovedPn' : null,
    	    	    	workPackageFareFilter.waiverPenaltyLostPercent.check && workPackageFareFilter.waiverPenaltyLostPercent.search != null && workPackageFareFilter.waiverPenaltyLostPercent.search != '' ? 'waiverPenaltyLostPercent' : null,
    	    	    	workPackageFareFilter.waiverPenaltyLostAmount.check && workPackageFareFilter.waiverPenaltyLostAmount.search != null && workPackageFareFilter.waiverPenaltyLostAmount.search != '' ? 'waiverPenaltyLostAmount' : null,
    	    	    	workPackageFareFilter.waiverCurrency.check && workPackageFareFilter.waiverCurrency.search != null && workPackageFareFilter.waiverCurrency.search != '' ? 'waiverCurrency' : null,
    	    	    	workPackageFareFilter.waiverTotalPax.check && workPackageFareFilter.waiverTotalPax.search != null && workPackageFareFilter.waiverTotalPax.search != '' ? 'waiverTotalPax' : null,
    	    	    	workPackageFareFilter.waiverTotalLost.check && workPackageFareFilter.waiverTotalLost.search != null && workPackageFareFilter.waiverTotalLost.search != '' ? 'waiverTotalLost' : null,
    	    	    	workPackageFareFilter.waiverRemark.check && workPackageFareFilter.waiverRemark.search != null && workPackageFareFilter.waiverRemark.search != '' ? 'waiverRemark' : null,
     	    		];

 	    			var found = false;
 	    			if(type == 'and'){
	 	    			found = true;
	 	    			for(var x=0;x<listField.length;x++){
	 	    				if(listField[x] != null){
	 	    					if(listField[x] == 'travelStart' || listField[x] == 'travelEnd' || listField[x] == 'saleStart' || listField[x] == 'saleEnd' || listField[x] == 'travelComplete'){
	 	    						var date = DateUtils.convertLocalDateToServer(fare[listField[x]]);
	 	    						if(date != getDescendantProp(workPackageFareFilter, listField[x]+'.search')){
		 	    						found = false;
	 	    						}
	 	    					}
	 	    					else{
		 	    					if(getDescendantProp(fare, listField[x]) != getDescendantProp(workPackageFareFilter, listField[x]+'.search')){
			 	    					found = false;
			 	    				}
	 	    					}
	 	    				}
	 	    			}
 	    			}
 	    			else if(type == 'or'){
 	    				for(var x=0;x<listField.length;x++){
	 	    				if(listField[x] != null){
	 	    					if(listField[x] == 'travelStart' || listField[x] == 'travelEnd' || listField[x] == 'saleStart' || listField[x] == 'saleEnd' || listField[x] == 'travelComplete'){
	 	    						var date = DateUtils.convertLocalDateToServer(fare[listField[x]]);
	 	    						if(date == getDescendantProp(workPackageFareFilter, listField[x]+'.search')){
		 	    						found = true;
	 	    						}
	 	    					}
	 	    					else{
		 	    					if(getDescendantProp(fare, listField[x]) == getDescendantProp(workPackageFareFilter, listField[x]+'.search')){
			 	    					found = true;
			 	    				}
	 	    					}
	 	    				}
	 	    			}
 	    			}
 	    			return found;
 	    		}

 	    		function replaceField(workPackageFareFilter, fare){
 	    			//regular
 	    			var listField = [
    	    			workPackageFareFilter.status.replace.check && workPackageFareFilter.status.replace.value != null && workPackageFareFilter.status.replace.value != '' ? 'status' : null,
    					workPackageFareFilter.origin.replace.check && workPackageFareFilter.origin.replace.value != null && workPackageFareFilter.origin.replace.value != '' ? 'origin' : null,
    					workPackageFareFilter.destination.replace.check && workPackageFareFilter.destination.replace.value != null && workPackageFareFilter.destination.replace.value != '' ? 'destination' : null,
    					workPackageFareFilter.fareBasis.replace.check && workPackageFareFilter.fareBasis.replace.value != null && workPackageFareFilter.fareBasis.replace.value != '' ? 'fareBasis' : null,
    					workPackageFareFilter.bookingClass.replace.check && workPackageFareFilter.bookingClass.replace.value != null && workPackageFareFilter.bookingClass.replace.value != '' ? 'bookingClass' : null,
						workPackageFareFilter.ssn.replace.check && workPackageFareFilter.ssn.replace.value != null && workPackageFareFilter.ssn.replace.value != '' ? 'ssn' : null,
    	    			workPackageFareFilter.cabin.replace.check && workPackageFareFilter.cabin.replace.value != null && workPackageFareFilter.cabin.replace.value != '' ? 'cabin' : null,
    	    			workPackageFareFilter.typeOfJourney.replace.check && workPackageFareFilter.typeOfJourney.replace.value != null && workPackageFareFilter.typeOfJourney.replace.value != '' ? 'typeOfJourney' : null,
    	    	    	workPackageFareFilter.footnote1.replace.check && workPackageFareFilter.footnote1.replace.value != null && workPackageFareFilter.footnote1.replace.value != '' ? 'footnote1' : null,
    	    			workPackageFareFilter.rtgno.replace.check && workPackageFareFilter.rtgno.replace.value != null && workPackageFareFilter.rtgno.replace.value != '' ? 'rtgno' : null,
    	    	    	workPackageFareFilter.ruleno.replace.check && workPackageFareFilter.ruleno.replace.value != null && workPackageFareFilter.ruleno.replace.value != '' ? 'ruleno' : null,
    	    	    	workPackageFareFilter.currency.replace.check && workPackageFareFilter.currency.replace.value != null && workPackageFareFilter.currency.replace.value != '' ? 'currency' : null,
    	    	    	workPackageFareFilter.amount.replace.check && workPackageFareFilter.amount.replace.value != null && workPackageFareFilter.amount.replace.value != '' ? 'amount' : null,
    	    	    	workPackageFareFilter.aif.replace.check && workPackageFareFilter.aif.search != null && workPackageFareFilter.aif.search != '' ? 'aif' : null,
    	    	    	workPackageFareFilter.travelStart.check && workPackageFareFilter.travelStart.replace.value != null && workPackageFareFilter.travelStart.replace.value != '' ? 'travelStart' : null,
    	    	    	workPackageFareFilter.travelEnd.replace.check && workPackageFareFilter.travelEnd.replace.value != null && workPackageFareFilter.travelEnd.replace.value != '' ? 'travelEnd' : null,
    	    	    	workPackageFareFilter.saleStart.replace.check && workPackageFareFilter.saleStart.replace.value != null && workPackageFareFilter.saleStart.replace.value != '' ? 'saleStart' : null,
    	    	    	workPackageFareFilter.saleEnd.replace.check && workPackageFareFilter.saleEnd.replace.value != null && workPackageFareFilter.saleEnd.replace.value != '' ? 'saleEnd' : null,
    	    	    	workPackageFareFilter.travelComplete.replace.check && workPackageFareFilter.travelComplete.replace.value != null && workPackageFareFilter.travelComplete.replace.value !=  '' ? 'travelComplete' : null,
    	    	    	workPackageFareFilter.travelCompleteIndicator.replace.check && workPackageFareFilter.travelCompleteIndicator.replace.value != null && workPackageFareFilter.travelCompleteIndicator.replace.value != '' ? 'travelCompleteIndicator' : null,
    	    	    	workPackageFareFilter.comment.replace.check && workPackageFareFilter.comment.replace.value != null && workPackageFareFilter.comment.replace.value != '' ? 'comment' : null,
    	    	    	workPackageFareFilter.ratesheetComment.replace.check && workPackageFareFilter.ratesheetComment.replace.value != null && workPackageFareFilter.ratesheetComment.replace.value != '' ? 'ratesheetComment' : null,

    	    	    	//addon
    	    	    	workPackageFareFilter.bucket.replace.check && workPackageFareFilter.bucket.replace.value != null && workPackageFareFilter.bucket.replace.value != '' ? 'bucket' : null,
    	    	    	workPackageFareFilter.zone.replace.check && workPackageFareFilter.zone.replace.value != null && workPackageFareFilter.zone.replace.value != '' ? 'zone' : null,

    	    	    	//discount
    	    	    	workPackageFareFilter.loc1Type.replace.check && workPackageFareFilter.loc1Type.replace.value != null && workPackageFareFilter.loc1Type.replace.value != '' ? 'loc1Type' : null,
    	    	    	workPackageFareFilter.loc1.replace.check && workPackageFareFilter.loc1.replace.value != null && workPackageFareFilter.loc1.replace.value != '' ? 'loc1' : null,
    	    	    	workPackageFareFilter.loc2Type.replace.check && workPackageFareFilter.loc2Type.replace.value != null && workPackageFareFilter.loc2Type.replace.value != '' ? 'loc2Type' : null,
    	    	    	workPackageFareFilter.loc2.replace.check && workPackageFareFilter.loc2.replace.value != null && workPackageFareFilter.loc2.replace.value != '' ? 'loc2' : null,
    	    	    	workPackageFareFilter.baseFareBasis.replace.check && workPackageFareFilter.baseFareBasis.replace.value != null && workPackageFareFilter.baseFareBasis.replace.value != '' ? 'baseFareBasis' : null,
    	    	    	workPackageFareFilter.baseRuleNo.replace.check && workPackageFareFilter.baseRuleNo.replace.value != null && workPackageFareFilter.baseRuleNo.replace.value != '' ? 'baseRuleNo' : null,
    	    	    	workPackageFareFilter.baseTarcd.replace.check && workPackageFareFilter.baseTarcd.replace.value != null && workPackageFareFilter.baseTarcd.replace.value != '' ? 'baseTarcd' : null,
    	    	    	workPackageFareFilter.calcType.replace.check && workPackageFareFilter.calcType.replace.value != null && workPackageFareFilter.calcType.replace.value != '' ? 'calcType' : null,
    	    	    	workPackageFareFilter.percentBaseFare.replace.check && workPackageFareFilter.percentBaseFare.replace.value != null && workPackageFareFilter.percentBaseFare.replace.value != '' ? 'percentBaseFare' : null,
    	    	    	workPackageFareFilter.discountSpecifiedAmount.replace.check && workPackageFareFilter.discountSpecifiedAmount.replace.value != null && workPackageFareFilter.discountSpecifiedAmount.replace.value != '' ? 'discountSpecifiedAmount' : null,
    	    	    	workPackageFareFilter.passengerType.replace.check && workPackageFareFilter.passengerType.replace.value != null && workPackageFareFilter.passengerType.replace.value != '' ? 'passengerType' : null,
    	    	    	workPackageFareFilter.fareType.replace.check && workPackageFareFilter.fareType.replace.value != null && workPackageFareFilter.fareType.replace.value != '' ? 'fareType' : null,
    	    	    	workPackageFareFilter.ticketCode.replace.check && workPackageFareFilter.ticketCode.replace.value != null && workPackageFareFilter.ticketCode.replace.value != '' ? 'ticketCode' : null,
    	    	    	workPackageFareFilter.ticketDesignator.replace.check && workPackageFareFilter.ticketDesignator.replace.value != null && workPackageFareFilter.ticketDesignator.replace.value != '' ? 'ticketDesignator' : null,
    	    	    	workPackageFareFilter.rtgnoTarno.replace.check && workPackageFareFilter.rtgnoTarno.replace.value != null && workPackageFareFilter.rtgnoTarno.replace.value != '' ? 'rtgnoTarno' : null,
    	    	    	workPackageFareFilter.newFareBasis.replace.check && workPackageFareFilter.newFareBasis.replace.value != null && workPackageFareFilter.newFareBasis.replace.value != '' ? 'newFareBasis' : null,
    	    	    	workPackageFareFilter.newTypeOfJourney.replace.check && workPackageFareFilter.newTypeOfJourney.replace.value != null && workPackageFareFilter.newTypeOfJourney.replace.value != '' ? 'newTypeOfJourney' : null,
    	    	    	workPackageFareFilter.newBookingCode.replace.check && workPackageFareFilter.newBookingCode.replace.value != null && workPackageFareFilter.newBookingCode.replace.value != '' ? 'newBookingCode' : null,

    	    	    	//waiver
    	    			workPackageFareFilter.waiverAgentName.replace.check && workPackageFareFilter.waiverAgentName.replace.value != null && workPackageFareFilter.waiverAgentName.replace.value != '' ? 'waiverAgentName' : null,
    	    			workPackageFareFilter.waiverIataNo.replace.check && workPackageFareFilter.waiverIataNo.replace.value != null && workPackageFareFilter.waiverIataNo.replace.value != '' ? 'waiverIataNo' : null,
    	    			workPackageFareFilter.waiverIocNo.replace.check && workPackageFareFilter.waiverIocNo.replace.value != null && workPackageFareFilter.waiverIocNo.replace.value != '' ? 'waiverIocNo' : null,
    	    			workPackageFareFilter.waiverApprovalDate.replace.check && workPackageFareFilter.waiverApprovalDate.replace.value != null && workPackageFareFilter.waiverApprovalDate.replace.value != '' ? 'waiverApprovalDate' : null,
    	    			workPackageFareFilter.waiverType.replace.check && workPackageFareFilter.waiverType.replace.value != null && workPackageFareFilter.waiverType.replace.value != '' ? 'waiverType' : null,
    	    			workPackageFareFilter.waiverFullPartial.replace.check && workPackageFareFilter.waiverFullPartial.replace.value != null && workPackageFareFilter.waiverFullPartial.replace.value != '' ? 'waiverFullPartial' : null,
    	    			workPackageFareFilter.waiverPnr.replace.check && workPackageFareFilter.waiverPnr.replace.value != null && workPackageFareFilter.waiverPnr.replace.value != '' ? 'waiverPnr' : null,
    	    			workPackageFareFilter.waiverTktFrom.replace.check && workPackageFareFilter.waiverTktFrom.replace.value != null && workPackageFareFilter.waiverTktFrom.replace.value != '' ? 'waiverTktFrom' : null,
    	    			workPackageFareFilter.waiverTktTo.replace.check && workPackageFareFilter.waiverTktTo.replace.value != null && workPackageFareFilter.waiverTktTo.replace.value != '' ? 'waiverTktTo' : null,

    	    			workPackageFareFilter.waiverOri.replace.check && workPackageFareFilter.waiverOri.replace.value != null && workPackageFareFilter.waiverOri.replace.value != '' ? 'waiverOri' : null,
    	    			workPackageFareFilter.waiverDest.replace.check && workPackageFareFilter.waiverDest.replace.value != null && workPackageFareFilter.waiverDest.replace.value != '' ? 'waiverDest' : null,
    	    			workPackageFareFilter.waiverOriginalItinerary.replace.check && workPackageFareFilter.waiverOriginalItinerary.replace.value != null && workPackageFareFilter.waiverOriginalItinerary.replace.value != '' ? 'waiverOriginalItinerary' : null,

    					workPackageFareFilter.waiverNewItinerary.replace.check && workPackageFareFilter.waiverNewItinerary.replace.value != null && workPackageFareFilter.waiverNewItinerary.search != '' ? 'waiverNewItinerary' : null,
    					workPackageFareFilter.waiverOriginalBasicFare.replace.check && workPackageFareFilter.waiverOriginalBasicFare.replace.value != null && workPackageFareFilter.waiverOriginalBasicFare.search != '' ? 'waiverOriginalBasicFare' : null,

    	    			workPackageFareFilter.waiverNewBasicFare.replace.check && workPackageFareFilter.waiverNewBasicFare.replace.value != null && workPackageFareFilter.waiverNewBasicFare.replace.value != '' ? 'waiverNewBasicFare' : null,
    	    			workPackageFareFilter.waiverApprovedFare.replace.check && workPackageFareFilter.waiverApprovedFare.replace.value != null && workPackageFareFilter.waiverApprovedFare.replace.value != '' ? 'waiverApprovedFare' : null,
    	    			workPackageFareFilter.waiverFareLost.replace.check && workPackageFareFilter.waiverFareLost.replace.value != null && workPackageFareFilter.waiverFareLost.replace.value != '' ? 'waiverFareLost' : null,
    	    			workPackageFareFilter.waiverCalculatedPn.replace.check && workPackageFareFilter.waiverCalculatedPn.replace.value != null && workPackageFareFilter.waiverCalculatedPn.replace.value != '' ? 'waiverCalculatedPn' : null,
    	    			workPackageFareFilter.waiverOriginalPn.replace.check && workPackageFareFilter.waiverOriginalPn.replace.value != null && workPackageFareFilter.waiverOriginalPn.replace.value != '' ? 'waiverOriginalPn' : null,
    	    	    	workPackageFareFilter.waiverApprovedPn.replace.check && workPackageFareFilter.waiverApprovedPn.replace.value != null && workPackageFareFilter.waiverApprovedPn.replace.value != '' ? 'waiverApprovedPn' : null,
    	    	    	workPackageFareFilter.waiverPenaltyLostPercent.replace.check && workPackageFareFilter.waiverPenaltyLostPercent.replace.value != null && workPackageFareFilter.waiverPenaltyLostPercent.replace.value != '' ? 'waiverPenaltyLostPercent' : null,
    	    	    	workPackageFareFilter.waiverPenaltyLostAmount.replace.check && workPackageFareFilter.waiverPenaltyLostAmount.replace.value != null && workPackageFareFilter.waiverPenaltyLostAmount.replace.value != '' ? 'waiverPenaltyLostAmount' : null,
    	    	    	workPackageFareFilter.waiverCurrency.replace.check && workPackageFareFilter.waiverCurrency.replace.value != null && workPackageFareFilter.waiverCurrency.replace.value != '' ? 'waiverCurrency' : null,
    	    	    	workPackageFareFilter.waiverTotalPax.replace.check && workPackageFareFilter.waiverTotalPax.replace.value != null && workPackageFareFilter.waiverTotalPax.replace.value != '' ? 'waiverTotalPax' : null,
    	    	    	workPackageFareFilter.waiverTotalLost.replace.check && workPackageFareFilter.waiverTotalLost.replace.value != null && workPackageFareFilter.waiverTotalLost.replace.value != '' ? 'waiverTotalLost' : null,
    	    	    	workPackageFareFilter.waiverRemark.replace.check && workPackageFareFilter.waiverRemark.replace.value != null && workPackageFareFilter.waiverRemark.replace.value != '' ? 'waiverRemark' : null,

    	    		];

 	    			for(var x=0;x<listField.length;x++){
 	    				if(listField[x] != null){
 	    					fare[listField[x]] = getDescendantProp(workPackageFareFilter, listField[x]+'.replace.value');
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
	    			fareSheet.fares[i].field = {};
	    		}

	    		var find = false;

	    		for(var i = index; i < fareSheet.fares.length; i++){

	    			if(fareSheet.fares[i].field == null || fareSheet.fares[i].field == undefined){
    					fareSheet.fares[i].field = {};
    		    	}

	    			//Unselect
	    			//fareSheet.fares[i].field['no'] =  false;

	    			if(checkField(workPackageFareFilter, workPackageFareFilter.andor, fareSheet.fares[i])){
    					find = true;

    					//select all field
    					var tableEl = document.querySelector( '#'+table );

    					//regular fare column size
    					var fromColumn = 0;
    					var toColumn = 50;
	    				for(var f=fromColumn; f<=toColumn;f++){
	    					  var column = angular.element(tableEl.rows[i+1].cells[f]).attr('id');
	    					  fareSheet.fares[i].field[column] = true;
	    				}

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
	    		}

    	    	vm.searchReplace(fareType, table, fareSheet, workPackageFareFilter);
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
       /* vm.moveUpFare = function(workPackageSheet, idx){
        	if(idx != 0){
        		swap(workPackageSheet.fares, idx, idx-1);
        	}
        }
        vm.moveDownFare = function(workPackageSheet, idx){
        	if(idx != workPackageSheet.fares.length-1){
        		swap(workPackageSheet.fares, idx, idx+1);
        	}
        }*/

        vm.moveUpFare = function(workPackageSheet){
        	 var fares = [];
             var selected = [];
             var exist = false;
         	  for(var x=0;x<workPackageSheet.fares.length;x++){
         		  if(workPackageSheet.fares[x].field != undefined){
         			  Object.keys(workPackageSheet.fares[x].field).forEach(function(key) {
         				  if(workPackageSheet.fares[x].field[key]){
         					  exist = true;
         					  selected.push(workPackageSheet.fares[x]);
         				  }
         			 });
         		  }
         	  }
         	if(exist){
         		var readySwap = true;
         		for(var l=selected.length; l>0; l--){
     				var index1 =  workPackageSheet.fares.indexOf(selected[l-1]);
     				var index2 =  workPackageSheet.fares.indexOf(selected[l-1])-1;
     				if(index1 == 0){
     					readySwap = false;
     				}
     			 }

         		if(readySwap){
         		 for(var l=0; l<selected.length; l++){
    				var index1 =  workPackageSheet.fares.indexOf(selected[l]);
    				var index2 =  workPackageSheet.fares.indexOf(selected[l])-1;
    				swap(workPackageSheet.fares, index1, index2);
    			 }
         		 for(var x=0;x<workPackageSheet.fares.length;x++){
         			workPackageSheet.fares[x].no = x+1;
         		 }
         		}
   		  }
        }
        vm.moveDownFare = function(workPackageSheet){
    	  var fares = [];
          var selected = [];
          var exist = false;
      	  for(var x=0;x<workPackageSheet.fares.length;x++){
      		  if(workPackageSheet.fares[x].field != undefined){
      			  Object.keys(workPackageSheet.fares[x].field).forEach(function(key) {
      				  if(workPackageSheet.fares[x].field[key]){
      					  exist = true;
      					  selected.push(workPackageSheet.fares[x]);
      				  }
      			 });
      		  }
      	  }
      	if(exist){
      		var readySwap = true;
      		for(var l=selected.length; l>0; l--){
  				var index1 =  workPackageSheet.fares.indexOf(selected[l-1]);
  				var index2 =  workPackageSheet.fares.indexOf(selected[l-1])+1;
  				if(index2 == workPackageSheet.fares.length){
  					readySwap = false;
  				}
  			 }

      		if(readySwap){
      		 for(var l=selected.length; l>0; l--){
 				var index1 =  workPackageSheet.fares.indexOf(selected[l-1]);
 				var index2 =  workPackageSheet.fares.indexOf(selected[l-1])+1;
 				swap(workPackageSheet.fares, index1, index2);
 			 }
      		 for(var x=0;x<workPackageSheet.fares.length;x++){
      			workPackageSheet.fares[x].no = x+1;
      		 }
      		}
		  }
        }

        vm.clearSelection = function(workPackageSheet){

        	for(var x=0;x<workPackageSheet.fares.length;x++){
//        		console.log(workPackageSheet.fares[x].field);
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
// 	    		console.log("WORKPACKAGE DISCOUNT FARES NULL");
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
//        	console.log(err);
        });

        vm.datePickerOpenStatus.createdDate = false;
        vm.datePickerOpenStatus.filingDate = false;
        vm.datePickerOpenStatus.distributionDate = false;
        vm.datePickerOpenStatus.discExpiryDate = false;

        function openCalendar (date) {
//        	console.log(date);
        	vm.datePickerOpenStatus = {};
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
		  if(vm.workPackage.reviewLevel != "DISTRIBUTION"){
			  if(vm.workPackage.status != "NEW"){
				   attachment.inOnly = false;
				   attachment.isDeleted = true;
			   }else{
				 var index = vm.workPackage.attachmentData.indexOf(attachment);
			  	 vm.workPackage.attachmentData.splice(index, 1);
			   }
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
		    	vm.workPackage.validate = true;

		    	var wp = angular.copy(vm.workPackage);
		    	removeTime(wp);

		    	WorkPackage.update(wp, function onSaveSuccess(result){
		    		if(vm.mapWorkpackage(result)){

		    			var wp2 = angular.copy(result);
		    			removeTime(wp2);

		    			WorkPackage.passup(wp2, function(wp){
			    			alert('Pass Up Success');
			    			$state.go('work-package');
			    		}, function(){
			    			alert('Pass Up Failed');
			    		});
		    		}
		    	}, function onSaveError(){
		    		alert('An error occured, please try again');
		    	});
		    } else {
		    }
	  };

	  vm.passDown = function(){
		    if (confirm("Are you sure to Pass down this workorder?")) {
		    	var wp = angular.copy(vm.workPackage);
		    	removeTime(wp);

		    	WorkPackage.passdown(wp, function(){
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
		    		vm.workPackage.validate = true;

		    		var wp = angular.copy(vm.workPackage);
			    	removeTime(wp);

			    	WorkPackage.update(wp, function onSaveSuccess(result){
			    		if(vm.mapWorkpackage(result)){

			    			var wp2 = angular.copy(result);
			    			removeTime(wp2);

			    			WorkPackage.passsideway(wp2, function(){
				    			alert('Pass Sideway Success');
				    			$state.go('work-package');
				    		}, function(){
				    			alert('Pass Sideway Failed');
				    		});
			    		}
			    		else{

			    		}
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
//    			console.log("fail");
    		});
          }, function() {

          });
	  };

	  vm.approve = function(){
		  vm.workPackage.validate = true;

		  var wp = angular.copy(vm.workPackage);
		  if(wp.type == 'WAIVER'){
			  for(var x=0;x<wp.waiverFareSheet.length;x++){
				  for(var y=0;y<wp.waiverFareSheet[x].fares.length;y++){
					  console.log( wp.waiverFareSheet[x].fares[y]);
					  wp.waiverFareSheet[x].fares[y].waiverApprovalDate = new Date();
				  }
			  }
		  }
	      removeTime(wp);

		  WorkPackage.update(wp, function onSaveSuccess(result){
			  if(vm.mapWorkpackage(result)){
				  var wp2 = angular.copy(result);
			      removeTime(wp2);

				  var validated = true;
				  var cekStatus = "";
				  var counterApprove = false;
				  var approveRuleNo = [];
				  var marketRulesNo = [];

				  if(vm.workPackage.interofficeComment == null || vm.workPackage.interofficeComment.length == 0){
					  cekStatus = "Interoffice comment could not be blank";
					  validated = false;
				  }

				  if(vm.workPackage.fareSheet != null && vm.workPackage.fareSheet.length > 0){
					  for(var x=0;x<vm.workPackage.fareSheet.length;x++){
						  if(vm.workPackage.fareSheet[x].fares != null && vm.workPackage.fareSheet[x].fares.length > 0){
							  //vm.expandCityGroup(vm.workPackage.fareSheet[x]);
							  for(var y=0;y<vm.workPackage.fareSheet[x].fares.length;y++){
								  if(vm.workPackage.fareSheet[x].fares[y].status == "APPROVED"){
									  counterApprove = true;
									  break;
								  }
							  }
							  for(var y=0;y<vm.workPackage.fareSheet[x].fares.length;y++){
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
									  break;
								  }
							  }
							  for(var y=0;y<vm.workPackage.discountFareSheet[x].fares.length;y++){
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
									  break;
								  }
							  }
							  for(var y=0;y<vm.workPackage.addonFareSheet[x].fares.length;y++){
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
									  approveRuleNo.push(vm.workPackage.marketFareSheet[x].fares[y].ruleno);
									  counterApprove =true;
								  }
							  }
							  for(var y=0;y<vm.workPackage.marketFareSheet[x].fares.length;y++){
								  if(vm.workPackage.marketFareSheet[x].fares[y].status == "" || vm.workPackage.marketFareSheet[x].fares[y].status == "PENDING" || !counterApprove){
									  cekStatus = "Can not approve because status fare is : "+vm.workPackage.marketFareSheet[x].fares[y].status;
									  validated = false;
									  break;
								  }
							  }
						  }
					  }
					  if(counterApprove){
						  if(vm.workPackage.marketRulesData != null && vm.workPackage.marketRulesData.length > 0 ){
							  for(var h=0; h<vm.workPackage.marketRulesData.length; h++){
								  marketRulesNo.push(vm.workPackage.marketRulesData[h].ruleid);
							  }
							  for(var l=0;l<approveRuleNo.length;l++){
								  if(marketRulesNo.indexOf(approveRuleNo[l]) < 0){
									  cekStatus = "Rule ID "+approveRuleNo[l]+ " does not exist in market rule";
									  validated = false;
								  }
							  }
						  }else{
							  cekStatus = "Market rules data could not be blank";
							  validated = false;
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

			        	  var wp2 = angular.copy(vm.workPackage);
					      removeTime(wp2);

			        	  WorkPackage.update(wp2, function onSaveSuccess(result){

			        		  var wp3 = angular.copy(result);
						      removeTime(wp3);

				        	  WorkPackage.approve(wp3, function(){
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
			  }
	    	}, function onSaveError(){
	    		alert('An error occured, please try again');
	    	});
	  };

	  vm.referback = function(){
		    if (confirm("Are you sure to Referback this workorder?")) {
		    	 	var wp = angular.copy(vm.workPackage);
		    	 	removeTime(wp);

		    		WorkPackage.referback(wp, function(){
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
		    	var wp = angular.copy(vm.workPackage);
	    	 	removeTime(wp);

		    		WorkPackage.complete(wp, function(){
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
			  var wp = angular.copy(vm.workPackage);
	    	 	removeTime(wp);

	    		WorkPackage.createbatch(wp, function(wo){
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
			  var wp = angular.copy(vm.workPackage);
	    	 	removeTime(wp);

	    		WorkPackage.revisebatch(wp, function(){
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
			  var wp = angular.copy(vm.workPackage);
	    	 	removeTime(wp);

	    		WorkPackage.completebatch(wp, function(){
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
        	  var newWp = angular.copy(vm.workPackage);
        	  removeTime(newWp);
              WorkPackage.update(newWp, onSaveSuccess, onSaveError);
          } else {
              WorkPackage.save(vm.workPackage, onSaveSuccess, onSaveError);
          }
      }

	  function removeTime(workPackage){
		  data = workPackage;

		  data.saleDate = DateUtils.convertLocalDateToServer(data.saleDate);

		  if(data.fareSheet.length > 0){
          	for(var x=0;x<data.fareSheet.length;x++){
          		var fares = data.fareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertLocalDateToServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertLocalDateToServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertLocalDateToServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertLocalDateToServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertLocalDateToServer(fares[y].travelComplete);
              		}
          		}
          	}
          }

          if(data.addonFareSheet.length > 0){
          	for(var x=0;x<data.addonFareSheet.length;x++){
          		var fares = data.addonFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertLocalDateToServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertLocalDateToServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertLocalDateToServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertLocalDateToServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertLocalDateToServer(fares[y].travelComplete);
              		}
          		}
          	}
          }

          if(data.marketFareSheet.length > 0){
          	for(var x=0;x<data.marketFareSheet.length;x++){
          		var fares = data.marketFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertLocalDateToServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertLocalDateToServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertLocalDateToServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertLocalDateToServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertLocalDateToServer(fares[y].travelComplete);
              		}
          		}
          	}
          }


          if(data.discountFareSheet.length > 0){
          	for(var x=0;x<data.discountFareSheet.length;x++){
          		var fares = data.discountFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertLocalDateToServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertLocalDateToServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertLocalDateToServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertLocalDateToServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertLocalDateToServer(fares[y].travelComplete);
              		}
          		}
          	}
          }
          
          if(data.waiverFareSheet.length > 0){
            	for(var x=0;x<data.waiverFareSheet.length;x++){
            		var fares = data.waiverFareSheet[x].fares;
            		for(var y=0;y<fares.length;y++){
                		if(fares[y] != null){
                			fares[y].waiverApprovalDate = DateUtils.convertLocalDateToServer(fares[y].waiverApprovalDate);
                		}
            		}
            	}
            }
	  }

      function onSaveSuccess (result) {
    	  alert("Save Success");
	      $scope.$emit('fmpApp:workPackageUpdate', result);

	      var data = result;
	      vm.mapWorkpackage(data);
          vm.isSaving = false;
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
          data.saleDate = DateUtils.convertDateFromServer(data.saleDate);

          if(data.fareSheet.length > 0){
          	for(var x=0;x<data.fareSheet.length;x++){
          		var fares = data.fareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }

          if(data.addonFareSheet.length > 0){
          	for(var x=0;x<data.addonFareSheet.length;x++){
          		var fares = data.addonFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }

          if(data.marketFareSheet.length > 0){
          	for(var x=0;x<data.marketFareSheet.length;x++){
          		var fares = data.marketFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }


          if(data.discountFareSheet.length > 0){
          	for(var x=0;x<data.discountFareSheet.length;x++){
          		var fares = data.discountFareSheet[x].fares;
          		for(var y=0;y<fares.length;y++){
              		if(fares[y] != null){
              			fares[y].travelStart = DateUtils.convertDateFromServer(fares[y].travelStart);
              			fares[y].travelEnd = DateUtils.convertDateFromServer(fares[y].travelEnd);
              			fares[y].saleStart = DateUtils.convertDateFromServer(fares[y].saleStart);
              			fares[y].saleEnd = DateUtils.convertDateFromServer(fares[y].saleEnd);
              			fares[y].travelComplete = DateUtils.convertDateFromServer(fares[y].travelComplete);
              		}
          		}
          	}
          }

          if(data.filingDetail != null && data.filingDetail.createdDate != null){
	      		data.filingDetail.createdDate = DateUtils.convertDateTimeFromServer(data.filingDetail.createdDate);
	      }

          if(data.filingDetail != null &&  data.filingDetail.releaseDate != null){
	     		data.filingDetail.releaseDate = DateUtils.convertDateTimeFromServer(data.filingDetail.releaseDate);
	      }

          vm.workPackage = data;

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

            if(vm.workPackage.validation != null && ((vm.workPackage.validation.errorsCount > 0) || (vm.workPackage.validation.warningsCount > 0))){
				alert('There is '+vm.workPackage.validation.errorsCount+' error(s) and '+vm.workPackage.validation.warningsCount+' warning(s)');
				return false;
		  }

            return true;
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
//	    		  console.log(error);
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
//	   		console.log(filing.isDeleted);
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

      vm.agent = function(disabled, viewOnly){
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
	              entity: object,
		          isDisabled : disabled,
		          isViewOnly : viewOnly
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
//    	    	  	console.log(result);
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
    	  if(fare.waiverApprovedFare != null || fare.waiverNewBasicFare != null){
    		  fare.waiverFareLost = parseInt(fare.waiverApprovedFare) - parseInt(fare.waiverNewBasicFare);
    		  if(fare.waiverTotalPax !=null){
        		  fare.waiverTotalLost = (parseInt(fare.waiverFareLost)+parseInt(fare.waiverPenaltyLostAmount))*parseInt(fare.waiverTotalPax);
        		  if(fare.waiverCalculatedPn=="amount"){
        			  if(fare.waiverTotalPax !=null && fare.waiverFareLost != null){
                		  fare.waiverTotalLost = (parseInt(fare.waiverFareLost)+parseInt(fare.waiverPenaltyLostAmount))*parseInt(fare.waiverTotalPax);
                	  }
        		  }else if(fare.waiverCalculatedPn=="percent"){
        			  if(fare.waiverTotalPax !=null){
                		  fare.waiverTotalLost = (parseInt(fare.waiverFareLost))*parseInt(fare.waiverTotalPax);
                	  }
        		  }
        	  }
    	  }
      }

      vm.calculatePenaltyLost = function(fare){
    	  if(fare.waiverApprovedPn != null || fare.waiverApprovedPn != undefined || fare.waiverOriginalPn != null || fare.waiverOriginalPn != undefined){
    		  if(fare.waiverCalculatedPn=="amount"){
    			  fare.waiverPenaltyLostPercent = null;
        		  fare.waiverPenaltyLostAmount = parseInt(fare.waiverApprovedPn) - parseInt(fare.waiverOriginalPn);
        		  if(fare.waiverTotalPax !=null && fare.waiverFareLost != null){
            		  fare.waiverTotalLost = (parseInt(fare.waiverFareLost)+parseInt(fare.waiverPenaltyLostAmount))*parseInt(fare.waiverTotalPax);
            	  }
    		  }else if(fare.waiverCalculatedPn=="percent"){
    			  fare.waiverPenaltyLostPercent = parseInt(fare.waiverApprovedPn) - parseInt(fare.waiverOriginalPn);
        		  fare.waiverPenaltyLostAmount = null;
        		  if(fare.waiverTotalPax !=null){
            		  fare.waiverTotalLost = (parseInt(fare.waiverFareLost))*parseInt(fare.waiverTotalPax);
            	  }
    		  }
    	  }
      }

    /*  vm.calculateTotalLost = function(fare){
    	  if(fare.waiverTotalPax !=null && fare.waiverFareLost != null && fare.waiverFareLost != null){
    		  fare.waiverTotalLost = (parseInt(fare.waiverFareLost)+parseInt(fare.waiverPenaltyLostAmount))*parseInt(fare.waiverTotalPax);
    	  }
      }*/

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
//    		  console.log(err);
    	  }
      };
      vm.close = function(){
    	  if (vm.isViewOnly) {
    		  $state.go("work-package-query");
    	  }else{
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
	    						  if(fares[0].fare.tariffNumber == null){
	    							  try {
	    								  workPackageSheet.fares[x].tarcd = fares[0].fare.tarcd;
									} catch (e) {
									}
	    						  }
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
    				  copiedFare.action = "A";
    				  copiedFare.id = null;
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
    				  copiedFare.id = null;
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

      vm.pasteFares = function(workPackageSheet, cancel){
    	  var clipboard = Clipboard.findByCurrentUsername({id : $stateParams.id}).$promise;

    	  clipboard.then(function(result){
			  if(result.page == 'workorder-fares'){
	    		  for(var x=0;x<result.content.length;x++){
	    			  result.content[x].no = workPackageSheet.fares.length+1;

	    			  result.content[x].status = "PENDING";
	    			  if(cancel){
	    				  result.content[x].action = "X";
	    			  }
	    			  else{
	    				  result.content[x].action = "A";
	    			  }
	    			  result.content[x].id = null;
	    			  result.content[x].travelStart = DateUtils.convertDateTimeFromServer(result.content[x].travelStart);
	    			  result.content[x].travelEnd = DateUtils.convertDateTimeFromServer(result.content[x].travelEnd);
	    			  result.content[x].saleStart = DateUtils.convertDateTimeFromServer(result.content[x].saleStart);
	    			  result.content[x].saleEnd = DateUtils.convertDateTimeFromServer(result.content[x].saleEnd);
	    			  result.content[x].travelComplete = DateUtils.convertDateTimeFromServer(result.content[x].travelComplete);
	        		  workPackageSheet.fares.push(result.content[x]);
	    		  }
			  }
			  else if(result.page == 'workorder-addon-fares'){
	    		  for(var x=0;x<result.content.length;x++){
	    			  result.content[x].no = workPackageSheet.fares.length+1;

	    			  result.content[x].status = "PENDING";
	    			  if(cancel){
	    				  result.content[x].action = "X";
	    			  }
	    			  else{
	    				  result.content[x].action = "A";
	    			  }
	    			  result.content[x].id = null;
	    			  result.content[x].travelStart = DateUtils.convertDateTimeFromServer(result.content[x].travelStart);
	    			  result.content[x].travelEnd = DateUtils.convertDateTimeFromServer(result.content[x].travelEnd);
	    			  result.content[x].saleStart = DateUtils.convertDateTimeFromServer(result.content[x].saleStart);
	    			  result.content[x].saleEnd = DateUtils.convertDateTimeFromServer(result.content[x].saleEnd);
	    			  result.content[x].travelComplete = DateUtils.convertDateTimeFromServer(result.content[x].travelComplete);
	        		  workPackageSheet.fares.push(result.content[x]);
	    		  }
			  }
			  else if(result.page == 'workorder-discount-fares'){
	    		  for(var x=0;x<result.content.length;x++){
	    			  result.content[x].no = workPackageSheet.fares.length+1;

	    			  result.content[x].status = "PENDING";
	    			  result.content[x].id = null;
	    			  result.content[x].travelStart = DateUtils.convertDateTimeFromServer(result.content[x].travelStart);
	    			  result.content[x].travelEnd = DateUtils.convertDateTimeFromServer(result.content[x].travelEnd);
	    			  result.content[x].saleStart = DateUtils.convertDateTimeFromServer(result.content[x].saleStart);
	    			  result.content[x].saleEnd = DateUtils.convertDateTimeFromServer(result.content[x].saleEnd);
	    			  result.content[x].travelComplete = DateUtils.convertDateTimeFromServer(result.content[x].travelComplete);
	        		  workPackageSheet.fares.push(result.content[x]);
	    		  }
			  }
			  else if(result.page == 'workorder-waiver-fares'){
	    		  for(var x=0;x<result.content.length;x++){
	    			  result.content[x].no = workPackageSheet.fares.length+1;

	    			  result.content[x].id = null;
	    			  result.content[x].travelStart = DateUtils.convertDateTimeFromServer(result.content[x].travelStart);
	    			  result.content[x].travelEnd = DateUtils.convertDateTimeFromServer(result.content[x].travelEnd);
	    			  result.content[x].saleStart = DateUtils.convertDateTimeFromServer(result.content[x].saleStart);
	    			  result.content[x].saleEnd = DateUtils.convertDateTimeFromServer(result.content[x].saleEnd);
	    			  result.content[x].travelComplete = DateUtils.convertDateTimeFromServer(result.content[x].travelComplete);
	        		  workPackageSheet.fares.push(result.content[x]);
	    		  }
			  }
			  else if(result.page == 'AFD_QUERY'){
				  for(var x=0;x<result.content.length;x++){
					  var tariffNumber = null;
					  for(var y=0;y<vm.tariffNumber.length;y++){
			    		  if(vm.tariffNumber[y].tarNo == result.content[x].tariffNo){
			    			  tariffNumber = angular.copy(vm.tariffNumber[y]);
			    			  break;
			    		  }
			    	  }

					  if(vm.workPackage.targetDistribution == 'ATPCO' && vm.workPackage.type == 'REGULAR'){
						  var fare = {
							  status:"PENDING",
							  action: cancel ? "X" : "A",
							  carrier:"GA",
							  tariffNumber:tariffNumber,
							  origin:result.content[x].originCity,
							  destination:result.content[x].destinationCity,
							  fareBasis:result.content[x].fareClassCode,
							  bookingClass:result.content[x].bookingClass.length > 0 ? result.content[x].bookingClass[0] : null,
							  cabin:result.content[x].cabin,
							  typeOfJourney:result.content[x].owrt,
							  footnote1:result.content[x].footnote,
							  rtgno:result.content[x].routingNo,
							  ruleno:result.content[x].ruleNo,
							  currency:result.content[x].currencyCode,
							  amount:result.content[x].baseAmount,
							  aif:result.content[x].aif,
							  travelStart:DateUtils.convertDateFromServer(result.content[x].travelStartDate),
							  travelEnd:DateUtils.convertDateFromServer(result.content[x].travelEndDate),
							  saleStart:DateUtils.convertDateFromServer(result.content[x].saleStartDate),
							  saleEnd:DateUtils.convertDateFromServer(result.content[x].saleEndDate),
							  travelComplete:DateUtils.convertDateFromServer(result.content[x].travelComplete)
						  };
					  } else if(vm.workPackage.targetDistribution == 'MARKET' && vm.workPackage.type == 'REGULAR'){
						  var fare = {
								  status:"PENDING",
								  action: cancel ? "X" : "A",
								  carrier:"GA",
								  tariffNumber:tariffNumber,
								  origin:result.content[x].originCity,
								  destination:result.content[x].destinationCity,
								  fareBasis:result.content[x].fareClassCode,
								  bookingClass:result.content[x].bookingClass,
								  cabin:result.content[x].cabin,
								  typeOfJourney:result.content[x].owrt,
								  footnote1:result.content[x].footnote,
								  rtgno:result.content[x].routingNo,
								  ruleno:result.content[x].ruleNo,
								  currency:result.content[x].currencyCode,
								  amount:result.content[x].baseAmount,
								  aif:result.content[x].aif,
								  travelStart:DateUtils.convertDateFromServer(result.content[x].travelStartDate),
								  travelEnd:DateUtils.convertDateFromServer(result.content[x].travelEndDate),
								  saleStart:DateUtils.convertDateFromServer(result.content[x].saleStartDate),
								  saleEnd:DateUtils.convertDateFromServer(result.content[x].saleEndDate),
								  travelComplete:DateUtils.convertDateFromServer(result.content[x].travelComplete),
								  prevAmount:result.content[x].baseAmount,
								  prevAmountDiff:0,
								  prevPercentAmountDiff:0
							  };
					  } else if(vm.workPackage.targetDistribution == 'ATPCO' && vm.workPackage.type == 'DISCOUNT'){
						  var fare = {
								  status:"PENDING",
								 // action: cancel ? "X" : "A",
								  carrier:"GA",
								  tariffNumber:tariffNumber,
								  loc1Type:'C',
								  loc1:result.content[x].originCity,
								  loc2Type:'C',
								  calcType:'S',
								  discountSpecifiedAmount:result.content[x].baseAmount,
								  loc2:result.content[x].destinationCity,
								  fareBasis:result.content[x].fareClassCode,
								  bookingClass:result.content[x].bookingClass,
								  cabin:result.content[x].cabin,
								  typeOfJourney:result.content[x].owrt,
								  footnote1:result.content[x].footnote,
								  rtgno:result.content[x].routingNo,
								  ruleno:result.content[x].ruleNo,
								  currency:result.content[x].currencyCode,
								  amount:result.content[x].baseAmount,
								  aif:result.content[x].aif,
								  passengerType:result.content[x].paxType.length > 0 ? result.content[x].paxType[0] : null,
								  travelStart:DateUtils.convertDateFromServer(result.content[x].travelStartDate),
								  travelEnd:DateUtils.convertDateFromServer(result.content[x].travelEndDate),
								  saleStart:DateUtils.convertDateFromServer(result.content[x].saleStartDate),
								  saleEnd:DateUtils.convertDateFromServer(result.content[x].saleEndDate),
								  travelComplete:DateUtils.convertDateFromServer(result.content[x].travelComplete)
							  };
					  }
					  workPackageSheet.fares.push(fare);
				  }
			  }
    		  else{
    			  alert('Nothing to paste');
    		  }
    	  });
      }

      vm.marketPrevBaseAmount = function(fare){
    	  if(fare.prevAmount != null){
			  fare.prevAmountDiff = fare.amount - fare.prevAmount;
			  fare.prevPercentAmountDiff = parseFloat((fare.prevAmountDiff/fare.prevAmount)*100).toFixed(2);
    	  }
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

      function getNearestTableAncestor(htmlElementNode) {
    	    while (htmlElementNode) {
    	        htmlElementNode = htmlElementNode.parentNode;
    	        if (htmlElementNode.tagName.toLowerCase() === 'table') {
    	            return htmlElementNode;
    	        }
    	    }
    	    return undefined;
      }

      vm.tdClick = function(workPackageSheet, fare, f, event){
    	  var elmtCell = event.target;
    	  while(elmtCell.cellIndex == undefined){
    		  elmtCell = elmtCell.parentNode;
    	  }
    	  var cellIndex = elmtCell.cellIndex;

    	  var elmtRow = event.target;
    	  while(elmtRow.rowIndex == undefined){
    		  elmtRow = elmtRow.parentNode;
    	  }
    	  var rowIndex = elmtRow.rowIndex;
    	  var table = getNearestTableAncestor(event.target);
//    	  $timeout(function() {
//    		    angular.element(table.rows[1].cells[1]).triggerHandler('click');
//    	  }, 0);
//    	  angular.element(table.rows[1].cells[1]).triggerHandler('click');
//    	  console.log(table.rows[1].cells[1].getAttributeNode("ng-click").value);
    	  //console.log(table.rows[rowIndex].cells[cellIndex].getAttributeNode("ng-click").value);

    	  if (event.ctrlKey || event.metaKey){
    		  if(fare.field == null || fare.field == undefined){
        		  fare.field = {};
        	  }
    		  if(workPackageSheet.column == null || workPackageSheet.column == undefined){
        		  workPackageSheet.column = [];
        	  }

        	  fare.field[f] = !fare.field[f];
        	  workPackageSheet.column.push({row:rowIndex,column:cellIndex});
    	  }
    	  else if(event.shiftKey){
    		  if(workPackageSheet.column == null || workPackageSheet.column == undefined){
    			  if(fare.field == null || fare.field == undefined){
            		  fare.field = {};
            	  }
            	  fare.field[f] = !fare.field[f];
        		  workPackageSheet.column = [];
        		  workPackageSheet.column.push({row:rowIndex,column:cellIndex});
        	  }
    		  else{
	    		  workPackageSheet.column.push({row:rowIndex,column:cellIndex});
	    		  //row index
	    		  var firstIndex = -1;
	    		  var lastIndex = -1;

	    		  //column index
	    		  var firstColumnIndex = -1;
	    		  var lastColumnIndex = -1;

	    		  for(var x=0;x<workPackageSheet.fares.length;x++){
	    			  //console.log(workPackageSheet.fares[x].field);
	    			  if(workPackageSheet.fares[x].field == undefined || Object.keys(workPackageSheet.fares[x].field).length === 0){

	    			  }
	    			  else{
	    				  firstIndex = x;
	    				  break;
	    			  }
	        	  }

	    		  for(var x=0;x<workPackageSheet.fares.length;x++){
	    			  if(fare == workPackageSheet.fares[x]){
	    				  lastIndex = x;
	    				  break;
	    			  }
	        	  }

	    		  if(firstIndex == lastIndex){

	    		  }
	    		  else if(firstIndex > lastIndex){
	    			  var temp = firstIndex;
	    			  firstIndex = lastIndex;
	    			  lastIndex = temp;
	    		  }


	    		  if(firstIndex < lastIndex){
	    			 //highlight multiple row column
	    			  var row = workPackageSheet.column[workPackageSheet.column.length-1].row;
	    			  var lastSelected = workPackageSheet.column[workPackageSheet.column.length-1];
	    			  var lastBeforeSelected = workPackageSheet.column[workPackageSheet.column.length-2];
	    			  var fromColumn = lastBeforeSelected.column;
	    			  var toColumn = lastSelected.column;
	    			  if(fromColumn > toColumn){
	    				  var temp = fromColumn;
	    				  fromColumn = toColumn;
	    				  toColumn = temp;
	    			  }

	    			  for(var z=firstIndex;z<=lastIndex;z++){
	    				  for(var x=0;x<workPackageSheet.fares.length;x++){
		        			  if(workPackageSheet.fares[x] == workPackageSheet.fares[z]){
		        				  for(var f=fromColumn; f<=toColumn;f++){
		        					  var column = angular.element(table.rows[row].cells[f]).attr('id');
		        					  workPackageSheet.fares[x].field[column] = true;
		        				  }
		        				  break;
		        			  }
		            	  }
	    			  }

	    		  }
	    		  else if(firstIndex == lastIndex){
	    			  //highlight 1 row column
	    			  var row = workPackageSheet.column[workPackageSheet.column.length-1].row;
	    			  var lastSelected = workPackageSheet.column[workPackageSheet.column.length-1];
	    			  var lastBeforeSelected = workPackageSheet.column[workPackageSheet.column.length-2];
	    			  var fromColumn = lastBeforeSelected.column;
	    			  var toColumn = lastSelected.column;
	    			  if(fromColumn > toColumn){
	    				  var temp = fromColumn;
	    				  fromColumn = toColumn;
	    				  toColumn = temp;
	    			  }

	    			  for(var x=0;x<workPackageSheet.fares.length;x++){
	        			  if(fare == workPackageSheet.fares[x]){
	        				  for(var f=fromColumn; f<=toColumn;f++){
	        					  var column = angular.element(table.rows[row].cells[f]).attr('id');
	        					  fare.field[column] = true;
	        				  }
	        				  break;
	        			  }
	            	  }
	    		  }
    		  }
    	  }
    	  else{
    		  for(var x=0;x<workPackageSheet.fares.length;x++){
				  workPackageSheet.fares[x].field = {};
        	  }

    		  if(fare.field == null || fare.field == undefined){
        		  fare.field = {};
        	  }
    		  if(workPackageSheet.column == null || workPackageSheet.column == undefined){
    			  workPackageSheet.column = [];
        	  }
        	  fare.field[f] = !fare.field[f];

        	  workPackageSheet.column = [];
        	  workPackageSheet.column.push({row:rowIndex,column:cellIndex});
    	  }

    	  console.log(workPackageSheet.column);
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

      vm.selectAtpcoMasterTariff = function(fare, field, type){
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
                  tariffNumber: ['AtpcoMasterTariff', function(AtpcoMasterTariff) {
                      return AtpcoMasterTariff.findByType({type:type}).$promise;
                  }],
                  AddOn : false,
              }
			}).result.then(function(option) {
				if(option != null){
					if(field=='tarcd'){
						fare[field] = option.tarCd;
					}else if(field=='baseTarcd'){
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

      vm.checkTariffDiscount = function(fare, inputField, type){
    	  var tariff = null;
    	  if(fare[inputField] != undefined && fare[inputField] != null && fare[inputField] != ""){
	    	  var exist = false;

              vm.tariffNumberCheck =  AtpcoMasterTariff.findByType({type:type}).$promise
	    	  if(type == 'FARE'){
	    		  console.log("CHECK TARIFF DISCOUNT FARE");
	    		  for(var x=0;x<vm.tariffNumberCheck.length;x++){
		    		  if(vm.tariffNumberCheck[x].tarCd == fare[inputField].toUpperCase()){
		    			  tariff = angular.copy(vm.tariffNumberCheck[x].tarCd);
		    			  exist = true;
		    			  break;
		    		  }
		    	  }
	    	  }
	    	  else if(type == 'FARE BY RULE'){
	    		  for(var x=0;x<vm.tariffNumberCheck.length;x++){
		    		  if(vm.tariffNumberCheck[x].tarCd == fare[inputField].toUpperCase()){
		    			  tariff = angular.copy(vm.tariffNumberCheck[x].tarCd);
		    			  exist = true;
		    			  break;
		    		  }
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

      vm.diff = [];
      vm.changeVersion = function(workPackageSheet, index){
    	  workPackageSheet.version = index;
    	  if(index == 'current'){
    		  vm.diff = [];
    		  if(workPackageSheet.fares == null){
    			  workPackageSheet.fares = [];
    		  }
    		  workPackageSheet.currentFares = workPackageSheet.fares;
    	  }
    	  else{
    		  if(index != null){
    			  vm.diff = [];
    			  vm.checkDiff(workPackageSheet.fares, workPackageSheet.fareVersion[index].fares);
    			  workPackageSheet.currentFares = workPackageSheet.fareVersion[index].fares;
    		  }
    	  }
      }

      vm.checkDiff = function(current, version){
    	  vm.diff = [];
    	  var idObject =[];
    	  var colorDiff = false;

    	  if(current.length == 0){
    		  for(var y = 0; y< version.length; y++){
    			 vm.diff[version[y].id]=['prevPercentAmountDiff','prevAmountDiff','prevAmount','percentBaseFare','passengerType','overrideIndicator','newTypeOfJourney','newFareBasis','newBookingCode','minStay','maxStay','loc2Type','loc1Type','loc2','loc1','itinerary','fareType','dowIn','discountSpecifiedAmount','direction','dealCode','calcType','cabinClass','status','bucket', 'carrier','tarNo','tarCd','global','origin','destination','fareBasis','bookingClass','cabin',
    					  'typeOfJourney','typeOfJourney','typeOfJourney','typeOfJourney','recommendedAmount','typeOfJourney','footnote1','rtgno','ruleno','currency', 'amount','amtDiff','amtPercentDiff', 'aif', 'action','no',
        				  'travelStart','travelEnd', 'saleStart','saleEnd','comment','travelComplete','travelCompleteIndicator','ratesheetComment'];
    			  }
    	  }
    	  for(var x = 0; x< current.length; x++){
    		  for(var y = 0; y< version.length; y++){
    			  if(current[x].id == version[y].id){
    				  idObject.push(version[y]);
    			  }
    			  if(idObject.length == 0){
    				  vm.diff[version[y].id]=['prevPercentAmountDiff','prevAmountDiff','prevAmount','percentBaseFare','passengerType','overrideIndicator','newTypeOfJourney','newFareBasis','newBookingCode','minStay','maxStay','loc2Type','loc1Type','loc2','loc1','itinerary','fareType','dowIn','discountSpecifiedAmount','direction','dealCode','calcType','cabinClass','status','bucket', 'carrier','tarNo','tarCd','global','origin','destination','fareBasis','bookingClass','cabin',
    					  'typeOfJourney','recommendedAmount','typeOfJourney','footnote1','rtgno','ruleno','currency', 'amount','amtDiff','amtPercentDiff', 'aif', 'action','no','rtgnoTarno',
        				  'travelStart','travelEnd', 'saleStart','saleEnd','comment','travelComplete','travelCompleteIndicator','ratesheetComment','baseRuleNo','baseFareBasis','baseTarcd','ticketCode','ticketDesignator'];
    			  }else{
    				  colorDiff = true;
    			  }
    		  }
    		  if(colorDiff){
        		  for(var y=0; y<idObject.length;y++){
    			    if(current[x].id == idObject[y].id){
    			    	if(vm.diff[idObject[y].id] == null){
    	    				  vm.diff[idObject[y].id] = [];
    	    			    }
    	    				if(current[x].status != idObject[y].status){
    	    	    			vm.diff[idObject[y].id].push('status');
    	      	    		}
    	      	    		if(current[x].ticketCode != idObject[y].ticketCode){
    	    	    			vm.diff[idObject[y].id].push('ticketCode');
    	      	    		}
    	      	    		if(current[x].ticketDesignator != idObject[y].ticketDesignator){
    	    	    			vm.diff[idObject[y].id].push('ticketDesignator');
    	      	    		}
    	    				if(current[x].baseTarcd != idObject[y].baseTarcd){
    	    	    			vm.diff[idObject[y].id].push('baseTarcd');
    	      	    		}
    	    				if(current[x].recommendedAmount != idObject[y].recommendedAmount){
    	    	    			vm.diff[idObject[y].id].push('recommendedAmount');
    	      	    		}
    	    				if(current[x].newBookingCode != idObject[y].newBookingCode){
    	    	    			vm.diff[idObject[y].id].push('newBookingCode');
    	      	    		}
    	    				if(current[x].newFareBasis != idObject[y].newFareBasis){
    	    	    			vm.diff[idObject[y].id].push('newFareBasis');
    	      	    		}
    	    				if(current[x].newTypeOfJourney != idObject[y].newTypeOfJourney){
    	    	    			vm.diff[idObject[y].id].push('newTypeOfJourney');
    	      	    		}
    	    				if(current[x].overrideIndicator != idObject[y].overrideIndicator){
    	    	    			vm.diff[idObject[y].id].push('overrideIndicator');
    	      	    		}
    	    				if(current[x].passengerType != idObject[y].passengerType){
    	    	    			vm.diff[idObject[y].id].push('passengerType');
    	      	    		}
    	    				if(current[x].percentBaseFare != idObject[y].percentBaseFare){
    	    	    			vm.diff[idObject[y].id].push('percentBaseFare');
    	      	    		}
    	    				if(current[x].prevAmount != idObject[y].prevAmount){
    	    	    			vm.diff[idObject[y].id].push('prevAmount');
    	      	    		}
    	    				if(current[x].prevAmountDiff != idObject[y].prevAmountDiff){
    	    	    			vm.diff[idObject[y].id].push('prevAmountDiff');
    	      	    		}
    	    				if(current[x].prevPercentAmountDiff != idObject[y].prevPercentAmountDiff){
    	    	    			vm.diff[idObject[y].id].push('prevPercentAmountDiff');
    	      	    		}
    	    				if(current[x].minStay != idObject[y].minStay){
    	    	    			vm.diff[idObject[y].id].push('minStay');
    	      	    		}
    	    				if(current[x].cabinClass != idObject[y].cabinClass){
    	    	    			vm.diff[idObject[y].id].push('cabinClass');
    	      	    		}
    	    				if(current[x].calcType != idObject[y].calcType){
    	    	    			vm.diff[idObject[y].id].push('calcType');
    	      	    		}
    	    				if(current[x].dealCode != idObject[y].dealCode){
    	    	    			vm.diff[idObject[y].id].push('dealCode');
    	      	    		}
    	    				if(current[x].direction != idObject[y].direction){
    	    	    			vm.diff[idObject[y].id].push('direction');
    	      	    		}
    	    				if(current[x].dowIn != idObject[y].dowIn){
    	    	    			vm.diff[idObject[y].id].push('dowIn');
    	      	    		}
    	    				if(current[x].dowOut != idObject[y].dowOut){
    	    	    			vm.diff[idObject[y].id].push('dowOut');
    	      	    		}
    	    				if(current[x].fareType != idObject[y].fareType){
    	    	    			vm.diff[idObject[y].id].push('fareType');
    	      	    		}
    	    				if(current[x].itinerary != idObject[y].itinerary){
    	    	    			vm.diff[idObject[y].id].push('itinerary');
    	      	    		}
    	    				if(current[x].loc1 != idObject[y].loc1){
    	    	    			vm.diff[idObject[y].id].push('loc1');
    	      	    		}
    	    				if(current[x].loc2 != idObject[y].loc2){
    	    	    			vm.diff[idObject[y].id].push('loc2');
    	      	    		}
    	    				if(current[x].loc1Type != idObject[y].loc1Type){
    	    	    			vm.diff[idObject[y].id].push('loc1Type');
    	      	    		}
    	    				if(current[x].loc2Type != idObject[y].loc2Type){
    	    	    			vm.diff[idObject[y].id].push('loc2Type');
    	      	    		}
    	    				if(current[x].maxStay != idObject[y].maxStay){
    	    	    			vm.diff[idObject[y].id].push('maxStay');
    	      	    		}
    	    				if(current[x].discountSpecifiedAmount != idObject[y].discountSpecifiedAmount){
    	    	    			vm.diff[idObject[y].id].push('discountSpecifiedAmount');
    	      	    		}
    	    				try {
    	    					if(current[x].tariffNumber.tarNo != idObject[y].tariffNumber.tarNo){
        	      	    			vm.diff[idObject[y].id].push('tarNo');
        	      	    		}
    	      	    		}
    	      	    		catch(err) {
    	      	    			if(current[x].tarno != idObject[y].tarno){
        	      	    			vm.diff[idObject[y].id].push('tarCd');
        	      	    		}
    	      	    		}
    	      	    		try {
    	      	    			if(current[x].tariffNumber.tarCd != idObject[y].tariffNumber.tarCd){
        	      	    			vm.diff[idObject[y].id].push('tarCd');
        	      	    		}
    	      	    		}
    	      	    		catch(err) {
    	      	    			if(current[x].tarcd != idObject[y].tarcd){
        	      	    			vm.diff[idObject[y].id].push('tarCd');
        	      	    		}
    	      	    		}
    	      	    		try {
    	      	    			if(current[x].tariffNumber.global != idObject[y].tariffNumber.global){
        	      	    			vm.diff[idObject[y].id].push('global');
        	      	    		}
    	      	    		}
    	      	    		catch(err) {
    	      	    			if(current[x].global != idObject[y].global){
        	      	    			vm.diff[idObject[y].id].push('global');
        	      	    		}
    	      	    		}
    	      	    		if(current[x].rtgnoTarno != idObject[y].rtgnoTarno){
    	      	    			vm.diff[idObject[y].id].push('rtgnoTarno');
    	      	    		}
    	      	    		if(current[x].baseFareBasis != idObject[y].baseFareBasis){
    	      	    			vm.diff[idObject[y].id].push('baseFareBasis');
    	      	    		}
    	      	    		if(current[x].baseRuleNo != idObject[y].baseRuleNo){
    	      	    			vm.diff[idObject[y].id].push('baseRuleNo');
    	      	    		}
    	      	    		if(current[x].origin != idObject[y].origin){
    	      	    			vm.diff[idObject[y].id].push('origin');
    	      	    		}
    	      	    		if(current[x].destination != idObject[y].destination){
    	      	    			vm.diff[idObject[y].id].push('destination');
    	      	    		}
    	      	    		if(current[x].fareBasis != idObject[y].fareBasis){
    	      	    			vm.diff[idObject[y].id].push('fareBasis');
    	      	    		}
    	      	    		if(current[x].bookingClass != idObject[y].bookingClass){
    	      	    			vm.diff[idObject[y].id].push('bookingClass');
    	      	    		}
    	      	    		if(current[x].cabin != idObject[y].cabin){
    	      	    			vm.diff[idObject[y].id].push('cabin');
    	      	    		}
    	      	    		if(current[x].typeOfJourney != idObject[y].typeOfJourney){
    	      	    			vm.diff[idObject[y].id].push('typeOfJourney');
    	      	    		}
    	      	    		if(current[x].footnote1 != idObject[y].footnote1){
    	      	    			vm.diff[idObject[y].id].push('footnote1');
    	      	    		}
    	      	    		if(current[x].rtgno != idObject[y].rtgno){
    	      	    			vm.diff[idObject[y].id].push('rtgno');
    	      	    		}
    	      	    		if(current[x].ruleno != idObject[y].ruleno){
    	      	    			vm.diff[idObject[y].id].push('ruleno');
    	      	    		}
    	      	    		if(current[x].currency != idObject[y].currency){
    	      	    			vm.diff[idObject[y].id].push('currency');
    	      	    		}
    	      	    		if(current[x].amount != idObject[y].amount){
    	      	    			vm.diff[idObject[y].id].push('amount');
    	      	    		}
    	      	    		if(current[x].amtDiff != idObject[y].amtDiff){
    	      	    			vm.diff[idObject[y].id].push('amtDiff');
    	      	    		}
    	      	    		if(current[x].amtPercentDiff != idObject[y].amtPercentDiff){
    	      	    			vm.diff[idObject[y].id].push('amtPercentDiff');
    	      	    		}
    	      	    		if(current[x].aif != idObject[y].aif){
    	      	    			vm.diff[idObject[y].id].push('aif');
    	      	    		}
    	      	    		if(current[x].travelStart != idObject[y].travelStart){
    	      	    			vm.diff[idObject[y].id].push('travelStart');
    	      	    		}
    	      	    		if(current[x].travelEnd != idObject[y].travelEnd){
    	      	    			vm.diff[idObject[y].id].push('travelEnd');
    	      	    		}
    	      	    		if(current[x].saleStart != idObject[y].saleStart){
    	      	    			vm.diff[idObject[y].id].push('saleStart');
    	      	    		}
    	      	    		if(current[x].saleEnd != idObject[y].saleEnd){
    	      	    			vm.diff[idObject[y].id].push('saleEnd');
    	      	    		}
    	      	    		if(current[x].comment != idObject[y].comment){
    	      	    			vm.diff[idObject[y].id].push('comment');
    	      	    		}
    	      	    		if(current[x].travelComplete != idObject[y].travelComplete){
    	      	    			vm.diff[idObject[y].id].push('travelComplete');
    	      	    		}
    	      	    		if(current[x].travelCompleteIndicator != idObject[y].travelCompleteIndicator){
    	      	    			vm.diff[idObject[y].id].push('travelCompleteIndicator');
    	      	    		}
    	      	    		if(current[x].ratesheetComment != idObject[y].ratesheetComment){
    	      	    			vm.diff[idObject[y].id].push('ratesheetComment');
    	      	    		}
    	      	    		if(current[x].bucket != idObject[y].bucket){
    	      	    			vm.diff[idObject[y].id].push('bucket');
    	      	    		}
    			      }
    			  }
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
        				  f.action="A";
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
    				  f.action="A";
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
    				  f.action="A";
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
    	  if(!disabled){
    		  if(vm.user.reviewLevels.indexOf(wp.reviewLevel) > -1){
    			  disabled = false;
    		  }else{
    			  disabled = true;
    		  }
    	  }
    	  return disabled;
      }

      vm.lockedOnly = function(wp){
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

      vm.reviewOnly = function(wp){
    	  var disabled = false;
    	  if(vm.user.reviewLevels.indexOf(wp.reviewLevel) > -1){
			  disabled = false;
		  }else{
			  disabled = true;
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

      vm.updateLatestFare = function(workPackageSheet){
    	  WorkPackage.updateLatestFare(workPackageSheet, function(result){
    		  alert('Fares updated');
    		  workPackageSheet.fares = result.fares;
    		  vm.changeVersion(workPackageSheet, 'current');
    	  }, function(error){});
      }

      vm.updateActionCodes = function(workPackageSheet){
    	  WorkPackage.updateActionCodes(workPackageSheet, function(result){
    		  alert('Action code updated');
    		  workPackageSheet.fares = result.fares;
    		  vm.changeVersion(workPackageSheet, 'current');
    	  }, function(error){});
      }

      vm.dateNgModelOpts = {
    		  timezone : '+07:00'
	  };

      vm.selectErrorField = function(sheetType, sheetIndex, fareIndex, field){
    	  if(sheetType == 'Fares'){
    		  vm.selectTab(sheetIndex);
    		  for(var x=0;x<vm.workPackage.fareSheet[sheetIndex].fares.length;x++){
    			  vm.workPackage.fareSheet[sheetIndex].fares[x].field = {};
        	  }

    		  vm.workPackage.fareSheet[sheetIndex].fares[fareIndex].field[field] = !vm.workPackage.fareSheet[sheetIndex].fares[fareIndex].field[field];

    		  var fieldName = ""+field+sheetIndex+fareIndex;
    		  var elmnt = $window.document.getElementsByName(fieldName)[0];
    		  var offset_top = elmnt.offsetTop;
//    		  var offset_left = elmnt.offsetLeft;
    		  var elmntPage = $window.document.querySelector(".table-wrapper");
    		  elmntPage.scrollTop = offset_top;
//    		  elmntPage.scrollLeft = offset_left;
    		  elmnt.focus();
    	  }
    	  else if(sheetType == 'Addon'){
    		  vm.selectAddonTab(sheetIndex);
    		  for(var x=0;x<vm.workPackage.addonFareSheet[sheetIndex].fares.length;x++){
    			  vm.workPackage.addonFareSheet[sheetIndex].fares[x].field = {};
        	  }

    		  vm.workPackage.addonFareSheet[sheetIndex].fares[fareIndex].field[field] = !vm.workPackage.addonFareSheet[sheetIndex].fares[fareIndex].field[field];

    		  var fieldName = ""+field+sheetIndex+fareIndex;
    		  var elmnt = $window.document.getElementsByName(fieldName)[0];
    		  var offset_top = elmnt.offsetTop;
//    		  var offset_left = elmnt.offsetLeft;
    		  var elmntPage = $window.document.querySelector(".table-wrapper");
    		  elmntPage.scrollTop = offset_top;
//    		  elmntPage.scrollLeft = offset_left;
    		  elmnt.focus();
    	  }
    	  else if(sheetType == 'Market'){
    		  console.log("FIELD NAME : "+field+sheetIndex+fareIndex);
    		  vm.selectMarketTab(sheetIndex);
    		  for(var x=0;x<vm.workPackage.marketFareSheet[sheetIndex].fares.length;x++){
    			  vm.workPackage.marketFareSheet[sheetIndex].fares[x].field = {};
        	  }

    		  vm.workPackage.marketFareSheet[sheetIndex].fares[fareIndex].field[field] = !vm.workPackage.marketFareSheet[sheetIndex].fares[fareIndex].field[field];

    		  var fieldName = ""+field+sheetIndex+fareIndex;
    		  var elmnt = $window.document.getElementsByName(fieldName)[0];
    		  var offset_top = elmnt.offsetTop;
//    		  var offset_left = elmnt.offsetLeft;
    		  var elmntPage = $window.document.querySelector(".table-wrapper");
    		  elmntPage.scrollTop = offset_top;
//    		  elmntPage.scrollLeft = offset_left;
    		  elmnt.focus();
    	  }
    	  else if(sheetType == 'Discount'){
    		  vm.selectDiscountTab(sheetIndex);
    		  for(var x=0;x<vm.workPackage.discountFareSheet[sheetIndex].fares.length;x++){
    			  vm.workPackage.discountFareSheet[sheetIndex].fares[x].field = {};
        	  }

    		  vm.workPackage.discountFareSheet[sheetIndex].fares[fareIndex].field[field] = !vm.workPackage.discountFareSheet[sheetIndex].fares[fareIndex].field[field];

    		  var fieldName = ""+field+sheetIndex+fareIndex;
    		  var elmnt = $window.document.getElementsByName(fieldName)[0];
    		  var offset_top = elmnt.offsetTop;
//    		  var offset_left = elmnt.offsetLeft;
    		  var elmntPage = $window.document.querySelector(".table-wrapper");
    		  elmntPage.scrollTop = offset_top;
//    		  elmntPage.scrollLeft = offset_left;
    		  elmnt.focus();
    	  }
    	  else if(sheetType == 'Waiver'){
    		  vm.selectWaiverTab(sheetIndex);
    		  for(var x=0;x<vm.workPackage.waiverFareSheet[sheetIndex].fares.length;x++){
    			  vm.workPackage.waiverFareSheet[sheetIndex].fares[x].field = {};
        	  }

    		  vm.workPackage.waiverFareSheet[sheetIndex].fares[fareIndex].field[field] = !vm.workPackage.waiverFareSheet[sheetIndex].fares[fareIndex].field[field];

    		  var fieldName = ""+field+sheetIndex+fareIndex;
    		  var elmnt = $window.document.getElementsByName(fieldName)[0];
    		  var offset_top = elmnt.offsetTop;
//    		  var offset_left = elmnt.offsetLeft;
    		  var elmntPage = $window.document.querySelector(".table-wrapper");
    		  elmntPage.scrollTop = offset_top;
//    		  elmntPage.scrollLeft = offset_left;
    		  elmnt.focus();
    	  }
    	  else if(sheetType == 'Header'){
    		  var elmnt = $window.document.getElementsByName(field)[0];
    		  var offset_top = elmnt.offsetTop;
    		  var elmntPage = $window.document.querySelector(".page-wrapper");
    		  elmntPage.scrollTop = offset_top;
    		  elmnt.focus();
    	  }
    	  else if(sheetType == 'Comment'){
    		  if(field == 'interofficeComment'){
    			  vm.selectCommentTab('interofficeComment');
    		  }
    		  var elmnt = $window.document.getElementsByName(field)[0];
    		  var offset_top = elmnt.offsetTop;
    		  var elmntPage = $window.document.querySelector(".page-wrapper");
    		  elmntPage.scrollTop = offset_top;
    		  elmnt.focus();
    	  }
      }


      vm.createBatch = function(){
    	  WorkPackage.createbatch(vm.workPackage, function(result){
    		  if(vm.mapWorkpackage(result)){
	    		  alert('Create batch Success');
	  			  $state.go('work-package');
    		  }
    	  }, function(){
    		  alert('Error occured, please try again')
    	  });
      }

      vm.reviseBatch = function(){
    	  WorkPackage.revisebatch(vm.workPackage, function(result){
    		  vm.mapWorkpackage(result);

    		  alert('Revise batch Success');
  			  $state.go('work-package');
    	  }, function(){
    		  alert('Error occured, please try again')
    	  });
      }

      vm.refreshTariff = function(){
    	  WorkPackage.refreshTariff(vm.workPackage, function(result){
    		  alert('Refresh Tariff Success');
    		  vm.mapWorkpackage(result);
//    		  onSaveSuccess(vm.workPackage);
    	  }, function(){});
      }

      vm.addBatchNumber = function(){
    	  if(vm.selectedTariffRow != null){
    		  if(vm.selectedTariffRow.batch == null) vm.selectedTariffRow.batch = [];

	    	  vm.selectedTariffRow.batch.push({
	    		  batchNo:null,
	    		  gfsRef:null,
	    		  gfsDate:null
	    	  });
  		  }
    	  else{
    		  alert('Please select a tariff');
    	  }
      }

      vm.removeBatchNumber = function(){
    	  if(vm.selectedBatchRow){
	    	  var index = vm.selectedTariffRow.batch.indexOf(vm.selectedBatchRow);
	    	  vm.selectedTariffRow.batch.splice(index, 1);
    	  }
    	  else{
    		  alert('Please select batch row');
    	  }
      }

      vm.checkFilingDetailDisabled = function(){
    	  return vm.workPackage.status == 'READY_TO_RELEASE';
      }

      vm.applyText = function(){
    	  if(vm.selectedTariffRow != null){
    		  if(vm.selectedTariffRow.justificationText != null && vm.selectedTariffRow.justificationText != ""){
	    		  for(var x=0;x<vm.workPackage.filingDetail.filingDetailTarif.length;x++){
					  vm.workPackage.filingDetail.filingDetailTarif[x].justificationText = vm.selectedTariffRow.justificationText;
	    		  }
    		  }
  		  }
    	  else{
    		  alert('Please select a tariff');
    	  }
      }

      vm.previewUploadFile = function(){
    	  alert(vm.workPackage.filingDetail.atpcoFile);
      }

      vm.resetCalculateField = function(fare){
    	  fare.percentBaseFare = null;
    	  fare.currency = null;
    	  fare.discountSpecifiedAmount = null;
    	  fare.typeOfJourney = null;
    	  fare.global=null;
      }

      vm.footnote = function(workPackageSheet){
    	  var selectedSize = 0;
    	  var tariff = null;
    	  for(var x=0;x<workPackageSheet.fares.length;x++){
    		  if(workPackageSheet.fares[x].field != undefined){
    			  var selected = false;
    			  Object.keys(workPackageSheet.fares[x].field).forEach(function(key,index) {
    				  if(workPackageSheet.fares[x].field[key]){
    					  selected = true;
    				  }
    			 });
    			  if(selected){
    				  selectedSize++;
    			  }
    		  }
    	  }
    	  if(selectedSize == 1){
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
        				  if(copiedFare.tariffNumber != null){
        					  tariff = copiedFare.tariffNumber.tarNo;
        				  }
        				  else{

        				  }
        			  }
        		  }
        	  }
    		  var url = $state.href('footnote-query', {cxr: "GA", tariff:tariff});
        	  window.open(url,'_blank');
    	  }
    	  else{
    		  alert('Please select one row');
    	  }

      }
    }
})();
