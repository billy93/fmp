(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('Routingquery', Routingquery);

    Routingquery.$inject = ['$resource'];

    function Routingquery ($resource) {
        var resourceUrl =  'api/routingqueries/:id';

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
            'getDetails': { method: 'GET', url:'api/routingqueries/details', isArray: true },
            'getMaps': { method: 'GET', url:'api/routingqueries/getmaps/:tarno/:crx/:rtg', isArray: true },
        });
    }
})();
