/* BAiEditorInput.java
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
package com.mg.merp.wb.bai.ui.editor;

import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.wb.bai.BAiPlugin;
import com.mg.merp.wb.core.ui.editor.StandartEditorInput;

/**
 * Входные данные для формы редактирования BAi
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: BAiEditorInput.java,v 1.2 2007/04/11 06:48:20 poroxnenko Exp $
 */
public class BAiEditorInput extends StandartEditorInput<Repository> {

  public static final String FORM_PART = "bai.form.edit.part";

  public BAiEditorInput(Repository object, boolean createNew) {
    super(object, createNew);
  }

  @Override
  public String getEditorName() {
    return String.format(BAiPlugin.getDefault().getString(FORM_PART),
        object.getCode().trim());
  }

  @Override
  public String getToolTip() {
    return object.getCode().trim();
  }

  @Override
  public boolean isMatch(Repository obj) {
    return object.getId() == obj.getId();
  }

}
