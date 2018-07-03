(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('InfoModalController', InfoModalController);

    InfoModalController.$inject = ['$state', '$uibModalInstance', 'entity'];

    function InfoModalController($state, $uibModalInstance, entity) {
        var vm = this;
        vm.clear = clear;
        
        vm.message = entity.message;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
