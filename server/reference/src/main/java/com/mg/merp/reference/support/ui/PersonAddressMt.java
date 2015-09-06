/*
 * PersonAddress.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.reference.PersonAddressServiceLocal;
import com.mg.merp.reference.model.PersonAddress;

/**
 * Контроллер формы поддержки бизнес-компонента "Адрес физического лица"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: PersonAddressMt.java,v 1.1 2007/09/05 11:07:23 alikaev Exp $
 */
public class PersonAddressMt extends DefaultMaintenanceForm {

  /**
   * действие на  событие обновить
   */
  public void onActionBuildFullAddress(WidgetEvent event) {
    PersonAddressServiceLocal servicePersonAddress = (PersonAddressServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PersonAddress");
    servicePersonAddress.getFullAddress((PersonAddress) getEntity());
  }

}
