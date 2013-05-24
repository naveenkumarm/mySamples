 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/scripts/jquery-1.6.4.js" type="text/javascript" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body onload="">
 
		 <%
				String imageFileName = "";
				if(request.getAttribute("imageFileName") != null)
					imageFileName = (String)request.getAttribute("imageFileName");
 		  %>
  				
	<%
		String result = "" + request.getAttribute("result");
	
		String resultType = "" + request.getAttribute("resultType");
	
		if(resultType.compareTo("null") == 0 || resultType.compareTo("") == 0){
			result = "Please upload image size less than 500 KB!";
		}
		
		if(resultType.compareTo("null") == 0 || resultType.compareTo("") == 0){
			resultType = "F";
		}
	%>

<br>	

<script> 
	var result = '<%=result%>';
	var imageFileName = '<%=imageFileName%>';
	var resultType = '<%=resultType%>';
	
	if(resultType != "F"){
		$("#imageUploadResponse",parent.document.body).html(result);
		$("#imageUploadErrorResponse",parent.document.body).html("");
	}else if(resultType != "S"){
		$("#imageUploadResponse",parent.document.body).html("");
		$("#imageUploadErrorResponse",parent.document.body).html(result);
	}
	
	///customAlert("result" +result);
	$("#image",parent.document.body).val("");
	$("#imageFileNameId",parent.document.body).val("");
	
	
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
  <div id="dialog-confirm"></div>
<div id="dialog-alert"></div>
</body>
</html>