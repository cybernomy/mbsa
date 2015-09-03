/*
 * FeeSummarySpecServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.merp.document.CreateSpecificationInfo;
import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.salary.FeeSummaryHeadServiceLocal;
import com.mg.merp.salary.FeeSummarySpecServiceLocal;
import com.mg.merp.salary.model.FeeSummarySpec;

/**
 * Реализация бизнес-компонента "Спецификация сводов н/у по аналитике" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FeeSummarySpecServiceBean.java,v 1.6 2007/08/27 06:19:40 sharapov Exp $
 */
@Stateless(name="merp/salary/FeeSummarySpecService") //$NON-NLS-1$
public class FeeSummarySpecServiceBean extends GoodsDocumentSpecificationServiceBean<FeeSummarySpec, Integer> implements FeeSummarySpecServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return FeeSummaryHeadServiceLocal.DOCSECTION;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#initializeForBulkCreate(com.mg.merp.document.model.DocHead, com.mg.merp.document.CreateSpecificationInfo)
	 */
	@Override
	protected FeeSummarySpec initializeForBulkCreate(DocHead docHead, CreateSpecificationInfo goodsInfo) {
		FeeSummarySpec result = super.initializeForBulkCreate(docHead, goodsInfo);
		if (goodsInfo instanceof CreateFeeSummarySpecInfo) {
			CreateFeeSummarySpecInfo feeSummarySpecInfo = (CreateFeeSummarySpecInfo) goodsInfo;
			result.setCostsAnl1(feeSummarySpecInfo.getCostsAnl1());
			result.setCostsAnl2(feeSummarySpecInfo.getCostsAnl2());
			result.setCostsAnl3(feeSummarySpecInfo.getCostsAnl3());
			result.setCostsAnl4(feeSummarySpecInfo.getCostsAnl4());
			result.setCostsAnl5(feeSummarySpecInfo.getCostsAnl5());
		}
		return result;
	}
	
}
