package com.epro.framework.login.service;

/*
 * LoginServiceImpl.java
 * Class description goes here.
 *
 * @version 1.0 26-Mar-2012
 * @author saravanau
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epro.framework.login.dao.LoginDAO;
import com.epro.framework.login.dao.MyAccountDAO;
import com.epro.framework.model.Menus;
import com.epro.framework.model.Users;

@Service("loginservice")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;
	@Autowired
	private MyAccountDAO myaccountDAO;

	@Override
	public Users searchUser(String userName, String password) {
		return loginDAO.searchUser(userName, password);
	}

	public Users searchUser(String userName) {
		return loginDAO.searchUser(userName);
	}

	@Override
	public int saveUser(Users user) {
		return myaccountDAO.saveUser(user);
	}

	@Override
	public boolean updateUser(Users users, Users updateusers) {
		users.setStatus(true);
 		if (updateusers.getPassword() != null && !updateusers.getPassword().trim().isEmpty() && !updateusers.getPassword().trim().equals("")) {
			users.setPassword(updateusers.getPassword());
		}
		users.setUserName(updateusers.getUserName());
		users.setEmailId(updateusers.getEmailId());
		users.setFirstName(updateusers.getFirstName());
		users.setLastName(updateusers.getLastName());
 		return myaccountDAO.updateUser(users);
	}

	@Override
	public boolean isUserExists(Users user) {
		return myaccountDAO.isUserExists(user);
	}

	@Override
	public boolean isEmailExists(Users user) {
		return myaccountDAO.isEmailExists(user);
	}

	 

	@Override
	public Users validateUser(String userName, String email) {
		return loginDAO.validateUser(userName, email);
	}

	@Override
	public boolean resetPassword(Users user, String password) {
		user.setPassword(password);
		return loginDAO.resetPassword(user);
	}

	@Override
	public void resetOldPassword(Users user, String oldPassword) {
		user.setPassword(oldPassword);
		loginDAO.resetOldPassword(user);
	}

	@Override
	public List<Menus> getApplicationMenuList() {
		return loginDAO.getApplicationMenuList();
	}

}
