(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	/*
        $stateProvider
        .state('report', {
            parent: 'app',
            url: '/rbdquery?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Rbdqueries'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/rbd-query/rbd-queries.html',
                    controller: 'RbdqueryController',
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
            	params: [function() {
            		return {
            			cxr: null,
                		ruleTarNo: null,
                		ruleNo: null,
                		effectiveDateFrom: null,
                		chart1: null,
                		chart2: null
                	}
            	}],
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
        .state('rbdquery-detail', {
            parent: 'rbdquery',
            url: '/rbdquery/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Rbdquery'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/rbd-query/rbd-query-detail.html',
                    controller: 'RbdqueryDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Rbdquery', function($stateParams, Rbdquery) {
                    return Rbdquery.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rbdquery',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rbdquery-detail.edit', {
            parent: 'rbdquery-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/pages/rbd-query/rbd-query-dialog.html',
                    controller: 'RbdqueryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Rbdquery', function(Rbdquery) {
                            return Rbdquery.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rbdquery.new', {
            parent: 'rbdquery',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/pages/rbd-query/rbd-query-dialog.html',
                    controller: 'RbdqueryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                recordBatch: null,
                                recordSequence: null,
                                recType: null,
                                action: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('rbdquery', null, { reload: 'rbdquery' });
                }, function() {
                    $state.go('rbdquery');
                });
            }]
        })
        .state('rbdquery.edit', {
            parent: 'rbdquery',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/pages/rbd-query/rbd-query-dialog.html',
                    controller: 'RbdqueryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Rbdquery', function(Rbdquery) {
                            return Rbdquery.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rbdquery', null, { reload: 'rbdquery' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rbdquery.delete', {
            parent: 'rbdquery',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/pages/rbd-query/rbd-query-delete-dialog.html',
                    controller: 'RbdqueryDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Rbdquery', function(Rbdquery) {
                            return Rbdquery.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rbdquery', null, { reload: 'rbdquery' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
        */
    }

})();
