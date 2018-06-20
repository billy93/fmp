(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tariff-number-add-on', {
            parent: 'entity',
            url: '/tariff-number-add-on?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'TariffNumberAddOns'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tariff-number-add-on/tariff-number-add-ons.html',
                    controller: 'TariffNumberAddOnController',
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
        .state('tariff-number-add-on-detail', {
            parent: 'tariff-number-add-on',
            url: '/tariff-number-add-on/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'TariffNumberAddOn'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tariff-number-add-on/tariff-number-add-on-detail.html',
                    controller: 'TariffNumberAddOnDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'TariffNumberAddOn', function($stateParams, TariffNumberAddOn) {
                    return TariffNumberAddOn.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tariff-number-add-on',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tariff-number-add-on-detail.edit', {
            parent: 'tariff-number-add-on-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tariff-number-add-on/tariff-number-add-on-dialog.html',
                    controller: 'TariffNumberAddOnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TariffNumberAddOn', function(TariffNumberAddOn) {
                            return TariffNumberAddOn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tariff-number-add-on.new', {
            parent: 'tariff-number-add-on',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tariff-number-add-on/tariff-number-add-on-dialog.html',
                    controller: 'TariffNumberAddOnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tarNoAddOn: null,
                                tarCdAddOn: null,
                                globalAddOn: null,
                                descriptionAddOn: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tariff-number-add-on', null, { reload: 'tariff-number-add-on' });
                }, function() {
                    $state.go('tariff-number-add-on');
                });
            }]
        })
        .state('tariff-number-add-on.edit', {
            parent: 'tariff-number-add-on',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tariff-number-add-on/tariff-number-add-on-dialog.html',
                    controller: 'TariffNumberAddOnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TariffNumberAddOn', function(TariffNumberAddOn) {
                            return TariffNumberAddOn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tariff-number-add-on', null, { reload: 'tariff-number-add-on' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tariff-number-add-on.delete', {
            parent: 'tariff-number-add-on',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tariff-number-add-on/tariff-number-add-on-delete-dialog.html',
                    controller: 'TariffNumberAddOnDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TariffNumberAddOn', function(TariffNumberAddOn) {
                            return TariffNumberAddOn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tariff-number-add-on', null, { reload: 'tariff-number-add-on' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
