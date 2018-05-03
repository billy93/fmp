(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('PasswordController', PasswordController);

    PasswordController.$inject = ['Auth', 'Principal', 'SystemParameter', '$stateParams'];

    function PasswordController (Auth, Principal, SystemParameter, $stateParams) {
        var vm = this;

        vm.changePassword = changePassword;
        vm.doNotMatch = null;
        vm.error = null;
        vm.success = null;
        vm.minLength = 8;
        vm.maxLength = 30;
        vm.errorMessage = null;
        
        vm.isExpired = $stateParams.isExpired;
        
        Principal.identity().then(function(account) {
            vm.account = account;
        });

        SystemParameter.getPasswordParameter(function(data) {
        	data.forEach(function(d) {
        		if (d.name == 'PASSWORD_MIN_LENGTH') {
        			vm.minLength = d.value;
        		} else if (d.name == 'PASSWORD_MAX_LENGTH') {
        			vm.maxLength = d.value;
        		}
        	});
        }, function(error){});
        
        function changePassword () {
            if (vm.password !== vm.confirmPassword) {
                vm.error = null;
                vm.success = null;
                vm.doNotMatch = 'ERROR';
            } else {
                vm.doNotMatch = null;
                Auth.changePassword(vm.password).then(function () {
                    vm.error = null;
                    vm.success = 'OK';
                }).catch(function (err) {
                    vm.success = null;
                    vm.error = 'ERROR';
                    console.log(err);
                    vm.errorMessage = err.data.title;
                });
            }
        }
    }
})();
