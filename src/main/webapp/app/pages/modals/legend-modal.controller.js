(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('LegendModalController', LegendModalController);

    LegendModalController.$inject = ['$state', '$uibModalInstance'];

    function LegendModalController($state, $uibModalInstance) {
        var vm = this;
        vm.clear = clear;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
