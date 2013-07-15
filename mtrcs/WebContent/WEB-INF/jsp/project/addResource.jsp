<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
</head>
<body onload='intiKo()'>
<div id="project_container">
	<div id="nav-calendar" class="default_calender calendar-picker">
		<div>
			<div id="mycalendar"></div>
		</div>
	</div>

	<div class="clearfix" style="margin-top: 2%; width: 100%;">
		<div class="float-left" style="margin-left: 2%; width: 25%;">
			<div class="float-left" style="width: 35%;">Project</div>
			<div class="float-left" style="margin-left: 2%; width: 55%;">
				<select style="width: 100%;" name="projectId" data-bind="value: projectId,optionsValue: 'id', optionsText: 'value', options: projectArray,optionsCaption:'Select',
				attr: {'title': $root.setTitle(projectArray(), projectId)}"></select>
				<div id="errorMessage" class="validationMessage" style="text-align: center; display: none; padding-right: 10%;"></div>
			</div>
		</div>
	</div>
	<div class="clearfix" style="margin: 1% 2%;">
		<div data-bind="click:saveResourceDetails" class="normal-button" style="width: 65px; float: left; margin: 5px 0;">Submit</div>
		<div data-bind='click: addresource' class="normal-button" style="width: 105px; float: left; margin: 5px 0 5px 5px;">Add Resource</div>
	</div>

	<div id="successMessage" align="center"
		style="display: none; background: none repeat scroll 0pt 0pt #DFF0D9;; color: #279800; font-size: 12px; padding: 5px; width: 95%; margin-left: 2%;"></div>
	<div id="errorResourceMessage" align="center"
		style="display: none; background: none repeat scroll 0 0 #FFE1D2; color: #FF5C5C; font-size: 12px; padding: 5px; width: 95%; margin-left: 2%;"></div>
	
	<br>
