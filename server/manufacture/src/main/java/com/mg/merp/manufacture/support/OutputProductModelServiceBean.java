/*
 * OutputProductModelServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
import com.mg.merp.manufacture.OutputProductHeadServiceLocal;
import com.mg.merp.manufacture.OutputProductModelServiceLocal;
import com.mg.merp.manufacture.model.OutputProductModel;

/**
 * Реализация бизнес-компонента "Образцы актов выпуска готовой продукции" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: OutputProductModelServiceBean.java,v 1.4 2007/01/16 11:32:08 sharapov Exp $
 */
@Stateless(name="merp/manufacture/OutputProductModelService") //$NON-NLS-1$
public class OutputProductModelServiceBean extends DocumentModelServiceBean<OutputProductModel, Integer> implements OutputProductModelServiceLocal{

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return OutputProductHeadServiceLocal.DOCSECTION;
	}

}
