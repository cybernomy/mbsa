/*
 * MRPOutputsServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

package com.mg.merp.planning.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.merp.planning.MRPOutputsServiceLocal;
import com.mg.merp.planning.model.MrpOutputs;
import com.mg.merp.planning.model.MrpVersionControl;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Потребности товаров для расчета MRP"
 *
 * @author Oleg V. Safonov
 * @version $Id: MRPOutputsServiceBean.java,v 1.3 2007/07/30 10:36:48 safonov Exp $
 */
@Stateless(name = "merp/planning/MRPOutputsService")
@PermitAll
public class MRPOutputsServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<MrpOutputs, Integer> implements MRPOutputsServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.planning.MRPOutputsServiceLocal#clear(com.mg.merp.planning.model.MrpVersionControl)
   */
  public void clear(MrpVersionControl mrpVersion) {
    OrmTemplate.getInstance().bulkUpdateByNamedQuery("Planing.MRPOutputs.clear", "mrpVersionControl", mrpVersion);
  }

}
