/*
 * ScrapLaborSpecServiceBean.java
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.merp.manufacture.ScrapLaborHeadServiceLocal;
import com.mg.merp.manufacture.ScrapLaborSpecServiceLocal;
import com.mg.merp.manufacture.generic.ScrapDocumentSpecServiceBean;
import com.mg.merp.manufacture.model.JobLabor;
import com.mg.merp.manufacture.model.ScrapDocumentHead;
import com.mg.merp.manufacture.model.ScrapDocumentSpec;
import com.mg.merp.mfreference.support.ConfigurationHelper;

/**
 * Бизнес-компонент "Спецификация актов на списание потерь времени, отработанного РС" 
 * 
 * @author leonova
 * @version $Id: ScrapLaborSpecServiceBean.java,v 1.6 2007/08/06 12:44:54 safonov Exp $
 */
@Stateless(name="merp/manufacture/ScrapLaborSpecService")
public class ScrapLaborSpecServiceBean extends ScrapDocumentSpecServiceBean<ScrapDocumentSpec> implements ScrapLaborSpecServiceLocal{

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return ScrapLaborHeadServiceLocal.DOCSECTION;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.generic.ScrapDocumentSpecServiceBean#doCreateSpecifications(com.mg.merp.manufacture.model.ScrapDocumentHead)
	 */
	@Override
	protected void doCreateSpecifications(ScrapDocumentHead docHead) {
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		for (JobLabor jobLabor : ManufactureUtils.loadJobRouteLabor(docHead.getDetectOper())) {
			docSpecs.add(new CreateManufactureSpecificationInfoImpl(
					ConfigurationHelper.getConfiguration().getLaborTime().getId(),
					null,
					BigDecimal.ZERO,
					jobLabor.getLbrNumber(),
					null,
					jobLabor,
					jobLabor.getLbrCostCategory(),
					null,
					null));
		}
		bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
	}

}
