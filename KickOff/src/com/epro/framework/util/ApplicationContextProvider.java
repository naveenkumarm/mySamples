/**
* All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
*/

package com.epro.framework.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
 

/*
 * ApplicationContextProvider.java
 * Class description goes here.
 *
 * @version 1.0 May 14, 2012
 * @author suresh
 */

public class ApplicationContextProvider implements ApplicationContextAware {  
   
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {  
        // Wiring the ApplicationContext into a static method  
        AppContext.setApplicationContext(ctx);  
    }  
}