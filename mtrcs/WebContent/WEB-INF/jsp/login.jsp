<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="jwr" uri="http://jawr.net/tags-el"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jwr:style src="/bundles/ems.css"/>
<jwr:script src="/bundles/yui3init.js"/>
<html>
<head>
<link rel="shortcut icon" href="/ems-web/images/favicon.ico" >
<title>Log-on</title>
</head>
<body>
<div id="header">
<div class="logo"></div>
</div>

<div id="loginPage" align="center">
<form:form action="${pageContext.request.contextPath}/j_spring_security_check" method="post" id="loginForm" name="loginForm">
<div class="login-bg clearfix">
	<div class="validationMessage"  id="errors" style="display: none;">
		User name or password cannot be empty!
	</div>
	<div id="loginErrorMessage"  class="validationMessage">${error}</div>
	<div class="login-row">
		<label>Login ID</label>
		<input id="j_username" name="j_username" type="text" style="width:249px;" >
	</div>
	<div class="login-row" style="margin-top:10px;">
		<label>Password</label>
		<input id="j_password" name="j_password" type="password" style="width:249px;">
	</div>
	<div class="login-row" style="margin-top:10px; color: #126F92;" align="left">
	<span onclick="forgetPassword();" style="cursor: pointer;">
	<u> Forget Password?</u>
	</span>
	<span>
	   <input name="submit" type="submit"  value="Login" class="normal-button" onclick="return login();" style="width:75px; float:right;height: auto;" />
	 </span>
	</div>
</div>
</form:form>
</div>
<div id="forgetPassword" style="border: 5px solid #F1F1F1;display: none;margin: 14% 0 0 37%;position: fixed;width: 25%; background: #E7E6E4; padding: 1%;" align="center" class="clearfix">
	<div class="validationMessage"  id="error_message" style="display: none;"></div>
	<div id="success_message" style="display: none; background: none repeat scroll 0 0 #DFF0D9; color: #279800; font-size: 12px;padding: 5px; width:95%;"></div>
	<div class="login-row clearfix">
		<label>Login ID</label>
		<input id="forgetLoginId" name="forgetLoginId" type="text" style="width: 85% !important;" autocomplete="off">
	</div>
	<div style="margin-top: 20px; color: #126F92;" class="login-row clearfix" align="left">
	<span onclick="loginView();" style="cursor: pointer;">
	<u>Login Page</u>
	</span>
	<span>
		<input style="margin-left: 44%;padding: 0 2px 0 2px;" align="right" class="normal-button" onclick="sendPassword();" type="button" value="send">
	</span> 
	</div>
</div>
</body>
<script type="text/javascript">
function login(){
	 var userName = document.getElementById('j_username').value;
	 var password = document.getElementById('j_password').value;
	 
	 if(userName == '' || password == ''){
		 document.getElementById('loginErrorMessage').style.display = 'none';
		 document.getElementById('errors').style.display = 'block';
		 return false;
	 }
	else {
		document.getElementById('loginErrorMessage').style.display = 'none';
		document.getElementById('errors').style.display = 'none';
	 }
	 return true;
}
function forgetPassword() {
	YUI().use("node",function (Y) {
	Y.one('#loginPage').setStyle('display','none');
	Y.one('#forgetPassword').setStyle('display','block');
	});
}
function sendPassword() {
	var loginId = '';
	YUI().use('io-base', 'json-parse', 'json-stringify','node', function(Y) {
		loginId = Y.one('#forgetLoginId').get('value');
		//Y.one('#error_message').setStyle('display','none');
		if(loginId.length == 0 || loginId == null || loginId == undefined){
			Y.one('#error_message').setHTML('Please Enter Login Id!').setStyle('display','block');
		}
		else{
			Y.io("/ems-web/ems/login/forgetPassword?loginId="+loginId, {
				method: 'POST',
				on: {
				success: function (id, o) {
				var response = Y.JSON.parse(o.responseText);
				var status = response.status;
				if(status == 'true' || status == true)	{
					Y.one('#success_message').setHTML('Password has been sent to your mail id.').setStyle("display","block");
					Y.one('#error_message').setStyle('display','none');
				}
				else{
					Y.one('#error_message').setHTML('Invalid Login Id!').setStyle('display','block');
					Y.one('#success_message').setStyle("display","none");
				}
				},
				failure: function (id, o) {
				alert("Async call failed");
				}
			}
			});	
		}
		});
}
function loginView() {
	YUI().use("node",function (Y) {
		Y.one('#loginPage').setStyle('display','block');
		Y.one('#forgetPassword').setStyle('display','none');
		Y.one('#error_message').setStyle('display','none');
		Y.one('#success_message').setStyle('display','none');
		});
}
</script>
</html>
