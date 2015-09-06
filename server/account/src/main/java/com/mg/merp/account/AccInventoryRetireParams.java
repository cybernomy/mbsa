/*
 * AccInventoryRetireParams.java
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
package com.mg.merp.account;

import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocType;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс результат для формирования параметров для операции списания инвентарных карточек
 *
 * @author Konstantin S. Alikaev
 * @version $Id: AccInventoryRetireParams.java,v 1.1 2008/04/28 10:09:51 alikaev Exp $
 */
public class AccInventoryRetireParams implements Serializable {

  /**
   * дата учета
   */
  private Date revalDate;

  /**
   * папка -приемник создаваемой хоз. операции
   */
  private Folder folder;

  /**
   * тип документа
   */
  private DocType docType;

  /**
   * номер документа
   */
  private String docNumber;

  /**
   * дата документа
   */
  private Date docDate;

  /**
   * тип документа основания
   */
  private DocType baseDocType;

  /**
   * номер докумнта основания
   */
  private String baseDocNumber;

  /**
   * дата документа основания
   */
  private Date baseDocDate;

  /**
   * счет
   */
  private AccPlan accPlan;

  /**
   * аналитика 1-го уровня
   */
  private AnlPlan anl1;

  /**
   * аналитика 2-го уровня
   */
  private AnlPlan anl2;
  /**
   * аналитика 3-го уровня
   */
  private AnlPlan anl3;

  /**
   * аналитика 4-го уровня
   */
  private AnlPlan anl4;

  /**
   * аналитика 5-го уровня
   */
  private AnlPlan anl5;

  public AccInventoryRetireParams(Date revalDate, Folder folder,
                                  DocType docType, String docNumber, Date docDate,
                                  DocType baseDocType, String baseDocNumber, Date baseDocDate,
                                  AccPlan accPlan, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3,
                                  AnlPlan anl4, AnlPlan anl5) {
    this.revalDate = revalDate;
    this.folder = folder;
    this.docType = docType;
    this.docNumber = docNumber;
    this.docDate = docDate;
    this.baseDocType = baseDocType;
    this.baseDocNumber = baseDocNumber;
    this.baseDocDate = baseDocDate;
    this.accPlan = accPlan;
    this.anl1 = anl1;
    this.anl2 = anl2;
    this.anl3 = anl3;
    this.anl4 = anl4;
    this.anl5 = anl5;
  }

  public Date getRevalDate() {
    return revalDate;
  }

  public void setRevalDate(Date revalDate) {
    this.revalDate = revalDate;
  }

  public DocType getDocType() {
    return docType;
  }

  public void setDocType(DocType docType) {
    this.docType = docType;
  }

  public String getDocNumber() {
    return docNumber;
  }

  public void setDocNumber(String docNumber) {
    this.docNumber = docNumber;
  }

  public Date getDocDate() {
    return docDate;
  }

  public void setDocDate(Date docDate) {
    this.docDate = docDate;
  }

  public DocType getBaseDocType() {
    return baseDocType;
  }

  public void setBaseDocType(DocType baseDocType) {
    this.baseDocType = baseDocType;
  }

  public String getBaseDocNumber() {
    return baseDocNumber;
  }

  public void setBaseDocNumber(String baseDocNumber) {
    this.baseDocNumber = baseDocNumber;
  }

  public Date getBaseDocDate() {
    return baseDocDate;
  }

  public void setBaseDocDate(Date baseDocDate) {
    this.baseDocDate = baseDocDate;
  }

  public AccPlan getAccPlan() {
    return accPlan;
  }

  public void setAccPlan(AccPlan accPlan) {
    this.accPlan = accPlan;
  }

  public AnlPlan getAnl1() {
    return anl1;
  }

  public void setAnl1(AnlPlan anl1) {
    this.anl1 = anl1;
  }

  public AnlPlan getAnl2() {
    return anl2;
  }

  public void setAnl2(AnlPlan anl2) {
    this.anl2 = anl2;
  }

  public AnlPlan getAnl3() {
    return anl3;
  }

  public void setAnl3(AnlPlan anl3) {
    this.anl3 = anl3;
  }

  public AnlPlan getAnl4() {
    return anl4;
  }

  public void setAnl4(AnlPlan anl4) {
    this.anl4 = anl4;
  }

  public AnlPlan getAnl5() {
    return anl5;
  }

  public void setAnl5(AnlPlan anl5) {
    this.anl5 = anl5;
  }

  public Folder getFolder() {
    return folder;
  }

  public void setFolder(Folder folder) {
    this.folder = folder;
  }

}
