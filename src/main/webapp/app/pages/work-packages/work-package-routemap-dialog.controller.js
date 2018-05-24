(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageRoutemapDialogController', WorkPackageRoutemapDialogController);

    WorkPackageRoutemapDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'GlobalService'];

    function WorkPackageRoutemapDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, GlobalService) {

        var vm = this;
        vm.clear = clear;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        GlobalService.boxHeader();
        
    }
})();
