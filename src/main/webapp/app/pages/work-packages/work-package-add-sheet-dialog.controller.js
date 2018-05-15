(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageAddSheetDialogController', WorkPackageAddSheetDialogController);

    WorkPackageAddSheetDialogController.$inject = ['$scope', '$uibModalInstance', '$state'];

    function WorkPackageAddSheetDialogController($scope, $uibModalInstance, $state) {

        var vm = this;     
        vm.clear = clear;
        vm.types = ["Fares", "Add-Ons"];
        vm.save = function(){
        	$uibModalInstance.close(vm.option);
        }
        vm.loadAll = function(){
        		
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();
