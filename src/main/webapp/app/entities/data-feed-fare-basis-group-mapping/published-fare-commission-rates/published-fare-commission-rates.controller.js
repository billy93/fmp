(function() {
	'use strict';

	angular.module('fmpApp').controller(
			'PublishedFareCommissionRatesController',
			PublishedFareCommissionRatesController);

	PublishedFareCommissionRatesController.$inject = [ 'PublishedFareCommissionRates' ];

	function PublishedFareCommissionRatesController(
			PublishedFareCommissionRates) {

		var vm = this;

		vm.publishedFareCommissionRates = [];

		loadAll();

		function loadAll() {
			PublishedFareCommissionRates.query(function(result) {
				vm.publishedFareCommissionRates = result;
				vm.searchQuery = null;
			});
		}
	}
})();
