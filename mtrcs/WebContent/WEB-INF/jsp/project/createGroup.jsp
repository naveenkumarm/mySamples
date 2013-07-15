<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
</head>
<body onload='intiKo()'>
<div id="create_group_container">
<f:form action="/ems-web/ems/project/saveGroup" method="post" id="groupForm" name="groupForm" >
	<div class="clearfix" id="group_container" style="width:75%; padding-top:5%; padding-left:5%; padding-right:5%; margin:5%; border:5px #f1f1f1 solid;">
		<span>Group Name :</span>
		<input type="text" maxlength="40" style="width: 20%;" name="groupName" data-bind="value:groupView.groupName, enable: enableFields() == true "/>
		<input type="hidden" name="groupId" data-bind="value:groupView.groupId"/>
		</br>
		</br>
		<span data-bind="visible: enableFields() == true">Select projects to add in this Group:</span>
		<span data-bind="visible: enableFields() != true">Below projects are available in this Group:</span>
		<div class="float-left" style="width:90%; padding:2%; margin:3%; border:5px #f1f1f1 solid;height: auto;">
		<div class="validationMessage" data-bind="visible: !selectedProject(), text:'select atleast one project'" id="error" style="margin-left: 0.5%;"></div>
		<br>
		<div class="float-left" style="width: 100%;" id="project_container"  data-bind="foreach: projectArray">
			
			<div class="float-left" style="width:48%; margin-left:.5%;">
				<input type="checkbox"  data-bind="checked: isProjectSelected, value: projectId, enable: enableFields() == true "/>
				<span style="margin-left:5px;" data-bind="text:projectName" ></span>
			</div>
			
			<input type="hidden" data-bind="value: projectId, uniqueName: true, attr: {name: 'projectList['+$parent.projectArray.indexOf($data)+'].projectId'}" />
			<input type="hidden" data-bind="value: projectName, uniqueName: true, attr: {name: 'projectList['+$parent.projectArray.indexOf($data)+'].projectName'}" />
			<input type="hidden" data-bind="value: isProjectSelected, uniqueName: true, attr: {name: 'projectList['+$parent.projectArray.indexOf($data)+'].isProjectSelected'}" />
		
		</div>
		</div>
		<div class="clearfix">
			<div style="width:65px; float:left; margin:0 0 0 2%;" class="normal-button float-left" type="button" data-bind="visible: enableFields() == true, click: saveGroup" >Save</div>
			<div style="width:65px; float:left; margin:0 0 0 2%;" class="normal-button float-left" type="button" data-bind="visible: enableFields() == true, click:resetGroup" >Reset</div>
			<a href="/ems-web/ems/project/listGroup" style="width:65px; float:left; margin:0 0 0 2%;text-decoration:none;" class="normal-button ">Back</a>
		</div>
		
		</br>
	</div>
</f:form>
</div>
<script type="text/javascript">

var self = new groupViewModel();

var allProjectList = '';
var existingProjectList = '';
var allGroupList = '';
var groupId = '';
var groupName = '';
var mode = ${mode};
//var selectedProject = false;

allProjectList = ${allProjectList};	
allGroupList = ${allGroupList};
groupId = ${groupId};
groupName = ${groupName};
existingProjectList = ${existingProjectList};

