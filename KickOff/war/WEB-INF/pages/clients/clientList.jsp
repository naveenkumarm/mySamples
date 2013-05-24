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
 	String clientId = "";
   	if (session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
 		user = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
 		sessionId = request.getSession().getId();
  		trackId = SecurityHolder.MENU_TRACKER_MAPPING.get(MenuConstants.CLIENT_MANAGEMENT) + "," + sessionId;
  		clientId = ""+user.getClientId();
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
	var clientNameKey = "";
 	var oldRow = null;
	var oldRowBackGroundColor = null;
	var oldImage = null;
	var oldTDRow = null;
	var oldTDRowInnerHTML = null;

	function submitDeleteCall(data, clientName, operation) {
		if (confirm("Are you sure to delete the client ' " + clientName + "'?")) {
			if (operation == "delete") {
				window.location.href = "deleteClient.do?uid="+uid+"&delete=" + data;
			}
		}
	}
	
	function getSearchKeyListener(event){
		clientNameKey = document.getElementById("searchKeyWord").value;
		
 		var key = event.keyCode || event.charCode;

	    if (key == 13) {
	    	document.getElementById('clientDetail').style.display = 'none'; 
	    	loadDataTable();
	    }
	}
	
	 
	var oldRow = null;
	var oldRowBackGroundColor = null;
	
	var oldImage = null;

	var oldTDRow = null;
	var oldTDRowInnerHTML = null;
	
	function showClientInfo(clientId,clientName,row,rowElement){
		
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
		
		$.post("getClientDetails.do?uid="+uid+"&clientId="+clientId, function(response){
			
			alert("response:  "+response);
			
			 var jsonObject = eval('(' + response + ')');
			 $("#clientDetail").slideDown(700);
			     $("#header").html("");
		         $("#header").append(""+jsonObject.clientName+" Details");
 				 $("#description").val(jsonObject.description);
 				 $("#businessType").val(jsonObject.businessType);
				 $("#address").val(jsonObject.address);
 				 $("#city").val(jsonObject.city);
				 $("#state").val(jsonObject.state);
				 $("#zip").val(jsonObject.zip);
				 $("#country").val(jsonObject.country);
 				 $("#contactPerson").val(jsonObject.contactPerson);
				 $("#workPhone").val(jsonObject.workPhone);
				 $("#faxNo").val(jsonObject.faxNo);
				 $("#mobileNumber").val(jsonObject.mobileNumber);
				 $("#emailId").val(jsonObject.emailId);
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
		
		document.getElementById('clientDetail').style.display = 'none'; 
		clientNameKey = $("#searchKeyWord").val().RTrim().LTrim();
 		var params = "uid="+uid+"&clientNameKey="+clientNameKey;
 		InitUserTable(params);
 		return false;
	}
	
	
	
	function InitUserTable(params)
	 {
	   var clientListTable= $('#clientListTable').dataTable( { 
			 "bLengthChange" : false,
	         "iDisplayLength" : 10,
	         "bSort" : false,
	         "bFilter": false,
	         "bDestroy":true,
	         "sPaginationType": "full_numbers",
	         "bProcessing": true,
	         "bServerSide": true, 
	         "bAutoWidth" : false,
			        "sAjaxSource": "../clients/clientDataTable.do?"+params,
			        "fnDrawCallback": function(){
			        	 $("#clientListTable tbody tr").click( function( e ) {
			        		 
			        	        if ( $(this).hasClass('datatablerowhighlight') ) {
			        	            $(this).removeClass('datatablerowhighlight');
			        	        }
			        	        else {
			        	        	clientListTable.$('tr.datatablerowhighlight').removeClass('datatablerowhighlight');
			        	            $(this).addClass('datatablerowhighlight');
			        	        }
			        	        
			        	 });
					      }, 
			        "aoColumns": [
			                    		  { "sTitle": "#",   "mData": "index", "sClass": "center"  },
			                    		  { "sTitle": "ClientName",   "mData": "clientName", "sClass": "left" ,"fnRender": function(obj) {
			                    			  var clientName = obj.aData.clientName;
			                    			  var clientId  = obj.aData.clientId;
			                    			  var index= obj.aData.index;
			                    			  var viewClient="";
			                    			  viewClient = "<div style='cursor:pointer;width:125px;' class='ellipsis'><u  id='"+index+"' onclick='javascript:showClientInfo(\""+clientId+"\" ,\""+clientName+ "\" , \""+index+"\",this);'>"+ clientName +" </u></div>";
			                    			  return viewClient;
			                    		  } },
			                    		   
			                    		  { "sTitle": "Buisness Type",   "mData": "businessType", "sClass": "left" ,"fnRender": function(obj) {
			                    			  var businessType = obj.aData.businessType;
			                    			  return "<div style='width:150px;' class='ellipsis'>"+ businessType +"</div>"
			                    			  
			                    		  } },
			                    		  { "sTitle": "Contact Person",   "mData": "contactPerson", "sClass": "left" ,"fnRender": function(obj) {
			                    			  var contactPerson = obj.aData.contactPerson;
			                    			  return "<div style='width:150px;' class='ellipsis'>"+ contactPerson +"</div>"
			                    			  
			                    		  } },
			                    		  { "sTitle": "Mobile Number",   "mData": "mobileNumber", "sClass": "center"  },
			                    		  { "sTitle": "Status",   "mData": "isActive", "sClass": "center"  ,"fnRender": function(obj) {
			                    			  var isActive = obj.aData.isActive;
			                    			  var status ="";
			                    				  if(isActive){
			                    					  status ="<font color='green'>Active</font>"
			                    				  }else{
			                    					  status ="<font color='red'>InActive</font>"
			                    				  }
			                    				 
			                    			  return status;
			                    			  
			                    		  } },
			                    		  <%if (user.getAccessRights().containsKey(MenuConstants.USER_MANAGEMENT) && user.getAccessRights().get(MenuConstants.USER_MANAGEMENT).isEditEnabled()) {%>
			                    		  { "sTitle": "Edit",   "mData": "edit", "sClass": "center","fnRender": function(obj) {
			                    			  var clientId  = obj.aData.clientId;
			                    			  var clientName = obj.aData.clientName;
			                    			  var image="";
			                    			  image="<img id='"+clientId+"' name='Edit'  src='../images/edit.png'  onClick='javascript:editClient(this);'  style='cursor:pointer' title=' Click here to Edit the Client'>&nbsp;<img id='" + clientId +"' name='UPload'  src='../images/upload_image.png'  onClick='javascript:viewImageUploadPopup(this);'  style='cursor:pointer' title=' Click here upload client logo'>";
			                    			  return image;
			                    		  } },
			                    	 		<%}%>
			                    	 		<%if (user.getAccessRights().containsKey(MenuConstants.USER_MANAGEMENT) && user.getAccessRights().get(MenuConstants.USER_MANAGEMENT).isDeleteEnabled()) {%>
			                    		  { "sTitle": "Delete",   "mData": "delete", "sClass": "center","fnRender": function(obj) {
			                    			  var clientId  = obj.aData.clientId;
			                    			  var clientName = obj.aData.clientName;
			                    			  var image="";
	                    							image="<img id='"+ clientId +"' onClick='javascript:deleteClient(this);'  style='cursor:pointer' title='Click here to delete the Client' src='../images/delete.png'/>";
	                    						
			                    			  return image;
			                    		  } }
			                    	 			<%}%>
			                    ]
	     } );
	 }
	
	 function editClient(html){
		 var clientId = html.id;
	     window.location.href ="../clients/editClient.do?uid=" + uid + "&clientId=" + clientId;
	 }
	 
	 function deleteClient(html){
		 var clientId = html.id;
		 var option = confirm("Are you sure to delete client ?");
			if (option == true)
			  {
			     window.location.href = "../clients/deleteClient.do?uid="+uid+"&userId="+userId;		
			}
	 }
	
		
	 
	 window.onload= function(){
			$('#adminMenu').addClass("menuActive");
		}
	 
	 
	 function uploadClientLogo(){
			document.getElementById('invalidImage').style.display = 'none';
		 	var success = validate();
		 	alert("respon: "+success);
		 	if(success == true){
		 		alert("succees");
				document.getElementById('invalidImage').style.display = 'none';
				var clientId= "<%=clientId%>";
				alert("clientId: "+clientId);
				$('#hiddenClientId').val(clientId);
		  		document.uploadLogo.action = "../clients/uploadClientLogo.do?uid="+uid+"&clientId="+clientId;
		   		$('form#uploadLogo').submit();
		 		//document.uploadLogo.submit();
		  	} else {
				document.getElementById("image").value = "";
				document.getElementById('invalidImage').style.display = 'inline-block';
				
				document.getElementById("imageUploadResponse").innerHTML = "";
				document.getElementById("imageUploadErrorResponse").InnerHTMl = "";
				
			}
		}
	 
	 function viewImageUploadPopup(html){
		    $(function() {
				$('#popup-wrapper').show();
				$('#popup-wrapper').modalPopLite({ openButton: '#'+html.id,closeButton:'#close-btn', isModal: true });
			});
	}
	function validate(){
		var imagePath = $('#image').val();
		alert("imagepath: "+imagePath);
		if(imagePath != null && imagePath.length > 0){
			return true;	
		}
		return false;
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
					<a href="createClient.do?uid=<%=trackId%>"> <span>New Client</span>  <img height="25" width="25" title="Create New User" alt="Create User" src="../images/user.png" class="buttonIcons" /> </a>
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
				<label class="customLabel" >Search Client :</label>
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
<div class="customDataTableBoxHead" ><div class="customDataTableHeading"><font>Client List</font></div>
      </div>
<table id='clientListTable' cellpadding="0" cellspacing="0" border="0" class="display" >
</table>
</div>

	<div>	

	<div id="clientDetail" class="customDataTableHeadingborder"  style="display: none; border: 1px solid #A28FB5; width: 94.5%;margin-bottom: 18px;margin-top: 20px;">
	<div class="customDataTableBoxHead" >
            <div id="header" class="customDataTableHeading"></div>
      </div>
       <div style="width:900px">
<table width="90%" align="left">
	<tr>
		<td><label class="customLabel">Description:</label></td>
		<td><input class="customInput" type="text" name="description" id="description" size="30"
			readonly="readonly" /></td>
		<td><label class="customLabel">BuisnessType:</label></td>
		<td><input class="customInput" type="text" id="businessType" size="30"
			readonly="readonly" /></td>
 	</tr>
 
	<tr>
		<td><label class="customLabel">Address:</label></td>
		<td><input class="customInput" type="text" name="address" id="address" size="30"
			readonly="readonly" /></td>
		<td><label class="customLabel">City:</label></td>
         <td >
         <input class="customInput" type="text" name="city" id="city" size="30"
			readonly="readonly" />
         </td>
	</tr>
	<tr>
		<td><label class="customLabel">State:</label></td>
		<td><input class="customInput" type="text" name="state" id="state" size="30"
			readonly="readonly" /></td>
		<td><label class="customLabel">Zip:</label></td>
		<td ><input class="customInput" type="text" name="zip" id="zip" size="30"
			readonly="readonly" />
		</td>
	</tr>
	<tr>
 		<td><label class="customLabel">Country:</label></td>
		<td ><input class="customInput" type="text" name="country" id="country"
			size="30" readonly="readonly" /></td>
  		<td><label class="customLabel">Contact Person:</label></td>
		<td ><input class="customInput" type="text" name="contactPerson" id="contactPerson"
			size="30" readonly="readonly" /></td>
	</tr>
	<tr>
		<td><label class="customLabel">Work Phone:</label></td>
		<td><input class="customInput" type="text" name="workPhone" id="workPhone" size="30"
			readonly="readonly" /></td>
		<td><label class="customLabel">Fax No:</label></td>
		<td><input class="customInput" type="text" id="faxNo" size="30"
			readonly="readonly" /></td>
 	</tr>
	<tr>
		<td><label class="customLabel">Mobile Number:</label></td>
		<td><input class="customInput" type="text" name="mobileNumber" id="mobileNumber"
			size="30" readonly="readonly" /></td>
		<td><label class="customLabel">Email:</label></td>
		<td ><input class="customInput" type="text" name="emailId" id="emailId"
			size="30" readonly="readonly" /></td>
	</tr>
</table>
</div>
</div>
</div>
</div>

<!-- pop up wrapper start -->
<div id="popup-wrapper" style="display: none; width: 400px;height: 100px;" class="popup-wrapper-Log">
<div class='popupHeader' style="width: 400px;"><span class='popupHeaderText' id="logHeader">Client Logo upload</span>
<img height="30px" width="30px"  style='cursor: pointer;float:right' id="close-btn" src='../images/greyCloseButton.png' /></div>
<div>
<form:form method="POST" modelAttribute="uploadImage" enctype="multipart/form-data" id="uploadLogo" target="imageUploadFrame" name="uploadLogo" >
<form:hidden id="hiddenClientId" path="clientId"/>
	<iframe id="imageUploadId" style="display: none"  name="imageUploadFrame" height="0" width="100%" frameborder="0" scrolling="no"></iframe>
	<table>
			<tr>
				<td align="left" valign="middle"> 
					<table>
						<tr>
							<td>
								<label class="customLabel" style="width: 120px; ">Select Image:</label>
							</td>
 							<td  align="left" valign="middle">
									<form:input path="file" type="file" id="image" style="border:1px solid lightgrey" />
							</td>
							<td>
		 							<input type="button" class="button"  style="margin-top: 0px;" value="Upload" onclick="javascript:uploadClientLogo();" title="Click here to upload file">
							</td>
						</tr>
						<tr>
							<td align="left" valign="top" colspan="3">
								&nbsp;<span class="successresponse" id="imageUploadResponse"></span><span class="errorResponse" id="imageUploadErrorResponse"></span>
								<span  id="invalidImage"  class="errorresponse" style="display:none; margin-left: 10px;">Please upload image for logo with '.png' as file extension.</span>
							</td>
						<tr>
					</table>
 				</td>
			</tr>
	</table>
	</form:form>
</div>
</div>
<!-- pop up wrapper end -->	
</body>
  
</html>