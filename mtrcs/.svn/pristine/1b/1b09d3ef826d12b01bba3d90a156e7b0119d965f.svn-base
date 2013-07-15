<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body onload="initKo()">
<div id="nav-calendar" class="default_calender calendar-picker">
  <div>
     <div id="mycalendar"></div>
  </div>
</div>
<br></br>
<div id="employeewisereports">
<f:form id="EmployeeWiseSearchForm" name="EmployeeWiseSearchForm">

<div class="clearfix" style="width:100%;">	
      <div class="float-left" style="margin-left:2%;width:25%;">
		 <div class="float-left" style="width:35%;margin-top:3px;"><label>Resource</label></div>
			<div class="float-left" style="margin-left:.5%;width:63%;">
			   <input style="width:95%;" type="text" onkeydown="emplyeeAutoComplete(this);" data-bind="value: employeeReportsView.employeeName,attr: {id:'employeeName'}">
            </div>
	 </div>
     <div class="float-left" style="margin-left:3%;width:25%;">
	    <div class="float-left" style="width:35%;margin-top:3px;"><label> From Date</label></div>
	      <div class="float-left" style="width:45%;">
	    	<input  type="text" readonly=true style="width:90%;"  data-bind="value: employeeReportsView.fromDate" />
		  </div>
		  <div class="float-left" style="width:15%;margin-left:1%; margin-top:3px;">
			  <a id="date-from" class="calender-ico" data-bind="click: function(){ showCalendar('date-from')}">&nbsp;&nbsp;&nbsp;&nbsp;</a>
    	  </div>
    </div>
    <div class="float-left" style="margin-left:2%;width:25%;">		
        <div class="float-left" style="width:28%;margin-top:3px;"><label>To Date</label></div>
       	  <div class="float-left" style="width:40%;">
       		   <input type="text" readonly=true style="width:100%;"  data-bind="value: employeeReportsView.toDate" /><br>
       		   <span id="errormessage" class="validationMessage" style="display: none;position:absolute;">To Date Should be greater than or equal to From Date</span>
	     </div>
	   <div class="float-left" style="width:15%;margin-left:1%; margin-top:3px;">
	        <a id="date-to" class="calender-ico" data-bind="click: function(){ showCalendar('date-to')}">&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</div>
     </div>     
</div>
<br><br>
<div class="clearfix" style="width:98%; margin-left: 2%;">	
    <div class="float-left" style="width:10%;">        
		<span><input type="radio" name="searchType" value="Daily" data-bind="checked: employeeReportsView.searchType" >&nbsp;&nbsp;&nbsp;&nbsp;Daily</span>
	</div>
	<div class="float-left" style="margin-left:0%;width:15%;">        
		<span><input type="radio" name="searchType" value="Summary" data-bind="checked: employeeReportsView.searchType" >&nbsp;&nbsp;&nbsp;&nbsp;Summary</span>
    </div>
    <div data-bind="click: searchReports" class="normal-button" style="width:65px; float:left;margin-top:-4px;">Search</div>
    <div data-bind="visible: employeeReports().length != 0,click: exportExcel" class="normal-button" style="margin-left: 2%; width:65px; float:left;margin-top:-4px;">Export</div>
</div>
</f:form>
</div>
<div class="normal-grid" id="dailyEmployeeReports" style="margin-left:2%;width: 80%;display: none;">
	<table cellpadding="0" cellspacing="0">
		<thead>
		            <tr>
		                <th width="10%">Date</th>
		                <th width="10%">Project Name</th>
		                <th width="15%">Activity Name</th>
		                <th width="15%">Task Name</th>
		                <th width="20%">Work Items</th>
		                <th width="10%">Effort<br/>(hh:mm)</th>
		            </tr>
		</thead>
		<tbody style="background-color: white !important;" data-bind="visible: employeeReports().length == 0">
		         	<tr>
		            	<td colspan="6" style="text-align:center;">
		            		<span>	No Data Found	</span>
		            	</td>
		            </tr>
		</tbody>
	<tbody data-bind="foreach: employeeReports">
		<tr>
		        <td>
		            <span  data-bind="text: date" ></span>
		        </td>
		        <td>
		            <span  data-bind="text: projectName" ></span>
		        </td>
		        <td>
		            <span  data-bind="text: activityName"> </span>
		        </td> 
		        <td>
		            <span  data-bind="text: taskName"> </span>
		        </td>
		        <td>
		            <span data-bind="text: workItems"></span>
		        </td>
		        <td>
		            <span  data-bind="text: hours"> </span> : <span  data-bind="text: minutes"> </span>
		        </td>         
		</tr>
	</tbody>
