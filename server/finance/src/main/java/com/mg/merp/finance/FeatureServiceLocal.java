/*
 * FeatureServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.finance;

import com.mg.merp.finance.model.Account;

/**
 * @author leonova
 * @version $Id: FeatureServiceLocal.java,v 1.2 2006/08/28 12:48:15 leonova Exp $
 */
public interface FeatureServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<Account, Integer> {
  /**
   * тип папки для признаков финансового учета
   */
  final static short FOLDER_PART = 39;
}
