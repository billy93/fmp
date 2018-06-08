(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('competitor-monitoring', {
        	parent: 'app',
            url: '/competitor-monitoring',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Competitor Monitoring'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/competitor-monitoring/competitor-monitoring.html',
                    controller: 'CompetitorMonitoringController',
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
