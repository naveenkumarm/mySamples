<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body onload="initKo()">
<div id="nav-calendar" class="default_calender calendar-picker"><!-- You need this skin class -->
<div>
<div id="mycalendar"></div>
</div>
</div>
<br></br>
<div id="groupWiseReports"><f:form id="GroupWiseSearchForm"
	name="GroupWiseSearchForm">
	<div class="clearfix" style="width: 100%;">
	<div class="float-left" style="margin-left: 2%; width: 25%;">
	<div class="float-left" style="width: 30%; margin-top: 3px;"><label>Group</label></div>
	<div class="float-left" style="margin-left: .5%; width: 68%;">
	<select	style="width: 100%"	data-bind="value: groupReportsView.groupId,optionsValue: 'id', optionsText: 'name',options: groupReportsView.groupArray,optionsCaption:'Select',
					attr: {'title': $root.setTitle(groupReportsView.groupArray(), groupReportsView.groupId)}">
	</select></div>
	</div>
	<div class="float-left" style="margin-left: 4%; width: 25%;">
	<div class="float-left" style="width: 35%; margin-top: 3px;"><label>From
	Date</label></div>
	<div class="float-left" style="margin-left: .5%; width: 35%;"><input
		style="width: 100%" id="from-date" type="text" readonly=true
		data-bind="value: groupReportsView.fromDate" /></div>
	<div class="float-left"
		style="margin-left: 2%; width: 14%; margin-top: 3px;"><a
		id="date-from" class="calender-ico"
		data-bind="click: function(){ showCalendar('date-from')}">&nbsp;&nbsp;&nbsp;&nbsp;</a>
	</div>
	</div>
	<div class="float-left" style="width: 25%;">
	<div class="float-left" style="width: 28%; margin-top: 3px;"><label>To
	Date</label></div>
	<div class="float-left" style="margin-left: .5%; width: 35%;"><input
		style="width: 100%" id="to-date" type="text" readonly=true
		data-bind="value: groupReportsView.toDate" /> <span id="dateValid"
		class="validationMessage" style="display: none; position: absolute;">To
	date should greater than or equal to From date</span></div>
	<div class="float-left"
		style="margin-left: 2%; width: 14%; margin-top: 3px;"><a
		id="date-to" class="calender-ico"
		data-bind="click: function(){ showCalendar('date-to')}">&nbsp;&nbsp;&nbsp;&nbsp;</a>

	</div>
	</div>
	</div>
	<br>
	<br>
	<div class="clearfix" style="width: 100%;">
	<div class="float-left" style="margin-left: 2%; width: 10%;"><span>
	<input type="radio" name="searchType" value="Daily"
		data-bind="checked: groupReportsView.searchType">&nbsp;&nbsp;&nbsp;&nbsp;Daily
	</span></div>
	<div class="float-left" style="margin-left: 0%; width: 15%;"><span>
	<input type="radio" name="searchType" value="Summary"
		data-bind="checked: groupReportsView.searchType">&nbsp;&nbsp;&nbsp;&nbsp;Summary
	</span></div>
	<div data-bind="click: searchReports" class="normal-button"
		style="width: 65px; float: left; margin-top: -4px;">Search</div>
	<div
		data-bind="visible: reportsArray().length != 0,click: exportReports"
		class="normal-button"
		style="margin-left: 2%; width: 65px; float: left; margin-top: -4px;">Export</div>
	</div>
</f:form></div>

<div class="normal-grid" id="dailyGroupReports"
	style="width: 95%; display: none; margin-left: 2%;">
