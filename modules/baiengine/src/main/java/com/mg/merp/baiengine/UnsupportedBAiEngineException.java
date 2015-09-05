/*
 * UnsupportedBAiEngineException.java
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

import com.mg.framework.utils.MiscUtils;
import com.mg.merp.baiengine.model.EngineType;
import com.mg.merp.baiengine.support.Messages;

/**
 * Класс ИС не поддерживаемого механизма выполнения бизнес расширений
 * 
 * @author Oleg V. Safonov
 * @version $Id: UnsupportedBAiEngineException.java,v 1.2 2007/11/15 13:12:08 safonov Exp $
 */
@javax.ejb.ApplicationException
public class UnsupportedBAiEngineException extends BusinessAddinException {
	private EngineType engineType;

	public UnsupportedBAiEngineException(EngineType engineType) {
		super("Unsupported BAi engine");
		this.engineType = engineType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return Messages.getInstance().getMessage(Messages.BAI_UNSUPPORTED_ENGINE, new Object[] {MiscUtils.getEnumTextRepresentation(engineType)});
	}

}
