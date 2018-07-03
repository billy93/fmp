(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFAirportMappingDialogController', DFAirportMappingDialogController);

    DFAirportMappingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DFAirportMapping'];

    function DFAirportMappingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DFAirportMapping) {
        var vm = this;

        vm.dFAirportMapping = entity;
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
            if (vm.dFAirportMapping.id !== null) {
                DFAirportMapping.update(vm.dFAirportMapping, onSaveSuccess, onSaveError);
            } else {
                DFAirportMapping.save(vm.dFAirportMapping, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:dFAirportMappingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
