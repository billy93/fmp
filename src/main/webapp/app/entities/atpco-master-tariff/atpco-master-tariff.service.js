(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('AtpcoMasterTariff', AtpcoMasterTariff);

    AtpcoMasterTariff.$inject = ['$resource'];

    function AtpcoMasterTariff ($resource) {
        var resourceUrl =  'api/atpco-master-tariff/:id';

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
            'getAll': { method: 'GET', isArray: true, url:"api/atpco-master-tariff/getAll"},
            'getAllGlobal': { method: 'GET', isArray: true, url:"api/atpco-master-tariff/getAllGlobal"},
        });
    }
})();
