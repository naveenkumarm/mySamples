/**
 * 
 */
package com.epro.patient.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.epro.patient.beans.User;


/**
 * 
 *
 */

public class UsersMapper implements RowMapper {    
	 public User mapRow(ResultSet rs, int rowNum) throws SQLException {    
	  User user = new User();    
	  user.setUserId(rs.getLong("UserId"));    
	  user.setUserName(rs.getString("UserName"));
	  user.setPassword(rs.getString("PassWord"));
	  
	  return user;    
	 }    
	}   