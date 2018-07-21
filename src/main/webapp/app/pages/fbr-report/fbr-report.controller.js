(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('FbrReportController', FbrReportController);

    FbrReportController.$inject = ['$state', 'FbrReport', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'Timezone', 'DateUtils'];

    function FbrReportController($state, FbrReport, ParseLinks, AlertService, paginationConstants, pagingParams, Timezone, DateUtils) {

        var vm = this;

        vm.queryParams = {
    		carrier: null,
    		tariff: null,
    		ruleNo: null
    	};
        
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.loadAll = loadAll;
        vm.query = query;
        vm.timezone = Timezone.GMT7;
        
        vm.reports = [];
        vm.reset = reset;
        vm.page = 0;
        vm.disableInfiniteScroll = true;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "dd/MM/yyyy";
        vm.openCalendar = openCalendar;
        
        function query() {
        	vm.reports = [];
        	vm.page = 0;
        	vm.lastIndex = 0;
        	vm.selectedYqyr = null;
        	vm.isLastPage = false;
        	vm.loadAll();
        }
        
        function loadAll () {
        	vm.isLoading = true;
        	vm.isLastPage = false;
        	
        	vm.queryParams.page = vm.page;
        	vm.queryParams.size = vm.itemsPerPage;
        	vm.queryParams.lastIndex = vm.lastIndex;
        	
        	FbrReport.query(vm.queryParams, onSuccess, onError);
        	
            function onSuccess(data) {
            	vm.isLastPage = data.lastPage;
            	vm.lastIndex = data.lastIndex;
                
                console.log(data);
                
//                DateUtils.convertDateTimeFromServer(data.createdDate);
                
                for (var i = 0; i < data.fbrReport.length; i++) {
                	vm.reports.push(data.fbrReport[i]);
                }
                
            	vm.isLoading = false;
                vm.disableInfiniteScroll = false;
                
                if (vm.reports.length == 0) {
                	vm.noDataAvailable = true;
                } else {
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
            }
            
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset() {
        	vm.queryParams = {
        		carrier: null,
        		tariff: null,
        		ruleNo: null
        	}
        }
        
        function loadPage(page) {
        	vm.page = page;
    		vm.loadAll();
        }
        
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus = {};
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
