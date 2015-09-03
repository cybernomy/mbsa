/*
 * ManufactureDocumentSpecServiceBean.java
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
package com.mg.merp.manufacture.generic;

import java.io.Serializable;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;

/**
 * @author Oleg V. Safonov
 *
 */
public abstract class ManufactureDocumentSpecServiceBean<T extends com.mg.merp.document.model.DocSpec, ID extends Serializable> extends
		GoodsDocumentSpecificationServiceBean<T, ID> {

	/**
	 * удаление всех спецификаций документа
	 * 
	 * @param docHead	документ
	 */
	@SuppressWarnings("unchecked")
	protected void clear(DocHead docHead) {
		List<DocSpec> docSpecs = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DocSpec.class)
				.add(Restrictions.eq("DocHead", docHead)));
		for (DocSpec spec : docSpecs)
			erase((T) spec);
	}
	
}
