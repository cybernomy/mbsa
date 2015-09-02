/*
 * Created on 21.01.2005
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
 */
package com.mg.merp.exchange.support;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.exchange.ImportProcessorServiceLocal;
import com.mg.merp.exchange.InitImportResult;

/**
 * 
 * @author Oleg V. Safonov
 * @version $Id: ImportProcessorServiceBean.java,v 1.3 2006/09/21 12:20:57 safonov Exp $
 */
@Stateless(name="merp/exchange/ImportProcessorService")
public class ImportProcessorServiceBean extends
		com.mg.framework.generic.AbstractPOJOBusinessObjectStatefulServiceBean implements ImportProcessorServiceLocal {

	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @return
	 * @throws ApplicationException
	 */
 	public InitImportResult initImport(String siteCode) throws ApplicationException {
 		//TODO
 		//return ((ImportProcessorDomainImpl) getDomain()).initImport(siteCode);
 		return null;
 	}

 	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @return
	 * @throws ApplicationException
	 */
 	public void doImport(String siteCode, int packet) throws ApplicationException {
 		//TODO
 		//((ImportProcessorDomainImpl) getDomain()).doImport(siteCode, packet);
 	}

 	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @return
	 * @throws ApplicationException
	 */
 	public void processConfirmations(String siteCode) throws ApplicationException {
 		//TODO
 		//((ImportProcessorDomainImpl) getDomain()).processConfirmations(siteCode);
 	}
}
