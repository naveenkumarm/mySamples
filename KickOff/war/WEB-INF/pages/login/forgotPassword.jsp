<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="../layout/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script>

	String.prototype.RTrim = function() {
		return this.replace(/(\s*$)/g, "");
	}
	
	String.prototype.LTrim = function() {
		return this.replace(/(^\s*)/g, "");
	}
	
	function resetPassword(){
		
		if((document.getElementById('userName').value.RTrim().LTrim()=="" || document.getElementById('userName').value.RTrim().LTrim()==null)  && (document.getElementById('email').value.RTrim().LTrim()=="" || document.getElementById('email').value.RTrim().LTrim()==null)){
			customAlert("Please Enter UserName (or) Email");
			document.getElementById('userName').focus();
			return false;
		}
		
		var userName=document.getElementById('userName').value.RTrim();
		var email=document.getElementById('email').value.RTrim();
		
		window.location.href ="resetPassword.do?userName="+userName+"&email="+email;
	}
	
	function login(){
		window.location.href ="../login/showLogin.do"
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
<body>
<div  class="content">
<div id="dialog-confirm"></div>
<div id="dialog-alert"></div>

 <span class="successresponse">${SUCCESS}</span>

 <div style="float: left; margin-left: 15px;">
 	<span style="display: inline;"><font id="errorMessageId" style="font-size: 11px;" class="errorresponse">${ERROR}</font></span>
</div>
<br>
<div class="headingborder"  style="float: left;display: inline; border: 1px solid #A28FB5; width: 95%;">
<div class="boxHead">
	<div class="heading">Forgot Password</div>
</div>

<table>
	<tr>
		<td align="right">
			<label class="customLabel">UserName:</label>
		</td>
		<td>
			<input class="customInput" type="text" name="userName" id="userName" />
		</td>
	</tr>
	<tr>
		<td >
		</td>
		<td align="center" valign="middle">(or)</td>
	</tr>
	<tr>
		<td align="right">
			<label class="customLabel">Registered Email:</label>
		</td>
		<td>
			<input class="customInput" type="text"  name="email" id="email"/>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td style="padding-bottom:10px;padding-top:10px">
			<input type="button" class="cnsButtonMed" value="Reset Password" onclick="javascript:resetPassword();"/>
		</td>
		<td style="padding-bottom:10px;padding-top:10px">
			<c:if test="${ERROR == null && SUCCESS == null}">
				<input type="button" class="cnsButtonSmall" value="Go Back" onclick="javascript:login();" />
			</c:if>
			<c:if test="${ERROR != null || SUCCESS != null}">
				<input  type="button" class="button" value="Login" onclick="javascript:login();" />
			</c:if>
		</td>
	</tr>
</table>
</div>
</div>
 
</body>
</html>