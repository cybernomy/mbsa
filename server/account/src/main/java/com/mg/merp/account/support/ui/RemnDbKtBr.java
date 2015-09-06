/*
 * RemnDbKtBr.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.OperationServiceLocal;
import com.mg.merp.account.RemnDbKtServiceLocal;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnDbKt;
import com.mg.merp.account.support.Messages;
import com.mg.merp.core.model.Folder;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.support.ui.FolderByTypeSearchHelp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы списка бизнес-компонента "Ведомости расчета с контрагентами"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @author Artem V. Sharapov
 * @version $Id: RemnDbKtBr.java,v 1.8 2009/03/12 07:18:55 sharapov Exp $
 */
public class RemnDbKtBr extends AbstractPlainBrowseFormWithTurn {

  private final String INIT_QUERY_TEXT_FOR_INTERBASE = "select %s from acc_remndbkt_select(%s, %s, %s) ra where %s order by ra.datefrom"; //$NON-NLS-1$
  private final String INIT_QUERY_TEXT_FOR_ORACLE = "select %s from table(acc_remndbkt_select(%s, %s, %s)) ra where %s order by ra.datefrom"; //$NON-NLS-1$
  private List<Object> paramsValue = new ArrayList<Object>();
  private RemnDbKtServiceLocal serviceRemnDbKt = null;

