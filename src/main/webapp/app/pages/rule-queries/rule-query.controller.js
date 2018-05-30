(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RuleQueryController', RuleQueryController);

    RuleQueryController.$inject = ['$state', 'RuleQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'queryParams'];

    function RuleQueryController($state, RuleQuery, ParseLinks, AlertService, paginationConstants, queryParams) {
    	
    	console.log("queryParams :: ",queryParams);

        var vm = this;
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.queryParams = queryParams;
        vm.loadAll = loadAll;
        vm.getRules = getRules;
        vm.showDetail = showDetail;
        vm.hideDetail = hideDetail;
        vm.reset = reset;
        vm.page = 1;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        
        vm.loadAll();

        function loadAll () {
        	vm.queryParams.page = vm.page - 1;
        	vm.queryParams.size = vm.itemsPerPage;
        	RuleQuery.query(vm.queryParams, onSuccess, onError);
        	
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.ruleQueries = data;
                console.log("data",data);
            }
            
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function loadPage(page) {
            vm.page = page;
        }

        function getRules(ruleQuery) {
        	RuleQuery.getRules(ruleQuery, function(data) {
        		vm.ruleQueryCategories = data;
        	}, function(error) {
        		console.log(error);
        	});
        }
        
        function showDetail() {
        	$("#tblDetail").show();
        }
        
        function hideDetail() {
        	$("#tblDetail").hide();
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
        
        function reset() {
        	vm.queryParams = {
        			cxr: null,
            		ruleTarNo: null,
            		ruleNo: null,
            		type: null,
            		src: null,
            		cat: null,
            		catNo: null
        	}
        	
        	vm.loadAll();
        }
        
    }
})();
