/*
 * WareCardMt.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.math.Constants;
import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.warehouse.BinLocationDetailData;
import com.mg.merp.warehouse.BinLocationServiceLocal;
import com.mg.merp.warehouse.WarehouseBatchHistoryServiceLocal;
import com.mg.merp.warehouse.WarehouseBatchServiceLocal;
import com.mg.merp.warehouse.model.SerialNum;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.model.StockBatchHistoryKind;
import com.mg.merp.warehouse.model.StockPlanHistory;
import com.mg.merp.warehouse.model.StockPlanHistoryDirection;
import com.mg.merp.warehouse.model.StockPlanHistoryKind;
import com.mg.merp.warehouse.support.Messages;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки "Карточки складского учета"
 *
 * @author Julia 'Jetta' Konyashkina
 * @author Konstantin S. Alikaev
 * @author Artem V. Sharapov
 * @version $Id: WareCardMt.java,v 1.22 2009/03/06 07:29:26 safonov Exp $
 */
public class WareCardMt extends DefaultMaintenanceForm {

  private static final String BATCHES_TABLE_NAME = "warehouseBatch"; //$NON-NLS-1$
  private static final String DISPOSAL_BATCHES_TABLE_NAME = "batchDisposal"; //$NON-NLS-1$
  private static final String RECEIPT_BATCHES_TABLE_NAME = "batchReceipt"; //$NON-NLS-1$
  protected AttributeMap warehouseBatchProperties = new LocalDataTransferObject();
  private Date factInTill;
  private Date factInFrom;
  private Date factOutTill;
  private Date factOutFrom;
  private Date planInTill;
  private Date planInFrom;
  private Date planOutTill;
  private Date planOutFrom;
  private Date reserveTill;
  private Date reserveFrom;
  private FieldMetadata processDateFM;
  private FieldMetadata docDateFM;
  private FieldMetadata baseDocDateFM;
  private MaintenanceTableController warehouseBatch;
  private MaintenanceTableController batchDisposal;
  private MaintenanceTableController batchReceipt;
  private WarehouseBatchServiceLocal warehouseBatchService;
  private DefaultTableController factMoveIn;
  private DefaultTableController factMoveOut;
  private DefaultTableController planMoveIn;
  private DefaultTableController planMoveOut;
  private DefaultTableController reserve;
  private DefaultTableController serialNumberTable;
  private DefaultTableController binLocationDetailTable;
  private StockBatch currentBatch;
  private AttributeMap batchDisposalProperties = new LocalDataTransferObject();
  private AttributeMap batchReceiptProperties = new LocalDataTransferObject();
  private WarehouseBatchHistoryServiceLocal sbhServ;
  private BinLocationServiceLocal binLocationService = null;
  private OrmTemplate ormTemplate = null;

