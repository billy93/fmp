(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdColorMappingDetailController', RbdColorMappingDetailController);

    RbdColorMappingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RbdColorMapping'];

    function RbdColorMappingDetailController($scope, $rootScope, $stateParams, previousState, entity, RbdColorMapping) {
        var vm = this;

        vm.rbdColorMapping = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:rbdColorMappingUpdate', function(event, result) {
            vm.rbdColorMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
