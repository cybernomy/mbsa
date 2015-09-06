/*
 * RemnAnlBr.java
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
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.AccountServiceLocal;
import com.mg.merp.account.RemnAnlServiceLocal;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnAnl;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.reference.model.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы списка бизнес-компонента "Остатки и обороты по аналитическим счетам"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @author Artem V. Sharapov
 * @version $Id: RemnAnlBr.java,v 1.8 2009/03/12 07:18:55 sharapov Exp $
 */
public class RemnAnlBr extends AbstractHierarchyBrowseFormWithTurn {
  private final String INIT_QUERY_TEXT_FOR_INTERBASE = "select %s from acc_remnanl_select(%s, %s, %s, %s) ra where %s order by ra.datefrom"; //$NON-NLS-1$
  private final String INIT_QUERY_TEXT_FOR_ORACLE = "select %s from table(acc_remnanl_select(%s, %s, %s, %s)) ra where %s order by ra.datefrom"; //$NON-NLS-1$
  private List<Object> paramsValue = new ArrayList<Object>();

  private RemnAnlServiceLocal serviceRemnAnl = null;

  @SuppressWarnings("unchecked")
  public RemnAnlBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
    treeUIProperties.put("FolderType", AccountServiceLocal.FOLDER_PART); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/account/resources/RemnAnlRest.mfd.xml"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return CoreUtils.loadFolderHierarchy(AccountServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    paramsValue.clear();

    String fields = " ra.ID, ra.PNAME, ra.DATEFROM, ra.DATETO, ra.ACC, ra.CURCODE, " + //$NON-NLS-1$
        "ra.ANL1_CODE, ra.ANL2_CODE, ra.ANL3_CODE, ra.ANL4_CODE, ra.ANL5_CODE, " + //$NON-NLS-1$
        "ra.REMNBEGINNATDB, ra.REMNBEGINNATKT, ra.REMNENDNATDB, ra.REMNENDNATKT, ra.TURNNATDB," + //$NON-NLS-1$
        "ra.TURNNATKT, ra.REMNBEGINCURDB, ra.REMNBEGINCURKT, " + //$NON-NLS-1$
        "ra.REMNENDCURDB, ra.REMNENDCURKT, ra.TURNCURDB, ra.TURNCURKT "; //$NON-NLS-1$
    RemnAnlRest restForm = (RemnAnlRest) getRestrictionForm();
    boolean restIsHierarchy = ((HierarchyRestrictionSupport) restForm).isUseHierarchy();
    AccPlan accPlan = restForm.getAccCode();
    Integer periodId1 = restForm.getPeriod1() != null ? restForm.getPeriod1().getId() : null;
    Integer periodId2 = restForm.getPeriod2() != null ? restForm.getPeriod2().getId() : null;
    Integer accountId = accPlan != null ? accPlan.getId() : null;
    StringBuilder whereText = new StringBuilder("(0=0)"); //$NON-NLS-1$
    Currency currency = restForm.getCurrencyCode();
    Integer folderId = null;
    if (restIsHierarchy) {
      folderId = (Integer) folderEntity.getPrimaryKey();
    }
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
    String queryText = StringUtils.format(INIT_QUERY_TEXT_FOR_INTERBASE, fields, periodId1, periodId2, accountId, folderId, whereText.toString());
    switch (DatabaseUtils.getDBMSType()) {
      case INTERBASE:
        return queryText;
      case FIREBIRD:
        return queryText;
      case ORACLE:
        return StringUtils.format(INIT_QUERY_TEXT_FOR_ORACLE, fields, periodId1, periodId2, accountId, folderId, whereText.toString());
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
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "Id", "ra.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "Period", "ra.Period.Name", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Period.class, "DateFrom", "p.DateFrom", "left join ra.Period as p", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Period.class, "DateTo", "p1.DateTo", "left join ra.Period as p1", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "AccPlan", "ra.AccPlan.Acc", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(AccPlan.class, "Currency", "acp.Currency.Code", "left join ra.AccPlan as acp", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "AnlPlan1", "anl1.Code", "left join ra.AnlPlan1 as anl1", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "AnlPlan2", "anl2.Code", "left join ra.AnlPlan2 as anl2", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "AnlPlan3", "anl3.Code", "left join ra.AnlPlan3 as anl3", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "AnlPlan4", "anl4.Code", "left join ra.AnlPlan4 as anl4", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "AnlPlan5", "anl5.Code", "left join ra.AnlPlan5 as anl5", true));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "RemnBeginNatDb", "ra.RemnBeginNatDb", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "RemnBeginNatKt", "ra.RemnBeginNatKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "RemnEndNatDb", "ra.RemnEndNatDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "RemnEndNatKt", "ra.RemnEndNatKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "TurnNatKt", "ra.TurnNatKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "TurnNatDb", "ra.TurnNatDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "RemnBeginCurDb", "ra.RemnBeginCurDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "RemnBeginCurKt", "ra.RemnBeginCurKt", true));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "RemnEndCurDb", "ra.RemnEndCurDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "RemnEndCurKt", "ra.RemnEndCurKt", true));     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "TurnCurDb", "ra.TurnCurDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RemnAnl.class, "TurnCurKt", "ra.TurnCurKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
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
                rs.getBigDecimal(12), rs.getBigDecimal(13), rs.getBigDecimal(14),
                rs.getBigDecimal(15), rs.getBigDecimal(16), rs.getBigDecimal(17), rs.getBigDecimal(18), rs.getBigDecimal(19), rs.getBigDecimal(20),
                rs.getBigDecimal(21), rs.getBigDecimal(22), rs.getBigDecimal(23)};
          }
        });
        setRowList(listRow);
      }
    };
  }

  /**
   * Вызов сервиса бизнес-компонента "Остатки и обороты по счетам бух. учета"
   */
  protected RemnAnlServiceLocal getRemnAnlService() {
    if (serviceRemnAnl == null)
      return (RemnAnlServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnAnl"); //$NON-NLS-1$
    return serviceRemnAnl;
  }

  /**
   * Обработчик кнопки контекстного меню "Удалить пустые строки"
   */
  public void onActionDeleteEmptyStrings(WidgetEvent event) throws ApplicationException {
    RemnAnlRest remnRest = (RemnAnlRest) getRestrictionForm();
    getRemnAnlService().removeEmptyRecords(remnRest.getPeriod1(), remnRest.getPeriod2());
    table.refresh();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AbstractHierarchyBrowseFormWithTurn#doGetTurnDbWhereText(java.util.List)
   */
  @Override
  protected String doGetTurnDbWhereText(List<String> paramsName) {
    paramsName.add("remnId");
    return " where es.RemnAnlDb.Id = :remnId";
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AbstractHierarchyBrowseFormWithTurn#doGetTurnKtWhereText(java.util.List)
   */
  @Override
  protected String doGetTurnKtWhereText(List<String> paramsName) {
    paramsName.add("remnId");
    return " where es.RemnAnlKt.Id = :remnId";
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.support.ui.AbstractHierarchyBrowseFormWithTurn#doGetTurnWhereText(java.util.List)
   */
  @Override
  protected String doGetTurnWhereText(List<String> paramsName) {
    paramsName.add("remnId");
    return " where es.RemnAnlDb.Id = :remnId or es.RemnAnlKt.Id = :remnId";
  }

}
