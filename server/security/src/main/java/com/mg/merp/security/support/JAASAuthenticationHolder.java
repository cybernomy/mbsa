/*
 * JAASAuthenticationHolder.java
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
package com.mg.merp.security.support;

import java.io.Serializable;

import javax.security.auth.Subject;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;

import net.sf.jguard.ext.authentication.AuthenticationUtils;

import com.mg.framework.api.Logger;
import com.mg.framework.api.Session;
import com.mg.framework.support.ui.ContainerContextFactory;
import com.mg.framework.utils.ServerUtils;

/**
 * ������ �������������� �������������� ����� JAAS
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class JAASAuthenticationHolder implements Serializable {
	/**
	 * ������������ ������������ ��������������
	 */
	public static final String MBSA_SYSTEM = "MBSASystem";

	/**
	 * ���� �������� ��������� ������ ��������������
	 */
	private static final String AUTH_HOLDER = "MG_AUTH_HOLDER";

	/**
	 * ���� �������� ��� ��������� � http ������, �������� ������������
	 * ����������� � MessAdmin
	 */
	private static final String USER_NAME = "UserName";

	/**
	 * logger
	 */
	private static final Logger logger = ServerUtils.getLogger(JAASAuthenticationHolder.class);

	private AuthenticationUtils authenticationUtils = null;
	
	private JAASAuthenticationHolder(Configuration conf) {
		authenticationUtils = new AuthenticationUtils(conf);
	}
	
	/**
	 * ���������� ��������������
	 * 
	 * @param login
	 * @param password
	 * @throws LoginException
	 */
	private void useLoginContext(String login, String password)
			throws LoginException {
		DefaultCallbackHandler cbh = new DefaultCallbackHandler(login, password);

		authenticationUtils.login(MBSA_SYSTEM, cbh);
	}

	/**
	 * �������� ������� ������ ��������������
	 * 
	 * @param conf	������������
	 * @return ������ ��������������
	 */
	public static JAASAuthenticationHolder getAuthenticationHolder(Configuration conf) {
		Session session = ServerUtils.getCurrentSession();
		if (session == null) {
			logger.debug("Current uses session is null");
			return null;			
		}
		
		JAASAuthenticationHolder auth = (JAASAuthenticationHolder) session.getAttribute(AUTH_HOLDER);

		// auth.getSubject() can return null if the user in session has been
		// deleted by authenticationManager
		// so we should use
		if (auth != null && auth.getSubject() == null) {
			logger.debug(" subject into JAASAuthenticationHolder is null ");
			auth.logout();
			ServerUtils.getCurrentSession().removeAttribute(AUTH_HOLDER);
			auth = null;
		}

		if (auth == null) {
			auth = new JAASAuthenticationHolder(conf);
			ServerUtils.getCurrentSession().setAttribute(AUTH_HOLDER, auth);
		}

		return auth;
	}

	/**
	 * ��������������
	 * 
	 * @param login
	 *            �����
	 * @param password
	 *            ������
	 * @param conf
	 * 			  ������������
	 * @throws LoginException
	 *             � ������ ��������� �������
	 */
	public static void authenticate(String login, String password, Configuration conf)
			throws LoginException {
		JAASAuthenticationHolder auth = getAuthenticationHolder(conf);

		if (auth == null)
			throw new LoginException("authentication holder is null");
		
		auth.useLoginContext(login, password);
		Subject subject = auth.getSubject();

		//��������� ������ ��� ���������������� ������� ����������� MessAdmin
		if (ServerUtils.getCurrentSession().isInteractive())
			ContainerContextFactory.getInstance().getDefaultContainerContext().getHttpSession().setAttribute(USER_NAME, login);

		if (logger.isDebugEnabled())
			logger.debug("subject retrieved=" + subject);
	}

	/**
	 * �������� Subject ������� ��������������
	 * 
	 * @return Returns the subject.
	 */
	public Subject getSubject() {
		return authenticationUtils.getSubject();
	}

	/**
	 * ��������� ���������� ������� ��������������
	 * 
	 */
	public void logout() {
		authenticationUtils.logout();
		Session session = ServerUtils.getCurrentSession();
		if (session != null && session.isInteractive())
			try {
				ContainerContextFactory.getInstance().getDefaultContainerContext().getHttpSession().removeAttribute(USER_NAME);
			} catch (Exception e) {
				//maybe http session is null
				logger.error("logout process failed, ignored", e);
			}
	}

}
