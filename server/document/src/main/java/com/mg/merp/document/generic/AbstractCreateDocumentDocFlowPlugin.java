/**
 * AbstractCreateDocumentDocFlowPlugin.java
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
package com.mg.merp.document.generic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.docflow.DocumentNotFound;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.document.DocumentNotCreated;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.document.support.Messages;

/**
 * Базовый класс подключаемого модуля документооборота для создания документов на основании другого
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractCreateDocumentDocFlowPlugin.java,v 1.2 2008/12/29 09:54:49 safonov Exp $
 */
public abstract class AbstractCreateDocumentDocFlowPlugin extends AbstractDocFlowPlugin {

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doGetDocActionResultTextRepresentation(com.mg.merp.docprocess.model.DocHeadState)
	 */
	@Override
	protected String doGetDocActionResultTextRepresentation(
			DocHeadState docHeadState) {
		List<Object[]> list = MiscUtils.convertUncheckedList(Object[].class, OrmTemplate.getInstance().findByNamedParam("select dh.DocSection.DSName, dh.DocType.Code, dh.DocNumber, dh.DocDate, dh.SumCur, dh.Currency.Code from DocHead dh where dh.Id = :docHeadId", "docHeadId", docHeadState.getData2()));
		if (list.size() == 0)
			return StringUtils.BLANK_STRING;
		Object[] docHead = list.get(0);
		return StringUtils.format(Messages.getInstance().getMessage(Messages.DOCUMENT_TITLE), docHead[0], ((String) docHead[1]).trim(), ((String) docHead[2]).trim(), SimpleDateFormat.getDateInstance(DateFormat.MEDIUM, ServerUtils.getUserLocale()).format(docHead[3]), docHead[4], ((String) docHead[5]).trim());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doShowDocActionResult(com.mg.merp.docprocess.model.DocHeadState)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doShowDocActionResult(DocHeadState docHeadState) {
		if (docHeadState.getData1() == null || docHeadState.getData2() == null)
			throw new DocumentNotCreated();
		
		DataBusinessObjectService<?, Integer> docService = DocumentUtils.getDocumentService(ServerUtils.getPersistentManager().find(DocSection.class, docHeadState.getData1()));
		Object docHead = docService.load(docHeadState.getData2());
		if (docHead == null)
			throw new DocumentNotFound();
		
		MaintenanceHelper.view(docService, docHeadState.getData2(), null, null);
	}

}
