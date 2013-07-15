<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Project Information</title>
</head>
<body>
<div id="nav-calendar" class="default_calender calendar-picker"> <!-- You need this skin class -->
  <div>
     <div id="mycalendar"></div>
  </div>
</div>

<div id="addprojectDetails" class="yui3-skin-sam">
    <ul>
        <li><a href="#projectInformation">Project Information</a></li>
        <li id="handoverTab" data-bind="click : addExistingHandOverInfo"><a href="#handoverInformation">Handover Information</a></li>
        <li id="resourceTab" style="visibility:  hidden;"><a href="#resourceInformation">Resource Information</a></li>
    </ul>

<div>
<div id="projectInformation" >
<f:form modelAttribute="projectInfo" id="projectInformationForm" name="projectInformationForm">
<!--<div style="display: none; margin-left: 8px;" id="currentProjectId">-->
<!--Project ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:-->
<!--<span data-bind="text: project.projectId"  id="projectId" style="width: 15%;"></span>-->
<!--</div>-->
<table border="0" cellspacing="0" cellpadding="0" style="width:480px;">
<tr data-bind="visible: !project.projectCode() && project.id()" id="currentProjectId">
<td style="width:30%"><label>Project ID </label></td>
<td style="width:50%"><input type="text" data-bind="value: project.projectId"  id="projectId" style="width: 80%;"/>
</td>
<td>&nbsp;</td>
</tr>
<tr data-bind="visible: project.projectCode()"  id="currentProjectCde">
<td style="width:30%"><label>Project ID </label></td>
<td style="width:50%"><input type="text" data-bind="value: project.projectCode"  id="projectCode" style="width: 80%;"/>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:30%"><label>Project Name <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></label></td>
<td style="width:50%"><input type="text" data-bind="value: project.name" id="projectName" name="projectName" style="width: 80%;"/>
<input type="hidden" data-bind="value: project.id" name="projectId"/>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:30%"><label>Project Description <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></label></td>
<td style="width:50%"><textarea data-bind="value:project.description" id="projectdescription" name="projectdescription" style="width: 80%; height: 60px;"></textarea>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:30%"><label>Project Type <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></label></td>
<td style="width:50%"><select data-bind="options: project.types , optionsText: 'value',optionsValue:'id',optionsCaption: 'Select',value: project.type,
				attr: {'title': $root.setTitle(project.types(), project.type)}" id="projectType" name="projectType" style="width: 80%;"></select>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:30%"><label>Customer <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></label> </td>
<td style="width:50%"> <select data-bind="options: project.customers,optionsText: 'value',optionsValue:'id',optionsCaption: 'Select',value:project.selectecustomer,
		attr: {'title': $root.setTitleFromServer(project.customers(), project.selectecustomer)}" id="customerDropdown" name="customerId" style="width: 80%;"></select>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:32%"><label>Development Platform <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></label></td>
<td style="width:50%"><textarea data-bind="value:project.platform" id="platform"  name="platform" style="width: 80%; height: 60px;"></textarea>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:30%"><label>Primary COE <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></label></td>
<td style="width:50%"><select data-bind="options: project.coes, optionsText: 'value',optionsValue:'id',optionsCaption: 'Select',value: project.selectedcoe,
		attr: {'title': $root.setTitleFromServer(project.coes(), project.selectedcoe)}" id="primaryCOE" name="primaryCOE" style="width: 80%;"></select>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:30%"><label>Estimated Effort <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></td>
<td style="width:50%"><input type="text" data-bind="value: project.effort" id="effort" name="effort" style="width: 80%;">
<input type="hidden" readonly=true data-bind= " value: project.unit" name="unit" style="width: 50%">
</td>
<td><span style="margin-left: -50%;">Person Days</span></td>
</tr>
<tr>
<td style="width:30%"><label>Planned Start Date <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></label></td>
<td style="width:50%"><input type="text" readonly=true type="text" data-bind="value: project.startDate,click: function(){ showCalendar('date-start')},attr: {id:'date-start'}"  name="startDate"  style="width: 80%;"/>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:30%"><label>Planned End Date <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></label> </td>
<td style="width:50%"><input type="text" readonly=true data-bind="value: project.endDate,click: function(){ showCalendar('date-end')},attr: {id:'date-end'}" name="endDate"  style="width: 80%;"/>
<span id="dateValid" style="display: none;" class="validationMessage">start date should be lesser than end date</span>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:30%"><label>Approval by <span data-bind="visible: pageType != 'view'" class="mandatory">*</span></label></td>
<td style="width:50%"><select data-bind="options: project.approverList,optionsCaption: 'Select', optionsText: 'value',optionsValue:'id', value:project.approver,
		attr: {'title': $root.setTitle(project.approverList(), project.approver)}" id="approver" name="approver" style="width: 80%;"></select>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td style="width:30%"><label>Approval Status</label> </td>
