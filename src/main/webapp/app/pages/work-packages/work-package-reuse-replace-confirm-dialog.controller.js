(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageReuseReplaceConfirmDialogController', WorkPackageReuseReplaceConfirmDialogController);

    WorkPackageReuseReplaceConfirmDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state'];

    function WorkPackageReuseReplaceConfirmDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state) {

        var vm = this;
        vm.clear = clear;
        vm.option = {
        		attachment:false
        };
        vm.save = function(){
        	$uibModalInstance.close(vm.option);
        }
       
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
    }
})();
