(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('Rbdquery', Rbdquery);

    Rbdquery.$inject = ['$resource'];

    function Rbdquery ($resource) {
        var resourceUrl =  'api/rbdqueries/:id';

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
            'getRbd': { method: 'GET', url:'api/rbdqueries/rbd', isArray: true }
        });
    }
})();
