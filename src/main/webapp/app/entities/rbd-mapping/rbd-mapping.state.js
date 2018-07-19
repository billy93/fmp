(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rbd-mapping', {
            parent: 'entity',
            url: '/rbd-mapping?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RbdMappings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rbd-mapping/rbd-mappings.html',
                    controller: 'RbdMappingController',
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
        .state('rbd-mapping-detail', {
            parent: 'rbd-mapping',
            url: '/rbd-mapping/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RbdMapping'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rbd-mapping/rbd-mapping-detail.html',
                    controller: 'RbdMappingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RbdMapping', function($stateParams, RbdMapping) {
                    return RbdMapping.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rbd-mapping',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rbd-mapping-detail.edit', {
            parent: 'rbd-mapping-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rbd-mapping/rbd-mapping-dialog.html',
                    controller: 'RbdMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RbdMapping', function(RbdMapping) {
                            return RbdMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rbd-mapping.new', {
            parent: 'rbd-mapping',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rbd-mapping/rbd-mapping-dialog.html',
                    controller: 'RbdMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                oalCxr: null,
                                oalCabin: null,
                                ownCabin: null,
                                oalRbd: null,
                                ownRbd: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('rbd-mapping', null, { reload: 'rbd-mapping' });
                }, function() {
                    $state.go('rbd-mapping');
                });
            }]
        })
        .state('rbd-mapping.edit', {
            parent: 'rbd-mapping',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rbd-mapping/rbd-mapping-dialog.html',
                    controller: 'RbdMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RbdMapping', function(RbdMapping) {
                            return RbdMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rbd-mapping', null, { reload: 'rbd-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rbd-mapping.delete', {
            parent: 'rbd-mapping',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rbd-mapping/rbd-mapping-delete-dialog.html',
                    controller: 'RbdMappingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RbdMapping', function(RbdMapping) {
                            return RbdMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rbd-mapping', null, { reload: 'rbd-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
