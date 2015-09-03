/*
 * ContractorSearchForm.java
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

import java.util.Set;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.RadioButtonGroup;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.reference.model.Partner;
import com.mg.merp.reference.support.Messages;

/**
 * Контроллер формы поиска контрагентов
 * 
 * @author Oleg V. Safonov
 * @version $Id: ContractorSearchForm.java,v 1.5 2009/02/09 16:39:37 safonov Exp $
 */
public class ContractorSearchForm extends AbstractSearchForm {
	/**
	 * контрагент партнер
	 */
	public final static String CONTRACTOR_PARTNER = "partner";
	/**
	 * контрагент подразделение
	 */
	public final static String CONTRACTOR_ORGUNIT = "orgunit";
	/**
	 * контрагент сотрудник
	 */
	public final static String CONTRACTOR_EMPLOYEE = "employee";

	private static final String CONTRACTOR_KIND_WIDGET = "contractorKind";
	private static final String CONTRACTOR_KIND_PROPERTY = "contractorKind";
	private DefaultTableController contractorList;
	private short contractorKind = -1;
	private String[] contractorKindNames;
	private Integer[] selectedContractorKeys;
	private Criteria criteria;
	private String[] contractorKinds;
	private StringBuilder cacheRegion = new StringBuilder();

	public ContractorSearchForm() {
		contractorList = new DefaultTableController(new DefaultEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Contractor.class, "Id", "contr.Id", true));
				result.add(new TableEJBQLFieldDef(Contractor.class, "Code", "contr.Code", true));
				result.add(new TableEJBQLFieldDef(Contractor.class, "FullName", "contr.FullName", true));
				return result;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setRowList(OrmTemplate.getInstance().findByCriteria(criteria));
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				selectedContractorKeys = new Integer[rows.length];
				for (int i = 0; i < rows.length; i++)
					selectedContractorKeys[i] = (Integer) getRowList().get(rows[i])[0];
			}

		});
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		if (selectedContractorKeys == null)
			return new PersistentObject[0];
		
		PersistentManager mgr = ServerUtils.getPersistentManager();
		PersistentObject[] result = new PersistentObject[selectedContractorKeys.length];
		for (int i = 0; i < selectedContractorKeys.length; i++)
			result[i] = mgr.find(Contractor.class, selectedContractorKeys[i]);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		//установим фокус на таблице
		((Widget) view.getWidget("contractorList")).requestFocus();
		//заполним переключатель доступными типами контрагентов
		((RadioButtonGroup) view.getWidget(CONTRACTOR_KIND_WIDGET)).setItems(contractorKindNames);
		//если выбор из одного контрагента, то сразу загрузим список
		if (contractorKinds.length == 1) {
			contractorKind = 0;
			loadContractors();
			view.flushModel();
		} else {
			//загрузим из профиля тип контрагента к которому обращались последний раз
			String lastKind = view.getUIProfile().getProperty(CONTRACTOR_KIND_PROPERTY);
			if (!StringUtils.stringNullOrEmpty(lastKind))
				for (int i = 0, length = contractorKinds.length; i < length; i++) {
					if (lastKind.equals(contractorKinds[i])) {
						contractorKind = (short) i;
						loadContractors();
						view.flushModel();
					}
				}
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnClose()
	 */
	@Override
	protected void doOnClose() {
		//сохраним в профиле тип контрагента
		if (contractorKind >= 0 && contractorKind < contractorKinds.length) {
			String kind = contractorKinds[contractorKind];
			view.getUIProfile().setProperty(CONTRACTOR_KIND_PROPERTY, kind);
		}
		super.doOnClose();
	}

	/**
	 * загрузка контрагентов
	 */
	protected void loadContractors() {
		if (contractorKind == -1)
			return;
		
		Projection projection = Projections.projectionList(Projections.property("contr.Id"), Projections.property("contr.Code"), Projections.property("contr.FullName"));
		
		String kind = contractorKinds[contractorKind];
		if (CONTRACTOR_PARTNER.equalsIgnoreCase(kind))
			criteria = DatabaseUtils.generateFlatBrowseCriteria(OrmTemplate.createCriteria(Partner.class, "contr").setProjection(projection), "contr.FolderId", 0);
		else if (CONTRACTOR_ORGUNIT.equalsIgnoreCase(kind))
			criteria = DatabaseUtils.generateFlatBrowseCriteria(OrmTemplate.createCriteria(OrgUnit.class, "contr").setProjection(projection), "contr.Id", 4);
		else if (CONTRACTOR_EMPLOYEE.equalsIgnoreCase(kind))
			criteria = DatabaseUtils.generateFlatBrowseCriteria(OrmTemplate.createCriteria(Employees.class, "contr").setProjection(projection), "contr.FolderId", 4);
		else
			throw new IllegalArgumentException("Invalid contractor kind: ".concat(kind));
		
		cacheRegion.setLength(0);
		cacheRegion.append("com/mg/merp/reference/contractorsearchform/").append(kind).append("/");
		criteria.addOrder(Order.asc("contr.UpCode")).setCacheable(true).setCacheRegion(cacheRegion.toString()).setFlushMode(FlushMode.MANUAL);
		
		contractorList.getModel().load();
	}
	
	/**
	 * обработчик события обновления списка контрагентов
	 * 
	 * @param event
	 */
	protected void onActionRefresh(WidgetEvent event) {
		loadContractors();
	}

	/**
	 * запуск формы поиска
	 * 
	 * @param kinds	список типов контрагентов для поиска
	 */
	public void execute(String[] kinds) {
		//при пустом даем доступ ко всем контракторам системы
		if (kinds != null)
			contractorKinds = kinds;
		else
			contractorKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_ORGUNIT, ContractorSearchForm.CONTRACTOR_EMPLOYEE};

		Messages msg = Messages.getInstance();
		contractorKindNames = new String[contractorKinds.length];
		for (int i = 0; i < contractorKinds.length; i++) {
			String kind = contractorKinds[i];
			if (CONTRACTOR_PARTNER.equalsIgnoreCase(kind))
				contractorKindNames[i] = msg.getMessage(Messages.CONTRACTOR_PARTNER);
			else if (CONTRACTOR_ORGUNIT.equalsIgnoreCase(kind))
				contractorKindNames[i] = msg.getMessage(Messages.CONTRACTOR_ORGUNIT);
			else if (CONTRACTOR_EMPLOYEE.equalsIgnoreCase(kind))
				contractorKindNames[i] = msg.getMessage(Messages.CONTRACTOR_EMPLOYEE);
			else
				throw new IllegalArgumentException("Invalid contractor kind: ".concat(kind));
		}
		
		run(UIUtils.isModalMode());
	}
	
}
