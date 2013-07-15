<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="yui3-skin-sam" onload="initKo()"><br>
<div id="userModule">
<form id="UserForm">
<div id="formDiv" align="center" style="display: none;padding-right: 25%;padding-top:45px;" ><br>
 <div id="errorUserMessage" align="center" style="display: none; background: none repeat scroll 0 0 #FFE1D2; color: #FF5C5C; font-size: 12px; padding: 5px; width: 55%; margin-left: 2%;"></div>
  <div style="width:330px; padding:25px; padding-top:25px; border:5px #f1f1f1 solid;" > 
	<table cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<td style="width:40%;"><label>User ID<span class="mandatory">*</span></label></td>
			<td><input id="userId"  name="userId" data-bind="value: userDetails.userId" type="text" style="width: 90%;"></td>
		</tr>
		<tr>
			<td style="width:40%;"><label>Employee Name<span class="mandatory">*</span></label></td>
			<td><input style="width:90%;" name="employeeName" type="text" onkeydown="emplyeeAutoComplete(this);" data-bind="value: userDetails.employeeName,attr: {id:'employeeName'}"></td>
		</tr>
		<tr>
			<td style="width:40%;"><label>Password<span class="mandatory">*</span></label></td>
			<td><input id="password"  name="password" data-bind="value: userDetails.password" type="password" style="width: 90%;" ></td>
		</tr>
		<tr>
			<td style="width:40%;"><label>Role<span class="mandatory">*</span></label></td>
			<td><select id="roleId"  name="roleId" data-bind="value: userDetails.roleId,optionsValue: 'id', optionsText: 'value',options: userDetails.roleArray, optionsCaption: 'Select',attr: {'title': $root.setTitle(userDetails.roleArray(), userDetails.roleId)}" type="text" style="width: 90%;">
			</select></td>
		</tr>
		<tr>
		<td colspan="2">
			<div class="clearfix" style="margin: 1% 2%;margin-left: 103px" align="center">
				<div data-bind='click: saveUser' class="normal-button" style="width: 75px; float: left; margin: 5px 0 5px 27px;">Save</div>
				<div data-bind="click: cancelUser" class="normal-button" style="width: 65px; float: left; margin: 5px 0; margin-left: 25px">Cancel</div>
			</div>
		</td>
		</tr>
	</table>	
 </div>
</div>
</form>
<div id="viewUser" align="center" style="display: none;padding-right: 25%;padding-top:80px;">
 <div style="width:370px; padding:25px; padding-top:25px; border:5px #f1f1f1 solid;" > 
	<table cellspacing="10" cellpadding="0">
		<tr>
			<td style="width:50%"><label>User ID</label></td>
			<td> <span  data-bind="text:': '+userDetails.userId()" style="float: left;"></span></td>
		</tr>
		<tr>
			<td style="width:50%"><label>Employee Name</label></td>
			<td> <span  data-bind="text: ': '+userDetails.employeeName()" style="float: left;"></span></td>
		</tr>
		<!-- <tr>
			<td style="width:40%;"><label>Password<span class="mandatory">*</span></label></td>
			<td>: <span  data-bind="text: userDetails.password"></span></td>
		</tr> -->
		<tr>
			<td style="width:50%"><label>Role</label></td>
			<td> <span  data-bind="text: ': '+userDetails.roleName()" style="float: left;"></span></td>
		</tr>
		<tr>
			<td colspan="2">
			<div align="center" style="padding-right:76px"><div class="normal-button" id="cancel" data-bind='click: cancelUser' style="width:65px; margin-left: 75px">Back</div></div>
			</td>
		</tr>
	</table>	
 </div>
</div>
<br>
	<div id="successMessage" align="center" style="display: none; background: none repeat scroll 0pt 0pt #DFF0D9;; color: #279800; font-size: 12px; padding: 5px; width: 95%; margin-left: 2%;"></div>
	<div id="dataTableContent" style="display: none;">
		<div id="Datatable"></div>
	</div>
	<div style="display: none;" class="demo-description"></div>
</div>
<script type="text/javascript">
var userValid = false;
var dataTable = null;
var userViewModel;
var sourceForm=null;
var exuserId=null;
var roleList = ${employeeRoles};
var pageType = '${pageType}';
var intialuserInfo = { employeeId:'',
						   userId:'',
						 password:'',
						 roleName:'',
					userFirstName:'',
					 userLastName:''
						};
/**
 * This method is used to create datatable
 */						
var optionFormatter = function(o){
	var id= o.record.get("userId");
	var roleId= o.record.get("roleId");
	var hlink = "";																																									
		hlink='<div class="yui3-datatable-width"><a class="link-button" onclick=\'viewUser(\"'+id+'\",\"'+roleId+'\")\'/>view</a> | <a class="link-button" onclick=\'editUser(\"'+id+'\",\"'+roleId+'\")\'/>edit</a>  | <a class="link-button" onclick=\'deleteUser(\"'+id+'\",\"'+roleId+'\")\'/>delete</div>';
	return hlink;
};
var dataTableColumns = [  {key: "employeeId",label: "Employee ID", name: 'employeeId'},
                          {key: "userId",label: "User ID", name: 'userId'},
                          {key: "roleName", label: "Role Name",  name: 'roleName'},
                          {key: "roleId", label: "Role ID",  name: 'roleId',className: 'hidden-col'},
                           {label:"Options", formatter:optionFormatter,allowHTML:true, className:'options'}
                      ];
