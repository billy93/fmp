(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('AtpcoMasterFareMatrix', AtpcoMasterFareMatrix);

    AtpcoMasterFareMatrix.$inject = ['$resource'];

    function AtpcoMasterFareMatrix ($resource) {
        var resourceUrl =  'api/atpco-master-fare-matrices/:id';

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
