(function() {
    'use strict';

    angular
        .module('fmpApp')
        .factory('GlobalService', GlobalService);

    GlobalService.$inject = ['$http'];

    function GlobalService($http) {
    	 return {
    		navbar: function(){
	    		function sidebarOpen(){    			
	    			$('.showLeftPush').toggleClass('active');
	    			$('#sideBar-s1').toggleClass('sideBar-open');
	    			$('.search-wrap').find('i').toggleClass('shrink');
	    			$('.sideBar').find('.menu-list').toggleClass('open');
	    			$('.menu-list').removeClass('sub');
	    			$('.sub-menu').slideUp(200);
	    			$('.page-content').toggleClass('pushed');
	    			$('.sidebar-toggle').toggleClass('pushed');
	    			$('.fa-home').toggleClass('hidden')
	    		}
	    		function sidebarClose(){ 
	    			$('.showLeftPush').removeClass('active');
				 	$('.sidebar-toggle').removeClass('pushed');
				 	$('#sideBar-s1').removeClass('sideBar-open');
				 	$('.search-wrap').find('i').removeClass('shrink');
				 	$('.sideBar').find('.menu-list').removeClass('open');
				 	$('.page-content').removeClass('pushed');
				 	$('.menu-list').removeClass('sub');
	    			$('.sub-menu').slideUp(200);
	    			$('.fa-home').removeClass('hidden');
	    		}
    			$(document).ready(function(){
    				$('.showLeftPush').on('click', function(){	     				
        				sidebarOpen();
        			});
    				$('.menu-list').on('click',function(e){	
    					e.preventDefault();
    					if ($(this).hasClass('open')){
    						$('.menu-list').not(this).removeClass('sub');
    						$('.menu-list').not(this).find('.sub-menu').slideUp(200);
    						$(this).toggleClass('sub');
    						$(this).find('.sub-menu').slideToggle(200);
    					}
    					else{
    						sidebarOpen();
    						if ($(this).hasClass('has-sub')){
    							$(this).toggleClass('sub');
    							$(this).find('.sub-menu').slideToggle(200);
    						}
    					}		
    				});
    				$('.search-wrap i').on('click',function(){	
    					if (!$('.menu-list').hasClass('open')){
    						sidebarOpen()
    						$('.search-wrap input').focus();
    					}		
    				});
					 $('.linkClick').on('click',function(e){
						e.stopPropagation();
						sidebarClose()
					 });	
    			});
    		},
		    sayHello: function () {
//		    	// input
//		    	$(document).ready(function(){
//			    	$(document).on('input', 'input.must-fill', function(){
//			    	  	$(this).removeClass('must-fill');
//			    	  	if($(this).val() == ''){
//			    	  		$(this).addClass('must-fill');
//			    	  	}
//			    	});
//			    	
//			    	// select
//			    	$(document).on('change','select.must-fill',function(){
//			    		$(this).removeClass('must-fill');
//			    	  	if($(this).find('option')[0].selected === true){
//			    	  		$(this).addClass('must-fill');
//			    	  	}
//			    	});
//			    	
//
////			    	dropdown
//			    	$(document).mouseup(function (e){	
//	    			    if($('.dropdown-menu.custom').has(e.target).length === 0)
//	    			    {
//	    			        //hide your element
//	    			        $('.dropdown.custom').removeClass('open');
//	    			        $('.dropdown-menu.custom').hide();
//	    			    }
//	    			});
//
//		    		$(document).on('click','.dropdown.custom', function(){
//		    			$(this).toggleClass('open');
//		    			if($(this).hasClass('open')){
//		    				$(this).find('.dropdown-menu.custom').slideDown(100);
//		    			}
//		    			else{
//		    				$(this).find('.dropdown-menu.custom').slideUp(100);
//		    			}		
//		    		});
//		    		
//
////			    	filter
//			    	$(document).on('click','.filter-label',function(){
//			    		$(this).toggleClass('show');
//			    		$(this).siblings('.filter-area').slideToggle(200);
//			    		if($(this).hasClass('show')){
//			    			$(this).html('Hide Filter')
//			    		}
//			    		else{
//			    			$(this).html('Show Filter')
//			    		}
//			    	});	
//		    	});
		    },
		    
		    mustFill: function(){
		    	$(document).ready(function(){
		    		$('select.must-fill').on('change',function(){
			    		$(this).removeClass('must-fill');
			    	  	if($(this).find('option')[0].selected === true){
			    	  		$(this).addClass('must-fill');
			    	  	}
			    	});
			    	$('input.must-fill').on('input',function(){
			    	  	$(this).removeClass('must-fill');
			    	  	if($(this).val() == ''){
			    	  		$(this).addClass('must-fill');
			    	  	}
			    	});
		    	});
		    },
		    
		    boxHeader: function(){
		    	// box detail
//		    	$(".box-header.box-tab").on('click',function(){
//		    		$(this).find('.tab-content').slideToggle(200);
//		    	});
		    	$(document).ready(function(){
			    	$(".box-header").on('click',function(){
			    		$(this).toggleClass('open');
			    		$(this).siblings('.box-body').slideToggle(200);
			    	});
		    	});
		    }
		 };
    }
})();
