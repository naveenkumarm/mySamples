/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.login.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.epro.framework.login.service.LoginService;
import com.epro.framework.model.Users;

/**
 * A custom authentication manager that allows access if the user details exist
 * in the database and if the username and password are not the same. Otherwise,
 * throw a {@link BadCredentialsException}
 */
public class CustomAuthenticationManager implements AuthenticationManager {

	protected static Logger log = Logger.getLogger("service");

	@Autowired
	private LoginService loginService;

 	
	/* Method which is used for Authentication.
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		Authentication authentication = null;
		log.debug("Performing custom authentication");
		try { 
 
			// Retrieve user details from database
			Users snsUser = loginService.searchUser(auth.getName(), "" + auth.getCredentials());

			if (snsUser != null) {
				log.info("Authentication Success");
				authentication = new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), getAuthorities(snsUser));
 			} 
			else {
				throw new BadCredentialsException("Invalid Username and Password");
			}

		}
		catch (Exception e) {
			log.error("Invalid Username and Password");
			throw new BadCredentialsException("Invalid Username and Password");
		}

		return authentication;
	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where
	 * access level is an Integer. Basically, this interprets the access value
	 * whether it's for a regular user or admin.
	 * 
	 * @param userRolesList
	 *            an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	public Collection<GrantedAuthority> getAuthorities(Users user) {
		// Create a list of grants for this user
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new GrantedAuthorityImpl(user.getRoles().getRoleName()));
		
		// Return list of granted authorities
		return authList;
	}

}
