package com.mg.merp.document.generic;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;

import java.io.Serializable;

public abstract class DocumentModelServiceBean<T extends DocHeadModel, ID extends Serializable> extends AbstractPOJODataBusinessObjectServiceBean<T, ID>
    implements DocumentPattern<T, ID> {

  @Override
  protected void onValidate(ValidationContext context, T entity) {
    context.addRule(new MandatoryStringAttribute(entity, "ModelName"));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(T entity) {
    entity.setDocSection(getDocSection());
  }

  protected DocSection getDocSection() {
    return getPersistentManager().find(DocSection.class, (int) getDocSectionIdentifier());
  }

  protected abstract short getDocSectionIdentifier();

}
