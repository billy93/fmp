(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageController', WorkPackageController);

    WorkPackageController.$inject = ['$uibModal', '$state', '$stateParams', 'WorkPackage', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'GlobalService'];

    function WorkPackageController($uibModal, $state, $stateParams, WorkPackage, ParseLinks, AlertService, paginationConstants, pagingParams, GlobalService) {
        var vm = this;
        vm.reviewLevel = true;
        vm.woStatus = true;
        vm.distributionType = true;
        vm.loadPage = loadPage;
        vm.workPackageActionButton = [];
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        
        if($stateParams.workPackageFilter != null){
        	vm.workPackageFilter = $stateParams.workPackageFilter;
        }
        else{
	        vm.workPackageFilter = {
	    		reviewLevel:{
	    			ho:true,
	    			lso:true,
	    			distribution:true,
	    			routeManagement:true
	    		},
	    		distributionType:{
	    			atpco:true,
	    			market:true
	    		},
	    		status:{
	    			new:true,
	    			pending:true,
	    			reviewing:true,
	    			readyToRelease:true,
	    			distributed:true,
	    			withdraw:true,
	    			discontinue:true,
	    			referred:true,
	    			replace:true,
	    			reuse:true
	    		},
	    		type:{
	    			regular:true,
	    			discount:true,
	    			waiver:true
	    		},
	        };
        }
        loadAll();

        function loadAll () {
            WorkPackage.query({
            	"reviewLevel.ho": vm.workPackageFilter.reviewLevel.ho,
            	"reviewLevel.lso": vm.workPackageFilter.reviewLevel.lso,
            	"reviewLevel.distribution": vm.workPackageFilter.reviewLevel.distribution,
            	"reviewLevel.routeManagement": vm.workPackageFilter.reviewLevel.routeManagement,
            	
            	"status.newStatus": vm.workPackageFilter.status.new,
            	"status.pending": vm.workPackageFilter.status.pending,
            	"status.reviewing": vm.workPackageFilter.status.reviewing,
            	"status.readyToRelease": vm.workPackageFilter.status.readyToRelease,
            	"status.distributed": vm.workPackageFilter.status.distributed,
            	"status.withdraw": vm.workPackageFilter.status.withdraw,
            	"status.replace": vm.workPackageFilter.status.replace,
            	"status.reuse": vm.workPackageFilter.status.reuse,
            	"distributionType.atpco":vm.workPackageFilter.distributionType.atpco,
            	"distributionType.market":vm.workPackageFilter.distributionType.market,
            	
            	"type.regular":vm.workPackageFilter.type.regular,
            	"type.discount":vm.workPackageFilter.type.discount,
            	"type.waiver":vm.workPackageFilter.type.waiver,
            	
            	"approvalReference": vm.workPackageFilter.approvalReference,
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
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
                vm.workPackages = data;
                console.log(vm.workPackages);
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
            	workPackageFilter: vm.workPackageFilter,
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
        
        vm.rowSelected = function(idx, workPackage){
    		vm.selectedRow = workPackage;
    		vm.workPackageActionButton[idx] = !vm.workPackageActionButton[idx];
        }
        
        vm.reuse = function(index){
        	WorkPackage.reuse(vm.workPackages[index], onReuseSuccess, onReuseFailed);
        	
        	function onReuseSuccess(result){
        		alert('Reuse Success');
        		$state.go('work-package-detail', {id:result.id});
        	}
        	
        	function onReuseFailed(error){
        		
        	}
        }
        
        vm.replace = function(index){
        	WorkPackage.replace(vm.workPackages[index], onReplaceSuccess, onReplceFailed);
        	
        	function onReplaceSuccess(result){
        		alert('Replace Success '+result.id);
        		$state.go('work-package-detail', {id:result.id});

        	}
        	
        	function onReplceFailed(error){
        		
        	}
        }
        vm.withdraw = function(index){
        	WorkPackage.withdraw(vm.workPackages[index], onWithdrawSuccess, onWithdrawFailed);
        	
        	function onWithdrawSuccess(result){
        		alert('Withdraw Success '+result.id);
        		$state.go('work-package-detail', {id:result.id});

        	}
        	
        	function onWithdrawFailed(error){
        		
        	}
        }
        
        vm.showHistory = function(){
        		if(vm.selectedRow != null){
        			WorkPackage.history({id:vm.selectedRow.id}, onSuccess, onError);
        			
        			function onSuccess(history){
        				$uibModal.open({
                            templateUrl: 'app/entities/work-package/work-package-history-dialog.html',
                            controller: 'WorkPackageHistoryDialogController',
                            controllerAs: 'vm',
                            backdrop: 'static',
                            size: 'lg',
                            resolve: {
                                entity: history.$promise
                            }
                        }).result.then(function(workPackage) {
                        	  
                        }, function() {
                    			
                        });
        			}
        			
        			function onError(){
        				
        			}
        			
        		}else{
        			alert('Select workorder first');
        		}
        }

        vm.refresh = function(){
        	loadAll();
        	console.log(vm.workPackageFilter);
        }
        
        $('.js-filter-toggle').click(function(e){
            e.preventDefault();
            e.stopPropagation();
            var _this = $(this);
            var _login = $('.wq').find('.filter-area');
            if (!_this.hasClass('is-active')) {
                _login.fadeIn(200);
                _this.addClass('is-active');
                $('.filter_wording').text('Hide Filter');
                $('.filter_wording').addClass('semibold');
            }
            else {
                _login.fadeOut(100);
                 _this.removeClass('is-active');
                 $('.filter_wording').text('Show Filter');
                 $('.filter_wording').removeClass('semibold');
            }   
        });
        
        GlobalService.sayHello();
        GlobalService.mustFill();
    }
})();
