/*
 * InputLaborModelSearchHelp.java
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
package com.mg.merp.mfreference.support.ui;


/**
 * Поисковик "Образцов актов по обратному списанию рабочей силы"
 *
 * @author Artem V. Sharapov
 * @version $Id: InputLaborModelSearchHelp.java,v 1.2 2007/01/13 15:44:06 sharapov Exp $
 */
public class InputLaborModelSearchHelp extends ModelSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.support.ui.InputModelSearchHelp#getDocSectionPrimaryKey()
   */
  @Override
  protected short getDocSectionPrimaryKey() {
    /**
     * docsection для актов на списание времени, отработанного РС в НЗП
     */
    return 12003;
    //return InputLaborHeadServiceLocal.DOCSECTION;
  }

}
