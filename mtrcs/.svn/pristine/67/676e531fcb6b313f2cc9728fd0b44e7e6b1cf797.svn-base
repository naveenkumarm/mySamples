<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
</head>

<body onload='intiKo()'>
<div id="timesheet_container">

<div id="nav-calendar" class="default_calender calendar-picker"> 
  <div>
     <div id="mycalendar"></div>
  </div>
</div>

<f:form method="post" name="timeSheetForm" id="timeSheetForm" action="/ems-web/ems/timesheet/save"
		modelAttribute="timeSheetList" >
	<div class="clearfix" style="width:100%;">	
	<sec:authorize access="hasAnyRole('ROLE_SR.MANAGEMENT','ROLE_MANAGER')">
	<div class="float-left" style="margin-top:2%;width:100%;">
		<div class="float-left" style="margin-left:2%;width:25%;">
			<div class="float-left" style="width:35%;margin-top:4px;"><LABEL>Project</LABEL></div>
			<div class="float-left" style="margin-left:.5%;width:63%;">
				<select style="width:87%;" name="" id="" data-bind="value: timesheetView.projectName,optionsValue: 'id', optionsText: 'name', options: timesheetView.projectArray,optionsCaption:'Select', attr: {'title': $root.setTitle(timesheetView.projectArray(), timesheetView.projectName)}">
            	</select>
            </div>
		</div>
		<div class="float-left" style="margin-left:4%;width:25%;">
			<div class="float-left" style="width:35%;margin-top:4px;"><LABEL>Employee</LABEL></div>
			<div class="float-left" style="margin-left:.5%;width:63%;">
				<select style="width:87%;" name="" id="" data-bind="value: timesheetView.empName,optionsValue: 'id', optionsText: 'name', options: timesheetView.empArray,optionsCaption:'Select', attr: {'title': $root.setTitle(timesheetView.empArray(), timesheetView.empName)}">
            	</select>
            </div>
		</div>
		<br/>
		<br/>
	</div>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_DEVELOPER','ROLE_ADMIN/PMO')">
	<div class="float-left" style="margin-top:2%;width:100%;">
		<div class="float-left" style="margin-left:2%;width:25%;">
			<div class="float-left" style="width:35%;margin-top:4px;"><LABEL>Project</LABEL></div>
			<div class="float-left" style="margin-left:.5%;width:63%;">
				<select style="width:87%;" name="" id="" data-bind="value: timesheetView.userProjectName,optionsValue: 'id', optionsText: 'name', options: timesheetView.userProjectArray,optionsCaption:'Select', attr: {'title': $root.setTitle(timesheetView.userProjectArray(), timesheetView.userProjectName)}">
            	</select>
            </div>
		</div>
		<div class="float-left" style="margin-left:4%;width:25%;">
			<div class="float-left" style="width:35%;margin-top:4px;"><LABEL>Employee</LABEL></div>
			<div class="float-left" style="margin-left:.5%;width:63%;">
				<select style="width:87%;" disabled="disabled" name="" id="" data-bind="value: timesheetView.userName,optionsValue: 'id', optionsText: 'name', options: timesheetView.userNameArray,optionsCaption:'Select', attr: {'title': $root.setTitle(timesheetView.userNameArray(), timesheetView.userName)}">
            	</select>
            </div>
		</div>
		<br/>
		<br/>
	</div>
	</sec:authorize>
	<div  class="float-left" style="margin-top:2%;width:100%;">
		<div class="float-left" style="margin-left:2%;width:25%;">
			<div  class="float-left" style="width:35%;margin-top:4px;"><LABEL>From Date</LABEL></div>
			<div  class="float-left" style="margin-left:.5%;width:45%;">
			<input  type="text" readonly=true style="width:100%;" data-bind="value: timesheetView.fromDate" />
            </div>
            <div  class="float-left" style="margin-left:2%;width:14%; margin-top:3px;">
				<a id="date-1" class="calender-ico" data-bind="click: showFromDateCalendar">&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</div>
		</div>
		<div class="float-left" style="margin-left:4%;width:25%;">
			<div  class="float-left" style="width:35%;margin-top:4px;"><LABEL>To Date</LABEL></div>
			<div  class="float-left" style="margin-left:.5%;width:45%;">
			<input type="text" readonly=true style="width:100%;" data-bind="value: timesheetView.toDate" />
            </div>
            <div  class="float-left" style="margin-left:2%;width:14%;margin-top:3px;">
				<a id="date-2" class="calender-ico" data-bind="click: showToDateCalendar">&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</div>
		</div>
		<sec:authorize access="hasAnyRole('ROLE_SR.MANAGEMENT','ROLE_MANAGER')">
		<div data-bind="click: searchTimeSheet" class="normal-button" style="width:65px; float:left;margin-top:-4px;">Search</div>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_DEVELOPER','ROLE_ADMIN/PMO')">
		<div data-bind="click: viewTimeSheet" class="normal-button" style="width:65px; float:left;margin-top:-4px;">Search</div>
		</sec:authorize>
	</div>
	</div>
	<br/>
