/**
 * CustomActionManagerImpl.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.Logger;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.CustomActionDescriptor;
import com.mg.framework.api.ui.CustomActionExecutionContext;
import com.mg.framework.api.ui.CustomActionListener;
import com.mg.framework.api.ui.CustomActionManager;
import com.mg.framework.api.ui.WidgetFactory;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.baiengine.model.CustomUserAction;

/**
 * Реализация менеджера настраиваемых действий
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionManagerImpl.java,v 1.2 2007/11/15 14:05:45 safonov Exp $
 */
public class CustomActionManagerImpl implements CustomActionManager {
	private final static String ACTION_WIDGET_PREFIX = "mg.jet.ui.CustomAction.";
	private Logger logger = ServerUtils.getLogger(CustomActionManagerImpl.class);
	
	/**
	 * загрузка списка настраиваемых действие для бизнес-компонента
	 * 
	 * @param classId	идентификатор бизнес-компонента
	 * @return	список доступных действий
	 */
	private List<CustomUserAction> loadUserActions(int classId) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CustomUserAction.class, "cua")
				.createAlias("cua.Permissions", "perm")
				.add(Restrictions.eq("cua.SysClass.Id", classId))
				.add(Restrictions.eq("Active", true))
				.add(Restrictions.in("perm.SecGroup.Id", (Object[]) ArrayUtils.toObject(ServerUtils.getUserProfile().getGroups())))
				.addOrder(Order.asc("cua.Priority"))
				.setFlushMode(FlushMode.MANUAL));
	}
	
	/**
	 * загрузка действия по коду
	 * 
	 * @param code	код действия
	 * @return	действие или <code>null</code> если не найдено или отсутствуют права
	 */
	private CustomUserAction loadUserAction(String code) {
		return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(CustomUserAction.class, "cua")
				.createAlias("cua.Permissions", "perm")
				.add(Restrictions.eq("Code", code))
				.add(Restrictions.eq("Active", true))
				.add(Restrictions.in("perm.SecGroup.Id", (Object[]) ArrayUtils.toObject(ServerUtils.getUserProfile().getGroups())))
				.setFlushMode(FlushMode.MANUAL));
	}
	
	/**
	 * создание разделителя
	 * 
	 * @param formlet		формлет
	 * @param actionName	имя действия
	 * @param isBefore	признак размещения разделителя
	 */
	private void createSeparator(StringBuilder formlet, String actionName, boolean isBefore) {
		formlet.append("<").append(WidgetFactory.SEPARATOR_WIDGET)
				.append(" id=\"separator").append(isBefore ? "Before" : "After").append(actionName).append("\" />");
	}
	
	/**
	 * реализация генерации формлета
	 * 
	 * @param service	бизнес-компонент
	 * @return	формлет
	 */
	private String doGenerateActionsArea(BusinessObjectService service) {
		if (service == null)
			throw new IllegalArgumentException("service is null");
		
		List<CustomUserAction> actions = loadUserActions(service.getBusinessServiceMetadata().getIdentificator());
		if (actions.isEmpty()) {
			logger.debug("list of custom actions is empty");
			return null;
		}
		
		StringBuilder result = new StringBuilder("<jfd:wrap-macros xmlns:jfd=\"http://xmlns.m-g.ru/jet/ui\">");
		for (CustomUserAction action : actions) {
			if (!action.isFromToolbar())
				continue;

			String code = action.getCode().trim();

			if (action.isSeparatorBefore())
				createSeparator(result, code, true);
			
			result.append("<jfd:button id=\"").append(ACTION_WIDGET_PREFIX).append(code).append("\" ")
					.append("actionListener=\"").append(CUSTOM_ACTION_LISTENER_NAME).append("\" ")
					.append("actionCommand=\"").append(code).append("\" ")
					.append("toolTip=\"").append(action.getHint()).append("\" ");
			if (StringUtils.stringNullOrEmpty(action.getIcon()))
				result.append("text=\"").append(action.getCaption()).append("\" ");
			else
				result.append("icon=\"").append(action.getIcon()).append("\" ");
			result.append("/>");
			
			if (action.isSeparatorAfter())
				createSeparator(result, code, false);
		}
		result.append("</jfd:wrap-macros>");
		if (logger.isDebugEnabled())
			logger.debug("generate custom user actions formlet: " + result.toString());
		return result.toString();
	}
	
	/**
	 * реализация выполнения действия
	 * 
	 * @param context	контекст выполнения
	 */
	private void doExecuteAction(final CustomActionExecutionContext context) {
		final CustomUserAction action = loadUserAction(context.getAction());
		if (action == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.CUSTOM_USER_ACTION_NOT_FOUND, new Object[] {context.getAction()}));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CustomActionBusinessAddin.SERVICE_PARAM_NAME, context.getService());
		params.put(CustomActionBusinessAddin.IDENTIFIERS_PARAM_NAME, context.getSelectedIdentifiers());
		BusinessAddinEngineLocator.locate().perform(action.getBAi(), params, new BusinessAddinListener<Void>() {

			public void aborted(BusinessAddinEvent<Void> event) {
				for (CustomActionListener listener : context.getListeners())
					listener.aborted();
			}

			public void completed(BusinessAddinEvent<Void> event) {
				for (CustomActionListener listener : context.getListeners())
					listener.completed(action.isForceRefresh());
			}
			
		});
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionManager#executeAction(com.mg.framework.api.ui.CustomActionExecutionContext)
	 */
	public void executeAction(CustomActionExecutionContext context) {
		doExecuteAction(context);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionManager#generateActionsArea(com.mg.framework.api.DataBusinessObjectService)
	 */
	public String generateActionsArea(BusinessObjectService service) {
		return doGenerateActionsArea(service);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionManager#getCustomActions(com.mg.framework.api.BusinessObjectService)
	 */
	public CustomActionDescriptor[] getCustomActions(BusinessObjectService service) {
		if (service == null)
			throw new IllegalArgumentException("service is null");
		
		List<CustomUserAction> actions = loadUserActions(service.getBusinessServiceMetadata().getIdentificator());
		if (actions.isEmpty()) {
			logger.debug("list of custom actions is empty");
			return null;
		}
		CustomActionDescriptor[] result = new CustomActionDescriptor[actions.size()];
		for (int i = 0; i < actions.size(); i++)
			result[i] = new CustomActionDescriptorImpl(actions.get(i));
		return result;
	}

}
