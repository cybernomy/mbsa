/**
 * FormServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.core;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.core.model.Form;

/**
 * ������-��������� "����� �������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: FormServiceLocal.java,v 1.1 2008/03/03 12:55:18 safonov Exp $
 */
public interface FormServiceLocal extends DataBusinessObjectService<Form, Integer> {
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/core/SysForm";
}
