(function() {
	'use strict';

	angular.module('fmpApp').controller('DataFeedSchedulerController',
			DataFeedSchedulerController);

	DataFeedSchedulerController.$inject = [ '$state', 'DFScheduler', 'params', '$stateParams' ];

	function DataFeedSchedulerController($state, DFScheduler, params, $stateParams) {

		var vm = this;
		vm.params = params;
		vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        vm.triggerManual = triggerManual;
        vm.changeTime = changeTime;
        vm.browseFolder = browseFolder;
        
        vm.selectDataFeedSchedulerTab = selectDataFeedSchedulerTab;
        
        vm.tab = 1;
        
        function browseFolder() {
        	
        }
        
        
        function selectDataFeedSchedulerTab(tab) {
        	if(tab == "autoScheduler") {
        		vm.tab = 1;
        	} else if (tab == "manualScheduler") {
        		vm.tab = 2;
        	}
        }
        
        function changeTime(e) {
        	e.preventDefault;
        }
        
        function triggerManual() {
        	
        	DFScheduler.setParam(vm.params);
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
		
		function reset() {
        	
        	vm.params = {
            	delayDays: null,
            	delayHours: null,
            	delayMinutes: null,
            	faresAtpco: null,
            	faresMarket: null,
            	startDate: null,
            	endDate: null
        	};
		}
	}
})();