<div align="center" class="add-time-sheet-tble" style="width:96%; margin:2%;" >
<table cellpadding="0" cellspacing="0">
    <thead>
            <tr>
               <th width="10%">Date</th>
				<th width="10%">Activity </th>
				<th width="20%">Task </th>
				<th width="25%">Work Items</th>
				<th width="8%">Hours Booked</th>
				<th width="3%">Billable</th>
				<th width="6%">Location</th>
				<th width="8%">Status</th>
            </tr>
        </thead>
		<tbody style="background-color: white !important;" data-bind="visible: timeSheet().length == 0">
         	<tr>
            	<td colspan="8" style="text-align:center;">
            		<span>	No Data Found	</span>
            	</td>
            </tr>
        </tbody>
    <tbody data-bind="foreach: timeSheet">
        <tr>
            <td>
            	<span>
            		<input type="hidden" data-bind="value: projectId ,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].projectId'}"  />
					<input type="hidden" data-bind="value: projectName,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].projectName'}" />
					<input type="text" style="width:100%;" data-bind="value: date,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].date', id:'timeSheetList['+$parent.timeSheet.indexOf($data)+'].date'}" />
				</span>
            </td>
            <td>
       			 <span style="width:100%;">
					<input type="hidden" data-bind="value: activityId,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].activityId'}" />
					<select style="width:100%;"  data-bind="value: activityId,optionsValue: 'id', optionsText: 'name', options: activityArray,optionsCaption:'Select',attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].activityId', 'title': $root.setTitle(activityArray(), activityId)}">
               		</select>
				</span>
            </td>
            <td>
				<span style="width:100%;">
					<input type="hidden" maxlength="35" data-bind="value: taskId,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].taskId'}" />
					<select style="width:100%;" data-bind="value: taskId,optionsValue: 'id', optionsText: 'name', options: taskArray,optionsCaption:'Select',attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].taskId', 'title': $root.setTitle(taskArray(), taskId)}">
		        	</select>
				</span>
            </td>
            <td>
            	<span style="width:100%;">
            	<input type="text" readonly=true style="width:100%;" maxlength="50" data-bind="value: workItems"/>
            		<input type="hidden"  maxlength="35" data-bind="value: departmentName,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].departmentId'}" />
            		<input type="hidden" data-bind="value: id,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].id'}" />
            		<input type="hidden" data-bind="value: employeeId,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].employeeId'}" />
           		</span>
            </td>
            <td>
            	<span style="width:100%;">
					<input type="text" maxlength="5" style="text-align:right;width:100%;" data-bind="value: effort,attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].effort'}" />
					<input type="hidden" maxlength="2" data-bind="value: hours,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].hours'}" />
					<input type="hidden" maxlength="2" data-bind="value: minutes,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].minutes'}" />
				</span>	
            </td>
            <td>
            	<span style="width:100%;">
            		<input type="text" style="width:100%;" maxlength="35" readonly=true data-bind="value: billable,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].billable'}" />
           		</span>
            </td>
            <td>
            	<span style="width:100%;">
            		<input type="text" style="width:100%;" maxlength="35" readonly=true data-bind="value: location,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].location'}" />
           		</span>
            </td>
            <td>
            	<span style="width:100%;">
					<input type="hidden" maxlength="35" data-bind="value: authoriser,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].authoriser'}" />
					<input type="text" maxlength="35" style="width:100%;" readonly=true data-bind="value: approvalStatus,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].authoriserStatus'}" />
				</span>
            </td>
        </tr>     
    </tbody>
     <tbody style="background-color: white !important;" data-bind="visible: timeSheet().length != 0">
         	<tr>
         		<td colspan="4"></td>
            	<td colspan="1" style="text-align:right">
            		<span data-bind="text: hoursBookedSummary"></span>
            	</td>
            	<td colspan="3"></td>
            </tr>
     </tbody>
