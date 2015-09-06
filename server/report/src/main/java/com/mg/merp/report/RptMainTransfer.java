/* RptMainTransfer.java
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
package com.mg.merp.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Трансферный класс. Был создан для передачи отчёта в среду разработки. Т.к. пакет
 * com.mg.merp.report.model.* был не доступен для Workbench.
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptMainTransfer.java,v 1.7 2007/08/30 14:20:51 safonov Exp $
 */
public class RptMainTransfer implements Serializable {
  public Integer id;

  public String name;

  public String code;

  public String comment;

  public byte[] template;

  public Integer sysClientId;

  public boolean askParams;

  public boolean directPrint;

  // public boolean invokeFromEdit;
  public Integer priority;

  public Date sysVersion;

  public String classNames;

  public String paramsFormName;

  public String outputFormat;

  public ArrayList<Integer> secGroupsIds;
  public ArrayList<String> secGroupsNames;


  public RptMainTransfer() {
  }

  public RptMainTransfer(RptMainTransfer rmt) {
    this.id = new Integer(rmt.id);
    this.name = rmt.name != null ? new String(rmt.name) : null;
    this.code = new String(rmt.code);
    this.comment = rmt.comment != null ? new String(rmt.comment) : null;
    if (rmt.template != null && rmt.template.length > 0) {
      this.template = new byte[rmt.template.length];
      System.arraycopy(rmt.template, 0, this.template, 0, rmt.template.length);
    }
    this.sysClientId = rmt.sysClientId != null ? new Integer(rmt.sysClientId) : null;
    this.askParams = rmt.askParams;
    this.directPrint = rmt.directPrint;
    this.priority = rmt.priority != null ? new Integer(rmt.priority) : null;
    this.sysVersion = rmt.sysVersion != null ? (Date) rmt.sysVersion.clone() : null;
    this.classNames = rmt.classNames != null ? new String(rmt.classNames) : null;
    this.paramsFormName = rmt.paramsFormName != null ? new String(rmt.paramsFormName) : null;
    this.outputFormat = rmt.outputFormat;

    this.secGroupsIds = new ArrayList<Integer>(rmt.secGroupsIds);
    this.secGroupsNames = new ArrayList<String>(rmt.secGroupsNames);
  }
}
