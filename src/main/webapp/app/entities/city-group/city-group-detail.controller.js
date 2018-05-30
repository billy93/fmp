(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CityGroupDetailController', CityGroupDetailController);

    CityGroupDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CityGroup'];

    function CityGroupDetailController($scope, $rootScope, $stateParams, previousState, entity, CityGroup) {
        var vm = this;

        vm.cityGroup = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:cityGroupUpdate', function(event, result) {
            vm.cityGroup = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
