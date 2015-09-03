/*
 * PmaFolderSearchHelp.java
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
package com.mg.merp.paymentalloc.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Базовый класс поиска сущностей "Папки" модуля <Журнал платежей>
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmaFolderSearchHelp.java,v 1.1 2007/05/25 08:42:43 sharapov Exp $
 */
public abstract class PmaFolderSearchHelp extends AbstractSearchHelp {

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
		PmaFolderSearchForm form = (PmaFolderSearchForm) UIProducer.produceForm("com/mg/merp/paymentalloc/resources/PmaFolderSearchForm.mfd.xml"); //$NON-NLS-1$
		form.addSearchHelpListener(this);
		form.setFolderPart(getFolderPart());
		form.run(UIUtils.isModalMode());
	}

}
