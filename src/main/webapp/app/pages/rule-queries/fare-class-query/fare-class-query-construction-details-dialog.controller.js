(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareClassConstructionDetailsController', FareClassConstructionDetailsController);

    FareClassConstructionDetailsController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FareClassQuery', 'AlertService'];

    function FareClassConstructionDetailsController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FareClassQuery, AlertService) {
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
        	vm.fareClassQuery.fareClassInformation = null;
        	FareClassQuery.getFareClassConstructionDetails(vm.fareClassQuery, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.fareClass = data.fareClassQuery;
                vm.fareClass.owrtType = "RT";
                if(vm.fareClass.owrt == 1)
                	vm.fareClass.owrtType = "OW";
                
                vm.cdP02 = data.constractionDataP02;
                vm.cdP02.nucFlag = false;
                vm.cdP02.usAllFlag = false;
                vm.cdP02.nusAllFlag = false;
                if(vm.cdP02.nuc == 'Y')
                	vm.cdP02.nucFlag = true;
                if(vm.cdP02.usAll == 'X')
                	vm.cdP02.usAllFlag = true;
                if(vm.cdP02.nusAll == 'X')
                	vm.cdP02.nusAllFlag = true;
                
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

    }
})();
