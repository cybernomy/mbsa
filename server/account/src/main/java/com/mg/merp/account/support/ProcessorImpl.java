/*
 * ProcessorImpl.java
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.PermitAll;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.AccCalcAverageOutCostResult;
import com.mg.merp.account.AccCheckLastBatchResult;
import com.mg.merp.account.CalculateOutCostResult;
import com.mg.merp.account.EconomicSpecModelServiceLocal;
import com.mg.merp.account.EconomicSpecServiceLocal;
import com.mg.merp.account.OperationModelServiceLocal;
import com.mg.merp.account.OperationServiceLocal;
import com.mg.merp.account.PeriodServiceLocal;
import com.mg.merp.account.Processor;
import com.mg.merp.account.model.AccBatch;
import com.mg.merp.account.model.AccBatchHistory;
import com.mg.merp.account.model.AccConfig;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlForm;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicOperModel;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.model.EconomicSpecModel;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnVal;
import com.mg.merp.account.support.ui.AccBatchDisposalDialog;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.CurrencyRateServiceLocal;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;
import com.mg.merp.reference.model.CatalogPrice;
import com.mg.merp.reference.model.CatalogType;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.TaxGroup;

/**
 * Реализация бизнес-компонента "Процессор хозяйственной операции"

 * @author Konstantin S. Alikaev
 * @version $Id: ProcessorImpl.java,v 1.4 2008/10/31 11:35:15 safonov Exp $
 */
public class ProcessorImpl implements Processor {

	private DocFlowPluginInvokeParams params;

	private List<DocumentSpecItem> docSpecItemList;

	private EconomicOper economicOper;

	private EconomicSpecServiceLocal economicSpecService;

	private EconomicSpecModelServiceLocal economicSpecModelService;

	private OperationServiceLocal operationService = null; 

	private CurrencyRateServiceLocal currencyRateService = null;

	private CurrencyServiceLocal currencyService = null;

	private CreateDocumentDocFlowListener docFlowListener;

	private AccConfig config = ConfigurationHelper.getConfiguration();

	private List<EconomicSpec> economicSpecsAccBath = new ArrayList<EconomicSpec>();

	private List<EconomicSpec> 	economicSpecs = new ArrayList<EconomicSpec>();

	private PeriodServiceLocal periodService = null;

	private RoundContext roundContext = new RoundContext(config.getCurrencyPrec());

