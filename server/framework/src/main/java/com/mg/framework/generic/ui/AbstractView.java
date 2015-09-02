/*
 * AbstractView.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.generic.ui;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.ControllableWidget;
import com.mg.framework.api.ui.Controller;
import com.mg.framework.api.ui.FieldEditor;
import com.mg.framework.api.ui.UIProfile;
import com.mg.framework.api.ui.View;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetFactory;
import com.mg.framework.api.ui.Window;
import com.mg.framework.api.ui.widget.BoxPane;
import com.mg.framework.service.UIProfileManagerLocator;
import com.mg.framework.support.ui.UIRender;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.WidgetFactoryFactory;
import com.mg.framework.utils.StringUtils;

/**
 * Реализация вида
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractView.java,v 1.16 2008/01/10 08:53:28 safonov Exp $
 */
public class AbstractView implements View, Serializable {    
	private boolean modal = false;
	private Window window = null;
	private boolean pack = false;
	private boolean initialized = false;
	protected Controller controller;
	protected BoxPane contentPane;
    protected Map<String, Widget> componentMap = new HashMap<String, Widget>();
    protected Element rootElement;
	protected String viewTitle;
	private boolean visible;
	private String helpTopic;
	private String viewName;
	private UIProfile profile;

	public AbstractView(Controller controller, String viewName) {
		this.controller = controller;
		this.viewName = viewName;
	}
	
	private void initWidgets() {
		doInitWidgets();
	}
	
	private void initListeners() {
		doInitListeners();
	}

	private boolean isEditor(Widget widget) {
		return (widget instanceof FieldEditor);
	}

	private boolean isControllableWidget(Widget widget) {
		return (widget instanceof ControllableWidget);
	}
	
	protected void doInitWidgets() {
		contentPane = (BoxPane) WidgetFactoryFactory.getInstance().getDefaultWidgetFactory().createWidget(WidgetFactory.BOX_LAYOUT, StringUtils.EMPTY_STRING, null, this);
		contentPane.init(rootElement);
	}

	protected void doInitListeners() {
		
	}
    

