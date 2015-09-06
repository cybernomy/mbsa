/*
 * PriceListPermissionsForm.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.ShuttleChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.ShuttleController;
import com.mg.framework.support.ui.widget.ShuttleListener;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.reference.PriceListAccessResult;
import com.mg.merp.reference.PriceListAccessServiceLocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер формы настройки прав пользователя на прайс-листы
 *
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListPermissionsForm.java,v 1.1 2008/05/13 09:55:17 alikaev Exp $
 */
public class PriceListPermissionsForm extends AbstractForm {

  private static String PERMISSIONS_WIDGET_NAME = "permissions"; //$NON-NLS-1$
  private static String BUSINESS_COMPONENT_NAME = "merp/reference/PriceListAccess"; //$NON-NLS-1$
  private static String GRANT_METHOD_NAME = "grantPermission"; //$NON-NLS-1$
  private static String REVOKE_METHOD_NAME = "revokePermission"; //$NON-NLS-1$
  private ShuttleController permissions;
  private Map<String, PriceListAccessResult> priceListPerms = new HashMap<String, PriceListAccessResult>();
  private PriceListAccessServiceLocal priceListAccessService;
  private Integer priceListId;

  // default constructor
  public PriceListPermissionsForm() {
    priceListAccessService = (PriceListAccessServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PriceListAccessServiceLocal.SERVICE_NAME);

    permissions = new ShuttleController();
    permissions.addShuttleListener(new ShuttleListener() {
      public void shuttleContentsMoved(ShuttleChangeEvent event) {
        grantPermission(event.getContents());
      }

      public void shuttleContentsRemoved(ShuttleChangeEvent event) {
        revokePermission(event.getContents());
      }
    });
  }

  /**
   * Запустить форму настройки прав для прайс-листов
   *
   * @param priceListId - идентификатор прайс-листа
   */
  public void execute(Integer priceListId) {
    this.priceListId = priceListId;
    fillPermissions();
    run(true);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    view.getWidget(PERMISSIONS_WIDGET_NAME).setReadOnly(
        !SecurityUtils.tryCheckPermission(new BusinessMethodPermission(BUSINESS_COMPONENT_NAME, GRANT_METHOD_NAME))
            || !SecurityUtils.tryCheckPermission(new BusinessMethodPermission(BUSINESS_COMPONENT_NAME, REVOKE_METHOD_NAME)));
  }

  private void grantPermission(Object[] permissions) {
    for (Object permission : permissions)
      priceListAccessService.grantPermission(priceListPerms.get(permission), priceListId);
  }

  private void revokePermission(Object[] permissions) {
    for (Object permission : permissions)
      priceListAccessService.revokePermission(priceListPerms.get(permission));
  }

  private void fillPermissions() {
    List<PriceListAccessResult> groups = priceListAccessService.loadPriceListPermissions(priceListId);
    List<String> leadingList = new ArrayList<String>();
    List<String> trailingList = new ArrayList<String>();

    for (PriceListAccessResult item : groups) {
      if (item.getPermission() == null || !item.getPermission())
        leadingList.add(item.getRoleName());
      else
        trailingList.add(item.getRoleName());
      priceListPerms.put(item.getRoleName(), item);
    }
    permissions.getModel().setLeadingList(leadingList.toArray(new String[leadingList.size()]));
    permissions.getModel().setTrailingList(trailingList.toArray(new String[trailingList.size()]));
  }

  /**
   * Обработчик события "Закрыть форму"
   *
   * @param event - событие
   */
  public void onActionOk(WidgetEvent event) {
    close();
  }

}
