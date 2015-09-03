/*
 * ModifyDocumentServiceBean.java
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

package com.mg.merp.contract.support;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.DataUtils;
import com.mg.merp.contract.ModifyDocumentServiceLocal;
import com.mg.merp.contract.model.ModifyDocument;

/**
 * Реализация бизнес-компонента "Изменение контракта" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ModifyDocumentServiceBean.java,v 1.6 2007/12/03 09:47:39 sharapov Exp $
 */
@Stateless(name="merp/contract/ModifyDocumentService")
public class ModifyDocumentServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ModifyDocument, Integer> implements ModifyDocumentServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.AttachmentHandler#loadAttachmentBody(java.lang.Integer)
	 */
	public byte[] loadAttachmentBody(Integer originalId) {
		return extractAttachmentBody(load(originalId));
	}

	private byte[] extractAttachmentBody(ModifyDocument entity) {
		if (entity == null)
			throw new IllegalArgumentException("entity is null"); //$NON-NLS-1$

		return DataUtils.extractOriginalBody(entity.getOriginal());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.AttachmentHandler#loadAttachmentName(java.lang.Integer)
	 */
	@PermitAll
	public String loadAttachmentName(Integer originalId) {
		return extractAttachmentName(load(originalId));
	}

	private String extractAttachmentName(ModifyDocument entity) {
		if (entity == null)
			throw new IllegalArgumentException("entity is null"); //$NON-NLS-1$

		return DataUtils.extractOriginalName(entity.getOriginal());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.AttachmentHandler#removeAttachment(java.io.Serializable[])
	 */
	public void removeAttachment(Serializable[] originalIds) {
		for (int i = 0; i < originalIds.length; i++) {
			ModifyDocument entity = load((Integer) originalIds[i]);
			entity.setOriginal(null);
			getPersistentManager().merge(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.AttachmentHandler#storeAttachment(byte[], java.lang.String, java.lang.Integer)
	 */
	public void storeAttachment(byte[] body, String name, Integer originalId) {
		ModifyDocument entity = load(originalId);
		entity.setOriginal(DataUtils.originalToBinary(body, name));
		getPersistentManager().merge(entity);
	}

}
