package com.epro.framework.login.service;

/*
 * LoginService.java
 * Class description goes here.
 *
 * @version 1.0 26-Mar-2012
 * @author saravanau
 */

import java.util.List;

import com.epro.framework.model.Menus;
import com.epro.framework.model.Users;

public interface LoginService {
	public Users searchUser(String userName, String password);

	public Users searchUser(String userName);

	public boolean updateUser(Users user, Users updateusers);

	public int saveUser(Users user);

	public boolean isUserExists(Users users);

	public boolean isEmailExists(Users users);

 	public Users validateUser(String userName, String email);

	public boolean resetPassword(Users user, String password);

	public void resetOldPassword(Users user, String oldPassword);

	public List<Menus> getApplicationMenuList();
}
