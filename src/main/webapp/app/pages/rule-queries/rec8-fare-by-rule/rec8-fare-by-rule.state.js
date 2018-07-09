(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rec8-fare-by-rule', {
            parent: 'rule-queries',
            url: '/rec8-fare-by-rule?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Rec 8 Fare By Rule'
            },
            views: {
                'content@': {
                	templateUrl: 'app/pages/rule-queries/rec8-fare-by-rule/rec8-fare-by-rule.html',
                    controller: 'Rec8FareByRuleController',
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
                rec8FareByRuleFilter: null
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
        });
    }

})();
