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
        vm.selectedRow = null;
        
        vm.addCities = function(){
        	if(vm.cityGroup.cities == null){
                vm.cityGroup.cities = [];
        	}
        	 vm.cityGroup.cities.push(vm.selectCity);
        }
        
        vm.removeCities = function(city){
        	var index = vm.cityGroup.cities.indexOf(city);
        	vm.cityGroup.cities.splice(index, 1); 
        }
        
        vm.addAll = function(){
        	vm.removeAll();
        	for(var x=0; x < vm.cities.length;x++){      
        		vm.cityGroup.cities.push(vm.cities[x]);
        	}
        }
        
        vm.add = function(){
        	if(vm.selectedCityRow != null){
        		var exist = false;
        		
        		if(vm.cityGroup.cities == null){
        			 vm.cityGroup.cities = [];
        		}
        		for(var x=0; x < vm.cityGroup.cities.length;x++){       
        			var index = vm.cityGroup.cities.indexOf(vm.selectedCityRow);
        			if(index != -1){
        				exist = true;
        			}
        		}
        		
        		if(!exist){
        			vm.cityGroup.cities.push(vm.selectedCityRow);        		
        		}
        	}
        }
        
        vm.removeAll = function(){
        	vm.cityGroup.cities = [];
        }
        
        vm.remove = function(){
        	var index = vm.cityGroup.cities.indexOf(vm.selectedRow);
        	vm.cityGroup.cities.splice(index, 1);
        }
        
        vm.rowHighlighted = function(city){
        	vm.selectedRow = city;
        }
        
        vm.rowListCityHighlighted = function(city){
        	vm.selectedCityRow = city;
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
