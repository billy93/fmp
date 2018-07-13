(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('InternetQueryController', InternetQueryController);

    InternetQueryController.$inject = ['$state', '$stateParams', 'InternetQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', '$uibModal'];

    function InternetQueryController($state, $stateParams, InternetQuery, ParseLinks, AlertService, paginationConstants, pagingParams, $uibModal) {

        var vm = this;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.loadAll = loadAll;
        vm.changeItemsPerPage = changeItemsPerPage;
        vm.clearFilter = clearFilter;
        vm.resetFilter = resetFilter;
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        vm.page = 1;
        vm.showCarrierModal = showCarrierModal;
        vm.showWebsiteModal = showWebsiteModal;
        vm.updateDataView = updateDataView;
        vm.summarizeByCaptDateView = summarizeByCaptDateView;
        vm.summarizeByDeptDateView = summarizeByDeptDateView;
        vm.dontSummarizeView = dontSummarizeView;
        
        if($stateParams.internetQueryFilter != null){
        	vm.queryParams = $stateParams.internetQueryFilter;
        	vm.itemsPerPage = vm.queryParams.size;
        } else {
        	vm.clearFilter();
        	vm.itemsPerPage = "10";
        }
        
        function loadAll() {
        	vm.queryParams.page = vm.page - 1;
			vm.queryParams.size = vm.itemsPerPage;
			vm.queryParams.cxr = vm.paramCarrier;
			vm.queryParams.website = vm.paramWebsite;
			
			InternetQuery.query(vm.queryParams, onSuccess, onError);
            
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.internetQueries = data;
                vm.dontSummarizeView();
                
                $(document).ready(function(){
            		var _parents = $('.table-internet-query').find('thead');
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
        }
        
        function changeItemsPerPage() {
        	vm.loadAll();
        }
        
        function clearFilter() {
        	vm.queryParams = {
        			cxr: null,
        			website: null,
        			origin: null,
        			destination: null,
        			departDateFrom: null,
        			departDateTo: null,
        			departDOW: "0",
        			captureDateFrom: null,
        			captureDateTo: null,
        			marketGroup: null,
        			biDirectional: null,
        			appendResults: null,
        			queryByGroup: null,
        			currency: "IDR",
        			summarizeType: "2"
        	};
        	
        	vm.paramCarrier = null;
        	vm.paramWebsite = null;
        }
        
        function resetFilter() {
        	vm.clearFilter();
        	vm.internetQueries = null;
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus = {};
            vm.datePickerOpenStatus[date] = true;
        }
        
        function showCarrierModal() {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/carrier-modal.html',
                controller: 'MasterCarrierModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                	entity: vm
                }
            }).result.then(function() {
                $state.go('internet-query', {}, { reload: false });
            }, function() {
                $state.go('internet-query');
            });
        }
        
        function showWebsiteModal() {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/website-modal.html',
                controller: 'MasterWebsiteModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                	entity: vm
                }
            }).result.then(function() {
                $state.go('internet-query', {}, { reload: false });
            }, function() {
                $state.go('internet-query');
            });
        }
        
        function updateDataView() {
        	if(vm.queryParams.summarizeType == "0") {
            	vm.summarizeByCaptDateView();
            } else if(vm.queryParams.summarizeType == "1") {
            	vm.summarizeByDeptDateView();
            } else if(vm.queryParams.summarizeType == "2") {
            	vm.dontSummarizeView();
            } 
        }
        
        function summarizeByCaptDateView() {
        	console.log("summarizeByCaptDateView");
        }
        
        function summarizeByDeptDateView() {
        	console.log("summarizeByDeptDateView");
        }
        
        function dontSummarizeView() {
        	console.log("dontSummarizeView");
        }
        
        vm.dayOfWeekList = [
        	{key: "0", value: "All"},
        	{key: "1", value: "Sunday"},
        	{key: "2", value: "Monday"},
        	{key: "3", value: "Tuesday"},
        	{key: "4", value: "Wednesday"},
        	{key: "5", value: "Thursday"},
        	{key: "6", value: "Friday"},
        	{key: "7", value: "Saturday"},
        ];
        
        vm.summarizeType = [
        	{key: "0", value: "Summarize by Capt. Date"},
        	{key: "1", value: "Summarize by Dept. Date"},
        	{key: "2", value: "Don't Summarize"},
        ];
        
    }
})();
