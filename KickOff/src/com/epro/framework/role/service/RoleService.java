package com.epro.framework.role.service;

import java.util.List;

import com.epro.framework.model.Menus;
import com.epro.framework.model.RoleAccessPermission;
import com.epro.framework.model.Roles;
import com.epro.framework.role.model.RoleListInfo;

/*
 * RoleService.java
 * Interface description goes here.
 *
 * @version 1.0 
 * @author Pradeepkumar.
 */

public interface RoleService {

 
	public int saveRole(Roles role);

	public Roles getRoleByRoleId(int roleId);

	public boolean deleteRole(Roles role);

	public List<Menus> getMenus();

	public List<RoleAccessPermission> getMenuAccessPermissions(long roleId);

	public List<RoleAccessPermission> getSubMenuAccessPermissions(long roleId);

	public boolean isRoleNameExist(Roles role);

	public List<RoleListInfo> getRoleDataTable(int startRecord, int recordsToShow, String roleNameKey);

}
