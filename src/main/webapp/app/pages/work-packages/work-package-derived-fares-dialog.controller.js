(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageDerivedFaresDialogController', WorkPackageDerivedFaresDialogController);

    WorkPackageDerivedFaresDialogController.$inject = ['$scope', 'FileSaver', 'DataUtils', '$uibModalInstance', 'WorkPackage', '$state', 'entity', 'fareSelected'];

    function WorkPackageDerivedFaresDialogController($scope, FileSaver, DataUtils, $uibModalInstance, WorkPackage, $state, entity, fareSelected) {

        var vm = this;
        vm.workPackageFare = entity;
        vm.fareSelected = fareSelected;     
        vm.openFile = DataUtils.openFile;
        vm.clear = clear;
        $scope.parseInt = parseInt;
        
        vm.exportDerivedFares = function(){
        		WorkPackage.exportDerivedFares(vm.fareSelected, onExportSuccess, onExportError);
        		
        		function onExportSuccess(result){
        			var fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        			var templateFilename = "Derived_Fare.xlsx";
        			var blob = b64toBlob(result.file, fileType);
        			FileSaver.saveAs(blob, templateFilename);
        		}
        		
        		function onExportError(){
        			alert('Export Error');
        		}
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
        
        vm.loadAll = function(){
        		
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();
