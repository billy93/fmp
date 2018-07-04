(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('ExchangeRatesQuery', ExchangeRatesQuery);

    ExchangeRatesQuery.$inject = ['$resource'];

    function ExchangeRatesQuery ($resource) {
        var resourceUrl =  'api/exchange-rates-query/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'POST', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },        
        });
    }
})();
