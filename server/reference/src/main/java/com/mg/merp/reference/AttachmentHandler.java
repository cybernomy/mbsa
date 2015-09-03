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
 * Сервис, обеспечивающий основную функциональность работы с вложениями
 * 
 * @author Artem V. Sharapov
 * @version $Id: AttachmentHandler.java,v 1.2 2007/04/02 10:44:27 sharapov Exp $
 */
public interface AttachmentHandler { 
	
	/**
	 * загрузка содержимого вложения
	 * 
	 * @param entitylId - идентификатор сущности, содержащей вложение
	 * @return	содержимое вложения
	 */
	byte[] loadAttachmentBody(Integer entitylId);

	/**
	 * загрузка имени вложения
	 * 
	 * @param entitylId	- идентификатор сущности, содержащей вложение
	 * @return	имя вложения
	 */
	String loadAttachmentName(Integer entitylId);

	/**
	 * сохранение вложения
	 * 
	 * @param body - содержимое вложения
	 * @param name - имя вложения
	 * @param entitylId - идентификатор сущности, содержащей вложение
	 */
	void storeAttachment(byte[] body, String name, Integer entitylId);
	
	/**
	 * удаление содержимого вложений
	 * 
	 * @param entitylIds - идентификаторы сущностей, содержащих вложения
	 */
	void removeAttachment(Serializable[] entitylIds);

}
