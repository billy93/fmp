(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageQueryController', WorkPackageQueryController);

    WorkPackageQueryController.$inject = ['$scope','Principal', '$uibModal', '$state', '$stateParams', 'WorkPackage', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'FileSaver', 'DateUtils', 'user', 'businessAreas', 'creator', 'fareTypes'];

    function WorkPackageQueryController($scope,Principal, $uibModal, $state, $stateParams, WorkPackage, ParseLinks, AlertService, paginationConstants, pagingParams, FileSaver, DateUtils, user, businessAreas, creator, fareTypes) {
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
        vm.optionFare = fareTypes;
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
        		wpID : vm.wpID == '' ? null : vm.wpID,
            	name : vm.name  == '' ? null : vm.name,
            	status : vm.status  == '' ? null : vm.status,
            	distribution : vm.distribution  == '' ? null : vm.distribution,
            	wpType : vm.wpType  == '' ? null : vm.wpType,
            	createdDateFrom: DateUtils.convertLocalDateToServer(vm.createdDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.createdDateFrom),
            	createdDateTo : DateUtils.convertLocalDateToServer(vm.createdDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.createdDateTo),
            	filingDateFrom : DateUtils.convertLocalDateToServer(vm.filingDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.filingDateFrom),
            	filingDateTo : DateUtils.convertLocalDateToServer(vm.filingDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.filingDateTo),
            	gfsDateFrom : DateUtils.convertLocalDateToServer(vm.gfsDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.gfsDateFrom),
            	gfsDateTo : DateUtils.convertLocalDateToServer(vm.gfsDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.gfsDateTo),
            	distribDateFrom : DateUtils.convertLocalDateToServer(vm.distribDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.distribDateFrom),
            	distribDateTo : DateUtils.convertLocalDateToServer(vm.distribDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.distribDateTo),
            	discDateFrom : DateUtils.convertLocalDateToServer(vm.discDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.discDateFrom),
            	discDateTo : DateUtils.convertLocalDateToServer(vm.discDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.discDateTo),
            	fareClass : vm.fareClass  == '' ? null : vm.fareClass,
            	businessArea : vm.businessAreas  == '' ? null : vm.businessAreas,
            	creator : vm.creator  == '' ? null : vm.creator,
            	approval : vm.approval  == '' ? null : vm.approval,
            	gfs : vm.gfs ,
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
        
        vm.reuse = function(){
        	vm.selectedRow.reuseReplaceConfig = {};
        		$uibModal.open({
                    templateUrl: 'app/pages/work-packages/work-package-reuse-replace-confirm-dialog.html',
                    controller: 'WorkPackageReuseReplaceConfirmDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    windowClass: 'full-page-modal',
                    resolve: {
                    	workPackage: function(){
                    		return vm.selectedRow;
                    	},
                    	 businessAreas: ['User', function(User) {
                             return User.getBusinessArea().$promise;
                         }],
                    }
    			}).result.then(function(workPackage) {
    				WorkPackage.reuse(workPackage, onReuseSuccess, onReuseFailed);

    				function onReuseSuccess(result){
    	        		alert('Reuse Success');
    	        		$state.go('work-package-detail', {id:result.id});
    	        	}

    	        	function onReuseFailed(error){
    	        		alert("An error occured, please try again");
    	        	}
    			});
        }
        
        vm.replace = function(){
        	vm.selectedRow.reuseReplaceConfig = {};
        		$uibModal.open({
                    templateUrl: 'app/pages/work-packages/work-package-reuse-replace-confirm-dialog.html',
                    controller: 'WorkPackageReuseReplaceConfirmDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    windowClass: 'full-page-modal',
                    resolve: {
                    	workPackage: function(){
                    		return vm.selectedRow;
                    	},
	                   	 businessAreas: ['User', function(User) {
	                         return User.getBusinessArea().$promise;
	                     }],
                    }
    			}).result.then(function(option) {
    				vm.selectedRow.reuseReplaceConfig.attachment = option.attachment;

    				WorkPackage.replace(vm.selectedRow, onReplaceSuccess, onReplceFailed);

    	        	function onReplaceSuccess(result){
    	        		alert('Replace Success');
    	        		$state.go('work-package-detail', {id:result.id});

    	        	}

    	        	function onReplceFailed(error){

    	        	}
    			});        	
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
        
        vm.printExport = function(){
        	var workPackageQuery = {
    			wpID : vm.wpID == '' ? null : vm.wpID,
            	name : vm.name  == '' ? null : vm.name,
            	status : vm.status  == '' ? null : vm.status,
            	distribution : vm.distribution  == '' ? null : vm.distribution,
            	wpType : vm.wpType  == '' ? null : vm.wpType,
            	createdDateFrom: DateUtils.convertLocalDateToServer(vm.createdDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.createdDateFrom),
            	createdDateTo : DateUtils.convertLocalDateToServer(vm.createdDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.createdDateTo),
            	filingDateFrom : DateUtils.convertLocalDateToServer(vm.filingDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.filingDateFrom),
            	filingDateTo : DateUtils.convertLocalDateToServer(vm.filingDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.filingDateTo),
            	gfsDateFrom : DateUtils.convertLocalDateToServer(vm.gfsDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.gfsDateFrom),
            	gfsDateTo : DateUtils.convertLocalDateToServer(vm.gfsDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.gfsDateTo),
            	distribDateFrom : DateUtils.convertLocalDateToServer(vm.distribDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.distribDateFrom),
            	distribDateTo : DateUtils.convertLocalDateToServer(vm.distribDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.distribDateTo),
            	discDateFrom : DateUtils.convertLocalDateToServer(vm.discDateFrom)  == '' ? null : DateUtils.convertLocalDateToServer(vm.discDateFrom),
            	discDateTo : DateUtils.convertLocalDateToServer(vm.discDateTo)  == '' ? null : DateUtils.convertLocalDateToServer(vm.discDateTo),
            	fareClass : vm.fareClass  == '' ? null : vm.fareClass,
            	businessArea : vm.businessAreas  == '' ? null : vm.businessAreas,
            	creator : vm.creator  == '' ? null : vm.creator,
            	approval : vm.approval  == '' ? null : vm.approval,
            	gfs : vm.gfs 
        	};
        	
        	var exportConfig = {
        		workPackageQuery : workPackageQuery,
        		outputTo:'excel',
        		gridLines:true,
        		columnHeaders:true,
        		onlySelectedRows:true
        	};

        	console.log(exportConfig);
        	WorkPackage.exportQueueQuery(exportConfig, function onExportSuccess(result){
//        		alert('Export Success');
        		 var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
       			var templateFilename = "Workorder_Queue.xlsx";
       			var blob = b64toBlob(result.file, fileType);
       			FileSaver.saveAs(blob, templateFilename);
        	}, function onExportFailed(){

        	});

        }
        
        function max_date(all_dates) {
        	var max_dt = all_dates[0],
        	  max_dtObj = new Date(all_dates[0]);
        	all_dates.forEach(function(dt, index)
        	  {
        	  if ( new Date( dt ) > max_dtObj)
        	  {
        	  max_dt = dt;
        	  max_dtObj = new Date(dt);
        	  }
        	  });
        	 return max_dt;
        	  }
        
        function min_date(all_dates) {
        	var max_dt = all_dates[0],
        	  max_dtObj = new Date(all_dates[0]);
        	all_dates.forEach(function(dt, index)
        	  {
        	  if ( new Date( dt ) < max_dtObj)
        	  {
        	  max_dt = dt;
        	  max_dtObj = new Date(dt);
        	  }
        	  });
        	 return max_dt;
        	  }
                
                
        vm.rightClick = function(field){
        	var value=[];
        	if(field.match(/date/gi) != null ){
            	for(var l=0; l< vm.workPackages.length; l++){
            		if(!vm.workPackages[l].hide){
    	        		if(value.indexOf(vm.workPackages[l][field]) < 0){
    	        			value.push(vm.workPackages[l][field]);
    	        		}
            		}
            	}
            	var maxDate = max_date(value);
            	var minDate = min_date(value);
            	var isDate = true;
            	vm.filterDialog(value, field, DateUtils.convertLocalDateToServer(maxDate), DateUtils.convertLocalDateToServer(minDate),isDate);
        	}else{
            	for(var l=0; l< vm.workPackages.length; l++){
            		if(!vm.workPackages[l].hide){
    	        		if(value.indexOf(vm.workPackages[l][field]) < 0){
    	        			value.push(vm.workPackages[l][field]);
    	        		}
            		}
            	}
            	var maxDate = null;
            	var minDate = null;
            	var isDate = false;
            	vm.filterDialog(value,field,maxDate,minDate,isDate);
        	}
        }
        
        vm.filterList = [];
        vm.filterDialog = function(value,field,maxDate,minDate,isDate){
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
  	              	maxDate : function(){
  	              		return maxDate;
  	              	},
  	              	minDate : function(){
	              		return minDate;
	              	},
	              	isDate : function(){
	              		return isDate;
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
  					vm.loadAll();
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
  				}else if(result.key == '*'){
  					vm.filterList.push({key:field, value:result.value});
	  				for(var l=0; l< vm.workPackages.length; l++){
	  					var countTrue = [];
	  					for(var m=0;m<vm.filterList.length;m++){
	  						if(vm.workPackages[l][vm.filterList[m].key].indexOf(vm.filterList[m].value) > -1){
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
  				}else if(result.key == '?'){
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
  				}else if(result.key == 'range'){
  					console.log(result.value);
  					vm.filterList.push({key:field, value:result.value});
	  				for(var l=0; l< vm.workPackages.length; l++){
	  					var countTrue = [];
	  					for(var m=0;m<vm.filterList.length;m++){
	  						if(result.value.from != null && result.value.to != null ){
	  							var to = new Date(result.value.to);
	  							to.setHours(23,59,59)
	  							if(new Date( vm.workPackages[l][vm.filterList[m].key] ) >= result.value.from && new Date( vm.workPackages[l][vm.filterList[m].key] ) <= to){
	  								countTrue.push(true);
	  							}
	  						}else if (result.value.from != null){
	  							if(new Date( vm.workPackages[l][vm.filterList[m].key] ) >= result.value.from){
	  								countTrue.push(true);
	  							}
	  						}else if(result.value.to != null){
	  							var to = new Date(result.value.to);
	  							to.setHours(23,59,59)
	  							if(new Date( vm.workPackages[l][vm.filterList[m].key] ) <= to){
	  								countTrue.push(true);
	  							}
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
            
            vm.datePickerOpenStatus = {};
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