</table>
</div>
<div class="normal-grid" id="summaryEmployeeReports" style="margin-left:2%;width: 80%;display: none;">
  <table cellpadding="0" cellspacing="0">
	<thead>
	            <tr>
	                <th width="10%">Project Name</th>
	                <th width="20%">Activity Name</th>
	                <th width="20%">Task Name</th>
	                <th width="10%">Effort<br/>(hh:mm)</th>
	            </tr>
	</thead>
	<tbody style="background-color: white !important;" data-bind="visible: employeeReports().length == 0">
	         	<tr>
	            	<td colspan="4" style="text-align:center;">
	            		<span>	No Data Found	</span>
	            	</td>
	            </tr>
	</tbody>
	<tbody data-bind="foreach: employeeReports">
		<tr>
		        <td>
		            <span  data-bind="text: projectName" ></span>
		        </td>
		        <td>
		            <span  data-bind="text: activityName"> </span>
		        </td> 
		        <td>
		            <span  data-bind="text: taskName"> </span>
		        </td>
		        <td>
		            <span  data-bind="text: hours"> </span> : <span  data-bind="text: minutes"> </span>
		        </td>         
		</tr>
	</tbody>
</table>
</div>
</body>
<script type="text/javascript">
var calendarExistsInd = false;
ko.validation.configure({
    registerExtenders: true,
    messagesOnModified: true,
    insertMessages: true,
    parseInputAttributes: true	,
    messageTemplate: null
});

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

var employeeList = ${employeeRoles};
var searchEmpType='';
var self = new AppViewModel();
AppViewModel.errors = ko.validation.group(AppViewModel);

var employeeinfo ={
	ProjectName:'',
	ActivityName:'',
	TaskName :'',
	Hours:'',
    WorkItems:''
};

