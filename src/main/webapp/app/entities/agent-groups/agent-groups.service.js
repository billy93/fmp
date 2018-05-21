(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('AgentGroups', AgentGroups);

    AgentGroups.$inject = ['$resource'];

    function AgentGroups ($resource) {
        var resourceUrl =  'api/agent-groups/:id';

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
