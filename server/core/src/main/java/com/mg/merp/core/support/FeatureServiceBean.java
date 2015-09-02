/*
 * FeatureServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.core.support;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.merp.core.FeatureLinkServiceLocal;
import com.mg.merp.core.FeatureServiceLocal;
import com.mg.merp.core.FeatureValServiceLocal;
import com.mg.merp.core.model.DataKind;
import com.mg.merp.core.model.Feature;
import com.mg.merp.core.model.FeatureLink;
import com.mg.merp.core.model.FeatureVal;

/**
 * Бизнес-компонент "Дополнительные признаки"
 * 
 * @author leonova
 * @version $Id: FeatureServiceBean.java,v 1.13 2007/11/08 12:03:10 sharapov Exp $
 */
@Stateless(name="merp/core/FeatureService") //$NON-NLS-1$
public class FeatureServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Feature, Integer> implements FeatureServiceLocal{

	protected void adjustFeature(Feature entity) {
		if (!entity.getIsArray())
			entity.setArraySize(null);
		if (DataKind.ENTITY != entity.getDataType())
			entity.setSysClass(null);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Feature entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Name")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "DataType")); //$NON-NLS-1$
		if (entity.getIsArray())
			context.addRule(new MandatoryAttribute(entity, "ArraySize")); //$NON-NLS-1$
		if (DataKind.ENTITY == entity.getDataType())
			context.addRule(new MandatoryAttribute(entity, "SysClass")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(Feature entity) {
		super.onCreate(entity);
		adjustFeature(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(Feature entity) {
		super.onStore(entity);
		adjustFeature(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.core.FeatureServiceLocal#findByCode(java.lang.String)
	 */
	@PermitAll
	public Feature findByCode(String code) {
		List<Feature> result = findByCriteria(Restrictions.eq("Code", code)); //$NON-NLS-1$
		if (!result.isEmpty())
			return result.get(0);
		else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onClone(Feature entity) {
		entity.setCode(DataUtils.generateUniqueString(20));
		entity.setName(entity.getName().trim().concat(DataUtils.generateUniqueString(4)));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doDeepClone(Feature entity, Feature entityClone) {
		final String FEATURE_ATTRIBUTE_NAME = "Feature"; //$NON-NLS-1$
		AttributeMap initAttributes = new LocalDataTransferObject();
		initAttributes.put(FEATURE_ATTRIBUTE_NAME, entityClone);
		FeatureValServiceLocal featureValService = (FeatureValServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/core/FeatureVal"); //$NON-NLS-1$
		FeatureLinkServiceLocal featureLinkService = (FeatureLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/core/FeatureLink"); //$NON-NLS-1$
		// копирование значений
		for (FeatureVal featureVal : featureValService.findByCriteria(Restrictions.eq(FEATURE_ATTRIBUTE_NAME, entity)))
			featureValService.clone(featureVal, true, initAttributes);
		// копирование связей
		for(FeatureLink featureLink : featureLinkService.findByCriteria(Restrictions.eq(FEATURE_ATTRIBUTE_NAME, entity)))
			featureLinkService.clone(featureLink, true, initAttributes);
	}

}
