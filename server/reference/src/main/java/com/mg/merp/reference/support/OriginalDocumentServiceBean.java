/*
 * CatalogServiceBean.java
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

package com.mg.merp.reference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.merp.reference.OriginalDocumentServiceLocal;
import com.mg.merp.reference.model.OriginalDocument;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Оригиналы докуметов"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: OriginalDocumentServiceBean.java,v 1.7 2007/07/12 13:35:48 safonov Exp $
 */
@Stateless(name = "merp/reference/OriginalDocumentService") //$NON-NLS-1$
public class OriginalDocumentServiceBean extends AbstractPOJODataBusinessObjectServiceBean<OriginalDocument, Integer> implements OriginalDocumentServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, OriginalDocument entity) {
    context.addRule(new MandatoryStringAttribute(entity, "DocName")); //$NON-NLS-1$
  }

  private byte[] extractOriginalBody(OriginalDocument entity) {
    if (entity == null)
      throw new IllegalArgumentException("entity is null"); //$NON-NLS-1$

    return DataUtils.extractOriginalBody(entity.getOriginal());
  }

  private String extractOriginalName(OriginalDocument entity) {
    if (entity == null)
      throw new IllegalArgumentException("entity is null"); //$NON-NLS-1$

    return DataUtils.extractOriginalName(entity.getOriginal());
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.AttachmentHandler#loadAttachmentBody(java.lang.Integer)
   */
  public byte[] loadAttachmentBody(Integer originalId) {
    return extractOriginalBody(load(originalId));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.AttachmentHandler#loadAttachmentName(java.lang.Integer)
   */
  @PermitAll
  public String loadAttachmentName(Integer originalId) {
    return extractOriginalName(load(originalId));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.AttachmentHandler#storeAttachment(byte[], java.lang.String, java.lang.Integer)
   */
  public void storeAttachment(byte[] body, String name, Integer originalId) {
    OriginalDocument entity = load(originalId);
    entity.setOriginal(DataUtils.originalToBinary(body, name));
    getPersistentManager().merge(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.AttachmentHandler#removeAttachment(java.io.Serializable[])
   */
  public void removeAttachment(Serializable[] originalIds) {
    for (int i = 0; i < originalIds.length; i++) {
      OriginalDocument entity = load((Integer) originalIds[i]);
      entity.setOriginal(null);
      getPersistentManager().merge(entity);
    }
  }

}
