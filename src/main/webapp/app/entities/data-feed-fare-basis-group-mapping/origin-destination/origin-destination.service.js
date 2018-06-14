(function() {
	'use strict';
	angular.module('fmpApp').factory('OriginDestination', OriginDestination);

	OriginDestination.$inject = [ '$resource' ];

	function OriginDestination($resource) {
		var resourceUrl = 'api/origin-destination/:id';

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
