/*
 * DocSectionSearchForm.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.document.model.DocSection;

/**
 * Форма поиска объектов-сущностей {@link com.mg.merp.document.model.DocSection DocSection}
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocSectionSearchForm.java,v 1.1 2006/08/23 13:27:52 safonov Exp $
 */
public class DocSectionSearchForm extends AbstractSearchForm {
	private final static String LOAD_CURRENCY_EJBQL = "from DocSection"; //$NON-NLS-1$
	private final static String[] fieldList = new String[] {"DSName"}; //$NON-NLS-1$
	
	private DefaultTableController docSectionList;

	public DocSectionSearchForm() {
		docSectionList = new DefaultTableController(new DefaultEntityListTableModel<DocSection>() {
			@Override
			protected void doLoad() {
				setEntityList(MiscUtils.convertUncheckedList(DocSection.class, OrmTemplate.getInstance().find(LOAD_CURRENCY_EJBQL)), fieldList);
			}
		});
		docSectionList.getModel().load();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return ((DefaultEntityListTableModel<DocSection>) docSectionList.getModel()).getSelectedEntities();
	}

}