	private void prepare(DocFlowPluginInvokeParams param) {
		this.params = param;
		if (this.params.getDocument().getDocSection().isWithSpec())
			this.docSpecItemList = param.getSpecList();

	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.account.Processor#processCreateEconomicOper(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.document.CreateDocumentDocFlowListener)
	 */
	public void processCreateEconomicOper(DocFlowPluginInvokeParams params,	CreateDocumentDocFlowListener listener) {
		prepare(params);
		this.docFlowListener = listener;
		doProcessCreateEconomicOper(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.account.Processor#rollbackCreateEconomicOper(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	public void rollbackCreateEconomicOper(DocFlowPluginInvokeParams params) {
		doRollbackCreateFinOper(params);
	}

	/**
	 * Реализация отработки ЭДО "Создать ХО"
	 * 
	 * @param params
	 * 			- параметры документооборота
	 */
	private void doProcessCreateEconomicOper(DocFlowPluginInvokeParams params) {
		OperationModelServiceLocal operServModel = (OperationModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/OperationModel"); //$NON-NLS-1$
		EconomicOperModel economicOperModel = operServModel.load(params.getPerformedStage().getLinkDocModel());

		EconomicOper eo =  createByPattern(economicOperModel);
		createEconomicOper(params.getDocument(), eo, economicOperModel, params.getProcessDate(), params.getPerformedStage().getLinkDocDestFolder());
	}

	/**
	 * Завершение выполнения ЭДО
	 * @param eo
	 * 			- созданная хоз. операция
	 */
	private void endDocFlow(EconomicOper eo) {
		bulkCreateEconomicSpec(eo, economicSpecs);
		params.setData1(params.getPerformedStage().getLinkDocSection().getId());
		params.setData2(eo.getId());

		if (params.getPerformedStage().isShowNewDocument()){
			//сбрасываем перед показом контекст
//			ServerUtils.getPersistentManager().flush();
			MaintenanceHelper.edit(getServiceEconomicOperation(), eo.getId(), null, new MaintenanceFormActionListener() {

				public void canceled(MaintenanceFormEvent event) {
					docFlowListener.canceled();
				}

				public void performed(MaintenanceFormEvent event) {
					docFlowListener.completed();
				}
			});
		} else
			docFlowListener.completed();
	}

	/**
	 * Функция отката ЭДО "Создать ХО"
	 * @param params
	 * 			- параметры отката
	 */
	private void doRollbackCreateFinOper(DocFlowPluginInvokeParams params) {
		getServiceEconomicOperation().erase(params.getData2());	
	}

	/**
	 * Возвращает сервис бизнес-компонента "Хозяйственная операция"
	 * @return
	 */
	private OperationServiceLocal getServiceEconomicOperation() {
		if (operationService == null)
			return (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Operation"); //$NON-NLS-1$
		return operationService;
	}

	/**
	 * Инициализируем создаваемую хоз. операцию значениями образца
	 * 
	 * @param pattern - образец хоз. операции
	 * @return
	 */
	private EconomicOper createByPattern(EconomicOperModel pattern) {
		OperationModelServiceLocal operServModel = (OperationModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/OperationModel"); //$NON-NLS-1$
		OperationServiceLocal economicOperService = getServiceEconomicOperation();
		EconomicOper economicOperation = economicOperService.initialize();
		for(String key : pattern.getAllAttributes().keySet())
			if (isMutableEconomicOperAttribute(key)){
				Object o = pattern.getAttribute(key); 
				if (o != null && economicOperation.hasAttribute(key))
					economicOperation.setAttribute(key, o);
			}
		economicOperation.setFolder(pattern.getModelDestFolder());
		CustomFieldsManagerLocator.locate().cloneValues(operServModel, pattern, economicOperService, economicOperation);
		return economicOperation;
	}

	/**
	 * Устанавливает какие атрибуты можно копировать из образца хоз. операции в 
	 * создаваемую хоз. операцию
	 * 
	 * @param attributeName 	-	 имя атрибута
	 * @return						<code>true</code> - если можно, иначе <code>false</code>				
	 */
	public static boolean isMutableEconomicOperAttribute(String attributeName) {
		String[] attributeNames = new String[] {"Id", "SourceTo", "ModelName", "SourceFrom", "ModelDestFolder", "Folder", "EconomicSpecsModel"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		for (String element : attributeNames)
			if (element.equals(attributeName))
				return false;
		return true;
	}

	/**
	 * Создание ХО на основание документа по образцу ХО
	 * 
	 * @param srcDocHead
	 * 			- документ основание
	 * @param economicOper
	 * 			- создаваемая хоз. операция
	 * @param economicOperModel
	 * 			- образец создаваемой хоз. операции
	 * @param processDate
	 * 			- время отработки ЭДО "Создать ХО"
	 * @param folder
	 * 			- папка-приемник
	 */
	private void createEconomicOper(DocHead srcDocHead, EconomicOper economicOper, EconomicOperModel economicOperModel, Date processDate, Folder folder) {
		//установим дату ХО датой отработки
		economicOper.setKeepDate(processDate);
		economicOper.setConfirmDoc(srcDocHead);
		economicOper.setConfirmDocType(srcDocHead.getDocType());
		economicOper.setConfirmDocDate(srcDocHead.getDocDate());
		economicOper.setConfirmDocNumber(srcDocHead.getDocNumber());
		economicOper.setBaseDoc(srcDocHead.getBaseDocument());
		economicOper.setBaseDocType(srcDocHead.getBaseDocType());
		economicOper.setBaseDocDate(srcDocHead.getBaseDocDate());
		economicOper.setBaseDocNumber(srcDocHead.getBaseDocNumber());
		economicOper.setContractId(srcDocHead.getContract() != null ? srcDocHead.getContract().getId() : null);
		economicOper.setContractType(srcDocHead.getContractType());
		economicOper.setContractDate(srcDocHead.getContractDate());
		economicOper.setContractNumber(srcDocHead.getContractNumber());
		if (economicOper.getFrom() == null) 
			if (economicOperModel.getSourceFrom() != null) {
				switch (economicOperModel.getSourceFrom()) {
				case NONE: 
					economicOper.setFrom(srcDocHead.getFrom());
					break;
				case PROVIDER: 
					economicOper.setFrom(srcDocHead.getFrom());
					break;
				case CUSTOMER: 
					economicOper.setFrom(srcDocHead.getTo());
					break;
				case SHIPPER: 
					if (srcDocHead.hasAttribute("Shipper")) //$NON-NLS-1$
						economicOper.setFrom((Contractor)srcDocHead.getAttribute("Shipper")); //$NON-NLS-1$
					break;
				case CONSIGNEE: 
					if (srcDocHead.hasAttribute("Considnee")) //$NON-NLS-1$
						economicOper.setFrom((Contractor)srcDocHead.getAttribute("Considnee")); //$NON-NLS-1$
					break;
				case STOCKSRC: 
					economicOper.setFrom(srcDocHead.getSrcStock());
					break;
				case STOCKDEST: 
					economicOper.setFrom(srcDocHead.getDstStock());
					break;
				case MOLSRC: 
					economicOper.setFrom(srcDocHead.getSrcMol());
					break;
				case MOLDEST: 
					economicOper.setFrom(srcDocHead.getDstMol());
					break;
				case THROUGH: 
					economicOper.setFrom(srcDocHead.getThrough());
					break;				
				}
			} else
				economicOper.setFrom(srcDocHead.getFrom());
		if (economicOper.getTo() == null) 
			if (economicOperModel.getSourceTo() != null) {
				switch (economicOperModel.getSourceTo()) {
				case NONE: 
					economicOper.setTo(srcDocHead.getTo());
					break;
				case CUSTOMER: 
					economicOper.setTo(srcDocHead.getTo());
					break;
				case PROVIDER: 
					economicOper.setTo(srcDocHead.getFrom());
					break;
				case SHIPPER: 
					if (srcDocHead.hasAttribute("Shipper")) //$NON-NLS-1$
						economicOper.setTo((Contractor)srcDocHead.getAttribute("Shipper")); //$NON-NLS-1$
					break;
				case CONSIGNEE: 
					if (srcDocHead.hasAttribute("Considnee")) //$NON-NLS-1$
						economicOper.setTo((Contractor)srcDocHead.getAttribute("Considnee")); //$NON-NLS-1$
					break;
				case STOCKSRC: 
					economicOper.setTo(srcDocHead.getSrcStock());
					break;
				case STOCKDEST: 
					economicOper.setTo(srcDocHead.getDstStock());
					break;
				case MOLSRC: 
					economicOper.setTo(srcDocHead.getSrcMol());
					break;
				case MOLDEST: 
					economicOper.setTo(srcDocHead.getDstMol());
					break;
				case THROUGH: 
					economicOper.setTo(srcDocHead.getThrough());
					break;					
				}
			} else
				economicOper.setTo(srcDocHead.getTo());			
		getServiceEconomicOperation().create(economicOper);
		this.economicOper = economicOper;
		createEconomicOperSpecs(economicOperModel);
	}

	/**
	 * Создание спецификаций хозяйственной операции
	 * 
	 * @param economicOperModel
	 * 			- образец создаваемой хоз операции.
	 */
	private void createEconomicOperSpecs(EconomicOperModel economicOperModel) {
		List<EconomicSpecModel> economicSpecModelList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(EconomicSpecModel.class)
				.add(Restrictions.eq("EconomicOperModel", economicOperModel))); //$NON-NLS-1$
		DocHead docHead = params.getDocument();
		DocSpec docSpec = null;
		economicSpecs.clear();
		for (EconomicSpecModel economicSpecModel: economicSpecModelList) {
			BigDecimal sum = BigDecimal.ZERO;
			EconomicSpec economicSpec = null;
			boolean specMat = economicSpecModel.getAccDb().isMaterialAcc() || economicSpecModel.getAccKt().isMaterialAcc();
			boolean specRealis = economicSpecModel.getAccDb().isRealisationAcc() || economicSpecModel.getAccKt().isRealisationAcc();
			if (specMat && !docHead.getDocSection().isWithSpec())
				throw new BusinessException(Messages.getInstance().getMessage(Messages.DOCUMENT_NOT_IS_WITH_SPEC));
			//валюта проводки
			Currency currencyEconomicSpec = economicSpecModel.getAccDb().getCurrency();
			Currency currencySrcDocHead = this.economicOper.getConfirmDoc().getCurrency();
			if (currencyEconomicSpec == null)
				currencyEconomicSpec = economicSpecModel.getAccKt().getCurrency();
			if (docSpecItemList != null) {
				for (DocumentSpecItem dsi : docSpecItemList) {
					docSpec = dsi.getDocSpec();
					//условие вхождения
					if (isEntryParams(economicSpecModel, docSpec)) {
						//если корреспонденция материальная, то документ должен быть со спецификациями
						if (specMat || specRealis) {
							economicSpec = createEconomicSpec(economicSpecModel, docSpec);
							economicSpec.setQuantity(calculateQuntity(economicSpec, economicSpecModel, dsi));
							BigDecimal sumSpec = calculateSum(economicSpec, economicSpecModel, dsi);
							if (sumSpec.compareTo(BigDecimal.ZERO) > 0) {
								//конвертация валют
								adjustEconomicSpec(economicSpec, currencyEconomicSpec, currencySrcDocHead, sumSpec);
								economicSpecs.add(economicSpec);
							}
						} else {
							if (economicSpec == null)
								economicSpec = createEconomicSpec(economicSpecModel, null);						
							sum = sum.add(calculateSum(economicSpec, economicSpecModel, dsi));					
						}
					}
				}
				if (sum.compareTo(BigDecimal.ZERO) > 0) {
					//конвертация валют
					adjustEconomicSpec(economicSpec, currencyEconomicSpec, currencySrcDocHead, sum);
					economicSpecs.add(economicSpec);
				}
			} else {
				economicSpec = createEconomicSpec(economicSpecModel, null);						
				adjustEconomicSpec(economicSpec, currencyEconomicSpec, currencySrcDocHead, docHead.getSumNat());
				economicSpecs.add(economicSpec);				
			}

		}
		economicSpecsAccBath.clear();
		createEconomicSpecByAccBath(economicSpecs.iterator());
	}

	/**
	 * Списываем партионные счета через запрос партий у пользователя
	 * 
	 * @param iterator
	 * 				- итератор списка сформированных спецификаций
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void createEconomicSpecByAccBath(final Iterator<EconomicSpec> iterator) {
		if (iterator.hasNext()) {
			final EconomicSpec economicSpec = iterator.next();
			AccPlan accKt = economicSpec.getAccKt();
			//если кредитный счет партионный, то предлагаем пользователю выбрать позиции по которым нужно списывать 
			if (accKt.getAnlForm() == AnlForm.BATCHCALC) {
				chooseAccBatch(economicSpec, new BusinessAddinListener() {

					public void aborted(BusinessAddinEvent event) {
						docFlowListener.canceled();
					}

					public void completed(BusinessAddinEvent event) {
						createEconomicSpecByAccBath(iterator);
					}
				});
			} else
				createEconomicSpecByAccBath(iterator);
		} else {
			economicSpecs.addAll(economicSpecsAccBath);
			endDocFlow(this.economicOper);
		}
	}

	/**
	 * Массовое добавление спецификаций
	 * @param economicSpecs
	 */
	private void bulkCreateEconomicSpec(EconomicOper eo, List<EconomicSpec> economicSpecs) {
		for (EconomicSpec economicSpec : economicSpecs) {
			getEconomicSpecService().create(economicSpec);
		}
		ServerUtils.getPersistentManager().flush();
		AccountTurnoverUpdater.execute(eo, false);
		for (EconomicSpec economicSpec : economicSpecs) {
			ServerUtils.getPersistentManager().refresh(economicSpec);
		}
		ServerUtils.getPersistentManager().refresh(eo);

	}

	/**
	 * Запуск диалогового окна списания с партии
	 * 
	 * @param economicSpec
	 * 				- 
	 * @param listener
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void chooseAccBatch(final EconomicSpec economicSpec, final BusinessAddinListener listener) {
		final AccBatchDisposalDialog dialog = (AccBatchDisposalDialog) UIProducer.produceForm("com/mg/merp/account/resources/AccBatchDisposalDialog.mfd.xml"); //$NON-NLS-1$
		dialog.addOkActionListener(new FormActionListener() {
			public void actionPerformed(FormEvent event) {
				List<AccBatchDisposalDialog.AccBatchHistoryItem> rowList = dialog.getRowList();
				BigDecimal allProcessQuntity = BigDecimal.ZERO;
				//количество указанное в спецификации
				BigDecimal economicSpecQuntity = economicSpec.getQuantity() != null ? economicSpec.getQuantity() : BigDecimal.ZERO;
				//условие первого вхождения
				boolean flag = true;
				CalculateOutCostResult result = null;
				PersistentManager pm = ServerUtils.getPersistentManager();
				for (AccBatchDisposalDialog.AccBatchHistoryItem accBHI : rowList) {
					if (allProcessQuntity.compareTo(economicSpecQuntity) != 0) {
						BigDecimal processQuntity = accBHI.getProcessQuntity() != null ? accBHI.getProcessQuntity() : BigDecimal.ZERO;
						if (processQuntity.compareTo(economicSpecQuntity) > -1 && flag) { 
							AccBatch accBatch = pm.find(AccBatch.class, accBHI.getId());
							result = calculateOutCost(economicOper.getKeepDate(), accBatch, 
									economicSpec.getAccKt(), economicSpec.getAnlKt1(), economicSpec.getAnlKt2(), economicSpec.getAnlKt3(), economicSpec.getAnlKt4(), 
									economicSpec.getAnlKt5(), economicSpec.getCatalog(), economicOper.getFrom(), processQuntity);
							economicSpec.setSummaCur(result.getSummaCur());
							economicSpec.setSummaNat(result.getSummaNat());
							economicSpec.setAccBatchKt(accBatch);
							break;
						}
						allProcessQuntity = allProcessQuntity.add(processQuntity);
						if (allProcessQuntity.compareTo(economicSpecQuntity) > 0) {
							//если общее отмеченное количество больше чем в спецификации, то уменьшаем количество
							// отмеченное пользователем 
							processQuntity = economicSpecQuntity.subtract(allProcessQuntity).add(processQuntity);
							allProcessQuntity = economicSpecQuntity;
						}
						// установка значений количества в спецификацию и создание спецификации если не создана
						if (MathUtils.compareToZero(processQuntity) > 0) {
							//пересчитать цену списания
							AccBatch accBatch = pm.find(AccBatch.class, accBHI.getId());
							result = calculateOutCost(economicOper.getKeepDate(), accBatch, 
									economicSpec.getAccKt(), economicSpec.getAnlKt1(), economicSpec.getAnlKt2(), economicSpec.getAnlKt3(), economicSpec.getAnlKt4(), 
									economicSpec.getAnlKt5(), economicSpec.getCatalog(), economicOper.getFrom(), processQuntity);
//							BigDecimal priceCur = accBHI.getPriceCur() != null ? accBHI.getPriceCur() : BigDecimal.ZERO;
//							BigDecimal priceNat = accBHI.getPriceNat() != null ? accBHI.getPriceNat() : BigDecimal.ZERO;
							BigDecimal sumCur = result.getSummaCur();//MathUtils.round(priceCur.multiply(processQuntity), roundContext);
							BigDecimal sumNat = result.getSummaNat();//MathUtils.round(priceNat.multiply(processQuntity), roundContext);

							if (flag) {
								flag = false;
								economicSpec.setQuantity(processQuntity);
								economicSpec.setSummaCur(sumCur);
								economicSpec.setSummaNat(sumNat);
								economicSpec.setAccBatchKt(accBatch);
							} else {
								economicSpecsAccBath.add(copyEconomicSpec(economicSpec, accBatch, processQuntity, sumNat, sumCur));
							}
						}
					} else 
						break;
				}
				listener.completed(null);				
			}
		});
		dialog.addCancelActionListener(new FormActionListener() {
			public void actionPerformed(FormEvent event) {
				docFlowListener.canceled();
			}
		});
		dialog.execute(economicSpec);
	}

	/**
	 * Копируем проводку и устанавливаем в нее значение суммы и количества
	 *  
	 * @param economicSpec
	 * 					- проводка-источник
	 * @param quntity
	 * 					- устанавливаемое количество
	 * @param sumNat
	 * 					- устанавливаемая сумма в нац. валюте
	 * @param sumCur
	 * 					- устанавливаемая сумма в валюте
	 * @return
	 */
	private EconomicSpec copyEconomicSpec(EconomicSpec economicSpec, AccBatch accBatchKt, BigDecimal quntity, BigDecimal sumNat, BigDecimal sumCur) {
		EconomicSpec dstEconomicSpec = getEconomicSpecService().initialize();
		dstEconomicSpec.setBulkOperation(true);
		AttributeMap attributeMap = economicSpec.getAllAttributes();
		attributeMap.remove("Id"); //$NON-NLS-1$
		attributeMap.remove("Quantity"); //$NON-NLS-1$
		attributeMap.remove("SummaNat"); //$NON-NLS-1$
		attributeMap.remove("SummaCur"); //$NON-NLS-1$
		dstEconomicSpec.setAttributes(attributeMap);
		dstEconomicSpec.setQuantity(quntity);
		dstEconomicSpec.setSummaNat(sumNat);
		dstEconomicSpec.setSummaCur(sumCur);
		dstEconomicSpec.setAccBatchKt(accBatchKt);
		CustomFieldsManagerLocator.locate().cloneValues(getEconomicSpecService(), economicSpec, getEconomicSpecService(), dstEconomicSpec);
		return dstEconomicSpec;		
	}

	/**
	 * Устанавливает в проводке значения валют и курс
	 * @param economicSpec
	 * 					- создаваемая проводка хоз. операции
	 * @param currencyEconomicSpec
	 * 					- валюта создаваемой проводки
	 * @param currencySrcDocHead
	 * 					- валюта документа-источника
	 * @param sumSpec
	 * 					- сумма, полученная BAi в валюте документа
	 */
	private void adjustEconomicSpec(EconomicSpec economicSpec, Currency currencyEconomicSpec, Currency currencySrcDocHead, BigDecimal sumSpec) {
		Currency currencyConfNat = config.getNatCurrency();
		if (currencySrcDocHead != null && currencySrcDocHead.getId() != currencyConfNat.getId()) {
			economicSpec.setSummaNat(MathUtils.round(getServiceCurrency().conversion(currencyConfNat, currencySrcDocHead, 
					config.getCurrencyRateAuthority(), config.getCurrencyRateType(), this.economicOper.getKeepDate(), sumSpec), roundContext));
		} else
			economicSpec.setSummaNat(sumSpec);
		if (currencyEconomicSpec != null)
			if (currencyEconomicSpec.getId() == currencyConfNat.getId())
				economicSpec.setSummaCur(BigDecimal.ZERO);
			else if (currencyEconomicSpec.getId() != currencySrcDocHead.getId()) {
				economicSpec.setSummaCur(MathUtils.round(getServiceCurrency().conversion(currencyEconomicSpec, currencySrcDocHead, 
						config.getCurrencyRateAuthority(), config.getCurrencyRateType(), this.economicOper.getKeepDate(), sumSpec), roundContext));
				economicSpec.setCurCource(MathUtils.round(getServiceCurrencyRate().getCurrencyRate(currencyEconomicSpec, currencyConfNat, 
						config.getCurrencyRateAuthority(), config.getCurrencyRateType(), this.economicOper.getKeepDate()), roundContext));
			}
			else 
				economicSpec.setSummaCur(sumSpec);
		else
			economicSpec.setSummaCur(BigDecimal.ZERO);
	} 

	/**
	 * Возвращает сервис курса валют
	 * @return
	 */
	private CurrencyRateServiceLocal getServiceCurrencyRate() {
		if (currencyRateService == null)
			currencyRateService = (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate()
			.getBusinessService(CurrencyRateServiceLocal.SERVICE_NAME);
		return currencyRateService;
	} 

	/**
	 * Возвращает сервис валют
	 * @return
	 */
	private CurrencyServiceLocal getServiceCurrency() {
		if (currencyService == null)
			currencyService = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate()
			.getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
		return currencyService;
	} 

	/**
	 * Вызывает BAi для пересчета суммы проводки
	 * @param economicSpec
	 * 					- создаваемая проводка
	 * @param economicSpecModel
	 * 					- образец проводки
	 * @param dsi
	 * 					- данные ДО
	 * @return
	 * 					- сумму проводки
	 */
	private BigDecimal calculateSum(EconomicSpec economicSpec, EconomicSpecModel economicSpecModel, DocumentSpecItem dsi) {
		Map<String, Object> baiParams = createBAiParams(dsi, economicSpec);
		BusinessAddinListenerImpl listener = new BusinessAddinListenerImpl();
		if (economicSpecModel.getSumAlg() == null)
			return BigDecimal.ZERO;		
		BusinessAddinEngineLocator.locate().perform(economicSpecModel.getSumAlg(), baiParams, listener);
		return MathUtils.round(listener.getResult() != null ? listener.getResult() : BigDecimal.ZERO, roundContext);
	}

	/**
	 * Вызывает BAi для пересчета количества проводки
	 * @param economicSpec
	 * 					- создаваемая проводка
	 * @param economicSpecModel
	 * 					- образец проводки
	 * @param dsi
	 * 					- данные ДО
	 * @return
	 * 					- количество проводки
	 */
	private BigDecimal calculateQuntity(EconomicSpec economicSpec, EconomicSpecModel economicSpecModel, DocumentSpecItem dsi) {
		Map<String, Object> baiParams = createBAiParams(dsi, economicSpec);
		BusinessAddinListenerImpl listener = new BusinessAddinListenerImpl();
		if (economicSpecModel.getQtyAlg() == null)
			return BigDecimal.ZERO;
		BusinessAddinEngineLocator.locate().perform(economicSpecModel.getQtyAlg(), baiParams, listener);
		return MathUtils.round(listener.getResult() != null ? listener.getResult() : BigDecimal.ZERO, roundContext);
	}

	/**
	 * Порверка условий вхождения
	 * @param economicSpecModel
	 * 				- спецификация образца хоз. операции
	 * @param srcDocSpec
	 * 				- спецификация документа-основания
	 * @return	- <code>true</code> - если параметры спецификации документа-основания удовлетворяют параметрам 
	 * вхождения спецификации образца хоз. операции
	 */
	private boolean isEntryParams(EconomicSpecModel economicSpecModel, DocSpec srcDocSpec) {
		CatalogFolder catalogFolderEconomicSpec = economicSpecModel.getEntryFolder();
		Catalog catalogDocSpec = srcDocSpec.getCatalog();
		CatalogType catalogTypeEconomicSpec = economicSpecModel.getEntryGoodType();
		Catalog catalogEconomicSpec = economicSpecModel.getCatalog();
		TaxGroup taxGroupEconomicSpec = economicSpecModel.getTaxGroup();
		TaxGroup taxGroupDocSpec = srcDocSpec.getTaxGroup();
		if (catalogTypeEconomicSpec != null && catalogDocSpec.getGoodType() != catalogTypeEconomicSpec)
			return false;
		if (catalogFolderEconomicSpec != null && catalogDocSpec.getFolder().getId() != catalogFolderEconomicSpec.getId())
			return false;
		if (catalogEconomicSpec != null && catalogEconomicSpec.getId() != catalogDocSpec.getId())
			return false;
		if (taxGroupEconomicSpec != null && taxGroupDocSpec != null && taxGroupEconomicSpec.getId() != taxGroupDocSpec.getId())
			return false;
		return true;
	}

	/**
	 * Создает проводку по образцу и по спецификации документа основания
	 * 
	 * @param economicSpecModel
	 * 				- образец проводки хоз. операции
	 * @param docSpec
	 * 				- спецификация документа-основания
	 * @return
	 * 				- созданную проводку
	 */
	private EconomicSpec createEconomicSpec(EconomicSpecModel economicSpecModel, DocSpec docSpec) {
		EconomicSpec dstEconomicSpec = getEconomicSpecService().initialize();
		dstEconomicSpec.setBulkOperation(true);
		for(String key : economicSpecModel.getAllAttributes().keySet())
			if (isMutableEconomicSpecAttribute(key)){
				Object o = economicSpecModel.getAttribute(key); 
				if (o != null && dstEconomicSpec.hasAttribute(key))
					dstEconomicSpec.setAttribute(key, o);
			}
		dstEconomicSpec.setEconomicOper(this.economicOper);
		if (docSpec != null) 
			dstEconomicSpec.setCatalog(docSpec.getCatalog());
		CustomFieldsManagerLocator.locate().cloneValues(getEconomicSpecModelService(), economicSpecModel, getEconomicSpecService(), dstEconomicSpec);
		return dstEconomicSpec;

	}

	/**
	 * Устанавливает какие атрибуты можно копировать из спецификации образца хоз. операции в 
	 * спецификацию создаваемой хоз. операции
	 * 
	 * @param attributeName 	-	 имя атрибута
	 * @return						<code>true</code> - если можно, иначе <code>false</code>				
	 */
	public static boolean isMutableEconomicSpecAttribute(String attributeName) {
		String[] attributeNames = new String[] {"Id", "EconomicOperModel", "TaxGroup", "SumAlg", "QtyAlg", "EntryFolder", "EntryGoodType", "SummaFormula",  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
				"QuanFormula", "FormulaContext", "Quantity", "SummaNat", "SummaCur", "Catalog"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		for (String element : attributeNames)
			if (element.equals(attributeName))
				return false;
		return true;
	}

	private Map<String, Object> createBAiParams(DocumentSpecItem docSpecItem, EconomicSpec economicSpec) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AccountBusinessAddin.DOCFLOW_PARAM_NAME, params);
		result.put(AccountBusinessAddin.DOCSPEC_PARAM_NAME, docSpecItem);
		result.put(AccountBusinessAddin.ECONOMIC_OPER_PARAM_NAME, this.economicOper);
		result.put(AccountBusinessAddin.ECONOMIC_SPEC_PARAM_NAME, economicSpec);
		return result;
	}

	private class BusinessAddinListenerImpl implements BusinessAddinListener<BigDecimal> {
		private BigDecimal result;

		public void aborted(BusinessAddinEvent<BigDecimal> event) {
			docFlowListener.canceled();
		}

		public void completed(BusinessAddinEvent<BigDecimal> event) {
			result = event.getResult();
		}

		public BigDecimal getResult() {
			return result;
		}

	}

	/**
	 * Бизнес-компонент проводка хоз. операции
	 * @return
	 */
	public EconomicSpecServiceLocal getEconomicSpecService() {
		if (economicSpecService == null)
			return (EconomicSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpec"); //$NON-NLS-1$
		return this.economicSpecService;
	}

	/**
	 * Бизнес-компонент образца проводки хоз. операции
	 * @return
	 */
	public EconomicSpecModelServiceLocal getEconomicSpecModelService() {
		if (economicSpecModelService == null)
			return (EconomicSpecModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpecModel"); //$NON-NLS-1$
		return this.economicSpecModelService;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.Processor#calculateOutCost(java.util.Date, com.mg.merp.account.model.AccBatch, com.mg.merp.account.model.AccPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.reference.model.Catalog, com.mg.merp.reference.model.Contractor, java.math.BigDecimal)
	 */
	public CalculateOutCostResult calculateOutCost(Date operDate, AccBatch accBatch, AccPlan acc,	AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4,	AnlPlan anl5, 
			Catalog catalog, Contractor contractor, BigDecimal quantity) {
		return doCalculateOutCost(operDate, accBatch, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor, quantity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.account.Processor#calculateQuantityEnd(java.util.Date, com.mg.merp.account.model.AccBatch, com.mg.merp.account.model.AccPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.reference.model.Catalog, com.mg.merp.reference.model.Contractor)
	 */
	@PermitAll
	public BigDecimal calculateQuantityEnd(Date operDate, AccBatch accBatch,
			AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3,
			AnlPlan anl4, AnlPlan anl5, Catalog catalog, Contractor contractor) {		
		return doCalculateQuantityEnd(operDate, accBatch, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor);
	}

	/**
	 * Процедура рассчитывает количество ТМЦ на заданную дату по оборотке по ТМЦ
	 * 
	 * @param operDate
	 * 				- дата для расчета цены списания
	 * @param acc
	 * 				- счет кредит
	 * @param anl1
	 * 				- аналитика 1-го уровня счета кредита
	 * @param anl2
	 * 				- аналитика 2-го уровня счета кредита
	 * @param anl3
	 * 				- аналитика 3-го уровня счета кредита
	 * @param anl4
	 * 				- аналитика 4-го уровня счета кредита
	 * @param anl5
	 * 				- аналитика 5-го уровня счета кредита
	 * @param catalog
	 * 				- товар
	 * @param contractor
	 * 				- контрагент
	 * @return
	 * 				- количество ТМЦ на заданную дату по оборотке по ТМЦ
	 */
	protected BigDecimal doCalculateQuantityEnd(Date operDate, AccBatch accBatch,
			AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3,
			AnlPlan anl4, AnlPlan anl5, Catalog catalog, Contractor contractor) {
		RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());		BigDecimal result = null;
		Period period = getPeriodService().findByDate(operDate);
		Criteria criteriaRemnVal = OrmTemplate.createCriteria(RemnVal.class)
				.add(Restrictions.eq("AccPlan", acc)) //$NON-NLS-1$
				.add(Restrictions.eq("Period", period)) //$NON-NLS-1$
				.add(Restrictions.isNull("BatchId")) //$NON-NLS-1$
				.add(Restrictions.eq("Catalog", catalog)) //$NON-NLS-1$
				.add(Restrictions.or(Restrictions.eq("Contractor", contractor), Restrictions.isNull("Contractor"))) //$NON-NLS-1$ //$NON-NLS-2$
				.setFlushMode(FlushMode.MANUAL)
				.setMaxResults(1)
				.setProjection(Projections.property("Id")); //$NON-NLS-1$
		if (anl1 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan1")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan1", anl1)); //$NON-NLS-1$
		if (anl2 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan2")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan2", anl2)); //$NON-NLS-1$
		if (anl3 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan3")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan3", anl3)); //$NON-NLS-1$
		if (anl4 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan4")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan4", anl4)); //$NON-NLS-1$
		if (anl5 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan5")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan5", anl5)); //$NON-NLS-1$
		List<Integer> remnValIds = OrmTemplate.getInstance().findByCriteria(criteriaRemnVal);
		if (remnValIds.isEmpty())
			return BigDecimal.ZERO;

		RemnVal remnVal = ServerUtils.getPersistentManager().find(RemnVal.class, remnValIds.get(0));
		result = remnVal.getBeginQuan();
		// calculate debet turn
		TurnResult turnDebit = getTurnResult(remnVal, operDate, false, true);
		result = MathUtils.addNullable(result, turnDebit.quan, roundContext);
		// calculate kredit turn
		TurnResult turnKredit = getTurnResult(remnVal, operDate, true, true);
		return MathUtils.subtractNullable(result, turnKredit.quan, roundContext);
	}

	/**
	 * Расчет цены списания по бух. учету
	 * 
	 * @param operDate
	 * @param accBatch
	 * @param acc
	 * @param anl1
	 * @param anl2
	 * @param anl3
	 * @param anl4
	 * @param anl5
	 * @param catalog
	 * @param contractor
	 * @param quantity
	 */
	protected CalculateOutCostResult doCalculateOutCost(Date operDate, AccBatch accBatch, AccPlan acc,	AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4,	AnlPlan anl5, 
			Catalog catalog, Contractor contractor, BigDecimal quantity) {

		Currency accCurrency = acc.getCurrency();
		BigDecimal costNat = BigDecimal.ZERO;
		BigDecimal costCur = BigDecimal.ZERO;
		BigDecimal summaNat = BigDecimal.ZERO;
		BigDecimal summaCur = BigDecimal.ZERO;
		switch (acc.getAnlForm()) {
		case CALCCOST: 
			Criteria criteria = OrmTemplate.createCriteria(CatalogPrice.class)
					.add(Restrictions.eq("Catalog", catalog)) //$NON-NLS-1$
					.add(Restrictions.le("InAction", operDate)) //$NON-NLS-1$
					.setFlushMode(FlushMode.MANUAL)
					.setProjection(Projections.max("InAction")); //$NON-NLS-1$
			if (accCurrency != null) 
				criteria.add(Restrictions.eq("Currency", accCurrency)); //$NON-NLS-1$
			Date maxDate = OrmTemplate.getInstance().findUniqueByCriteria(criteria);
			List<Object[]> prices = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CatalogPrice.class)
					.add(Restrictions.eq("Catalog", catalog)) //$NON-NLS-1$
					.add(Restrictions.eq("InAction", maxDate)) //$NON-NLS-1$
					.setFlushMode(FlushMode.MANUAL)
					.setProjection(Projections.projectionList(Projections.property("Price"), Projections.property("EquivalentPrice"))) //$NON-NLS-1$ //$NON-NLS-2$
					.setResultTransformer(new ResultTransformer<Object[]>() {

						/* (non-Javadoc)
						 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
						 */
						public Object[] transformTuple(Object[] tuple, String[] aliases) {
							return tuple;
						}

					}));					
			costNat = (BigDecimal) prices.get(0)[0];
			costCur = accCurrency != null ? (BigDecimal) prices.get(0)[1] : costNat;
			if (costNat == null)
				costNat = BigDecimal.ZERO;
			else
				summaNat = MathUtils.multiply(costNat, quantity, roundContext);
			if (costCur == null)
				costCur = BigDecimal.ZERO;
			else
				summaCur = MathUtils.multiply(costCur, quantity, roundContext);
			return new CalculateOutCostResult(costNat, costCur, summaNat, summaCur, quantity, null, null);
		case BATCHCALC:
			if (accBatch != null) {
				AccBatchHistory abh = getAccBatchHistory(accBatch, operDate);
				if(abh != null) {
					costNat = abh.getCostNat();
					costCur = abh.getCostCur();
					summaNat = MathUtils.multiply(costNat, quantity, roundContext);
					summaCur = MathUtils.multiply(costCur, quantity, roundContext);
					AccCheckLastBatchResult result = accCheckLastBatch(operDate, accBatch, quantity, summaNat, summaCur);
					return new CalculateOutCostResult(costNat, costCur, result.getRealSummaNat(), result.getRealSummaCur(), quantity, accBatch, abh);
				}					
			} else
				return calculateOutCostLifoFifo(operDate, accBatch, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor, quantity, false);
		case AVERAGECOST:
			AccCalcAverageOutCostResult result = accCalcAverageOutCost(operDate, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor, quantity);
			return new CalculateOutCostResult(result.getCostNat(), result.getCostCur(), result.getSummaNat(), result.getSummaCur(), result.getRealQuan(), null, null);
		case FIFO:
			return calculateOutCostLifoFifo(operDate, accBatch, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor, quantity, false);			
		case LIFO: 
			return calculateOutCostLifoFifo(operDate, accBatch, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor, quantity, true);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.Processor#accCheckLastBatch(java.util.Date, com.mg.merp.account.model.AccBatch, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)
	 */
	public AccCheckLastBatchResult accCheckLastBatch(Date operDate,	AccBatch accBatch, BigDecimal quantity, BigDecimal summaNat, BigDecimal summaCur) {
		return doAccCheckLastBatch(operDate, accBatch, quantity, summaNat, summaCur);
	}

	/**
	 * Рассчет сумм списания
	 * 
	 * @param operDate
	 * @param accBatch
	 * @param quantity
	 * @param summaNat
	 * @param summaCur
	 * @return
	 */
	protected AccCheckLastBatchResult doAccCheckLastBatch(Date operDate, AccBatch accBatch, BigDecimal quantity, BigDecimal summaNat, BigDecimal summaCur) {
		BigDecimal realSummaNat = BigDecimal.ZERO;
		BigDecimal realSummaCur = BigDecimal.ZERO;
		BigDecimal beginQuantity = BigDecimal.ZERO;
		List<Integer> remnValIds = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(RemnVal.class)
				.add(Restrictions.eq("BatchId", accBatch.getId())) //$NON-NLS-1$
				.add(Restrictions.eq("Period", getPeriodService().findByDate(operDate))) //$NON-NLS-1$
				.setProjection(Projections.property("Id")) //$NON-NLS-1$
				.setFlushMode(FlushMode.MANUAL)
				.setMaxResults(1));
		if (remnValIds.isEmpty())
			return new AccCheckLastBatchResult(realSummaNat, realSummaCur);
		RemnVal remnVal = ServerUtils.getPersistentManager().find(RemnVal.class, remnValIds.get(0));
		realSummaCur = remnVal.getRemnBeginCur();
		realSummaNat = remnVal.getRemnBeginNat();
		beginQuantity = remnVal.getBeginQuan();
		// calculate debet turn
		TurnResult turnDebit = getTurnResult(remnVal, operDate, false, true);
		realSummaCur = MathUtils.addNullable(realSummaCur, turnDebit.turnCur, roundContext);
		realSummaNat = MathUtils.addNullable(realSummaNat, turnDebit.turnNat, roundContext);
		beginQuantity = MathUtils.addNullable(beginQuantity, turnDebit.quan, roundContext);
		// calculate kredit turn
		TurnResult turnKredit = getTurnResult(remnVal, operDate, true, true);
		realSummaCur = MathUtils.subtractNullable(realSummaCur, turnKredit.turnCur, roundContext);
		realSummaNat = MathUtils.subtractNullable(realSummaNat, turnKredit.turnNat, roundContext);
		beginQuantity = MathUtils.subtractNullable(beginQuantity, turnKredit.quan, roundContext);

		if (MathUtils.subtractNullable(beginQuantity, quantity, roundContext).compareTo(BigDecimal.ZERO) == 0)
			return new AccCheckLastBatchResult(realSummaNat, realSummaCur);
		return new AccCheckLastBatchResult(summaNat, summaCur);
	}

	/**
	 * Возвращает сумму и количество оборотки по ТМЦ
	 * @param remnVal
	 * 			- оборотка по ТМЦ
	 * @param operDate
	 * 			- дата
	 * @param isKredit
	 * 			- <code>true</code> возвращает кредитовые суммы и количества, иначе дебетовые
	 * @param isBoundIncluded
	 * 			- признак включения оборотов из даты <code>operDate</code>
	 * @return
	 */
	private TurnResult getTurnResult(RemnVal remnVal, Date operDate, boolean isKredit, boolean isBoundIncluded) {
		Criteria criteria = OrmTemplate.createCriteria(EconomicSpec.class)
				.createAlias("EconomicOper", "eo") //$NON-NLS-1$ //$NON-NLS-2$
				.add(isBoundIncluded ? Restrictions.le("eo.KeepDate", operDate) : Restrictions.lt("eo.KeepDate", operDate)) //$NON-NLS-1$
				.setProjection(Projections.projectionList(Projections.sum("SummaNat"), Projections.sum("SummaCur"), Projections.sum("Quantity"))) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setFlushMode(FlushMode.MANUAL)
				.setResultTransformer(new ResultTransformer<TurnResult>() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
			 */
			public TurnResult transformTuple(Object[] tuple, String[] aliases) {
				return new TurnResult((BigDecimal) tuple[0], (BigDecimal) tuple[1], (BigDecimal) tuple[2]);
			}					
		});
		if (isKredit)
			criteria.add(Restrictions.eq("RemnValKt", remnVal)); //$NON-NLS-1$
		else
			criteria.add(Restrictions.eq("RemnValDb", remnVal)); //$NON-NLS-1$
		return OrmTemplate.getInstance().findUniqueByCriteria(criteria);

	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.Processor#accCalcAverageOutCost(java.util.Date, com.mg.merp.account.model.AccPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.account.model.AnlPlan, com.mg.merp.reference.model.Catalog, com.mg.merp.reference.model.Contractor, java.math.BigDecimal)
	 */
	public AccCalcAverageOutCostResult accCalcAverageOutCost(Date operDate,	AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5, 
			Catalog catalog, Contractor contractor,	BigDecimal quantity) {
		return doAccCalcAverageOutCost(operDate, acc, anl1, anl2, anl3, anl4, anl5, catalog, contractor, quantity);
	}

	/**
	 * Рассчитывает цену списания для средних цен
	 * 
	 * @param operDate
	 * @param acc
	 * @param anl1
	 * @param anl2
	 * @param anl3
	 * @param anl4
	 * @param anl5
	 * @param catalog
	 * @param contractor
	 * @param quantity
	 * @return
	 */
	protected AccCalcAverageOutCostResult doAccCalcAverageOutCost(Date operDate,	AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5, 
			Catalog catalog, Contractor contractor,	BigDecimal quantity) {

		BigDecimal realSummaNat = BigDecimal.ZERO;
		BigDecimal realSummaCur = BigDecimal.ZERO;
		BigDecimal beginQuantity = BigDecimal.ZERO;
		Period period = getPeriodService().findByDate(operDate);
		Criteria criteriaRemnVal = OrmTemplate.createCriteria(RemnVal.class)
				.add(Restrictions.eq("AccPlan", acc)) //$NON-NLS-1$
				.add(Restrictions.eq("Period", period)) //$NON-NLS-1$
				.add(Restrictions.isNull("BatchId")) //$NON-NLS-1$
				.add(Restrictions.eq("Catalog", catalog)) //$NON-NLS-1$
				.add(Restrictions.or(Restrictions.eq("Contractor", contractor), Restrictions.isNull("Contractor"))) //$NON-NLS-1$ //$NON-NLS-2$
				.setFlushMode(FlushMode.MANUAL)
				.setMaxResults(1)
				.setProjection(Projections.property("Id")); //$NON-NLS-1$
		if (anl1 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan1")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan1", anl1)); //$NON-NLS-1$
		if (anl2 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan2")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan2", anl2)); //$NON-NLS-1$
		if (anl3 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan3")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan3", anl3)); //$NON-NLS-1$
		if (anl4 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan4")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan4", anl4)); //$NON-NLS-1$
		if (anl5 == null)
			criteriaRemnVal.add(Restrictions.isNull("AnlPlan5")); //$NON-NLS-1$
		else
			criteriaRemnVal.add(Restrictions.eq("AnlPlan5", anl5)); //$NON-NLS-1$
		List<Integer> remnValIds = OrmTemplate.getInstance().findByCriteria(criteriaRemnVal);
		if (remnValIds.isEmpty())
			return new AccCalcAverageOutCostResult(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, quantity);

