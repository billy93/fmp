(function() {
	'use strict';

	angular.module('fmpApp').controller('OriginDestinationDetailController',
			OriginDestinationDetailController);

	OriginDestinationDetailController.$inject = [ '$scope', '$rootScope',
			'$stateParams', 'previousState', 'entity', 'OriginDestination' ];

	function OriginDestinationDetailController($scope, $rootScope,
			$stateParams, previousState, entity, OriginDestination) {
		var vm = this;

		vm.originDestination = entity;
		vm.previousState = previousState.name;

		var unsubscribe = $rootScope.$on('fmpApp:originDestinationUpdate',
				function(event, result) {
					vm.originDestination = result;
				});
		$scope.$on('$destroy', unsubscribe);
	}
})();
