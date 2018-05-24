(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareTypeDetailController', FareTypeDetailController);

    FareTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FareType'];

    function FareTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, FareType) {
        var vm = this;

        vm.fareType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:fareTypeUpdate', function(event, result) {
            vm.fareType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
