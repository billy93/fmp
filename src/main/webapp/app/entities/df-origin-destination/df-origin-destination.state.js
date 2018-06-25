(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('df-origin-destination', {
            parent: 'entity',
            url: '/df-origin-destination?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DFOriginDestinations'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/df-origin-destination/df-origin-destinations.html',
                    controller: 'DFOriginDestinationController',
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
                search: null
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
        .state('df-origin-destination-detail', {
            parent: 'df-origin-destination',
            url: '/df-origin-destination/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DFOriginDestination'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/df-origin-destination/df-origin-destination-detail.html',
                    controller: 'DFOriginDestinationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DFOriginDestination', function($stateParams, DFOriginDestination) {
                    return DFOriginDestination.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'df-origin-destination',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('df-origin-destination-detail.edit', {
            parent: 'df-origin-destination-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-origin-destination/df-origin-destination-dialog.html',
                    controller: 'DFOriginDestinationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DFOriginDestination', function(DFOriginDestination) {
                            return DFOriginDestination.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('df-origin-destination.new', {
            parent: 'df-origin-destination',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-origin-destination/df-origin-destination-dialog.html',
                    controller: 'DFOriginDestinationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                origAirport: null,
                                destAirport: null,
                                origCity: null,
                                destCity: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('df-origin-destination', null, { reload: 'df-origin-destination' });
                }, function() {
                    $state.go('df-origin-destination');
                });
            }]
        })
        .state('df-origin-destination.edit', {
            parent: 'df-origin-destination',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-origin-destination/df-origin-destination-dialog.html',
                    controller: 'DFOriginDestinationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DFOriginDestination', function(DFOriginDestination) {
                            return DFOriginDestination.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('df-origin-destination', null, { reload: 'df-origin-destination' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('df-origin-destination.delete', {
            parent: 'df-origin-destination',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-origin-destination/df-origin-destination-delete-dialog.html',
                    controller: 'DFOriginDestinationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DFOriginDestination', function(DFOriginDestination) {
                            return DFOriginDestination.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('df-origin-destination', null, { reload: 'df-origin-destination' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
