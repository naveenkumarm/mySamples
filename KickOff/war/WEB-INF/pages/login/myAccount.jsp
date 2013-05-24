<%@page import="com.epro.framework.util.ApplicationConstants"%>
<%@page import="com.epro.framework.security.SecurityHolder"%>
<%@page import="com.epro.framework.login.util.UserSessionInfo"%>
<%@page import="com.epro.framework.security.MenuConstants"%>
<%@include file="../layout/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.epro.framework.model.Users"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Account</title>

<script type="text/javascript">
alert('true')
	function modifyUser()
	{
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		var disVisibility = document.getElementById("pass").style.display;
		
		if(disVisibility != "none"){
			if(password == confirmPassword ){
				document.forms['myAccountForm'].action = "updateMyAccount.do";
				document.forms['myAccountForm'].submit();
			}else{
				customAlert("Please Enter the same confirm password");
			}
		}else {
			document.forms['myAccountForm'].action = "updateMyAccount.do";
			document.forms['myAccountForm'].submit();
		}
	}
	
	function showNewPassword()
	{
		if( document.getElementById("pass").style.display == ''){
			document.getElementById("pass").style.display = 'none';
			document.getElementById("confirmpass").style.display = 'none';
		}
		else{
	   		document.getElementById("pass").style.display = '';
	   		document.getElementById("confirmpass").style.display = '';
		}
	}
	
	function cancel()
	{
		window.location.href="../campaign/home.do";	
	}
	
 	function customAlert(message){
		
		document.getElementById('dialog-alert').innerHTML = "<font size='2px'>"+message+"</font>";
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		$( "#dialog-alert" ).dialog({
			title:'<font size="2px"> Alert </font>',
			resizable: false,
			height:130,
			width:300,
			modal: true,
			buttons: {
				Ok: function() {
					$( this ).dialog( "close" );
				}
			}
		});
	 } 		
 	
  
</script>
</head>
<body>
<div class="content">
<div id="dialog-confirm"></div>
<div id="dialog-alert"></div>
 
 		<span class="successresponse">${update}</span>
		<span class="errorresponse">${errorResponse}</span>
 	<span class="errorresponse" style="text-align: left;margin-left: 15px;">${SameEmailExist}</span> 
 <div class="headingborder"  style="float: left;margin-top:8px; border: 1px solid #A28FB5;width: 95.1%;">
<div class="boxHead">
		<span class="heading">My Account</span>
	</div>
<form:form action="updateMyAccount.do" id="myAccountForm" modelAttribute="LOGGED_IN_USER" method="POST">
	<form:hidden readOnly="true" path="userId" id="userId" name="userId" size="30" autocomplete="off"/>
	<form:errors cssClass="errorField" path="password" element="li"></form:errors>
	<table align="left">
		<tr>
			<td align="right" width="120px" >
				<label class="customLabel" for="username">Username:</label>
			</td>
			<td>
				<form:input class="customInput" path="userName" name="username"  readOnly="true" />
	 		</td>
			<td align="left"><a style="margin-left:-15px" class="text"   href="javascript:showNewPassword();"><font color="blue">Change Password</font></a></td>
		</tr>
		 
		<tr id="pass" style="display: none">
			<td align="right">
				<label class="customLabel" for="password">New Password:</label>
			</td>
			<td>
				<form:password path="password" class="textbox" name="password" id="password"/>
			</td>
		</tr>
		<tr id="confirmpass" style="display: none">
			<td align="right">
				<label class="customLabel" for="password">Confirm Password:</label>
			</td>
			<td>
				<input type="password" class="textbox" name="confirmPassword" id="confirmPassword"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="customLabel" for="Email">Email:</label>
			</td>
			<td>
				<form:input class="customInput" path="emailId" name="email"  />
				<span class="mandatory"><em>*</em></span>
			</td>
			<td>
				<font color="red"> &nbsp;&nbsp;
					<form:errors path="emailId" cssClass="errorMessage" />
				</font>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="customLabel" for="Firstname">First Name:</label>
			</td>
			<td>
				<form:input class="customInput" path="firstName" name="firstname"/>
				<span class="mandatory"><em>*</em></span>
			</td>
			<td>
				<font color="red"> &nbsp;&nbsp;
					<form:errors path="firstName" cssClass="errorMessage"/>
				</font>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="customLabel" for="Lastname">Last Name:</label>
			</td>
			<td>
				<form:input class="customInput" path="lastName" name="lastname"/>
				<span class="mandatory"><em>*</em></span>
			</td>
			<td>
				<font color="red">&nbsp;&nbsp;
					<form:errors path="lastName" cssClass="errorMessage"/>
				</font>
			</td>
		</tr>
		 
		<tr>
			<td align="left" colspan="3">
				<input style="margin-left:45px"  class="button" type="button" value="Modify" onclick="return modifyUser();" /> 
			 
				<input class="button" type="reset" value="Reset"/> 
			 
 			</td> 
		</tr>
	</table>
</form:form>
</div>
</div>
 
</body>
</html>