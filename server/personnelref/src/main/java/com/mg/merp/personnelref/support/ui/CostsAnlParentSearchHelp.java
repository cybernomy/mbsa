/*
 * CostsAnlParentSearchHelp.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.personnelref.support.ui;

/**
 * Поисковик сущностей "Аналитика состава затрат" (верхнего уровня)
 *
 * @author Artem V. Sharapov
 * @version $Id: CostsAnlParentSearchHelp.java,v 1.1 2007/07/18 05:14:53 sharapov Exp $
 */
public class CostsAnlParentSearchHelp extends CostsAnlSearchHelp {

  private final String ANALITICS_LEVEL_IMPORT = "AnlLevel"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.merp.personnelref.support.ui.CostsAnlSearchHelp#getAnaliticsLevel()
   */
  @Override
  short getAnaliticsLevel() {
    return getParentAnaliticsLevel();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{ANALITICS_LEVEL_IMPORT};
  }

  /**
   * Получить верхний уровень аналитики состава затрат
   */
  protected short getParentAnaliticsLevel() {
    return new Short((short) ((Short) getImportContextValue(ANALITICS_LEVEL_IMPORT) - 1));
  }

}
