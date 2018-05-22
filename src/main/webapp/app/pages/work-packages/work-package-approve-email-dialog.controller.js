(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageApproveEmailDialogController', WorkPackageApproveEmailDialogController);

    WorkPackageApproveEmailDialogController.$inject = ['email', 'User', 'Principal', 'workPackage', '$scope', '$uibModalInstance', '$state'];

    function WorkPackageApproveEmailDialogController(email, User, Principal, workPackage, $scope, $uibModalInstance, $state) {

        var vm = this;     
        vm.clear = clear;
        vm.option = {};
        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        
        vm.workPackage = workPackage;
        vm.option.email =  email;
        vm.save = function(){
        	$uibModalInstance.close(vm.option);
        }
        vm.loadAll = function(){
        		
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        vm.addEmail = function() {
        	if (vm.option != undefined && vm.option.email != null) {
        		if (!vm.option.email) {
        			vm.option.email = [];        			
        		}
        		
        		if (vm.option.email.indexOf(vm.email) == -1) {
        			vm.option.email.push(vm.email);
        		}
        		
        		vm.email = null;
        	}
        } 

        vm.removeEmail = function(index) {
        	vm.option.email.splice(index, 1);
        }
    }
})();
