/*
 * AnlPlanSearchForm.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;

/**
 * Класс отображения SearchHelp для аналитических счетов
 * 
 * @author leonova
 * @version $Id: AnlPlanSearchForm.java,v 1.3 2009/02/25 14:44:21 safonov Exp $
 */
public class AnlPlanSearchForm extends AbstractSearchForm {
	private final static String LOAD_ANLPLAN_EJBQL = "from AnlPlan as ap where ap.AccPlan = :accPlan and ap.AnlLevel = :anlLevel"; //$NON-NLS-1$
	private final static String[] FIELD_LIST = new String[] {"Code", "AnlName"}; //$NON-NLS-1$ $NON-NLS-2$
	private DefaultTableController anlPlanList;
	protected short anlLevel;
	protected AccPlan accPlan;
	
	@Override
	protected void doOnRun() {
		anlPlanList = new DefaultTableController(new DefaultEntityListTableModel<AnlPlan>() {

			@Override
			protected void doLoad() {
				List<AnlPlan> list = OrmTemplate.getInstance().findByNamedParam(LOAD_ANLPLAN_EJBQL, new String[] {"accPlan", "anlLevel"}, new Object[] {accPlan, anlLevel});
				setEntityList(list, FIELD_LIST);
			}

		});
		anlPlanList.getModel().load();
		super.doOnRun();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return ((DefaultEntityListTableModel<?>) anlPlanList.getModel()).getSelectedEntities();
	}

	public void setSearchParams(AccPlan accPlan, short anlLevel) {
		this.accPlan = accPlan;
		this.anlLevel = anlLevel;
	}

}
