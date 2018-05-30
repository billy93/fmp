(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AgentCategoryDialogController', AgentCategoryDialogController);

    AgentCategoryDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AgentCategory'];

    function AgentCategoryDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AgentCategory) {
        var vm = this;

        vm.agentCategory = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.agentCategory.id !== null) {
                AgentCategory.update(vm.agentCategory, onSaveSuccess, onSaveError);
            } else {
                AgentCategory.save(vm.agentCategory, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:agentCategoryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
