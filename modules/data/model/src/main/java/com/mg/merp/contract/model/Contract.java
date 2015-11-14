/*
 * Contract.java
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
package com.mg.merp.contract.model;

import com.mg.framework.api.annotations.DataItemName;

import java.math.BigDecimal;

/**
 * Модель бизнес-компонента "Контракт"
 *
 * @author Artem V. Sharapov
 * @version $Id: Contract.java,v 1.12 2008/09/22 10:03:59 sharapov Exp $
 */
public class Contract extends com.mg.merp.document.model.DocHead implements java.io.Serializable, org.hibernate.bytecode.internal.javassist.FieldHandled {

  // Fields

  private com.mg.merp.reference.model.Contractor Consignee;

  private com.mg.merp.reference.model.Contractor Shipper;

  private com.mg.merp.reference.model.BankAccount ContractorBankReq;

  private com.mg.merp.reference.model.BankAccount BankReq;

  private com.mg.merp.reference.model.Contractor Responsible;

  private com.mg.merp.reference.model.PartnerEmpl ContractorResponsible;

  private com.mg.merp.reference.model.BankAccount ShipperBankReq;

  private com.mg.merp.reference.model.BankAccount ConsigneeBankReq;

  private java.lang.String IncomingNumber;

  private java.util.Date RegistrationDate;

  private ContractStatus Status;

  private java.util.Date RatificationDate;

  private java.util.Date CompletedDate;

  private java.util.Date BeginActionDate;

  private java.util.Date EndActionDate;

  private java.lang.String subject;

  private java.lang.String Comment;

  private CalcSumKind CalcSumKind;

  private java.math.BigDecimal ShippedPayment;

  private java.math.BigDecimal ReceivedPayment;

  private java.math.BigDecimal ShippedGood;

  private java.math.BigDecimal ReceivedGood;

  private java.math.BigDecimal FactShippedPayment;

  private java.math.BigDecimal FactReceivedPayment;

  private java.math.BigDecimal FactShippedGood;

  private java.math.BigDecimal FactReceivedGood;

  private java.math.BigDecimal PhaseShippedPayment;

  private java.math.BigDecimal PhaseReceivedPayment;

  private java.math.BigDecimal PhaseShippedGood;

  private java.math.BigDecimal PhaseReceivedGood;

  private java.lang.Short ContractorBankDays;

  private java.lang.Short BankDays;

  private java.math.BigDecimal PennyRate;

  private java.math.BigDecimal ContractorPennyRate;

  private ContractType TypeContract;

  private ContractCategory ContractCategory;

  private String contractorInNameOf;

  private String contractorActOnBasis;

  private Short contractorConsignmentTerm;

  private BigDecimal contractorGoodsCredit;

  private String inNameOf;

  private String actOnBasis;

  private Short consignmentTerm;

  private BigDecimal goodsCredit;


  // Constructors

  /**
   * default constructor
   */
  public Contract() {
  }


  // Property accessors

  public ContractType getTypeContract() {
    return TypeContract;
  }

  public void setTypeContract(ContractType typeContract) {
    TypeContract = typeContract;
  }

  /**
   *
   */
  @DataItemName("Contract.Consignee") //$NON-NLS-1$
  public com.mg.merp.reference.model.Contractor getConsignee() {
    return this.Consignee;
  }

  public void setConsignee(com.mg.merp.reference.model.Contractor Contractor) {
    this.Consignee = Contractor;
  }

  /**
   *
   */
  @DataItemName("Contract.Shipper")     //$NON-NLS-1$
  public com.mg.merp.reference.model.Contractor getShipper() {
    return this.Shipper;
  }

  public void setShipper(com.mg.merp.reference.model.Contractor Contractor_1) {
    this.Shipper = Contractor_1;
  }

  public ContractCategory getContractCategory() {
    return ContractCategory;
  }

  public void setContractCategory(ContractCategory contractCategory) {
    ContractCategory = contractCategory;
  }

  /**
   *
   */
  @DataItemName("Contract.ContractorBankReq") //$NON-NLS-1$
  public com.mg.merp.reference.model.BankAccount getContractorBankReq() {
    return this.ContractorBankReq;
  }

  public void setContractorBankReq(com.mg.merp.reference.model.BankAccount ContractorBankReq) {
    this.ContractorBankReq = ContractorBankReq;
  }

  /**
   *
   */
  @DataItemName("Contract.BankReq") //$NON-NLS-1$
  public com.mg.merp.reference.model.BankAccount getBankReq() {
    return this.BankReq;
  }

  public void setBankReq(com.mg.merp.reference.model.BankAccount BankReq) {
    this.BankReq = BankReq;
  }

