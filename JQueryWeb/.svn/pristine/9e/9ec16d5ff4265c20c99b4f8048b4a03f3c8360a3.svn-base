	<div>
		<div id="chartDiv" style="width:600px; height:400px;" ></div>
	</div>
	
	<script type="text/javascript">

	    var jsonObj = { "pageHits": [30, 60, 22, 5, 60, 88, 102], "rssHits": [33, 45, 121, 23, 55, 35, 77], "xAxis": ['Jan 2009', 'Feb 2009', 'Mar 2009', 'Apr 2009', 'May 2009', 'June 2009', 'Jul 2009'] };

	    var jsonPieObj = { "pageHits": [['Jan 2009',30], ['Feb 2009',60], ['Mar 2009',22], ['Apr 2009',5], ['May 2009',60], ['June 2009',88], ['Jul 2009',102]], 
                               "rssHits": [['Jan 2009',33], ['Feb 2009',45], ['Mar 2009',121], ['Apr 2009',23], ['May 2009',55], ['June 2009',35], ['Jul 2009',77]]	};

		
		jq(function() {
			
			//jq.jqplot.config.enablePlugins = true;

			jq.jqplot('chartDiv', [jsonObj.pageHits, jsonObj.rssHits], CreateLineChartOptions());
			
			jq('#barChartButton').click(function() {
				jq('#tabs').tabs('select', '#chartsTab');
				jq('#chartDiv').html('');
				jq.jqplot('chartDiv', [jsonObj.pageHits, jsonObj.rssHits], CreateBarChartOptions());
			});
			
			jq('#lineChartButton').click(function() {
				jq('#tabs').tabs('select', '#chartsTab');
				jq('#chartDiv').html('');
				jq.jqplot('chartDiv', [jsonObj.pageHits, jsonObj.rssHits], CreateLineChartOptions());
			});
			
			jq('#stackedBarChartButton').click(function() {
				jq('#tabs').tabs('select', '#chartsTab');
				jq('#chartDiv').html('');
				jq.jqplot('chartDiv', [jsonObj.pageHits, jsonObj.rssHits], CreateStackedBarChartOptions());
			});
			
            jq('#pieChartButton1').click(function() {
            	jq('#tabs').tabs('select', '#chartsTab');
				jq('#chartDiv').html('');
				jq.jqplot('chartDiv', [jsonPieObj.pageHits], CreatePieChartOptions1());
			});
            
            jq('#pieChartButton2').click(function() {
            	jq('#tabs').tabs('select', '#chartsTab');
				jq('#chartDiv').html('');
				jq.jqplot('chartDiv', [jsonPieObj.rssHits], CreatePieChartOptions2());
			});
		});
		
		function CreateLineChartOptions()
		{
			var optionsObj = {
				title: 'Line Chart',
				axes: {
					 xaxis: {
			            renderer: jq.jqplot.CategoryAxisRenderer,
			            ticks: jsonObj.xAxis
			        }
				},
				series: [{label:'Page Hits'}, {label: 'RSS Hits'}],
				seriesDefaults:{
					markerOptions:{
						show: true,
						style: 'diamond'
					}
				},
				legend: {
					show: true,
					location: 'nw'
				},
				highlighter: {
					show: true,
					showTooltip: true,
					tooltipFade: true
				},
				cursor:{
					show:true,
					zoom:true
				}
			};
			return optionsObj;
		}
		
		function CreateBarChartOptions()
		{
			var optionsObj = {
				title: 'Bar Chart',
				axes: {
					 xaxis: {
			            renderer: jq.jqplot.CategoryAxisRenderer,
			            ticks: jsonObj.xAxis
			        }
				},
				series: [{label:'Page Hits'}, {label: 'RSS Hits'}],
				legend: {
					show: true,
					location: 'nw'
				},
				seriesDefaults:{
					shadow: true,
					renderer:jq.jqplot.BarRenderer,
					rendererOptions:{
		 	           barPadding: 8,
			           barMargin: 10
			       }
				},
				highlighter: {
					show: true,
					showTooltip: true,
					tooltipFade: true
				}
			};
			return optionsObj;
		}

       function CreatePieChartOptions1()
		{
			var optionsObj = {
				title: 'Pie Chart',
				legend: {
					show: true,
					location: 'nw'
				},
				seriesDefaults:{
					shadow: true,
					renderer:jq.jqplot.PieRenderer,
					rendererOptions:{
						sliceMargin:10,
						shadowOffset:1,
						shadowAlpha:0.5,
						shadowDepth:5
					}
				},
				highlighter: {
					showTooltip: true,
					tooltipFade: true
				}
			};
			return optionsObj;
		}
		
     function CreatePieChartOptions2()
		{
			var optionsObj = {
				title: 'Pie Chart',
				legend: {
					show: true,
					location: 'nw'
				},
				seriesDefaults:{
					shadow: true,
					renderer:jq.jqplot.PieRenderer,
					rendererOptions:{
						diameter: 300,
						fill:false,
						sliceMargin:10,
						shadowOffset:1,
						shadowAlpha:1,
						shadowDepth:2
					}
				},
				highlighter: {
					showTooltip: true,
					tooltipFade: true
				}
			};
			return optionsObj;
		}

		function CreateStackedBarChartOptions()
		{
			var optionsObj = {
				stackSeries: true,
				title: 'Stacked Bar Chart',
				axes: {
					 xaxis: {
			            renderer: jq.jqplot.CategoryAxisRenderer,
			            ticks: jsonObj.xAxis
			        }
				},
				series: [{label:'Page Hits'}, {label: 'RSS Hits'}],
				legend: {
					show: true,
					location: 'nw'
				},
				seriesDefaults:{
					shadow: true,
					renderer:jq.jqplot.BarRenderer
				},
				highlighter: {
					show: true,
					showTooltip: true,
					tooltipFade: true
				}
			};
			return optionsObj;
		}	
	</script>