(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageHistoryDialogController', WorkPackageHistoryDialogController);

    WorkPackageHistoryDialogController.$inject = ['$uibModalInstance', '$state', 'entity'];

    function WorkPackageHistoryDialogController($uibModalInstance, $state, entity) {

        var vm = this;
        vm.history = entity;
        vm.clear = clear;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();
