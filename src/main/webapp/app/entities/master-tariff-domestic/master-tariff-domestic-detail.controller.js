(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('MasterTariffDomesticDetailController', MasterTariffDomesticDetailController);

    MasterTariffDomesticDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'MasterTariffDomestic'];

    function MasterTariffDomesticDetailController($scope, $rootScope, $stateParams, previousState, entity, MasterTariffDomestic) {
        var vm = this;

        vm.masterTariffDomestic = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:masterTariffDomesticUpdate', function(event, result) {
            vm.masterTariffDomestic = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
