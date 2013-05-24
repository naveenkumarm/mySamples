package com.epro.framework.role.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epro.framework.login.util.UserSessionInfo;
import com.epro.framework.model.Menus;
import com.epro.framework.model.RoleAccessPermission;
import com.epro.framework.model.Roles;
import com.epro.framework.model.SubMenus;
import com.epro.framework.role.model.RoleListInfo;
import com.epro.framework.role.service.RoleService;
import com.epro.framework.security.RoleAccessPermissionInfo;
import com.epro.framework.user.controller.UserController;
import com.epro.framework.util.ApplicationConstants;
import com.epro.framework.util.MenuInfo;
import com.epro.framework.util.SubMenuInfo;

/*
 * RoleController.java
 * Class description goes here.
 *
 * @version 1.0 
 * @author Pradeepkumar.
 */

@Controller
@RequestMapping("/role")
public class RoleController {

	private Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private RoleService roleService;

	@Autowired
	private CreateRoleValidator createRoleValidator;

	/**
	 * This method used to show the Roles List Page
	 * 
	 * @param model
	 * @return roleList.jsp
	 */
	@RequestMapping(value = "/getRole", method = RequestMethod.GET)
	public String showRoles(ModelMap model, HttpSession session) {
		try {

			UserSessionInfo userSessionInfo = null;
			if (session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
				userSessionInfo = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
			}
 
		} catch (Exception e) {
			log.error("Exception in show Roles:::" + ExceptionUtils.getStackTrace(e));
		}
		return "roleList";
	}

	/**
	 * This method used to display the new role page.
	 * 
	 * @param model
	 * @param session
	 * @return createRolePage.jsp
	 */
	@RequestMapping(value = "/createRole", method = RequestMethod.GET)
	public String createRole(ModelMap model, HttpSession session, HttpServletRequest request) {
		try {
			Roles role = new Roles();
			loadAccessMenus(role.getRoleId(), request, true);
			model.addAttribute("role", role);
			model.addAttribute("headers", "Create Roles");
			model.addAttribute("Save", "Save");
		} catch (Exception e) {
			log.error("Exception in create Roles:::" + ExceptionUtils.getStackTrace(e));
		}
		return "createRolePage";
	}