<div align="center" class="assignresource-grid" style="width: 96%; margin: 0 2% 2% 2%;">
	<table cellpadding="0" cellspacing="0" style="margin-top: 0;">
		<thead>
			<tr>
				<th class="empid">Employee Id</th>
				<th class="fname">First Name</th>
				<th class="lname">Last Name</th>
				<th class="role">Role</th>
				<th class="sdate">Start Date</th>
				<th class="edate">End Date</th>
				<th class="billable">Billable</th>
				<th class="location">Location</th>
				<th class="astatus">Active Status</th>
				<th class="apercent">Allocation Percentage</th>
				<th class="normal-options">options</th>
			</tr>
		</thead>
		<tbody style="background-color: white !important;"
			data-bind="visible: resource().length == 0">
			<tr>
				<td colspan="11" style="text-align: center;">
				<span> No Data Found </span></td>
			</tr>
		</tbody>
		<tbody data-bind="foreach: resource">
			<tr>
				<td class="empid">
				    <input type="text" onkeydown="emplyeeAutoComplete(this);" data-bind="value: employeeId,attr: {id:'employeeId-'+$parent.resource.indexOf($data)+'',name: 'resourceList['+$parent.resource.indexOf($data)+'].employee.employeeId'}"
					style="width: 100%;">
				</td>
				<td class="fname">
				    <input type="text" readonly="readonly" data-bind="value: firstName,attr: {id:'employeeFirstName-'+$parent.resource.indexOf($data)+'',name: 'resourceList['+$parent.resource.indexOf($data)+'].employee.firstName'}"
					style="width: 100%;" />
				</td>
				<td class="lname">
				    <input type="text" readonly="readonly" data-bind="value: lastName,attr: {id:'employeeLastName-'+$parent.resource.indexOf($data)+'',name: 'resourceList['+$parent.resource.indexOf($data)+'].employee.lastName'}"
					style="width: 100%;" />
				</td>
				<td class="role">
				    <select data-bind="value: roleId,optionsValue: 'id', optionsText: 'value',options: roleArray, optionsCaption: 'Select',attr: {name: 'resourceList['+$parent.resource.indexOf($data)+'].roleId','title': $root.setTitle(roleArray(), roleId)}"
					style="width: 100%;"></select>
				</td>
				<td class="sdate">
				    <input type="hidden" data-bind="value: indexValue" /> 
				    <input type="text" data-bind="value: startDate,uniqueName: true,attr: {id:'startDate-'+$parent.resource.indexOf($data)+'',name: 'resourceList['+$parent.resource.indexOf($data)+'].startDate'},click: function(data, event) { $root.showCalendar($index , $data, event,'startDate') }"
					readonly=true style="width: 100%;" />
				</td>
				<td class="edate">
				    <input type="text" data-bind="value: endDate,uniqueName: true,attr: {id:'endDate-'+$parent.resource.indexOf($data)+'',name: 'resourceList['+$parent.resource.indexOf($data)+'].endDate'},click: function(data, event) { $root.showCalendar($index , $data, event,'endDate') }"
					readonly=true style="width: 100%;" />
				</td>
				<td class="billable">
				   <select data-bind="options: billableOptions , optionsCaption: 'Select', value: billable,attr: {name: 'resourceList['+$parent.resource.indexOf($data)+'].billable','title': billable}"	style="width: 100%;"></select>
				</td>
				<td class="location">
				   <select data-bind="options: locationOptions , optionsCaption: 'Select', value: location,attr: {name: 'resourceList['+$parent.resource.indexOf($data)+'].currentLocation','title': location}" style="width: 100%;"></select>
				</td>
				<td class="astatus">
				    <input type="text" readonly="readonly" data-bind="value: activeStatus,attr: {name: 'resourceList['+$parent.resource.indexOf($data)+'].activeStatus'}" id="activeStatus" style="width: 100%;" />
				</td>
				<td class="apercent">
				    <input type="hidden" data-bind="value: removeInd"> 
				    <input type="hidden" data-bind="value: projectResourceId,attr: {name: 'resourceList['+$parent.resource.indexOf($data)+'].projectResourceId'}" />
				    <input type="text" data-bind="value: allocation,attr: {name: 'resourceList['+$parent.resource.indexOf($data)+'].allocationPercentage'}" style="width: 100%;" />
				</td>
				<td class="normal-options">
				    <span data-bind="click: $parent.removeresource,attr:{title:'Remove'}" class="link-button" style="margin-left: 1%;" style="width: 100%;">Remove</span>
				    <span data-bind="visible: (removeInd() == 'N'),click: $parent.realeaseResource,attr:{title:'Release'}" class="link-button" style="margin-left: 1%;" style="width: 40px">Release</span>
				</td>
			</tr>
		</tbody>
		
  </table>
  			<div id="panelContent" style="background-color: #DBE4F5;height: 165px; width: 400px; display: none;padding-top: 20px; ">
	  			<div align="center"><b>Release Resource</b></div>
			 		 <div id="releaseDate" style="padding-top: 20px;margin-left: -100px;">
			  			<input id='releaseDate-0' type='text' onclick = "popupCalendar('releaseDate-','0')" readonly="readonly">
			 		 </div>
			     <div id='errorDateMessage' class='validationMessage' style='text-align: center; display: none; padding-right: 10%;'></div>
  			</div>
</div>
</div>
<script type="text/javascript">
ko.validation.configure({
    registerExtenders: true,
    messagesOnModified: true,
    insertMessages: true,
    parseInputAttributes: true,
    messageTemplate: null
});

var self = new AppViewModel();
var projectList = ${projects};
var roleList = ${employeeRoles};
var projectStartdate ='';
var projectEnddate ='';
var calenderID = '';
var calendarExistsInd = false;
var resourceDBDate='';
var panel = null;

//Initializing the resource info
var resourceinfo ={
    employee :{employeeId : '',
			   firstName : '',
			   lastName : ''
    },
	roleId:'',
	roleName :'',
	startDate :'',
	endDate:'',
    billable:'',
	currentLocation:'',
	activeStatus:'Assigned',
	allocationPercentage:'',
	projectResourceId:'-1',
	removeInd:''
};

var validateResources = {}; 
AppViewModel.errors = ko.validation.group(AppViewModel);

