(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('TariffNumber', TariffNumber);

    TariffNumber.$inject = ['$resource'];

    function TariffNumber ($resource) {
        var resourceUrl =  'api/tariff-numbers/:id';

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
