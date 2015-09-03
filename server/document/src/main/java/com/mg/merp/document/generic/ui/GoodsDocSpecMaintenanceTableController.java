/**
 * GoodsDocSpecMaintenanceTableController.java
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
package com.mg.merp.document.generic.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.Form;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.Document;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.GoodsSelectionEvent;
import com.mg.merp.document.GoodsSelectionListener;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;

/**
 * Адаптер таблицы списка спецификаций отображаемой как дополнительный список спецификаций
 * в основном списке документов
 * 
 * @author Oleg V. Safonov
 * @version $Id: GoodsDocSpecMaintenanceTableController.java,v 1.2 2009/02/17 12:30:18 safonov Exp $
 */
public class GoodsDocSpecMaintenanceTableController extends
		MaintenanceTableController {
	private Document<DocHead, Integer, ?> docService = null;
	private Integer docHeadId = null;

	public GoodsDocSpecMaintenanceTableController(AttributeMap uiProperties) {
		super(uiProperties);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableController#fireMasterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	@Override
	public void fireMasterChange(ModelChangeEvent event) {
		docHeadId = (Integer) event.getModelKey();
		super.fireMasterChange(event);
	}

	@SuppressWarnings("unchecked")
	private DocHead loadDocHead() {
		if (docService == null)
			docService = DocumentUtils.getDocumentService(((GoodsDocumentSpecification) getService()).getDocSection());
		
		return docService.load(docHeadId);
	}

	private boolean checkDocument() {
		if (docHeadId == null)
			return false;
		
		DocHead docHead = loadDocHead();
		if (docHead == null)
			return false;
		
		DocFlowHelper.checkStatus(docHead);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doAdd()
	 */
	@Override
	protected void doAdd() {
		if (!checkDocument())
			return;
		Form goodsSelectionForm = ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.document.GoodsSelectionForm");
		((com.mg.merp.document.GoodsSelectionForm) goodsSelectionForm).execute(new GoodsSelectionListener() {

			@SuppressWarnings("unchecked")
			public void doSelect(GoodsSelectionEvent event) {
				((GoodsDocumentSpecification) getService()).bulkCreate(loadDocHead(), event.getSpecInfo());
				refresh();
			}
			
		}, loadDocHead());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doClone(boolean)
	 */
	@Override
	protected void doClone(boolean deepClone) {
		if (!checkDocument())
			return;
		super.doClone(deepClone);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doEdit()
	 */
	@Override
	protected void doEdit() {
		if (!checkDocument())
			return;
		super.doEdit();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.MaintenanceTableController#doErase()
	 */
	@Override
	protected void doErase() {
		if (!checkDocument())
			return;
		super.doErase();
	}

}
