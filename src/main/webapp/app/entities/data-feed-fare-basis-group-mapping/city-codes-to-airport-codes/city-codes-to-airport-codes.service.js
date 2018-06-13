(function() {
	'use strict';
	angular.module('fmpApp').factory('CityCodestoAirportCodes',
			CityCodestoAirportCodes);

	CityCodestoAirportCodes.$inject = [ '$resource' ];

	function CityCodestoAirportCodes($resource) {
		var resourceUrl = 'api/city-codes-to-airport-codes/:id';

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
