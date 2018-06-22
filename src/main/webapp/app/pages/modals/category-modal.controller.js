(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CategoryModalController', CategoryModalController);

    CategoryModalController.$inject = ['$state', 'entity', '$uibModalInstance'];

    function CategoryModalController($state, entity, $uibModalInstance) {
        var vm = this;
        vm.clear = clear;
        vm.showCodedValue = false;
        
        vm.textTable996Tags = ['text_tbl_no_996', 'text_table_no_996', 'textTableNumber996'];
        
        vm.category = entity;
        console.log(vm.category);
        
        vm.checkPreElement = function(key, value) {
        	if (vm.textTable996Tags.includes(key) && value != '00000000') {
        		return true;
        	} else {
        		return false;
        	}
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
