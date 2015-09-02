/*
 * ResourceServiceBean.java
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.JoinType;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.paymentcontrol.ResourceServiceLocal;
import com.mg.merp.paymentcontrol.model.Execution;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.paymentcontrol.model.TurnResult;

/**
 * Реализация бизнес-компонента "Средства платежа" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ResourceServiceBean.java,v 1.6 2007/09/10 08:08:27 sharapov Exp $
 */
@Stateless(name="merp/paymentcontrol/ResourceService") //$NON-NLS-1$
public class ResourceServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PmcResource, Integer> implements ResourceServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PmcResource entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurCode")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurRateType")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurRateAuthority")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "OrgUnit")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.ResourceServiceLocal#getBalance(java.lang.Integer, java.util.Date)
	 */
	@PermitAll
	public BigDecimal getBalance(Integer resourceId, Date dateBalance) {
		BigDecimal incomeSum = getAgregateSum(resourceId, dateBalance, true);
		BigDecimal expenseSum = getAgregateSum(resourceId, dateBalance, false);
		return incomeSum.subtract(expenseSum);
	}

	/**
	 * Получить приход/расход средства платежа
	 * @param resourceId - идентификатор средства платежа
	 * @param date - расчет на дату
	 * @param isIncome - признак(приход/расход)
	 * @return приход/расход
	 */
	private BigDecimal getAgregateSum(Integer resourceId, Date date, boolean isIncome) {
		Criteria criteria = OrmTemplate.createCriteria(Execution.class)
		.setProjection(Projections.sum("SumCur")) //$NON-NLS-1$
		.add(Restrictions.eq("Resource.Id", resourceId)) //$NON-NLS-1$
		.add(Restrictions.isNull("Version")) //$NON-NLS-1$
		.add(Restrictions.eq("Receivable", isIncome)) //$NON-NLS-1$
		.add(Restrictions.le("PlanDate", date)); //$NON-NLS-1$

		Object result = OrmTemplate.getInstance().findUniqueByCriteria(criteria);

		if(result != null)
			return (BigDecimal) result;
		else
			return BigDecimal.ZERO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.ResourceServiceLocal#getResurceGroups(java.lang.Integer)
	 */
	@PermitAll
	public List<Folder> getResurceGroups(Integer parentId) {
		return doGetResurceGroups(parentId);
	}

	private List<Folder> doGetResurceGroups(Integer parentId) {
		Criteria criteia = OrmTemplate.createCriteria(Folder.class).add(Restrictions.eq("FolderType", ResourceServiceLocal.FOLDER_PART)); //$NON-NLS-1$
		if(parentId == null)
			criteia.add(Restrictions.isNull("Folder")); //$NON-NLS-1$
		else
			criteia.add(Restrictions.eq("Folder.Id", parentId)); //$NON-NLS-1$
		return OrmTemplate.getInstance().findByCriteria(criteia);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.ResourceServiceLocal#getResourcesByGroup(java.lang.Integer)
	 */
	@PermitAll
	public List<PmcResource> getResourcesByGroup(Integer folderId) {
		return doGetResourcesByGroup(folderId);
	}

	private List<PmcResource> doGetResourcesByGroup(Integer folderId) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PmcResource.class)
				.add(Restrictions.eq("Folder.Id", folderId))); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.ResourceServiceLocal#getNestedResurceGroups(java.lang.Integer, boolean)
	 */
	public List<Folder> getNestedResurceGroups(Integer rootFolderId, boolean isIncludeRootFolder) {
		return doGetNestedResurceGroups(rootFolderId, isIncludeRootFolder);
	}

	private List<Folder> doGetNestedResurceGroups(Integer rootFolderId, boolean isIncludeRootFolder) {
		List<Folder> nestedFolders = new ArrayList<Folder>();
		if(isIncludeRootFolder)
			nestedFolders.add(ServerUtils.getPersistentManager().find(Folder.class, rootFolderId));

		iterateResourceFolders(rootFolderId, nestedFolders);
		return nestedFolders;
	}

	private void iterateResourceFolders(Integer parentId, List<Folder> nestedFolders) {
		if(parentId == null)
			return;
		List<Folder> folders = getFolders(parentId);
		if(!folders.isEmpty())
			for(Folder folder : folders) {
				nestedFolders.add(folder);
				iterateResourceFolders(folder.getId(), nestedFolders);
			}
	}

	private List<Folder> getFolders(Integer parentId) {
		Criteria criteia = OrmTemplate.createCriteria(Folder.class).add(Restrictions.eq("FolderType", ResourceServiceLocal.FOLDER_PART)); //$NON-NLS-1$
		criteia.add(Restrictions.eq("Folder.Id", parentId)); //$NON-NLS-1$
		return OrmTemplate.getInstance().findByCriteria(criteia);	
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.ResourceServiceLocal#getTurnByResource(java.lang.Integer, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	@PermitAll
	public TurnResult getTurnByResource(Integer resourceId, Integer versionId, Date dateFrom, Date dateTill) {
		return doGetTurnByResource(resourceId, versionId, dateFrom, dateTill);
	}

	private TurnResult doGetTurnByResource(Integer resourceId, Integer versionId, Date dateFrom, Date dateTill) {
		BigDecimal beginSaldo = getBeginSaldoByResource(resourceId, versionId, dateFrom, dateTill);
		BigDecimal income = getAgregateSumByResource(resourceId, versionId, dateFrom, dateTill, true);
		BigDecimal expense = getAgregateSumByResource(resourceId, versionId, dateFrom, dateTill, false);
		BigDecimal endSaldo = beginSaldo.add(income).subtract(expense);
		return new TurnResult(beginSaldo, income, expense, endSaldo);
	}

	/**
	 * Получить остаток на начало для средства платежа
	 * @param resourceId - идентификатор средства платежа
	 * @param versionId - идентификатор версии планирования
	 * @param dateFrom - период (дата с)
	 * @param dateTill - период (дата по)
	 * @return остаток на начало для средства платежа
	 */
	private BigDecimal getBeginSaldoByResource(Integer resourceId, Integer versionId, Date dateFrom, Date dateTill) {
		Locale locale = ServerUtils.getUserLocale();
		BigDecimal income = getAgregateSumByResource(resourceId, versionId, null, PmcPeriodUtils.incDay(dateFrom, -1, locale), true);
		BigDecimal expense = getAgregateSumByResource(resourceId, versionId, null, PmcPeriodUtils.incDay(dateFrom, -1, locale), false);
		BigDecimal beginSaldo = income.subtract(expense);
		return beginSaldo;
	}

	private BigDecimal getAgregateSumByResource(Integer resourceId, Integer versionId, Date dateFrom, Date dateTill, boolean isReceivable) {
		Criteria criteria = OrmTemplate.createCriteria(Execution.class)
		.setProjection(Projections.sum("SumCur")) //$NON-NLS-1$
		.add(Restrictions.eq("Resource.Id", resourceId)) //$NON-NLS-1$
		.add(Restrictions.eq("Version.Id", versionId)) //$NON-NLS-1$
		.add(Restrictions.eq("Receivable", isReceivable)); //$NON-NLS-1$

		if(dateFrom == null)
			criteria.add(Restrictions.le("PlanDate", dateTill)); //$NON-NLS-1$
		else
			criteria.add(Restrictions.between("PlanDate", dateFrom, dateTill)); //$NON-NLS-1$

		Object result = OrmTemplate.getInstance().findUniqueByCriteria(criteria);

		if(result != null)
			return (BigDecimal) result;
		else
			return BigDecimal.ZERO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.ResourceServiceLocal#getTurnByResourceGroup(java.lang.Integer, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	@PermitAll
	public TurnResult getTurnByResourceGroup(Integer resourceFolderId, Integer versionId, Date dateFrom, Date dateTill) {
		return doGetTurnByResourceGroup(resourceFolderId, versionId, dateFrom, dateTill);
	}

	private TurnResult doGetTurnByResourceGroup(Integer resourceFolderId, Integer versionId, Date dateFrom, Date dateTill) {
		List<Folder> nestedFolders = getNestedResurceGroups(resourceFolderId, true);
		BigDecimal beginSaldo = getBeginSaldoByResourceGroup(nestedFolders.toArray(), versionId, dateFrom, dateTill);
		BigDecimal income = getAgregateSumByResourceGroup(nestedFolders.toArray(), versionId, dateFrom, dateTill, true); 
		BigDecimal expense = getAgregateSumByResourceGroup(nestedFolders.toArray(), versionId, dateFrom, dateTill, false);
		BigDecimal endSaldo = beginSaldo.add(income).subtract(expense);
		return new TurnResult(beginSaldo, income, expense, endSaldo);
	}

	/**
	 * Получить остаток на начало для группы(папки) средства платежа
	 * @param nestedFolders - вложенные группы(папки) средства платежа
	 * @param versionId - идентификатор версии планирования
	 * @param dateFrom - период(дата с)
	 * @param dateTill - период(дата по)
	 * @return остаток на начало для группы(папки) средства платежа
	 */
	private BigDecimal getBeginSaldoByResourceGroup(Object[] nestedFolders, Integer versionId, Date dateFrom, Date dateTill) {
		Locale locale = ServerUtils.getUserLocale();
		BigDecimal income = getAgregateSumByResourceGroup(nestedFolders,  versionId, null, PmcPeriodUtils.incDay(dateFrom, -1, locale), true);
		BigDecimal expense = getAgregateSumByResourceGroup(nestedFolders, versionId, null, PmcPeriodUtils.incDay(dateFrom, -1, locale), false);
		BigDecimal beginSaldo = income.subtract(expense);
		return beginSaldo;
	}

	private BigDecimal getAgregateSumByResourceGroup(Object[] nestedFolders, Integer versionId, Date dateFrom, Date dateTill, boolean isReceivable) {
		Criteria criteria = OrmTemplate.createCriteria(Execution.class)
		.setProjection(Projections.sum("SumNat")) //$NON-NLS-1$
		.createAlias("Resource", "r", JoinType.LEFT_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
		.add(Restrictions.eq("Version.Id", versionId)) //$NON-NLS-1$
		.add(Restrictions.eq("Receivable", isReceivable)) //$NON-NLS-1$
		.add(Restrictions.or(
				Restrictions.and(Restrictions.isNull("ResourceFolder"), Restrictions.in("r.Folder", nestedFolders)), //$NON-NLS-1$ //$NON-NLS-2$
				Restrictions.and(Restrictions.isNull("Resource"), Restrictions.in("ResourceFolder", nestedFolders)))); //$NON-NLS-1$ //$NON-NLS-2$

		if(dateFrom == null)
			criteria.add(Restrictions.le("PlanDate", dateTill)); //$NON-NLS-1$
		else
			criteria.add(Restrictions.between("PlanDate", dateFrom, dateTill)); //$NON-NLS-1$

		Object result = OrmTemplate.getInstance().findUniqueByCriteria(criteria);

		if(result != null)
			return (BigDecimal) result;
		else
			return BigDecimal.ZERO;
	}

}
