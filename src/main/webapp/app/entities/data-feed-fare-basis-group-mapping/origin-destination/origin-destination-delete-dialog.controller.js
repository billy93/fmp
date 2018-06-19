(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('OriginDestinationDeleteController',OriginDestinationDeleteController);

    OriginDestinationDeleteController.$inject = ['$uibModalInstance', 'entity', 'OriginDestination'];

    function OriginDestinationDeleteController($uibModalInstance, entity, OriginDestination) {
        var vm = this;

        vm.originDestination = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
        	OriginDestination.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
