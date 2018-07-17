(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageSearchReplaceDialogController', WorkPackageSearchReplaceDialogController);

    WorkPackageSearchReplaceDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', 'DateUtils', '$uibModalInstance', 'WorkPackage', '$state', 'Agent', 'filter', 'fareSheet', 'workPackage', 'fareType'];

    function WorkPackageSearchReplaceDialogController($scope, FileSaver, DataUtils, DateUtils, $uibModalInstance, WorkPackage, $state, Agent, filter, fareSheet, workPackage, fareType) {

        var vm = this;
        vm.clear = clear;
        vm.workPackage = workPackage;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        $scope.dateformat = "dd/MM/yyyy";
        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
        
        vm.filter = {
        	andor:'and',
        	no:{
        		check:false
        	},
        	status:{
    			check:false,
    			replace:{
    				check:false
    			}
    		},
    		action:{
    			check:false
    		},
        	tariffNumber:{        		
        		tarNo:{
        			check:false,
        			search:null
        		},
        		tarCd:{
        			check:false,
        			search:null
        		},
        		global:{
        			check:false,
        			search:null
        		}
        	},
        	origin:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	destination:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	fareBasis:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	bookingClass:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	cabin:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	typeOfJourney:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},        	
        	footnote1:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	rtgno:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	ruleno:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	currency:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	amount:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	aif:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	travelStart:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	travelEnd:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	saleStart:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	saleEnd:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	comment:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	travelComplete:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	travelCompleteIndicator:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	ratesheetComment:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	carrier:{
        		check:false,
        		search:'GA',
        		replace:{
    				check:false
    			}
        	},
        	bucket:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	zone:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	ssn:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	loc1:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	loc1Type:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	loc2:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	loc2Type:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	baseFareBasis:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	baseRuleNo:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	baseTarcd:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	calcType:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	percentBaseFare:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	discountSpecifiedAmount:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	passengerType:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	fareType:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	ticketCode:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	ticketDesignator:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	rtgnoTarno:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	newFareBasis:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	newTypeOfJourney:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        	newBookingCode:{
        		check:false,
        		replace:{
    				check:false
    			}
        	},
        };
        vm.originalFilter = angular.copy(vm.filter);
        
        if(filter != null){
        	vm.filter = filter;
        	
        	if(vm.filter.message != null){
        		if(confirm(vm.filter.message)){
        			vm.filter.index = 0;
        			vm.filter.message = null;
        			$uibModalInstance.close(vm.filter);        			
        		}
        	}
        }
        
        vm.find = function(){
        	vm.mapFilter();
        	vm.filter.find = true;
        	vm.filter.replace = false;
        	vm.filter.replaceAll = false;
        	$uibModalInstance.close(vm.filter);
        }
        
        vm.replace = function(){
        	vm.mapFilter();
        	vm.filter.replace = true;
        	vm.filter.replaceAll = false;
        	vm.filter.find = false;
        	$uibModalInstance.close(vm.filter);
        }
        
        vm.replaceAll = function(){
        	vm.mapFilter();
        	vm.filter.replaceAll = true;
        	vm.filter.replace = false;
        	vm.filter.find = false;
        	console.log(vm.filter);
        	$uibModalInstance.close(vm.filter);
        }
        
        vm.mapFilter = function(){
        	if(vm.filter.travelStart.search != null){
        		vm.filter.travelStart.search = DateUtils.convertLocalDateToServer(vm.filter.travelStart.search);
        	}
        	if(vm.filter.travelEnd.search != null){
        		vm.filter.travelEnd.search = DateUtils.convertLocalDateToServer(vm.filter.travelEnd.search);
        	}
        	if(vm.filter.saleStart.search != null){
        		vm.filter.saleStart.search = DateUtils.convertLocalDateToServer(vm.filter.saleStart.search);
        	}
        	if(vm.filter.saleEnd.search != null){
        		vm.filter.saleEnd.search = DateUtils.convertLocalDateToServer(vm.filter.saleEnd.search);
        	}
        	if(vm.filter.travelComplete.search != null){
        		vm.filter.travelComplete.search = DateUtils.convertLocalDateToServer(vm.filter.travelComplete.search);
        	}
//        	if(vm.filter.travelStart.replace.value != null){
//        		vm.filter.travelStart.replace.value = DateUtils.convertLocalDateToServer(vm.filter.travelStart.replace.value);
//        	}
//        	if(vm.filter.travelEnd.replace.value != null){
//        		vm.filter.travelEnd.replace.value = DateUtils.convertLocalDateToServer(vm.filter.travelEnd.replace.value);
//        	}
//        	if(vm.filter.saleStart.replace.value != null){
//        		vm.filter.saleStart.replace.value = DateUtils.convertLocalDateToServer(vm.filter.saleStart.replace.value);
//        	}
//        	if(vm.filter.saleEnd.replace.value != null){
//        		vm.filter.saleEnd.replace.value = DateUtils.convertLocalDateToServer(vm.filter.saleEnd.replace.value);
//        	}
//        	if(vm.filter.travelComplete.replace.value != null){
//        		vm.filter.travelComplete.replace.value = DateUtils.convertLocalDateToServer(vm.filter.travelComplete.replace.value);
//        	}
        };
        
        vm.reset = function(){
        	 vm.filter = vm.originalFilter;
        };
        
        vm.showField = function(field){   
        	var fields = [];

//    		var fields = [
//    			'status', 'carrier', 'action', 'tarno', 'tarcd', 'global', 'origin', 'destination', 
//    			'bucket', 'farebasis', 'bookingclass', 'cabin', 'owrt', 'footnote', 'zone', 'rtgno', 'ruleno',
//    			'currency', 'amount', 'aif', 'travelstart', 'travelend', 'salestart', 'saleend', 'comment',
//    			'travelcomplete', 'travelcompleteindicator', 'ratesheetcomment'
//    		];
        	
        	if(fareType == 'fare'){
        		var fields = [
        			'status', 'carrier', 'action', 'tarno', 'tarcd', 'global', 'origin', 'destination', 'farebasis', 
        			'bookingclass', 'cabin', 'owrt', 'footnote', 'rtgno', 'ruleno',
        			'currency', 'amount', 'aif', 'travelstart', 'travelend', 'salestart', 'saleend', 'comment',
        			'travelcomplete', 'travelcompleteindicator', 'ratesheetcomment'
        		];    
        		
        	}
        	else if(fareType == 'fare-regular-addon'){
        		var fields = [
        			'status', 'carrier', 'action', 'tarno', 'tarcd', 'global','origin', 'destination',
        			'bucket', 'owrt', 'footnote', 'zone', 'rtgno','currency', 'amount', 
        			'travelstart', 'travelend', 'salestart', 'saleend', 'comment', 'travelcomplete', 'travelcompleteindicator',
        		];
        	}
        	else if(fareType == 'fare-market'){
        		var fields = [
        			'status', 'carrier', 'action','origin', 'destination',
        			'farebasis', 'bookingclass', 'ssn', 'cabin', 'owrt', 'ruleno', 'currency', 'amount', 
        			'travelstart', 'travelend', 'salestart', 'saleend', 'comment', 'travelcomplete', 'travelcompleteindicator',
        			'ratesheetcomment'
        		];
        	}
        	else if(fareType == 'fare-market-addon'){
        	}
        	else if(fareType == 'fare-discount'){
        		var fields = [
        			'status', 'tarcd', 'loc1Type', 'loc1', 'loc2Type', 'loc2', 'basefarebasis', 'baseruleno', 'basetarcd', 'calctype',
        			'percentbasefare', 'currency', 'discountspecifiedamount', 'passengertype', 'faretype', 'ticketcode', 'ticketdesignator',
        			'owrt', 'global', 'rtgno', 'rtgnotarno', 'newfarebasis', 'newtypeofjourney', 'newbookingcode', 
        			'travelstart', 'travelend', 'salestart', 'saleend', 'comment', 'travelcomplete', 'travelcompleteindicator',
        		];
        	}
        	else if(fareType == 'fare-waiver'){
        	}
        	if(fields.indexOf(field) > -1){
    			return true;
    		}
        	return false;
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
