<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!--Layout-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/styleSheet.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/menuStyleSheet.css" />

<!--For Jquery -->
<script src="<%=request.getContextPath()%>/scripts/jquery-1.6.4.js" type="text/javascript" ></script>
 

   
<!-- DATATABLE INHERITS  -->  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datatable/media/css/demo_table_jui.css" /> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datatable/media/css/demo_table.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datatable/media/extras/TableTools/media/css/TableTools.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/datatable/media/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript"  src="<%=request.getContextPath()%>/datatable/media/extras/TableTools/media/js/ZeroClipboard.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath()%>/datatable/media/extras/TableTools/media/js/TableTools.js"></script>

<!-- Attachment pop up -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/modalPopLite1.3.0.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/modalPopLite1.3.0.min.js"></script>