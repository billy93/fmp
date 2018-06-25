(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareDetailModalController', FareDetailModalController);

    FareDetailModalController.$inject = ['$state', 'fare', 'rules', '$uibModalInstance', 'Timezone'];

    function FareDetailModalController($state, fare, rules, $uibModalInstance, Timezone) {
        var vm = this;
        vm.clear = clear;
        vm.timezone = Timezone.GMT7;
        vm.fare = fare;
        vm.categoryRules = rules.categories;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
