/*
 * DocumentProcessorServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.document.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.document.CreateDocumentBasisOfCallback;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.document.DocumentProcessorServiceLocal;

/**
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentProcessorServiceBean.java,v 1.8 2009/02/04 09:41:44 safonov Exp $
 */
@Stateful(name="merp/document/DocumentProcessorService")
public class DocumentProcessorServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean implements DocumentProcessorServiceLocal {

	private DocumentProcessorImpl delegate = new DocumentProcessorImpl();
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public int[] initDocumentOnBasisOfSeveral() throws ApplicationException {
		return delegate.initDocumentOnBasisOfSeveral();
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @param srcDocSec
	 * @param srcKeys
	 * @param dstDocSec
	 * @param modelId
	 * @return
	 * @throws ApplicationException
	 */
	public int createDocumentOnBasisOfSeveral(int srcDocSec, int[] srcKeys, int dstDocSec, int modelId) throws ApplicationException {
		return delegate.createDocumentOnBasisOfSeveral(srcDocSec, srcKeys, dstDocSec, modelId);
	}

	@PermitAll
	@Remove
	public void processCreateDocumentBasisOf(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener) {
		delegate.processCreateDocumentBasisOf(params, listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.DocumentProcessor#processCreateDocumentBasisOf(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.document.CreateDocumentDocFlowListener, com.mg.merp.document.CreateDocumentBasisOfCallback)
	 */
	@PermitAll
	@Remove
	public void processCreateDocumentBasisOf(DocFlowPluginInvokeParams params,
			CreateDocumentDocFlowListener listener,
			CreateDocumentBasisOfCallback createCallback) {
		delegate.processCreateDocumentBasisOf(params, listener, createCallback);
	}

	@PermitAll
	@Remove
	public void rollbackCreateDocumentBasisOf(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener) {
		delegate.rollbackCreateDocumentBasisOf(params, listener);
	}

	@PermitAll
	@Remove
	public void processCreateDocOnComponents(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener) {
		delegate.processCreateDocOnComponents(params, listener);
	}

	@PermitAll
	@Remove
	public void rollbackCreateDocOnComponents(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener) {
		delegate.rollbackCreateDocOnComponents(params, listener);
	}

}
