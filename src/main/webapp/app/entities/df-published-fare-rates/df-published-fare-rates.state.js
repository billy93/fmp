(function() {
	'use strict';

	angular.module('fmpApp').config(stateConfig);

	stateConfig.$inject = [ '$stateProvider' ];

	function stateConfig($stateProvider) {
		$stateProvider
				.state(
						'df-published-fare-rates',
						{
							parent : 'data-feed',
							url : '/df-published-fare-rates?page&sort&search',
							data : {
								authorities : [ 'ROLE_USER' ],
								pageTitle : 'DFPublishedFareRates'
							},
							views : {
								'content@' : {
									templateUrl : 'app/entities/df-published-fare-rates/df-published-fare-rates.html',
									controller : 'DFPublishedFareRatesController',
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
						'df-published-fare-rates.new',
						{
							parent : 'data-feed',
							url : '/published/new',
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
													templateUrl : 'app/entities/df-published-fare-rates/df-published-fare-rates-dialog.html',
													controller : 'DFPublishedFareRatesDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : function() {
															return {
																postCountry : null,
																travelDateFrom : null,
																travelDateTo : null,
																rate : null,
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
						'df-published-fare-rates.edit',
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
													templateUrl : 'app/entities/df-published-fare-rates/df-published-fare-rates-dialog.html',
													controller : 'DFPublishedFareRatesDialogController',
													controllerAs : 'vm',
													backdrop : 'static',
													size : 'lg',
													resolve : {
														entity : [
																'DFPublishedFareRates',
																function(
																		DFPublishedFareRates) {
																	return DFPublishedFareRates
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
						'df-published-fare-rates.delete',
						{
							parent : 'df-published-fare-rates',
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
													templateUrl : 'app/entities/df-published-fare-rates/df-published-fare-rates-delete-dialog.html',
													controller : 'DFPublishedFareRatesDeleteController',
													controllerAs : 'vm',
													size : 'md',
													resolve : {
														entity : [
																'DFPublishedFareRates',
																function(
																		DFPublishedFareRates) {
																	return DFPublishedFareRates
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
