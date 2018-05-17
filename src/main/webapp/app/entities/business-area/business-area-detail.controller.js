(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('BusinessAreaDetailController', BusinessAreaDetailController);

    BusinessAreaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'BusinessArea'];

    function BusinessAreaDetailController($scope, $rootScope, $stateParams, previousState, entity, BusinessArea) {
        var vm = this;

        vm.businessArea = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:businessAreaUpdate', function(event, result) {
            vm.businessArea = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
