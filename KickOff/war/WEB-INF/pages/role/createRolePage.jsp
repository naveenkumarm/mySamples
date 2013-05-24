<%@page import="com.epro.framework.security.RoleAccessPermissionInfo"%>
<%@page import="com.epro.framework.util.SubMenuInfo"%>
<%@page import="com.epro.framework.util.MenuInfo"%>
<%@page import="java.util.Hashtable"%>
<%@include file="../layout/include.jsp"%>
<%@page import="com.epro.framework.security.MenuConstants"%>
<%@page import="com.epro.framework.security.SecurityHolder"%>
<%@page import="com.epro.framework.util.ApplicationConstants"%>
<%@page import="com.epro.framework.login.util.UserSessionInfo"%>
<%@page import="javax.sound.midi.SysexMessage"%>
<%@page import="org.apache.velocity.runtime.directive.Foreach"%> 
<%@page import="com.epro.framework.model.Menus"%>
<%@page import="com.epro.framework.model.SubMenus"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>  
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%> 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 


 <%
   	UserSessionInfo user1 = null;
  	String trackId = "";
  	String sessionId = "";
   	if (session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
  		user1 = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
    	sessionId = request.getSession().getId();
  		trackId = SecurityHolder.MENU_TRACKER_MAPPING.get(MenuConstants.ROLE_MANAGEMENT) + "," + sessionId;
  	}
  	
	    List<MenuInfo> menuList = (List) request.getAttribute("menuList");
		Hashtable<Long, RoleAccessPermissionInfo> menuAccessPermissionHash = (Hashtable) request.getAttribute("menuAccessPermissionHash");
		Hashtable<Long, RoleAccessPermissionInfo> subMenuAccessPermissionHash = (Hashtable) request.getAttribute("subMenuAccessPermissionHash");

  %>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

var uid = "<%=trackId%>";

window.onload=function(){
 	 $('#adminMenu').addClass("menuActive");
  	 var status = '${role.status}';
	 
		if(status == 'true'){
			document.getElementById("active").checked = true;
			document.getElementById("inActive").checked = false;
		}else if(status == 'false'){
			document.getElementById("active").checked = false;
			document.getElementById("inActive").checked = true;
		}else{
			//Empty while at the first time creation page
			document.getElementById("active").checked = false;
			document.getElementById("inActive").checked = true;
		}
}


function onMenuChecked(viewObj,menuName){ 
	var createObj = document.getElementById(menuName+"create");
	var editObj = document.getElementById(menuName+"edit");
	var deleteObj = document.getElementById(menuName+"deleted");
	
	if(viewObj.checked){
		createObj.checked = true;
		editObj.checked = true;
		deleteObj.checked = true;
		checkAllSubMenu(menuName);
	}
	else{
		createObj.checked = false;
		editObj.checked = false;
		deleteObj.checked = false;
		unCheckAllSubMenu(menuName);
	} 
}

function onSubMenuAllChecked(allObj,subMenuName){ 
	var viewObj = document.getElementById(subMenuName+"view");
	var createObj = document.getElementById(subMenuName+"create");
	var editObj = document.getElementById(subMenuName+"edit");
	var deleteObj = document.getElementById(subMenuName+"deleted");
	
	
	if(allObj.checked){
		viewObj.checked = true;
		createObj.checked = true;
		editObj.checked = true;
		deleteObj.checked = true;
	}
	else{
		viewObj.checked = false;
		createObj.checked = false;
		editObj.checked = false;
		deleteObj.checked = false;
	} 
}
 
	function saveNewRole(){
 					document.forms['createRole'].action="saveRole.do?uid="+uid; 
			 		document.forms['createRole'].submit();
	}  
	
	 function cancelSave(){
						window.location.href = "getRole.do?uid="+uid;
	}

function showSubMenus(submenus){
	
	
	document.getElementById(submenus+"label").style.color = "black";
	
	document.getElementById(submenus+"checkAll").disabled = false;
	document.getElementById(submenus+"view").disabled = false;
	document.getElementById(submenus+"create").disabled = false;
	document.getElementById(submenus+"edit").disabled = false;
	document.getElementById(submenus+"deleted").disabled = false;
}

