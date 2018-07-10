(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('FareClassQuery', FareClassQuery);

    FareClassQuery.$inject = ['$resource'];

    function FareClassQuery ($resource) {
        var resourceUrl =  'api/fare-class-query/:id';

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
            'getFareClassGroups': { method: 'GET', url:'api/fare-class-query/groups', isArray: true },
            'getFareClassText': { method: 'GET', url:'api/fare-class-query/text', isArray: true },
            'getFareClassConstructionDetails': { method: 'GET', url:'api/fare-class-query/construction-details', isArray: true },
        });
    }
})();
