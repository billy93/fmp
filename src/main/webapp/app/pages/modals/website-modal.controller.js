(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('MasterWebsiteModalController', MasterWebsiteModalController);

    MasterWebsiteModalController.$inject = ['$state', 'entity', '$uibModalInstance', 'MasterWebsite'];

    function MasterWebsiteModalController($state, entity, $uibModalInstance, MasterWebsite) {
    	 var vm = this;
    	 
    	 vm.parent = entity;
         vm.clear = clear;
         vm.masterWebsite = MasterWebsite;
         vm.option = { searchType:'website' };
         vm.selectedRow = vm.masterWebsite[0];
         vm.sortType     = 'website';
         vm.sortReverse  = false;
         vm.searchFish   = '';
         
         vm.selectedRow = null;
         
         vm.rowHighlighted = function (idSelected) {
            vm.selectedRow = idSelected;
         };
         
         vm.search = function(){
         	vm.selectedRow = null;
         }
         
         vm.select = function(){
        	vm.parent.paramWebsite = vm.selectedRow;
         	$uibModalInstance.close(vm.selectedRow);
         }
         
         function clear () {
             $uibModalInstance.dismiss('cancel');
         }
        
         vm.masterWebsite.getAll(function(data) {
         	vm.websites = data;
         });
        
        
    }
})();
