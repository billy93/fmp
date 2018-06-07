(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RoutingqueryDialogController', RoutingqueryDialogController);

    RoutingqueryDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Routingquery'];

    function RoutingqueryDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Routingquery) {
        var vm = this;

        vm.routingquery = entity;
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
            if (vm.routingquery.id !== null) {
                Routingquery.update(vm.routingquery, onSaveSuccess, onSaveError);
            } else {
                Routingquery.save(vm.routingquery, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:routingqueryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
