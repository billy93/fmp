(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('RbdMapping', RbdMapping);

    RbdMapping.$inject = ['$resource'];

    function RbdMapping ($resource) {
        var resourceUrl =  'api/rbd-mappings/:id';

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
            'getByCarriers' : { method: 'POST', isArray: true, url:'api/rbd-mappings/getByCarriers' }
        });
    }
})();
