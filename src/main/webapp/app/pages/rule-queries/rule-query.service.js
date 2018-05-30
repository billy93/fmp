(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('RuleQuery', RuleQuery);

    RuleQuery.$inject = ['$resource'];

    function RuleQuery ($resource) {
        var resourceUrl =  'api/rule-queries/:id';

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
            'getRules': { method: 'GET', url:'api/rule-queries/rules', isArray: true }
        });
    }
})();
