/*
 * ContractRest.java
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
package com.mg.merp.contract.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.contract.model.ContractStatus;
import com.mg.merp.contract.model.ItemKind;
import com.mg.merp.document.generic.ui.DocumentRest;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.PartnerEmpl;

import java.util.Date;

/**
 * Контроллер формы условий отбора контрактов
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractRest.java,v 1.6 2008/12/01 14:54:08 sharapov Exp $
 */
public class ContractRest extends DocumentRest {

  @DataItemName("Contract.ContrResponsible") //$NON-NLS-1$
  private PartnerEmpl contrResponsible = null;
  @DataItemName("Contract.Responsible") //$NON-NLS-1$
  private Contractor responsible = null;
  @DataItemName("Contract.Cond.BeginActionPlan") //$NON-NLS-1$
  private Date beginActionPlan = null;
  @DataItemName("Contract.Cond.EndActionPlan") //$NON-NLS-1$
  private Date endActionPlan = null;
  @DataItemName("Contract.Cond.BeginActionFact") //$NON-NLS-1$
  private Date beginActionFact = null;
  @DataItemName("Contract.Cond.EndActionFact") //$NON-NLS-1$
  private Date endActionFact = null;
  @DataItemName("Contract.Cond.PlanKind") //$NON-NLS-1$
  private ItemKind planKind = null;
  @DataItemName("Contract.Cond.FactKind") //$NON-NLS-1$
  private ItemKind factKind = null;
  private ContractStatus status = null;
  @DataItemName("Document.DocDate")     //$NON-NLS-1$
  private Date docModifyDate = null;
  @DataItemName("Document.DocNumber") //$NON-NLS-1$
  private String docModifyNumber = "";     //$NON-NLS-1$
  private DocType docModifyType = null;
  @DataItemName("Contract.IncomingNumber")     //$NON-NLS-1$
  private String incomingNumber = ""; //$NON-NLS-1$
  private int incoming = 0;

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.DocumentRest#doClearRestrictionItem()
   */
  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.responsible = null;
    this.contrResponsible = null;
    this.beginActionFact = null;
    this.beginActionPlan = null;
    this.endActionFact = null;
    this.endActionPlan = null;
    this.planKind = null;
    this.factKind = null;
    this.status = null;
    this.docModifyDate = null;
    this.docModifyNumber = ""; //$NON-NLS-1$
    this.docModifyType = null;
    this.incomingNumber = ""; //$NON-NLS-1$
    this.incoming = 0;
  }


  /**
   * @return Returns the beginActionFact.
   */
  public Date getBeginActionFact() {
    return beginActionFact;
  }

  /**
   * @return Returns the beginActionPlan.
   */
  public Date getBeginActionPlan() {
    return beginActionPlan;
  }

  /**
   * @return Returns the docModifyDate.
   */
  public Date getDocModifyDate() {
    return docModifyDate;
  }

  /**
   * @return Returns the docModifyNumber.
   */
  public String getDocModifyNumber() {
    return docModifyNumber;
  }

  /**
   * @return Returns the docModifyType.
   */
  public DocType getDocModifyType() {
    return docModifyType;
  }

  /**
   * @return Returns the endActionFact.
   */
  public Date getEndActionFact() {
    return endActionFact;
  }

  /**
   * @return Returns the endActionPlan.
   */
  public Date getEndActionPlan() {
    return endActionPlan;
  }

  /**
   * @return Returns the factKind.
   */
  public ItemKind getFactKind() {
    return factKind;
  }

  /**
   * @return Returns the incoming.
   */
  public int getIncoming() {
    return incoming;
  }

  /**
   * @return Returns the incomingNumber.
   */
  public String getIncomingNumber() {
    return incomingNumber;
  }

  /**
   * @return Returns the planKind.
   */
  public ItemKind getPlanKind() {
    return planKind;
  }

  /**
   * @return Returns the responsible.
   */
  public Contractor getResponsible() {
    return responsible;
  }

  /**
   * @return Returns the status.
   */
  public ContractStatus getStatus() {
    return status;
  }

  /**
   * @return the contrResponsible
   */
  public PartnerEmpl getContrResponsible() {
    return contrResponsible;
  }

}
