(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('ReviewLevelDetailController', ReviewLevelDetailController);

    ReviewLevelDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ReviewLevel'];

    function ReviewLevelDetailController($scope, $rootScope, $stateParams, previousState, entity, ReviewLevel) {
        var vm = this;

        vm.reviewLevel = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:reviewLevelUpdate', function(event, result) {
            vm.reviewLevel = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
