/*
 * UIProducer.java
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
package com.mg.framework.support.ui;

import java.io.StringReader;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.Controller;
import com.mg.framework.api.ui.Form;
import com.mg.framework.api.ui.RuntimeMacrosLoader;
import com.mg.framework.api.ui.View;
import com.mg.framework.generic.ui.AbstractView;
import com.mg.framework.support.Messages;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Фабрика форм пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIProducer.java,v 1.9 2007/03/13 13:32:39 safonov Exp $
 */
public class UIProducer {
	private final static Logger logger = ServerUtils.getLogger(UIProducer.class);
	private final static String NODE_TYPE = "type";
	private final static String CONTROLLER_NAME = "controller";
	private final static String FORM_NAME = "name";
	private final static String EXTENDS_NAME = "extends";
	private final static String INCLUDE_TAG_NAME = "jfd:include";
	private final static String EMPTY_MACROS = "jfd:empty-macros";
	private final static String WRAP_MACROS = "jfd:wrap-macros";
	private final static String MACROS_NAME_ATTR = "name";
	private final static String RUNTIME_MACROS_NAME_ATTR = "runtime";
	
	/**
	 * создает форму по наименованию описателя, описатель формы должен быть доступен текущему загрузчику
	 * ресурсов, если описатель не нейден будет сгенерирована ИС.
	 * 
	 * @param formName	имя формы
	 * @return	форма
	 */
	public static Form produceForm(String formName) {
		return doProduceForm(loadFormDescription(formName), formName, null);
	}
	
	/**
	 * создает форму по наименованию описателя, используется генератор макросов, описатель формы должен быть доступен текущему загрузчику
	 * ресурсов, если описатель не нейден будет сгенерирована ИС.
	 * 
	 * @see RuntimeMacrosLoader
	 * 
	 * @param formName				имя формы
	 * @param runtimeMacrosLoader	генератор динамических макросов
	 * @return	форма
	 */
	public static Form produceForm(String formName, RuntimeMacrosLoader runtimeMacrosLoader) {
		return doProduceForm(loadFormDescription(formName), formName, runtimeMacrosLoader);
	}
	
	/**
	 * создает форму по содежимому, строка должна представлять из себя стандартный описатель форм
	 * 
	 * @param body	тело описателя
	 * @return	форма
	 */
	public static Form produceFormFromString(String body) {
		if (body == null)
			throw new IllegalArgumentException("Form is null");
		
		return doProduceFormFromString(body, null);
	}
	
	private static Form doProduceFormFromString(String body, RuntimeMacrosLoader runtimeMacrosLoader) {
		try {
			return parseFormDescription(DocumentHelper.parseText(body), null, runtimeMacrosLoader);
		} catch (DocumentException e) {
			throw new ApplicationException(e);
		}
	}
	
