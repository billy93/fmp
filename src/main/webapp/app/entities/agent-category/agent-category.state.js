(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('agent-category', {
            parent: 'entity',
            url: '/agent-category?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AgentCategories'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/agent-category/agent-categories.html',
                    controller: 'AgentCategoryController',
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
        .state('agent-category-detail', {
            parent: 'agent-category',
            url: '/agent-category/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AgentCategory'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/agent-category/agent-category-detail.html',
                    controller: 'AgentCategoryDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'AgentCategory', function($stateParams, AgentCategory) {
                    return AgentCategory.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'agent-category',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('agent-category-detail.edit', {
            parent: 'agent-category-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent-category/agent-category-dialog.html',
                    controller: 'AgentCategoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AgentCategory', function(AgentCategory) {
                            return AgentCategory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('agent-category.new', {
            parent: 'agent-category',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent-category/agent-category-dialog.html',
                    controller: 'AgentCategoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('agent-category', null, { reload: 'agent-category' });
                }, function() {
                    $state.go('agent-category');
                });
            }]
        })
        .state('agent-category.edit', {
            parent: 'agent-category',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent-category/agent-category-dialog.html',
                    controller: 'AgentCategoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AgentCategory', function(AgentCategory) {
                            return AgentCategory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('agent-category', null, { reload: 'agent-category' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('agent-category.delete', {
            parent: 'agent-category',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent-category/agent-category-delete-dialog.html',
                    controller: 'AgentCategoryDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AgentCategory', function(AgentCategory) {
                            return AgentCategory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('agent-category', null, { reload: 'agent-category' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
