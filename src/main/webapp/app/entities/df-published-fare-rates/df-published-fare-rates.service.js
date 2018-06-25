(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('DFPublishedFareRates', DFPublishedFareRates);

    DFPublishedFareRates.$inject = ['$resource'];

    function DFPublishedFareRates ($resource) {
        var resourceUrl =  'api/df-published-fare-rates/:id';

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
