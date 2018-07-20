(function() {
    'use strict';
    angular
        .module('fmpApp')
        .factory('WorkPackage', WorkPackage);

    WorkPackage.$inject = ['$resource', 'DateUtils'];

    function WorkPackage ($resource, DateUtils) {
        var resourceUrl =  'api/work-packages/:id';

        function transformResponse(data){
        	if (data) {
                data = angular.fromJson(data);
                data.filingDate = DateUtils.convertDateTimeFromServer(data.filingDate);
                data.newCreatedDate = DateUtils.convertDateTimeFromServer(data.createdDate);

                data.distributionDate = DateUtils.convertDateTimeFromServer(data.distributionDate);
                data.discExpiryDate = DateUtils.convertDateTimeFromServer(data.discExpiryDate);
                data.queuedDate = DateUtils.convertDateTimeFromServer(data.queuedDate);
                data.lockedSince = DateUtils.convertDateTimeFromServer(data.lockedSince);

                data.saleDate = DateUtils.convertDateFromServer(data.saleDate);

                if(data.fareSheet != null && data.fareSheet.length > 0){
                	for(var x=0;x<data.fareSheet.length;x++){
                		var fares = data.fareSheet[x].fares;
                		for(var y=0;y<fares.length;y++){
                    		if(fares[y] != null){
                    			fares[y].travelStart = DateUtils.convertDateFromServer(fares[y].travelStart);
                    			fares[y].travelEnd = DateUtils.convertDateFromServer(fares[y].travelEnd);
                    			fares[y].saleStart = DateUtils.convertDateFromServer(fares[y].saleStart);
                    			fares[y].saleEnd = DateUtils.convertDateFromServer(fares[y].saleEnd);
                    			fares[y].travelComplete = DateUtils.convertDateFromServer(fares[y].travelComplete);
                    		}
                		}
                	}
                }

                if(data.addonFareSheet != null && data.addonFareSheet.length > 0){
                	for(var x=0;x<data.addonFareSheet.length;x++){
                		var fares = data.addonFareSheet[x].fares;
                		for(var y=0;y<fares.length;y++){
                    		if(fares[y] != null){
                    			fares[y].travelStart = DateUtils.convertDateFromServer(fares[y].travelStart);
                    			fares[y].travelEnd = DateUtils.convertDateFromServer(fares[y].travelEnd);
                    			fares[y].saleStart = DateUtils.convertDateFromServer(fares[y].saleStart);
                    			fares[y].saleEnd = DateUtils.convertDateFromServer(fares[y].saleEnd);
                    			fares[y].travelComplete = DateUtils.convertDateFromServer(fares[y].travelComplete);
                    		}
                		}
                	}
                }

                if(data.marketFareSheet != null && data.marketFareSheet.length > 0){
                	for(var x=0;x<data.marketFareSheet.length;x++){
                		var fares = data.marketFareSheet[x].fares;
                		for(var y=0;y<fares.length;y++){
                    		if(fares[y] != null){
                    			fares[y].travelStart = DateUtils.convertDateFromServer(fares[y].travelStart);
                    			fares[y].travelEnd = DateUtils.convertDateFromServer(fares[y].travelEnd);
                    			fares[y].saleStart = DateUtils.convertDateFromServer(fares[y].saleStart);
                    			fares[y].saleEnd = DateUtils.convertDateFromServer(fares[y].saleEnd);
                    			fares[y].travelComplete = DateUtils.convertDateFromServer(fares[y].travelComplete);
                    		}
                		}
                	}
                }


                if(data.discountFareSheet != null && data.discountFareSheet.length > 0){
                	for(var x=0;x<data.discountFareSheet.length;x++){
                		var fares = data.discountFareSheet[x].fares;
                		for(var y=0;y<fares.length;y++){
                    		if(fares[y] != null){
                    			fares[y].travelStart = DateUtils.convertDateFromServer(fares[y].travelStart);
                    			fares[y].travelEnd = DateUtils.convertDateFromServer(fares[y].travelEnd);
                    			fares[y].saleStart = DateUtils.convertDateFromServer(fares[y].saleStart);
                    			fares[y].saleEnd = DateUtils.convertDateFromServer(fares[y].saleEnd);
                    			fares[y].travelComplete = DateUtils.convertDateFromServer(fares[y].travelComplete);
                    		}
                		}
                	}
                }

            	if(data.filingDetail != null && data.filingDetail.filingDetailTarif != null){
            		for(var x=0;x<data.filingDetail.filingDetailTarif.length;x++){
		                if(data.filingDetail.filingDetailTarif[x].batch.length > 0){
		                	for(var y=0;y<data.filingDetail.filingDetailTarif[x].batch.length;y++){
		                		data.filingDetail.filingDetailTarif[x].batch[y].gfsDate = DateUtils.convertDateTimeFromServer(data.filingDetail.filingDetailTarif[x].batch[y].gfsDate);
		                	}
		                }
            		}
            	}

            	if(data.filingDetail != null && data.filingDetail.createdDate != null){
            		data.filingDetail.createdDate = DateUtils.convertDateTimeFromServer(data.filingDetail.createdDate);
            	}
            	if(data.filingDetail != null && data.filingDetail.releaseDate != null){
            		data.filingDetail.releaseDate = DateUtils.convertDateTimeFromServer(data.filingDetail.releaseDate);
            	}

            }
        	return data;
        }

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'findDiscountDerivedFares': { method: 'POST', isArray: true, url:'api/work-packages/derived/findFares'},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    return transformResponse(data);
                }
            },
            'getQuery': {
                method: 'GET',
                url:'api/work-packages/view/:id',
                transformResponse: function (data) {
                    return transformResponse(data);
                }
            },
            'update': { method:'PUT' },
            'createFromRecommendation': {
            	    method:'POST', url:'api/work-packages/create/fromRecommendation'
            },
            'passup': {
            		method:'POST', url:'api/work-packages/passup'
            },
            'passdown': {
        			method:'POST', url:'api/work-packages/passdown'
            },
            'passsideway': {
	    			method:'POST', url:'api/work-packages/passsideway'
	        },
	        'approve': {
	    			method:'POST', url:'api/work-packages/approve'
	        },
	        'resendApprove': {
    			method:'POST', url:'api/work-packages/resend-approve'
	        },
	        'referback': {
	    			method:'POST', url:'api/work-packages/referback'
	        },
	        'complete': {
    			method:'POST', url:'api/work-packages/complete'
	        },
	        'createbatch': {
	    			method:'POST', url:'api/work-packages/createbatch'
	        },
	        'revisebatch': {
    				method:'POST', url:'api/work-packages/revisebatch'
	        },
	        'completebatch': {
				method:'POST', url:'api/work-packages/completebatch'
	        },
	        'reuse': {
				method:'POST', url:'api/work-packages/reuse'
	        },
	        'replace': {
				method:'POST', url:'api/work-packages/replace'
	        },
	        'withdraw': {
				method:'POST', url:'api/work-packages/withdraw'
	        },
	        'unlock': {
				method:'POST', url:'api/work-packages/unlock'
	        },
	        'closeEditor': {
				method:'POST', url:'api/work-packages/closed'
	        },
	        'history': { method: 'GET', isArray: true, url:'api/work-packages/history/:id'},
	        'changeVersion': { method: 'POST', url:'api/work-packages/changeVersion'},
	        'exportQueue': {method:'POST', url:'api/work-packages/exportQueue'},
	        'exportQueueQuery': {method:'POST', url:'api/work-packages/exportQueueQuery'},
	        'importFares': { method: 'POST',  url:'api/work-packages/import-fares'},
	        'importFaresAddon': { method: 'POST',  url:'api/work-packages/import-fares-addon'},
	        'importFaresMarket': { method: 'POST',  url:'api/work-packages/import-fares-market'},
	        'importFaresDiscount': { method: 'POST',  url:'api/work-packages/import-fares-discount'},
	        'importFaresWaiver': { method: 'POST',  url:'api/work-packages/import-fares-waiver'},
	        'exportFares': { method: 'POST',  url:'api/work-packages/export-fares'},
	        'exportFaresAddon': { method: 'POST',  url:'api/work-packages/export-fares-addon'},
	        'exportFaresMarket': { method: 'POST',  url:'api/work-packages/export-fares-market'},
	        'exportFaresDiscount': { method: 'POST',  url:'api/work-packages/export-fares-discount'},
	        'exportFaresWaiver': { method: 'POST',  url:'api/work-packages/export-fares-waiver'},
	        'exportDerivedFares': { method: 'POST',  url:'api/work-packages/derived/exportDerivedFares'},
	        'publish' : { method: 'POST',  url:'api/work-packages/market/publish'},
	        'exportRateSheet': { method: 'POST',  url:'api/work-packages/export-ratesheet'},
	        'exportRateSheetCSV': { method: 'POST',  url:'api/work-packages/export-ratesheet-csv'},
	        'exportRateSheetExcel': { method: 'POST',  url:'api/work-packages/export-ratesheet-excel'},
	        'exportRateSheetWord': { method: 'POST',  url:'api/work-packages/export-ratesheet-word'},
	        'exportRateSheetDiscount': { method: 'POST',  url:'api/work-packages/export-ratesheet-discount'},
            'exportRateSheetCSVDiscount': { method: 'POST',  url:'api/work-packages/export-ratesheet-csv-discount'},
	        'exportRateSheetExcelDiscount': { method: 'POST',  url:'api/work-packages/export-ratesheet-excel-discount'},
	        'exportRateSheetWordDiscount': { method: 'POST',  url:'api/work-packages/export-ratesheet-word-discount'},
	        'exportRateSheetWaiver': { method: 'POST',  url:'api/work-packages/export-ratesheet-waiver'},
            'exportRateSheetCSVWaiver': { method: 'POST',  url:'api/work-packages/export-ratesheet-csv-waiver'},
	        'exportRateSheetExcelWaiver': { method: 'POST',  url:'api/work-packages/export-ratesheet-excel-waiver'},
	        'exportRateSheetWordWaiver': { method: 'POST',  url:'api/work-packages/export-ratesheet-word-waiver'},
	        'downloadMarketRules': { method: 'POST',  url:'api/work-packages/download-market-rules'},
	        'workPackagefilter': { method: 'GET',  url:'api/work-packages/byname'},
	        'discontinue': { method: 'POST',  url:'api/work-packages/discontinue'},
	        'updateLatestFare' : { method: 'POST',  url:'api/work-packages/update-latest-fare'},
	        'updateActionCodes' : { method: 'POST',  url:'api/work-packages/update-action-codes'},
	        'refreshTariff' : { method: 'POST',  url:'api/work-packages/refresh-tariff'},
	        'customQuery': { method: 'POST', url:'api/work-packages/query', isArray:true}
        });
    }
})();
