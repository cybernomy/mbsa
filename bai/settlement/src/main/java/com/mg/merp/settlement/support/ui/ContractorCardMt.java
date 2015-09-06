/*
 * ContractorCardMt.java
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
package com.mg.merp.settlement.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.settlement.SelectionRowListener;
import com.mg.merp.settlement.model.ContractorCard;
import com.mg.merp.settlement.model.ContractorCardHist;
import com.mg.merp.settlement.model.ContractorCardPlan;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Контроллер формы поддержки бизнес-компонента "Карточки расчетов с партнерами"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractorCardMt.java,v 1.7 2007/03/19 15:05:29 sharapov Exp $
 */
public class ContractorCardMt extends DefaultMaintenanceForm implements MasterModelListener {

  private static String CUR_CODE_FILTER = "curCodeFilter"; //$NON-NLS-1$
  private static String DATE_FROM_FILTER = "dateFromFilter"; //$NON-NLS-1$
  private static String DATE_TILL_FILTER = "dateTillFilter"; //$NON-NLS-1$
  private static String CONTRACTOR_CARD_ID = "contractorCardId"; //$NON-NLS-1$
  private static String CONTRACTOR_CARD = "contractorCard"; //$NON-NLS-1$
  private static String DOC_DATE = "docDate"; //$NON-NLS-1$
  private static String DOC_TYPE = "docTypeCode"; //$NON-NLS-1$
  private static String DOC_NUMBER = "docNumber"; //$NON-NLS-1$
  protected DefaultTableController confirmDocIncomeTable;
  protected DefaultTableController confirmDocExpenseTable;
  protected DefaultTableController baseDocTable;
  protected DefaultTableController baseDocIncomeTable;
  protected DefaultTableController baseDocExpenseTable;
  protected DefaultTableController contractTable;
  protected DefaultTableController contractIncomeTable;
  protected DefaultTableController contractExpenseTable;
  protected DefaultTableController planIncomeTable;
  protected DefaultTableController planExpenseTable;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private DocListItem selectedParentItem;
  private boolean isFilterEnabled = true;
  private boolean isFilterApplied = false;
  private Date dateFrom;
  private Date dateTill;
  private Currency curCode;

