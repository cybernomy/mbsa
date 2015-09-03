/*
 * StaffListPositionMt.java
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
package com.mg.merp.personnelref.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.StaffListPositionFillServiceLocal;
import com.mg.merp.personnelref.TariffingServiceLocal;
import com.mg.merp.personnelref.model.Personnel;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffListPosition;
import com.mg.merp.personnelref.model.Tariffing;
import com.mg.merp.reference.model.NaturalPerson;

/**
 * Контроллер формы поддержки "Должности в штатном расписании"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: StaffListPositionMt.java,v 1.8 2007/08/22 14:24:32 sharapov Exp $
 */
public class StaffListPositionMt extends DefaultMaintenanceForm implements MasterModelListener {

	private MaintenanceTableController tariff;
	private TariffingServiceLocal tariffService;	
	protected AttributeMap tariffProperties = new LocalDataTransferObject();

	private MaintenanceTableController positionFill;
	private StaffListPositionFillServiceLocal positionFillService;	
	protected AttributeMap positionFillProperties = new LocalDataTransferObject();

	private Integer staffListId;


	public StaffListPositionMt() throws Exception {
		setMasterDetail(true);
		addMasterModelListener(this);

		tariffService = (TariffingServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/Tariffing"); //$NON-NLS-1$
		tariff = new MaintenanceTableController(tariffProperties);
		tariff.initController(tariffService, new DefaultMaintenanceEJBQLTableModel() {

		private final String INIT_QUERY_TEXT = "select %s from Tariffing tar %s where tar.StaffList.Id = :staffList and tar.SlPositionUniqueId = :positionUnique"; //$NON-NLS-1$
			
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromsList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("staffList"); //$NON-NLS-1$
				paramsValue.add(staffListId);			
				paramsName.add("positionUnique"); //$NON-NLS-1$
				paramsValue.add(getEntity().getAttribute("SlPositionUniqueId")); //$NON-NLS-1$				
				return String.format(INIT_QUERY_TEXT, fieldsList, fromsList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Tariffing.class, "Id", "tar.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "Category", "tar.Category.CCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "BeginDate", "tar.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "EndDate", "tar.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "MinSalaryNumber", "tar.MinSalaryNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "TariffScaleCode", "tar.TariffScaleCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "UseRiseReference", "tar.UseRiseReference", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "RiseValue", "tar.RiseValue", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "Rise", "r.RCode", "left join tar.Rise as r", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "RateOfSalary", "tar.RateOfSalary", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl1", "anl1.ACode", "left join tar.CostsAnl1 as anl1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl2", "anl2.ACode", "left join tar.CostsAnl2 as anl2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl3", "anl3.ACode", "left join tar.CostsAnl3 as anl3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl4", "anl4.ACode", "left join tar.CostsAnl4 as anl4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl5", "anl5.ACode", "left join tar.CostsAnl5 as anl5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, tariffService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {				
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(tariff);

		positionFillService = (StaffListPositionFillServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/StaffListPositionFill"); //$NON-NLS-1$
		//positionFill = new MaintenanceTableController(positionFillProperties);
		positionFill = new MaintenanceTableController(positionFillProperties) {

			/* (non-Javadoc)
			 * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doEdit()
			 */
			@Override
			protected void doEdit() {
				editPersonalAccount(((DefaultMaintenanceEJBQLTableModel) positionFill.getModel()).getSelectedPrimaryKeys());
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doView()
			 */
			@Override
			protected void doView() {
				viewPersonalAccount(((DefaultMaintenanceEJBQLTableModel) positionFill.getModel()).getSelectedPrimaryKeys());
			}
		};
		
		positionFill.initController(positionFillService, new DefaultMaintenanceEJBQLTableModel() {

			private final String INIT_QUERY_TEXT = "select %s from PositionFill pf left join pf.SlPositionUnique pu %s where pu.SlPositionUniqueId = :positionUniqueId"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromsList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();				
				paramsName.add("positionUniqueId"); //$NON-NLS-1$
				paramsValue.add(getEntity().getAttribute("SlPositionUniqueId")); //$NON-NLS-1$
				return String.format(INIT_QUERY_TEXT, fieldsList, fromsList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				//result.add(new TableEJBQLFieldDef(PositionFill.class, "Id", "pf.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "Id", "pf.PersonalAccount.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Surname", "person.Surname", "left join pf.PersonalAccount pa left join pa.Personnel personnel left join personnel.Person person", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Name", "person.Name",  false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Patronymic", "person.Patronymic",  false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Personnel.class, "TableNumber", "personnel.TableNumber",  false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "PositionFillKind", "pfk.KCode", "left join pf.PositionFillKind as pfk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "BeginDate", "pf.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "EndDate", "pf.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "RateNumber", "pf.RateNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "IsBasic", "pf.IsBasic", false));	 //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, positionFillService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {				
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(positionFill);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		staffListId = ((StaffListPosition) getEntity()).getStaffListUnit().getStaffList().getId();
		tariffProperties.put("StaffList.Id", staffListId); //$NON-NLS-1$
		tariffProperties.put("SlPositionUniqueId", event.getEntity().getAttribute("SlPositionUniqueId")); //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-2$
		positionFillProperties.put("SlPositionUniqueId", event.getEntity().getAttribute("SlPositionUniqueId")); //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-2$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		adjustPositionFillPopupMenu();
	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void editPersonalAccount(Serializable[] personalAccountIds) {
		if(personalAccountIds != null && personalAccountIds.length > 0)
			MaintenanceHelper.edit((DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/PersonalAccount"), personalAccountIds[0], null, null); //$NON-NLS-1$
	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void viewPersonalAccount(Serializable[] personalAccountIds) {
		if(personalAccountIds != null && personalAccountIds.length > 0)
			MaintenanceHelper.view((DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/PersonalAccount"), personalAccountIds[0], null, null); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
	 */
	@Override
	protected void doSetDependentReadOnly(boolean readOnly) {
		super.doSetDependentReadOnly(readOnly);
		adjustPositionFillPopupMenu();
	}

	protected void adjustPositionFillPopupMenu() {
		PopupMenu positionFillPopupMenu = view.getWidget("positionFill").getPopupMenu(); //$NON-NLS-1$
		positionFillPopupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
		positionFillPopupMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setEnabled(false);
		positionFillPopupMenu.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
		positionFillPopupMenu.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setEnabled(false);
	}

}