<table>
	<thead>
		<tr>
			<th width='12%'>Date</th>
			<th width='12%'>Project</th>
			<th width='10%'>Employee Id</th>
			<th width='15%'>Employee<br />
			Name</th>
			<th width='13%'>Activity</th>
			<th width='14%'>Task</th>
			<th width='19%'>Work Items</th>
			<th width='5%'>Effort<br />
			(hh:mm)</th>
		</tr>
	</thead>
	<tbody style="background-color: white !important;"
		data-bind="visible: reportsArray().length == 0">
		<tr>
			<td colspan="8" style="text-align: center;"><span> No
			Data Found </span></td>
		</tr>
	</tbody>
	<tbody data-bind="foreach: reportsArray">
		<tr>
			<td><span data-bind="text: date"></span></td>
			<td><span data-bind="text: projectName"></span></td>
			<td><span data-bind="text: employeeId"></span></td>
			<td><span data-bind="text: lastName"></span>, <span
				data-bind="text: firstName"></span></td>
			<td><span data-bind="text: activityName"></span></td>
			<td><span data-bind="text: taskName"></span></td>
			<td><span data-bind="text: workItems"></span></td>
			<td><span data-bind="text: hours"></span>:<span
				data-bind="text: minutes"></span></td>
		</tr>
	</tbody>
</table>
</div>

<div class="normal-grid" id="groupReportsSummary"
	style="width: 95%; display: none; margin-left: 2%;">
<table>
	<thead>
		<tr>
			<th width='15%'>Project</th>
			<th width='10%'>Employee Id</th>
			<th width='15%'>Employee Name</th>
			<th width='20%'>Activity</th>
			<th width='32%'>Task</th>
			<th width='8%'>Effort<br />
			(hh:mm)</th>
		</tr>
	</thead>
	<tbody style="background-color: white !important;"
		data-bind="visible: reportsArray().length == 0">
		<tr>
			<td colspan="6" style="text-align: center;"><span> No
			Data Found </span></td>
		</tr>
	</tbody>
	<tbody data-bind="foreach: reportsArray">
		<tr>
			<td><span data-bind="text: projectName"></span></td>
			<td><span data-bind="text: employeeId"></span></td>
			<td><span data-bind="text: lastName"></span>, <span
				data-bind="text: firstName"></span></td>
			<td><span data-bind="text: activityName"></span></td>
			<td><span data-bind="text: taskName"></span></td>
			<td><span data-bind="text: hours"></span>: <span
				data-bind="text: minutes"></span></td>
		</tr>
	</tbody>
</table>
</div>
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

var groupList = ${groupList};
var workItemInd = ${workItemInd};

var self = new AppViewModel();
AppViewModel.errors = ko.validation.group(AppViewModel);

