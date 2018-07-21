(function() {
	'use strict';

	angular.module('fmpApp').controller('CompetitorMonitoringController',
			CompetitorMonitoringController);

	CompetitorMonitoringController.$inject = [ '$state',
			'CompetitorMonitoring', 'RbdColorMapping', 'RbdMapping', 'ParseLinks', 'AlertService',
			'paginationConstants', 'queryParams', 'tariffNumbers', 'cities',
			'$uibModal' ];

	function CompetitorMonitoringController($state, CompetitorMonitoring, RbdColorMapping, RbdMapping,
			ParseLinks, AlertService, paginationConstants, queryParams,
			tariffNumbers, cities, $uibModal) {

		var vm = this;
		vm.loadPage = loadPage;
		vm.itemsPerPage = paginationConstants.itemsPerPage;
		vm.queryParams = queryParams;
		vm.loadAll = loadAll;
		vm.generateGraph = generateGraph;
		vm.graphDisabled = true;
		vm.isDisabled = isDisabled;

		// vm.getRules = getRules;
		vm.getChartData = getChartData;
		vm.showCategoryDetail = showCategoryDetail;
		vm.showLegend = showLegend;
		vm.viewFullText = viewFullText;

		vm.reset = reset;
		vm.page = 1;
		
		vm.barThickness = 8;
		vm.barOpacity = 1;

		vm.datePickerOpenStatus = {};
		vm.dateFormat = "yyyy-MM-dd";
		vm.openCalendar = openCalendar;
		
		vm.checkValidParameters = checkValidParameters;
		
		vm.compMonitoring = [];
		vm.compTitle = '';
		vm.origDests = [];
		vm.cabins = [];
		vm.carriers = [];
		vm.rbdMapping = [];
		vm.generateChart = generateChart;
		
		
		vm.sources = [
        	{key: "A", value: "A - ATPCO"},
        	{key: "M", value: "M - Market"},
        	{key: "W", value: "W - Web"},
        	{key: "C", value: "C - Competitor Private"}
        ]

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
			value : "2 - Round Trip"
		}, {
			key : "3",
			value : "3 - One Way Only"
		}, ]

		vm.dateOptions = [ {
			key : "",
			value : ""
		},{
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
		
		vm.infoMessage = null;
		vm.showErrorModal = showErrorModal;

		// vm.loadAll();
		vm.loadRbdMapping = loadRbdMapping;
		
		loadRbdColor();
		
		function loadRbdColor() {
			
			RbdColorMapping.query({
                page: 0,
                size: 100
            }, onSuccess, onError);
			
			function onSuccess(data, headers) {
				console.log('rbdColor : ',data);
				vm.rbdColors = data;
			}
			
			function onError(error) {
				AlertService.error(error.data.message);
			}
			
		}
		
		function loadRbdMapping() {
			var param = {
					carriers: vm.carriers
			}
			
			RbdMapping.getByCarriers(param, onSuccess, onError);
			
			function onSuccess(data, headers) {
				console.log('rbdMapping : ',data);
				vm.rbdMapping = data;
			}
			
			function onError(error) {
				AlertService.error(error.data.message);
			}
			
		}
		
		function loadAll() {
			
			
			vm.infoMessage = vm.checkValidParameters();
    		
    		if (vm.infoMessage != 'Valid') {
        		vm.showErrorModal(vm.infoMessage);
        		return;
        	}
    		
			vm.categoryRules = null;
			vm.currentCompetitorMonitoring = null;
			vm.compMonitoring = [];

			vm.page = 1;
			vm.queryParams.page = vm.page-1;
        	vm.queryParams.size = vm.itemsPerPage;
			
			CompetitorMonitoring.query(vm.queryParams, onSuccess, onError);
			

			function onSuccess(data, headers) {
				vm.links = ParseLinks.parse(headers('link'));
				vm.totalItems = headers('X-Total-Count');
				vm.queryCount = vm.totalItems;
				vm.compMonitoring = data;
				vm.graphEnabled = isDisabled();
				vm.origDests = vm.queryParams.origin.split(',');
				vm.compTitle = vm.origDests[0];
				var cabins = [];
				var carriers = [];
				for(var i=0;i<data.length;i++) {
					  cabins.push(data[i].cabin);
					  carriers.push(data[i].carrierCode);
				 }
				
				vm.cabins = Array.from(new Set(cabins));
				vm.cabins.unshift('All');
				vm.cabin = vm.cabins[0];
				vm.carriers = Array.from(new Set(carriers));
				
				vm.loadRbdMapping();
				
				console.log('length : ',data.length);
				
				$(document).ready(function(){
					var _parents = $('.table-afd').find('thead');
	        		var _th = _parents.find('.th-fixed');
	        		var _tr = _parents.siblings('tbody').find('tr:first-child');
	        		var _td = _tr.find('td');
	        		var _length = _th.length;
	        		_th.last().css('border-right','none');
	        		for(var i=0;i<_length;i++){
	        			var _width = _th.eq(i).outerWidth();
	        			var _width2 = _td.eq(i).outerWidth();
	        			if(_width > _width2){
	        				_td.eq(i).css('min-width', _width);
	        				_td.eq(i).css('width', _width);
	        			}
	        			else{
	        				_th.eq(i).css('min-width', _width2);
	        				_th.eq(i).css('width', _width2);
	        			}
	        		}
					
				});
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
					
				}, function(error) {
					console.log(error);
				});
			}
		}
		
		function getChartData() {
				vm.queryParams.page = 0;
				vm.queryParams.size = vm.totalItems;
				CompetitorMonitoring.getChartData(vm.queryParams, function(data) {
					vm.generateGraph(data);
				}, function(error) {
					console.log(error);
				});
			
		}

		function openCalendar(e, date) {
			e.preventDefault();
			e.stopPropagation();
			
			vm.datePickerOpenStatus = {};
			vm.datePickerOpenStatus[date] = true;
		}

		function reset() {
			$('#comporDiv').hide();
			vm.queryParams = {
	        		carrier: null,
	        		source: vm.sources[0].key,
	        		publicPrivate: null,
	        		tariff: null,
	        		globalIndicator: null,
	        		gaFareType: null,
	        		fareType: null,
	        		fareBasis: null,
	        		origin: null,
	        		destination: null,
	        		owrt: null,
	        		footnote: null,
	        		ruleNo: null,
	        		ocRuleNo: null,
	        		routingNo: null,
	        		woId: null,
	        		effectiveDateFrom: null,
	        		effectiveDateTo: null,
	        		effectiveDateOption: vm.dateOptions[0].key,
	        		saleDateFrom: null,
	        		saleDateTo: null,
	        		saleDateOption: vm.dateOptions[0].key,
	        		travelDateFrom: null,
	        		travelDateTo: null,
	        		travelDateOption: vm.dateOptions[0].key,
	        		seasonDateFrom: null,
	        		seasonDateTo: null,
	        		seasonDateOption: vm.dateOptions[0].key,
	        		amountRange: null,
	        		tourCode: null,
	        		paxType: null,
	        		cabin: null,
	        		bookingClass: null,
	        		advancePurchase: null,
	        		minStay: null,
	        		maxStay: null,
	        		includeConstructed: false,
	        		appendResults: false,
	        		biDirectional: false,
	        		calculateTfc: false
	        	}

			vm.compMonitoring = [];
			vm.barThickness = 8;
			vm.barOpacity = 1;
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
		
		function isDisabled() {
			if(vm.compMonitoring.length > 0) {
				return false;
			} else {
				return true;
			}
		}
		
		function generateChart(data) {
			$('#comporDiv').show();
			
			var fares = data;
			
			var barThickness = vm.barThickness/100;
		    var barOpacity = vm.barOpacity/10;
		    
		    var dataGroup = [];
		    var ownCarrier = "GA";
		    
		    for(var a=0; a<vm.carriers.length; a++) {
		    	var dataGraph = [];
		    	
		    	for(var b=0; b<fares.length; b++) {
		    		
		    		if(vm.carriers[a] == fares[b].carrierCode) {
		    			var cities = fares[b].originCity+'-'+fares[b].destinationCity;
		    			
		    			if(vm.queryParams.appendResults) {
		    				
		    			} else {
		    				
		    				if(vm.compTitle == cities) {
		    					
			    				var amt = fares[b].baseAmount;
			    				
			    				if (vm.carriers[a] == ownCarrier) {
			    					
			    					for(var c=0; c<vm.rbdColors.length; c++) {
				    					if (data[b].bookingClass == vm.rbdColors[c].rbd) {
				    						cabColor = 'rgba(' + vm.rbdColors[c].colorVal +','+barOpacity+ ')';
//				    						console.log(cabColor);
				    						break;
				    					}
				    				}
			    					
			    					var lowVal = parseFloat(String(amt))-(parseFloat(String(amt))*parseFloat(String(barThickness)));
						        	dataGraph.push({'x':a,'name':data[b].cabin,'bookingClass':data[b].bookingClass, 'color':cabColor, 'oalRbd':data[b].bookingClass,'high':amt,'low':lowVal});
			    				} else {
			    					var ownRbd = null;
				    				var oalRbd = null;
				    				var cabColor = null;
				    				var found = false;
				    				
				    				for (var d = 0; d < vm.rbdMapping.length; d++) {
				    					if (vm.carriers[a] == vm.rbdMapping[d].oalCxr) {
				    						if (vm.rbdMapping[d].oalRbd == data[b].bookingClass[0]) {
				    							oalRbd = vm.rbdMapping[d].oalRbd;
				    							ownRbd = vm.rbdMapping[d].ownRbd;
				    							found = true;
				    							break;
				    						}
				    					}
				    				}
				    				
				    				if (found) {
				    					for(var c=0; c<vm.rbdColors.length; c++) {
					    					if (ownRbd == vm.rbdColors[c].rbd) {
					    						cabColor = 'rgba(' + vm.rbdColors[c].colorVal +','+barOpacity+ ')';
					    						break;
					    					}
					    				}
				    				} else {
				    					oalRbd = data[b].bookingClass[0];
				    					ownRbd = oalRbd;
				    					cabColor = 'rgba(0,0,0,1)';
				    				}
				    				
				    				var lowVal = parseFloat(String(amt))-(parseFloat(String(amt))*parseFloat(String(barThickness)));
						        	dataGraph.push({'x':a,'name':data[b].cabin,'bookingClass':ownRbd, 'color':cabColor, 'oalRbd':oalRbd,'high':amt,'low':lowVal});
			    				}
			    			}
		    			}
		    		}
		    	}
		    	dataGroup.push({'name':vm.carriers[a], 'data':dataGraph});
		    }
		    console.log(dataGroup);
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
			               categories : vm.carriers,
			               gridLineWidth : 2,
			            },{
			              linkedTo: 0,
			              type : 'category',
			              categories : vm.carriers,
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
			              return '<span>price : '+this.point.high+'</span> <br/> <span>cabin : '+this.point.name+'</span> </br> <span>RBD : '+this.point.oalRbd+'</span>';
			
			          }
			          },
			          plotOptions : {
			            columnrange : {
			              stickyTracking : false,
			              grouping : false
			            },
			            series:{
			                turboThreshold:2000
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

//		    console.log(dataGroup);
		}

		function generateGraph(data) {


			$('#comporDiv').show();
			
			 var fares = data;
		  
		      var barThickness = vm.barThickness/100;
		      var barOpacity = vm.barOpacity/10;
		      
		      var totalData = fares.length;
		      var items = [];
		      var dataGroup = [];
		      var xGroup = [];
		      var dataGraph = [];
		      var cabins = [];
		      var low = [];
		      
			  for(var i=0;i<fares.length;i++) {
				  items.push(fares[i].carrierCode);
				  cabins.push(fares[i].cabin);
			  }
		      
			  var categories = Array.from(new Set(items));
			  var groupedCabin = Array.from(new Set(cabins));
		    
		      var catLogo = [];
		      var catWidth = $('#container').width/categories.length;
		      var cabColor = '';
		      var min = 0;
        	  var minTemp = 0;
		    
		      
		    for(var j=0;j<categories.length;j++) {
		      for(var k=0;k<fares.length;k++) {
		    	  
		        if(fares[k].carrierCode==categories[j]) {
		          var amt = String(fares[k].baseAmount);
		          
		          if(fares[k].cabin != undefined || fares[k].cabin != null) {
		        	  
		        	var red = 'rgba(255,0,0,'+barOpacity+')';
		        	var green = 'rgba(0,255,0,'+barOpacity+')';
		        	var blue = 'rgba(0,0,255,'+barOpacity+')';
		        	var yellow = 'rgba(255,255,0,'+barOpacity+')';
		        	  
		        	  if(fares[k].cabin == 'F') {
			        	  cabColor = blue;
			          } else if(fares[k].cabin == 'J') {
			        	  cabColor = yellow;
			          } else if(fares[k].cabin == 'W') {
			        	  cabColor = green;
			          } else if(fares[k].cabin == 'Y') {
			        	  cabColor = red;
			          }
		        	
		        	  var lowVal = parseFloat(String(amt))-(parseFloat(String(amt))*parseFloat(String(barThickness)));
		        	  dataGraph.push({'x':j,'name':fares[k].cabin,'bookingClass':fares[k].bookingClass,'color':cabColor,'high':amt,'low':lowVal});
		        	  
		          }
		        }
		      }
		    }
		  dataGroup.push({'name':categories[j], 'data':dataGraph});
		    
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
//		              if(this.value == "GA")
//		                  return '<img src="http://3.bp.blogspot.com/_UZImdYAiry8/SV9oVvC_WqI/AAAAAAAAOZI/vWKb9Pjuhl4/s400/Logo_garuda_indonesia.png" style="width:'+catWidth+'px; vertical-align: middle; height:50px;" />';
//		              else
//		                  return this.value;
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
		              return '<span>price : '+this.point.high+'</span> <br/> <span>cabin : '+this.point.name+'</span> </br> <span>RBD : '+this.point.bookingClass+'</span>';
		
		          }
		          },
		          plotOptions : {
		            columnrange : {
		              stickyTracking : false,
		              grouping : false
		            },
		            series:{
		                turboThreshold:2000
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
		
		function checkValidParameters() {
			var msg = "Error: One set of the following is required.\n\tCarrier and Origin-Destination\n\tCarrier and Fare Basis\n";
			
        	if ((vm.queryParams.carrier != null && vm.queryParams.carrier != '' && vm.queryParams.origin != null && vm.queryParams.origin != '') ||
        		(vm.queryParams.origin != null && vm.queryParams.fareBasis != null && vm.queryParams.origin != '' && vm.queryParams.fareBasis != '') ||
        		(vm.queryParams.carrier != null && vm.queryParams.fareBasis != null && vm.queryParams.carrier != '' && vm.queryParams.fareBasis != '')) {
        		var x = vm.queryParams.carrier.indexOf(",");
    			var y = vm.queryParams.origin.indexOf("-");
    			
    			if((x > -1) && (y > -1)) {
    				var cxr2 = vm.queryParams.carrier.charAt(x+1);
    				var orig2 = vm.queryParams.origin.substr(y+1);
    				
    				if((cxr2 != null && cxr2 != "") && (orig2 != null && orig2 != "")) {
    					msg =  'Valid';
    				}
    				else {
    					msg = "Error: One set of the following is required.\n\t2 Carriers are required\n\tDestination is required\n";
    				}
    				
    			} else {
    				msg = "Error: One set of the following is required.\n\t2 Carriers are required\n\tDestination is required\n";
    			}
        		
        	}
        	
        	return msg;
        }
		
		function showErrorModal(message) {
        	$uibModal.open({
                templateUrl: 'app/pages/modals/info-modal.html',
                controller: 'InfoModalController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'md',
                resolve: {
                    entity: {
                    	message: message
                    }	
                }
            }).result.then(function() {
                $state.go('competitor-monitoring', {}, { reload: false });
            }, function() {
                $state.go('competitor-monitoring');
            });
        }

	}
})();
