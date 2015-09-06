/*
 * CostdetailServiceBean.java
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

package com.mg.merp.mfreference.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.mfreference.CostDetailServiceLocal;
import com.mg.merp.mfreference.model.CostDetail;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Расшифровка стоимости"
 *
 * @author leonova
 * @version $Id: CostDetailServiceBean.java,v 1.3 2006/09/07 10:57:04 leonova Exp $
 */
@Stateless(name = "merp/mfreference/CostDetailService")
public class CostDetailServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CostDetail, Integer> implements CostDetailServiceLocal {


}
