/*
 * ContractorSearchHelp.java
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
package com.mg.merp.reference.support.ui;

/**
 * Поиск контрагента для поля документа, имеющий все три позиции
 *
 * @author Anna V. Leonova
 * @version $Id: ContractorSearchHelp.java,v 1.3 2006/12/20 11:53:49 leonova Exp $
 */
public class ContractorSearchHelp extends UniversalContractorSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.document.support.ui.UniversalContractorSearchHelp#getContractorKinds()
   */
  @Override
  protected String[] getContractorKinds() {
    return null;
  }

}
