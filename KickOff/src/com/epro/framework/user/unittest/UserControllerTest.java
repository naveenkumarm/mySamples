package com.epro.framework.user.unittest;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.epro.framework.model.Roles;
import com.epro.framework.model.Users;
import com.epro.framework.role.service.RoleService;
import com.epro.framework.user.model.UserListInfo;
import com.epro.framework.user.service.UserService;

/*
 * UserControllerTest.java
 * Class description goes here.
 *
 * @version 1.0 
 * @author Pradeepkumar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class UserControllerTest {

	private Logger log = Logger.getLogger(UserControllerTest.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	private int roleLevelId;
	private int roleId;
	private int userId;
	private int tenantId;
	private int subtenantId;
	private int startingRecord;
	private int recordToShow;
	private String searchRoleByName;

	private boolean updateMode;

	@Before
	// Informs JUnit that this method should be run before each test
	public void setUp() {
		this.roleLevelId = 1;
		this.roleId = 23; // AdminRole
		this.userId = 117; // sensiple
		this.tenantId = 1;
		this.subtenantId = 0;
		this.startingRecord = 0;
		this.recordToShow = 10;
		this.searchRoleByName = "";
        this.updateMode = true;
	}

	@Test
	public void testCreateUser() {
		Users user = new Users();
		
		if(this.updateMode){
			user.setUserId(this.userId);
		}
		 
		user.setEmailId("apradeepkumar@gmail.com");
		 
		user.setExtn1("1234");
		user.setExtn2("1234");
	 
		user.setFirstName("UnitTestFirstName");
		user.setLastName("UnitTestLastName");
		 
		user.setIsDeleted(false);
		user.setPassword("sensiple1");
 	 
		Roles roles = roleService.getRoleByRoleId(this.roleId);

		user.setRoles(roles);
		user.setMobileNumber1("1234512341");
		user.setMobileNumber2("123456789098765");
		 
		user.setUserName("sensiple");
		user.setWorkingPhone1("8456745674");
		user.setWorkingPhone2("7356745675");
		 

		int userId = userService.saveUser(user);

		Assert.isTrue(userId > 0);
		
		if(!this.updateMode){
			log.debug("New User Name : " + user.getUserName() + "Created ID : " + userId);
		}else{
			log.debug("Updated User Name : " + user.getUserName());
		}
	}

	@Test
	public void testUserById() {
		Users users = userService.getUserByUserId(this.userId);
		Assert.isTrue(users.getUserId() > 0);
		log.debug("The UserName :: " + users.getUserName());

	}


	@Test
	public void testSameUserNameExist() {
		Users users = userService.getUserByUserId(this.userId);
		if (userService.isUserNameExist(users)) {
			log.debug("The UserName Already Exists");
		} else {
			log.debug("The new username");
		}
	}

	@Test
	public void testSameEmailIdExist() {
		Users users = userService.getUserByUserId(this.userId);
		if (userService.isEmailExist(users)) {
			log.debug("EmailId Already Exists");
		} else {
			log.debug("New Email Id");
		}
	}

	@Test
	public void testUsersDataTable() {
		List<UserListInfo> numberOfRocords = userService.getUsersDataTable(this.startingRecord, this.recordToShow, this.searchRoleByName); // 2=rolelevelId,136=tenantId,0=subtenantId
		log.debug("Number Of Records found in DataTable :" + numberOfRocords.size());
	}

	@Test
	public void testDeleteUser() {
		Users users = userService.getUserByUserId(this.userId); 
		String userName = users.getUserName();
		boolean deleteSucess = userService.deleteUser(users);
		if (deleteSucess) {
			log.debug("The "+userName+" User deleted successfully");
		} else {
			log.debug("The "+userName+" User not deleted");
		}
	}
}
