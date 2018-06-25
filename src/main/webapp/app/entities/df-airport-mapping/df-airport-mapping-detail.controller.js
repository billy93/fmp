(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFAirportMappingDetailController', DFAirportMappingDetailController);

    DFAirportMappingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DFAirportMapping'];

    function DFAirportMappingDetailController($scope, $rootScope, $stateParams, previousState, entity, DFAirportMapping) {
        var vm = this;

        vm.dFAirportMapping = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:dFAirportMappingUpdate', function(event, result) {
            vm.dFAirportMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
