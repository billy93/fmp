(function() {
	'use strict';

	angular.module('fmpApp').config(stateConfig);

	stateConfig.$inject = [ '$stateProvider' ];

	function stateConfig($stateProvider) {
		$stateProvider
				.state(
						'origin-destination',
						{
							parent : 'entity',
							url : '/origin-destination',
							data : {
								authorities : [ 'ROLE_USER' ],
								pageTitle : 'OriginDestination'
							},
							views : {
								'content@' : {
									templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/origin-destination/origin-destination.html',
									controller : 'OriginDestinationController',
									controllerAs : 'vm'
								}
							},
							resolve : {}
						})
				.state(
						'origin-destination-detail',
						{
							parent : 'origin-destination',
							url : '/origin-destination/{id}',
							data : {
								authorities : [ 'ROLE_USER' ],
								pageTitle : 'OriginDestination'
							},
							views : {
								'content@' : {
									templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/origin-destination/origin-destination-detail.html',
									controller : 'OriginDestinationDetailController',
									controllerAs : 'vm'
								}
							},
							resolve : {
								entity : [
										'$stateParams',
										'OriginDestination',
										function($stateParams,
												OriginDestination) {
											return OriginDestination.get({
												id : $stateParams.id
											}).$promise;
										} ],
								previousState : [
										"$state",
										function($state) {
											var currentStateData = {
												name : $state.current.name
														|| 'origin-destination',
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
						'origin-destination-detail.edit',
						{
							parent : 'origin-destination-detail',
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
													templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/origin-destination/origin-destination-dialog.html',
													controller : 'OriginDestinationDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : [
																'OriginDestination',
																function(
																		OriginDestination) {
																	return OriginDestination
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
						'origin-destination.new',
						{
							parent : 'origin-destination',
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
												}).result
												.then(
														function() {
															$state
																	.go(
																			'origin-destination',
																			null,
																			{
																				reload : 'origin-destination'
																			});
														},
														function() {
															$state
																	.go('origin-destination');
														});
									} ]
						})
				.state(
						'origin-destination.edit',
						{
							parent : 'origin-destination',
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
													templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/origin-destination/origin-destination-dialog.html',
													controller : 'OriginDestinationDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : [
																'OriginDestination',
																function(
																		OriginDestination) {
																	return OriginDestination
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
																			'origin-destination',
																			null,
																			{
																				reload : 'origin-destination'
																			});
														}, function() {
															$state.go('^');
														});
									} ]
						})
				.state(
						'origin-destination.delete',
						{
							parent : 'origin-destination',
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
													templateUrl : 'app/entities/data-feed-fare-basis-group-mapping/origin-destination/origin-destination-delete-dialog.html',
													controller : 'OriginDestinationDeleteController',
													controllerAs : 'vm',
													size : 'md',
													resolve : {
														entity : [
																'OriginDestination',
																function(
																		OriginDestination) {
																	return OriginDestination
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
																			'origin-destination',
																			null,
																			{
																				reload : 'origin-destination'
																			});
														}, function() {
															$state.go('^');
														});
									} ]
						});
	}

})();
