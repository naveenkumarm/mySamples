package com.epro.framework.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.epro.framework.login.service.LoginService;
import com.epro.framework.model.Menus;
import com.epro.framework.model.SubMenus;
import com.epro.framework.security.MenuConstants;
import com.epro.framework.security.SecurityHolder;
import com.epro.framework.security.SubModuleInfo;
import com.epro.framework.security.UrlConstants;

public class ApplicationConfigLoader {

	private Logger log = Logger.getLogger(getClass());

	
 
	@Autowired
	private LoginService loginService;
 	
	

	@Value("#{applicationProperties['client_logo_path']}")
	private String clientImagePath;
	
	private String resetTemplate;

	@Value("#{applicationProperties['reset_password_template']}")
	public void init() {
		log.info("Initializing ApplicationConfig...");
  
		/* Email Template for Reset Password */
		ApplicationConstants.RESET_TEMPLATE = this.resetTemplate;
		ApplicationConstants.CLIENT_LOGO_PATH = this.clientImagePath;
		
		File file = null;
		if (clientImagePath != null) {
			try {
				file = new File(clientImagePath);
				if (!file.exists()) {
					file.mkdirs();
				}
			} catch (Exception e) {
				log.error("Error while creating audioFileUploadPath: " + ExceptionUtils.getStackTrace(e));
			}
		}
		
		loadApplicationMenus();

		loadRoleBasedAccessDetails();
 		 
	}
 	 
	private void loadApplicationMenus() {
		
		List<Menus> menuList = loginService.getApplicationMenuList();

		if (menuList != null) {
			
			for (Menus menu : menuList) {
				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setMenuId(menu.getMenuId());
				menuInfo.setMenuName(menu.getMenuName());
				menuInfo.setMenuOrder(menu.getOrderId());
 				SecurityHolder.APPLICATION_MENUS.put(menuInfo.getMenuId(), menuInfo);
				SecurityHolder.APPLICATION_SUBMENUS.put(menuInfo.getMenuName(), new ArrayList<SubMenuInfo>());

				if (menu.getSubMenuses() != null) {
					for (SubMenus subMenu : menu.getSubMenuses()) {
						SubMenuInfo subMenuInfo = new SubMenuInfo();
						subMenuInfo.setSubMenuId(subMenu.getSubMenuId());
						subMenuInfo.setSubMenuName(subMenu.getSubMenuName());
						subMenuInfo.setSubMenuOrder(subMenu.getOrderId());
						SecurityHolder.APPLICATION_SUBMENUS.get(menuInfo.getMenuName()).add(subMenuInfo);
						String randomId = new RandomGUID(true).toString();
						SecurityHolder.MENU_TRACKER_MAPPING.put(subMenuInfo.getSubMenuName(), randomId);
						SecurityHolder.TRACKER_MENU_MAPPING.put(randomId, subMenuInfo.getSubMenuName());
					}
				}
			}
		}
	}

	private void loadRoleBasedAccessDetails() {
 		loadModules();
  	}

