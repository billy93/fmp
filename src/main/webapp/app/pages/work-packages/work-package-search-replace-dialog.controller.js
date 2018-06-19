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
        }
        
        vm.find = function(){
        	vm.filter.find = true;
        	if(vm.filter.andor == 'and'){
        		$uibModalInstance.close(vm.filter);
        	}
        	else if(vm.filter.andor == 'or'){
        		
        	}
        	//console.log(vm.filter);
        }
        
        vm.reset = function(){
        	 vm.filter = vm.originalFilter;
        };
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
