/*
 * InputMaterialSpecServiceBean.java
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

package com.mg.merp.manufacture.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.manufacture.InputMaterialHeadServiceLocal;
import com.mg.merp.manufacture.InputMaterialSpecServiceLocal;
import com.mg.merp.manufacture.TransactionServiceLocal;
import com.mg.merp.manufacture.generic.InputDocumentSpecServiceBean;
import com.mg.merp.manufacture.model.InputDocumentHead;
import com.mg.merp.manufacture.model.InputDocumentSpec;
import com.mg.merp.manufacture.model.JobMaterial;

/**
 * Бизнес-компонент "Спецификация актов на списание материалов в НЗП" 
 * 
 * @author leonova
 * @version $Id: InputMaterialSpecServiceBean.java,v 1.6 2007/08/06 12:44:53 safonov Exp $
 */
@Stateless(name="merp/manufacture/InputMaterialSpecService")
public class InputMaterialSpecServiceBean extends InputDocumentSpecServiceBean<InputDocumentSpec> implements InputMaterialSpecServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.generic.InputDocumentSpecServiceBean#doCreateSpecifications(com.mg.merp.manufacture.model.InputDocumentHead)
	 */
	@Override
	protected void doCreateSpecifications(InputDocumentHead docHead) {
		TransactionServiceLocal mfTran = (TransactionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactionServiceLocal.SERVICE_NAME);
		List<CreateManufactureSpecificationInfoImpl> docSpecs = new ArrayList<CreateManufactureSpecificationInfoImpl>();
		for (JobMaterial jobMaterial : ManufactureUtils.loadJobRouteMaterial(docHead.getOper())) {
			docSpecs.add(new CreateManufactureSpecificationInfoImpl(
					jobMaterial.getCatalog().getId(),
					null,
					BigDecimal.ZERO,
					jobMaterial.getMtlQty().multiply(docHead.getJob().getQtyComplete()).subtract(mfTran.getQuantityByResource(jobMaterial.getId())),
					null,
					jobMaterial,
					jobMaterial.getMtlCostCategory(),
					jobMaterial.getMeasure(),
					null));
		}
		bulkCreate(docHead, docSpecs.toArray(new CreateManufactureSpecificationInfoImpl[docSpecs.size()]));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InputMaterialHeadServiceLocal.DOCSECTION;
	}

}
