(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('TariffNumberAddOnDetailController', TariffNumberAddOnDetailController);

    TariffNumberAddOnDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'TariffNumberAddOn'];

    function TariffNumberAddOnDetailController($scope, $rootScope, $stateParams, previousState, entity, TariffNumberAddOn) {
        var vm = this;

        vm.tariffNumberAddOn = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:tariffNumberAddOnUpdate', function(event, result) {
            vm.tariffNumberAddOn = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
