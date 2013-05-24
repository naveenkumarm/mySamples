package com.epro.framework.login.dao;

/*
 * LoginDAO.java
 * Class description goes here.
 *
 * @version 1.0 26-Mar-2012
 * @author saravanau
 */

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epro.framework.model.Menus;
import com.epro.framework.model.Users;

@Transactional
@Repository
public class LoginDAO {

	private Logger log = Logger.getLogger(LoginDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * This method is used to search the given username and password.
	 * 
	 * @param username
	 * @param password
	 * @return Users object.
	 */
	public Users searchUser(String username, String password) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion userNameCriteria = Restrictions.eq("userName", username);
			Criterion passwordCriteria = Restrictions.eq("password", password);
			Criterion isDeletedCriteria = Restrictions.eq("isDeleted", false);
			criteria.add(Restrictions.and(userNameCriteria, passwordCriteria));
			criteria.add(isDeletedCriteria);
			log.info("Authenticated......");
			List<Users> results = criteria.list();
			log.debug(results.size());
			if (results.size() >= 1) {
				log.info("results");
				return results.get(0);
			}
		} catch (Exception e) {
			log.error("Failed to search User:" + ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	/**
	 * This method is used to search the given username.
	 * 
	 * @param username
	 * @return Users object.
	 */
	public Users searchUser(String username) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			Criterion userNameCriteria = Restrictions.ilike("userName", username);
			Criterion isDeletedCriteria = Restrictions.eq("isDeleted", false);

			criteria.add(userNameCriteria);
			criteria.add(isDeletedCriteria);

			List<Users> userList = criteria.list();
			if (criteria.list().size() > 0) {
				return userList.get(0);
			}
		} catch (Exception e) {
			log.error("Failed to search User:" + ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

  
	/**
	 * This method is to validate user for reset password.
	 * 
	 * @param userName
	 * @param email
	 * @return validateUser object.
	 */
	public Users validateUser(String userName, String email) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
			if (userName != null && userName.length() != 0 && !userName.equals("")) {
				criteria.add(Restrictions.eq("userName", userName.trim()));
			}
			if (email != null && email.length() != 0 && !email.equals("")) {
				criteria.add(Restrictions.eq("emailId", email.trim()));
			}

			criteria.add(Restrictions.eq("isDeleted", Boolean.FALSE));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Users> usersList = criteria.list();
			if (usersList.size() >= 1) {
				return usersList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Exception in validateUser" + ExceptionUtils.getStackTrace(e));
			return null;

		}
	}

	/**
	 * This method is to reset the forget Password.
	 * 
	 * @param user
	 * @return true(if user password resets) else false.
	 */
	public boolean resetPassword(Users user) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			log.error("Exception in resetPassword." + ExceptionUtils.getStackTrace(e));
			return false;

		}
	}

	/**
	 * This method is to reset old password.
	 * 
	 * @param user
	 */
	public void resetOldPassword(Users user) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
		} catch (Exception e) {
			log.error("Exception in resetPassword." + ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * This menu is to populate the Menus.
	 * 
	 * @return List of Menus.
	 */
	public List<Menus> getApplicationMenuList() {
		try {
			String strQuery = " from Menus order by orderId ASC";
			Query query = sessionFactory.getCurrentSession().createQuery(strQuery);
			List<Menus> menuList = query.list();
			if (menuList.size() > 0) {
				return menuList;
			}
		} catch (Exception e) {
			log.error("Exception in getting ApplicationMenuList " + ExceptionUtils.getStackTrace(e));
		}

		return null;
	}

}