  public RemnDbKtBr() {
    super();
    restrictionFormName = "com/mg/merp/account/resources/RemnDbKtRest.mfd.xml"; //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {

    paramsValue.clear();
    String fields = " ra.ID, ra.PNAME, ra.DATEFROM, ra.DATETO, ra.ACC, " + //$NON-NLS-1$
        "ra.ANL1_CODE, ra.ANL2_CODE, ra.ANL3_CODE, ra.ANL4_CODE, ra.ANL5_CODE, " + //$NON-NLS-1$
        "ra.CURCODE, ra.REMNBEGINNATDB, ra.REMNBEGINNATKT, ra.REMNENDNATDB, ra.REMNENDNATKT," + //$NON-NLS-1$
        "ra.REMNBEGINCURDB, ra.REMNBEGINCURKT, ra.REMNENDCURDB, ra.REMNENDCURKT," + //$NON-NLS-1$
        "ra.TURNNATDB, ra.TURNNATKT, ra.TURNCURDB, ra.TURNCURKT, ra.DOCTYPE, ra.DOCNUMBER, ra.DOCDATE, " + //$NON-NLS-1$
        "ra.DOCBASETYPE, ra.DOCBASENUMBER, ra.DOCBASEDATE, ra.CONTRACTORCODE "; //$NON-NLS-1$
    RemnDbKtRest restForm = (RemnDbKtRest) getRestrictionForm();
    AccPlan accPlan = restForm.getAccCode();
    Integer periodId1 = restForm.getPeriodId1() != null ? restForm.getPeriodId1().getId() : null;
    Integer periodId2 = restForm.getPeriodId2() != null ? restForm.getPeriodId2().getId() : null;
    Integer accountId = accPlan != null ? accPlan.getId() : null;
    StringBuilder whereText = new StringBuilder("(0=0)"); //$NON-NLS-1$
    Currency currency = restForm.getCurrencyCode();
    if (currency != null) {
      whereText.append("and (ra.CURCODE = ?)"); //$NON-NLS-1$
      paramsValue.add(currency.getCode());
    }
    if (accPlan != null) {
      AnlPlan anlplan = restForm.getAnlCode1();
      if (anlplan != null) {
        whereText.append("and (ra.ANL1_CODE = ?)"); //$NON-NLS-1$
        paramsValue.add(anlplan.getCode());
      }
      anlplan = restForm.getAnlCode2();
      if (anlplan != null) {
        whereText.append("and (ra.ANL2_CODE = ?)"); //$NON-NLS-1$
        paramsValue.add(anlplan.getCode());
      }
      anlplan = restForm.getAnlCode3();
      if (anlplan != null) {
        whereText.append("and (ra.ANL3_CODE = ?)"); //$NON-NLS-1$
        paramsValue.add(anlplan.getCode());
      }
      anlplan = restForm.getAnlCode4();
      if (anlplan != null) {
        whereText.append("and (ra.ANL4_CODE = ?)"); //$NON-NLS-1$
        paramsValue.add(anlplan.getCode());
      }
      anlplan = restForm.getAnlCode5();
      if (anlplan != null) {
        whereText.append("and (ra.ANL5_CODE = ?)"); //$NON-NLS-1$
        paramsValue.add(anlplan.getCode());
      }
    }
    //Контрагент
    Contractor contractorCode = restForm.getContractorCode();
    if (contractorCode != null) {
      whereText.append("and (ra.CONTRACTORCODE = ?)"); //$NON-NLS-1$
      paramsValue.add(contractorCode.getCode());
    }
    //Вид сальдо
    switch (restForm.getBalanceKind()) {
      case 1:
        whereText.append("and ((ra.REMNENDNATDB - ra.REMNENDNATKT) > 0)"); //$NON-NLS-1$
        break;
      case 2:
        whereText.append("and ((ra.REMNENDNATDB - ra.REMNENDNATKT) < 0)"); //$NON-NLS-1$
        break;
      case 3:
        whereText.append("and ((ra.REMNENDNATDB - ra.REMNENDNATKT) = 0)"); //$NON-NLS-1$
        break;
      case 4:
        whereText.append("and ((ra.REMNENDNATDB - ra.REMNENDNATKT) <> 0)"); //$NON-NLS-1$
        break;
    }
    if (restForm.getBaseDocType() != null) {
      whereText.append("and (ra.DOCBASETYPE = ?)"); //$NON-NLS-1$
      paramsValue.add(restForm.getBaseDocType().getCode());
    }
    if (!StringUtils.stringNullOrEmpty(restForm.getBaseDocNumber())) {
      whereText.append("and (ra.DOCBASENUMBER = ?)"); //$NON-NLS-1$
      paramsValue.add(restForm.getBaseDocNumber());
    }
    if (restForm.getBaseDocDate() != null) {
      whereText.append("and (ra.DOCBASEDATE = ?)"); //$NON-NLS-1$
      paramsValue.add(restForm.getBaseDocDate());
    }
    if (restForm.getDocType() != null) {
      whereText.append("and (ra.DOCTYPE = ?)"); //$NON-NLS-1$
      paramsValue.add(restForm.getDocType().getCode());
    }
    if (!StringUtils.stringNullOrEmpty(restForm.getDocNumber())) {
      whereText.append("and (ra.CONTRACTORCODE = ?)"); //$NON-NLS-1$
      paramsValue.add(restForm.getDocNumber());
    }
    if (restForm.getDocDate() != null) {
      whereText.append("and (ra.CONTRACTORCODE = ?)"); //$NON-NLS-1$
      paramsValue.add(restForm.getDocDate());
    }
    String queryText = StringUtils.format(INIT_QUERY_TEXT_FOR_INTERBASE, fields, periodId1, periodId2, accountId, whereText.toString());
    switch (DatabaseUtils.getDBMSType()) {
      case INTERBASE:
        return queryText;
      case FIREBIRD:
        return queryText;
      case ORACLE:
        return StringUtils.format(INIT_QUERY_TEXT_FOR_ORACLE, fields, periodId1, periodId2, accountId, whereText.toString());
      default:
        throw new UnsupportedOperationException();
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "Id", "ra.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "Period", "ra.Period.Name", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Period.class, "DateFrom", "p.DateFrom", "left join ra.Period as p", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Period.class, "DateTo", "p1.DateTo", "left join ra.Period as p1", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "AccPlan", "ra.AccPlan.Acc", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "AnlPlan1", "anl1.Code", "left join ra.AnlPlan1 as anl1", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "AnlPlan2", "anl2.Code", "left join ra.AnlPlan2 as anl2", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "AnlPlan3", "anl3.Code", "left join ra.AnlPlan3 as anl3", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "AnlPlan4", "anl4.Code", "left join ra.AnlPlan4 as anl4", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "AnlPlan5", "anl5.Code", "left join ra.AnlPlan5 as anl5", true));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "AccPlan.Currency", "cur.Code", "left join ra.AccPlan.Currency as cur", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "RemnBeginNatDb", "ra.RemnBeginNatDb", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "RemnBeginNatKt", "ra.RemnBeginNatKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "RemnEndNatDb", "ra.RemnEndNatDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "RemnEndNatKt", "ra.RemnEndNatKt", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "RemnBeginCurDb", "ra.RemnBeginCurDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "RemnBeginCurKt", "ra.RemnBeginCurKt", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "RemnEndCurDb", "ra.RemnEndCurDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "RemnEndCurKt", "ra.RemnEndCurKt", true));     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "TurnNatDb", "ra.TurnNatDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "TurnNatKt", "ra.TurnNatKt", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "TurnCurDb", "ra.TurnCurDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "TurnCurKt", "ra.TurnCurKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "DocType", "dt.Code", "left join ra.DocType as dt", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "DocNumber", "ra.DocNumber", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "DocDate", "ra.DocDate", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "DocBaseType", "dbt.Code", "left join ra.DocBaseType as dbt", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "DocBaseNumber", "ra.DocBaseNumber", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "DocBaseDate", "ra.DocBaseDate", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnDbKt.class, "Contractor", "ra.Contractor.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$

        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        List<Object[]> listRow = JdbcTemplate.getInstance().query(createQueryText(), paramsValue.toArray(new Object[paramsValue.size()]), new RowMapper<Object[]>() {

          public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Object[]{rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getString(6),
                rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                rs.getString(11), rs.getBigDecimal(12), rs.getBigDecimal(13), rs.getBigDecimal(14),
                rs.getBigDecimal(15), rs.getBigDecimal(16), rs.getBigDecimal(17),
                rs.getBigDecimal(18), rs.getBigDecimal(19), rs.getBigDecimal(20),
                rs.getBigDecimal(21), rs.getBigDecimal(22), rs.getBigDecimal(23),
                rs.getString(24), rs.getString(25), rs.getDate(26),
                rs.getString(27), rs.getString(28), rs.getDate(29), rs.getString(30)};
          }
        });
        setRowList(listRow);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    };
  }

  /**
   * Вызов сервиса бизнес-компонента "Остатки и обороты по счетам бух. учета"
   */
  protected RemnDbKtServiceLocal getRemnAccService() {
    if (serviceRemnDbKt == null)
      return (RemnDbKtServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnDbKt"); //$NON-NLS-1$
    return serviceRemnDbKt;
  }

  /**
   * Обработчик кнопки контекстного меню "Удалить пустые строки"
   */
  public void onActionDeleteEmptyStrings(WidgetEvent event) throws ApplicationException {
    RemnDbKtRest remnRest = (RemnDbKtRest) getRestrictionForm();
    getRemnAccService().removeEmptyRecords(remnRest.getPeriodId1(), remnRest.getPeriodId2());
    table.refresh();
  }

  /**
   * Обработчик кнопки контекстного меню "Закрыть задолжность по контрагенту"
   */
  public void onActionCloseDebet(WidgetEvent event) throws Exception {
    if (getSearchedEntities().length != 1)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.NOT_SEARCHED_ENTITIES));
    final OperationServiceLocal operationService = (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Operation");
    final RemnDbKt remnDbKt = (RemnDbKt) getSearchedEntities()[0];
    FolderByTypeSearchHelp folderSearchHelper = new FolderByTypeSearchHelp() {
      /*
       * (non-Javadoc)
       * @see com.mg.merp.reference.support.ui.FolderByTypeSearchHelp#getFolderType()
       */
      @Override
      protected short getFolderType() {
        return OperationServiceLocal.FOLDER_PART;
      }
    };
    folderSearchHelper.addSearchHelpListener(new SearchHelpListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchCanceled(SearchHelpEvent event) {
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchPerformed(SearchHelpEvent event) {
        final EconomicOper economicOper = operationService.addFromRemnDbKt(remnDbKt, (Folder) event.getItems()[0]);
        if (economicOper != null)
          MaintenanceHelper.edit(operationService, economicOper.getId(), null, new MaintenanceFormActionListener() {

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.MaintenanceFormActionListener#canceled(com.mg.framework.api.ui.MaintenanceFormEvent)
             */
            public void canceled(MaintenanceFormEvent event) {
              operationService.erase(economicOper.getId());
            }

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.MaintenanceFormActionListener#performed(com.mg.framework.api.ui.MaintenanceFormEvent)
             */
            public void performed(MaintenanceFormEvent event) {
            }
          });
      }
    });
    folderSearchHelper.search();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AbstractPlainBrowseFormWithTurn#doGetTurnDbWhereText(java.util.List)
   */
  @Override
  protected String doGetTurnDbWhereText(List<String> paramsName) {
    paramsName.add("remnId");
    return " where es.RemnDb.Id = :remnId";
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AbstractPlainBrowseFormWithTurn#doGetTurnKtWhereText(java.util.List)
   */
  @Override
  protected String doGetTurnKtWhereText(List<String> paramsName) {
    paramsName.add("remnId");
    return " where es.RemnKt.Id = :remnId";
  }

}