function AppViewModel() {
	 var self = this;
	 var validateEmployeeSearch = {}; 
		
		self.employeeReports = ko.observableArray([]);

		self.employeeReportsView = {
			employeeName :ko.observable().extend().extend({ required: 'Employee'}),
		    employeeId : ko.observable(),
			fromDate : ko.observable().extend({ required: 'From Date'}),
			toDate : ko.observable().extend({ required: 'To Date'}),
			searchType : ko.observable('Daily')
		};	
		
		self.employeeReportsView.employeeName.subscribe(function(employeeName) {
			YUI().use('node', "io", function(Y) {
			    Y.one('#dailyEmployeeReports').setStyle('display', 'none');
				Y.one('#summaryEmployeeReports').setStyle('display', 'none');				
				self.employeeReports.removeAll();
			});
		});
		validateEmployeeSearch.errors = ko.validation.group(self.employeeReportsView,{deep: true});
		
		self.showCalendar = function(data){
			popupCalendar(data);
		};
		function check(){
		var start=self.employeeReportsView.fromDate();
		var end=self.employeeReportsView.toDate();

		var fromdate = new Date('' + start.split('-')[2].substring(2), start.split('-')[1], start.split('-')[0]);
			fromdate.setFullYear(start.split('-')[2]);
			fromdate.setMonth(start.split('-')[1] - 1);
			fromdate.setDate(start.split('-')[0]);
		
		var todate= new Date('' + end.split('-')[2].substring(2),end.split('-')[1], end.split('-')[0]);
			todate.setFullYear(end.split('-')[2]);
			todate.setMonth(end.split('-')[1] - 1);
			todate.setDate(end.split('-')[0]);
			
			if ( fromdate > todate) {
				return false;
			}else{
				return true;
			}
		}
		self.exportExcel = function() {
			document.location.href = '/ems-web/ems/project/getExcelEmployeeReports?searchType=employee'+self.employeeReportsView.searchType();
		}
		self.searchReports = function(){
			if(validateEmployeeSearch.errors().length == 0)
			{
			  YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y) {
			    Y.one("#loadingImage").setStyle("display","block");
				var employeeInfo = 'employeeId='+self.employeeReportsView.employeeId()+'&fromDate='+self.employeeReportsView.fromDate()+'&toDate='+self.employeeReportsView.toDate();
				employeeInfo = employeeInfo +'&searchType='+self.employeeReportsView.searchType();
					var cfg = {
							method: 'GET',
							data: employeeInfo,
							on: {
								success: function (id, o) {
									var response = Y.JSON.parse(o.responseText);
									self.employeeReports.removeAll();
									searchEmpType=response.searchType;								
									if(response.employeeReport != undefined){
										var employeeReports = response.employeeReport;
										for(var i=0 ; i < employeeReports.length ; i++){
											self.employeeReports.push(new addemployeereports(employeeReports[i]));
										}
										if(searchEmpType=='Daily'){
											Y.one("#summaryEmployeeReports").setStyle("display","none");
											Y.one("#dailyEmployeeReports").setStyle("display","block");
											Y.one("#loadingImage").setStyle("display","none");
										}else{
											Y.one("#dailyEmployeeReports").setStyle("display","none");
											Y.one("#summaryEmployeeReports").setStyle("display","block");
											Y.one("#loadingImage").setStyle("display","none");
										}
						   		  }
							}
						}
	  				};
	  				if(check()){
		  				Y.one("#errormessage").setStyle("display","none");
						Y.io("/ems-web/ems/project/getEmployeeReports", cfg);
	  				}else{
		  				Y.one("#errormessage").setStyle("display","block");
		  				Y.one("#loadingImage").setStyle("display","none");
	  				}
				});
			}
			else
			{
				validateEmployeeSearch.errors.showAllMessages(true); 
			}
		}
	function addemployeereports(employeeReports) {
		var self = this;
		self.date=ko.observable(employeeReports.date);
		self.projectName=ko.observable(employeeReports.projectName);
		self.activityName=ko.observable(employeeReports.activityName);
		self.taskName=ko.observable(employeeReports.taskName);
		self.hours=ko.observable(employeeReports.hours);
		self.minutes=ko.observable(employeeReports.minutes);
		self.workItems=ko.observable(employeeReports.workItems);
	};
}

function initKo() {
	ko.applyBindings(self);
}

/*
 Script for calendar
*/

var calenderObj = null;
var outerCalWidgetContainerId = '#nav-calendar';
var calWidgetContainerId = '#cal-widget-container';
var outerCalWidgetContainerDiv = null;
var currDateBxId = null;
var futureDate = new Date();
futureDate.setHours(13);

