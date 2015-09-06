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
public class ReturnGoodsCond implements Serializable {
  public int catalogId;
  public String catalogCode;
  public String catalogName;
  public int accId;
  public String acc;
  public int accFolderId;
  public String anl1Code;
  public String anl2Code;
  public String anl3Code;
  public String anl4Code;
  public String anl5Code;
  public int anl1Id;
  public int anl2Id;
  public int anl3Id;
  public int anl4Id;
  public int anl5Id;
  public long date1;
  public long date2;
  public int contractorKind;
  public int contractorId;
  public String contractorCode;
  public int contractorFolderId;
  public String docType;
  public String docNumber;
  public long docDate;
  public double price;

  public ReturnGoodsCond() {
    super();
  }

  public ReturnGoodsCond(int catalogId, String catalogCode,
                         String catalogName, int accId, String acc, int accFolderId,
                         String anl1Code, String anl2Code, String anl3Code, String anl4Code,
                         String anl5Code, int anl1Id, int anl2Id, int anl3Id, int anl4Id,
                         int anl5Id, long date1, long date2, int contractorKind,
                         int contractorId, String contractorCode, int contractorFolderId,
                         String docType, String docNumber, long docDate, double price) {
    super();
    this.catalogId = catalogId;
    this.catalogCode = catalogCode;
    this.catalogName = catalogName;
    this.accId = accId;
    this.acc = acc;
    this.accFolderId = accFolderId;
    this.anl1Code = anl1Code;
    this.anl2Code = anl2Code;
    this.anl3Code = anl3Code;
    this.anl4Code = anl4Code;
    this.anl5Code = anl5Code;
    this.anl1Id = anl1Id;
    this.anl2Id = anl2Id;
    this.anl3Id = anl3Id;
    this.anl4Id = anl4Id;
    this.anl5Id = anl5Id;
    this.date1 = date1;
    this.date2 = date2;
    this.contractorKind = contractorKind;
    this.contractorId = contractorId;
    this.contractorCode = contractorCode;
    this.contractorFolderId = contractorFolderId;
    this.docType = docType;
    this.docNumber = docNumber;
    this.docDate = docDate;
    this.price = price;
  }
}
