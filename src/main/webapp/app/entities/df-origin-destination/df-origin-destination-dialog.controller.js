(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFOriginDestinationDialogController', DFOriginDestinationDialogController);

    DFOriginDestinationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DFOriginDestination'];

    function DFOriginDestinationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DFOriginDestination) {
        var vm = this;

        vm.dFOriginDestination = entity;
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
            if (vm.dFOriginDestination.id !== null) {
                DFOriginDestination.update(vm.dFOriginDestination, onSaveSuccess, onSaveError);
            } else {
                DFOriginDestination.save(vm.dFOriginDestination, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:dFOriginDestinationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
