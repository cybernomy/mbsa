/*
 * DistrictServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.reference.DistrictServiceLocal;
import com.mg.merp.reference.model.District;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Районы"
 *
 * @author leonova
 * @version $Id: DistrictServiceBean.java,v 1.3 2006/08/03 11:17:05 leonova Exp $
 */
@Stateless(name = "merp/reference/DistrictService")
public class DistrictServiceBean extends AbstractPOJODataBusinessObjectServiceBean<District, Integer> implements DistrictServiceLocal {

}
