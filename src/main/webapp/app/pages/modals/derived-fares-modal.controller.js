(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DerivedFareModalController', DerivedFareModalController);

    DerivedFareModalController.$inject = ['$state', '$uibModalInstance', 'entity', 'DerivedFare', 'Passenger'];

    function DerivedFareModalController($state, $uibModalInstance, entity, DerivedFare, Passenger) {
        var vm = this;
        vm.clear = clear;
        
        vm.param = entity;
        vm.paxTypes = Passenger.getAll();
        
        vm.queryParams = {
			paxType: 'ADT',
    		paxAge: 21,
    		accountCode: null,
    		ticketDesignator: null
        }
        
        vm.generateDerivedFares = function() {
        	vm.queryParams.fares = vm.param.fares;
        	
        	DerivedFare.getAfdQueryDerivedFares(vm.queryParams, function(data) {
        		console.log(data);
        	}, function(error) {
        		console.log(error);
        	});
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