function AppViewModel() {
		var self = this;
		self.resource = ko.observableArray([]);
		self.projectArray =ko.observableArray([]);
		self.projectId = ko.observable();
				
		/**
		*  If you select the project in drop down list should be populated with existing report details in table and 
		*  then show details in selected project. 
		*/			
	    self.projectId.subscribe(function (projectId){
	    	textId=null;
			YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
			Y.one("#successMessage").setStyle("display","none");
			Y.one("#errorResourceMessage").setStyle("display","none");
			Y.one("#errorMessage").setStyle("display","none");
			Y.one("#loadingImage").setStyle("display","block");
			var project=self.projectId();
				if(project!= undefined){
					Y.io("/ems-web/ems/project/getResources?projectId="+self.projectId()+"&sourceFrom=resource", {
					method: 'GET',
					on: {
						success: function (id, o) {
						var response = Y.JSON.parse(o.responseText);
						self.resource.removeAll();
						projectStartdate = response.projectStartdate;
						projectEnddate = response.projectEnddate;
							if(response.resources != undefined){
								var resource = response.resources;
								for(var i in resource){								
									self.addExistingresource(i,resource[i]);
									validateResources.errors = ko.validation.group(self.resource,{deep: true});
									}
								}
							self.addresource();
							Y.one("#loadingImage").setStyle("display","none");
							}
						}
					});	
				}
			else {
				self.resource.removeAll();
				Y.one("#loadingImage").setStyle("display","none");
			}
		});
	});
		
	var resourceFormValues = '';
	self.constructQueryString = function() {          
		resourceFormValues = resourceFormValues+'&projectId='+self.projectId();
		for(var i = 0 ; i < self.resource().length ; i++) {
			resourceFormValues = resourceFormValues
			+'&resourceList%5B'+i+'%5D.'+''+'employee.employeeId'+'='+self.resource()[i].employeeId()+
			'&resourceList%5B'+i+'%5D.'+''+'employee.firstName'+'='+self.resource()[i].firstName()+
			'&resourceList%5B'+i+'%5D.'+''+'employee.lastName'+'='+self.resource()[i].lastName()+
			'&resourceList%5B'+i+'%5D.'+''+'roleId'+'='+self.resource()[i].roleId()+
			'&resourceList%5B'+i+'%5D.'+''+'startDate'+'='+self.resource()[i].startDate()+
			'&resourceList%5B'+i+'%5D.'+''+'endDate'+'='+self.resource()[i].endDate()+
			'&resourceList%5B'+i+'%5D.'+''+'billable'+'='+self.resource()[i].billable()+
			'&resourceList%5B'+i+'%5D.'+''+'currentLocation'+'='+self.resource()[i].location()+
			'&resourceList%5B'+i+'%5D.'+''+'activeStatus'+'='+self.resource()[i].activeStatus()+
			'&resourceList%5B'+i+'%5D.'+''+'projectResourceId'+'='+self.resource()[i].projectResourceId()+
			'&resourceList%5B'+i+'%5D.'+''+'allocationPercentage'+'='+self.resource()[i].allocation();
		}
		return resourceFormValues;
	};
	
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
	
	/**
	* If you click the submit buttion all the value stored in data base using this function.
	*/	
	self.saveResourceDetails = function () {
		var project=self.projectId();
		if(project!= undefined){
			if (validateResources.errors() == '') {
				var resourceInfo = "";
				resourceInfo = self.constructQueryString();	
				YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y) {
					var cfg = {
						method: 'POST',
						data: resourceInfo,
						on: {
							success: function (id, o) {
								if (o.responseText != null) {
									document.location.href = '/ems-web/ems/project/viewProjectList';
								}
							}
						}
					};
					Y.io("/ems-web/ems/project/saveResources", cfg);
				});
			}
			else {
				validateResources.errors.showAllMessages();            
			}
		} 
		else {
			YUI().use('event', 'node', function (Y) {
			Y.one("#errorMessage").setHTML('Please Select Project').setStyle("display","block");
			Y.one("#loadingImage").setStyle("display","none"); 
		 });
		}
	};
	
	/**
	* This function used to get data base value map in add new resource and then show in table
	*/
	function addNewResource(resourceinfo) {
		var self = this;
		self.indexValue = ko.observable();
		self.employeeArray=ko.observableArray([]);
		self.employeeId = ko.observable(resourceinfo.employee.employeeId).extend({ required: 'EmployeeId'});
		self.firstName = ko.observable(resourceinfo.employee.firstName).extend({ required: 'First Name'});
		self.lastName = ko.observable(resourceinfo.employee.lastName).extend({ required: 'Last Name'});
		self.roleArray = ko.observableArray([]);
		self.roleId = ko.observable(resourceinfo.roleId).extend({ required: 'Role'});
		self.startDate = ko.observable(resourceinfo.startDate).extend({required: 'Start Date',validateStartEndDate:true,startDateValidation: projectStartdate});
		self.endDate = ko.observable(resourceinfo.endDate).extend({required: 'End Date',validateEndStartDate:true,endDateValidation: projectEnddate});
		self.billableOptions = ko.observableArray(['Yes', 'No']);
		self.locationOptions = ko.observableArray(['Onsite', 'Offshore']);
		self.billable = ko.observable(resourceinfo.billable).extend({ required: 'Billable'});
		self.location = ko.observable(resourceinfo.currentLocation).extend({ required: 'Location'});
		self.activeStatus=ko.observable(resourceinfo.activeStatus);
			if(resourceinfo.sumOfAllocationPercent != undefined){
				var remainingAllocationPercent = (100 + parseInt(resourceinfo.allocationPercentage))  - resourceinfo.sumOfAllocationPercent;
				self.allocation = ko.observable(resourceinfo.allocationPercentage).extend({ required: 'Allocation Percentage',number: true,step: 1, min: 1, max: remainingAllocationPercent});
			}else{
				self.allocation = ko.observable(resourceinfo.allocationPercentage).extend({ required: 'Allocation Percentage',number: true,step: 1, min: 1});
			}
		self.projectResourceId = ko.observable(resourceinfo.projectResourceId);
		self.removeInd = ko.observable(resourceinfo.removeInd);
		self.releaseDate=ko.observable();
		ko.mapping.fromJS(roleList, {}, self.roleArray);  
		self.startDate.subscribe(function (startDate){
		 self.startDate.extend({resourceStartDate: resourceDBDate});
		});  
	};
	
	/**
	* This function is used to get the existing value
	*/
	self.addExistingresource = function(index,resourceinfo){
		self.resource.push(new addNewResource(resourceinfo));
		self.resource()[index].indexValue(index); 
		validateResources.errors = ko.validation.group(self.resource,{deep: true});
	};
	
	/**
	* This function is used to get new row in the table
	*/	
	self.addresource = function(){
		YUI().use('event', 'node', function (Y) {
			Y.one("#successMessage").setStyle("display","none");
			Y.one("#errorResourceMessage").setStyle("display","none");
		});
		self.resource.push(new addNewResource(resourceinfo));
		var i = self.resource().length - 1;
		self.resource()[i].indexValue(i);
		validateResources.errors = ko.validation.group(self.resource,{deep: true});
		validateResources.errors.showAllMessages(false);
	};
	
	/**
	* This function is used to show calendar
	*/		
	self.showCalendar = function(index,data,event,inputBox){
		popupCalendar(inputBox+'-'+index(),index());
	};
	
    /**
	* This method is used to check Date Should be greater than or equal to project start date
	*/
	ko.validation.rules['startDateValidation'] = {
		validator: function (val, required) {
			if(val != '' && val != undefined){
				var to_date = val;
				var to_dated = new Date('' + to_date.split('-')[2].substring(2), to_date.split('-')[1], to_date.split('-')[0]);
				to_dated.setFullYear(to_date.split('-')[2]);
				to_dated.setMonth(to_date.split('-')[1] - 1);
				to_dated.setDate(to_date.split('-')[0]);
				
				var from_date = new Date('' + projectStartdate.split('-')[2].substring(2),projectStartdate.split('-')[1], projectStartdate.split('-')[0]);
				from_date.setFullYear(projectStartdate.split('-')[2]);
				from_date.setMonth(projectStartdate.split('-')[1] - 1);
				from_date.setDate(projectStartdate.split('-')[0]);
				
				if (from_date > to_dated) {
					return false;
				}else{
					return true;
				}
			}
		},
		message: 'Date Should be greater than or equal to project start date ('+'{0}'+')'
	};
	
	/**
	* This method is used to check Date should be less than or equal to project end Date
	*/
	ko.validation.rules['endDateValidation'] = {
		validator: function (val, required) {
			if(val != '' && val != undefined){
				return validateEnd(val);	
			}
		},
		message: 'Date should be less than or equal to project end Date ('+'{0}'+')'
	};
	
	/**
	* This method is used to check Start date should be greater than previously released date
	*/
	ko.validation.rules['resourceStartDate'] = {
		validator: function (val, required) {
			if(resourceDBDate != '' && resourceDBDate != undefined){
				var resourceDBDated = new Date('' + resourceDBDate.split('-')[2].substring(2),resourceDBDate.split('-')[1],resourceDBDate.split('-')[0]);
				resourceDBDated.setFullYear(resourceDBDate.split('-')[2]);
				resourceDBDated.setMonth(resourceDBDate.split('-')[1] - 1);
				resourceDBDated.setDate(resourceDBDate.split('-')[0]);
				
				var startDBDate=val;		
				var startDBDated = new Date('' +startDBDate.split('-')[2].substring(2),startDBDate.split('-')[1],startDBDate.split('-')[0]);
				startDBDated.setFullYear(startDBDate.split('-')[2]);
				startDBDated.setMonth(startDBDate.split('-')[1] - 1);
				startDBDated.setDate(startDBDate.split('-')[0]);
						
				if ( resourceDBDated < startDBDated ) {
					return true;
				}else{
					return false; 
				}
			}
			else{
			 return true;
			}
		},
		message: 'Start date should be greater than previously released date ('+'{0}'+')'
	};
	
    /**
	* This method is used to check empty field
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
		message: '{0}' +' is Required.'
	};
	
	/**
	* This method is used to check Start date should be less than end date.
	*/
	ko.validation.rules['validateStartEndDate'] = {
		validator: function (date, status) {
		if(textId != null){
		var id = textId.split('-')[1];
		if (textId.indexOf('startDate-') != -1) {
			if (self.resource()[id].endDate() != undefined && self.resource()[id].endDate() != '') {
				var to_date = self.resource()[id].endDate();
				
				var to_dated = new Date('' + to_date.split('-')[2].substring(2), to_date.split('-')[1], to_date.split('-')[0]);
				to_dated.setFullYear(to_date.split('-')[2]);
				to_dated.setMonth(to_date.split('-')[1] - 1);
				to_dated.setDate(to_date.split('-')[0]);
				
				var from_date = new Date('' + date.split('-')[2].substring(2),date.split('-')[1], date.split('-')[0]);
				from_date.setFullYear(date.split('-')[2]);
				from_date.setMonth(date.split('-')[1] - 1);
				from_date.setDate(date.split('-')[0]);
				
				if (from_date < to_dated) {
					return true;
				}else{
					return false;
				}
			} else {
				return true;
			}
		}
		}else{
			return true;
		}
		return true;
		},
		message:  'Start date should be less than end date.'
	};
	
	/**
	* This method is used to check End date should be greater than start date.
	*/
	ko.validation.rules['validateEndStartDate'] = {
	   validator: function (date, status) {
	   if(textId != null){
		var id = textId.split('-')[1];
			if (textId.indexOf('endDate-') != -1) {
				if (self.resource()[id].startDate() != undefined && self.resource()[id].startDate() != '') {
					var from_date = self.resource()[id].startDate();	
					var from_dated = new Date('' + from_date.split('-')[2].substring(2), from_date.split('-')[1], from_date.split('-')[0]);
						from_dated.setFullYear(from_date.split('-')[2]);
						from_dated.setMonth(from_date.split('-')[1] - 1);
						from_dated.setDate(from_date.split('-')[0]);
						
					var to_date = new Date('' + date.split('-')[2].substring(2),date.split('-')[1], date.split('-')[0]);
						to_date.setFullYear(date.split('-')[2]);
						to_date.setMonth(date.split('-')[1] - 1);
						to_date.setDate(date.split('-')[0]);
					
							if (from_dated < to_date) {
								return true;		
							}else{
								return false;
							}
					} else {
						return true;
					}
			}
			}else{
				return true;
			}
		return true;
		},
		message:  'End date should be greater than start date.'
	};

   /**
	* This method is used to check Decimal number not allowed.
	*/
	ko.validation.rules['step'] = {
			validator: function (val, step) {
				return val === "" || val === undefined || val === null || (val * 100) % (step * 100) === 0;
			},
			message:  'Decimal number not allowed'
		};
	
	/**
	* This method is used to check Resource already exists for this project.
	*/	
	ko.validation.rules['validateDuplicateResource'] = {
		validator: function (val, step) {
			var count = 0;
			if(val != "" && val != undefined && val != null){
				for(i=0 ;i<self.resource().length; i++){
					if(self.resource()[i].employeeId() == val && step != i){
						count++;
					}
				}
				if(count ==0 ){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return true;
			}          
		},
		message:  'Resource already exists for this project.'
	};  
	ko.validation.registerExtenders();
	
	/**
	* This function is used to remove the record
	*/
	self.removeresource = function(index){
		var resource = ko.toJS(index);
		var EmployeeId=resource.employeeId;
		var removeInd=resource.removeInd;
		YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
			Y.one("#successMessage").setStyle("display","none");
			Y.one('#errorResourceMessage').setStyle("display","none");
			if(resource.projectResourceId != '-1') {
				if(removeInd=='Y'){
					var answer = confirm("Please confirm to delete Resource");
					if (!answer) {
						return false;
					}
					Y.io("/ems-web/ems/project/removeResource?projectResourceId="+resource.projectResourceId, {
						method: 'GET',
						on: {
							success: function (id, o) {
								textId = null;
								self.resource.remove(index);
								validateResources.errors = ko.validation.group(self.resource,{deep: true});
								Y.one("#successMessage").setHTML("<b>"+EmployeeId+"</b> removed successfully ").setStyle("display","block");
								Y.one('#errorResourceMessage').setStyle("display","none");
							}
						}
					});	
				}
				else{
					Y.one("#successMessage").setStyle("display","none");
					Y.one("#errorResourceMessage").setHTML("Unable to delete. Timesheet exists for <b>"+EmployeeId+"</b> resource").setStyle("display","block");
				}
			}
			else {
				Y.one("#successMessage").setStyle("display","none");
				Y.one('#errorResourceMessage').setStyle("display","none");
				textId = null;
				self.resource.remove(index);
				validateResources.errors = ko.validation.group(self.resource,{deep: true});
			}
		}); 
	};  
	
	/**
	* This function is used to release the record
	*/	
	self.realeaseResource= function(index){
		var resource = ko.toJS(index);
		var currentDate=new Date();
		var releaseend=resource.endDate
	    var EmployeeId=resource.employeeId;
	    var projectResourceId=resource.projectResourceId;
	    var startDate=resource.startDate;
		var answer = confirm("Please confirm to Release Resource");
			if (!answer) {
					return false;
			}
	    YUI().use('io-base', 'json-parse', 'json-stringify','node',"panel", function(Y) {
	    Y.one('#errorDateMessage').setStyle("display","none");
	    Y.one('#releaseDate').setHTML("Release Date : <input id='releaseDate-"+resource.indexValue+"' type='text' onclick = popupCalendar('releaseDate-"+resource.indexValue+"',"+resource.indexValue+")  readonly='readonly'>");
	    Y.one('#panelContent').setStyle("display","block");
	    if(panel == null){
			 panel = new Y.Panel({
				        		 srcNode      : '#panelContent',
						         width        : 400,
						         height       : 150,
						         zIndex       : 5,
						         centered     : true,
						         modal        : true,
						         visible      : false,
						         render       : true,
						         plugins      : [Y.Plugin.Drag],
						    	 buttons	  : false
  	 					 });
  	 					panel.addButton({
    				    value  : 'Release',
    				    section: Y.WidgetStdMod.FOOTER,
      					action : function (e) {
      					        e.preventDefault();
      					        var releaseDate=self.resource()[resource.indexValue].releaseDate();
      					           if(releaseDate != '' && releaseDate != undefined){
		      					        if(releaseCheck()){
		      					       		self.save(projectResourceId,startDate,releaseDate,EmployeeId,index);
		      					        }else{
		      					  	        Y.one('#errorDateMessage').setHTML("Release date should be greater than Start Date ("+startDate+")");
											Y.one('#errorDateMessage').setStyle("display","block");
		      					        }
	      					        }else{
	      					        		Y.one('#errorDateMessage').setHTML("Release date should not be Empty");
											Y.one('#errorDateMessage').setStyle("display","block");
	      					        }
       						    }
  						  });
  						panel.addButton({
    				    value  : 'Cancel',
    				    section: Y.WidgetStdMod.FOOTER,
      					action : function (e) {
      					        e.preventDefault();
      					        panel.hide();
       						 }
  						  }); 
    			    	panel.show();
    	}else{
    				    panel.show();
    	}
                      
		}); 
	  };
	self.save = function(projectResourceId,startDate,releaseDate,EmployeeId,index) {
		YUI().use('io-base', 'json-parse', 'json-stringify','node',"panel", function(Y) {
			if(validateEnd(releaseDate)){
				Y.one('#errorDateMessage').setStyle("display","none");
				var url ='/ems-web/ems/project/resourceRealease?projectResourceId='+projectResourceId+'&startDate='+startDate+'&endDate='+releaseDate;
				if(projectResourceId != '-1') {
					Y.io(url, {
						method: 'POST',
						on: {
							success: function (id, o) {
							    textId = null;
								self.resource.remove(index);
								validateResources.errors = ko.validation.group(self.resource,{deep: true});
								panel.hide();
								Y.one("#successMessage").setHTML("<b>"+EmployeeId+"</b> released successfully ").setStyle("display","block");
								Y.one('#errorResourceMessage').setStyle("display","none");
								Y.one('#errorDateMessage').setStyle("display","none");
							}
						} 
					});								
				}else {
				}
			}else{
				Y.one('#errorDateMessage').setHTML("Date should be less than or equal to project end Date ("+projectEnddate+")");
				Y.one('#errorDateMessage').setStyle("display","block");
			}
		});
	};  
	
	/**
	* This function is used to validate start should be less than end date
	*/	
	function validateEnd(val) {
	   var to_date = val;
	    if(val != '' && val != undefined){
			var to_dated = new Date('' + to_date.split('-')[2].substring(2), to_date.split('-')[1], to_date.split('-')[0]);
				to_dated.setFullYear(to_date.split('-')[2]);
				to_dated.setMonth(to_date.split('-')[1] - 1);
				to_dated.setDate(to_date.split('-')[0]);
					
			var from_date = new Date('' + projectEnddate.split('-')[2].substring(2),projectStartdate.split('-')[1], projectEnddate.split('-')[0]);
				from_date.setFullYear(projectEnddate.split('-')[2]);
				from_date.setMonth(projectEnddate.split('-')[1] - 1);
				from_date.setDate(projectEnddate.split('-')[0]);
					
				if ( to_dated > from_date) {
						return false;
				}else{
						return true;
				}
			}	
	      }
	      
   /**
	* This function is used to validate release should be greater than end date
	*/    
	  function releaseCheck(){
		if(textId != null){
			var id = textId.split('-')[1];
			if (textId.indexOf('releaseDate-') != -1) {
				if (self.resource()[id].startDate() != undefined && self.resource()[id].startDate() != '') {
					var from_date = self.resource()[id].startDate();

					var from_dated = new Date('' + from_date.split('-')[2].substring(2), from_date.split('-')[1], from_date.split('-')[0]);
						from_dated.setFullYear(from_date.split('-')[2]);
						from_dated.setMonth(from_date.split('-')[1] - 1);
						from_dated.setDate(from_date.split('-')[0]);
						
					var date =self.resource()[id].releaseDate();
					var to_date = new Date('' + date.split('-')[2].substring(2),date.split('-')[1], date.split('-')[0]);
						to_date.setFullYear(date.split('-')[2]);
						to_date.setMonth(date.split('-')[1] - 1);
						to_date.setDate(date.split('-')[0]);
						
					if (from_dated < to_date) {
								return true;		
					}else{
								return false;
					}
				} else {
					return true;
				}
			}
			}else{
				return true;
			}
	    }
}
function intiKo() {
	ko.applyBindings(self);
	ko.mapping.fromJS(projectList, {}, self.projectArray);
}

