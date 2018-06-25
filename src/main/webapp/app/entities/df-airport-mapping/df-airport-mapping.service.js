(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('DFAirportMapping', DFAirportMapping);

    DFAirportMapping.$inject = ['$resource'];

    function DFAirportMapping ($resource) {
        var resourceUrl =  'api/df-airport-mappings/:id';

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
            'update': { method:'PUT' }
        });
    }
})();
