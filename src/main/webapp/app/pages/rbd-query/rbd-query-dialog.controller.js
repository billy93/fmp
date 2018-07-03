(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdqueryDialogController', RbdqueryDialogController);

    RbdqueryDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Rbdquery'];

    function RbdqueryDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Rbdquery) {
        var vm = this;

        vm.rbdquery = entity;
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
            if (vm.rbdquery.id !== null) {
                Rbdquery.update(vm.rbdquery, onSaveSuccess, onSaveError);
            } else {
                Rbdquery.save(vm.rbdquery, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:rbdqueryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
