<%@page import="com.epro.framework.user.service.UserService"%>
<%@page import="com.epro.framework.security.SecurityHolder"%>
<%@page import="com.epro.framework.security.MenuConstants"%>
<%@page import="com.epro.framework.util.ApplicationConstants"%>
<%@page import="com.epro.framework.login.util.UserSessionInfo"%>
<%@page import="java.util.List"%>
<%@include file="../layout/include.jsp"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.epro.framework.model.Users"%>
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
<style type="text/css">
.customLabel{
	width: 97%!important;
	}
</style>
 
<script>

String.prototype.RTrim = function() {
	return this.replace(/(\s*$)/g, "");
}
String.prototype.LTrim = function() {
	return this.replace(/(^\s*)/g, "");
}

$(document).ready(function () {
	loadDataTable();
});

	var uid = "<%=trackId%>";
	var userNameKey = "";
 	var oldRow = null;
	var oldRowBackGroundColor = null;
	var oldImage = null;
	var oldTDRow = null;
	var oldTDRowInnerHTML = null;

	function submitDeleteCall(data, username, operation) {
		if (confirm("Are you sure to delete the user ' " + username + "'?")) {
			if (operation == "delete") {
				window.location.href = "deleteUser.do?uid="+uid+"&delete=" + data;
			}
		}
	}
	
	function searchUser() {
		var searchUserName = "";
	
		if(document.getElementById("searchUserName") != null){
			searchUserName = document.getElementById("searchUserName").value;		 
		}
		
		window.location.href = "searchUser.do?uid="+uid+"&searchUserName=" + searchUserName;
	}
	
 	
	function getSearchKeyListener(event){
		userNameKey = document.getElementById("searchKeyWord").value;
		
 		var key = event.keyCode || event.charCode;

	    if (key == 13) {
	    	document.getElementById('usersDetail').style.display = 'none'; 
	    	loadDataTable();
	    }
	}
	
	 
	var oldRow = null;
	var oldRowBackGroundColor = null;
	
	var oldImage = null;

	var oldTDRow = null;
	var oldTDRowInnerHTML = null;
	
	function showUserInfo(userId,userName,row,rowElement){
		
	    if(oldRow != null && oldRowBackGroundColor != null){
			oldRow.style.backgroundColor = oldRowBackGroundColor;
		}
	    
	    if(oldImage != null){
	    	oldImage.innerHTML = "";
	    }
	    
	    if(oldTDRow != null){
	    	oldTDRow = oldTDRowInnerHTML;
	    }
	    
	    var imageElement = null;
		oldrow = rowElement;
		
		$.post("getUserInfo.do?uid="+uid+"&userId="+userId, function(response){
			
			 var jsonObject = eval('(' + response + ')');
			 $("#usersDetail").slideDown(700);
			     $("#header").html("");
		         $("#header").append(""+userName+" Details");
 				 $("#role").val(jsonObject.role);
 				 $("#firstName").val(jsonObject.firstName);
				 $("#lastName").val(jsonObject.lastName);
 				 $("#employeeId").val(jsonObject.employeeId);
				 $("#userName").val(jsonObject.userName);
				 $("#password").val(jsonObject.password);
				 $("#confPassword").val(jsonObject.confirmPassword);
 				 $("#wphone1").val(jsonObject.workingPhone1);
				 $("#wphone2").val(jsonObject.workingPhone2);
				 $("#mobilePh1").val(jsonObject.mobileNumber1);
				 $("#mobilePh2").val(jsonObject.mobileNumber2);
				 $("#extn1").val(jsonObject.extn1);
				 $("#extn2").val(jsonObject.extn2);
				 $("#department").val(jsonObject.department);
				 $("#email").val(jsonObject.email);
				 if(jsonObject.active=="true"){
				      document.getElementById("active").checked = true;
				      document.getElementById("inActive").checked = false;
				 }else{
					 document.getElementById("active").checked = false;
				      document.getElementById("inActive").checked = true;
				 }
		 },'html');
	}
	 
	function loadDataTable(){
		
		document.getElementById('usersDetail').style.display = 'none'; 
     	userNameKey = $("#searchKeyWord").val().RTrim().LTrim();
 		var params = "uid="+uid+"&userNameKey="+userNameKey;
 		InitUserTable(params);
 		return false;
	}
	
	
	
	function InitUserTable(params)
	 {
	   var userListTable= $('#userListTable').dataTable( { 
			 "bLengthChange" : false,
	         "iDisplayLength" : 10,
	         "bSort" : false,
	         "bFilter": false,
	         "bDestroy":true,
	         "sPaginationType": "full_numbers",
	         "bProcessing": true,
	         "bServerSide": true, 
	         "bAutoWidth" : false,
			        "sAjaxSource": "../user/userListInfo.do?"+params,
			        "fnDrawCallback": function(){
			        	 $("#userListTable tbody tr").click( function( e ) {
			        		 
			        	        if ( $(this).hasClass('datatablerowhighlight') ) {
			        	            $(this).removeClass('datatablerowhighlight');
			        	        }
			        	        else {
			        	        	userListTable.$('tr.datatablerowhighlight').removeClass('datatablerowhighlight');
			        	            $(this).addClass('datatablerowhighlight');
			        	        }
			        	        
			        	 });
					      }, 
			        "aoColumns": [
			                    		  { "sTitle": "#",   "mData": "index", "sClass": "center"  },
			                    		  { "sTitle": "UserName",   "mData": "userName", "sClass": "left" ,"fnRender": function(obj) {
			                    			  var userName = obj.aData.userName;
			                    			  var userId  = obj.aData.userId;
			                    			  var index =  obj.aData.index;
			                    			  var viewUser="";
			                    			  if(userName.length > 20){
			                    				  var shortForm = userName.substr(0,20);
			                    				  shortForm = shortForm+"..";
			                    				  viewUser = "<div style='cursor:pointer;width:125px;'><u id='"+index+"' title='"+userName+"' onclick='javascript:showUserInfo(\""+userId+"\" ,\""+userName+ "\" , \""+index+"\",this);'>"+ shortForm +" </u></div>";
			                    			  }else{
				                    			 viewUser = "<div style='cursor:pointer;width:125px;' onclick='javascript:showUserInfo(\""+userId+"\" ,\""+userName+ "\" , \""+index+"\",this);'><u id='"+index+"' >"+ userName +" </u></div>"; 
			                    			  }
			                    			  
			                    			  return viewUser;
			                    		  } },
			                    		   
			                    		  { "sTitle": "First Name",   "mData": "firstName", "sClass": "left" ,"fnRender": function(obj) {
			                    			  var firstName = obj.aData.firstName;
			                    			  var viewUser="";
			                    			  if(firstName.length > 20){
			                    				  var shortForm = firstName.substr(0,20);
			                    				  shortForm = shortForm+"..";
			                    				  viewUser = "<div style='width:150px;' title='"+firstName+"'>"+ shortForm +"</div>";
			                    			  }else{
				                    			 viewUser = "<div style='width:150px;' title=''>"+ firstName +"</div>"; 
			                    			  }
			                    			  
			                    			  return viewUser;
			                    			  
			                    		  } },
			                    		  { "sTitle": "Last Name",   "mData": "lastName", "sClass": "left" ,"fnRender": function(obj) {
			                    			  var lastName = obj.aData.lastName;
			                    			  var viewUser="";
			                    			  if(lastName.length > 20){
			                    				  var shortForm = lastName.substr(0,20);
			                    				  shortForm = shortForm+"..";
			                    				  viewUser = "<div style='width:150px;' title='"+lastName+"'>"+ shortForm +"</div>";
			                    			  }else{
				                    			 viewUser = "<div style='width:150px;' title=''>"+ lastName +"</div>"; 
			                    			  }
			                    			  
			                    			  return viewUser;
			                    			  
			                    		  }},
			                    		  { "sTitle": "Department",   "mData": "department", "sClass": "center"  },
			                    		  <%if (user.getAccessRights().containsKey(MenuConstants.USER_MANAGEMENT) && user.getAccessRights().get(MenuConstants.USER_MANAGEMENT).isEditEnabled()) {%>
			                    		  { "sTitle": "Edit",   "mData": "edit", "sClass": "center","fnRender": function(obj) {
			                    			  var editUser  = obj.aData.userId;
			                    			  var userName = obj.aData.userName;
			                    			  var image="";
			                    			  image="<img id='" + editUser +"' name='Edit'  src='../images/edit.png'  onClick='javascript:editUser(this);'  style='cursor:pointer' title=' Click here to Edit the User'>";
			                    			  return image;
			                    		  } },
			                    	 		<%}%>
			                    	 		<%if (user.getAccessRights().containsKey(MenuConstants.USER_MANAGEMENT) && user.getAccessRights().get(MenuConstants.USER_MANAGEMENT).isDeleteEnabled()) {%>
			                    		  { "sTitle": "Delete",   "mData": "delete", "sClass": "center","fnRender": function(obj) {
			                    			  var deleteUser  = obj.aData.userId;
			                    			  var userName = obj.aData.userName;
			                    			  var image="";
			                    			  if(deleteUser != "<%=user.getUserId()%>"){
	                    							image="<img id='"+ deleteUser +"' onClick='javascript:deleteUser(this);'  style='cursor:pointer' title='Click here to delete the User' src='../images/delete.png'/>";
	                    						}else{
	                    							image="NA";
	                    						}
			                    			  return image;
			                    		  } }
			                    	 			<%}%>
			                    ]
	     } );
	 }
	
	 function editUser(html){
		 var userId = html.id;
	     window.location.href ="../user/editUserPage.do?uid=" + uid + "&userId=" + userId;
	 }
	 
	 function deleteUser(html){
		 var userId = html.id;
		 var option = confirm("Are you sure to delete user ?");
			if (option == true)
			  {
			     window.location.href = "../user/deleteUserInfo.do?uid="+uid+"&userId="+userId;		
			}
	 }
	
		
	 
	 window.onload= function(){
			$('#adminMenu').addClass("menuActive");
		}
	 
