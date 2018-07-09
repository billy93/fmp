(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('Rec8FareByRule', Rec8FareByRule);

    Rec8FareByRule.$inject = ['$resource'];

    function Rec8FareByRule ($resource) {
        var resourceUrl =  'api/rec8-fare-by-rule/:id';

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
        });
    }
})();
