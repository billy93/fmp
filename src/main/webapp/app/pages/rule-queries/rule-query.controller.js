(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RuleQueryController', RuleQueryController);

    RuleQueryController.$inject = ['$state', 'RuleQuery', 'ParseLinks', 'AlertService', 'paginationConstants', 'queryParams', 'params', '$uibModal'];

    function RuleQueryController($state, RuleQuery, ParseLinks, AlertService, paginationConstants, queryParams, params, $uibModal) {
    	

        var vm = this;
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.queryParams = queryParams;
        vm.params = params;
        vm.loadAll = loadAll;
        vm.loadRec8 = loadRec8;
        vm.getRules = getRules;
        vm.getRules2 = getRules2;
        vm.reset = reset;
        vm.page = 1;
        vm.hideDetail = hideDetail;
        vm.showCategoryDetail = showCategoryDetail;
        vm.clearFilter = clearFilter;
        vm.showDetail = showDetail;
        vm.getTab = getTab;
        
        
        
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "yyyy-MM-dd";
        vm.openCalendar = openCalendar;
        
        vm.openTooltip = true;
        
        $(".ng-valid").keyup(function() {
        	
        	$("input.ng-valid").css("border", "0px");
        });
        
        $(".ng-valid").keyup(function() {
        	
        	$("input.ng-invalid").css("border", "1px solid red");
        });
        
        
        function loadAll (queryForm) {
        	if(queryForm.$valid) {
        		vm.queryParams.page = vm.page - 1;
            	vm.queryParams.size = vm.itemsPerPage;
            	
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

        function loadRec8 (rec8Form) {
        	console.log(rec8Form);
        	if(rec8Form.$valid) {
        		vm.rec8params.page = vm.page - 1;
            	vm.rec8params.size = vm.itemsPerPage;
            	
            	RuleQuery.queryRec8(vm.rec8params, onSuccess, onError);
            	
                function onSuccess(data, headers) {
                    vm.links = ParseLinks.parse(headers('link'));
                    vm.totalItems = headers('X-Total-Count');
                    vm.queryCount = vm.totalItems;
                    vm.record8 = data;
                }
                
                function onError(error) {
                    AlertService.error(error.data.message);
                }
        	}
        }

        function loadPage(page) {
            vm.page = page;
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
        	RuleQuery.getRules2(ruleQuery, function(data) {
        		vm.ruleQueryCategories2 = data;
        	}, function(error) {
        		console.log(error);
        	});
        }        
        
        function showDetail() {
        	$("#tblDetail").show();
        	$("#tblDetail").focus();
        	$("#tblDetail").css("display","block");
        	$("#tblDetail").css("max-height","440px");
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
            
            cosole.log("date :: "+date);
            
            vm.datePickerOpenStatus[date] = true;
        }
        
        vm.tab1 = true;
        function getTab(tab) {
        	if(tab=='1') {
        		vm.reset();
        		vm.tab1 = true;
        		vm.tab2 = false;
        		vm.tab3 = false;
        	} else if(tab=='2') {
        		vm.reset();
        		vm.tab1 = false;
        		vm.tab2 = true;
        		vm.tab3 = false;
        	}  else if(tab=='3') {
        		vm.reset();
        		vm.tab1 = false;
        		vm.tab2 = false;
        		vm.tab3 = true;
        	}
        }
        
        function reset() {
        	vm.clearFilter();
        	
        	vm.queryParams = {
    			cxr: null,
        		ruleTarNo: null,
        		ruleNo: null,
        		type: null,
        		catNo: null,
        		includeDisc : null,
        	};
        	
        	vm.rec8params = {
        			cxr: null,
            		ruleTarNo: null,
            		ruleNo: null,
            		catNo: null,
            		includeDisc : null,
            };
        	
        	
        	vm.ruleQueries = null;
        	vm.ruleQueryCategories = null;
        	vm.ruleQueryCategories2 = null;
        	vm.record8 = null;
        }
        
        
        function clearFilter() {
        	$("#cxr").val("");
        	$("#ruleNo").val("");
        	$("#type").val("");
        	$("#src").val("");
        	$("#ruleTarNo").val("");
        	$("#catNo").val("");
        	$("#cxrRec8").val("");
        	$("#ruleNoRec8").val("");
        	$("#typeRec8").val("");
        	$("#srcRec8").val("");
        	$("#ruleTarNoRec8").val("");
        	$("#catNoRec8").val("");
        	
        	$("input.ng-invalid").css("border", "1px solid red");
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
                $state.go('rule-query', {}, { reload: false });
            }, function() {
                $state.go('rule-query');
            });
        }
    }
})();