</script>
<title> </title>
</head>
<body onkeypress="return getSearchKeyListener(event);">
<div class="content">
 


 <div  style="position: relative;left:800px; ">
			<%
					if (user.getAccessRights().containsKey(MenuConstants.USER_MANAGEMENT) && user.getAccessRights().get(MenuConstants.USER_MANAGEMENT).isCreateEnabled()) {
			%>
				<div class="cnsButtonSmall">
					<a href="createUser.do?uid=<%=trackId%>"> <span>New User</span>  <img height="25" width="25" title="Create New User" alt="Create User" src="../images/user.png" class="buttonIcons" /> </a>
			   </div>
  			<%}%>
 </div>
		 
<br>
 <span class="successresponse">${response}</span>
 <span class="errorresponse">${errorresponse}</span>
<br>
	<table align="right" style="margin-right: 50px;margin-top: 30px;">
		<tr>
    		<td>
				<label class="customLabel" >Search User :</label>
			</td>
			<td>
 				<input class="customInput" type="text" id="searchKeyWord" title="Type the Search key and press enter" onkeypress="getSearchKeyListener(event);" maxlength="50">
 			</td>	
		    <td>				 
				<input  type="button" class="button" style="margin-top: -10px;" value="  Search  " title="Type the Search key and press enter"  onclick="loadDataTable();"  />
			</td>
 		</tr>	
	</table>
 
