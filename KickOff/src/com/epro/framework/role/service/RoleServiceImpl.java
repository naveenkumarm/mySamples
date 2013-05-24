package com.epro.framework.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epro.framework.model.Menus;
import com.epro.framework.model.RoleAccessPermission;
import com.epro.framework.model.Roles;
import com.epro.framework.role.dao.RolesDAO;
import com.epro.framework.role.model.RoleListInfo;

/*
 * RoleServiceImpl.java
 * Class description goes here.
 *
 * @version 1.0 
 * @author Pradeepkumar.
 */

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RolesDAO rolesDAO;

 
	@Override
	public int saveRole(Roles role) {
		role.setOrganization("EPRO");
		role.setIsDeleted(false);
		return rolesDAO.saveRole(role);
	}

	@Override
	public Roles getRoleByRoleId(int roleId) {
		return rolesDAO.getRoleByRoleId(roleId);
	}

	@Override
	public boolean deleteRole(Roles role) {
		return rolesDAO.deleteRole(role);
	}

	@Override
	public List<Menus> getMenus() {
		return rolesDAO.getMenus();
	}

	/*
	 * @Override public List<SubMenus> getSubMenus() { return
	 * rolesDAO.getSubMenus(); }
	 */

	@Override
	public List<RoleAccessPermission> getMenuAccessPermissions(long roleId) {
		return rolesDAO.getMenuAccessPermissions(roleId);
	}

	@Override
	public List<RoleAccessPermission> getSubMenuAccessPermissions(long roleId) {
		return rolesDAO.getSubMenuAccessPermissions(roleId);
	}

	@Override
	public boolean isRoleNameExist(Roles role) {
		return rolesDAO.isRoleNameExist(role);
	}

	@Override
	public List<RoleListInfo> getRoleDataTable(int startRecord, int recordsToShow, String roleNameKey) {
		return rolesDAO.getRoleDataTable(startRecord, recordsToShow, roleNameKey);
	}

}