(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('TaxQuery', TaxQuery);

    TaxQuery.$inject = ['$resource'];

    function TaxQuery ($resource) {
        var resourceUrl =  'api/tax-queries/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'POST'},
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
