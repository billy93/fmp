(function() {
    'use strict';

    angular
        .module('fmpApp')
        .filter('objectname', objectname);

    function objectname() {
        return objectnameFilter;

        function objectnameFilter (input) {
            if (input !== null) {
            	if (input.includes('_')) {
            		return input.replace(/_/g, ' ').toLowerCase();
            	} else {
            		var positions = [];
            		var result = input;
            		
            		for (var i = 0; i < input.length; i++) {
            		    if (input[i].match(/[A-Z]/) != null) {
            		        positions.push(i);
            		    }
            		}
            		
            		for (var index = 0; index < positions.length; index++) {
            			result = insertCharacter(result, positions[index] + index, ' ');
            		}
            		
            		return result.toLowerCase();
            	}
            }
            
            return input.toLowerCase();
        }
         
        function insertCharacter(originalString, index, string) {
        	if (index > 0) {
        		return originalString.substring(0, index) + string + originalString.substring(index, originalString.length);
        	} else {
        		return string + originalString;
        	}
        }
    }
})();
