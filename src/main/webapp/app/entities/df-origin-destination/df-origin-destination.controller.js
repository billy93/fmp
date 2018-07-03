(function() {
	'use strict';

	angular.module('fmpApp').controller('DFOriginDestinationController',
			DFOriginDestinationController);

	DFOriginDestinationController.$inject = [ '$state', 'DFOriginDestination',
			'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams' ];

	function DFOriginDestinationController($state, DFOriginDestination,
			ParseLinks, AlertService, paginationConstants, pagingParams) {

		var vm = this;

		vm.predicate = pagingParams.predicate;
		vm.reverse = pagingParams.ascending;
		vm.itemsPerPage = paginationConstants.itemsPerPage;
		vm.page = pagingParams.page;
		vm.loadAll = loadAll;

		loadAll();

		function loadAll() {
			DFOriginDestination.query({
				page : vm.page - 1,
				size : vm.itemsPerPage,
				sort : sort()
			}, onSuccess, onError);
			function sort() {
				var result = [ vm.predicate + ','
						+ (vm.reverse ? 'asc' : 'desc') ];
				if (vm.predicate !== 'id') {
					result.push('id');
				}
				return result;
			}
			function onSuccess(data, headers) {
				vm.links = ParseLinks.parse(headers('link'));
				vm.totalItems = headers('X-Total-Count');
				vm.queryCount = vm.totalItems;
				vm.dFOriginDestinations = data;
			}
			function onError(error) {
				AlertService.error(error.data.message);
			}
		}
	}
})();
