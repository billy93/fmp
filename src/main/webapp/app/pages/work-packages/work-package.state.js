(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('work-package', {
            parent: 'app',
            url: '/work-package?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Work Queue'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/work-packages/work-packages.html',
                    controller: 'WorkPackageController',
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
                }]
            }
        })
        
        .state('work-package-detail', {
            parent: 'work-package',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fmpApp.workPackage.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/work-packages/work-package-detail.html',
                    controller: 'WorkPackageDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'WorkPackage', function($stateParams, WorkPackage) {
                    return WorkPackage.get({id : $stateParams.id}).$promise;
                }],
                user: ['$stateParams', 'User', 'Principal', function($stateParams, User, Principal) {
                	return Principal.identity().then(function(account) {
                        return User.get({login : account.login}).$promise;
                    });
                }],
                tariffNumber: ['$stateParams', 'TariffNumber', 'Principal', function($stateParams, TariffNumber, Principal) {
                	return Principal.identity().then(function(account) {
                        return TariffNumber.getAll().$promise;
                    });
                }],
                cities: ['$stateParams', 'City', 'Principal', function($stateParams, City, Principal) {
                	return Principal.identity().then(function(account) {
                        return City.getAll().$promise;
                    });
                }],
                currencies: ['$stateParams', 'Currency', 'Principal', function($stateParams, Currency, Principal) {
                	return Principal.identity().then(function(account) {
                        return Currency.getAll().$promise;
                    });
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'work-package',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
//        .state('work-package-detail.edit', {
//            parent: 'work-package-detail',
//            url: '/detail/edit',
//            data: {
//                authorities: ['ROLE_USER']
//            },
//            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/entities/work-package/work-package-dialog.html',
//                    controller: 'WorkPackageDialogController',
//                    controllerAs: 'vm',
//                    backdrop: 'static',
//                    size: 'lg',
//                    resolve: {
//                        entity: ['WorkPackage', function(WorkPackage) {
//                            return WorkPackage.get({id : $stateParams.id}).$promise;
//                        }],
//                        reviewLevels: ['ReviewLevel', function(ReviewLevel) {
//                            return ReviewLevel.queryAll().$promise;
//                        }],
//                        businessAreas: ['BusinessArea', function(BusinessArea) {
//                            return BusinessArea.queryAll().$promise;
//                        }]
//                    }
//                }).result.then(function() {
//                    $state.go('^', {}, { reload: false });
//                }, function() {
//                    $state.go('^');
//                });
//            }]
//        })
//        .state('work-package.new', {
//            parent: 'work-package',
//            url: '/new?type',
//            data: {
//                authorities: ['ROLE_USER']
//            },
//            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/entities/work-package/work-package-dialog.html',
//                    controller: 'WorkPackageDialogController',
//                    controllerAs: 'vm',
//                    backdrop: 'static',
//                    size: 'lg',
//                    resolve: {
//                    	    type: function(){
//                    	    	return null;
//                    	    },
//                        entity: function () {
//                            return {
//                                status: null,
//                                wpid: null,
//                                name: null,
//                                priority: null,
//                                targetSub: null,
//                                fillingDate: null,
//                                distributionDate: null,
//                                discExpiryDate: null,
//                                fillingStatus: null,
//                                fillingError: null,
//                                qaStatus: null,
//                                queuedDate: null,
//                                lockedBy: null,
//                                lockedSince: null,
//                                type: null,
//                                id: null
//                            };
//                        },
//                        reviewLevels: ['ReviewLevel', function(ReviewLevel) {
//                            return ReviewLevel.queryAll().$promise;
//                        }],
//                        businessAreas: ['BusinessArea', function(BusinessArea) {
//                            return BusinessArea.queryAll().$promise;
//                        }]
//                    }
//                }).result.then(function(workPackage) {
//	                	var params = {
//	                			id: workPackage.id
//	                	};
//                  	$state.go('work-package-detail', params);
//                    //$state.go('work-package', null, { reload: 'work-package' });
//                }, function() {
//                    $state.go('work-package');
//                });
//            }]
//        })
//        .state('work-package.new.recommendation', {
//            parent: 'fares-recommendation',
//            url: '/new?type',
//            data: {
//                authorities: ['ROLE_USER']
//            },
//            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/entities/work-package/work-package-dialog.html',
//                    controller: 'WorkPackageDialogController',
//                    controllerAs: 'vm',
//                    backdrop: 'static',
//                    size: 'lg',
//                    resolve: {
//                        entity: function () {
//                            return {
//                                status: null,
//                                wpid: null,
//                                name: null,
//                                priority: null,
//                                targetSub: null,
//                                fillingDate: null,
//                                distributionDate: null,
//                                discExpiryDate: null,
//                                fillingStatus: null,
//                                fillingError: null,
//                                qaStatus: null,
//                                queuedDate: null,
//                                lockedBy: null,
//                                lockedSince: null,
//                                type: null,
//                                id: null
//                            };
//                        },
//                        reviewLevels: ['ReviewLevel', function(ReviewLevel) {
//                            return ReviewLevel.queryAll().$promise;
//                        }],
//                        businessAreas: ['BusinessArea', function(BusinessArea) {
//                            return BusinessArea.queryAll().$promise;
//                        }]
//                    }
//                }).result.then(function(workPackage) {
//	                	var params = {
//	                		workPackageId: workPackage.id
//	                	};
//                  	//$state.go('fares-recommendation', params);
//                    //$state.go('work-package', null, { reload: 'work-package' });
//                }, function() {
//                    //$state.go('work-package');
//                });
//            }]
//        })
//        .state('work-package.edit', {
//            parent: 'work-package',
//            url: '/{id}/edit',
//            data: {
//                authorities: ['ROLE_USER']
//            },
//            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/entities/work-package/work-package-dialog.html',
//                    controller: 'WorkPackageDialogController',
//                    controllerAs: 'vm',
//                    backdrop: 'static',
//                    size: 'lg',
//                    resolve: {
//                        entity: ['WorkPackage', function(WorkPackage) {
//                            return WorkPackage.get({id : $stateParams.id}).$promise;
//                        }],
//                        reviewLevels: ['ReviewLevel', function(ReviewLevel) {
//                            return ReviewLevel.queryAll().$promise;
//                        }],
//                        businessAreas: ['BusinessArea', function(BusinessArea) {
//                            return BusinessArea.queryAll().$promise;
//                        }]
//                    }
//                }).result.then(function() {
//                    $state.go('work-package', null, { reload: 'work-package' });
//                }, function() {
//                    $state.go('^');
//                });
//            }]
//        })
//        .state('work-package.delete', {
//            parent: 'work-package',
//            url: '/{id}/delete',
//            data: {
//                authorities: ['ROLE_USER']
//            },
//            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/entities/work-package/work-package-delete-dialog.html',
//                    controller: 'WorkPackageDeleteController',
//                    controllerAs: 'vm',
//                    size: 'md',
//                    resolve: {
//                        entity: ['WorkPackage', function(WorkPackage) {
//                            return WorkPackage.get({id : $stateParams.id}).$promise;
//                        }]
//                    }
//                }).result.then(function() {
//                    $state.go('work-package', null, { reload: 'work-package' });
//                }, function() {
//                    $state.go('^');
//                });
//            }]
//        })
        ;
    }

})();
