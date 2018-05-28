(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('MasterTariffDomestic', MasterTariffDomestic);

    MasterTariffDomestic.$inject = ['$resource'];

    function MasterTariffDomestic ($resource) {
        var resourceUrl =  'api/master-tariffs-domestic/:id';

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
