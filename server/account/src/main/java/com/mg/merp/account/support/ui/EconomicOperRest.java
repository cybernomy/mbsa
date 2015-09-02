/*
 * EconomicOperRest.java
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.SpecMark;
import com.mg.merp.account.support.Messages;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер формы условий отбора хозяйственных операций
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: EconomicOperRest.java,v 1.5 2009/03/11 13:40:30 sharapov Exp $
 */
public class EconomicOperRest extends DefaultHierarhyRestrictionForm {

	@DataItemName("Reference.Cond.DateFrom")
	private Date dateFrom = null;
	@DataItemName("Reference.Cond.DateTill")	
	private Date dateTill = null;
	private Catalog catalogCode = null;
	private CatalogFolder catalogFolder = null;	
	@DataItemName("Document.From")
	private Contractor fromCode = null;
	@DataItemName("Document.To")
	private Contractor toCode = null;	
	@DataItemName("Document.Cond.SumNatMin")
	private BigDecimal fromSum = null;
	@DataItemName("Document.Cond.SumNatMax")
	private BigDecimal toSum = null;	
	@DataItemName("Document.DocDate")	
	private Date contractDate = null;
	@DataItemName("Document.DocNumber")
	private String contractNumber = "";	
	@DataItemName("Account.EconOper.DocType")
	private DocType contractType = null;	
	@DataItemName("Account.EconOper.DocType")
	private DocType baseDocType = null;
	@DataItemName("Document.DocDate")
	private Date baseDocDate = null;
	@DataItemName("Document.DocNumber")
	private String baseDocNumber = "";	
	@DataItemName("Account.EconOper.DocType")
	private DocType docType = null;	
	@DataItemName("Document.DocDate")
	private Date docDate = null;
	@DataItemName("Document.DocNumber")
	private String docNumber = "";	
	private SpecMark specMark = null; 
	@DataItemName("Account.EconSpec.AccDb")	
	private AccPlan AccDb = null;
	@DataItemName("Account.EconSpec.AnlDb1")
	private AnlPlan anl1Db1Code = null;
	@DataItemName("Account.EconSpec.AnlDb2")	
	private AnlPlan anl1Db2Code = null;
	@DataItemName("Account.EconSpec.AnlDb3")	
	private AnlPlan anl1Db3Code = null;
	@DataItemName("Account.EconSpec.AnlDb4")	
	private AnlPlan anl1Db4Code = null;
	@DataItemName("Account.EconSpec.AnlDb5")	
	private AnlPlan anl1Db5Code = null;
	@DataItemName("Account.EconSpec.AccKt")
	private AccPlan AccKt = null;
	@DataItemName("Account.EconSpec.AnlKt1")		
	private AnlPlan anl1Kt1Code = null;
	@DataItemName("Account.EconSpec.AnlKt2")		
	private AnlPlan anl1Kt2Code = null;
	@DataItemName("Account.EconSpec.AnlKt3")		
	private AnlPlan anl1Kt3Code = null;
	@DataItemName("Account.EconSpec.AnlKt4")		
	private AnlPlan anl1Kt4Code = null;
	@DataItemName("Account.EconSpec.AnlKt5")		
	private AnlPlan anl1Kt5Code = null;

	protected DefaultTableController tableDb = null;
	private List<AccountTableModelItem> tableModelItemListDb;

	protected DefaultTableController tableKt = null;
	private List<AccountTableModelItem> tableModelItemListKt;

	protected DefaultTableController tableAnlDb1 = null;
	protected DefaultTableController tableAnlDb2 = null;
	protected DefaultTableController tableAnlDb3 = null;
	protected DefaultTableController tableAnlDb4 = null;
	protected DefaultTableController tableAnlDb5 = null;

	protected DefaultTableController tableAnlKt1 = null;
	protected DefaultTableController tableAnlKt2 = null;
	protected DefaultTableController tableAnlKt3 = null;
	protected DefaultTableController tableAnlKt4 = null;
	protected DefaultTableController tableAnlKt5 = null;

	private List<AccountTableModelItem> tableModelItemListAnlDb1 = new ArrayList<AccountTableModelItem>();
	private List<AccountTableModelItem> tableModelItemListAnlDb2 = new ArrayList<AccountTableModelItem>();
	private List<AccountTableModelItem> tableModelItemListAnlDb3 = new ArrayList<AccountTableModelItem>();
	private List<AccountTableModelItem> tableModelItemListAnlDb4 = new ArrayList<AccountTableModelItem>();
	private List<AccountTableModelItem> tableModelItemListAnlDb5 = new ArrayList<AccountTableModelItem>();

	private List<AccountTableModelItem> tableModelItemListAnlKt1 = new ArrayList<AccountTableModelItem>();
	private List<AccountTableModelItem> tableModelItemListAnlKt2 = new ArrayList<AccountTableModelItem>();
	private List<AccountTableModelItem> tableModelItemListAnlKt3 = new ArrayList<AccountTableModelItem>();
	private List<AccountTableModelItem> tableModelItemListAnlKt4 = new ArrayList<AccountTableModelItem>();
	private List<AccountTableModelItem> tableModelItemListAnlKt5 = new ArrayList<AccountTableModelItem>();

