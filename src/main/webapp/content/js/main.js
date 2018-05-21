
var REinit = function() {
    /// PLACE HERE ALL YOUR DOC.READY SCRIPTS
	// sidebar menu
	function sidebarOpen(){
		$('.showLeftPush').toggleClass('active');
		$('#sideBar-s1').toggleClass('sideBar-open');
		$('.search-wrap').find('i').toggleClass('shrink');
		$('.sideBar').find('.menu-list').toggleClass('open');
		$('.menu-list').removeClass('sub');
		$('.sub-menu').slideUp(200);
		$('.sidebar-toggle').toggleClass('pushed');
		$('.fa-home').toggleClass('hidden')
	}

	$('.showLeftPush').on('click',function(){	
		sidebarOpen();
	});
	
	$(".table-responsive").mCustomScrollbar({
    	scrollButtons:{enable:true,scrollType:"stepped",scrollAmount: 200},
		keyboard:{scrollType:"stepped"},
		mouseWheel:{scrollAmount:175},
		theme:"rounded-dark",
		axis:"x"
    });
	
	$('.search-wrap i').on('click',function(){	
		if (!$('.menu-list').hasClass('open')){
			sidebarOpen()
			$('.search-wrap input').focus();
		}		
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

	// filter

	$('.filter-label').on('click',function(){
		$(this).toggleClass('show');
		$(this).siblings('.filter-area').slideToggle(200);
		if($(this).hasClass('show')){
			$(this).html('Hide Filter')
		}
		else{
			$(this).html('Show Filter')
		}
	});	

	// dropdown
	$(document).mouseup(function (e)
		{	
		    if($('.dropdown-menu').has(e.target).length === 0)
		    {
		        //hide your element
		        $('.dropdown').removeClass('open');
		        $('.dropdown-menu').hide();
		    }
		});

	$('.dropdown').on('click',function(){
		$(this).toggleClass('open');
		if($(this).hasClass('open')){
			$(this).find('.dropdown-menu').slideDown(100);
		}
		else{
			$(this).find('.dropdown-menu').slideUp(100);
		}		
	});


	// table
	$("table").on('click',function(e){
		e.stopPropagation();
        var $target = $(e.target);
        var $parent = $target.parent('tr');
        var $next = $parent.next('.datatable-action');
//        $('.datatable-action').not($next).hide();	
		$next.slideToggle(100);		
		$('.datatable-row').removeClass('selected');
		$parent.addClass('selected');
		$('.table-responsive').scrollLeft(0);
	});

	// box detail
	$(".box-header").on('click',function(){
		$(this).toggleClass('open');
		$(this).siblings('.box-body').slideToggle(200);
	});
	
	// box detail
	$(".box-header.box-tab").on('click',function(){
		$(this).find('.tab-content').slideToggle(200);
	});

	// comment
	/*$("#btn-comment").on('click',function(){
		var _text = $('#comment-area').val();
		var currentdate = new Date(); 
    	var datetime = currentdate.getDate() + "/"
                + (currentdate.getMonth()+1)  + "/" 
                + currentdate.getFullYear() + " @ "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes();
        var _last = $('.comment-wrapper').find('.comment-list').last();
		
		if(!_text == ''){
			if($('.comment-list').length > 0) {
				$('.comment-wrapper').prepend("<div class='comment-list'>"+
										"<div class='clearfix'>"+
										"<div class='pull-left comment-detail'>ADMIN</div>"+
										"<div class='pull-right comment-detail'>"+datetime+"</div>"+
										"</div>"+
										"<div class='comment-message'>"+"</div>"+
										"</div>");
				$('.comment-wrapper').find('.comment-message').first().html(_text);
				if($('.comment-list').length > 10) {
					$('.comment-list').last().remove();
				}
			}
			else{
				$('.comment-wrapper').append("<div class='comment-list'>"+
										"<div class='clearfix'>"+
										"<div class='pull-left comment-detail'>ADMIN</div>"+
										"<div class='pull-right comment-detail'>"+datetime+"</div>"+
										"</div>"+
										"<div class='comment-message'>"+"</div>"+
										"</div>");	
				$('.comment-wrapper').find('.comment-message').html(_text);
			}						
		}
		else{
			alert('diisi dulu mas bro');
		}
		$('#comment-area').val('');		
	});*/

	// tabs
//	$(".nav-tabs li").on('click',function(e){
//		e.preventDefault();
//		var _href = $(this).find('a').attr('href');
//		var baseurl = window.location.origin+window.location.pathname;
//
//		$(".nav-tabs li").removeClass('active');
//		$(this).addClass('active');
//		$('.tab-content .tab-pane').removeClass('active');
//		$(_href).addClass('active');
//	});
//	$(".tab-header").on('click',function(){
//		$(this).toggleClass('open');
//		$(this).siblings('.tab-body').slideToggle(200);
//	});

	// input
	$('input.must-fill').on('input',function(){
	  	$(this).removeClass('must-fill');
	  	if($(this).val() == ''){
	  		$(this).addClass('must-fill');
	  	}
	});

	// select
	$('select.must-fill').on('change',function(){
		$(this).removeClass('must-fill');
	  	if($(this).find('option')[0].selected === true){
	  		$(this).addClass('must-fill');
	  	}
	});

	// work sheet change function
	$('.worksheet-option').on('change',function(){
		var _checked = $(this).find('input').prop( "checked" );
		var _siblingDisabled = $(this).siblings('.worksheet-disabled').find('input, select');		
	  	if(_checked == true){
	  		_siblingDisabled.prop('disabled',false)
	  	}
	  	else{
	  		_siblingDisabled.prop('disabled',true)
	  	}
	});

	// multiple select
	$('.js-add-ratesheet').on('click',function(){
		if ($('select.left-select option:selected').val() != null) {
			var tempSelect = $('select.left-select option:selected').val();
			$('select.left-select option:selected').remove().appendTo('select.right-select');
			$('select.left-select option:selected').attr('selectedIndex', '-1').find("option:selected").removeAttr("selected");
          	$('select.right-select').attr('selectedIndex', '-1').find("option:selected").removeAttr("selected");
          	$('select.right-select').val(tempSelect);
          	tempSelect = '';
          	$('.form-validation-msg').hide();
		}
		else{
			$('.form-validation-msg').hide();
			$('.validate-add').show()
		}
	});

	$('.js-addAll-ratesheet').on('click',function(){
		var tempSelectAll = $('select.left-select option').val();
		if($('select.left-select option').length > 0){
			$('select.left-select option').remove().appendTo('select.right-select');
	      	$('select.right-select').val(tempSelectAll);
	      	tempSelectAll = '';
	      	$('.form-validation-msg').hide();
		}		
		else{
			$('.form-validation-msg').hide();
			$('.validate-addAll').show();
		}
	});

	$('.js-removeAll-ratesheet').on('click',function(){
		var tempRemoveAll = $('select.right-select option').val();
		if($('select.right-select option').length > 0){
			$('select.right-select option').remove().appendTo('select.left-select');
	      	$('select.left-select').val(tempRemoveAll);
	      	tempRemoveAll = '';
	      	$('.form-validation-msg').hide();
		}
		else{
			$('.form-validation-msg').hide();
			$('.validate-removeAll').show();
		}		
	});

	$('.js-remove-ratesheet').on('click',function(){
		if ($('select.right-select option:selected').val() != null) {
			var tempRemove = $('select.right-select option:selected').val();
			$('select.right-select option:selected').remove().appendTo('select.left-select');
			$('select.right-select option:selected').attr('selectedIndex', '-1').find("option:selected").removeAttr("selected");
          	$('select.left-select').attr('selectedIndex', '-1').find("option:selected").removeAttr("selected");
          	$('select.left-select').val(tempRemove);
          	tempRemove = '';
          	$('.form-validation-msg').hide();
		}
		else{
			$('.form-validation-msg').hide();
			$('.validate-remove').show()
		}
	});
		
	// add agent
	function addAgent(){
		var _val = $(".add-agent").siblings('input').val();
		if(_val === ''){
			$('.form-validation-msg').hide();
			$('.validate-agent').show();			
		}
		else{
			$("#input-agent").val('');	
			$('.form-validation-msg').hide();
			if($('.agent-list').length > 0) {
				$('.agent-list-wrapper').prepend("<div class='agent-list'>"+
										"<span>"+ _val +"</span>"+
										"<div class='remove-agent'>"+ "<i class='fa fa-close'></i>" + "</div>"+
										"</div>");
				if($('.agent-list').length > 6) {
					$('.agent-list').last().remove();
				}
			}
			else{
				$('.agent-list-wrapper').append("<div class='agent-list'>"+
										"<span>"+ _val +"</span>"+
										"<div class='remove-agent'>"+ "<i class='fa fa-close'></i>" + "</div>"+
										"</div>");	
			}
		}
	}
	function removeAgent(){
		$(".remove-agent").on('click',function(e){				
			e.target.closest('.agent-list').remove();
		});
	}
	$(".add-agent").on('click',function(){
		addAgent();
		removeAgent();
	});	
	$("#input-agent").keypress(function (e) {
	  if (e.which == 13) {
	    addAgent();
	    removeAgent();
	    return false;    //<---- Add this line
	  }
	});
}

$(REinit);