  /**
   *
   */
  @DataItemName("Contract.Responsible") //$NON-NLS-1$
  public com.mg.merp.reference.model.Contractor getResponsible() {
    return this.Responsible;
  }

  public void setResponsible(com.mg.merp.reference.model.Contractor Contractor_2) {
    this.Responsible = Contractor_2;
  }

  /**
   *
   */
  @DataItemName("Contract.ContrResponsible") //$NON-NLS-1$
  public com.mg.merp.reference.model.PartnerEmpl getContractorResponsible() {
    return this.ContractorResponsible;
  }

  public void setContractorResponsible(com.mg.merp.reference.model.PartnerEmpl Partnempl) {
    this.ContractorResponsible = Partnempl;
  }

  /**
   *
   */
  @DataItemName("Contract.IncomingNumber") //$NON-NLS-1$
  public java.lang.String getIncomingNumber() {
    return this.IncomingNumber;
  }

  public void setIncomingNumber(java.lang.String IncomingNumber) {
    this.IncomingNumber = IncomingNumber;
  }

  /**
   *
   */
  @DataItemName("Contract.RegistrationDate") //$NON-NLS-1$
  public java.util.Date getRegistrationDate() {
    return this.RegistrationDate;
  }

  public void setRegistrationDate(java.util.Date RegistrationDate) {
    this.RegistrationDate = RegistrationDate;
  }

  /**
   *
   */
  public ContractStatus getStatus() {
    return this.Status;
  }

  public void setStatus(ContractStatus Status) {
    this.Status = Status;
  }

  /**
   *
   */
  @DataItemName("Contract.RatificationDate") //$NON-NLS-1$
  public java.util.Date getRatificationDate() {
    return this.RatificationDate;
  }

  public void setRatificationDate(java.util.Date RatificationDate) {
    this.RatificationDate = RatificationDate;
  }

  /**
   *
   */
  @DataItemName("Contract.CompletedDate") //$NON-NLS-1$
  public java.util.Date getCompletedDate() {
    return this.CompletedDate;
  }

  public void setCompletedDate(java.util.Date CompletedDate) {
    this.CompletedDate = CompletedDate;
  }

  /**
   *
   */
  @DataItemName("Contract.BeginActionDate") //$NON-NLS-1$
  public java.util.Date getBeginActionDate() {
    return this.BeginActionDate;
  }

  public void setBeginActionDate(java.util.Date BeginactionDate) {
    this.BeginActionDate = BeginactionDate;
  }

  /**
   *
   */
  @DataItemName("Contract.EndActionDate") //$NON-NLS-1$
  public java.util.Date getEndActionDate() {
    return this.EndActionDate;
  }

  public void setEndActionDate(java.util.Date EndactionDate) {
    this.EndActionDate = EndactionDate;
  }

  /**
   *
   */
  @DataItemName("Contract.Description") //$NON-NLS-1$
  public java.lang.String getSubject() {
    return this.subject;
  }

  public void setSubject(java.lang.String subject) {
    this.subject = subject;
  }

  /**
   *
   */
  @DataItemName("Contract.Comment") //$NON-NLS-1$
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */
  @DataItemName("Contract.ShipperBankReq") //$NON-NLS-1$
  public com.mg.merp.reference.model.BankAccount getShipperBankReq() {
    return this.ShipperBankReq;
  }

  public void setShipperBankReq(com.mg.merp.reference.model.BankAccount ShipperBankReq) {
    this.ShipperBankReq = ShipperBankReq;
  }

  /**
   *
   */
  @DataItemName("Contract.ConsigneeBankReq") //$NON-NLS-1$
  public com.mg.merp.reference.model.BankAccount getConsigneeBankReq() {
    return this.ConsigneeBankReq;
  }

  public void setConsigneeBankReq(com.mg.merp.reference.model.BankAccount ConsigneeBankReq) {
    this.ConsigneeBankReq = ConsigneeBankReq;
  }

  /**
   *
   */
  public CalcSumKind getCalcSumKind() {
    return this.CalcSumKind;
  }

  public void setCalcSumKind(CalcSumKind Calcsumkind) {
    this.CalcSumKind = Calcsumkind;
  }

  /**
   *
   */
  @DataItemName("Contract.ShippedPayment") //$NON-NLS-1$
  public java.math.BigDecimal getShippedPayment() {
    return this.ShippedPayment;
  }

  public void setShippedPayment(java.math.BigDecimal Shippedpayment) {
    this.ShippedPayment = Shippedpayment;
  }

