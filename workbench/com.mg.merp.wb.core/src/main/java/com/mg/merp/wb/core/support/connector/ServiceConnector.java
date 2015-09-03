/* ServiceConnector.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.wb.core.support.connector;

import java.rmi.NoSuchObjectException;
import java.util.*;

import javax.management.*;
import javax.naming.*;

import org.jnp.interfaces.NamingContext;

import com.mg.merp.wb.core.CorePlugin;
import com.mg.merp.wb.core.support.CoreUtils;

/**
 * Сервис подключения студии разработки к серверу приложений
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: ServiceConnector.java,v 1.7 2008/08/15 13:47:50 safonov Exp $ 
 */
public class ServiceConnector {
    private Object server;
    private static ServiceConnector instance;
    private static boolean isUpdated;
    
    public static final String SERVER_TYPE="servertype";
    public static final String PRVD_HOST="providerHost";
    public static final String PRVD_PORT="providerPort";
    
    public static final int AS_JBOSS_4X = 0;
    public static final int AS_WEBSPHERE_5X = 1;
        
    private ServiceConnector (){
    	isUpdated = true;
    }
    
    /**
     * Возвращает сервис подключения к серверу приложений
     * 
     * @return
     * 			сервис подключения
     * @throws Exception
     */
    public static ServiceConnector getServiceConnector() throws Exception {
    	if (instance == null){
    		instance = new ServiceConnector();
    	}
    	if (isUpdated || instance.server == null)
    		instance.init();
    	return instance;
    }
    
    private void init() throws Exception {
		isUpdated = false;
    	
    	String host = CoreUtils.loadString(CorePlugin.getDefault(), PRVD_HOST);
    	Integer port = CoreUtils.loadInt(CorePlugin.getDefault(), PRVD_PORT);
    	
    	if ("".equals(host) || port == 0)
    		throw new IllegalStateException("host or port is empty");
    	
        Properties p = new Properties();
        StringBuilder url = new StringBuilder("jnp://").append(host).append(":").append(port);
        p.setProperty(NamingContext.PROVIDER_URL, url.toString());
        switch (CoreUtils.loadInt(CorePlugin.getDefault(), SERVER_TYPE)) {
		case AS_JBOSS_4X:
	        p.setProperty(NamingContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
	        InitialContext initialContext = new InitialContext(p);
	        try {
	        	server = initialContext.lookup("jmx/invoker/RMIAdaptor"); //$NON-NLS-1$
	        } catch (javax.naming.CommunicationException e) {
	        	//пытались обратиться через созданный объект к рестартованному серверу
	        	if (e.getCause() instanceof NoSuchObjectException)
	        		server = initialContext.lookup("jmx/invoker/RMIAdaptor"); //$NON-NLS-1$
	        	else
	        		throw e;
	        }
			break;
		default:
			server = null;
			break;
		}	
    }
    
    /**
     * Вызов удалённого метода на сервере приложений
     * 
     * @param name
     * 			имя сервиса, у которого вызывается метод
     * @param method
     * 			имя вызываемого метода
     * @param args
     * 			значения аргументов, передаваемых в метод
     * @param sig
     * 			сигнатура метода
     * 
     * @return
     * 			результат вызова операции
     * @throws Exception
     */
    public Object invoke(ObjectName name, String method, Object[] args, String[] sig) throws Exception {
    	int serverType = CoreUtils.loadInt(CorePlugin.getDefault(), SERVER_TYPE);
		try {
			return internalInvoke(serverType, name, method, args, sig);
		} catch (NoSuchObjectException e) {
			//пытались обратиться через созданный объект к рестартованному серверу 
			init();
			return internalInvoke(serverType, name, method, args, sig);
		}
    }
    
    private Object internalInvoke(int serverType, ObjectName name, String method, Object[] args, String[] sig) throws Exception {
    	switch (serverType) {
    	case AS_JBOSS_4X:
    		try {
    			return ((MBeanServerConnection) server).invoke(name, method, args, sig);
    		} catch (RuntimeMBeanException ex) {
    			throw ex.getTargetException();
    		}
    	default:
    		throw new IllegalStateException(); 
    	}    	
    }
    
	public static void updateConfig() {
		isUpdated = true;
	}
}
