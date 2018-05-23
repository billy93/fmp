(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('FareType', FareType);

    FareType.$inject = ['$resource'];

    function FareType ($resource) {
        var resourceUrl =  'api/fare-types/:id';

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
            'getAll': { method: 'GET', isArray: true, url:"api/fare-types/getAll"},
        });
    }
})();
