(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageSelectCityDialogController', WorkPackageSelectCityDialogController);

    WorkPackageSelectCityDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'cities', 'cityGroup'];

    function WorkPackageSelectCityDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, cities, cityGroup) {

        var vm = this;
        vm.clear = clear;
        vm.cities = cities;
        vm.cityGroup = cityGroup;
        vm.option = { searchType:'cityCode' };
        vm.selectedRow = vm.cities[0];
        vm.sortType     = 'cityCode'; // set the default sort type
        vm.sortReverse  = false;  // set the default sort order
        vm.searchFish   = '';     // set the default search/filter term
        vm.currentTab = 'city';
        vm.selectedRow = null;
        vm.selectedCityGroupRow = null;
         
        vm.rowHighlighted = function (idSelected) {
           vm.selectedRow = idSelected;
        };
        
        vm.rowCityGroupHighlighted = function (idSelected) {
            vm.selectedCityGroupRow = idSelected;
        };
        
        vm.search = function(){
        	vm.selectedRow = null;
        }
        
        vm.searchCityGroup = function(){
        	vm.selectedCityGroupRow = null;
        }
        
        vm.select = function(){
        	if(vm.currentTab == 'city'){
        		vm.selectedRow.type = 'city';
        		$uibModalInstance.close(vm.selectedRow);
        	}
        	else if(vm.currentTab == 'cityGroup'){
        		vm.selectedCityGroupRow.type = 'cityGroup';
        		$uibModalInstance.close(vm.selectedCityGroupRow);
        	}
        }
        
        vm.selectTab = function(tab){
        	vm.currentTab = tab;
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
    }
})();
