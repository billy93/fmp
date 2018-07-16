(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('work-package-query', {
            parent: 'app',
            url: '/work-package-query?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Work Queue'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/work-packages-query/work-packages-query.html',
                    controller: 'WorkPackageQueryController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'queued_date,desc',
                    squash: true
                },
                size: null,
                workPackageFilter: null
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
                user: ['$stateParams', 'User', 'Principal', function($stateParams, User, Principal) {
                	return Principal.identity().then(function(account) {
                        return User.get({login : account.login}).$promise;
                    });
                }],
                businessAreas: ['BusinessArea', function(BusinessArea) {
                    return BusinessArea.queryAll().$promise;
                }],
                creator: ['User', function(User) {
                    return User.queryAll().$promise;
                }]
            }
        })

        .state('work-package-query-detail', {
            parent: 'work-package-query',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Work Package'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/work-packages/work-package-detail-query.html',
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
                tariffNumberAddOn: ['$stateParams', 'TariffNumberAddOn', 'Principal', function($stateParams, TariffNumberAddOn, Principal) {
                	return Principal.identity().then(function(account) {
                        return TariffNumberAddOn.getAll().$promise;
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
                fareTypes: ['FareType', function(FareType) {
                    return FareType.getAll().$promise;
                }],
                businessAreas: ['User', function(User) {
                    return User.getBusinessArea().$promise;
                }],
                priorities: ['Priority', function(Priority) {
                    return Priority.getAll().$promise;
                }],
                passengers: ['Passenger', function(Passenger) {
                    return Passenger.getAll().$promise;
                }],
                states: ['State', function(State) {
                    return State.getAll().$promise;
                }],
                cityGroups: ['CityGroup', function(CityGroup) {
                    return CityGroup.getAll().$promise;
                }],
                atpcoFareTypes: ['AtpcoMasterFareType', function(AtpcoMasterFareType) {
                    return AtpcoMasterFareType.getAll().$promise;
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

        
        .state('work-package-query.export', {
            parent: 'work-package-query',
            url: '/export',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/pages/work-packages/work-package-export-dialog.html',
                    controller: 'WorkPackageExportDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                    }
                }).result.then(function(workPackage) {
	                	var params = {
	                			id: workPackage.id
	                	};
                  	$state.go('work-package-detail', params);
                    //$state.go('work-package', null, { reload: 'work-package' });
                }, function() {
                    $state.go('work-package');
                });
            }]
        })
        ;
    }

})();
