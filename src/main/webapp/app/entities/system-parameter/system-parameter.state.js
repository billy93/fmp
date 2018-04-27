(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('system-parameter', {
            parent: 'entity',
            url: '/system-parameter?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'SystemParameters'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/system-parameter/system-parameters.html',
                    controller: 'SystemParameterController',
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
        .state('system-parameter-detail', {
            parent: 'system-parameter',
            url: '/system-parameter/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'SystemParameter'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/system-parameter/system-parameter-detail.html',
                    controller: 'SystemParameterDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'SystemParameter', function($stateParams, SystemParameter) {
                    return SystemParameter.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'system-parameter',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('system-parameter-detail.edit', {
            parent: 'system-parameter-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/system-parameter/system-parameter-dialog.html',
                    controller: 'SystemParameterDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SystemParameter', function(SystemParameter) {
                            return SystemParameter.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('system-parameter.new', {
            parent: 'system-parameter',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/system-parameter/system-parameter-dialog.html',
                    controller: 'SystemParameterDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                value: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('system-parameter', null, { reload: 'system-parameter' });
                }, function() {
                    $state.go('system-parameter');
                });
            }]
        })
        .state('system-parameter.edit', {
            parent: 'system-parameter',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/system-parameter/system-parameter-dialog.html',
                    controller: 'SystemParameterDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SystemParameter', function(SystemParameter) {
                            return SystemParameter.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('system-parameter', null, { reload: 'system-parameter' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('system-parameter.delete', {
            parent: 'system-parameter',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/system-parameter/system-parameter-delete-dialog.html',
                    controller: 'SystemParameterDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['SystemParameter', function(SystemParameter) {
                            return SystemParameter.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('system-parameter', null, { reload: 'system-parameter' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
