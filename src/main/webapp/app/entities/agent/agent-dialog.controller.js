(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AgentDialogController', AgentDialogController);

    AgentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Agent', 'agentCategory'];

    function AgentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Agent, agentCategory) {
        var vm = this;

        vm.agent = entity;
        vm.agentCategory = agentCategory;
        if(vm.agent.agentPcc == null){
        	vm.agent.agentPcc = [];
        }
        
        vm.clear = clear;
        vm.save = save;
        vm.optionAgentType=['Bulk','Consolidator','Corporate/TMC','Ethnic Market','Retailers','Seamen','Student','Tour operator','VFR','Web/E-Channel','Worker'];
        vm.optionAgentCrs=['Amadeus', 'Galileo', 'Worldspan', "Sabre", "ITA", "Apollo", "EDS", "Expedia"];

        vm.addAgentPcc = function(){
        	vm.agent.agentPcc.push({});        	
        }
        
        vm.removeAgent = function(idx){
        	vm.agent.agentPcc.splice(idx, 1);
        }  
        
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            console.log(vm.agent);
            if (vm.agent.id !== null) {
                Agent.update(vm.agent, onSaveSuccess, onSaveError);
            } else {
                Agent.save(vm.agent, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:agentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
