(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('AtpcoMasterFareType', AtpcoMasterFareType);

    AtpcoMasterFareType.$inject = ['$resource'];

    function AtpcoMasterFareType ($resource) {
        var resourceUrl =  'api/atpco-master-fare-types/:id';

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
            'getAll': { method:'GET', isArray: true, url:'api/atpco-master-fare-types/all'}
        });
    }
})();
