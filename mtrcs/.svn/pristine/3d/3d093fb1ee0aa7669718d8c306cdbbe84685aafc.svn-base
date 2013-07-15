<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
</head>
<body onload='intiKo()'>
<div id="issueRegister_container">
	<div id="nav-calendar" class="default_calender calendar-picker">
		<div>
			<div id="mycalendar"></div>
		</div>
	</div>

	<div class="clearfix" style="margin-top: 2%; width: 100%;">
		<div class="float-left" style="margin-left: 2%; width: 25%;">
			<div class="float-left" style="width: 35%;">Project</div>
			<div class="float-left" style="margin-left: 2%; width: 55%;">
				<select style="width: 100%;" name="projectId" data-bind="value: projectId,optionsValue: 'projectId', 
										optionsText: 'projectName', options: projectArray,optionsCaption:'Select',
										attr: {'title': $root.setProjectTitle(projectArray(), projectId)}"></select>
				<div id="errorMessage" class="validationMessage" style="text-align: center; display: none; padding-right: 10%;"></div>
			</div>
		</div>
	</div>
<div id="addIssueRegister" style="display: none;">
	<div class="clearfix" style="margin: 1% 2%;">
		<div data-bind="click:saveIssuesDetail" class="normal-button" style="width: 65px; float: left; margin: 5px 0;">Submit</div>
		<div data-bind='click: addRow' class="normal-button" style="width: 105px; float: left; margin: 5px 0 5px 5px;">Add New Row</div>
	</div>

	<div id="successMessage" align="center"
		style="display: none; background: none repeat scroll 0pt 0pt #DFF0D9;; color: #279800; font-size: 12px; padding: 5px; width: 95%; margin-left: 2%;"></div>
	<div id="errorResourceMessage" align="center"
		style="display: none; background: none repeat scroll 0 0 #FFE1D2; color: #FF5C5C; font-size: 12px; padding: 5px; width: 95%; margin-left: 2%;"></div>
	<br>
