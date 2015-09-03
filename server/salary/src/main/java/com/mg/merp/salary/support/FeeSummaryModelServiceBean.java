/*
 * FeeSummaryModelServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.salary.FeeSummaryHeadServiceLocal;
import com.mg.merp.salary.FeeSummaryModelServiceLocal;

/**
 * Бизнес-компонент "Образцы сводов по н/у" 
 * 
 * @author leonova
 * @version $Id: FeeSummaryModelServiceBean.java,v 1.4 2006/09/12 11:03:39 leonova Exp $
 */
@Stateless(name="merp/salary/FeeSummaryModelService")
public class FeeSummaryModelServiceBean extends DocumentModelServiceBean<DocHeadModel, Integer> implements FeeSummaryModelServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return FeeSummaryHeadServiceLocal.DOCSECTION;
	}



}
