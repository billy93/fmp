(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('agent-groups', {
            parent: 'entity',
            url: '/agent-groups?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AgentGroups'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/agent-groups/agent-groups.html',
                    controller: 'AgentGroupsController',
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
        .state('agent-groups-detail', {
            parent: 'agent-groups',
            url: '/agent-groups/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AgentGroups'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/agent-groups/agent-groups-detail.html',
                    controller: 'AgentGroupsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'AgentGroups', function($stateParams, AgentGroups) {
                    return AgentGroups.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'agent-groups',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('agent-groups-detail.edit', {
            parent: 'agent-groups-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent-groups/agent-groups-dialog.html',
                    controller: 'AgentGroupsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AgentGroups', function(AgentGroups) {
                            return AgentGroups.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('agent-groups.new', {
            parent: 'agent-groups',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent-groups/agent-groups-dialog.html',
                    controller: 'AgentGroupsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                agencyGroupName: null,
                                agentName: null,
                                id: null
                            };
                        },
                        agencies: ['Agent', function(Agent) {
                            return Agent.queryAll().$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('agent-groups', null, { reload: 'agent-groups' });
                }, function() {
                    $state.go('agent-groups');
                });
            }]
        })
        .state('agent-groups.edit', {
            parent: 'agent-groups',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent-groups/agent-groups-dialog.html',
                    controller: 'AgentGroupsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AgentGroups', function(AgentGroups) {
                            return AgentGroups.get({id : $stateParams.id}).$promise;
                        }],
                        agencies: ['Agent', function(Agent) {
                            return Agent.queryAll().$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('agent-groups', null, { reload: 'agent-groups' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('agent-groups.delete', {
            parent: 'agent-groups',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agent-groups/agent-groups-delete-dialog.html',
                    controller: 'AgentGroupsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AgentGroups', function(AgentGroups) {
                            return AgentGroups.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('agent-groups', null, { reload: 'agent-groups' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
