/*
 * ManualDistributionDlg.java
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
package com.mg.merp.contract.support.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.Dialogs;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.contract.model.ItemKind;
import com.mg.merp.contract.model.ManualDistributionData;
import com.mg.merp.contract.model.PhasePlanItem;
import com.mg.merp.contract.support.Messages;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер диалога "Распределение фактичеcкой суммы"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ManualDistributionDlg.java,v 1.3 2007/03/09 06:42:37 sharapov Exp $
 */
public class ManualDistributionDlg extends AbstractForm {

	// Fields

	private DefaultTableController distributionTable;
	private List<DistributionListItem> distributionList = new ArrayList<DistributionListItem>();
	private String[] columnsName;
	private ManualDistributionData[] manualDistributionItems;

	private Integer planItemSelectedIndex;
	private BigDecimal distributionSum;

	class DistributionListItem {
		Integer id;
		Date beginActionDate;
		Date endActionDate;
		String contractorFrom;
		String contractorTo;
		BigDecimal planSum;
		BigDecimal factSum;
		BigDecimal distSum;

		public DistributionListItem(Integer id, Date beginActionDate, Date endActionDate, String contractorFrom, String contractorTo, BigDecimal planSum, BigDecimal factSum) {
			this.id = id;
			this.beginActionDate = beginActionDate;
			this.endActionDate = endActionDate;
			this.contractorFrom = contractorFrom;
			this.contractorTo = contractorTo;
			this.planSum = planSum;
			this.factSum = factSum;
			this.distSum = new BigDecimal(0);
		}
	}

	/* Default constructor */
	public ManualDistributionDlg () {

		columnsName = getColumnNames();
		distributionTable = new DefaultTableController(new AbstractTableModel() {

			public int getColumnCount() {
				return columnsName.length;
			}

			public String getColumnName(int column) {
				return columnsName[column];
			}

			public int getRowCount() {
				return distributionList.size();
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
			 */
			public Object getValueAt(int row, int column) {
				DistributionListItem item = distributionList.get(row);

				switch (column) {
				case 0: return item.id;
				case 1: return String.format("%1$td.%1tm.%1$tY", item.beginActionDate); //$NON-NLS-1$
				case 2: return String.format("%1$td.%1tm.%1$tY", item.endActionDate); //$NON-NLS-1$
				case 3: return item.contractorFrom;
				case 4: return item.contractorTo;
				case 5: return item.planSum;
				case 6: return item.factSum;
				case 7: return item.distSum;
				}
				return null;
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#isCellEditable(int, int)
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					planItemSelectedIndex = null;
				else 
					planItemSelectedIndex = rows[0];
			}
		});
	}

	// Methods

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		refreshTable();
	}

	/**
	 * Обработка события "Выполнить распределение"
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionPerformDistribution(WidgetEvent event) throws Exception {
		if(planItemSelectedIndex != null) {
			BigDecimal manualDistSum = distributionList.get(planItemSelectedIndex).planSum.subtract(distributionList.get(planItemSelectedIndex).factSum);
			BigDecimal sum = new BigDecimal(0);

			if(distributionList.get(planItemSelectedIndex).distSum.equals(new BigDecimal(0)))
				sum = manualDistSum.compareTo(distributionSum) > 0 ? distributionSum : manualDistSum;
				else
					sum = distributionList.get(planItemSelectedIndex).distSum;

			Dialogs.inputQuery(Messages.getInstance().getMessage(Messages.MANUAL_DISTRIBUTION_TITLE), Messages.getInstance().getMessage(Messages.MANUAL_DISTRIBUTION_FIELD_NAME), sum, new Dialogs.InputQueryDialogListener<BigDecimal>() {

				public void inputPerformed(BigDecimal value) {
					doDistributeItem(planItemSelectedIndex, value);
					refreshModel();
				}

				public void inputCanceled() {
					// Do nothing
				}

			});
		}
	}

	private void doDistributeItem(Integer planItemSelectedIndex, BigDecimal manualDistSum) {
		if(distributionSum.compareTo(new BigDecimal(0)) > 0) {
			if(distributionSum.compareTo(manualDistSum) >= 0) {
				distributionList.get(planItemSelectedIndex).distSum = manualDistSum;
				distributionSum = distributionSum.subtract(manualDistSum);
			}
			else {
				distributionList.get(planItemSelectedIndex).distSum = distributionSum;
				distributionSum = new BigDecimal(0);
			}
			refreshTable();
		}
	}

	/**
	 * Обработка события "Отменить распределение" 
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionCancelDistribution(WidgetEvent event) throws Exception {
		if(planItemSelectedIndex != null)
			doCancelDistributeItem(planItemSelectedIndex);
	}

	private void doCancelDistributeItem(Integer planItemSelectedIndex) {
		distributionSum = distributionSum.add(distributionList.get(planItemSelectedIndex).distSum);
		distributionList.get(planItemSelectedIndex).distSum = new BigDecimal(0);
		refreshTable();
	}

	/**
	 * Обработка события нажатия кнопки "OK"
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionOk(WidgetEvent event) throws Exception {
		doDistributeItems();
		closeDialog();
	}

	/**
	 * Собрать информацию о выполненом распределении позиций
	 */
	private void doDistributeItems() {
		manualDistributionItems = new ManualDistributionData[getDisributedItemsCount()];
		int counter = 0;
		for(DistributionListItem item : distributionList)
			if(item.distSum.compareTo(new BigDecimal(0)) > 0) // собираем только распределённые позиции
				manualDistributionItems[counter++] = new ManualDistributionData(item.id, item.distSum);
	}

