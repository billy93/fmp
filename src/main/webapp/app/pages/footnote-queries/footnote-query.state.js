(function() {
    'use strict';

    angular
        .module('fmpApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('footnote-query', {
        	parent: 'app',
            url: '/footnote-query',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Footnote Query'
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/footnote-queries/footnote-queries.html',
                    controller: 'FootnoteQueryController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	queryParams: [function() {
            		return {
            			cxr: null,
                		ftnt: null,
                		tarNo: null,
                		catNo: null,
                		saleDateFrom: null,
                		saleDateTo: null,
                		saleDateType : "1",
                		travelDateFrom: null,
                		travelDateTo: null,
                		travelDateType : "1",
                		completedDateFrom: null,
                		travelOpt: "2",
                		includeDisc : null
                	}
            	}]
            }
        })
    }

})();
