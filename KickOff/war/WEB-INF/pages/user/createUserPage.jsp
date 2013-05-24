<%@page import="com.epro.framework.security.SecurityHolder"%>
<%@page import="com.epro.framework.security.MenuConstants"%>
<%@page import="com.epro.framework.util.ApplicationConstants"%>
<%@page import="com.epro.framework.login.util.UserSessionInfo"%>
<%@include file="../layout/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<%
  	UserSessionInfo user = null;
 	String trackId = "";
	String sessionId = "";
	
	if (session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
		user = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
		sessionId = request.getSession().getId(); 
 		trackId = SecurityHolder.MENU_TRACKER_MAPPING.get(MenuConstants.USER_MANAGEMENT) + "," + sessionId;
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User</title>
<style type="text/css">
.ui-datepicker-title select{
font-family: tahoma, Arial, Helvetica, sans-serif;
font-size: 0.8em !important;
color: #000;
font-weight: normal;
}
.customLabel{
width: 97%!important;
}
</style>
<script type="text/javascript">

var uid="<%=trackId%>";

	function saveNewUser(){
	 		
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		
		if(password != confirmPassword){
			document.getElementById("formErrors").innerHTML = "Password and confirm password does not match."
			return; 
		}
					
	    document.forms['createUser'].action = "saveNewUser.do?uid=" + uid;
        document.forms['createUser'].submit();

	}

	function cancelUser() {
	  window.location.href = "getUser.do?uid=" + uid;
	}

	window.onload = function() {
		$('#adminMenu').addClass("menuActive");
	}

	function clearRole() {
		$("#roleId").html("");
		var html = '<option value="0">Select Role</option>';
		$('#roleId').append(html);
	}

	function showRole(roleLevel, tenantId, subTenantId) {
		$
				.get(
						"getRoleInfo.do?uid=" + uid + "&roleLevel=" + roleLevel
								+ "&tenantId=" + tenantId + "&subTenantId="
								+ subTenantId,
						function(response) {
							$("#roleId").html("");
							var parseJsonObject = jQuery.parseJSON(response);
							var total = parseJsonObject.total;
							var success = parseJsonObject.success;
							var dataArray = parseJsonObject.roleInfo;
							var html = '<option value="0">Please Select a Role</option>';
							for ( var i = 0; i < dataArray.length; i++) {

								html += '<option value="' + dataArray[i].roleId + '">'
										+ dataArray[i].roleName + '</option>';
							}
							$('#roleId').append(html);

							document.getElementById('roleId').disabled = false;
						}, 'html');
	}
</script>
</head>
<body>
	<div class="content">
 	
 
		<br>
		<div align="right">
			<label class="customLabel"style="font-size: 12px;text-align: left;margin-left: 5px;"><font class="errorresponse">${errorresponse}</font></label>
		</div>
		<div class="headingborder"
			style="float: left; display: inline; margin-top: 1em; border: 1px solid #A28FB5; margin-left: 0.4em; margin-bottom: 1em; width: 95%;">
			<div class="boxHead">
				<div class="heading">${headers}</div>
			</div>
			<form:form id="createUser" modelAttribute="user" method="POST" autocomplete="off">
				<div Class="errorField" id="formErrors"></div>
				<form:errors path="*" element="ol" errorElement="li" cssClass="errorField"/>
				<form:hidden path="userId" id="userId" class="textbox" name="userId" />

				<table width="90%" align="left" style="margin-left: 0px;">
					 
					<tr>
						<td><label class="customLabel">Role :</label>
						</td>
						<td><form:select class="customSelect" path="roles.roleId" id="roleId" title="Please select a role"
								style="width:178px;">
								<form:option value="-1">Select Role</form:option>
								<form:options items="${roleInfoList}" itemValue="roleId"
									itemLabel="roleName" />
							</form:select>&nbsp;<span class="mandatory">*</span>
						</td>
						<td><label class="customLabel">Employee Id :</label>
						</td>
						<td><form:input class="customInput" path="employeeId" title="Please enter the Employee Id" maxlength="10" size="30" />&nbsp;<span class="mandatory">*</span>
						</td>
					<tr>
 					</tr>
					<tr>
						<td><label class="customLabel">First Name :</label>
						</td>
						<td><form:input class="customInput" path="firstName" name="firstName" title="Please enter the first name" maxlength="30"
								id="firstName" size="30" />&nbsp;<span class="mandatory">*</span>
						</td>
							<td><label class="customLabel">Work phone1 :</label>
						</td>
						<td nowrap="nowrap">
						<table width="100%">
						<tr>
						<td><form:input class="customInput" path="workingPhone1" name="wphone1" title="Please enter the Work phone1 " maxlength="20"
								id="wphone1" size="30" />&nbsp;<span class="mandatory">*</span>
						</td>
						<td align="right"><label class="customLabelExtn" style="margin-top: -5px">Extn :</label></td>
						<td> <form:input class="customInput" path="extn1" maxlength="6" name="extn" id="extn1" size="4"  title="Please enter the extn"/></td>
						</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td><label class="customLabel">Last Name :</label>
						</td>
						<td><form:input class="customInput" path="lastName" name="lastName" id="lastName" title="Please enter the last name" maxlength="30"
								size="30" />&nbsp;<span class="mandatory">*</span>
						</td>
						<td><label class="customLabel">Work phone2 :</label>
						</td>
						<td>
							<table width="101%">
							<tr>
							<td><form:input class="customInput" path="workingPhone2" name="wphone2"  title="Please enter the Work phone2 " maxlength="20"
									id="wphone2" size="30" />
							</td>
							<td align="right"><label class="customLabelExtn" style="margin-top: -5px"> Extn :</label></td>
							<td> <form:input class="customInput" path="extn2" maxlength="6" name="extn" id="extn2" size="4"  title="Please enter the extn"/></td>
							</tr>
							</table>
						</td>
						
					</tr>
					<tr>
 						<td><label class="customLabel">Mobile phone1 :</label>
						</td>
						<td><form:input class="customInput" path="mobileNumber1" title="Please enter the Mobile phone1" maxlength="20"
								name="mobilePh1" id="mobilePh1" size="30" />&nbsp;<span
							class="mandatory">*</span>
						</td>
					 
 						<td><label class="customLabel">Mobile phone2 :</label>
						</td>
						<td><form:input class="customInput" path="mobileNumber2" title="Please enter the Mobile phone2" maxlength="20"
								name="mobilePh2" id="mobilePh2" size="30" />
						</td>
					</tr>
					<tr>
						<td><label class="customLabel">User Name :</label>
						</td>
						<td><form:input class="customInput" path="userName" name="userName" id="userName" title="Please enter the login username" maxlength="50"
								size="30" />&nbsp;<span class="mandatory">*</span>
						</td>
						<td><label class="customLabel">Department :</label>
						</td>
						<td><form:input class="customInput" path="department" title="Please enter the department" maxlength="50"
								size="30" />&nbsp;<span class="mandatory">*</span>
						</td>
 					</tr>
					<tr>
						<td><label class="customLabel">Password :</label>
						</td>
				 		<td><input type='password' class='customInput' title="Please enter the confirm password" name="confirmPassword" value="${user.password}" id="password" size="30" maxlength="50"/>&nbsp;<span
							class="mandatory">*</span>
						</td>
						<td><label class="customLabel">Email :</label>
						</td>
						<td><form:input class='customInput' path="emailId" name="email" title="Please enter the Email Id" maxlength="50"
								id="email" size="30" />&nbsp;<span class="mandatory">*</span>
						</td>
					</tr>
					<tr>	
						<td><label class="customLabel">Confirmation Password :</label>
						</td>
						<td><form:input path="password" type='password' class='customInput' title="Please enter the confirm password" name="confirmPassword" value="${user.password}" id="confirmPassword" size="30" maxlength="50"/>&nbsp;<span
							class="mandatory">*</span>
						</td>
							<td ><label class="customLabel">Status :</label>
						</td>
						<td><form:radiobutton path="status" name="status" title="Please select the status" id="active"
								value="true" />&nbsp;Active&nbsp;&nbsp;&nbsp;&nbsp; <form:radiobutton
								path="status"  id="inActive" name="status" value="false" title="Please select the status" />&nbsp;InActive
						</td>
					</tr>
					<tr>
						<td colspan="6" align="right">
							<input type="reset" title="Click to reset all fields" class="button">
							<input class="button" type="button" id="saveEditedUser" title="Click to save user details"
									name="saveEditedUser" value="${Save}" onclick="saveNewUser();">
							<input class="button" type="button" id="cancel" name="cancel" title="Click to cancel"
									value="Cancel" onclick="cancelUser();">
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
	  

</body>
</html>