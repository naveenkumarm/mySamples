<%@page import="com.epro.framework.security.SecurityHolder"%>
<%@page import="com.epro.framework.security.MenuConstants"%>
<%@page import="com.epro.framework.util.ApplicationConstants"%>
<%@page import="com.epro.framework.login.util.UserSessionInfo"%>
<%@include file="../layout/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Role</title>

<% 
		UserSessionInfo user = null;
   		String trackId = "";
		String sessionId = "";
 		if (session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
			user = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
			sessionId = request.getSession().getId(); 
   			trackId = SecurityHolder.MENU_TRACKER_MAPPING.get(MenuConstants.ROLE_MANAGEMENT) + "," + sessionId;
		}
%>
<script>

	var roleNameKey = "";
	String.prototype.RTrim = function() {
		return this.replace(/(\s*$)/g, "");
	}
	String.prototype.LTrim = function() {
		return this.replace(/(^\s*)/g, "");
	}
	function getSearchKeyListener(event){
  		
		var key =  event.charCode  || event.keyCode;
		
	    if (key == 13) {
 	    	loadDataTable();
	    }
	}
	
	function loadDataTable(){
		
  		roleNameKey = $("#searchRoleName").val().RTrim().LTrim();
 		var params = "uid="+uid+"&roleNameKey="+roleNameKey;
 		InitRoleTable(params);
 	}
	 
	$(document).ready(function () {
		loadDataTable();
	});
	
	 
	var uid = "<%=trackId%>";
	
	function submitDeleteCall(roleId,roleName){
		var isDelete = confirm("Are you sure to Delete [ "+ roleName+" ] Role?");
		if (isDelete == true) {
		   window.location.href = "deleteRole.do?uid="+uid+"&roleId="+roleId;
		}
	}
	
	function InitRoleTable(params)
	{
 	  var rolesList= $('#rolesList').dataTable( { 
			"bLengthChange" : false,
	        "iDisplayLength" : 10,
	        "bSort" : false,
	        "bFilter": false,
	        "bDestroy":true,
	        "sPaginationType": "full_numbers",
	        "bProcessing": true,
	        "bServerSide": true, 
	        "bAutoWidth":false,
			"sAjaxSource": "../role/roleListInfo.do?"+params,
			"fnDrawCallback": function(){
	        	 $("#rolesList tbody tr").click( function( e ) {
	        		 
	        	        if ( $(this).hasClass('datatablerowhighlight') ) {
	        	            $(this).removeClass('datatablerowhighlight');
	        	        }
	        	        else {
	        	        	rolesList.$('tr.datatablerowhighlight').removeClass('datatablerowhighlight');
	        	            $(this).addClass('datatablerowhighlight');
	        	        }
	        	        
	        	 });
			      }, 
		       	 "aoColumns": [
	                    		  { "sTitle": "#",   "mData": "index", "sClass": "center"  },
	                    		  { "sTitle": "Role Name",   "mData": "roleName", "sClass": "left" ,"fnRender": function(obj) {
	                    			  
	                    			  var roleName = obj.aData.roleName;
 			                		  var viewcontent ="";
 			                		  
 			                		  if(roleName.length > 30 ){
 			                			  var shortName = roleName.substr(0,23);
 			                			  shortName  = shortName+"..";
 			                			  viewcontent = "<div  style='width:160px;' title='"+roleName+"'>"+ shortName +" </div>"; 			                			  
 			                		  }else{
 			                			  viewcontent = "<div style='width:160px;' title=''>"+ roleName +" </div>"; 
 			                		  }
 			                		  
			                		  return viewcontent; 
	                    		  }},
		                    		  {"sTitle": "Status",   "mData": "status", "sClass": "center" ,"fnRender": function(obj) { 
	                    			  var isActivated  = obj.aData.status;
			                    	  var state="";
			                    	  if(isActivated == true){
			                    		  state="<font color='green' title='Role Activated'>Active</font>";
			                    	  }else{
			                    		  state="<font color='red' title='Role not Activated'>InActive</font>";
			                    	  }  
			                    	  return state;
	                    		  } },
	                    		  
	                    		  { "sTitle": "Organization",   "mData": "organization", "sClass": "center"  },
	                    		  <%
	                    	 		if(user.getAccessRights().containsKey(MenuConstants.ROLE_MANAGEMENT) && user.getAccessRights().get(MenuConstants.ROLE_MANAGEMENT).isEditEnabled()) {
	                    			%>
	                    		  { "sTitle": "Edit",   "mData": "edit", "sClass": "center","fnRender": function(obj) {
	                    			  var editUser  = obj.aData.roleId;
	                    			  var image="";
		                    			  image="<img id='"+ editUser +"'   name='Edit'  src=\"../images/edit.png\" onClick='javascript:editRole(this);'  style='cursor:pointer' title='Click here to Edit the role'/>"; 
	                    			  return image;
	                    		  } },
	                    	 		<%} %>
	                    	 		<% if(user.getAccessRights().containsKey(MenuConstants.ROLE_MANAGEMENT) && user.getAccessRights().get(MenuConstants.ROLE_MANAGEMENT).isDeleteEnabled()) { 
	                    	 			%>
	                    		  { "sTitle": "Delete",   "mData": "deleted", "sClass": "center","fnRender": function(obj) {
	                    			  var deleteRole  = obj.aData.roleId;
	                    			  var image="";
		                    			  image="<img id='"+ deleteRole +"' src=../images/delete.png onClick='javascript:deleteUser(this);'  style='cursor:pointer' title='Click here to Delete the role'/>"; 
	                    			  return image;
	                    		  } }
	                    	 			<%}%>
			                    ]
	    } );
	}
	
	function editRole(html){
		 var roleId = html.id;
	    window.location.href ="../role/editRolePage.do?uid=" + uid + "&roleId=" + roleId;
	}
	
	function deleteUser(html){
		 var roleId = html.id;
		 var roleName = html.name;
		 var option = confirm("Are you sure to delete "+roleName+" ?");
			if (option == true)
			  {
				window.location.href = "../role/deleteRole.do?uid="+uid+"&roleId="+roleId;			
			}
 		
					
	}
	
	window.onload= function(){
		$('#adminMenu').addClass("menuActive");
	}
