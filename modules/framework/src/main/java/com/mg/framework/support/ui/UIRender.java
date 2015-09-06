/*
 * UIRender.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.metadata.BuiltInType;
import com.mg.framework.api.metadata.ReflectionMetadata;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.ui.Container;
import com.mg.framework.api.ui.Controller;
import com.mg.framework.api.ui.FieldEditor;
import com.mg.framework.api.ui.View;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetFactory;
import com.mg.framework.api.ui.widget.EnumField;
import com.mg.framework.api.ui.widget.Label;
import com.mg.framework.api.ui.widget.ScrollPane;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.framework.utils.XMLUtils;

import org.dom4j.Element;

import java.util.List;
import java.util.Map;

/**
 * User interface render
 *
 * @author Oleg V. Safonov
 * @version $Id: UIRender.java,v 1.16 2008/07/28 13:37:22 safonov Exp $
 */
public class UIRender {
  /**
   * элемент контейнер
   */
  public static final String CONTAINER_WIDGET = "jfd:container"; //$NON-NLS-1$
  /**
   * элемент поле, решение о типе элемента принимается в зависимости от типа модели
   */
  public static final String FIELD_WIDGET = "jfd:field"; //$NON-NLS-1$
  private static final String SCROLL_PANE = "scrollPane"; //$NON-NLS-1$
  private static final String LABEL_FOR = "labelFor"; //$NON-NLS-1$
  private Map<String, Widget> componentMap;
  private Controller controller;
  private View view;
  private WidgetFactory widgetFactory;

  /**
   * produce form by decriptor
   *
   * @param rootElement  descriptor
   * @param view         view
   * @param contentPane  root pane
   * @param componentMap components map
   */
  public static void produce(Element rootElement, View view, Container contentPane, Map<String, Widget> componentMap) {
    new UIRender().initWidgets(rootElement, view, contentPane, componentMap);
  }

  private void initWidgets(Element rootElement, View view, Container contentPane, Map<String, Widget> componentMap) {
    this.componentMap = componentMap;
    this.view = view;
    this.controller = view.getController();
    widgetFactory = WidgetFactoryFactory.getInstance().getDefaultWidgetFactory();
    List<Element> elements = MiscUtils.convertUncheckedList(Element.class, rootElement.elements());
    for (Element element : elements)
      handleElement(element, contentPane);
  }

  private void handleElement(Element element, Container parent) {
    Widget widget = createWidget(element, parent);
    if (widget instanceof Container) {
      ((Container) widget).startContainer();
      try {
        //обрабатываем контейнер, возможно у него есть вложенные элементы
        List<Element> nestedElements = MiscUtils.convertUncheckedList(Element.class, element.elements());
        for (Element nestedElement : nestedElements) {
          handleElement(nestedElement, (Container) widget);
        }
      } finally {
        ((Container) widget).endContainer();
      }
    } else {

    }
  }

  private Widget createWidget(Element element, Container parent) {
    Widget result = null;
    Label labelFor = null;
    String elementName = element.getQualifiedName();
    String id = element.attributeValue(Widget.WIDGET_ID);

    result = componentMap.get(id);
    if (result != null)
      throw new ApplicationException(Messages.getInstance().getMessage(Messages.DUPLICATE_WIDGET_NAME_ERROR, new Object[]{id}));

    boolean scroll = XMLUtils.checkBoolean(element.attributeValue(ScrollPane.SCROLL), false);

    if (elementName.equals(FIELD_WIDGET)) {
      result = createFieldWidget(id, element);
      labelFor = (Label) componentMap.get(generateLabelForName(id)); //проверим была ли создана метка
    } else if (elementName.equals(CONTAINER_WIDGET)) {
      String layout = element.attributeValue(Container.LAYOUT, StringUtils.EMPTY_STRING);
      result = widgetFactory.createWidget(layout, id, element, view);
      labelFor = createLabelFor(element, id, result);
    } else {
      result = widgetFactory.createWidget(elementName, id, element, view);
      labelFor = createLabelFor(element, id, result);
    }

    result.init(element);

    //видимость метки зависит от элемента
    //см. https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4191
    if (labelFor != null && !result.isVisible())
      labelFor.setVisible(false);

    Widget tmpResult = null;
    if (scroll || result.isScrollable()) {
      tmpResult = result;
      result = widgetFactory.createWidget(WidgetFactory.SCROLL_PANE, generateScrollName(id), null, view);
      ((ScrollPane) result).setViewPortView(tmpResult);
    }

    try {
      parent.add(result, labelFor, element);
    } finally {
      if (tmpResult != null)
        result = tmpResult;
    }

    componentMap.put(id, result);

    return result;
  }

  private Label createLabelFor(Element element, String widgetId, Widget widget) {
    return createLabelFor(element.attributeValue(Widget.LABEL), widgetId, widget);
  }

