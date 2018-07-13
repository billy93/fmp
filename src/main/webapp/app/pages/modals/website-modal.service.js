(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('MasterWebsite', MasterWebsite);

    MasterWebsite.$inject = ['$resource'];

    function MasterWebsite ($resource) {
        var resourceUrl =  'api/master-website/:id';

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
            'getAll': { method: 'GET', isArray: true, url:"api/master-website/getAll"},
        });
    }
})();
