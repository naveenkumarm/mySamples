/**
showVoiceCallReport* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.security;

/*
 * UrlConstants.java
 * Class description goes here.
 *
 * @version 1.0 Oct 20, 2012
 * @author suresh
 */

public class UrlConstants {

	// CLIENTS
	public static final String CLIENT_SHOW_CLIENT_LIST = "/clients/showClients.do";
	public static final String CLIENT_CREATE_CLIENT = "/clients/createClient.do";
	public static final String CLIENT_SAVE_CLIENT = "/clients/saveClient.do";
	public static final String CLIENT_DELETE_CLIENT = "/clients/deleteClient.do";
	public static final String CLIENT_EDIT_CLIENT = "/clients/editClient.do";
	public static final String CLIENT_GET_CLIENT_DATATABLE = "/clients/clientDataTable.do";
	public static final String CLIENT_GET_CLIENT_DETAILS = "/clients/getClientDetails.do";
	public static final String CLIENT_IMAGE_UPLOAD = "/clients/uploadClientLogo.do";
	

	// ROLES URL'S
	public static final String ROLE_GETROLE_LIST = "/role/getRole.do";
	public static final String ROLE_CREATE_ROLE = "/role/createRole.do";
	public static final String ROLE_SAVE_ROLE = "/role/saveRole.do";
	public static final String ROLE_UPDATE_ROLE = "/role/updateRole.do";
	public static final String ROLE_DELETE_ROLE = "/role/deleteRole.do";
	public static final String ROLE_EDITROLEPAGE_ROLE = "/role/editRolePage.do";
	public static final String ROLE_GETROLE_LIST_INFO = "/role/roleListInfo.do";

	// USER MANAGEMENT
	public static final String USER_GET_USERS_LIST = "/user/getUser.do";
	public static final String USER_CREATE_USER = "/user/createUser.do";
	public static final String USER_SAVE_NEW_USER = "/user/saveNewUser.do";
	public static final String USER_GET_USERINFO = "/user/getUserInfo.do";
	public static final String USER_EDIT_USER_PAGE = "/user/editUserPage.do";
	public static final String USER_DELETE_USER = "/user/deleteUserInfo.do";
	public static final String USER_GET_ROLEINFO = "/user/getRoleInfo.do";
	public static final String USER_GET_USERS_LIST_INFO = "/user/userListInfo.do";

	// COMMON
	public static final String VALIDATE_LOGIN_URL = "/login/validateUser.do";
	public static final String VALIDATE_LOGIN_ERROR_URL = "/login/validateUser.do?error=true";
	public static final String FORGOT_PASSWORD_URL = "/login/forgotPassword.do";
	public static final String LOGOUT_URL = "/login/logout.do";
	public static final String SESSION_EXPIRED_URL = "/login/sessionExpired.do";
	public static final String MY_ACCOUNT_URL = "/login/myAccount.do";

}
