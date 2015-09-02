/*
 * ScheduleHeadMt.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 *  Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.table.support.ui;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Table;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.Dialogs;
import com.mg.framework.support.ui.Dialogs.InputQueryDialogListener;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;
import com.mg.merp.personnelref.model.CalcPeriod;
import com.mg.merp.table.ScheduleHeadServiceLocal;
import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.ScheduleHead;
import com.mg.merp.table.model.TimeKind;
import com.mg.merp.table.support.Messages;
import com.mg.merp.table.support.TimeBoardHelper;

/**
 * Контроллер формы поддержки "График работ в табельном учете"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleHeadMt.java,v 1.4 2008/08/12 14:38:08 sharapov Exp $
 */
public class ScheduleHeadMt extends DefaultMaintenanceForm implements MasterModelListener {

	private static final String SPEC_TABLE_WIDGET_NAME = "specTable"; //$NON-NLS-1$
	private static final String CALC_PERIOD_WIDGET_NAME = "calcPeriod"; //$NON-NLS-1$

	private MaintenanceTableController calcPeriod;
	private CalcPeriodServiceLocal calcPeriodService;
	protected AttributeMap calcPeriodProperties = new LocalDataTransferObject();
	private DefaultTableController specTable;
	protected ScheduleHeadServiceLocal scheduleHeadService = (ScheduleHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ScheduleHeadServiceLocal.SERVICE_NAME);
	protected TimeBoardHelper timeBoardHelper = new TimeBoardHelper();


