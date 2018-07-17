(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageQueryController', WorkPackageQueryController);

    WorkPackageQueryController.$inject = ['$scope','Principal', '$uibModal', '$state', '$stateParams', 'WorkPackage', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'FileSaver', 'DateUtils', 'user', 'businessAreas', 'creator'];

    function WorkPackageQueryController($scope,Principal, $uibModal, $state, $stateParams, WorkPackage, ParseLinks, AlertService, paginationConstants, pagingParams, FileSaver, DateUtils, user, businessAreas, creator) {
        var vm = this;
        vm.reviewLevel = true;
        vm.woStatus = true;
        vm.distributionType = true;
        vm.workPackageActionButton = [];
        vm.predicate = pagingParams.predicate;
        vm.page = 1;
        
        vm.login =user;
        vm.optionDistribution = ['ATPCO', 'MARKET'];
        vm.optionWpType = ['Regular', 'Discount', 'Waiver'];
        vm.optionStatus = ['New', 'Pending','Reviewing', 'Ready to Release','Distributed', 'Withdrawn','Reffered', 'Discontinued'];
        vm.optionBusinessAreas = businessAreas;
        vm.optionCreator = creator;
        vm.datePickerOpenStatus = {};
        vm.dateFormat = "dd/MM/yyyy";
        vm.openCalendar = openCalendar;
        if($stateParams.size != null || $stateParams.size != undefined){
        	vm.itemsPerPage = $stateParams.size;
        }
        else{
        	vm.itemsPerPage = "10";
        }
        

        vm.loadQuery = loadQuery;        
        vm.loadQuery(); 
                              
        function loadQuery () { 
        	WorkPackage.customQuery({
        		wpID : vm.wpID,
            	name : vm.name,
            	status : vm.status,
            	distribution : vm.distribution,
            	wpType : vm.wpType,
            	createdDateFrom: DateUtils.convertLocalDateToServer(vm.createdDateFrom),
            	createdDateTo : DateUtils.convertLocalDateToServer(vm.createdDateTo),
            	filingDateFrom : DateUtils.convertLocalDateToServer(vm.filingDateFrom),
            	filingDateTo : DateUtils.convertLocalDateToServer(vm.filingDateTo),
            	gfsDateFrom : DateUtils.convertLocalDateToServer(vm.gfsDateFrom),
            	gfsDateTo : DateUtils.convertLocalDateToServer(vm.gfsDateTo),
            	distribDateFrom : DateUtils.convertLocalDateToServer(vm.distribDateFrom),
            	distribDateTo : DateUtils.convertLocalDateToServer(vm.distribDateTo),
            	discDateFrom : DateUtils.convertLocalDateToServer(vm.discDateFrom),
            	discDateTo : DateUtils.convertLocalDateToServer(vm.discDateTo),
            	fareClass : vm.fareClass,
            	businessArea : vm.businessAreas,
            	creator : vm.creator,
            	approval : vm.approval,
            	gfs : vm.gfs,
            	page: vm.page-1,
                size: vm.itemsPerPage,
                sort: sort()}, onSuccess, onError);
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
                vm.timezone = headers('timezone');
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        vm.rowSelected = function(idx, workPackage){
    		vm.selectedRow = workPackage;
    		vm.workPackageActionButton[idx] = !vm.workPackageActionButton[idx];
        }

   
        vm.showHistory = function(){
    		WorkPackage.history({id:vm.selectedRow.id}, onSuccess, onError);

			function onSuccess(history){
				$uibModal.open({
                    templateUrl: 'app/pages/work-packages/work-package-history-dialog.html',
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

        }

        vm.refresh = function(){
        	vm.reset();
        	vm.loadQuery();
        }
        
        
        vm.changeItemsPerPage = function(){
        	vm.loadQuery();
        }

        function b64toBlob(b64Data, contentType, sliceSize) {
  		  contentType = contentType || '';
  		  sliceSize = sliceSize || 512;

  		  var byteCharacters = atob(b64Data);
  		  var byteArrays = [];

  		  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
  		    var slice = byteCharacters.slice(offset, offset + sliceSize);

  		    var byteNumbers = new Array(slice.length);
  		    for (var i = 0; i < slice.length; i++) {
  		      byteNumbers[i] = slice.charCodeAt(i);
  		    }

  		    var byteArray = new Uint8Array(byteNumbers);

  		    byteArrays.push(byteArray);
  		  }

  		  var blob = new Blob(byteArrays, {type: contentType});

  		  return blob;
        }
                
                
        vm.rightClick = function(field){
        	var value=[];
        	for(var l=0; l< vm.workPackages.length; l++){
        		if(!vm.workPackages[l].hide){
	        		if(value.indexOf(vm.workPackages[l][field]) < 0){
	        			value.push(vm.workPackages[l][field]);
	        		}
        		}
        	}
        	vm.filterDialog(value, field);
        }
        
        vm.filterList = [];
        vm.filterDialog = function(value,field){
      	  $uibModal.open({
                templateUrl: 'app/pages/work-packages/work-package-filter-dialog.html',
                controller: 'WorkPackageFilterDialogController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                windowClass: 'full-page-modal',
                resolve: {
                    value : function(){
  	              		return value;
  	              	},
                    field : function(){
  	              		return field;
  	              	},
                }
  			}).result.then(function(result) {
  				if(result.key == 'distinctValue'){
	  				vm.filterList.push({key:field, value:result.value});
	  				for(var l=0; l< vm.workPackages.length; l++){
	  					var countTrue = [];
	  					for(var m=0;m<vm.filterList.length;m++){
	  						if(vm.workPackages[l][vm.filterList[m].key] == vm.filterList[m].value){
	  							countTrue.push(true);
	  						}
	  					}  					
	  					
	  					if(countTrue.length == vm.filterList.length){
	  						vm.workPackages[l].hide = false;
	  					}
	  					else{
	  						vm.workPackages[l].hide = true;
	  					}
	  	        	}
  				}
  				else if(result.key == 'removeAllColumnFilter'){
  					vm.filterList = [];
  					vm.loadQuery();
  				}
  				else if(result.key == 'removeThisColumnFilter'){
  					for(var x=0;x<vm.filterList.length;x++){
  						if(vm.filterList[x].key == field){
  							var index = vm.filterList.indexOf(vm.filterList[x]);
  							vm.filterList.splice(index, 1);
  						}
  					}
  					for(var l=0; l< vm.workPackages.length; l++){
	  					var countTrue = [];
	  					for(var m=0;m<vm.filterList.length;m++){
	  						if(vm.workPackages[l][vm.filterList[m].key] == vm.filterList[m].value){
	  							countTrue.push(true);
	  						}
	  					}  					
	  					
	  					if(countTrue.length == vm.filterList.length){
	  						vm.workPackages[l].hide = false;
	  					}
	  					else{
	  						vm.workPackages[l].hide = true;
	  					}
	  	        	}
  				}
  				else if(result.key == 'removeLastColumnFilter'){
					vm.filterList.splice(vm.filterList.length-1, 1);
  					for(var l=0; l< vm.workPackages.length; l++){
	  					var countTrue = [];
	  					for(var m=0;m<vm.filterList.length;m++){
	  						if(vm.workPackages[l][vm.filterList[m].key] == vm.filterList[m].value){
	  							countTrue.push(true);
	  						}
	  					}  					
	  					
	  					if(countTrue.length == vm.filterList.length){
	  						vm.workPackages[l].hide = false;
	  					}
	  					else{
	  						vm.workPackages[l].hide = true;
	  					}
	  	        	}
  				}
            }, function() {
        			
            });
        }
                
        function openCalendar (e, date) {
        	e.preventDefault();
            e.stopPropagation();
            
            vm.datePickerOpenStatus[date] = true;
        }
        
        vm.reset = function(){
        	vm.wpID = null;
        	vm.name = null;
        	vm.status = null;
        	vm.distribution = null;
        	vm.wpType = null;
        	vm.createdDateFrom = null;
        	vm.createdDateTo = null;
        	vm.filingDateFrom = null;
        	vm.filingDateTo = null;
        	vm.gfsDateFrom = null;
        	vm.gfsDateTo = null;
        	vm.distribDateFrom = null;
        	vm.distribDateTo = null;
        	vm.discDateFrom = null;
        	vm.discDateTo = null;
        	vm.fareClass = null;
        	vm.businessAreas = null;
        	vm.creator = null;
        	vm.approval = null;
        	vm.gfs = null;
        }
        
        vm.query = function(){
        	vm.loadQuery();
        }
        
    }
})();
