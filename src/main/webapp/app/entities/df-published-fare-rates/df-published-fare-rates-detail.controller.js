(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFPublishedFareRatesDetailController', DFPublishedFareRatesDetailController);

    DFPublishedFareRatesDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DFPublishedFareRates'];

    function DFPublishedFareRatesDetailController($scope, $rootScope, $stateParams, previousState, entity, DFPublishedFareRates) {
        var vm = this;

        vm.dFPublishedFareRates = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:dFPublishedFareRatesUpdate', function(event, result) {
            vm.dFPublishedFareRates = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
