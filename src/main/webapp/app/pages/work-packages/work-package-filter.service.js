(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('WorkPackageFilter', WorkPackageFilter);

    WorkPackageFilter.$inject = ['$resource'];

    function WorkPackageFilter ($resource) {
        var resourceUrl =  'api/workpackagefilters/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
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
            'deleteByName': { method: 'DELETE', url:'api/workpackagefilters/deleteByName' }
        });
    }
})();
