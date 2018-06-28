(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('DFScheduler', DFScheduler);

    DFScheduler.$inject = ['$resource'];

    function DFScheduler ($resource) {
        var resourceUrl =  'api/data-feed-scheduler/';

        return $resource(resourceUrl, {}, {
//        	'setParam': { method:'POST' }
        });
    }
})();
