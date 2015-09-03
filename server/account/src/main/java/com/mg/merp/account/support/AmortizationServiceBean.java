/*
 * AmortizationServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

package com.mg.merp.account.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.AmortizationServiceLocal;
import com.mg.merp.account.EconomicSpecServiceLocal;
import com.mg.merp.account.InvHistoryServiceLocal;
import com.mg.merp.account.OperationServiceLocal;
import com.mg.merp.account.model.Amortization;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.model.InvActionKind;
import com.mg.merp.account.model.InvHistory;
import com.mg.merp.account.model.Inventory;
import com.mg.merp.core.model.Folder;
import com.mg.merp.reference.model.Contractor;

/**
 * Реализация бизнес-компонента "Ведомость начисления амортизации"
 * 
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: AmortizationServiceBean.java,v 1.7 2008/05/08 09:05:13 alikaev Exp $
 */
@Stateless(name="merp/account/AmortizationService")
public class AmortizationServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<Amortization, Integer> implements AmortizationServiceLocal {
	
	private EconomicSpecServiceLocal economicSpecService = null;
	
	private InvHistoryServiceLocal invHistoryService = null;
	
	private OperationServiceLocal operationService = null;
	
	private final String COMMENT_ECONOMIC_OPER = Messages.getInstance().getMessage(Messages.COMMENT_CALC_AMORTIZATION);
		
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(Amortization entity) {
		entity.setSumTotal(MathUtils.addNullable(entity.getSumTotal(), entity.getSumAdd(), new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec())));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.AmortizationServiceLocal#commitAmortization(java.lang.Integer, com.mg.merp.core.model.Folder)
	 */
	@PermitAll
	public void commitAmortization(Integer batch, Folder folder) {
		doCommitAmortization(batch, folder);
	}

