/*
 * ConfidentialDataServiceBean.java
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
package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.reference.ConfidentialDataServiceLocal;
import com.mg.merp.reference.model.ConfidentialData;

/**
 * 
 * @author Oleg V. Safonov
 * @version $Id: ConfidentialDataServiceBean.java,v 1.4 2006/09/20 13:12:59 safonov Exp $
 */
@Stateless(name="merp/reference/ConfidentialDataService")
public class ConfidentialDataServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<ConfidentialData, Integer> implements ConfidentialDataServiceLocal {

	public void ejbCreate() throws javax.ejb.CreateException {
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @param id
	 * @return
	 * @throws ApplicationException
	 */
	public byte[] getConfData(int id) throws ApplicationException {
		//TODO
		//return ((ConfidentialDataDomainImpl) getDomain()).getConfData(id);
		return null;
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @param confData
	 * @param id
	 * @throws ApplicationException
	 */
	public void setConfData(byte[] confData, int id) throws ApplicationException {
		//TODO
		//((ConfidentialDataDomainImpl) getDomain()).setConfData(confData, id);
	}
}
