/*
 * SolutionsearchServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.crm.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.crm.SolutionSearchServiceLocal;

import javax.ejb.Stateless;

/**
 * @author Oleg V. Safonov
 * @version $Id: SolutionSearchServiceBean.java,v 1.4 2006/09/21 12:47:44 safonov Exp $
 */
@Stateless(name = "merp/crm/SolutionSearchService")
public class SolutionSearchServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean implements SolutionSearchServiceLocal {

  /**
   * @ejb.interface-method view-type = "local"
   */
  public void saveSearch(int[] symptoms, int[] problemsAdded, int[] problemsRemoved) throws ApplicationException {
    //TODO
    //((SolutionSearchDomainImpl) getDomain()).saveSearch(symptoms, problemsAdded, problemsRemoved);
  }

}
