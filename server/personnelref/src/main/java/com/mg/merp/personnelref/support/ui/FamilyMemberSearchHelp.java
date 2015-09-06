/*
 * FamilyMemberSearchHelp.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.model.FamilyMember;
import com.mg.merp.reference.model.NaturalPerson;

/**
 * Поисковик членов семьи бизнес-компонента "Вычеты на членов семьи"
 *
 * @author Artem V. Sharapov
 * @version $Id: FamilyMemberSearchHelp.java,v 1.1 2007/01/24 11:28:59 sharapov Exp $
 */
public class FamilyMemberSearchHelp extends AbstractSearchHelp {
  private final String importContextPerson = "Person"; //$NON-NLS-1$
  private final String importContextFamilyMember = "FamilyMember"; //$NON-NLS-1$

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    FamilyMemberSearchForm form = (FamilyMemberSearchForm) UIProducer.produceForm("com/mg/merp/personnelref/resources/FamilyMemberSearchForm.mfd.xml"); //$NON-NLS-1$
    form.addSearchHelpListener(this);
    NaturalPerson personel = (NaturalPerson) getImportContextValue(importContextPerson);
    if (personel == null) {
      FamilyMember familyMember = ServerUtils.getPersistentManager().find(FamilyMember.class, ((FamilyMember) getImportContextValue(importContextFamilyMember)).getId());
      personel = familyMember.getNaturalPerson();
    }
    form.setNaturalPerson(personel);
    form.run(UIUtils.isModalMode());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{importContextPerson, importContextFamilyMember};
  }

}
