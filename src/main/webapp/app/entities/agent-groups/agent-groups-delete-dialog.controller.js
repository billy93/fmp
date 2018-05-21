(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AgentGroupsDeleteController',AgentGroupsDeleteController);

    AgentGroupsDeleteController.$inject = ['$uibModalInstance', 'entity', 'AgentGroups'];

    function AgentGroupsDeleteController($uibModalInstance, entity, AgentGroups) {
        var vm = this;

        vm.agentGroups = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AgentGroups.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
