(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('routingquery', {
            parent: 'app',
            url: '/routingquery?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Routingqueries'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/routing-query/routing-query.html',
                    controller: 'RoutingqueryController',
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
//                itemsPerPage: "10",
                routingQueryFilter: null
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
