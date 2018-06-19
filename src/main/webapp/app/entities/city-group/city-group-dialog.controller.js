(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CityGroupDialogController', CityGroupDialogController);

    CityGroupDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CityGroup', 'cities'];

    function CityGroupDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CityGroup, cities) {
        var vm = this;

        vm.cityGroup = entity;
        vm.clear = clear;
        vm.save = save;
        vm.cities = cities;
        
        vm.addCities = function(){
        	if(vm.cityGroup.cities == null){
                vm.cityGroup.cities = [];
        	}
        	 vm.cityGroup.cities.push(vm.selectCity);
        }
        
        vm.removeCities = function(city){
        	var index = vm.selectedCities.indexOf(city);
        	 vm.cityGroup.cities.splice(index, 1); 
        }
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.cityGroup.id !== null) {
                CityGroup.update(vm.cityGroup, onSaveSuccess, onSaveError);
            } else {
                CityGroup.save(vm.cityGroup, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:cityGroupUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
