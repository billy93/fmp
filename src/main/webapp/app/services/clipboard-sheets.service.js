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
            'findByCurrentUsername': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                },
                url:'api/clipboard-sheets/findByCurrentUsername'
            },
            'copy': { method: 'PUT', url:'api/clipboard-sheets/copy' }
        });
    }
})();
