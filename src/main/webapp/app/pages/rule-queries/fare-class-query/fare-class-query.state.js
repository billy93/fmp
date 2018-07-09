(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fare-class-query', {
            parent: 'rule-query',
            url: '/fare-class-query?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Fare Class Query'
            },
            views: {
                'content@': {
                	templateUrl: 'app/pages/rule-queries/fare-class-query/fare-class-query.html',
                    controller: 'FareClassQueryController',
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
                fareClasssQueryFilter: null
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
