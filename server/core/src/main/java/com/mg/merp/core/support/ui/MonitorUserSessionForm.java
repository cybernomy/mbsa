/**
 * MonitorUserSessionForm.java
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
package com.mg.merp.core.support.ui;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.UserSessionInfo;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.ColumnsTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.ApplicationServerLocator;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.support.Messages;

/**
 *  онтроллер формы списка сессий пользователей
 * 
 * @author Oleg V. Safonov
 * @version $Id: MonitorUserSessionForm.java,v 1.2 2008/12/08 06:22:24 safonov Exp $
 */
public class MonitorUserSessionForm extends AbstractForm {
	private DefaultTableController userList;
	private List<UserSessionInfo> users = new ArrayList<UserSessionInfo>();
	private DateFormat dateTimeFormat;
	private DateFormat timeFormat;
	private String message;
	private UserSessionInfo currentInfo = null;
	private int[] currentIndexes = new int[0];
	
	public MonitorUserSessionForm() {
		super();
		dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, ServerUtils.getUserLocale());
		timeFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, ServerUtils.getUserLocale());
		timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		userList = new DefaultTableController(new UserTableModel());
	}
	
	private class UserTableModel extends ColumnsTableModel {

		public int getRowCount() {
			return users == null ? 0 : users.size();
		}

		public Object getValueAt(int row, int column) {
			UserSessionInfo userInfo = users.get(row);
			switch (column) {
			case 0:
				return userInfo.isCurrentPrincipal() ? Messages.getInstance().getMessage(Messages.CURRENT_USER_NAME, new Object[] {userInfo.getUserName()}) : userInfo.getUserName();
			case 1:
				return userInfo.getCreationTime();
			case 2:
				return userInfo.getLastAccessedTime();
			case 3:
				return userInfo.getUsedServerTime();
			case 4:
				return userInfo.getIdleTime();
			case 5:
				return userInfo.getTTL();
			case 6:
				return userInfo.getHttpSessionId();
			//case 7:
				//return userInfo.isActive();
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#getDecoratorValueAt(int, int)
		 */
		@Override
		public Object getDecoratorValueAt(int row, int column) {
			UserSessionInfo userInfo = users.get(row);
			switch (column) {
			case 1:
				return userInfo.getCreationTime() == null ? null : dateTimeFormat.format(userInfo.getCreationTime());
			case 2:
				return userInfo.getLastAccessedTime() == null ? null : dateTimeFormat.format(userInfo.getLastAccessedTime());
			case 3:
				return userInfo.getUsedServerTime() == null ? null : timeFormat.format(userInfo.getUsedServerTime());
			case 4:
				return userInfo.getIdleTime() == null ? null : timeFormat.format(userInfo.getIdleTime());
			case 5:
				return userInfo.getTTL() == null ? null : timeFormat.format(userInfo.getTTL());
			case 6:
				return userInfo.getHttpSessionId();
			//case 7:
				//return userInfo.isActive();
			}
			return super.getDecoratorValueAt(row, column);
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
		 */
//		@Override
//		public Class<?> getColumnClass(int column) {
//			if (column == 7)
//				return Boolean.class;
//			else
//				return super.getColumnClass(column);
//		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
		 */
		@Override
		public void setSelectedRows(int[] rows) {
			if (rows.length == 0)
				currentInfo = null;
			else
				currentInfo = users.get(rows[0]);
			currentIndexes = rows;
		}

	}
	
	private void loadUser() {
		users.clear();
		try {
			users.addAll(ApplicationServerLocator.locate().loadUserSessionInfos());
		} catch (Exception e) {
			getLogger().error("Cann't load user session informations", e);
			throw new ApplicationException(e);
		}
		Collections.sort(users, new Comparator<UserSessionInfo>() {

			public int compare(UserSessionInfo o1, UserSessionInfo o2) {
				return o1.getUserName().compareTo(o2.getUserName());
			}
			
		});
	}
	
	protected void onActionRefresh(WidgetEvent event) {
		loadUser();
		((UserTableModel) userList.getModel()).fireModelChange();
	}

	protected void onActionCheckClose(WidgetEvent event) {
		close();
	}

	protected void onActionShowUserSessionInfoDetail(WidgetEvent event) {
		if (currentInfo == null)
			return;
		
		((UserSessionInfoDetailForm) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.core.UserSessionInfoDetailForm")).execute(currentInfo);
	}

	protected void onActionSendMessage(WidgetEvent event) throws Exception {
		if (currentIndexes.length != 0) {
			List<String> sessionIds = new ArrayList<String>();
			for (int i = 0; i < currentIndexes.length; i++) {
				UserSessionInfo userSessionInfo = users.get(currentIndexes[i]);
				//не отправл€ет сам себе
				if (!userSessionInfo.isCurrentPrincipal())
					sessionIds.add(userSessionInfo.getHttpSessionId());
			}
			
			ApplicationServerLocator.locate().sendAdminMessage(sessionIds.toArray(new String[sessionIds.size()]), message);
		}
	}

	protected void onActionInvalidateSessions(WidgetEvent event) throws Exception {
		com.mg.framework.support.Messages msg = com.mg.framework.support.Messages.getInstance();
		final String yesButton = msg.getMessage(com.mg.framework.support.Messages.YES_BUTTON_TEXT), noButton = msg.getMessage(com.mg.framework.support.Messages.NO_BUTTON_TEXT);
		UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, null,
				msg.getMessage(com.mg.framework.support.Messages.CONFIRMATION_ALERT_QUESTION),
				yesButton, noButton, new AlertListener() {

			public void alertClosing(String value) {
				if (yesButton.equals(value))
				if (currentIndexes.length != 0) {
					List<String> sessionIds = new ArrayList<String>();
					for (int i = 0; i < currentIndexes.length; i++) {
						UserSessionInfo userSessionInfo = users.get(currentIndexes[i]);
						sessionIds.add(userSessionInfo.getHttpSessionId());
					}
					
					try {
						ApplicationServerLocator.locate().invalidateUserSessions(sessionIds.toArray(new String[sessionIds.size()]));
					} catch (Exception e) {
						getLogger().error("invalidate user sessions failed, ignored", e);
					}
				}
			}

		});
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		loadUser();
		super.doOnRun();
	}

}