<div class="customDataTableHeadingborder"  style="float: left;display: inline;  border: 1px solid #A28FB5; width: 94.7%;">
<div class="customDataTableBoxHead" ><div class="customDataTableHeading"><font>Users List</font></div>
      </div>
<table id='userListTable' cellpadding="0" cellspacing="0" border="0" class="display" >
</table>
</div>


	<div>	
	<div id="usersDetail" class="customDataTableHeadingborder"  style="display: none; border: 1px solid #A28FB5; width: 94.5%;margin-bottom: 18px;margin-top: 10px;">
	<div class="customDataTableBoxHead" >
            <div id="header" class="customDataTableHeading"></div>
      </div>
       <div style="width:900px">
<table width="90%" align="left">
	 

	<tr>
		<td><label class="customLabel">Role:</label></td>
		<td><input class="customInput" type="text" name="role" id="role" size="30"
			readonly="readonly" /></td>
		<td><label class="customLabel">Employee Id:</label></td>
		<td><input class="customInput" type="text" id="employeeId" size="30"
			readonly="readonly" /></td>
 	</tr>
 
	<tr>
		<td><label class="customLabel">First Name:</label></td>
		<td><input class="customInput" type="text" name="firstName" id="firstName" size="30"
			readonly="readonly" /></td>
		<td><label class="customLabel">Work phone1:</label></td>
         <td nowrap="nowrap"><table  width="100%">
         <tr>
         <td><input class="customInput" type="text" name="wphone1" id="wphone1"
			size="30" readonly="readonly" /></td>
		<td><label class="customLabelExtn">Extn:</label></td>
		<td><input class="customInput" type="text" name="extn" id="extn1" size="4"
			readonly="readonly" /></td>
         </tr>
         </table>
         </td>
	</tr>
	<tr>
		<td><label class="customLabel">Last Name:</label></td>
		<td><input class="customInput" type="text" name="lastName" id="lastName" size="30"
			readonly="readonly" /></td>
		<td><label class="customLabel">Work phone2:</label></td>
		<td nowrap="nowrap"><table width="100%">
		<tr>
	     <td><input class="customInput" type="text" name="wphone2" id="wphone2"
			size="30" readonly="readonly" /></td>
		<td ><label class="customLabelExtn">Extn:</label></td>
		<td><input class="customInput" type="text" name="extn" id="extn2" size="4"
			readonly="readonly" /></td>
		</tr>
		</table>
		</td>
	</tr>
	<tr>
 		<td><label class="customLabel">Mobile phone1:</label></td>
		<td ><input class="customInput" type="text" name="mobilePh1" id="mobilePh1"
			size="30" readonly="readonly" /></td>
  		<td><label class="customLabel">Mobile phone2:</label></td>
		<td ><input class="customInput" type="text" name="mobilePh2" id="mobilePh2"
			size="30" readonly="readonly" /></td>
	</tr>
	<tr>
		<td><label class="customLabel">User Name:</label></td>
		<td><input class="customInput" type="text" name="userName" id="userName" size="30"
			readonly="readonly" /></td>
		<td><label class="customLabel">Department:</label></td>
		<td><input class="customInput" type="text" id="department" size="30"
			readonly="readonly" /></td>
 	</tr>
	<tr>
		<td><label class="customLabel">Password:</label></td>
		<td><input class="customInput" type="password" name="password" id="password"
			size="30" readonly="readonly" /></td>
		<td><label class="customLabel">Email:</label></td>
		<td ><input class="customInput" type="text" name="email" id="email"
			size="30" readonly="readonly" /></td>
	</tr>
	<tr>
		<td><label class="customLabel">Confirmation Password:</label></td>
		<td><input class="customInput" type="password" name="confPassword" id="confPassword"
			size="30" readonly="readonly" /></td>
		<td ><label class="customLabel">Status:</label></td>
		<td><input class="customInput" type="radio" name="active" id="active"
			disabled="disabled" />&nbsp;Active&nbsp;&nbsp;&nbsp;<input class="customInput"
			type="radio" name="inActive" id="inActive" disabled="disabled" />&nbsp;InActive
		</td>
	</tr>
</table>
</div>
</div>
</div>
</div>
</body>
  
</html>