	/**
	 * This Method used to save the Roles
	 * 
	 * @param selectedLevel
	 * @param status
	 * @param roleName
	 * @param model
	 * @param session
	 * @return roleList.jsp
	 */
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	public String saveRole(@ModelAttribute("role") Roles role, ModelMap model, HttpSession session, HttpServletResponse response, BindingResult result, Locale locale, HttpServletRequest request) {
		try {
			UserSessionInfo userSessionInfo = null;
			if (request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
				userSessionInfo = (UserSessionInfo) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY);
			}

 			createRoleValidator.roleValidate(role, result, userSessionInfo);
			List<Menus> menuList = roleService.getMenus();
			Set<RoleAccessPermission> accessPermissionList = new HashSet<RoleAccessPermission>();
			Set<RoleAccessPermission> popAccessPermissionList = null;

			if (result.hasErrors()) {
				model.addAttribute("role", role);
				model.addAttribute("headers", "Create Roles");
				model.addAttribute("Save", "Save");
				popAccessPermissionList = populateRoleAccessPermission(request, menuList, role, accessPermissionList);
				populateLoadAccessMenus(popAccessPermissionList, request, false);
				return "createRolePage";
			} else {

				popAccessPermissionList = populateRoleAccessPermission(request, menuList, role, accessPermissionList);

				 
				role.setRoleAccessPermissionses(popAccessPermissionList);
				int roleId = roleService.saveRole(role);

				if (roleId == 0) {
					log.info("Roles " + role.getRoleName() + " save failed.");

 					model.addAttribute("role", role);
					loadAccessMenus(role.getRoleId(), request, false);

					model.addAttribute("response", "");
					model.addAttribute("errorresponse", "Save new role failed!");

					model.addAttribute("headers", "Create Roles");
					model.addAttribute("Save", "Save");
					return "createRolePage";
				}

				if (session.getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
					userSessionInfo = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
				}
 				model.addAttribute("response", "Role \"" + role.getRoleName() + "\" Saved Successfully");
				model.addAttribute("errorresponse", "");

				return "roleList";
			}
		} catch (Exception e) {

			model.addAttribute("role", role);
			loadAccessMenus(role.getRoleId(), request, false);

			model.addAttribute("errorresponse", "Un expected error occur while saving role");
			model.addAttribute("response", "");

			model.addAttribute("headers", "Create Roles");
			log.error("Failed to save Role:" + ExceptionUtils.getStackTrace(e));
			model.addAttribute("Save", "Save");
		}
		model.addAttribute("Error", messageSource.getMessage("user.save.failure", new String[] { role.getRoleName() }, locale));
		return "createRolePage";
	}

	/**
	 * This method used to set the role access permission. Menus and submenus
	 * access permission.
	 * 
	 * @param request
	 * @param menuList
	 * @param role
	 * @param levelId
	 * @param accessPermissionList
	 * @return list of RoleAccessPermission
	 */
	private Set<RoleAccessPermission> populateRoleAccessPermission(HttpServletRequest request, List<Menus> menuList, Roles role,Set<RoleAccessPermission> accessPermissionList) {
		try {
			List<SubMenus> subMenuList = new ArrayList<SubMenus>();
			for (Menus menu : menuList) {
				RoleAccessPermission menuAccessPermission = new RoleAccessPermission();

				String selectionView = request.getParameter(menu.getMenuName() + "view");
				String selectionCreate = request.getParameter(menu.getMenuName() + "create");
				String selectionEdit = request.getParameter(menu.getMenuName() + "edit");
				String selectionDelete = request.getParameter(menu.getMenuName() + "deleted");

				if (selectionView != null) {
					boolean isViewEnabled = Boolean.parseBoolean(selectionView);
					menuAccessPermission.setIsViewEnabled(isViewEnabled);
				} else {
					menuAccessPermission.setIsViewEnabled(false);
				}

				if (selectionCreate != null) {
					boolean isCreateEnabled = Boolean.parseBoolean(selectionCreate);
					menuAccessPermission.setIsCreateEnabled(isCreateEnabled);
				} else {
					menuAccessPermission.setIsCreateEnabled(false);
				}

				if (selectionEdit != null) {
					boolean isEditEnabled = Boolean.parseBoolean(selectionEdit);
					menuAccessPermission.setIsEditEnabled(isEditEnabled);
				} else {
					menuAccessPermission.setIsEditEnabled(false);
				}

				if (selectionDelete != null) {
					boolean isDeleteEnabled = Boolean.parseBoolean(selectionDelete);
					menuAccessPermission.setIsDeleteEnabled(isDeleteEnabled);
				} else {
					menuAccessPermission.setIsDeleteEnabled(false);
				}

				menuAccessPermission.setSubMenuId(0);
				menuAccessPermission.setMenuId(menu.getMenuId());
				menuAccessPermission.setRoles(role);
				menuAccessPermission.setModifiedDate(new Date());
 				accessPermissionList.add(menuAccessPermission);

				subMenuList.clear();

				if (menu.getSubMenuses() != null) {
					subMenuList.addAll(menu.getSubMenuses());
					for (SubMenus subMenus : subMenuList) {
						String subMenuView = request.getParameter(subMenus.getSubMenuName() + "view");
						String subMenCreate = request.getParameter(subMenus.getSubMenuName() + "create");
						String subMenuEdit = request.getParameter(subMenus.getSubMenuName() + "edit");
						String subMenuDelete = request.getParameter(subMenus.getSubMenuName() + "deleted");

						RoleAccessPermission subMenuAccessPermission = new RoleAccessPermission();

						if (subMenuView != null) {
							boolean isViewEnabled = Boolean.parseBoolean(subMenuView);
							subMenuAccessPermission.setIsViewEnabled(isViewEnabled);
						} else {
							subMenuAccessPermission.setIsViewEnabled(false);
						}

						if (subMenCreate != null) {
							boolean isCreateEnabled = Boolean.parseBoolean(subMenCreate);
							subMenuAccessPermission.setIsCreateEnabled(isCreateEnabled);
						} else {
							subMenuAccessPermission.setIsCreateEnabled(false);
						}

						if (subMenuEdit != null) {
							boolean isEditEnabled = Boolean.parseBoolean(subMenuEdit);
							subMenuAccessPermission.setIsEditEnabled(isEditEnabled);
						} else {
							subMenuAccessPermission.setIsEditEnabled(false);
						}

						if (subMenuDelete != null) {
							boolean isDeleteEnabled = Boolean.parseBoolean(subMenuDelete);
							subMenuAccessPermission.setIsDeleteEnabled(isDeleteEnabled);
						} else {
							subMenuAccessPermission.setIsDeleteEnabled(false);
						}

						subMenuAccessPermission.setMenuId(menu.getMenuId());
						subMenuAccessPermission.setSubMenuId(subMenus.getSubMenuId());
						subMenuAccessPermission.setRoles(role);
						subMenuAccessPermission.setModifiedDate(new Date());
 						accessPermissionList.add(subMenuAccessPermission);

					}
				}
			}
		} catch (Exception e) {
			log.error("Failed to populateRoleAccessPermission:" + ExceptionUtils.getStackTrace(e));
		}
		return accessPermissionList;

	}

	/**
	 * This method used for delete the Roles
	 * 
	 * @param roleId
	 * @param model
	 * @param session
	 * @param response
	 * @return roleList.jsp
	 */
	@RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
	public String deleteRole(int roleId, ModelMap model, HttpSession session, HttpServletResponse response) {

		UserSessionInfo userSessionInfo = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
		try {
			Roles role = roleService.getRoleByRoleId(roleId);
			boolean result = roleService.deleteRole(role);
			if (result == true) {
				response.getWriter().write(role.getRoleName() + "Deleted successfully");
				model.addAttribute("response", " Role \"" + role.getRoleName() + "\"Deleted successfully");
				model.addAttribute("errorresponse", "");
			} else {
				response.getWriter().write(role.getRoleName() + "was not Deleted");

				model.addAttribute("errorresponse", " Role \"" + role.getRoleName() + "\"not deleted successfully");
				model.addAttribute("response", "");
			}

		} catch (Exception e) {
			log.error("Exception in deleteRole::" + ExceptionUtils.getStackTrace(e));

			model.addAttribute("errorresponse", "Role was not deleted successfully");
			model.addAttribute("response", "");
		}
 		return "roleList";
	}

	/**
	 * This method used to load access menus and submenus.
	 * 
	 * @param accessPermissionList
	 * @param request
	 * @param isCreate
	 */
	private void populateLoadAccessMenus(Set<RoleAccessPermission> accessPermissionList, HttpServletRequest request, boolean isCreate) {
		try {
			List<Menus> menuList = roleService.getMenus();

			List<MenuInfo> menuInfoList = new ArrayList<MenuInfo>();
			List<SubMenuInfo> subMenuInfoList = null;

			for (Menus menu : menuList) {

				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setMenuId(menu.getMenuId());
				menuInfo.setMenuName(menu.getMenuName());
				menuInfo.setMenuOrder(menu.getOrderId());
				menuInfoList.add(menuInfo);

				subMenuInfoList = new ArrayList<SubMenuInfo>();

				for (SubMenus subMenu : menu.getSubMenuses()) {
					SubMenuInfo subMenuInfo = new SubMenuInfo();
					subMenuInfo.setParentMenuId((int) menu.getMenuId());
					subMenuInfo.setSubMenuId(subMenu.getSubMenuId());
					subMenuInfo.setSubMenuName(subMenu.getSubMenuName());
					subMenuInfo.setSubMenuOrder(subMenu.getOrderId());
					subMenuInfoList.add(subMenuInfo);
				}

				Collections.sort(subMenuInfoList, new Comparator<SubMenuInfo>() {
					public int compare(SubMenuInfo s1, SubMenuInfo s2) {
						return (s1.getSubMenuOrder() < s2.getSubMenuOrder() ? -1 : (s1.getSubMenuOrder() == s2.getSubMenuOrder() ? 0 : 1));
					}
				});

				menuInfo.setSubMenuInfoList(subMenuInfoList);
			}

			Hashtable<Long, RoleAccessPermissionInfo> menuAccessPermissionHash = new Hashtable<Long, RoleAccessPermissionInfo>();
			Hashtable<Long, RoleAccessPermissionInfo> subMenuAccessPermissionHash = new Hashtable<Long, RoleAccessPermissionInfo>();

			if (!isCreate) {
				for (RoleAccessPermission roleAccessPermission : accessPermissionList) {
					if (roleAccessPermission.getSubMenuId() == 0) {
						RoleAccessPermissionInfo roleAccessPermissionInfo = setRoleAccessPermissionInfo(roleAccessPermission);

						menuAccessPermissionHash.put(roleAccessPermission.getMenuId(), roleAccessPermissionInfo);
					} else {

						RoleAccessPermissionInfo roleAccessPermissionInfo = setRoleAccessPermissionInfo(roleAccessPermission);

						if (roleAccessPermission.getIsViewEnabled() && roleAccessPermission.getIsCreateEnabled() && roleAccessPermission.getIsEditEnabled() && roleAccessPermission.getIsDeleteEnabled()) {
							roleAccessPermissionInfo.setAllChecked(true);
						}

						subMenuAccessPermissionHash.put(roleAccessPermission.getSubMenuId(), roleAccessPermissionInfo);
					}
				}
			}
			request.setAttribute("menuAccessPermissionHash", menuAccessPermissionHash);
			request.setAttribute("subMenuAccessPermissionHash", subMenuAccessPermissionHash);
			request.setAttribute("menuList", menuInfoList);
		} catch (Exception e) {
			log.error("Exception in populateLoadAccessMenus::" + ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * This method used to load access menus and submenus.
	 * 
	 * @param roleId
	 * @param request
	 * @param isCreate
	 */
	private void loadAccessMenus(long roleId, HttpServletRequest request, boolean isCreate) {
		try {
			List<Menus> menuList = roleService.getMenus();

			List<MenuInfo> menuInfoList = new ArrayList<MenuInfo>();
			List<SubMenuInfo> subMenuInfoList = null;

			for (Menus menu : menuList) {

				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setMenuId(menu.getMenuId());
				menuInfo.setMenuName(menu.getMenuName());
				menuInfo.setMenuOrder(menu.getOrderId());
				menuInfoList.add(menuInfo);

				subMenuInfoList = new ArrayList<SubMenuInfo>();

				for (SubMenus subMenu : menu.getSubMenuses()) {
					SubMenuInfo subMenuInfo = new SubMenuInfo();
					subMenuInfo.setParentMenuId((int) menu.getMenuId());
					subMenuInfo.setSubMenuId(subMenu.getSubMenuId());
					subMenuInfo.setSubMenuName(subMenu.getSubMenuName());
					subMenuInfo.setSubMenuOrder(subMenu.getOrderId());
					subMenuInfoList.add(subMenuInfo);
				}

				Collections.sort(subMenuInfoList, new Comparator<SubMenuInfo>() {
					public int compare(SubMenuInfo s1, SubMenuInfo s2) {
						return (s1.getSubMenuOrder() < s2.getSubMenuOrder() ? -1 : (s1.getSubMenuOrder() == s2.getSubMenuOrder() ? 0 : 1));
					}
				});

				menuInfo.setSubMenuInfoList(subMenuInfoList);
			}

			Hashtable<Long, RoleAccessPermissionInfo> menuAccessPermissionHash = new Hashtable<Long, RoleAccessPermissionInfo>();
			Hashtable<Long, RoleAccessPermissionInfo> subMenuAccessPermissionHash = new Hashtable<Long, RoleAccessPermissionInfo>();

			if (!isCreate) {
				List<RoleAccessPermission> menuAccessPermissionList = roleService.getMenuAccessPermissions(roleId);
				if (menuAccessPermissionList != null) {
					for (RoleAccessPermission roleAccessPermission : menuAccessPermissionList) {

						RoleAccessPermissionInfo roleAccessPermissionInfo = setRoleAccessPermissionInfo(roleAccessPermission);
						menuAccessPermissionHash.put(roleAccessPermission.getMenuId(), roleAccessPermissionInfo);
					}
				}

				List<RoleAccessPermission> subMenuAccessPermissionList = roleService.getSubMenuAccessPermissions(roleId);

				if (subMenuAccessPermissionList != null) {
					for (RoleAccessPermission roleAccessPermission : subMenuAccessPermissionList) {

						RoleAccessPermissionInfo roleAccessPermissionInfo = setRoleAccessPermissionInfo(roleAccessPermission);
						if (roleAccessPermission.getIsViewEnabled() && roleAccessPermission.getIsCreateEnabled() && roleAccessPermission.getIsEditEnabled() && roleAccessPermission.getIsDeleteEnabled()) {
							roleAccessPermissionInfo.setAllChecked(true);
						}

						subMenuAccessPermissionHash.put(roleAccessPermission.getSubMenuId(), roleAccessPermissionInfo);
					}
				}
			}

			request.setAttribute("menuAccessPermissionHash", menuAccessPermissionHash);
			request.setAttribute("subMenuAccessPermissionHash", subMenuAccessPermissionHash);
			request.setAttribute("menuList", menuInfoList);
		} catch (Exception e) {
			log.error("Exception in loadAccessMenus::" + ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * This Method used to set the RoleAccessPermissionInfo values.
	 * 
	 * @param roleAccessPermission
	 * @return roleAccessPermissionInfo
	 */
	private RoleAccessPermissionInfo setRoleAccessPermissionInfo(RoleAccessPermission roleAccessPermission) {
		RoleAccessPermissionInfo roleAccessPermissionInfo = new RoleAccessPermissionInfo();

		try {
			roleAccessPermissionInfo.setPermissionId(roleAccessPermission.getPermissionId());
			roleAccessPermissionInfo.setRoleId(roleAccessPermission.getRoles().getRoleId());
 			roleAccessPermissionInfo.setMenuId(roleAccessPermission.getMenuId());
			roleAccessPermissionInfo.setSubMenuId(roleAccessPermission.getSubMenuId());
			roleAccessPermissionInfo.setViewEnabled(roleAccessPermission.getIsViewEnabled());
			roleAccessPermissionInfo.setEditEnabled(roleAccessPermission.getIsEditEnabled());
			roleAccessPermissionInfo.setCreateEnabled(roleAccessPermission.getIsCreateEnabled());
			roleAccessPermissionInfo.setDeleteEnabled(roleAccessPermission.getIsDeleteEnabled());
		} catch (Exception e) {
			log.error("Exception in setRoleAccessPermissionInfo::" + ExceptionUtils.getStackTrace(e));
		}
		return roleAccessPermissionInfo;
	}

	/**
	 * This method used to edit the existed Roles Information
	 * 
	 * @param roleId
	 * @param model
	 * @return createRolePage.jsp
	 */
	@RequestMapping(value = "/editRolePage", method = RequestMethod.GET)
	public String editRolePage(String roleId, ModelMap model, HttpServletRequest request) {
		try {
			Roles role = roleService.getRoleByRoleId(Integer.parseInt(roleId));
 			model.addAttribute("role", role);
 			loadAccessMenus(role.getRoleId(), request, false);
 			model.addAttribute("headers", "Edit Roles");
			model.addAttribute("Save", "Modify");
		} catch (Exception e) {
			log.error("Exception in Edit Role::::" + ExceptionUtils.getStackTrace(e));
		}
		return "createRolePage";
	}

 	/**
	 * This method used to show the RoleList in Datatable.
	 * 
	 * @param levelIdParam
	 * @param tenantIdParam
	 * @param subTenantIdParam
	 * @param roleNameKey
	 * @param model
	 * @param session
	 * @param request
	 */
	@RequestMapping(value = "/roleListInfo", method = RequestMethod.GET)
	public @ResponseBody
	String getRoleDataTable(String levelIdParam, String tenantIdParam, String subTenantIdParam, String roleNameKey, Model model, HttpSession session, HttpServletRequest request) {
		try {
			int startRecord = 0;
			int recordsToShow = 0;

			String sEcho = request.getParameter("sEcho");

			if (request.getParameter("iDisplayStart") != null) {
				startRecord = Integer.parseInt(request.getParameter("iDisplayStart"));
				startRecord = startRecord + 1;
			}

			if (request.getParameter("iDisplayLength") != null) {
				recordsToShow = Integer.parseInt(request.getParameter("iDisplayLength"));
			}

			ArrayList arrayObj = new ArrayList();
			List<RoleListInfo> roleList = new ArrayList<RoleListInfo>();
			JSONObject itemObj = new JSONObject();
			UserSessionInfo userSessionInfo = (UserSessionInfo) session.getAttribute(ApplicationConstants.USER_SESSION_KEY);
 
			roleList = roleService.getRoleDataTable(startRecord, recordsToShow, roleNameKey);
			int totalRows = 0;
			for (int i = 0; i < roleList.size(); i++) {
				RoleListInfo roleListInfo = roleList.get(i);
				totalRows = roleListInfo.getTotalRows();
				itemObj = JSONObject.fromObject(roleListInfo);
				arrayObj.add(itemObj);

			}

			JSONObject myObj = new JSONObject();
			myObj.put("sEcho", sEcho);
			myObj.put("iTotalRecords", totalRows);
			myObj.put("iTotalDisplayRecords", totalRows);
			myObj.put("aaData", arrayObj);
			return myObj.toString();
		} catch (Exception e) {
			log.error("Exception in getRoleDataTable::" + ExceptionUtils.getStackTrace(e));
			return "";
		}
	}

}
