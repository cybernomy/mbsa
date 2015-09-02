/**
 * CustomActionBusinessAddin.java
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
package com.mg.merp.baiengine.support;

import java.io.Serializable;
import java.util.Map;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;

/**
 * Базовый класс BAi выполнения настраиваемого действия. Класс BAi должен
 * реализовывать следующий метод <code>protected String doPerform() throws Exception</code>.
 * В случае успешного выполнения необходимо вызвать {@link #complete(Void)}, если необходимо
 * прервать выполнение действия необходимо вызвать {@link #abort()}.
 * 
 * 
 * <p>Пример метода {@link #doPerform()}:
 * <pre>
 *  protected void doPerform() throws Exception {
 *  	getLogger().info("Business service: " + getService());
 *  	if (getSelectedIdentifiers() == null)
 *  		getLogger().info("List of selected identifiers is empty");
 *  	else
 *  		for (Serializable id : getSelectedIdentifiers())
 *  			getLogger().info("Id: " + id);
 *  	UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, "Foo", "foo", "Yes", "No", new AlertListener() {
 *  		public void alertClosing(String value) {
 *  			if ("Yes".equals(value))
 *  				complete(null);
 *  			else
 *  			abort();
 *  		}
 *  	});
 *  }
 * </pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionBusinessAddin.java,v 1.1 2007/11/15 09:19:05 safonov Exp $
 */
public abstract class CustomActionBusinessAddin extends AbstractBusinessAddin<Void> {
	//public static final String ACTION_PARAM_NAME = "ACTION_PARAM_NAME";
	public static final String SERVICE_PARAM_NAME = "SERVICE_PARAM_NAME";
	public static final String IDENTIFIERS_PARAM_NAME = "ACTION_PARAM_NAME";
	private BusinessObjectService service;
	private Serializable[] selectedIdentifiers;

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
	 */
	@Override
	protected void extractParams(Map<String, ? extends Object> params) {
		service = (BusinessObjectService) params.get(SERVICE_PARAM_NAME);
		selectedIdentifiers = (Serializable[]) params.get(IDENTIFIERS_PARAM_NAME);
	}

	/**
	 * получить бизнес-копмонент для которого выполняется действие
	 * 
	 * @return the service	бизнес-копмонент
	 */
	protected BusinessObjectService getService() {
		return service;
	}

	/**
	 * получить список идентификаторов сущностей отмеченных пользователем
	 * 
	 * @return the selectedIdentifiers	список идентификаторов
	 */
	protected Serializable[] getSelectedIdentifiers() {
		return selectedIdentifiers;
	}

}
