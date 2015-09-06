/*
 * AbstractForm.java
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
package com.mg.framework.generic.ui;

import com.mg.framework.api.Logger;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.ui.Controller;
import com.mg.framework.api.ui.Form;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.SetPropertyCommand;
import com.mg.framework.api.ui.View;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.BeanUtils;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактная реализация контроллера формы пользовательского интерфейса. Рекомендуется использовать
 * в качестве предка для контроллеров форм.
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractForm.java,v 1.12 2008/10/08 11:41:50 safonov Exp $
 */
public abstract class AbstractForm implements Form, Controller, Serializable {
  protected transient final Logger logger = ServerUtils.getLogger(getClass());
  protected View view;
  private List<FormActionListener> closeActionListeners = new ArrayList<FormActionListener>();
  private boolean modal = false;
  private List<SetPropertyCommand> modelChanges = new ArrayList<SetPropertyCommand>();

  /**
   * реализация получения значения свойства модели
   *
   * @param name имя свойства
   * @return значение
   */
  protected Object doGetFieldValue(String name) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Field fld = ReflectionUtils.findDeclaredField(this.getClass(), name);
    if (fld == null)
      throw new NoSuchFieldException(name);
    return fld.get(this);
  }

  /**
   * реализация установки значения свойства модели
   *
   * @param name  имя свойства
   * @param value значение
   */
  protected void doSetFieldValue(String name, Object value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Field fld = ReflectionUtils.findDeclaredField(this.getClass(), name);
    if (fld == null)
      throw new NoSuchFieldException(name);
    fld.set(this, value);
  }

  /**
   * реализация получения метаданных свойства модели
   *
   * @param name имя свойства
   * @return метаданные или <code>null</code> если свойство не найдено
   */
  protected FieldMetadata doGetFieldMetadata(String name) {
    Field fld = ReflectionUtils.findDeclaredField(this.getClass(), name);
    return fld != null ? ApplicationDictionaryLocator.locate().getFieldMetadata(ReflectionUtils.getFieldReflectionMetadata(fld))
        : null;
  }

  /**
   * реализация получения типа свойства модели
   *
   * @param name имя свойства
   * @return тип
   */
  protected Class<?> doGetFieldType(String name) {
    Field fld = ReflectionUtils.findDeclaredField(this.getClass(), name);
    return fld != null ? fld.getType() : null;
  }

  /**
   * получить список изменений модели
   *
   * @return список изменений модели
   */
  protected List<SetPropertyCommand> getModelChanges() {
    return modelChanges;
  }

  /**
   * добавить изменение в список изменений модели
   *
   * @param command команда изменения модели
   */
  private void addModelChange(SetPropertyCommand command) {
    getModelChanges().add(command);
  }

  /**
   * реализация вызова обработчика события
   *
   * @param handlerName имя обработчика
   * @param args        аргументы вызова
   * @return результат работы обработчика
   */
  protected Object doInvokeHandler(String handlerName, Object... args) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    String name = ACTION_HANDLER_PREFIX.concat(StringUtils.firstCharToUpper(handlerName));
    Class<?>[] paramTypes = new Class[args.length];
    for (int i = 0; i < args.length; i++)
      paramTypes[i] = args[i].getClass();
    Method m = ReflectionUtils.findDeclaredMethod(getClass(), name, paramTypes);
    if (m == null)
      throw new NoSuchMethodException(name);
    m.setAccessible(true);
    return m.invoke(this, args);
  }

  /**
   * реализация показа формы в UI
   */
  protected final void showForm() {
    view.show(modal);
  }

  /**
   * реализация закрытия формы в UI
   */
  protected final void closeForm() {
    view.close();
    //help GC
    //view = null;
  }

  /**
   * обработчик события <code>close</code>
   *
   * @param event событие
   */
  public final void onActionClose(WidgetEvent event) {
    doOnActionClose(event);
  }

  /**
   * используется для обработки события <code>close</code>, не вызывать напрямую, данное событие
   * можно использовать для обработки закрытия формы, например, если сделать реализацию пустой, то
   * окно не будет закрываться при нажатии на кнопку X
   *
   * @param event событие
   */
  protected void doOnActionClose(WidgetEvent event) {
    close();
  }

  /**
   * Метод предназначен для реализации дополнительных действий в классах наследниках непосредственно
   * перед показом формы, в базовой реализации данный метод показывает форму, в случае если метод
   * был переопределен, то необходимо вызвать базовую реализацию <code>super.doOnRun();</code> или
   * вызвать метод <code>showForm();</code>
   */
  protected void doOnRun() {
    showForm();
  }

  /**
   * Метод предназначен для реализации дополнительных действий в классах наследниках непосредственно
   * перед закрытием формы, в базовой реализации данный метод закрывает форму, в случае если метод
   * был переопределен, то необходимо вызвать базовую реализацию <code>super.doOnClose();</code> или
   * вызвать метод <code>closeForm();</code>
   */
  protected void doOnClose() {
    closeForm();
  }

  /**
   * признак модальности формы
   *
   * @return <code>true</code> если форма модальная
   */
  protected boolean isModal() {
    return modal;
  }

  /**
   * реализация разрушения формы
   */
  protected void doDispose() {

  }

  /**
   * отправка события о закрытии формы
   *
   * @param event событие
   */
  public void fireCloseAction(FormEvent event) {
    for (FormActionListener listener : closeActionListeners)
      listener.actionPerformed(event);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.core.support.ui.IController#invokeHandler(java.lang.String, java.lang.Object[])
   */
  public final Object invokeHandler(String handlerName, Object... args) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    return doInvokeHandler(handlerName, args);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.support.ui.IForm#run()
   */
  public void run() {
    run(false);
  }

  /*
   *  (non-Javadoc)
   * @see com.mg.framework.api.ui.Form#run(boolean)
   */
  public void run(boolean modal) {
    this.modal = modal;
    doOnRun();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Form#getTitle()
   */
  public String getTitle() {
    return view.getTitle();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.support.ui.IForm#setTitle(java.lang.String)
   */
  public void setTitle(String title) {
    view.setTitle(title);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.support.ui.IForm#close()
   */
  public void close() {
    doOnClose();
    fireCloseAction(new FormEvent(this));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.support.ui.IController#setView(com.mg.merp.core.support.ui.IView)
   */
  public void setView(View view) {
    this.view = view;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.support.ui.IController#getFieldValue(java.lang.String)
   */
  public final Object getFieldValue(String name) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    return doGetFieldValue(name);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.support.ui.IController#setFieldValue(java.lang.String, java.lang.Object)
   */
  public final void setFieldValue(String name, Object value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Object oldValue = getFieldValue(name); //получим старое значение
    //изменим модель в случае если было изменение значения
    if (BeanUtils.isPropertyDifferent(oldValue, value)) {
      doSetFieldValue(name, value);
      addModelChange(new SetPropertyCommand(value, oldValue, name));
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.support.ui.IController#getFieldMetadata(java.lang.String)
   */
  public final FieldMetadata getFieldMetadata(String name) {
    return doGetFieldMetadata(name);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Controller#getFieldType(java.lang.String)
   */
  public Class<?> getFieldType(String name) {
    return doGetFieldType(name);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Controller#getLogger()
   */
  public Logger getLogger() {
    return logger;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Form#dispose()
   */
  public void dispose() {
    doDispose();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Form#addCloseActionListener(com.mg.framework.api.ui.FormActionListener)
   */
  public void addCloseActionListener(FormActionListener listener) {
    closeActionListeners.add(listener);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Form#removeCloseActionListener(com.mg.framework.api.ui.FormActionListener)
   */
  public void removeCloseActionListener(FormActionListener listener) {
    closeActionListeners.remove(listener);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Controller#undo()
   */
  public void undo() {
    SetPropertyCommand propertyChange = getModelChanges().remove(getModelChanges().size() - 1);
    try {
      doSetFieldValue(propertyChange.getPropertyName(), propertyChange.getOldValue());
    } catch (Exception e) {
      logger.warn("Cann't undo property '" + propertyChange.getPropertyName() + "'", e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Controller#undoAll()
   */
  public void undoAll() {
    while (!getModelChanges().isEmpty())
      undo();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Controller#resetUndo()
   */
  public void resetUndo() {
    getModelChanges().clear();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.Controller#hasModelChanges()
   */
  public boolean hasModelChanges() {
    return !getModelChanges().isEmpty();
  }

}
