/*
 * XMLMenuLoader.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.support.ui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Загрузчик меню, обработка загрузки происходит в классе наследнике {@link #XMLMenuLoader.AbstractHandler}
 * 
 * @author Oleg V. Safonov
 * @version $Id: XMLMenuLoader.java,v 1.4 2008/04/09 14:34:01 safonov Exp $
 */
public class XMLMenuLoader {
	/**
	 * Ресурс хранящий глобальное меню поставляемое основным вендором продукта 
	 */
	private static final String GLOBAL_MENU = "/conf/GlobalMenu.xml"; //$NON-NLS-1$
	
	/**
	 * Наименование тэга действия
	 */
	private static final String ACTION = "action"; //$NON-NLS-1$
	
	/**
	 * Наименование тэга текста элемента
	 */
	private static final String TEXT = "text"; //$NON-NLS-1$
	
	/**
	 * Наименование тэга элемента меню
	 */
	private static final String MENU_ITEM = "menuItem"; //$NON-NLS-1$
	
	/**
	 * Наименование тэга меню
	 */
	private static final String MENU = "menu"; //$NON-NLS-1$
	
	/**
	 * Наименование тэга разделителя
	 */
	private static final String SEPARATOR = "separator"; //$NON-NLS-1$
	
	/**
	 * Атрибут наименования элемента
	 */
	private static final String NAME_ATTR = "name"; //$NON-NLS-1$

	/**
	 * Локаль по умолчанию
	 */
	private static final String DEFAULT_LOCALE = "ru_RU"; //$NON-NLS-1$ не очень понятно какой язык брать по умолчанию

	/**
	 * Наименование тэга параметра действия
	 */
	private static final String ACTION_PARAM = "param"; //$NON-NLS-1$
	
	/**
	 * Атрибут наименования класса реализующего действие
	 */
	private static final String ACTION_CODE = "code"; //$NON-NLS-1$

	private static Logger logger = ServerUtils.getLogger(XMLMenuLoader.class);
	private URL descriptor;
	private SAXReader parser;
	private Stack<Object> menus = new Stack<Object>();
	private Object currentMenuItem = null;
	private String localeStr = DEFAULT_LOCALE;
	private Object rootItem = null;

	public XMLMenuLoader(AbstractHandler handler, URL descriptor, Locale locale) {
		handler.loader = this;
		
		if (descriptor != null)
			this.descriptor = descriptor;
		else
			try {
				this.descriptor = new File(ServerUtils.MBSA_CUSTOM_LOCATION.concat(GLOBAL_MENU)).toURI().toURL();
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException("Menu descriptor not found", e); //$NON-NLS-1$
			}
		
		if (this.descriptor == null)
			throw new IllegalArgumentException("Menu descriptor is null"); //$NON-NLS-1$
		
		if (locale != null)
			localeStr = locale.toString();
		
		parser = new SAXReader();
		parser.setDefaultHandler(handler);
	}
	
	private void parse() {
		try {
			parser.read(descriptor);
		} catch (DocumentException e) {
			throw new ApplicationException(e);
		}
	}
	
	private Object getRootItem() {
		return rootItem;
	}
	
	public static Object build(AbstractHandler handler, URL descriptor, Locale locale) {
		XMLMenuLoader loader = new XMLMenuLoader(handler, descriptor, locale);
		loader.parse();
		return loader.getRootItem();
	}
	
	/**
	 * @author Oleg V. Safonov
	 * @version $Id: XMLMenuLoader.java,v 1.4 2008/04/09 14:34:01 safonov Exp $
	 */
	public static abstract class AbstractHandler implements ElementHandler {
		private XMLMenuLoader loader;

		/**
		 * Создает меню
		 * 
		 * @return	меню
		 */
		abstract protected Object createMenu();
		
		/**
		 * Создает элемент меню
		 * 
		 * @return	элемент меню
		 */
		abstract protected Object createMenuItem();
		
		/**
		 * Устанавливает текст пункта меню
		 * 
		 * @param menuItem	элемент меню
		 * @param text		текст
		 */
		abstract protected void adjustText(Object menuItem, String text);
		
