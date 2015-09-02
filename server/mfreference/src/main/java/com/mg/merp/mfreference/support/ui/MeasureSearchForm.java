/*
 * MeasureSearchForm.java
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
package com.mg.merp.mfreference.support.ui;

import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.reference.model.Measure;

/**
 * 
 * @author leonova
 * @version $Id: MeasureSearchForm.java,v 1.4 2009/02/09 11:58:46 safonov Exp $
 * @deprecated
 */
@Deprecated
public class MeasureSearchForm extends AbstractSearchForm {
	private final static String LOAD_ANLPLAN_EJBQL = "from Measure as m"; //$NON-NLS-1$
	private final static String[] fieldList = new String[] {"Code", "FullName"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$

	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private DefaultTableController measureList;
	
	@Override
	protected void doOnRun() {
		measureList = new DefaultTableController(new DefaultEntityListTableModel<Measure>() {
			@Override
			protected void doLoad() {
				paramsName.clear();
				paramsValue.clear();
				setEntityList(MiscUtils.convertUncheckedList(Measure.class, OrmTemplate.getInstance().findByNamedParam(LOAD_ANLPLAN_EJBQL, paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]))), fieldList);
			}
		});
		measureList.getModel().load();
		showForm();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return new PersistentObject[0];
	}

}

