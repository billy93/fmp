(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('DFScheduler', DFScheduler);

    DFScheduler.$inject = ['$resource'];

    function DFScheduler ($resource) {
        var resourceUrl =  'api/data-feed-scheduler/';

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
