(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AgentDetailController', AgentDetailController);

    AgentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Agent'];

    function AgentDetailController($scope, $rootScope, $stateParams, previousState, entity, Agent) {
        var vm = this;

        vm.agent = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:agentUpdate', function(event, result) {
            vm.agent = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
