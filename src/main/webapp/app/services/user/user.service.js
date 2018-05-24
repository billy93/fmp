(function () {
    'use strict';

    angular
        .module('fmpApp')
        .factory('User', User);

    User.$inject = ['$resource'];

    function User ($resource) {
        var service = $resource('api/users/:login', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': { method:'POST' },
            'update': { method:'PUT' },
            'delete':{ method:'DELETE'},
            'generateTourcode':{method:'POST', url:'api/users/generateTourcode'},
            'getBusinessArea':{method:'GET',isArray: true, url:'api/users/getBusinessArea'}
        });

        return service;
    }
})();