		/**
		 * Устанавливает действие для пункта меню
		 * 
		 * @param menuItem	элемент меню
		 * @param action	действие
		 */
		abstract protected void adjustAction(Object menuItem, MenuCommand action);
		
		/**
		 * Установка дополнительных свойств
		 * 
		 * @param menuItem	элемент меню
		 * @param element	элемент XML документа соответсвующий элементу меню
		 */
		abstract protected void adjustProperties(Object menuItem, Element element);
		
		/**
		 * Добавляет элемент меню в меню
		 * 
		 * @param menu		меню
		 * @param menuItem	элемент меню
		 */
		abstract protected void addMenuItem(Object menu, Object menuItem);
		
		/**
		 * Добавляет разделитель в меню
		 * 
		 * @param menu	меню
		 */
		abstract protected void addMenuSeparator(Object menu);
		
		private void parseMenu(Element element) {
			Object menu = createMenu();
			if (loader.menus.size() != 0)
				addMenuItem(loader.menus.peek(), menu);
			loader.menus.push(menu);
			
			//установим самый первый элемент меню
			if (loader.rootItem == null)
				loader.rootItem = menu;
		}
		
		private void parseMenuItem(Element element) {
			if (element.attributeValue(NAME_ATTR).equals(SEPARATOR)) {
				addMenuSeparator(loader.menus.peek());
				return;
			}
			
			Object menuItem = createMenuItem();
			adjustProperties(menuItem, element);
			addMenuItem(loader.menus.peek(), menuItem);
			loader.currentMenuItem = menuItem;
			
			//установим самый первый элемент меню
			if (loader.rootItem == null)
				loader.rootItem = menuItem;
		}
		
		private void parseText(Element element) {
			String text;
			Element captionTextElement = (Element) element.selectSingleNode(loader.localeStr);
			if (captionTextElement != null)
				text = captionTextElement.getStringValue(); //меню локализовано в описателе
			else {
				Element parent = element.getParent();
				text = parent != null ? parent.attributeValue("name") : element.getName();
			}
			
			if (loader.currentMenuItem == null)
				adjustText(loader.menus.peek(), text);
			else
				adjustText(loader.currentMenuItem, text);
		}
		
		private void parseAction(Element element) {
			//действие можно установить только для конечных элементов
			if (loader.currentMenuItem == null)
				return;
			
			try {
				MenuCommand action = (MenuCommand) ServerUtils.loadClass(element.attributeValue(ACTION_CODE, "")).newInstance(); //$NON-NLS-1$
				List<Element> list = MiscUtils.convertUncheckedList(Element.class, element.elements(ACTION_PARAM));
				Map<String, String> params = new HashMap<String, String>();
				for (Element param : list)
					params.put(param.attributeValue(NAME_ATTR), param.getStringValue());
				action.init(params);
				adjustAction(loader.currentMenuItem, action);			
			} catch (Throwable e) {
				logger.error("Create menu action failed", e); //$NON-NLS-1$
			}
		}
		
		/* (non-Javadoc)
		 * @see org.dom4j.ElementHandler#onStart(org.dom4j.ElementPath)
		 */
		public final void onStart(ElementPath path) {
			Element element = path.getCurrent();
			if (element.getName().equals(MENU))
				parseMenu(element);
			else if (element.getName().equals(MENU_ITEM))
				parseMenuItem(element);
		}

		/* (non-Javadoc)
		 * @see org.dom4j.ElementHandler#onEnd(org.dom4j.ElementPath)
		 */
		public final void onEnd(ElementPath path) {
			Element element = path.getCurrent();
			if (element.getName().equals(MENU)) {
				adjustProperties(loader.menus.peek(), element);
				loader.menus.pop();
			}
			else if (element.getName().equals(MENU_ITEM)) {
				adjustProperties(loader.currentMenuItem, element);
				loader.currentMenuItem = null;
			}
			else if (element.getName().equals(TEXT))
				parseText(element);
			else if (element.getName().equals(ACTION))
				parseAction(element);
		}

	}

}
