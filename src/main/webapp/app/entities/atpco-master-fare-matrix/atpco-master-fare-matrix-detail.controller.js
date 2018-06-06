(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AtpcoMasterFareMatrixDetailController', AtpcoMasterFareMatrixDetailController);

    AtpcoMasterFareMatrixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AtpcoMasterFareMatrix'];

    function AtpcoMasterFareMatrixDetailController($scope, $rootScope, $stateParams, previousState, entity, AtpcoMasterFareMatrix) {
        var vm = this;

        vm.atpcoMasterFareMatrix = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:atpcoMasterFareMatrixUpdate', function(event, result) {
            vm.atpcoMasterFareMatrix = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