	public ScheduleHeadMt() throws Exception {
		addMasterModelListener(this);
		setMasterDetail(true);

		calcPeriodService = (CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/CalcPeriod"); //$NON-NLS-1$
		calcPeriod = new MaintenanceTableController(calcPeriodProperties);
		calcPeriod.initController(calcPeriodService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from CalcPeriod c %s"; //$NON-NLS-1$

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);	
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
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
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "Id", "c.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "PName", "c.PName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "BeginDate", "c.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "EndDate", "c.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "StaffList", "sl.LName", "left join c.StaffList as sl", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, calcPeriodService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText());
			}
		});
		addMasterModelListener(calcPeriod);

		specTable = new DefaultTableController(new ScheduleSpecTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				CalcPeriod calcPeriodEntity = getCurrentCalcPeriod();
				if(calcPeriodEntity != null) {
					ScheduleHead scheduleHead = (ScheduleHead) getEntity();
					getTableModel().fillGrid(scheduleHead, calcPeriodEntity.getBeginDate(), calcPeriodEntity.getEndDate(), scheduleSpecService.loadSpecs(scheduleHead), timeBoardHelper.getTimeKinds());
				}
			}
		});
		calcPeriod.addMasterModelListener(specTable);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		adjustCalcPeriodPopupMenu();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
	 */
	@Override
	protected void doSetDependentReadOnly(boolean readOnly) {
		super.doSetDependentReadOnly(readOnly);
		adjustCalcPeriodPopupMenu();
	}

	/**
	 * Обработчик пункта КМ "Выбрать тип времени с учетом по дням"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseDayliTimeKind(WidgetEvent event) throws Exception {
		final int[] columns = getSelectedColumns();
		if(columns.length > 0 && columns[0] > 0) {
			SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.table.support.ui.TimeKindDayliSearchHelp"); //$NON-NLS-1$
			searchHelp.addSearchHelpListener(new SearchHelpListener() {

				public void searchPerformed(SearchHelpEvent event) {
					getTableModel().setTimeKind((TimeKind) event.getItems()[0], columns);
				}

				public void searchCanceled(SearchHelpEvent event) {
					//do nothing
				}
			});
			searchHelp.search();
		}
	}

	/**
	 * Обработчик пункта КМ "Установить кол-во часов"
	 * @param event - событие
	 */
	public void onActionSetHours(WidgetEvent event) {
		final int[] columns = getSelectedColumns();
		final ScheduleSpecTableModel tableModel = getTableModel();
		if(columns.length > 0 && columns[0] > 0) {
			Dialogs.inputQuery(Messages.getInstance().getMessage(Messages.INPUT_HOURS_DIALOG_TITLE), StringUtils.BLANK_STRING, tableModel.getHours(columns), new InputQueryDialogListener<BigDecimal>() {

				/* (non-Javadoc)
				 * @see com.mg.framework.support.ui.Dialogs.InputQueryDialogListener#inputCanceled()
				 */
				public void inputCanceled() {
					// do nothing
				}

				/* (non-Javadoc)
				 * @see com.mg.framework.support.ui.Dialogs.InputQueryDialogListener#inputPerformed(java.lang.Object)
				 */
				public void inputPerformed(BigDecimal value) {
					tableModel.setHours(value, columns);
				}
			});
		}
	}

	/**
	 * Обработчик пункта КМ "Заполнить по шаблону"
	 * @param event - событие
	 */
	public void onActionFillByPattern(WidgetEvent event) {
		SecurityUtils.checkPermission(new BusinessMethodPermission(ScheduleHeadServiceLocal.SERVICE_NAME, ScheduleHeadServiceLocal.CREATE_BY_PATTERN_METHOD_NAME));
		final FillByPatternScheduleSpecParamsDlg paramsDialog = (FillByPatternScheduleSpecParamsDlg) ApplicationDictionaryLocator.locate().getWindow(FillByPatternScheduleSpecParamsDlg.WINDOW_NAME);
		final ScheduleHead scheduleHead = (ScheduleHead) getEntity();
		paramsDialog.addCancelActionListener(new FormActionListener() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
			 */
			public void actionPerformed(FormEvent event) {
				// do nothing
			}
		});
		paramsDialog.addOkActionListener(new FormActionListener() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
			 */
			public void actionPerformed(FormEvent event) {
				doOnFillByPatternParamsSelected(scheduleHead, paramsDialog.getPattern(), paramsDialog.getOffset(), paramsDialog.getDateFrom(), paramsDialog.getDateTill());
			}
		});
		if(scheduleHead != null)
			paramsDialog.setPattern(scheduleHead.getDefaultPatternHead());

		CalcPeriod calcPeriodEntity = getCurrentCalcPeriod();
		if(calcPeriodEntity != null) {
			paramsDialog.setDateFrom(calcPeriodEntity.getBeginDate());
			paramsDialog.setDateTill(calcPeriodEntity.getEndDate());
		}
		paramsDialog.setOffset(0);
		paramsDialog.execute();
	}

	/**
	 * Обработчик события "Выбора параметров заполнения спецификации по шаблону"
	 */
	protected void doOnFillByPatternParamsSelected(ScheduleHead scheduleHead, PatternHead patternHead, Integer initialShift, Date beginDate, Date endDate) {
		CalcPeriod calcPeriodEntity = getCurrentCalcPeriod();
		getTableModel().fillGrid(scheduleHead, calcPeriodEntity.getBeginDate(), calcPeriodEntity.getEndDate(), scheduleHeadService.createByPattern(scheduleHead, patternHead, initialShift, beginDate, endDate), timeBoardHelper.getTimeKinds());
	}

	private CalcPeriod getCurrentCalcPeriod() {
		return calcPeriodService.load((Integer) ((DefaultMaintenanceEJBQLTableModel) calcPeriod.getModel()).getSelectedPrimaryKeys()[0]);
	}

	private int[] getSelectedColumns() {
		return ((Table) view.getWidget(SPEC_TABLE_WIDGET_NAME)).getSelectedColumns(); 
	}

	private ScheduleSpecTableModel getTableModel() {
		return (ScheduleSpecTableModel) specTable.getModel();
	}

	private void adjustCalcPeriodPopupMenu() {
		view.getWidget(CALC_PERIOD_WIDGET_NAME).setReadOnly(true);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
	}

}
