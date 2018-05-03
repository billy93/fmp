(function() {
    'use strict';

    angular
        .module('fmpApp')
        .factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider.$inject = ['$http', '$localStorage', '$sessionStorage', '$q'];

    function AuthServerProvider ($http, $localStorage, $sessionStorage, $q) {
        var service = {
            getToken: getToken,
            login: login,
            loginWithToken: loginWithToken,
            storeAuthenticationToken: storeAuthenticationToken,
            logout: logout
        };

        return service;

        function getToken () {

            return $localStorage.authenticationToken || $sessionStorage.authenticationToken;
        }

        function login (credentials) {
            var data = {
                username: credentials.username,
                password: credentials.password,
                rememberMe: credentials.rememberMe
            };
            return $http.post('api/authenticate', data).success(authenticateSuccess);

            function authenticateSuccess (data, status, headers) {
                var bearerToken = headers('Authorization');
                var changePasswordNeeded = headers('ChangePasswordNeeded');
                
                if (angular.isDefined(bearerToken) && bearerToken.slice(0, 7) === 'Bearer ') {
                    var jwt = bearerToken.slice(7, bearerToken.length);
                    service.storeAuthenticationToken(jwt, credentials.rememberMe, changePasswordNeeded);
                    
                    return jwt;
                }
            }
        }

        function loginWithToken(jwt, rememberMe) {
            var deferred = $q.defer();

            if (angular.isDefined(jwt)) {
                this.storeAuthenticationToken(jwt, rememberMe);
                deferred.resolve(jwt);
            } else {
                deferred.reject();
            }

            return deferred.promise;
        }

        function storeAuthenticationToken(jwt, rememberMe, changePasswordNeeded) {
            if(rememberMe){
                $localStorage.authenticationToken = jwt;
                $localStorage.changePasswordNeeded = changePasswordNeeded;
            } else {
                $sessionStorage.authenticationToken = jwt;
                $sessionStorage.changePasswordNeeded = changePasswordNeeded;
            }
        }
        
        function logout () {
            delete $localStorage.authenticationToken;
            delete $sessionStorage.authenticationToken;
            delete $localStorage.changePasswordNeeded;
            delete $sessionStorage.changePasswordNeeded;
        }
    }
})();