		RemnVal remnVal = ServerUtils.getPersistentManager().find(RemnVal.class, remnValIds.get(0));
		realSummaCur = remnVal.getRemnBeginCur();
		realSummaNat = remnVal.getRemnBeginNat();
		beginQuantity = remnVal.getBeginQuan();

		// calculate debet turn
		TurnResult turnDebit = getTurnResult(remnVal, operDate, false, true);
		realSummaCur = MathUtils.addNullable(realSummaCur, turnDebit.turnCur, roundContext);
		realSummaNat = MathUtils.addNullable(realSummaNat, turnDebit.turnNat, roundContext);
		beginQuantity = MathUtils.addNullable(beginQuantity, turnDebit.quan, roundContext);

		// calculate kredit turn
		TurnResult turnKredit = getTurnResult(remnVal, operDate, true, false);
		realSummaCur = MathUtils.subtractNullable(realSummaCur, turnKredit.turnCur, roundContext);
		realSummaNat = MathUtils.subtractNullable(realSummaNat, turnKredit.turnNat, roundContext);
		beginQuantity = MathUtils.subtractNullable(beginQuantity, turnKredit.quan, roundContext);

		if (beginQuantity == null || MathUtils.compareToZero(beginQuantity) == 0)
			return new AccCalcAverageOutCostResult(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, quantity);
		BigDecimal costNat = MathUtils.divide(realSummaNat, beginQuantity, roundContext);
		BigDecimal costCur = MathUtils.divide(realSummaCur, beginQuantity, roundContext);	
		return new AccCalcAverageOutCostResult(costNat, costCur, quantity.multiply(costNat), quantity.multiply(costCur), quantity);
	}

	/**
	 * Расчет цены списания для счетов 'Материалы, товары FIFO', 'Материалы, товары LIFO'
	 * @param operDate
	 * @param accBatch
	 * @param acc
	 * @param anl1
	 * @param anl2
	 * @param anl3
	 * @param anl4
	 * @param anl5
	 * @param catalog
	 * @param contractor
	 * @param quantity
	 * @param isLifo
	 * @return
	 */
	private CalculateOutCostResult calculateOutCostLifoFifo(Date operDate, AccBatch accBatch, AccPlan acc,	AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4,	AnlPlan anl5, 
			Catalog catalog, Contractor contractor, BigDecimal quantity, boolean isLifo) {
		Criteria criteria = OrmTemplate.createCriteria(AccBatch.class)
				.add(Restrictions.le("InComeDate", operDate)) //$NON-NLS-1$
				.add(Restrictions.gt("EndQuan", BigDecimal.ZERO)) //$NON-NLS-1$
				.add(Restrictions.eq("AccPlan", acc)) //$NON-NLS-1$
				.add(Restrictions.eq("CatalogId", catalog.getId())) //$NON-NLS-1$
				.add(Restrictions.or(Restrictions.eq("Contractor", contractor), Restrictions.isNull("Contractor"))) //$NON-NLS-1$ //$NON-NLS-2$
				.setFlushMode(FlushMode.MANUAL)
				.setMaxResults(1);
		if (isLifo)
			criteria.addOrder(Order.desc("InComeDate")); //$NON-NLS-1$
		else
			criteria.addOrder(Order.asc("InComeDate")); //$NON-NLS-1$
		if (anl1 == null)
			criteria.add(Restrictions.isNull("AnlPlan1Id")); //$NON-NLS-1$
		else
			criteria.add(Restrictions.eq("AnlPlan1Id", anl1.getId())); //$NON-NLS-1$
		if (anl2 == null)
			criteria.add(Restrictions.isNull("AnlPlan2Id")); //$NON-NLS-1$
		else
			criteria.add(Restrictions.eq("AnlPlan2Id", anl2.getId())); //$NON-NLS-1$
		if (anl3 == null)
			criteria.add(Restrictions.isNull("AnlPlan3Id")); //$NON-NLS-1$
		else
			criteria.add(Restrictions.eq("AnlPlan3Id", anl3.getId())); //$NON-NLS-1$
		if (anl4 == null)
			criteria.add(Restrictions.isNull("AnlPlan4Id")); //$NON-NLS-1$
		else
			criteria.add(Restrictions.eq("AnlPlan4Id", anl4.getId())); //$NON-NLS-1$
		if (anl5 == null)
			criteria.add(Restrictions.isNull("AnlPlan5Id")); //$NON-NLS-1$
		else
			criteria.add(Restrictions.eq("AnlPlan5Id", anl5.getId())); //$NON-NLS-1$
		List<AccBatch> accBatchList = OrmTemplate.getInstance().findByCriteria(criteria);
		if (accBatchList != null) {
			AccBatch accBatchItem = accBatchList.get(0);
			BigDecimal realQuantity = accBatchItem.getEndQuan();
			AccBatchHistory accBatchHistory = getAccBatchHistory(accBatchItem, operDate);
			if (accBatchHistory != null) {
				BigDecimal costNat = accBatchHistory.getCostNat();
				BigDecimal costCur = accBatchHistory.getCostCur();
				if (realQuantity.compareTo(quantity) > 0)
					realQuantity = quantity;
				BigDecimal summaNat = MathUtils.multiply(costNat, realQuantity, roundContext);
				BigDecimal summaCur = MathUtils.multiply(costCur, realQuantity, roundContext);
				AccCheckLastBatchResult result = accCheckLastBatch(operDate, accBatchItem, realQuantity, summaNat, summaCur);
				return new CalculateOutCostResult(costNat, costCur, result.getRealSummaNat(), result.getRealSummaCur(), realQuantity, accBatchItem, accBatchHistory);
			}
		}
		return null;
	}

	/**
	 * Возращает историю партии
	 * @param accBatch
	 * @param operDate
	 * @return
	 */
	private AccBatchHistory getAccBatchHistory(AccBatch accBatch, Date operDate) {
		List<AccBatchHistory> aBHList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(AccBatchHistory.class)
				.add(Restrictions.eq("AccBatch", accBatch)) //$NON-NLS-1$
				.add(Restrictions.or(Restrictions.and(Restrictions.le("BeginDate", operDate), Restrictions.gt("EndDate", operDate)), Restrictions.and(Restrictions.le("BeginDate", operDate), Restrictions.isNull("EndDate"))))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		return !aBHList.isEmpty() ? aBHList.get(0) : null;
	}

	/**
	 * Класс результат для хранения обороток 
	 */
	private class TurnResult {

		private BigDecimal turnNat;

		private BigDecimal turnCur;

		private BigDecimal quan;

		public TurnResult(BigDecimal turnNat, BigDecimal turnCur, BigDecimal quan) {
			this.turnNat = turnNat;
			this.turnCur = turnCur;
			this.quan = quan;
		}		
	}

	/**
	 * Возвращает сервис бизнес-компонента "Периоды бух. учета" 
	 * @return
	 */
	private PeriodServiceLocal getPeriodService() {
		if (periodService == null)
			return (PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Period"); //$NON-NLS-1$
		return this.periodService;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.Processor#evaluateOutCost(com.mg.merp.account.model.RemnVal)
	 */
	public void evaluateOutCost(RemnVal remnVal) {
		doEvaluateOutCost(remnVal.getId());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.Processor#evaluateOutCost(java.lang.Integer)
	 */
	public void evaluateOutCost(Integer remnValId) {
		doEvaluateOutCost(remnValId);		
	}

	/**
	 * Пересчет цен списания
	 * 
	 * @param remnValId
	 * 			- идентификатор оборотной ведомости по ТМЦ
	 */
	protected void doEvaluateOutCost(Integer remnValId) {
		RemnVal remnVal = ServerUtils.getPersistentManager().find(RemnVal.class, remnValId);
		AnlForm anlForm = remnVal.getAccPlan().getAnlForm();
		if (anlForm != AnlForm.BATCHCALC && anlForm != AnlForm.FIFO && anlForm != AnlForm.LIFO) {
			List<EconomicSpec> economicSpecList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(EconomicSpec.class)
					.createAlias("EconomicOper", "eo") //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("RemnValKt", remnVal)) //$NON-NLS-1$
					.setFlushMode(FlushMode.MANUAL)
					.addOrder(Order.desc("eo.KeepDate"))); //$NON-NLS-1$
			Set<Integer> economicOperIds = new HashSet<Integer>();
			PersistentManager pm = ServerUtils.getPersistentManager();
			for (EconomicSpec es : economicSpecList) {
				EconomicOper eo = es.getEconomicOper();
				economicOperIds.add(eo.getId());
				CalculateOutCostResult result = calculateOutCost(eo.getKeepDate(), es.getAccBatchKt(), es.getAccKt(), es.getAnlKt1(), es.getAnlKt2(), es.getAnlKt3(), es.getAnlKt4(), es.getAnlKt5(), es.getCatalog(), eo.getFrom(), es.getQuantity());
				es.setBulkOperation(true);
				es.setSummaNat(result.getSummaNat());
				es.setSummaCur(result.getSummaCur());
				if (result.getAccBatch() != null)
					es.setAccBatchKt(result.getAccBatch());
				es.setAccBatchHistoryKt(result.getAccBatchHistory());
			}
			pm.flush();
			if (!economicSpecList.isEmpty())
				for (Integer eoId : economicOperIds)
					AccountTurnoverUpdater.execute(eoId, false);
		}
	}

}
