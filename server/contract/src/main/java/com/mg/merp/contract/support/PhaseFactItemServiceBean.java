/*
 * PhaseFactItemServiceBean.java
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

package com.mg.merp.contract.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.contract.ContractSpecServiceLocal;
import com.mg.merp.contract.PhaseFactItemServiceLocal;
import com.mg.merp.contract.PhasePlanItemServiceLocal;
import com.mg.merp.contract.model.ContractFactItemSpecData;
import com.mg.merp.contract.model.ContractSpec;
import com.mg.merp.contract.model.FactItemContractorSource;
import com.mg.merp.contract.model.FactItemData;
import com.mg.merp.contract.model.ManualDistributionData;
import com.mg.merp.contract.model.PhaseFactItem;
import com.mg.merp.contract.model.PhaseItemLink;
import com.mg.merp.contract.model.PhasePlanItem;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

/**
 * Реализация бизнес-компонента "Фактические пункты контракта" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PhaseFactItemServiceBean.java,v 1.11 2008/03/13 14:32:01 sharapov Exp $
 */
@Stateless(name="merp/contract/PhaseFactItemService") //$NON-NLS-1$
public class PhaseFactItemServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PhaseFactItem, Integer> implements PhaseFactItemServiceLocal {

	//контекст округления
	private RoundContext roundContext = null;


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(PhaseFactItem entity) {
		doAdjust(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.contract.PhaseFactItemServiceLocal#adjust(com.mg.merp.contract.model.PhaseFactItem)
	 */
	@PermitAll
	public void adjust(PhaseFactItem phaseFactItem) {
		doAdjust(phaseFactItem);
	}

	/**
	 * Рассчитать аттрибуты фактического пункта контракта
	 * @param phaseFactItem - фактический пункт контракта
	 */
	public void doAdjust(PhaseFactItem phaseFactItem) {
		Integer currencyPrec = ConfigurationHelper.getConfiguration().getCurrencyPrec();
		AgregateSumBySpecificationResult factSumResult = getFactSumBySpecification(phaseFactItem); 
		if(factSumResult.getContractSpecCount() != 0)
			phaseFactItem.setFactSum(factSumResult.getAgregateSum());

		if(phaseFactItem.getFactSum() != null)
			phaseFactItem.setFactSum(MathUtils.round(phaseFactItem.getFactSum(), new RoundContext(currencyPrec == null ? 0 : currencyPrec)));
		else
			phaseFactItem.setFactSum(BigDecimal.ZERO);
		if(phaseFactItem.getDistSum() == null)
			phaseFactItem.setDistSum(BigDecimal.ZERO);
	}

	/**
	 * Получить фактическую сумму для пункта контракта
	 * @param phaseFactItem - фактический пункт
	 * @return фактическая сумма для пункта контракта
	 */
	protected AgregateSumBySpecificationResult getFactSumBySpecification(PhaseFactItem phaseFactItem) {
		return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(ContractSpec.class)
				.setProjection(Projections.projectionList(Projections.rowCount(), Projections.sum("Summa"))) //$NON-NLS-1$
				.add(Restrictions.eq("PhaseItemFact", phaseFactItem)) //$NON-NLS-1$
				.setFlushMode(FlushMode.MANUAL)
				.setResultTransformer(new ResultTransformer<AgregateSumBySpecificationResult>() {

					/* (non-Javadoc)
					 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
					 */
					public AgregateSumBySpecificationResult transformTuple(Object[] tuple, String[] aliases) {
						return new AgregateSumBySpecificationResult((Integer) tuple[0], (BigDecimal) tuple[1]);
					}
				}));
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.contract.PhaseFactItemServiceLocal#autoDistributionFactSum(com.mg.merp.contract.model.PhaseFactItem)
	 */
	public void autoDistributionFactSum(PhaseFactItem phaseFactItem) {
		iternalAutoDistributionFactSum(phaseFactItem);
	}

	/**
	 * Распределить фактичекую сумму автоматически
	 * @param phaseFactItem - фактический пункт контракта
	 */
	private void iternalAutoDistributionFactSum(PhaseFactItem phaseFactItem) {
		final String QUERY_TEXT = "select pip from PhasePlanItem pip, PhaseFactItem pif, Phase cp where (pip.PlanSum > pip.FactSum) and (pip.ContractPhase.Id = cp.Id) and (cp.DocHead.Id = pif.DocHead.Id) and (pip.Kind = pif.Kind) and (pif.Id = :factId)"; //$NON-NLS-1$
		final String PARAM_NAME = "factId"; //$NON-NLS-1$

		List<PhaseItemLink> phaseItemLinks = new ArrayList<PhaseItemLink>(); 
		BigDecimal distSum = phaseFactItem.getFactSum().subtract(phaseFactItem.getDistSum());
		BigDecimal dsum;
		BigDecimal freeSum;

		List<PhasePlanItem> phasePlanItems = MiscUtils.convertUncheckedList(PhasePlanItem.class, OrmTemplate.getInstance().findByNamedParam(QUERY_TEXT, PARAM_NAME, phaseFactItem.getId()));

		for (PhasePlanItem item : phasePlanItems) {
			if(item.getFactSum() == null)
				freeSum = item.getPlanSum();
			else
				freeSum = item.getPlanSum().subtract(item.getFactSum());

			if(freeSum.compareTo(distSum) > 0) {
				dsum = distSum;
				distSum = new BigDecimal(0);
			}
			else {
				dsum = freeSum;
				distSum = distSum.subtract(freeSum);
			}

			phaseItemLinks.add(initializePhaseItemLink(item, phaseFactItem, dsum));

			if(distSum.compareTo(new BigDecimal(0)) == 0)
				break;
		}
		storePhaseItemLinks(phaseItemLinks);
	}

	private PhaseItemLink initializePhaseItemLink(PhasePlanItem planItem, PhaseFactItem factItem, BigDecimal distSum) {
		PhaseItemLink phaseItemLink = new PhaseItemLink();
		phaseItemLink.setPhaseItemPlan(planItem);
		phaseItemLink.setPhaseItemFact(factItem);
		phaseItemLink.setDistSum(distSum);
		return phaseItemLink;
	}

	private void storePhaseItemLinks(List<PhaseItemLink> phaseItemLinks) {
		if(phaseItemLinks != null && !phaseItemLinks.isEmpty())
			for (PhaseItemLink link : phaseItemLinks) 
				ServerUtils.getPersistentManager().persist(link);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.contract.PhaseFactItemServiceLocal#manualDistributionFactSum(com.mg.merp.contract.model.ManualDistributionData[], java.lang.Integer)
	 */
	public void manualDistributionFactSum(ManualDistributionData[] data, Integer factItemId) {
		iternalManuaDistributionFactSum(data, factItemId);
	}

	/**
	 * Распределить фактичекую сумму вручную
	 * @param data - структура данных для распределения фактичекой суммы
	 * @param factItemId - идентификатор фактического пункта контракта
	 */
	private void iternalManuaDistributionFactSum(ManualDistributionData[] data, Integer factItemId) {
		if(data != null && data.length > 0) {
			PhaseFactItem phaseFactItem = load(factItemId);
			List<PhaseItemLink> phaseItemLinks = new ArrayList<PhaseItemLink>();
			for (int i = 0; i < data.length; i++) {
				PhasePlanItemServiceLocal service = (PhasePlanItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/PhasePlanItem"); //$NON-NLS-1$
				PhasePlanItem planItem = service.load(data[i].getPlanItemId());
				phaseItemLinks.add(initializePhaseItemLink(planItem, phaseFactItem, data[i].getDistSum()));
			}
			storePhaseItemLinks(phaseItemLinks);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.contract.PhaseFactItemServiceLocal#avoidDistributionFactSum(java.lang.Integer)
	 */
	public void avoidDistributionFactSum(Integer factItemId) {
		iternalAvoidDistributionFactSum(factItemId);
	}

	/**
	 * Аннулировать распределение фактической суммы
	 * @param factItemId - идентификатор фактического пункта договора
	 */
	private void iternalAvoidDistributionFactSum(Integer factItemId) {
		final String QUERY_TEXT = "delete from PhaseItemLink pil where pil.PhaseItemFact.Id = :factItemId"; //$NON-NLS-1$
		final String PARAM_NAME = "factItemId"; //$NON-NLS-1$
		OrmTemplate.getInstance().bulkUpdate(QUERY_TEXT, new String[] {PARAM_NAME}, new Object[] {factItemId});	
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.contract.PhaseFactItemServiceLocal#createContractFactItem(com.mg.merp.contract.model.FactItemData)
	 */
	@PermitAll
	public PhaseFactItem createContractFactItem(FactItemData factItemData) {
		return internalCreateContractFactItem(factItemData);
	}

	/**
	 * Создать фактический пункт контракта
	 * @param factItemData - данные для создания фактического пункта
	 * @return фактический пункт
	 */
	protected PhaseFactItem internalCreateContractFactItem(FactItemData factItemData) {
		//берем из настроек конфигурации модуля количество знаков после запятой
		Integer currencyPrec = ConfigurationHelper.getConfiguration().getCurrencyPrec();
		//устанавливаем контекст округления
		this.roundContext = new RoundContext(currencyPrec != 0 ? currencyPrec : 4);
		DocHead docHead = factItemData.getDocHead();
		DocHead contractDocHead = factItemData.getContract(); // не может быть NULL!!!!

		// если контракт не был проставлен в заголовке документа, то проставим его
		docHead.setContractDate(factItemData.getContractDate());
		docHead.setContractType(factItemData.getсontractType());
		docHead.setContractNumber(factItemData.getсontractNumber());
		docHead.setContract(contractDocHead);
		ServerUtils.getPersistentManager().merge(docHead);

		PhaseFactItem contractFactItem = initializeContractFactItem(docHead, contractDocHead, factItemData);
		ServerUtils.getPersistentManager().persist(contractFactItem);
		if(factItemData.isCreateSpec()) { 
			ContractSpec[] contractSpecs = createCotractFactItemSpec(docHead, contractFactItem);
			storeContractSpecItems(contractSpecs);
		}
		return contractFactItem;
	}

	/**
	 * Инициализировать фактический пункт контракта
	 * @param docHead - заголовок документа, ссылающийся на контракт
	 * @param contractDocHead - заголовок контракта
	 * @param factItemData - данные для инициализации пункта
	 * @return фактический пункт контракта
	 */
	protected PhaseFactItem initializeContractFactItem(DocHead docHead, DocHead contractDocHead,FactItemData factItemData) {
		PhaseFactItem contractFactItem = initialize();
		contractFactItem.setDocument(docHead);
		contractFactItem.setDocHead(contractDocHead);
		contractFactItem.setRegDate(factItemData.getProcessDate());
		contractFactItem.setDocType(docHead.getDocType());
		contractFactItem.setDocNumber(docHead.getDocNumber());
		contractFactItem.setDocDate(docHead.getDocDate());
		//конвертируем сумму в валюту контракта
		Currency contractCurrency = contractDocHead != null ? contractDocHead.getCurrency() : null;
		Currency docHeadCurrency = docHead.getCurrency();
		contractFactItem.setFactSum(getConversionSum(contractCurrency, docHeadCurrency, contractDocHead.getCurrencyRateAuthority(), contractDocHead.getCurrencyRateType(), factItemData.getProcessDate(), factItemData.getPerformedSum(), roundContext));

		contractFactItem.setDistSum(new BigDecimal(0));
		contractFactItem.setKind(factItemData.getItemKind());
		if(factItemData.getContractorSource() == FactItemContractorSource.TO)
			contractFactItem.setContractor(docHead.getTo());
		else
			contractFactItem.setContractor(docHead.getFrom());
		return contractFactItem;
	}

	/**
	 * Возвращает конвертированную сумму
	 * @param currencyTo
	 * 				- валюта в которую конвертируем
	 * @param currencyFrom
	 * 				- валюта из которой конвертируем
	 * @param curRateAuthority
	 * 				- источник курса
	 * @param curType
	 * 				- тип курса
	 * @param date
	 * 				- дата 
	 * @param summ
	 * 				- сумма для конвертации
	 * @param roundContext
	 * 				- контекст округления
	 * @return
	 */
	private BigDecimal getConversionSum(Currency currencyTo, Currency currencyFrom, CurrencyRateAuthority curRateAuthority, CurrencyRateType curType, Date date, BigDecimal summ, RoundContext roundContext) {
		if (currencyTo != null && currencyFrom != null && currencyTo.getCode().compareTo(currencyFrom.getCode()) != 0) {
			CurrencyServiceLocal currencyService = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
			return MathUtils.round(currencyService.conversion(
					currencyTo, currencyFrom,
					curRateAuthority, curType, date,
					summ), roundContext);
		} else 
			return summ;
	}

	/**
	 * Создать спецификацию для фактического пункта контракта
	 * @param docHead - заголовок документа, ссылающийся на контракт
	 * @param contractFactItem - фактический пункт контракта
	 * @return набор позиций спецификации пункта контракта
	 */
	protected ContractSpec[] createCotractFactItemSpec(DocHead docHead, PhaseFactItem contractFactItem) {
		ContractSpec[] contractSpecs = null;
		Projection projection = Projections.projectionList(Projections.property("Catalog"), Projections.property("Quantity"), Projections.property("Price"), Projections.property("Summa")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		List<ContractFactItemSpecData> docSpecs = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DocSpec.class)
				.setProjection(projection)
				.add(Restrictions.eq("DocHead", docHead)) //$NON-NLS-1$
				.setResultTransformer(new ResultTransformer<ContractFactItemSpecData>() {

					/* (non-Javadoc)
					 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
					 */
					public ContractFactItemSpecData transformTuple(Object[] tuple, String[] aliases) {
						return new ContractFactItemSpecData((Catalog) tuple[0], (BigDecimal) tuple[1], (BigDecimal) tuple[2], (BigDecimal) tuple[3]);
					}
				}));

		if(docSpecs != null && !docSpecs.isEmpty()) {
			contractSpecs = new ContractSpec[docSpecs.size()]; 
			int counter = 0;
			for(ContractFactItemSpecData data : docSpecs) 
				contractSpecs[counter++] = initializeContractFactItemSpec(data, contractFactItem);
		}
		return contractSpecs;
	}

	/**
	 * Инициализировать позицию спецификации фактического пункта контракта
	 * @param data - данные для инициализации
	 * @param contractFactItem - фактический пункт контракта
	 * @return позиция спецификации
	 */
	protected ContractSpec initializeContractFactItemSpec(ContractFactItemSpecData data, PhaseFactItem contractFactItem) {
		ContractSpecServiceLocal contractSpecService = (ContractSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/ContractSpec"); //$NON-NLS-1$
		ContractSpec contractSpec = contractSpecService.initialize();
		contractSpec.setPhaseItemFact(contractFactItem);
		contractSpec.setPhaseItemPlan(null);
		contractSpec.setCatalog(data.getCatalog());
		contractSpec.setPriceListHead(null);
		contractSpec.setQuantity(data.getQuantity());
		//конвертируем сумму и цену в валюту контракта
		Date processDate = contractFactItem.getRegDate();
		DocHead contract = contractFactItem.getDocHead();
		Currency contractCurrency = contract != null ? contract.getCurrency() : null;
		Currency docHeadCurrency = contractFactItem.getDocument().getCurrency();
		contractSpec.setPrice(getConversionSum(contractCurrency, docHeadCurrency, contract.getCurrencyRateAuthority(), contract.getCurrencyRateType(), processDate, data.getPrice(), roundContext));
		contractSpec.setSumma(getConversionSum(contractCurrency, docHeadCurrency, contract.getCurrencyRateAuthority(), contract.getCurrencyRateType(), processDate, data.getSumma(), roundContext));
		return contractSpec;
	}

	/**
	 * Поместить позиции спецификации в перманентное хранилище
	 * @param contractSpecs - позиции спецификации фактического пункта контракта
	 */
	protected void storeContractSpecItems(ContractSpec[] contractSpecs) {
		if(contractSpecs != null && contractSpecs.length > 0)
			for (int i = 0; i < contractSpecs.length; i++) {
				ContractSpec spec = contractSpecs[i];
				if(spec != null)
					ServerUtils.getPersistentManager().persist(spec);	
			}
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.contract.PhaseFactItemServiceLocal#removeContractFactItem(com.mg.merp.document.model.DocHead)
	 */
	@PermitAll
	public void rollBackCreateContractFactItem(DocHead docHead, Integer contractFactItemId, Integer data) {
		if(data != null  && data == 1) {
			docHead.setContract(null);
			docHead.setContractDate(null);
			docHead.setContractNumber(null);
			docHead.setContractType(null);
		}
		else
			docHead.setContract(null);
		ServerUtils.getPersistentManager().merge(docHead);

		erase(contractFactItemId);
	}

}
