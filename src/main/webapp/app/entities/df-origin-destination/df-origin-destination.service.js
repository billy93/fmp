(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('DFOriginDestination', DFOriginDestination);

    DFOriginDestination.$inject = ['$resource'];

    function DFOriginDestination ($resource) {
        var resourceUrl =  'api/df-origin-destinations/:id';

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
