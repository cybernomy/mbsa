/*
 * AbbreviationSearchHelp.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Поисковик сущностей "Сокращенные наименования элемента адреса"
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: AbbreviationSearchHelp.java,v 1.3 2007/07/16 13:22:45 sharapov Exp $
 */
public class AbbreviationSearchHelp extends AbstractSearchHelp {

	private final String FORM_NAME = "com/mg/merp/personnelref/resources/AbbreviationSearchForm.mfd.xml"; //$NON-NLS-1$

	private Integer abbreviationLevel;


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		AbbreviationSearchForm searchForm = (AbbreviationSearchForm) UIProducer.produceForm(FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.setAbbreviationLevel(abbreviationLevel);
		searchForm.run(UIUtils.isModalMode());
	}

	/**
	 * Установить уровень для поиска (если NULL, то поиск будет осуществлен для всех уровней)
	 */
	public void setAbbreviationLevel(Integer abbreviationLevel) {
		this.abbreviationLevel = abbreviationLevel;
	}

}