	private Map<Integer, Integer> selectedStorageAccDb = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> selectedStorageAccKt = new HashMap<Integer, Integer>();

	private Map<Integer, Integer> selectedStorageAnlDb = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> selectedStorageAnlKt = new HashMap<Integer, Integer>();

	private Map<Integer, Short> selectedStorageAnlDbLevel = new HashMap<Integer, Short>();
	private Map<Integer, Short> selectedStorageAnlKtLevel = new HashMap<Integer, Short>();

	private Map<Integer, IndexData> indexAnlDb = new HashMap<Integer, IndexData>();
	private Map<Integer, IndexData> indexAnlKt = new HashMap<Integer, IndexData>();

	private List<Integer> selectedDbAnl1 = new ArrayList<Integer>();
	private List<Integer> selectedDbAnl2 = new ArrayList<Integer>();
	private List<Integer> selectedDbAnl3 = new ArrayList<Integer>();
	private List<Integer> selectedDbAnl4 = new ArrayList<Integer>();
	private List<Integer> selectedDbAnl5 = new ArrayList<Integer>();

	private List<Integer> selectedKtAnl1 = new ArrayList<Integer>();
	private List<Integer> selectedKtAnl2 = new ArrayList<Integer>();
	private List<Integer> selectedKtAnl3 = new ArrayList<Integer>();
	private List<Integer> selectedKtAnl4 = new ArrayList<Integer>();
	private List<Integer> selectedKtAnl5 = new ArrayList<Integer>();


