(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('SpecifiedConstructed', SpecifiedConstructed);

    SpecifiedConstructed.$inject = ['$resource'];

    function SpecifiedConstructed ($resource) {
        var resourceUrl =  'api/afd-queries/:id';

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
            'update': { method:'PUT' },
            'getRules': { method: 'POST', url:'api/afd-queries/rules', isArray: true }
        });
    }
})();
