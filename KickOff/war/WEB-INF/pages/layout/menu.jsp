<%@page import="com.epro.framework.security.MenuConstants"%>
<%@page import="com.epro.framework.util.ApplicationConstants"%>
<%@page import="com.epro.framework.security.SecurityHolder"%>
<%@page import="com.epro.framework.util.SubMenuInfo"%>
<%@page import="com.epro.framework.util.ApplicationConfigLoader"%>
<%@page import="com.epro.framework.util.MenuInfo"%>
<%@page import="com.epro.framework.login.util.UserSessionInfo"%>
<%@page import="com.epro.framework.model.Users"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!--menu - div-->
  <div class="menuBar">
  
        <ul id="coolMenu">
        <%
		String menu = "";
		UserSessionInfo user = null;
		String sessionId = "";
		List<String> accessModuleList = new ArrayList<String>();
		if (session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
			user = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
			sessionId = request.getSession().getId();
 			
	%>
	
   	<!-- Main Menu : - Admin	 -->
   	<%
		
		menu = MenuConstants.ADMIN;
   			if (user.getIsMenuEnabled().containsKey(menu) && user.getIsMenuEnabled().get(menu)) {
				
	%>
			<li>
				<a href="#" id="adminMenu"> <%=menu%></a>
				<ul class="noJS">
				<%
			menu = MenuConstants.USER_MANAGEMENT;
					if (user.getIsSubMenuEnabled().containsKey(menu) && user.getIsSubMenuEnabled().get(menu)) {
						String trackId = SecurityHolder.MENU_TRACKER_MAPPING.get(menu) + "," + sessionId;
		%>
					<li><a href="../user/getUser.do?uid=<%=trackId%>"><%=menu%></a></li>
	    <%
			}
		%>

		<%
			menu = MenuConstants.ROLE_MANAGEMENT;
					if (user.getIsSubMenuEnabled().containsKey(menu) && user.getIsSubMenuEnabled().get(menu)) {
						String trackId = SecurityHolder.MENU_TRACKER_MAPPING.get(menu) + "," + sessionId;
		%>
					<li><a href="../role/getRole.do?uid=<%=trackId%>"><%=menu%></a></li>
	    <%
			}
		%>
		
		<%
			menu = MenuConstants.CLIENT_MANAGEMENT;
					if (user.getIsSubMenuEnabled().containsKey(menu) && user.getIsSubMenuEnabled().get(menu)) {
						String trackId = SecurityHolder.MENU_TRACKER_MAPPING.get(menu) + "," + sessionId;
		%>
					<li><a href="../clients/showClients.do?uid=<%=trackId%>"><%=menu%></a></li>
	    <%
			}
		%>
		
 				</ul>
			</li>
		 <%
		}	
   	%>
   			
   			
   <%
	}
 	%>

		</ul>
        
      </div>
 


