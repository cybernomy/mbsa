/*
 * MaintenanceConversationSession.java
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
package com.mg.framework.api.ui;

import java.io.Serializable;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;

/**
 * Диалоговая сессия, служит для хранения произвольной информации в процессе
 * интерактивного взаимодействия с пользователем, доступна в событиях beforeOutput и
 * afterInput для форм поддержки
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceConversationSession.java,v 1.1 2006/10/26 13:19:12 safonov Exp $
 */
public interface MaintenanceConversationSession extends Serializable {

    /**
     * Возвращает сервис обслуживающий текущий сеанс пользователя 
     * 
     * @return  сервис
     */
    DataBusinessObjectService getService();
    
    /**
     * получить действие выполняемое пользователем
     * 
     * @return
     */
    MaintenanceAction getMaintenanceAction();

    /**
     * получить элемент пользовательского интерфейса
     * 
     * @param name	имя элемента
     * @return	элемент или <code>null</code> если не найден
     */
    Widget getWidget(String name);

    /**
     * получить объект-сущность
     * 
     * @return	объект-сущность
     */
    PersistentObject getEntity();
    
    /**
     * получить значение атрибута модели
     * 
     * @param name	имя атрибута модели
     * @return	значение
     */
    Object getModelAttribute(String name) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException;
    
    /**
     * установить значение атрибута модели
     * 
     * @param name	имя атрибута модели
     * @param value	значение
     */
    void setModelAttribute(String name, Object value) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException;
    
    /**
     * Получает значение атрибута диалоговой сессии по имени
     * 
     * @param name  наименование атрибута
     * @return      значение атрибута
     */
    Serializable getAttribute(String name);
    
    /**
     * Устанавливает значение атрибута в диалоговой сессии
     * 
     * @param name  наименование атрибута
     * @param value значение атрибута
     */
    void setAttribute(String name, Serializable value);
}
