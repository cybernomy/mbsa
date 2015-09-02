/*
 * InventoryActHeadServiceBean.java
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

package com.mg.merp.warehouse.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.math.Constants;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.JoinType;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.warehouse.InventoryActHeadServiceLocal;
import com.mg.merp.warehouse.InventoryActSpecServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocument;
import com.mg.merp.warehouse.model.InventoryActCommission;
import com.mg.merp.warehouse.model.InventoryActHead;
import com.mg.merp.warehouse.model.InventoryActSpec;
import com.mg.merp.warehouse.model.InventoryActSpecDifferencesResult;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.model.StockBatchHistoryKind;
import com.mg.merp.warehouse.model.StockCard;

/**
 * Реализация бизнес-компонента "Акты инвентаризации" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: InventoryActHeadServiceBean.java,v 1.22 2009/01/05 15:03:42 sharapov Exp $
 */
@Stateless(name="merp/warehouse/InventoryActHeadService") //$NON-NLS-1$
public class InventoryActHeadServiceBean extends AbstractWarehouseDocument<InventoryActHead, Integer, DocumentPattern, InventoryActSpecServiceLocal> implements InventoryActHeadServiceLocal {

	private class StockBatchItems {

		private StockCard stockCard;

		private Integer catalog;

		private BigDecimal priceCur;

		private String currencyCode;

		public StockBatchItems() {
		}

