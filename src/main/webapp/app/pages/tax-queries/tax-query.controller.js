(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('TaxQueryController', TaxQueryController);

    TaxQueryController.$inject = ['$state', 'TaxQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'Timezone', 'Passenger', 'City', 'DateUtils'];

    function TaxQueryController($state, TaxQuery, ParseLinks, AlertService, paginationConstants, pagingParams, Timezone, Passenger, City, DateUtils) {

        var vm = this;

        vm.queryParams = {
    		carrier: null
    	};
        
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.loadAll = loadAll;
        vm.query = query;
        vm.timezone = Timezone.GMT7;
        
        vm.taxes = [];
        vm.reset = reset;
        vm.page = 0;
        vm.disableInfiniteScroll = true;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "dd/MM/yyyy";
        vm.openCalendar = openCalendar;

        vm.paxTypes = Passenger.getAll();
        vm.cities = City.getAll();

        vm.dateOptions = [
        	{key: "A", value: "Active In"},
        	{key: "E", value: "Exact Match"}
        ]
        
        vm.securityTable183s = [
        	{key: "", value: "Select Option"},
        	{key: "E", value: "Exists"},
        	{key: "D", value: "Does Not Exist"}
        ]
        
        function query() {
        	vm.taxes = [];
        	vm.page = 0;
        	vm.lastIndex = 0;
        	vm.selectedTax = null;
        	vm.isLastPage = false;
        	vm.loadAll();
        }
        
        function loadAll () {
        	vm.isLoading = true;
        	vm.isLastPage = false;
        	
        	vm.queryParams.page = vm.page;
        	vm.queryParams.size = vm.itemsPerPage;
        	vm.queryParams.lastIndex = vm.lastIndex;
        	
        	TaxQuery.query(vm.queryParams, onSuccess, onError);
        	
            function onSuccess(data) {
            	console.log(data);
            	
            	vm.isLastPage = data.lastPage;
            	vm.lastIndex = data.lastIndex;
            	
                for (var i = 0; i < data.tax.length; i++) {
                	vm.taxes.push(data.tax[i]);
                }
                
            	vm.isLoading = false;
                vm.disableInfiniteScroll = false;
                
                if (vm.taxes.length == 0) {
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
        		carrier: null
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
