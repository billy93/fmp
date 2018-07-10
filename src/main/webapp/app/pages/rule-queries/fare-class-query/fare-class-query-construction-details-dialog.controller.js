(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareClassConstructionDetailsController', FareClassConstructionDetailsController);

    FareClassConstructionDetailsController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FareClassQuery'];

    function FareClassConstructionDetailsController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FareClassQuery) {
        var vm = this;

        vm.fareClassQuery = entity;
        vm.getText = getText;
        vm.clear = clear;
        
        vm.getText();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }
        
        function getText() {
        	FareClassQuery.getFareClassConstructionDetails(vm.fareClassQuery, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.text = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

    }
})();
