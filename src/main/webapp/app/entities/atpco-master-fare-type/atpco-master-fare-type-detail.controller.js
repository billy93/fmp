(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AtpcoMasterFareTypeDetailController', AtpcoMasterFareTypeDetailController);

    AtpcoMasterFareTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AtpcoMasterFareType'];

    function AtpcoMasterFareTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, AtpcoMasterFareType) {
        var vm = this;

        vm.atpcoMasterFareType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:atpcoMasterFareTypeUpdate', function(event, result) {
            vm.atpcoMasterFareType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
