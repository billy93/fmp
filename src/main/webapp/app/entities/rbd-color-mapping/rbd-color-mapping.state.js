(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rbd-color-mapping', {
            parent: 'entity',
            url: '/rbd-color-mapping?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RbdColorMappings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rbd-color-mapping/rbd-color-mappings.html',
                    controller: 'RbdColorMappingController',
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
        .state('rbd-color-mapping-detail', {
            parent: 'rbd-color-mapping',
            url: '/rbd-color-mapping/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RbdColorMapping'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rbd-color-mapping/rbd-color-mapping-detail.html',
                    controller: 'RbdColorMappingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RbdColorMapping', function($stateParams, RbdColorMapping) {
                    return RbdColorMapping.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rbd-color-mapping',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rbd-color-mapping-detail.edit', {
            parent: 'rbd-color-mapping-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rbd-color-mapping/rbd-color-mapping-dialog.html',
                    controller: 'RbdColorMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RbdColorMapping', function(RbdColorMapping) {
                            return RbdColorMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rbd-color-mapping.new', {
            parent: 'rbd-color-mapping',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rbd-color-mapping/rbd-color-mapping-dialog.html',
                    controller: 'RbdColorMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                rbd: null,
                                color: null,
                                colorVal: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('rbd-color-mapping', null, { reload: 'rbd-color-mapping' });
                }, function() {
                    $state.go('rbd-color-mapping');
                });
            }]
        })
        .state('rbd-color-mapping.edit', {
            parent: 'rbd-color-mapping',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rbd-color-mapping/rbd-color-mapping-dialog.html',
                    controller: 'RbdColorMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RbdColorMapping', function(RbdColorMapping) {
                            return RbdColorMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rbd-color-mapping', null, { reload: 'rbd-color-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rbd-color-mapping.delete', {
            parent: 'rbd-color-mapping',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rbd-color-mapping/rbd-color-mapping-delete-dialog.html',
                    controller: 'RbdColorMappingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RbdColorMapping', function(RbdColorMapping) {
                            return RbdColorMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rbd-color-mapping', null, { reload: 'rbd-color-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
