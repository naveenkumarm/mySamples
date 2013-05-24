<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 
<html>
<head>
<script type="text/javascript">
   window.history.forward();
  /*  function noBack() { window.history.forward(); } */
</script>


<title><tiles:insertAttribute name="title" ignore="true" /></title> 
</head>
<body  onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload=""> 
<div class="mainDiv">
 
				  <tiles:insertAttribute name="header" />
				  <tiles:insertAttribute name="menu" /> 
				  <tiles:insertAttribute name="body" /> 
				  <tiles:insertAttribute name="footer"/>
				  
			</div>
 	 
 
 </body>
</html>