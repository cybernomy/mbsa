/*
 * ConversationTaskController.java
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
package com.mg.merp.bpm.support;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.def.TaskControllerHandler;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * @author Oleg V. Safonov
 * @version $Id: ConversationTaskController.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class ConversationTaskController implements TaskControllerHandler {
  private String taskConversation;

  /* (non-Javadoc)
   * @see org.jbpm.taskmgmt.def.TaskControllerHandler#initializeTaskVariables(org.jbpm.taskmgmt.exe.TaskInstance, org.jbpm.context.exe.ContextInstance, org.jbpm.graph.exe.Token)
   */
  public void initializeTaskVariables(TaskInstance taskInstance,
                                      ContextInstance contextInstance, Token token) {
    // TODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see org.jbpm.taskmgmt.def.TaskControllerHandler#submitTaskVariables(org.jbpm.taskmgmt.exe.TaskInstance, org.jbpm.context.exe.ContextInstance, org.jbpm.graph.exe.Token)
   */
  public void submitTaskVariables(TaskInstance taskInstance,
                                  ContextInstance contextInstance, Token token) {
    // TODO Auto-generated method stub

  }

  /**
   * @return the taskConversation
   */
  public String getTaskConversation() {
    return taskConversation;
  }

  /**
   * @param taskConversation the taskConversation to set
   */
  public void setTaskConversation(String formController) {
    this.taskConversation = formController;
  }

}
