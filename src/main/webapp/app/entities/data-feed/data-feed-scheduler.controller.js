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
        vm.startScheduler = startScheduler;
        vm.stopScheduler = stopScheduler;
        vm.getDayValue = getDayValue;
        vm.getTimeValue = getTimeValue;
        vm.getDateValue = getDateValue;
        vm.getDateTimeValue = getDateTimeValue;
        
        vm.showData();
        
        function showData() {
        	DFScheduler.getAll("", onSuccess, onError);
        	
        	function onSuccess(data) {
        		vm.automaticData = data.automatic;
        		vm.automaticData.startDate = DateUtils.convertDateTimeFromServer(data.automatic.startDate);
        		vm.automaticData.startTime = DateUtils.convertDateTimeFromServer(data.automatic.startTime);
        		vm.automaticData.dayOfWeekValue = vm.getDayValue(vm.automaticData.dayOfWeek);
        		vm.automaticData.timeValue = vm.getTimeValue(vm.automaticData.startTime);
        		vm.automaticData.filepathValue = vm.automaticData.filepath;
        		
        		vm.manualData = data.manual;
        		vm.manualData.startDate = DateUtils.convertDateTimeFromServer(data.manual.startDate);
        		vm.manualData.endDate = DateUtils.convertDateTimeFromServer(data.manual.endDate);
        		vm.manualData.startTime = DateUtils.convertDateTimeFromServer(data.manual.startTime);
        		vm.manualData.delayTimeValue = vm.getDateTimeValue(vm.manualData.startTime);
        		vm.manualData.startDateValue = vm.getDateValue(vm.manualData.startDate);
        		vm.manualData.endDateValue = vm.getDateValue(vm.manualData.endDate);
        		vm.manualData.filepathValue = vm.manualData.filepath;
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
            
            vm.datePickerOpenStatus = {};
            vm.datePickerOpenStatus[date] = true;
        }
        
        function triggerAutomatic() {
        	vm.automaticData.type = "automatic";
        	DFScheduler.updateScheduler(vm.automaticData, onSuccess, onError);
        	function onSuccess(data) {
        		vm.automaticData = data;
        		vm.automaticData.startDate = DateUtils.convertDateTimeFromServer(data.startDate);
        		vm.automaticData.startTime = DateUtils.convertDateTimeFromServer(data.startTime);
        		vm.automaticData.dayOfWeekValue = vm.getDayValue(vm.automaticData.dayOfWeek);
        		vm.automaticData.timeValue = vm.getTimeValue(vm.automaticData.startTime);
        		vm.automaticData.filepathValue = vm.automaticData.filepath;
            }
        	
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function triggerManual() {
        	vm.manualData.type = "manual";
        	DFScheduler.updateScheduler(vm.manualData, onSuccess, onError);
        	function onSuccess(data) {
        		vm.manualData = data;
        		vm.manualData.startDate = DateUtils.convertDateTimeFromServer(data.startDate);
        		vm.manualData.endDate = DateUtils.convertDateTimeFromServer(data.endDate);
        		vm.manualData.startTime = DateUtils.convertDateTimeFromServer(data.startTime);
        		vm.manualData.delayTimeValue = vm.getDateTimeValue(vm.manualData.startTime);
        		vm.manualData.startDateValue = vm.getDateValue(vm.manualData.startDate);
        		vm.manualData.endDateValue = vm.getDateValue(vm.manualData.endDate);
        		vm.manualData.filepathValue = vm.manualData.filepath;
            }
        	
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function startScheduler() {
        	DFScheduler.startScheduler();
        }
        
        function stopScheduler() {
        	DFScheduler.stopScheduler();
        }
        
        vm.dayOfWeekList = [
        	{key: "SUN", value: "Sunday"},
        	{key: "MON", value: "Monday"},
        	{key: "TUE", value: "Tuesday"},
        	{key: "WED", value: "Wednesday"},
        	{key: "THU", value: "Thursday"},
        	{key: "FRI", value: "Friday"},
        	{key: "SAT", value: "Saturday"},
        ]
        
        function getDayValue(key) {
        	var dayValue = '';
        	vm.dayOfWeekList.forEach(function(day) {
        		if(key == day.key) {
        			dayValue = day.value;
        		}
        	});
        	
        	return dayValue;
        }
        
        function getTimeValue(date) {
        	return date.getHours()+":"+date.getMinutes();
        }
        
        function getDateValue(date) {
        	return date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate();
        }
        
        function getDateTimeValue(date) {
        	return getDateValue(date)+" "+getTimeValue(date);
        }
	}
})();