		public StockBatchItems(StockCard stockCard, Integer catalog,
				BigDecimal priceCur, String currencyCode) {
			this.stockCard = stockCard;
			this.catalog = catalog;
			this.priceCur = priceCur;
			this.currencyCode = currencyCode;
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, InventoryActHead entity) {
		super.onValidate(context, entity);		
		context.addRule(new MandatoryAttribute(entity, "SrcStock") { //$NON-NLS-1$

			@Override
			public String getMessage() {
				return com.mg.merp.warehouse.support.Messages.getInstance().getMessage(com.mg.merp.warehouse.support.Messages.MANDATORY_VALIDATOR_SRCSTOCK);
			}
		});		
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InventoryActHeadServiceLocal.DOCSECTION;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.InventoryActHeadServiceLocal#excludeInvCommision(com.mg.merp.warehouse.model.InventoryActCommission)
	 */
	@PermitAll
	public void excludeInvCommision(InventoryActCommission commLink) throws ApplicationException {
		ServerUtils.getPersistentManager().remove(commLink);
	}	

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.InventoryActHeadServiceLocal#includeInvCommision(com.mg.merp.warehouse.model.InventoryActHead, com.mg.merp.reference.model.Contractor)
	 */
	@PermitAll
	public InventoryActCommission includeInvCommision(InventoryActHead invActHead, Contractor empl) throws ApplicationException {
		InventoryActCommission result = new InventoryActCommission();
		result.setInventoryAct(invActHead);
		result.setContractor(empl);
		ServerUtils.getPersistentManager().persist(result);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.InventoryActHeadServiceLocal#executeStockInventory(com.mg.merp.warehouse.model.InventoryActHead, java.util.Date, com.mg.merp.reference.model.OrgUnit, com.mg.merp.reference.model.Contractor, java.lang.String, java.lang.String, short, boolean)
	 */
	public void executeStockInventory(InventoryActHead invActHead, Date endDate, OrgUnit stock, Contractor mol, String beginCode, String endCode, short stockKind, boolean isIncludeEmpty) {
		InventoryParametrs.StockInventoryKind stockInventoryKind = stockKind == 0 ? InventoryParametrs.StockInventoryKind.ONEPRICEKIND 
				: InventoryParametrs.StockInventoryKind.AGREGATEPRICEKIND;
		doExecuteStockInventory(invActHead, new InventoryParametrs(	
				(OrgUnit) ServerUtils.getPersistentManager().find(OrgUnit.class, invActHead.getSrcStock().getId())
				, mol
				, endDate
				, beginCode
				, endCode
				, stockInventoryKind
				, InventoryParametrs.MolInventoryKind.AGREGATEMOLKIND
				, isIncludeEmpty
				, true), null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.warehouse.InventoryActHeadServiceLocal#executeStockInventory(com.mg.merp.warehouse.model.InventoryActHead, com.mg.merp.warehouse.support.InventoryParametrs)
	 */
	public void executeStockInventory(InventoryActHead invActHead, InventoryParametrs params, Criteria criteria) {
		doExecuteStockInventory(invActHead, params, criteria);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.warehouse.InventoryActHeadServiceLocal#executeStockInventory(com.mg.merp.warehouse.model.InventoryActHead, com.mg.merp.warehouse.support.InventoryParametrs)
	 */
	public void executeStockInventory(InventoryActHead invActHead, InventoryParametrs params) {
		doExecuteStockInventory(invActHead, params, null);
	}

	protected void doExecuteStockInventory(InventoryActHead invActHead, InventoryParametrs paramInventory, Criteria criteria) {
		if(paramInventory.getEndDate() == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.STOCK_INVENTORY_DATE_MISSING));

		BigDecimal qty = BigDecimal.ZERO;
		BigDecimal qty2 = BigDecimal.ZERO;
		RoundContext currencyPrecRoundContext = new RoundContext(getConfiguration().getCurrencyScale());
		List<DocSpec> docSpecList = new ArrayList<DocSpec>();
		//существующие на момент создания спецификаций
		if (paramInventory.isDeleteSpecList())
			deleteSpecs(invActHead);
		Contractor mol = paramInventory.getMol();
		List<StockBatchItems> stockBatchList = getStockBatches(paramInventory.getStock(), mol
				, paramInventory.getBeginCode(), paramInventory.getEndCode(), criteria);

		// увеличиваем значение даты инвентаризации, для того чтобы учесть 
		// все приходы за текущий день. Например при установке значения даты 23.11 значение часов, минут, секунд устанавливается 00:00:00
		// а надо учесть все приходы на число 23.11. Поэтому устанавливаем значение 24.11 00:00:00 тем самым учтем все приходы за 23.11
		Date actualDate = DateTimeUtils.incDay(paramInventory.getEndDate(), 1);
		for(StockBatchItems stockBatch : stockBatchList) {
			StockCard stockCard = stockBatch.stockCard;//(StockCard) stockBatch[0];
			BigDecimal priceCur = stockBatch.priceCur;//(BigDecimal) stockBatch[2];

			BigDecimal incomeQty = (BigDecimal) getBatchesQuantity(stockCard, actualDate, priceCur, StockBatchHistoryKind.IN)[0];
			BigDecimal expenseQty = (BigDecimal) getBatchesQuantity(stockCard, actualDate, priceCur, StockBatchHistoryKind.OUT)[0];
			qty = incomeQty.subtract(expenseQty);

			incomeQty = (BigDecimal) getBatchesQuantity(stockCard, actualDate, priceCur, StockBatchHistoryKind.IN)[1];
			expenseQty = (BigDecimal) getBatchesQuantity(stockCard, actualDate, priceCur, StockBatchHistoryKind.OUT)[1];
			qty2 = incomeQty.subtract(expenseQty);

			if((paramInventory.isIncludeEmpty()) || (MathUtils.round(qty, Constants.QUANTITY_ROUND_CONTEXT_EXT).compareTo(BigDecimal.ZERO) > 0))
				docSpecList.add(initializeDocSpec(invActHead, stockCard, qty, qty2, priceCur, currencyPrecRoundContext));
		}
		if (paramInventory.getMolKind() == InventoryParametrs.MolInventoryKind.AGREGATEMOLKIND 
				|| paramInventory.getStockKind() == InventoryParametrs.StockInventoryKind.AGREGATEPRICEKIND)
			agregateQuantities(docSpecList, currencyPrecRoundContext, paramInventory.getMolKind(), paramInventory.getStockKind());
		bulkCreateDocSpecs(prepareSpecsForBulkCreate(docSpecList), load(invActHead.getId()));
		if (paramInventory.isDeleteSpecList()) {
			List<InventoryActSpec> invactSpecs = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(InventoryActSpec.class)
					.add(Restrictions.eq("DocHead", load(invActHead.getId()))));
			modifySpecifaction(invactSpecs.toArray(new InventoryActSpec[invactSpecs.size()]));
		}
		
	}

	/**
	 * Подготовить позиции спецификации для массового добавления
	 * @param docSpecList - список позиций спецификации акта инвентаризации
	 * @return спецификация акта инвентаризации для массового добавления
	 */
	private CreateInventoryActSpecInfo[] prepareSpecsForBulkCreate(List<DocSpec> docSpecList) {
		CreateInventoryActSpecInfo[] inventoryActSpecList = new CreateInventoryActSpecInfo[docSpecList.size()];
		for(int i = 0; i < docSpecList.size(); i++) {
			DocSpec docSpec = docSpecList.get(i);
			inventoryActSpecList[i] = new CreateInventoryActSpecInfo(
					docSpec.getCatalog().getId(),
					docSpec.getPrice(),
					docSpec.getQuantity(),
					docSpec.getQuantity2(),
					docSpec.getSumma(),
					docSpec.getSumma1());
		}
		return inventoryActSpecList;
	}

	/**
	 * Объединяет спецификации по ценам или мол-ам
	 * 
	 * @param docSpecs
	 * 		список спецификаций
	 * @param currencyPrecRoundContext
	 * 		контекст округления
	 * @param molInventoryKind
	 * 		объединять по мол-ам
	 * @param priceInventoryKind
	 * 		объединять по ценам
	 */
	private void agregateQuantities(List<DocSpec> docSpecs, RoundContext currencyPrecRoundContext, InventoryParametrs.MolInventoryKind molInventoryKind
			, InventoryParametrs.StockInventoryKind priceInventoryKind) {
		int i = 0;
		int j = 0;
		int docSpecsSize = docSpecs.size();
		while(i <= docSpecsSize - 1) {
			j = i + 1;
			while(j <= docSpecsSize - 1) {
				DocSpec docSpecI = docSpecs.get(i);
				DocSpec docSpecJ = docSpecs.get(j);
				Contractor molI = docSpecI.getDstMol();
				Contractor molJ = docSpecJ.getDstMol();	
				BigDecimal priceI = docSpecI.getPrice();
				BigDecimal priceJ = docSpecJ.getPrice();
				if(docSpecJ.getCatalog().getId() == docSpecI.getCatalog().getId() && docSpecJ.getMeasure1().getId() == docSpecI.getMeasure1().getId()) {
					// одна строка по каждому МОЛ
					if (molInventoryKind == InventoryParametrs.MolInventoryKind.ONEMOLKIND 
							&& priceInventoryKind == InventoryParametrs.StockInventoryKind.AGREGATEPRICEKIND) {
						if (isEqualityMol(molI, molJ)) {
							ajustDocSpec(docSpecI, docSpecJ);
							docSpecs.remove(j);
							docSpecsSize--;
							if(j == docSpecsSize)
								break;
						} else
							j++;
						// одна строка по каждой цене прихода
					} else if (molInventoryKind == InventoryParametrs.MolInventoryKind.AGREGATEMOLKIND 
							&& priceInventoryKind == InventoryParametrs.StockInventoryKind.ONEPRICEKIND) {
						if (isEqualityPrice(priceI, priceJ)) {
							ajustDocSpec(docSpecI, docSpecJ);
							docSpecs.remove(j);						
							docSpecsSize--;
							if(j == docSpecsSize)
								break;
						} else
							j++;
					} else {
						// одна строка по всем МОЛ и всем ценам прихода
						ajustDocSpec(docSpecI, docSpecJ);
						docSpecs.remove(j);
						docSpecsSize--;
						if(j == docSpecsSize)
							break;
					}
				} else
					j++;
			}
			i++;
		}
		for(DocSpec docSpec : docSpecs) {
			if(MathUtils.round(docSpec.getQuantity(), Constants.QUANTITY_ROUND_CONTEXT).compareTo(BigDecimal.ZERO) != 0)
				docSpec.setPrice(MathUtils.divide(docSpec.getSumma(), docSpec.getQuantity(), currencyPrecRoundContext));
			else
				docSpec.setPrice(docSpec.getSumma());
			docSpec.setPrice1(docSpec.getPrice());
			docSpec.setDstMol(null);
		}
	}

	/**
	 * Определяет равенство МОЛ
	 * @param molI
	 * @param molJ
	 * @return <code>true</code> - МОЛ равны
	 */
	private boolean isEqualityMol(Contractor molI, Contractor molJ) {
		if (molI == null && molJ == null)
			return true;
		else if (molI != null && molJ != null && molI.getId() == molJ.getId())
			return true;
		return false;
	}

	/**
	 * Определяет равенство цен прихода 
	 * @param priceI
	 * @param priceJ
	 * @return <code>true</code> - цены равны
	 */
	private boolean isEqualityPrice(BigDecimal priceI, BigDecimal priceJ) {
		if (priceI.compareTo(priceJ) == 0)
			return true;
		return false;
	}

	/**
	 * Объединяем две спецификации
	 * 
	 * @param docSpecI	
	 * @param docSpecJ
	 */
	private void ajustDocSpec(DocSpec docSpecI, DocSpec docSpecJ) {
		docSpecI.setQuantity(docSpecI.getQuantity().add(docSpecJ.getQuantity()));
		docSpecI.setQuantity2(docSpecI.getQuantity2().add(docSpecJ.getQuantity2()));
		docSpecI.setSumma(docSpecI.getSumma().add(docSpecJ.getSumma()));
		docSpecI.setSumma1(docSpecI.getSumma());
	}

	private DocSpec initializeDocSpec(InventoryActHead invActHead, StockCard stockCard, BigDecimal qty, BigDecimal qty2, BigDecimal price, RoundContext currencyPrecRoundContext) {
		DocSpec docSpec = getSpecificationService().initialize();
		Catalog catalog = stockCard.getCatalog();
		docSpec.setDocHead(invActHead);
		docSpec.setCatalog(catalog);
		docSpec.setMeasure1(catalog.getMeasure1());
		docSpec.setMeasure2(catalog.getMeasure2());
		docSpec.setQuantity(qty);
		docSpec.setQuantity2(qty2);
		docSpec.setPrice(price);
		docSpec.setPrice1(price);
		docSpec.setSumma(MathUtils.multiply(price, qty, currencyPrecRoundContext));
		docSpec.setSumma1(docSpec.getSumma());
		docSpec.setDstMol(stockCard.getMol());
		return docSpec;
	}

	/**
	 * Возвращает  параметры складской партии
	 * 
	 * @param stock			- склад
	 * @param mol			- мол склада
	 * @param beginCode		- начало диапазона кодов позиций каталога
	 * @param endCode		- конец диапазона кодов позиций каталога
	 * @param criteria		- условие отбора партионной карточки
	 * @return
	 */
	protected List<StockBatchItems> getStockBatches(Contractor stock, Contractor mol, String beginCode, String endCode, Criteria criteria) {
		Projection projection = Projections.projectionList(Projections.groupProperty("StockCard"), Projections.groupProperty("cat.Id"), Projections.groupProperty("PriceCur"), Projections.groupProperty("CurrencyCode")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		if (criteria == null) {
			criteria = OrmTemplate.createCriteria(StockBatch.class)
			.createAlias("StockCard", "sc", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
			.createAlias("sc.Catalog", "cat", JoinType.INNER_JOIN)
			.add(Restrictions.eq("sc.Stock", stock)); //$NON-NLS-1$
		}
		criteria.setProjection(projection)
		.setResultTransformer(new ResultTransformer<StockBatchItems>() {

			public StockBatchItems transformTuple(Object[] tuple, String[] aliases) {
				return new StockBatchItems((StockCard) tuple[0], (Integer) tuple[1], (BigDecimal) tuple[2], (String) tuple[3]);
			}
		}); 
		if(mol != null)
			criteria.add(Restrictions.eq("sc.Mol", mol)); //$NON-NLS-1$
		if(beginCode != null)
			criteria.add(Restrictions.ge("cat.UpCode", beginCode.toUpperCase())); //$NON-NLS-1$
		if(endCode != null)
			criteria.add(Restrictions.le("cat.UpCode", endCode.toUpperCase())); //$NON-NLS-1$
		return OrmTemplate.getInstance().findByCriteria(criteria);
	}

	private Object[] getBatchesQuantity(StockCard stockCard, Date actualDate, BigDecimal priceCur, StockBatchHistoryKind kind) {
		Projection projection = Projections.projectionList(Projections.sum("Quantity"), Projections.sum("Quantity2")); //$NON-NLS-1$ //$NON-NLS-2$
		Criteria criteria = OrmTemplate.createCriteria(StockBatchHistory.class)
		.setProjection(projection)
		.createAlias("StockBatch", "sb", JoinType.LEFT_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
		.add(Restrictions.eq("sb.StockCard", stockCard)) //$NON-NLS-1$
		.add(Restrictions.eq("sb.PriceCur", priceCur)) //$NON-NLS-1$
		.add(Restrictions.eq("Kind", kind)) //$NON-NLS-1$
		.add(Restrictions.lt("ProcessDate", actualDate)); //$NON-NLS-1$
		Object[] batchQuantities = OrmTemplate.getInstance().findUniqueByCriteria(criteria);

		if(batchQuantities != null) {
			if(batchQuantities[0] == null)
				batchQuantities[0] = BigDecimal.ZERO;
			if(batchQuantities[1] == null)
				batchQuantities[1] = BigDecimal.ZERO;
		}
		return batchQuantities;
	}

	/**
	 * Выполнить массовое добавление позиций спецификации
	 * @param inventoryActSpecInfo - информация о номеклатуре для создания спецификации акта инвентаризации
	 * @param invActHead - акт инвентаризации
	 */
	private void bulkCreateDocSpecs(CreateInventoryActSpecInfo[] inventoryActSpecInfo, DocHead invActHead) {
		getSpecificationService().bulkCreate(invActHead, inventoryActSpecInfo);
	}

	/**
	 * Удалить спецификацию акта инвентаризации
	 * @param invActHead - акт инвентаризации
	 */
	private void deleteSpecs(InventoryActHead invActHead) {
		List<InventoryActSpec> docSpecs = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(InventoryActSpec.class).add(Restrictions.eq("DocHead", invActHead))); //$NON-NLS-1$
		if(docSpecs.size() > 0) {
			InventoryActSpecServiceLocal specService = getSpecificationService();
			for(InventoryActSpec docSpec : docSpecs) {
				docSpec.setBulkOperation(true);
				specService.erase(docSpec);
			}
		}
		removeSpecifaction(docSpecs.toArray(new InventoryActSpec[docSpecs.size()]));
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.warehouse.InventoryActHeadServiceLocal#computeDifferenceByQuantity(com.mg.merp.warehouse.model.InventoryActSpec)
	 */
	@PermitAll
	public InventoryActSpecDifferencesResult computeDifferenceByQuantity(InventoryActSpec inventoryActSpec) {
		return doComputeDifferenceQuantity(inventoryActSpec);
	}

	protected InventoryActSpecDifferencesResult doComputeDifferenceQuantity(InventoryActSpec inventoryActSpec) {
		BigDecimal realQuantity = BigDecimal.ZERO;
		BigDecimal realQuantity2 = BigDecimal.ZERO;

		if(inventoryActSpec.getRealQuantity() != null)
			realQuantity = inventoryActSpec.getRealQuantity();

		if(inventoryActSpec.getRealQuantity2() != null)
			realQuantity2 = inventoryActSpec.getRealQuantity2();

		inventoryActSpec.setRealSumma(inventoryActSpec.getPrice().multiply(realQuantity));

		return new InventoryActSpecDifferencesResult(
				realQuantity.subtract(inventoryActSpec.getQuantity() == null ? BigDecimal.ZERO : inventoryActSpec.getQuantity()),
				realQuantity2.subtract(inventoryActSpec.getQuantity2() == null ? BigDecimal.ZERO : inventoryActSpec.getQuantity2()),
				inventoryActSpec.getRealSumma().subtract(inventoryActSpec.getSumma() == null ? BigDecimal.ZERO : inventoryActSpec.getSumma()));
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.warehouse.InventoryActHeadServiceLocal#computeDifferenceBySum(com.mg.merp.warehouse.model.InventoryActSpec)
	 */
	@PermitAll
	public InventoryActSpecDifferencesResult computeDifferenceBySum(InventoryActSpec inventoryActSpec) {
		return doComputeDifferenceBySum(inventoryActSpec);
	}

	protected InventoryActSpecDifferencesResult doComputeDifferenceBySum(InventoryActSpec inventoryActSpec) {
		BigDecimal realSumma = BigDecimal.ZERO;
		BigDecimal realQuantity = BigDecimal.ZERO;
		BigDecimal realQuantity2 = BigDecimal.ZERO;

		if(inventoryActSpec.getRealSumma() != null)
			realSumma = inventoryActSpec.getRealSumma();

		if(inventoryActSpec.getRealQuantity2() != null)
			realQuantity2 = inventoryActSpec.getRealQuantity2();

		if(MathUtils.compareToZero(inventoryActSpec.getPrice1()) != 0)
			inventoryActSpec.setRealQuantity(realSumma.divide(inventoryActSpec.getPrice1(), getConfiguration().getCurrencyScale()));

		if(inventoryActSpec.getRealQuantity() != null)
			realQuantity = inventoryActSpec.getRealQuantity();

		return new InventoryActSpecDifferencesResult(
				realQuantity.subtract(inventoryActSpec.getQuantity() == null ? BigDecimal.ZERO : inventoryActSpec.getQuantity()),
				realQuantity2.subtract(inventoryActSpec.getQuantity2() == null ? BigDecimal.ZERO : inventoryActSpec.getQuantity2()),
				realSumma.subtract(inventoryActSpec.getSumma() == null ? BigDecimal.ZERO : inventoryActSpec.getSumma()));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#doCreateSpecifaction(com.mg.merp.document.model.DocSpec[])
	 */
	@Override
	protected void doCreateSpecifaction(DocSpec ... docSpecs) {
		InventoryActAggregatePropertiesStrategy st = new InventoryActAggregatePropertiesStrategy(this, docSpecs, false, getConfiguration().getCurrencyScale());
		st.calculate();
		doComputeShortageAndExsessSum(load(docSpecs[0].getDocHead().getId()));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#doModifySpecifaction(com.mg.merp.document.model.DocSpec)
	 */
	@Override
	protected void doModifySpecifaction(DocSpec ... docSpecs) {
		InventoryActAggregatePropertiesStrategy st = new InventoryActAggregatePropertiesStrategy(this, docSpecs, false, getConfiguration().getCurrencyScale());
		st.calculate();
		doComputeShortageAndExsessSum(load(docSpecs[0].getDocHead().getId()));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#doRemoveSpecifaction(com.mg.merp.document.model.DocSpec)
	 */
	@Override
	protected void doRemoveSpecifaction(DocSpec ... docSpecs) {
		InventoryActAggregatePropertiesStrategy st = new InventoryActAggregatePropertiesStrategy(this, docSpecs, true, getConfiguration().getCurrencyScale());
		st.calculate();
		doComputeShortageAndExsessSum(load(docSpecs[0].getDocHead().getId()));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.InventoryActHeadServiceLocal#computeShortageAndExsessSum(com.mg.merp.warehouse.model.InventoryActHead)
	 */
	@PermitAll
	public void computeShortageAndExsessSum(InventoryActHead inventoryActHead) {
		doComputeShortageAndExsessSum(inventoryActHead);
	}

	protected void doComputeShortageAndExsessSum(InventoryActHead inventoryActHead) {
		computeInventoryActHeadShortageAndExsessSum(inventoryActHead, inventoryActHead.getRealSummaCur(), inventoryActHead.getRealSummaNat());
	}

	/**
	 * Расчитать суммы излишков/недостачи акта инвентаризации
	 * @param inventoryActHead - акт инвентаризации
	 * @param realDocSumCur - фактичекая сумма в валюте
	 * @param realDocSumNat - фактичекая сумма в НДЕ
	 */
	private void computeInventoryActHeadShortageAndExsessSum(InventoryActHead inventoryActHead, BigDecimal realDocSumCur, BigDecimal realDocSumNat) {
		if(inventoryActHead.getId() == null)
			return;

		RoundContext curPrecRoundContext = new RoundContext(getConfiguration().getCurrencyScale());

		inventoryActHead.setRealSummaCur(realDocSumCur);
		inventoryActHead.setRealSummaNat(realDocSumNat);

		BigDecimal exsessSummaCur = realDocSumCur == null ? BigDecimal.ZERO : realDocSumCur.subtract(inventoryActHead.getSumCur() == null ? BigDecimal.ZERO : inventoryActHead.getSumCur());
		if(MathUtils.compareToZero(MathUtils.round(exsessSummaCur, curPrecRoundContext)) < 0)
			exsessSummaCur = BigDecimal.ZERO;
		inventoryActHead.setExsessSummaCur(exsessSummaCur);

		BigDecimal exsessSummaNat = realDocSumNat == null ? BigDecimal.ZERO : realDocSumNat.subtract(inventoryActHead.getSumNat() == null ? BigDecimal.ZERO : inventoryActHead.getSumNat());
		if(MathUtils.compareToZero(MathUtils.round(exsessSummaNat, curPrecRoundContext)) < 0)
			exsessSummaNat = BigDecimal.ZERO;
		inventoryActHead.setExsessSummaNat(exsessSummaNat);

		BigDecimal shortageSummaCur = inventoryActHead.getSumCur() == null ? BigDecimal.ZERO : inventoryActHead.getSumCur().subtract(realDocSumCur);
		if(MathUtils.compareToZero(MathUtils.round(shortageSummaCur, curPrecRoundContext)) < 0)
			shortageSummaCur = BigDecimal.ZERO;
		inventoryActHead.setShortageSummaCur(shortageSummaCur);

		BigDecimal shortageSummaNat = inventoryActHead.getSumNat() == null ? BigDecimal.ZERO : inventoryActHead.getSumNat().subtract(realDocSumNat);
		if(MathUtils.compareToZero(MathUtils.round(shortageSummaNat, curPrecRoundContext)) < 0)
			shortageSummaNat = BigDecimal.ZERO;
		inventoryActHead.setShortageSummaNat(shortageSummaNat);
	}

}
