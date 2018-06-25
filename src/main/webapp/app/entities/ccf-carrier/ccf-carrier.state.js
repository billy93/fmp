(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ccf-carrier', {
            parent: 'entity',
            url: '/ccf-carrier?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CcfCarriers'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ccf-carrier/ccf-carriers.html',
                    controller: 'CcfCarrierController',
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
        .state('ccf-carrier-detail', {
            parent: 'ccf-carrier',
            url: '/ccf-carrier/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CcfCarrier'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ccf-carrier/ccf-carrier-detail.html',
                    controller: 'CcfCarrierDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'CcfCarrier', function($stateParams, CcfCarrier) {
                    return CcfCarrier.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ccf-carrier',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ccf-carrier-detail.edit', {
            parent: 'ccf-carrier-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ccf-carrier/ccf-carrier-dialog.html',
                    controller: 'CcfCarrierDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CcfCarrier', function(CcfCarrier) {
                            return CcfCarrier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ccf-carrier.new', {
            parent: 'ccf-carrier',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ccf-carrier/ccf-carrier-dialog.html',
                    controller: 'CcfCarrierDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                svcTagsPsgdom: null,
                                filler: null,
                                svcTagsCardom: null,
                                cxrCode: null,
                                recType: null,
                                batchNumber: null,
                                svcTagsPsgint: null,
                                batchDate: null,
                                svgTagsCarint: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('ccf-carrier', null, { reload: 'ccf-carrier' });
                }, function() {
                    $state.go('ccf-carrier');
                });
            }]
        })
        .state('ccf-carrier.edit', {
            parent: 'ccf-carrier',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ccf-carrier/ccf-carrier-dialog.html',
                    controller: 'CcfCarrierDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CcfCarrier', function(CcfCarrier) {
                            return CcfCarrier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ccf-carrier', null, { reload: 'ccf-carrier' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ccf-carrier.delete', {
            parent: 'ccf-carrier',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ccf-carrier/ccf-carrier-delete-dialog.html',
                    controller: 'CcfCarrierDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CcfCarrier', function(CcfCarrier) {
                            return CcfCarrier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ccf-carrier', null, { reload: 'ccf-carrier' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