	/**
	 * Получить количество распределенных позиций (т.е. те позиции у которых распределенная сумма > 0)
	 * @return количество
	 */
	private int getDisributedItemsCount() {
		int counter = 0;
		for(DistributionListItem item : distributionList)
			if(item.distSum.compareTo(new BigDecimal(0)) > 0)
				counter++;
		return counter;
	}

	/**
	 * Установить параметры запуска
	 * @param docHead - документ
	 * @param kind - тип пункта
	 * @param distributionSum - распределяемая сумма
	 */
	public void setParams(DocHead docHead, ItemKind kind, BigDecimal distributionSum) {
		this.distributionSum = distributionSum;
		createTableModel(docHead, kind);
	}

	private void createTableModel(DocHead docHead, ItemKind kind) {
		Projection projection = Projections.projectionList(
				Projections.property("Id"),  //$NON-NLS-1$
				Projections.property("BeginActionDate"),  //$NON-NLS-1$
				Projections.property("EndActionDate"),  //$NON-NLS-1$
				Projections.property("From"),  //$NON-NLS-1$
				Projections.property("To"),  //$NON-NLS-1$
				Projections.property("PlanSum"),  //$NON-NLS-1$
				Projections.property("FactSum"));  //$NON-NLS-1$

		distributionList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PhasePlanItem.class)
				.setProjection(projection)
				.createAlias("ContractPhase", "p")  //$NON-NLS-1$ //$NON-NLS-2$
				.add(Restrictions.eq("Kind", kind)) //$NON-NLS-1$
				.add(Restrictions.eq("p.DocHead", docHead)) //$NON-NLS-1$
				.add(Restrictions.gtProperty("PlanSum", "FactSum")) //$NON-NLS-1$ //$NON-NLS-2$
				.setResultTransformer(new ResultTransformer<DistributionListItem>() {

					public DistributionListItem transformTuple(Object[] tuple, String[] aliases) {
						String contractorFromCode = null;
						String contractorToCode = null;
						if(((Contractor) tuple[3]) != null)
							contractorFromCode = ((Contractor) tuple[3]).getCode();
						if(((Contractor) tuple[4]) != null)
							contractorToCode = ((Contractor) tuple[4]).getCode();
						return new DistributionListItem(
								(Integer) tuple[0], 
								(Date) tuple[1], 
								(Date) tuple[2], 
								(String) contractorFromCode, 
								(String) contractorToCode, 
								(BigDecimal) tuple[5],
								(BigDecimal) tuple[6]);
					}
				}));
	}

	private String[] getColumnNames() {
		String[] columnNames = new String[8];
		columnNames[0] = getHeaderName("ID"); //$NON-NLS-1$
		columnNames[1] = getHeaderName("Contract.ItemnPlan.BeginActionDate"); //$NON-NLS-1$
		columnNames[2] = getHeaderName("Contract.ItemnPlan.EndActionDate"); //$NON-NLS-1$
		columnNames[3] = getHeaderName("Contract.ItemPan.From"); //$NON-NLS-1$
		columnNames[4] = getHeaderName("Contract.ItemPan.To"); //$NON-NLS-1$
		columnNames[5] = getHeaderName("Contract.ItemnPlan.PlanSum"); //$NON-NLS-1$
		columnNames[6] = getHeaderName("Contract.ItemnPlan.FactSum"); //$NON-NLS-1$
		columnNames[7] = getHeaderName("Contract.ItemFact.Distsum"); //$NON-NLS-1$
		return columnNames;
	}

	private String getHeaderName(String dataItemName) {
		return ApplicationDictionaryLocator.locate().getDataItem(dataItemName).getHeader();
	}


	public void onActionCancel(WidgetEvent event) throws Exception {
		closeDialog();
	}

	private void closeDialog() {
		super.close();
	}

	private void refreshModel() {
		view.flushModel();	
	}

	private void refreshTable() {
		((AbstractTableModel) distributionTable.getModel()).fireModelChange();
	}

	// Property accessors

	/**
	 * @return the distributionSum
	 */
	public BigDecimal getDistributionSum() {
		return distributionSum;
	}

	/**
	 * @param distributionSum the distributionSum to set
	 */
	public void setDistributionSum(BigDecimal distributionSum) {
		this.distributionSum = distributionSum;
	}

	/**
	 * @return the manualDistributionItems
	 */
	public ManualDistributionData[] getManualDistributionItems() {
		return manualDistributionItems;
	}

}
