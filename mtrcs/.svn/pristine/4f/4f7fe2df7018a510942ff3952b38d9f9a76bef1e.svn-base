<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
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
		
	<div class="clearfix" style="margin-top:2%;width:100%;">
		<div class="float-left" style="margin-left:2%;width:30%;">
			<div class="float-left" style="width:30%;margin-top:3px;"><label>Project</label></div>
			<div class="float-left" style="margin-left:2%;width:68%;">
				<select style="width:100%;" name="projectId" id="" data-bind="value: projectName,optionsValue: 'id', optionsText: 'name', options: projectArray,optionsCaption:'Select', attr: {'title': $root.setTitle(projectArray(), projectName)}">
            	</select>
            </div>
		</div>
		<div class="float-left" style="margin-left:4%;width:30%;">
			<div class="float-left" style="width:30%;margin-top:3px;"><label>Employee</label></div>
			<div class="float-left" style="margin-left:2%;width:68%;">
				<select style="width:100%;" name="" id="" data-bind="value: empName,optionsValue: 'id', optionsText: 'name', options: empArray,optionsCaption:'Select', attr: {'title': $root.setTitle(empArray(), empName)}">
            	</select>
            </div>
		</div>
	</div>
	<div class="clearfix" data-bind="visible: projectClosedInd" style="margin-top:4%;width:100%;">
	
		<div data-bind="click: saveTimeSheetInfo" class="normal-button" style="width:65px; float:left; margin:0 0 0 2%;">Save</div>
		<div data-bind="click: submitTimeSheetInfo" class="normal-button" style="width:65px; float:left; margin:0 0 0 1%;">Submit</div>
		
				
	</div>
<div id="successMessage" align="center" style="display: none; background: none repeat scroll 0pt 0pt #DFF0D9;; color: #279800; font-size: 12px; padding: 5px; width: 95%; margin: 2% 0 0 2%;"></div>		
<div align="center" class="add-time-sheet-tble" style="width:96%; margin:2%;" >
<table cellpadding="0" cellspacing="0">
    <thead>
            <tr>
               <th width="10%">Date</th>
				<th width="10%">Activity </th>
				<th width="20%">Task </th>
				<th width="25%">Work Items</th>
				<th width="6%">Hours Booked</th>
				<th width="3%">Billable</th>
				<th width="6%">Location</th>
				<th width="5%">Status</th>
				<th width="4%" data-bind="visible: disableRemoveLink">Remove</th>
            </tr>
        </thead>
          <tbody style="background-color: white !important;" data-bind="visible: timeSheet().length == 0">
         	<tr>
            	<td colspan="9" style="text-align:center;">
            		<span>	No Data Found	</span>
            	</td>
            </tr>
        </tbody>
    <tbody style="background-color: white !important;" data-bind="foreach: timeSheet">
        <tr>
            <td>
            	<span>
            		<input type="hidden" data-bind="value: projectId ,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].projectId'}"  />
					<input type="hidden" data-bind="value: projectName,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].projectName'}" />
					<input type="hidden" data-bind="value: indexValue" />
					<input type="text" style="margin-left:1%;width:100%;" readonly=true data-bind="value: date,uniqueName: true,click: $root.showCalendar,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].date', id:'date-'+$parent.timeSheet.indexOf($data)+''}" />
				</span>
            </td>
            <td>
       			 
			<select name="" style="width:100%;" id="" data-bind="value: activityId,optionsValue: 'id', optionsText: 'name', options: activityArray,optionsCaption:'Select',uniqueName: true,attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].activityId',name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].activityId', 'title': $root.setTitle(activityArray(), activityId)}">
             		</select>

            </td>
            <td>

					<select name="" style="width:100%;" id="" data-bind="value: taskId,optionsValue: 'id', optionsText: 'name', options: taskArray,optionsCaption:'Select',uniqueName: true,attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].taskId',name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].taskId', 'title': $root.setTitle(taskArray(), taskId)}">
		        	</select>

            </td>
            <td>

            		<input type="text"  style="width:100%;" maxlength="50" data-bind="value: workItems,attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].workItems',name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].workItems'}" />
            		<input type="hidden" maxlength="35" data-bind="value: departmentId,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].departmentId'}" />
            		<input type="hidden" maxlength="35" data-bind="value: departmentName,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].departmentName'}" />
            		<input type="hidden" data-bind="value: id,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].id'}" />
            		<input type="hidden" data-bind="value: employeeId,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].employeeId'}" />
            		<input type="hidden" data-bind="value: onBehalf,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].onBehalf'}" />

            </td>
            <td>

					<input type="text" maxlength="5" style="text-align:right;width:100%;" data-bind="value: effort,attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].effort'},event:{ blur : $root.addNewTimeSheetFromEffort}" />
					<input type="hidden" maxlength="2" data-bind="value: hours,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].hours'}" />
					<input type="hidden" maxlength="2" data-bind="value: minutes,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].minutes'}" />

            </td>
            <td>

            		<input type="text" style="width:100%;" maxlength="35" readonly=true data-bind="value: billable,uniqueName: true,attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].billable',name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].billable'}" />

            </td>
            <td>

            		<input type="text" style="width:100%;" maxlength="35" readonly=true data-bind="value: location,uniqueName: true,attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].location',name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].location'}" />

            </td>
            <td>

					<input type="hidden" maxlength="35" data-bind="value: authoriser,uniqueName: true,attr: {name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].authoriser'}" />
					<input type="text" maxlength="35" style="width:100%;" readonly=true data-bind="value: approvalStatus,uniqueName: true,attr: {id: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].approvalStatus',name: 'timeSheetList['+$parent.timeSheet.indexOf($data)+'].approvalStatus'}" />

            </td>
            <td data-bind="visible: $root.disableRemoveLink">
            	<span data-bind="visible: id() < 0 ,click: $parent.removeTimeSheet" class="link-button" style="margin-left:1%;">
					<span>Remove</span>
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
            	<td colspan="4"></td>
            </tr>
        </tbody>