<td style="width:50%"><input type="text" readonly=true data-bind="value: project.approvalStatus" style="width: 80%;"/>
</td>
<td>&nbsp;</td>
</tr>
<tr id=projectStatus>
<td style="width:30%"><label>Project Status</label> </td>
<td style="width:50%"><select data-bind="options: project.projectStatusList, optionsText: 'value',optionsValue:'id', value: project.projectStatus,
		attr: {'title': $root.setTitle(project.projectStatusList(), project.projectStatus)}" id="projectStatusVal" name="projectStatus" style="width: 80%;"></select>
</td>
<td>&nbsp;</td>
</tr>
<tr id="closureDate" data-bind="visible: project.projectStatus() == 'C'">
<td style="width:30%"><label>Project Closure Date</label> </td>
<td style="width:50%"><input type="text" readonly=true data-bind="visible: project.projectStatus() == 'C', value: project.closureDate,click: function(){ showCalendar('date-closure')},attr: {id:'date-closure'}" name="closureDate"  style="width: 80%;"/>
<span id="closureValid" style="display: none;" class="validationMessage">Closure Date Required</span>
<span id="closure-End-Valid" style="display: none;" class="validationMessage">Closure Date should greater than or equal to end date</span>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>
	<div data-bind='click: saveProjectInfo' id="saveProjectInfo" class="normal-button" style="width:65px; float:left;">Next</div>
	<a href="/ems-web/ems/project/viewProjectList" id="cancelProjectInfo" class="normal-button" style="width:65px; float:left; margin-left:5px;text-decoration:none;">Cancel</a>
		
</td>
<td>&nbsp;</td>
</tr>
</table>
 </f:form>  