<div align="center" class="assignresource-grid" style="width: 97%; margin: 0 1% 0 2%;">
	<table cellpadding="0" cellspacing="0" style="margin-top: 0; width: 100%;">
		<thead>
			<tr>
				<th width="9%;">Date Raised</th>
				<th width="7%;">Raised By</th>
				<th width="8%;">Status</th>
				<th width="14%;">Description Of Issue</th>
				<th width="8%;">Additional Info</th>
				<th width="7%;">Impact Rating</th>
				<th width="7%;">Priority</th>
				<th width="7%;">Action</th>
				<th width="8%;">Assigned To</th>
				<th width="9%;">Date For Resolution</th>
				<th width="9%;">Resolution Date</th>
				<th width="7%">Remove</th>
			</tr>
		</thead>
		<tbody style="background-color: white !important;"
			data-bind="visible: issueList().length == 0">
			<tr>
				<td colspan="12" style="text-align: center;">
				<span> No Data Found </span></td>
			</tr>
		</tbody>
		<tbody data-bind="foreach: issueList">
			<tr> 
				<td >
				    <input type="text" readonly="readonly" data-bind="value: dateRaised,attr: {id:'dateRaised-'+$parent.issueList.indexOf($data)+''},click: function(data, event) { $root.showCalendar($index , $data, event,'dateRaised') }" style="width: 100%;">
				</td>
				<td >
				    <input type="text" data-bind="value: raisedBy,attr: {id:'raisedBy-'+$parent.issueList.indexOf($data)+''}" style="width: 100%;">
				</td>
				<td >
				    <select data-bind="options: statusArray ,optionsValue: 'id', optionsText: 'value', optionsCaption: 'Select', value: status,attr: {id:'status-'+$parent.issueList.indexOf($data)+'','title': $root.setTitle(statusArray(), status)}"	style="width: 100%;"></select>
				</td>
				<td >
				    <textarea type="text" data-bind="value: descriptionOfIssue,attr: {id:'descriptionOfIssue-'+$parent.issueList.indexOf($data)+''}" style="width: 100%;height: 40px !important;" ></textarea>
				</td>
				<td >
				     <input type="text" data-bind="value: additionalInfo,attr: {id:'additionalInfo-'+$parent.issueList.indexOf($data)+''}" style="width: 100%;" />
				</td>
				<td >
				    <select data-bind="options: impactRatingArray ,optionsValue: 'id', optionsText: 'value', optionsCaption: 'Select', value: impactRating,attr: {id:'impactRating-'+$parent.issueList.indexOf($data)+'','title': $root.setTitle(impactRatingArray(), impactRating)}"	style="width: 100%;"></select>
				</td>
				<td >
				    <select data-bind="options: priorityArray ,optionsValue: 'id', optionsText: 'value', optionsCaption: 'Select', value: priority,attr: {id:'priority-'+$parent.issueList.indexOf($data)+'','title': $root.setTitle(priorityArray(), priority)}"	style="width: 100%;"></select>
				</td>
				<td >
				  <input type="text" data-bind="value: action,attr: {id:'action-'+$parent.issueList.indexOf($data)+''}"	style="width: 100%;">
				</td>
				<td >
				  <input type="text" data-bind="value: assignedTo,attr: {id:'assignedTo-'+$parent.issueList.indexOf($data)+''}"	style="width: 100%;">
				</td>
				<td >
				  <input type="text" readonly="readonly" data-bind="value: dateForResolution,click: function(data, event) { $root.showCalendar($index , $data, event,'dateForResolution') },attr: {id:'dateForResolution-'+$parent.issueList.indexOf($data)+''}"	style="width: 100%;">
				</td>
				<td >
				  <input type="text" readonly="readonly" data-bind="value: resolutionDate,click: function(data, event) { $root.showCalendar($index , $data, event,'resolutionDate') },attr: {id:'resolutionDate-'+$parent.issueList.indexOf($data)+''}"	style="width: 100%;">
				</td>
				<td >
				    <span data-bind="visible:status() != '2',click: $parent.removeIssue,attr:{title:'Remove'}" class="link-button" style="margin-left: 1%;" style="width: 100%;">Remove</span>
				</td>
			</tr>
		</tbody>
		
  </table>
</div>
</div>
	<div id="viewIssueRegister" class="assignresource-grid" style="display: none;width: 97%; margin: 0 1% 0 2%;">
        <table  width='100%' cellspacing="0" cellpadding="0" >
          <thead>
          <tr>
				<th width="9%;">Date Raised</th>
				<th width="10%;">Raised By</th>
				<th width="6%;">Status</th>
				<th width="19%;">Description Of Issue</th>
				<th width="9%;">Additional Info</th>
				<th width="6%;">Impact Rating</th>
				<th width="6%;">Priority</th>
				<th width="7%;">Action</th>
				<th width="10%;">Assigned To</th>
				<th width="9%;">Date For Resolution</th>
				<th width="9%;">Resolution Date</th>
			</tr>
</thead>
<tbody style="background-color: white !important;" data-bind="visible:  issueList().length == 0">
         	<tr>
            	<td colspan="11" style="text-align:center;">
            		<span>	No Data Found	</span>
            	</td>
            </tr>
</tbody>
<tbody data-bind="foreach:  issueList">
<tr>
        <td>
          <span  data-bind="text: dateRaised" ></span>
        </td>
        <td>
            <span data-bind="text: raisedBy"></span>
        </td>
        <td data-bind="foreach: statusArray">
           <span data-bind="visible:( id() == $parent.status()),text:value"></span>
        </td>
        <td>
            <span data-bind="text: descriptionOfIssue"></span>
        </td>
        <td>
            <span data-bind="text: additionalInfo"></span>
        </td>
        <td data-bind="foreach: impactRatingArray">
            <span data-bind="visible:( id() == $parent.impactRating()),text: value"></span>
        </td>  
        <td data-bind="foreach: priorityArray">
            <span data-bind="visible:( id() == $parent.priority()),text: value"></span>
        </td>
        <td>
            <span data-bind="text: action"></span>
        </td>
        <td>
         <span data-bind="text: assignedTo"></span>
        </td> 
        <td>
         <span data-bind="text: dateForResolution"></span>
        </td>
        <td>
         <span data-bind="text: resolutionDate"></span>
        </td>     
