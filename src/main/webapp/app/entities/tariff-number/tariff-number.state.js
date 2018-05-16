(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tariff-number', {
            parent: 'entity',
            url: '/tariff-number?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'TariffNumbers'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tariff-number/tariff-numbers.html',
                    controller: 'TariffNumberController',
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
        .state('tariff-number-detail', {
            parent: 'tariff-number',
            url: '/tariff-number/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'TariffNumber'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tariff-number/tariff-number-detail.html',
                    controller: 'TariffNumberDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'TariffNumber', function($stateParams, TariffNumber) {
                    return TariffNumber.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tariff-number',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tariff-number-detail.edit', {
            parent: 'tariff-number-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tariff-number/tariff-number-dialog.html',
                    controller: 'TariffNumberDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TariffNumber', function(TariffNumber) {
                            return TariffNumber.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tariff-number.new', {
            parent: 'tariff-number',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tariff-number/tariff-number-dialog.html',
                    controller: 'TariffNumberDialogController',
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
                    $state.go('tariff-number', null, { reload: 'tariff-number' });
                }, function() {
                    $state.go('tariff-number');
                });
            }]
        })
        .state('tariff-number.edit', {
            parent: 'tariff-number',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tariff-number/tariff-number-dialog.html',
                    controller: 'TariffNumberDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TariffNumber', function(TariffNumber) {
                            return TariffNumber.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tariff-number', null, { reload: 'tariff-number' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tariff-number.delete', {
            parent: 'tariff-number',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tariff-number/tariff-number-delete-dialog.html',
                    controller: 'TariffNumberDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TariffNumber', function(TariffNumber) {
                            return TariffNumber.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tariff-number', null, { reload: 'tariff-number' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
