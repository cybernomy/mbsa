/*
 * InputMaterialHeadMt.java
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
package com.mg.merp.manufacture.support.ui;

import java.util.Set;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.manufacture.InputMaterialSpecServiceLocal;
import com.mg.merp.manufacture.generic.ui.InputHeadMt;
import com.mg.merp.manufacture.model.InputDocumentSpec;

/**
 * ���������� ����� ��������� ����� �� �������� ���������� � ���
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: InputMaterialHeadMt.java,v 1.8 2008/12/25 10:17:45 safonov Exp $
 */
public class InputMaterialHeadMt extends InputHeadMt {
	
	public InputMaterialHeadMt() throws Exception {
		super();
		specService = ((InputMaterialSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/manufacture/InputMaterialSpec"));

		spec.initController(specService, new ManufactureDocSpecMaintenanceEJBQLTableModel() {			
			
			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
			 */			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(super.getDefaultFieldDefsSet(), service);
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel#getDocSpecModelName()
			 */
			@Override
			protected String getDocSpecModelName() {
				return InputDocumentSpec.class.getName();
			}

		});

		addMasterModelListener(spec);
	}

}