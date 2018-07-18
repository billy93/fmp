(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RoutingqueryDetailController', RoutingqueryDetailController);

    RoutingqueryDetailController.$inject = ['$scope', '$uibModalInstance', 'entity', 'Routingquery', 'FileSaver', 'AlertService'];

    function RoutingqueryDetailController($scope, $uibModalInstance, entity, Routingquery, FileSaver, AlertService) {
        var vm = this;

        vm.routingquery = entity;
        vm.selectTab = selectTab;
        vm.print = print;
        vm.exportData = exportData;
        vm.clear = clear;
        
        vm.currentTab = [true, false, false];
        vm.activeTab = 0;

        function selectTab(index) {
        	vm.currentTab = [false, false, false];
        	vm.currentTab[index] = true;
        	vm.activeTab = index;
        }
        
        function print() {
        }
        
        function exportData() {
        	vm.isLoading = true;
        	var exportConfig = {
        		routingQuery : vm.routingquery,
        		outputTo : 'excel',
        		gridLines : true,
        		columnHeaders : true,
        		onlySelectedRows : true,
        		tab : vm.activeTab
        	};

        	Routingquery.exportQueue(exportConfig, function onExportSuccess(result){
        		var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
       			var templateFilename = "Routing_Query.xlsx";
       			var blob = b64toBlob(result.file, fileType);
       			FileSaver.saveAs(blob, templateFilename);
       			vm.isLoading = false;
        	}, function onExportFailed(error){
        		 AlertService.error(error.data.message);
                 vm.isLoading = false;
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

			var blob = new Blob(byteArrays, {
				type : contentType
			});

			return blob;
        }
        
        function clear() {
            $uibModalInstance.dismiss('cancel');
        }
        
        
    }
    
})();
