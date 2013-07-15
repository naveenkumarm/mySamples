package com.epro.service;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.epro.adapter.AuthenticationAdapter;
import com.epro.beans.Role;
import com.epro.beans.UserInfo;


/**
 * A custom authentication manager that allows access if the user details exist
 * in the database and if the username and password are not the same. Otherwise,
 * throw a {@link BadCredentialsException}
 */
public class CustumUserDetailService implements AuthenticationManager {

	protected static Logger log = Logger.getLogger(CustumUserDetailService.class);

	@Autowired
	private AuthenticationAdapter authenticationAdapter;

	// We need an Md5 encoder since our passwords in the database are Md5
	// encoded.
	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	/**
	 * Method which is used for Authentication.
	 */
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		Authentication authentication = null;
		log.debug("Performing custom authentication");
		try {
			log.info("authenticate:" + System.currentTimeMillis());

			log.info("inside auth manager.." + auth.getName());

			// Retrieve user details from database
			UserInfo userInfo = authenticationAdapter.loginRequest(auth.getName(), "" + auth.getCredentials());

			if (userInfo != null) {
				log.info("Authentication Success");
				authentication = new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), getAuthorities(userInfo.getUserRoles()));

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
	public Collection<GrantedAuthority> getAuthorities(List<Role> userRolesList) {
		// Create a list of grants for this user
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if(userRolesList != null && userRolesList.size() > 0){
			for(Role userRoles:userRolesList){
				authList.add(new GrantedAuthorityImpl(userRoles.getRoleName()));
			}
		}
		else{
			authList.add(new GrantedAuthorityImpl("ROLE_ALL"));
		}
		// Return list of granted authorities
		return authList;
	}

}
