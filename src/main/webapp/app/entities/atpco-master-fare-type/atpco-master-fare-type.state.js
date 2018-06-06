(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('atpco-master-fare-type', {
            parent: 'entity',
            url: '/atpco-master-fare-type?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AtpcoMasterFareTypes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atpco-master-fare-type/atpco-master-fare-types.html',
                    controller: 'AtpcoMasterFareTypeController',
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
        .state('atpco-master-fare-type-detail', {
            parent: 'atpco-master-fare-type',
            url: '/atpco-master-fare-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AtpcoMasterFareType'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atpco-master-fare-type/atpco-master-fare-type-detail.html',
                    controller: 'AtpcoMasterFareTypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'AtpcoMasterFareType', function($stateParams, AtpcoMasterFareType) {
                    return AtpcoMasterFareType.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'atpco-master-fare-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('atpco-master-fare-type-detail.edit', {
            parent: 'atpco-master-fare-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-fare-type/atpco-master-fare-type-dialog.html',
                    controller: 'AtpcoMasterFareTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AtpcoMasterFareType', function(AtpcoMasterFareType) {
                            return AtpcoMasterFareType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atpco-master-fare-type.new', {
            parent: 'atpco-master-fare-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-fare-type/atpco-master-fare-type-dialog.html',
                    controller: 'AtpcoMasterFareTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                fareTypeDesignation: null,
                                typeCode: null,
                                definition: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('atpco-master-fare-type', null, { reload: 'atpco-master-fare-type' });
                }, function() {
                    $state.go('atpco-master-fare-type');
                });
            }]
        })
        .state('atpco-master-fare-type.edit', {
            parent: 'atpco-master-fare-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-fare-type/atpco-master-fare-type-dialog.html',
                    controller: 'AtpcoMasterFareTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AtpcoMasterFareType', function(AtpcoMasterFareType) {
                            return AtpcoMasterFareType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atpco-master-fare-type', null, { reload: 'atpco-master-fare-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atpco-master-fare-type.delete', {
            parent: 'atpco-master-fare-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-fare-type/atpco-master-fare-type-delete-dialog.html',
                    controller: 'AtpcoMasterFareTypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AtpcoMasterFareType', function(AtpcoMasterFareType) {
                            return AtpcoMasterFareType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atpco-master-fare-type', null, { reload: 'atpco-master-fare-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
