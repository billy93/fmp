(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdMappingDetailController', RbdMappingDetailController);

    RbdMappingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RbdMapping'];

    function RbdMappingDetailController($scope, $rootScope, $stateParams, previousState, entity, RbdMapping) {
        var vm = this;

        vm.rbdMapping = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:rbdMappingUpdate', function(event, result) {
            vm.rbdMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
