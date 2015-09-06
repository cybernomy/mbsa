/*
 * AccInventoryMoveParams.java
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
import com.mg.merp.account.model.InvLocation;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Contractor;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс параметров для операции перемещения инвентарной карточки
 *
 * @author Konstantin S. Alikaev
 * @version $Id: AccInventoryMoveParams.java,v 1.1 2008/04/28 10:09:51 alikaev Exp $
 */
public class AccInventoryMoveParams implements Serializable {

  private Date revalDate;

  private Contractor contractor;

  private Date inOperDate;

  private String inOperDocNum;

  private InvLocation invLocation;

  private DocType docType;

  private String docNumber;

  private Date docDate;

  private AccPlan AccPlan;

  private AnlPlan anl1;

  private AnlPlan anl2;

  private AnlPlan anl3;

  private AnlPlan anl4;

  private AnlPlan anl5;

  private AccPlan AccKt;

  private AnlPlan anlKt1;

  private AnlPlan anlKt2;

  private AnlPlan anlKt3;

  private AnlPlan anlKt4;

  private AnlPlan anlKt5;

  private Folder folder;

  /**
   * Конструктор
   *
   * @param revalDate    - дата учета
   * @param contractor   - контрагент
   * @param inOperDate   - дата ввода в эксплуатацию
   * @param inOperDocNum - номер акта ввода в эксплуатацию
   * @param invLocation  - местонахождение объекта
   * @param docType      - тип документа
   * @param docNumber    - номер документа
   * @param docDate      - дата документа
   * @param accPlan      - счет учета ОС
   * @param anl1         - аналитика 1-го уровня счета учета ОС
   * @param anl2         - аналитика 2-го уровня счета учета ОС
   * @param anl3         - аналитика 3-го уровня счета учета ОС
   * @param anl4         - аналитика 4-го уровня счета учета ОС
   * @param anl5         - аналитика 5-го уровня счета учета ОС
   * @param accKt        - счет учета начисленной амортизации
   * @param anlKt1       - аналитика 1-го уровня счет учета начисленной амортизации
   * @param anlKt2       - аналитика 2-го уровня счет учета начисленной амортизации
   * @param anlKt3       - аналитика 3-го уровня счет учета начисленной амортизации
   * @param anlKt4       - аналитика 4-го уровня счет учета начисленной амортизации
   * @param anlKt5       - аналитика 5-го уровня счет учета начисленной амортизации
   * @param folder       - папка-приемник для хоз. операции
   */
  public AccInventoryMoveParams(Date revalDate, Contractor contractor,
                                Date inOperDate, String inOperDocNum, InvLocation invLocation,
                                DocType docType, String docNumber, Date docDate, AccPlan accPlan,
                                AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4,
                                AnlPlan anl5, AccPlan accKt, AnlPlan anlKt1, AnlPlan anlKt2,
                                AnlPlan anlKt3, AnlPlan anlKt4, AnlPlan anlKt5, Folder folder) {
    this.revalDate = revalDate;
    this.contractor = contractor;
    this.inOperDate = inOperDate;
    this.inOperDocNum = inOperDocNum;
    this.invLocation = invLocation;
    this.docType = docType;
    this.docNumber = docNumber;
    this.docDate = docDate;
    AccPlan = accPlan;
    this.anl1 = anl1;
    this.anl2 = anl2;
    this.anl3 = anl3;
    this.anl4 = anl4;
    this.anl5 = anl5;
    AccKt = accKt;
    this.anlKt1 = anlKt1;
    this.anlKt2 = anlKt2;
    this.anlKt3 = anlKt3;
    this.anlKt4 = anlKt4;
    this.anlKt5 = anlKt5;
    this.folder = folder;
  }

  public Date getRevalDate() {
    return revalDate;
  }

  public void setRevalDate(Date revalDate) {
    this.revalDate = revalDate;
  }

  public Contractor getContractor() {
    return contractor;
  }

  public void setContractor(Contractor contractor) {
    this.contractor = contractor;
  }

  public Date getInOperDate() {
    return inOperDate;
  }

  public void setInOperDate(Date inOperDate) {
    this.inOperDate = inOperDate;
  }

  public String getInOperDocNum() {
    return inOperDocNum;
  }

  public void setInOperDocNum(String inOperDocNum) {
    this.inOperDocNum = inOperDocNum;
  }

  public InvLocation getInvLocation() {
    return invLocation;
  }

  public void setInvLocation(InvLocation invLocation) {
    this.invLocation = invLocation;
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

  public AccPlan getAccPlan() {
    return AccPlan;
  }

  public void setAccPlan(AccPlan accPlan) {
    AccPlan = accPlan;
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

  public AccPlan getAccKt() {
    return AccKt;
  }

  public void setAccKt(AccPlan accKt) {
    AccKt = accKt;
  }

  public AnlPlan getAnlKt1() {
    return anlKt1;
  }

  public void setAnlKt1(AnlPlan anlKt1) {
    this.anlKt1 = anlKt1;
  }

  public AnlPlan getAnlKt2() {
    return anlKt2;
  }

  public void setAnlKt2(AnlPlan anlKt2) {
    this.anlKt2 = anlKt2;
  }

  public AnlPlan getAnlKt3() {
    return anlKt3;
  }

  public void setAnlKt3(AnlPlan anlKt3) {
    this.anlKt3 = anlKt3;
  }

  public AnlPlan getAnlKt4() {
    return anlKt4;
  }

  public void setAnlKt4(AnlPlan anlKt4) {
    this.anlKt4 = anlKt4;
  }

  public AnlPlan getAnlKt5() {
    return anlKt5;
  }

  public void setAnlKt5(AnlPlan anlKt5) {
    this.anlKt5 = anlKt5;
  }

  public Folder getFolder() {
    return folder;
  }

  public void setFolder(Folder folder) {
    this.folder = folder;
  }

}
