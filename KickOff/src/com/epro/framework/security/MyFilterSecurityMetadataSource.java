/**
\* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.epro.framework.login.service.LoginService;
import com.epro.framework.login.util.UserSessionInfo;
import com.epro.framework.util.AppContext;
import com.epro.framework.util.ApplicationConstants;

/*
 * MyFilterSecurityMetadataSource.java
 * Class description goes here.
 *
 * @version 1.0 Oct 20, 2012
 * @author suresh
 */

public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
 	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		 return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		
		FilterInvocation fi = (FilterInvocation) object;
	    String url = fi.getRequestUrl();
	    
	    //System.out.println("Request URL >> " + url);
	    
	    try {
			//check whether the user is logged in or not
			if(SecurityContextHolder.getContext().getAuthentication() == null || fi.getRequest().getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY) == null){ 
				System.out.println("User not logged in....");
			}
			else{
				UserSessionInfo loggedUser = (UserSessionInfo) fi.getRequest().getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY);
				
				Collection<?> attributes = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
				 
				GrantedAuthority grant = ((List<GrantedAuthority>)attributes).get(0);
				
				String currentMenu = fi.getHttpRequest().getParameter("uid");
				
				//System.out.println("Current Menu >> " + currentMenu);
				
				//validate the uid
				if(currentMenu != null){
					String[] uid = currentMenu.split("\\,");
					
					if(uid.length == 2 && uid[1].equals(fi.getRequest().getSession().getId())){
						if(SecurityHolder.TRACKER_MENU_MAPPING.containsKey(uid[0]))
							currentMenu = SecurityHolder.TRACKER_MENU_MAPPING.get(uid[0]);
					}
				}
				
				//System.out.println("Current Menu >> " + currentMenu);
				
				//System.out.println("SecurityHolder.SUBMODULE_URLS >> " + SecurityHolder.SUBMODULE_URLS);
				if(currentMenu != null && SecurityHolder.SUBMODULE_URLS.containsKey(currentMenu)){
					SubModuleInfo subModuleInfo = SecurityHolder.SUBMODULE_URLS.get(currentMenu);
					
					int lastIndex = url.indexOf("?");
					String actualUrl = url.substring(0,lastIndex);
					//System.out.println("Actual URL >> " + actualUrl);
					
					if(subModuleInfo.getViewModeUrls() != null && subModuleInfo.getViewModeUrls().contains(actualUrl)){
						return null;
					}
					else if(subModuleInfo.getCreateModeUrls() != null && subModuleInfo.getCreateModeUrls().contains(actualUrl)){
						return null;
					}
					else if(subModuleInfo.getEditModeUrls() != null && subModuleInfo.getEditModeUrls().contains(actualUrl)){
						return null;
					}
					else if(subModuleInfo.getDeleteModeUrls() != null && subModuleInfo.getDeleteModeUrls().contains(actualUrl)){
						return null;
					}
					else if(SecurityHolder.COMMON_URLS.contains(url)){
						return null;
					}
					else{
						List<ConfigAttribute> attributes1 = new ArrayList<ConfigAttribute>();
						fi.getRequest().getSession().invalidate();
						attributes1.add(new SecurityConfig("IS_NOT_AUTHENTICATED_FULLY"));
					}
				}
				else if(SecurityHolder.COMMON_URLS.contains(url)){
					return null;
				}
				else{
					List<ConfigAttribute> attributes1 = new ArrayList<ConfigAttribute>();
					fi.getRequest().getSession().invalidate();
					attributes1.add(new SecurityConfig("IS_NOT_AUTHENTICATED_FULLY"));
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	   
		if(SecurityHolder.COMMON_URLS.contains(url)){
			return null;
		}
		else{
			List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
			 
			if(fi.getRequest().getParameter("uid") != null){
				String uid = fi.getRequest().getParameter("uid");
				String[] uidArray = uid.split("\\,");
				if(uidArray.length == 2){
					//update logout time
					ApplicationContext ctx = AppContext.getApplicationContext();
					LoginService loginService = (LoginService) ctx.getBean("loginservice");
					/*if(loginService != null)*/
						/*loginService.saveLogoutTime(uidArray[1]);*/
				}
			}
			 
			
			fi.getRequest().getSession().invalidate();
			attributes.add(new SecurityConfig("IS_NOT_AUTHENTICATED_FULLY"));
			return attributes;
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
