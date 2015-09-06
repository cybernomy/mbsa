/*
 * EconomicOperMt.java
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

import com.mg.framework.api.AttributeMap;
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
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.EconomicSpecServiceLocal;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.contract.model.Contract;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки хозяйственных операций
 *
 * @author leonova
 * @version $Id: EconomicOperMt.java,v 1.10 2009/04/27 07:37:30 safonov Exp $
 */
public class EconomicOperMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap specProperties = new LocalDataTransferObject();
  private MaintenanceTableController spec;
  private EconomicSpecServiceLocal specService;

  public EconomicOperMt() throws Exception {
    setMasterDetail(true);
    addMasterModelListener(this);

    specService = (EconomicSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpec"); //$NON-NLS-1$
    spec = new MaintenanceTableController(specProperties);
    spec.initController(specService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from EconomicSpec es %s where es.EconomicOper = :operation"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("operation"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Id", "es.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AccDb", "db.Acc", "left join es.AccDb as db", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AccKt", "kt.Acc", "left join es.AccKt as kt", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb1", "db1.Code", "left join es.AnlDb1 as db1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb2", "db2.Code", "left join es.AnlDb2 as db2", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb3", "db3.Code", "left join es.AnlDb3 as db3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb4", "db4.Code", "left join es.AnlDb4 as db4", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb5", "db5.Code", "left join es.AnlDb5 as db5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt1", "kt1.Code", "left join es.AnlKt1 as kt1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt2", "kt2.Code", "left join es.AnlKt2 as kt2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt3", "kt3.Code", "left join es.AnlKt3 as kt3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt4", "kt4.Code", "left join es.AnlKt4 as kt4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt5", "kt5.Code", "left join es.AnlKt5 as kt5", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Catalog", "cat.Code", "left join es.Catalog as cat", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Catalog.FullName", "es.Catalog.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Catalog.Measure1", "meas.Code", "left join es.Catalog.Measure1 as meas", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AccDb.Currency", "cur.Code", "left join es.AccDb.Currency as cur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "CurCource", "es.CurCource", false));     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "SummaCur", "es.SummaCur", false));     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "SummaNat", "es.SummaNat", false));     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Quantity", "es.Quantity", false));                     //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(spec);
  }


  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    specProperties.put("EconomicOper", event.getEntity());     //$NON-NLS-1$
  }

  /**
   * Обработчик просмотра документа-основания
   *
   * @param event - событие
   */
  public void onActionViewBaseDocument(WidgetEvent event) {
    DocumentUtils.viewDocument(((EconomicOper) getEntity()).getBaseDoc());
  }

  /**
   * Обработчик просмотра документа
   *
   * @param event - событие
   */
  public void onActionViewConfirmDocument(WidgetEvent event) {
    DocumentUtils.viewDocument(((EconomicOper) getEntity()).getConfirmDoc());
  }

  /**
   * Обработчик просмотра/выбора контракта
   *
   * @param event - событие
   */
  public void onActionViewOrChooseContract(WidgetEvent event) {
    final EconomicOper economicOper = (EconomicOper) getEntity();
    if (economicOper.getContractId() != null) {
      Contract contract = ServerUtils.getPersistentManager().find(Contract.class, economicOper.getContractId());
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
          economicOper.setContractType(contract.getDocType());
          economicOper.setContractNumber(contract.getDocNumber().trim());
          economicOper.setContractDate(contract.getDocDate());
          view.flushModel();
        }
      });
  }

}
