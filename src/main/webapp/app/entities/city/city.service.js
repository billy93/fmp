(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('City', City);

    City.$inject = ['$resource'];

    function City ($resource) {
        var resourceUrl =  'api/cities/:id';

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
            'getAll': { method: 'GET', isArray: true, url:"api/cities/getAll"},
        });
    }
})();
