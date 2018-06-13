(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('city-group', {
            parent: 'entity',
            url: '/city-group?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CityGroups'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/city-group/city-groups.html',
                    controller: 'CityGroupController',
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
        .state('city-group-detail', {
            parent: 'city-group',
            url: '/city-group/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CityGroup'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/city-group/city-group-detail.html',
                    controller: 'CityGroupDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'CityGroup', function($stateParams, CityGroup) {
                    return CityGroup.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'city-group',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('city-group-detail.edit', {
            parent: 'city-group-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-group/city-group-dialog.html',
                    controller: 'CityGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CityGroup', function(CityGroup) {
                            return CityGroup.get({id : $stateParams.id}).$promise;
                        }],
                        cities: ['City', function(City) {
                            return City.getAll().$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('city-group.new', {
            parent: 'city-group',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-group/city-group-dialog.html',
                    controller: 'CityGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                code: null,
                                description: null,
                                id: null
                            };
                        },
                        cities: ['City', function(City) {
                            return City.getAll().$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('city-group', null, { reload: 'city-group' });
                }, function() {
                    $state.go('city-group');
                });
            }]
        })
        .state('city-group.edit', {
            parent: 'city-group',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-group/city-group-dialog.html',
                    controller: 'CityGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CityGroup', function(CityGroup) {
                            return CityGroup.get({id : $stateParams.id}).$promise;
                        }],
                        cities: ['City', function(City) {
                            return City.getAll().$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('city-group', null, { reload: 'city-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('city-group.delete', {
            parent: 'city-group',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-group/city-group-delete-dialog.html',
                    controller: 'CityGroupDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CityGroup', function(CityGroup) {
                            return CityGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('city-group', null, { reload: 'city-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
