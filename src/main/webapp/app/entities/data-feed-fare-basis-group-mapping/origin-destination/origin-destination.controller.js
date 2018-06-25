(function() {
	'use strict';

	angular.module('fmpApp').controller('OriginDestinationController',
			OriginDestinationController);

	OriginDestinationController.$inject = [ 'OriginDestination' ];

	function OriginDestinationController(OriginDestination) {

		var vm = this;

		vm.originDestination = [];

		loadAll();

		function loadAll() {
			OriginDestination.query(function(result) {
				vm.originDestination = result;
				vm.searchQuery = null;
			});
		}
	}
})();
