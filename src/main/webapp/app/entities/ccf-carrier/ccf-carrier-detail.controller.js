(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CcfCarrierDetailController', CcfCarrierDetailController);

    CcfCarrierDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CcfCarrier'];

    function CcfCarrierDetailController($scope, $rootScope, $stateParams, previousState, entity, CcfCarrier) {
        var vm = this;

        vm.ccfCarrier = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:ccfCarrierUpdate', function(event, result) {
            vm.ccfCarrier = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
