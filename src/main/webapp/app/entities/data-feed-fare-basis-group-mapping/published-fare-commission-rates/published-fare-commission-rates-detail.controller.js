(function() {
	'use strict';

	angular.module('fmpApp').controller(
			'PublishedFareCommissionRatesDetailController',
			PublishedFareCommissionRatesDetailController);

	PublishedFareCommissionRatesDetailController.$inject = [ '$scope',
			'$rootScope', '$stateParams', 'previousState', 'entity',
			'PublishedFareCommissionRates' ];

	function PublishedFareCommissionRatesDetailController($scope, $rootScope,
			$stateParams, previousState, entity, PublishedFareCommissionRates) {
		var vm = this;

		vm.publishedFareCommissionRates = entity;
		vm.previousState = previousState.name;

		var unsubscribe = $rootScope.$on(
				'fmpApp:publishedFareCommissionRatesUpdate', function(event,
						result) {
					vm.publishedFareCommissionRates = result;
				});
		$scope.$on('$destroy', unsubscribe);
	}
})();
