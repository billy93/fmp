(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AgentCategoryDeleteController',AgentCategoryDeleteController);

    AgentCategoryDeleteController.$inject = ['$uibModalInstance', 'entity', 'AgentCategory'];

    function AgentCategoryDeleteController($uibModalInstance, entity, AgentCategory) {
        var vm = this;

        vm.agentCategory = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AgentCategory.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
