(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('data-feed-scheduler', {
        	parent: 'app',
            url: '/data-feed-scheduler',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Data Feed'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data-feed/data-feed-scheduler.html',
                    controller: 'DataFeedSchedulerController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	manualData: [function() {
            		return {
            			id: null,
                		delayDays: null,
                		delayHours: null,
                		delayMinutes: null,
                		startDate: null,
                		endDate: null,
                		type: null,
                		startTime: null,
                		atpcoFares: null,
                		marketFares: null,
                		dayOfWeek: null,
                		filepath: null
                	}
            	}],
            	automaticData: [function() {
            		return {
            			id: null,
                		delayDays: null,
                		delayHours: null,
                		delayMinutes: null,
                		startDate: null,
                		endDate: null,
                		type: null,
                		startTime: null,
                		atpcoFares: null,
                		marketFares: null,
                		dayOfWeek: null,
                		filepath: null
                	}
            	}],
            }
        })
    }

})();
