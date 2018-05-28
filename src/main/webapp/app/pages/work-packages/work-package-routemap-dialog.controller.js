(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageRoutemapDialogController', WorkPackageRoutemapDialogController);

    WorkPackageRoutemapDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state'];

    function WorkPackageRoutemapDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state) {

        var vm = this;
        vm.clear = clear;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
    }
})();