var dataTableSchema = {
	  sync: true,
	  resultListLocator: "userList",
      resultFields: [
          {key:"employeeId"},
          {key:"userId"},  
          {key: "roleName"},
          {key: "roleId"}              
       ]
      };

function getListDetails() {
	var isGetDetails = '${datasource}';
		if(isGetDetails != ""){
			getUserDetails();
		}else{
			YUI().use("node",function(Y){
				Y.one("#formDiv").setStyle("display","block");
			});
		}
}

/**
 * This method is used to call the list of user information
 */
function getUserDetails() {
	YUI().use("datasource-io","datatable-message","datasource-jsonschema", "datatable-datasource", function(Y) {
		Y.one("#Datatable").setHTML("");
		Y.one("#formDiv").setStyle("display","none");
		Y.one("#viewUser").setStyle("display","none");
		Y.one("#successMessage").setStyle("display","none");
		Y.one("#dataTableContent").setStyle("display","block");
		var config = {
			sync:true,
			id: "#Datatable",
			dataSrcUrl :  "/ems-web/ems/login/getUser",
			dataSrcQueryStr: "",
			dataSrcMethod: 'POST',
			tableColumns : dataTableColumns,
			dataTableSchema: dataTableSchema
		};
		dataTable = DataTable(config);
	});
}

/**
 * This method is used to call the view user information
 */
function viewUser(id,roleId) {
	YUI().use('io-base', 'node', "json-parse","io", function(Y) {
		Y.one("#loadingImage").setStyle("display","block");
		var queryStr = 'loginId='+id+'&roleId='+roleId;
		var cfg = {
			method: 'POST',
			data: queryStr,
			on: {
				success: function (id, o) {
					var response = Y.JSON.parse(o.responseText);
					if (response.user != "undefined") {
						userViewModel.setUserValues(response.user);
						Y.one("#formDiv").setStyle("display","none");
						Y.one("#viewUser").setStyle("display","block");
						Y.one("#dataTableContent").setStyle("display","none");
						Y.one("#successMessage").setStyle("display","none");
						Y.one("#loadingImage").setStyle("display","none");
					}
				}
			}
		};
		Y.io("/ems-web/ems/login/edit", cfg);
	});
}

/**
 * This method is used to call the edit user information
 */
function editUser(id,roleId) {
	userViewModel.setUserValues(intialuserInfo);
	AppViewModel.errors.showAllMessages(false); 
	YUI().use('io-base', 'node', "json-parse","io", function(Y) {
		Y.one("#loadingImage").setStyle("display","block");
		var queryStr = 'loginId='+id+'&roleId='+roleId;
		var cfg = {
			method: 'POST',
			data: queryStr,
			on: {
				success: function (id, o) {
					var response = Y.JSON.parse(o.responseText);
					if (response.user != "undefined") {
						userViewModel.setUserValues(response.user);
						Y.one("#formDiv").setStyle("display","block");
						Y.one("#viewUser").setStyle("display","none");
						Y.one("#dataTableContent").setStyle("display","none");
						Y.one("#successMessage").setStyle("display","none");
						Y.one("#loadingImage").setStyle("display","none");
						Y.one("#userId").set("disabled",true); 
						Y.one("#password").set("disabled",true);
							if(response.pageType == 'edit'){
								userValid = true;
								pageType = response.pageType;
								exuserId=response.userId;
							}
						userViewModel.userDetails.employeeName.extend({validateEmployeeName: true });
					}
				}
			}
		};
		Y.io("/ems-web/ems/login/edit", cfg);
	});
}

/**
 * This method is used to call the delete user information
 */
function deleteUser(id,roleId) {
	var answer = confirm("Please confirm to delete "+id+" ");
	if (!answer) {
		return false;
	}
	YUI().use('io-base', 'node', "json-parse","io", function(Y) {
		Y.one("#loadingImage").setStyle("display","block");
		var queryStr = 'loginId='+id+'&roleId='+roleId;
		var cfg = {
			method: 'POST',
			data: queryStr,
			on: {
				success: function (id, o) {
					var response = Y.JSON.parse(o.responseText);
					if (response.success == true || response.success == "true") {
					Y.one("#loadingImage").setStyle("display","none");
						dataTable.datasource.load({request: ''});
						Y.one("#successMessage").setHTML("User Information deleted successfully").setStyle("display","block");
					}
				}
			}
		};
		Y.io("/ems-web/ems/login/delete", cfg);
	});	 
}

userViewModel = new AppViewModel();
					
