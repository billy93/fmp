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
		vm.generateGraph = generateGraph;
		vm.graphDisabled = true;
		vm.isDisabled = isDisabled;

		// vm.getRules = getRules;
		vm.showCategoryDetail = showCategoryDetail;
		vm.showLegend = showLegend;
		vm.viewFullText = viewFullText;

		vm.reset = reset;
		vm.page = 1;

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
			vm.splitOrigDest(vm.queryParams.origDest);
			
			CompetitorMonitoring.query(vm.queryParams, onSuccess, onError);
			

			function onSuccess(data, headers) {
				vm.links = ParseLinks.parse(headers('link'));
				vm.totalItems = headers('X-Total-Count');
				vm.queryCount = vm.totalItems;
				vm.compMonitoring = data;
				vm.graphEnabled = isDisabled();
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
			$('#chartOptions').hide();
			vm.compMonitoring = null;
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
				calculateTfc : false,
				origDest : null
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
				$state.go('competitor-monitoring', {}, {
					reload : false
				});
			}, function() {
				$state.go('competitor-monitoring');
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
				$state.go('competitor-monitoring', {}, {
					reload : false
				});
			}, function() {
				$state.go('competitor-monitoring');
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

		function splitOrigDest(origDest) {
			if(origDest != null && origDest.trim() != '') {
				origDest = origDest+""; //converting to string, otherwise got an error here...
				var chr = origDest.indexOf("-");
				var orig = origDest.substring(0, chr);
				var dest = origDest.substring(chr + 1, origDest.length);

				vm.queryParams.origin = orig;
				vm.queryParams.destination = dest;
			}
			
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
				return false;
			} else {
				return true;
			}
		}

		function generateGraph(data) {

				$('#container').show();
				$('#chartOptions').show();
				
				$('#barThickness option[value="0.04"]').attr("selected",true);
				$('#barOpacity option[value="0.1"]').attr("selected",true);
			  	
				var fares = data;
			  
			  
			      var barThickness = $('#barThickness').val();;
			      var barOpacity = $('#barOpacity').val();;
			      
			      var totalData = fares.length;
			      var items = [];
			      var dataGroup = [];
			      var dataGraph = [];
			    
						for(var i=0;i<fares.length;i++) {
							items.push(fares[i].carrierCode);
						}
			    
			      var categories = Array.from(new Set(items));
			    
			      var catLogo = [];
			      var catWidth = $('#container').width/categories.length;
			      var cabColor = '';
			    
			      
			    for(var j=0;j<categories.length;j++) {
			      for(var k=0;k<fares.length;k++) {
			    	  
			        if(fares[k].carrierCode==categories[j]) {
			          var amt = String(fares[k].baseAmount);
			          
			          
			          if(fares[k].cabin == 'Y') {
			        	  cabColor = 'rgba(255,0,0,'+barOpacity+')';
			          } else if(fares[k].cabin == 'W') {
			        	  cabColor = 'rgba(0,255,0,'+barOpacity+')';
			          } else if(fares[k].cabin == 'F') {
			        	  cabColor = 'rgba(0,0,255,'+barOpacity+')';
			          } else if(fares[k].cabin == 'J') {
			        	  cabColor = 'rgba(255,255,0,'+barOpacity+')';
			          }
			          var lowVal = parseFloat(String(amt))-(parseFloat(String(amt))*parseFloat(String(barThickness)));
			           dataGraph.push({'x':j,'name':fares[k].cabin,'bookingClass':fares[k].bookingClass,'color':cabColor,'high':amt,'low':lowVal});
			        }
			    }
			      var objCat = {'name':categories[j], 'data':dataGraph};
			      dataGroup.push(objCat);
			    }
			
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
			            text : ''
			          },
			          xAxis :
			            [{
			               type : 'category',
			               categories : categories,
			               gridLineWidth : 2,
			            },{
			              linkedTo: 0,
			              type : 'category',
			              categories : categories,
			              opposite:true,
			              labels: {
			              useHTML: true,
			              formatter: function() {
//			              if(this.value == "GA")
//			                  return '<img src="http://3.bp.blogspot.com/_UZImdYAiry8/SV9oVvC_WqI/AAAAAAAAOZI/vWKb9Pjuhl4/s400/Logo_garuda_indonesia.png" style="width:'+catWidth+'px; vertical-align: middle; height:50px;" />';
//			              else
//			                  return this.value;
			          }
			      }
			            }],
			
			
			          yAxis : {
			            type : 'logarithmic',
			            minorTickInterval: 0.1,
			            tickmarkPlacement : 'on',
			            title : {
			              text : 'Price'
			            }
			          },
			          tooltip : {
			            enabled : true,
			            useHTML: true,
			            formatter: function() {
			              return '<span>price : '+this.point.high+'</span> <br/> <span>cabin : '+this.point.name+'</span> </br> <span>class : '+this.point.bookingClass+'</span>';
			
			          }
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
			          series : dataGroup
			        };
			  
	              var chart1 = new Highcharts.Chart(chartOptions);
	              chart1.redraw();
      
		}

	}
})();
