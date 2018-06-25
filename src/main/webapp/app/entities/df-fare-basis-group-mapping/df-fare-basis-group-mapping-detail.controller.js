(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DFFareBasisGroupMappingDetailController', DFFareBasisGroupMappingDetailController);

    DFFareBasisGroupMappingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DFFareBasisGroupMapping'];

    function DFFareBasisGroupMappingDetailController($scope, $rootScope, $stateParams, previousState, entity, DFFareBasisGroupMapping) {
        var vm = this;

        vm.dFFareBasisGroupMapping = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:dFFareBasisGroupMappingUpdate', function(event, result) {
            vm.dFFareBasisGroupMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