  /**
   *
   */
  @DataItemName("Contract.ReceivedPayment") //$NON-NLS-1$
  public java.math.BigDecimal getReceivedPayment() {
    return this.ReceivedPayment;
  }

  public void setReceivedPayment(java.math.BigDecimal Receivedpayment) {
    this.ReceivedPayment = Receivedpayment;
  }

  /**
   *
   */
  @DataItemName("Contract.ShippedGood") //$NON-NLS-1$
  public java.math.BigDecimal getShippedGood() {
    return this.ShippedGood;
  }

  public void setShippedGood(java.math.BigDecimal Shippedgood) {
    this.ShippedGood = Shippedgood;
  }

  /**
   *
   */
  @DataItemName("Contract.ReceivedGood") //$NON-NLS-1$
  public java.math.BigDecimal getReceivedGood() {
    return this.ReceivedGood;
  }

  public void setReceivedGood(java.math.BigDecimal Receivedgood) {
    this.ReceivedGood = Receivedgood;
  }

  /**
   *
   */
  @DataItemName("Contract.FactShippedPayment") //$NON-NLS-1$
  public java.math.BigDecimal getFactShippedPayment() {
    return this.FactShippedPayment;
  }

  public void setFactShippedPayment(java.math.BigDecimal Factshippedpayment) {
    this.FactShippedPayment = Factshippedpayment;
  }

  /**
   *
   */
  @DataItemName("Contract.FactReceivedPayment") //$NON-NLS-1$
  public java.math.BigDecimal getFactReceivedPayment() {
    return this.FactReceivedPayment;
  }

  public void setFactReceivedPayment(java.math.BigDecimal Factreceivedpayment) {
    this.FactReceivedPayment = Factreceivedpayment;
  }

  /**
   *
   */
  @DataItemName("Contract.FactShippedGood") //$NON-NLS-1$
  public java.math.BigDecimal getFactShippedGood() {
    return this.FactShippedGood;
  }

  public void setFactShippedGood(java.math.BigDecimal Factshippedgood) {
    this.FactShippedGood = Factshippedgood;
  }

  /**
   *
   */
  @DataItemName("Contract.FactReceivedGood") //$NON-NLS-1$
  public java.math.BigDecimal getFactReceivedGood() {
    return this.FactReceivedGood;
  }

  public void setFactReceivedGood(java.math.BigDecimal Factreceivedgood) {
    this.FactReceivedGood = Factreceivedgood;
  }

  /**
   *
   */
  @DataItemName("Contract.PhaseShippedPayment") //$NON-NLS-1$
  public java.math.BigDecimal getPhaseShippedPayment() {
    return this.PhaseShippedPayment;
  }

  public void setPhaseShippedPayment(java.math.BigDecimal Phaseshippedpayment) {
    this.PhaseShippedPayment = Phaseshippedpayment;
  }

  /**
   *
   */
  @DataItemName("Contract.PhaseReceivedPayment") //$NON-NLS-1$
  public java.math.BigDecimal getPhaseReceivedPayment() {
    return this.PhaseReceivedPayment;
  }

  public void setPhaseReceivedPayment(java.math.BigDecimal Phasereceivedpayment) {
    this.PhaseReceivedPayment = Phasereceivedpayment;
  }

  /**
   *
   */
  @DataItemName("Contract.PhaseShippedGood") //$NON-NLS-1$
  public java.math.BigDecimal getPhaseShippedGood() {
    return this.PhaseShippedGood;
  }

  public void setPhaseShippedGood(java.math.BigDecimal Phaseshippedgood) {
    this.PhaseShippedGood = Phaseshippedgood;
  }

  /**
   *
   */
  @DataItemName("Contract.PhaseReceivedGood") //$NON-NLS-1$
  public java.math.BigDecimal getPhaseReceivedGood() {
    return this.PhaseReceivedGood;
  }

  public void setPhaseReceivedGood(java.math.BigDecimal Phasereceivedgood) {
    this.PhaseReceivedGood = Phasereceivedgood;
  }

  /**
   *
   */
  @DataItemName("Contract.ContrBankDays") //$NON-NLS-1$
  public java.lang.Short getContractorBankDays() {
    return this.ContractorBankDays;
  }

  public void setContractorBankDays(java.lang.Short Contractorbankdays) {
    this.ContractorBankDays = Contractorbankdays;
  }

  /**
   *
   */
  @DataItemName("Contract.BankDays") //$NON-NLS-1$
  public java.lang.Short getBankDays() {
    return this.BankDays;
  }

  public void setBankDays(java.lang.Short Bankdays) {
    this.BankDays = Bankdays;
  }

