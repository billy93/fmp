(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('DFFareBasisGroupMapping', DFFareBasisGroupMapping);

    DFFareBasisGroupMapping.$inject = ['$resource'];

    function DFFareBasisGroupMapping ($resource) {
        var resourceUrl =  'api/df-fare-basis-group-mappings/:id';

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
