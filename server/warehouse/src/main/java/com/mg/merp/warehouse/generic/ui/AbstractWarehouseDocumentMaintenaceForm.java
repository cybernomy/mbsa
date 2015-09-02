/**
 * AbstractWarehouseDocumentMaintenaceForm.java.java
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
package com.mg.merp.warehouse.generic.ui;

import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.merp.discount.ApplyDiscountListener;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.warehouse.DiscountDocument;

/**
 * Базовая реализация контроллера формы поддержки товарных документов
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractWarehouseDocumentMaintenaceForm.java,v 1.4 2009/01/22 06:53:18 sharapov Exp $
 */
public abstract class AbstractWarehouseDocumentMaintenaceForm extends
		GoodsDocumentMaintenanceForm {

	public AbstractWarehouseDocumentMaintenaceForm() throws Exception {
		super();
	}

	private void setEnabledApplyDiscountButton(boolean enabled) {
		Widget applyDiscountButton = view.getWidget("ApplyDiscount");
		if (applyDiscountButton != null)
			applyDiscountButton.setEnabled(enabled);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnEdit()
	 */
	@Override
	protected void doOnEdit() {
		super.doOnEdit();
		setEnabledApplyDiscountButton(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnView()
	 */
	@Override
	protected void doOnView() {
		super.doOnView();
		setEnabledApplyDiscountButton(false);
	}

	/**
	 * реализация применения скидок/наценок
	 */
	protected void doApplyDiscount() {
		DocHead docHead = (DocHead) getEntity();
		if (docHead.getId() == null) //не обрабатываем не созданную запись
			return;
		
		((DiscountDocument) getService()).applyDiscount(docHead, new ApplyDiscountListener() {

			/* (non-Javadoc)
			 * @see com.mg.merp.discount.ApplyDiscountListener#aborted()
			 */
			public void aborted() {
				// do nothing
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.discount.ApplyDiscountListener#completed()
			 */
			public void completed() {
				refreshModel();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#setSpecificationEditable()
	 */
	@Override
	public void setSpecificationEditable() {
		super.setSpecificationEditable();
		setEnabledApplyDiscountButton(true);
	}

	/**
	 * обработчик события "Применить скидку/наценку"
	 * 
	 * @param event
	 */
	public void onActionApplyDiscount(WidgetEvent event) {
		doApplyDiscount();
	}
	
}
