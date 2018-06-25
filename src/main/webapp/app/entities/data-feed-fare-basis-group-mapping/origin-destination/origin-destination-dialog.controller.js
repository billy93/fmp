(function() {
	'use strict';

	angular.module('fmpApp').controller('OriginDestinationDialogController',
			OriginDestinationDialogController);

	OriginDestinationDialogController.$inject = [ '$timeout', '$scope',
			'$stateParams', '$uibModalInstance', 'entity', 'OriginDestination' ];

	function OriginDestinationDialogController($timeout, $scope, $stateParams,
			$uibModalInstance, entity, OriginDestination) {
		var vm = this;

		vm.originDestination = entity;
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
			if (vm.originDestination.id !== null) {
				OriginDestination.update(vm.originDestination, onSaveSuccess,
						onSaveError);
			} else {
				OriginDestination.save(vm.originDestination, onSaveSuccess,
						onSaveError);
			}
		}

		function onSaveSuccess(result) {
			$scope.$emit('fmpApp:originDestinationUpdate', result);
			$uibModalInstance.close(result);
			vm.isSaving = false;
		}

		function onSaveError() {
			vm.isSaving = false;
		}

	}
})();
