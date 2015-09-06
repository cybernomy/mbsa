/*
 * ProblemRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.crm.model.Solution;

/**
 * Контроллер формы условий отбора бизнес-компонента "Проблемы"
 *
 * @author Artem V. Sharapov
 * @version $Id: ProblemRest.java,v 1.1 2007/02/07 07:01:42 sharapov Exp $
 */
public class ProblemRest extends DefaultHierarhyRestrictionForm {

  // Fields

  @DataItemName("CRM.BigName") //$NON-NLS-1$
  private String searchText;

  @DataItemName("CRM.ProblemRest.Solution") //$NON-NLS-1$
  private Solution solution;

  private boolean isSearchInName = true;
  private boolean isSearchInKeyWords = true;
  private boolean isSearchInInfo;
  private boolean isExact;
  private boolean isCaseSensetive;

  // Methods

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
   */
  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.searchText = null;
    this.solution = null;
  }

  // Property accessors

  /**
   * @return the isSearchInInfo
   */
  public boolean isSearchInInfo() {
    return isSearchInInfo;
  }

  /**
   * @param isSearchInInfo the isSearchInInfo to set
   */
  public void setSearchInInfo(boolean isSearchInInfo) {
    this.isSearchInInfo = isSearchInInfo;
  }

  /**
   * @return the isSearchInKeyWords
   */
  public boolean isSearchInKeyWords() {
    return isSearchInKeyWords;
  }

  /**
   * @param isSearchInKeyWords the isSearchInKeyWords to set
   */
  public void setSearchInKeyWords(boolean isSearchInKeyWords) {
    this.isSearchInKeyWords = isSearchInKeyWords;
  }

  /**
   * @return the isSearchInName
   */
  public boolean isSearchInName() {
    return isSearchInName;
  }

  /**
   * @param isSearchInName the isSearchInName to set
   */
  public void setSearchInName(boolean isSearchInName) {
    this.isSearchInName = isSearchInName;
  }

  /**
   * @return the searchText
   */
  public String getSearchText() {
    return searchText;
  }

  /**
   * @param searchText the searchText to set
   */
  public void setSearchText(String searchText) {
    this.searchText = searchText;
  }

  /**
   * @return the solution
   */
  public Solution getSolution() {
    return solution;
  }

  /**
   * @param solution the solution to set
   */
  public void setSolution(Solution solution) {
    this.solution = solution;
  }

  /**
   * @return the isCaseSensetive
   */
  public boolean isCaseSensetive() {
    return isCaseSensetive;
  }

  /**
   * @param isCaseSensetive the isCaseSensetive to set
   */
  public void setCaseSensetive(boolean isCaseSensetive) {
    this.isCaseSensetive = isCaseSensetive;
  }

  /**
   * @return the isExact
   */
  public boolean isExact() {
    return isExact;
  }

  /**
   * @param isExact the isExact to set
   */
  public void setExact(boolean isExact) {
    this.isExact = isExact;
  }

}
