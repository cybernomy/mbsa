/*
 * PlaceServiceBean.java
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

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.reference.PlaceServiceLocal;
import com.mg.merp.reference.model.Place;

/**
 * Бизнес-компонент "Населенные пункты"
 * 
 * @author leonova
 * @version $Id: PlaceServiceBean.java,v 1.3 2006/08/03 10:37:01 leonova Exp $
 */
@Stateless(name="merp/reference/PlaceService")
public class PlaceServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Place, Integer> implements PlaceServiceLocal {


}
