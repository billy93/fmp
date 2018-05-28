(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('MasterTariffDomesticDialogController', MasterTariffDomesticDialogController);

    MasterTariffDomesticDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'MasterTariffDomestic'];

    function MasterTariffDomesticDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, MasterTariffDomestic) {
        var vm = this;

        vm.masterTariffDomestic = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        console.log(entity);
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.masterTariffDomestic.id !== null) {
                MasterTariffDomestic.update(vm.masterTariffDomestic, onSaveSuccess, onSaveError);
            } else {
                MasterTariffDomestic.save(vm.masterTariffDomestic, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:masterTariffDomesticUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.addGeneralRulesName = function() {
//        	console.log(vm.masterTariffDomestic);
        	if (vm.generalRulesName != undefined && vm.generalRulesName != null) {
        		if (!vm.masterTariffDomestic.generalRules)
        			vm.masterTariffDomestic.generalRules = [];
        		
        		if (vm.masterTariffDomestic.generalRules.indexOf(vm.generalRulesName) == -1) {
        			vm.masterTariffDomestic.generalRules.push(vm.generalRulesName);
        		}
        		vm.generalRulesName = null;
                }
        	}
        vm.removeGeneralRulesName = function(index) {
			vm.masterTariffDomestic.generalRules.name.splice(index, 1);
        }
        
        vm.addGeneralRulesCode = function() {
        	if (vm.generalRulesCode != undefined && vm.generalRulesCode != null) {
        		if (!vm.masterTariffDomestic.generalRules)
        			vm.masterTariffDomestic.generalRules = [];
        		
        		if (vm.masterTariffDomestic.generalRules.indexOf(vm.generalRulesCode) == -1) {
        			vm.masterTariffDomestic.generalRules.push(vm.generalRulesCode);
        		}
        		vm.generalRulesCode = null;
                }
        	}
        vm.removeGeneralRulesName = function(index) {
			vm.masterTariffDomestic.generalRules.name.splice(index, 1);
        }

    }
})();
