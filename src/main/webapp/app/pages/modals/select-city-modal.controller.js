(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('SelectCityModalController', SelectCityModalController);

    SelectCityModalController.$inject = ['$scope', '$uibModalInstance', '$state', 'City', 'CityGroup'];

    function SelectCityModalController($scope, $uibModalInstance, $state, City, CityGroup) {

        var vm = this;
        vm.clear = clear;
        vm.option = { searchType:'cityCode' };
        vm.sortType     = 'cityCode';
        vm.sortReverse  = false; 
        vm.searchFish   = ''; 
        vm.selectedRow = null;
        vm.selectedCityGroupRow = null;
        vm.selectTab = selectTab;
        vm.isLoading = false;
        
        vm.selectTab('city');
        
        vm.rowHighlighted = function (idSelected) {
           vm.selectedRow = idSelected;
        };
        
        vm.rowCityGroupHighlighted = function (idSelected) {
            vm.selectedCityGroupRow = idSelected;
        };
        
        vm.search = function(){
        	vm.selectedRow = null;
        }
        
        vm.searchCityGroup = function(){
        	vm.selectedCityGroupRow = null;
        }
        
        vm.select = function(){
        	if(vm.currentTab == 'city'){
        		$uibModalInstance.close(vm.selectedRow.cityCode);
        	} else if(vm.currentTab == 'cityGroup'){
        		var concat = '';
        		
        		if (vm.selectedCityGroupRow != null && vm.selectedCityGroupRow.cities != null) {
        			for (var i = 0; i < vm.selectedCityGroupRow.cities.length; i++) {
        				if (vm.selectedCityGroupRow.cities[i] != null && vm.selectedCityGroupRow.cities[i].cityCode != null) {
        					if (i < vm.selectedCityGroupRow.cities.length - 1) {
            					concat += vm.selectedCityGroupRow.cities[i].cityCode + ',';
            				} else {
            					concat += vm.selectedCityGroupRow.cities[i].cityCode;
            				}
        				}
        			}
        		}
        		$uibModalInstance.close(concat);
        	}
        }
        
        function selectTab (tab){
        	vm.currentTab = tab;
        	
        	if (vm.currentTab == 'city'  && (vm.cities == null || vm.cities == undefined || vm.cities == [])) {
        		vm.isLoading = true;
        		City.getAll(function(data) {
        			vm.cities = data;
        			vm.isLoading = false;
        		}, function(error) {
        			console.log(error);
        			vm.isLoading = false;
        		});
        	} else  if (vm.currentTab == 'cityGroup' && (vm.cityGroup == null || vm.cityGroup == undefined || vm.cityGroup == [])) {
        		vm.isLoading = true;
        		CityGroup.getAll(null, function(data) {
        			vm.cityGroup = data;
        			vm.isLoading = false;
        		}, function(error) {
        			console.log(error);
        			vm.isLoading = false;
        		});
        	}
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
    }
})();
