(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareClassTextController', FareClassTextController);

    FareClassTextController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FareClassQuery'];

    function FareClassTextController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FareClassQuery) {
        var vm = this;

        vm.fareClassQuery = entity;
        vm.getText = getText;
        vm.print = print;
        vm.clear = clear;
        
        vm.getText();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }
        
        function getText() {
        	FareClassQuery.getFareClassText(vm.fareClassQuery, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.text = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function print() {
            console.log("print text");
        }

    }
})();
