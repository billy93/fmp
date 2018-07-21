(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('DerivedFare', DerivedFare);

    DerivedFare.$inject = ['$resource'];

    function DerivedFare ($resource) {
        return $resource(null, {}, {
            'getAfdQueryDerivedFares': { method: 'POST', url:'api/derived-fares/fares' }
        });
    }
})();
