/*
 * PartnerEmplServiceBean.java
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
import com.mg.merp.reference.PartnerEmplServiceLocal;
import com.mg.merp.reference.model.PartnerEmpl;

/**
 * ������-��������� "���������� ��������" 
 * 
 * @author leonova
 * @version $Id: PartnerEmplServiceBean.java,v 1.3 2006/08/16 04:56:01 leonova Exp $
 */
@Stateless(name="merp/reference/PartnerEmplService")
public class PartnerEmplServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PartnerEmpl, Integer> implements PartnerEmplServiceLocal {


}
