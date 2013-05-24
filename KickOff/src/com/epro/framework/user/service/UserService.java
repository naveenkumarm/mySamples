/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.user.service;

import java.util.List;

import com.epro.framework.model.Roles;
import com.epro.framework.model.Users;
import com.epro.framework.user.model.UserListInfo;

/*
 * UserService.java
 * Interface description goes here.
 *
 * @version 1.0 
 * @author Pradeepkumar.
 */

public interface UserService {

	public int saveUser(Users users);

	public Users getUserByUserId(int userId);

	public boolean deleteUser(Users user);

	public List<Roles> getRoleInfo();

	public boolean isUserNameExist(Users user);

	public boolean isEmailExist(Users user);

 	public List<UserListInfo> getUsersDataTable(int startRecord, int recordsToShow, String userNameKey);

}
