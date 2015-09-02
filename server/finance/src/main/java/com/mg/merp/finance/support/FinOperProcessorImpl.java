/*
 * FinOperProcessorImpl.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.merp.finance.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.finance.FinOperProcessor;
import com.mg.merp.finance.OperationModelServiceLocal;
import com.mg.merp.finance.OperationServiceLocal;
import com.mg.merp.finance.SpecificationModelServiceLocal;
import com.mg.merp.finance.SpecificationServiceLocal;
import com.mg.merp.finance.model.FinConfig;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.OperationModel;
import com.mg.merp.finance.model.Specification;
import com.mg.merp.finance.model.SpecificationModel;
import com.mg.merp.reference.CurrencyRateNotFoundException;
import com.mg.merp.reference.CurrencyRateServiceLocal;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;

/**
 * ���������� ������-���������� "��������� ���������� ��������"
 * 
 * @author Konstantin S. Alikaev
 * @author Oleg V. Safonov
 * @version $Id: FinOperProcessorImpl.java,v 1.4 2009/01/05 07:57:45 safonov Exp $
 */
public class FinOperProcessorImpl implements FinOperProcessor{

	private DocFlowPluginInvokeParams params;

	private List<DocumentSpecItem> docSpecItemList;

	private FinOperation finOper;

	private List<Specification> finSpecList = new ArrayList<Specification>();

	private SpecificationServiceLocal specificationService = null;

	private CurrencyRateServiceLocal currencyRateService = null;

	private OperationServiceLocal operationService = null;

	private SpecificationModelServiceLocal specificationModelService = null;

	private CreateDocumentDocFlowListener docFlowListener;

