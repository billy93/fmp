(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('FbrReport', FbrReport);

    FbrReport.$inject = ['$resource'];

    function FbrReport ($resource) {
        return $resource(null, {}, {
            'query': { method: 'POST', url:'api/fbr-report' }
        });
    }
})();