</table>
</div>			
</f:form>
</div>	

<script type="text/javascript">
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
var calendarExistsInd = false;
var projectList = ${projectList};
var activityList = ${activityList};
var employeeList = ${empInfoList};
var userInfo = ${userInfo};
var userInfoList = ${userInfoList};
var self = new AppViewModel();

AppViewModel.errors = ko.validation.group(AppViewModel);

// creating knockout model
function AppViewModel() {
	
	 var self = this;
	 var validateTimeSheet = {}; 
	
	self.timeSheet = ko.observableArray([]);

	self.timesheetView = {
		projectArray : ko.observableArray([]),
		projectName : ko.observable(),
		userProjectArray : ko.observableArray([]),
		userProjectName : ko.observable(),
		empArray : ko.observableArray([]),
		empName : ko.observable().extend({ required: 'Employee'}),
		userNameArray : ko.observableArray([]),
		userName : ko.observable(),
		fromDate : ko.observable().extend({ required: 'From Date'}),
		toDate : ko.observable().extend({ required: 'To Date'})
	};	
	validateTimeSheet.errors = ko.validation.group(self.timesheetView,{deep: true});
	
	self.timesheetView.projectName.subscribe(function (projectId){
		if(projectId != undefined){
		for(var i=0;i<projectList.length;i++){
			if(projectList[i]['projectId'] == projectId){
				var empList = projectList[i]['employeeList'];
				self.timesheetView.empArray.removeAll();
				self.timesheetView.empName(undefined);
				for (var m=0,n=empList.length ; m < n ; m++) {
					self.timesheetView.empArray.push(new empDropDown(empList[m]['employeeId'],empList[m]['firstName']+' '+empList[m]['lastName']));
				}
			}
		}
		}
		else{
			self.timesheetView.empArray.removeAll();
			self.timesheetView.empName(undefined);
			for(var i=0;i<employeeList.length;i++){
				self.timesheetView.empArray.push(new empDropDown(employeeList[i]['employeeId'],employeeList[i]['firstName']+' '+employeeList[i]['lastName']));
			}
			}
	});
	
	self.searchTimeSheet = function(){
		if(self.timesheetView.empName() != undefined && self.timesheetView.fromDate() != undefined && self.timesheetView.toDate() != undefined) {
		var url = '/ems-web/ems/timesheet/get?';
		if(self.timesheetView.projectName() != undefined){
			 url = url+'projectId='+self.timesheetView.projectName()+'&';
		}
		url = url +'empId='+self.timesheetView.empName()+'&fromDate='+self.timesheetView.fromDate()+'&toDate='+self.timesheetView.toDate();
		YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
		 Y.one("#loadingImage").setStyle("display","block");
			Y.io(url, {
				method: 'GET',
				on: {
				success: function (id, o) {
				var response = Y.JSON.parse(o.responseText);
				self.timeSheet.removeAll();
				if(response.timSheetList != undefined){
					var timeSheet = response.timSheetList;
					for(var i=0 ; i < timeSheet.length ; i++){
						self.timeSheet.push(new addNewTimeSheet(timeSheet[i]));
					}
					for(var i=0 ; i < timeSheet.length ; i++){
						Y.one(document.getElementById('timeSheetList['+i+'].date')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].activityId')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].taskId')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].effort')).set('disabled',true);
					}
				}
				Y.one("#loadingImage").setStyle("display","none");
			}
			}
			});	
		});
		}
		else{
			validateTimeSheet.errors.showAllMessages(true); 
			}
	};

	/**
	*	method used to search the  timesheet.
	*/	
	self.viewTimeSheet = function(){
		if(self.timesheetView.fromDate() != undefined && self.timesheetView.toDate() != undefined) {
		var url = '/ems-web/ems/timesheet/get?';
		if(self.timesheetView.userProjectName() != undefined){
			 url = url+'projectId='+self.timesheetView.userProjectName()+'&';
		}
		url = url +'empId='+self.timesheetView.userName()+'&fromDate='+self.timesheetView.fromDate()+'&toDate='+self.timesheetView.toDate();
		YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
		 Y.one("#loadingImage").setStyle("display","block");
			Y.io(url, {
				method: 'GET',
				on: {
				success: function (id, o) {
				var response = Y.JSON.parse(o.responseText);
				self.timeSheet.removeAll();
				if(response.timSheetList != undefined){
					var timeSheet = response.timSheetList;
					for(var i=0 ; i < timeSheet.length ; i++){
						self.timeSheet.push(new addNewTimeSheet(timeSheet[i]));
					}
					for(var i=0 ; i < timeSheet.length ; i++){
						Y.one(document.getElementById('timeSheetList['+i+'].date')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].activityId')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].taskId')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].effort')).set('disabled',true);
					}
				}
				Y.one("#loadingImage").setStyle("display","none");
			}
			}
			});	
		});
		}
		else{
			validateTimeSheet.errors.showAllMessages(true); 
			}
	};
    /**
   	* Set title function.
   	*/
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
	function addNewTimeSheet(timeSheetInfo) {
		var self = this;
			self.id = ko.observable(timeSheetInfo.id);
			self.employeeId = ko.observable(timeSheetInfo.employeeId);
			self.projectId = ko.observable(timeSheetInfo.projectId);
			self.projectName = ko.observable(timeSheetInfo.projectName);
			self.date = ko.observable(timeSheetInfo.date);
			self.activityArray = ko.observableArray([]);
			self.activityId = ko.observable(timeSheetInfo.activityId);
			for (var j=0,k=activityList.length ; j < k ; j++) {
				self.activityArray.push(new activityDropDown(activityList[j]['activityId'],activityList[j]['activityName']));
			}
			self.taskArray = ko.observableArray([]);
			for (var j=0,k=activityList.length ; j < k ; j++) {
				if(activityList[j]['activityId'] == timeSheetInfo.activityId){
					var taskList = activityList[j]['taskList'];
					self.taskArray.removeAll();
					for (var m=0,n=taskList.length ; m < n ; m++) {
						self.taskArray.push(new taskDropDown(taskList[m]['taskId'],taskList[m]['taskName']));
					}
				}
			}
			self.taskId = ko.observable(timeSheetInfo.taskId);
			self.workItems = ko.observable(timeSheetInfo.workItems);
			self.effort = ko.observable(timeSheetInfo.hours+'.'+timeSheetInfo.minutes);
			self.hours = ko.observable(timeSheetInfo.hours);
			self.minutes = ko.observable(timeSheetInfo.minutes);
			self.billable = ko.observable(timeSheetInfo.billable);
			self.location = ko.observable(timeSheetInfo.location);
			self.authoriser = ko.observable(timeSheetInfo.authoriser);
			self.approvalStatus = ko.observable(timeSheetInfo.approvalStatus);
			self.departmentId = ko.observable(timeSheetInfo.departmentId);
			self.departmentName = ko.observable(timeSheetInfo.departmentName);
			/**
			*	subscribe event used to load the task with respect to activity.
			*/	
			self.activityId.subscribe(function (activityId) {
				for (var j=0,k=activityList.length ; j < k ; j++) {
					if(activityList[j]['activityId'] == activityId){
						var taskList = activityList[j]['taskList'];
						self.taskArray.removeAll();
						for (var m=0,n=taskList.length ; m < n ; m++) {
							self.taskArray.push(new taskDropDown(taskList[m]['taskId'],taskList[m]['taskName']));
						}
					}
				}
			});
		};
		
		/**
		*	auto compute used to summarize hours booked in ui .
		*/	
		self.hoursBookedSummary = ko.computed({
			read: function() {
			var hoursSummary = null;
			for(var i=0 , j=self.timeSheet().length ; i < j ; i++ )
			{
				if(i == 0){
					hoursSummary =   Number(self.timeSheet()[i].effort());
				}
				else{
					hoursSummary = hoursSummary + Number(self.timeSheet()[i].effort());
				}
			}
			if(hoursSummary == 0){
				hoursSummary = '0.00'
				}
			return hoursSummary;
		}
		});
		
	/**
	*	Method used to show from date calendar
	*/
	self.showFromDateCalendar = function(data){
		popupCalendar('1');
	};
	
	/**
	*	Method used to show to date calendar
	*/
	self.showToDateCalendar = function(data){
		popupCalendar('2');
	};
    
}

