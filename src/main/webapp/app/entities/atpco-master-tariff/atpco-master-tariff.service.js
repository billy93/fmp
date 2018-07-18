(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('AtpcoMasterTariff', AtpcoMasterTariff);

    AtpcoMasterTariff.$inject = ['$resource'];

    function AtpcoMasterTariff ($resource) {
        var resourceUrl =  'api/atpcoMasterTariff/:id';

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
            'getAll': { method: 'GET', isArray: true, url:"api/atpcoMasterTariff/getAll"},
            'getAllGlobal': { method: 'GET', isArray: true, url:"api/atpcoMasterTariff/getAllGlobal"},
            'findByType' : { method: 'GET', isArray: true, url:"api/atpcoMasterTariff/findByType/:type"},
        });
    }
})();