/*
 Script for calendar 
*/
var calenderObj = null;
var outerCalWidgetContainerId = '#nav-calendar';
var calWidgetContainerId = '#cal-widget-container';
var outerCalWidgetContainerDiv = null;
var currDateBxId = null;
var textId = null;
var clickId = null;

function popupCalendar(imgEl, callbackOrId) {
		YUI().use('node','node-core','io-base','json-parse','json-stringify','event', function(Y){
		Y.one('#nav-calendar').setStyle('display','block');
		outerCalWidgetContainerDiv = Y.one(outerCalWidgetContainerId);
		if(calenderObj == null){
		calenderObj =  returnCalendar();
		Y.delegate('click', function(e) {
				 var clickNode = e.target;
				 clickId = clickNode.get('id');
				 var isCalIcon, isCal = false;
				 if(clickId.indexOf('startDate-') != -1 || clickId.indexOf('endDate-') != -1 || clickId.indexOf('releaseDate-') != -1){
					 currDateBxId = clickId.split('-')[1];
					 textId = clickId;
					 isCal = true;
				 }		
				 var clazz = clickNode.getAttribute('class');		 
		         if(clickNode == outerCalWidgetContainerDiv 
		        		 || outerCalWidgetContainerDiv.contains(clickNode)) {
		        	 isCal = true;
		         }
				if(clazz.indexOf('yui3-calendar-day-selected')>= 0) { 
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
			}
			outerCalWidgetContainerDiv.setStyle('display', 'block');
			
	        var node = '#'+imgEl;
	        var xy = Y.one(node).getXY();
	        xy[0] = xy[0] - 90;
			xy[1] = xy[1] + 25;
			outerCalWidgetContainerDiv.setXY(xy);	
		});
}

function selectionChangeCallback(date) {
 	var id = textId.split('-')[1];
	if (textId.indexOf('startDate-') != -1) {
		self.resource()[id].startDate(date);
	}
	if (textId.indexOf('endDate-') != -1) {
		self.resource()[id].endDate(date);
	}
	if (textId.indexOf('releaseDate-') != -1) {
		 self.resource()[id].releaseDate(date);
	     YUI().use('node',function(Y){
	     Y.one('#releaseDate-'+id).set("value",date);
	  });
	} 
}

function returnCalendar(){
	if(calendarExistsInd){
		return;
		}
	calendarExistsInd = true;
	YUI().use('calendar','datatype-date','node',  function(Y) {
		Y.CalendarBase.CONTENT_TEMPLATE = Y.CalendarBase.ONE_PANE_TEMPLATE;
	    calenderObj = new Y.Calendar({
	      contentBox: "#mycalendar",
	      width:'350px',
	      showPrevMonth: true,
	      showNextMonth: true,
	      date: new Date()}).render();

	    var dtdate = Y.DataType.Date;
	    calenderObj.on("selectionChange", function (ev) {
	      var newDates = ev.newSelection;
		    if (newDates.length > 0) {
		    	var selDate = newDates[0];		    	
				var dStr = selDate.getDate();
				var mStr = (selDate.getMonth() + 1);
				var yStr = selDate.getFullYear();				
				if (dStr < 10)  dStr = '0' + dStr;
				if (mStr < 10)  mStr = '0' + mStr;
				var dateStr = dStr + "-" + mStr + "-" +  yStr;
					selectionChangeCallback(dateStr);
			    }
	    });
	    calenderObj.on("dateClick", function (ev) {
	    	Y.one('#nav-calendar').setStyle('display','none');
		});		
	    return calenderObj;
	});
}

emplyeeAutoComplete = function(object){
	YUI().use('node','node-core','io-base','json-parse','json-stringify','event', function(Y){
	var textCallback =  function (result) {
		if(Y.Lang.isValue(result)){
			var cellHTML =  '';
			if(object.id.indexOf('employeeId') >= 0)
				if(result.employeeId != undefined){
					cellHTML =  result.employeeId;
				}
			return cellHTML;
		}
	};
	var beforeSearchCallback =  function (result) {
		var id =  object.id.replace('employeeId-','');
		self.resource()[id].lastName("");
		self.resource()[id].firstName("");
		validateResources.errors.showAllMessages(false);
	};
	var selectCallback =  function (ev) {
		resourceDBDate='';
		if(Y.Lang.isValue(ev.result)){//ev.result.text
			var resultObj = ev.result.raw;
			var count = 0;
			var id =  object.id.replace('employeeId-','');
			
				if(resultObj.employeeId != undefined){
					self.resource()[id].employeeId(resultObj.employeeId);
				}
				if(resultObj.firstName != undefined){
					self.resource()[id].firstName(resultObj.firstName);
				}
				if(resultObj.lastName != undefined){
					self.resource()[id].lastName(resultObj.lastName);
				}
				if(resultObj.sumOfAllocationPercent != undefined ){
					var remainingAllocationPercent = 100 - resultObj.sumOfAllocationPercent;
					self.resource()[id].allocation.extend({max: remainingAllocationPercent});
				}
				if(resultObj.projectLastReleasedDate != undefined){
					resourceDBDate=resultObj.projectLastReleasedDate;
				}
		}
    };
    var afterSelectCallback = function (){
    	var empId = Y.one('#'+object.id).get('value');
    	var id =  object.id.replace('employeeId-','');
    	var count = 0;
		for(i=0 ; i<self.resource().length; i++){
			if(self.resource()[i].employeeId() == empId && id != i){
				count++;
			}
		}
		if(count > 0){
			self.resource()[id].employeeId.extend({ validateDuplicateResource: id});
		}
    };
    var acUrl = "/ems-web/ems/project/employeeLookup?columnType=employee_id"+"&projectId="+self.projectId()+"&employeeId=";
    Y.use('autocomplete', 'autocomplete-highlighters', function(Y) {
	   var input = AutoComplete({
    	   inputId: object.id,
    	   resultsLocator:'employees',
    	   queryUrl: acUrl,
    	   maxResults: 10,
    	   textLocatorCallback:textCallback,
    	   onSelectCallback: selectCallback,
    	   afterSelectCallback: afterSelectCallback,
    	   beforeSearchCallback: beforeSearchCallback
    	 });
	});
	});
};
</script>
</body>
</html>