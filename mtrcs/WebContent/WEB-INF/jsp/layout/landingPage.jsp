<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="jwr"  uri="http://jawr.net/tags-el" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="/ems-web/images/favicon.ico" >
<title><tiles:getAsString name="title" ignore="true"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jwr:style src="/bundles/ems.css"/>
<jwr:script src="/bundles/ko.js"/>
<jwr:script src="/bundles/yui3init.js"/>
<jwr:script src="/bundles/common.js"/>

</head> 
<body>
<div id="shell">
	<div id="loadingImage" class="loadingCircle" style ="display :none;">loading</div>
	<div id="header">
    	<tiles:insertAttribute name="header" />
    </div>
    <div id="left-panel">
    	<tiles:insertAttribute name="leftPanel" />
    </div>
    <div id="content">
    	<tiles:insertAttribute name="body" />
    </div>
    <div id="footer">
    	<tiles:insertAttribute name="footer" />
    </div>
</div>
</body>
</html>