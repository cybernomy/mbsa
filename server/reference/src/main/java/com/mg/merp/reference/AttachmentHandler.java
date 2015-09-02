/*
 * AttachmentHandler.java
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
package com.mg.merp.reference;

import java.io.Serializable;

/**
 * —ервис, обеспечивающий основную функциональность работы с вложени€ми
 * 
 * @author Artem V. Sharapov
 * @version $Id: AttachmentHandler.java,v 1.2 2007/04/02 10:44:27 sharapov Exp $
 */
public interface AttachmentHandler { 
	
	/**
	 * загрузка содержимого вложени€
	 * 
	 * @param entitylId - идентификатор сущности, содержащей вложение
	 * @return	содержимое вложени€
	 */
	byte[] loadAttachmentBody(Integer entitylId);

	/**
	 * загрузка имени вложени€
	 * 
	 * @param entitylId	- идентификатор сущности, содержащей вложение
	 * @return	им€ вложени€
	 */
	String loadAttachmentName(Integer entitylId);

	/**
	 * сохранение вложени€
	 * 
	 * @param body - содержимое вложени€
	 * @param name - им€ вложени€
	 * @param entitylId - идентификатор сущности, содержащей вложение
	 */
	void storeAttachment(byte[] body, String name, Integer entitylId);
	
	/**
	 * удаление содержимого вложений
	 * 
	 * @param entitylIds - идентификаторы сущностей, содержащих вложени€
	 */
	void removeAttachment(Serializable[] entitylIds);

}
