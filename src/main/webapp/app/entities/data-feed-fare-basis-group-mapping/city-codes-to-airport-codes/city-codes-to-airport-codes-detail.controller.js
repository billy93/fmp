(function() {
	'use strict';

	angular.module('fmpApp').controller(
			'CityCodestoAirportCodesDetailController',
			CityCodestoAirportCodesDetailController);

	CityCodestoAirportCodesDetailController.$inject = [ '$scope', '$rootScope',
			'$stateParams', 'previousState', 'entity',
			'CityCodestoAirportCodes' ];

	function CityCodestoAirportCodesDetailController($scope, $rootScope,
			$stateParams, previousState, entity, CityCodestoAirportCodes) {
		var vm = this;

		vm.airportMapingDataFeed = entity;
		vm.previousState = previousState.name;

		var unsubscribe = $rootScope.$on(
				'fmpApp:cityCodestoAirportCodesUpdate',
				function(event, result) {
					vm.airportMapingDataFeed = result;
				});
		$scope.$on('$destroy', unsubscribe);
	}
})();
