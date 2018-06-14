(function() {
    'use strict';

    angular
        .module('fmpApp')
        .constant('Timezone', (function() {
            return {
                GMT7: '+0700'
            }
        })());
})();
