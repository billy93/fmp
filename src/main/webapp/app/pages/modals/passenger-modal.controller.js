(function() {
	'use strict';

	angular.module('fmpApp').controller('MasterPassengerModalController', MasterPassengerModalController);

	MasterPassengerModalController.$inject = [ '$state', 'entity', '$uibModalInstance', 'Passenger' ];

	function MasterPassengerModalController($state, entity, $uibModalInstance, Passenger) {
		var vm = this;
		vm.clear = clear;
		vm.parent = entity;
		vm.passenger = Passenger;
		vm.option = {
			searchType : 'code'
		};
		vm.selectedRow = vm.passenger[0];
		vm.sortType = 'code';
		vm.sortReverse = false;
		vm.searchFish = '';

		vm.selectedRow = null;

		vm.rowHighlighted = function(idSelected) {
			vm.selectedRow = idSelected;
		};

		vm.search = function() {
			vm.selectedRow = null;
		}

		vm.select = function() {
			vm.parent.paramPassengerCode = vm.selectedRow.code;
			$uibModalInstance.close(vm.selectedRow);
		}
		function clear() {
			$uibModalInstance.dismiss('cancel');
		}

		vm.passenger.getAll(function(data) {
			vm.passengers = data;
		});

	}
})();
