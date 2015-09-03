/*
 * TimeBoardPositionBr.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.table.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffListUnit;
import com.mg.merp.personnelref.support.ui.StaffListUnitTreeNode;
import com.mg.merp.table.TimeBoardPositionServiceLocal;
import com.mg.merp.table.model.TimeBoardPosition;


/**
 * Контроллер браузера должностей в табеле
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardPositionBr.java,v 1.6 2008/08/12 14:38:08 sharapov Exp $
 */
public class TimeBoardPositionBr extends DefaultHierarchyBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from TimeBoardPosition tbp %s where tbp.TimeBoardHead.Id = :timeBoardHeadId %s";	 //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private Serializable timeBoardHeadId;

	public TimeBoardPositionBr() throws Exception{
		super();
		tree.setParentPropertyName("StaffListUnit.Id"); //$NON-NLS-1$
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/StaffListUnit");				 //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
	 */
	@Override
	protected void initializeMaster(PersistentObject master) {		
		uiProperties.put("StaffListUnit", master); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
	 */
	protected TreeNode loadFolders() {
		List<StaffListUnit> list = MiscUtils.convertUncheckedList(StaffListUnit.class, OrmTemplate.getInstance().findByNamedParam(String.format("from StaffListUnit slu where %s and slu.StaffList = :staffList order by slu.Id", DatabaseUtils.generateFlatBrowseWhereEJBQL("slu.Id", 6)),  "staffList", getCalcPeriodService().getCurrentCalcPeriod().getStaffList())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$				
		return StaffListUnitTreeNode.createTree(list);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		PopupMenu popupMenu = view.getWidget(TABLE_WIDGET).getPopupMenu();
		popupMenu.getMenuItem(MaintenanceTable.EDIT_MENU_ITEM).setEnabled(false);
		popupMenu.getMenuItem(MaintenanceTable.VIEW_MENU_ITEM).setEnabled(false);
		popupMenu.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
		popupMenu.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setEnabled(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createTableController()
	 */
	@Override
	protected MaintenanceTableController createTableController() {
		return new MaintenanceTableController(uiProperties) {

			/* (non-Javadoc)
			 * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doAdd()
			 */
			@Override
			protected void doAdd() {
				addTimeBoardPositions();
			}
		};
	}

	/**
	 * Добавить сотрудника в табель
	 */
	private void addTimeBoardPositions() {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.salary.support.ui.PositionFillInStaffListSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				PositionFill[] positionFills = new PositionFill[event.getItems().length];
				for(int i = 0; i < event.getItems().length; i++)
					positionFills[i] = (PositionFill) event.getItems()[i];
				getTimeBoardPositionService().addTimeBoardPositions((Integer) timeBoardHeadId, positionFills);
				table.refresh();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		try {
			searchHelp.search();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(TimeBoardPosition.class, "Id", "tbp.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "PersonalAccount.Personnel.Person.Surname", "np.Surname", "left join tbp.PositionFill.PersonalAccount as pa left join pa.Personnel as p left join p.Person np", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "PersonalAccount.Personnel.Person.Name", "np.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "PersonalAccount.Personnel.Person.Patronymic", "np.Patronymic", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PositionFill.class, "PersonalAccount.Personnel.TableNumber", "p.TableNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TimeBoardPosition.class, "PositionFill.SlPositionUnique.Position", "tbp.PositionFill.SlPositionUnique.Position.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
			 */
			@Override
			protected void doLoad() {				
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));				
			}
		};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		StringBuilder whereText = new StringBuilder();
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		whereText.append(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "tbp.StaffListUnit", 6, "folder", folderEntity, paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}

	/**
	 * @param timeBoardHeadId The timeBoardHeadId to set.
	 */
	protected void setTimeBoardHeadId(Serializable timeBoardHeadId) {
		this.timeBoardHeadId = timeBoardHeadId;
		paramsName.add("timeBoardHeadId"); //$NON-NLS-1$
		paramsValue.add((Integer) timeBoardHeadId);
	}

	private CalcPeriodServiceLocal getCalcPeriodService() {
		return (CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME);
	}

	private TimeBoardPositionServiceLocal getTimeBoardPositionService() {
		return (TimeBoardPositionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TimeBoardPositionServiceLocal.SERVICE_NAME);
	}

}
