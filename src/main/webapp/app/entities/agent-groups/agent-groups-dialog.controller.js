(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AgentGroupsDialogController', AgentGroupsDialogController);

    AgentGroupsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AgentGroups', 'agencies'];

    function AgentGroupsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AgentGroups, agencies) {
        var vm = this;

        vm.agentGroups = entity;
        vm.agencies = agencies;
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
            if (vm.agentGroups.id !== null) {
                AgentGroups.update(vm.agentGroups, onSaveSuccess, onSaveError);
            } else {
                AgentGroups.save(vm.agentGroups, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:agentGroupsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
        
        vm.addAgentName = function() {
        	if (vm.agentName != undefined && vm.agentName != null) {
        		if (!vm.agentGroups.agentName)
        			vm.agentGroups.agentName = [];
        		
        		if (vm.agentGroups.agentName.indexOf(vm.agentName) == -1) {
        			vm.agentGroups.agentName.push(vm.agentName);
        		}
        		vm.agentName = null;
                }
        	}
        vm.removeAgentName = function(index) {
			vm.agentGroups.agentName.splice(index, 1);
        }

    }
})();
