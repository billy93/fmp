(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('Clipboard', Clipboard);

    Clipboard.$inject = ['$resource'];

    function Clipboard ($resource) {
        var resourceUrl =  'api/clipboards/:id';

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
            'copy': { method: 'PUT', url:'api/clipboards/copy' }
        });
    }
})();
