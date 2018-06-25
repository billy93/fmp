(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFPublishedFareRatesDialogController', DFPublishedFareRatesDialogController);

    DFPublishedFareRatesDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DFPublishedFareRates'];

    function DFPublishedFareRatesDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DFPublishedFareRates) {
        var vm = this;

        vm.dFPublishedFareRates = entity;
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
            if (vm.dFPublishedFareRates.id !== null) {
                DFPublishedFareRates.update(vm.dFPublishedFareRates, onSaveSuccess, onSaveError);
            } else {
                DFPublishedFareRates.save(vm.dFPublishedFareRates, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:dFPublishedFareRatesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
