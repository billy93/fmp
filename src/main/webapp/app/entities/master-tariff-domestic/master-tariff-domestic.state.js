(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('master-tariff-domestic', {
            parent: 'entity',
            url: '/master-tariff-domestic?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'MasterTariffsDomestic'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/master-tariff-domestic/master-tariffs-domestic.html',
                    controller: 'MasterTariffDomesticController',
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
        .state('master-tariff-domestic-detail', {
            parent: 'master-tariff-domestic',
            url: '/master-tariff-domestic/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'MasterTariffDomestic'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/master-tariff-domestic/master-tariff-domestic-detail.html',
                    controller: 'MasterTariffDomestic',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'MasterTariffDomestic', function($stateParams, MasterTariffDomestic) {
                    return MasterTariffDomestic.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'master-tariff-domestic',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('master-tariff-domestic-detail.edit', {
            parent: 'master-tariff-domestic-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/master-tariff-domestic/master-tariff-domestic-dialog.html',
                    controller: 'MasterTariffDomesticDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MasterTariffDomestic', function(MasterTariffDomestic) {
                            return MasterTariffDomestic.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('master-tariff-domestic.new', {
            parent: 'master-tariff-domestic',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/master-tariff-domestic/master-tariff-domestic-dialog.html',
                    controller: 'MasterTariffDomesticDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                gfsGlobalArea: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('master-tariff-domestic', null, { reload: 'master-tariff-domestic' });
                }, function() {
                    $state.go('master-tariff-domestic');
                });
            }]
        })
        .state('master-tariff-domestic.edit', {
            parent: 'master-tariff-domestic',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/master-tariff-domestic/master-tariff-domestic-dialog.html',
                    controller: 'MasterTariffDomesticDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MasterTariffDomestic', function(MasterTariffDomestic) {
                            return MasterTariffDomestic.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('master-tariff-domestic', null, { reload: 'master-tariff-domestic' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('master-tariff-domestic.delete', {
            parent: 'master-tariff-domestic',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/master-tariff-domestic/master-tariff-domestic-delete-dialog.html',
                    controller: 'MasterTariffDomesticDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['MasterTariffDomestic', function(MasterTariffDomestic) {
                            return MasterTariffDomestic.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('master-tariff-domestic', null, { reload: 'master-tariff-domestic' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
