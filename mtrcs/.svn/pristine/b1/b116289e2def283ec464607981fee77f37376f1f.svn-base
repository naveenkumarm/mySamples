<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
</head>

<body onload='initKo()'><br>
<div id="list_group_container">
<div class="normal-grid"  style="width: 60%; margin-left: 2%;">
<div id="success-message" class="success-message" style="display: none;" align="center"></div>
<div id="failure-message" class="failure-message" style="display: none;"></div>
<table cellpadding="0" cellspacing="0" width="95%">
	<thead>
		<tr>
			<th width="25%;">Group Id</th>
			<th width="35%;">Group Name</th>
			<th width="40%;">Options</th>
		</tr>
	</thead>
	<tbody style="background-color: white !important;"
		data-bind="visible: groupListArray().length == 0">
		<tr>
			<td colspan="3" style="text-align: center;"><span> No
			Data Found </span></td>
		</tr>
	</tbody>
	<tbody data-bind='foreach: groupListArray'>
		<tr >
			<td data-bind="text: 'Group'+groupId()"></td>
			<td data-bind="text: groupName"></td>
			<td>
			<a class="link-button" href='#' data-bind="click: $parent.viewGroup , text:'view'"></a> &nbsp;&nbsp;|&nbsp;&nbsp;
			<a class="link-button" href='#' data-bind="click: $parent.editGroup , text:'Edit'"></a> &nbsp;&nbsp;|&nbsp;&nbsp;
			<a class="link-button" href='#' data-bind="click: $parent.deleteGroup , text:'Delete'"></a>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
<script type="text/javascript">
var groupList = ${groupList};
var success = '${success}';
var failure = '${failure}';
var self = new listGroupViewModel();
//creating knockout view model
function listGroupViewModel() {
		var self = this;
		self.groupListArray = ko.observableArray([]);
		for(var index in groupList) { 
			self.groupListArray.push(new addGroup(groupList[index]));
		}
     	self.viewGroup = function(response){
	      	var group = ko.toJS(response);
	      	var url = '/ems-web/ems/project/viewGroup?groupId=';
		      	url = url + group.groupId;
	      		url = url + '&groupName='+group.groupName;
	      	document.location.href = url;
      	};
     	self.editGroup = function(response){
	      	var group = ko.toJS(response);
	      	var url = '/ems-web/ems/project/editGroup?groupId=';
		      	url = url + group.groupId;
	      		url = url + '&groupName='+group.groupName;
	      	document.location.href = url;
      	};
	    self.deleteGroup = function(response){
	    	YUI().use('io-base', 'json-parse', 'json-stringify','node',"io",'array-extras',  
	    			function(Y) {
							      	var group = ko.toJS(response);
										var cfg = {
											method: 'POST',
											on: {
												success: function (id, o) {
													var res = Y.JSON.parse(o.responseText);
													if (res.success == 'true' || res.success == true) {
														self.groupListArray.remove(response);
												      	Y.one('#failure-message').setStyle('display','none');
												      	Y.one('#success-message').setStyle('display','block');
												      	Y.one("#success-message").set('innerHTML', group.groupName+' deleted successfully.');
													}
													else{
														Y.one('#success-message').setStyle('display','none');
											      		Y.one('#failure-message').setStyle('display','block');
											      		Y.one("#failure-message").set('innerHTML', 'unable to delete '+group.groupName);
													}
												},
												failure: function (id, o) {
													Y.one('#success-message').setStyle('display','none');
										      		Y.one('#failure-message').setStyle('display','block');
										      		Y.one("#failure-message").set('innerHTML', 'unable to delete '+group.groupName);
												}
											}
										};
										var answer = confirm('Please confirm to delete '+group.groupName+' Group');
						          	   	 if (!answer) {
						          	   		return false;
						          	   	 }
										Y.io("/ems-web/ems/project/deleteGroup?groupId="+group.groupId, cfg);
	    						});
		};
		/**
		*	method to add group.
		*/      
		function addGroup(group) {
			var self = this;
			self.groupId  = ko.observable(group.groupId);
			self.groupName  = ko.observable(group.groupName);
		}
}
/**
*	method to hide success and failure messages.
*/	
function hideMessage() {
	YUI().use('node', 
			function(Y) {
						setTimeout(function(){
				  		Y.one('#failure-message').setStyle('display','none');
				  		Y.one('#success-message').setStyle('display','none');
      					},5000);
	});
}
/**
*	method to show success and failure messages.
*/
function showMessage() {
	YUI().use('node', 
			function(Y) {
		if(success.length>0)
		{
			Y.one('#success-message').setStyle('display','block');
	  		Y.one("#success-message").set('innerHTML', success);
		}
		if(failure.length>0)
		{
			Y.one('#failure-message').setStyle('display','block');
	  		Y.one("#failure-message").set('innerHTML', failure);
		}
		hideMessage();
	});
}
/**
*	method used to initialize knockout.
*/	
function initKo() {
	ko.applyBindings(self);
	showMessage();
}
</script>
</body>
</html>