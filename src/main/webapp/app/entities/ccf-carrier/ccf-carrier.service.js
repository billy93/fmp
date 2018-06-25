(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('CcfCarrier', CcfCarrier);

    CcfCarrier.$inject = ['$resource'];

    function CcfCarrier ($resource) {
        var resourceUrl =  'api/ccf-carriers/:id';

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
            'getAll': { method: 'GET', isArray: true, url:"api/ccf-carriers/getAll"},
        });
    }
})();
