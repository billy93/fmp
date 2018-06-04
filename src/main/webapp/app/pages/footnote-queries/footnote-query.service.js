(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('FootnoteQuery', FootnoteQuery);

    FootnoteQuery.$inject = ['$resource'];

    function FootnoteQuery ($resource) {
        var resourceUrl =  'api/footnote-queries/:id';

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
            'getRules': { method: 'GET', url:'api/footnote-queries/rules', isArray: true },
            'getRules2': { method: 'GET', url:'api/footnote-queries/rules2', isArray: true }
        });
    }
})();
