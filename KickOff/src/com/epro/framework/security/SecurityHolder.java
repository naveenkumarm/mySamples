/**
* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.security;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.epro.framework.util.MenuInfo;
import com.epro.framework.util.SubMenuInfo;

/*
 * SecurityHolder.java
 * Class description goes here.
 *
 * @version 1.0 Oct 20, 2012
 * @author suresh
 */

public class SecurityHolder {
	  
	public static Hashtable<Long,MenuInfo> APPLICATION_MENUS = new Hashtable<Long,MenuInfo>();
	public static Hashtable<String,List<SubMenuInfo>> APPLICATION_SUBMENUS = new Hashtable<String, List<SubMenuInfo>>();
	
	//holds menu as key and trackid as value
	public static Hashtable<String,String> MENU_TRACKER_MAPPING = new Hashtable<String, String>();
	
	//holds trackid as key and menu as value
	public static Hashtable<String,String> TRACKER_MENU_MAPPING = new Hashtable<String, String>();
	
	public static Hashtable<String, ModuleInfo> MODULE_URLS = new Hashtable<String, ModuleInfo>();
	public static Hashtable<String, SubModuleInfo> SUBMODULE_URLS = new Hashtable<String, SubModuleInfo>();
	public static List<String> COMMON_URLS = new ArrayList<String>();
		
	static{
		COMMON_URLS.add(UrlConstants.LOGOUT_URL);
		COMMON_URLS.add(UrlConstants.MY_ACCOUNT_URL); 
		COMMON_URLS.add(UrlConstants.VALIDATE_LOGIN_URL); 
		COMMON_URLS.add(UrlConstants.VALIDATE_LOGIN_ERROR_URL);
	}
}
