(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageController', WorkPackageController);

    WorkPackageController.$inject = ['$uibModal', '$state', 'WorkPackage', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function WorkPackageController($uibModal, $state, WorkPackage, ParseLinks, AlertService, paginationConstants, pagingParams) {
        var vm = this;
        vm.reviewLevel = true;
        vm.woStatus = true;
        vm.distributionType = true;
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;

        loadAll();

        function loadAll () {
            WorkPackage.query({
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
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
        
        vm.rowSelected = function(workPackage){
        		vm.selectedRow = workPackage;
        		console.log(workPackage);
        }
        
        vm.reuse = function(){
        	WorkPackage.reuse(vm.selectedRow, onReuseSuccess, onReuseFailed);
        	
        	function onReuseSuccess(result){
        		alert('Reuse Success');
        		$state.go('work-package-detail.edit', {id:result.id});
        	}
        	
        	function onReuseFailed(error){
        		
        	}
        }
        
        vm.replace = function(){
        	WorkPackage.replace(vm.selectedRow, onReplaceSuccess, onReplceFailed);
        	
        	function onReplaceSuccess(result){
        		alert('Replace Success '+result.id);
        		$state.go('work-package-detail.edit', {id:result.id});

        	}
        	
        	function onReplceFailed(error){
        		
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
    }
})();