function User(roleList){
	this.id = roleList.id;
	this.value = roleList.value;
};					

function AppViewModel() {
var self = this;

    /**
	 * This method is used to check field is empty
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
	* This method is used to check valid employee name or not
 	*/   
	ko.validation.rules['validateEmployeeName'] = {
		validator: function (val, required) {
			if(userValid){
				return true;
			}
			else{
				return false;
			}
		},
		message: 'Invalid Employee Name.'
	};
	ko.validation.registerExtenders();
	
	self.userDetails = {
		userId : ko.observable("").extend({ required: 'User Id'}),
		employeeName : ko.observable("").extend({ required: 'Employee Name', validateEmployeeName: true}),
		employeeId : ko.observable(),
		password : ko.observable("").extend({ required: 'Password'}),
		roleId : ko.observable().extend({ required: 'Role'}),
		roleArray : ko.observableArray([]),
		roleName : ko.observable()
	};
	ko.mapping.fromJS(roleList, {}, self.userDetails.roleArray);

	AppViewModel.errors = ko.validation.group(self.userDetails,{deep: false});
	AppViewModel.errors.showAllMessages(false);
   	
   	/**
	 * This function is used to call the save user information
 	 */			
	self.saveUser = function(){
		if (AppViewModel.errors().length == 0) {
		var userInfo="";
			YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y){
				if(exuserId != self.userDetails.userId()){
					sourceForm="create";
				}else{
					sourceForm="edit";
				}
				var form = Y.one('#UserForm');
				userInfo = Y.QueryString.stringify(Y.Array.reduce(Y.one(form).all('input[name],select[name],textarea[name]')._nodes, {}, function (init, el, index, array) {
				init[el.name] = el.value;
				return init;					
				}));
				userInfo = userInfo+'&sourceFrom='+sourceForm+'&employeeId='+self.userDetails.employeeId(); 
				//var userInfo = 'userId='+self.userDetails.userId()+'&employeeId='+self.userDetails.employeeId()+'&password='+self.userDetails.password()+'&role='+self.userDetails.roleId()+'&sourceForm='+sourceForm;
				var cfg = {
					method: 'POST',
					data: userInfo,
					on: {
						success: function (id, o) {
							if (o.responseText != null) {		
								var response = Y.JSON.parse(o.responseText);
								if (response.success == true || response.success == "true") {
									Y.one("#errorUserMessage").setStyle("display","none");
									getUserDetails();
									Y.one("#loadingImage").setStyle("display","none");
								}else{
									Y.one("#errorUserMessage").setHTML("User ID already exists").setStyle("display","block");
									setTimeout(function(){
										Y.one('#errorUserMessage').setStyle('display','none');
									},6000);
								}								
							}								
						}
					}
				};
				Y.io("/ems-web/ems/login/saveUser", cfg);	  				
			});
		}else{
			AppViewModel.errors.showAllMessages(); 
		}
	}
	
	/**
	 * This method is used to call cancel user information
 	 */	
	self.cancelUser = function(){
		getUserDetails();
	}
	
	/**
	 * This method is used to assign user information
 	 */		
	self.setUserValues = function(response) {
		if(response!==null){
			 ko.mapping.fromJS(response, {}, self.userDetails);
			 ko.mapping.fromJS(response.roleId, {}, self.userDetails.roleId);
			 ko.mapping.fromJS(response.userFirstName+' '+response.userLastName, {}, self.userDetails.employeeName);
		 }
	};
	
	/**
	 * This method is used to set title
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
}

function initKo() {
	ko.applyBindings(userViewModel);
	getListDetails();
}

emplyeeAutoComplete = function(object){
	userValid = false;
	YUI().use('node','node-core','io-base','json-parse','json-stringify','event', function(Y){
	var textCallback =  function (result) {
		if(Y.Lang.isValue(result)){
			var cellHTML =  '';
			if(object.id.indexOf('employeeName') >= 0)
				if(result.employeeId != undefined){
					cellHTML =  result.firstName+' '+result.lastName;
				}
			return cellHTML;
		}
	};
	var selectCallback =  function (ev) {
		if(Y.Lang.isValue(ev.result)){
			var resultObj = ev.result.raw;
				if(resultObj.employeeId != undefined){
					userViewModel.userDetails.employeeId(resultObj.employeeId);
					userValid = true;
				}
				userViewModel.userDetails.employeeName.extend({validateEmployeeName: true });
		}
		};
    var acUrl = "/ems-web/ems/project/employeeLookup?&columnType=employee_name"+"&projectId=''"+"&employeeId=";
    Y.use('autocomplete', 'autocomplete-highlighters', function(Y) {
	   var input = AutoComplete({
    	   inputId: object.id,
    	   resultsLocator:'employees',
    	   queryUrl: acUrl,
    	   maxResults: 10,
    	   textLocatorCallback: textCallback,
    	   onSelectCallback: selectCallback
    	 });
	});
	});
};	
</script>
</body>
</html>