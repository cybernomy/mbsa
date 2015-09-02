/*
 * ServerUtils.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.utils;

import java.io.File;
import java.net.URL;
import java.util.Locale;

import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.management.MalformedObjectNameException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.jboss.ejb3.BaseSessionContext;
import org.jboss.ejb3.ProxyFactoryHelper;
import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.system.server.ServerConfigLocator;

import com.mg.framework.api.Logger;
import com.mg.framework.api.Session;
import com.mg.framework.api.SystemAuditEvent;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.service.SecuritySystemLocator;
import com.mg.framework.service.SessionControlImpl;
import com.mg.framework.service.WorkingConnectionImpl;

/**
 * ��������� ������� ������ ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ServerUtils.java,v 1.29 2008/12/08 06:02:01 safonov Exp $
 *
 */
public class ServerUtils {
	private static Logger logger = getLogger(ServerUtils.class);
	
	/**
	 * �������� ������ �������
	 */
	public static String MBSA_DATASOURCE_NAME = "java:/MERPBackboneDS";
	/**
	 * ������������ �������������� �������� ������� � ����� ������� ����������
	 */
	public static String MBSA_CUSTOM_LOCATION = "";

	/**
	 * �������� �������� ���������� JNDI
	 * 
	 * @return	��������
	 * @throws NamingException	��� ������ ��������
	 */
	public static Context getContext() throws NamingException {
		return new InitialContext();
	}

	/**
	 * �������� JNDI ��� EJB �� ��������� ���������
	 * 
	 * @param sessionContext	��������
	 * @return	��� EJB
	 */
	public static String getJNDIName(SessionContext sessionContext) {
		return ProxyFactoryHelper.getLocalJndiName(((BaseSessionContext) sessionContext).getContainer());
	}
	
	/**
	 * �������� ������� ������
	 * 
	 * @return	������� ������
	 */
	public static com.mg.framework.api.Session getCurrentSession() {
		return SessionControlImpl.getSingleton().getCurrentSession();
	}

	/**
	 * �������� ������ �������� ������������ 
	 * 
	 * @return	������� ������
	 */
	public static Locale getUserLocale() {
		com.mg.framework.api.Session session = getCurrentSession();
		if (session == null)
			return Locale.getDefault();
		else
			return session.getWorkingConnection().getUserProfile().getLocale();
	}

	/**
	 * �������� ������� ������� ����������
	 * 
	 * @return	������� ���������� ��� <code>null</code> ���� ��� ������� ������
	 */
	public static com.mg.framework.api.WorkingConnection getWorkingConnection() {
		//check current session, maybe already initialized
		com.mg.framework.api.Session session = ServerUtils.getCurrentSession();
		return session == null ? null : session.getWorkingConnection();
	}

	/**
	 * �������� ������� �������� ������������
	 * 
	 * @return	������� �������� ������������ ��� <code>null</code> ���� ��� ������� ������
	 */
	public static com.mg.framework.api.UserProfile getUserProfile() {
		com.mg.framework.api.WorkingConnection wc = getWorkingConnection();
	    return wc == null ? null : wc.getUserProfile();
	}
	
	/**
	 * ������� ������� ����������
	 * 
	 * @return	������� ����������
	 */
	public static com.mg.framework.api.WorkingConnection createWorkingConnection(String sessionImpl) throws Exception {
		return new WorkingConnectionImpl(sessionImpl);
	}

	/**
	 * �������� ���������� � ���������� ������
	 * 
	 * @return	����������
	 */
	public static java.sql.Connection getConnection() {
		try {
			javax.sql.DataSource ds;

			ds = ContextUtils.lookup(MBSA_DATASOURCE_NAME, javax.sql.DataSource.class);

			return ds.getConnection();
		} catch (NamingException e) {
			throw new EJBException("Can't find DataSource " + e);
		} catch (Exception e) {
			throw new EJBException("Can't get connection " + e);
		}
	}

	/**
	 * �������� ���������� �������� ������������� ���������
	 * 
	 * @return
	 * 
	 * @deprecated
	 */
	@Deprecated
	public static PersistentManager getLegacyPersistentManager() {
		return (PersistentManager) com.mg.framework.service.PluginFactory.getPlugin(PersistentManager.class);
	}

	/**
	 * �������� �������� ������������� ���������
	 * 
	 * @return	�������� ������������� ���������
	 */
	public static PersistentManager getPersistentManager() {
		try {
			return ContextUtils.lookup(PersistentManager.JNDI_NAME, PersistentManager.class);
		} catch (NamingException e) {
			throw new RuntimeException("Unable to locate PersistentManager in JNDI under name [" + PersistentManager.JNDI_NAME + "]", e);
		}
	}

	/**
	 * �������� ��� ������ ������������ Logger
	 * 
	 * @return	��� ������ ������������ Logger
	 */
	public static String getLoggerClassName() {
		//TODO get logger plugin name from config file
		return com.mg.framework.service.jboss.JBossLoggerPlugin.class.getName();
	}

	/**
	 * �������� ������ Logger ��� ������
	 * 
	 * @param clazz	�����
	 * @return	������ Logger
	 */
	public static com.mg.framework.api.Logger getLogger(Class<?> clazz) {
        	return getLogger(clazz.getName());
	}

