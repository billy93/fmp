(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdqueryDetailController', RbdqueryDetailController);

    RbdqueryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Rbdquery'];

    function RbdqueryDetailController($scope, $rootScope, $stateParams, previousState, entity, Rbdquery) {
        var vm = this;

        vm.rbdquery = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fmpApp:rbdqueryUpdate', function(event, result) {
            vm.rbdquery = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
