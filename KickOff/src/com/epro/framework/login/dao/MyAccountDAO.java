/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.login.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epro.framework.model.Users;

/*
 * UsersDAO.java
 * Class description goes here.
 *
 * @version 1.0 30-Mar-2012
 * @author saravanau
 */

@Transactional
@Repository
public class MyAccountDAO {

	private Logger log = Logger.getLogger(MyAccountDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

 	/**
	 * This Method which is used to save User.
	 * @param user
	 * @return
	 */
	public int saveUser(Users user) {
		try {
 			user.setIsDeleted(false);
			return (Integer) sessionFactory.getCurrentSession().save(user);
		} 
		catch (Exception e) {
			log.error("Failed to save User:" + e.getMessage());
			return 0;
		}
	}

 
	/**
	 * This Method which is used to update Users in database.
	 * @param user
	 * @return
	 */
	public boolean updateUser(Users user) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
 			log.info("User Service updated......");
		}
		catch (Exception e) {
			log.error("Failed to update user:" + e.getMessage());
		}

		return true;
	}
 
	/**
	 * This Method used to check the Same username exists.
	 * @param user
	 * @return
	 */
	public boolean isUserExists(Users user) {
		try { 
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion userNameCriteria = Restrictions.ilike("userName", user.getUserName());
			Criterion userIdCriteria = Restrictions.ne("userId", user.getUserId());
			criteria.add(Restrictions.and(userNameCriteria, userIdCriteria));

			List<Users> results = criteria.list();
			if (results.size() >= 1) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			log.error("Failed to check whether Same User name exist:" + e.getMessage());
			return false;
		}
	}
 
	/**
	 * This Method used to check the Same Email exists.
	 * @param user
	 * @return
	 */
	public boolean isEmailExists(Users user) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion emailCriteria = Restrictions.ilike("email", user.getEmailId());
			Criterion userIdCriteria = Restrictions.ne("userId", user.getUserId());
			criteria.add(Restrictions.and(emailCriteria, userIdCriteria));

			List<Users> results = criteria.list();
			if (results.size() >= 1) {
				return true;
			} 
			else {
				return false;
			}
		} 
		catch (Exception e) {
			log.error("Failed to check whether same Email exist:" + e.getMessage());
			return false;
		}
	}

}
