(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('DataFeedFareBasisGroupMapping', DataFeedFareBasisGroupMapping);

    DataFeedFareBasisGroupMapping.$inject = ['$resource'];

    function DataFeedFareBasisGroupMapping ($resource) {
        var resourceUrl =  'api/data-feed-fare-basis-group-mappings/:id';

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