// methods to create an array .

function activityDropDown(id,name){
	this.id = id;
	this.name = name;
};
function taskDropDown(id,name){
	this.id = id;
	this.name = name;
};
function projectDropDown(id,name){
	this.id = id;
	this.name = name;
};
function empDropDown(id,name){
	this.id = id;
	this.name = name;
};

/**
*	method used to initialize knockout.
*/	
function intiKo() {
	ko.applyBindings(self);
	for(var i=0;i<projectList.length;i++){
		self.timesheetView.projectArray.push(new projectDropDown(projectList[i]['projectId'],projectList[i]['projectName']));
	}
	for(var i=0;i<employeeList.length;i++){
		self.timesheetView.empArray.push(new empDropDown(employeeList[i]['employeeId'],employeeList[i]['firstName']+' '+employeeList[i]['lastName']));
	}
	if(userInfoList != undefined && userInfoList[0] != undefined && userInfoList[0].projectList != undefined){
		var userProjectList = userInfoList[0].projectList; 
		for(var i=0;i<userProjectList.length;i++){
			self.timesheetView.userProjectArray.push(new projectDropDown(userProjectList[i]['projectId'],userProjectList[i]['projectName']));
		}
	}
	if(userProjectList.length != 0){
		self.timesheetView.userProjectName(userProjectList[0]['projectId']);
	}
	
	var userDetail = userInfo;
	self.timesheetView.userNameArray.push(new empDropDown(userDetail.userId,userDetail.userFirstName+' '+userDetail.userLastName));
	self.timesheetView.userName(userDetail.userId);
}
// calendar implementation
var calenderObj = null;
var outerCalWidgetContainerId = '#nav-calendar';
var calWidgetContainerId = '#cal-widget-container';
var outerCalWidgetContainerDiv = null;
var currDateBxId = null;
var futureDate = new Date();
futureDate.setHours(13);

