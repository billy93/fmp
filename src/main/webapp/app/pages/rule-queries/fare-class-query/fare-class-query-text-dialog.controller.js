(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareClassTextController', FareClassTextController);

    FareClassTextController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FareClassQuery'];

    function FareClassTextController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FareClassQuery) {
        var vm = this;

        vm.fareClassQuery = entity;
        vm.clear = clear;
        vm.getText = getText;
        
        vm.getText();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        function getText() {
        	FareClassQuery.getFareClassText(vm.fareClassQuery, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.text = data;
                console.log(vm.text);
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

    }
})();