</script>

</head>
<body>
<div class="content">

 

			<% if(user.getAccessRights().containsKey(MenuConstants.ROLE_MANAGEMENT) && user.getAccessRights().get(MenuConstants.ROLE_MANAGEMENT).isCreateEnabled()) {
			 				%>
<div  style="position: relative;left:780px; ">
				<div class="cnsButtonMed">
					<a href="createRole.do?uid=<%=trackId%>" title="Create New Role" > <span>Create Role</span>  <img alt="Create Role" height="25px" width="25px" src="../images/addIcon.png" class="buttonIcons" /> </a>
			   </div>
 </div>
  			<%}%>
 
 <span class="successresponse">${response}</span>
		<span class="errorresponse">${errorResponse}</span>
<br>
	<table align="right" style="margin-right: 50px;">
	<tr>
 		<td>
			<label class="customLabel" >Search Role :</label>
		</td>
		<td>
			<input class="customInput" type="text" id="searchRoleName" title="Type the Search key and press enter" onkeypress="getSearchKeyListener(event);" maxlength="50">
		</td>	
		<td>
			<input  type="button" class="button" style="margin-top: -10px;" value="  Search  " title="Type the Search key and press enter"  onclick="loadDataTable();"  />
		</td>
 		</tr>	
	</table>
<div class="customDataTableHeadingborder"  style="float: left;display: inline;border: 1px solid #A28FB5; width: 94.5%;">
<div class="customDataTableBoxHead" >
            <div class="customDataTableHeading"><font>Role List View</font></div>
      </div>
      <div>
<table id='rolesList' cellpadding="0" cellspacing="0" border="0" class="display">
</table>
</div>
</div>
</div>
</body>
	 
</html>