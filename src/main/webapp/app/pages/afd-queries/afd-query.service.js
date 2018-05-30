(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('AfdQuery', AfdQuery);

    AfdQuery.$inject = ['$resource'];

    function AfdQuery ($resource) {
        var resourceUrl =  'api/afd-queries/:id';

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
            'update': { method:'PUT' },
            'getRules': { method: 'GET', url:'api/afd-queries/rules', isArray: true }
        });
    }
})();