	public EconomicOperRest() {
		super();
		String[] accountColumnNames = initColumnNames();
		loadAccTabelsData();

		tableDb = new DefaultTableController(new AccountTableModel(tableModelItemListDb, accountColumnNames, 
				new SelectRowListener() {

			/* (non-Javadoc)
			 * @see com.mg.merp.account.support.ui.EconomicOperRest.SelectRowListener#rowSelected(java.lang.Object)
			 */
			public void rowSelected(AccountTableModelItem selectedRow) {
				loadAnlTablesData(selectedRow, true);
			}
		}, new AccRowChangeListener(true)));

		tableKt = new DefaultTableController(new AccountTableModel(tableModelItemListKt, accountColumnNames, 
				new SelectRowListener() {

			/* (non-Javadoc)
			 * @see com.mg.merp.account.support.ui.EconomicOperRest.SelectRowListener#rowSelected(java.lang.Object)
			 */
			public void rowSelected(AccountTableModelItem selectedRow) {
				loadAnlTablesData(selectedRow, false);
			}
		}, new AccRowChangeListener(false)));

		AnlRowChangeListener anlDbRowChangeListener = new AnlRowChangeListener(true);
		tableAnlDb1 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlDb1, accountColumnNames, anlDbRowChangeListener));
		tableAnlDb2 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlDb2, accountColumnNames, anlDbRowChangeListener));
		tableAnlDb3 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlDb3, accountColumnNames, anlDbRowChangeListener));
		tableAnlDb4 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlDb4, accountColumnNames, anlDbRowChangeListener));
		tableAnlDb5 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlDb5, accountColumnNames, anlDbRowChangeListener));

		AnlRowChangeListener anlKtRowChangeListener = new AnlRowChangeListener(false);
		tableAnlKt1 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlKt1, accountColumnNames, anlKtRowChangeListener));
		tableAnlKt2 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlKt2, accountColumnNames, anlKtRowChangeListener));
		tableAnlKt3 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlKt3, accountColumnNames, anlKtRowChangeListener));
		tableAnlKt4 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlKt4, accountColumnNames, anlKtRowChangeListener));
		tableAnlKt5 = new DefaultTableController(new AccountTableModel(tableModelItemListAnlKt5, accountColumnNames, anlKtRowChangeListener));

		refreshAccTabels();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultRestrictionForm#doClearRestrictionItem()
	 */
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.dateFrom = null;		
		this.dateTill = null;
		this.catalogCode = null;
		this.catalogFolder = null;
		this.fromCode = null;
		this.toCode = null;
		this.fromSum = null;	
		this.toSum = null;	
		this.contractDate = null;
		this.contractNumber = StringUtils.EMPTY_STRING;
		this.contractType = null;	
		this.baseDocDate = null;
		this.baseDocNumber = StringUtils.EMPTY_STRING;
		this.baseDocType = null;
		this.docNumber = StringUtils.EMPTY_STRING;
		this.docDate = null;		
		this.docType = null;
		this.specMark = null;	
		this.AccDb = null;
		this.anl1Db1Code = null;
		this.anl1Db2Code = null;
		this.anl1Db3Code = null;
		this.anl1Db4Code = null;
		this.anl1Db5Code = null;	
		this.AccKt = null;
		this.anl1Kt1Code = null;
		this.anl1Kt2Code = null;
		this.anl1Kt3Code = null;
		this.anl1Kt4Code = null;
		this.anl1Kt5Code = null;
		clearCheckedItems();
	}

	/**
	 * Получить список идентификаторов выбранных счетов по дебету
	 * @return список идентификаторов выбранных счетов по дебету
	 */
	public Set<Integer> getSelectedAccDbIds() {
		return selectedStorageAccDb.keySet();
	}

	/**
	 * Получить список идентификаторов выбранных счетов по кредиту
	 * @return список идентификаторов выбранных счетов по кредиту
	 */
	public Set<Integer> getSelectedAccKtIds() {
		return selectedStorageAccKt.keySet();
	}

	/**
	 * Получить список идентификаторов аналитик 1-го уровня по дебету
	 * @return список идентификаторов аналитик 1-го уровня по дебету
	 */
	public List<Integer> getSelectedDbAnl1Ids() {
		return selectedDbAnl1;
	}

	/**
	 * Получить список идентификаторов аналитик 2-го уровня по дебету
	 * @return список идентификаторов аналитик 2-го уровня по дебету
	 */
	public List<Integer> getSelectedDbAnl2Ids() {
		return selectedDbAnl2;
	}

	/**
	 * Получить список идентификаторов аналитик 3-го уровня по дебету
	 * @return список идентификаторов аналитик 3-го уровня по дебету
	 */
	public List<Integer> getSelectedDbAnl3Ids() {
		return selectedDbAnl3;
	}

	/**
	 * Получить список идентификаторов аналитик 4-го уровня по дебету
	 * @return список идентификаторов аналитик 4-го уровня по дебету
	 */
	public List<Integer> getSelectedDbAnl4Ids() {
		return selectedDbAnl4;
	}

	/**
	 * Получить список идентификаторов аналитик 5-го уровня по дебету
	 * @return список идентификаторов аналитик 5-го уровня по дебету
	 */
	public List<Integer> getSelectedDbAnl5Ids() {
		return selectedDbAnl5;
	}

	/**
	 * Получить список идентификаторов аналитик 1-го уровня по кредиту
	 * @return список идентификаторов аналитик 1-го уровня по кредиту
	 */
	public List<Integer> getSelectedKtAnl1Ids() {
		return selectedKtAnl1;
	}

	/**
	 * Получить список идентификаторов аналитик 2-го уровня по кредиту
	 * @return список идентификаторов аналитик 2-го уровня по кредиту
	 */
	public List<Integer> getSelectedKtAnl2Ids() {
		return selectedKtAnl2;
	}

	/**
	 * Получить список идентификаторов аналитик 3-го уровня по кредиту
	 * @return список идентификаторов аналитик 3-го уровня по кредиту
	 */
	public List<Integer> getSelectedKtAnl3Ids() {
		return selectedKtAnl3;
	}

	/**
	 * Получить список идентификаторов аналитик 4-го уровня по кредиту
	 * @return список идентификаторов аналитик 4-го уровня по кредиту
	 */
	public List<Integer> getSelectedKtAnl4Ids() {
		return selectedKtAnl4;
	}

	/**
	 * Получить список идентификаторов аналитик 5-го уровня по кредиту
	 * @return список идентификаторов аналитик 5-го уровня по кредиту
	 */
	public List<Integer> getSelectedKtAnl5Ids() {
		return selectedKtAnl5;
	}

	/**
	 * Обработчик кнопки "Очистить выбранные счета по дебету"
	 * @param event - событие
	 */
	public void onActionClearDb(WidgetEvent event) {
		unCheckCurrentAnl(((AccountTableModel) tableDb.getModel()).getSelectedItem().getId(), true);
		unCheckAllAcc(true);
	}

	/**
	 * Обработчик кнопки "Очистить выбранные счета по кредиту"
	 * @param event - событие
	 */
	public void onActionClearKt(WidgetEvent event) {
		unCheckCurrentAnl(((AccountTableModel) tableKt.getModel()).getSelectedItem().getId(), false);
		unCheckAllAcc(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	public void onActionOk(WidgetEvent event) {
		prepareSelectedDbAnlIds();
		prepareSelectedKtAnlIds();
		super.onActionOk(event);
	}

	private void unCheckAllAcc(boolean isDbMode) {
		Collection<Integer> values = isDbMode ? selectedStorageAccDb.values() : selectedStorageAccKt.values(); 
		for (Integer value : values) {
			AccountTableModelItem modelItem = isDbMode ? tableModelItemListDb.get(value) : tableModelItemListKt.get(value);
			if (modelItem != null) {
				modelItem.setSelected(false);
				unCheckCell(isDbMode ? tableDb : tableKt, value);
			}
		}
		invalidateSelectedStorages(isDbMode);
	}

	private void invalidateSelectedStorages(boolean isDbMode) {
		if (isDbMode) {
			selectedStorageAccDb.clear();
			selectedStorageAnlDb.clear();
			selectedStorageAnlDbLevel.clear();
		} else {
			selectedStorageAccKt.clear();
			selectedStorageAnlKt.clear();
			selectedStorageAnlKtLevel.clear();
		}
	}

	private void unCheckAnl(Integer masterId, boolean isDbMode) {
		Iterator<Integer> valueIterator = isDbMode ? selectedStorageAnlDb.values().iterator() : selectedStorageAnlKt.values().iterator();
		while (valueIterator.hasNext()) {
			Integer anlId = valueIterator.next();
			if (masterId.equals(anlId))
				valueIterator.remove();
		}
	}

	private void unCheckCurrentAnl(Integer masterDbId, boolean isDbMode) {
		Set<Map.Entry<Integer, Integer>> entrySet = isDbMode ? selectedStorageAnlDb.entrySet() : selectedStorageAnlKt.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			if (masterDbId.equals(entry.getValue())) {
				if (isDbMode)
					unCheckAnlDb(entry.getKey());
				else
					unCheckAnlKt(entry.getKey());
			}
		}
		unCheckAnl(masterDbId, isDbMode);
	}

	private void unCheckAnlDb(Integer anlDbId) {
		IndexData indexData = indexAnlDb.get(anlDbId);
		if (indexData != null) {
			if (indexData.getAnlLvel() == 1)
				unCheckByLevel(tableAnlDb1, tableModelItemListAnlDb1, indexData);
			if (indexData.getAnlLvel() == 2)
				unCheckByLevel(tableAnlDb2, tableModelItemListAnlDb2, indexData);
			if (indexData.getAnlLvel() == 3)
				unCheckByLevel(tableAnlDb3, tableModelItemListAnlDb3, indexData);
			if (indexData.getAnlLvel() == 4)
				unCheckByLevel(tableAnlDb4, tableModelItemListAnlDb4, indexData);
			if (indexData.getAnlLvel() == 5)
				unCheckByLevel(tableAnlDb5, tableModelItemListAnlDb5, indexData);
		}
	}

	private void unCheckAnlKt(Integer anlKtId) {
		IndexData indexData = indexAnlKt.get(anlKtId);
		if (indexData != null) {
			if (indexData.getAnlLvel() == 1)
				unCheckByLevel(tableAnlKt1, tableModelItemListAnlKt1, indexData);
			if (indexData.getAnlLvel() == 2)
				unCheckByLevel(tableAnlKt2, tableModelItemListAnlKt2, indexData);
			if (indexData.getAnlLvel() == 3)
				unCheckByLevel(tableAnlKt3, tableModelItemListAnlKt3, indexData);
			if (indexData.getAnlLvel() == 4)
				unCheckByLevel(tableAnlKt4, tableModelItemListAnlKt4, indexData);
			if (indexData.getAnlLvel() == 5)
				unCheckByLevel(tableAnlKt5, tableModelItemListAnlKt5, indexData);
		}
	}

	private void unCheckByLevel(DefaultTableController table, List<AccountTableModelItem> tableModelItemList, IndexData indexData) {
		if (indexData != null) {
			AccountTableModelItem tableModelItem = tableModelItemList.get(indexData.getListIndex());
			if (tableModelItem != null) {
				tableModelItem.setSelected(false);
				unCheckCell(table, tableModelItem.getIndex());
			}
		}
	}

	private void unCheckCell(DefaultTableController tableController, int row) {
		((AccountTableModel) tableController.getModel()).fireTableCellUpdated(row, AccountTableModel.SELECTED_COLUMN);
	}

	private void loadAccTabelsData() {
		tableModelItemListDb = OrmTemplate.getInstance().findByNamedParam(String.format("SELECT a.Id, a.Acc, a.AccName FROM com.mg.merp.account.model.AccPlan AS a WHERE %s ORDER BY a.Acc", DatabaseUtils.generateFlatBrowseWhereEJBQL("a.Folder.Id", 0)), 
				new String[]{}, new Object[]{}, 
				new ResultTransformer<AccountTableModelItem>() {

			private int index = 0;

			/* (non-Javadoc)
			 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
			 */
			public AccountTableModelItem transformTuple(Object[] tuple, String[] aliases) {
				return new AccountTableModelItem((Integer) tuple[0], (String) tuple[1], (String) tuple[2], null, index++);
			}
		});

		tableModelItemListKt = new ArrayList<AccountTableModelItem>(tableModelItemListDb.size());
		for (AccountTableModelItem accountTableModelItem : tableModelItemListDb)
			tableModelItemListKt.add(accountTableModelItem.clone());
	}

	private void loadAnlTablesData(AccountTableModelItem master, boolean isDbMode) {
		List<AccountTableModelItem> anlItemList = OrmTemplate.getInstance().findByNamedParam("SELECT ap.Id, ap.Code, ap.AnlName, ap.AnlLevel FROM com.mg.merp.account.model.AnlPlan AS ap WHERE ap.AccPlan.Id = :accPlanId", 
				new String[] {"accPlanId"}, new Object[] {master.getId()}, 
				new ResultTransformer<AccountTableModelItem>() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
			 */
			public AccountTableModelItem transformTuple(Object[] tuple, String[] aliases) {
				return new AccountTableModelItem((Integer) tuple[0], (String) tuple[1], (String) tuple[2], (Short) tuple[3]);
			}
		});
		if (isDbMode)
			loadAnlDbTablesData(master, anlItemList);
		else
			loadAnlKtTablesData(master, anlItemList);
	}

	private void loadAnlDbTablesData(AccountTableModelItem master, List<AccountTableModelItem> anlItemList) {
		boolean isDbMode = true;
		resetAnlTablesData(isDbMode);
		int anlIndex1 = 0;
		int anlIndex2 = 0;
		int anlIndex3 = 0;
		int anlIndex4 = 0;
		int anlIndex5 = 0;
		for (AccountTableModelItem anlItem : anlItemList) {
			Integer masterAccPlanId = selectedStorageAnlDb.get(anlItem.getId());
			boolean isSelected = master.getId().equals(masterAccPlanId);
			anlItem.setSelected(isSelected);
			if (anlItem.getLevel() == 1)
				tableModelItemListAnlDb1.add(adjustAccountTableModelItem(anlItem, anlIndex1++, isDbMode));
			if (anlItem.getLevel() == 2)
				tableModelItemListAnlDb2.add(adjustAccountTableModelItem(anlItem, anlIndex2++, isDbMode));
			if (anlItem.getLevel() == 3)
				tableModelItemListAnlDb3.add(adjustAccountTableModelItem(anlItem, anlIndex3++, isDbMode));
			if (anlItem.getLevel() == 4)
				tableModelItemListAnlDb4.add(adjustAccountTableModelItem(anlItem, anlIndex4++, isDbMode));
			if (anlItem.getLevel() == 5)
				tableModelItemListAnlDb5.add(adjustAccountTableModelItem(anlItem, anlIndex5++, isDbMode));
		}
		refreshAnlTablesData(isDbMode);
	}

	private void loadAnlKtTablesData(AccountTableModelItem master, List<AccountTableModelItem> anlItemList) {
		boolean isDbMode = false;
		resetAnlTablesData(isDbMode);
		int anlIndex1 = 0;
		int anlIndex2 = 0;
		int anlIndex3 = 0;
		int anlIndex4 = 0;
		int anlIndex5 = 0;
		for (AccountTableModelItem anlItem : anlItemList) {
			Integer masterAccPlanId = selectedStorageAnlKt.get(anlItem.getId());
			boolean isSelected = master.getId().equals(masterAccPlanId);
			anlItem.setSelected(isSelected);
			if (anlItem.getLevel() == 1)
				tableModelItemListAnlKt1.add(adjustAccountTableModelItem(anlItem, anlIndex1++, isDbMode));
			if (anlItem.getLevel() == 2)
				tableModelItemListAnlKt2.add(adjustAccountTableModelItem(anlItem, anlIndex2++, isDbMode));
			if (anlItem.getLevel() == 3)
				tableModelItemListAnlKt3.add(adjustAccountTableModelItem(anlItem, anlIndex3++, isDbMode));
			if (anlItem.getLevel() == 4)
				tableModelItemListAnlKt4.add(adjustAccountTableModelItem(anlItem, anlIndex4++, isDbMode));
			if (anlItem.getLevel() == 5)
				tableModelItemListAnlKt5.add(adjustAccountTableModelItem(anlItem, anlIndex5++, isDbMode));
		}
		refreshAnlTablesData(isDbMode);
	}

	private void resetAnlTablesData(boolean isDbMode) {
		if (isDbMode) {
			tableModelItemListAnlDb1.clear();
			tableModelItemListAnlDb2.clear();
			tableModelItemListAnlDb3.clear();
			tableModelItemListAnlDb4.clear();
			tableModelItemListAnlDb5.clear();
		} else {
			tableModelItemListAnlKt1.clear();
			tableModelItemListAnlKt2.clear();
			tableModelItemListAnlKt3.clear();
			tableModelItemListAnlKt4.clear();
			tableModelItemListAnlKt5.clear();
		}
	}

	private void refreshAnlTablesData(boolean isDbMode) {
		if (isDbMode) {
			((AccountTableModel) tableAnlDb1.getModel()).refresh(tableModelItemListAnlDb1);
			((AccountTableModel) tableAnlDb2.getModel()).refresh(tableModelItemListAnlDb2);
			((AccountTableModel) tableAnlDb3.getModel()).refresh(tableModelItemListAnlDb3);
			((AccountTableModel) tableAnlDb4.getModel()).refresh(tableModelItemListAnlDb4);
			((AccountTableModel) tableAnlDb5.getModel()).refresh(tableModelItemListAnlDb5);
		} else {
			((AccountTableModel) tableAnlKt1.getModel()).refresh(tableModelItemListAnlKt1);
			((AccountTableModel) tableAnlKt2.getModel()).refresh(tableModelItemListAnlKt2);
			((AccountTableModel) tableAnlKt3.getModel()).refresh(tableModelItemListAnlKt3);
			((AccountTableModel) tableAnlKt4.getModel()).refresh(tableModelItemListAnlKt4);
			((AccountTableModel) tableAnlKt5.getModel()).refresh(tableModelItemListAnlKt5);
		}
	}

	private AccountTableModelItem adjustAccountTableModelItem(AccountTableModelItem anlItem, int index, boolean isDbMode) {
		if (isDbMode)
			indexAnlDb.put(anlItem.getId(), new IndexData(index, anlItem.getLevel()));
		else
			indexAnlKt.put(anlItem.getId(), new IndexData(index, anlItem.getLevel()));
		anlItem.setIndex(index);
		return anlItem;
	}

	private void refreshAccTabels() {
		((AccountTableModel) tableDb.getModel()).fireModelChange();
		((AccountTableModel) tableKt.getModel()).fireModelChange();
	}

	private void prepareSelectedDbAnlIds() {
		clearSelectedAnlIds(true);
		Set<Map.Entry<Integer, Short>> entrySet = selectedStorageAnlDbLevel.entrySet();
		for (Entry<Integer, Short> entry : entrySet) {
			Integer anlId = entry.getKey();
			Short anlLevel = entry.getValue();
			if (anlLevel == 1)
				selectedDbAnl1.add(anlId);
			if (anlLevel == 2)
				selectedDbAnl2.add(anlId);
			if (anlLevel == 3)
				selectedDbAnl3.add(anlId);
			if (anlLevel == 4)
				selectedDbAnl4.add(anlId);
			if (anlLevel == 5)
				selectedDbAnl5.add(anlId);
		}
	}

	private void prepareSelectedKtAnlIds() {
		clearSelectedAnlIds(false);
		Set<Map.Entry<Integer, Short>> entrySet = selectedStorageAnlKtLevel.entrySet();
		for (Entry<Integer, Short> entry : entrySet) {
			Integer anlId = entry.getKey();
			Short anlLevel = entry.getValue();
			if (anlLevel == 1)
				selectedKtAnl1.add(anlId);
			if (anlLevel == 2)
				selectedKtAnl2.add(anlId);
			if (anlLevel == 3)
				selectedKtAnl3.add(anlId);
			if (anlLevel == 4)
				selectedKtAnl4.add(anlId);
			if (anlLevel == 5)
				selectedKtAnl5.add(anlId);
		}
	}

	private void clearSelectedAnlIds(boolean isDbMode) {
		if (isDbMode) {
			selectedDbAnl1.clear();
			selectedDbAnl2.clear();
			selectedDbAnl3.clear();
			selectedDbAnl4.clear();
			selectedDbAnl5.clear();
		} else {
			selectedKtAnl1.clear();
			selectedKtAnl2.clear();
			selectedKtAnl3.clear();
			selectedKtAnl4.clear();
			selectedKtAnl5.clear();
		}
	}

	private void clearCheckedItems() {
		unCheckCurrentAnl(((AccountTableModel) tableDb.getModel()).getSelectedItem().getId(), true);
		unCheckCurrentAnl(((AccountTableModel) tableKt.getModel()).getSelectedItem().getId(), false);
		unCheckAllAcc(true);
		unCheckAllAcc(false);
	}

	private String[] initColumnNames() {
		Messages msg = Messages.getInstance();
		return new String[] {msg.getMessage(Messages.CODE), msg.getMessage(Messages.NAME), msg.getMessage(Messages.SELECTED)};
	}

	private class AccountTableModel extends AbstractTableModel {

		public static final int SELECTED_COLUMN = 2; 
		private String[] columnNames = null;
		private List<AccountTableModelItem> tableModelItemList;
		private SelectRowListener selectRowListener = null;
		private RowChangeListener checkedListener = null;
		private AccountTableModelItem selectedItem = null;

		public AccountTableModel(List<AccountTableModelItem> tableModelItemList, String[] columnsNames) {
			this.tableModelItemList = tableModelItemList;
			this.columnNames = columnsNames;
		}

		public AccountTableModel(List<AccountTableModelItem> tableModelItemList, String[] columnsNames, SelectRowListener selectRowListener, RowChangeListener checkedListener) {
			this.tableModelItemList = tableModelItemList;
			this.columnNames = columnsNames;
			this.selectRowListener = selectRowListener;
			this.checkedListener = checkedListener;
		}

		public AccountTableModel(List<AccountTableModelItem> tableModelItemList, String[] columnsNames, RowChangeListener checkedListener) {
			this.tableModelItemList = tableModelItemList;
			this.columnNames = columnsNames;
			this.checkedListener = checkedListener;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
		 */
		public int getColumnCount() {
			return columnNames == null ? 0 : columnNames.length;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
		 */
		public String getColumnName(int column) {
			return columnNames[column];
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
		 */
		public int getRowCount() {
			return tableModelItemList == null ? 0 : tableModelItemList.size();
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int row, int column) {
			AccountTableModelItem item = tableModelItemList.get(row);
			switch (column) {
			case 0:
				return item.getCode();
			case 1:
				return item.getName();
			case SELECTED_COLUMN:
				return item.isSelected();
			default:
				return null;
			}
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setValueAt(java.lang.Object, int, int)
		 */
		public void setValueAt(Object value, int rowIndex, int columnIndex) {
			AccountTableModelItem item = tableModelItemList.get(rowIndex);
			switch (columnIndex) {
			case SELECTED_COLUMN:
				Boolean isSelected = (Boolean) value;
				item.setSelected(isSelected);
				fireRowChanged(item);
			default:
				break;
			}
		}

		private void fireRowChanged(AccountTableModelItem item) {
			if (checkedListener != null)
				checkedListener.rowChanged(item);
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#isCellEditable(int, int)
		 */
		@Override
		public boolean isCellEditable(int row, int column) {
			return column == SELECTED_COLUMN;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int column) {
			if(column == SELECTED_COLUMN)
				return Boolean.class;
			else
				return super.getColumnClass(column);
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
		 */
		@Override
		public void setSelectedRows(int[] rows) {
			if (rows != null && rows.length > 0) {
				AccountTableModelItem item = tableModelItemList.get(rows[0]);
				selectedItem = item;
				fireRowSelected(item);
			}
		}

		private void fireRowSelected(AccountTableModelItem item) {
			if (selectRowListener != null) {
				selectRowListener.rowSelected(item);
			}
		}

		public AccountTableModelItem getSelectedItem() {
			return selectedItem;
		}

		public void refresh(List<AccountTableModelItem> tableModelItemList) {
			this.tableModelItemList = tableModelItemList;
			fireModelChange();
		}


		/**
		 * @return the tableModelItemList
		 */
		public List<AccountTableModelItem> getTableModelItemList() {
			return this.tableModelItemList;
		}

		/**
		 * @param tableModelItemList the tableModelItemList to set
		 */
		public void setTableModelItemList(List<AccountTableModelItem> tableModelItemList) {
			this.tableModelItemList = tableModelItemList;
		}

	}

	private class AnlRowChangeListener implements RowChangeListener {

		private boolean isDbMode = true;

		public AnlRowChangeListener(boolean isDbMode) {
			this.isDbMode = isDbMode;
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.account.support.ui.EconomicOperRest.RowChangeListener#rowChanged(java.lang.Object)
		 */
		public void rowChanged(AccountTableModelItem row) {
			if (row.isSelected()) {
				if (isDbMode) {
					selectedStorageAnlDb.put(row.getId(), ((AccountTableModel) tableDb.getModel()).getSelectedItem().getId());
					selectedStorageAnlDbLevel.put(row.getId(), row.getLevel());
				} else {
					selectedStorageAnlKt.put(row.getId(), ((AccountTableModel) tableKt.getModel()).getSelectedItem().getId());
					selectedStorageAnlKtLevel.put(row.getId(), row.getLevel());
				}
			} else {
				if (isDbMode) {
					selectedStorageAnlDb.remove(row.getId());
					selectedStorageAnlDbLevel.remove(row.getId());
				} else {
					selectedStorageAnlKt.remove(row.getId());
					selectedStorageAnlKtLevel.remove(row.getId());
				}
			}
		}

	}

	private class AccRowChangeListener implements RowChangeListener {

		private boolean isDbMode = true;

		public AccRowChangeListener(boolean isDbMode) {
			this.isDbMode = isDbMode;
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.account.support.ui.EconomicOperRest.RowChangeListener#rowChanged(com.mg.merp.account.support.ui.EconomicOperRest.AccountTableModelItem)
		 */
		public void rowChanged(AccountTableModelItem row) {
			Integer rowId = row.getId();
			if (row.isSelected()) {
				if (isDbMode)
					selectedStorageAccDb.put(rowId, row.getIndex());
				else
					selectedStorageAccKt.put(rowId, row.getIndex());
			} else {
				unCheckAnl(rowId, isDbMode);
				if (isDbMode)
					selectedStorageAccDb.remove(rowId);
				else
					selectedStorageAccKt.remove(rowId);
			}
		}

	}

	private interface SelectRowListener extends EventListener {

		void rowSelected(AccountTableModelItem selectedRow);
	}

	private interface RowChangeListener extends EventListener {

		void rowChanged(AccountTableModelItem row);
	}

	private class IndexData {
		private Integer listIndex;
		private short anlLvel;

		public IndexData(Integer listIndex, short anlLvel) {
			this.listIndex = listIndex;
			this.anlLvel = anlLvel;
		}

		/**
		 * @return the listIndex
		 */
		public Integer getListIndex() {
			return this.listIndex;
		}

		/**
		 * @param listIndex the listIndex to set
		 */
		public void setListIndex(Integer listIndex) {
			this.listIndex = listIndex;
		}

		/**
		 * @return the anlLvel
		 */
		public short getAnlLvel() {
			return this.anlLvel;
		}

		/**
		 * @param anlLvel the anlLvel to set
		 */
		public void setAnlLvel(short anlLvel) {
			this.anlLvel = anlLvel;
		}
	}


	private class AccountTableModelItem {

		private Integer id;
		private String code;
		private String name;
		private boolean isSelected;
		private int index;
		private Short level;

		public AccountTableModelItem(Integer id, String code, String name, Short level) {
			this(id, code, name, level, 0);
		}

		public AccountTableModelItem(Integer id, String code, String name, Short level, int index) {
			this.id = id;
			this.code = code;
			this.name = name;
			this.index = index;
			this.level = level;
		}

		public AccountTableModelItem(Integer id, String code, String name, boolean isSelected, Short level, int index) {
			this(id, code, name, level, index);
			this.isSelected = isSelected;
		}

		/**
		 * @return the id
		 */
		public Integer getId() {
			return this.id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return this.code;
		}

		/**
		 * @param code the code to set
		 */
		public void setCode(String code) {
			this.code = code;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the isSelected
		 */
		public boolean isSelected() {
			return this.isSelected;
		}

		/**
		 * @param isSelected the isSelected to set
		 */
		public void setSelected(boolean isSelected) {
			this.isSelected = isSelected;
		}

		/**
		 * @return the index
		 */
		public int getIndex() {
			return this.index;
		}

		/**
		 * @param index the index to set
		 */
		public void setIndex(int index) {
			this.index = index;
		}

		/**
		 * @return the level
		 */
		public Short getLevel() {
			return this.level;
		}

		/**
		 * @param level the level to set
		 */
		public void setLevel(Short level) {
			this.level = level;
		}

		public AccountTableModelItem clone() {
			return new AccountTableModelItem(this.id, this.code, this.name, this.level, this.index);
		}

	}


	/**
	 * @return Returns the anl1Db1Code.
	 */
	protected AnlPlan getAnl1Db1Code() {
		return anl1Db1Code;
	}

	/**
	 * @return Returns the anl1Db2Code.
	 */
	protected AnlPlan getAnl1Db2Code() {
		return anl1Db2Code;
	}

	/**
	 * @return Returns the anl1Db3Code.
	 */
	protected AnlPlan getAnl1Db3Code() {
		return anl1Db3Code;
	}

	/**
	 * @return Returns the anl1Db4Code.
	 */
	protected AnlPlan getAnl1Db4Code() {
		return anl1Db4Code;
	}

	/**
	 * @return Returns the anl1Db5Code.
	 */
	protected AnlPlan getAnl1Db5Code() {
		return anl1Db5Code;
	}

	/**
	 * @return Returns the anl1Kt1Code.
	 */
	protected AnlPlan getAnl1Kt1Code() {
		return anl1Kt1Code;
	}

	/**
	 * @return Returns the anl1Kt2Code.
	 */
	protected AnlPlan getAnl1Kt2Code() {
		return anl1Kt2Code;
	}

	/**
	 * @return Returns the anl1Kt3Code.
	 */
	protected AnlPlan getAnl1Kt3Code() {
		return anl1Kt3Code;
	}

	/**
	 * @return Returns the anl1Kt4Code.
	 */
	protected AnlPlan getAnl1Kt4Code() {
		return anl1Kt4Code;
	}

	/**
	 * @return Returns the anl1Kt5Code.
	 */
	protected AnlPlan getAnl1Kt5Code() {
		return anl1Kt5Code;
	}

	/**
	 * @return Returns the baseDocDate.
	 */
	protected Date getBaseDocDate() {
		return baseDocDate;
	}

	/**
	 * @return Returns the baseDocNumber.
	 */
	protected String getBaseDocNumber() {
		return baseDocNumber;
	}

	/**
	 * @return Returns the baseDocType.
	 */
	protected DocType getBaseDocType() {
		return baseDocType;
	}

	/**
	 * @return Returns the catalogCode.
	 */
	protected Catalog getCatalogCode() {
		return catalogCode;
	}

	/**
	 * @return Returns the catalogFolder.
	 */
	protected CatalogFolder getCatalogFolder() {
		return catalogFolder;
	}

	/**
	 * @return Returns the contractDate.
	 */
	protected Date getContractDate() {
		return contractDate;
	}

	/**
	 * @return Returns the contractNumber.
	 */
	protected String getContractNumber() {
		return contractNumber;
	}

	/**
	 * @return Returns the contractType.
	 */
	protected DocType getContractType() {
		return contractType;
	}

	/**
	 * @return Returns the dateFrom.
	 */
	protected Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * @return Returns the dateTill.
	 */
	protected Date getDateTill() {
		return dateTill;
	}

	/**
	 * @return Returns the docDate.
	 */
	protected Date getDocDate() {
		return docDate;
	}

	/**
	 * @return Returns the docNumber.
	 */
	protected String getDocNumber() {
		return docNumber;
	}

	/**
	 * @return Returns the docType.
	 */
	protected DocType getDocType() {
		return docType;
	}

	/**
	 * @return Returns the fromCode.
	 */
	protected Contractor getFromCode() {
		return fromCode;
	}

	/**
	 * @return Returns the fromSum.
	 */
	protected BigDecimal getFromSum() {
		return fromSum;
	}

	/**
	 * @return Returns the specMark.
	 */
	protected SpecMark getSpecMark() {
		return specMark;
	}

	/**
	 * @return Returns the toCode.
	 */
	protected Contractor getToCode() {
		return toCode;
	}

	/**
	 * @return Returns the toSum.
	 */
	protected BigDecimal getToSum() {
		return toSum;
	}

	public AccPlan getAccDb() {
		return AccDb;
	}

	public AccPlan getAccKt() {
		return AccKt;
	}

}
