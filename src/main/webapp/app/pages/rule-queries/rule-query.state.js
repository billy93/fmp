(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rule-query', {
        	parent: 'app',
            url: '/rule-query',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Rule Query'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/rule-queries/rule-queries.html',
                    controller: 'RuleQueryController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	queryParams: [function() {
            		return {
                		cxr: null,
                		ruleTarNo: null,
                		ruleNo: null,
                		type: null,
                		src: null,
                		cat: null,
                		catNo: null
                	}
            	}]
            }
        })
    }

})();
