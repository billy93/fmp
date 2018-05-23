(function() {
    'use strict';

    angular
        .module('fmpApp')
        .factory('stateHandler', stateHandler);

    stateHandler.$inject = ['$rootScope', '$state', '$sessionStorage', '$localStorage', '$window',
        'Auth', 'Principal', 'VERSION', '$ocLazyLoad'];

    function stateHandler($rootScope, $state, $sessionStorage, $localStorage, $window,
        Auth, Principal, VERSION, $ocLazyLoad) {
        return {
            initialize: initialize
        };

        function initialize() {
            $rootScope.VERSION = VERSION;
            $rootScope.isAuthenticated = Principal.isAuthenticated;
            
            var stateChangeStart = $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams, fromState) {
                $rootScope.toState = toState;
                $rootScope.toStateParams = toStateParams;
                $rootScope.fromState = fromState;

                // Redirect to a state with an external URL (http://stackoverflow.com/a/30221248/1098564)
                if (toState.external) {
                    event.preventDefault();
                    $window.open(toState.url, '_self');
                }
                
            	if (Principal.isIdentityResolved()) {
                    Auth.authorize();
                }
            });

            var stateChangeSuccess = $rootScope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams) {
                var titleKey = 'fmp' ;

                if ($sessionStorage.changePasswordNeeded == '1' || $localStorage.changePasswordNeeded == '1') {
                	$state.go('password-expired');
                } 
                
                // Set the page title key to the one configured in state or use default one
                if (toState.data.pageTitle) {
                    titleKey = toState.data.pageTitle;
                }
                $window.document.title = titleKey;

//            	$ocLazyLoad.load('content/js/modernizr-custom.js');
//               	$ocLazyLoad.load('content/js/plugins.js');
//               	$ocLazyLoad.load('content/js/revealer.js');
//                $ocLazyLoad.load('content/js/main.js');
//            	$ocLazyLoad.load('content/js/home.js');            	
            });

            $rootScope.$on('$destroy', function () {
                if(angular.isDefined(stateChangeStart) && stateChangeStart !== null){
                    stateChangeStart();
                }
                if(angular.isDefined(stateChangeSuccess) && stateChangeSuccess !== null){
                    stateChangeSuccess();
                }
            });
        }
    }
})();
