/*
 * VarianceDocumentModelServiceBean.java
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

package com.mg.merp.manufacture.support;

import javax.ejb.Stateless;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.manufacture.VarianceDocumentHeadServiceLocal;
import com.mg.merp.manufacture.VarianceDocumentModelServiceLocal;
import com.mg.merp.manufacture.model.VarianceDocumentModel;

/**
 * Бизнес-компонент "Образцы документов по отклонениям" 
 * 
 * @author leonova
 * @version $Id: VarianceDocumentModelServiceBean.java,v 1.4 2007/09/17 08:07:15 alikaev Exp $
 */
@Stateless(name="merp/manufacture/VarianceDocumentModelService")
public class VarianceDocumentModelServiceBean extends DocumentModelServiceBean<VarianceDocumentModel, Integer> implements VarianceDocumentModelServiceLocal{

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return VarianceDocumentHeadServiceLocal.DOCSECTION;
	}

}
