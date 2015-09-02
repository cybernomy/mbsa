/*
 * CreateUserForm.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.security.support.ui;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.security.support.Messages;

/**
 * Форма создания пользователя системы
 * 
 * @author Oleg V. Safonov
 * @version $Id: CreateUserForm.java,v 1.2 2007/09/15 07:04:24 alikaev Exp $
 */
public class CreateUserForm extends DefaultDialog {
	private String name;
	private String passw;
	private String verifyPassw;
	
	public CreateUserForm() {
		addOkActionListener(new FormActionListener() {

			public void actionPerformed(FormEvent event) {
				if (name == null || StringUtils.EMPTY_STRING.equals(name))
					throw new BusinessException(Messages.getInstance().getMessage(Messages.NAME_NOT_NULL));
				if (passw == null || StringUtils.EMPTY_STRING.equals(passw))
					throw new BusinessException(Messages.getInstance().getMessage(Messages.PASSWORD_NOT_NULL));
				if (passw != null && !passw.equals(verifyPassw))
					throw new BusinessException(Messages.getInstance().getMessage(Messages.PASSWORD_NOT_MATCH));
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		view.pack();
		super.doOnRun();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the passw
	 */
	public String getPassw() {
		return passw;
	}
	
}
