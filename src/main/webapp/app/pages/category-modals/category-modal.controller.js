(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CategoryModalController', CategoryModalController);

    CategoryModalController.$inject = ['$state', 'entity', '$uibModalInstance'];

    function CategoryModalController($state, entity, $uibModalInstance) {
        var vm = this;
        vm.clear = clear;
        
        vm.category = entity;
        
        console.log(vm.category);
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
