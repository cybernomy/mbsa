/*
 * BOMMaterialCostCategoryEmptyException.java
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
package com.mg.merp.manufacture;

import com.mg.framework.api.BusinessException;
import com.mg.merp.mfreference.model.BomMaterial;

import javax.ejb.ApplicationException;

/**
 * @author Oleg V. Safonov
 * @version $Id: BOMMaterialCostCategoryEmptyException.java,v 1.1 2007/08/06 12:46:24 safonov Exp $
 */
@ApplicationException
public class BOMMaterialCostCategoryEmptyException extends BusinessException {

  public BOMMaterialCostCategoryEmptyException(BomMaterial material) {

  }

}
