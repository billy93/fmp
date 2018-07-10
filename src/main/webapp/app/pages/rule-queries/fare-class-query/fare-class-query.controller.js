(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FareClassQueryController', FareClassQueryController);

    FareClassQueryController.$inject = ['$state', '$stateParams', 'FareClassQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', '$uibModal'];

    function FareClassQueryController($state, $stateParams, FareClassQuery, ParseLinks, AlertService, paginationConstants, pagingParams, $uibModal) {

        var vm = this;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.loadAll = loadAll;
        vm.clearFilter = clearFilter;
        vm.resetFilter = resetFilter;
        vm.page = 1;
        vm.getFareClassQueries = getFareClassQueries;
        vm.fareClassGroupText = fareClassGroupText;
        vm.constructDetails = constructDetails;
        vm.getFareClassDetails = getFareClassDetails;
        
        if($stateParams.fareClasssQueryFilter != null){
        	vm.queryParams = $stateParams.fareClasssQueryFilter;
        	vm.itemsPerPage = vm.queryParams.size;
        } else {
        	vm.clearFilter();
        	vm.itemsPerPage = "10";
        }
        
        function loadAll() {
        	vm.queryParams.page = vm.page - 1;
			vm.queryParams.size = vm.itemsPerPage;
			
			FareClassQuery.query(vm.queryParams, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                
                vm.fareClassGroups = data;
                if(vm.fareClassGroups.length > 0) {
                	vm.selectedRow = vm.fareClassGroups[0];
                    getFareClassQueries(vm.selectedRow);
                } else {
                	vm.fareClassQueries = null;
                	vm.fareClassSelectedRow = null;
                	vm.fareClassDetails = null;
                }
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function clearFilter() {
        	vm.queryParams = {
        			cxr: null,
        			ruleNo: null,
        			fareClass: null,
        			tarNo: null,
        			psgrType: null,
        			fareType: null,
        			bookingClass: null
        	};
        }
        
        function resetFilter() {
        	vm.clearFilter();
        	vm.fareClassGroups = null;
        	vm.fareClassQueries = null;
        	vm.fareClassSelectedRow = null;
        	vm.fareClassDetails = null;
        }
        
        function getFareClassQueries(fareClassGroup) {
        	fareClassGroup.fareClass = vm.queryParams.fareClass;
        	fareClassGroup.psgrType = vm.queryParams.psgrType;
        	fareClassGroup.fareType = vm.queryParams.fareType;
        	fareClassGroup.bookingClass = vm.queryParams.bookingClass;
        	
        	FareClassQuery.getFareClassGroups(fareClassGroup, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.fareClassQueries = data;
                vm.fareClassSelectedRow = vm.fareClassQueries[0];
            	vm.fareClassDetails = [vm.fareClassSelectedRow];
            	
            	$(document).ready(function(){
            		var _parents = $('.table-afd').find('thead');
            		var _th = _parents.find('.th-fixed');
            		var _tr = _parents.siblings('tbody').find('tr:first-child');
            		var _td = _tr.find('td');
            		var _length = _th.length;
            		_th.last().css('border-right','none');
            		for(var i=0;i<_length;i++){
            			var _width = _th.eq(i).outerWidth();
            			var _width2 = _td.eq(i).outerWidth();
            			if(_width > _width2){
            				_td.eq(i).css('min-width', _width);
            				_td.eq(i).css('width', _width);
            			}
            			else{
            				_th.eq(i).css('min-width', _width2);
            				_th.eq(i).css('width', _width2);
            			}
            		}
            	});
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
            
            vm.fareClassDetails = null;
        }
        
        function fareClassGroupText() {
        	$uibModal.open({
                templateUrl: 'app/pages/rule-queries/fare-class-query/fare-class-query-text-dialog.html',
                controller: 'FareClassTextController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                	entity: vm.fareClassSelectedRow
                }
            }).result.then(function() {
                $state.go('rule-queries', {}, { reload: false });
            }, function() {
                $state.go('rule-queries');
            });
        }
        
        function constructDetails() {
        	$uibModal.open({
                templateUrl: 'app/pages/rule-queries/fare-class-query/fare-class-query-construction-details-dialog.html',
                controller: 'FareClassConstructionDetailsController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                	entity: vm.fareClassSelectedRow
                }
            }).result.then(function() {
                $state.go('rule-queries', {}, { reload: false });
            }, function() {
                $state.go('rule-queries');
            });
        }
        
        function getFareClassDetails(fareClassQuery) {
        	vm.fareClassDetails = [fareClassQuery];
        }
    }
})();
