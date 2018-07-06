(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RuleQueriesController', RuleQueriesController);

    RuleQueriesController.$inject = ['$state', '$stateParams', 'RuleQuery', 'FareClassQuery', 'Rec8FareByRule'];

    function RuleQueriesController($state, $stateParams, RuleQuery, FareClassQuery, Rec8FareByRule) {
    	var vm = this;
		vm.ruleQueries = RuleQuery;
		
		vm.tab = $stateParams.tab;

		vm.selectDataFeedTab = function(tab) {
			if (tab == 'ruleQuery') {
				vm.tab = 1;
			} else if (tab == 'fareClassQuery') {
				vm.tab = 2;
			} else if (tab == 'rec8FareByRule') {
				vm.tab = 3;
			}
		}
    }
})();
