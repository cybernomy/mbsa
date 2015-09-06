/**
 * UserSessionInfoDetailForm.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.core.support.ui;

import com.mg.framework.api.UserSessionInfo;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.service.ApplicationServerLocator;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ServerUtils;

import java.text.DateFormat;
import java.util.TimeZone;

/**
 * Контроллер формы подробной информации о сессии пользователя
 *
 * @author Oleg V. Safonov
 * @version $Id: UserSessionInfoDetailForm.java,v 1.2 2008/12/08 06:22:24 safonov Exp $
 */
public class UserSessionInfoDetailForm extends AbstractForm {
  protected String userName;
  protected boolean isActive;
  protected String creationTime;
  protected String lastAccessedTime;
  protected String usedServerTime;
  protected String idleTime;
  protected String ttl;
  protected String httpSessionId;
  protected String remoteHost;
  protected int lastUsedTime;
  protected int minUsedTime;
  protected int maxUsedTime;
  protected int hits;
  protected long size;
  protected long lastRequestSize;
  protected long lastResponseSize;
  protected long totalRequestSize;
  protected long totalResponseSize;
  protected String message;

  private void loadModel(UserSessionInfo userSessionInfo) {
    if (userSessionInfo == null)
      return;

    DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, ServerUtils.getUserLocale());
    DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, ServerUtils.getUserLocale());
    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    userName = userSessionInfo.getUserName();
    isActive = userSessionInfo.isActive();
    creationTime = dateTimeFormat.format(userSessionInfo.getCreationTime());
    lastAccessedTime = dateTimeFormat.format(userSessionInfo.getLastAccessedTime());
    usedServerTime = timeFormat.format(userSessionInfo.getUsedServerTime());
    idleTime = timeFormat.format(userSessionInfo.getIdleTime());
    ttl = timeFormat.format(userSessionInfo.getTTL());
    httpSessionId = userSessionInfo.getHttpSessionId();
    remoteHost = userSessionInfo.getRemoteHost();
    lastUsedTime = userSessionInfo.getLastUsedTime();
    minUsedTime = userSessionInfo.getMinUsedTime();
    maxUsedTime = userSessionInfo.getMaxUsedTime();
    hits = userSessionInfo.getHits();
    size = userSessionInfo.getSize();
    lastRequestSize = userSessionInfo.getLastRequestSize();
    lastResponseSize = userSessionInfo.getLastResponseSize();
    totalRequestSize = userSessionInfo.getTotalRequestSize();
    totalResponseSize = userSessionInfo.getTotalResponseSize();
  }

  protected void onActionCheckClose(WidgetEvent event) {
    close();
  }

  protected void onActionRefresh(WidgetEvent event) throws Exception {
    loadModel(ApplicationServerLocator.locate().loadUserSessionInfo(httpSessionId));
  }

  protected void onActionSendMessage(WidgetEvent event) throws Exception {
    ApplicationServerLocator.locate().sendAdminMessage(new String[]{httpSessionId}, message);
  }

  protected void onActionInvalidateSession(WidgetEvent event) throws Exception {
    com.mg.framework.support.Messages msg = com.mg.framework.support.Messages.getInstance();
    final String yesButton = msg.getMessage(com.mg.framework.support.Messages.YES_BUTTON_TEXT), noButton = msg.getMessage(com.mg.framework.support.Messages.NO_BUTTON_TEXT);
    UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, null,
        msg.getMessage(com.mg.framework.support.Messages.CONFIRMATION_ALERT_QUESTION),
        yesButton, noButton, new AlertListener() {

          public void alertClosing(String value) {
            if (yesButton.equals(value))
              try {
                ApplicationServerLocator.locate().invalidateUserSessions(new String[]{httpSessionId});
              } catch (Exception e) {
                getLogger().error("invalidate user session failed, ignored", e);
              }
          }

        });
  }

  /**
   * запуск формы
   */
  public void execute(UserSessionInfo userSessionInfo) {
    loadModel(userSessionInfo);
    run(UIUtils.isModalMode());
  }

}