</div>
    <div id="handoverInformation" >
        <f:form id="handoverInformationForm" action="/ems-web/ems/project/saveHandOverInfo" 
        modelAttribute="handoverList" name="handoverInformationForm" enctype="multipart/form-data">
        <div id="errorTabMessage" class="validationMessage" style="display: none; margin-left: 25%;">Please fill Project Information and give next Button</div>
         	<div id="addProjectId" class="clearfix" style="font-size:12px;margin: 1%;">
         		<label style="float:left;width:12%;">Project Id</label> 
         		<span style="float:left;" data-bind="visible: !project.projectCode(),text: project.projectId"></span>
         		<span style="float:left;" data-bind="visible: project.projectCode(), text: project.projectCode"></span>
         	</div>
         	<div class="clearfix" style="font-size:12px;margin: 1%;">
            <label style="float:left;width:12%;">Project Name</label><span style="float:left;" data-bind="text: project.name"></span><br>
            </div>
            <div class="clearfix" style="font-size:12px;margin: 1%;">
            <label style="float:left;width:12%;">Project Type</label><span style="float:left;" data-bind="text: project.type"></span><br>
           </div>
            <div id="errormessage" class="validationMessage" style="display: none; margin-left: 30%;">Remarks needed for No and NA Status</div>
            <div id="handoverGrid" class="normal-grid" style="width: 500px; margin-left: 1%;">
		<table width='100%' cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width='25%'>CheckList Item</th>
                <th width='25%'>Status</th>
                <th width='30%'>Remarks</th>
            </tr>
        </thead>
        <tbody data-bind='foreach: checkListArray'>
            <tr>
                <td>
                <input type="hidden" data-bind="value: projectCategory , attr: {name: 'checkLists['+$parent.checkListArray.indexOf($data)+'].projectCategory'}"></input>
                    <input type="hidden" data-bind="value: projectId, attr: {name: 'checkLists['+$parent.checkListArray.indexOf($data)+'].projectId'}"></input>
                    <input type="text" readonly=true style="width: 80%; height: 20px !important;" data-bind="enable: pageType != 'view' ,value: checkListItem, attr: {name: 'checkLists['+$parent.checkListArray.indexOf($data)+'].checkListItem'}">
                    <input type="hidden" data-bind="value: checkListId, attr: {name: 'checkLists['+$parent.checkListArray.indexOf($data)+'].checkListId'}">
                    <input type="hidden" data-bind="value: projectCheckListId , attr: {name: 'checkLists['+$parent.checkListArray.indexOf($data)+'].projectCheckListId'}" ></input> 
                </td>
                <td>
                    <select style="width: 80%; height: 20px !important;" data-bind="enable: pageType != 'view' , options: optionsArray, optionsText: 'value',optionsValue:'id' ,optionsCaption: 'Select', value:checkListStatus, attr: {name: 'checkLists['+$parent.checkListArray.indexOf($data)+'].checkListStatus', id: 'status-'+$parent.checkListArray.indexOf($data)+''}"> </select>
                    <span data-bind="attr: {id: 'status-'+$parent.checkListArray.indexOf($data)+'-err'}" style="display: none; color: red;">status needed</span>
                </td>
                <td class='remarks'>
                    <textarea data-bind="enable: pageType != 'view' ,value: checkListRemarks,attr: {name: 'checkLists['+$parent.checkListArray.indexOf($data)+'].remarks', id: 'remarks-'+$parent.checkListArray.indexOf($data)+''}" style="height: 35px !important;"></textarea>
                     <span class="validationMessage" data-bind="attr: {id: 'remarks-'+$parent.checkListArray.indexOf($data)+'-err'}" style="display: none;">Remarks needed</span>
                </td>
                
            </tr>
        </tbody>
    </table></div>
    <div data-bind='click: addFile ,visible : pageType != "view"' id="addFile" class="normal-button" style="width:65px; margin :2% 0 0 1%; float:left;">Add File</div>
    
    <div id="fileTable" style="clear:both;">
    <table >
    <tbody data-bind='foreach: importFiles'>
		<tr>
			<td><input data-bind="value: inputFile , attr: {name: 'files['+$parent.importFiles.indexOf($data)+']'}" type="file" />
			</td>
			
			<td>
			<div id="removeFile">
                    <a class="link-button" href='#' data-bind='click: $parent.removeFile'>Remove</a>
                    </div>
                </td>
		</tr>
	</table>
	</div>
	<br>
	<div data-bind='visible : documentsArray().length != 0'  id="availableDocument" class="clearfix">
	<b style="font-size:14px;margin-left:1%;width:98%;">Handover Documents</b>
	<table cellpadding="0" cellspacing="0" style="margin-left:1%;">
		<tbody data-bind='foreach: documentsArray'>
			<tr>
			<td>
			<a class="link-button" href='#' data-bind="text: documentName, click: $parent.download"></a>&nbsp;&nbsp;&nbsp;
			</td>
			<td>
			<a class="link-button" href='#' data-bind='visible : pageType != "view" ,click: $parent.removeDocument'>Remove</a>
			</td>
			</tr>
		</tbody>
	</table>
	</div>
	<div class="clearfix">
		<div data-bind='click: saveHandoverInfo' id="saveHandoverInfo" class="normal-button" style="width:65px; margin-top:5px;margin-left:16%; float:left;">Submit</div>
		<a href="/ems-web/ems/project/viewProjectList" id="cancelHandoverInfo" style="width:65px; float:left;  margin:5px 0 5px 5px; text-decoration:none;" class="normal-button">Cancel</a>
	</div>
	</f:form>
	</div>
	<div id="resourceInformation" style="visibility: visible;" class="normal-grid">
        <table  width='100%' cellspacing="0" cellpadding="0" >
          <thead>
          <tr>
                <th>Employee Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Role</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Billable</th>
                <th>Location</th>
                <th>Active Status</th>
                <th>Allocation Percentage</th>
          </tr>
</thead>
<tbody style="background-color: white !important;" data-bind="visible: resourse().length == 0">
         	<tr>
            	<td colspan="10" style="text-align:center;">
            		<span>	No Data Found	</span>
            	</td>
            </tr>
</tbody>
<tbody data-bind="foreach: resourse">
<tr>
       <td>
         <span  data-bind="text: employeeId" name="lastName" style="width: 20%"></span>
        </td>
        <td>
          <span  data-bind="text: firstName" name="firstName"></span>
        </td>
        <td>
            <span data-bind="text: lastName" name="lastName"></span>
        </td>
        <td>
          <span  data-bind="text: roleName" name="lastName"></span>
        </td>
         <td>
            <span data-bind="text: startDate" name="startDate"></span>
         </td>
       
         <td>
            <span data-bind="text: endDate" id="endDate" name="endDate"></span>
          </td>
          
         <td>
            <span data-bind="text: billable" id="billable" name="billable"></span>
        </td>  
        <td>
           <span data-bind="text: location" id="location" name="location"></span>
        </td>
        <td>
            <span data-bind="text: activeStatus" id="activeStatus" name="activeStatus"></span>
       </td>
       <td>
         <span data-bind="text: allocation" id="allocation" name="allocation"></span>
        </td>   
