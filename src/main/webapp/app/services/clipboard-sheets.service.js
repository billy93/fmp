(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('ClipboardSheet', ClipboardSheet);

    ClipboardSheet.$inject = ['$resource'];

    function ClipboardSheet ($resource) {
        var resourceUrl =  'api/clipboard-sheets/:id';

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
            'copy': { method: 'PUT', url:'api/clipboard-sheets/copy' }
        });
    }
})();
