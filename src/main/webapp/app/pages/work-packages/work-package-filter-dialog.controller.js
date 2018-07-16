(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageFilterDialogController', WorkPackageFilterDialogController);

    WorkPackageFilterDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'value', 'field'];

    function WorkPackageFilterDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, value, field) {

        var vm = this;
        vm.clear = clear;        
        vm.sortType     = 'cityCode'; // set the default sort type
        vm.sortReverse  = false;  // set the default sort order
        vm.searchFish   = '';     // set the default search/filter term
        vm.currentTab = 'value';
        vm.selectedRow = null;
        vm.selectedCityGroupRow = null;
        vm.value = value;
        vm.field = field;
        
                 
        vm.rowValueHighlighted = function (idSelected) {
            vm.selectedValueRow = idSelected;
        };
                        
        vm.select = function(){
        	if(vm.currentTab == 'range'){
        		$uibModalInstance.close(vm.selectedRow);
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
        
    }
})();
