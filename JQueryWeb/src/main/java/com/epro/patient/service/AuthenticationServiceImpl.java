package com.epro.patient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epro.patient.beans.User;
import com.epro.patient.utils.UsersMapper;

@Service("AuthenticationService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired  
	 private JdbcTemplate jdbcTemplateObject;  
	
	/** 
	  * @param jdbcTemplateObject the jdbcTemplateObject to set 
	  */  
	 public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {  
	  this.jdbcTemplateObject = jdbcTemplateObject;  
	 }  
	 
	 
	@Override
	public User authenticate(User user) {
		String SQL = "SELECT * FROM jQuery_Login WHERE UserName = ? and Password = ?";  
		 List <User> userList = jdbcTemplateObject.query(SQL, new Object[]{user.getUserName(),user.getPassword()}, new UsersMapper());  
		 if(userList.size() > 0){
			 return userList.get(0); 
		 }   
		 return null;  
	}

}
