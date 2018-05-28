(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('PassengerDialogController', PassengerDialogController);

    PassengerDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Passenger'];

    function PassengerDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Passenger) {
        var vm = this;

        vm.passenger = entity;
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
            if (vm.passenger.id !== null) {
                Passenger.update(vm.passenger, onSaveSuccess, onSaveError);
            } else {
                Passenger.save(vm.passenger, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:passengerUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
