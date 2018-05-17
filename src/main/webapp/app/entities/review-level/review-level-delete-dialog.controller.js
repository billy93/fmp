(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('ReviewLevelDeleteController',ReviewLevelDeleteController);

    ReviewLevelDeleteController.$inject = ['$uibModalInstance', 'entity', 'ReviewLevel'];

    function ReviewLevelDeleteController($uibModalInstance, entity, ReviewLevel) {
        var vm = this;

        vm.reviewLevel = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ReviewLevel.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