  public WareCardMt() throws Exception {
    warehouseBatchService = (WarehouseBatchServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseBatchServiceLocal.LOCAL_SERVICE_NAME);
    warehouseBatch = new MaintenanceTableController(warehouseBatchProperties);
    warehouseBatch.initController(warehouseBatchService,
        new DefaultMaintenanceEJBQLTableModel() {
          private final String INIT_QUERY_TEXT = "select %s from StockBatch sb %s where sb.StockCard = :stockCard"; //$NON-NLS-1$
          private List<String> paramsName = new ArrayList<String>();
          private List<Object> paramsValue = new ArrayList<Object>();

          protected String createQueryText() {
            Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
            String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
            String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
            paramsName.clear();
            paramsValue.clear();
            paramsName.add("stockCard"); //$NON-NLS-1$
            paramsValue.add(getEntity());
            return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
          }

          /*
           * (non-Javadoc)
           *
           * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
           */
          @Override
          protected int getPrimaryKeyFieldIndex() {
            return 0;
          }

          /*
           * (non-Javadoc)
           *
           * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
           */
          @Override
          protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
            Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
            result.add(new TableEJBQLFieldDef(StockBatch.class, "Id", "sb.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "DocType", "sb.DocType", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "DocNumber", "sb.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "DocDate", "sb.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "BeginQuan", "sb.BeginQuan", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "EndQuan", "sb.EndQuan", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "BeginQuan2", "sb.BeginQuan2", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "EndQuan2", "sb.EndQuan2", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "PriceNat", "sb.PriceNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "PriceCur", "sb.PriceCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "CurrencyCode", "sb.CurrencyCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "CreateDate", "sb.CreateDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "BestBefore", "sb.BestBefore", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "NumberLot", "sb.NumberLot", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "VendorLot", "sb.VendorLot", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "Certificate", "sb.Certificate", false)); //$NON-NLS-1$ //$NON-NLS-2$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "Owner", "o.Code", "left join sb.Owner as o", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "StockKind", "sk.Code", "left join sb.StockKind as sk", false)); //$NON-NLS-1$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "CountryOfOrigin", "co.CName", "left join sb.CountryOfOrigin co", false)); //$NON-NLS-1$
            result.add(new TableEJBQLFieldDef(StockBatch.class, "CustomsDeclaration", "cd.Number", "left join sb.CustomsDeclaration cd", false)); //$NON-NLS-1$
            return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, warehouseBatchService);
          }

          /*
           * (non-Javadoc)
           *
           * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
           */
          @Override
          protected void doLoad() {
            setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
          }

        });

    warehouseBatch.addMasterModelListener(new MasterModelListener() {

      public void masterChange(ModelChangeEvent event) {
        currentBatch = event.getModelKey() == null ? null : warehouseBatchService.load((Integer) event.getModelKey());
        batchReceipt.refresh();
        batchDisposal.refresh();
        serialNumberTable.getModel().load();
        binLocationDetailTable.getModel().load();
        ((AbstractTableModel) binLocationDetailTable.getModel()).fireModelChange();
      }

    });
    addMasterModelListener(warehouseBatch);


    sbhServ = (WarehouseBatchHistoryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseBatchHistoryServiceLocal.LOCAL_SERVICE_NAME);
    batchDisposal = new MaintenanceTableController(batchDisposalProperties);
    batchDisposal.initController(sbhServ, new StockBathHistoryMaintenanceTableModel() {

      @Override
      protected StockBatchHistoryKind getKind() {
        return StockBatchHistoryKind.OUT;
      }

    });

    batchReceipt = new MaintenanceTableController(batchReceiptProperties);
    batchReceipt.initController(sbhServ, new StockBathHistoryMaintenanceTableModel() {

      @Override
      protected StockBatchHistoryKind getKind() {
        return StockBatchHistoryKind.IN;
      }

    });

    serialNumberTable = new DefaultTableController(new DefaultEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from SerialNum sn %s where sn.Batch = :batch"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(SerialNum.class, "Id", "sn.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(SerialNum.class, "SerialNum", "sn.SerialNum", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) serialNumberTable.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("batch"); //$NON-NLS-2$ //$NON-NLS-1$
        paramsValue.add(currentBatch);
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

    });

    binLocationDetailTable = new DefaultTableController(new AbstractTableModel() {
      private List<BinLocationDetailData> tableModelItemList = new ArrayList<BinLocationDetailData>();
      private String[] columnNames = getColumnNames();

      private String[] getColumnNames() {
        ApplicationDictionary appDictionary = ApplicationDictionaryLocator.locate();
        return new String[]{appDictionary.getDataItem("Warehouse.BinLocation.Code").getHeader(), appDictionary.getDataItem("Warehouse.BinLocationDetail.Quantity").getHeader()};
      }

      /* (non-Javadoc)
       * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
       */
      public int getColumnCount() {
        return columnNames.length;
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
        return tableModelItemList.size();
      }

      /* (non-Javadoc)
       * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
       */
      public Object getValueAt(int row, int column) {
        BinLocationDetailData item = tableModelItemList.get(row);
        switch (column) {
          case 0:
            return item.getBinLocationCode();
          case 1:
            return item.getQuantity();
          default:
            return StringUtils.EMPTY_STRING;
        }
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        if (binLocationService == null)
          binLocationService = (BinLocationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BinLocationServiceLocal.LOCAL_SERVICE_NAME);

        tableModelItemList = binLocationService.getBinLocationDetails(currentBatch);
      }

    });

    factMoveIn = new DefaultTableController(new WareCardFactTableModel(new ArrayList<FactItem>()));
    factMoveOut = new DefaultTableController(new WareCardFactTableModel(new ArrayList<FactItem>()));

