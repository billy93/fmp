(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('SystemParameterDeleteController',SystemParameterDeleteController);

    SystemParameterDeleteController.$inject = ['$uibModalInstance', 'entity', 'SystemParameter'];

    function SystemParameterDeleteController($uibModalInstance, entity, SystemParameter) {
        var vm = this;

        vm.systemParameter = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            SystemParameter.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
