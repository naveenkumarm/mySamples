package com.epro.adapter;


import java.util.List;

import com.epro.beans.UserInfo;

public interface AuthenticationAdapter {
	public UserInfo loginRequest(String loginId, String passWord);
	public String getPassword(String loginId);
	public boolean savePassword(String loginId,String password);
	public boolean saveUser(UserInfo userInfo);
	public List<UserInfo> getUserDetailsList(String userId,String roleId);
	public boolean deleteUserInfo(String userId,String roleId);
}
