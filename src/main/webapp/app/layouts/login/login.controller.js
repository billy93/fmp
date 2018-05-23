(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('HomeLoginController', HomeLoginController);

    HomeLoginController.$inject = ['Principal', 'Auth', '$state', '$rootScope'];

    function HomeLoginController (Principal, Auth, $state, $rootScope) {
        var vm = this;

        vm.isAuthenticated = Principal.isAuthenticated;
        vm.authenticationError = false;
        vm.login = login;

        function login(event) {
        	event.preventDefault();
            Auth.login({
                username: vm.username,
                password: vm.password,
                rememberMe: vm.rememberMe
            }).then(function () {
                vm.authenticationError = false;
                if ($state.current.name === 'register' || $state.current.name === 'activate' ||
                    $state.current.name === 'finishReset' || $state.current.name === 'requestReset') {
                    $state.go('home');
                }

                $rootScope.$broadcast('authenticationSuccess');
                
                // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // since login is successful, go to stored previousState and clear previousState
                if (Auth.getPreviousState()) {
                    var previousState = Auth.getPreviousState();
                    Auth.resetPreviousState();
                    $state.go(previousState.name, previousState.params);
                }
            }).catch(function (err) {
            	vm.errorMessage = err.data.detail;
                vm.authenticationError = true;
            });
        }
    }
})();