  /**
   *
   */
  @DataItemName("Contract.PennyRate") //$NON-NLS-1$
  public java.math.BigDecimal getPennyRate() {
    return this.PennyRate;
  }

  public void setPennyRate(java.math.BigDecimal PennyRate) {
    this.PennyRate = PennyRate;
  }

  /**
   *
   */
  @DataItemName("Contract.ContrPennyRate") //$NON-NLS-1$
  public java.math.BigDecimal getContractorPennyRate() {
    return this.ContractorPennyRate;
  }

  public void setContractorPennyRate(java.math.BigDecimal ContractorpennyRate) {
    this.ContractorPennyRate = ContractorpennyRate;
  }

  /**
   * @return the contractorInNameOf
   */
  @DataItemName("Contract.Contract.ContractorInNameOf") //$NON-NLS-1$
  public String getContractorInNameOf() {
    return this.contractorInNameOf;
  }

  /**
   * @param contractorInNameOf the contractorInNameOf to set
   */
  public void setContractorInNameOf(String contractorInNameOf) {
    this.contractorInNameOf = contractorInNameOf;
  }

  /**
   * @return the contractorActOnBasis
   */
  @DataItemName("Contract.Contract.ContractorActOnBasis") //$NON-NLS-1$
  public String getContractorActOnBasis() {
    return this.contractorActOnBasis;
  }

  /**
   * @param contractorActOnBasis the contractorActOnBasis to set
   */
  public void setContractorActOnBasis(String contractorActOnBasis) {
    this.contractorActOnBasis = contractorActOnBasis;
  }

  /**
   * @return the contractorConsignmentTerm
   */
  @DataItemName("Contract.Contract.ContractorConsignmentTerm") //$NON-NLS-1$
  public Short getContractorConsignmentTerm() {
    return this.contractorConsignmentTerm;
  }

  /**
   * @param contractorConsignmentTerm the contractorConsignmentTerm to set
   */
  public void setContractorConsignmentTerm(Short contractorConsignmentTerm) {
    this.contractorConsignmentTerm = contractorConsignmentTerm;
  }

  /**
   * @return the contractorGoodsCredit
   */
  @DataItemName("Contract.Contract.ContractorGoodsCredit") //$NON-NLS-1$
  public BigDecimal getContractorGoodsCredit() {
    return this.contractorGoodsCredit;
  }

  /**
   * @param contractorGoodsCredit the contractorGoodsCredit to set
   */
  public void setContractorGoodsCredit(BigDecimal contractorGoodsCredit) {
    this.contractorGoodsCredit = contractorGoodsCredit;
  }

  /**
   * @return the inNameOf
   */
  @DataItemName("Contract.Contract.InNameOf") //$NON-NLS-1$
  public String getInNameOf() {
    return this.inNameOf;
  }

  /**
   * @param inNameOf the inNameOf to set
   */
  public void setInNameOf(String inNameOf) {
    this.inNameOf = inNameOf;
  }

  /**
   * @return the actOnBasis
   */
  @DataItemName("Contract.Contract.ActOnBasis") //$NON-NLS-1$
  public String getActOnBasis() {
    return this.actOnBasis;
  }

  /**
   * @param actOnBasis the actOnBasis to set
   */
  public void setActOnBasis(String actOnBasis) {
    this.actOnBasis = actOnBasis;
  }

  /**
   * @return the consignmentTerm
   */
  @DataItemName("Contract.Contract.ConsignmentTerm") //$NON-NLS-1$
  public Short getConsignmentTerm() {
    return this.consignmentTerm;
  }

  /**
   * @param consignmentTerm the consignmentTerm to set
   */
  public void setConsignmentTerm(Short consignmentTerm) {
    this.consignmentTerm = consignmentTerm;
  }

  /**
   * @return the goodsCredit
   */
  @DataItemName("Contract.Contract.GoodsCredit") //$NON-NLS-1$
  public BigDecimal getGoodsCredit() {
    return this.goodsCredit;
  }

  /**
   * @param goodsCredit the goodsCredit to set
   */
  public void setGoodsCredit(BigDecimal goodsCredit) {
    this.goodsCredit = goodsCredit;
  }

  /**
   * Получить "Контрагента"
   */
  @DataItemName("Contract.Contract.Contractor") //$NON-NLS-1$
  public com.mg.merp.reference.model.Contractor getFrom() {
    return super.getFrom();
  }

  /**
   * Получить "Нашу организацию"
   */
  @DataItemName("Contract.Contract.Company") //$NON-NLS-1$
  public com.mg.merp.reference.model.Contractor getTo() {
    return super.getTo();
  }

}