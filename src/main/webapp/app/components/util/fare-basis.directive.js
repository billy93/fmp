(function() {
    'use strict';

    angular
        .module('fmpApp')
        .directive('farebasis', farebasis);

    function farebasis() {
    	return {
	      require: 'ngModel',
	      link: function(scope, element, attrs, modelCtrl) {
	    	  var farebasis = function(inputValue) {
		          if (inputValue == undefined) inputValue = '';
		          var capitalized = inputValue.toUpperCase();
		          if (capitalized !== inputValue) {
		        	  var selection = element[0].selectionStart;
		        	  modelCtrl.$setViewValue(capitalized);
		        	  modelCtrl.$render();
		        	  element[0].selectionStart = selection;
		        	  element[0].selectionEnd = selection;
		          }
		          
		          var reg = new RegExp('^[A-Z0-9*-]*$');
	        	  if (reg.test(capitalized)) {
	        		  console.log('matched');
	        		  var selection = element[0].selectionStart;
		        	  modelCtrl.$setViewValue(capitalized);
		        	  modelCtrl.$render();
		        	  element[0].selectionStart = selection;
		        	  element[0].selectionEnd = selection;
	        	  } else {
	        		  console.log('not matched');
	        	  }
	        	  
		          return capitalized;
		        }
	        modelCtrl.$parsers.push(farebasis);
	        farebasis(scope[attrs.ngModel]);
	      }
	    };
    }
})();
