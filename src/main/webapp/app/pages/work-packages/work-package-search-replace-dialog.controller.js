(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageSearchReplaceDialogController', WorkPackageSearchReplaceDialogController);

    WorkPackageSearchReplaceDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'Agent'];

    function WorkPackageSearchReplaceDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, Agent) {

        var vm = this;
        vm.clear = clear;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
