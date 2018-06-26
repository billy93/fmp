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
            	params: [function() {
            		return {
                		outputFile: null,
                		delayDays: null,
                		delayHours: null,
                		delayMinutes: null,
                		fares: [],
                		startDate: null,
                		endDate: null
                	}
            	}]
            }
        })
    }

})();
