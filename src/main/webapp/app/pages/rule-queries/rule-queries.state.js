(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rule-queries', {
        	parent: 'app',
            url: '/rule-queries',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Rule Query'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/rule-queries/rule-queries.html',
                    controller: 'RuleQueriesController',
                    controllerAs: 'vm'
                },
                'rule-query@rule-queries': {
                    templateUrl: 'app/pages/rule-queries/rule-query/rule-query.html',
                    controller: 'RuleQueryController',
                    controllerAs: 'vm'
                },
				'fare-class-query@rule-queries' : {
					templateUrl: 'app/pages/rule-queries/fare-class-query/fare-class-query.html',
                    controller: 'FareClassQueryController',
                    controllerAs: 'vm'
				},
				'rec8-fare-by-rule@rule-queries' : {
					templateUrl: 'app/pages/rule-queries/rec8-fare-by-rule/rec8-fare-by-rule.html',
                    controller: 'Rec8FareByRuleController',
                    controllerAs: 'vm'
				}
            },
            params : {
            	tab: 1,
				page : {
					value : '1',
					squash : true
				},
				sort : {
					value : 'id,asc',
					squash : true
				},
				search : null
			},
			resolve : {
				pagingParams : [
					'$stateParams',
					'PaginationUtil',
					function($stateParams, PaginationUtil) {
						return {
							page : PaginationUtil
									.parsePage($stateParams.page),
							sort : $stateParams.sort,
							predicate : PaginationUtil
									.parsePredicate($stateParams.sort),
							ascending : PaginationUtil
									.parseAscending($stateParams.sort),
							search : $stateParams.search
						};
					} 
				],
			}
        })
    }

})();
