(function() {
	'use strict';

	angular.module('fmpApp').controller(
			'DataFeedFareBasisGroupMappingController',
			DataFeedFareBasisGroupMappingController);

	DataFeedFareBasisGroupMappingController.$inject = [
			'DataFeedFareBasisGroupMapping', 'CityCodestoAirportCodes',
			'PublishedFareCommissionRates', 'OriginDestination', '$uibModal',
			'$state', '$stateParams' ];

	function DataFeedFareBasisGroupMappingController(
			DataFeedFareBasisGroupMapping, CityCodestoAirportCodes,
			PublishedFareCommissionRates, OriginDestination, $uibModal, $state,
			$stateParams) {

		var vm = this;
		vm.dataFeedFareBasisGroupMappings = [];

		loadAll();

		function loadAll() {
			DataFeedFareBasisGroupMapping.query(function(result) {
				vm.dataFeedFareBasisGroupMappings = result;
				vm.searchQuery = null;
			});
		}

		vm.publishedFareCommissionRates = [];

		loadAllPublished();

		function loadAllPublished() {
			PublishedFareCommissionRates.query(function(result) {
				vm.publishedFareCommissionRates = result;
				vm.searchQuery = null;
			});
		}

		vm.airportMapingDataFeeds = [];

		loadAllCity();

		function loadAllCity() {
			CityCodestoAirportCodes.query(function(result) {
				vm.airportMapingDataFeeds = result;
				vm.searchQuery = null;
			});
		}

		vm.originDestination = [];

		loadAllOrigin();

		function loadAllOrigin() {
			OriginDestination.query(function(result) {
				vm.originDestination = result;
				vm.searchQuery = null;
			});
		}

		// City Codes

		vm.addCity = function() {
			$uibModal
					.open({
						templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/city-codes-to-airport-codes/city-codes-to-airport-codes-dialog.html',
						controller : 'CityCodestoAirportCodesDialogController',
						controllerAs : 'vm',
						backdrop : 'static',
						size : 'lg',
						resolve : {
							entity : function() {
								return {
									cityCode : null,
									airportCode : null,
									countryCode : null,
									id : null
								};
							}
						}
					}).result.then(function(option) {
				vm.currentTabCityCodes = true;
			}, function() {
			});
		}

		vm.editCity = function(id) {
			$uibModal
					.open({
						templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/city-codes-to-airport-codes/city-codes-to-airport-codes-dialog.html',
						controller : 'CityCodestoAirportCodesDialogController',
						controllerAs : 'vm',
						backdrop : 'static',
						size : 'lg',
						resolve : {
							entity : [ 'CityCodestoAirportCodes',
									function(CityCodestoAirportCodes) {
										return CityCodestoAirportCodes.get({
											id : id
										}).$promise;
									} ]
						}
					}).result.then(function(option) {
				vm.currentTabCityCodes = true;
			}, function() {
			});
		}

		vm.deleteCity = function(id) {
			$uibModal
					.open({
						templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/city-codes-to-airport-codes/city-codes-to-airport-codes-delete-dialog.html',
						controller : 'CityCodestoAirportCodesDeleteController',
						controllerAs : 'vm',
						size : 'md',
						resolve : {
							entity : [ 'CityCodestoAirportCodes',
									function(CityCodestoAirportCodes) {
										return CityCodestoAirportCodes.get({
											id : id
										}).$promise;
									} ]
						}
					}).result.then(function(option) {
				vm.currentTabCityCodes = true;
			}, function() {
			});
		}

		// end City Codes

		// Published

		vm.addPublished = function() {
			$uibModal
					.open({
						templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/published-fare-commission-rates/published-fare-commission-rates-dialog.html',
						controller : 'PublishedFareCommissionRatesDialogController',
						controllerAs : 'vm',
						backdrop : 'static',
						size : 'lg',
						resolve : {
							entity : function() {
								return {
									posCountry : null,
									travelDateFrom : null,
									travelDateTo : null,
									rate : null,
									id : null
								};
							}
						}
					}).result.then(function(option) {
				vm.currentTabPublishedFare = true;
			}, function() {
			});
		}

		vm.editPublished = function(id) {
			$uibModal
					.open({
						templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/published-fare-commission-rates/published-fare-commission-rates-dialog.html',
						controller : 'PublishedFareCommissionRatesDialogController',
						controllerAs : 'vm',
						backdrop : 'static',
						size : 'lg',
						resolve : {
							entity : [
									'PublishedFareCommissionRates',
									function(PublishedFareCommissionRates) {
										return PublishedFareCommissionRates
												.get({
													id : id
												}).$promise;
									} ]
						}
					}).result.then(function(option) {
				vm.currentTabPublishedFare = true;
			}, function() {
			});
		}

		vm.deletePublished = function(id) {
			$uibModal
					.open({
						templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/published-fare-commission-rates/published-fare-commission-rates-delete-dialog.html',
						controller : 'PublishedFareCommissionRatesDeleteController',
						controllerAs : 'vm',
						size : 'md',
						resolve : {
							entity : [
									'PublishedFareCommissionRates',
									function(PublishedFareCommissionRates) {
										return PublishedFareCommissionRates
												.get({
													id : id
												}).$promise;
									} ]
						}
					}).result.then(function(option) {
				vm.currentTabPublishedFare = true;
			}, function() {
			});
		}

		// end Published

		// Origin

		vm.addOrigin = function() {
			$uibModal
					.open({
						templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/origin-destination/origin-destination-dialog.html',
						controller : 'OriginDestinationDialogController',
						controllerAs : 'vm',
						backdrop : 'static',
						size : 'lg',
						resolve : {
							entity : function() {
								return {
									origCity : null,
									destCity : null,
									origAirport : null,
									destAirport : null,
									id : null
								};
							}
						}
					}).result.then(function(option) {
				vm.currentTabSignificant = true;
			}, function() {
			});
		}

		vm.editOrigin = function(id) {
			$uibModal
					.open({
						templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/origin-destination/origin-destination-dialog.html',
						controller : 'OriginDestinationDialogController',
						controllerAs : 'vm',
						backdrop : 'static',
						size : 'lg',
						resolve : {
							entity : [ 'OriginDestination',
									function(OriginDestination) {
										return OriginDestination.get({
											id : id
										}).$promise;
									} ]
						}
					}).result.then(function(option) {
				vm.currentTabSignificant = true;
			}, function() {
			});
		}

		vm.deleteOrigin = function(id) {
			$uibModal
					.open({
						templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/origin-destination/origin-destination-delete-dialog.html',
						controller : 'OriginDestinationDeleteController',
						controllerAs : 'vm',
						size : 'md',
						resolve : {
							entity : [ 'OriginDestination',
									function(OriginDestination) {
										return OriginDestination.get({
											id : id
										}).$promise;
									} ]
						}
					}).result.then(function(option) {
				vm.currentTabSignificant = true;
			}, function() {
			});
		}

		// end Origin

		vm.currentTabFareBasis = true;
		vm.selectDataFeedTab = function(tab) {
			if (tab == 'fareBasis') {
				vm.currentTabFareBasis = true;
				vm.currentTabCityCodes = false;
				vm.currentTabPublishedFare = false;
				vm.currentTabSignificant = false;
			} else if (tab == 'cityCodes') {
				vm.currentTabFareBasis = false;
				vm.currentTabCityCodes = true;
				vm.currentTabPublishedFare = false;
				vm.currentTabSignificant = false;
			} else if (tab == 'publishedFare') {
				vm.currentTabFareBasis = false;
				vm.currentTabCityCodes = false;
				vm.currentTabPublishedFare = true;
				vm.currentTabSignificant = false;
			} else if (tab == 'significant') {
				vm.currentTabFareBasis = false;
				vm.currentTabCityCodes = false;
				vm.currentTabPublishedFare = false;
				vm.currentTabSignificant = true;
			}
		}
	}
})();
