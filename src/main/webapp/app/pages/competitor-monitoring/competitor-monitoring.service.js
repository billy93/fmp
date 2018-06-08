(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('CompetitorMonitoring', CompetitorMonitoring);

    CompetitorMonitoring.$inject = ['$resource'];

    function CompetitorMonitoring ($resource) {
        var resourceUrl =  'api/competitor-monitoring/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'POST', isArray: true},
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
            'getRules': { method: 'GET', url:'api/competitor-monitoring/rules', isArray: true }
        });
    }
})();
