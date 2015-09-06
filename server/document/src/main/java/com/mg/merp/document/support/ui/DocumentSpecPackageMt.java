/*
 * DocumentSpecPackageMt.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.merp.document.DocumentSpecPackageServiceLocal;
import com.mg.merp.document.model.DocumentSpecPackage;

/**
 * Контроллер формы поддержки "Упаковки товара позиции спецификации документа"
 *
 * @author Artem V. Sharapov
 * @version $Id: DocumentSpecPackageMt.java,v 1.1 2007/06/21 11:59:42 sharapov Exp $
 */
public class DocumentSpecPackageMt extends DefaultMaintenanceForm {


  /**
   * Обработчик кнопки "Рассчитать вес и объем"
   *
   * @param event - событие
   */
  public void onActionComputeWeightAndVolume(WidgetEvent event) {
    ((DocumentSpecPackageServiceLocal) getService()).computeWeightAndVolume((DocumentSpecPackage) getEntity());
  }

}
