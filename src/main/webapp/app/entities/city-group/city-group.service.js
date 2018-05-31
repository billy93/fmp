(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('CityGroup', CityGroup);

    CityGroup.$inject = ['$resource'];

    function CityGroup ($resource) {
        var resourceUrl =  'api/city-groups/:id';

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
            'getAll': { method: 'GET', isArray: true, url:"api/city-groups/getAll"}
        });
    }
})();
