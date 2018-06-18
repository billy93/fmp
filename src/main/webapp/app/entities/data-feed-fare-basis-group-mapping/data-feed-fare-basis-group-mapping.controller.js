(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('DataFeedFareBasisGroupMappingController', DataFeedFareBasisGroupMappingController);

    DataFeedFareBasisGroupMappingController.$inject = ['DataFeedFareBasisGroupMapping'];

    function DataFeedFareBasisGroupMappingController(DataFeedFareBasisGroupMapping) {

        var vm = this;

        vm.dataFeedFareBasisGroupMappings = [];

        loadAll();

        function loadAll() {
            DataFeedFareBasisGroupMapping.query(function(result) {
                vm.dataFeedFareBasisGroupMappings = result;
                vm.searchQuery = null;
            });
        }
    }
})();
