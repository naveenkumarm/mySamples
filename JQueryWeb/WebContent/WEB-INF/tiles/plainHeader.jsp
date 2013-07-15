<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if scope="session" test="${!empty loginForm.userName}">
<h4 class="username" align="right" >Welcome <core:out value="${loginForm.userName}" /></h4>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <!-- 
  <link rel="stylesheet" type="text/css" media="screen" href="/JQueryWeb/styles/jquery/ui-lightness/jquery-ui-1.8.6.custom.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="/JQueryWeb/styles/jqgrid/ui.jqgrid.css" />
	  -->	
  
   <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/redmond/jquery-ui.css" />
   <link rel="stylesheet" type="text/css" href="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.1.2/css/ui.jqgrid.css" />


	<script type="text/javascript" src="/JQueryWeb/js/jquery/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	
	    var jq = jQuery.noConflict();
	</script>
	<script type="text/javascript" src="/JQueryWeb/js/jquery/jquery-ui-1.8.6.custom.min.js"></script> 
	<script type="text/javascript" src="/JQueryWeb/js/jqgrid/grid.locale-en.js" ></script>
	<script type="text/javascript" src="/JQueryWeb/js/jqgrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="/JQueryWeb/js/jquery/jquery.contextmenu.js"></script>
	<script type="text/javascript" src="/JQueryWeb/js/jquery/celledit.js"></script>
	
	<!-- jqplot libraries-->
	<link rel="stylesheet" type="text/css" href="/JQueryWeb/js/jqplot/jquery.jqplot.css" />
	<script  type="text/javascript" src="/JQueryWeb/js/jqplot/excanvas.min.js"></script>
	<script  type="text/javascript" src="/JQueryWeb/js/jqplot/jquery.jqplot.min.js"></script>
	<script  type="text/javascript" src="/JQueryWeb/js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
	<script  type="text/javascript" src="/JQueryWeb/js/jqplot/plugins/jqplot.barRenderer.min.js"></script>
	<script  type="text/javascript" src="/JQueryWeb/js/jqplot/plugins/jqplot.dragable.min.js"></script>
	<script  type="text/javascript" src="/JQueryWeb/js/jqplot/plugins/jqplot.highlighter.min.js"></script>
    <script  type="text/javascript" src="/JQueryWeb/js/jqplot/plugins/jqplot.pieRenderer.min.js"></script>
    <script  type="text/javascript" src="/JQueryWeb/js/jqplot/plugins/jqplot.cursor.min.js"></script>
    <script  type="text/javascript" src="/JQueryWeb/js/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
	<!--End of jqplot libraries-->
	
	<style type="text/css">
.ui-tabs .ui-tabs-hide {
	display: none !important;
}
.ui-tabs-vertical .ui-tabs-nav { padding: .2em .1em .2em .2em; float: left; width: 12em; }
.ui-tabs-vertical .ui-tabs-nav li { clear: left; width: 100%; border-bottom-width: 1px !important; border-right-width: 0 !important; margin: 0 -1px .2em 0; }
.ui-tabs-vertical .ui-tabs-nav li a { display:block; }
.ui-tabs-vertical .ui-tabs-nav li.ui-tabs-active { padding-bottom: 0; padding-right: .1em; border-right-width: 1px; border-right-width: 1px; }
.ui-tabs-vertical .ui-tabs-panel { padding: 1em; float: left; width: 40em;}
 .progress-label {
float: left;
margin-left: 50%;
margin-top: 5px;
font-weight: bold;
text-shadow: 1px 1px 0 #fff;
}
	</style>
	
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head></html>