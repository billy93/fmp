(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('TariffNumberAddOn', TariffNumberAddOn);

    TariffNumberAddOn.$inject = ['$resource'];

    function TariffNumberAddOn ($resource) {
        var resourceUrl =  'api/tariff-number-add-ons/:id';

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
            'getAll': { method: 'GET', isArray: true, url:"api/tariff-number-add-ons/getAll"},
        });
    }
})();
