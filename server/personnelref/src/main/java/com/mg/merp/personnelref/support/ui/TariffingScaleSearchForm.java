/*
 * CurrencySearchForm.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.personnelref.model.TariffScale;

/**
 * Форма поиска бизнес-компонента "Тарифные сетки"
 * 
 * @author leonova
 * @version $Id: TariffingScaleSearchForm.java,v 1.3 2009/02/09 12:11:17 safonov Exp $
 */
public class TariffingScaleSearchForm extends AbstractSearchForm {
	private final static String LOAD_TARIFFING_SCALE_EJBQL = "from TariffScale"; //$NON-NLS-1$
	private final static String[] fieldList = new String[] {"SCode", "SName", "SType", "BeginDate", "FirstClassAlg"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
	
	private DefaultTableController tariffinhScalerist;
	
	public TariffingScaleSearchForm() {
		tariffinhScalerist = new DefaultTableController(new DefaultEntityListTableModel<TariffScale>() {
			@Override
			protected void doLoad() {
				setEntityList(MiscUtils.convertUncheckedList(TariffScale.class, OrmTemplate.getInstance().find(LOAD_TARIFFING_SCALE_EJBQL)), fieldList);
			}
		});
		tariffinhScalerist.getModel().load();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return ((DefaultEntityListTableModel<?>) tariffinhScalerist.getModel()).getSelectedEntities();
	}

}
