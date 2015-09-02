/*
 * InputHeadMt.java
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
package com.mg.merp.manufacture.generic.ui;

import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.manufacture.InputSpecificationServiceLocal;
import com.mg.merp.manufacture.model.InputDocumentHead;

/**
 * ������� ���������� ���������� ����� ��������� ���������� �� �������� ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: InputHeadMt.java,v 1.1 2007/08/06 12:45:50 safonov Exp $
 */
public abstract class InputHeadMt extends GoodsDocumentMaintenanceForm {

	public InputHeadMt() throws Exception {
		super();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doAddSpecification()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doAddSpecification() {
		((InputSpecificationServiceLocal) specService).createSpecifications((InputDocumentHead) getEntity());
		spec.refresh();
	}

}
