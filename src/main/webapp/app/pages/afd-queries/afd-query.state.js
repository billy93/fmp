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
            url: '/afd-query',
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
            	tariffNumbers: ['TariffNumber', 'Principal', function(TariffNumber, Principal) {
                	return Principal.identity().then(function(account) {
                        return TariffNumber.getAll().$promise;
                    });
                }],
                cities: ['City', 'Principal', function(City, Principal) {
                	return Principal.identity().then(function(account) {
                        return City.getAll().$promise;
                    });
                }],
            }
        })
    }

})();
