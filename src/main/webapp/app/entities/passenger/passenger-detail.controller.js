(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('PassengerDetailController', PassengerDetailController);

    PassengerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Passenger'];

    function PassengerDetailController($scope, $rootScope, $stateParams, previousState, entity, Passenger) {
        var vm = this;

        vm.passenger = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:passengerUpdate', function(event, result) {
            vm.passenger = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
