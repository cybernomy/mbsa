/*
 * PmcFolderSearchHelp.java
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

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Базовый класс поиска сущностей "Папки" модуля <Платежный календарь>
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcFolderSearchHelp.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public abstract class PmcFolderSearchHelp extends AbstractSearchHelp {

	/**
	 * Получить тип папки
	 * @return  тип папки
	 */
	public abstract short getFolderPart();
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		PmcFolderSearchForm form = (PmcFolderSearchForm) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/PmcFolderSearchForm.mfd.xml"); //$NON-NLS-1$
		form.addSearchHelpListener(this);
		form.setFolderPart(getFolderPart());
		form.run(UIUtils.isModalMode());
	}

}
