/*
 * RemnValBr.java
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
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.ProcessorServiceLocal;
import com.mg.merp.account.RemnValServiceLocal;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnVal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы списка бизнес-компонента "Остатки и обороты по товарно-материальным ценностям"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @author Artem V. Sharapov
 * @version $Id: RemnValBr.java,v 1.8 2009/03/12 07:18:55 sharapov Exp $
 */
public class RemnValBr extends AbstractPlainBrowseFormWithTurn {

  private final String INIT_QUERY_TEXT_FOR_INTERBASE = "select %s from acc_remnval_select(%s, %s, %s) ra where %s order by ra.datefrom"; //$NON-NLS-1$
  private final String INIT_QUERY_TEXT_FOR_ORACLE = "select %s from table(acc_remnval_select(%s, %s, %s)) ra where %s order by ra.datefrom"; //$NON-NLS-1$
  private List<Object> paramsValue = new ArrayList<Object>();
  private RemnValServiceLocal serviceRemnVal = null;

  public RemnValBr() {
    super();
    restrictionFormName = "com/mg/merp/account/resources/RemnValRest.mfd.xml"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    paramsValue.clear();

    String fields = " ra.ID, ra.PNAME, ra.DATEFROM, ra.DATETO, ra.ACC, ra.CURCODE," + //$NON-NLS-1$
        "ra.ANL1_CODE, ra.ANL2_CODE, ra.ANL3_CODE, ra.ANL4_CODE, ra.ANL5_CODE, " + //$NON-NLS-1$
        "ra.CATCODE, ra.CATNAME, ra.CATMEAS, ra.BEGINQUAN, ra.ENDQUAN, ra.QUANTITYDB, " + //$NON-NLS-1$
        "ra.QUANTITYKT, ra.REMNBEGINNAT, ra.REMNENDNAT, ra.TURNNATDB, ra.TURNNATKT, " + //$NON-NLS-1$
        "ra.REMNBEGINCUR, ra.REMNENDCUR, ra.TURNCURDB, ra.TURNCURKT, ra.CONTRACTORCODE"; //$NON-NLS-1$
    RemnValRest restForm = (RemnValRest) getRestrictionForm();
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
    //КТУ
    Catalog catalogCode = restForm.getCatalogName();
    if (catalogCode != null) {
      whereText.append("and (ra.CATCODE = ?)"); //$NON-NLS-1$
      paramsValue.add(catalogCode.getCode());
    } else if (restForm.getCatalogFolder() != null) {
      whereText.append("and (ra.catalogfolder_id in (select cf.folder_id from f_nested_catfolders(?) cf))"); //$NON-NLS-1$
      paramsValue.add(restForm.getCatalogFolder().getId());
    }
    //Вид сальдо
    switch (restForm.getBalanceKind()) {
      case 1:
        whereText.append("and (ra.remnendnat > 0)"); //$NON-NLS-1$
        break;
      case 2:
        whereText.append("and (ra.remnendnat < 0)"); //$NON-NLS-1$
        break;
      case 3:
        whereText.append("and (ra.remnendnat = 0)"); //$NON-NLS-1$
        break;
      case 4:
        whereText.append("and (ra.remnendnat <> 0)"); //$NON-NLS-1$
        break;
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
        result.add(new TableEJBQLFieldDef(RemnVal.class, "Id", "ra.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "Period", "ra.Period.Name", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Period.class, "DateFrom", "p.DateFrom", "left join ra.Period as p", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Period.class, "DateTo", "p1.DateTo", "left join ra.Period as p1", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "AccPlan", "ra.AccPlan.Acc", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(AccPlan.class, "Currency", "acp.Currency.Code", "left join ra.AccPlan as acp", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "AnlPlan1", "anl1.Code", "left join ra.AnlPlan1 as anl1", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "AnlPlan2", "anl2.Code", "left join ra.AnlPlan2 as anl2", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "AnlPlan3", "anl3.Code", "left join ra.AnlPlan3 as anl3", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "AnlPlan4", "anl4.Code", "left join ra.AnlPlan4 as anl4", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "AnlPlan5", "anl5.Code", "left join ra.AnlPlan5 as anl5", true));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Catalog.class, "Code", "", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Catalog.class, "FullName", "", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Catalog.class, "Measure1", "ra.Catalog.Code", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "BeginQuan", "ra.BeginQuan", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "EndQuan", "ra.EndQuan", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "QuantityDb", "ra.QuantityDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "QuantityKt", "ra.QuantityKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "RemnBeginNat", "ra.RemnBeginNat", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "RemnEndNat", "ra.RemnEndNat", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "TurnNatDb", "ra.TurnNatDb", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "TurnNatKt", "ra.TurnNatKt", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "RemnBeginCur", "ra.RemnBeginCur", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "RemnEndCur", "ra.RemnEndCur", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "TurnCurDb", "ra.TurnCurDb", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "TurnCurKt", "ra.TurnCurKt", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnVal.class, "Contractor", "ra.Contractor.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$

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
                rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                rs.getString(12), rs.getString(13), rs.getString(14),
                rs.getBigDecimal(15), rs.getBigDecimal(16), rs.getBigDecimal(17),
                rs.getBigDecimal(18), rs.getBigDecimal(19), rs.getBigDecimal(20),
                rs.getBigDecimal(21), rs.getBigDecimal(22), rs.getBigDecimal(23),
                rs.getBigDecimal(24), rs.getBigDecimal(25), rs.getBigDecimal(26), rs.getString(27)};
          }
        });
        setRowList(listRow);
      }
    };
  }

  /**
   * Вызов сервиса бизнес-компонента "Остатки и обороты по счетам бух. учета"
   */
  protected RemnValServiceLocal getRemnValService() {
    if (serviceRemnVal == null)
      return (RemnValServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnVal"); //$NON-NLS-1$
    return serviceRemnVal;
  }

  /**
   * Обработчик кнопки контекстного меню "Удалить пустые строки"
   */
  public void onActionDeleteEmptyStrings(WidgetEvent event) throws ApplicationException {
    RemnValRest remnRest = (RemnValRest) getRestrictionForm();
    getRemnValService().removeEmptyRecords(remnRest.getPeriodId1(), remnRest.getPeriodId2());
    table.refresh();
  }

  /**
   * Обработчик кнопки контекстного меню "Пересчитать цены списания"
   */
  public void onActionEvaluateOutCost(WidgetEvent event) throws ApplicationException {
    PersistentObject[] entities = getSearchedEntities();
    if (entities.length > 0) {
      ProcessorServiceLocal service = (ProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Processor");
      for (int i = 0; i < entities.length; i++)
        service.evaluateOutCost((RemnVal) entities[i]);
      table.refresh();
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AbstractPlainBrowseFormWithTurn#doGetTurnDbWhereText(java.util.List)
   */
  @Override
  protected String doGetTurnDbWhereText(List<String> paramsName) {
    paramsName.add("remnId");
    return " where es.RemnValDb.Id = :remnId";
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AbstractPlainBrowseFormWithTurn#doGetTurnKtWhereText(java.util.List)
   */
  @Override
  protected String doGetTurnKtWhereText(List<String> paramsName) {
    paramsName.add("remnId");
    return " where es.RemnValKt.Id = :remnId";
  }

}