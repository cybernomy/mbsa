/*
 * ContactServiceLocal.java
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
package com.mg.merp.crm;

import com.mg.merp.crm.model.Contact;
import com.mg.merp.crm.model.Relation;

/**
 * Сервис бизнес-компонента "Контактные лица" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContactServiceLocal.java,v 1.2 2007/02/07 06:58:56 sharapov Exp $
 */
public interface ContactServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Contact, Integer> {

	/**
	 * Добавить связь "отношение - контактное лицо"
	 * @param contact - контактное лицо
	 * @param relation - отношение
	 */
	void linkRelation(Contact contact, Relation relation);

	/**
	 * Удалить связь "отношение - контактное лицо"
	 * @param contact - контактное лицо
	 * @param relation - отношение
	 */
	void unLinkRelation(Contact contact, Relation relation);
}