  public ContractorCardMt() {
    addMasterModelListener(this);

    confirmDocIncomeTable = new DefaultTableController(new CardHistTableModel() {
      protected String createQueryText() {
        String INIT_QUERY_TEXT = "select %s from ContractorCardHist cch %s where cch.ContractorCard = :contractorCard and cch.Kind = 0"; //$NON-NLS-1$
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        if (isFilterApplied)
          INIT_QUERY_TEXT = INIT_QUERY_TEXT.concat(getDocFilterQueryText());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(confirmDocIncomeTable);

    confirmDocExpenseTable = new DefaultTableController(new CardHistTableModel() {
      protected String createQueryText() {
        String INIT_QUERY_TEXT = "select %s from ContractorCardHist cch %s where cch.ContractorCard = :contractorCard and cch.Kind = 1";  //$NON-NLS-1$
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        if (isFilterApplied)
          INIT_QUERY_TEXT = INIT_QUERY_TEXT.concat(getDocFilterQueryText());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(confirmDocExpenseTable);

    baseDocTable = new DefaultTableController(new DocTableModel(new SelectionRowListener() {

      /* (non-Javadoc)
       * @see com.mg.merp.settlement.SelectionRowListener#selectedRowChange(com.mg.merp.settlement.support.ui.ContractorCardMt.DocListItem)
       */
      public void selectedRowChange(DocListItem selectedItem) {
        selectedParentItem = selectedItem;
        setChildTableParams(selectedItem.docDate, selectedItem.docType, selectedItem.docNumber);
        refreshBaseDocChildTables();
      }
    }
    ));
    addMasterModelListener(baseDocTable);

    baseDocIncomeTable = new DefaultTableController(new CardHistTableModel() {
      protected String createQueryText() {
        String INIT_QUERY_TEXT = "select %s from ContractorCardHist cch %s left join cch.DocHead d left join d.BaseDocType bdt where cch.Kind = 0 and cch.ContractorCard = :contractorCard and bdt.Code = :docTypeCode and d.BaseDocDate = :docDate and d.BaseDocNumber = :docNumber"; //$NON-NLS-1$
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        if (isFilterApplied) {
          INIT_QUERY_TEXT = INIT_QUERY_TEXT.concat(getDocFilterQueryText());
          refreshChildTableParams();
        }
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    baseDocExpenseTable = new DefaultTableController(new CardHistTableModel() {
      protected String createQueryText() {
        String INIT_QUERY_TEXT = "select %s from ContractorCardHist cch %s left join cch.DocHead d left join d.BaseDocType bdt where cch.Kind = 1 and cch.ContractorCard = :contractorCard and bdt.Code = :docTypeCode and d.BaseDocDate = :docDate and d.BaseDocNumber = :docNumber"; //$NON-NLS-1$
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        if (isFilterApplied) {
          INIT_QUERY_TEXT = INIT_QUERY_TEXT.concat(getDocFilterQueryText());
          refreshChildTableParams();
        }
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    contractTable = new DefaultTableController(new DocTableModel(new SelectionRowListener() {

      /* (non-Javadoc)
       * @see com.mg.merp.settlement.SelectionRowListener#selectedRowChange(com.mg.merp.settlement.support.ui.ContractorCardMt.DocListItem)
       */
      public void selectedRowChange(DocListItem selectedItem) {
        selectedParentItem = selectedItem;
        setChildTableParams(selectedItem.docDate, selectedItem.docType, selectedItem.docNumber);
        refreshContractChildTables();
      }
    }
    ));
    addMasterModelListener(contractTable);

    contractIncomeTable = new DefaultTableController(new CardHistTableModel() {
      protected String createQueryText() {
        String INIT_QUERY_TEXT = "select %s from ContractorCardHist cch %s left join cch.DocHead d left join d.ContractType cdt where cch.Kind = 0 and cch.ContractorCard = :contractorCard and cdt.Code = :docTypeCode and d.ContractDate = :docDate and d.ContractNumber = :docNumber"; //$NON-NLS-1$
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        if (isFilterApplied) {
          INIT_QUERY_TEXT = INIT_QUERY_TEXT.concat(getDocFilterQueryText());
          refreshChildTableParams();
        }
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    contractExpenseTable = new DefaultTableController(new CardHistTableModel() {
      protected String createQueryText() {
        String INIT_QUERY_TEXT = "select %s from ContractorCardHist cch %s left join cch.DocHead d left join d.ContractType cdt where cch.Kind = 1 and cch.ContractorCard = :contractorCard and cdt.Code = :docTypeCode and d.ContractDate = :docDate and d.ContractNumber = :docNumber"; //$NON-NLS-1$
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        if (isFilterApplied) {
          INIT_QUERY_TEXT = INIT_QUERY_TEXT.concat(getDocFilterQueryText());
          refreshChildTableParams();
        }
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    planIncomeTable = new DefaultTableController(new CardPlanTableModel() {
      protected String createQueryText() {
        String INIT_QUERY_TEXT = "select %s from ContractorCardPlan ccp %s where ccp.ContractorCard = :contractorCard and ccp.Kind = 0 and (not exists(select ccp1 from ContractorCardPlan ccp1  where (ccp1.ContractorCard = ccp.ContractorCard) and (ccp1.Kind = 0) and (ccp1.IsSet = 0) and (ccp1.DocHead = ccp.DocHead)))"; //$NON-NLS-1$
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        if (isFilterApplied)
          INIT_QUERY_TEXT = INIT_QUERY_TEXT.concat(getDocFilterQueryText());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(planIncomeTable);

    planExpenseTable = new DefaultTableController(new CardPlanTableModel() {
      protected String createQueryText() {
        String INIT_QUERY_TEXT = "select %s from ContractorCardPlan ccp %s where ccp.ContractorCard = :contractorCard and ccp.Kind = 1 and (not exists(select ccp1 from ContractorCardPlan ccp1  where (ccp1.ContractorCard = ccp.ContractorCard) and (ccp1.Kind = 1) and (ccp1.IsSet = 0) and (ccp1.DocHead = ccp.DocHead)))"; //$NON-NLS-1$
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        if (isFilterApplied)
          INIT_QUERY_TEXT = INIT_QUERY_TEXT.concat(getDocFilterQueryText());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(planExpenseTable);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    setContractorCardParamNameAndValue();
    refreshTables();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnAdd()
   */
  @Override
  protected void doOnAdd() {
    super.doOnAdd();
    isFilterEnabled = false;
  }

  /**
   * Обработка события "Применить" (фильтр)
   *
   * @param event - событие
   */
  public void onActionApply(WidgetEvent event) {
    if (!isFilterEnabled)
      return;
    prepareFilter();
    if (curCode != null || dateFrom != null || dateTill != null) {
      isFilterApplied = true;
      resetChildTables();
      refreshTables();
    }
  }

  /**
   * Обработка события "Очистить" (фильтр)
   *
   * @param event - событие
   */
  public void onActionClear(WidgetEvent event) {
    clearFilter();
    view.flushModel();
    if (isFilterEnabled) {
      isFilterApplied = false;
      setContractorCardParamNameAndValue();
      resetChildTables();
      refreshTables();
    }
  }

  /**
   * Обработка события "Показать документ"
   *
   * @param event - событие
   */
  public void onActionPlanIncomeShowDocument(WidgetEvent event) throws ApplicationException {
    Integer identifier = ((CardPlanTableModel) planIncomeTable.getModel()).getSelectedIdentifier();
    internalShowDocument(getCardPlanDocHead(identifier));
  }

  /**
   * Обработка события "Показать документ"
   *
   * @param event - событие
   */
  public void onActionPlanExpenseShowDocument(WidgetEvent event) throws ApplicationException {
    Integer identifier = ((CardPlanTableModel) planExpenseTable.getModel()).getSelectedIdentifier();
    internalShowDocument(getCardPlanDocHead(identifier));
  }

  /**
   * Обработка события "Показать документ"
   *
   * @param event - событие
   */
  public void onActionConfirmDocIncomeShowDocument(WidgetEvent event) throws ApplicationException {
    Integer identifier = ((CardHistTableModel) confirmDocIncomeTable.getModel()).getSelectedIdentifier();
    internalShowDocument(getCardHistDocHead(identifier));
  }

  /**
   * Обработка события "Показать документ"
   *
   * @param event - событие
   */
  public void onActionConfirmDocExpenseShowDocument(WidgetEvent event) throws ApplicationException {
    Integer identifier = ((CardHistTableModel) confirmDocExpenseTable.getModel()).getSelectedIdentifier();
    internalShowDocument(getCardHistDocHead(identifier));
  }

  /**
   * Обработка события "Показать документ"
   *
   * @param event - событие
   */
  public void onActionBaseDocIncomeShowDocument(WidgetEvent event) throws ApplicationException {
    Integer identifier = ((CardHistTableModel) baseDocIncomeTable.getModel()).getSelectedIdentifier();
    internalShowDocument(getCardHistDocHead(identifier));
  }

  /**
   * Обработка события "Показать документ"
   *
   * @param event - событие
   */
  public void onActionBaseDocExpenseShowDocument(WidgetEvent event) throws ApplicationException {
    Integer identifier = ((CardHistTableModel) baseDocExpenseTable.getModel()).getSelectedIdentifier();
    internalShowDocument(getCardHistDocHead(identifier));
  }

  /**
   * Обработка события "Показать документ"
   *
   * @param event - событие
   */
  public void onActionContractIncomeShowDocument(WidgetEvent event) throws ApplicationException {
    Integer identifier = ((CardHistTableModel) contractIncomeTable.getModel()).getSelectedIdentifier();
    internalShowDocument(getCardHistDocHead(identifier));
  }

  /**
   * Обработка события "Показать документ"
   *
   * @param event - событие
   */
  public void onActionContractExpenseShowDocument(WidgetEvent event) throws ApplicationException {
    Integer identifier = ((CardHistTableModel) contractExpenseTable.getModel()).getSelectedIdentifier();
    internalShowDocument(getCardHistDocHead(identifier));
  }

  /**
   * Получить документ
   *
   * @param identifier - идентификатор
   * @return документ
   */
  private DocHead getCardPlanDocHead(Integer identifier) {
    if (identifier != null)
      return ((ContractorCardPlan) ServerUtils.getPersistentManager().find(ContractorCardPlan.class, identifier)).getDocHead();
    else
      return null;
  }

  /**
   * Получить документ
   *
   * @param identifier - идентификатор
   * @return документ
   */
  private DocHead getCardHistDocHead(Integer identifier) {
    if (identifier != null)
      return ((ContractorCardHist) ServerUtils.getPersistentManager().find(ContractorCardHist.class, identifier)).getDocHead();
    else
      return null;
  }

  /**
   * Вывод документа для просмотра
   *
   * @param docHead - документ
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private void internalShowDocument(DocHead docHead) {
    if (docHead != null)
      MaintenanceHelper.view(DocumentUtils.getDocumentService(docHead.getDocSection()), docHead.getId(), null, null);
  }

  /**
   * Получить список документов-оснований
   *
   * @param queryParams - параметры отбора (фильтрация)
   * @return список документов-оснований
   */
  private List<DocListItem> getBaseDocs(Map<String, Object> queryParams) {
    StringBuilder queryText = new StringBuilder().
        append("select A.basedoctype, A.basedocnumber, A.basedocdate, A.currency_code, "). //$NON-NLS-1$
        append("sum(d.summanat) summanat, sum(d.summacur) summacur "). //$NON-NLS-1$
        append("from ( "). //$NON-NLS-1$
        append("select distinct  c.contractor_id cntr_id, d1.basedoctype, "). //$NON-NLS-1$
        append("d1.basedocnumber, d1.basedocdate basedocdate, d1.currency_code "). //$NON-NLS-1$
        append("from contractorcardhist ch, contractorcard c, dochead d1 "). //$NON-NLS-1$
        append("where (ch.dochead_id=d1.id) and (ch.contractorcard_id = c.id) "). //$NON-NLS-1$
        append("and (d1.basedoctype is not null) "). //$NON-NLS-1$
        append("and (c.id = ?) "). //$NON-NLS-1$
        append(") a "). //$NON-NLS-1$
        append("join dochead d on "). //left join ? //$NON-NLS-1$
        append("( ((d.to_id = a.cntr_id) or (d.from_id = a.cntr_id)) and "). //$NON-NLS-1$
        append("(d.doctype = a.basedoctype) and "). //$NON-NLS-1$
        append("(d.docnumber = a.basedocnumber) and "). //$NON-NLS-1$
        append("(d.docdate = a.basedocdate) ) "); //$NON-NLS-1$

    if (queryParams.containsKey(CUR_CODE_FILTER))
      queryText.append("and (d.currency_code = ?) "); //$NON-NLS-1$
    if (queryParams.containsKey(DATE_FROM_FILTER))
      queryText.append("and (d.docdate >= ?) "); //d.basedocdate ? //$NON-NLS-1$
    if (queryParams.containsKey(DATE_TILL_FILTER))
      queryText.append("and (d.docdate <= ?) "); //d.basedocdate ? //$NON-NLS-1$

    queryText.append("group by A.basedoctype, A.basedocnumber, A.basedocdate, A.currency_code"); //$NON-NLS-1$

    return getDocList(queryText.toString(), queryParams.values().toArray());
  }

  /**
   * Получить список контрактов
   *
   * @param queryParams - параметры отбора (фильтрация)
   * @return список контрактов
   */
  private List<DocListItem> getContracts(Map<String, Object> queryParams) {
    StringBuilder queryText = new StringBuilder().
        append("select A.contracttype, A.contractnumber, A.contractdate, A.currency_code, "). //$NON-NLS-1$
        append("sum(d.summanat) summanat, sum(d.summacur) summacur "). //$NON-NLS-1$
        append("from ( "). //$NON-NLS-1$
        append("select distinct  c.contractor_id cntr_id, d1.contracttype, "). //$NON-NLS-1$
        append("d1.contractnumber, d1.contractdate contractdate, d1.currency_code "). //$NON-NLS-1$
        append("from contractorcardhist ch, contractorcard c, dochead d1 "). //$NON-NLS-1$
        append("where (ch.dochead_id=d1.id) and (ch.contractorcard_id = c.id) "). //$NON-NLS-1$
        append("and (d1.contracttype is not null) "). //$NON-NLS-1$
        append("and (c.id = ?) "). //$NON-NLS-1$
        append(") a "). //$NON-NLS-1$
        append("join dochead d on "). //left join ? //$NON-NLS-1$
        append("( ((d.to_id = a.cntr_id) or (d.from_id = a.cntr_id)) and "). //$NON-NLS-1$
        append("(d.doctype = a.contracttype) and "). //$NON-NLS-1$
        append("(d.docnumber = a.contractnumber) and "). //$NON-NLS-1$
        append("(d.docdate = a.contractdate) ) "); //$NON-NLS-1$

    if (queryParams.containsKey(CUR_CODE_FILTER))
      queryText.append("and (d.currency_code = ?) "); //$NON-NLS-1$
    if (queryParams.containsKey(DATE_FROM_FILTER))
      queryText.append("and (d.docdate >= ?) "); //d.contractdate ? //$NON-NLS-1$
    if (queryParams.containsKey(DATE_TILL_FILTER))
      queryText.append("and (d.docdate <= ?) "); //d.contractdate ? //$NON-NLS-1$

    queryText.append("group by A.contracttype, A.contractnumber, A.contractdate, A.currency_code"); //$NON-NLS-1$

    return getDocList(queryText.toString(), queryParams.values().toArray());
  }

  /**
   * Получить список документов
   *
   * @param queryText   - SQL-запрос
   * @param queryParams - параметры запроса
   * @return список документов
   */
  private List<DocListItem> getDocList(String queryText, Object[] queryParams) {
    List<DocListItem> docList = JdbcTemplate.getInstance().query(queryText, queryParams, new RowMapper<DocListItem>() {
      public DocListItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DocListItem(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getBigDecimal(5), rs.getBigDecimal(6));
      }
    });
    return docList;
  }

  private void refreshTables() {
    loadTables();
    ((CardHistTableModel) confirmDocIncomeTable.getModel()).fireModelChange();
    ((CardHistTableModel) confirmDocExpenseTable.getModel()).fireModelChange();
    ((DocTableModel) baseDocTable.getModel()).fireModelChange();
    ((DocTableModel) contractTable.getModel()).fireModelChange();
    ((CardPlanTableModel) planIncomeTable.getModel()).fireModelChange();
    ((CardPlanTableModel) planExpenseTable.getModel()).fireModelChange();
  }

  private void loadTables() {
    ((CardHistTableModel) confirmDocIncomeTable.getModel()).load();
    ((CardHistTableModel) confirmDocExpenseTable.getModel()).load();
    ((DocTableModel) baseDocTable.getModel()).setDocListAndRefresh(getBaseDocs(getQueryMap()));
    ((DocTableModel) contractTable.getModel()).setDocListAndRefresh(getContracts(getQueryMap()));
    ((CardPlanTableModel) planIncomeTable.getModel()).load();
    ((CardPlanTableModel) planExpenseTable.getModel()).load();
  }

  private void refreshBaseDocChildTables() {
    baseDocIncomeTable.getModel().load();
    baseDocExpenseTable.getModel().load();
    ((CardHistTableModel) baseDocIncomeTable.getModel()).fireModelChange();
    ((CardHistTableModel) baseDocExpenseTable.getModel()).fireModelChange();
  }

  private void refreshContractChildTables() {
    contractIncomeTable.getModel().load();
    contractExpenseTable.getModel().load();
    ((CardHistTableModel) contractIncomeTable.getModel()).fireModelChange();
    ((CardHistTableModel) contractExpenseTable.getModel()).fireModelChange();
  }

  private void clearFilter() {
    ((ContractorCard) getEntity()).setDateFrom(null);
    ((ContractorCard) getEntity()).setDateTill(null);
    ((ContractorCard) getEntity()).setCurCode(null);
  }

  private void resetChildTables() {
    ((CardHistTableModel) baseDocIncomeTable.getModel()).clearTable();
    ((CardHistTableModel) baseDocExpenseTable.getModel()).clearTable();
    ((CardHistTableModel) contractIncomeTable.getModel()).clearTable();
    ((CardHistTableModel) contractExpenseTable.getModel()).clearTable();
  }

  private String getDocFilterQueryText() {
    prepareFilter();
    StringBuilder filterQueryText = new StringBuilder();
    setContractorCardParamNameAndValue();
    if (curCode != null) {
      paramsName.add(CUR_CODE_FILTER);
      paramsValue.add(curCode.getCode());
      filterQueryText.append(" and  d.Currency.Code = :curCodeFilter"); //$NON-NLS-1$
    }
    if (dateFrom != null) {
      paramsName.add(DATE_FROM_FILTER);
      paramsValue.add(dateFrom);
      filterQueryText.append(" and  d.DocDate >= :dateFromFilter"); //$NON-NLS-1$
    }
    if (dateTill != null) {
      paramsName.add(DATE_TILL_FILTER);
      paramsValue.add(dateTill);
      filterQueryText.append(" and  d.DocDate <= :dateTillFilter"); //$NON-NLS-1$
    }
    return filterQueryText.toString();
  }

  private void prepareFilter() {
    curCode = ((ContractorCard) getEntity()).getCurCode();
    dateFrom = ((ContractorCard) getEntity()).getDateFrom();
    dateTill = ((ContractorCard) getEntity()).getDateTill();
  }

  private Map<String, Object> getQueryMap() {
    prepareFilter();
    Map<String, Object> queryMap = new HashMap<String, Object>();
    queryMap.put(CONTRACTOR_CARD_ID, getContractorCardIdentifier());
    if (curCode != null)
      queryMap.put(CUR_CODE_FILTER, curCode.getCode());
    if (dateFrom != null)
      queryMap.put(DATE_FROM_FILTER, new java.sql.Date(dateFrom.getTime()));
    if (dateTill != null)
      queryMap.put(DATE_TILL_FILTER, new java.sql.Date(dateTill.getTime()));
    return queryMap;
  }

  private Integer getContractorCardIdentifier() {
    return ((ContractorCard) getEntity()).getId();
  }

  private void setContractorCardParamNameAndValue() {
    clearParamsNameAndValue();
    paramsName.add(CONTRACTOR_CARD);
    paramsValue.add(getEntity());
  }

  private void clearParamsNameAndValue() {
    paramsName.clear();
    paramsValue.clear();
  }

  private void setChildTableParams(Date docDate, String docType, String docNumber) {
    paramsName.add(CONTRACTOR_CARD);
    paramsValue.add(getEntity());
    paramsName.add(DOC_DATE);
    paramsValue.add(docDate);
    paramsName.add(DOC_TYPE);
    paramsValue.add(docType);
    paramsName.add(DOC_NUMBER);
    paramsValue.add(docNumber);
  }

  private void refreshChildTableParams() {
    if (selectedParentItem != null) {
      paramsName.add(DOC_DATE);
      paramsValue.add(selectedParentItem.docDate);
      paramsName.add(DOC_TYPE);
      paramsValue.add(selectedParentItem.docType);
      paramsName.add(DOC_NUMBER);
      paramsValue.add(selectedParentItem.docNumber);
    }
  }

  public class DocListItem {
    String docType;
    String docNumber;
    Date docDate;
    String curCode;
    BigDecimal sumNat;
    BigDecimal sumCur;

    public DocListItem(String docType, String docNumber, Date docDate, String curCode, BigDecimal sumNat, BigDecimal sumCur) {
      this.docType = docType;
      this.docNumber = docNumber;
      this.docDate = docDate;
      this.curCode = curCode;
      this.sumNat = sumNat;
      this.sumCur = sumCur;
    }
  }

}
