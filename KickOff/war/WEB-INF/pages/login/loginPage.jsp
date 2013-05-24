<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../layout/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
.loginPanel{
	background-color: #F0C375;
    height: 157px;
    margin: 120px auto 0;
    width: 440px;
}
.loginText{
	color:#644583;
	text-align: center;
	font-weight: bold;
}
.inputPanel{
margin-top:10px; 
}
</style>
<script type="text/javascript">
window.history.forward();
function noBack() {
	window.history.forward();
}
 
window.onload=  function() {	      
     checkCookie();
  	document.getElementById('j_username').focus(); 
}
function checkSubmit(e)
{ 
	var	keyPressed;
 	if(window.event){
		keyPressed = window.event.keyCode;
	}
	else
		keyPressed = e.which;
	if( keyPressed== 13)
	{
 	  login();
    }
}
function login(){
	
	var username = document.getElementById("j_username").value;
	var password = document.getElementById("j_password").value;	
	var errorCount = getCookie("errorMsgCount");
  	if(username.length == 0 && password.length == 0){
		customAlert("Please Enter Username,Password");
		return false;
	}
	else if(username.length == 0){
		customAlert("Please Enter Username!");
		return false;
	}
	else if(password.length == 0){
		customAlert("Please Enter Password!");
		return false;
	}
 	else{
		if(document.getElementById("rememberme").checked==true){
		setCookie("username",username,1)
		setCookie("password",password,1)
		}else{
			setCookie("username","",1)
			setCookie("password","",1)
		}
   		document.forms['loginForm'].submit();
   }
}
 
function getCookie(c_name)
{
var i,x,y,ARRcookies=document.cookie.split(";");
for (i=0;i<ARRcookies.length;i++)
  {
  x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
  y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
  x=x.replace(/^\s+|\s+$/g,"");
  if (x==c_name)
    {
    return unescape(y);
    }
  }
}

function setCookie(c_name,value,exdays)
{
var exdate=new Date();
exdate.setDate(exdate.getDate() + exdays);
var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
document.cookie=c_name + "=" + c_value;
}

function checkCookie()
{
var username=getCookie("username");
var password=getCookie("password");
if (username!=null && username!="")
  {
  document.getElementById('rememberme').checked=true;
  document.getElementById('j_username').value=username;
  }
if (password!=null && password!="")
{
document.getElementById('j_password').value=password;
}

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
 
 function forgetPassword(){
	 
	 window.location.href="../login/forgotPassword.do";
 }
 
</script>
</head>
<body onkeypress="return checkSubmit(event);">
<div id="dialog-confirm"></div>
<div id="dialog-alert"></div>
       
<div class="content">
<div style="position:absolute;top:240px; left: 594px;margin-bottom: 10px;">
			<span style="font-size: 12px;text-align: left;"><font class="errorresponse" style="font-style: normal;">${error}</font><font class="successresponse">${Logout}</font></span>
</div>
  <div class="loginPanel">
  		<div class="loginText">Log In</div>
  		<div class="inputPanel">
  		<form:form  method="post" action="../j_spring_security_check" modelAttribute="users" id="loginForm">
  			<table align="center">
  				<tr>
  					<td align="right"><label  class="popupLabel">Username:</label></td>
  					<td> <input type="text" id="j_username" name="j_username" style="width:100%;" /> </td>
  				</tr>
  				<tr>
  					<td align="right"><label  class="popupLabel">Password:</label></td>
  					<td> <input type="password" id="j_password" name="j_password" style="width:100%;" /> </td>
  				</tr>
  				<tr>
  					<td>  </td>
  					<td><input type="checkbox" id="rememberme" name="rememberme" /><span class="popupLabel">&nbsp;Remember me</span></td>
  				</tr>
  				<tr>
  				<td>
  				</td>
  					<td  align="right">
  						<div class="cnsButtonSmall">
							<a href="#" onclick="javascript:login();"> <span>Login</span> <img src="../images/Keys.png"class="buttonIcons" /> </a>
						 </div>
						 <span style="position: relative;top:9px;left: 5px"><font class="popupLabel" onclick="forgetPassword()" style="cursor: pointer;text-decoration: underline;">Forgot Password?</font></span>
  					</td>
  				</tr>
  			</table>
  			</form:form>
  		</div>
  </div>	 
 
	 
      </div>
  
      
 	 
</body>
</html>