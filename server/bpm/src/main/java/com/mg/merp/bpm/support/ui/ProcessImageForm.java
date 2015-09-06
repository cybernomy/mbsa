/*
 * ProcessImageForm.java
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
package com.mg.merp.bpm.support.ui;

import com.mg.framework.api.ui.widget.Image;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.merp.bpm.support.BPMManagerLocator;
import com.mg.merp.bpm.support.Messages;

import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

/**
 * Контроллер формы показа схемы процесса
 *
 * @author Oleg V. Safonov
 * @version $Id: ProcessImageForm.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class ProcessImageForm extends AbstractForm {
  private byte[] image;

  private void loadProcessImage(ProcessDefinition processDefinition) {
    image = processDefinition.getFileDefinition().getBytes("processimage.jpg");
  }

  public void execute(long processDefinitionId) {
    JbpmContext context = BPMManagerLocator.locate().getCurrentBpmContext();
    try {
      ProcessDefinition processDefinition = context.getGraphSession().loadProcessDefinition(processDefinitionId);
      setTitle(Messages.getInstance().getMessage(Messages.PROCESS_IMAGE_TITLE, new Object[]{processDefinition.getName(), processDefinition.getVersion()}));
      loadProcessImage(processDefinition);
    } finally {
      context.close();
    }
    run();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    ((Image) view.getWidget("processImage")).setData(image);
  }

}
