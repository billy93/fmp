(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RuleQueryController', RuleQueryController);

    RuleQueryController.$inject = ['$state', '$stateParams', 'RuleQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'Timezone', 'pagingParams', '$uibModal'];

    function RuleQueryController($state, $stateParams, RuleQuery, ParseLinks, AlertService, paginationConstants, Timezone, pagingParams, $uibModal) {
    	
        var vm = this;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.loadAll = loadAll;
        vm.getRules = getRules;
        vm.getRules2 = getRules2;
        vm.resetFilter = resetFilter;
        vm.page = 1;
        vm.hideDetail = hideDetail;
        vm.showCategoryDetail = showCategoryDetail;
        vm.clearFilter = clearFilter;
        vm.showDetail = showDetail;
        vm.showTariffModal = showTariffModal;
        vm.showCarrierModal = showCarrierModal;
        
        vm.showLegend = showLegend;
        vm.viewFullText = viewFullText;
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        
        vm.showCategoryDetail = showCategoryDetail;
        
        vm.timezone = Timezone.GMT7;
        
        vm.openTooltip = true;
        
        if($stateParams.ruleQueryFilter != null){
        	vm.queryParams = $stateParams.ruleQueryFilter;
        	vm.params = $stateParams.ruleQueryDetailFilter;
        	vm.itemsPerPage = vm.queryParams.size;
        } else {
        	vm.clearFilter();
        	vm.itemsPerPage = "10";
        }
        
        function loadAll() {
        	if(vm.paramCarrier != null) {
        		vm.ruleQueries = null;
        		vm.ruleQueryCategories = null;
        		vm.ruleQueryCategories2 = null
	        	vm.queryParams.page = vm.page - 1;
	        	vm.queryParams.size = vm.itemsPerPage;
	        	
	        	vm.queryParams.cxr = vm.paramCarrier;
	        	vm.queryParams.ruleTarNo = vm.paramTarNo;
	        	
	        	RuleQuery.query(vm.queryParams, onSuccess, onError);
	        	
	            function onSuccess(data, headers) {
	                vm.links = ParseLinks.parse(headers('link'));
	                vm.totalItems = headers('X-Total-Count');
	                vm.queryCount = vm.totalItems;
	                vm.ruleQueries = data;
	            }
	            
	            function onError(error) {
	                AlertService.error(error.data.message);
	            }
        	}
    	
        }

        function getRules(ruleQuery) {
        	vm.params.cxr= ruleQuery.cxr;
        	vm.params.ruleTarNo= ruleQuery.tarNo;
        	vm.params.ruleNo= ruleQuery.ruleNo;
        	vm.params.catNo= ruleQuery.catNo;
        	
        	RuleQuery.getRules(vm.params, function(data) {
        		vm.ruleQueryCategories = data;
        	}, function(error) {
        		console.log(error);
        	});
        }
        
        function getRules2(ruleQuery) {
        	vm.isLoadingRule = true;
        	RuleQuery.getRules2(ruleQuery, function(data) {
        		vm.ruleQueryCategories2 = data;
        		vm.isLoadingRule = false;
        	}, function(error) {
        		console.log(error);
        		vm.isLoadingRule = false;
        	});
        }        
        
        function showDetail() {
        	$("#tblDetail").show();
        	$("#tblDetail").focus();
        	$("#ruleCategories").show();
        	
        }
        
        function hideDetail() {
        	$("#tblDetail").css("display","none");
        	$("#tblDetail").hide();
        	$("#ruleCategories").hide();
        }
       
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus = {};
            vm.datePickerOpenStatus[date] = true;
        }
        
        function resetFilter() {
        	vm.clearFilter();
        	vm.ruleQueries = null;
    		vm.ruleQueryCategories = null;
    		vm.ruleQueryCategories2 = null
        	
        }
        
        function clearFilter() {
        	vm.queryParams = {
        			cxr: null,
        			ruleNo: null,
        			type: null,
        			ruleTarNo: null,
        			catNo: null,
        			includeDisc: null
        	}
        	vm.paramCarrier = null;
        	vm.paramTarNo = null;
        	
        	vm.params = {
        			cxr: null,
            		ruleTarNo: null,
            		ruleNo: null,
            		type: null,
            		src: null,
            		cat: null,
            		catNo: null
        	}
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
				$state.go('rule-queries', {}, {
					reload : false
				});
			}, function() {
				$state.go('rule-queries');
			});
		}
        
        function showTariffModal() {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/tariff-modal.html',
                controller: 'MasterTariffModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                	entity: vm
                }
            }).result.then(function() {
                $state.go('rule-queries', {}, { reload: false });
            }, function() {
                $state.go('rule-queries');
            });
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
                $state.go('rule-queries', {}, { reload: false });
            }, function() {
                $state.go('rule-queries');
            });
        }
        
        function showLegend() {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/legend-modal.html',
                controller: 'LegendModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'md'
            }).result.then(function() {
                $state.go('rule-queries', {}, { reload: false });
            }, function() {
                $state.go('rule-queries');
            });
        }
        
        function viewFullText() {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/full-text-modal.html',
                controller: 'FullTextModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full',
                resolve: {
                    entity: {
                    	categories: vm.ruleQueryCategories2
                    }
                }
            }).result.then(function() {
                $state.go('rule-queries', {}, { reload: false });
            }, function() {
                $state.go('rule-queries');
            });
        }
    }
})();