	/**
	 * �������� ������ Logger �� ����� ������
	 * 
	 * @param className	��� ������
	 * @return	������ Logger
	 */
	public static com.mg.framework.api.Logger getLogger(String className) {
        return com.mg.framework.api.Logger.getLogger(className);
	}

	/**
	 * �������� ������ ������� ������������
	 * 
	 * @return	������ ������� ������������
	 */
	public static com.mg.framework.api.security.SecuritySystem getSecuritySystem() {
		return SecuritySystemLocator.locate();
	}

	/**
	 * �������� �������� ����������
	 * 
	 * @return	�������� ����������
	 */
	public static TransactionManager getTransactionManager() {
        try {
            return ContextUtils.lookup("java:/TransactionManager", TransactionManager.class);
        }
        catch (Exception e) {
        	throw new RuntimeException("java:/TransactionManager lookup failed", e);
        }
	}
	
	/**
	 * �������� ������� ����������
	 * 
	 * @return	������� ����������
	 */
	public static Transaction getCurrentTransaction() {
        TransactionManager transactionManager;
        try {
            transactionManager = ContextUtils.lookup("java:/TransactionManager", TransactionManager.class);
        }
        catch (Exception e) {
        	throw new RuntimeException("java:/TransactionManager lookup failed", e);
        }
        
        try
        {
            return transactionManager.getTransaction();
        }
        catch (SystemException e)
        {
            throw new RuntimeException("Failed to obtain current transaction: " + e.getMessage(),e);
        }
	}

	/**
	 * ������� MBean �������� (proxy)
	 * 
	 * @param intf	��������� ��� �������� ����� ����������� ��������
	 * @param name	������ ������������ ��� �������� ��� MBean �������
	 * @return	MBean ��������
	 * @throws MalformedObjectNameException	��� �������� ����� �������
	 */
	public static Object createMBeanProxy(final Class<?> intf, final String name) throws MalformedObjectNameException {
	    return MBeanProxyExt.create(intf, name);
	}
    
	/**
	 * ��������� ����� �� �����
	 * 
	 * @param className	��� ������
	 * @return	�����
	 * @throws ClassNotFoundException	���� ����� �� ������
	 */
    public static Class<?> loadClass(String className) throws ClassNotFoundException {
        return Thread.currentThread().getContextClassLoader().loadClass(className);
    }
    
    /**
     * ��������� ������ �� �����
     * 
     * @param name	��� �������
     * @return	������
     */
    public static URL loadResource(String name) {
    	return Thread.currentThread().getContextClassLoader().getResource(name);
    }
    
    /**
     * �������� ��������� ����� ������� ����������
     * 
     * @return	��������� �����
     */
    public static File getServerTempDir() {
    	//TODO remove JBoss specific
    	return ServerConfigLocator.locate().getServerTempDir();
    }

    /**
     * �������� ����� ����� ������� ����������
     * 
     * @return	����� �����
     */
    public static File getServerLogDir() {
    	//TODO remove JBoss specific
    	return ServerConfigLocator.locate().getServerLogDir();
    }

    /**
     * �������� ������� ���������� ���, ��� �������� ������ ����� ������ ����������
     *
     */
    public static void setTransactionRollbackOnly() {
    	try {
    		ServerUtils.getTransactionManager().setRollbackOnly();
    	} catch (Exception e) {
    		logger.warn("Failed to set rollback only; ignoring", e);
    	}
    }

    /**
     * ���������� ������� ������� ����������
     * 
     * @param seconds	�������� �������� � ��������, ���� �������� ��������� <code>0</code>, �� ��������
     *        �������� ����� �������� � �������� �� ���������
     */
    public static void setTransactionTimeout(int seconds) {
    	try {
    		ServerUtils.getTransactionManager().setTransactionTimeout(seconds);
    	} catch (Exception e) {
    		logger.warn("Failed to set transaction timeout; ignoring", e);
    	}
    }

    /**
     * �������� ������������� �������� ��������
     * 
     * @return	�������������
     */
    public static int getSystemTenantId() {
    	//TODO ����������� �������� �������������� � ������, ����� �� ����������� ��������� � ���������, �.�. ������� ������� ��� ��������� ��� ����������
    	return 1;
    	/*Session session = ServerUtils.getCurrentSession();
    	if (session != null)
    		return (Integer) ((PersistentObject) session.getSystemTenant()).getPrimaryKey();
    	else
    		return 1;*/
    }
    
    /**
     * ������� ������� ���������� ������
     * 
     * @param event	�������
     */
    public static void addSystemAuditEvent(SystemAuditEvent event) {
    	try {
			JmsUtils.sendObjectMessageToTopic("topic/com/mg/jet/systemaudit", event);
		} catch (Exception e) {
			logger.error("system audit failed", e);
		}
    }
    
    /**
     * ������� ������� ���������� ������, � �������� ����������� ����� ����������� �������
     * ������������
     * 
     * @param beanName	������ ��������
     * @param operation	��������
     * @param details	�������� �������
     */
    public static void addSystemAuditEvent(String beanName, String operation, String details) {
    	Session session = ServerUtils.getCurrentSession();
    	String userName = "system";
    	if (session != null)
    		userName = session.getWorkingConnection().getUserProfile().getUserName();
    	addSystemAuditEvent(new SystemAuditEvent(userName, beanName, operation, details));
    }

}
