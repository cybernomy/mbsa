/**
 * SysCompanyBr.java
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
package com.mg.merp.core.support.ui;

import java.io.Serializable;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.generic.ui.DefaultMaintenanceEntityListTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.merp.core.model.SysCompany;

/**
 * Контроллер формы списка "Балансовых единиц"
 * 
 * @author Oleg V. Safonov
 * @version $Id: SysCompanyBr.java,v 1.1 2007/09/20 15:07:10 safonov Exp $
 */
public class SysCompanyBr extends DefaultPlainBrowseForm {

	private class SysCompanyTableModel extends DefaultMaintenanceEntityListTableModel<SysCompany> {

		public SysCompanyTableModel() {
			setColumns(new String[] {"Id", "Code", "Name", "Active"});
		}
		
		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
		 */
		@Override
		protected void doLoad() {
			List<SysCompany> list = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(SysCompany.class));
			setEntityList(list);
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.MaintenanceTableModel#setCurrentMaster(java.io.Serializable)
		 */
		public void setCurrentMaster(Serializable masterKey) {
		}

	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new SysCompanyTableModel();
	}

}
