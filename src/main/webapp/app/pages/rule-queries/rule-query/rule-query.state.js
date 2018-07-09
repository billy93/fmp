(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rule-query', {
        	parent: 'rule-queries',
            url: '/rule-query',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Rule Query'
            },
            views: {
                'content@': {
                	templateUrl: 'app/pages/rule-queries/rule-query/rule-query.html',
                    controller: 'RuleQueryController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null,
                size: null,
                ruleQueryFilter: null,
                ruleQueryDetailFilter: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                
            }
        })
    }

})();
