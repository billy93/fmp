(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageGenerateTourcodeDialogController', WorkPackageGenerateTourcodeDialogController);

    WorkPackageGenerateTourcodeDialogController.$inject = ['User', 'Principal', 'workPackage', '$scope', '$uibModalInstance', '$state'];

    function WorkPackageGenerateTourcodeDialogController(User, Principal, workPackage, $scope, $uibModalInstance, $state) {

        var vm = this;     
        vm.clear = clear;
        
        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        
        vm.workPackage = workPackage;
        vm.types = ["Fares", "Add-Ons"];
        vm.correspondences = ["Internal", "External"];
        vm.areas = ["INT", "DOM"];
        vm.subareas = ["JKT", "AMQ"];
        vm.fareType = ["AH", "YR", "OTHER", "UR", "WV"];
        
        if(!vm.workPackage.attachment){
        	vm.types.push("Attachment");
        }
        if(!vm.workPackage.filingInstruction){
        	vm.types.push("Filing Instruction");
        }
        
        vm.save = function(){
        	vm.option.department = vm.account.department;
        	User.generateTourcode(vm.option, function(result){
    			$uibModalInstance.close(result);
    		}, function (error){
    			alert('Error occured. Please try again');
    		});
        	
        }
        vm.loadAll = function(){
        		
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();