	private static Form doProduceForm(URL url, String formName, RuntimeMacrosLoader runtimeMacrosLoader) {
		if (url == null)
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.FORM_DESCRIPTOR_NOT_FOUND, new Object[] {formName}));
		
		SAXReader reader = new SAXReader();
		
		try {
			return parseFormDescription(reader.read(url), formName, runtimeMacrosLoader);
		} catch (DocumentException e) {
			throw new ApplicationException(e);
		}
	}
	
	private static URL loadFormDescription(String formName) {
		return Thread.currentThread().getContextClassLoader().getResource(formName); //$NON-NLS-1$
	}
	
	private static Form parseFormDescription(Document document, String formName, RuntimeMacrosLoader runtimeMacrosLoader) {
		Element root = performDocument(document, runtimeMacrosLoader).getRootElement();
		
		String controllerName = root.attributeValue(CONTROLLER_NAME);
		//пытаемся прочитать имя формы из описателя
		if (formName == null)
			formName = root.attributeValue(FORM_NAME);
		
		Controller formController;
		try {
			formController = (Controller) ServerUtils.loadClass(controllerName).newInstance();
		}
		catch (ClassNotFoundException e) {
			throw new ApplicationException("Form controller not found", e);
		}
		catch (InstantiationException e) {
			throw new ApplicationException(e);
		}
		catch (IllegalAccessException e) {
			throw new ApplicationException(e);
		}
		
		View view = createView(formController, formName);
		
		formController.setView(view);
		
		((AbstractView) view).initView(root);
		return (Form) formController;
	}
	
	public static Document performDocument(Document document, RuntimeMacrosLoader runtimeMacrosLoader) {
		logger.debug("Original form descriptor:\n".concat(document.asXML()));
		Document result = DocumentFactory.getInstance().createDocument(document.getXMLEncoding());
		result.setDocType(document.getDocType());
		result.setRootElement(copyElement(document.getRootElement(), runtimeMacrosLoader));
		result.getRootElement().addNamespace(document.getRootElement().getNamespacePrefix(), document.getRootElement().getNamespaceURI());
		try {
			//пересоздаем документ через строку, чтобы устранить проблемы с namespaces
			result = DocumentHelper.parseText(result.asXML());
			logger.debug("Finished form descriptor:\n".concat(result.asXML()));
			return result;
		} catch (DocumentException e) {
			throw new ApplicationException(e);
		}
	}
	
	private static Element includeMacros(Element parentElement, Element element, RuntimeMacrosLoader runtimeMacrosLoader) {
		Document macros = null;
		String runtimeMacros = element.attributeValue(RUNTIME_MACROS_NAME_ATTR);
		if (runtimeMacros == null)
			macros = loadMacros(element.attributeValue(MACROS_NAME_ATTR));
		else
			macros = loadRuntimeMacros(runtimeMacros, runtimeMacrosLoader);
		String macrosType = macros.getRootElement().getQualifiedName();
		if (EMPTY_MACROS.equals(macrosType))
			return null; //handle special case for empty macros
		else if (WRAP_MACROS.equals(macrosType)) {
			//copy macros contents exclude root element
			List<Element> macrosChildElements = MiscUtils.convertUncheckedList(Element.class, macros.getRootElement().elements());
			for (Element macrosChild : macrosChildElements) {
//				if (INCLUDE_TAG_NAME.equals(macrosChild.getQualifiedName()))
//					includeMacros(parentElement, macrosChild, runtimeMacrosLoader);
//				else {
				Element macrosElement = copyElement(macrosChild, runtimeMacrosLoader);
				if (macrosElement != null)
					parentElement.add(macrosElement);
//				}
			}
			return null;
		}
		else {
			if (INCLUDE_TAG_NAME.equals(macros.getRootElement().getQualifiedName()))
				return includeMacros(parentElement, macros.getRootElement(), runtimeMacrosLoader);
			return copyElement(macros.getRootElement(), runtimeMacrosLoader); //copy root element			
		}
	}
	
	private static Element copyElement(Element element, RuntimeMacrosLoader runtimeMacrosLoader) {
		//if (INCLUDE_TAG_NAME.equals(element.getQualifiedName()))
			//return includeMacros();
		
		Element result = DocumentHelper.createElement(element.getQualifiedName());
		result.setAttributes(element.attributes());
		List<Element> childElements = MiscUtils.convertUncheckedList(Element.class, element.elements());
		for (Element childElement : childElements) {
			Element copy;
			if (childElement.getQualifiedName().equals(INCLUDE_TAG_NAME)) {
				copy = includeMacros(result, childElement, runtimeMacrosLoader);
				/*Document macros = null;
				String runtimeMacros = childElement.attributeValue(RUNTIME_MACROS_NAME_ATTR);
				if (runtimeMacros == null)
					macros = loadMacros(childElement.attributeValue(MACROS_NAME_ATTR));
				else
					macros = loadRuntimeMacros(runtimeMacros, runtimeMacrosLoader);
				String macrosType = macros.getRootElement().getQualifiedName();
				if (macrosType.equals(EMPTY_MACROS))
					copy = null; //handle special case for empty macros
				else if (macrosType.equals(WRAP_MACROS)) {
					//copy macros contents exclude root element
					List<Element> macrosChildElements = MiscUtils.convertUncheckedList(Element.class, macros.getRootElement().elements());
					for (Element macrosChild : macrosChildElements) {
						Element macrosElement = copyElement(macrosChild, runtimeMacrosLoader);
						if (macrosElement != null)
							result.add(macrosElement);
					}
					copy = null;
				}
				else
					copy = copyElement(macros.getRootElement(), runtimeMacrosLoader); //copy root element
*/			}
			else {
				copy = copyElement(childElement, runtimeMacrosLoader);
			}
			if (copy != null)
				result.add(copy);
		}
		return result;
	}
	
	public static Document loadMacros(String name) {
		try {
			URL macros = loadFormDescription(name);
			if (macros == null)
				throw new ApplicationException(String.format("Macros %s not found", name));
			return new SAXReader().read(macros);
		} catch (DocumentException e) {
			throw new ApplicationException(e);
		}
	}
	
	public static Document loadRuntimeMacros(String name, RuntimeMacrosLoader runtimeMacrosLoader) {
		try {
			String macros = null;
			if (runtimeMacrosLoader != null)
				macros = runtimeMacrosLoader.loadMacros(name);
			//если не загрузили макрос, то сделаем его пустым
			if (macros == null)
				macros = "<jfd:empty-macros xmlns:jfd=\"http://xmlns.m-g.ru/jet/ui\" />";
			return new SAXReader().read(new StringReader(macros));
		} catch (DocumentException e) {
			throw new ApplicationException(e);
		}
	}
	
	private static View createView(Controller formController, String formName) {
		return new AbstractView(formController, formName);
	}
	
}
