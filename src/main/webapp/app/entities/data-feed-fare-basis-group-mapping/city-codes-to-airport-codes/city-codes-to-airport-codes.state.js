(function() {
	'use strict';

	angular.module('fmpApp').config(stateConfig);

	stateConfig.$inject = [ '$stateProvider' ];

	function stateConfig($stateProvider) {
		$stateProvider
				.state(
						'city-codes-to-airport-codes',
						{
							parent : 'entity',
							url : '/city-codes-to-airport-codes',
							data : {
								authorities : [ 'ROLE_USER' ],
								pageTitle : 'CityCodestoAirportCodes'
							},
							views : {
								'content@' : {
									templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/city-codes-to-airport-codes/city-codes-to-airport-codes.html',
									controller : 'CityCodestoAirportCodesController',
									controllerAs : 'vm'
								}
							},
							resolve : {}
						})
				.state(
						'city-codes-to-airport-codes-detail',
						{
							parent : 'city-codes-to-airport-codes',
							url : '/city-codes-to-airport-codes/{id}',
							data : {
								authorities : [ 'ROLE_USER' ],
								pageTitle : 'CityCodestoAirportCodes'
							},
							views : {
								'content@' : {
									templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/city-codes-to-airport-codes/city-codes-to-airport-codes-detail.html',
									controller : 'CityCodestoAirportCodesDetailController',
									controllerAs : 'vm'
								}
							},
							resolve : {
								entity : [
										'$stateParams',
										'CityCodestoAirportCodes',
										function($stateParams,
												CityCodestoAirportCodes) {
											return CityCodestoAirportCodes
													.get({
														id : $stateParams.id
													}).$promise;
										} ],
								previousState : [
										"$state",
										function($state) {
											var currentStateData = {
												name : $state.current.name
														|| 'city-codes-to-airport-codes',
												params : $state.params,
												url : $state.href(
														$state.current.name,
														$state.params)
											};
											return currentStateData;
										} ]
							}
						})
				.state(
						'city-codes-to-airport-codes-detail.edit',
						{
							parent : 'city-codes-to-airport-codes-detail',
							url : '/detail/edit',
							data : {
								authorities : [ 'ROLE_USER' ]
							},
							onEnter : [
									'$stateParams',
									'$state',
									'$uibModal',
									function($stateParams, $state, $uibModal) {
										$uibModal
												.open({
													templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/city-codes-to-airport-codes/city-codes-to-airport-codes-dialog.html',
													controller : 'CityCodestoAirportCodesDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : [
																'CityCodestoAirportCodes',
																function(
																		CityCodestoAirportCodes) {
																	return CityCodestoAirportCodes
																			.get({
																				id : $stateParams.id
																			}).$promise;
																} ]
													}
												}).result.then(function() {
											$state.go('^', {}, {
												reload : false
											});
										}, function() {
											$state.go('^');
										});
									} ]
						})
				.state(
						'city-codes-to-airport-codes.new',
						{
							parent : 'city-codes-to-airport-codes',
							url : '/new',
							data : {
								authorities : [ 'ROLE_USER' ]
							},
							onEnter : [
									'$stateParams',
									'$state',
									'$uibModal',
									function($stateParams, $state, $uibModal) {
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
												}).result
												.then(
														function() {
															$state
																	.go(
																			'city-codes-to-airport-codes',
																			null,
																			{
																				reload : 'city-codes-to-airport-codes'
																			});
														},
														function() {
															$state
																	.go('city-codes-to-airport-codes');
														});
									} ]
						})
				.state(
						'city-codes-to-airport-codes.edit',
						{
							parent : 'city-codes-to-airport-codes',
							url : '/{id}/edit',
							data : {
								authorities : [ 'ROLE_USER' ]
							},
							onEnter : [
									'$stateParams',
									'$state',
									'$uibModal',
									function($stateParams, $state, $uibModal) {
										$uibModal
												.open({
													templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/city-codes-to-airport-codes/city-codes-to-airport-codes-dialog.html',
													controller : 'CityCodestoAirportCodesDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : [
																'CityCodestoAirportCodes',
																function(
																		CityCodestoAirportCodes) {
																	return CityCodestoAirportCodes
																			.get({
																				id : $stateParams.id
																			}).$promise;
																} ]
													}
												}).result
												.then(
														function() {
															$state
																	.go(
																			'city-codes-to-airport-codes',
																			null,
																			{
																				reload : 'city-codes-to-airport-codes'
																			});
														}, function() {
															$state.go('^');
														});
									} ]
						})
				.state(
						'city-codes-to-airport-codes.delete',
						{
							parent : 'city-codes-to-airport-codes',
							url : '/{id}/delete',
							data : {
								authorities : [ 'ROLE_USER' ]
							},
							onEnter : [
									'$stateParams',
									'$state',
									'$uibModal',
									function($stateParams, $state, $uibModal) {
										$uibModal
												.open({
													templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/city-codes-to-airport-codes/city-codes-to-airport-codes-delete-dialog.html',
													controller : 'CityCodestoAirportCodesDeleteController',
													controllerAs : 'vm',
													size : 'md',
													resolve : {
														entity : [
																'CityCodestoAirportCodes',
																function(
																		CityCodestoAirportCodes) {
																	return CityCodestoAirportCodes
																			.get({
																				id : $stateParams.id
																			}).$promise;
																} ]
													}
												}).result
												.then(
														function() {
															$state
																	.go(
																			'city-codes-to-airport-codes',
																			null,
																			{
																				reload : 'city-codes-to-airport-codes'
																			});
														}, function() {
															$state.go('^');
														});
									} ]
						});
	}

})();
