(function() {
	'use strict';

	angular.module('fmpApp').controller('AtpcoMasterFareTypeModalController', AtpcoMasterFareTypeModalController);

	AtpcoMasterFareTypeModalController.$inject = [ '$state', 'entity', '$uibModalInstance', 'AtpcoMasterFareType' ];

	function AtpcoMasterFareTypeModalController($state, entity,
			$uibModalInstance, AtpcoMasterFareType) {
		var vm = this;
		vm.clear = clear;
		vm.parent = entity;
		vm.fareType = AtpcoMasterFareType;
		vm.option = {
			searchType : 'typeCode'
		};
		vm.selectedRow = vm.fareType[0];
		vm.sortType = 'typeCode';
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
			vm.parent.paramFareTypeCode = vm.selectedRow.typeCode;
			$uibModalInstance.close(vm.selectedRow);
		}
		function clear() {
			$uibModalInstance.dismiss('cancel');
		}

		vm.fareType.getAll(function(data) {
			vm.fareTypes = data;
		});

	}
})();
