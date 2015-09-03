/*
 * LiabilityServiceBean.java
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

package com.mg.merp.paymentcontrol.support;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.support.validator.PositiveAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocType;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;
import com.mg.merp.paymentcontrol.model.Execution;
import com.mg.merp.paymentcontrol.model.Liability;

/**
 * Реализация бизнес-компонента "Обязательства" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LiabilityServiceBean.java,v 1.11 2007/07/12 09:31:16 safonov Exp $
 */
@Stateless(name="merp/paymentcontrol/LiabilityService") //$NON-NLS-1$
public class LiabilityServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Liability, Integer> implements LiabilityServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Liability entity) {
		context.addRule(new MandatoryStringAttribute(entity, "CurCode")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurRateType")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurRateAuthority")); //$NON-NLS-1$
		context.addRule(new PositiveAttribute(entity, "SumCur")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.LiabilityServiceLocal#getExecutedSum(java.lang.Integer, java.lang.Integer)
	 */
	@PermitAll
	public BigDecimal getExecutedSum(Integer liabilityId, Integer versionId) {
		Criteria criteria = OrmTemplate.createCriteria(Execution.class)
		.setProjection(Projections.sum("SumCur")) //$NON-NLS-1$
		.add(Restrictions.eq("Liability.Id", liabilityId)); //$NON-NLS-1$

		if(versionId != null) 
			criteria.add(Restrictions.eq("Version.Id", versionId)); //$NON-NLS-1$
		else
			criteria.add(Restrictions.isNull("Version")); //$NON-NLS-1$

		Object result = OrmTemplate.getInstance().findUniqueByCriteria(criteria);

		if(result == null)
			return BigDecimal.ZERO;
		else
			return (BigDecimal) result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.LiabilityServiceLocal#getRemnSum(java.lang.Integer, java.lang.Integer)
	 */
	@PermitAll
	public BigDecimal getRemnSum(Integer liabilityId, Integer versionId) {
		return doGetRemnSum(liabilityId, versionId);
	}

	private BigDecimal doGetRemnSum(Integer liabilityId, Integer versionId) {
		Liability liability = load(liabilityId);
		BigDecimal remnSumm  = liability.getSumCur().subtract(getExecutedSum(liabilityId, versionId));
		return remnSumm;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.LiabilityServiceLocal#createByPattern(com.mg.merp.paymentcontrol.model.Liability, com.mg.merp.core.model.Folder)
	 */
	@PermitAll
	public Liability createByPattern(Liability pattern, Folder folder) {
		return doCreateByPattern(pattern, folder);
	}

	/**
	 * Создание обязательства по образцу
	 * @param pattern - образец
	 * @param folder - папка-приемник
	 * @return обязательство
	 */
	protected Liability doCreateByPattern(Liability pattern, Folder folder) {
		Liability liability = initialize();
		AttributeMap attributes = pattern.getAllAttributes(); 
		attributes.remove("Id"); //$NON-NLS-1$
		liability.setAttributes(attributes);
		liability.setIsModel(false);
		if(pattern.getDestFolder() != null)
			liability.setFolder(pattern.getDestFolder());
		else
			liability.setFolder(folder);
		return liability;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.LiabilityServiceLocal#getRootFolder()
	 */
	@PermitAll
	public Folder getRootFolder() {
		return doGetRootFolder();
	}

	protected Folder doGetRootFolder() {
		return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Folder.class)
				.add(Restrictions.isNull("Folder")) //$NON-NLS-1$
				.add(Restrictions.eq("FolderType", LiabilityServiceLocal.FOLDER_PART))); //$NON-NLS-1$
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.LiabilityServiceLocal#findDoc(com.mg.merp.paymentcontrol.model.Liability)
	 */
	@PermitAll
	public DocHead findDoc(Liability liability) {
		return doFindDoc(liability);
	}
	
	protected DocHead doFindDoc(Liability liability) {
		if(liability.getDocHead() != null)
			return liability.getDocHead();
		else
			return findDocHeadByParams(liability.getDocType(), liability.getDocNumber(), liability.getDocDate());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.LiabilityServiceLocal#findBaseDoc(com.mg.merp.paymentcontrol.model.Liability)
	 */
	@PermitAll
	public DocHead findBaseDoc(Liability liability) {
		return doFindBaseDoc(liability);
	}
	
	protected DocHead doFindBaseDoc(Liability liability) {
		if(liability.getBaseDoc() != null)
			return liability.getBaseDoc();
		else
			return findDocHeadByParams(liability.getBaseDocType(), liability.getBaseDocNumber(), liability.getBaseDocDate());
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.LiabilityServiceLocal#findContract(com.mg.merp.paymentcontrol.model.Liability)
	 */
	@PermitAll
	public DocHead findContract(Liability liability) {
		return doFindContract(liability);
	}

	protected DocHead doFindContract(Liability liability) {
		if(liability.getContract() != null)
			return liability.getContract();
		else
			return findDocHeadByParams(liability.getContractType(), liability.getContractNumber(), liability.getContractDate());
	}

	/**
	 * Найти заголовок документа по заданным параметрам
	 * @param type - тип документа
	 * @param number - номер документа
	 * @param date - дата документа
	 * @return заголовок документа
	 */
	private DocHead findDocHeadByParams(DocType type, String number, Date date) {
		if(number != null)
			number = StringUtils.padLeft(number.trim(), DocHead.DOCNUMBER_LENGTH, ' ', true);
		return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(DocHead.class)
					.add(Restrictions.eq("DocType", type)) //$NON-NLS-1$
					.add(Restrictions.eq("DocNumber", number)) //$NON-NLS-1$
					.add(Restrictions.eq("DocDate", date))); //$NON-NLS-1$
	}
	
}