//creating knockout view model
function groupViewModel() {
	
	var groupViewModel = {};
	var projectViewModel = {};
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
	    ko.validation.rules['validategroupName'] = {
		        validator: function (val, required) {
		            var isExist = false;
		    		if(val != undefined){
		    			for(var index in allGroupList) { 
		    				if(allGroupList[index].groupName == val){
		    					isExist = true;
		    				}
			    		}
		    		}
		    		if(mode == 'create'){
						if(!isExist){
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
		        message: 'Group name exsists.'
		};
	ko.validation.registerExtenders();   
	var self = this;
	self.selectedProject = ko.observable(true);
	self.projectArray = ko.observableArray([]).extend({ projectValidation: 'select atleast one project'});
	self.groupView = {
			groupId : ko.observable('-1'),
			groupName : ko.observable().extend({ required: 'Group Name',validategroupName:true }),
			mode : ko.observable()
		};
	groupViewModel.errors = ko.validation.group(self.groupView,{deep: true});
	groupViewModel.errors.showAllMessages(false); 
	/**
	*	method to add project values.
	*/
	function addNewProject(projectInfo,mode) {
		var self = this;
			self.projectId = ko.observable(projectInfo.projectId);
			self.projectName = ko.observable(projectInfo.projectName);
			self.mode = ko.observable(mode);
			self.isProjectSelected = ko.observable(false);
			/**
			*	method used to add all projects.
			*/
			self.enableFields = ko.computed({
			    read: function() {
			        var enable = false;
					var mode = self.mode();	       
			        if(mode == 'create' || mode == 'edit'){
			  			enable = true;
			    	} else {
			    		enable = false;
			    	}
			        	return enable;
					}
			});
	};

	/**
	*	method to add existing project values.
	*/
	function addExistingProject(projectInfo,mode) {
		var self = this;
			self.projectId = ko.observable(projectInfo.projectId);
			self.projectName = ko.observable(projectInfo.projectName);
			self.isProjectSelected = ko.observable(true);
			self.mode = ko.observable(mode);
			/**
			*	method used to add all projects.
			*/
			self.enableFields = ko.computed({
			    read: function() {
			        var enable = false;
					var mode = self.mode();	       
			        if(mode == 'create' || mode == 'edit'){
			  			enable = true;
			    	} else {
			    		enable = false;
			    	}
			        	return enable;
					}
			});
	};

	/**
	*	method used to save group project.
	*/	
	self.saveGroup = function(){
		self.selectedProject(false);
		YUI().use('node', function(Y) {
		for(var index in self.projectArray()) { 
			if(self.projectArray()[index].isProjectSelected()){
				self.selectedProject(true);
			}
		}
		/*if(selectedProject > 0){
			//Y.one('#error').setStyle('display','none');
		}
		else{
			//Y.one('#error').setStyle('display','block');
		}*/
		if(groupViewModel.errors() == '' && self.selectedProject()){
			groupViewModel.errors.showAllMessages(false);
			document.groupForm.submit();
		}
		else{
			
			groupViewModel.errors.showAllMessages(true);
			
		}
		});
		};
	/**
	*	method used to reset all checkbox values.
	*/
	self.resetGroup = function(){
		for(var index in self.projectArray()) { 
			self.projectArray()[index].isProjectSelected(false);
		}
		};

	/**
	*	method used to add all existing projects.
	*/
	self.listExistingProjects = function(mode){
		for(var i=0,j=existingProjectList.length; i < j ; i++){
			self.projectArray.push(new addExistingProject(existingProjectList[i],mode));
		}
		self.groupView.groupId(groupId);
		self.groupView.groupName(groupName);
	};

	/**
	*	method used to add all projects.
	*/
	self.listAllProject = function(mode){
		for(var i=0,j=allProjectList.length; i < j ; i++){
			self.projectArray.push(new addNewProject(allProjectList[i],mode));
			}
		};

	/**
	*	method used to add all projects.
	*/
	self.enableFields = ko.computed({
	    read: function() {
	        var enable = false;
			var mode = self.groupView.mode();	       
	        if(mode == 'create' || mode == 'edit'){
	  			enable = true;
	    	} else {
	    		enable = false;
	    	}
	        	return enable;
			}
	});
		
}

/**
*	method used to initialize knockout.
*/	
function intiKo() {
	ko.applyBindings(self);
	self.groupView.mode(mode);
	if(mode == 'edit' || mode == 'view'){
		self.listExistingProjects(mode);
	}
	if(mode == 'create' || mode == 'edit'){
		self.listAllProject(mode);	
	}
	
}

</script>
</body>
</html>