/**
*	method used to calendar change its position with respect to field  .
*/	
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
	var node = '#date-'+data+'';
	var xy = Y.one(node).getXY();
	xy[0] = xy[0] - 150;
	xy[1] = xy[1] + 25;
	outerCalWidgetContainerDiv.setXY(xy);
		
});
}

/**
*	method used to assign date value to respective fields.
*/	
	function selectionChangeCallback(date) {
		if (currDateBxId == '1') {
			if (self.timesheetView.toDate() != undefined) {
				var to_date = self.timesheetView.toDate();
				var to_dated = new Date(
						'' + to_date.split('-')[2].substring(2), to_date
								.split('-')[1], to_date.split('-')[0]);
				to_dated.setFullYear(to_date.split('-')[2]);
				to_dated.setMonth(to_date.split('-')[1] - 1);
				to_dated.setDate(to_date.split('-')[0]);
				var from_date = new Date('' + date.split('-')[2].substring(2),
						date.split('-')[1], date.split('-')[0]);
				from_date.setFullYear(date.split('-')[2]);
				from_date.setMonth(date.split('-')[1] - 1);
				from_date.setDate(date.split('-')[0]);
				if (from_date <= to_dated) {
					self.timesheetView.fromDate(date);
				}
			} else {
				self.timesheetView.fromDate(date);
			}
		}
		if (currDateBxId == '2') {
			if (self.timesheetView.fromDate() != undefined) {
				var from_date = self.timesheetView.fromDate();
				var from_dated = new Date('' + from_date.split('-')[2]
						.substring(2), from_date.split('-')[1], from_date
						.split('-')[0]);
				from_dated.setFullYear(from_date.split('-')[2]);
				from_dated.setMonth(from_date.split('-')[1] - 1);
				from_dated.setDate(from_date.split('-')[0]);
				var to_date = new Date('' + date.split('-')[2].substring(2),
						date.split('-')[1], date.split('-')[0]);
				to_date.setFullYear(date.split('-')[2]);
				to_date.setMonth(date.split('-')[1] - 1);
				to_date.setDate(date.split('-')[0]);
				if (from_dated <= to_date) {
					self.timesheetView.toDate(date);
				}
			} else {
				self.timesheetView.toDate(date);
			}
		}
	}

/**
*	method used to calendar create calendar per page.
*/
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