</tr>
</tbody>
</table>      
</div>
</div>
</div>
<script type="text/javascript">
var projectViewModel;
var tabview;
var customerList = ${customerList};
var employeeList = ${employeeList};
var pageType = '${pageType}';
var projectDetail = ${projectDetail};
var checkLists  = ${checkLists};
var existingcheckLists = ${checkListInfos};
var documents = ${documents};
var projectIdRes = '${projectId}';
var categoryRes = '${category}';
var isExists = false;
var presentYear = new Date().getFullYear();
var calendarExistsInd = false;
var projectStartDate = null;

YUI({ filter: 'raw' }).use("yui", "tabview","io-form","datasource-jsonschema",
		"datasource-io","datasource-io", function(Y) {
    tabview = new Y.TabView({srcNode:'#addprojectDetails'});
    tabview.render();
    initKo();
});
YUI().use("node", "io", "dump", "json-parse",function (Y) {
if(pageType == "add"){
	//Y.one('#closureDate').setStyle('display','none');
	//Y.one('#addProjectId').setStyle('display','none');
	Y.one('#projectStatus').setStyle('display','none');
	Y.one('#handoverTab').set('disabled',true);
	Y.one("#handoverTab").setStyle("visibility","hidden");
	Y.one("#availableDocument").setStyle('display','none');
}
if(pageType == "view"){
	Y.one('#platform').set('disabled',true);
	Y.one('#saveProjectInfo').setStyle('display','none');
	Y.one('#saveHandoverInfo').setStyle('visibility','hidden');
	Y.one('#fileTable').setStyle('display','none');
	Y.one('#addFile').setStyle('display','none');
	//Y.one('#currentProjectId').setStyle('display','');
	Y.one("#handoverInformation").setStyle("visibility","visible");
	Y.one("#cancelHandoverInfo").set('innerHTML', 'Back');
	Y.one("#cancelProjectInfo").set('innerHTML', 'Back');
	
	var inputs = document.getElementsByTagName('input');
	var len = inputs.length;
	for (var i = 0; i < len; i++) {
		inputs[i].disabled = true;
	}
	var dropdown = document.getElementsByTagName('select');
	var len = dropdown.length;
	for (var i = 0; i < len; i++) {
		dropdown[i].disabled = true;
	}
	var textarea = document.getElementsByTagName('textarea');
	var len = textarea.length;
	for (var i = 0; i < len; i++) {
		textarea[i].disabled = true;
	}
}

if(pageType == "edit"){
	//Y.one('#saveProjectInfo').setStyle('display','none');
	Y.one("#handoverInformation").setStyle("visibility","visible");
	Y.one('#projectName').set('disabled',true);
	Y.one('#projectId').set('disabled',true);
	Y.one('#projectCode').set('disabled',true);
	//Y.one('#currentProjectId').setStyle('display','');
	Y.one('#resourceTab').setStyle('display','none');
}

});
var Category = function (id, value) {
    this.id = ko.observable(id); 
    this.value = ko.observable(value);
};
var Option = function (id, value) {
    this.id = ko.observable(id); 
    this.value = ko.observable(value);
};
var ProjectStatus = function (id, value) {
    this.id = ko.observable(id); 
    this.value = ko.observable(value);
};

var Approver = function (id, value) {
    this.id = ko.observable(id); 
    this.value = ko.observable(value);
};
var ChildFile = function(){
	 var self = this;
	 self.inputFile = ko.observable();
};

