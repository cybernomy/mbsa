/*
 * PmcPlaningRest.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.core.model.Folder;
import com.mg.merp.paymentcontrol.model.PmcResource;

/**
 * Контроллер формы условий отбора позиций "планирования платежей"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcPlaningRest.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class PmcPlaningRest extends DefaultRestrictionForm {

	@DataItemName("PaymentControl.Liab.PrefResFolder") //$NON-NLS-1$
	private Folder resourceFolder;
	private PmcResource resource;
	private boolean isExclude;

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultRestrictionForm#doClearRestrictionItem()
	 */
	@Override
	protected void doClearRestrictionItem() {
		resourceFolder = null;
		resource = null;
		isExclude = false;
	}

	/**
	 * @return the resource
	 */
	public PmcResource getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(PmcResource resource) {
		this.resource = resource;
	}

	/**
	 * @return the resourceFolder
	 */
	public Folder getResourceFolder() {
		return resourceFolder;
	}

	/**
	 * @param resourceFolder the resourceFolder to set
	 */
	public void setResourceFolder(Folder resourceFolder) {
		this.resourceFolder = resourceFolder;
	}

	/**
	 * @return the isExclude
	 */
	public boolean isExclude() {
		return isExclude;
	}

	/**
	 * @param isExclude the isExclude to set
	 */
	public void setExclude(boolean isExclude) {
		this.isExclude = isExclude;
	}

}
