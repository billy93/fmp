(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('RbdColorMapping', RbdColorMapping);

    RbdColorMapping.$inject = ['$resource'];

    function RbdColorMapping ($resource) {
        var resourceUrl =  'api/rbd-color-mappings/:id';

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
