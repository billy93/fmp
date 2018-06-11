(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('WorkPackageDialogController', WorkPackageDialogController);

    WorkPackageDialogController.$inject = ['type', '$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkPackage', 'reviewLevels', 'businessAreas', 'fareTypes', 'atpcoFareTypes'];

    function WorkPackageDialogController (type, $timeout, $scope, $stateParams, $uibModalInstance, entity, WorkPackage, reviewLevels, businessAreas, fareTypes, atpcoFareTypes) {
        var vm = this;

        vm.workPackage = entity;
        vm.workPackage.fareSheet = [{fareCarrier:'GA'}];
        vm.workPackage.addonFareSheet = [{fareCarrier:'GA'}];
        vm.workPackage.discountFareSheet = [{fareCarrier:'GA'}];
        vm.workPackage.marketFareSheet = [{fareCarrier:'GA'}];
        vm.workPackage.waiverFareSheet = [{fareCarrier:'GA'}];
        vm.reviewLevels = reviewLevels;
        vm.businessAreas = businessAreas;
        
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.fareType = {};
        vm.optionFare = fareTypes;
        vm.atpcoFareTypes = atpcoFareTypes;

        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        
        vm.datePickerOpenStatus.saleDate = false;
        
        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
                
        vm.distributionTypes = ['ATPCO'];
        
        if($stateParams.type == null){
        		$stateParams.type = type;
        }
        
        if($stateParams.type == 'reguler'){
        		vm.workPackage.type = 'REGULAR';
        		vm.distributionTypes = ['ATPCO', 'MARKET'];
        }
        else if($stateParams.type == 'discount'){
        		vm.workPackage.type = 'DISCOUNT';
        		vm.distributionTypes = ['ATPCO'];
        }
        else if($stateParams.type == 'waiver'){
    		vm.workPackage.type = 'WAIVER';
    		vm.distributionTypes = ['MARKET'];
        }
        
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.workPackage.id !== null) {
                WorkPackage.update(vm.workPackage, onSaveSuccess, onSaveError);
            } else {
                WorkPackage.save(vm.workPackage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:workPackageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.fillingDate = false;
        vm.datePickerOpenStatus.distributionDate = false;
        vm.datePickerOpenStatus.discExpiryDate = false;
        vm.datePickerOpenStatus.queuedDate = false;
        vm.datePickerOpenStatus.lockedSince = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
        
        vm.checkMarketRules = function(field){
        	if(field == 'marketFares'){
	        	if(vm.workPackage.marketFares){
	        		vm.workPackage.addon = false;
	        		vm.workPackage.marketRules = true;
	        	}
	        	else{
	        		vm.workPackage.addon = true;
	        		vm.workPackage.marketRules = false;
	        	}
        	} else if(field == 'addon'){
            	if(vm.workPackage.addon){
            		vm.workPackage.marketFares = false;
            		vm.workPackage.marketRules = false;
            	}
            	else{
            		vm.workPackage.marketFares = true;
            		vm.workPackage.marketRules = true;
            	}
        	}
        }
        
        
    }
})();
