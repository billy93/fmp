(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageDeleteController',WorkPackageDeleteController);

    WorkPackageDeleteController.$inject = ['$uibModalInstance', 'entity', 'WorkPackage'];

    function WorkPackageDeleteController($uibModalInstance, entity, WorkPackage) {
        var vm = this;

        vm.workPackage = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            WorkPackage.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
