/*
 * MRPShortageServiceBean.java
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
import com.mg.merp.planning.MRPShortageServiceLocal;
import com.mg.merp.planning.model.MrpShortage;
import com.mg.merp.planning.model.MrpVersionControl;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Дефицит товаров по результатом MRP расчета"
 *
 * @author Oleg V. Safonov
 * @version $Id: MRPShortageServiceBean.java,v 1.3 2007/07/30 10:36:48 safonov Exp $
 */
@Stateless(name = "merp/planning/MRPShortageService")
@PermitAll
public class MRPShortageServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<MrpShortage, Integer> implements MRPShortageServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.planning.MRPShortageServiceLocal#clear(com.mg.merp.planning.model.MrpVersionControl)
   */
  public void clear(MrpVersionControl mrpVersion) {
    OrmTemplate.getInstance().bulkUpdateByNamedQuery("Planing.MRPShortage.clear", "mrpVersionControl", mrpVersion);
  }

}
