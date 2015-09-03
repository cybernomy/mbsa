/*
 * SecuritySystemService.java
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
package com.mg.merp.security.support.jboss;

import java.security.AccessControlException;
import java.security.Permission;
import java.util.Locale;

import javax.security.auth.login.LoginException;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.UserProfile;
import com.mg.framework.api.security.SecurityAuditType;
import com.mg.framework.api.security.InvalidUserNameOrPassword;
import com.mg.merp.security.support.SecuritySystemImpl;

/**
 * Реализация поддержки MBean системы безопасности
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class SecuritySystemService extends ServiceMBeanSupport implements
		SecuritySystemServiceMBean {
	private SecuritySystemImpl delegate = new SecuritySystemImpl();

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#checkPermission(java.security.Permission)
	 */
	public void checkPermission(Permission perm) throws AccessControlException {
		delegate.checkPermission(perm);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#authenticate(java.lang.String, java.lang.String)
	 */
	public void authenticate(String login, String password) throws LoginException {
		delegate.authenticate(login, password);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#logout()
	 */
	public void logout() {
		delegate.logout();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#loadUserProfile()
	 */
	public UserProfile loadUserProfile(String login, Locale locale, Locale defaultLocale) {
		return delegate.loadUserProfile(login, locale, defaultLocale);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#login(java.lang.String, java.lang.String, boolean)
	 */
	public Integer login(String name, String password, boolean smartCard) throws ApplicationException, InvalidUserNameOrPassword {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#getModuleAccess(java.lang.Integer)
	 */
	public String[] getModuleAccess(Integer userId) throws ApplicationException {
		return delegate.getModuleAccess(userId);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#setFolderPermission(int, int, int)
	 */
	public void grantTreePermission(int treeIdentifier, int parentIdentifier, int treePart) {
		delegate.grantTreePermission(treeIdentifier, parentIdentifier, treePart);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#showTreePermission(int, int)
	 */
	public void setupTreePermission(int treeIdentifier, int treePart, String className, String parentProperty) {
		delegate.setupTreePermission(treeIdentifier, treePart, className, parentProperty);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#createUser(java.lang.String, java.lang.String)
	 */
	public void createUser(String userName, String passw) {
		delegate.createUser(userName, passw);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#changePassword(java.lang.String, java.lang.String)
	 */
	public void changePassword(String userName, String passw) {
		delegate.changePassword(userName, passw);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#deleteUser(java.lang.String)
	 */
	public void deleteUser(String userName) {
		delegate.deleteUser(userName);
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.Service#startService()
	 */
	public void startService() throws Exception {
		delegate.start();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.Service#stopService()
	 */
	public void stopService() {
		delegate.stop();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.security.support.jboss.SecuritySystemServiceMBean#refreshPermissions()
	 */
	public void refreshPermissions() {
		delegate.refreshPermissions();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.security.SecuritySystem#addAuditEvent(com.mg.framework.api.security.SecurityAuditType, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addAuditEvent(SecurityAuditType auditType, String beanName,
			String userName, String details) {
		delegate.addAuditEvent(auditType, beanName, userName, details);
	}

}
