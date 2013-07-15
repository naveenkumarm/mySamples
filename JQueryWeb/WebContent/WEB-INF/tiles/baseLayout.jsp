<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link href="../styles/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div id="shell">
		<div class="header-inner">
			<tiles:insertAttribute name="header" />
		</div>
		<!--End of header-->
		<div class="content">
			<tiles:insertAttribute name="body" />
		</div>
		<!--End of loginuser-->
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
		<!--End of Footer-->
	</div>
</body>
</html>
