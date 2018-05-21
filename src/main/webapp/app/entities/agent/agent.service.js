(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('Agent', Agent);

    Agent.$inject = ['$resource'];

    function Agent ($resource) {
        var resourceUrl =  'api/agents/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },
            'queryAll': { method: 'GET', isArray: true, url:'api/agencies/all' },
	        'exportAgent': { method: 'POST',  url:'api/agencies/export-agencies'},
	        'importAgent': { method: 'POST',  url:'api/agencies/import-agencies'}
        });
    }
})();
