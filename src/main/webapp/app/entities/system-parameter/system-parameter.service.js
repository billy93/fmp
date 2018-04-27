(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('SystemParameter', SystemParameter);

    SystemParameter.$inject = ['$resource'];

    function SystemParameter ($resource) {
        var resourceUrl =  'api/system-parameters/:id';

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
            'getPasswordParameter': { method: 'GET', isArray: true, url: 'api/system-parameters/passwords' }
        });
    }
})();
