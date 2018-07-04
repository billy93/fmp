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
            url: '/footnote-query?cxr&tariff',
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
            params: {
            	cxr:null,
            	tariff:null
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
                		saleDateType : null,
                		travelDateFrom: null,
                		travelDateTo: null,
                		travelDateType : null,
                		completedDateFrom: null,
                		travelOpt: null,
                		includeDisc : null
                	}
            	}]
            }
        })
    }

})();
