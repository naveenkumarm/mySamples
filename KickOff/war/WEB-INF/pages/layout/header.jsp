<!--header - div-->
 
<%@page import="com.epro.framework.security.MenuConstants"%>
<%@page import="com.epro.framework.security.SecurityHolder"%>
<%@page import="com.epro.framework.login.controller.LoginController"%>
<%@page import="org.apache.commons.lang.exception.ExceptionUtils"%>
<%@page import="java.io.File"%>
<%@page import="com.epro.framework.util.ApplicationConstants"%>
<%@page import="com.epro.framework.login.util.UserSessionInfo"%>
<%@page import="com.epro.framework.model.Users"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%> 
<%@include file="../layout/include.jsp"%>    

<%
 	UserSessionInfo user = null; 
	String trackId = "";
	String sessionId = "";
	String logoPath = "";
	String logo = "../images/sensiplelogo.png";
	String LOGGED_IN_LEVEL = "";
	
 	if(session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null){
		user = (UserSessionInfo)session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
		LOGGED_IN_LEVEL = user.getRoleLevel();
		sessionId = request.getSession().getId();
		logoPath = user.getLogoPath();
 	}
 	
 	File file = new File(logoPath);
 	
 	if(file.exists()){
 		logoPath = logo;
 	}
%> 
 <div class="banner">
 	<div style="float: left;"> 	
         <div class="logo"><img src="<%=logoPath%>" alt="" width="200px" height="50px" style="position:relative;top:20px;left:20px">
         </div>
 	</div>
    <div style="float: right;">
        <div class="logo"><img src="../images/yourLogoImage.png" alt="LOGO" width="200px" height="50px" style="position:relative;top:10px;left:-5px"></div>     
        <% if(user != null){ %>
        <div class="detailsPane">
         <a href="../login/myAccount.do" style="color: #3958A7" >   My Account </a>|
        <a href="../login/logout.do" style="color: #3958A7">  Logout </a>
        </div>
        <%}%> 
    </div>
</div>
 