</table>
</div>			
</f:form>
</div>	

<script type="text/javascript">

var self = new AppViewModel();

ko.validation.configure({
    registerExtenders: true,
    messagesOnModified: true,
    insertMessages: true,
    parseInputAttributes: true	,
    messageTemplate: null
});
var projectList = ${projectList};
var activityList = ${activityList};
var userInfo = ${userInfo};
var loginEmployee = userInfo.userId;
var maxHours = userInfo.maxHours;
var billableResource = null;
var empId = null;
var resourceDeptId = null;
var resourceDeptName = null;
var resourceProjectId = null;
var resourceAuthoriser = null;
var resourceLocation = null;
var onBehalf = loginEmployee;
var calendarExistsInd = false;
var readOnlyLength = null;
var timeSheetInfo = {
	    id: '-1',
	    employeeId : '',
	    projectId: '',
	    projectName: '',
	    date: '',
	    activityId: '',
	    taskId: '',
	    effort: '',
	    hours: '0',
	    minutes: '00',
	    billable: '',
	    location: '',
	    authoriser: '',
	    approvalStatus: '',
	    departmentId: '',
	    departmentName: '',
	    onBehalf: '',
	    workItems: ''
	};

// creating knockout model
function AppViewModel() {
	
	 var self = this;
	 var validateTimeSheet = {}; 
	
	self.timeSheet = ko.observableArray([]);
	self.projectArray =ko.observableArray([]);
	self.projectName = ko.observable();
	self.empArray =ko.observableArray([]);
	self.empName = ko.observable();
	self.disableRemoveLink = ko.observable(true);

	/**
	*	subscribe event used to add timesheet from the back end.
	*/	
	self.projectName.subscribe(function (projectId){
	YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
		    Y.one("#successMessage").setStyle("display","none");
		});   
	  self.empArray.removeAll();
	  self.timeSheet.removeAll(); 
		for(var i=0;i<projectList.length;i++){
			if(projectList[i]['projectId'] == projectId){
				var empList = projectList[i]['employeeList'];
				self.empName(undefined);
				for (var m=0,n=empList.length ; m < n ; m++) {
					if(empList[m]['employeeId'] != loginEmployee){
						self.empArray.push(new empDropDown(empList[m]['employeeId'],empList[m]['firstName']+' '+empList[m]['lastName']));
						}
				}
				if(projectList[i]['projectClosedInd'] == 'Y'){
					self.disableRemoveLink(false);
				}else{
					self.disableRemoveLink(true);
				}
			}
		}
	});

	/**
	*	subscribe event used to add timesheet from the back end.
	*/	
	self.empName.subscribe(function (employeeId){
		if(employeeId != undefined) {
		YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
		    Y.one("#successMessage").setStyle("display","none");
		    Y.one("#loadingImage").setStyle("display","block");
			Y.io('/ems-web/ems/timesheet/get?empId='+employeeId+'&projectId='+self.projectName(), {
				method: 'GET',
				on: {
				success: function (id, o) {
				var response = Y.JSON.parse(o.responseText);
				self.timeSheet.removeAll();
				if(response.timSheetList != undefined){
					var timeSheet = response.timSheetList;
					for(var i=0 ; i < timeSheet.length ; i++){
						self.timeSheet.push(new addNewTimeSheet(timeSheet[i]));
						self.timeSheet()[i].indexValue(i);
						validateTimeSheet.errors = ko.validation.group(self.timeSheet,{deep: true});
						validateTimeSheet.errors.showAllMessages(false);
					}
					readOnlyLength = timeSheet.length;
					for(var i=0 ; i < timeSheet.length ; i++){
						Y.one(document.getElementById('date-'+i+'')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].activityId')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].taskId')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].effort')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].billable')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].location')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].approvalStatus')).set('disabled',true);
						Y.one(document.getElementById('timeSheetList['+i+'].workItems')).set('disabled',true);
					}
				}
				if(response.empInfoList != undefined){
					var empInfoList = response.empInfoList;
					 
					 empId = employeeId;
					 resourceDeptId = empInfoList[0].departmentId;
					 resourceDeptName = empInfoList[0].departmentName;
					 resourceProjectId = self.projectName();
					 resourceAuthoriser = empInfoList[0].authoriser;
					 resourceLocation = empInfoList[0].location;
					 var empProjectList = response.empProjectList;
					 for(var j=0; j < empProjectList.length; j++){
							if(empProjectList[j]['projectId'] == resourceProjectId){
								billableResource = empProjectList[j]['billable'];
							}
						}
					}
				self.addTimeSheet();
				Y.one("#loadingImage").setStyle("display","none");
			}
			}
			});	
		});
		}
		else{
			self.timeSheet.removeAll();
		}
	});

	/**
	*	method to add timesheet values.
	*/
	function addNewTimeSheet(timeSheetInfo) {
		var self = this;
			self.id = ko.observable(timeSheetInfo.id);
			self.indexValue = ko.observable();
			self.employeeId = ko.observable(timeSheetInfo.employeeId);
			self.projectId = ko.observable(timeSheetInfo.projectId);
			self.projectName = ko.observable(timeSheetInfo.projectName);
			self.date = ko.observable(timeSheetInfo.date).extend({ dateRequired: 'Date is Required'});
			self.activityArray = ko.observableArray([]);
			self.activityId = ko.observable(timeSheetInfo.activityId).extend({ required: 'Activity'});
			for (var j=0,k=activityList.length ; j < k ; j++) {
				self.activityArray.push(new activityDropDown(activityList[j]['activityId'],activityList[j]['activityName']));
			}
			self.taskArray = ko.observableArray([]).extend({ required: true});
			for (var j=0,k=activityList.length ; j < k ; j++) {
				if(activityList[j]['activityId'] == timeSheetInfo.activityId){
					var taskList = activityList[j]['taskList'];
					self.taskArray.removeAll();
					for (var m=0,n=taskList.length ; m < n ; m++) {
						self.taskArray.push(new taskDropDown(taskList[m]['taskId'],taskList[m]['taskName']));
					}
				}
			}
			self.taskId = ko.observable(timeSheetInfo.taskId).extend({ required: 'Task'});
			self.workItems = ko.observable(timeSheetInfo.workItems).extend({ required: 'Work Item'});
			self.effort = ko.observable(timeSheetInfo.hours+'.'+timeSheetInfo.minutes).extend({ validateHours: 'Hours Booked is Invalid'});
			self.hours = ko.observable(timeSheetInfo.hours);
			self.minutes = ko.observable(timeSheetInfo.minutes);
			self.billable = ko.observable(timeSheetInfo.billable);
			self.location = ko.observable(timeSheetInfo.location);
			self.authoriser = ko.observable(timeSheetInfo.authoriser);
			self.approvalStatus = ko.observable(timeSheetInfo.approvalStatus);
			self.departmentId = ko.observable(timeSheetInfo.departmentId);
			self.departmentName = ko.observable(timeSheetInfo.departmentName);
			self.onBehalf = ko.observable('');
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
			/**
			*	subscribe event used to hours and minutes from the field.
			*/	
			self.effort.subscribe(function (effort) {
				if(effort.indexOf('.') != -1){
					self.hours(effort.split('.')[0]);
					self.minutes(effort.split('.')[1]);
					}
				else{
					self.hours(effort);
					self.minutes('00');
					}
			});
		};

		

		/**
		*	method used to construct query string.
		*/
        self.constructQueryString = function() {
              var timeSheetFormValues = '';
              timeSheetFormValues = timeSheetFormValues+'&projectId='+self.projectName();

              for(var i = 0 ; i < self.timeSheet().length ; i++) {
              timeSheetFormValues = timeSheetFormValues +'&timeSheetList%5B'+i+'%5D.'+''+'activityId'+'='+self.timeSheet()[i].activityId()+
              '&timeSheetList%5B'+i+'%5D.'+''+'authoriser'+'='+self.timeSheet()[i].authoriser()+
              '&timeSheetList%5B'+i+'%5D.'+''+'approvalStatus'+'='+self.timeSheet()[i].approvalStatus()+
              '&timeSheetList%5B'+i+'%5D.'+''+'billable'+'='+self.timeSheet()[i].billable()+
              '&timeSheetList%5B'+i+'%5D.'+''+'date'+'='+self.timeSheet()[i].date()+
              '&timeSheetList%5B'+i+'%5D.'+''+'departmentId'+'='+self.timeSheet()[i].departmentId()+
              '&timeSheetList%5B'+i+'%5D.'+''+'departmentName'+'='+self.timeSheet()[i].departmentName()+
              '&timeSheetList%5B'+i+'%5D.'+''+'employeeId'+'='+self.timeSheet()[i].employeeId()+
              '&timeSheetList%5B'+i+'%5D.'+''+'hours'+'='+self.timeSheet()[i].hours()+
              '&timeSheetList%5B'+i+'%5D.'+''+'id'+'='+self.timeSheet()[i].id()+
              '&timeSheetList%5B'+i+'%5D.'+''+'location'+'='+self.timeSheet()[i].location()+
              '&timeSheetList%5B'+i+'%5D.'+''+'minutes'+'='+self.timeSheet()[i].minutes()+
              '&timeSheetList%5B'+i+'%5D.'+''+'projectId'+'='+self.timeSheet()[i].projectId()+
              '&timeSheetList%5B'+i+'%5D.'+''+'projectName'+'='+self.timeSheet()[i].projectName()+
              '&timeSheetList%5B'+i+'%5D.'+''+'taskId'+'='+self.timeSheet()[i].taskId()+
              '&timeSheetList%5B'+i+'%5D.'+''+'workItems'+'='+self.timeSheet()[i].workItems();
              }
              return timeSheetFormValues;
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
    /**
	*	method used to save time sheet list.
	*/
	self.save = function() {
		
		var timeSheetInfo = "";
		timeSheetInfo = self.constructQueryString();
		 
	    YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y) {

           		var cfg = {
           				method: 'POST',
						data: timeSheetInfo,
						on: {
							success: function (id, o) {
								if (o.responseText != null) {
		   							Y.one("#loadingImage").setStyle("display","block");
									Y.io('/ems-web/ems/timesheet/get?empId='+self.empName()+'&projectId='+self.projectName(), {
									method: 'GET',
									on: {
									success: function (id, o) {
									var response = Y.JSON.parse(o.responseText);
									self.timeSheet.removeAll();
									if(response.timSheetList != undefined){
									var timeSheet = response.timSheetList;
									for(var i=0 ; i < timeSheet.length ; i++){
									self.timeSheet.push(new addNewTimeSheet(timeSheet[i]));
									self.timeSheet()[i].indexValue(i);
									validateTimeSheet.errors = ko.validation.group(self.timeSheet,{deep: true});
									validateTimeSheet.errors.showAllMessages(false);
								}
									readOnlyLength = timeSheet.length;
								for(var i=0 ; i < timeSheet.length ; i++){
									Y.one(document.getElementById('date-'+i+'')).set('disabled',true);
									Y.one(document.getElementById('timeSheetList['+i+'].activityId')).set('disabled',true);
									Y.one(document.getElementById('timeSheetList['+i+'].taskId')).set('disabled',true);
									Y.one(document.getElementById('timeSheetList['+i+'].effort')).set('disabled',true);
									Y.one(document.getElementById('timeSheetList['+i+'].billable')).set('disabled',true);
									Y.one(document.getElementById('timeSheetList['+i+'].location')).set('disabled',true);
									Y.one(document.getElementById('timeSheetList['+i+'].approvalStatus')).set('disabled',true);
									Y.one(document.getElementById('timeSheetList['+i+'].workItems')).set('disabled',true);
								}
							}
								if(response.empInfoList != undefined){
								   var empInfoList = response.empInfoList;
						 
								   	empId = self.empName();
								 	resourceDeptId = empInfoList[0].departmentId;
					 			  	resourceDeptName = empInfoList[0].departmentName;
					 			   	resourceProjectId = self.projectName();
					 				resourceAuthoriser = empInfoList[0].authoriser;
					 				resourceLocation = empInfoList[0].location;
					 				var empProjectList = response.empProjectList;
									for(var j=0; j < empProjectList.length; j++){
										if(empProjectList[j]['projectId'] == resourceProjectId){
										billableResource = empProjectList[j]['billable'];
								}
							}
						}
								self.addTimeSheet();
								Y.one("#loadingImage").setStyle("display","none");
								}
							  }
							});
									Y.one("#successMessage").setStyle("display","block");
								}
							}
						}
				  };
           	Y.io("/ems-web/ems/timesheet/save", cfg);
			});
		};

	/**
	*	method used to validate,add approval status to time sheet list when save.
	*/		
	self.saveTimeSheetInfo = function() {
		if(self.validate()){
			for(var i=0; i < self.timeSheet().length; i++){
				if(self.timeSheet()[i].approvalStatus() != 'A' && self.timeSheet()[i].approvalStatus() != 'U' 
					&& self.timeSheet()[i].approvalStatus() != 'S' && i > readOnlyLength){
					self.timeSheet()[i].approvalStatus('N');
					}
				}
			document.getElementById("successMessage").innerHTML="Time Sheet Successfully Saved";
			self.save();
		}
		else{
			if(self.timeSheet().length != 0)
			validateTimeSheet.errors.showAllMessages(true);
			}
    };

    /**
	*	method used to validate,add approval status to time sheet list when submit.
	*/	
    self.submitTimeSheetInfo = function() {
		if(self.validate()){
			if (!self.confirmSubmit()) {
				return;
			}
			for(var i=0; i < self.timeSheet().length; i++){
				if(self.timeSheet()[i].approvalStatus() != 'A' && self.timeSheet()[i].approvalStatus() != 'U' && i > readOnlyLength){
					self.timeSheet()[i].approvalStatus('S');
					}
				}
			document.getElementById("successMessage").innerHTML="Time Sheet Successfully Submitted";	
			self.save();
		}
		else{
			if(self.timeSheet().length != 0)
			validateTimeSheet.errors.showAllMessages(true);
			}
    };

    /**
	*	method used to show confirm pop'up.
	*/	
    self.confirmSubmit = function() {
		return confirm("Are you Sure you want to submit this Timesheets?");
	}

    /**
	*	method used to validate time sheet list.
	*/	
	self.validate = function(){
		return (self.timeSheet().length != 0 && validateTimeSheet.errors() == '' );
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
	
	self.projectClosedInd = ko.observable(true);
	/**
	*	method used to add time sheet list.
	*/		
	self.addTimeSheet = function(){
		self.projectClosedInd(true);
		//do not add new row in timesheet when selected project is closed.
		for(var j=0; j < projectList.length; j++){
			if(projectList[j]['projectId'] == self.projectName()){
				if(projectList[j]['projectClosedInd'] == 'Y'){
					self.projectClosedInd(false);
					return;
				}
			}
		}
		self.timeSheet.push(new addNewTimeSheet(timeSheetInfo));
		var i = self.timeSheet().length - 1;
		self.timeSheet()[i].projectId(resourceProjectId);
		self.timeSheet()[i].employeeId(empId);
		self.timeSheet()[i].billable(billableResource);
		self.timeSheet()[i].location(resourceLocation);
		self.timeSheet()[i].departmentId(resourceDeptId);
		self.timeSheet()[i].departmentName(resourceDeptName);
		self.timeSheet()[i].authoriser(resourceAuthoriser);
		self.timeSheet()[i].onBehalf(onBehalf);
		self.timeSheet()[i].indexValue(i);
		validateTimeSheet.errors = ko.validation.group(self.timeSheet,{deep: true});
		validateTimeSheet.errors.showAllMessages(false);
	};

	/**
	*	method used to add timesheet automatically
	*     when than hours booked field is changed 
	*     in the last row.
	*/	
	self.addNewTimeSheetFromEffort = function(data){
		var timesheet = ko.toJS(data);
		if(timesheet.effort != '0.00' && timesheet.indexValue == self.timeSheet().length - 1 && timesheet.effort != '')
			self.addTimeSheet();
	};

	/**
	*	method used to validate date.
	*/	
	ko.validation.rules['dateRequired'] = {
	        validator: function (val, errorMsg) {
	            if (val == ''){
						return false;
		            }
	            return true;
	        },
	        message: '{0}'
	    };
    
	/**
	*	method used to validate hours booked field.
	*/	
	ko.validation.rules['validateHours'] = {
	        validator: function (val, errorMsg) {
	            if( val == '0.00' || val == '' || val.indexOf("-") != -1){
		            return false;
		         }
	            else{
	            	if (val.indexOf(".") != -1){
		            		if(val.split('.')[0] == undefined || val.split('.')[0] == 0){
									return (val.split('.')[1] != undefined && val.split('.')[1] > 0 && val.split('.')[1] < 60);
			            		}
		            		else{
									return (Number(val.split('.')[0]) < Number(maxHours) && val.split('.')[1] < 60);
			            		}
		            	 }
	            	else{
							return (val > 0 && Number(val) < Number(maxHours));
		            	} 
		            }
	        },
	        message: '{0}'
	    };

	/**
	*	method used to validate required fields.
	*/	
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

	/**
	*	method used to remove time sheet .
	*/	
	self.removeTimeSheet = function(index){
		var timesheet = ko.toJS(index);
		YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
			Y.io("/ems-web/ems/timesheet/remove?id="+timesheet.id, {
				method: 'GET',
				on: {
				success: function (id, o) {
				self.timeSheet.remove(index);
				validateTimeSheet.errors = ko.validation.group(self.timeSheet,{deep: true});
				if(self.timeSheet().length == 0 || self.timeSheet().length == readOnlyLength){
					self.addTimeSheet();
					}
				}
			}
			});	
		});
	};

	/**
	*	method used to show calendar.
	*/	
	self.showCalendar = function(data, event){
		var data = ko.toJS(data);
		popupCalendar(event.target,data.indexValue);
			
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
		self.projectArray.push(new projectDropDown(projectList[i]['projectId'],projectList[i]['projectName']));
	}
}
// calendar implementation
var calenderObj = null;
var outerCalWidgetContainerId = '#nav-calendar';
var calWidgetContainerId = '#cal-widget-container';
var outerCalWidgetContainerDiv = null;
var currDateBxId = null;
var futureDate = new Date();
futureDate.setHours(13);
var cutoff_date = userInfo.timeSheetCutoffInd;
var cal_cuttoff = 	new Date(''+cutoff_date.split('-')[0].substring(2),cutoff_date.split('-')[1],cutoff_date.split('-')[2].substring(0,2));
cal_cuttoff.setFullYear(cutoff_date.split('-')[0]);
cal_cuttoff.setMonth(cutoff_date.split('-')[1] - 1);