    planMoveIn = new DefaultTableController(new WareCardPlanReservTableModel(new ArrayList<PlanReservItem>()));
    planMoveOut = new DefaultTableController(new WareCardPlanReservTableModel(new ArrayList<PlanReservItem>()));
    reserve = new DefaultTableController(new WareCardPlanReservTableModel(new ArrayList<PlanReservItem>()));

    processDateFM = ApplicationDictionaryLocator.locate().getFieldMetadata(StockBatchHistory.class, "ProcessDate"); //$NON-NLS-1$
    docDateFM = ApplicationDictionaryLocator.locate().getFieldMetadata(DocHead.class, "DocDate"); //$NON-NLS-1$
    baseDocDateFM = ApplicationDictionaryLocator.locate().getFieldMetadata(DocHead.class, "BaseDocDate"); //$NON-NLS-1$
  }

  private OrmTemplate getOrmTemplate() {
    if (ormTemplate == null)
      ormTemplate = OrmTemplate.getInstance();
    return ormTemplate;
  }

  public void onActionRefreshPlanIn(WidgetEvent event) throws Exception {
    ((WareCardPlanReservTableModel) planMoveIn.getModel()).refresh(
        StockPlanHistoryKind.IN_PLAN, StockPlanHistoryDirection.IN, planInFrom, planInTill);
  }

  public void onActionRefreshPlanOut(WidgetEvent event) throws Exception {
    ((WareCardPlanReservTableModel) planMoveOut.getModel()).refresh(
        StockPlanHistoryKind.IN_PLAN, StockPlanHistoryDirection.OUT, planOutFrom, planOutTill);
  }

  public void onActionRefreshReserve(WidgetEvent event) throws Exception {
    ((WareCardPlanReservTableModel) reserve.getModel()).refresh(StockPlanHistoryKind.IN_RESERVE,
        StockPlanHistoryDirection.IN, reserveFrom, reserveTill);
  }

  public void onActionRefreshFactIn(WidgetEvent event) throws Exception {
    ((WareCardFactTableModel) factMoveIn.getModel()).refresh(StockBatchHistoryKind.IN, factInFrom, factInTill);
  }

  public void onActionRefreshFactOut(WidgetEvent event) throws Exception {
    ((WareCardFactTableModel) factMoveOut.getModel()).refresh(StockBatchHistoryKind.OUT, factOutFrom, factOutTill);
  }

  protected void doOnRun() {
    super.doOnRun();
    PopupMenu batchesMenu = view.getWidget(BATCHES_TABLE_NAME).getPopupMenu();
    batchesMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
    batchesMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setEnabled(false);
    batchesMenu.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
    batchesMenu.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setEnabled(false);

    PopupMenu disposalBatchesMenu = view.getWidget(DISPOSAL_BATCHES_TABLE_NAME).getPopupMenu();
    disposalBatchesMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
    disposalBatchesMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setEnabled(false);
    disposalBatchesMenu.getMenuItem(MaintenanceTable.EDIT_MENU_ITEM).setEnabled(false);
    disposalBatchesMenu.getMenuItem(MaintenanceTable.VIEW_MENU_ITEM).setEnabled(false);
    disposalBatchesMenu.getMenuItem(MaintenanceTable.RESTRICTION_MENU_ITEM).setEnabled(false);
    disposalBatchesMenu.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
    disposalBatchesMenu.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setEnabled(false);
    PopupMenu receiptBatchesMenu = view.getWidget(RECEIPT_BATCHES_TABLE_NAME).getPopupMenu();
    receiptBatchesMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
    receiptBatchesMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setEnabled(false);
    receiptBatchesMenu.getMenuItem(MaintenanceTable.EDIT_MENU_ITEM).setEnabled(false);
    receiptBatchesMenu.getMenuItem(MaintenanceTable.VIEW_MENU_ITEM).setEnabled(false);
    receiptBatchesMenu.getMenuItem(MaintenanceTable.RESTRICTION_MENU_ITEM).setEnabled(false);
    receiptBatchesMenu.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
    receiptBatchesMenu.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setEnabled(false);
  }

  /**
   * Обработчик пункта КМ таблицы списание с партии "Просмотреть документ"
   *
   * @param event событие
   */
  public void onActionViewDocument(WidgetEvent event) {
    Serializable[] keys = ((DefaultMaintenanceEJBQLTableModel) batchDisposal.getModel()).getSelectedPrimaryKeys();
    if (keys.length > 0)
      viewDocument(ServerUtils.getPersistentManager().find(StockBatchHistory.class, keys[0]).getDocHead());
  }

  /**
   * Обработчик пункта КМ таблицы фактическое движение приход "Просмотреть документ"
   *
   * @param event событие
   */
  public void onActionViewDocumentOnTableFactMoveIn(WidgetEvent event) {
    Serializable[] keys = ((WareCardFactTableModel) factMoveIn.getModel()).getSelectedPrimaryKeys();
    if (keys != null && keys.length > 0)
      viewDocument(ServerUtils.getPersistentManager().find(StockBatchHistory.class, keys[0]).getDocHead());
  }

  /**
   * Обработчик пункта КМ таблицы фактическое движение расход "Просмотреть документ"
   *
   * @param event событие
   */
  public void onActionViewDocumentOnTableFactMoveOut(WidgetEvent event) {
    Serializable[] keys = ((WareCardFactTableModel) factMoveOut.getModel()).getSelectedPrimaryKeys();
    if (keys != null && keys.length > 0)
      viewDocument(ServerUtils.getPersistentManager().find(StockBatchHistory.class, keys[0]).getDocHead());
  }

  /**
   * Обработчик пункта КМ таблицы планируемое движение приход "Просмотреть документ"
   *
   * @param event событие
   */
  public void onActionViewDocumentOnTablePlanMoveIn(WidgetEvent event) {
    Serializable[] keys = ((WareCardPlanReservTableModel) planMoveIn.getModel()).getSelectedPrimaryKeys();
    if (keys != null && keys.length > 0)
      viewDocument(ServerUtils.getPersistentManager().find(StockPlanHistory.class, keys[0]).getDocHead());
  }

  /**
   * Обработчик пункта КМ таблицы планируемое движение расход "Просмотреть документ"
   *
   * @param event событие
   */
  public void onActionViewDocumentOnTablePlanMoveOut(WidgetEvent event) {
    Serializable[] keys = ((WareCardPlanReservTableModel) planMoveOut.getModel()).getSelectedPrimaryKeys();
    if (keys != null && keys.length > 0)
      viewDocument(ServerUtils.getPersistentManager().find(StockPlanHistory.class, keys[0]).getDocHead());
  }

  /**
   * Обработчик пункта КМ таблицы зарезервировано "Просмотреть документ"
   *
   * @param event событие
   */
  public void onActionViewDocumentOnTableReserve(WidgetEvent event) {
    Serializable[] keys = ((WareCardPlanReservTableModel) reserve.getModel()).getSelectedPrimaryKeys();
    if (keys != null && keys.length > 0)
      viewDocument(ServerUtils.getPersistentManager().find(StockPlanHistory.class, keys[0]).getDocHead());
  }

  /**
   * Обработчик пункта КМ таблицы поступления в партию "Просмотреть документ"
   *
   * @param event событие
   */
  public void onActionViewDocumentOnTableWarehouseBatch(WidgetEvent event) {
    Serializable[] keys = ((DefaultMaintenanceEJBQLTableModel) batchReceipt.getModel()).getSelectedPrimaryKeys();
    if (keys.length > 0)
      viewDocument(ServerUtils.getPersistentManager().find(StockBatchHistory.class, keys[0]).getDocHead());
  }

  /**
   * Открывает документ на просмотр
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private void viewDocument(DocHead docHead) {
    if (docHead != null)
      MaintenanceHelper.view(DocumentUtils.getDocumentService(docHead.getDocSection()), docHead.getId(), null, null);
    else
      getLogger().error("Not found document"); //$NON-NLS-1$
  }

  private class StockBathHistoryMaintenanceTableModel extends DefaultMaintenanceEJBQLTableModel {
    private final String INIT_QUERY_TEXT = "select %s from StockBatchHistory sbh %s %s "; //$NON-NLS-1$
    private List<String> paramsName = new ArrayList<String>();
    private List<Object> paramsValue = new ArrayList<Object>();

    /**
     * Возвращает признак списание или приход в партию
     */
    protected StockBatchHistoryKind getKind() {
      return null;
    }

    protected String createQueryText() {
      Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
      String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
      String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
      paramsName.clear();
      paramsValue.clear();
      paramsName.add("currentBatch"); //$NON-NLS-1$
      paramsValue.add(currentBatch);
      StringBuilder whereText = new StringBuilder(" where (sbh.StockBatch = :currentBatch) ");
      if (getKind() != null) {
        if (getKind() == StockBatchHistoryKind.IN)
          whereText.append(" and (sbh.Kind = com.mg.merp.warehouse.model.StockBatchHistoryKind.IN) ");
        else
          whereText.append(" and (sbh.Kind = com.mg.merp.warehouse.model.StockBatchHistoryKind.OUT) ");
      }
      return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
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
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "Id", "sbh.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "ProcessDate", "sbh.ProcessDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "Quantity", "sbh.Quantity", false)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "Quantity2", "sbh.Quantity2", false)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "DocHead.DocType", "sbh.DocHead.DocType.Code", "left join sbh.DocHead.DocType as dt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "DocHead.DocNumber", "sbh.DocHead.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "DocHead.DocDate", "sbh.DocHead.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "DocHead.BaseDocType", "bdt.Code", "left join sbh.DocHead.BaseDocType as bdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "DocHead.BaseDocNumber", "sbh.DocHead.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "DocHead.BaseDocDate", "sbh.DocHead.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "DocHead.From", "sbh.DocHead.From.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "DocHead.To", "sbh.DocHead.To.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
      result.add(new TableEJBQLFieldDef(StockBatchHistory.class, "DateTime", "sbh.DateTime", false)); //$NON-NLS-1$ //$NON-NLS-2$
      return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
    }

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
     */
    @Override
    protected void doLoad() {
      setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
    }

  }

  private class WareCardFactTableModel extends AbstractTableModel {
    private String[] columnsName;
    private List<FactItem> moveList;
    private int[] selectedIds = null;

    public WareCardFactTableModel(List<FactItem> moveList) {
      Messages msg = Messages.getInstance();
      columnsName = new String[]{
          msg.getMessage(Messages.DOC_PROCESS_DATE_CAPTION),
          msg.getMessage(Messages.DOC_QUAN1_CAPTION),
          msg.getMessage(Messages.DOC_QUAN2_CAPTION),
          msg.getMessage(Messages.DOC_FROM),
          msg.getMessage(Messages.DOC_TO),
          msg.getMessage(Messages.DOC_TYPE_HEADER),
          msg.getMessage(Messages.DOC_NUMBER_HEADER),
          msg.getMessage(Messages.DOC_DATE_HEADER),
          msg.getMessage(Messages.DOC_TYPE_BASE_HEADER),
          msg.getMessage(Messages.DOC_NUMBER_BASE_HEADER),
          msg.getMessage(Messages.DOC_DATE_BASE_HEADER),
          msg.getMessage(Messages.PRICE_NAT_CAPTION),
          msg.getMessage(Messages.PRICE_CUR_CAPTION),
          msg.getMessage(Messages.CUR_CODE_CAPTION),
          msg.getMessage(Messages.DOC_DATE),
          msg.getMessage(Messages.BATCH_OWNER),
          msg.getMessage(Messages.BATCH_KIND)};
      this.moveList = moveList;
    }

    /*
     * (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
     */
    @Override
    public void setSelectedRows(int[] rows) {
      selectedIds = rows == null || rows.length == 0 ? null : rows;
    }

    /*
     * (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#getSelectedPrimaryKeys()
     */
    @Override
    public Serializable[] getSelectedPrimaryKeys() {
      if (selectedIds == null)
        return null;
      Serializable[] result = new Serializable[selectedIds.length];
      for (int i = 0; i < selectedIds.length; i++)
        result[i] = moveList.get(selectedIds[i]).historyId;
      return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
     */
    public int getColumnCount() {
      return columnsName.length;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
     */
    public String getColumnName(int column) {
      return columnsName[column];
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
     */
    public int getRowCount() {
      return moveList != null ? moveList.size() : 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int,
     *      int)
     */
    public Object getValueAt(int row, int column) {
      if (moveList == null)
        throw new IllegalStateException();
      FactItem item = moveList.get(row);
      switch (column) {
        case 0:
          return MiscUtils.getDateTextRepresentation(item.ProcessDate, processDateFM);
        case 1:
          return item.Quantity;
        case 2:
          return item.Quantity2;
        case 3:
          return item.From;
        case 4:
          return item.To;
        case 5:
          return item.DocType;
        case 6:
          return item.DocNumber;
        case 7:
          return MiscUtils.getDateTextRepresentation(item.DocDate, docDateFM);
        case 8:
          return item.BaseDocType;
        case 9:
          return item.BaseDocNumber;
        case 10:
          return MiscUtils.getDateTextRepresentation(item.BaseDocDate, baseDocDateFM);
        case 11:
          return item.PriceNat;
        case 12:
          return item.PriceCur;
        case 13:
          return item.CurCode;
        case 14:
          return item.SysDateTime;
        case 15:
          return item.Owner;
        case 16:
          return item.Kind;
      }
      return null;
    }

    public void refresh(StockBatchHistoryKind kind, Date dateFrom, Date dateTill) {
      moveList = findFact(kind, dateFrom, dateTill);
      fireModelChange();
    }

    private List<FactItem> findFact(StockBatchHistoryKind kind, Date dateFrom, Date dateTill) {
      StringBuilder query = new StringBuilder();
      StringBuilder queryDate = new StringBuilder();
      List<String> paramNames = new ArrayList<String>();
      List<Object> paramValues = new ArrayList<Object>();
      paramNames.add("card"); //$NON-NLS-1$
      paramValues.add(getEntity());
      paramNames.add("kind"); //$NON-NLS-1$
      paramValues.add(kind);

      if (dateFrom != null) {
        queryDate.append(" and(sbh.ProcessDate >= (:dateFrom))"); //$NON-NLS-1$
        paramNames.add("dateFrom"); //$NON-NLS-1$
        paramValues.add(dateFrom);
      }
      if (dateTill != null) {
        queryDate.append(" and(sbh.ProcessDate <= (:dateTill))"); //$NON-NLS-1$
        paramNames.add("dateTill"); //$NON-NLS-1$
        paramValues.add(dateTill);
      }
      query.append("select sbh from StockBatchHistory sbh where") //$NON-NLS-1$
          .append(" (sbh.StockBatch.StockCard = :card) and (sbh.Kind = :kind)") //$NON-NLS-1$
          .append(queryDate)
          .append(" order by sbh.ProcessDate"); //$NON-NLS-1$;

      List<StockBatchHistory> sbhL = getOrmTemplate().findByNamedParam(query.toString()
          , paramNames.toArray(new String[paramNames.size()]), paramValues.toArray());

      ArrayList<FactItem> result = new ArrayList<FactItem>(sbhL.size());
      for (StockBatchHistory sbh : sbhL) {
        FactItem item = new FactItem();
        DocHead docHead = sbh.getDocHead();
        item.BaseDocDate = docHead.getBaseDocDate();
        item.BaseDocNumber = docHead.getBaseDocNumber() != null ? docHead.getBaseDocNumber().trim() : null;
        item.BaseDocType = docHead.getBaseDocType() != null ? docHead.getBaseDocType().getCode().trim() : null;
        item.historyId = sbh.getId();
        item.DocDate = docHead.getDocDate();
        item.DocNumber = docHead.getDocNumber().trim();
        item.DocType = docHead.getDocType() != null ? docHead.getDocType().getCode().trim() : null;
        item.From = docHead.getFrom() != null ? docHead.getFrom().getCode().trim() : null;
        item.To = docHead.getTo() != null ? docHead.getTo().getCode().trim() : null;
        item.ProcessDate = sbh.getProcessDate();
        item.Quantity = sbh.getQuantity();
        item.Quantity2 = sbh.getQuantity2();
        item.SysDateTime = sbh.getDateTime();
        item.CurCode = docHead.getCurrency() != null ? docHead.getCurrency().getCode().trim() : null;
        item.Kind = sbh.getStockBatch().getStockKind() != null ? sbh.getStockBatch().getStockKind().getCode().trim() : null;
        item.Owner = sbh.getStockBatch().getOwner() != null ? sbh.getStockBatch().getOwner().getCode().trim() : null;
        item.PriceCur = sbh.getStockBatch().getPriceCur();
        item.PriceNat = sbh.getStockBatch().getPriceNat();
        result.add(item);
      }

      return result;
    }
  }

  private class WareCardPlanReservTableModel extends AbstractTableModel {
    private String[] columnsName;
    private List<PlanReservItem> moveList;
    private int[] selectedIds = null;

    public WareCardPlanReservTableModel(List<PlanReservItem> moveList) {
      Messages msg = Messages.getInstance();
      columnsName = new String[]{
          msg.getMessage(Messages.DOC_PROCESS_DATE_CAPTION),
          msg.getMessage(Messages.DOC_QUAN1_CAPTION),
          msg.getMessage(Messages.DOC_QUAN2_CAPTION),
          msg.getMessage(Messages.DOC_FROM),
          msg.getMessage(Messages.DOC_TO),
          msg.getMessage(Messages.DOC_TYPE_HEADER),
          msg.getMessage(Messages.DOC_NUMBER_HEADER),
          msg.getMessage(Messages.DOC_DATE_HEADER),
          msg.getMessage(Messages.DOC_TYPE_BASE_HEADER),
          msg.getMessage(Messages.DOC_NUMBER_BASE_HEADER),
          msg.getMessage(Messages.DOC_DATE_BASE_HEADER),
          msg.getMessage(Messages.DOC_DATE)};
      this.moveList = moveList;
    }

    /*
     * (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
     */
    @Override
    public void setSelectedRows(int[] rows) {
      selectedIds = rows == null || rows.length == 0 ? null : rows;
    }

    /*
     * (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#getSelectedPrimaryKeys()
     */
    @Override
    public Serializable[] getSelectedPrimaryKeys() {
      if (selectedIds == null)
        return null;
      Serializable[] result = new Serializable[selectedIds.length];
      for (int i = 0; i < selectedIds.length; i++)
        result[i] = moveList.get(selectedIds[i]).historyId;
      return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
     */
    public int getColumnCount() {
      return columnsName.length;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
     */
    public String getColumnName(int column) {
      return columnsName[column];
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
     */
    public int getRowCount() {
      return moveList != null ? moveList.size() : 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int,
     *      int)
     */
    public Object getValueAt(int row, int column) {
      if (moveList == null)
        throw new IllegalStateException();
      PlanReservItem item = moveList.get(row);
      switch (column) {
        case 0:
          return MiscUtils.getDateTextRepresentation(item.ProcessDate, processDateFM);
        case 1:
          return item.Quantity;
        case 2:
          return item.Quantity2;
        case 3:
          return item.From;
        case 4:
          return item.To;
        case 5:
          return item.DocType;
        case 6:
          return item.DocNumber;
        case 7:
          return MiscUtils.getDateTextRepresentation(item.DocDate, docDateFM);
        case 8:
          return item.BaseDocType;
        case 9:
          return item.BaseDocNumber;
        case 10:
          return MiscUtils.getDateTextRepresentation(item.BaseDocDate, baseDocDateFM);
        case 11:
          return item.SysDateTime;
      }
      return null;
    }

    public void refresh(StockPlanHistoryKind kind, StockPlanHistoryDirection direction, Date dateFrom, Date dateTill) {
      moveList = findPlanReserv(kind, direction, dateFrom, dateTill);
      fireModelChange();
    }

    private List<PlanReservItem> findPlanReserv(StockPlanHistoryKind kind,
                                                StockPlanHistoryDirection direction, Date dateFrom, Date dateTill) {
      StringBuilder query = new StringBuilder();
      StringBuilder queryDate = new StringBuilder();
      List<String> paramNames = new ArrayList<String>();
      List<Object> paramValues = new ArrayList<Object>();
      paramNames.add("card"); //$NON-NLS-1$
      paramValues.add(getEntity());
      paramNames.add("kind"); //$NON-NLS-1$
      paramValues.add(kind);
      paramNames.add("dir"); //$NON-NLS-1$
      paramValues.add(direction);
      paramNames.add("dispKind"); //$NON-NLS-1$
      switch (kind) {
        case IN_PLAN:
          paramValues.add(StockPlanHistoryKind.OUT_PLAN);
          break;
        case IN_RESERVE:
          paramValues.add(StockPlanHistoryKind.OUT_RESERVE);
          break;
      }

      if (dateFrom != null) {
        queryDate.append(" and(sph.ProcessDate >= (:dateFrom))"); //$NON-NLS-1$
        paramNames.add("dateFrom"); //$NON-NLS-1$
        paramValues.add(dateFrom);
      }
      if (dateTill != null) {
        queryDate.append(" and(sph.ProcessDate <= (:dateTill))"); //$NON-NLS-1$
        paramNames.add("dateTill"); //$NON-NLS-1$
        paramValues.add(dateTill);
      }
      query.append("select sph from StockPlanHistory sph where") //$NON-NLS-1$
          .append(" (sph.StockCard = :card) and (sph.Kind = :kind or sph.Kind = :dispKind) and (sph.Direction = :dir)") //$NON-NLS-1$
          .append(queryDate)
          .append(" order by sph.ProcessDate, sph.Kind"); //$NON-NLS-1$

      List<StockPlanHistory> sphL = getOrmTemplate().findByNamedParam(query.toString()
          , paramNames.toArray(new String[paramNames.size()]), paramValues.toArray());

      ArrayList<PlanReservItem> result = new ArrayList<PlanReservItem>(sphL.size());
      for (StockPlanHistory sph : sphL) {
        if (sph.getKind() == kind) {
          PlanReservItem item = new PlanReservItem();
          DocHead docHead = sph.getDocHead();
          item.documentId = docHead.getId();
          item.BaseDocDate = docHead.getBaseDocDate();
          item.BaseDocNumber = docHead.getBaseDocNumber() != null ? docHead.getBaseDocNumber().trim() : null;
          item.BaseDocType = docHead.getBaseDocType() != null ? docHead.getBaseDocType().getCode().trim() : null;
          item.historyId = sph.getId();
          item.DocDate = docHead.getDocDate();
          item.DocNumber = docHead.getDocNumber().trim();
          item.DocType = docHead.getDocType() != null ? docHead.getDocType().getCode().trim() : null;
          item.From = docHead.getFrom() != null ? docHead.getFrom().getCode().trim() : null;
          item.To = docHead.getTo() != null ? docHead.getTo().getCode().trim() : null;
          item.ProcessDate = sph.getProcessDate();
          item.Quantity = sph.getQuantity();
          item.Quantity2 = sph.getQuantity2();
          item.SysDateTime = sph.getSysDateTime();
          result.add(item);
        } else {
          //ведем поиск по документу (https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4951)
          for (PlanReservItem item : result) {
            if (item.documentId == sph.getDocHead().getId()) {
              item.Quantity = MathUtils.subtractNullable(item.Quantity, sph.getQuantity(), Constants.QUANTITY_ROUND_CONTEXT);
              item.Quantity2 = MathUtils.subtractNullable(item.Quantity2, sph.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT);
            }
          }

					/*PlanReservItem item = result.get(result.size() - 1);
                    item.Quantity = MathUtils.subtractNullable(item.Quantity, sph.getQuantity(), Constants.QUANTITY_ROUND_CONTEXT);
					item.Quantity2 = MathUtils.subtractNullable(item.Quantity2, sph.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT);

					if ((MathUtils.compareToZero(item.Quantity) > 0)
							|| (item.Quantity2 != null && MathUtils.compareToZero(item.Quantity2) > 0))
						result.set(result.size() - 1, item);
					else
						result.remove(result.size() - 1);*/
        }
      }

      //remove zero or negative
      for (int i = result.size() - 1; i >= 0; i--) {
        PlanReservItem item = result.get(i);
        if ((MathUtils.compareToZero(item.Quantity) <= 0)
            && (item.Quantity2 != null && MathUtils.compareToZero(item.Quantity2) <= 0))
          result.remove(i);
      }

      return result;
    }

  }

  private class PlanReservItem {
    int documentId;

    Date ProcessDate;

    BigDecimal Quantity;

    BigDecimal Quantity2;

    String From;

    String To;

    String DocType;

    String DocNumber;

    Date DocDate;

    Integer historyId;

    String BaseDocType;

    String BaseDocNumber;

    Date BaseDocDate;

    Date SysDateTime;
  }

  private class FactItem extends PlanReservItem {
    BigDecimal PriceCur;

    BigDecimal PriceNat;

    String CurCode;

    String Owner;

    String Kind;
  }

}
