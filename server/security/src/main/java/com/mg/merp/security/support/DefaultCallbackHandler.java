/*
 * DefaultCallbackHandler.java
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

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class DefaultCallbackHandler implements CallbackHandler {
	private String login = null;
	private char[] password = null;

	public DefaultCallbackHandler(String login, String password) {
		this.login = login;
		if (password != null)
			this.password = password.toCharArray();
	}
	
	/* (non-Javadoc)
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
	 */
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		// TODO Auto-generated method stub
		for (Callback callback : callbacks) {
			if (callback instanceof NameCallback)
				((NameCallback) callback).setName(login);
			else if (callback instanceof PasswordCallback)
				((PasswordCallback) callback).setPassword(password);
		}
	}

}
