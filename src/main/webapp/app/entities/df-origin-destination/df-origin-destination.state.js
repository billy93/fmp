(function() {
	'use strict';

	angular.module('fmpApp').config(stateConfig);

	stateConfig.$inject = [ '$stateProvider' ];

	function stateConfig($stateProvider) {
		$stateProvider
				.state(
						'df-origin-destination',
						{
							parent : 'data-feed',
							url : '/df-origin-destination?page&sort&search',
							data : {
								authorities : [ 'ROLE_USER' ],
								pageTitle : 'DFOriginDestinations'
							},
							views : {
								'content@' : {
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
						})
				.state(
						'df-origin-destination.new',
						{
							parent : 'data-feed',
							url : '/significant/new',
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
													templateUrl : 'app/entities/df-origin-destination/df-origin-destination-dialog.html',
													controller : 'DFOriginDestinationDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : function() {
															return {
																origAirport : null,
																destAirport : null,
																origCity : null,
																destCity : null,
																id : null
															};
														}
													}
												}).result.then(function() {
											$state.go('data-feed', null, {
												reload : 'data-feed'
											});
										}, function() {
											$state.go('data-feed');
										});
									} ]
						})
				.state(
						'df-origin-destination.edit',
						{
							parent : 'data-feed',
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
													templateUrl : 'app/entities/df-origin-destination/df-origin-destination-dialog.html',
													controller : 'DFOriginDestinationDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : [
																'DFOriginDestination',
																function(
																		DFOriginDestination) {
																	return DFOriginDestination
																			.get({
																				id : $stateParams.id
																			}).$promise;
																} ]
													}
												}).result.then(function() {
											$state.go('data-feed', null, {
												reload : 'data-feed'
											});
										}, function() {
											$state.go('^');
										});
									} ]
						})
				.state(
						'df-origin-destination.delete',
						{
							parent : 'data-feed',
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
													templateUrl : 'app/entities/df-origin-destination/df-origin-destination-delete-dialog.html',
													controller : 'DFOriginDestinationDeleteController',
													controllerAs : 'vm',
													size : 'md',
													resolve : {
														entity : [
																'DFOriginDestination',
																function(
																		DFOriginDestination) {
																	return DFOriginDestination
																			.get({
																				id : $stateParams.id
																			}).$promise;
																} ]
													}
												}).result.then(function() {
											$state.go('data-feed', null, {
												reload : 'data-feed'
											});
										}, function() {
											$state.go('^');
										});
									} ]
						});
	}

})();