</tr>
</tbody>
</table>      
</div>
</div>
<script type="text/javascript">
var self = new AppViewModel();
var empInfoList = ${empInfoList};
var projectList = empInfoList[0].projectList;
var priorityList = ${priority};
var impactRatingList = ${impactRating};
var statusList = ${status};
var projectStartdate ='';
var projectEnddate ='';
var calenderID = '';
var view = '';
var calendarExistsInd = false;
var resourceDBDate='';
var info ={
	issueId :'-1',
    dateRaised :'',
	raisedBy:'',
	status :'',
	descriptionOfIssue :'',
	additionalInfo:'',
    impactRating:'',
	priority:'',
	action:'',
	assignedTo:'',
	dateForResolution:'',
	resolutionDate:''
};

AppViewModel.errors = ko.validation.group(AppViewModel);

function AppViewModel() {
		var validateIssuesList = {}; 
		var self = this;
		self.issueList = ko.observableArray([]);
		self.projectArray =ko.observableArray([]);
		self.projectId = ko.observable();
	    self.projectId.subscribe(function (projectId){
			YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
			Y.one("#errorMessage").setStyle("display","none");
				Y.one("#successMessage").setStyle("display","none");
				Y.one("#loadingImage").setStyle("display","block");
				var project=self.projectId();
				if(project!= undefined){
					Y.io("/ems-web/ems/issueRegister/getIssueList?projectId="+self.projectId(), {
						method: 'GET',
						on: {
							success: function (id, o) {
								var response = Y.JSON.parse(o.responseText);
								self.issueList.removeAll();
								if(response.issueList != undefined){
									var issueList = response.issueList;
									for(var i=0 ; i < issueList.length ; i++){								
										self.addExistingIssues(issueList[i],i);
										//validateIssuesList.errors = ko.validation.group(self.resource,{deep: true});
									}
								}
								if(view == 'add'){
									self.addRow(info);
								}
								//validateIssuesList.errors.showAllMessages(false);
								Y.one("#loadingImage").setStyle("display","none");
							}
						}
					});	
				}
				else {
					self.issueList.removeAll();
					Y.one("#loadingImage").setStyle("display","none");
			  }
		});
	});
		
	    self.addRow = function(){
	    	self.issueList.push(new addNewRow(info));
			validateIssuesList.errors = ko.validation.group(self.issueList,{deep: true});
			validateIssuesList.errors.showAllMessages(false);
		};
	self.constructQueryString = function() {          
		var queryString = 'projectId='+self.projectId();
		for(var i = 0 ; i < self.issueList().length ; i++) {
			queryString = queryString
			+'&issueRegisterList%5B'+i+'%5D.'+''+'issueId'+'='+self.issueList()[i].issueId()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'dateRaised'+'='+self.issueList()[i].dateRaised()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'raisedBy'+'='+self.issueList()[i].raisedBy()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'status'+'='+self.issueList()[i].status()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'descriptionOfIssue'+'='+self.issueList()[i].descriptionOfIssue()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'additionalInfo'+'='+self.issueList()[i].additionalInfo()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'impactRating'+'='+self.issueList()[i].impactRating()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'priority'+'='+self.issueList()[i].priority()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'action'+'='+self.issueList()[i].action()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'assignedTo'+'='+self.issueList()[i].assignedTo()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'dateForResolution'+'='+self.issueList()[i].dateForResolution()+
			'&issueRegisterList%5B'+i+'%5D.'+''+'resolutionDate'+'='+self.issueList()[i].resolutionDate();
		}
		return queryString;
	};
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
	self.setProjectTitle = function(array,data) {
		var response = ko.toJS(data);
		for(i in array)
		{
			if(array[i].projectId() == response)
			{
				return array[i].projectName;
			}
		}
	};

	self.saveIssuesDetail = function () {
		var project = self.projectId();
		YUI().use('io-base', 'node', "json-parse","io", function(Y) {
		Y.one("#errorMessage").setStyle("display","none");
		Y.one("#successMessage").setStyle("display","none");
		if(project!= undefined){
			if (validateIssuesList.errors() == '') {
				var registerInfo = "";
				registerInfo = self.constructQueryString();	
					var cfg = {
						method: 'POST',
						data: registerInfo,
						on: {
							success: function (id, o) {
								if (o.responseText != null) {
								    textId = null;
									var projectid = self.projectId();
									self.projectId(undefined);
									self.projectId(projectid);
									Y.one("#successMessage").setHTML('Issue Details successfully added').setStyle("display","block");
								}
							}
						}
					};
					Y.io("/ems-web/ems/issueRegister/save", cfg);
			}
			else {
				validateIssuesList.errors.showAllMessages();            
			}
		} 
		else {
			Y.one("#successMessage").setStyle("display","none");
			Y.one("#errorMessage").setHTML('Please Select Project').setStyle("display","block");
			Y.one("#loadingImage").setStyle("display","none"); 
		}
		});
	};
	function addNewRow(info) {
		var self = this;
		self.issueId = ko.observable(info.issueId);
		self.dateRaised = ko.observable(info.dateRaised);
		self.raisedBy = ko.observable(info.raisedBy);
		self.status = ko.observable(info.status);
		self.statusArray = ko.observableArray([]);
		self.descriptionOfIssue = ko.observable(info.descriptionOfIssue).extend({ required: 'Description Of Issue'});
		self.additionalInfo = ko.observable(info.additionalInfo);
		self.impactRating = ko.observable(info.impactRating);
		self.impactRatingArray = ko.observableArray([]);
		self.priorityArray = ko.observableArray([]);
		self.priority = ko.observable(info.priority);
		self.action = ko.observable(info.action);
		self.assignedTo = ko.observable(info.assignedTo);
		self.dateForResolution = ko.observable(info.dateForResolution).extend({dateForResolutionValidation:true});
		self.resolutionDate = ko.observable(info.resolutionDate).extend({dateResolutionValidation:true});
		ko.mapping.fromJS(statusList, {}, self.statusArray);
		ko.mapping.fromJS(priorityList, {}, self.priorityArray);
		ko.mapping.fromJS(impactRatingList, {}, self.impactRatingArray);       
	};
	self.addExistingIssues = function(info,index){
		self.issueList.push(new addNewRow(info));
		if(info.status == '2'){
			YUI().use('node', function(Y) {
				Y.one("#dateRaised-"+index).set('disabled',true);
				Y.one("#raisedBy-"+index).set('disabled',true);
				Y.one("#status-"+index).set('disabled',true);
				Y.one("#descriptionOfIssue-"+index).set('disabled',true);
				Y.one("#additionalInfo-"+index).set('disabled',true);
				Y.one("#impactRating-"+index).set('disabled',true);
				Y.one("#priority-"+index).set('disabled',true);
				Y.one("#action-"+index).set('disabled',true);
				Y.one("#assignedTo-"+index).set('disabled',true);
				Y.one("#dateForResolution-"+index).set('disabled',true);
				Y.one("#resolutionDate-"+index).set('disabled',true);
			});
			}
		validateIssuesList.errors = ko.validation.group(self.issueList,{deep: true});
	};
	 
	self.removeIssue = function(index){
		var issueDetail = ko.toJS(index);
		YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
		Y.one("#successMessage").setStyle("display","none");
			if(issueDetail.issueId != '-1') {
					var answer = confirm("Please confirm to delete this");
					if (!answer) {
						return false;
					}
					Y.io("/ems-web/ems/issueRegister/remove?issueRegisterId="+issueDetail.issueId, {
						method: 'GET',
						on: {
							success: function (id, o) {
								textId = null;
								self.issueList.remove(index);
								validateIssuesList.errors = ko.validation.group(self.issueList,{deep: true});
								Y.one("#successMessage").setHTML('Issue Detail successfully removed').setStyle("display","block");
								setTimeout(function(){
									Y.one('#successMessage').setStyle('display','none');
								},5000);
							}
						}
					});	
			}
			else {
				textId = null;
				self.issueList.remove(index);
				validateIssuesList.errors = ko.validation.group(self.issueList,{deep: true});
			}
		}); 
	};
			
	self.showCalendar = function(index,data,event,inputBox){
		popupCalendar(inputBox+'-'+index(),index());
	};
	
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
	* This validation is used to Date for resolution should be greater than or equal to Date raised
	*/
	ko.validation.rules['dateForResolutionValidation'] = {
		validator: function (date, required) {
			if(textId != null || textId != undefined){
				var id = textId.split('-')[1];
				if(self.issueList()[id].dateRaised() != undefined && self.issueList()[id].dateRaised() != ''){
					var to_date = self.issueList()[id].dateRaised();
	
					var to_dated = new Date('' + to_date.split('-')[2].substring(2), to_date.split('-')[1], to_date.split('-')[0]);
					to_dated.setFullYear(to_date.split('-')[2]);
					to_dated.setMonth(to_date.split('-')[1] - 1);
					to_dated.setDate(to_date.split('-')[0]);
					
					var from_date = new Date('' + date.split('-')[2].substring(2),date.split('-')[1], date.split('-')[0]);
					from_date.setFullYear(date.split('-')[2]);
					from_date.setMonth(date.split('-')[1] - 1);
					from_date.setDate(date.split('-')[0]);
					
					if (from_date >= to_dated) {
						return true;
					}else{
						return false;
					}
				}else {
					return true;
			    }
			}else{
				return true;
		   }	
		return true;
		},
		message:  'Date for resolution should be greater than or equal to Date raised.'
	};
	
   /**
	* This validation is used to Resolution date should be greater than or equal to Date raised
	*/
	ko.validation.rules['dateResolutionValidation'] = {
		validator: function (date, required) {
			if(textId != null){
				var id = textId.split('-')[1];
				if(self.issueList()[id].dateRaised() != undefined && self.issueList()[id].dateRaised() != ''){
					var to_date = self.issueList()[id].dateRaised();
	
					var to_dated = new Date('' + to_date.split('-')[2].substring(2), to_date.split('-')[1], to_date.split('-')[0]);
					to_dated.setFullYear(to_date.split('-')[2]);
					to_dated.setMonth(to_date.split('-')[1] - 1);
					to_dated.setDate(to_date.split('-')[0]);
					
					var from_date = new Date('' + date.split('-')[2].substring(2),date.split('-')[1], date.split('-')[0]);
					from_date.setFullYear(date.split('-')[2]);
					from_date.setMonth(date.split('-')[1] - 1);
					from_date.setDate(date.split('-')[0]);
					
					if (from_date >= to_dated) {
						return true;
					}else{
						return false;
					}
				}else {
					return true;
			    }
			}else{
				return true;
		    }		
		return true;
		},
		message:  'Resolution date should be greater than or equal to Date raised.'
	};
	
	ko.validation.registerExtenders();
}
function intiKo() {
	ko.applyBindings(self);
	ko.mapping.fromJS(projectList, {}, self.projectArray);
	view = '${view}';
	YUI().use('node', function(Y) {
		if(view == "add"){
			Y.one("#addIssueRegister").setStyle("display","block");
			Y.one("#viewIssueRegister").setStyle("display","none");
		}else{
			Y.one("#addIssueRegister").setStyle("display","none");
			Y.one("#viewIssueRegister").setStyle("display","block");
		}
	});
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
				 if(clickId.indexOf('dateRaised-') != -1 || clickId.indexOf('dateForResolution-') != -1 || clickId.indexOf('resolutionDate-') != -1){
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
	if (textId.indexOf('dateRaised-') != -1) {
		self.issueList()[id].dateRaised(date);
	}
	if (textId.indexOf('resolutionDate-') != -1) {
		self.issueList()[id].resolutionDate(date);
	}
	if (textId.indexOf('dateForResolution-') != -1) {
		self.issueList()[id].dateForResolution(date);
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
</script>
</body>
</html>