/*
 * LBScheduleManagerImpl.java
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
package com.mg.merp.lbschedule.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.LBScheduleManager;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.lbschedule.ScheduleServiceLocal;
import com.mg.merp.lbschedule.model.Schedule;
import com.mg.merp.lbschedule.model.ScheduleConfig;
import com.mg.merp.lbschedule.model.ScheduleDocHeadLink;

/**
 * Реализация мененеджера управления "Графиками исполнения обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: LBScheduleManagerImpl.java,v 1.3 2008/05/23 16:08:26 safonov Exp $
 */
public class LBScheduleManagerImpl implements LBScheduleManager {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.LBScheduleManager#createLBSchedule(com.mg.merp.document.model.DocHead)
	 */
	public void createLBSchedule(DocHead docHead) {
		doCreateLBSchedule(docHead);
	}

	protected void doCreateLBSchedule(final DocHead docHead) {
		if(docHead == null)
			return;

		if(getScheduleDocHeadLink(docHead) != null)
			return;

		Folder lbScheduleFolder = getLbScheduleDefaultFolder();
		if(lbScheduleFolder == null) {
			SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.lbschedule.support.ui.ScheduleDefaultFolderSearchHelp"); //$NON-NLS-1$
			searchHelp.addSearchHelpListener(new SearchHelpListener() {
				public void searchPerformed(SearchHelpEvent event) {
					doInternalCreateLBSchedule(docHead, (Folder) event.getItems()[0]);
				}

				public void searchCanceled(SearchHelpEvent event) {
					//do nothing
				}
			});
			searchHelp.search();
		}
		else
			doInternalCreateLBSchedule(docHead, lbScheduleFolder);
	}

	protected void doInternalCreateLBSchedule(DocHead docHead, Folder folder) {
		SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();

		Schedule schedule = initializeLbSchedule(folder);
		ServerUtils.getPersistentManager().persist(schedule);

		ScheduleDocHeadLink scheduleDocHeadLink = intializeScheduleDocHeadLink(docHead, schedule, sysClient);
		ServerUtils.getPersistentManager().persist(scheduleDocHeadLink);

		showLbSchedule(schedule);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.LBScheduleManager#openLBSchedule(com.mg.merp.document.model.DocHead)
	 */
	public void openLBSchedule(DocHead docHead) {
		ScheduleDocHeadLink scheduleDocHeadLink = getScheduleDocHeadLink(docHead);
		if(scheduleDocHeadLink == null)
			return;

		Schedule schedule = scheduleDocHeadLink.getSchedule();
		showLbSchedule(schedule);
	}

	private Schedule initializeLbSchedule(Folder folder) {
		Schedule schedule = getScheduleService().initialize();
		schedule.setFolder(folder);
		return schedule;
	}

	private ScheduleDocHeadLink intializeScheduleDocHeadLink(DocHead docHead, Schedule schedule, SysClient sysClient) {
		ScheduleDocHeadLink scheduleDocHeadLink = new ScheduleDocHeadLink();
		scheduleDocHeadLink.setDocHead(docHead);
		scheduleDocHeadLink.setSchedule(schedule);
		scheduleDocHeadLink.setSysClient(sysClient);
		return scheduleDocHeadLink;
	}

	private Folder getLbScheduleDefaultFolder() {
		ScheduleConfig scheduleConfig = ServerUtils.getPersistentManager().find(ScheduleConfig.class, ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId());
		if(scheduleConfig != null)
			return scheduleConfig.getFolder();
		else
			return null;
	}

	private ScheduleServiceLocal getScheduleService() {
		return (ScheduleServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ScheduleServiceLocal.LOCAL_SERVICE_NAME);
	}

	private ScheduleDocHeadLink getScheduleDocHeadLink(DocHead docHead) {
		return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(ScheduleDocHeadLink.class)
				.add(Restrictions.eq("DocHead", docHead))); //$NON-NLS-1$
	}

	private void showLbSchedule(Schedule schedule) {
		ScheduleServiceLocal scheduleService = getScheduleService();
		MaintenanceHelper.edit(scheduleService, schedule.getId(), null, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.LBScheduleManager#removeLBSchedule(com.mg.merp.document.model.DocHead)
	 */
	public void removeLBSchedule(final DocHead docHead) {
		if(docHead == null)
			return;

		final ScheduleDocHeadLink  scheduleDocHeadLink = getScheduleDocHeadLink(docHead);
		if(scheduleDocHeadLink != null)
			ServerUtils.getPersistentManager().remove(scheduleDocHeadLink.getSchedule());
	}

}
