(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageSelectTariffDialogController', WorkPackageSelectTariffDialogController);

    WorkPackageSelectTariffDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'tariffNumber'];

    function WorkPackageSelectTariffDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, tariffNumber) {

        var vm = this;
        vm.clear = clear;
        vm.tariffNumber = tariffNumber;
        vm.option = { searchType:'tarNo' };
        vm.selectedRow = vm.tariffNumber[0];
        vm.sortType     = 'tarNo'; // set the default sort type
        vm.sortReverse  = false;  // set the default sort order
        vm.searchFish   = '';     // set the default search/filter term
        
        vm.selectedRow = null;
        
        vm.rowHighlighted = function (idSelected) {
           vm.selectedRow = idSelected;
        };
        
        vm.search = function(){
        	vm.selectedRow = null;
//        	if(vm.filteredCities != null){
//        		console.log("FILTERED CITY NOT NULL");
//        		console.log(vm.filteredCities);
//        		vm.selectedRow = vm.filteredCities[0];
//        	}
        }
        
        vm.select = function(){
        	$uibModalInstance.close(vm.selectedRow);
        }
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
    }
})();
