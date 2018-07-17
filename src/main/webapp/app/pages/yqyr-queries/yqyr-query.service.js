(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('YqyrQuery', YqyrQuery);

    YqyrQuery.$inject = ['$resource'];

    function YqyrQuery ($resource) {
        var resourceUrl =  'api/yqyr-queries/:id';

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
            'update': { method:'PUT' }
        });
    }
})();
