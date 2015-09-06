/*
 * ScrapMachineModelSearchHelp.java
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
 * Поисковик "Образцов актов по списанию потерь оборудования"
 *
 * @author Artem V. Sharapov
 * @version $Id: ScrapMachineModelSearchHelp.java,v 1.2 2007/01/13 15:44:06 sharapov Exp $
 */
public class ScrapMachineModelSearchHelp extends ModelSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.support.ui.ModelSearchHelp#getDocSectionPrimaryKey()
   */
  @Override
  protected short getDocSectionPrimaryKey() {
    //return ScrapMachineHeadServiceLocal.DOCSECTION;
    /**
     * docsection для актов на списание потерь времени, отработанного оборудованием
     */
    return 12008;
  }

}
