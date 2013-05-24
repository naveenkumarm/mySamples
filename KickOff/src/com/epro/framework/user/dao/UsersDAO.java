/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.user.dao;

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

import com.epro.framework.model.Roles;
import com.epro.framework.model.Users;
import com.epro.framework.user.model.UserListInfo;

/*
 * UsersDAO.java
 * Class which is used to carry out database operations related to User information.
 *
 * @version 1.0 .
 * @author Pradeepkumar.
 */
@Repository
@Transactional
public class UsersDAO {
	/** Logger for the class */
	private Logger log = Logger.getLogger(UsersDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	

	/**
	 * This method used to save the User information
	 * 
	 * @param user
	 * @return userId
	 */
	public int saveUser(Users user) {
		try {
 			if (user.getUserId() == 0) {
				return (Integer) sessionFactory.getCurrentSession().save(user);
			} else {
				sessionFactory.getCurrentSession().saveOrUpdate(user);
				return 1;
			}
		} catch (Exception e) {
			log.error("Exception in saveUser::" + ExceptionUtils.getStackTrace(e));
			return 0;
		}
	}

	/**
	 * This method used to get the user details
	 * 
	 * @param userId
	 * @return User object
	 */
	public Users getUserByUserId(int userId) {
		Users user = null;
		try {
 			String queryString = "FROM Users WHERE isDeleted='false' AND userId=" + userId;
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
 			user = (Users) query.list().get(0);
			return user;
		} 
		catch (Exception e) {
			log.error("Exception in getUserByUserId::" + ExceptionUtils.getStackTrace(e));
		} 
	 
		return user;
	}

	/**
	 * This method used for delete the user information
	 * 
	 * @param users
	 */
	public boolean deleteUser(Users users) {
		try {
			users.setIsDeleted(true);
			sessionFactory.getCurrentSession().saveOrUpdate(users);
			return true;
		} catch (Exception e) {
			log.error("Exception in deleteUser::" + ExceptionUtils.getStackTrace(e));
		}
		return false;
	}

	/**
	 * This method used to get the role information
	 * 
	 * @param roleLevel
	 * @param subTenantId
	 * @param tenantId
	 * @return list of Roles
	 */
	public List<Roles> getRoleInfo() {
		try {
 			String queryString = "FROM Roles WHERE isDeleted='false'";
 			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			return query.list();
		} catch (Exception e) {
			log.error("Exception in getRoleInfo::" + ExceptionUtils.getStackTrace(e));
			return null;
		}

	}

	/**
	 * This method used to check sameUserName Exist
	 * 
	 * @param user
	 */
	public boolean isUserNameExist(Users user) {
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("FROM Users WHERE isDeleted='false' AND userName='" + user.getUserName() + "'");

			if (user.getUserId() != 0) {
				queryString.append(" AND userId !=" + user.getUserId());
			}
			Query query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
			List<Users> results = query.list();
			if (results.size() >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			log.error("Exception in isUserNameExist::" + ExceptionUtils.getStackTrace(e));
			return false;
		}
	}

	/**
	 * This method used to check Same Email Exist
	 * 
	 * @param user
	 */
	public boolean isEmailExist(Users user) {
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("FROM Users WHERE  isDeleted='false' AND emailId='" + user.getEmailId() + "'");

			if (user.getUserId() != 0) {
				queryString.append(" AND userId!=" + user.getUserId());
			}
			Query query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
			List<Users> results = query.list();
			if (results.size() >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			log.error("Exception in isEmailExist::" + ExceptionUtils.getStackTrace(e));
			return false;
		}
	}

  	/**
	 * This method used to get users.
	 * 
	 * @param levelId
	 * @param tenantId
	 * @param subTenantId
	 * @param startRecord
	 * @param recordsToShow
	 * @param userNameKey
	 * @return
	 */
	public List<UserListInfo> getUsersDataTable(int startRecord, int recordsToShow, String userNameKey) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CallableStatement cstmt = session.connection().prepareCall("{call ADMIN_USERS(?,?,?)}");
			List<UserListInfo> userListInfo = new ArrayList<UserListInfo>();
 			cstmt.setInt(1, startRecord);
			cstmt.setInt(2, recordsToShow);
			cstmt.setString(3, userNameKey.trim());
			ResultSet rs = cstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					UserListInfo userInfo = new UserListInfo();
					userInfo.setIndex(rs.getInt(1));
					userInfo.setUserId(rs.getInt(2));
					userInfo.setUserName(rs.getString(3));
 					userInfo.setFirstName(rs.getString(4));
					userInfo.setLastName(rs.getString(5));
 					userInfo.setTotalRows(rs.getInt(6));
					userInfo.setEdit(rs.getInt(2));
					userInfo.setDelete(rs.getInt(2));
					userInfo.setDepartment(rs.getString(7));
					userListInfo.add(userInfo);
				}
			}
			return userListInfo;
		} catch (Exception e) {
			log.error("Exception in getUsersDataTable::" + ExceptionUtils.getStackTrace(e));
			return null;
		}

	}
}
