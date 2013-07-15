<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<body onload="initKo()">
<div id="nav-calendar" class="default_calender calendar-picker">
  <div>
     <div id="mycalendar"></div>
  </div>
</div>
<div id="dashboardSearch">
<f:form id="DashBoardSearchForm" name="DashBoardSearchForm">
<br>
<div class="clearfix" style="margin-left:2%;width:98%;">	
<div class="float-left" style="margin-left:0%;width:25%;">
   <div class="float-left" style="width:30%;"><label>Project</label></div> 
       <div class="float-left" style="width:35%;"> 
	 	<input type="radio" name="searchType" value="A" data-bind="checked: dashboardSearch.searchType" >&nbsp;Active
	 	</div>
	 <div class="float-left" style="width:35%;">
	 	<input type="radio" name="searchType" value="C" data-bind="checked: dashboardSearch.searchType" >&nbsp;Closed
	 </div>
</div>
<div class="float-left" style="margin-left:2%;width:30%;">	 
	 <div class="float-left" style="width:37%;"><label>Project Name</label></div>
	  <div class="float-left" style="margin-left:3%;width:57%;">
		<select data-bind="value: dashboardSearch.projectId,optionsValue: 'id', optionsText: 'value', options: dashboardSearch.projectArray,optionsCaption:'Select', attr: {'title': $root.setTitle(dashboardSearch.projectArray(), dashboardSearch.projectId)}" style="width: 90%"></select>
	</div>
</div>	
<div class="float-left" style="margin-left:3%;width:40%;">
	 <div class="float-left" style="width:25%;"><label>Month/Year</label></div>
	  <div class="float-left" style="margin-left:0%;width:55%;">
		<select data-bind="value: dashboardSearch.month,optionsValue: 'id', optionsText: 'value', options: dashboardSearch.monthArray,optionsCaption:'Select'" style="width: 70%"></select>
	</div>
</div>	
</div>
<br>
<div id="style-one"></div>
<div data-bind="visible : effortContent">
<div class="clearfix" style="width:100%;">
<table cellspacing="10">
<tr>
<td><label>Planned Effort</label></td>
<td width="40%">: <span data-bind='text: dashboardProjectInfo.plannedEffort'></span></td>
<td><label>Actual Effort</label></td>
<td>: <span data-bind="text: dashboardProjectInfo.actualEffort"></span></td>
</tr>
<tr>
<td><label>Planned Start</label></td>
<td>: <span data-bind="text: dashboardProjectInfo.plannedStartDate"></span></td>
<td><label>Actual Start</label></td>
<td>: <span data-bind="text: dashboardProjectInfo.actualStartDate"></span></td>
</tr>
<tr>
<td><label>Planned Completed</label></td>
<td>: <span data-bind="text: dashboardProjectInfo.plannedEndDate"></span></td>
<td><label>Actual Completed</label></td>
<td>: <span data-bind="text: dashboardProjectInfo.actualEndDate"></span></td>
</tr>
</table>
</div>
<div id="style-one"></div>
</div>
</f:form>
<div id="dashboardChart" style="margin-top:15px;" data-bind="visible : effortContent"></div>
</div>
<script type="text/javascript">
ko.validation.configure({
    registerExtenders: true,
    messagesOnModified: true,
    insertMessages: true,
    parseInputAttributes: true	,
    messageTemplate: null
});
var customerViewModel;
var projects = ${projects};
ko.validation.rules['required'] = {
        validator: function (val, required) {
            var stringTrimRegEx = /^\s+|\s+$/g,
                testVal;

            if (val === undefined || val === null) {
                return !required;
            }

            testVal = val;
            if (typeof (val) == "string") {
                testVal = val.replace(stringTrimRegEx, '');
            }

            return required && (testVal + '').length > 0;
        },
        message: '{0}' +' is required.'
    };
    
AppViewModel.errors = ko.validation.group(AppViewModel);

