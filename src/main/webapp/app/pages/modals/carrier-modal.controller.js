(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('MasterCarrierModalController', MasterCarrierModalController);

    MasterCarrierModalController.$inject = ['$state', 'entity', '$uibModalInstance', 'CcfCarrier'];

    function MasterCarrierModalController($state, entity, $uibModalInstance, CcfCarrier) {
    	 var vm = this;
    	 
    	 vm.parent = entity;
         vm.clear = clear;
         vm.carrier = CcfCarrier;
         vm.option = { searchType:'cxrCode' };
         vm.selectedRow = vm.carrier[0];
         vm.sortType     = 'cxrCode'; // set the default sort type
         vm.sortReverse  = false;  // set the default sort order
         vm.searchFish   = '';     // set the default search/filter term
         
         vm.selectedRow = null;
         
         vm.rowHighlighted = function (idSelected) {
            vm.selectedRow = idSelected;
         };
         
         vm.search = function(){
         	vm.selectedRow = null;
//         	if(vm.filteredCities != null){
//         		console.log("FILTERED CITY NOT NULL");
//         		console.log(vm.filteredCities);
//         		vm.selectedRow = vm.filteredCities[0];
//         	}
         }
         
         vm.select = function(){
        	 console.log(vm.selectedRow);
        	vm.parent.paramCarrier = vm.selectedRow.cxrCode;
//          	$(cxrModel.varName).val(vm.selectedRow.cxrCode);
         	$uibModalInstance.close(vm.selectedRow);
         }
         
         function clear () {
             $uibModalInstance.dismiss('cancel');
         }
        
         vm.carrier.getAll(function(data) {
         	vm.carriers = data;
         });
        
        
    }
})();
