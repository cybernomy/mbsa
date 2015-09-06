/*
 * FinOperMt.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.contract.model.Contract;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.finance.SpecFeatureServiceLocal;
import com.mg.merp.finance.SpecificationServiceLocal;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;
import com.mg.merp.finance.model.FinConfig;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.Specification;
import com.mg.merp.finance.support.ConfigurationHelper;
import com.mg.merp.finance.support.Messages;
import com.mg.merp.reference.CurrencyRateNotFoundException;
import com.mg.merp.reference.CurrencyRateServiceLocal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки бизнес - компонента "Финансовая операция"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: FinOperMt.java,v 1.16 2009/03/06 07:19:11 safonov Exp $
 */
public class FinOperMt extends DefaultMaintenanceForm implements MasterModelListener {

  protected AttributeMap specAccountProperties = new LocalDataTransferObject();
  protected AttributeMap specFeatureProperties = new LocalDataTransferObject();
  private MaintenanceTableController specAccount;
  private SpecificationServiceLocal specAccountService;
  private Specification currentSpec;
  private MaintenanceTableController specFeature;
  private SpecFeatureServiceLocal specFeatureService;

  public FinOperMt() throws Exception {
    setMasterDetail(true);
    specAccountService = (SpecificationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Specification"); //$NON-NLS-1$
    specAccount = new MaintenanceTableController(specAccountProperties);
    specAccount.initController(specAccountService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from Specification fs %s where fs.FinOper = :finoper and fs.Parent = null and (fs.SrcAcc.Kind = 0 or fs.SrcAcc = null)"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("finoper"); //$NON-NLS-1$
        paramsValue.add(getEntity());
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
        result.add(new TableEJBQLFieldDef(Specification.class, "Id", "fs.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAcc", "src.Code", "left join fs.SrcAcc as src", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl1", "fs.SrcAnl1", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl2", "fs.SrcAnl2", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl3", "fs.SrcAnl3", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl4", "fs.SrcAnl4", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl5", "fs.SrcAnl5", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "DstAcc", "dst.Code", "left join fs.DstAcc as dst", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Specification.class, "DstAnl1", "fs.DstAnl1", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "DstAnl2", "fs.DstAnl2", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "DstAnl3", "fs.DstAnl3", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "DstAnl4", "fs.DstAnl4", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "DstAnl5", "fs.DstAnl5", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SumNat", "fs.SumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SumCur", "fs.SumCur", false));                 //$NON-NLS-1$ //$NON-NLS-2$

        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specAccountService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      public Object getDecoratorValueAt(int row, int column) {
        switch (column) {
          case 2:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[2], 1, "Anl1Class");
          case 3:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[3], 2, "Anl2Class");
          case 4:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[4], 3, "Anl3Class");
          case 5:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[5], 4, "Anl4Class");
          case 6:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[6], 5, "Anl5Class");
          case 8:
            return getFieldName((String) getRowList().get(row)[7], (Integer) getRowList().get(row)[8], 1, "Anl1Class");
          case 9:
            return getFieldName((String) getRowList().get(row)[7], (Integer) getRowList().get(row)[9], 2, "Anl2Class");
          case 10:
            return getFieldName((String) getRowList().get(row)[7], (Integer) getRowList().get(row)[10], 3, "Anl3Class");
          case 11:
            return getFieldName((String) getRowList().get(row)[7], (Integer) getRowList().get(row)[11], 4, "Anl4Class");
          case 12:
            return getFieldName((String) getRowList().get(row)[7], (Integer) getRowList().get(row)[12], 5, "Anl5Class");
          default:
            return super.getDecoratorValueAt(row, column);
        }
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getColumnMetadata(int)
       */
      @Override
      public FieldMetadata getColumnMetadata(int column) {
        switch (column) {
          case 2:
          case 3:
          case 4:
          case 5:
          case 6:
          case 8:
          case 9:
          case 10:
          case 11:
          case 12:
            return null;
          default:
            return super.getColumnMetadata(column);
        }
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getValueAt(int, int)
       */
      @Override
      public String getColumnName(int column) {
        switch (column) {
          case 2:
            return Messages.getInstance().getMessage(Messages.SRCACCOUNT_ANALYTICS1);
          case 3:
            return Messages.getInstance().getMessage(Messages.SRCACCOUNT_ANALYTICS2);
          case 4:
            return Messages.getInstance().getMessage(Messages.SRCACCOUNT_ANALYTICS3);
          case 5:
            return Messages.getInstance().getMessage(Messages.SRCACCOUNT_ANALYTICS4);
          case 6:
            return Messages.getInstance().getMessage(Messages.SRCACCOUNT_ANALYTICS5);
          case 8:
            return Messages.getInstance().getMessage(Messages.DSTACCOUNT_ANALYTICS1);
          case 9:
            return Messages.getInstance().getMessage(Messages.DSTACCOUNT_ANALYTICS2);
          case 10:
            return Messages.getInstance().getMessage(Messages.DSTACCOUNT_ANALYTICS3);
          case 11:
            return Messages.getInstance().getMessage(Messages.DSTACCOUNT_ANALYTICS4);
          case 12:
            return Messages.getInstance().getMessage(Messages.DSTACCOUNT_ANALYTICS5);
          default:
            return super.getColumnName(column);
        }
      }

    });

    addMasterModelListener(specAccount);

    specFeatureService = (SpecFeatureServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/SpecFeature"); //$NON-NLS-1$
    specFeature = new MaintenanceTableController(specFeatureProperties);
    specFeature.initController(specFeatureService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from Specification fs %s where fs.Parent = :parent and fs.SrcAcc.Kind = 1"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("parent"); //$NON-NLS-1$
        paramsValue.add(currentSpec);
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
        result.add(new TableEJBQLFieldDef(Specification.class, "Id", "fs.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAcc", "fs.SrcAcc.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl1", "fs.SrcAnl1", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl2", "fs.SrcAnl2", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl3", "fs.SrcAnl3", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl4", "fs.SrcAnl4", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SrcAnl5", "fs.SrcAnl5", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SumNat", "fs.SumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Specification.class, "SumCur", "fs.SumCur", false));                 //$NON-NLS-1$ //$NON-NLS-2$

        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specFeatureService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDecoratorValueAt(int, int)
       */
      @Override
      public Object getDecoratorValueAt(int row, int column) {
        switch (column) {
          case 2:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[2], 1, "Anl1Class");
          case 3:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[3], 2, "Anl2Class");
          case 4:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[4], 3, "Anl3Class");
          case 5:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[5], 4, "Anl4Class");
          case 6:
            return getFieldName((String) getRowList().get(row)[1], (Integer) getRowList().get(row)[6], 5, "Anl5Class");
          default:
            return super.getDecoratorValueAt(row, column);
        }
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getColumnMetadata(int)
       */
      @Override
      public FieldMetadata getColumnMetadata(int column) {
        switch (column) {
          case 2:
          case 3:
          case 4:
          case 5:
          case 6:
            return null;
          default:
            return super.getColumnMetadata(column);
        }
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getColumnName(int)
       */
      @Override
      public String getColumnName(int column) {
        switch (column) {
          case 1:
            return Messages.getInstance().getMessage(Messages.FEATURE);
          case 2:
            return Messages.getInstance().getMessage(Messages.FEATURE_ANALYTICS1);
          case 3:
            return Messages.getInstance().getMessage(Messages.FEATURE_ANALYTICS2);
          case 4:
            return Messages.getInstance().getMessage(Messages.FEATURE_ANALYTICS3);
          case 5:
            return Messages.getInstance().getMessage(Messages.FEATURE_ANALYTICS4);
          case 6:
            return Messages.getInstance().getMessage(Messages.FEATURE_ANALYTICS5);
          default:
            return super.getColumnName(column);
        }
      }


    });
    specAccount.addMasterModelListener(new MasterModelListener() {

      public void masterChange(ModelChangeEvent event) {
        currentSpec = event.getModelKey() == null ? null : specAccountService.load((Integer) event.getModelKey());
        specFeatureProperties.put("Parent.Id", (Integer) event.getModelKey()); //$NON-NLS-1$
        specFeature.refresh();
      }

    });

    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    specAccountProperties.put("FinOper", event.getEntity()); //$NON-NLS-1$
    specFeatureProperties.put("FinOper", event.getEntity()); //$NON-NLS-1$

  }

  /**
   * Обработчик курса валют
   */
  public void onActionChooseCurrencyRate(WidgetEvent event) {
    FinOperation entity = (FinOperation) this.getEntity();
    FinConfig cfg = ConfigurationHelper.getConfiguration();
    if (cfg != null) {
      CurrencyRateServiceLocal currencyRateService = (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/CurrencyRate"); //$NON-NLS-1$
      try {
        //пытаемся получить прямой курс для валют
        entity.setCurRate(currencyRateService.getCurrencyRate(cfg.getNatCurrency(), entity.getCurrency(), entity.getCurRateAuthority(), entity.getCurRateType(), entity.getKeepDate()));
      } catch (CurrencyRateNotFoundException e) {
        //если прямого курса нет, то пытаемся получить обратный курс
        entity.setCurRate(MathUtils.divide(BigDecimal.ONE, currencyRateService.getCurrencyRate(entity.getCurrency(), cfg.getNatCurrency(), entity.getCurRateAuthority(), entity.getCurRateType(), entity.getKeepDate()), new RoundContext(CurrencyRateServiceLocal.DEFAULT_RATE_SCALE)));
      }
    } else {
      //если нет конфигурации, то вообще непонятно как обработать
      entity.setCurRate(BigDecimal.ONE);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnClone()
   */
  @Override
  protected void doOnClone() {
    view.getWidget("Planned").setEnabled(false);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
   */
  @Override
  protected void doOnEdit() {
    view.getWidget("Planned").setEnabled(false);
  }

  /**
   * Возвращает код аналитики
   *
   * @param accountCode - код счета
   * @param anlId       - идентификатор аналитики
   * @param anlLevel    - уровень аналитики
   * @param anlClass    - класс аналитики
   */
  protected String getFieldName(String accountCode, Integer anlId, int anlLevel, String anlClass) {
    List<Account> accList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Account.class)
        .add(Restrictions.eq("Code", accountCode))
        .setMaxResults(1));
    if (accList.isEmpty() || anlId == null)
      return StringUtils.EMPTY_STRING;
    Account account = accList.get(0);
    if (anlId != null) {
      if ((account.isAnl1Kind() && anlLevel == 1) || (account.isAnl2Kind() && anlLevel == 2) || (account.isAnl3Kind() && anlLevel == 3) || (account.isAnl4Kind() && anlLevel == 4) || (account.isAnl5Kind() && anlLevel == 5)) {
        return FinUtils.getAnlName(FinUtils.getBeanName((SysClass) account.getAttribute(anlClass)), anlId).trim();
      } else {
        return ServerUtils.getPersistentManager().find(Analytics.class, anlId).getCode().trim();
      }
    } else
      return StringUtils.EMPTY_STRING;
  }

  /**
   * Обработчик просмотра документа-основания
   *
   * @param event - событие
   */
  public void onActionViewBaseDocument(WidgetEvent event) {
    DocumentUtils.viewDocument(((FinOperation) getEntity()).getBaseDoc());
  }

  /**
   * Обработчик просмотра документа
   *
   * @param event - событие
   */
  public void onActionViewConfirmDocument(WidgetEvent event) {
    DocumentUtils.viewDocument(((FinOperation) getEntity()).getConfirmDoc());
  }

  /**
   * Обработчик просмотра/выбора контракта
   *
   * @param event - событие
   */
  public void onActionViewOrChooseContract(WidgetEvent event) {
    final FinOperation finOperation = (FinOperation) getEntity();
    if (finOperation.getContractId() != null) {
      Contract contract = ServerUtils.getPersistentManager().find(Contract.class, finOperation.getContractId());
      DocumentUtils.viewDocument(contract);
    } else
      DocumentUtils.chooseContract(new SearchHelpListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
         */
        public void searchCanceled(SearchHelpEvent event) {
          // do nothing
        }

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
         */
        public void searchPerformed(SearchHelpEvent event) {
          DocHead contract = (DocHead) event.getItems()[0];
          finOperation.setContractType(contract.getDocType());
          finOperation.setContractNumber(contract.getDocNumber().trim());
          finOperation.setContractDate(contract.getDocDate());
          view.flushModel();
        }
      });
  }

}
