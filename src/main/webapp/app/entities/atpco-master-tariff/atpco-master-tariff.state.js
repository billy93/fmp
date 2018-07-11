(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('atpco-master-tariff', {
            parent: 'entity',
            url: '/atpco-master-tariff?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'TariffNumbers'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atpco-master-tariff/atpco-master-tariff.html',
                    controller: 'AtpcoMasterTariffController',
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
        .state('atpco-master-tariff-detail', {
            parent: 'atpco-master-tariff',
            url: '/atpco-master-tariff/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AtpcoMasterTariff'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atpco-master-tariff/atpco-master-tariff-detail.html',
                    controller: 'AtpcoMasterTariffDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'AtpcoMasterTariff', function($stateParams, AtpcoMasterTariff) {
                    return AtpcoMasterTariff.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'atpco-master-tariff',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('atpco-master-tariff-detail.edit', {
            parent: 'atpco-master-tariff-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-tariff/atpco-master-tariff-dialog.html',
                    controller: 'AtpcoMasterTariffDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AtpcoMasterTariff', function(AtpcoMasterTariff) {
                            return AtpcoMasterTariff.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atpco-master-tariff.new', {
            parent: 'atpco-master-tariff',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-tariff/atpco-master-tariff-dialog.html',
                    controller: 'AtpcoMasterTariffDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tarNo: null,
                                tarCd: null,
                                global: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('atpco-master-tariff', null, { reload: 'atpco-master-tariff' });
                }, function() {
                    $state.go('atpco-master-tariff');
                });
            }]
        })
        .state('atpco-master-tariff.edit', {
            parent: 'atpco-master-tariff',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-tariff/atpco-master-tariff-dialog.html',
                    controller: 'AtpcoMasterTariffDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AtpcoMasterTariff', function(AtpcoMasterTariff) {
                            return AtpcoMasterTariff.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atpco-master-tariff', null, { reload: 'atpco-master-tariff' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atpco-master-tariff.delete', {
            parent: 'atpco-master-tariff',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atpco-master-tariff/atpco-master-tariff-delete-dialog.html',
                    controller: 'AtpcoMasterTariffDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AtpcoMasterTariff', function(AtpcoMasterTariff) {
                            return AtpcoMasterTariff.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atpco-master-tariff', null, { reload: 'atpco-master-tariff' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
