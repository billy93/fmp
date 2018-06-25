(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFOriginDestinationDetailController', DFOriginDestinationDetailController);

    DFOriginDestinationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DFOriginDestination'];

    function DFOriginDestinationDetailController($scope, $rootScope, $stateParams, previousState, entity, DFOriginDestination) {
        var vm = this;

        vm.dFOriginDestination = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:dFOriginDestinationUpdate', function(event, result) {
            vm.dFOriginDestination = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
