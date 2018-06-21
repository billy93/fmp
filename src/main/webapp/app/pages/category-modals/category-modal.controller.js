(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('CategoryModalController', CategoryModalController);

    CategoryModalController.$inject = ['$state', 'entity', '$uibModalInstance', '$q'];

    function CategoryModalController($state, entity, $uibModalInstance, $q) {
        var vm = this;
        vm.clear = clear;
        vm.showCodedValue = false;
        
        vm.textTable996Tags = ['text_tbl_no_996', 'text_table_no_996', 'textTableNumber996'];
        
        vm.category = entity;
        
        if (vm.category.catNo == '10') {
        	vm.category10Attributes = [];
        	
        	vm.category.catAttributes.forEach(function(attribute) {
    			var found = false;
    			
    			for (var j = 0; j < vm.category10Attributes.length; j++) {
    				if (vm.category10Attributes[j].cat == attribute.subcat) {
    					vm.category10Attributes[j].content.push(attribute);
    					found = true;
    					break;
    				}
    			}
    			
    			if (!found) {
    				var obj = {
            				cat: attribute.subcat,
            				content: []
            		}
    				obj.content.push(attribute);
    				vm.category10Attributes.push(obj);
    			}
    		});
            
            console.log(vm.category10Attributes);
        }
        
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
