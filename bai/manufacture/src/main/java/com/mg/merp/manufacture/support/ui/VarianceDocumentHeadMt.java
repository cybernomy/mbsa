/*
 * VarianceDocumentHeadMt.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.manufacture.VarianceDocumentSpecServiceLocal;
import com.mg.merp.manufacture.model.VarianceDocumentSpec;

import java.util.Set;

/**
 * Контроллер формы поддержки документов по отклонениям
 *
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: VarianceDocumentHeadMt.java,v 1.10 2008/12/25 10:17:45 safonov Exp $
 */
public class VarianceDocumentHeadMt extends GoodsDocumentMaintenanceForm {

  public VarianceDocumentHeadMt() throws Exception {
    super();
    specService = ((VarianceDocumentSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/manufacture/VarianceDocumentSpec"));

    spec.initController(specService, new ManufactureDocSpecMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(super.getDefaultFieldDefsSet(), service);
      }

      /* (non-Javadoc)
       * @see com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel#getDocSpecModelName()
       */
      @Override
      protected String getDocSpecModelName() {
        return VarianceDocumentSpec.class.getName();
      }

    });

    addMasterModelListener(spec);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#setSpecificationEditable()
   */
  @Override
  protected void setSpecificationEditable() {
    super.setSpecificationEditable();
    adjustSpecPopupMenu();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.DocumentMaintenanceForm#doSetDependentReadOnly(boolean)
   */
  @Override
  protected void doSetDependentReadOnly(boolean readOnly) {
    super.doSetDependentReadOnly(readOnly);
    adjustSpecPopupMenu();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnSave()
   */
  @Override
  protected void doOnSave() {
    super.doOnSave();
    adjustSpecPopupMenu();
  }

  /**
   * Выполнить корректировку доступности пунктов КМ списка спецификации
   */
  protected void adjustSpecPopupMenu() {
    Widget specWidget = view.getWidget(SPEC_TABLE_WIDGET);
    PopupMenu popupMenu = specWidget.getPopupMenu();
    popupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
  }

}

