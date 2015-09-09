/*
 * ClassLinkServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.report.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.report.ClassLinkServiceLocal;
import com.mg.merp.report.model.ClassLink;

import javax.ejb.Stateless;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: ClassLinkServiceBean.java,v 1.2 2006/07/05 09:42:30 poroxnenko Exp $
 */
@Stateless(name = "merp/report/ClassLinkService")
public class ClassLinkServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ClassLink, Integer> implements ClassLinkServiceLocal {

}
