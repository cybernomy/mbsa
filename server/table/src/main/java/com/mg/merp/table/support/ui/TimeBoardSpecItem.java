/*
 * TimeBoardSpecItem.java
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
package com.mg.merp.table.support.ui;

import com.mg.framework.utils.StringUtils;
import com.mg.merp.table.model.TimeBoardPosition;
import com.mg.merp.table.model.TimeBoardSpec;

import java.util.Date;
import java.util.List;

/**
 * Элемент данных позиции табеля
 *
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardSpecItem.java,v 1.1 2008/08/12 14:38:08 sharapov Exp $
 */
public class TimeBoardSpecItem {

  private String personelSurname;
  private String personelName;
  private String personelPatronymic;

  private Date positionBeginDate;
  private Date positionEndDate;
  private String positionName;

  private TimeBoardPosition timeBoardPosition;
  private List<TimeBoardSpec> timeBoardSpecs;

  public TimeBoardSpecItem() {
  }

  public TimeBoardSpecItem(String personelSurname, String personelName, String personelPatronymic, Date positionBeginDate, Date positionEndDate, String positionName, TimeBoardPosition timeBoardPosition) {
    this.personelSurname = personelSurname;
    this.personelName = personelName;
    this.personelPatronymic = personelPatronymic;
    this.positionBeginDate = positionBeginDate;
    this.positionEndDate = positionEndDate;
    this.positionName = positionName;
    this.timeBoardPosition = timeBoardPosition;
  }

  public String getCode() {
    StringBuilder code = new StringBuilder();
    if (!StringUtils.stringNullOrEmpty(personelSurname))
      code.append(personelSurname).append(StringUtils.BLANK_STRING);
    if (!StringUtils.stringNullOrEmpty(personelName))
      code.append(personelName.substring(0, 1)).append(".").append(StringUtils.BLANK_STRING);
    if (!StringUtils.stringNullOrEmpty(personelPatronymic))
      code.append(personelPatronymic.substring(0, 1)).append(".").append(StringUtils.BLANK_STRING);
    if (!StringUtils.stringNullOrEmpty(positionName))
      code.append("(").append(positionName).append(")");
    return code.toString();
  }

  /**
   * @return the personelName
   */
  public String getPersonelName() {
    return this.personelName;
  }

  /**
   * @param personelName the personelName to set
   */
  public void setPersonelName(String personelName) {
    this.personelName = personelName;
  }

  /**
   * @return the personelSurname
   */
  public String getPersonelSurname() {
    return this.personelSurname;
  }

  /**
   * @param personelSurname the personelSurname to set
   */
  public void setPersonelSurname(String personelSurname) {
    this.personelSurname = personelSurname;
  }

  /**
   * @return the personelPatronymic
   */
  public String getPersonelPatronymic() {
    return this.personelPatronymic;
  }

  /**
   * @param personelPatronymic the personelPatronymic to set
   */
  public void setPersonelPatronymic(String personelPatronymic) {
    this.personelPatronymic = personelPatronymic;
  }

  /**
   * @return the positionBeginDate
   */
  public Date getPositionBeginDate() {
    return this.positionBeginDate;
  }

  /**
   * @param positionBeginDate the positionBeginDate to set
   */
  public void setPositionBeginDate(Date positionBeginDate) {
    this.positionBeginDate = positionBeginDate;
  }

  /**
   * @return the positionEndDate
   */
  public Date getPositionEndDate() {
    return this.positionEndDate;
  }

  /**
   * @param positionEndDate the positionEndDate to set
   */
  public void setPositionEndDate(Date positionEndDate) {
    this.positionEndDate = positionEndDate;
  }

  /**
   * @return the positionName
   */
  public String getPositionName() {
    return this.positionName;
  }

  /**
   * @param positionName the positionName to set
   */
  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  /**
   * @return the timeBoardPosition
   */
  public TimeBoardPosition getTimeBoardPosition() {
    return this.timeBoardPosition;
  }

  /**
   * @param timeBoardPosition the timeBoardPosition to set
   */
  public void setTimeBoardPosition(TimeBoardPosition timeBoardPosition) {
    this.timeBoardPosition = timeBoardPosition;
  }

  /**
   * @return the timeBoardSpecs
   */
  public List<TimeBoardSpec> getTimeBoardSpecs() {
    return this.timeBoardSpecs;
  }

  /**
   * @param timeBoardSpecs the timeBoardSpecs to set
   */
  public void setTimeBoardSpecs(List<TimeBoardSpec> timeBoardSpecs) {
    this.timeBoardSpecs = timeBoardSpecs;
  }

}
