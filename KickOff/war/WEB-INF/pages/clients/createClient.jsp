<%@page import="com.epro.framework.security.SecurityHolder"%>
<%@page import="com.epro.framework.security.MenuConstants"%>
<%@page import="com.epro.framework.util.ApplicationConstants"%>
<%@page import="com.epro.framework.login.util.UserSessionInfo"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="../layout/include.jsp"%>    


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 <% 
		UserSessionInfo user = null;
 		String trackId = "";
		String sessionId = "";
		if (session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
			user = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
			sessionId = request.getSession().getId(); 
			trackId = SecurityHolder.MENU_TRACKER_MAPPING.get(MenuConstants.CLIENT_MANAGEMENT) + "," + sessionId;
		}
	%>
</head>
  
<script type="text/javascript">
 
var uid = "<%=trackId%>";
 
	 
	function saveClientCreation(){
		
		if (confirm("Are you confirm to save?")) {
			document.forms['clientCreation'].action = "../clients/saveClient.do?uid="+uid;
		 	document.forms['clientCreation'].submit();
		}
	}

	function cancelOperation(){
		window.location.href = "../clients/showClients.do?uid="+uid;
	}
	
	function checkValidNumber(obj){
  		var optionValue = obj.value;
  		
		var str = obj.value;
		var n = str.match('^[0-9]+$');
		if (!n) {
			obj.value = str.substr(0, str.length - 1);
			return;
		}
		
		if(str.length > 10){
			obj.value = str.substr(0, 10);
			return;
		}
  	}
	
	function checkValidNumberForZipcode(obj){
  		var optionValue = obj.value;
  		
		var str = obj.value;
		var n = str.match('^[0-9]+$');
		if (!n) {
			obj.value = str.substr(0, str.length - 1);
			return;
		}
  	}
	function checkForEmpty(obj){
		var optionValue = obj.value;
		var str = obj.value;
		
		if(str.length == 0){
			obj.value = 0;
			alert("Please Enter the Zip code");
			return;
		}
	}
</script>
<body>
<div class="content">
<span class="successresponse">${response}</span>		
<span class="errorresponse">${errorresponse} </span>		
   <div class="headingborder"  style="float: left;display: inline; margin-top: 1em; border: 1px solid #A28FB5; width: 95%;">
	<form:form modelAttribute="clients" id="clientCreation" method="POST">
	<form:hidden path="clientId"/>
 	<form:hidden path="modifiedDate"/>
 	<form:hidden path="logoPath"/>
<div class="boxHead"> <div class="heading">${headers}</div> </div>
<div>
	 	<form:errors path="*" element="ul" class="errorField"/> 
 	
 			<table align="left" cellspacing="5" cellpadding="2" >
				<tr>
					<td>
						<label class="customLabel">Client Name :</label>
					</td> 
					<td> 
						<form:input class="customInput" path="clientName" maxlength="50" id="tenantName" title="Please enter the Client name"/><span class="mandatory"><sup> *</sup></span> 
					</td>
					<td>
						<label class="customLabel">Description :</label>
					</td>
					<td>
						<form:input class="customInput" cssStyle="width:200px;" maxlength="150" path="description" id="description" title="Please enter the description"/>
					</td>
 				</tr>
				<tr>
				<td>
						<label class="customLabel">Business Type :</label>
					</td> 
					<td> 
						<form:input class="customInput" path="businessType" maxlength="30" id="businessType" title="Please enter the business type"/><span class="mandatory"><sup> *</sup></span> 
					</td>
					<td> 
						<label class="customLabel">Contact Person :</label>
					</td>
					<td> 
						<form:input class="customInput" path="contactPerson" maxlength="30"id="contactPerson" title="Please enter contact person1"/><span class="mandatory"><sup> *</sup></span> 
					</td>
					
				</tr>
				
				<tr>
					<td>
						<label class="customLabel">Address :</label>
					</td> 
					<td> 
						<form:input class="customInput" path="address" id="address" maxlength="100" title="Please enter the address"/><span class="mandatory"><sup> *</sup></span> 
					</td>
					<td>
						<label class="customLabel">City :</label>
					</td> 
					<td> 
						<form:input class="customInput" path="city" id="city" title="Please enter the city" maxlength="20"/><span class="mandatory"><sup> *</sup></span> 
					</td>
					
					 
				</tr>
				
				<tr>
					<td>
						<label class="customLabel">State :</label>
					</td> 
					<td> 
						<form:input class="customInput" path="state" id="state" title="Please enter the state" maxlength="30"/><span class="mandatory" ><sup> *</sup></span> 
					</td>
					<td>
						<label class="customLabel">Country :</label>
					</td> 
					<td> 
						<form:input class="customInput" path="country" id="country" title="Please enter the country" maxlength="50"/><span class="mandatory" ><sup> *</sup></span> 
					</td>
					 
				</tr>
				<tr>
					<td>
						<label class="customLabel">Zip :</label>
					</td> 
					<td> 
						<form:input class="customInput" path="zip" id="zip" maxlength="10" title="Please enter the zip code"/><span class="mandatory"><sup> *</sup></span> 
					</td>
					<td> 
						<label class="customLabel">Work Phone :</label>
					</td>
					<td> 
						<form:input class="customInput" path="workPhone" id="workPhone" maxlength="15" onkeyup="checkValidNumber(this)" title="Please enter work phone"/><span class="mandatory"><sup> *</sup></span> 
					</td>
					
					 
				</tr>
				<tr>
				<td> 
						<label class="customLabel">Mobile Phone :</label>
					</td>
					<td> 
						<form:input class="customInput" path="mobileNumber" id="mobileNumber" maxlength="20" onkeyup="checkValidNumber(this)" title="Please enter mobile phone"/><span class="mandatory"><sup> *</sup></span> 
					</td>
					
					<td> 
						<label class="customLabel">Fax :</label>
					</td>
					<td> 
						<form:input class="customInput" path="faxNo" id="faxNo" onkeyup="checkValidNumber(this)" maxlength="15" title="Please enter fax"/>
					</td>
					 
				</tr>
				
				<tr>
				<td> 
						<label class="customLabel">Email :</label>
					</td>
					<td> 
						<form:input class="customInput" path="emailId" id="emailId" title="Please enter email id" maxlength="100"/><span class="mandatory"><sup> *</sup></span> 
					</td>
					<td>
						<label class="customLabel">Status :</label>
					</td>
					<td align="left"   style="font-size: 12px;">
						<div style="margin-bottom: 8px;" title="Please select the status">
	 						<form:radiobutton path="status" id="active"	name="isActive" value="true" />  &nbsp;Active 
							<form:radiobutton path="status" id="inActive" name="isActive" value="false" /> &nbsp;Inactive 
							<span class="mandatory"><sup> *</sup></span>
						</div>
 					</td>
				</tr>
				<tr>
					<td colspan="2">
					</td>
					<td colspan="2" align="right" >
					<input type="reset" class="button" value="Reset" title="Click to reset the fields">							
						<input type="button" class="button" value="Save" onclick="javascript:saveClientCreation();" title="Click to save Client details">						
						<input type="button" class="button" value="Cancel" onclick="cancelOperation();" title="Click to cancel">
					</td>
 				</tr>
			</table>
			</div>
			</form:form>
		</div>
</div>
</body>
</html>
