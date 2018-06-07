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
                itemsPerPage: "10",
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
        .state('routingquery-detail', {
            parent: 'routingquery',
            url: '/routingquery/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Routingquery'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/routing-query/routing-query-detail.html',
                    controller: 'RoutingqueryDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Routingquery', function($stateParams, Routingquery) {
                    return Routingquery.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'routingquery',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('routingquery-detail.edit', {
            parent: 'routingquery-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/pages/routing-query/routing-query-dialog.html',
                    controller: 'RoutingqueryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Routingquery', function(Routingquery) {
                            return Routingquery.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('routingquery.new', {
            parent: 'routingquery',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/pages/routing-query/routing-query-dialog.html',
                    controller: 'RoutingqueryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                linkNo: null,
                                src: null,
                                cxr: null,
                                tarNo: null,
                                routingNo: null,
                                effectiveDate: null,
                                discontinuedDate: null,
                                drv: null,
                                cpi: null,
                                di: null,
                                intPt: null,
                                untPt: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('routingquery', null, { reload: 'routingquery' });
                }, function() {
                    $state.go('routingquery');
                });
            }]
        })
        .state('routingquery.edit', {
            parent: 'routingquery',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/pages/routing-query/routing-query-dialog.html',
                    controller: 'RoutingqueryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Routingquery', function(Routingquery) {
                            return Routingquery.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('routingquery', null, { reload: 'routingquery' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('routingquery.delete', {
            parent: 'routingquery',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/pages/routing-query/routing-query-delete-dialog.html',
                    controller: 'RoutingqueryDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Routingquery', function(Routingquery) {
                            return Routingquery.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('routingquery', null, { reload: 'routingquery' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
