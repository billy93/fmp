(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RoutingqueryDetailController', RoutingqueryDetailController);

    RoutingqueryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Routingquery'];

    function RoutingqueryDetailController($scope, $rootScope, $stateParams, previousState, entity, Routingquery) {
        var vm = this;

        vm.routingquery = entity;
        console.log("ini");
        console.log(entity);
        vm.previousState = previousState.name;
        vm.selectTab = selectTab;
        
        vm.currentTab = [true, false, false];
        $("#tab-0").show();
    	$("#tab-1").hide();
    	$("#tab-2").hide();

        var unsubscribe = $rootScope.$on('fmpApp:routingqueryUpdate', function(event, result) {
            vm.routingquery = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        function selectTab(index) {
        	vm.currentTab = [false, false, false];
        	$("#tab-0").hide();
        	$("#tab-1").hide();
        	$("#tab-2").hide();
        	
        	vm.currentTab[index] = true;
        	$("#tab-"+index).show();
        }
    }
    
})();
