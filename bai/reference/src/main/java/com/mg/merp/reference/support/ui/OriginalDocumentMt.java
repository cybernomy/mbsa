/*
 * OriginalDocumentMt.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.ComboMenuBar;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.merp.reference.OriginalDocumentServiceLocal;

import java.io.Serializable;

/**
 * Котроллер формы поддержки бизнес-компонента "Оригиналы документов"
 *
 * @author leonova
 * @version $Id: OriginalDocumentMt.java,v 1.3 2008/12/29 13:08:40 safonov Exp $
 */
public class OriginalDocumentMt extends DefaultCompoundMaintenanceForm {

  private Serializable[] getPrimaryKey() {
    return new Serializable[]{(Serializable) getEntity().getPrimaryKey()};
  }

  /**
   * обработчик загрузки оригинала на клиента
   */
  public void onActionDownloadOriginal(WidgetEvent event) {
    AttachmentHelper.download(getPrimaryKey(), OriginalDocumentServiceLocal.SERVICE_NAME);
  }

  /**
   * обработчик загрузки оригинала в систему
   */
  public void onActionUploadOriginal(WidgetEvent event) {
    AttachmentHelper.upload(getPrimaryKey(), OriginalDocumentServiceLocal.SERVICE_NAME);
  }

  /**
   * обработчик удаления оригиналов
   */
  public void onActionRemoveOriginal(WidgetEvent event) {
    AttachmentHelper.remove(getPrimaryKey(), OriginalDocumentServiceLocal.SERVICE_NAME);
  }

  /**
   * обработчик показа оригиналов
   */
  public void onActionShowOriginal(WidgetEvent event) {
    AttachmentHelper.show(getPrimaryKey(), OriginalDocumentServiceLocal.SERVICE_NAME);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
   */
  @Override
  protected void doSetDependentReadOnly(boolean readOnly) {
    super.doSetDependentReadOnly(readOnly);
    ComboMenuBar comboMenuBar = (ComboMenuBar) view.getWidget("originalFunctions");
    if (comboMenuBar != null) {
      comboMenuBar.getMenuItem("showOriginal").setEnabled(!readOnly || getAction() == MaintenanceAction.VIEW);
      comboMenuBar.getMenuItem("downloadOriginal").setEnabled(!readOnly);
      comboMenuBar.getMenuItem("uploadOriginal").setEnabled(!readOnly);
      comboMenuBar.getMenuItem("removeOriginal").setEnabled(!readOnly);
    }
  }

}
