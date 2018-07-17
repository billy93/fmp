(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('InternetQuery', InternetQuery);

    InternetQuery.$inject = ['$resource'];

    function InternetQuery ($resource) {
        var resourceUrl =  'api/internet-query/:id';

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
            'exportQueue': {method:'POST', url:'api/internet-query/exportQueue'},
        });
    }
})();