function hideSubMenus(submenus){
	var checkAllObj = document.getElementById(submenus+"checkAll");
	var labelObj = document.getElementById(submenus+"label");
	var viewObj = document.getElementById(submenus+"view");
	var createObj = document.getElementById(submenus+"create");
	var editObj = document.getElementById(submenus+"edit");
	var deleteObj = document.getElementById(submenus+"deleted");

	checkAllObj.checked = false;
	viewObj.checked = false;
	createObj.checked = false;
	editObj.checked = false;
	deleteObj.checked = false;
	
	checkAllObj.disabled = true;
	viewObj.disabled = true;
	createObj.disabled = true;
	editObj.disabled = true;
	deleteObj.disabled = true;
	document.getElementById(submenus+"label").style.color = "#CAC8BB";
}

	
 
 
 function checkMenu(menuName){
	 
	 <%if (menuList != null) {
 			for (int i = 0; i < menuList.size(); i++) {
 				MenuInfo menuItem = menuList.get(i);
 				String mainMenuName = menuItem.getMenuName();
 				System.out.println(mainMenuName);
 			%>
              if(menuName == "<%=mainMenuName%>"){
            		<%	List<SubMenuInfo> subMenuList = menuItem.getSubMenuInfoList();
         			for (SubMenuInfo submenu : subMenuList) {
        					String submenuName = submenu.getSubMenuName();%>
        					
        				    if(document.getElementById('<%=submenuName%>view' ).checked ||
        				        document.getElementById('<%=submenuName%>create' ).checked ||
        				        document.getElementById('<%=submenuName%>edit' ).checked ||
        				        document.getElementById('<%=submenuName%>deleted' ).checked) {
        				    	
                          	  document.getElementById('<%=mainMenuName%>view' ).checked = true;
                          	  return;
                            }else{
                            	document.getElementById('<%=mainMenuName%>view' ).checked = false;
                            }
         			<%}%>
            	  
              } 			
 		<%	}
 			
 	}%>		
 }
 
 function checkSubmenu(allObj,subMenuName){
	 var viewObj = document.getElementById(subMenuName+"view");
		var createObj = document.getElementById(subMenuName+"create");
		var editObj = document.getElementById(subMenuName+"edit");
		var deleteObj = document.getElementById(subMenuName+"deleted");
		
		
		if(viewObj.checked || createObj.checked || editObj.checked || deleteObj.checked){
			document.getElementById(subMenuName+"checkAll").checked = true;
		}
		else{
			document.getElementById(subMenuName+"checkAll").checked = false;
		} 
 }
 
 
function unCheckAllSubMenu(menuName){
	 <%if (menuList != null) {
 			for (int i = 0; i < menuList.size(); i++) {
 				MenuInfo menuItem = menuList.get(i);
 				String mainMenuName = menuItem.getMenuName();
 			%>
 			
 			 if(menuName == "<%=mainMenuName%>"){
            		<%	List<SubMenuInfo> subMenuList = menuItem.getSubMenuInfoList();
         			for (SubMenuInfo submenu : subMenuList) {
        					String submenuName = submenu.getSubMenuName();%>
	 
	                        document.getElementById('<%=submenuName%>checkAll' ).checked = false;
        					document.getElementById('<%=submenuName%>view' ).checked = false;
        					document.getElementById('<%=submenuName%>create' ).checked = false;
        					document.getElementById('<%=submenuName%>edit' ).checked = false;
        					document.getElementById('<%=submenuName%>deleted' ).checked = false;
        					
         			<%}%>
 			 }
 		<%	}
 			
 	}%>		
 }
 
function checkAllSubMenu(menuName){
	<%if (menuList != null) {
			for (int i = 0; i < menuList.size(); i++) {
				MenuInfo menuItem = menuList.get(i);
				String mainMenuName = menuItem.getMenuName();
			%>
			
			 if(menuName == "<%=mainMenuName%>"){
        		<%	List<SubMenuInfo> subMenuList = menuItem.getSubMenuInfoList();
     			for (SubMenuInfo submenu : subMenuList) {
    					String submenuName = submenu.getSubMenuName();%>
                         if(document.getElementById('<%=submenuName%>checkAll' ).disabled == false){
                        	 
                        document.getElementById('<%=submenuName%>checkAll' ).checked = true;
    					document.getElementById('<%=submenuName%>view' ).checked = true;
    					document.getElementById('<%=submenuName%>create' ).checked = true;
    					document.getElementById('<%=submenuName%>edit' ).checked = true;
    					document.getElementById('<%=submenuName%>deleted' ).checked = true;
                         }
    					
     			<%}%>
			 }
		<%	}
			
	}%>		
} 
</script>
<title>Create Role</title>
</head>
<body>

<div class="content">


	<br>
 	<div align="left" >
		<label class="customLabel" style="font-size: 12px;text-align: left;margin-top: 20px;"><font class="errorresponse">${errorresponse}</font><font class="successresponse">${response}</font></label>
	</div>
