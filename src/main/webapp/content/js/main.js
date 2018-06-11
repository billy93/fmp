
$(function(){
	//navbar
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
	
	$(document).on('click', '.showLeftPush', function(){	     				
		sidebarOpen();
	});
	
	$(document).on('click','.menu-list',function(e){	
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
	
	$(document).on('click','.search-wrap i',function(){	
		if (!$('.menu-list').hasClass('open')){
			sidebarOpen()
			$('.search-wrap input').focus();
		}		
	});
	
	$(document).on('click','.linkClick',function(e){
		e.stopPropagation();
		sidebarClose()
	 });
	
	$(document).on('click','.btn-group.edit-btn',function(e){
		e.stopPropagation();
		sidebarClose()
	 });
	
	
	// dropdown
	$(document).mouseup(function (e){	
	    if($('.dropdown-menu.custom').has(e.target).length === 0)
	    {
	        //hide your element
	        $('.dropdown.custom').removeClass('open');
	        $('.dropdown-menu.custom').hide();
	    }
	});

	$(document).on('click', '.dropdown.custom', function(){
		$(this).toggleClass('open');
		if($(this).hasClass('open')){
			$(this).find('.dropdown-menu.custom').slideDown(100);
		}
		else{
			$(this).find('.dropdown-menu.custom').slideUp(100);
		}		
	});
	
	// filter
	$(document).on('click','.filter-label',function(){
		$(this).toggleClass('show');
		$(this).siblings('.filter-area').slideToggle(200);
		if($(this).hasClass('show')){
			$(this).html('Hide Filter')
		}
		else{
			$(this).html('Show Filter')
		}
	});	
	
	
//	box-header
	$(document).on('click','.box-header',function(){
		$(this).toggleClass('open');
		$(this).siblings('.box-body').slideToggle(200);
	});
	
	$(document).on('click','.box-header.box-tab',function(){
		$(this).find('.tab-content').slideToggle(200);
	});
	
	$(document).on('click','.sub-table',function(e){
		e.stopPropagation();
		$(this).toggleClass('opened');
		$(this).find('.sub-second-th').slideToggle(200);
		$(this).find('tbody').slideToggle(200);
	});
	
//	range slider
	$(document).on('change','.custom-range-slider',function(){
		var _display = $(this).parents('.rangeslider-wrapper').find('.range-slider-time');
		var _val = $(this).val();
		if(_val == 1){
			_display.text('1 Day');
		}
		else if(_val == 2){
			_display.text('2 Days');
		}
		else if(_val == 3){
			_display.text('3 Days');
		}
		else if(_val == 4){
			_display.text('4 Days');
		}
		else if(_val == 5){
			_display.text('5 Days');
		}
		else if(_val == 6){
			_display.text('6 Days');
		}
		else if(_val == 7){
			_display.text('1 Week');
		}
		else if(_val == 8){
			_display.text('2 Weeks');
		}
		else if(_val == 9){
			_display.text('3 Weeks');
		}
		else if(_val == 10){
			_display.text('1 Month');
		}
		else if(_val == 11){
			_display.text('2 Months');
		}
		else if(_val == 12){
			_display.text('3 Months');
		}
		else if(_val == 13){
			_display.text('4 Months');
		}
		else if(_val == 14){
			_display.text('5 Months');
		}
		else if(_val == 15){
			_display.text('6 Months');
		}
		else if(_val == 16){
			_display.text('7 Months');
		}
		else if(_val == 17){
			_display.text('8 Months');
		}
		else if(_val == 18){
			_display.text('9 Months');
		}
		else if(_val == 19){
			_display.text('10 Months');
		}
		else if(_val == 20){
			_display.text('11 Months');
		}
		else if(_val == 21){
			_display.text('1 Year');
		}
		else if(_val == 22){
			_display.text('2 Years');
		}
		else if(_val == 23){
			_display.text('3 Years');
		}
		else if(_val == 24){
			_display.text('EveryTime');
		}
	});
	
});
