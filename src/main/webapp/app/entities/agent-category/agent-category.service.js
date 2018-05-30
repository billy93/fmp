(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('AgentCategory', AgentCategory);

    AgentCategory.$inject = ['$resource'];

    function AgentCategory ($resource) {
        var resourceUrl =  'api/agent-categories/:id';

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
            'queryAll': { method: 'GET', isArray: true, url:'api/agent-categories/all' },
        });
    }
})();