function AppViewModel() {
	 var self = this;
	var AppViewModel = {};
	var AppViewModelHandover = {};
	var validateHandver = {}; 
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
	ko.validation.rules['endDateValid'] = {
	        validator: function (val, required) {
				if(val != '' && val != undefined){
		            projectStartDate=self.project.startDate();
		          //  alert(projectStartDate);
		           // alert(val);
		            if(projectStartDate != ''){
						var projectStartDated = new Date('' + projectStartDate.split('-')[2].substring(2),projectStartDate.split('-')[1],projectStartDate.split('-')[0]);
						projectStartDated.setFullYear(projectStartDate.split('-')[2]);
						projectStartDated.setMonth(projectStartDate.split('-')[1] - 1);
						projectStartDated.setDate(projectStartDate.split('-')[0]);
						
						var endDate = val;		
						var endDated = new Date('' +endDate.split('-')[2].substring(2),endDate.split('-')[1],endDate.split('-')[0]);
						endDated.setFullYear(endDate.split('-')[2]);
						endDated.setMonth(endDate.split('-')[1] - 1);
						endDated.setDate(endDate.split('-')[0]);
								
						if ( projectStartDated <= endDated ) {
							return true;
						}else{
							return false;
						}
		            }
		            else{
		            	return true;
		            }
				}
				else{
				 return true;
				}
			},
	        message: 'End date Should be greater than or equal to Start date'
	    };
	ko.validation.registerExtenders();
	if(projectDetail.projectId == null)
	{
	 projectDetail = {
			projectId:'',
			projectCode:'',
			projectName:'',
			projectdescription:'',
			projectType:undefined,
			customerId:undefined,
			platform:'',
			primaryCOE:undefined,
			effort:'',
			unit:'PD',
			startDate:'',
			endDate:'',
			approver:undefined,
			approvalStatus:'Awaiting for Approval',
			closureDate:'',
			projectStatus : 'A'
	};
	};
	self.project = {
			projectId : ko.observable('EPRO'+presentYear+''+projectDetail.projectId),
			projectCode : ko.observable(projectDetail.projectCode),
			id : ko.observable(projectDetail.projectId),
			name : ko.observable(projectDetail.projectName).extend({ required: 'Project Name ' }),
		    description :ko.observable(projectDetail.projectdescription).extend({ required: ' Project Description' }),
		    types : ko.observableArray([new Category('POC', "POC"),
   		         		            new Category('Development', "Development")]),
		    type : ko.observable(projectDetail.projectType).extend({ required: 'Project Type' }),
		    customers: ko.observableArray(customerList),
		    selectecustomer:ko.observable(projectDetail.customerId).extend({ required: 'Customer' }),
		    platform : ko.observable(projectDetail.platform).extend({ required: 'Project Platform'}),
		    selectedcoe:ko.observable(projectDetail.primaryCOE).extend({ required: 'COE'}),
		    coes:ko.observableArray(employeeList),
		    effort : ko.observable(projectDetail.effort).extend({ required: 'Effort',number: true, min: 0}),
		    unit:ko.observable(projectDetail.unit).extend({ required:'unit'}),
		    startDate : ko.observable(projectDetail.startDate).extend({ required : 'Start Date'}),
		    endDate : ko.observable(projectDetail.endDate).extend({ required: 'End Date' }),
		    approverList : ko.observableArray([new Approver('Sreeni', "Sreenivasan P"),
		                                       new Approver('Sadeesh', "Sadeesh Venugopal")]),
		    approver : ko.observable(projectDetail.approver).extend({ required: 'Approval by'}),
		    approvalStatus :ko.observable(projectDetail.approvalStatus),
		    closureDate : ko.observable(projectDetail.closureDate),
		    projectStatusList : ko.observableArray([new ProjectStatus('A', "Active"),
		                                       new ProjectStatus('C', "Closed")]),
		    projectStatus :ko.observable(projectDetail.projectStatus)
			};
			
   AppViewModel.errors = ko.validation.group(self.project,{deep: true});
   AppViewModel.errors.showAllMessages(false); 
   self.handOverList = ko.observableArray([]);
   self.checkListArray = ko.observableArray([]);
   self.project.projectStatus.subscribe(function (projectStatus){
	   self.project.closureDate('');
	});
   self.project.endDate.subscribe(function (endDate){
	   self.project.endDate.extend({endDateValid: true });
	});
   self.project.startDate.subscribe(function (){
	   self.project.endDate.extend({endDateValid: true });
	});
   self.addExistingHandOverInfo = function(){
	   self.checkListArray.removeAll();
	   if(existingcheckLists != undefined){
			for(var i=0 ; i < checkLists.length ; i++){
				if(existingcheckLists[i]== null || existingcheckLists[i] == undefined){
					self.checkListArray.push(new addNewCheckList(checkLists[i]));
					AppViewModelHandover.errors = ko.validation.group(self.checkListArray,{deep: true});
					AppViewModelHandover.errors.showAllMessages(false);
				}
				else{
					self.checkListArray.push(new addNewCheckList(existingcheckLists[i]));
					AppViewModelHandover.errors = ko.validation.group(self.checkListArray,{deep: true});
					AppViewModelHandover.errors.showAllMessages(false);
				}
			}
		}
		/*if(existingcheckLists.length == 0){
			document.getElementById('errorTabMessage').style.display = 'block';
			document.getElementById('saveHandoverInfo').style.visibility = 'hidden';
			tabview.selectChild(0);
			return false;
		}
		else
		{
			document.getElementById('errorTabMessage').style.display = 'none';
		}*/
	   }
	   
   self.addHandOverInfo = function(){
	if(existingcheckLists.length == 0){
		self.checkListArray.removeAll();
	   if(checkLists != undefined){
			for(var i=0 ; i < checkLists.length ; i++){
				self.checkListArray.push(new addNewCheckList(checkLists[i]));
				AppViewModelHandover.errors = ko.validation.group(self.checkListArray,{deep: true});
				AppViewModelHandover.errors.showAllMessages(false);
			}
		}
	   }
	else{
		self.addExistingHandOverInfo();
	}
   }
   function addNewCheckList(checkListInfo) {
		var self = this;
		if(checkListInfo.checkListId == null || checkListInfo.checkListId =='' || checkListInfo.checkListId == 'undefined')
			self.checkListId = ko.observable('-1');
		else
			self.checkListId = ko.observable(checkListInfo.checkListId);
		self.checkListItem = ko.observable(checkListInfo.checkListItem);
		self.checkListStatus = ko.observable(checkListInfo.checkListStatus).extend({ required : 'CheckList Status'});
		self.checkListRemarks = ko.observable(checkListInfo.remarks);
		self.optionsArray = ko.observableArray([new Option('Yes', "Yes"),
		                                           new Option('No', "No"),
		        		         		            new Option('NA', "NA")]);
		if(checkListInfo.projectCheckListId == null || checkListInfo.projectCheckListId =='' || checkListInfo.projectCheckListId == 'undefined')
			self.projectCheckListId = ko.observable('-1');
		else
			self.projectCheckListId = ko.observable(checkListInfo.projectCheckListId);
		self.projectCategory = ko.observable(categoryRes);
		self.projectId = ko.observable(projectIdRes);
   }
   AppViewModelHandover.errors = ko.validation.group(self.checkListArray,{deep: true});
   AppViewModelHandover.errors.showAllMessages(false);
	 
   self.showCalendar = function(data){
		popupCalendar(data);
	};
    self.importFiles = ko.observableArray([new ChildFile()]);
    self.addFile = function() { 
        self.importFiles.push(new ChildFile());
     };
     self.documentsArray = ko.observableArray([]);
     ko.mapping.fromJS(documents, {}, self.documentsArray);
     self.removeFile = function(file) { self.importFiles.remove(file); };
     
     self.removeDocument = function(document) {
    	 var answer = confirm('Please confirm to remove '+document.documentName());
  	   	 if (!answer) {
  	   		return false;
  	   	 } 
    	 var projectDocumentId = document.projectDocId();
    	 YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
    		 Y.one("#loadingImage").setStyle("display","block");
    			Y.io("/ems-web/ems/project/deleteProjectHandoverDocument?projectDocumentId="+projectDocumentId, {
    				method: 'GET',
    				on: {
    				success: function (id, o) {
    				var response = Y.JSON.parse(o.responseText);
    				var deleteStatus = response.deleteStatus;
    				if(deleteStatus == 'true'){
    					Y.one("#loadingImage").setStyle("display","none");
    					self.documentsArray.remove(document);
    					}
    				else{
        				alert('Unable to remove document at this time');
    				}
    			}
    			}
    			});	
    		});
          };
     
     self.download = function(documen){
     	var record = ko.toJS(documen);
     	document.location.href = "/ems-web/ems/project/download?id="+record.projectDocId;
     };
     
    self.saveProjectInfo = function() {
    	document.getElementById('errorTabMessage').style.display = 'none';
		//document.getElementById('saveHandoverInfo').style.visibility = 'visible';
	  
	  function ClosureEndCheck(){
		 	 var EndDate= document.getElementById('date-end').value;
		     var ClosureDate= document.getElementById('date-closure').value;
		     var projectStatus = document.getElementById('projectStatusVal').value;
		     var d1 = EndDate.split("-");
		     var d2 = ClosureDate.split("-");
		     if(projectStatus == 'A'){
			     return true;
			     }
		     else{
				var fromdate= new Date('' + EndDate.split('-')[2].substring(2),EndDate.split('-')[1], EndDate.split('-')[0]);
				fromdate.setFullYear(EndDate.split('-')[2]);
				fromdate.setMonth(EndDate.split('-')[1] - 1);
				fromdate.setDate(EndDate.split('-')[0]);
				
				var todate = new Date('' + ClosureDate.split('-')[2].substring(2), ClosureDate.split('-')[1], ClosureDate.split('-')[0]);
				todate.setFullYear(ClosureDate.split('-')[2]);
				todate.setMonth(ClosureDate.split('-')[1] - 1);
				todate.setDate(ClosureDate.split('-')[0]);
					
					if ( fromdate > todate) {
						return false;
					}else{
						return true;
					}
		}
	  }
	   function isClosure()
	   {
		   var ClosureDate= document.getElementById('date-closure').value;
		   var projectStatus = document.getElementById('projectStatusVal').value;
		   
		   if((projectStatus == 'C')&&((ClosureDate=='')||(ClosureDate == undefined)))
		   {
			   return false;
		   }
		   else
		   {
			   return true;
		   }
	   }
	  	  
   	if (AppViewModel.errors().length == 0) {
   		 var projectInfo = "";
	        YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y) {
	        	var form = Y.one('#projectInformationForm');
				projectInfo = Y.QueryString.stringify(Y.Array.reduce(Y.one(form).all('input[name],select[name],textarea[name]')._nodes, {}, function (init, el, index, array) {
					init[el.name] = el.value;
					return init;
				
				}));
				var cfg = {
							method: 'POST',
						data: projectInfo,
						on: {
							success: function (id, o) {
							var message =Y.JSON.parse(o.responseText);
								if (message != null) {
									 projectIdRes = message.projectId;
									 categoryRes = message.category;
									 Y.one("#loadingImage").setStyle("display","none");
									 Y.one("#handoverTab").setStyle("visibility","visible");
									 tabview.selectChild(1);
					                 Y.one("#handoverInformation").setStyle("visibility","visible");
					                 self.project.projectId('EPRO'+presentYear+''+projectIdRes);
									 self.addHandOverInfo();
								}
								
							}
						}
  				};

  	  				if(isClosure()){
  	  					Y.one('#closureValid').setStyle('display','none');
  	  					if(ClosureEndCheck()){
  	  						Y.one('#closure-End-Valid').setStyle('display','none');
	  	  					Y.one("#loadingImage").setStyle("display","block");
			              	Y.io("/ems-web/ems/project/saveProjectInfo", cfg);
			              	//tabview.selectChild(1);
  	  					}else{
  	  						Y.one('#closure-End-Valid').setStyle('display','block');
  	  					}
  	  				}
  	  				else
  	  				{
  	  					Y.one('#closureValid').setStyle('display','block');
  	  				}
  						
			  });
       } else {
       	AppViewModel.errors.showAllMessages();
       }
   };
   self.saveHandoverInfo = function() {
	   var statusVal;
	   var remarksVal;
	   var errIdstaus;
	   var errIdRemarks;
   	function validateCheckList(){
   	var status = true;
   	for(var i=0;i<checkLists.length;i++)
   	{
   		statusVal = document.getElementById("status-"+i).value;
     	remarksVal = document.getElementById("remarks-"+i).value;
     	errIdRemarks = "remarks-"+i+"-err";
   	   	if(statusVal == 'No' || statusVal == 'NA')
     	  {
     		if(remarksVal == '' || remarksVal == 'undefined' || remarksVal == undefined)
     		  {
     			document.getElementById(errIdRemarks).style.display = 'block'; 
     			status = false;
     			//alert("errIdRemarks"+errIdRemarks);
     		  }
     		else
     		{
     			document.getElementById(errIdRemarks).style.display = 'none';
     		}
     	  }
   	   	else{
   	   	   	document.getElementById(errIdRemarks).style.display = 'none';
   	   	   	}
   	}
   	return status;
   	}
   	if (AppViewModelHandover.errors()=='') {
   	   	//alert(AppViewModelHandover.errors()+AppViewModelHandover.errors().length);
   	if(validateCheckList())
   		{
   		YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y) {
   		Y.one("#loadingImage").setStyle("display","block");
   		});
   		document.handoverInformationForm.submit();
   		}
   	else
   	{
   		document.getElementById("errormessage").style.display = 'block'; 
   	}
   	}
   	else{
   		AppViewModelHandover.errors.showAllMessages(true); 
   	}
   	};
	/**
	*	Method to set title
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
	self.setTitleFromServer = function(array,data) {
		var response = ko.toJS(data);
		for(i in array)
		{
			if(array[i].id == response)
			{
				return array[i].value;
			}
		}
	};
	
   self.resourse = ko.observableArray([]);
	YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
		if(pageType == "view"){
		Y.io("/ems-web/ems/project/getResources?projectId="+projectIdRes+"&sourceFrom=project", {
			method: 'GET',
			on: {
			success: function (id, o) {
			var response = Y.JSON.parse(o.responseText);
			var resourse = response.resources;
			self.resourse.removeAll();
			if(resourse.length == 0){
				Y.one('#resourceTab').setStyle('visibility','hidden');
				}
			else{
			if(response.resources != undefined){
				for(var i=0 ; i < resourse.length ; i++){
					self.resourse.push(new addNewResource(resourse[i]));
				}
				Y.one('#resourceTab').setStyle('visibility','visible');
			}
			}
		}
		}
		});	
		}
	});
	
 function addNewResource(resourceinfo) {
       var self = this;
       self.employeeId = ko.observable(resourceinfo.employee.employeeId).extend({ required: true});
		self.firstName = ko.observable(resourceinfo.employee.firstName);
       self.lastName = ko.observable(resourceinfo.employee.lastName);
       self.roleName = ko.observable(resourceinfo.roleName).extend({ required: true});
       self.startDate = ko.observable(resourceinfo.startDate).extend({ required: true});
       self.endDate = ko.observable(resourceinfo.endDate).extend({ required: true});
       self.billable = ko.observable(resourceinfo.billable).extend({ required: true});
       self.location = ko.observable(resourceinfo.currentLocation).extend({ required: true});
       self.activeStatus=ko.observable(resourceinfo.activeStatus).extend({ required: true});
       self.allocation = ko.observable(resourceinfo.allocationPercentage).extend({ required: true});
};
  

}
function initKo() {
	projectViewModel = new AppViewModel();
	   ko.applyBindings(projectViewModel);
	}
//calendar
/**
 * Calender widget common across all the pagse.
 * 
 */
