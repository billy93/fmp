(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('review-level', {
            parent: 'entity',
            url: '/review-level?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fmpApp.reviewLevel.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/review-level/review-levels.html',
                    controller: 'ReviewLevelController',
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
                    $translatePartialLoader.addPart('reviewLevel');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('review-level-detail', {
            parent: 'review-level',
            url: '/review-level/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fmpApp.reviewLevel.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/review-level/review-level-detail.html',
                    controller: 'ReviewLevelDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('reviewLevel');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ReviewLevel', function($stateParams, ReviewLevel) {
                    return ReviewLevel.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'review-level',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('review-level-detail.edit', {
            parent: 'review-level-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/review-level/review-level-dialog.html',
                    controller: 'ReviewLevelDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ReviewLevel', function(ReviewLevel) {
                            return ReviewLevel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('review-level.new', {
            parent: 'review-level',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/review-level/review-level-dialog.html',
                    controller: 'ReviewLevelDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                activeStatus: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('review-level', null, { reload: 'review-level' });
                }, function() {
                    $state.go('review-level');
                });
            }]
        })
        .state('review-level.edit', {
            parent: 'review-level',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/review-level/review-level-dialog.html',
                    controller: 'ReviewLevelDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ReviewLevel', function(ReviewLevel) {
                            return ReviewLevel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('review-level', null, { reload: 'review-level' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('review-level.delete', {
            parent: 'review-level',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/review-level/review-level-delete-dialog.html',
                    controller: 'ReviewLevelDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ReviewLevel', function(ReviewLevel) {
                            return ReviewLevel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('review-level', null, { reload: 'review-level' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
