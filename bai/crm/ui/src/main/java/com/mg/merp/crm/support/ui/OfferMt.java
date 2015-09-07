/*
 * OfferMt.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.crm.LinkedDocumentServiceLocal;

import java.util.Set;

/**
 * Контроллер формы поддержи бизнес-компонента "Предложения"
 *
 * @author leonova
 * @version $Id: OfferMt.java,v 1.4 2008/03/18 12:14:47 alikaev Exp $
 */
public class OfferMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {

  protected AttributeMap originalProperties = new LocalDataTransferObject();
  private MaintenanceTableController original;
  private LinkedDocumentServiceLocal originalService;

  public OfferMt() throws Exception {
    super();
    addMasterModelListener(this);

    originalService = (LinkedDocumentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/LinkedDocument"); //$NON-NLS-1$
    original = new MaintenanceTableController(originalProperties);
    original.initController(originalService, new LinkedDocumentMaintenanceEJBQLTableModel() {
      protected final String INIT_QUERY_TEXT = "select %s from LinkedDocument ld %s where ld.Offer = :offer"; //$NON-NLS-1$

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("offer"); //$NON-NLS-1$
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

    });
    addMasterModelListener(original);
    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    originalProperties.put("Offer", event.getEntity()); //$NON-NLS-1$
  }

}
