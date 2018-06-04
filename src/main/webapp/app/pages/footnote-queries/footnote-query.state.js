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
                		type: null,
                		cat: null,
                		catNo: null,
                		fareAddon: null,
                		trcd: null,
                		fareAddonCount: null
                	}
            	}]
            }
        })
    }

})();
