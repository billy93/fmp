(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('atpco-master-fare-matrix', {
            parent: 'entity',
            url: '/atpco-master-fare-matrix?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AtpcoMasterFareMatrices'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atpco-master-fare-matrix/atpco-master-fare-matrices.html',
                    controller: 'AtpcoMasterFareMatrixController',
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
        .state('atpco-master-fare-matrix-detail', {
            parent: 'atpco-master-fare-matrix',
            url: '/atpco-master-fare-matrix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AtpcoMasterFareMatrix'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atpco-master-fare-matrix/atpco-master-fare-matrix-detail.html',
                    controller: 'AtpcoMasterFareMatrixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'AtpcoMasterFareMatrix', function($stateParams, AtpcoMasterFareMatrix) {
                    return AtpcoMasterFareMatrix.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'atpco-master-fare-matrix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('atpco-master-fare-matrix-detail.edit', {
            parent: 'atpco-master-fare-matrix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-fare-matrix/atpco-master-fare-matrix-dialog.html',
                    controller: 'AtpcoMasterFareMatrixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AtpcoMasterFareMatrix', function(AtpcoMasterFareMatrix) {
                            return AtpcoMasterFareMatrix.get({id : $stateParams.id}).$promise;
                        }],
                        fareTypes: ['AtpcoMasterFareType', function(AtpcoMasterFareType) {
                            return AtpcoMasterFareType.getAll().$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atpco-master-fare-matrix.new', {
            parent: 'atpco-master-fare-matrix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-fare-matrix/atpco-master-fare-matrix-dialog.html',
                    controller: 'AtpcoMasterFareMatrixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                cabinService: null,
                                cabinCode: null,
                                fareTypeCode: null,
                                id: null
                            };
                        },
                        fareTypes: ['AtpcoMasterFareType', function(AtpcoMasterFareType) {
                            return AtpcoMasterFareType.getAll().$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atpco-master-fare-matrix', null, { reload: 'atpco-master-fare-matrix' });
                }, function() {
                    $state.go('atpco-master-fare-matrix');
                });
            }]
        })
        .state('atpco-master-fare-matrix.edit', {
            parent: 'atpco-master-fare-matrix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-fare-matrix/atpco-master-fare-matrix-dialog.html',
                    controller: 'AtpcoMasterFareMatrixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AtpcoMasterFareMatrix', function(AtpcoMasterFareMatrix) {
                            return AtpcoMasterFareMatrix.get({id : $stateParams.id}).$promise;
                        }],
                        fareTypes: ['AtpcoMasterFareType', function(AtpcoMasterFareType) {
                            return AtpcoMasterFareType.getAll().$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atpco-master-fare-matrix', null, { reload: 'atpco-master-fare-matrix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atpco-master-fare-matrix.delete', {
            parent: 'atpco-master-fare-matrix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-fare-matrix/atpco-master-fare-matrix-delete-dialog.html',
                    controller: 'AtpcoMasterFareMatrixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AtpcoMasterFareMatrix', function(AtpcoMasterFareMatrix) {
                            return AtpcoMasterFareMatrix.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atpco-master-fare-matrix', null, { reload: 'atpco-master-fare-matrix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
