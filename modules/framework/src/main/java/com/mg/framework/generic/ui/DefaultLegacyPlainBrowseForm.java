/*
 * DefaultLegacyPlainBrowseForm.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BrowseCond;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MaintenanceBrowseForm;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.MaintenanceTableController;

import java.io.Serializable;

/**
 * @author Oleg V. Safonov
 * @version $Id: DefaultLegacyPlainBrowseForm.java,v 1.9 2009/02/09 14:31:28 safonov Exp $
 */
@Deprecated
public class DefaultLegacyPlainBrowseForm extends AbstractForm implements MaintenanceBrowseForm, SearchHelpForm, MasterModelListener {
  protected BrowseCond cond = new BrowseCond(null, "", new LocalDataTransferObject(), false, DataBusinessObjectService.INTERNAL_LEGACY_FORMAT);
  protected MaintenanceTableController table;
  protected Serializable currentKey = null;
  protected DataBusinessObjectService<PersistentObject, Serializable> service;
  protected AttributeMap uiProperties = new LocalDataTransferObject();
  protected String restrictionFormName = null;
  //protected Object masterKey = null;
  private SearchHelpListener listener = null;
  private DefaultLegacyRestrictionForm restrictionForm = null;

  public DefaultLegacyPlainBrowseForm() {
    table = new MaintenanceTableController(this.cond, this.uiProperties);
    table.addMasterModelListener(this);
    //view = new BrowseView(this);
  }

  public void fireSearchPerformed(SearchHelpEvent event) {
    if (listener != null)
      listener.searchPerformed(event);
  }

  protected void onActionChoose(WidgetEvent event) throws ApplicationException {
    fireSearchPerformed(new SearchHelpEvent(this, new PersistentObject[]{service.load(currentKey)}));
    close();
  }

  private void runForm() {
    table.initController(service);

    super.doOnRun();

    //обработка SearchHelp, если используется в данном качестве, то откроем кнопку chooseButton
    if (listener != null) {
      Widget chooseButton = view.getWidget("сhooseButton");
      if (chooseButton != null)
        chooseButton.setVisible(true);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    if (service == null)
      throw new IllegalStateException("Service cann't be null");

    //обработаем условия отбора если существуют для данного браузера
    if (restrictionFormName != null) {
      if (restrictionForm == null)
        restrictionForm = (DefaultLegacyRestrictionForm) UIProducer.produceForm(restrictionFormName);
      table.setRestrictionForm(restrictionForm);
      restrictionForm.addOkActionListener(new FormActionListener() {
        public void actionPerformed(FormEvent event) {
          cond.browseCond = restrictionForm.getRestrictionItem();
          runForm();
        }
      });
      restrictionForm.execute();
    } else
      runForm();
  }

	/* (non-Javadoc)
     * @see com.mg.merp.core.support.ui.IMaintenanceBrowseForm#setService(java.lang.String)
	 */
  /*public void setService(String serviceName) throws NamingException, ApplicationException {
		setService((DataBusinessObjectService) BusinessObjectFactory.getInstance().getLocalService(serviceName));
	}*/

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.SearchHelpForm#addSearchHelpListener(com.mg.framework.api.ui.SearchHelpListener)
   */
  public void addSearchHelpListener(SearchHelpListener listener) {
    this.listener = listener;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    currentKey = event.getModelKey();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MaintenanceBrowseForm#setService(com.mg.framework.api.DataBusinessObjectService)
   */
  public void setService(DataBusinessObjectService<PersistentObject, Serializable> service) {
    this.service = service;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.SearchHelpForm#setEntity(com.mg.framework.api.orm.PersistentObject)
   */
  public void setTargetEntity(PersistentObject entity) {
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.SearchHelpForm#getSearchHelpListeners()
   */
  public SearchHelpListener[] getSearchHelpListeners() {
    return null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.SearchHelpForm#removeSearchHelpListener(com.mg.framework.api.ui.SearchHelpListener)
   */
  public void removeSearchHelpListener(SearchHelpListener listener) {
  }

}
