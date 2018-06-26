(function() {
	'use strict';

	angular.module('fmpApp').controller('DataFeedController',
			DataFeedController);

	DataFeedController.$inject = [ '$state', 'DFFareBasisGroupMapping',
			'DFAirportMapping', 'DFOriginDestination', 'DFPublishedFareRates',
			'$stateParams' ];

	function DataFeedController($state, DFFareBasisGroupMapping,
			DFAirportMapping, DFOriginDestination, DFPublishedFareRates,
			$stateParams) {

		var vm = this;
		vm.dFFareBasisGroupMappings = DFFareBasisGroupMapping;

		vm.tab = $stateParams.tab;

		vm.selectDataFeedTab = function(tab) {
			if (tab == 'fareBasis') {
				vm.tab = 1;
			} else if (tab == 'cityCodes') {
				vm.tab = 2;
			} else if (tab == 'publishedFare') {
				vm.tab = 3;
			} else if (tab == 'significant') {
				vm.tab = 4;
			}
		}
	}
})();
