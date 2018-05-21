(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('PriorityDetailController', PriorityDetailController);

    PriorityDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Priority'];

    function PriorityDetailController($scope, $rootScope, $stateParams, previousState, entity, Priority) {
        var vm = this;

        vm.priority = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:priorityUpdate', function(event, result) {
            vm.priority = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
