(function() {
	'use strict';

	angular.module('fmpApp').controller('DataFeedSchedulerController',
			DataFeedSchedulerController);

	DataFeedSchedulerController.$inject = [ '$state', 'DFScheduler', 'manualData', 'automaticData', '$stateParams', 'DateUtils' ];

	function DataFeedSchedulerController($state, DFScheduler, manualData, automaticData, $stateParams, DateUtils) {

		var vm = this;
		vm.manualData = manualData;
		vm.automaticData = automaticData;
		vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        vm.showData = showData;
        vm.triggerAutomatic = triggerAutomatic;
        vm.triggerManual = triggerManual;
        vm.selectDataFeedSchedulerTab = selectDataFeedSchedulerTab;
        vm.tab = 1;
        
        vm.showData();
        
        function showData() {
        	DFScheduler.getAll("", onSuccess, onError);
        	
        	function onSuccess(data) {
        		vm.automaticData = data.automatic;
        		vm.automaticData.startDate = DateUtils.convertDateTimeFromServer(data.automatic.startDate);
        		vm.automaticData.startTime = DateUtils.convertDateTimeFromServer(data.automatic.startTime);
        		
        		vm.manualData = data.manual;
        		vm.manualData.startDate = DateUtils.convertDateTimeFromServer(data.manual.startDate);
        		vm.manualData.endDate = DateUtils.convertDateTimeFromServer(data.manual.endDate);
            }
        	
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function selectDataFeedSchedulerTab(tab) {
        	if(tab == "autoScheduler") {
        		vm.tab = 1;
        	} else if (tab == "manualScheduler") {
        		vm.tab = 2;
        	}
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            vm.datePickerOpenStatus[date] = true;
        }
        
        function triggerAutomatic() {
        	vm.automaticData.type = "automatic";
        	DFScheduler.setParam(vm.automaticData);
        }
        
        function triggerManual() {
        	vm.manualData.type = "manual";
        	DFScheduler.setParam(vm.manualData);
        }
	}
})();
