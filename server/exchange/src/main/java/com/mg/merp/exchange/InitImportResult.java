/*
 * Created on 21.01.2005
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
package com.mg.merp.exchange;

import java.io.Serializable;

/**
 * @author krivopoustov
 */
public class InitImportResult implements Serializable {
  public int packet;
  public String requests;

  public InitImportResult() {
    super();
  }

  public InitImportResult(int packet, String requests) {
    super();
    this.packet = packet;
    this.requests = requests;
  }
}
