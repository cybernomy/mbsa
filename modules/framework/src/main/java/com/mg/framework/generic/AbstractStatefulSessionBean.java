/*
 * AbstractStatefulSessionBean.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.generic;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;

/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractStatefulSessionBean.java,v 1.3 2006/03/01 15:26:36 safonov Exp $
 *
 */
public abstract class AbstractStatefulSessionBean extends AbstractSessionBean {

	@PostActivate
	public void postActivateCallback() {
		try {
			getLogger().debug("postActivateCallback");
			doActivate();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PrePassivate
	public void prePassivateCallback() {
		try {
			getLogger().debug("prePassivateCallback");
			doPassivate();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void doActivate() {
	}

	protected void doPassivate() {
	}
}
