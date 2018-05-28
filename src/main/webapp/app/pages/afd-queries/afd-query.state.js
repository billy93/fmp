(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('afd-query', {
        	parent: 'app',
            url: '/afd-query?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AFD Query'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/afd-queries/afd-queries.html',
                    controller: 'AfdQueryController',
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
            	queryParams: [function() {
            		return {
                		carrier: null,
                		source: null,
                		publicPrivate: null,
                		tariff: null,
                		globalIndicator: null,
                		gaFareType: null,
                		fareType: null,
                		fareBasis: null,
                		origin: null,
                		destination: null,
                		owrt: null,
                		footnote: null,
                		ruleNo: null,
                		routingNo: null,
                		woId: null,
                		effectiveDateFrom: null,
                		effectiveDateTo: null,
                		effectiveDateOption: null,
                		saleDateFrom: null,
                		saleDateTo: null,
                		saleDateOption: null,
                		travelDateFrom: null,
                		travelDateTo: null,
                		travelDateOption: null,
                		seasonDateFrom: null,
                		seasonDateTo: null,
                		seasonDateOption: null,
                		amountRange: null,
                		tourCode: null,
                		paxType: null,
                		cabin: null,
                		bookingClass: null,
                		advancePurchase: null,
                		minStay: null,
                		maxStay: null,
                		includeConstructed: false,
                		appendResults: false,
                		biDirectional: false,
                		calculateTfc: false
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
    }

})();
