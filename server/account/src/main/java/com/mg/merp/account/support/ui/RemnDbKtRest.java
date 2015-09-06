/*
 * RemnDbKtRest.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Contractor;

import java.util.Date;

/**
 * Контроллер формы условия отбора бизнес-компонента "Ведомости расчета с контрагентами"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: RemnDbKtRest.java,v 1.5 2008/02/21 10:20:28 alikaev Exp $
 */

public class RemnDbKtRest extends RemnPlainRest {
  @DataItemName("Account.RemnAnl.AnlPlan1")
  private AnlPlan anlCode1 = null;
  @DataItemName("Account.RemnAnl.AnlPlan2")
  private AnlPlan anlCode2 = null;
  @DataItemName("Account.RemnAnl.AnlPlan3")
  private AnlPlan anlCode3 = null;
  @DataItemName("Account.RemnAnl.AnlPlan4")
  private AnlPlan anlCode4 = null;
  @DataItemName("Account.RemnAnl.AnlPlan5")
  private AnlPlan anlCode5 = null;
  private Contractor contractorCode = null;
  @DataItemName("Account.EconOper.DocType")
  private DocType baseDocType = null;
  @DataItemName("Document.DocDate")
  private Date baseDocDate = null;
  @DataItemName("Document.DocNumber")
  private String baseDocNumber = StringUtils.EMPTY_STRING;
  @DataItemName("Account.EconOper.DocType")
  private DocType docType = null;
  @DataItemName("Document.DocDate")
  private Date docDate = null;
  @DataItemName("Document.DocNumber")
  private String docNumber = StringUtils.EMPTY_STRING;
  private int balanceKind = 0;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.anlCode1 = null;
    this.anlCode2 = null;
    this.anlCode3 = null;
    this.anlCode4 = null;
    this.anlCode5 = null;
    this.contractorCode = null;
    this.baseDocDate = null;
    this.baseDocNumber = StringUtils.EMPTY_STRING;
    this.baseDocType = null;
    this.docNumber = StringUtils.EMPTY_STRING;
    this.docDate = null;
    this.docType = null;
    this.balanceKind = 0;
  }

  public int getBalanceKind() {
    return balanceKind;
  }

  public AnlPlan getAnlCode1() {
    return anlCode1;
  }

  public AnlPlan getAnlCode2() {
    return anlCode2;
  }

  public AnlPlan getAnlCode3() {
    return anlCode3;
  }

  public AnlPlan getAnlCode4() {
    return anlCode4;
  }

  public AnlPlan getAnlCode5() {
    return anlCode5;
  }

  public Date getBaseDocDate() {
    return baseDocDate;
  }

  public String getBaseDocNumber() {
    return baseDocNumber;
  }

  public DocType getBaseDocType() {
    return baseDocType;
  }

  public Contractor getContractorCode() {
    return contractorCode;
  }

  public Date getDocDate() {
    return docDate;
  }

  public String getDocNumber() {
    return docNumber;
  }

  public DocType getDocType() {
    return docType;
  }

}