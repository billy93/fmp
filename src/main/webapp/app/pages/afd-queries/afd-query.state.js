(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('afd-query', {
        	parent: 'app',
            url: '/afd-query',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AFD Query'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/afd-queries/afd-queries.html',
                    controller: 'AfdQueryController',
                    controllerAs: 'vm'
                },
                'specified-constructed@afd-query': {
                    templateUrl: 'app/pages/afd-queries/specified-constructed/specified-constructed.html',
                    controller: 'SpecifiedConstructedController',
                    controllerAs: 'vm'
                },
				'add-ons@afd-query' : {
					templateUrl: 'app/pages/afd-queries/add-ons/add-ons.html',
                    controller: 'AddOnsController',
                    controllerAs: 'vm'
				},
				'fbr-query@afd-query' : {
					templateUrl: 'app/pages/afd-queries/fbr-query/fbr-query.html',
                    controller: 'FbrQueryController',
                    controllerAs: 'vm'
				}
            },
            params: {
            	tab: '1'
            }
        })
    }

})();
