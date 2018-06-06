(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FootnoteQueryController', FootnoteQueryController);

    FootnoteQueryController.$inject = ['$state', 'FootnoteQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'queryParams', '$uibModal'];

    function FootnoteQueryController($state, FootnoteQuery, ParseLinks, AlertService, paginationConstants, queryParams, $uibModal) {
    	
        var vm = this;
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.queryParams = queryParams;
        vm.loadAll = loadAll;
        vm.getFtnt = getFtnt;
        vm.getFtnt2 = getFtnt2;
        vm.reset = reset;
        vm.page = 1;
        vm.showCategoryDetail = showCategoryDetail;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        
        function loadAll () {
        	vm.queryParams.page = vm.page - 1;
        	vm.queryParams.size = vm.itemsPerPage;
        	
        	FootnoteQuery.query(vm.queryParams, onSuccess, onError);
        	
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.footnoteQueries = data;
            }
            
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function loadPage(page) {
            vm.page = page;
        }

        function getFtnt(footnoteQuery) {
        	FootnoteQuery.getFtnt(footnoteQuery, function(data) {
        		vm.footnoteQueryCategories = data;
        	}, function(error) {
        		console.log(error);
        	});
        }
        
        function getFtnt2(footnoteQuery) {
        	FootnoteQuery.getFtnt2(footnoteQuery, function(data) {
        		vm.footnoteQueryCategories2 = data;
        	}, function(error) {
        		console.log(error);
        	});
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
        
        function reset() {
        	vm.queryParams = {
    			cxr: null,
        		ftnt: null,
        		tarNo: null,
        		catNo: null,
        		saleDateFrom: null,
        		saleDateTo: null,
        		travelDateFrom: null,
        		travelDateTo: null,
        		completedDateFrom: null,
        		travelOpt: null
        	}
        	
        	vm.loadAll();
        }
        
        function showCategoryDetail(category) {
        	$uibModal.open({
                templateUrl: 'app/pages/category-modals/category-modal.html',
                controller: 'CategoryModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                    entity: category
                }
            }).result.then(function() {
                $state.go('footnote-query', {}, { reload: false });
            }, function() {
                $state.go('footnote-query');
            });
        }
    }
})();
