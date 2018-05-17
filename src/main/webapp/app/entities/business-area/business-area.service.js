(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('BusinessArea', BusinessArea);

    BusinessArea.$inject = ['$resource'];

    function BusinessArea ($resource) {
        var resourceUrl =  'api/business-areas/:id';

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
            'queryAll': { method: 'GET', isArray: true, url:'api/business-areas/all' }
        });
    }
})();
