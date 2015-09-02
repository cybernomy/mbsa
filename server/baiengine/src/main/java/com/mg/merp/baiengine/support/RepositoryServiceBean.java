/* RepositoryServiceBean.java
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
package com.mg.merp.baiengine.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.baiengine.RepositoryServiceLocal;
import com.mg.merp.baiengine.model.Repository;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: RepositoryServiceBean.java,v 1.1 2006/12/29 14:44:12 poroxnenko Exp $
 */
@Stateless(name = "merp/algorithmengine/AlgorithmRepositoryService")
public class RepositoryServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<Repository, Integer>
		implements RepositoryServiceLocal {

}
