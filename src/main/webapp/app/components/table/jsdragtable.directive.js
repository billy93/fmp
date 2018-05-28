(function() {
    'use strict';
    
    angular
	    .module('fmpApp')
	    .directive('jqSlider', jqSlider);
	
	function jqSlider () {
	    var directive = {
	    restrict: 'A',
	    scope: {
	      'model': '='
	    },
	    link: function(scope, elem, attrs) {
	    	$(elem).jsdragtable();
	    }
	  };
	
	    return directive;
	}
})();
