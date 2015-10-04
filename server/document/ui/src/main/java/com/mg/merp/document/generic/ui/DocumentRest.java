/*
 * DocumentRest.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.document.generic.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.SysCompany;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Контроллер формы условия отбора документов
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: DocumentRest.java,v 1.9 2008/10/29 15:16:14 safonov Exp $
 */
public class DocumentRest extends DefaultHierarhyRestrictionForm {

  protected String[] contractorFromKinds;
  protected String[] contractorToKinds;
  @DataItemName("Document.DocNumber") //$NON-NLS-1$
  private String docNumber = StringUtils.EMPTY_STRING;
  @DataItemName("Document.Cond.SumNatMin") //$NON-NLS-1$
  private BigDecimal sumNatMin = null;
  @DataItemName("Document.Cond.SumNatMax") //$NON-NLS-1$
  private BigDecimal sumNatMax = null;
  @DataItemName("Document.Cond.SumCurMin") //$NON-NLS-1$
  private BigDecimal sumCurMin = null;
  @DataItemName("Document.Cond.SumCurMax") //$NON-NLS-1$
  private BigDecimal sumCurMax = null;
  @DataItemName("Reference.Cond.DateFrom") //$NON-NLS-1$
  private Date docDateFrom = null;
  @DataItemName("Document.DocDate") //$NON-NLS-1$
  private Date baseDocDate = null;
  @DataItemName("Document.DocNumber") //$NON-NLS-1$
  private String baseDocNumber = StringUtils.EMPTY_STRING;
  @DataItemName("DocumentRest.BaseDocType") //$NON-NLS-1$
  private DocType baseDocType = null;
  @DataItemName("Document.DocDate") //$NON-NLS-1$
  private Date contractDate = null;
  @DataItemName("Document.DocNumber") //$NON-NLS-1$
  private String contractNumber = StringUtils.EMPTY_STRING;
  @DataItemName("DocumentRest.ContractType") //$NON-NLS-1$
  private DocType contractType = null;
  @DataItemName("Reference.Cond.DateTill") //$NON-NLS-1$
  private Date docDateTill = null;
  @DataItemName("DocumentRest.DocType") //$NON-NLS-1$
  private DocType docType = null;
  private Currency сurCode = null;
  @DataItemName("Document.Through")     //$NON-NLS-1$
  private Contractor throughCode = null;
  @DataItemName("Document.From") //$NON-NLS-1$
  private Contractor fromCode = null;
  @DataItemName("Document.To") //$NON-NLS-1$
  private Contractor toCode = null;
  @DataItemName("Core.SysCompany") //$NON-NLS-1$
  private SysCompany sysCompany = null;
  @DataItemName("Document.DocumentRest.DocProcessStage") //$NON-NLS-1$
  private DocProcessStage docProcessStage = null;
  @DataItemName("Document.Cond.DPSCompleted") //$NON-NLS-1$
  private boolean isDpsCompleted = false;
  @DataItemName("Document.Cond.DPSPart") //$NON-NLS-1$
  private boolean isDpsCompletedPartly = false;
  private DocSection docSection = null;

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
   */
  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.docNumber = StringUtils.EMPTY_STRING;
    this.docDateFrom = null;
    this.docDateTill = null;
    this.docType = null;
    this.baseDocDate = null;
    this.baseDocNumber = StringUtils.EMPTY_STRING;
    this.baseDocType = null;
    this.contractDate = null;
    this.contractNumber = StringUtils.EMPTY_STRING;
    this.contractType = null;
    this.сurCode = null;
    this.sumNatMin = null;
    this.sumNatMax = null;
    this.sumCurMin = null;
    this.sumCurMax = null;
    this.throughCode = null;
    this.fromCode = null;
    this.toCode = null;
    this.sysCompany = null;
    this.docProcessStage = null;
    this.isDpsCompleted = false;
    this.isDpsCompletedPartly = false;
  }

  /**
   * @return Returns the baseDocDate.
   */
  public Date getBaseDocDate() {
    return baseDocDate;
  }

  /**
   * @return Returns the baseDocNumber.
   */
  public String getBaseDocNumber() {
    return baseDocNumber;
  }

  /**
   * @return Returns the baseDocType.
   */
  public DocType getBaseDocType() {
    return baseDocType;
  }

  /**
   * @return Returns the contractDate.
   */
  public Date getContractDate() {
    return contractDate;
  }

  /**
   * @return Returns the contractNumber.
   */
  public String getContractNumber() {
    return contractNumber;
  }

  /**
   * @return Returns the contractType.
   */
  public DocType getContractType() {
    return contractType;
  }

  /**
   * @return Returns the docDateFrom.
   */
  public Date getDocDateFrom() {
    return docDateFrom;
  }

  /**
   * @return Returns the docDateTill.
   */
  public Date getDocDateTill() {
    return docDateTill;
  }

  /**
   * @return Returns the docNumber.
   */
  public String getDocNumber() {
    return docNumber;
  }

  /**
   * @return Returns the docType.
   */
  public DocType getDocType() {
    return docType;
  }

  /**
   * @return Returns the fromCode.
   */
  public Contractor getFromCode() {
    return fromCode;
  }

  /**
   * @return Returns the sumCurMax.
   */
  public BigDecimal getSumCurMax() {
    return sumCurMax;
  }

  /**
   * @return Returns the sumCurMin.
   */
  public BigDecimal getSumCurMin() {
    return sumCurMin;
  }

  /**
   * @return Returns the sumNatMax.
   */
  public BigDecimal getSumNatMax() {
    return sumNatMax;
  }

  /**
   * @return Returns the sumNatMin.
   */
  public BigDecimal getSumNatMin() {
    return sumNatMin;
  }

  /**
   * @return Returns the throughCode.
   */
  public Contractor getThroughCode() {
    return throughCode;
  }

  /**
   * @return Returns the toCode.
   */
  public Contractor getToCode() {
    return toCode;
  }

  /**
   * @return Returns the сurCode.
   */
  public Currency getСurCode() {
    return сurCode;
  }

  public DocSection getDocSection() {
    return docSection;
  }

  public void setDocSection(DocSection docSection) {
    this.docSection = docSection;
  }

  /**
   * @return sysCompany
   */
  public SysCompany getSysCompany() {
    return sysCompany;
  }

  /**
   * @return the isDpsCompleted
   */
  public boolean isDpsCompleted() {
    return this.isDpsCompleted;
  }

  /**
   * @param isDpsCompleted the isDpsCompleted to set
   */
  public void setDpsCompleted(boolean isDpsCompleted) {
    this.isDpsCompleted = isDpsCompleted;
  }

  /**
   * @return the isDpsCompletedPartly
   */
  public boolean isDpsCompletedPartly() {
    return this.isDpsCompletedPartly;
  }

  /**
   * @param isDpsCompletedPartly the isDpsCompletedPartly to set
   */
  public void setDpsCompletedPartly(boolean isDpsCompletedPartly) {
    this.isDpsCompletedPartly = isDpsCompletedPartly;
  }

  /**
   * @return the docProcessStage
   */
  public DocProcessStage getDocProcessStage() {
    return this.docProcessStage;
  }

  /**
   * @param docProcessStage the docProcessStage to set
   */
  public void setDocProcessStage(DocProcessStage docProcessStage) {
    this.docProcessStage = docProcessStage;
  }

}
