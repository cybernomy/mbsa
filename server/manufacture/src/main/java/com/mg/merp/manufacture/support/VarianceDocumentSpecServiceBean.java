/*
 * VarianceDocumentSpecServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.manufacture.support;

import javax.ejb.Stateless;

import com.mg.merp.manufacture.VarianceDocumentHeadServiceLocal;
import com.mg.merp.manufacture.VarianceDocumentSpecServiceLocal;
import com.mg.merp.manufacture.generic.ManufactureDocumentSpecServiceBean;
import com.mg.merp.manufacture.model.VarianceDocumentSpec;

/**
 * Бизнес-компонент "Спецификация докумементов по отклонениям" 
 * 
 * @author leonova
 * @version $Id: VarianceDocumentSpecServiceBean.java,v 1.5 2007/02/06 17:15:40 safonov Exp $
 */
@Stateless(name="merp/manufacture/VarianceDocumentSpecService")
public class VarianceDocumentSpecServiceBean extends ManufactureDocumentSpecServiceBean<VarianceDocumentSpec, Integer> implements VarianceDocumentSpecServiceLocal{

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return VarianceDocumentHeadServiceLocal.DOCSECTION;
	}

}
