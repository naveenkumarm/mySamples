/**
* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.util;

import org.springframework.context.ApplicationContext;

/*
 * AppContext.java
 * Class description goes here.
 *
 * @version 1.0 May 14, 2012
 * @author suresh
 */

public class AppContext {  
	  
    private static ApplicationContext ctx;  
  
    /** 
     * Injected from the class "ApplicationContextProvider" which is automatically 
     * loaded during Spring-Initialization. 
     */  
    public static void setApplicationContext(ApplicationContext applicationContext) {  
        ctx = applicationContext;  
    }  
  
    /** 
     * Get access to the Spring ApplicationContext from everywhere in your Application. 
     * 
     * @return 
     */  
    public static ApplicationContext getApplicationContext() {  
        return ctx;  
    }  
}