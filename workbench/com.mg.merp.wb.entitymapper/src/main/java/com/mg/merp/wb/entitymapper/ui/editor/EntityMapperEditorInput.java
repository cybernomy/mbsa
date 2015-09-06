/* EntityMapperEditorInput.java
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
package com.mg.merp.wb.entitymapper.ui.editor;

import com.mg.merp.core.model.EntityTransformerMapping;
import com.mg.merp.wb.core.ui.editor.StandartEditorInput;
import com.mg.merp.wb.entitymapper.Activator;

/**
 * Входные данные для формы редактирования маппинга преобразования классов
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityMapperEditorInput.java,v 1.1 2007/05/07 13:09:12 poroxnenko Exp $
 */
public class EntityMapperEditorInput extends StandartEditorInput<EntityTransformerMapping> {

  public static final String ENTITYMAPPER_FORM_PART = "entitymapper.form.edit.part";

  public EntityMapperEditorInput(EntityTransformerMapping object, boolean createNew) {
    super(object, createNew);
  }

  @Override
  public String getEditorName() {
    return String.format(Activator.getDefault().getString(ENTITYMAPPER_FORM_PART),
        object.getMapId().trim());
  }

  @Override
  public String getToolTip() {
    return object.getMapId().trim();
  }

  @Override
  public boolean isMatch(EntityTransformerMapping obj) {
    return object.getId() == obj.getId();
  }

}
