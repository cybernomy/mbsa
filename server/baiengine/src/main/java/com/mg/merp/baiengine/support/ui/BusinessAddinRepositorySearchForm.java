/*
 * BusinessAddinRepositorySearchForm.java
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
package com.mg.merp.baiengine.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.baiengine.model.Repository;

/**
 * @author Oleg V. Safonov
 * @version $Id: BusinessAddinRepositorySearchForm.java,v 1.2 2008/12/17 13:35:58 safonov Exp $
 */
public class BusinessAddinRepositorySearchForm extends AbstractSearchForm {
	private final static String LOAD_BAI_REPOSITORY_EJBQL = "from Repository r where r.Engine <> com.mg.merp.baiengine.model.EngineType.PASCAL_ENGINE order by r.Code";
	private DefaultTableController repositoryModelList;
	private final static String[] fieldList = new String[] {"Code", "Name", "Engine", "ImplementationName"};

	public BusinessAddinRepositorySearchForm() {
		repositoryModelList = new DefaultTableController(new DefaultEntityListTableModel<Repository>() {

			@Override
			protected void doLoad() {
				setEntityList(OrmTemplate.getInstance().find(Repository.class, LOAD_BAI_REPOSITORY_EJBQL), fieldList);
			}
			
		});
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		repositoryModelList.getModel().load();
		super.doOnRun();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return ((DefaultEntityListTableModel<Repository>) repositoryModelList.getModel()).getSelectedEntities();
	}

}
