(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('agent', {
            parent: 'entity',
            url: '/agent?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Agents'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/agent/agents.html',
                    controller: 'AgentController',
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
        .state('agent-detail', {
            parent: 'agent',
            url: '/agent/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Agent'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/agent/agent-detail.html',
                    controller: 'AgentDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Agent', function($stateParams, Agent) {
                    return Agent.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'agent',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('agent-detail.edit', {
            parent: 'agent-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent/agent-dialog.html',
                    controller: 'AgentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Agent', function(Agent) {
                            return Agent.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('agent.new', {
            parent: 'agent',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent/agent-dialog.html',
                    controller: 'AgentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                agentName: null,
                                agentType: null,
                                agentCategory: null,
                                posCity: null,
                                address: null,
                                telephone: null,
                                fax: null,
                                email: null,
                                contact: null,
                                iataCode: null,
                                isDeleted: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('agent', null, { reload: 'agent' });
                }, function() {
                    $state.go('agent');
                });
            }]
        })
        .state('agent.edit', {
            parent: 'agent',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent/agent-dialog.html',
                    controller: 'AgentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Agent', function(Agent) {
                            return Agent.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('agent', null, { reload: 'agent' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('agent.delete', {
            parent: 'agent',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent/agent-delete-dialog.html',
                    controller: 'AgentDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Agent', function(Agent) {
                            return Agent.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('agent', null, { reload: 'agent' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
