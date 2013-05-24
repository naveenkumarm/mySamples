package com.epro.framework.role.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epro.framework.model.Menus;
import com.epro.framework.model.RoleAccessPermission;
import com.epro.framework.model.Roles;
import com.epro.framework.model.Users;
import com.epro.framework.role.model.RoleListInfo;

@Repository
@Transactional
public class RolesDAO {

	private Logger log = Logger.getLogger(RolesDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	 
	/**
	 * This method used to create new role. And updating a existing role.
	 * 
	 * @param role
	 * @return Role Id.
	 */
	public int saveRole(Roles role) {
		try {
			if (role.getRoleId() > 0)
				sessionFactory.getCurrentSession().merge(role);
			else
				sessionFactory.getCurrentSession().save(role);

			return role.getRoleId();
		} catch (Exception e) {
			log.error("Exception in saveRole:" + ExceptionUtils.getStackTrace(e));
		}
		return 0;
	}

	/**
	 * This method used to retrieve role information by using unique roleid.
	 * 
	 * @param roleId
	 * @return role
	 */
	public Roles getRoleByRoleId(int roleId) {
		Roles role = null;
		try {
			String queryString = "FROM Roles WHERE isDeleted='false' AND roleId=" + roleId;
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			role = (Roles) query.list().get(0);
			return role;
		} catch (Exception e) {
			log.error("Exception in getRoleByRoleId:" + ExceptionUtils.getStackTrace(e));
		} finally {
			role = null;
		}
		return role;
	}

	/**
	 * This method used to get all meta data Menus.
	 * 
	 * @return list of menus.
	 */
	public List<Menus> getMenus() {
		try {
			String queryString = "FROM Menus";
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			return query.list();
		} catch (Exception e) {
			log.error("Exception in getMenus" + ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	/**
	 * This method used to get menu access permissions by using unique roleid.
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleAccessPermission> getMenuAccessPermissions(long roleId) {
		try {
			String queryString = "FROM RoleAccessPermission WHERE roleId = " + roleId + " AND subMenuId = 0";
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			return query.list();

		} catch (Exception e) {
			log.error("Exception in getMenuAccessPermissions:" + ExceptionUtils.getStackTrace(e));
		}

		return null;
	}

	/**
	 * This method used to get submenu access permissions by using unique
	 * roleid.
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleAccessPermission> getSubMenuAccessPermissions(long roleId) {
		try {
			String queryString = "FROM RoleAccessPermission WHERE roleId = " + roleId + " AND subMenuId > 0";
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			return query.list();

		} catch (Exception e) {
			log.error("Exception in getSubMenuAccessPermissions:" + ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	/**
	 * This method used for delete the Roles information
	 * 
	 * @param role
	 * @return
	 */
	public boolean deleteRole(Roles role) {
		try {
			role.setIsDeleted(true);
			sessionFactory.getCurrentSession().saveOrUpdate(role);
			return true;
		} catch (Exception e) {
			log.error("Exception in deleteRole:" + ExceptionUtils.getStackTrace(e));
			return false;
		}
	}

	/**
	 * This method used to check whether RoleName already exist.
	 * 
	 * @param role
 	 * @return
	 */
	public boolean isRoleNameExist(Roles role) {
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("FROM Roles WHERE isDeleted='false' AND roleName='" + role.getRoleName() + "'");

			if (role.getRoleId() != 0) {
				queryString.append(" AND roleId !=" + role.getRoleId());
			}
			Query query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
			List<Users> results = query.list();
			if (results.size() >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			log.error("Exception in isRollNameExist" + ExceptionUtils.getStackTrace(e));
			return false;
		}
	}

	/**
	 * This method used to get the list of role.
	 * 
	 * @param levelId
	 * @param tenantId
	 * @param subTenantId
	 * @param startRecord
	 * @param recordsToShow
	 * @param roleNameKey
	 * @return
	 */
	public List<RoleListInfo> getRoleDataTable(int startRecord, int recordsToShow, String roleNameKey) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CallableStatement cstmt = session.connection().prepareCall("{call [ADMIN_ROLE_MANAGEMENT](?,?,?)}");
			List<RoleListInfo> roleListInfo = new ArrayList<RoleListInfo>();
 			cstmt.setInt(1, startRecord);
			cstmt.setInt(2, recordsToShow);
			cstmt.setString(3, roleNameKey);

			ResultSet rs = cstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					RoleListInfo roleInfo = new RoleListInfo();
					roleInfo.setIndex(rs.getInt(1));
					roleInfo.setRoleId(rs.getInt(2));
					roleInfo.setRoleName(rs.getString(3));
					roleInfo.setOrganization(rs.getString(4));
 					roleInfo.setStatus(rs.getString(5));
					roleInfo.setTotalRows(rs.getInt(6));
 					roleInfo.setEdit(rs.getInt(2));
					roleInfo.setDeleted(rs.getInt(2));
 					roleListInfo.add(roleInfo);
				}
			}
			return roleListInfo;
		} catch (Exception e) {
			log.error("Exception in getRoleDataTable" + ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

}