/*
 * CompanyServiceBean.java
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

package com.mg.merp.settlement.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.settlement.CompanyServiceLocal;

/**
 * Реализация бизнес-компонента "Центры учета" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CompanyServiceBean.java,v 1.4 2007/03/20 10:32:25 sharapov Exp $
 */
@Stateless(name="merp/settlement/CompanyService") //$NON-NLS-1$
public class CompanyServiceBean extends AbstractPOJODataBusinessObjectServiceBean<OrgUnit, Integer> implements CompanyServiceLocal {

}
