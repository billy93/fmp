(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AfdQueryController', AfdQueryController);

    AfdQueryController.$inject = ['$state', '$stateParams'];

    function AfdQueryController($state, $stateParams) {

        var vm = this;
        vm.tab = $stateParams.tab;
        
    }
})();
