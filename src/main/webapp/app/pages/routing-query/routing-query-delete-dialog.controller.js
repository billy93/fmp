(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RoutingqueryDeleteController',RoutingqueryDeleteController);

    RoutingqueryDeleteController.$inject = ['$uibModalInstance', 'entity', 'Routingquery'];

    function RoutingqueryDeleteController($uibModalInstance, entity, Routingquery) {
        var vm = this;

        vm.routingquery = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Routingquery.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
