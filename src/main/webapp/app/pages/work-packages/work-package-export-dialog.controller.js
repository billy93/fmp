(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageExportDialogController', WorkPackageExportDialogController);

    WorkPackageExportDialogController.$inject = ['WorkPackage', '$uibModalInstance', '$uibModal'];

    function WorkPackageExportDialogController (WorkPackage, $uibModalInstance, $uibModal) {
        var vm = this;

        vm.clear = clear;
        vm.save = save;
        vm.workPackageExportOption = {
        	outputTo:"Screen"
        }
        vm.exportOutputTo = {
    		"Screen":"Screen", 
    		"Printer":"Printer", 
    		"Word File":"Word File", 
    		"Excel File":"Excel File"
        };
        
        vm.selectColumn = function(){
        	$uibModal.open({
                templateUrl: 'app/pages/work-packages/work-package-export-select-column-dialog.html',
                controller: 'WorkPackageExportSelectColumnDialogController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full-page-modal',
                resolve: {
//                	workPackage: function(){
//                		return vm.workPackage;
//                	},
//                    fareTypes: ['FareType', function(FareType) {
//                        return FareType.getAll().$promise;
//                    }],
                }
			}).result.then(function(option) {
				console.log(option);
				vm.addTab(option);

//				GlobalService.sayHello();
//			    GlobalService.boxHeader();
            }, function() {
        			
            });
        }
        
        vm.export = function(){
        	WorkPackage.exportQueue(vm.workPackageExportOption, onExportSuccess, onExportFailure);
        	
        	function onExportSuccess(){
        		
        	}
        	
        	function onExportFailure(){
        		
        	}        	
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.workPackage.id !== null) {
                WorkPackage.update(vm.workPackage, onSaveSuccess, onSaveError);
            } else {
                WorkPackage.save(vm.workPackage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:workPackageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    }
})();
