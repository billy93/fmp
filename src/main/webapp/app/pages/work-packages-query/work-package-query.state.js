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
            url: '/work-package-query',
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
