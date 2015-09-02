/*
 * AccountConfigServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.exchange.support;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.exchange.ConfigServiceLocal;

/**
 * 
 * @author Oleg V. Safonov
 * @version $Id: ConfigServiceBean.java,v 1.3 2006/09/21 12:20:57 safonov Exp $
 */
@Stateless(name="merp/exchange/ConfigService")
public class ConfigServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean implements ConfigServiceLocal {

	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public String load() throws ApplicationException {
		//TODO
		//return ((ConfigDomainImpl) getDomain()).load();
		return null;
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @param conf
	 * @throws ApplicationException
	 */
	public void store(String siteCode) throws ApplicationException {
		//TODO
		//((ConfigDomainImpl) getDomain()).store(siteCode);
	}
}
