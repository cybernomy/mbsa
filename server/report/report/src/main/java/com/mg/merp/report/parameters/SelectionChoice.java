/**
 * SelectionChoice.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.report.parameters;

/**
 * Defines one choice in a parameter selction value list
 *
 * @author Oleg V. Safonov
 * @version $Id: SelectionChoice.java,v 1.1 2008/05/13 15:08:53 safonov Exp $
 */
public interface SelectionChoice {

  /**
   * returns the value of the selection choice
   *
   * @return the value of the selction choice
   */
  Object getValue();

  /**
   * returns the locale-specific label for a selection choice. The locale used is the locale in the
   * parameter definition request.
   *
   * @return the localized label for the parameter
   */
  String getLabel();

}
