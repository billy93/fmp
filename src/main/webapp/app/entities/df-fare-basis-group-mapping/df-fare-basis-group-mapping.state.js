(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('df-fare-basis-group-mapping', {
            parent: 'entity',
            url: '/df-fare-basis-group-mapping?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DFFareBasisGroupMappings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/df-fare-basis-group-mapping/df-fare-basis-group-mappings.html',
                    controller: 'DFFareBasisGroupMappingController',
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
        .state('df-fare-basis-group-mapping-detail', {
            parent: 'df-fare-basis-group-mapping',
            url: '/df-fare-basis-group-mapping/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DFFareBasisGroupMapping'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/df-fare-basis-group-mapping/df-fare-basis-group-mapping-detail.html',
                    controller: 'DFFareBasisGroupMappingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DFFareBasisGroupMapping', function($stateParams, DFFareBasisGroupMapping) {
                    return DFFareBasisGroupMapping.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'df-fare-basis-group-mapping',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('df-fare-basis-group-mapping-detail.edit', {
            parent: 'df-fare-basis-group-mapping-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-fare-basis-group-mapping/df-fare-basis-group-mapping-dialog.html',
                    controller: 'DFFareBasisGroupMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DFFareBasisGroupMapping', function(DFFareBasisGroupMapping) {
                            return DFFareBasisGroupMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('df-fare-basis-group-mapping.new', {
            parent: 'df-fare-basis-group-mapping',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-fare-basis-group-mapping/df-fare-basis-group-mapping-dialog.html',
                    controller: 'DFFareBasisGroupMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                originCity: null,
                                originCountry: null,
                                destinationCity: null,
                                destinationCountry: null,
                                rbd: null,
                                fareBasisCode: null,
                                fareBasisGroup: null,
                                priority: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('df-fare-basis-group-mapping', null, { reload: 'df-fare-basis-group-mapping' });
                }, function() {
                    $state.go('df-fare-basis-group-mapping');
                });
            }]
        })
        .state('df-fare-basis-group-mapping.edit', {
            parent: 'df-fare-basis-group-mapping',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-fare-basis-group-mapping/df-fare-basis-group-mapping-dialog.html',
                    controller: 'DFFareBasisGroupMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DFFareBasisGroupMapping', function(DFFareBasisGroupMapping) {
                            return DFFareBasisGroupMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('df-fare-basis-group-mapping', null, { reload: 'df-fare-basis-group-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('df-fare-basis-group-mapping.delete', {
            parent: 'df-fare-basis-group-mapping',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-fare-basis-group-mapping/df-fare-basis-group-mapping-delete-dialog.html',
                    controller: 'DFFareBasisGroupMappingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DFFareBasisGroupMapping', function(DFFareBasisGroupMapping) {
                            return DFFareBasisGroupMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('df-fare-basis-group-mapping', null, { reload: 'df-fare-basis-group-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
