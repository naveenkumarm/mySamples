/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epro.framework.model.Roles;
import com.epro.framework.model.Users;
import com.epro.framework.user.dao.UsersDAO;
import com.epro.framework.user.model.UserListInfo;

/*
 * UserServiceImpl.java
 * Class description goes here.
 *
 * @version 1.0 
 * @author Pradeepkumar.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersDAO usersDAO;
	
 	@Override
	public int saveUser(Users users) {
  		 
		users.setIsDeleted(false);
		users.setModifiedTime(new Date());
 		return usersDAO.saveUser(users);
	}

	@Override
	public Users getUserByUserId(int userId) {
		return usersDAO.getUserByUserId(userId);
	}

	@Override
	public boolean deleteUser(Users user1) {
		return usersDAO.deleteUser(user1);
	}

	@Override
	public List<Roles> getRoleInfo() {
		return usersDAO.getRoleInfo();
	}

	@Override
	public boolean isUserNameExist(Users user) {
		return usersDAO.isUserNameExist(user);
	}

	@Override
	public boolean isEmailExist(Users user) {
		return usersDAO.isEmailExist(user);
	}
 	 
	@Override
	public List<UserListInfo> getUsersDataTable(int startRecord, int recordsToShow, String userNameKey) {
		return usersDAO.getUsersDataTable(startRecord, recordsToShow, userNameKey);
	}

}
