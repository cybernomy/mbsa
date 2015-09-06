/*
 * Created on 24.12.2004
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 */
package com.mg.merp.warehouse;

import java.io.Serializable;

/**
 * @author krivopoustov
 */
public class BinLocationInfo implements Serializable {
  public String code;
  public double quan;
  public int batchId;

  public BinLocationInfo() {
    super();
  }

  public BinLocationInfo(String code, double quan, int batchId) {
    super();
    this.code = code;
    this.quan = quan;
    this.batchId = batchId;
  }
}
