(function() {
    'use strict';

    angular
        .module('fmpApp')
        .factory('GlobalService', GlobalService);

    GlobalService.$inject = ['$http'];

    function GlobalService($http) {
    	 return {
    	 }
    }
})();
