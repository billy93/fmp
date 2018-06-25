(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageSearchReplaceDialogController', WorkPackageSearchReplaceDialogController);

    WorkPackageSearchReplaceDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'Agent', 'filter', 'fareSheet'];

    function WorkPackageSearchReplaceDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, Agent, filter, fareSheet) {

        var vm = this;
        vm.clear = clear;
        
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        $scope.dateformat = "yyyy-MM-dd";
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
        	},
        	destination:{
        		check:false,
        	},
        	fareBasis:{
        		check:false,
        	},
        	bookingClass:{
        		check:false,
        	},
        	cabin:{
        		check:false,
        	},
        	typeOfJourney:{
        		check:false,
        	},        	
        	footnote:{
        		check:false,
        	},
        	rtgno:{
        		check:false,
        	},
        	ruleno:{
        		check:false,
        	},
        	currency:{
        		check:false,
        	},
        	amount:{
        		check:false,
        	},
        	aif:{
        		check:false,
        	},
        	travelStart:{
        		check:false,
        	},
        	travelEnd:{
        		check:false,
        	},
        	saleStart:{
        		check:false,
        	},
        	saleEnd:{
        		check:false,
        	},
        	comment:{
        		check:false,
        	},
        	travelComplete:{
        		check:false,
        	},
        	travelCompleteIndicator:{
        		check:false,
        	},
        	ratesheetComment:{
        		check:false,
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
        	vm.filter.find = true;
        	vm.filter.replace = false;
        	vm.filter.replaceAll = false;
        	$uibModalInstance.close(vm.filter);
        }
        
        vm.replace = function(){
        	vm.filter.replace = true;
        	vm.filter.replaceAll = false;
        	vm.filter.find = false;
        	$uibModalInstance.close(vm.filter);
        }
        
        vm.replaceAll = function(){
        	vm.filter.replaceAll = true;
        	vm.filter.replace = false;
        	vm.filter.find = false;
        	$uibModalInstance.close(vm.filter);
        }
        
        vm.reset = function(){
        	 vm.filter = vm.originalFilter;
        };
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