	protected void initWidgets(Element rootElement) {
		if (initialized)
			return;
		initialized = true;
		
		initWidgets();
		initListeners();
		UIRender.produce(rootElement, this, contentPane, componentMap);
	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	protected void fillWidget(Widget widget, String fieldName) {
		Object value = null;
		try {
			value = controller.getFieldValue(fieldName);
		} catch (NoSuchFieldException e) {
			getLogger().error("Could not get form field: ".concat(fieldName), e);
			return;
		} catch (IllegalArgumentException e) {
			getLogger().error("Could not get form field: ".concat(fieldName), e);
		} catch (IllegalAccessException e) {
			getLogger().error("Could not get form field: ".concat(fieldName), e);
			return;
		}
		
		try {
			if (widget instanceof FieldEditor)
				((FieldEditor) widget).setEditorValue(value);
			else if (widget instanceof ControllableWidget)
				((ControllableWidget) widget).setAdapter(value);
		} catch (RuntimeException e) {
			getLogger().debug("Could not set editor value for field: ".concat(fieldName), e);
			throw e;
		}
	}
	
	protected void fillField(Widget component, String fieldName) {
		Object value;
		try {
			value = ((FieldEditor) component).getEditorValue();
			
		} catch (RuntimeException e) {
			getLogger().debug("Could not get editor value for field: ".concat(fieldName), e);
			throw e;
		}
		
		try {
			controller.setFieldValue(fieldName, value);
		} catch (NoSuchFieldException e) {
			getLogger().error("Could not set form field: ".concat(fieldName), e);
		} catch (IllegalArgumentException e) {
			getLogger().error("Could not set form field: ".concat(fieldName), e);
		} catch (IllegalAccessException e) {
			getLogger().error("Could not set form field: ".concat(fieldName), e);
		}
	}

	protected void fillFields() {
		for (Map.Entry<String, Widget> entry : componentMap.entrySet()) {
			Widget widget = entry.getValue();
			if (isEditor(widget))
				fillField(widget, entry.getKey());
		}
	}
	
	protected void fillWidgets(boolean fillControllable) {
		for (Map.Entry<String, Widget> entry : componentMap.entrySet()) {
			Widget widget = entry.getValue();
			if (isEditor(widget) || fillControllable && isControllableWidget(widget))
				fillWidget(widget, entry.getKey());
		}
	}
	
	protected void doShow() {
		//возможно форма уже показывалась и создание не требуется
		if (window == null) {
			String implName = isModal() || UIUtils.isConversation() ? WidgetFactory.DIALOG_WINDOW : WidgetFactory.INTERNAL_FRAME_WINDOW;
			window = (Window) WidgetFactoryFactory.getInstance().getDefaultWidgetFactory().createWidget(implName, StringUtils.EMPTY_STRING, null, this);
	        if (pack)
	        	window.pack();
	        ((Widget) window).init(rootElement);
	        window.setTitle(viewTitle);
	        window.add(contentPane);
		}
    	
        fillWidgets(true);

        window.show();        
    }
    
    protected void doClose() {
		if (window != null) {
			for (Widget widget : componentMap.values())
				widget.dispose();
			window.close();
			window = null;
			if (profile != null)
				UIProfileManagerLocator.locate().store(profile);
			profile = null;
		}
    }
    
	protected boolean isModal() {
		return modal;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#getLogger()
	 */
	public Logger getLogger() {
		return controller.getLogger();
	}
	
	protected Element doInitView(Element rootElement) {
		helpTopic = rootElement.attributeValue(Widget.HELP_TOPIC);
		viewTitle = UIUtils.loadL10nText(rootElement.attributeValue(Window.TITLE));
		return rootElement;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#flushForm()
	 */
	public void flushForm() {
		fillFields();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#flushModel()
	 */
	public void flushModel() {
		fillWidgets(false);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#show(boolean)
	 */
	public void show(boolean modal) {
		this.modal = modal;

		initWidgets(rootElement);
		
		doShow();
		
		visible = true;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#close()
	 */
	public void close() {
		doClose();
		
		visible = false;
		//help GC
//		componentMap.clear();
//		componentMap = null;
//		controller = null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		viewTitle = title;
		//если уже установлена реализация вида, то установим у элемента реализации
		if (window != null)
			window.setTitle(title);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#getTitle()
	 */
	public String getTitle() {
		return viewTitle;
	}

	/**
	 * initialize view by XML element
	 * 
	 * @param rootElement	XML root element
	 */
	public void initView(Element rootElement) {
		this.rootElement = doInitView(rootElement);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#invokeHandler(java.lang.String, java.lang.Object...)
	 */
	public Object invokeHandler(String handlerName, Object... args) throws Throwable {
		try {
			//заполним поля контроллера перед вызовом обработчика
			fillFields();
			Object result = controller.invokeHandler(handlerName, args);
			//заполним поля вида, если не была выполнена операция закрытия формы
			if (controller != null)
				fillWidgets(false);
			return result;
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#getController()
	 */
	public Controller getController() {
		return controller;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#getWidget(java.lang.String)
	 */
	public Widget getWidget(String name) {
		return componentMap.get(name);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#getWidgets()
	 */
	public Widget[] getWidgets() {
		Collection<Widget> widges = componentMap.values();
		return widges.toArray(new Widget[widges.size()]);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#getWindow()
	 */
	public Window getWindow() {
		return window;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#dispose()
	 */
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#pack()
	 */
	public void pack() {
		pack = true;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#isVisible()
	 */
	public boolean isVisible() {
		return visible;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#getHelpTopic()
	 */
	public String getHelpTopic() {
		return helpTopic;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#getName()
	 */
	public String getName() {
		return viewName;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.View#getUIProfile()
	 */
	public UIProfile getUIProfile() {
		if (profile == null && viewName != null)
			profile = UIProfileManagerLocator.locate().load(viewName);
		return profile;
	}

}