function AppViewModel() {
	 var self = this;
	 var validatedashboard = {}; 
	 self.effortContent = ko.observable(false);
	 self.dashboardSearch = {
			projectArray : ko.observableArray(),
		    projectId : ko.observable().extend({ required: 'Project'}),
			searchType : ko.observable('A'),
			monthArray : ko.observableArray(),
			month : ko.observable().extend({ required: 'Month/Year'})
		};	
	 self.dashboardProjectInfo = {
			 plannedEffort : ko.observable(''),
			 plannedStartDate : ko.observable(''),
			 plannedEndDate : ko.observable(''),
			 actualEffort : ko.observable(''),
			 actualStartDate : ko.observable(''),
			 actualEndDate : ko.observable('')
	 };
	 ko.mapping.fromJS(projects, {},self.dashboardSearch.projectArray);
	 self.dashboardSearch.searchType.subscribe(function (value){
		 self.effortContent(false);
		 self.dashboardSearch.monthArray.removeAll();
		 self.dashboardSearch.projectArray.removeAll();
			YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
				Y.one("#dashboardChart").setHTML("");
		        if(value != undefined){
		        	Y.one("#loadingImage").setStyle("display","block");
						Y.io("/ems-web/ems/dashboard/getProjectList?projectType="+value, {
							method: 'GET',
							on: {
								success: function (id, o) {
											var response = Y.JSON.parse(o.responseText);
											if(response.projects != undefined){
												 ko.mapping.fromJS(response.projects, {},self.dashboardSearch.projectArray);
											}
								Y.one("#loadingImage").setStyle("display","none");
								}
							}
						});	
				}
			});
	 });
	 self.dashboardSearch.projectId.subscribe(function (projectId){
		 	self.effortContent(false);
		 	self.dashboardSearch.monthArray.removeAll();
			YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
				Y.one("#dashboardChart").setHTML("");
		        if(projectId != undefined){
		        	Y.one("#loadingImage").setStyle("display","block");
						Y.io("/ems-web/ems/dashboard/getMonth?projectId="+projectId, {
							method: 'GET',
							on: {
								success: function (id, o) {
											var response = Y.JSON.parse(o.responseText);
											if(response.months != undefined){
												 ko.mapping.fromJS(response.months, {},self.dashboardSearch.monthArray);
											}
								Y.one("#loadingImage").setStyle("display","none");
								}
							}
						});	
				}
			});
		});
		 self.dashboardSearch.month.subscribe(function (month){
				YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
					Y.one("#dashboardChart").setHTML("");
			        if(month != undefined && self.dashboardSearch.projectId() != undefined){
			        	Y.one("#loadingImage").setStyle("display","block");
							Y.io("/ems-web/ems/dashboard/getDashboardInfo?projectId="+self.dashboardSearch.projectId()+'&monthYear='+month, {
								method: 'GET',
								on: {
									success: function (id, o) {
												var response = Y.JSON.parse(o.responseText);
												var dashboardInfo = response.dashboardInfo;
												if(dashboardInfo != undefined){
													 self.effortContent(true);
													 ko.mapping.fromJS(dashboardInfo, {},self.dashboardProjectInfo);
													 var myDataValues = dashboardInfo.activityDetailList;
									                // Instantiate and render the chart
													 self.formChart(myDataValues);
												 	
												}
												Y.one("#loadingImage").setStyle("display","none");
									}
								}
							});	
					}
				});
			});
    /**
   	* Set title function.
   	*/
   	self.setTitle = function(array,data) {
   		var response = ko.toJS(data);
   		for(i in array)
   		{
   			if(array[i].id() == response)
   			{
   				return array[i].value;
   			}
   		}
   	};
	 self.formChart = function(dataValues){
		 YUI().use('node','charts', function(Y) {
		 var dashboardChart = new Y.Chart({
		 		axes: {
			                 category: {
			                     keys: ["activity"],
			                     type: "category",
			                     title:'Activity',
			                     styles: {
			                         label: {
												color:"#000000"
			                         }
			                     }
			                 },
			                 hours: {
			                     keys: ["hours"],
			                     type: "numeric",
			                     title:'Hours',
			                     styles: {
			                         label: {
												color:"#000000"
			                         }
			                     }
			                 }
            	 },
				horizontalGridlines: true,
 				verticalGridlines: true,
            	dataProvider: dataValues,
            	type:"column",
            	categoryKey :"activity"
         });
		dashboardChart.render('#dashboardChart');
		});
	 };
	 validatedashboard.errors = ko.validation.group(self.dashboardSearch,{deep: true});
					
}

function initKo() {
	customerViewModel = new AppViewModel();
	ko.applyBindings(customerViewModel);
}   
</script>
</body>
</html>