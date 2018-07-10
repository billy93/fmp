(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareClassConstructionDetailsController', FareClassConstructionDetailsController);

    FareClassConstructionDetailsController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FareClassQuery'];

    function FareClassConstructionDetailsController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FareClassQuery) {
        var vm = this;

        vm.fareClassQuery = entity;
        console.log(vm.fareClassQuery);
        vm.clear = clear;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();
