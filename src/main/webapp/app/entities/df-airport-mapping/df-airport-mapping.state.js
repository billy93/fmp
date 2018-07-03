(function() {
	'use strict';

	angular.module('fmpApp').config(stateConfig);

	stateConfig.$inject = [ '$stateProvider' ];

	function stateConfig($stateProvider) {
		$stateProvider
				.state(
						'df-airport-mapping',
						{
							parent : 'data-feed',
							url : '/df-airport-mapping?page&sort&search',
							data : {
								authorities : [ 'ROLE_USER' ],
								pageTitle : 'DFAirportMappings'
							},
							views : {
								'content@' : {
									templateUrl : 'app/entities/df-airport-mapping/df-airport-mappings.html',
									controller : 'DFAirportMappingController',
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
						'df-airport-mapping.new',
						{
							parent : 'data-feed',
							url : '/airport/new',
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
													templateUrl : 'app/entities/df-airport-mapping/df-airport-mapping-dialog.html',
													controller : 'DFAirportMappingDialogController',
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
						'df-airport-mapping.edit',
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
													templateUrl : 'app/entities/df-airport-mapping/df-airport-mapping-dialog.html',
													controller : 'DFAirportMappingDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : [
																'DFAirportMapping',
																function(
																		DFAirportMapping) {
																	return DFAirportMapping
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
						'df-airport-mapping.delete',
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
													templateUrl : 'app/entities/df-airport-mapping/df-airport-mapping-delete-dialog.html',
													controller : 'DFAirportMappingDeleteController',
													controllerAs : 'vm',
													size : 'md',
													resolve : {
														entity : [
																'DFAirportMapping',
																function(
																		DFAirportMapping) {
																	return DFAirportMapping
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
