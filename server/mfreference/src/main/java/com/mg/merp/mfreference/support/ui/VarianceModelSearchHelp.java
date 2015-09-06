/*
 * VarianceModelSearchHelp.java
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
 * Поисковик "Образцов актов по отклонениям"
 *
 * @author Artem V. Sharapov
 * @version $Id: VarianceModelSearchHelp.java,v 1.2 2007/01/13 15:44:06 sharapov Exp $
 */
public class VarianceModelSearchHelp extends ModelSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.support.ui.ModelSearchHelp#getDocSectionPrimaryKey()
   */
  @Override
  protected short getDocSectionPrimaryKey() {
    //return VarianceDocumentHeadServiceLocal.DOCSECTION;
    /**
     * docsection для Документов по отклонениям
     */
    return 12009;
  }

}
