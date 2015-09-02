/*
 * PaymentcontrolProcessorServiceBean.java
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
import javax.ejb.Remove;
import javax.ejb.Stateful;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.generic.AbstractPOJOBusinessObjectStatefulServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.paymentcontrol.ExecutionServiceLocal;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;
import com.mg.merp.paymentcontrol.PaymentControlProcessorListener;
import com.mg.merp.paymentcontrol.PaymentcontrolProcessorServiceLocal;
import com.mg.merp.paymentcontrol.model.ContractorSource;
import com.mg.merp.paymentcontrol.model.Execution;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.paymentcontrol.model.PmcConfig;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.BankAccount;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;

/**
 * Реализация процессора модуля "Платежный календарь"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PaymentcontrolProcessorServiceBean.java,v 1.6 2007/10/02 12:54:35 sharapov Exp $
 */
@Stateful(name="merp/paymentcontrol/PaymentcontrolProcessorService") //$NON-NLS-1$
public class PaymentcontrolProcessorServiceBean extends AbstractPOJOBusinessObjectStatefulServiceBean 
implements PaymentcontrolProcessorServiceLocal {

	private DocFlowPluginInvokeParams docFlowParams;
	private PaymentControlProcessorListener processorListener;


	/* (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.PaymentcontrolProcessorServiceLocal#createLiability(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	@Remove
	public void createLiability(DocFlowPluginInvokeParams docFlowParams, PaymentControlProcessorListener processorListener) throws Exception {
		prepareValues(docFlowParams, processorListener);
		doCreateLiability();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.PaymentcontrolProcessorServiceLocal#rollBackCreateLiability(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	@Remove
	public void rollBackCreateLiability(DocFlowPluginInvokeParams docFlowParams) {
		dolRollBackCreateLiability(docFlowParams);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.PaymentcontrolProcessorServiceLocal#confirmExecutionByDocument(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	@Remove
	public void confirmExecutionByDocument(DocFlowPluginInvokeParams docFlowParams) {
		prepareValues(docFlowParams, null);
		doConfirmExecutionByDocument();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.PaymentcontrolProcessorServiceLocal#rollBackConfirmExecutionByDocument(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	@Remove
	public void rollBackConfirmExecutionByDocument(DocFlowPluginInvokeParams docFlowParams) {
		doUnConfirmExecutionByDocument(docFlowParams.getData1());
	}

	private void prepareValues(DocFlowPluginInvokeParams docFlowParams, PaymentControlProcessorListener processorListener) {
		this.docFlowParams = docFlowParams;
		this.processorListener = processorListener;
	}
	
	protected void doUnConfirmExecutionByDocument(Integer executionId) {
		if(executionId != null) {
			Execution execution = getExecutionService().load(executionId);
			if(execution != null) {
				processExecution(execution, false, null);
				getExecutionService().store(execution);
			}
		}
	}
	
	protected void doConfirmExecutionByDocument() {
		Execution execution = getExecutionByDoc(docFlowParams.getDocument());
		if(execution == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.EXECUTION_NOT_FOUND));
		
		processExecution(execution, true, docFlowParams.getProcessDate());
		getExecutionService().store(execution);
		
		updateDocFlowParamsByConfirmExecutionByDocument(execution.getId());
	}
	
	/**
	 * Получить исполнение обязательства по сформированному документу
	 * @param docHead - документ
	 * @return исполнение обязательства
	 */
	protected Execution getExecutionByDoc(DocHead docHead) {
		return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Execution.class)
					.add(Restrictions.eq("DocHead", docHead))); //$NON-NLS-1$
	}
	
	/**
	 * Обработать исполнение
	 * @param execution - исполнение
	 * @param isDocPocessed - признак подтверждения исполнения документом
	 * @param factDate - дата подтверждения
	 */
	protected void processExecution(Execution execution, boolean isDocPocessed, Date factDate) {
		execution.setDocProcessed(isDocPocessed);
		execution.setFactDate(factDate);
	}
	
	private void updateDocFlowParamsByConfirmExecutionByDocument(Integer executionId) {
		docFlowParams.setData1(executionId);
		docFlowParams.setData2(0);
	}

	private void doCreateLiability() throws Exception {
		Integer modelId = docFlowParams.getPerformedStage().getLinkDocModel();

		if(modelId == null) {
			SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.paymentcontrol.support.ui.LiabilityModelSearchHelp"); //$NON-NLS-1$
			searchHelp.addSearchHelpListener(new SearchHelpListener() {

				public void searchPerformed(SearchHelpEvent event) {
					internalCreateLiability((Liability) event.getItems()[0]);
				}

				public void searchCanceled(SearchHelpEvent event) {
					processorListener.canceled();
				}
			});
			searchHelp.search();
		}
		else 
			internalCreateLiability(loadLiabilityModel(modelId));
	}

	private void internalCreateLiability(Liability liabilityModel) {
		Liability liability = createLiabilityByDoc(
				docFlowParams.getDocument(),
				docFlowParams.getProcessDate(),
				docFlowParams.getPerformedSum(),
				liabilityModel);

		createLiability(liability);
		updateDocFlowParams(liability);

		if(docFlowParams.getPerformedStage().isShowNewDocument())
			editLiability(liability);
		else
			processorListener.completed();
	}

	private void updateDocFlowParams(Liability liability) {
		docFlowParams.setData2(liability.getId());
	}

	private void editLiability(Liability liability) {
		if(liability.getId() != null) {
			ServerUtils.getPersistentManager().flush(); // to avoid DataAccessException by cancel maintenanceAction 
			MaintenanceHelper.edit(getLiabilityService(), liability.getId(), null, new MaintenanceFormActionListener() {

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.MaintenanceFormActionListener#canceled(com.mg.framework.api.ui.MaintenanceFormEvent)
				 */
				public void canceled(MaintenanceFormEvent event) {
					processorListener.canceled();
				}

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.MaintenanceFormActionListener#performed(com.mg.framework.api.ui.MaintenanceFormEvent)
				 */
				public void performed(MaintenanceFormEvent event) {
					processorListener.completed();
				}
			});
		}
	}

	private LiabilityServiceLocal getLiabilityService() {
		return (LiabilityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(LiabilityServiceLocal.LOCAL_SERVICE_NAME);
	}

	private void createLiability(Liability liability) {
		if(liability != null)
			getLiabilityService().create(liability);
	}

	private Liability createLiabilityByDoc(DocHead document, Date processDate, BigDecimal docSum, Liability liabilityModel) {
		PmcConfig moduleConfig = getModuleConfiguration();

		Liability liability = initializeByPattern(liabilityModel, processDate);
		initializeByDocument(liability, document);

		if(liability.getFrom() == null)
			liability.setFrom(getContractorBySource(liabilityModel.getFromSource(), document));

		if(liability.getTo() == null)
			liability.setTo(getContractorBySource(liabilityModel.getToSource(), document));

		if(liability.getFrom() != null)
			liability.setFromBankAcc(getDefaultBankAccount(liability.getFrom()));

		if(liability.getTo() != null)
			liability.setToBankAcc(getDefaultBankAccount(liability.getTo()));

		liability.setSumCur(MathUtils.round(docSum, new RoundContext(moduleConfig.getCurrencyPrec())));
		// берем валюту из документа, если не указана в образце
		if(liabilityModel.getCurCode() == null) {
			liability.setCurCode(document.getCurrency());
			liability.setCurRateAuthority(document.getCurrencyRateAuthority());
			liability.setCurRateType(document.getCurrencyRateType());
		}
		else { 
			// если валюты образца и документа различны, то конвертируем сумму в валюту образца 
			if(!isSameCurrency(liability.getCurCode(), document.getCurrency())) {
				BigDecimal convertedToModelCurSum = getCurrencyConverter().conversion(
						liability.getCurCode(), 
						document.getCurrency(), 
						liability.getCurRateAuthority(), 
						liability.getCurRateType(), 
						processDate, 
						docSum);

				liability.setSumCur(MathUtils.round(convertedToModelCurSum, new RoundContext(moduleConfig.getCurrencyPrec())));
			}
		}
		//clone custom fields
		CustomFieldsManagerLocator.locate().cloneValues(DocumentUtils.getDocumentService(document.getDocSection()),	document,
		(LiabilityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(LiabilityServiceLocal.LOCAL_SERVICE_NAME),	liability);

		return liability;
	}

	private Contractor getContractorBySource(ContractorSource source, DocHead document) {
		if(source == ContractorSource.FROM)
			return document.getFrom();
		if(source == ContractorSource.TO)
			return document.getTo();
		if(source == ContractorSource.THROUGH)
			return document.getThrough();
		return null;
	}

	private Liability initializeByPattern(Liability pattern, Date regDate) {
		Messages msg = Messages.getInstance();
		Folder folder = pattern.getDestFolder();

		if(folder == null)
			throw new BusinessException(msg.getMessage(Messages.LIABILITY_DST_FOLDER_INVALID));

		Liability liability = getLiabilityService().createByPattern(pattern, folder);
		liability.setRegDate(regDate);
		liability.setExecutions(null);
		liability.setNum(null);
		liability.setSumCur(BigDecimal.ZERO);
		return liability;
	}

	private Liability initializeByDocument(Liability liability, DocHead document) {
		liability.setDocHead(document);
		liability.setDocType(document.getDocType());
		liability.setDocNumber(document.getDocNumber());
		liability.setDocDate(document.getDocDate());

		liability.setBaseDoc(document.getBaseDocument());
		liability.setBaseDocType(document.getBaseDocType());
		liability.setBaseDocNumber(document.getBaseDocNumber());
		liability.setBaseDocDate(document.getBaseDocDate());

		liability.setContract(document.getContract());
		liability.setContractType(document.getContractType());
		liability.setContractNumber(document.getContractNumber());
		liability.setContractDate(document.getContractDate());

		return liability;
	}

	private BankAccount getDefaultBankAccount(Contractor contractor) {
		if(contractor != null)
			return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(BankAccount.class)
					.add(Restrictions.eq("IsDefault", true)) //$NON-NLS-1$
					.add(Restrictions.eq("Contractor", contractor))); //$NON-NLS-1$
		else
			return null;
	}

	private boolean isSameCurrency(Currency currency1, Currency currency2) {
		if(currency1.getId().equals(currency2.getId()))
			return true;
		else
			return false;
	}

	private CurrencyServiceLocal getCurrencyConverter() {
		return (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Currency"); //$NON-NLS-1$
	}

	private Liability loadLiabilityModel(Integer liabilityModelId) {
		return ServerUtils.getPersistentManager().find(Liability.class, liabilityModelId);
	}

	private PmcConfig getModuleConfiguration() {
		SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
		return ServerUtils.getPersistentManager().find(PmcConfig.class, sysClient.getId()); 
	}

	private void dolRollBackCreateLiability(DocFlowPluginInvokeParams docFlowParams) {
		Integer liabilityId = docFlowParams.getData2();
		
		if(liabilityId != null) {
			Object count = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Liability.class)
					.setProjection(Projections.rowCount())
					.add(Restrictions.eq("Id", liabilityId))); //$NON-NLS-1$

			if((Integer) count > 0)
				getLiabilityService().erase(liabilityId);
		}
	}
	
	private ExecutionServiceLocal getExecutionService() {
		return (ExecutionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ExecutionServiceLocal.LOCAL_SERVICE_NAME);
	}

}
