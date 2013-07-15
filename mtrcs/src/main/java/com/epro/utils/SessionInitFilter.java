package com.epro.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Filter implementation which simply checks for the existance of a flag in the HttpSession
 * to determine if the Session Init process has completed yet for the users session.  If it has, request
 * is passed on untouched, if it hasn't, the user is redirected back to sessionInit url.
 */
public class SessionInitFilter implements Filter {

	
	private static final String DEFAULT_SESSION_INIT_URL = "/ems-web/ems/login/view";
	private final Logger log = Logger.getLogger(this.getClass());
	
	//private String targetUrlKey = DEFAULT_TARGET_URL_KEY;
	private String sessionInitUrl = DEFAULT_SESSION_INIT_URL;
	//TODO robustify ignoreURL/Pattern logic
	private String ignoreURL = "login";
	
	
	public String getSessionInitUrl() {
		return sessionInitUrl;
	}

	public void setSessionInitUrl(String sessionInitUrl) {
		this.sessionInitUrl = sessionInitUrl;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hReq = (HttpServletRequest) request;
		HttpServletResponse hResp = (HttpServletResponse) response;
		
		String url = hReq.getRequestURL().toString();
		if (shouldIgnoreRequest(url)) {
			chain.doFilter(request, hResp);
			return;
		}
		

		String queryString = hReq.getQueryString();
		if (queryString != null) {
			url += "?" + queryString;
		}
		log.trace("Hit SessionInitFilter with for URL: '" + url + "'");
		
		// Add NoCache headers for all non static resource requests
		addNoCacheHeaders(hReq, hResp);
		
		HttpSession session = hReq.getSession();
		
		boolean isSessionInitComplete = (session.getAttribute("sessionInit") != null);
		
		if (!isSessionInitComplete) {
			log.warn("Request recieved for a session that has not been initialized yet. Redirecting to session init URL (" + this.sessionInitUrl + ").");
			//session.setAttribute(targetUrlKey, url);
			hResp.sendRedirect(sessionInitUrl);
			return;
		}
		
		chain.doFilter(request, hResp);
	}
	
	/**
	 * Convenience method used to determine if a given URL should be ignored
	 * @param url
	 * @return true if the url matches any of the "ignored" patterns
	 */
	protected boolean shouldIgnoreRequest(String url) {
		// TODO robustify ignore url logic.
		
		return  url.contains(ignoreURL) ||
				url.endsWith(".js") || 
				url.endsWith(".css") || 
				url.endsWith(".png") || 
				url.endsWith(".gif") || 
				url.endsWith(".jpg") || 
				url.endsWith(".jpeg")|| 
				url.contains("/images") || 
				url.contains("/bundles") || 
				url.contains("/themes"); 
	}

	/**
	 * Adds response headers to disable browser caching
	 * @param request
	 * @param response
	 */
	private void addNoCacheHeaders(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Setting headers to disable browser cache.");
		response.addHeader("pragma", "no-store, no-cache"); //HTTP 1.0
		response.addHeader("cache-control", "no-cache, no-store, must-revalidate, max-age=-1"); //HTTP 1.1
		response.setDateHeader("expires", 0); //Prevents caching at the proxy server
		log.debug("Setting headers to disable browser cache - completed.");
	}
	
	// Filter lifecycle call back not used...just declared to fulfill Filter contract
	public void destroy() { }
	public void init(FilterConfig fConfig) throws ServletException {}

}