function popupCalendar(data) {
YUI().use('node','node-core','io-base','json-parse','json-stringify','event', function(Y){
	Y.one('#nav-calendar').setStyle('display','block');
	outerCalWidgetContainerDiv = Y.one(outerCalWidgetContainerId);
	if(calenderObj == null){
		calenderObj =  returnCalendar();
		Y.delegate('click', function(e) {
			 var clickNode = e.target;
			 var clickId = clickNode.get('id');
			 var isCalIcon, isCal = false;
			 if(clickId.indexOf('date-') != -1){
				 currDateBxId = clickId.split('-')[1];
				 isCal = true;
				}
			 var clazz = clickNode.getAttribute('class');
	         if(clickNode == outerCalWidgetContainerDiv 
	        		 || outerCalWidgetContainerDiv.contains(clickNode)) {
	        	 isCal = true;
	         }
			if(clazz.indexOf('yui3-calendar-day-selected') >= 0) { 
	             isCal = false;
	         }
	         if(clickNode.get('id') == 'calendar-close'){
	        	 isCal = false;
	         }
	         if(!isCalIcon && !isCal) {
				outerCalWidgetContainerDiv.setStyle('display', 'none');
			}
		}, document, 'body');
	}else{
		calenderObj.deselectDates();
		calenderObj.set('date', futureDate);
	}
	outerCalWidgetContainerDiv.setStyle('display', 'block');
	var node = '#'+data+'';
	var xy = Y.one(node).getXY();
	xy[0] = xy[0] - 150;
	xy[1] = xy[1] + 25;
	outerCalWidgetContainerDiv.setXY(xy);
});
}
	function selectionChangeCallback(date) {
		if (currDateBxId == 'from') {
				self.employeeReportsView.fromDate(date);
		}
		if (currDateBxId == 'to') {
				self.employeeReportsView.toDate(date);
		}
	}
	function returnCalendar() {
		if(calendarExistsInd){
			return;
			}
		calendarExistsInd = true;
		YUI().use('calendar', 'datatype-date', 'node', function(Y) {
			Y.CalendarBase.CONTENT_TEMPLATE = Y.CalendarBase.ONE_PANE_TEMPLATE;
			calenderObj = new Y.Calendar( {
				contentBox : "#mycalendar",
				width : '350px',
				showPrevMonth : true,
				showNextMonth : true,
				maximumDate : futureDate,
				date : new Date()
			}).set('customRenderer', {
				filterFunction : function(cellDate, cellNode) {
					if (cellDate > futureDate) {
						cellNode.addClass('calendarDiasabled');
					}
				},
				rules : {
					all : 'all'
				}
			}).render(), myCalendarCanBeSelected = calenderObj._canBeSelected;
			calenderObj._canBeSelected = function(oDate) {
				if (oDate > futureDate) {
					return false;
				}
				return myCalendarCanBeSelected.call(calenderObj, oDate);
			};
				var dtdate = Y.DataType.Date;
				calenderObj.on("selectionChange", function(ev) {
					var newDates = ev.newSelection;
					if (newDates.length > 0) {
						var selDate = newDates[0];
						var dStr = selDate.getDate();
						var mStr = (selDate.getMonth() + 1);
						var yStr = selDate.getFullYear();
						if (dStr < 10)
							dStr = '0' + dStr;
						if (mStr < 10)
							mStr = '0' + mStr;
						var dateStr = dStr + "-" + mStr + "-" + yStr;
						selectionChangeCallback(dateStr);
					}
				});
				calenderObj.on("dateClick", function(ev) {
					Y.one('#nav-calendar').setStyle('display', 'none');
				});
				return calenderObj;
			});
	}
	
/*
  This function is used to generate Autocomplete 
*/	
		
emplyeeAutoComplete = function(object){
	YUI().use('node','node-core','io-base','json-parse','json-stringify','event', function(Y){
	var textCallback =  function (result) {
		if(Y.Lang.isValue(result)){
			var cellHTML =  '';
			if(object.id.indexOf('employeeName') >= 0)
				if(result.employeeId != undefined){
					cellHTML =  result.firstName+'  '+result.lastName;
				}
			return cellHTML;
		}
	};
	var selectCallback =  function (ev) {
		if(Y.Lang.isValue(ev.result)){
			var resultObj = ev.result.raw;
				if(resultObj.employeeId != undefined){
					self.employeeReportsView.employeeId(resultObj.employeeId);
				}
		}
		};
    var acUrl = "/ems-web/ems/project/employeeLookup?&columnType=employee_name"+"&projectId=''"+"&employeeId=";
    Y.use('autocomplete', 'autocomplete-highlighters', function(Y) {
	   var input = AutoComplete({
    	   inputId: object.id,
    	   resultsLocator:'employees',
    	   queryUrl: acUrl,
    	   maxResults: 10,
    	   textLocatorCallback:textCallback,
    	   onSelectCallback: selectCallback
    	 });
	});
	});
};	
</script>
</html>