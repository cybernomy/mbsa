/*
 * ScrapMaterialModelServiceBean.java
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

import javax.ejb.Stateless;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.manufacture.ScrapMaterialHeadServiceLocal;
import com.mg.merp.manufacture.ScrapMaterialModelServiceLocal;
import com.mg.merp.manufacture.model.ScrapDocumentModel;

/**
 * Бизнес-компонент "Образцы актов на списание потарь материалов" 
 * 
 * @author leonova
 * @version $Id: ScrapMaterialModelServiceBean.java,v 1.3 2006/09/12 11:08:13 leonova Exp $
 */
@Stateless(name="merp/manufacture/ScrapMaterialModelService")
public class ScrapMaterialModelServiceBean extends DocumentModelServiceBean<ScrapDocumentModel, Integer> implements ScrapMaterialModelServiceLocal{

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return ScrapMaterialHeadServiceLocal.FOLDER_PART;
	}



}
