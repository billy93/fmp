(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageSearchReplaceDialogController', WorkPackageSearchReplaceDialogController);

    WorkPackageSearchReplaceDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', 'DateUtils', '$uibModalInstance', 'WorkPackage', '$state', 'Agent', 'filter', 'fareSheet'];

    function WorkPackageSearchReplaceDialogController($scope, FileSaver, DataUtils, DateUtils, $uibModalInstance, WorkPackage, $state, Agent, filter, fareSheet) {

        var vm = this;
        vm.clear = clear;
        
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
        	}
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
        };
        
        vm.reset = function(){
        	 vm.filter = vm.originalFilter;
        };
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