function AppViewModel() {
	 var self = this;
	 var validateGroupSearch = {}; 
		
		self.reportsArray = ko.observableArray([]);
		self.groupReportsView = {
			groupArray : ko.observableArray([]),
			groupId : ko.observable().extend({ required: 'Group'}),
			fromDate : ko.observable().extend({ required: 'From Date'}),
			toDate : ko.observable().extend({ required: 'To Date'}),
			searchType : ko.observable('Daily')
		};	
		self.groupReportsView.groupId.subscribe(function(groupId) {
			YUI().use('node', "io", function(Y) {
				Y.one('#groupReportsSummary').setStyle('display', 'none');
				Y.one('#dailyGroupReports').setStyle('display', 'none');
				self.reportsArray.removeAll();
			});
		});
		validateGroupSearch.errors = ko.validation.group(self.groupReportsView,{deep: true});
		
		self.showCalendar = function(data){
			popupCalendar(data);
		};
		//self.workItemInd = ko.observable(workItemInd);
		self.exportReports = function() {
			document.location.href = '/ems-web/ems/project/getExcelGroupWise?searchType=group'+self.groupReportsView.searchType();
		}
		
		self.setTitle = function(array,data) {
			var response = ko.toJS(data);
			for(i in array)
			{
				if(array[i].id == response)
				{
					return array[i].name;
				}
			}
		};
		self.searchReports = function(){
			if(validateGroupSearch.errors().length == 0)
			{
				if(StartEndCheck())
				{
					YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y) {
						var groupInfo = 'groupId='+self.groupReportsView.groupId()+'&fromDate='+self.groupReportsView.fromDate()+'&toDate='+self.groupReportsView.toDate();
						groupInfo = groupInfo +'&searchType='+self.groupReportsView.searchType();
			        	//alert(projectInfo);
			        	Y.one('#dateValid').setStyle('display','none');
			        	Y.one("#loadingImage").setStyle("display","block");
						var cfg = {
								method: 'POST',
								data: groupInfo,
								on: {
									success: function (id, o) {
									var message =Y.JSON.parse(o.responseText);
									var reports = message.reportDetails;
										if (message != null) {
											Y.one("#loadingImage").setStyle("display","none");
											self.reportsArray.removeAll();
											for(var i=0 ; i < reports.length ; i++){
												self.reportsArray.push(new addNewReport(reports[i]));
											}
											workItemInd = message.workItemInd;
											if(workItemInd == 'Summary')
											{
												Y.one('#dailyGroupReports').setStyle('display','none');
												Y.one('#groupReportsSummary').setStyle('display','block');
											}
											if(workItemInd == 'Daily')
											{
												Y.one('#groupReportsSummary').setStyle('display','none');
												Y.one('#dailyGroupReports').setStyle('display','block');
											}
											//self.workItemInd(message.workItemInd);
											//alert(self.workItemInd());
											//ko.mapping.fromJS(message.reportDetails, {}, self.reportsArray);
										}
										
									}
								}
		  				};
						Y.io("/ems-web/ems/project/groupWiseSearch", cfg);
					});
				}
				else
				{
					YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y) {
					Y.one('#dateValid').setStyle('display','block');
					});
				}
			}
			else
			{
				validateGroupSearch.errors.showAllMessages(true); 
			}
			
		}
		function StartEndCheck()
		   {
		     var StartDate = self.groupReportsView.fromDate();
		     var EndDate = self.groupReportsView.toDate();
		     var fromdate = new Date('' + StartDate.split('-')[2].substring(2), StartDate.split('-')[1], StartDate.split('-')[0]);
				fromdate.setFullYear(StartDate.split('-')[2]);
				fromdate.setMonth(StartDate.split('-')[1] - 1);
				fromdate.setDate(StartDate.split('-')[0]);
			
			 var todate= new Date('' + EndDate.split('-')[2].substring(2),EndDate.split('-')[1], EndDate.split('-')[0]);
				todate.setFullYear(EndDate.split('-')[2]);
				todate.setMonth(EndDate.split('-')[1] - 1);
				todate.setDate(EndDate.split('-')[0]);
				
				if ( fromdate > todate) {
					return false;
				}else{
					return true;
				}
		    
		   }
		function addNewReport(reports) {
			var self = this;
				self.employeeId = ko.observable(reports.employeeId);
				self.projectName = ko.observable(reports.projectName);
				self.firstName = ko.observable(reports.firstName);
				self.lastName = ko.observable(reports.lastName);
				self.activityName = ko.observable(reports.activityName);
				self.taskName = ko.observable(reports.taskName);
				self.workItems = ko.observable(reports.workItems);
				self.date = ko.observable(reports.date);
				self.hours = ko.observable(reports.hours);
				self.minutes = ko.observable(reports.minutes);
		}
	
}
function projectDropDown(id,name){
	this.id = id;
	this.name = name;
};
function initKo() {
	ko.applyBindings(self);
	for(var i=0;i<groupList.length;i++){
		self.groupReportsView.groupArray.push(new projectDropDown(groupList[i]['groupId'],groupList[i]['groupName']));
	}
}
//calendar
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
	
	// render calendar
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
	//move the calender and show cal 
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
				self.groupReportsView.fromDate(date);
		}
		if (currDateBxId == 'to') {
				self.groupReportsView.toDate(date);
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

			// Get a reference to Y.DataType.Date
				var dtdate = Y.DataType.Date;

				// Listen to calendar's selectionChange event.
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
						// Date string
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
</script>
</body>
</html>