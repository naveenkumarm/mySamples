package com.epro.adapter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ws.client.core.WebServiceTemplate;

import com.epro.adapter.AuthenticationAdapter;
import com.epro.beans.Role;
import com.epro.beans.UserInfo;
import com.epro.schema.ChangePasswordRequest;
import com.epro.schema.ChangePasswordResponse;
import com.epro.schema.DeleteUserDetailsRequest;
import com.epro.schema.DeleteUserDetailsResponse;
import com.epro.schema.GetPasswordRequest;
import com.epro.schema.GetPasswordResponse;
import com.epro.schema.GetUserDetailsRequest;
import com.epro.schema.GetUserDetailsResponse;
import com.epro.schema.LoginRequest;
import com.epro.schema.LoginResponse;
import com.epro.schema.RoleInfo;
import com.epro.schema.SaveUserInfoRequest;
import com.epro.schema.SaveUserInfoResponse;
import com.epro.schema.UserInformation;


public class AuthenticationAdapterImpl implements AuthenticationAdapter {
	private WebServiceTemplate webServiceTemplate;	
	public AuthenticationAdapterImpl(
			WebServiceTemplate webServiceTemplate) {
		super();
		this.webServiceTemplate = webServiceTemplate;
	}

	/**
	 * This method is used to call the service for get user info
	 */
	public UserInfo loginRequest(String loginId, String passWord){
		LoginRequest request = new LoginRequest();
		UserInfo info = new UserInfo();
		request.setLoginId(loginId);
		request.setPassWord(passWord);
		LoginResponse response = new LoginResponse();
		try{
			response = (LoginResponse) webServiceTemplate.marshalSendAndReceive(request);
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		if(response.getUserInfo() != null){
			info.setUserId(response.getUserInfo().getUserId());
			info.setLoginId(response.getUserInfo().getLoginId());
			info.setUserFirstName(response.getUserInfo().getUserFirstName());
			info.setUserLastName(response.getUserInfo().getUserLastName());
			info.setTimeSheetCutoffInd(response.getUserInfo().getCutOffDate());
			info.setMaxHours(response.getUserInfo().getMaxHours());
			List<RoleInfo> rolesDtoList = response.getUserInfo().getRoles();
			if(rolesDtoList != null){
				List<Role> rolesList = new ArrayList<Role>();
				for(RoleInfo data : rolesDtoList){
					Role role = new Role();
					role.setRoleId(data.getRoleId());
					role.setRoleName(data.getRoleName());
					role.setRoleDescription(data.getRoleDesc());
					rolesList.add(role);
				}
				info.setUserRoles(rolesList);
			}
			info.setUserRoles(info.getUserRoles());
		}else{
			return null;
		}
		return info;
	}
	
	/**
	 * This method is used to call the service for get old password
	 */
	@Override
	public String getPassword(String loginId) {
		// TODO Auto-generated method stub
		GetPasswordResponse response = new GetPasswordResponse();
		GetPasswordRequest request = new GetPasswordRequest();
		request.setLoginId(loginId);
			try{
			response = (GetPasswordResponse) webServiceTemplate.marshalSendAndReceive(request);
			}catch (Exception e) {
				// TODO: handle exception
			}
		return response.getPassWord();
	}
	
	/**
	 * This method is used to call the service for change password
	 */
	@Override
	public boolean savePassword(String loginId,String password){
		ChangePasswordResponse response = new ChangePasswordResponse();
		ChangePasswordRequest request = new ChangePasswordRequest();
		request.setLoginId(loginId);
		request.setPassWord(password);
		try {
			response = (ChangePasswordResponse) webServiceTemplate.marshalSendAndReceive(request);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response.getSuccess();
	}

	/**
	 * This method is used to call the service for save user
	 */
	@Override
	public boolean saveUser(UserInfo userInfo) {
		SaveUserInfoRequest request=new SaveUserInfoRequest();
		SaveUserInfoResponse response=new SaveUserInfoResponse();
		UserInformation user=new UserInformation();
		user.setUserId(userInfo.getUserId());
		user.setLoginId(userInfo.getUserId());
		user.setEmployeeId(userInfo.getEmployeeId());
		user.setPassword(userInfo.getPassword());
		user.setRoleId(userInfo.getRoleId());
		request.setUserInfo(user);
		request.setSourceFrom(userInfo.getSourceFrom());
		try {
			response = (SaveUserInfoResponse) webServiceTemplate.marshalSendAndReceive(request);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response.getSuccess();
	}
	
	/**
	 * This method is used to delete user information
	 */
	@Override
	public boolean deleteUserInfo(String userId, String roleId) {
		// TODO Auto-generated method stub
		DeleteUserDetailsRequest request = new DeleteUserDetailsRequest();
		DeleteUserDetailsResponse response = new DeleteUserDetailsResponse();
		request.setUserId(userId);
		request.setRoleId(roleId);
		try {
			response = (DeleteUserDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response.getSuccess();
	}
	
	/**
	 * This method is used to get user Information
	 */
	@Override
	public List<UserInfo> getUserDetailsList(String userId, String roleId) {
		// TODO Auto-generated method stub
		GetUserDetailsRequest request = new GetUserDetailsRequest();
		GetUserDetailsResponse response = new GetUserDetailsResponse();
		List<UserInformation> userInfoList = null;
		List<UserInfo> userList = null;
		try {
			request.setUserId(userId);
			request.setRoleId(roleId);
			response = (GetUserDetailsResponse) webServiceTemplate.marshalSendAndReceive(request);
			if(response != null && response.getUserInfos() != null){
				userInfoList = response.getUserInfos();
				userList = new ArrayList<UserInfo>();
				for(UserInformation data :userInfoList){
					UserInfo userInfo = new UserInfo();
					userInfo.setUserId(data.getUserId());
					userInfo.setPassword(data.getPassword());
					userInfo.setEmployeeId(data.getEmployeeId());
					userInfo.setUserFirstName(data.getUserFirstName());
					userInfo.setUserLastName(data.getUserLastName());
					userInfo.setRoleId(data.getRoleId());
					userInfo.setRoleName(data.getRoleName());
					userList.add(userInfo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userList;
	}
}
