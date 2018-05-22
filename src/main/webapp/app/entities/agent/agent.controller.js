(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('AgentController', AgentController);

    AgentController.$inject = ['$state', 'FileSaver','Agent', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'DataUtils', '$scope'];

    function AgentController($state, FileSaver, Agent, ParseLinks, AlertService, paginationConstants, pagingParams, DataUtils, $scope) {

        var vm = this;

        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.optionDelete= [null,true,false];
        vm.optionAgentType=[null,'Bulk','Consolidator','Corporate/TMC','Ethnic Market','Retailers','Seamen','Student','Tour operator','VFR','Web/E-Channel','Worker'];

        loadAll();

        function loadAll () {
            Agent.query({
            	"agentType" : vm.agentType,
            	"agentCategory" : vm.agentCategory,
            	"posCountry" : vm.posCountry,
            	"agentName" : vm.agentName,
            	"posCity" : vm.posCity,
            	"iataCode"  : vm.iataCode,
            	"isDeleted" : vm.isDeleted,
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
                vm.agents = data;
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

        vm.searchQuery = function() {
			loadAll();
		}
        
        vm.reset = function() {
        	vm.agentType = null,
        	vm.agentCategory = null,
        	vm.posCountry = null,
        	vm.agentName = null,
        	vm.posCity = null,
        	vm.iataCode = null,
        	vm.isDeleted = null
		}
        
        function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
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
        
        vm.importAgencies = function ($file) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        var testing = { 
                      		  file : base64Data,
                      		  fileContentType : $file.type
                        };
                        console.log(testing);
                        
                        //send to backend
                        Agent.importAgent(testing, onImportSuccess, onImportFailure);
                        
                        function onImportSuccess(result){
                      	  	alert('Import Success');
                      	  	console.log(result);
                      	  	loadAll();
                            Agent.query(function(resultData) {
                                vm.agencies = resultData;
                                vm.searchQuery = null;
                          	  	console.log(resultData);

                            });

                        }
                        
                        function onImportFailure(){
                      	    alert('Import Failed');
                        }
                    });
                });
            }
        };
        
        vm.exportAgencies = function(){
  	  	  	console.log(vm.agents)
        	Agent.exportAgent(vm.agents[0], onExportSuccess, onExportFailure);
	    	  function onExportSuccess(result){
  	  	  		var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    			var templateFilename = "Agencies.xlsx";
    			var blob = b64toBlob(result.file, fileType);
    			FileSaver.saveAs(blob, templateFilename);
	    	  }
	    	  function onExportFailure(){
	    		  alert('Export Failed');
	    	  }    	  
        }
        
    }
})();
