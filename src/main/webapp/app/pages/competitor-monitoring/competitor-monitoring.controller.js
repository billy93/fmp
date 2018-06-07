(function() {
	'use strict';

	angular.module('fmpApp').controller('CompetitorMonitoringController',
			CompetitorMonitoringController);

	CompetitorMonitoringController.$inject = [ '$state',
			'CompetitorMonitoring', 'ParseLinks', 'AlertService',
			'paginationConstants', 'queryParams', 'tariffNumbers', 'cities',
			'$uibModal' ];

	function CompetitorMonitoringController($state, CompetitorMonitoring,
			ParseLinks, AlertService, paginationConstants, queryParams,
			tariffNumbers, cities, $uibModal) {

		var vm = this;
		vm.loadPage = loadPage;
		vm.itemsPerPage = paginationConstants.itemsPerPage;
		vm.queryParams = queryParams;
		vm.loadAll = loadAll;
		vm.splitOrigDest = splitOrigDest;
		vm.getOrigDest = getOrigDest;
//		vm.checkRuleNoGA = checkRuleNoGA;
		vm.generateGraph = generateGraph;
		vm.graphdEnabled = false;
		vm.isDisabled = isDisabled;

		// vm.getRules = getRules;
		vm.showCategoryDetail = showCategoryDetail;
		vm.showLegend = showLegend;
		vm.viewFullText = viewFullText;

		vm.reset = reset;
		vm.page = 1;

		vm.origDest = getOrigDest;

		vm.datePickerOpenStatus = {};
		vm.dateFormat = "yyyy-MM-dd";
		vm.openCalendar = openCalendar;

		vm.sources = [ {
			key : "",
			value : "Select Source"
		}, {
			key : "A",
			value : "A - ATPCO"
		}, {
			key : "M",
			value : "M - Market"
		}, {
			key : "W",
			value : "W - Web"
		}, ]

		vm.publicPrivate = [ {
			key : "",
			value : "Select Public/Private"
		}, {
			key : "Public",
			value : "Public"
		}, {
			key : "Private",
			value : "Private"
		}, ]

		vm.owrts = [ {
			key : "",
			value : "Select OW/RT"
		}, {
			key : "1",
			value : "1 - One Way"
		}, {
			key : "2",
			value : "2 - Rount Trip"
		}, {
			key : "3",
			value : "3 - One Way Only"
		}, ]

		vm.dateOptions = [ {
			key : "A",
			value : "Active In"
		}, {
			key : "E",
			value : "Exact Match"
		} ]

		vm.tariffs = tariffNumbers;

		vm.cities = cities;

		vm.globalIndicators = "?";

		vm.gaFareTypes = "?";

		vm.fareTypes = "?";

		vm.paxTypes = "?";

		vm.cabins = "?";

		// vm.loadAll();

		function loadAll() {
			vm.categoryRules = null;
			vm.currentCompetitorMonitoring = null;

			vm.queryParams.page = vm.page - 1;
			vm.queryParams.size = vm.itemsPerPage;

//			vm.checkRuleNoGA();
			vm.splitOrigDest(vm.origDest);
			
			CompetitorMonitoring.query(vm.queryParams, onSuccess, onError);
			

			function onSuccess(data, headers) {
				vm.links = ParseLinks.parse(headers('link'));
				vm.totalItems = headers('X-Total-Count');
				vm.queryCount = vm.totalItems;
				vm.compMonitoring = data;
				vm.graphdEnabled = isDisabled();
			}

			function onError(error) {
				AlertService.error(error.data.message);
			}
		}

		function loadPage(page) {
			vm.page = page;
		}

		function getRules(compMonitoring) {
			if (vm.currentCompetitorMonitoring == undefined
					|| vm.currentCompetitorMonitoring == null
					|| vm.currentCompetitorMonitoring != compMonitoring) {
				CompetitorMonitoring.getRules(compMonitoring, function(data) {
					vm.categoryRules = data;
					vm.currentCompetitorMonitoring = compMonitoring;
					// console.log(vm.categoryRules);
				}, function(error) {
					console.log(error);
				});
			}
		}

		function openCalendar(e, date) {
			e.preventDefault();
			e.stopPropagation();

			vm.datePickerOpenStatus[date] = true;
		}

		function reset() {
			$('#container').hide();
			vm.queryParams = {
				carrier : null,
				source : null,
				publicPrivate : null,
				tariff : null,
				globalIndicator : null,
				gaFareType : null,
				fareType : null,
				fareBasis : null,
				origin : null,
				destination : null,
				owrt : null,
				footnote : null,
				ruleNo : null,
				routingNo : null,
				woId : null,
				effectiveDateFrom : null,
				effectiveDateTo : null,
				effectiveDateOption : null,
				saleDateFrom : null,
				saleDateTo : null,
				saleDateOption : null,
				travelDateFrom : null,
				travelDateTo : null,
				travelDateOption : null,
				seasonDateFrom : null,
				seasonDateTo : null,
				seasonDateOption : null,
				amountRange : null,
				tourCode : null,
				paxType : null,
				cabin : null,
				bookingClass : null,
				advancePurchase : null,
				minStay : null,
				maxStay : null,
				includeConstructed : false,
				appendResults : false,
				biDirectional : false,
				calculateTfc : false
			}

//			vm.loadAll();
		}

		function showCategoryDetail(category) {
			$uibModal.open({
				templateUrl : 'app/pages/category-modals/category-modal.html',
				controller : 'CategoryModalController',
				controllerAs : 'vm',
				backdrop : 'static',
				size : 'lg',
				windowClass : 'full',
				resolve : {
					entity : category
				}
			}).result.then(function() {
				$state.go('afd-query', {}, {
					reload : false
				});
			}, function() {
				$state.go('afd-query');
			});
		}

		function showLegend() {
			$uibModal.open({
				templateUrl : 'app/pages/category-modals/legend-modal.html',
				controller : 'LegendModalController',
				controllerAs : 'vm',
				backdrop : 'static',
				size : 'md'
			}).result.then(function() {
				$state.go('afd-query', {}, {
					reload : false
				});
			}, function() {
				$state.go('afd-query');
			});
		}

		function viewFullText() {
			$uibModal.open({
				templateUrl : 'app/pages/category-modals/full-text-modal.html',
				controller : 'FullTextModalController',
				controllerAs : 'vm',
				backdrop : 'static',
				size : 'lg',
				windowClass : 'full',
				resolve : {
					entity : {
						categories : vm.categoryRules
					}
				}
			}).result.then(function() {
				$state.go('afd-query', {}, {
					reload : false
				});
			}, function() {
				$state.go('afd-query');
			});
		}

		function getOrigDest() {
			if (vm.queryParams.origin != null
					&& vm.queryParams.destination != null) {
				return vm.queryParams.origin + "-" + vm.queryParams.destination
			}
		}

		function splitOrigDest(origDest) {
			origDest = origDest+""; //converting to string, otherwise got an error here...
			var chr = origDest.indexOf("-");
			var orig = origDest.substring(0, chr);
			var dest = origDest.substring(chr + 1, origDest.length);

			vm.queryParams.origin = orig;
			vm.queryParams.destination = dest;
		}

		function checkRuleNoGA() {
			if (vm.queryParams.ruleNo != null) {
				if (vm.queryParams.carrierCode == null) {
					vm.queryParams.carrierCode = 'GA';
					console.log('masuk sini gak ???');
				}
			}
		}
		
		function isDisabled() {
			if(vm.compMonitoring.length > 0) {
				return true;
			} else {
				return false;
			}
		}

		function generateGraph(data) {

			$('#container').show();
			var dec = 0.003
			var categories = [ 'GA', 'SQ' ];
			var seriesData = [ {
				name : 'GA',
				data : [ {
					x : 0,
					name : "Cab1",
					color : 'rgba(0,0,255,0.1)',
					high : 100000,
					low : 100000 - (100000 * dec),

				}, {
					x : 0,
					name : "Cab2",
					color : 'rgba(0,255,0,0.1)',
					high : 90000,
					low : 90000 - (90000 * dec),
				}, {
					x : 0,
					name : "Cab2",
					color : 'rgba(0,255,0,0.1)',
					high : 90010,
					low : 90010 - (90010 * dec),
				}, {
					x : 0,
					name : "Cab2",
					color : 'rgba(0,255,0,0.1)',
					high : 90050,
					low : 90050 - (90050 * dec),
				}, {
					x : 0,
					high : 95000,
					low : 95000 - (90000 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95000,
					low : 95000 - (90000 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 94000,
					low : 94000 - (94000 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95100,
					low : 95100 - (95100 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95800,
					low : 95800 - (95800 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 94700,
					low : 94700 - (94700 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95900,
					low : 95900 - (95900 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95950,
					low : 95950 - (95950 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95800,
					low : 95800 - (95800 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 94700,
					low : 94700 - (94700 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95850,
					low : 95850 - (95850 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95980,
					low : 95980 - (95980 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95990,
					low : 95990 - (95990 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95995,
					low : 95995 - (95995 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 95997,
					low : 95997 - (95997 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 96000,
					low : 96000 - (96000 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 96010,
					low : 96010 - (96010 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 96020,
					low : 96020 - (96020 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 96030,
					low : 96030 - (96030 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 0,
					high : 96040,
					low : 96040 - (96040 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				} ]
			}, {
				name : 'sQ',
				data : [ {
					x : 1,
					high : 92000,
					low : 92000 - (92000 * dec),
					name : "Cab3",
					color : 'rgba(0,255,0,0.1)',
				}, {
					x : 1,
					high : 93000,
					low : 93000 - (93000 * dec),
					name : "Cab3",
					color : 'rgba(0,255,0,0.1)',
				}, {
					x : 1,
					high : 95000,
					low : 95000 - (90000 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95000,
					low : 95000 - (90000 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95100,
					low : 95100 - (95100 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95800,
					low : 95800 - (95800 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95900,
					low : 95900 - (95900 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95950,
					low : 95950 - (95950 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95800,
					low : 95800 - (95800 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 94700,
					low : 94700 - (94700 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95850,
					low : 95850 - (95850 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95980,
					low : 95980 - (95980 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95990,
					low : 95990 - (95990 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95995,
					low : 95995 - (95995 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 95997,
					low : 95997 - (95997 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 96000,
					low : 96000 - (96000 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 96010,
					low : 96010 - (96010 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 96020,
					low : 96020 - (96020 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 96030,
					low : 96030 - (96030 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				}, {
					x : 1,
					high : 96040,
					low : 96040 - (96040 * dec),
					name : "Cab3",
					color : 'rgba(255,0,0,0.1)',
				} ]
			}, ];

			var chartOptions = {
				chart : {
					renderTo : 'container',
					type : 'columnrange',
					zoomType : 'xy',
					inverted : false,
				},
				credits : {
					enabled : false
				},
				title : {
					text : 'Price Comparator'
				},
				xAxis : {
					type : 'category',
					categories : categories,
					gridLineWidth : 2
				},
				yAxis : {
					type : 'logarithmic',
					tickmarkPlacement : 'on',
					title : {
						text : 'Price'
					}
				},
				tooltip : {
					enabled : true
				},
				plotOptions : {
					columnrange : {
						stickyTracking : false,
						grouping : false
					}
				},
				legend : {
					enabled : false
				},
				scrollbar : {
					enabled : true
				},
				series : seriesData
			};

			var chart1 = new Highcharts.Chart(chartOptions);
			chart1.redraw();

		}

	}
})();
