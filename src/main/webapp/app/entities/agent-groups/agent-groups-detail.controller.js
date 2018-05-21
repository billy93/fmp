(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AgentGroupsDetailController', AgentGroupsDetailController);

    AgentGroupsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AgentGroups'];

    function AgentGroupsDetailController($scope, $rootScope, $stateParams, previousState, entity, AgentGroups) {
        var vm = this;

        vm.agentGroups = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:agentGroupsUpdate', function(event, result) {
            vm.agentGroups = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