	private void prepare(DocFlowPluginInvokeParams param) {
		this.params = param;
		if (this.params.getDocument().getDocSection().isWithSpec())
			this.docSpecItemList = param.getSpecList();

	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.finance.FinOperProcessor#processCreateFinOper(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.document.CreateDocumentDocFlowListener)
	 */
	public void processCreateFinOper(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener) {
		prepare(params);
		this.docFlowListener = listener;
		doProcessCreateFinOper(params, listener);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.finance.FinOperProcessor#rollbackCreateFinOper(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	public void rollbackCreateFinOper(DocFlowPluginInvokeParams params) {
		doRollbackCreateFinOper(params);
	}

	/**
	 * ���������� ��������� ��� "������� ��"
	 * 
	 * @param params
	 * 			- ��������� ����������������
	 * @param listener
	 * 			- ��������� �� ������� ������� �������� �� ��������������
	 */
	private void doProcessCreateFinOper(DocFlowPluginInvokeParams params, final CreateDocumentDocFlowListener listener) {
		OperationModelServiceLocal operServModel = (OperationModelServiceLocal) ApplicationDictionaryLocator.locate()
				.getBusinessService(OperationModelServiceLocal.SERVICE_NAME);
		OperationModel finOperModel = operServModel.load(params.getPerformedStage().getLinkDocModel());

		FinOperation fo =  createByPattern(finOperModel);
		createFinOper(params.getDocument(), fo, finOperModel, params.getProcessDate(), params.getPerformedStage().getLinkDocDestFolder());

		//data1-������ ��������� data2-id ���������� ���������
		params.setData1(params.getPerformedStage().getLinkDocSection().getId());
		params.setData2(fo.getId());

		if (params.getPerformedStage().isShowNewDocument() && !params.isSilent()){
			//���������� ����� ������� ��������
			ServerUtils.getPersistentManager().flush();
			//������� ����� ���� ����� ����� ��������
			ServerUtils.getPersistentManager().refresh(this.finOper);
			MaintenanceHelper.edit(getServiceFinOperation(), fo.getId(), null, new MaintenanceFormActionListener() {

				public void canceled(MaintenanceFormEvent event) {
					listener.canceled();
				}

				public void performed(MaintenanceFormEvent event) {
					listener.completed();
				}

			});
		} else
			listener.completed();
	}

	/**
	 * ������� ������ ��� "������� ��"
	 * @param params
	 * 			- ��������� ������
	 */
	private void doRollbackCreateFinOper(DocFlowPluginInvokeParams params) {
		getServiceFinOperation().erase(params.getData2());	
	}

	/**
	 * ���������� ������ ���������� ��������
	 * @return
	 */
	private OperationServiceLocal getServiceFinOperation() {
		if (operationService == null)
			operationService = (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Operation");
		return operationService;
	}

	/**
	 * ���������� ������ ������������ ���. ��������
	 * @return
	 */
	private SpecificationServiceLocal getServiceFinSpec() {
		if (specificationService == null)
			specificationService = (SpecificationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Specification");
		return specificationService;
	} 

	/**
	 * ���������� ������ ����� �����
	 * @return
	 */
	private CurrencyRateServiceLocal getServiceCurrencyRate() {
		if (currencyRateService == null)
			currencyRateService = (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyRateServiceLocal.SERVICE_NAME);
		return currencyRateService;
	} 

	private SpecificationModelServiceLocal getServiceFinSpecModel() {
		if (specificationModelService == null)
			specificationModelService = (SpecificationModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(SpecificationModelServiceLocal.SERVICE_NAME);
		return specificationModelService;
	}

	/**
	 * �������� �� �� ��������� ��������� �� ������� ��
	 * 
	 * @param srcDocHead
	 * 			- �������� ���������
	 * @param finOper
	 * 			- ����������� ���. ��������
	 * @param finOperModel
	 * 			- ������� ����������� ���. ��������
	 * @param processDate
	 * 			- ����� ��������� ��� "������� ��"
	 * @param folder
	 * 			- �����-��������
	 */
	private void createFinOper(DocHead srcDocHead, FinOperation finOper, OperationModel finOperModel, Date processDate, Folder folder) {
		fillItemByModel(finOper, finOperModel, processDate);

		finOper.setConfirmDoc(srcDocHead);
		finOper.setConfirmDocType(srcDocHead.getDocType());
		finOper.setConfirmDocNumber(srcDocHead.getDocNumber());
		finOper.setConfirmDocDate(srcDocHead.getDocDate());
		if (finOper.getBaseDocType() == null && finOper.getBaseDocDate() == null && StringUtils.stringNullOrEmpty(finOper.getBaseDocNumber())) {
			finOper.setBaseDoc(srcDocHead.getBaseDocument());
			finOper.setBaseDocType(srcDocHead.getBaseDocType());
			finOper.setBaseDocNumber(srcDocHead.getBaseDocNumber());
			finOper.setBaseDocDate(srcDocHead.getBaseDocDate());
		}
		if (finOper.getContractType() == null && finOper.getContractDate() == null && StringUtils.stringNullOrEmpty(finOper.getContractNumber())) {
			if (srcDocHead.getContract() != null)
				finOper.setContractId(srcDocHead.getContract().getId());
			finOper.setContractType(srcDocHead.getContractType());
			finOper.setContractNumber(srcDocHead.getContractNumber());
			finOper.setContractDate(srcDocHead.getContractDate());
		}
		if (finOper.getFrom() == null)
			finOper.setFrom(srcDocHead.getFrom());
		if (finOper.getTo() == null)
			finOper.setTo(srcDocHead.getTo());
		if (finOperModel.getFrom() == null)
			if (finOperModel.getSourceFrom() == null)
				finOper.setFrom(srcDocHead.getFrom());
			else
				switch (finOperModel.getSourceFrom()) {
				case NONE: 
					finOper.setFrom(srcDocHead.getFrom());
					break;
				case PROVIDER: 
					finOper.setFrom(srcDocHead.getFrom());
					break;
				case CUSTOMER: 
					finOper.setFrom(srcDocHead.getTo());
					break;
				case THROUGH: 
					finOper.setFrom(srcDocHead.getThrough());
					break;
				case SHIPPER:
					if (srcDocHead.hasAttribute("Shipper"))
						finOper.setFrom((Contractor)srcDocHead.getAttribute("Shipper"));
					break;	
				case CONSIDNEE:
					if (srcDocHead.hasAttribute("Considnee"))
						finOper.setFrom((Contractor)srcDocHead.getAttribute("Considnee"));
					break;	
				case STOCKDEST: 
					finOper.setFrom(srcDocHead.getDstStock());
					break;
				case STOCKSRC: 
					finOper.setFrom(srcDocHead.getSrcStock());
					break;
				case MOLDEST: 
					finOper.setFrom(srcDocHead.getDstMol());
					break;
				case MOLSRC: 
					finOper.setFrom(srcDocHead.getSrcMol());
					break;
				}
		else 
			finOper.setFrom(finOperModel.getFrom());
		if (finOperModel.getTo() == null)
			if (finOperModel.getSourceTo() == null) 
				finOper.setTo(srcDocHead.getTo());
			else
				switch (finOperModel.getSourceTo()) {
				case NONE: 
					finOper.setTo(srcDocHead.getTo());
					break;
				case PROVIDER: 
					finOper.setTo(srcDocHead.getTo());
					break;
				case CUSTOMER: 
					finOper.setTo(srcDocHead.getFrom());
					break;
				case THROUGH: 
					finOper.setTo(srcDocHead.getThrough());
					break;
				case SHIPPER:
					if (srcDocHead.hasAttribute("Shipper"))
						finOper.setTo((Contractor)srcDocHead.getAttribute("Shipper"));
					break;	
				case CONSIDNEE:
					if (srcDocHead.hasAttribute("Considnee"))
						finOper.setTo((Contractor)srcDocHead.getAttribute("Considnee"));
					break;	
				case STOCKDEST: 
					finOper.setTo(srcDocHead.getDstStock());
					break;
				case STOCKSRC: 
					finOper.setTo(srcDocHead.getSrcStock());
					break;
				case MOLDEST: 
					finOper.setTo(srcDocHead.getDstMol());
					break;
				case MOLSRC: 
					finOper.setTo(srcDocHead.getSrcMol());
					break;
				}
		else 
			finOper.setTo(finOperModel.getTo());
		// ��������� ���������� ������
		FinConfig config = ConfigurationHelper.getConfiguration(); 
		if (finOperModel.getCurrency() == null) 
			finOper.setCurrency(srcDocHead.getCurrency());
		if (finOperModel.getCurRateType() == null)
			finOper.setCurRateType(srcDocHead.getCurrencyRateType());
		if (finOperModel.getCurRateAuthority() == null)
			finOper.setCurRateAuthority(srcDocHead.getCurrencyRateAuthority());
		if (finOper.getCurrency().getId() != config.getNatCurrency().getId())
			finOper.setCurRate(getCurRate(finOper, config));
		else
			finOper.setCurRate(BigDecimal.ONE);
		if (finOper.getFolder() == null)
			finOper.setFolder(folder);
		if (finOper.getComment() == null)
			finOper.setComment(StringUtils.EMPTY_STRING);
		getServiceFinOperation().create(finOper);
		this.finOper = finOper;
		//������� ��������
		createFinSpecs(srcDocHead, finOper, finOperModel);
	}

	/**
	 * �������������� ����������� ���. �������� ���������� �������
	 * 
	 * @param pattern - ������� ���. ��������
	 * @return
	 */
	private FinOperation createByPattern(OperationModel pattern) {
		OperationModelServiceLocal finOperModelService = (OperationModelServiceLocal) ApplicationDictionaryLocator.locate()
				.getBusinessService(OperationModelServiceLocal.SERVICE_NAME);
		OperationServiceLocal finOperService = getServiceFinOperation();
		FinOperation finOperation = finOperService.initialize();
		for(String key : pattern.getAllAttributes().keySet())
			if (isMutableFinOperAttribute(key)){
				Object o = pattern.getAttribute(key); 
				if (o != null && finOperation.hasAttribute(key))
					finOperation.setAttribute(key, o);
			}
		finOperation.setFolder(pattern.getModelDestFolder());
		CustomFieldsManagerLocator.locate().cloneValues(finOperModelService, pattern, finOperService, finOperation);
		return finOperation;
	}

	/**
	 * ������������� ����� �������� ����� ���������� �� ������� ���. �������� � 
	 * ����������� ���. ��������
	 * 
	 * @param attributeName 	-	 ��� ��������
	 * @return						<code>true</code> - ���� �����, ����� <code>false</code>				
	 */
	public static boolean isMutableFinOperAttribute(String attributeName) {
		String[] attributeNames = new String[] {"Id", "SourceTo", "ModelName", "SourceFrom", "ModelDestFolder", "Folder", "Specifications"};
		for (String element : attributeNames)
			if (element.equals(attributeName))
				return false;
		return true;
	}

	/**
	 * ���������� ���� ������ �� ������������ ����������� ������ ������ �� ������������ ������
	 * 
	 * @param finOper
	 * 		- ��
	 * @param config
	 * 		- ������������ ������ ���. ����
	 * @return
	 */
	private BigDecimal getCurRate(FinOperation finOper, FinConfig config) {
		try {
			//�������� �������� ������ ���� ��� �����
			return getServiceCurrencyRate().getCurrencyRate(config.getNatCurrency(), finOper.getCurrency()
					, finOper.getCurRateAuthority(), finOper.getCurRateType(), finOper.getKeepDate());
		} catch (CurrencyRateNotFoundException e) {
			//���� ������� ����� ���, �� �������� �������� �������� ����
			return MathUtils.divide(BigDecimal.ONE, getServiceCurrencyRate().getIndirectCurrencyRate(config.getNatCurrency(), finOper.getCurrency()
					, finOper.getCurRateAuthority(), finOper.getCurRateType(), finOper.getKeepDate()), new RoundContext(CurrencyRateServiceLocal.DEFAULT_RATE_SCALE));
		}
	}

	/**
	 * ������ ����� ����� ����������� ��
	 * @param finOper
	 * 			- ����������� ��
	 * @param finOperModel
	 * 			- ������� ��
	 */
	private void fillItemByModel(FinOperation finOper, OperationModel finOperModel, Date processDate) {
		if (processDate != null)
			finOper.setKeepDate(processDate);
		else
			finOper.setKeepDate(DateTimeUtils.getDayStart(DateTimeUtils.nowDate()));
		// � ������ ������������� �������� ����� � 4- �� ��� �������� �� ������� ������
	}

	/**
	 * �������������� �������� ������������ ����������� ���. ��������
	 * 
	 * @param srcDocHead
	 * 			- ��������-��������
	 * @param finOper
	 * 			- ����������� ���. ��������
	 * @param finOperModel
	 * 			- ������� ���. ��������
	 */
	private void createFinSpecs(DocHead srcDocHead, FinOperation finOper, OperationModel finOperModel) {
		// � ������ ��� ����� ������, ����� ������ �������� � ������
		// �� � � ����� ��� �������������, ����� ����������� �������
		// ���� ������� ����� ����� �������� �������������� ���������
		finSpecList.clear();
		for (SpecificationModel finSpecModel : finOperModel.getSpecifications()) {
			// � ������ calculateFormula ��������� � 
			// ��������������� �� ��������� ����� ����������� ��� �������� � ��������� � map
			calculateFormula(finSpecModel, null);
			for (Specification finSpec : finSpecList) {
				List<SpecificationModel> finFeatureModelList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(SpecificationModel.class)
						.add(Restrictions.eq("Parent", finSpecModel))
						.setFlushMode(FlushMode.MANUAL));
				//���� �� ��������������� ������ ������������ �������
				for (SpecificationModel finFeatureModel : finFeatureModelList) {
					// � ������ calculateFormula ��������� � 
					// ��������������� �� ��������� �������� ����������� ��� �������� � ��������� � map
					calculateFormula(finFeatureModel, finSpec);					
				}

			}
		}
	}

	private Map<String, Object> createBAiParams(DocumentSpecItem docSpecItem, Specification finSpec) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(FinanceBusinessAddin.DOCFLOW_PARAM_NAME, params);
		result.put(FinanceBusinessAddin.DOCSPEC_PARAM_NAME, docSpecItem);
		result.put(FinanceBusinessAddin.FINOPER_PARAM_NAME, finOper);
		result.put(FinanceBusinessAddin.FINSPEC_PARAM_NAME, finSpec);
		return result;
	}

	private class BusinessAddinListenerImpl implements BusinessAddinListener<BigDecimal> {
		private BigDecimal sum;

		public void aborted(BusinessAddinEvent<BigDecimal> event) {
			docFlowListener.canceled();
		}

		public void completed(BusinessAddinEvent<BigDecimal> event) {
			sum = event.getResult();
		}

		public BigDecimal getSum() {
			return sum;
		}

	}

	/**
	 * �������������� �������� ������������ ����������� ���. ��������, � ����� �������� �� ����� � ������ ����������� ����������
	 * 
	 * @param finSpecModel
	 * 		- ������������ ������� ���. ��������
	 * @param parentSpec
	 * 		- ��� �������� ��������� - ������������� �������� ������, ������� ����������� ������ ��������, �
	 *	��� �������� ������ - null 
	 */
	private void calculateFormula(SpecificationModel finSpecModel, Specification parentSpec) {
		//����� ������������ BAI
		BigDecimal sum = BigDecimal.ZERO;
		FinConfig config = ConfigurationHelper.getConfiguration(); 
		CurrencyServiceLocal curServ = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
		Currency srcDocCurrency = params.getDocument().getCurrency();
		Currency finOperCurrency = finOper.getCurrency();
		Specification dstFinSpec = createFinSpec(finSpecModel, parentSpec, null, null);
		//���� � ��������� ��������� ���� ������������
		if (docSpecItemList != null)
			for (DocumentSpecItem dsi : docSpecItemList) {
				Map<String, Object> baiParams = createBAiParams(dsi, dstFinSpec);
				BusinessAddinListenerImpl listener = new BusinessAddinListenerImpl();
				BusinessAddinEngineLocator.locate().perform(finSpecModel.getAlg(), baiParams, listener);
				sum = MathUtils.addNullable(sum, listener.getSum(), new RoundContext(config.getCurrencyPrec()));
			}
		else {
			// ���� � ��������� ��������� ��� ������������
			// ��� �� ���������������� �������� � �����������
			Map<String, Object> baiParams = createBAiParams(null, dstFinSpec);
			BusinessAddinListenerImpl listener = new BusinessAddinListenerImpl();
			BusinessAddinEngineLocator.locate().perform(finSpecModel.getAlg(), baiParams, listener);
			sum = listener.getSum();
		}
		//��������� ���������� ����� � ������ ���. ��������
		BigDecimal sumCur = BigDecimal.ZERO;
		if (finOperCurrency.getId() != srcDocCurrency.getId()) {
			sumCur = currencyConvert(finOperCurrency, srcDocCurrency, curServ, sum, config);					
		} else
			sumCur = sum;

		// ����� ������������ ���. �������� � ������������ ������
		BigDecimal sumNat = BigDecimal.ZERO;
		Currency natCurrency = config.getNatCurrency();
		//��������� ���������� ����� � ������������ ������
		if (natCurrency.getId() != srcDocCurrency.getId())
			sumNat = currencyConvert(natCurrency, srcDocCurrency, curServ, sum, config);
		else 
			sumNat = sum;

		persistFinSpec(dstFinSpec, sumCur, sumNat);
	}

	/**
	 * ����������� �������� ����
	 * @param curTo
	 * 			- � ����� ������
	 * @param curFrom
	 * 			- �� ����� ������
	 * @param curServ
	 * 			- ������ ����������� �����
	 * @param currencyFromAmount
	 * 			- �������� ������� ����� ����������
	 * @param config
	 * 			- ����������� ������ ���. ����
	 * @return currencyFromAmount � ������ curTo
	 */
	private BigDecimal currencyConvert(Currency curTo, Currency curFrom, CurrencyServiceLocal curServ, BigDecimal currencyFromAmount, FinConfig config) {
		return MathUtils.round(curServ.conversion(curTo, curFrom, finOper.getCurRateAuthority(), finOper.getCurRateType(), finOper.getKeepDate(), currencyFromAmount),
				new RoundContext(config.getCurrencyPrec()));					

	}
	/**
	 * ������� ������������(����/�������) ���. ��������
	 * 
	 * @param finSpecModel - ������� ������������
	 * @param parent	   - ��� �������� ��������� - ������������� �������� ������, ������� ����������� ������ ��������, �
	 *					��� �������� ������ - null 
	 * @param sumSpec	   - �������� ����� ����������� ������������
	 */
	private Specification createFinSpec(SpecificationModel finSpecModel, Specification parent, BigDecimal sumCur, BigDecimal sumNat) {
		Specification dstFinSpec = getServiceFinSpec().initialize();
		for(String key : finSpecModel.getAllAttributes().keySet())
			if (isMutableFinSpecAttribute(key)){
				Object o = finSpecModel.getAttribute(key); 
				if (o != null && dstFinSpec.hasAttribute(key))
					dstFinSpec.setAttribute(key, o);
			}
		dstFinSpec.setFinOper(this.finOper);
		dstFinSpec.setParent(parent);
		CustomFieldsManagerLocator.locate().cloneValues(getServiceFinSpecModel(), finSpecModel, getServiceFinSpec(), dstFinSpec);
		if (parent == null)
			finSpecList.add(dstFinSpec);
		return dstFinSpec;
	}

	private void persistFinSpec(Specification finOper, BigDecimal sumCur, BigDecimal sumNat) {
		finOper.setSumCur(sumCur);
		finOper.setSumNat(sumNat);
		getServiceFinSpec().create(finOper);
	}

	/**
	 * ������������� ����� �������� ����� ���������� �� ������������ ������� ���. �������� � 
	 * ������������ ����������� ���. ��������
	 * 
	 * @param attributeName 	-	 ��� ��������
	 * @return						<code>true</code> - ���� �����, ����� <code>false</code>				
	 */
	public static boolean isMutableFinSpecAttribute(String attributeName) {
		String[] attributeNames = new String[] {"Id", "FinOper", "Parent", "Alg", "SumNat", "SumCur", "Formula"};
		for (String element : attributeNames)
			if (element.equals(attributeName))
				return false;
		return true;
	}

}
