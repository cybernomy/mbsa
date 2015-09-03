/*
 * InputLaborSpecServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.merp.manufacture.model.InputDocumentSpec;


/**
 * Бизнес-компонент "Спецификации актов на списание времени РС"
 * 
 * @author Oleg V. Safonov
 * @version $Id: InputLaborSpecServiceLocal.java,v 1.4 2007/08/06 12:46:24 safonov Exp $
 */
public interface InputLaborSpecServiceLocal
		extends InputSpecificationServiceLocal<InputDocumentSpec> {

	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/manufacture/InputLaborSpec";

}
