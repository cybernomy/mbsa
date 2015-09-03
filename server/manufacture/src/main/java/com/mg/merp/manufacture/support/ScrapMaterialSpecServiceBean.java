/*
 * ScrapMaterialSpecServiceBean.java
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

import com.mg.merp.manufacture.ScrapMaterialHeadServiceLocal;
import com.mg.merp.manufacture.ScrapMaterialSpecServiceLocal;
import com.mg.merp.manufacture.generic.ScrapDocumentSpecServiceBean;
import com.mg.merp.manufacture.model.JobMaterial;
import com.mg.merp.manufacture.model.ScrapDocumentHead;
import com.mg.merp.manufacture.model.ScrapDocumentSpec;

/**
 * Бизнес-компонент "Спецификации актов на списание потерь материалов 
 * 
 * @author leonova
 * @version $Id: ScrapMaterialSpecServiceBean.java,v 1.6 2007/08/06 12:44:53 safonov Exp $
 */
@Stateless(name="merp/manufacture/ScrapMaterialSpecService")
public class ScrapMaterialSpecServiceBean extends ScrapDocumentSpecServiceBean<ScrapDocumentSpec> implements ScrapMaterialSpecServiceLocal{

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return ScrapMaterialHeadServiceLocal.DOCSECTION;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.generic.ScrapDocumentSpecServiceBean#doCreateSpecifications(com.mg.merp.manufacture.model.ScrapDocumentHead)
	 */
	@Override
	protected void doCreateSpecifications(ScrapDocumentHead docHead) {
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		for (JobMaterial jobMaterial : ManufactureUtils.loadJobRouteMaterial(docHead.getDetectOper())) {
			docSpecs.add(new CreateManufactureSpecificationInfoImpl(
					jobMaterial.getCatalog().getId(),
					null,
					BigDecimal.ZERO,
					jobMaterial.getMtlQty().multiply(docHead.getDetectJob().getQtyScrapped()),
					null,
					jobMaterial,
					jobMaterial.getMtlCostCategory(),
					jobMaterial.getMeasure(),
					null));
		}
		bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
	}

}
