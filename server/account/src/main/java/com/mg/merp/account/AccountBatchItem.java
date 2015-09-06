/*
 * Created on 23.12.2004
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
package com.mg.merp.account;

import java.io.Serializable;

/**
 * @author krivopoustov
 */
public class AccountBatchItem implements Serializable {
  public int key;
  public double quan;

  public AccountBatchItem() {
    super();
  }

  public AccountBatchItem(int key, double quan) {
    super();
    this.key = key;
    this.quan = quan;
  }
}