	private void loadModules() {
		
		/**  URL ACCESS SETTINGS **/
		

		SubModuleInfo subModuleInfo = new SubModuleInfo();
 		/** ROLE MANAGEMENT **/
		subModuleInfo = new SubModuleInfo();
		subModuleInfo.setSubModuleName(MenuConstants.ROLE_MANAGEMENT);
		// view
		subModuleInfo.setViewModeUrls(new ArrayList<String>());
		subModuleInfo.getViewModeUrls().add(UrlConstants.ROLE_GETROLE_LIST);
		subModuleInfo.getViewModeUrls().add(UrlConstants.ROLE_GETROLE_LIST_INFO);

		// create
		subModuleInfo.setCreateModeUrls(new ArrayList<String>());
		subModuleInfo.getCreateModeUrls().add(UrlConstants.ROLE_CREATE_ROLE);
		subModuleInfo.getCreateModeUrls().add(UrlConstants.ROLE_SAVE_ROLE);

		// edit
		subModuleInfo.setEditModeUrls(new ArrayList<String>());
		subModuleInfo.getEditModeUrls().add(UrlConstants.ROLE_EDITROLEPAGE_ROLE);
		subModuleInfo.getEditModeUrls().add(UrlConstants.ROLE_UPDATE_ROLE);
		subModuleInfo.getEditModeUrls().add(UrlConstants.ROLE_SAVE_ROLE);

		// delete
		subModuleInfo.setDeleteModeUrls(new ArrayList<String>());
		subModuleInfo.getDeleteModeUrls().add(UrlConstants.ROLE_DELETE_ROLE);

		SecurityHolder.SUBMODULE_URLS.put(MenuConstants.ROLE_MANAGEMENT, subModuleInfo);

		/** USER MANAGEMENT **/
		subModuleInfo = new SubModuleInfo();
		subModuleInfo.setSubModuleName(MenuConstants.USER_MANAGEMENT);
		// view
		subModuleInfo.setViewModeUrls(new ArrayList<String>());
		subModuleInfo.getViewModeUrls().add(UrlConstants.USER_GET_USERS_LIST);
		subModuleInfo.getViewModeUrls().add(UrlConstants.USER_GET_USERINFO);
		subModuleInfo.getViewModeUrls().add(UrlConstants.USER_GET_ROLEINFO);
		subModuleInfo.getViewModeUrls().add(UrlConstants.USER_GET_USERS_LIST_INFO);
		
		// create
		subModuleInfo.setCreateModeUrls(new ArrayList<String>());
		subModuleInfo.getCreateModeUrls().add(UrlConstants.USER_CREATE_USER);
		subModuleInfo.getCreateModeUrls().add(UrlConstants.USER_SAVE_NEW_USER);

		// edit
		subModuleInfo.setEditModeUrls(new ArrayList<String>());
		subModuleInfo.getEditModeUrls().add(UrlConstants.USER_GET_USERS_LIST);
		subModuleInfo.getEditModeUrls().add(UrlConstants.USER_EDIT_USER_PAGE);
		subModuleInfo.getEditModeUrls().add(UrlConstants.USER_SAVE_NEW_USER);
		subModuleInfo.getEditModeUrls().add(UrlConstants.USER_GET_USERS_LIST_INFO);

		// delete
		subModuleInfo.setDeleteModeUrls(new ArrayList<String>());
		subModuleInfo.getDeleteModeUrls().add(UrlConstants.USER_DELETE_USER);

		SecurityHolder.SUBMODULE_URLS.put(MenuConstants.USER_MANAGEMENT, subModuleInfo);

		/** CLIENT MANAGEMENT **/
		subModuleInfo = new SubModuleInfo();
		subModuleInfo.setSubModuleName(MenuConstants.CLIENT_MANAGEMENT);
		// view
		subModuleInfo.setViewModeUrls(new ArrayList<String>());
		subModuleInfo.getViewModeUrls().add(UrlConstants.CLIENT_SHOW_CLIENT_LIST);
		subModuleInfo.getViewModeUrls().add(UrlConstants.CLIENT_GET_CLIENT_DATATABLE);
		
		
		// create
		subModuleInfo.setCreateModeUrls(new ArrayList<String>());
		subModuleInfo.getCreateModeUrls().add(UrlConstants.CLIENT_CREATE_CLIENT);
		subModuleInfo.getCreateModeUrls().add(UrlConstants.CLIENT_SAVE_CLIENT);

		// edit
		subModuleInfo.setEditModeUrls(new ArrayList<String>());
		subModuleInfo.getEditModeUrls().add(UrlConstants.CLIENT_EDIT_CLIENT);
		subModuleInfo.getEditModeUrls().add(UrlConstants.CLIENT_SAVE_CLIENT);
		subModuleInfo.getEditModeUrls().add(UrlConstants.CLIENT_IMAGE_UPLOAD);

		// delete
		subModuleInfo.setDeleteModeUrls(new ArrayList<String>());
		subModuleInfo.getDeleteModeUrls().add(UrlConstants.CLIENT_DELETE_CLIENT);

		SecurityHolder.SUBMODULE_URLS.put(MenuConstants.CLIENT_MANAGEMENT, subModuleInfo);
	}
}
