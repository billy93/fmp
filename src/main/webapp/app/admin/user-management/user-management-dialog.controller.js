(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('UserManagementDialogController',UserManagementDialogController);

    UserManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'User', 'businessAreas'];

    function UserManagementDialogController ($stateParams, $uibModalInstance, entity, User, businessAreas) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.optionDepartment = ['RZT', 'RZI', 'RZD', 'RZ'];
        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.user = entity;
        vm.user.password="";
        vm.reviewLevels = [
            {"name":"DISTRIBUTION"},
            {"name":"HO"},
            {"name":"LSO"},
            {"name":"ROUTE_MANAGEMENT"}];
        vm.businessAreas = businessAreas;

        console.log(vm.user.reviewLevels);
        console.log(vm.user.reviewLevels == 'undefined');

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function onSaveSuccess (result) {
            vm.isSaving = false;
            $uibModalInstance.close(result);
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        function save () {
            vm.isSaving = true;
            if (vm.user.id !== null) {
                User.update(vm.user, onSaveSuccess, onSaveError);
            } else {
                vm.user.langKey = 'en';
                User.save(vm.user, onSaveSuccess, onSaveError);
            }
        }

        vm.addReviewLevel = function() {
        	if (vm.reviewLevel != undefined && vm.reviewLevel != null) {
        		if (!vm.user.reviewLevels)
        			vm.user.reviewLevels = [];

        		if (vm.user.reviewLevels.indexOf(vm.reviewLevel.name) == -1) {
        			vm.user.reviewLevels.push(vm.reviewLevel.name);
        		}

        		vm.reviewLevel = null;
        	}
        }

        vm.removeReviewLevel = function(index) {
        	vm.user.reviewLevels.splice(index, 1);
        }

        vm.addBusinessArea = function() {
        	if (vm.businessArea != undefined && vm.businessArea != null) {
        		if (!vm.user.businessAreas)
        			vm.user.businessAreas = [];

        		if (vm.user.businessAreas.indexOf(vm.businessArea.name) == -1) {
        			vm.user.businessAreas.push(vm.businessArea.name);
        		}

        		vm.businessArea = null;
        	}
        }

        vm.removeBusinessArea = function(index) {
        	vm.user.businessAreas.splice(index, 1);
        }
    }
})();
