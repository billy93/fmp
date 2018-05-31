(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AgentCategoryDetailController', AgentCategoryDetailController);

    AgentCategoryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AgentCategory'];

    function AgentCategoryDetailController($scope, $rootScope, $stateParams, previousState, entity, AgentCategory) {
        var vm = this;

        vm.agentCategory = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:agentCategoryUpdate', function(event, result) {
            vm.agentCategory = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
