/*
 * BrowseCond.java
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
package com.mg.framework.api;

import java.io.Serializable;

/**
 * @author Oleg V. Safonov
 * @version $Id: BrowseCond.java,v 1.5 2006/09/28 12:24:12 safonov Exp $
 */
@Deprecated
public class BrowseCond implements Serializable {
  public final static String BY_FOLDER_ATTRIBUTE = "ByFolder";

  public Object browseMasterKey;
  public java.lang.String browseFieldsSet;
  public AttributeMap browseCond;
  public boolean searching;
  public int format;

  public BrowseCond() {
  }

  public BrowseCond(Object browseMasterKey, String browseFieldsSet, AttributeMap browseCond, boolean searching, int format) {
    this.browseMasterKey = browseMasterKey;
    this.browseFieldsSet = browseFieldsSet;
    this.browseCond = browseCond;
    this.searching = searching;
    this.format = format;
  }

}
