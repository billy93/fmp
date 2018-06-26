(function() {
	'use strict';

	angular.module('fmpApp').controller('DataFeedSchedulerController',
			DataFeedSchedulerController);

	DataFeedSchedulerController.$inject = [ '$state', 'DFScheduler', '$stateParams' ];

	function DataFeedSchedulerController($state, DFScheduler, $stateParams) {

		var vm = this;
		vm.params = params;
		vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
		
		function reset() {
        	
        	vm.params = {
        		outputFile: null,
            	delayDays: null,
            	delayHours: null,
            	delayMinutes: null,
            	fares: [],
            	startDate: null,
            	endDate: null
        	};
		}
	}
})();
