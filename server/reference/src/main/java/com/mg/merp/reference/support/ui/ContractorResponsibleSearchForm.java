/*
 * ContractorResponsibleSearchForm.java
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
package com.mg.merp.reference.support.ui;

import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.PartnerEmpl;

/**
 * Класс отображения SearchHelp для сотрудников партнера
 *  
 * @author leonova
 * @version $Id: ContractorResponsibleSearchForm.java,v 1.2 2009/02/09 12:12:58 safonov Exp $
 */
public class ContractorResponsibleSearchForm extends AbstractSearchForm {
	private final static String LOAD_PARTNER_EMPL_EJBQL = "from PartnerEmpl as pe where pe.Partner = :partner"; //$NON-NLS-1$
	private final static String[] fieldList = new String[] {"Code", "Office"}; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private DefaultTableController partnerEmplist;
	protected Contractor partner;
	
	@Override
	protected void doOnRun() {
		partnerEmplist = new DefaultTableController(new DefaultEntityListTableModel<PartnerEmpl>() {
			@Override
			protected void doLoad() {
				paramsName.clear();
				paramsValue.clear();
				paramsName.add("partner");
				paramsValue.add(partner);
				setEntityList(MiscUtils.convertUncheckedList(PartnerEmpl.class, OrmTemplate.getInstance().findByNamedParam(LOAD_PARTNER_EMPL_EJBQL, paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]))), fieldList);
			}
		});
		partnerEmplist.getModel().load();
		showForm();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return ((DefaultEntityListTableModel<?>) partnerEmplist.getModel()).getSelectedEntities();
	}

	public void setPartner(Contractor partner) {
		this.partner = partner;
	}

}
