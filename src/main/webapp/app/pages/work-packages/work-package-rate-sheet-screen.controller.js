(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageRateSheetScreenController', WorkPackageRateSheetScreenController);

    WorkPackageRateSheetScreenController.$inject = ['Principal', '$state', '$stateParams', 'entity', '$window'];

    function WorkPackageRateSheetScreenController(Principal, $state, $stateParams, entity, $window) {
        var vm = this;
        console.log($window.variable);
        vm.workPackage = $window.variable;
        vm.index = 0;
    }
})();
