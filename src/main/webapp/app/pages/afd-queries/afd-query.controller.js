(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AfdQueryController', AfdQueryController);

    AfdQueryController.$inject = ['$state', 'AfdQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'queryParams'];

    function AfdQueryController($state, AfdQuery, ParseLinks, AlertService, paginationConstants, pagingParams, queryParams) {

        var vm = this;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        
        vm.sources = [
        	{key: "A", value: "ATPCO"},
        	{key: "M", value: "Market"},
        	{key: "W", value: "Web"},
        	{key: "C", value: "Competitor Private"}
        ]
        
        vm.publicPrivate = [
    		"Public",
    		"Private"
        ]
        
        vm.owrts = [
    		{key: "1", value: "One Way"},
    		{key: "2", value: "Rount Trip"},
    		{key: "3", value: "One Way Only"},
        ]
        
        vm.dateOptions = [
        	"Active In",
        	"Exact Match"
        ]
        
        vm.tariffs = "?";
        
        vm.globalIndicators = "?";
        
        vm.gaFareTypes = "?";
        
        vm.fareTypes = "?";
        
        vm.paxTypes = "?";
        
        vm.cabins = "?";
        
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.queryParams = queryParams;
        vm.loadAll = loadAll;
        
        vm.loadAll();

        function loadAll () {
        	console.log(vm.queryParams);
        	
        	AfdQuery.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort(),
                queryParam: vm.queryParams
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.afdQueries = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function loadPage(page) {
            vm.page = page;
            vm.transition();
        }

        function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
        
        vm.getRules = function(afdQuery) {
        	AfdQuery.getRules(afdQuery, function(data) {
        		console.log(data);
        	}, function(error) {
        		console.log(error);
        	});
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
