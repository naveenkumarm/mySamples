<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div id="formDiv" align="center" style="padding-right: 25%;padding-top:80px;" >
<div id="successMessage" align="center" style="display: none; background: none repeat scroll 0pt 0pt #DFF0D9;; color: #279800; font-size: 12px; padding: 5px; width: 55%;">Password successfully changed</div>
<br>
<div style="width:330px; padding:25px; padding-top:25px; border:5px #f1f1f1 solid;" > 
<table cellspacing="0" cellpadding="0" style="width:100%;">
	<tr>
	    <td style="width:40%;"><label>Old password <span class="mandatory">*</span></label></td>
		<td><input id="oldPassword"  name="oldPassword" data-bind="value: changepassword.oldPassword" type="password" style="width: 90%;">
		    <div id="errorMessage" class="validationMessage" style="text-align: center; display: none;"></div></td>
	</tr>
	<tr>
	    <td style="width:40%;"><label>New password <span class="mandatory">*</span></label></td>
		<td><input id="newPassword"  name="newPassword" data-bind="value: changepassword.newPassword" type="password" style="width: 90%;"></td>
	</tr>
	<tr>
	    <td style="width:40%;"><label>Confirm password <span class="mandatory">*</span></label></td>
		<td><input id="confirmPassword"  name="confirmPassword" data-bind="value: changepassword.confirmPassword" type="password" style="width: 90%;">
		    <div id="errorMessage1" class="validationMessage" style="text-align: center; display: none;">Password do not match</div></td>
	</tr>
	<tr>
		<td colspan="2">
		<div class="clearfix" style="margin: 1% 2%;margin-left: 103px" align="center">
			<div data-bind='click: savePassword' class="normal-button" style="width: 115px; float: left; margin: 5px 0 5px 5px;">Change Password</div>
			<div data-bind="click: cancelPassword" class="normal-button" style="width: 65px; float: left; margin: 5px 0; margin-left: 13px">Cancel</div>
		</div>
	 </td>
	</tr>
</table>	
</div>
</div>
<script type="text/javascript">
var loginId = '${loginId}';
var oldpassword='${oldpassword}';

var changePasswordViewModel = '';
var intialValue = {oldPassword :'',
					newPassword:'',
					confirmPassword:''};
function AppViewModel() {
		var self = this;
		 self.changepassword = {
			oldPassword : ko.observable("").extend({required: {message: 'Old Password is required'}, oldPasswordValidation: true}),
			newPassword : ko.observable("").extend({ required: {message: 'New Password is required'}}),
			confirmPassword : ko.observable("").extend({ required: {message: 'Confirm Password is required'}})
			};
	  
	  AppViewModel.errors = ko.validation.group(self.changepassword,{deep: false});	

      self.savePassword = function(){
	      YUI().use('io-base', 'node', "json-parse",'array-extras', 'querystring-stringify',"io", function(Y) {
		      if(AppViewModel.errors().length == 0){
			      if(self.changepassword.newPassword()==self.changepassword.confirmPassword()){
					  Y.one("#errorMessage").setStyle("display","none");
					  Y.one("#errorMessage1").setStyle("display","none");
					  Y.one("#successMessage").setStyle("display","none");
					  var employeeInfo = 'loginId='+loginId+'&password='+ self.changepassword.newPassword();
					  var cfg = {
						  method: 'GET',
						  sync: true,
						  data: employeeInfo,
						  on: {
							  success: function (id, o) {										  
								if (o.responseText != null) {
								   ko.mapping.fromJS(intialValue, {}, self.changepassword);
								   Y.one("#successMessage").setStyle("display","block");
								   setTimeout(function(){
								       Y.one('#successMessage').setStyle('display','none');
								   },6000);
								   AppViewModel.errors.showAllMessages(false); 
								}
							  }
						  }
					  };
					  Y.io("/ems-web/ems/login/savePassword", cfg);	  				
				 }
				 else
				 {
				 	Y.one("#errorMessage1").setStyle("display","block");
				 }
			 }else{
				 AppViewModel.errors.showAllMessages(true); 
			 }
		 });
       };
		self.cancelPassword = function(){
			document.location.href="/ems-web/ems/landing/view";
		};		
	}
	ko.validation.rules['oldPasswordValidation'] = {
		validator: function (val, required) {
			if(val != '' && val != undefined){
			var currentPassword = val;
				if (oldpassword != currentPassword) {
					return false;
				 }else{
					return true;
				 }
			  }
			},
			message: 'Invalid old password'
	  	 };
	ko.validation.registerExtenders();
	
	changePasswordViewModel = new AppViewModel();
	ko.applyBindings(changePasswordViewModel);
</script>
</body>
</html>