/*
 * OrderHeadModelSupMt.java
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
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.OrderSpecModelSupServiceLocal;

import java.util.Set;

/**
 * Контроллер формы поддержки заказов поставщиков
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: OrderHeadModelSupMt.java,v 1.8 2008/03/20 13:03:36 alikaev Exp $
 */
public class OrderHeadModelSupMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {

  protected AttributeMap orderSpecModelSupProperties = new LocalDataTransferObject();
  /**
   * контекст импорта для SearchHelp поля "Ответственный"
   */
  protected String[] contractorResponsibleKinds;
  /**
   * контекст импорта для SearchHelp поля "через кого"
   */
  protected String[] contractorThroughKinds;
  private MaintenanceTableController orderSpecModelSup;
  private OrderSpecModelSupServiceLocal orderSpecModelSupService;

  public OrderHeadModelSupMt() throws Exception {
    super();

    contractorThroughKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
    contractorResponsibleKinds = new String[]{ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};

    orderSpecModelSupService = (OrderSpecModelSupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/OrderSpecModelSup"); //$NON-NLS-1$
    orderSpecModelSup = new MaintenanceTableController(orderSpecModelSupProperties);
    orderSpecModelSup.initController(orderSpecModelSupService, new OrderModelSpecMaintenanceEJBQLTableModel() {
      private OrderSpecModelSupServiceLocal specService;

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        super.getDefaultFieldDefsSet();

        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specService);
      }

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#createQueryText()
       */
      @Override
      protected String createQueryText() {
        super.createQueryText();
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    });
    addMasterModelListener(orderSpecModelSup);

    addMasterModelListener(this);
  }

	/* (non-Javadoc)
     * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */

  public void masterChange(ModelChangeEvent event) {
    orderSpecModelSupProperties.put("DocHeadModel", event.getEntity());     //$NON-NLS-1$
  }

}
