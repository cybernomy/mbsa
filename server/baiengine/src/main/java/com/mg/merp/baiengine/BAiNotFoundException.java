/*
 * BAiNotFoundException.java
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
package com.mg.merp.baiengine;

import com.mg.merp.baiengine.support.Messages;

/**
 * Класс ИС указывающий о проблеме обнаружения бизнес расширения в репозитарии
 * 
 * @author Oleg V. Safonov
 * @version $Id: BAiNotFoundException.java,v 1.1 2006/10/12 12:02:05 safonov Exp $
 */
@javax.ejb.ApplicationException
public class BAiNotFoundException extends BusinessAddinException {

	public BAiNotFoundException(String name) {
		super(Messages.getInstance().getMessage(Messages.BAI_NOT_FOUND, new Object[] {name}));
	}

}
