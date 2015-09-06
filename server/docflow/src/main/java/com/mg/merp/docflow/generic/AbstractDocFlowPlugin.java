/*
 * AbstractDocFlowPlugin.java
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
package com.mg.merp.docflow.generic;

import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.DocFlowPluginEvent;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocFlowPluginListener;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docprocess.model.DocHeadState;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактная реализация подключаемого модуля машины документооборота для выполнения этапов. Класс
 * модуля должен реализовывать методы {@link #doExecute()} и {@link #doRoolback()}.
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractDocFlowPlugin.java,v 1.3 2008/06/09 11:36:01 safonov Exp $
 */
public abstract class AbstractDocFlowPlugin implements DocFlowPlugin {
  private Logger logger = ServerUtils.getLogger(getClass());
  private List<DocFlowPluginListener> listeners = new ArrayList<DocFlowPluginListener>();
  private DocFlowPluginInvokeParams params;

  /**
   * параметры ДО с которыми запущен данный этап
   *
   * @return параметры ДО
   */
  protected DocFlowPluginInvokeParams getParams() {
    return params;
  }

  /**
   * get logger
   *
   * @return logger
   */
  protected Logger getLogger() {
    return logger;
  }

  private void firePerformCompleted(DocFlowPluginEvent event) {
    for (DocFlowPluginListener listener : listeners)
      listener.performed(event);
  }

  private void firePerformCanceled() {
    for (DocFlowPluginListener listener : listeners)
      listener.canceled();
  }

  /**
   * отправить сообщение об успешном завершении выполнения (или отката) этапа ДО
   */
  protected final void complete() {
    //отправляем спецификации только для документов со спецификациями
    List<DocumentSpecItem> specList = params.getDocument().getDocSection().isWithSpec() ? params.getSpecList() : null;
    firePerformCompleted(new DocFlowPluginEvent(this, params.getProcessDate(), params.getPerformedSum(), params.getData1(), params.getData2(), params.getHeadStateValue(), specList));
  }

  /**
   * отправить сообщение о прерывании выполнения (или отката) этапа ДО
   */
  protected final void cancel() {
    firePerformCanceled();
  }

  /**
   * реализация выполнения этапа ДО, должен быть переопределен в классах наследниках.
   * <strong>Необходимо обязательно вызвать метод {@link #complete()} или {@link
   * #cancel()}.</strong>
   *
   * <p>Пример данного метода:
   * <pre>
   *  protected void doExecute() throws Exception {
   *  	if (<все номально>)
   *  		complete();
   *   	else
   *   		cancel();
   *  }
   * </pre>
   */
  protected abstract void doExecute() throws Exception;

  /**
   * реализация выполнения отката этапа ДО, должен быть переопределен в классах наследниках.
   * <strong>Необходимо обязательно вызвать метод {@link #complete()} или {@link
   * #cancel()}.</strong>
   *
   * <p>Пример данного метода:
   * <pre>
   *  protected void doRoolback() throws Exception {
   *  	if (<все номально>)
   *  		complete();
   *   	else
   *   		cancel();
   *  }
   * </pre>
   */
  protected abstract void doRoolback() throws Exception;

  /**
   * реализация текстового представления результата выполнения этапа ДО, используется для
   * отображения в истории ДО и т.п.
   *
   * @param docHeadState состояние этапа ДО
   * @return текстовое представление
   */
  protected String doGetDocActionResultTextRepresentation(DocHeadState docHeadState) {
    return null;
  }

  /**
   * реализация показа результата выполнения этапа ДО в пользовательском интерфейсе
   *
   * @param docHeadState состояние этапа ДО
   */
  protected void doShowDocActionResult(DocHeadState docHeadState) {
    //empty
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPlugin#registerListener(com.mg.merp.docflow.DocFlowPluginListener)
   */
  public void registerListener(DocFlowPluginListener listener) {
    this.listeners.add(listener);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPlugin#unregisterListener(com.mg.merp.docflow.DocFlowPluginListener)
   */
  public void unregisterListener(DocFlowPluginListener listener) {
    this.listeners.remove(listener);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPlugin#execute(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  public final void execute(DocFlowPluginInvokeParams params) throws Exception {
    getLogger().debug("Invoke execute for plug-in");
    this.params = params;
    doExecute();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPlugin#rollback()
   */
  public final void rollback(DocFlowPluginInvokeParams params) throws Exception {
    getLogger().debug("Invoke rollback for plug-in");
    this.params = params;
    doRoolback();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPlugin#getDocActionStateTextRepresentation(com.mg.merp.docprocess.model.DocAction)
   */
  public final String getDocActionResultTextRepresentation(DocHeadState docHeadState) {
    return doGetDocActionResultTextRepresentation(docHeadState);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPlugin#showDocActionState(com.mg.merp.docprocess.model.DocAction)
   */
  public final void showDocActionResult(DocHeadState docHeadState) {
    doShowDocActionResult(docHeadState);
  }

}
