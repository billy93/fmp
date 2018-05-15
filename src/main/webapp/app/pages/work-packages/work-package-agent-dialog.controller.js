(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageAgentDialogController', WorkPackageAgentDialogController);

    WorkPackageAgentDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'entity', 'Agencies'];

    function WorkPackageAgentDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, entity, Agencies) {

        var vm = this;
        vm.clear = clear;
        vm.agents = entity.agents;
        vm.agencies = [];
        
        console.log(vm.agents);
        
        vm.save = function(){
        	$uibModalInstance.close(vm.agents);
        }
        
//        vm.loadAll = function(){
//        	
//        }
        
        loadAll();

        function loadAll() {
            Agencies.query(function(result) {
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
