(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FullTextModalController', FullTextModalController);

    FullTextModalController.$inject = ['$state', 'entity', '$uibModalInstance'];

    function FullTextModalController($state, entity, $uibModalInstance) {
        var vm = this;
        vm.clear = clear;
        
        vm.categoryRules = entity.categories;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
