(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageSelectStateDialogController', WorkPackageSelectStateDialogController);

    WorkPackageSelectStateDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'states', 'header'];

    function WorkPackageSelectStateDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, states, header) {

        var vm = this;
        vm.clear = clear;
        vm.states = states;
        vm.option = { searchType:'code' };
        vm.selectedRow = vm.states[0];
        vm.sortType     = 'code'; // set the default sort type
        vm.sortReverse  = false;  // set the default sort order
        vm.searchFish   = '';     // set the default search/filter term
        vm.header = header;
        console.log(vm.header);
        vm.selectedRow = null;
        
        vm.rowHighlighted = function (idSelected) {
           vm.selectedRow = idSelected;
        };
        
        vm.search = function(){
        	vm.selectedRow = null;
        }
        
        vm.select = function(){
        	$uibModalInstance.close(vm.selectedRow);
        }
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
    }
})();
