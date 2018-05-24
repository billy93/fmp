(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fare-type', {
            parent: 'entity',
            url: '/fare-type?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FareTypes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fare-type/fare-types.html',
                    controller: 'FareTypeController',
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
        .state('fare-type-detail', {
            parent: 'fare-type',
            url: '/fare-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FareType'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fare-type/fare-type-detail.html',
                    controller: 'FareTypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'FareType', function($stateParams, FareType) {
                    return FareType.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fare-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fare-type-detail.edit', {
            parent: 'fare-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fare-type/fare-type-dialog.html',
                    controller: 'FareTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FareType', function(FareType) {
                            return FareType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fare-type.new', {
            parent: 'fare-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fare-type/fare-type-dialog.html',
                    controller: 'FareTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                code: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fare-type', null, { reload: 'fare-type' });
                }, function() {
                    $state.go('fare-type');
                });
            }]
        })
        .state('fare-type.edit', {
            parent: 'fare-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fare-type/fare-type-dialog.html',
                    controller: 'FareTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FareType', function(FareType) {
                            return FareType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fare-type', null, { reload: 'fare-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fare-type.delete', {
            parent: 'fare-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fare-type/fare-type-delete-dialog.html',
                    controller: 'FareTypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FareType', function(FareType) {
                            return FareType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fare-type', null, { reload: 'fare-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
