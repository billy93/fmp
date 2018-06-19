(function() {
	'use strict';

	angular.module('fmpApp').controller(
			'CityCodestoAirportCodesDialogController',
			CityCodestoAirportCodesDialogController);

	CityCodestoAirportCodesDialogController.$inject = [ '$timeout', '$scope',
			'$stateParams', '$uibModalInstance', 'entity',
			'CityCodestoAirportCodes' ];

	function CityCodestoAirportCodesDialogController($timeout, $scope,
			$stateParams, $uibModalInstance, entity, CityCodestoAirportCodes) {
		var vm = this;

		vm.airportMapingDataFeed = entity;
		vm.clear = clear;
		vm.save = save;

		$timeout(function() {
			angular.element('.form-group:eq(1)>input').focus();
		});

		function clear() {
			$uibModalInstance.dismiss('cancel');
		}

		function save() {
			vm.isSaving = true;
			if (vm.airportMapingDataFeed.id !== null) {
				CityCodestoAirportCodes.update(vm.airportMapingDataFeed,
						onSaveSuccess, onSaveError);
			} else {
				CityCodestoAirportCodes.save(vm.airportMapingDataFeed,
						onSaveSuccess, onSaveError);
			}
		}

		function onSaveSuccess(result) {
			$scope.$emit('fmpApp:cityCodestoAirportCodesUpdate', result);
			$uibModalInstance.close(result);
			vm.isSaving = false;
		}

		function onSaveError() {
			vm.isSaving = false;
		}

	}
})();
