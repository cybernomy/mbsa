/*
 * InputMaterialModelServiceBean.java
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
import com.mg.merp.manufacture.InputMaterialHeadServiceLocal;
import com.mg.merp.manufacture.InputMaterialModelServiceLocal;
import com.mg.merp.manufacture.model.InputDocumentModel;

/**
 * Бизнес-компонент "Образцы актов на списание материалов в НЗП" 
 * 
 * @author leonova
 * @version $Id: InputMaterialModelServiceBean.java,v 1.3 2006/09/12 11:08:13 leonova Exp $
 */
@Stateless(name="merp/manufacture/InputMaterialModelService")
public class InputMaterialModelServiceBean extends DocumentModelServiceBean<InputDocumentModel, Integer> implements InputMaterialModelServiceLocal{

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return InputMaterialHeadServiceLocal.FOLDER_PART;
	}



}
