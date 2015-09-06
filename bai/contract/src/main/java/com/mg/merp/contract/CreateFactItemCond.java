/*
 * Created on 22.12.2004
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
package com.mg.merp.contract;

import java.io.Serializable;

/**
 * @author krivopoustov
 */
public class CreateFactItemCond implements Serializable {
  public int docId;
  public int docSection;
  public String docType;
  public String docNumber;
  public long docDate;
  public int src;
  public int kind;
  public boolean useSpec;
  public boolean createSpec;

  public CreateFactItemCond() {
    super();
  }

  public CreateFactItemCond(int docId, int docSection, String docType,
                            String docNumber, long docDate, int src, int kind, boolean useSpec,
                            boolean createSpec) {
    super();
    this.docId = docId;
    this.docSection = docSection;
    this.docType = docType;
    this.docNumber = docNumber;
    this.docDate = docDate;
    this.src = src;
    this.kind = kind;
    this.useSpec = useSpec;
    this.createSpec = createSpec;
  }
}