<div class="headingborder"  style="float: left;display: inline; margin-top: 1em; border: 1px solid #A28FB5; width: 95%;">
<div class="boxHead">
		<div class="heading">${headers}</div>
	</div>
	<form:form id="createRole"	modelAttribute="role" method="POST">
		<form:errors path="*" 
		             element="ol" 
		             errorElement="li" 
		             cssClass="errorField"/>
		             
        <form:hidden path="roleId" 
                     id="roleId"
					 class="textbox" 
					 name="roleId" />
	<table>
 
        
        <tr>
        	<td width="120px"><label class="customLabel" id="roleNameLabel">Role Name:</label></td>
        	<td> <form:input class="customInput" path="roleName"   title="Please Enter Role Name."
                                    name="roleName"  
                                    size="30"  maxlength="50"
                                    id="roleName"/>&nbsp; <span
							class="mandatory">*</span>
             </td>
                       
              <td style="padding-left:50px"><label class="customLabel">Status:</label></td>
		        <td>  
		        	<form:radiobutton path="status"   
		                                 name="status" 
		                                 value="true" title="Please select the status"
		                                 id="active"/>&nbsp;Active&nbsp;&nbsp;&nbsp;&nbsp;
		                                 
				       <form:radiobutton path="status"   title="Please select the status"
				                         name="status" 
				                         value="false" 
				                         id="inActive" />&nbsp;Inactive &nbsp;&nbsp;&nbsp;&nbsp;
				                      </td>
           </tr> 		
		</table>
<!--		=====================================================-->


  
<table style="padding-left: 50px; padding-top: 10px;" cellspacing="0" cellpadding="0"    id="accessPermissions">
<tr>
    <th align="center" width="68" rowspan="2"  bgcolor="#D7E4BC" class="tableHeader" >SI</th>
    <th rowspan="2" width="200" bgcolor="#D7E4BC" class="tableHeader">Modules</th>
    <th colspan="4" bgcolor="#D7E4BC" class="tableHeader">Access Rights</th>
 </tr>
<tr>
    <th width="90" bgcolor="#D7E4BC" class="tableHeader" >View</th>
    <th width="90" bgcolor="#D7E4BC" class="tableHeader">Create</th>
    <th width="90" bgcolor="#D7E4BC" class="tableHeader">Edit</th>
    <th width="90" bgcolor="#D7E4BC" class="tableHeader">Delete</th>
</tr> 