var calenderObj = null;
var outerCalWidgetContainerId = '#nav-calendar';
var calWidgetContainerId = '#cal-widget-container';
var outerCalWidgetContainerDiv = null;
var currDateBxId = null;
var futureDate = new Date();

function popupCalendar(callbackOrId) {
//new
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
			 currDateBxId = clickId;
			 isCal = true;
			}
		 var clazz = clickNode.getAttribute('class');
         if(clickNode == outerCalWidgetContainerDiv 
        		 || outerCalWidgetContainerDiv.contains(clickNode)) {
        	 isCal = true;
         }
		if(clazz.indexOf('yui3-calendar-day-selected') >= 0) { //bug Fix 19409
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
	var xy = Y.one('#'+callbackOrId).getXY();
	xy[0] = xy[0] - 90;
	xy[1] = xy[1] + 25;
	outerCalWidgetContainerDiv.setXY(xy);
});
}
function selectionChangeCallback(date) {
	if(currDateBxId=='date-end')
		projectViewModel.project.endDate(date);
	if(currDateBxId=='date-start')
		projectViewModel.project.startDate(date);
	if(currDateBxId=='date-closure')
		projectViewModel.project.closureDate(date);
}

function returnCalendar(){
	if(calendarExistsInd){
		return;
		}
	calendarExistsInd = true;
	YUI().use('calendar', 'datatype-date','node',  function(Y) {
		Y.CalendarBase.CONTENT_TEMPLATE = Y.CalendarBase.ONE_PANE_TEMPLATE;
	    // Create a new instance of calendar, placing it in 
	    // #mycalendar container, setting its width to 350px,
	    // the flags for showing previous and next month's 
	    // dates in available empty cells to true, and setting 
	    // the date to today's date. 
	    calenderObj = new Y.Calendar({
	      contentBox: "#mycalendar",
	      width:'350px',
	      showPrevMonth: true,
	      showNextMonth: true,
	      date: new Date()}).set('customRenderer', {
              filterFunction: function (cellDate, cellNode) {
              /*if (cellDate <= futureDate) {
                  cellNode.addClass('calendarDiasabled');
              }*/
          },
          rules: {
              all: 'all'
          }
      }).render(),
      myCalendarCanBeSelected = calenderObj._canBeSelected;
	    calenderObj._canBeSelected = function (oDate) {
          /*if (oDate <= futureDate) {
              return false;
          }*/
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
</script>
</body>
</html>