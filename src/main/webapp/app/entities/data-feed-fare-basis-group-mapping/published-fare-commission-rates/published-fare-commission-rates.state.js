(function() {
	'use strict';

	angular.module('fmpApp').config(stateConfig);

	stateConfig.$inject = [ '$stateProvider' ];

	function stateConfig($stateProvider) {
		$stateProvider
				.state(
						'published-fare-commission-rates',
						{
							parent : 'entity',
							url : '/published-fare-commission-rates',
							data : {
								authorities : [ 'ROLE_USER' ],
								pageTitle : 'PublishedFareCommissionRates'
							},
							views : {
								'content@' : {
									templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/published-fare-commission-rates/published-fare-commission-rates.html',
									controller : 'PublishedFareCommissionRatesController',
									controllerAs : 'vm'
								}
							},
							resolve : {}
						})
				.state(
						'published-fare-commission-rates-detail',
						{
							parent : 'published-fare-commission-rates',
							url : '/published-fare-commission-rates/{id}',
							data : {
								authorities : [ 'ROLE_USER' ],
								pageTitle : 'PublishedFareCommissionRates'
							},
							views : {
								'content@' : {
									templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/published-fare-commission-rates/published-fare-commission-rates-detail.html',
									controller : 'PublishedFareCommissionRatesDetailController',
									controllerAs : 'vm'
								}
							},
							resolve : {
								entity : [
										'$stateParams',
										'PublishedFareCommissionRates',
										function($stateParams,
												PublishedFareCommissionRates) {
											return PublishedFareCommissionRates
													.get({
														id : $stateParams.id
													}).$promise;
										} ],
								previousState : [
										"$state",
										function($state) {
											var currentStateData = {
												name : $state.current.name
														|| 'published-fare-commission-rates',
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
						'published-fare-commission-rates-detail.edit',
						{
							parent : 'published-fare-commission-rates-detail',
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
													templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/published-fare-commission-rates/published-fare-commission-rates-dialog.html',
													controller : 'PublishedFareCommissionRatesDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : [
																'PublishedFareCommissionRates',
																function(
																		PublishedFareCommissionRates) {
																	return PublishedFareCommissionRates
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
						'published-fare-commission-rates.new',
						{
							parent : 'published-fare-commission-rates',
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
												}).result
												.then(
														function() {
															$state
																	.go(
																			'published-fare-commission-rates',
																			null,
																			{
																				reload : 'published-fare-commission-rates'
																			});
														},
														function() {
															$state
																	.go('published-fare-commission-rates');
														});
									} ]
						})
				.state(
						'published-fare-commission-rates.edit',
						{
							parent : 'published-fare-commission-rates',
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
													templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/published-fare-commission-rates/published-fare-commission-rates-dialog.html',
													controller : 'PublishedFareCommissionRatesDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : [
																'PublishedFareCommissionRates',
																function(
																		PublishedFareCommissionRates) {
																	return PublishedFareCommissionRates
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
																			'published-fare-commission-rates',
																			null,
																			{
																				reload : 'published-fare-commission-rates'
																			});
														}, function() {
															$state.go('^');
														});
									} ]
						})
				.state(
						'published-fare-commission-rates.delete',
						{
							parent : 'published-fare-commission-rates',
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
													templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/published-fare-commission-rates/published-fare-commission-rates-delete-dialog.html',
													controller : 'PublishedFareCommissionRatesDeleteController',
													controllerAs : 'vm',
													size : 'md',
													resolve : {
														entity : [
																'PublishedFareCommissionRates',
																function(
																		PublishedFareCommissionRates) {
																	return PublishedFareCommissionRates
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
																			'published-fare-commission-rates',
																			null,
																			{
																				reload : 'published-fare-commission-rates'
																			});
														}, function() {
															$state.go('^');
														});
									} ]
						});
	}

})();
