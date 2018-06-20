(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageSearchReplaceDialogController', WorkPackageSearchReplaceDialogController);

    WorkPackageSearchReplaceDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'Agent', 'filter', 'fareSheet'];

    function WorkPackageSearchReplaceDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, Agent, filter, fareSheet) {

        var vm = this;
        vm.clear = clear;
        
        
        vm.filter = {
        	andor:'and',
        	no:{
        		check:false
        	},
        };
        vm.originalFilter = angular.copy(vm.filter);
        
        if(filter != null){
        	vm.filter = filter;
        	
        	if(vm.filter.message != null){
        		if(confirm(vm.filter.message)){
//        			vm.filter.index = 0;
        			//vm.filter.message = null;
        			$uibModalInstance.close(vm.filter);        			
        		}
        	}
        }
        
        vm.find = function(){
        	vm.filter.find = true;
        	vm.filter.replace = false;
        	vm.filter.replaceAll = false;
        	$uibModalInstance.close(vm.filter);
        }
        
        vm.replace = function(){
        	vm.filter.replace = true;
        	vm.filter.replaceAll = false;
        	vm.filter.find = false;
        	$uibModalInstance.close(vm.filter);
        }
        
        vm.replaceAll = function(){
        	vm.filter.replace = true;
        	vm.filter.replaceAll = true;
        	vm.filter.find = false;
        	$uibModalInstance.close(vm.filter);
        }
        
        vm.reset = function(){
        	 vm.filter = vm.originalFilter;
        };
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
