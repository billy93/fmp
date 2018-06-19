(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('data-feed-fare-basis-group-mapping', {
            parent: 'entity',
            url: '/data-feed-fare-basis-group-mapping',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DataFeedFareBasisGroupMappings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data-feed-fare-basis-group-mapping/data-feed-fare-basis-group-mappings.html',
                    controller: 'DataFeedFareBasisGroupMappingController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('data-feed-fare-basis-group-mapping-detail', {
            parent: 'data-feed-fare-basis-group-mapping',
            url: '/data-feed-fare-basis-group-mapping/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DataFeedFareBasisGroupMapping'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data-feed-fare-basis-group-mapping/data-feed-fare-basis-group-mapping-detail.html',
                    controller: 'DataFeedFareBasisGroupMappingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DataFeedFareBasisGroupMapping', function($stateParams, DataFeedFareBasisGroupMapping) {
                    return DataFeedFareBasisGroupMapping.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'data-feed-fare-basis-group-mapping',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('data-feed-fare-basis-group-mapping-detail.edit', {
            parent: 'data-feed-fare-basis-group-mapping-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-feed-fare-basis-group-mapping/data-feed-fare-basis-group-mapping-dialog.html',
                    controller: 'DataFeedFareBasisGroupMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataFeedFareBasisGroupMapping', function(DataFeedFareBasisGroupMapping) {
                            return DataFeedFareBasisGroupMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data-feed-fare-basis-group-mapping.new', {
            parent: 'data-feed-fare-basis-group-mapping',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-feed-fare-basis-group-mapping/data-feed-fare-basis-group-mapping-dialog.html',
                    controller: 'DataFeedFareBasisGroupMappingDialogController',
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
                    $state.go('data-feed-fare-basis-group-mapping', null, { reload: 'data-feed-fare-basis-group-mapping' });
                }, function() {
                    $state.go('data-feed-fare-basis-group-mapping');
                });
            }]
        })
        .state('data-feed-fare-basis-group-mapping.edit', {
            parent: 'data-feed-fare-basis-group-mapping',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-feed-fare-basis-group-mapping/data-feed-fare-basis-group-mapping-dialog.html',
                    controller: 'DataFeedFareBasisGroupMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataFeedFareBasisGroupMapping', function(DataFeedFareBasisGroupMapping) {
                            return DataFeedFareBasisGroupMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data-feed-fare-basis-group-mapping', null, { reload: 'data-feed-fare-basis-group-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data-feed-fare-basis-group-mapping.delete', {
            parent: 'data-feed-fare-basis-group-mapping',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-feed-fare-basis-group-mapping/data-feed-fare-basis-group-mapping-delete-dialog.html',
                    controller: 'DataFeedFareBasisGroupMappingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DataFeedFareBasisGroupMapping', function(DataFeedFareBasisGroupMapping) {
                            return DataFeedFareBasisGroupMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data-feed-fare-basis-group-mapping', null, { reload: 'data-feed-fare-basis-group-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
