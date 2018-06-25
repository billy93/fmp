(function() {
	'use strict';
	angular.module('fmpApp').factory('PublishedFareCommissionRates',
			PublishedFareCommissionRates);

	PublishedFareCommissionRates.$inject = [ '$resource' ];

	function PublishedFareCommissionRates($resource) {
		var resourceUrl = 'api/published-fare-commission-rates/:id';

		return $resource(resourceUrl, {}, {
			'query' : {
				method : 'GET',
				isArray : true
			},
			'get' : {
				method : 'GET',
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'update' : {
				method : 'PUT'
			}
		});
	}
})();
