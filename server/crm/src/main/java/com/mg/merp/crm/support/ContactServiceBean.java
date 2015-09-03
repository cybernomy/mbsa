/*
 * ContactServiceBean.java
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

package com.mg.merp.crm.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.crm.ContactServiceLocal;
import com.mg.merp.crm.model.Contact;
import com.mg.merp.crm.model.ContactLink;
import com.mg.merp.crm.model.ContactLinkId;
import com.mg.merp.crm.model.Relation;

/**
 * Реализация бизнес-компонента "Контактные лица" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContactServiceBean.java,v 1.6 2007/07/27 08:13:57 safonov Exp $
 */
@Stateless(name="merp/crm/ContactService") //$NON-NLS-1$
public class ContactServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Contact, Integer> implements ContactServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.crm.ContactServiceLocal#linkRelation(com.mg.merp.crm.model.Contact, com.mg.merp.crm.model.Relation)
	 */
	@PermitAll
	public void linkRelation(Contact contact, Relation relation) {
		if(contact != null && relation != null) {
			ContactLink contactLink = createContactLink(contact, relation);
			if(!isContactLinkExist(contactLink.getId()))
				ServerUtils.getPersistentManager().persist(contactLink);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.crm.ContactServiceLocal#unLinkRelation(com.mg.merp.crm.model.Contact, com.mg.merp.crm.model.Relation)
	 */
	@PermitAll
	public void unLinkRelation(Contact contact, Relation relation) {
		if(contact != null && relation != null) {
			ServerUtils.getPersistentManager().remove(createContactLink(contact, relation));
		}
	}

	/**
	 * Проверяет существование связи "отношение-контакт"
	 * @param contactLinkId - идентификатор связи
	 * @return
	 */
	private boolean isContactLinkExist(ContactLinkId contactLinkId) {
		ContactLink contactLink = ServerUtils.getPersistentManager().find(ContactLink.class, contactLinkId);
		if(contactLink != null)
			return true;
		else
			return false;
	}

	/**
	 * Создать связь "отношение-контакт"
	 * @param contact - контакт
	 * @param relation - отношение
	 * @return связь "отношение-контакт"
	 */
	private ContactLink createContactLink(Contact contact, Relation relation) {
		if(contact == null || relation == null)
			return null;
		ContactLink contactLink = new ContactLink();
		ContactLinkId contactLinkId = new ContactLinkId(); 
		contactLinkId.setCrmContact(contact);
		contactLinkId.setCrmRelation(relation);
		contactLinkId.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());
		contactLink.setId(contactLinkId);
		return contactLink;
	}

}
