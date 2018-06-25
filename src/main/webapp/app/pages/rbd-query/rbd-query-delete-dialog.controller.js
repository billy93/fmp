(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdqueryDeleteController',RbdqueryDeleteController);

    RbdqueryDeleteController.$inject = ['$uibModalInstance', 'entity', 'Rbdquery'];

    function RbdqueryDeleteController($uibModalInstance, entity, Rbdquery) {
        var vm = this;

        vm.rbdquery = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Rbdquery.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
