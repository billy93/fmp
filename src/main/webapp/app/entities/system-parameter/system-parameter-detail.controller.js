(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('SystemParameterDetailController', SystemParameterDetailController);

    SystemParameterDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'SystemParameter'];

    function SystemParameterDetailController($scope, $rootScope, $stateParams, previousState, entity, SystemParameter) {
        var vm = this;

        vm.systemParameter = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:systemParameterUpdate', function(event, result) {
            vm.systemParameter = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