<%
 	String title = "";
 		if (menuList != null) {
 			for (int i = 0; i < menuList.size(); i++) {
 				MenuInfo menuItem = menuList.get(i);
 				String mainMenuName = menuItem.getMenuName();
 				long mainMenuId = menuItem.getMenuId();

 				String viewChecked = "";
 				String createChecked = "";
 				String editChecked = "";
 				String deleteChecked = "";
 				String value = "checked=\"checked\"";

 				if (menuAccessPermissionHash.containsKey(mainMenuId)) {
 					RoleAccessPermissionInfo roleAccessPermission = menuAccessPermissionHash.get(mainMenuId);
 					if (roleAccessPermission.isViewEnabled()) {
 						viewChecked = value;
 					}

 					if (roleAccessPermission.isCreateEnabled()) {
 						createChecked = value;
 					}

 					if (roleAccessPermission.isEditEnabled()) {
 						editChecked = value;
 					}

 					if (roleAccessPermission.isDeleteEnabled()) {
 						deleteChecked = value;
 					}
 				}
 %>
	     <tr>
		  <td bgcolor="#EDEDED" align="center" style="font-weight: bold;" id="<%=mainMenuId%>"  >
	  	        <input type="hidden" id="mainMenuId" value="<%=mainMenuId%>" name="mainMenuId" />
			  <%=mainMenuId%> <input style="margin-left: 19%;" onclick="onMenuChecked(this,'<%=mainMenuName%>')" type="checkbox"  title="Click this to <%=mainMenuName%> View" value="true" <%=viewChecked%> name="<%=mainMenuName%>view" id="<%=mainMenuName%>view" /> 
		      <input  style="display:none;" type="checkbox" value="true"  <%=createChecked%> title="Click this to <%=mainMenuName%>  Create" name="<%=mainMenuName%>create" id="<%=mainMenuName%>create" class="dynamicCheckbox"/>
		      <input style="display:none;"  type="checkbox" value="true" <%=editChecked%> title="Click this to <%=mainMenuName%>  Edit" name="<%=mainMenuName%>edit" id="<%=mainMenuName%>edit" class="dynamicCheckbox" />
		      <input style="display:none;"  type="checkbox" value="true" <%=deleteChecked%> title="Click this to <%=mainMenuName%>  Delete" name="<%=mainMenuName%>deleted" id="<%=mainMenuName%>deleted" class="dynamicCheckbox" />
	 	  </td>
		  <td  bgcolor="#EDEDED"  colspan="5" ><label class="tablelabel" style="font-weight: bold;"><%=mainMenuName%></label></td>
		  
	    </tr>
	 
		<%
	 			List<SubMenuInfo> subMenuList = menuItem.getSubMenuInfoList();
	 						for (SubMenuInfo submenu : subMenuList) {
	 							String submenuName = submenu.getSubMenuName();
	 							long subMenuId = submenu.getSubMenuId();

	 							String viewSubChecked = "";
	 							String createSubChecked = "";
	 							String editSubChecked = "";
	 							String deleteSubChecked = "";
	 							String allSubChecked = "";
	 							String valueSub = "checked=\"checked\"";

	 							if (subMenuAccessPermissionHash.containsKey(subMenuId)) {
	 								RoleAccessPermissionInfo roleAccessPermission = subMenuAccessPermissionHash.get(subMenuId);
	 								if (roleAccessPermission.isViewEnabled()) {
	 									viewSubChecked = valueSub;
	 								}

	 								if (roleAccessPermission.isCreateEnabled()) {
	 									createSubChecked = valueSub;
	 								}

	 								if (roleAccessPermission.isEditEnabled()) {
	 									editSubChecked = valueSub;
	 								}

	 								if (roleAccessPermission.isDeleteEnabled()) {
	 									deleteSubChecked = valueSub;
	 								}

	 								if (roleAccessPermission.isAllChecked()) {
	 									allSubChecked = valueSub;
	 								}
	 							}
	 		%>	
	 		
			 		 <tr id="<%=submenuName%>">
				     <td><input type="checkbox" onclick="onSubMenuAllChecked(this,'<%=submenuName%>');checkMenu('<%=mainMenuName%>');"  <%=allSubChecked%> name="<%=submenuName%>checkAll"   id="<%=submenuName%>checkAll"  class="dynamicCheckbox"/></td>
				      
				    
				 	 <td style="padding-left: 15px;"><label class="tablelabel" id="<%=submenuName%>label"><%=submenuName%></label></td>
				      <td align="center" ><input type="checkbox" value="true" <%=viewSubChecked%>  title=" Click this to <%=submenuName%> View" name="<%=submenuName%>view"   id="<%=submenuName%>view" class="dynamicCheckbox" onclick="checkMenu('<%=mainMenuName%>');checkSubmenu(this,'<%=submenuName%>');"/></td>
				      <td align="center"><input type="checkbox" value="true" <%=createSubChecked%> title="Click this to <%=submenuName%> Create" name="<%=submenuName%>create"  id="<%=submenuName%>create" class="dynamicCheckbox"  onclick="checkMenu('<%=mainMenuName%>');checkSubmenu(this,'<%=submenuName%>');"/></td>
				      <td align="center"><input type="checkbox" value="true" <%=editSubChecked%> title="Click this to <%=submenuName%> Edit" name="<%=submenuName%>edit"  id="<%=submenuName%>edit" class="dynamicCheckbox" onclick="checkMenu('<%=mainMenuName%>');checkSubmenu(this,'<%=submenuName%>');"/></td>
				      <td align="center"><input type="checkbox" value="true" <%=deleteSubChecked%> title="Click this to <%=submenuName%> Delete" name="<%=submenuName%>deleted"  id="<%=submenuName%>deleted" class="dynamicCheckbox" onclick="checkMenu('<%=mainMenuName%>');checkSubmenu(this,'<%=submenuName%>');"/></td>
					 
				     
				    </tr>
			<%
				}
						}
					}
			%>	
</table>
<br>
 <%
 		if (menuAccessPermissionHash != null && menuAccessPermissionHash.size() == 0) {
 			title = "Save";
 		} else {
 			title = "Modify";
 		}
 %>
	<table>
		<tr>
			<td width="900px" align="right">
				<input type="button" value="${Save}" id="operation"
				title="Click to ${Save} details" class="button"
				onclick="javascript:saveNewRole();"> <input type="reset"
				value="Cancel" title="Cancel the create role" class="button" onclick="cancelSave();">
			</td>
		</tr>
	</table>


</form:form>
</div>
</div>

</body>
</html>