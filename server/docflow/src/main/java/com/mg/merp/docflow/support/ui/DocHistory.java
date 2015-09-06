/*
 * DocHistory.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow.support.ui;

import com.mg.framework.utils.StringUtils;
import com.mg.merp.docprocess.model.ActionType;
import com.mg.merp.docprocess.model.StageState;

import java.math.BigDecimal;

/**
 * Класс помошник для получиения истории ДО
 *
 * @author Oleg V. Safonov
 * @version $Id: DocHistory.java,v 1.3 2006/11/11 11:19:18 safonov Exp $
 */
public class DocHistory {
  private Integer id;
  private ActionType actionType;
  private String name;
  private StageState stageState;
  private BigDecimal readyAmount;

  public DocHistory(Integer id, ActionType actionType, String internalName, String name, StageState stageState, BigDecimal readySum) {
    this.id = id;
    this.actionType = actionType;
    this.name = name == null || StringUtils.EMPTY_STRING.equals(name) ? internalName : name;
    this.stageState = stageState;
    this.readyAmount = readySum;
  }

  /**
   * @return the actionType
   */
  public ActionType getActionType() {
    return actionType;
  }

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the readyAmount
   */
  public BigDecimal getReadyAmount() {
    return readyAmount;
  }

  /**
   * @return the stageState
   */
  public StageState getStageState() {
    return stageState;
  }

}
