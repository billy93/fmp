(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('DFScheduler', DFScheduler);

    DFScheduler.$inject = ['$resource'];

    function DFScheduler ($resource) {
        var resourceUrl =  'api/data-feed-scheduler/';

        return $resource(resourceUrl, {}, {
        	'getAll': { method: 'GET' },
        	'updateScheduler': { method:'PUT' },
        	'startScheduler': { method: 'PUT', url:'api/data-feed-scheduler/start' },
        	'stopScheduler': { method:'PUT', url:'api/data-feed-scheduler/stop' },
        });
    }
})();
