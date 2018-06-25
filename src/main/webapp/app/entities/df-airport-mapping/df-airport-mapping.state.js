(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('df-airport-mapping', {
            parent: 'entity',
            url: '/df-airport-mapping?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DFAirportMappings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/df-airport-mapping/df-airport-mappings.html',
                    controller: 'DFAirportMappingController',
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
        .state('df-airport-mapping-detail', {
            parent: 'df-airport-mapping',
            url: '/df-airport-mapping/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DFAirportMapping'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/df-airport-mapping/df-airport-mapping-detail.html',
                    controller: 'DFAirportMappingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DFAirportMapping', function($stateParams, DFAirportMapping) {
                    return DFAirportMapping.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'df-airport-mapping',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('df-airport-mapping-detail.edit', {
            parent: 'df-airport-mapping-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-airport-mapping/df-airport-mapping-dialog.html',
                    controller: 'DFAirportMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DFAirportMapping', function(DFAirportMapping) {
                            return DFAirportMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('df-airport-mapping.new', {
            parent: 'df-airport-mapping',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-airport-mapping/df-airport-mapping-dialog.html',
                    controller: 'DFAirportMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                cityCode: null,
                                airportCode: null,
                                countryCode: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('df-airport-mapping', null, { reload: 'df-airport-mapping' });
                }, function() {
                    $state.go('df-airport-mapping');
                });
            }]
        })
        .state('df-airport-mapping.edit', {
            parent: 'df-airport-mapping',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-airport-mapping/df-airport-mapping-dialog.html',
                    controller: 'DFAirportMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DFAirportMapping', function(DFAirportMapping) {
                            return DFAirportMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('df-airport-mapping', null, { reload: 'df-airport-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('df-airport-mapping.delete', {
            parent: 'df-airport-mapping',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/df-airport-mapping/df-airport-mapping-delete-dialog.html',
                    controller: 'DFAirportMappingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DFAirportMapping', function(DFAirportMapping) {
                            return DFAirportMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('df-airport-mapping', null, { reload: 'df-airport-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
