(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService', '$window', 'GlobalService'];

    function NavbarController ($state, Auth, Principal, ProfileService, LoginService, $window, GlobalService) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerEnabled = response.swaggerEnabled;
        });

        vm.login = login;
        vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.$state = $state;

//        vm.toggle = function(){
//        	$('.showLeftPush').toggleClass('active')
//    		$('#sideBar-s1').toggleClass('sideBar-open');
//    		$('.search-wrap').find('i').toggleClass('shrink');
//    		$('.sideBar').find('.menu-list').toggleClass('open');
//    		$('.menu-list').removeClass('sub');
//    		$('.sub-menu').slideUp(200);
//    		$('.sidebar-toggle').toggleClass('pushed');
//    		$('.fa-home').toggleClass('hidden');
//        }
//        
//        vm.toggleMenu = function(e){
//        	e.preventDefault();
//        	var element = angular.element(e.currentTarget);
//        	if(element.hasClass('open')){
//    			$('.menu-list').not(element).removeClass('sub');
//    			$('.menu-list').not(element).find('.sub-menu').slideUp(200);
//    			element.toggleClass('sub');
//    			element.find('.sub-menu').slideToggle(200);
//    		}
//    		else{
//    			if (element.hasClass('has-sub')){
//    				element.toggleClass('sub');
//    				element.find('.sub-menu').slideToggle(200);
//    			}
//    		}	
//        };
        
        vm.test = function(){
        	 
        }
        
        function login() {
            collapseNavbar();
            LoginService.open();
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $state.go('home'); $window.location.reload();
        }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }
        
        
        GlobalService.navbar();
    }
})();
