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
public class BatchItem implements Serializable {
  public int key;
  public double qty;
  public double qty2;

  public BatchItem() {
    super();
  }

  public BatchItem(int key, double qty, double qty2) {
    super();
    this.key = key;
    this.qty = qty;
    this.qty2 = qty2;
  }
}