/**
*	method used to calendar change its position with respect to field  .
*/	
function popupCalendar(imgEl, callbackOrId) {
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
				 if(currDateBxId > readOnlyLength -1 ){
				 	isCal = true;
				 }
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
	var node = '#date-'+callbackOrId+'';
	var xy = Y.one(node).getXY();
	xy[0] = xy[0] - 90;
	xy[1] = xy[1] + 25;
	outerCalWidgetContainerDiv.setXY(xy);
		
});
}

/**
*	method used to assign date value to respective fields.
*/	
function selectionChangeCallback(date) {
	self.timeSheet()[currDateBxId].date(date);
}

/**
*	method used to calendar create calendar per page.
*/
function returnCalendar(){
	if(calendarExistsInd){
		return;
		}
	calendarExistsInd = true;
	YUI().use('calendar', 'datatype-date','node',  function(Y) {
		Y.CalendarBase.CONTENT_TEMPLATE = Y.CalendarBase.ONE_PANE_TEMPLATE;

	    calenderObj = new Y.Calendar({
	      contentBox: "#mycalendar",
	      width:'350px',
	      showPrevMonth: true,
	      showNextMonth: true,
	      date: new Date()}).set('customRenderer', {
              filterFunction: function (cellDate, cellNode) {
	    	  if (cellDate < cal_cuttoff) {
                  cellNode.addClass('calendarDiasabled');
              }
              if (cellDate > futureDate) {
                  cellNode.addClass('calendarDiasabled');
              }
          },
          rules: {
              all: 'all'
          }
      }).render(),
      myCalendarCanBeSelected = calenderObj._canBeSelected;
	    calenderObj._canBeSelected = function (oDate) {
          if (oDate > futureDate) {
              return false;
          }
          return myCalendarCanBeSelected.call(calenderObj, oDate);
      };
	    
	    // Get a reference to Y.DataType.Date
	    var dtdate = Y.DataType.Date;

	    // Listen to calendar's selectionChange event.
	    calenderObj.on("selectionChange", function (ev) {

	      var newDates = ev.newSelection;
		    if (newDates.length > 0) {
		    	var selDate = newDates[0];
		    	
				var dStr = selDate.getDate();
				var mStr = (selDate.getMonth() + 1);
				var yStr = selDate.getFullYear();
				
				if (dStr < 10)  dStr = '0' + dStr;
				if (mStr < 10)  mStr = '0' + mStr;
				// Date string
				var dateStr = dStr + "-" + mStr + "-" + yStr;
					selectionChangeCallback(dateStr);
			    }
	    });
	    calenderObj.on("dateClick", function (ev) {
	    	Y.one('#nav-calendar').setStyle('display','none');
		});
	    return calenderObj;
	});

}
</script>
</body>
</html>
