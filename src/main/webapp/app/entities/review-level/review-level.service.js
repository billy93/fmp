(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('ReviewLevel', ReviewLevel);

    ReviewLevel.$inject = ['$resource'];

    function ReviewLevel ($resource) {
        var resourceUrl =  'api/review-levels/:id';

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
            'queryAll': { method: 'GET', isArray: true, url:'api/review-levels/all' }
        });
    }
})();