	/**
	 * Создание хоз. операций по партии амортизаций
	 * 
	 * @param batch
	 * 			- служебное поле у амортизации(номер партии)
	 * @param folder
	 * 			- папка для создания хоз. операции
	 */
	protected void doCommitAmortization(Integer batch, Folder folder) {
		OrmTemplate ormTemplate = OrmTemplate.getInstance();
		List<AmortItems> amortizations = ormTemplate.findByCriteria(OrmTemplate.createCriteria(Amortization.class)
				.createAlias("Inventory", "i")
				.setProjection(Projections.distinct(Projections.projectionList(Projections.property("IMonth"), Projections.property("i.Contractor"))))
				.setFlushMode(FlushMode.MANUAL)
				.add(Restrictions.eq("Batch", batch))
				.setResultTransformer(new ResultTransformer<AmortItems>() {
					/* (non-Javadoc)
					 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
					 */
					public AmortItems transformTuple(Object[] tuple, String[] aliases) {
						return new AmortItems((Short) tuple[0], (Contractor) tuple[1]);
					}					
				}));
		PersistentManager pm = ServerUtils.getPersistentManager();
		for (AmortItems amortItems : amortizations) {
			Date calcDate = getCalcDate(amortItems.iMonth);
			EconomicOper economicOper = createEconomicOper(calcDate, amortItems.contractor, folder);
			Criteria criteria = OrmTemplate.createCriteria(Amortization.class)
					.createAlias("Inventory", "i")
					.setFlushMode(FlushMode.MANUAL)
					.add(Restrictions.eq("Batch", batch));
			if (amortItems.contractor == null)
				criteria.add(Restrictions.isNull("i.Contractor"));
			else
				criteria.add(Restrictions.eq("i.Contractor", amortItems.contractor));
			List<Amortization> amortizationList = ormTemplate.findByCriteria(criteria);
			for (Amortization amortization : amortizationList) {
				BigDecimal sumTotal = amortization.getSumTotal();
				Inventory inventory = adjustInventory(amortization.getInventory(), sumTotal, calcDate);
				EconomicSpec es = createEconomicSpec(economicOper, inventory, sumTotal);
				createInvHistory(inventory, amortization, economicOper, es, sumTotal.negate(), calcDate);
			}
			pm.flush();
			AccountTurnoverUpdater.execute(economicOper, false);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.AmortizationServiceLocal#rollbackAmortization(java.lang.Integer)
	 */
	@PermitAll
	public void rollbackAmortization(Integer batch) {
		doRollbackAmortization(batch);
	}

	/**
	 * Откат создания хоз. операций по партиям амортизаций
	 * 
	 * @param batch
	 * 	    	- служебное поле у амортизации(номер партии)
	 */
	protected void doRollbackAmortization(Integer batch) {
		OrmTemplate.getInstance().bulkUpdateByNamedQuery("Account.Amortization.rollbackAmortization", "batch", batch);
	}
	
	/**
	 * Возвращает бизнес-компонент "Спецификация хоз. операции"
	 * 
	 * @return
	 */
	private EconomicSpecServiceLocal getEconomicSpecService() {
		if (economicSpecService != null)
			return economicSpecService;
		return (EconomicSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpec");
	}
	
	/**
	 * Возвращает бизнес-компонент "История инвентраной карточки"
	 * 
	 * @return
	 */
	private InvHistoryServiceLocal getInvHistoryService() {
		if (invHistoryService != null)
			return invHistoryService;
		return (InvHistoryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/InvHistory");
	}
	
	/**
	 * Возвращет бизнес-компонент "Хоз. операция"
	 *  
	 * @return
	 */
	private OperationServiceLocal getOperationService() {
		if (operationService != null)
			return operationService;
		return (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Operation");
	}
	
	/**
	 * Создание хоз. операции
	 *  
	 * @param keepDate
	 * 				- дата хоз. операции
	 * @param from
	 * 				- контрагент "От кого"
	 * @param folder
	 * 				- папка хоз. операции
	 * @return
	 * 				- созданную хоз. операцию
	 */
	protected EconomicOper createEconomicOper(Date keepDate, Contractor from, Folder folder) {
		EconomicOper economicOper = getOperationService().initialize();
		economicOper.setKeepDate(keepDate);
		economicOper.setComment(COMMENT_ECONOMIC_OPER);
		economicOper.setFolder(folder);
		economicOper.setFrom(from);
		getOperationService().create(economicOper);
		return economicOper;		
	}
	
	/**
	 * Создание проводки хоз. операции
	 * 
	 * @param eo
	 * 			- хоз. операция
	 * @param inventory
	 * 			- данные по видам учета
	 * @param summaNat
	 * 			- сумма устанавливаемая в проводке
	 * @return
	 */
	protected EconomicSpec createEconomicSpec(EconomicOper eo, Inventory inventory, BigDecimal summaNat) {
		EconomicSpec es = getEconomicSpecService().initialize();
		es.setBulkOperation(true);
		es.setEconomicOper(eo);
		es.setAccDb(inventory.getAccDb());
		es.setAnlDb1(inventory.getAnlDb1());
		es.setAnlDb2(inventory.getAnlDb2());
		es.setAnlDb3(inventory.getAnlDb3());
		es.setAnlDb4(inventory.getAnlDb4());
		es.setAnlDb5(inventory.getAnlDb5());
		es.setAccKt(inventory.getAccKt());
		es.setAnlKt1(inventory.getAnlKt1());
		es.setAnlKt2(inventory.getAnlKt2());
		es.setAnlKt3(inventory.getAnlKt3());
		es.setAnlKt4(inventory.getAnlKt4());
		es.setAnlKt5(inventory.getAnlKt5());
		es.setCatalog(inventory.getCatalog());
		es.setQuantity(BigDecimal.ONE);
		es.setSummaNat(summaNat);
		getEconomicSpecService().create(es);
		return es;
	}
	
	/**
	 * Изменение данных по видам учета
	 * 
	 * @param inventory
	 * 				- данные по видам учета
	 * @param sumTotal
	 * 				- сумма 
	 * @param amortDate
	 * 				- дата начисление амортизации
	 * @return
	 */
	private Inventory adjustInventory(Inventory inventory, BigDecimal sumTotal, Date amortDate) {
		RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
		inventory.setAmort(MathUtils.addNullable(inventory.getAmort(), sumTotal, roundContext));
		inventory.setEndCost(MathUtils.subtractNullable(inventory.getEndCost(), sumTotal, roundContext));
		inventory.setAmortDate(amortDate);
		return inventory;
	}
	
	/**
	 * Создание истории действия, произведенных над инвентарной карточкой
	 * 
	 * @param inventory
	 * 				- данные по видам учета
	 * @param amortization
	 * 				- ведомость начисления амортизации
	 * @param eo
	 * 				- хоз. операция
	 * @param es
	 * 				- проводка хоз. операции
	 * @param deprVal
	 * 				- изменение остаточной стоимости, при переоценке - изменение начисленной амортизации
	 * @param actDate
	 * 				- дата произведенного действия
	 */
	private void createInvHistory(Inventory inventory, Amortization amortization, EconomicOper eo, EconomicSpec es, BigDecimal deprVal, Date actDate) {
		InvHistory invHistory = getInvHistoryService().initialize();
		invHistory.setInventory(inventory);
		invHistory.setAccAmortization(amortization);
		invHistory.setEconomicOper(eo);
		invHistory.setEconomicSpec(es);
		invHistory.setKind(InvActionKind.AMORT);
		invHistory.setDeltaDeprVal(deprVal);
		invHistory.setActDate(actDate);
		getInvHistoryService().create(invHistory);
	}
	
	/**
	 * По месяцу начисления амортизации в абсолютном исчислении возвращает дату начисления амортизации
	 * 
	 * @param months
	 * 			- месяц начисления амортизации в абсолютном исчислении (год*12 + месяц) 
	 * @return
	 * 			- дату начисления амортизации
	 */
	private Date getCalcDate(Short months) {
		int calcYear = months / 12;
		int calcMonth = months % 12;
		if (calcMonth == 0) {
			calcYear -= 1;
			calcMonth = 12;
		}
		int calcDay = DaysPerMonth(calcMonth, calcYear); 
		return DateTimeUtils.toDate(calcMonth, calcDay, calcYear, 0, 0, 0);
	}

	/**
	 * По месяцу и году возвращет количество дней в месяце
	 * 
	 * @param month
	 * 			- номер месяца
	 * @param year
	 * 			- год
	 * @return
	 */
	private int DaysPerMonth(int month, int year) {
		int [] daysIsMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		return (month < 1 || month > 12) ? 0 : (month == 2 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? daysIsMonth[month-1] + 1 : daysIsMonth[month-1];			
	}
	
	private class AmortItems {
		
		/**
		 * месяц начисления амортизации в абсолютном исчислении (год*12 + месяц) 
		 */
		private Short iMonth;
		
		/**
		 * материально-ответственное лицо
		 */
		private Contractor contractor;

		public AmortItems(Short month, Contractor contractor) {
			iMonth = month;
			this.contractor = contractor;
		}				
	} 

}
