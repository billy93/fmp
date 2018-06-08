(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DataFeedFareBasisGroupMappingDetailController', DataFeedFareBasisGroupMappingDetailController);

    DataFeedFareBasisGroupMappingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DataFeedFareBasisGroupMapping'];

    function DataFeedFareBasisGroupMappingDetailController($scope, $rootScope, $stateParams, previousState, entity, DataFeedFareBasisGroupMapping) {
        var vm = this;

        vm.dataFeedFareBasisGroupMapping = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:dataFeedFareBasisGroupMappingUpdate', function(event, result) {
            vm.dataFeedFareBasisGroupMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
