(function() {
	'use strict';

	angular.module('fmpApp').controller('CityCodestoAirportCodesController',
			CityCodestoAirportCodesController);

	CityCodestoAirportCodesController.$inject = [ 'CityCodestoAirportCodes' ];

	function CityCodestoAirportCodesController(CityCodestoAirportCodes) {

		var vm = this;

		vm.airportMapingDataFeeds = [];

		loadAll();

		function loadAll() {
			CityCodestoAirportCodes.query(function(result) {
				vm.airportMapingDataFeeds = result;
				vm.searchQuery = null;
			});
		}
	}
})();
