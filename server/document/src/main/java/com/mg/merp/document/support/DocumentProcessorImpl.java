/* DocumentProcessorImpl.java
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
package com.mg.merp.document.support;

import java.util.List;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocFlowListener;
import com.mg.merp.docflow.DocFlowManager;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docflow.RollbackNotAllowedException;
import com.mg.merp.docprocess.model.DocProcessInteractiveKind;
import com.mg.merp.document.CreateDocOnComponentsLocator;
import com.mg.merp.document.CreateDocumentBasisOf;
import com.mg.merp.document.CreateDocumentBasisOfCallback;
import com.mg.merp.document.CreateDocumentBasisOfLocator;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.document.Document;
import com.mg.merp.document.DocumentProcessor;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;

/**
 * Реализация процессора документов
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: DocumentProcessorImpl.java,v 1.9 2009/04/29 06:55:02 safonov Exp $
 */
public class DocumentProcessorImpl implements DocumentProcessor {

	public int createDocumentOnBasisOfSeveral(int srcDocSec, int[] srcKeys, int dstDocSec, int modelId) throws ApplicationException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int[] initDocumentOnBasisOfSeveral() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.DocumentProcessor#processCreateDocumentBasisOf(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.document.CreateDocumentDocFlowListener)
	 */
	public void processCreateDocumentBasisOf(DocFlowPluginInvokeParams params, final CreateDocumentDocFlowListener listener) {
		doProcessCreateDocument(params, 0, listener, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.DocumentProcessor#processCreateDocumentBasisOf(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.document.CreateDocumentDocFlowListener, com.mg.merp.document.CreateDocumentBasisOfCallback)
	 */
	public void processCreateDocumentBasisOf(DocFlowPluginInvokeParams params,
			CreateDocumentDocFlowListener listener,
			CreateDocumentBasisOfCallback createCallback) {
		doProcessCreateDocument(params, 1, listener, createCallback);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.DocumentProcessor#processCreateDocOnComponents(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.document.CreateDocumentDocFlowListener)
	 */
	public void processCreateDocOnComponents(DocFlowPluginInvokeParams params, final CreateDocumentDocFlowListener listener) {
		doProcessCreateDocument(params, 1, listener, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.DocumentProcessor#rollbackCreateDocumentBasisOf(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.document.CreateDocumentDocFlowListener)
	 */
	public void rollbackCreateDocumentBasisOf(DocFlowPluginInvokeParams params, final CreateDocumentDocFlowListener listener) {
		doRollbackCreateDocument(params, listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.DocumentProcessor#rollbackCreateDocOnComponents(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.document.CreateDocumentDocFlowListener)
	 */
	public void rollbackCreateDocOnComponents(DocFlowPluginInvokeParams params, final CreateDocumentDocFlowListener listener) {
		doRollbackCreateDocument(params, listener);
	}

	/**
	 * Создание документа
	 *
	 * @param params
	 * 		данные этапа документооборота
	 * @param kind
	 * 		признак создания документа (0 - создать документ на основании, 1 - создать документ на комплектующие и т.д.)
	 * @param listener
	 * 		слушатель на событие редактирование документа
	 */
	@SuppressWarnings("unchecked")
	private void doProcessCreateDocument(DocFlowPluginInvokeParams params, int kind, final CreateDocumentDocFlowListener listener, final CreateDocumentBasisOfCallback createCallback) {
		if (listener == null)
			throw new IllegalArgumentException("listener is null");

		CreateDocumentBasisOf crdoc = getCreateDocumentStrategy(kind);
		DocSection ds = params.getPerformedStage().getLinkDocSection();
		Document docServ = DocumentUtils.getDocumentService(ds);

		DocHeadModel dhm = (DocHeadModel)((DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService(ds.getModelSysClass().getBeanName())).load(params.getPerformedStage().getLinkDocModel());

		List<DocumentSpecItem> specList = null;

		//проверка на наличие спецификаций
		if (ds.isWithSpec())
			specList = params.getSpecList();

		DocHead dh = (DocHead) docServ.initialize();
		crdoc.doCreate(params.getDocument(), dh, dhm, params.getProcessDate(), specList, params.getPerformedStage().getLinkDocDestFolder(), createCallback);

		//data1-секция документа data2-id созданного документа
		params.setData1(params.getPerformedStage().getLinkDocSection().getId());
		params.setData2(dh.getId());


		if (params.getPerformedStage().isShowNewDocument() && !params.isSilent()){
			//иначе refresh ругается, что запись ещё не создана в БД!
			//Когда в одном из документов присутствуют спецификации, а в другом-нет
			ServerUtils.getPersistentManager().flush();
			MaintenanceHelper.edit((DataBusinessObjectService)ApplicationDictionaryLocator.locate().getBusinessService(docServ.getBusinessServiceMetadata().getName()), dh.getId(), null, new MaintenanceFormActionListener() {

				public void canceled(MaintenanceFormEvent event) {
					try {
						listener.canceled();
					} catch (RuntimeException e) {
						event.getForm().close();
						throw e;
					}
				}

				public void performed(MaintenanceFormEvent event) {
					try {
						listener.completed();
					} catch (RuntimeException e) {
						event.getForm().close();
						throw e;
					}
				}

			});
		} else
			listener.completed();
	}

	private class RollbackDocFlowListenerImpl implements DocFlowListener {
		private DocFlowPluginInvokeParams params;
		private CreateDocumentDocFlowListener listener;
		private short currentOwner;
		private boolean isSilent;

		private RollbackDocFlowListenerImpl(DocFlowPluginInvokeParams params,
				CreateDocumentDocFlowListener listener, short currentOwner, boolean isSilent) {
			super();
			this.params = params;
			this.listener = listener;
			this.currentOwner = currentOwner;
			this.isSilent = isSilent;
		}

		public void canceled() {
			listener.canceled();
		}

		public void performed() {
			try {
				//пытаемся еще раз откатывать, т.о. откатываем весь ДО, до тех пор пока не возникнет ИС RollbackNotAllowedException
				DocFlowManager dm = (DocFlowManager) ApplicationDictionaryLocator.locate().getBusinessService(DocFlowManager.SERVICE_NAME);
				dm.rollback(params.getData2(), this, currentOwner, isSilent);
			} catch (RollbackNotAllowedException e) {
				//данная ИС возникает если ДО откатываемого документа не выполнен, предположительно откатили весь ДО,
				//поэтому пытаемся просто удалить созданный
				//документ, если же ИС возникла вследствии отсутствия прав на откат, но при этом ДО созданного
				//документа не откатили полностью, то ИС будет сгенерирована уже при удалении
				try {
					//сбросим в хранилище информацию об откате ДО, иначе удаление не пройдет, т.к. будет
					//видеть ДО на удаляемом документе
					ServerUtils.getPersistentManager().flush();
					eraseDocument(params);
					listener.completed();
				} catch (RuntimeException re) {
					listener.canceled();
					throw re;
				}
			} catch (RuntimeException e) {
				listener.canceled();
				throw e;
			}
		}

	}

	/**
	 * Откат создания документа
	 *
	 * @param params
	 */
	private void doRollbackCreateDocument(final DocFlowPluginInvokeParams params, final CreateDocumentDocFlowListener listener) {
		if (listener == null)
			throw new IllegalArgumentException("listener is null");

		if (params.getPerformedStage().isLinkDocRollback()) {
			DocFlowManager dm = (DocFlowManager) ApplicationDictionaryLocator.locate().getBusinessService(DocFlowManager.SERVICE_NAME);
			DocProcessInteractiveKind dpKind = params.getPerformedStage().getLinkDocRollbackInteractive();
			//передадим параметр silent в зависимости от настроек этапа создания документа
			boolean isSilent = DocProcessInteractiveKind.INHERITED.equals(dpKind) ? params.isSilent() : DocProcessInteractiveKind.SILENT.equals(dpKind);
			short currentOwner = com.mg.merp.docflow.support.DocumentUtils.getCurrentDocumentOwner();
			DocFlowListener docFlowListener = new RollbackDocFlowListenerImpl(params, listener, currentOwner, isSilent);
			try {
				dm.rollback(params.getData2(), docFlowListener, currentOwner, isSilent);
			} catch (RollbackNotAllowedException re) {
				//сбросим в хранилище информацию об откате ДО, иначе удаление не пройдет, т.к. будет
				//видеть ДО на удаляемом документе
				ServerUtils.getPersistentManager().flush();
				eraseDocument(params);
				listener.completed();
			}
		}
		else {
			eraseDocument(params);
			listener.completed();
		}
	}

	@SuppressWarnings("unchecked")
	private void eraseDocument(DocFlowPluginInvokeParams params) {
		Document docServ = DocumentUtils.getDocumentService(params.getPerformedStage().getLinkDocSection());
		docServ.erase(params.getData2());
	}

	/**
	 * Определяет стратегию создания документа
	 *
	 * @param kind
	 * 		признак создания документа (0 - создать документ на основании, 1 - создать документ на комплектующие и т.д.)
	 * @return
	 * 		стратегия создания документа
	 */
	@SuppressWarnings("unchecked")
	private CreateDocumentBasisOf getCreateDocumentStrategy(int kind) {
		switch (kind) {
		case 0: return CreateDocumentBasisOfLocator.locate();
		case 1: return CreateDocOnComponentsLocator.locate();
		default: return CreateDocumentBasisOfLocator.locate();
		}
	}

}
