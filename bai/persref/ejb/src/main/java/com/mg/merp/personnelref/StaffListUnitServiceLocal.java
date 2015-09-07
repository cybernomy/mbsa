/*
 * StaffListUnitServiceLocal.java
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
package com.mg.merp.personnelref;

import com.mg.merp.personnelref.model.StaffListUnit;

/**
 * Сервис бизнес-компонента "Подразделения в штатном расписании"
 *
 * @author leonova
 * @version $Id: StaffListUnitServiceLocal.java,v 1.4 2007/08/16 14:09:22 safonov Exp $
 */
public interface StaffListUnitServiceLocal extends com.mg.framework.api.DataBusinessObjectService<StaffListUnit, Integer> {

  /**
   * Имя сервиса
   */
  final static String LOCAL_SERVICE_NAME = "merp/personnelref/StaffListUnit"; //$NON-NLS-1$

  /**
   * штатное расписание на период
   */
  final static int STAFF_LIST_ID = 48;

}
