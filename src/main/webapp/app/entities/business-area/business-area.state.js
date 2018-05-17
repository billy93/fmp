(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('business-area', {
            parent: 'entity',
            url: '/business-area?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fmpApp.businessArea.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/business-area/business-areas.html',
                    controller: 'BusinessAreaController',
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
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('businessArea');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('business-area-detail', {
            parent: 'business-area',
            url: '/business-area/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fmpApp.businessArea.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/business-area/business-area-detail.html',
                    controller: 'BusinessAreaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('businessArea');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'BusinessArea', function($stateParams, BusinessArea) {
                    return BusinessArea.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'business-area',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('business-area-detail.edit', {
            parent: 'business-area-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/business-area/business-area-dialog.html',
                    controller: 'BusinessAreaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BusinessArea', function(BusinessArea) {
                            return BusinessArea.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('business-area.new', {
            parent: 'business-area',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/business-area/business-area-dialog.html',
                    controller: 'BusinessAreaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                pic: null,
                                contactNumber: null,
                                activeStatus: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('business-area', null, { reload: 'business-area' });
                }, function() {
                    $state.go('business-area');
                });
            }]
        })
        .state('business-area.edit', {
            parent: 'business-area',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/business-area/business-area-dialog.html',
                    controller: 'BusinessAreaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BusinessArea', function(BusinessArea) {
                            return BusinessArea.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('business-area', null, { reload: 'business-area' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('business-area.delete', {
            parent: 'business-area',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/business-area/business-area-delete-dialog.html',
                    controller: 'BusinessAreaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['BusinessArea', function(BusinessArea) {
                            return BusinessArea.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('business-area', null, { reload: 'business-area' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
