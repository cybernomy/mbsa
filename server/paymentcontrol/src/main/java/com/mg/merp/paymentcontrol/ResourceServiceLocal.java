/*
 * ResourceServiceLocal.java
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
package com.mg.merp.paymentcontrol;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mg.merp.core.model.Folder;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.paymentcontrol.model.TurnResult;

/**
 * Сервис бизнес-компонента "Средства платежа"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ResourceServiceLocal.java,v 1.3 2007/05/14 04:59:59 sharapov Exp $
 */
public interface ResourceServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PmcResource, Integer> {
	
	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/Resource"; //$NON-NLS-1$
	
	/**
	 * Тип папки для средства платежа
	 */
	final static short FOLDER_PART = 13400;
	
	/**
	 * Получить баланс средства платежа на заданную дату
	 * @param resourceId - идентификатор средства платежа
	 * @param dateBalance - дата баланса
	 * @return баланс
	 */
	BigDecimal getBalance(Integer resourceId, Date dateBalance);
	
	/**
	 * Получить список групп(папок) средств платежа
	 * @param parentId - идентификатор родительской группы(папки)
	 * @return список групп(папок) средств платежа
	 */
	List<Folder> getResurceGroups(Integer parentId);
	 
	/**
	 * Получить список средств платежа принадлежащих к группе(папке)
	 * @param folderId - идентификатор группы(папки)
	 * @return список средств платежа
	 */
	List<PmcResource> getResourcesByGroup(Integer folderId);
	
	/**
	 * Получить оборотку по средству платежа
	 * @param resourceId - идентификатор средства платежа 
	 * @param versionId - идентификатор версии планирования
	 * @param dateFrom - период(дата с)
	 * @param dateTill - период(дата по)
	 * @return оборотка по средству платежа
	 */
	TurnResult getTurnByResource(Integer resourceId, Integer versionId, Date dateFrom, Date dateTill);
	
	/**
	 * Получить оборотку по группе(папке) средства платежа
	 * @param resourceFolderId - идентификатор группы(папки) средства платежа
	 * @param versionId - идентификатор версии планирования
	 * @param dateFrom - период(дата с)
	 * @param dateTill - период(дата по)
	 * @return оборотка по группе(папке) средства платежа
	 */
	TurnResult getTurnByResourceGroup(Integer resourceFolderId, Integer versionId, Date dateFrom, Date dateTill);
	
}
