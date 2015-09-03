/*
 * ScrapProductHeadMt.java
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
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.manufacture.ScrapProductSpecServiceLocal;
import com.mg.merp.manufacture.model.ScrapDocumentHead;
import com.mg.merp.manufacture.model.ScrapDocumentSpec;

/**
 * Контроллер формы поддержки актов на списание потерь с операций 
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: ScrapProductHeadMt.java,v 1.8 2008/12/25 10:17:46 safonov Exp $
 */
public class ScrapProductHeadMt extends GoodsDocumentMaintenanceForm {
	
	public ScrapProductHeadMt() throws Exception {
		super();
		specService = ((ScrapProductSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/manufacture/ScrapProductSpec"));

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
				return ScrapDocumentSpec.class.getName();
			}

		});

		addMasterModelListener(spec);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doAddSpecification()
	 */
	@Override
	protected void doAddSpecification() {
		((ScrapProductSpecServiceLocal) specService).createSpecifications((ScrapDocumentHead) getEntity());
		spec.refresh();
	}

}

