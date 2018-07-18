(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageFilterAdvanceDialogController', WorkPackageFilterAdvanceDialogController);

    WorkPackageFilterAdvanceDialogController.$inject = ['$uibModal','$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'value','field', 'filter'];

    function WorkPackageFilterAdvanceDialogController($uibModal, $scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, value,field,filter) {

        var vm = this;
        vm.clear = clear;        
        vm.sortReverse  = false;  // set the default sort order
        vm.searchFish   = '';     // set the default search/filter term
        vm.currentTab = 'filtering';
        vm.selectedRow = null;       
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "dMMMyyyy";
        vm.openCalendar = openCalendar;
        vm.ListFilter =[];
		vm.param={field:[], isNot : [], comparator: [], value : [], ignoreCase : []};
        vm.value = value;
        vm.field = field;
        vm.filter = filter;
        
        vm.rowValueHighlighted = function (idSelected) {
            vm.selectedValueRow = idSelected;
        };
                        
        vm.select = function(){
        	if(vm.currentTab == 'range'){
        		$uibModalInstance.close({key:'range', value:{from : vm.From, to : vm.To}});
        	}
        	else if(vm.currentTab == 'value'){
        		$uibModalInstance.close({key:'distinctValue', value:vm.selectedValueRow});
        	}
        	else if(vm.currentTab == 'card'){
        		$uibModalInstance.close({key:vm.option, value:vm.search});
        	}
        }
        
        vm.selectTab = function(tab){
        	vm.currentTab = tab;
        }
        
        vm.removeAllColumnFilter = function(){
        	$uibModalInstance.close({key:'removeAllColumnFilter'});
        }
        
        vm.removeThisColumnFilter = function(){
        	$uibModalInstance.close({key:'removeThisColumnFilter'});
        }
        
        vm.removeLastColumnFilter = function(){
        	$uibModalInstance.close({key:'removeLastColumnFilter'});
        }
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
        
        vm.addNewFilter = function(){
        	if(vm.valueFilter == undefined || vm.valueFilter ==''){
        		alert("You must enter a value to test against");
        	}else{
        		var isEmpty =null;
        		var ok = true; 
        		vm.valueFilterPass = vm.valueFilter;
        		if(vm.isCheckedNot == undefined || vm.isCheckedNot == false){
        			vm.not = isEmpty;
        		}else if(vm.isCheckedNot == true){
        			vm.not = "not";
        		}
        		
        		if(vm.ignoreCase == undefined || vm.ignoreCase == false){
        			vm.ignore = isEmpty;
        		}else if(vm.ignoreCase == true){
        			vm.ignore = "Ignore Case";
        		}
        		
        		if(vm.newFilterComparator == "equal" ){
        			vm.Comparator= "equal to";
        		}else if(vm.newFilterComparator == "greaterThan"){
        			vm.Comparator = "greater than";
        		}else if(vm.newFilterComparator == "greaterThanorEqual"){
        			vm.Comparator = "greater than or equal to";
        		}else if(vm.newFilterComparator == "lessThan"){
        			vm.Comparator = "less than";
        		}else if(vm.newFilterComparator == "lessThanorEqual"){
        			vm.Comparator = "less than or equal to";
        		}else if(vm.newFilterComparator == "between"){
        			if(vm.valueFilter.match(/,/gi) != null ){
        				vm.Comparator = '';
        				var temp = vm.valueFilter;
        				var splitx = temp.split(",");
        				vm.valueFilterPass = splitx[0]+" between "+splitx[1];
        			}else{
        				alert("You must select two values. Use a comma to seperate values");
        				ok = false;
        			}
        		}else{
        			vm.Comparator = vm.newFilterComparator;
        		}
        		
        		if(ok){
        			var filter = vm.newFilterField+" "+vm.not+" "+vm.Comparator+" "+vm.valueFilterPass+" "+vm.ignore;
        			var final = filter.replace(/null/gi,'');
        			vm.ListFilter.push(final); 
        			vm.param.field.push(vm.newFilterField);
        			vm.param.isNot.push(vm.isCheckedNot);
        			vm.param.comparator.push(vm.newFilterComparator);
        			vm.param.value.push(vm.valueFilter);
        			vm.param.ignoreCase.push(vm.ignoreCase);
        		}
        		
        	}
        }
        
        if(vm.filter.length > 0 ){
        	for(var bakso=0 ; bakso< vm.filter.length; bakso++){
        		var temp = vm.filter[bakso].key+" equal to "+vm.filter[bakso].value;
        		vm.ListFilter.push(temp); 
        		vm.param.field.push(vm.filter[bakso].key);
    			vm.param.isNot.push(false);
    			vm.param.comparator.push("equal");
    			vm.param.value.push(vm.filter[bakso].value);
    			vm.param.ignoreCase.push(false);
        	}
        }
              
        vm.removeAllFilter = function(){
        	vm.ListFilter = [];
        	vm.param = null;
        }
        
        vm.removeFilter = function (query){
        	var index = vm.ListFilter.indexOf(query);
        	vm.ListFilter.splice(index, 1); 
        }
        
        vm.simple = function(){
        	$uibModalInstance.close({key:'simple', value:vm.field});
        }
        
        vm.ok = function(){
        	$uibModalInstance.close({key:'filtering', value:vm.param});
        }
               
    }
})();
