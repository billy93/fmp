(function() {
	'use strict';

	angular.module('fmpApp').config(stateConfig);

	stateConfig.$inject = [ '$stateProvider' ];

	function stateConfig($stateProvider) {
		$stateProvider
				.state(
						'data-feed',
						{
							parent : 'entity',
							url : '/data-feed/{tab}',
							views : {
								'content@' : {
									templateUrl : 'app/entities/data-feed/data-feed.html',
									controller : 'DataFeedController',
									controllerAs : 'vm'
								},
								'fare-basis-group-mapping@data-feed' : {
									templateUrl : 'app/entities/df-fare-basis-group-mapping/df-fare-basis-group-mappings.html',
									controller : 'DFFareBasisGroupMappingController',
									controllerAs : 'vm'
								},
								'city-codes-to-airport-codes@data-feed' : {
									templateUrl : 'app/entities/df-airport-mapping/df-airport-mappings.html',
									controller : 'DFAirportMappingController',
									controllerAs : 'vm'
								},
								'published-fare-commission-rates@data-feed' : {
									templateUrl : 'app/entities/df-published-fare-rates/df-published-fare-rates.html',
									controller : 'DFPublishedFareRatesController',
									controllerAs : 'vm'
								},
								'significant-od@data-feed' : {
									templateUrl : 'app/entities/df-origin-destination/df-origin-destinations.html',
									controller : 'DFOriginDestinationController',
									controllerAs : 'vm'
								}
							},
							params : {
								page : {
									value : '1',
									squash : true
								},
								sort : {
									value : 'id,asc',
									squash : true
								},
								search : null
							},
							resolve : {
								pagingParams : [
										'$stateParams',
										'PaginationUtil',
										function($stateParams, PaginationUtil) {
											return {
												page : PaginationUtil
														.parsePage($stateParams.page),
												sort : $stateParams.sort,
												predicate : PaginationUtil
														.parsePredicate($stateParams.sort),
												ascending : PaginationUtil
														.parseAscending($stateParams.sort),
												search : $stateParams.search
											};
										} ],
							}
						});
	}

})();
