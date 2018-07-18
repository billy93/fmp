(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageAgentDialogController', WorkPackageAgentDialogController);

    WorkPackageAgentDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'entity', 'Agent','isDisabled', 'isViewOnly'];

    function WorkPackageAgentDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, entity, Agent,isDisabled, isViewOnly) {

        var vm = this;
        vm.clear = clear;
        vm.agents = entity.agents;
        vm.agencies = [];
        vm.disabled = isDisabled;
        vm.isViewOnly = isViewOnly;
                
        vm.save = function(){
        	$uibModalInstance.close(vm.agents);
        }
        
        loadAll();
        
        function loadAll() {
            Agent.queryAll(function(result) {
                vm.agencies = result;
                vm.searchQuery = null;
                console.log(vm.agencies);

            });
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        vm.addAgents = function() {
        	if (vm.agent != undefined && vm.agent != null) {
        		if (!vm.agents) {
        			vm.agents = [];        			
        		}
        		
        		if (vm.agents.indexOf(vm.agent) == -1) {
        			vm.agents.push(vm.agent);
        		}
        		
        		vm.agent = null;
        	}
        } 
        
        vm.removeAgent = function(index) {
//        	var index = vm.agents.indexOf(vm.remove);
        		vm.agents.splice(index, 1);
       	
        }
      
    }
})();