  private Label createLabelFor(String labelText, String widgetId, Widget widget) {
    if (labelText != null) {
      String labelName = generateLabelForName(widgetId);
      Label result = (Label) widgetFactory.createWidget(WidgetFactory.LABEL_WIDGET, labelName, null, view);
      result.setText(UIUtils.loadL10nText(labelText));
      result.setLabelFor(widget);
      componentMap.put(labelName, result);
      return result;
    } else
      return null;
  }

  private Widget createFieldWidget(String id, Element element) {
    Widget result = null;
    boolean showLabel = XMLUtils.checkBoolean(element.attributeValue(FieldEditor.SHOW_LABEL), true);

    FieldMetadata fldMeta = null;
    //пытаемся загрузить из описателя поля
    String dataItemName = element.attributeValue(FieldEditor.DATA_ITEM);
    if (!StringUtils.stringNullOrEmpty(dataItemName))
      fldMeta = ApplicationDictionaryLocator.locate().getFieldMetadata(new ReflectionMetadata(dataItemName, controller.getFieldType(id)));
    //если не установлен, то грузим стандартно из контроллера
    if (fldMeta == null)
      fldMeta = controller.getFieldMetadata(id);
    if (fldMeta == null)
      throw new IllegalStateException(StringUtils.format("Not found in controller. Field: %s.", id)); //$NON-NLS-1$

    String widgetType = null;
    BuiltInType type = fldMeta.getBuiltInType();
    if (type == null)
      throw new IllegalStateException(StringUtils.format("Type is null. Field: %s.", id)); //$NON-NLS-1$
    switch (type) {
      case SMALLINTEGER:
      case INTEGER:
      case BIGINTEGER:
        widgetType = WidgetFactory.INTEGER_EDIT_WIDGET;
        break;
      case BOOLEAN:
        widgetType = WidgetFactory.CHECKBOX_WIDGET;
        showLabel = false;
        break;
      case STRING:
        widgetType = WidgetFactory.TEXT_EDIT_WIDGET;
        break;
      case CLOB:
        widgetType = WidgetFactory.TEXT_AREA_EDIT_WIDGET;
        break;
      case DATETIME:
        widgetType = WidgetFactory.DATE_TIME_EDIT_WIDGET;
        break;
      case DATE:
        widgetType = WidgetFactory.DATE_EDIT_WIDGET;
        break;
      case TIME:
        widgetType = WidgetFactory.TIME_EDIT_WIDGET;
        break;
      case DOUBLE:
      case FLOAT:
      case NUMBER:
        widgetType = WidgetFactory.NUMBER_EDIT_WIDGET;
        break;
      case MONETARY_AMOUNT:
        widgetType = WidgetFactory.MONETARY_AMOUNT_EDIT_WIDGET;
        break;
      case QUANTITY:
        widgetType = WidgetFactory.QUANTITY_EDIT_WIDGET;
        break;
      case ENUM:
        widgetType = WidgetFactory.ENUM_EDIT_WIDGET;

        String editorType = element.attributeValue(EnumField.EDITOR_TYPE);
        if (editorType == null) {
          //если не установлен тип редактора,
          //принимаем решение о типе редактора, если количество вариантов > 3 используем combobox
          widgetType = fldMeta.getEnumConstantsText().length > 3 ? WidgetFactory.ENUM_COMBOBOX_EDIT_WIDGET : WidgetFactory.ENUM_RADIOBUTTON_EDIT_WIDGET;
        } else if (editorType.equalsIgnoreCase(EnumField.EnumFieldType.COMBOBOX.name()))
          widgetType = WidgetFactory.ENUM_COMBOBOX_EDIT_WIDGET;
        else if (editorType.equalsIgnoreCase(EnumField.EnumFieldType.RADIOBUTTON.name()))
          widgetType = WidgetFactory.ENUM_RADIOBUTTON_EDIT_WIDGET;
        break;
      case ENTITY:
        widgetType = WidgetFactory.ENTITY_EDIT_WIDGET;
        break;
      default:
        throw new IllegalArgumentException(StringUtils.format("Unsupported type: %s. Field: %s.", id, type.toString())); //$NON-NLS-1$
    }

    if (widgetType != null)
      result = widgetFactory.createWidget(widgetType, id, element, view);

    ((FieldEditor) result).setFieldMetadata(fldMeta);

    //create label for widget
    if (showLabel) {
      String labelText = element.attributeValue(Label.TEXT);
      if (labelText == null)
        labelText = fldMeta.getShortLabel();
      else
        labelText = UIUtils.loadL10nText(labelText);
      createLabelFor(labelText, id, result);
    }

    return result;
  }

  private String generateLabelForName(String id) {
    return LABEL_FOR.concat(id);
  }

  private String generateScrollName(String id) {
    return SCROLL_PANE.concat(id);
  }

}
