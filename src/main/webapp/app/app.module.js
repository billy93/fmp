(function() {
    'use strict';

    angular
        .module('fmpApp', [
            'ngStorage',
            'ngResource',
            'ngCookies',
            'ngAria',
            'ngCacheBuster',
            'ngFileUpload',
            'ui.bootstrap',
            'ui.bootstrap.datetimepicker',
            'ui.router',
            'infinite-scroll',
            // jhipster-needle-angularjs-add-module JHipster will add new module here
            'angular-loading-bar',
            'ngFileSaver',
            'ui.select',
            'ngIdle',
            'ngWYSIWYG'
        ])
        .config(config)
        .run(run);

    run.$inject = ['stateHandler'];

    function run(stateHandler) {
        stateHandler.initialize();
    }
    
    config.$inject = ['IdleProvider','KeepaliveProvider'];

    
    function config(IdleProvider, KeepaliveProvider) {
		// configure Idle settings
		IdleProvider.idle(1200); // in seconds
	}
})();
