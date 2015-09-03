/*
 * InvoiceSpecServiceBean.java
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

package com.mg.merp.retail.support;

import javax.ejb.Stateless;

import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.retail.InvoiceHeadServiceLocal;
import com.mg.merp.retail.InvoiceSpecServiceLocal;
import com.mg.merp.retail.model.RtlInvoiceSpec;

/**
 * Реализация бизнес-компонента "Спецификация документов на отпуск" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: InvoiceSpecServiceBean.java,v 1.5 2007/10/30 14:54:00 sharapov Exp $
 */
@Stateless(name="merp/retail/InvoiceSpecService")
public class InvoiceSpecServiceBean extends GoodsDocumentSpecificationServiceBean<RtlInvoiceSpec, Integer> implements InvoiceSpecServiceLocal {
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InvoiceHeadServiceLocal.DOCSECTION;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#doAdjust(com.mg.merp.document.model.DocSpec)
	 */
	@Override
	protected void doAdjust(RtlInvoiceSpec entity) {
		DefaultRtlDocSpecPropertiesCalculationStrategy strategy = new DefaultRtlDocSpecPropertiesCalculationStrategy(entity, getDocSection().isWithTaxes(), DocumentUtils.getDocumentService(getDocSection()).getConfiguration().getCurrencyScale(), getRoundContext()); 
		strategy.adjust();
	}

}
