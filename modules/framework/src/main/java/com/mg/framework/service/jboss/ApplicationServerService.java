/*
 * ApplicationServerService.java
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
package com.mg.framework.service.jboss;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

import javax.naming.InitialContext;

import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.system.ServiceMBeanSupport;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.ApplicationServer;
import com.mg.framework.api.UserSessionInfo;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.service.ApplicationServerImpl;
import com.mg.framework.utils.ContextUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;

/**
 * JMX реализация сервиса управления прикладными функциями сервера приложения
 * 
 * @author Oleg V. Safonov
 * @version $Id: ApplicationServerService.java,v 1.6 2008/12/08 06:08:15 safonov Exp $
 */
public class ApplicationServerService extends ServiceMBeanSupport implements
		ApplicationServerServiceMBean {
	private Element databaseConfig = null;
	private String databaseName = "";
	private String persistentManagerImpl = null;
	private ApplicationServer delegate;

	private Properties getDatabaseProperties() {
		Properties props = new Properties();
		if (databaseConfig == null)
		{
			log.warn("No configuration specified; using empty properties map");
			return props;
		}

		NodeList list = databaseConfig.getElementsByTagName("property");
		int len = list.getLength();

		for (int i = 0; i < len; i++)
		{
			Node node = list.item(i);

			switch (node.getNodeType())
			{
			case Node.ELEMENT_NODE:
				Element child = (Element) node;
				String name, value;

				// get the name
				if (child.hasAttribute("name"))
				{
					name = child.getAttribute("name");
				}
				else
				{
					log.warn("Ignoring invalid element; missing 'name' attribute: " + child);
					break;
				}

				// get the value
				if (child.hasAttribute("value"))
				{
					value = child.getAttribute("value");
				}
				else
				{
					log.warn("Ignoring invalid element; missing 'value' attribute: " + child);
					break;
				}

				if (log.isDebugEnabled())
				{
					log.debug("setting property " + name + "=" + value);
				}
				props.setProperty(name, value);
				break;

			case Node.COMMENT_NODE:
				// ignore
				break;

			default:
				log.debug("ignoring unsupported node type: " + node);
				break;
			}
		}

		return props;
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#createService()
	 */
	@Override
	protected void createService() throws Exception {
		super.createService();
		this.delegate = new ApplicationServerImpl();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#destroyService()
	 */
	@Override
	protected void destroyService() throws Exception {
		this.delegate = null;
		super.destroyService();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#startService()
	 */
	@Override
	protected void startService() throws Exception {
		super.startService();
		if (persistentManagerImpl != null) {
			Object pm = ServerUtils.loadClass(persistentManagerImpl).newInstance();
			ContextUtils.bind(new InitialContext(), PersistentManager.JNDI_NAME, pm);
		}
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#stopService()
	 */
	@Override
	protected void stopService() throws Exception {
		ContextUtils.unbind(new InitialContext(), PersistentManager.JNDI_NAME);
		super.stopService();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.service.jboss.ApplicationServerServiceMBean#convertDatabase()
	 */
	public void convertDatabase() throws Exception {
		Object converter = null;
		try {
			//проверка наличия класса конвертации системы
			Class<?> converterClass = ServerUtils.loadClass("com.mg.framework.service.DatabaseConverter");
			Method getInstanceMethod = converterClass.getMethod("getInstance", String.class);
			converter = getInstanceMethod.invoke(null, databaseName);
		} catch (InvocationTargetException e) {
			log.error("Create database converter failed", e.getTargetException());
			throw new Exception(String.format("Create database converter failed with message: %s", e.getTargetException().toString()));
		} catch (ClassNotFoundException e) {
			throw new Exception(String.format("Commercial license wanted"));
		}
		try {
			Method convertMethod = converter.getClass().getMethod("convert", Properties.class);
			convertMethod.invoke(converter, getDatabaseProperties());
		} catch (InvocationTargetException e) {
			Throwable targetException = e.getTargetException();
			if (targetException instanceof ApplicationException) {
				log.error("Convert database failed", targetException);
				throw new Exception(String.format("Convert database failed with message: %s.", targetException.getMessage()));				
			} else {
				log.error("Convert database failed", targetException);
				throw new Exception(String.format("Convert database failed with message: %s. The full information is available in the server.log.", targetException.toString()));				
			}
		}
	}

	private String getConnectionPoolName(String connectionPoolName) {
		//если не указан, то работаем с главным соединением системы
		return StringUtils.stringNullOrEmpty(connectionPoolName) ? "MERPBackboneDS" : connectionPoolName;
	}
	
	private org.jboss.system.ServiceMBean getManagedConnectionPoolService(String connectionPoolName) throws Exception {
		return (org.jboss.system.ServiceMBean) MBeanProxyExt.create(org.jboss.system.ServiceMBean.class, "jboss.jca:service=ManagedConnectionPool,name=" + connectionPoolName);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.service.jboss.ApplicationServerServiceMBean#startDatabasePool()
	 */
	public void startDatabasePool(String connectionPoolName) throws Exception {
		String poolName = getConnectionPoolName(connectionPoolName);
		log.info("Start connection pool: " + poolName);
		try {
			getManagedConnectionPoolService(poolName).start();
			log.info("Connection pool started successfully: " + poolName);
		} catch (ApplicationException e) {
			log.error("Start connection pool failed: " + poolName, e);
			throw new Exception(String.format("Start connection pool %s failed with message: %s.", poolName, e.getMessage()));
		} catch (Exception e) {
			log.error("Start connection pool failed: " + poolName, e);
			throw new Exception(String.format("Start connection pool %s failed with message: %s. The full information is available in the server.log.", poolName, e.toString()));
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.service.jboss.ApplicationServerServiceMBean#stopDatabasePool()
	 */
	public void stopDatabasePool(String connectionPoolName) throws Exception {
		String poolName = getConnectionPoolName(connectionPoolName);
		log.info("Stop connection pool: " + poolName);
		try {
			getManagedConnectionPoolService(poolName).stop();
			log.info("Connection pool stoped successfully: " + poolName);
		} catch (ApplicationException e) {
			log.error("Stop connection pool failed: " + poolName, e);
			throw new Exception(String.format("Stop connection pool %s failed with message: %s.", poolName, e.getMessage()));
		} catch (Exception e) {
			log.error("Stop connection pool failed: " + poolName, e);
			throw new Exception(String.format("Stop connection pool %s failed with message: %s. The full information is available in the server.log.", poolName, e.toString()));
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.service.jboss.ApplicationServerServiceMBean#getConfiguration()
	 */
	public Element getDatabaseConfiguration() {
		return databaseConfig;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.service.jboss.ApplicationServerServiceMBean#setConfiguration(org.w3c.dom.Element)
	 */
	public void setDatabaseConfiguration(Element element) {
		databaseConfig = element;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.service.jboss.ApplicationServerServiceMBean#getDatabaseName()
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.service.jboss.ApplicationServerServiceMBean#setDatabaseName(java.lang.String)
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.service.jboss.ApplicationServerServiceMBean#getPersistentManagerImpl()
	 */
	public String getPersistentManagerImpl() {
		return persistentManagerImpl;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.service.jboss.ApplicationServerServiceMBean#setPersistentManagerImpl(java.lang.String)
	 */
	public void setPersistentManagerImpl(String implName) {
		persistentManagerImpl = implName;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#getUserSessionInfos()
	 */
	public Set<UserSessionInfo> loadUserSessionInfos() throws Exception {
		return delegate.loadUserSessionInfos();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#loadUserSessionInfo(java.lang.String)
	 */
	public UserSessionInfo loadUserSessionInfo(String httpSessionId)
			throws Exception {
		return delegate.loadUserSessionInfo(httpSessionId);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#sendAdminMessage(java.lang.String, java.lang.String)
	 */
	public void sendAdminMessage(String sessionIds, String message)
			throws Exception {
		delegate.sendAdminMessage(sessionIds, message);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#sendAdminMessage(java.lang.String[], java.lang.String)
	 */
	public void sendAdminMessage(String[] sessionIds, String message)
			throws Exception {
		delegate.sendAdminMessage(sessionIds, message);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ApplicationServer#invalidateUserSessions(java.lang.String[])
	 */
	public void invalidateUserSessions(String[] sessionIds) throws Exception {
		delegate.invalidateUserSessions(sessionIds);
	}

}
