(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('SpecifiedConstructed', SpecifiedConstructed);

    SpecifiedConstructed.$inject = ['$resource'];

    function SpecifiedConstructed ($resource) {

        return $resource(null, {}, {
            'getSpecified': { method: 'POST', url:'api/afd-queries/specified' },
            'getSpecifiedConstructed': { method: 'POST', url:'api/afd-queries/specified-constructed' },
            'getRules': { method: 'POST', url:'api/afd-queries/rules', isArray: true }
        });
    }
})();
