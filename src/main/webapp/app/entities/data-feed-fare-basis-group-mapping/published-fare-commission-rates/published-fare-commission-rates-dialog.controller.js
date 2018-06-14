(function() {
	'use strict';

	angular.module('fmpApp').controller(
			'PublishedFareCommissionRatesDialogController',
			PublishedFareCommissionRatesDialogController);

	PublishedFareCommissionRatesDialogController.$inject = [ '$timeout',
			'$scope', '$stateParams', '$uibModalInstance', 'entity',
			'PublishedFareCommissionRates' ];

	function PublishedFareCommissionRatesDialogController($timeout, $scope,
			$stateParams, $uibModalInstance, entity,
			PublishedFareCommissionRates) {
		var vm = this;

		vm.publishedFareCommissionRates = entity;
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
			if (vm.publishedFareCommissionRates.id !== null) {
				PublishedFareCommissionRates.update(
						vm.publishedFareCommissionRates, onSaveSuccess,
						onSaveError);
			} else {
				PublishedFareCommissionRates.save(
						vm.publishedFareCommissionRates, onSaveSuccess,
						onSaveError);
			}
		}

		function onSaveSuccess(result) {
			$scope.$emit('fmpApp:publishedFareCommissionRatesUpdate', result);
			$uibModalInstance.close(result);
			vm.isSaving = false;
		}

		function onSaveError() {
			vm.isSaving = false;
		}

	}
})();
