(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('TariffNumberDetailController', TariffNumberDetailController);

    TariffNumberDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'TariffNumber'];

    function TariffNumberDetailController($scope, $rootScope, $stateParams, previousState, entity, TariffNumber) {
        var vm = this;

        vm.tariffNumber = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:tariffNumberUpdate', function(event, result) {
            vm.tariffNumber = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
