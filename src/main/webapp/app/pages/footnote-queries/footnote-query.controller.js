(function() {
	'use strict';

	angular.module('fmpApp').controller('FootnoteQueryController',
			FootnoteQueryController);

	FootnoteQueryController.$inject = [ '$state', '$stateParams', 'FootnoteQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'queryParams', '$uibModal' ];

	function FootnoteQueryController($state, $stateParams, FootnoteQuery, ParseLinks, AlertService, paginationConstants, queryParams, $uibModal) {

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
		vm.paramDetails = null;

		vm.datePickerOpenStatus = {};
		vm.dateFormat = "yyyy-MM-dd";
		vm.openCalendar = openCalendar;
		
		vm.paramCarrier = null;
		vm.paramTarNo = null;
		if($stateParams.cxr != null){
			vm.paramCarrier = $stateParams.cxr;
		}
		if($stateParams.tariff != null){
			vm.paramTarNo = $stateParams.tariff;
		}
        vm.showTariffModal = showTariffModal;
        vm.showCarrierModal = showCarrierModal;
        
        vm.loadAvailable = loadAvailable;
        vm.loadExpired = loadExpired;
        vm.loadAll();
        
        $("#catNo").change(function() {
        	vm.queryParams.saleDateFrom = null;
        	vm.queryParams.saleDateTo = null;
        	vm.queryParams.saleDateType = null;
        	vm.queryParams.travelDateFrom = null;
        	vm.queryParams.travelDateTo = null;
        	vm.queryParams.travelDateType = null;
        	vm.queryParams.completedDateFrom = null;
        	vm.queryParams.travelOpt = null;
        	
        });
        
		function loadAll() {
			if(vm.paramCarrier != null) {
				vm.footnoteQueries = null;
				vm.footnoteQueryCategories = null;
				
				vm.queryParams.page = vm.page - 1;
				vm.queryParams.size = vm.itemsPerPage;
				
				vm.queryParams.cxr = vm.paramCarrier;
				vm.queryParams.tarNo = vm.paramTarNo;

				vm.paramDetails = vm.queryParams;

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
		}
		
		function loadAvailable() {
			vm.footnoteQueries = null;
			vm.footnoteQueryCategories = null;
			vm.queryParams.page = vm.page - 1;
			vm.queryParams.size = vm.itemsPerPage;
			
			vm.queryParams.cxr = vm.paramCarrier;
			vm.queryParams.tarNo = vm.paramTarNo;

			FootnoteQuery.queryAvailable(vm.queryParams, onSuccess, onError);

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
		
		function loadExpired() {
			vm.footnoteQueries = null;
			vm.footnoteQueryCategories = null;
			vm.queryParams.page = vm.page - 1;
			vm.queryParams.size = vm.itemsPerPage;
			
			vm.queryParams.cxr = vm.paramCarrier;
			vm.queryParams.tarNo = vm.paramTarNo;

			FootnoteQuery.queryExpired(vm.queryParams, onSuccess, onError);

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
			vm.isLoadingRule = true;
			FootnoteQuery.getFtnt2(footnoteQuery, function(data) {
				vm.footnoteQueryCategories2 = data;
				console.log(vm.footnoteQueryCategories2);
				vm.isLoadingRule = false;
			}, function(error) {
				console.log(error);
				vm.isLoadingRule = false;
			});
		}

		function openCalendar(e, date) {
			e.preventDefault();
			e.stopPropagation();

			vm.datePickerOpenStatus[date] = true;
		}

		function reset() {
			vm.queryParams = {
				cxr : null,
				ftnt : null,
				tarNo : null,
				catNo : null,
				saleDateFrom : null,
				saleDateTo : null,
				saleDateType : null,
				travelDateFrom : null,
				travelDateTo : null,
				travelDateType : null,
				completedDateFrom : null,
				travelOpt : null,
				includeDisc : null
			}

			vm.footnoteQueries = null;
			vm.footnoteQueryCategories = null;
			
			vm.paramCarrier = null;
			vm.paramTarNo = null;
			
		}

		function showCategoryDetail(category) {
			$uibModal.open({
				templateUrl : 'app/pages/modals/category-modal.html',
				controller : 'CategoryModalController',
				controllerAs : 'vm',
				backdrop : 'static',
				size : 'lg',
				windowClass : 'full',
				resolve : {
					entity : category
				}
			}).result.then(function() {
				$state.go('footnote-query', {}, {
					reload : false
				});
			}, function() {
				$state.go('footnote-query');
			});
		}
		
		function showTariffModal() {
			$uibModal.open({
				templateUrl : 'app/pages/modals/tariff-modal.html',
				controller : 'MasterTariffModalController',
				controllerAs : 'vm',
				backdrop : 'static',
				size : 'lg',
				windowClass : 'full',
				resolve : {
					entity : vm
				}
			}).result.then(function() {
				$state.go('footnote-query', {}, {
					reload : false
				});
			}, function() {
				$state.go('footnote-query');
			});
		}

		function showCarrierModal() {
			$uibModal.open({
				templateUrl : 'app/pages/modals/carrier-modal.html',
				controller : 'MasterCarrierModalController',
				controllerAs : 'vm',
				backdrop : 'static',
				size : 'lg',
				windowClass : 'full',
				resolve : {
					entity : vm
				}
			}).result.then(function() {
				$state.go('footnote-query', {}, {
					reload : false
				});
			}, function() {
				$state.go('footnote-query');
			});
		}
	}
})();
