(function() {
    'use strict';

    angular
        .module('fmpApp')
        .factory('DateUtils', DateUtils);

    DateUtils.$inject = ['$filter'];

    function DateUtils($filter) {

        var service = {
            convertDateTimeFromServer: convertDateTimeFromServer,
            convertLocalDateFromServer: convertLocalDateFromServer,
            convertLocalDateToServer: convertLocalDateToServer,
            convertDateFromServer: convertDateFromServer,
            dateformat: dateformat
        };

        return service;

        function convertDateFromServer(date){
        	 if (date) {
        		 date = date.substring(0,10).split('-');
        		 date = date[1] + '-' + date[2] + '-' + date[0];
                 return new Date(date);
             } else {
                 return null;
             }
        }
        
        function convertDateTimeFromServer(date) {
            if (date) {
                return new Date(date);
            } else {
                return null;
            }
        }

        function toTimeZone(time, zone) {
            var format = 'YYYY/MM/DD HH:mm:ss ZZ';
            return moment(time, format).tz(zone).format(format);
        }
        
        function convertLocalDateFromServer(date) {
            if (date) {
                var dateString = date.split('-');
                return new Date(dateString[0], dateString[1] - 1, dateString[2]);
            }
            return null;
        }

        function convertLocalDateToServer(date) {
            if (date) {
                return $filter('date')(date, 'yyyy-MM-dd');
            } else {
                return null;
            }
        }

        function dateformat() {
            return 'yyyy-MM-dd';
        }
    }

})();
