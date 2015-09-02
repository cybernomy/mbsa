/**
 * UIProperties.java
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
package com.mg.framework.utils;

import java.io.FileInputStream;
import java.util.Properties;

import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.HorizontalAlignment;

/**
 * Свойства пользовательского интерфейса. При изменении файла настроек требуется перезагрузка
 * сервера приложения для вступления в силу изменений.
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIProperties.java,v 1.1 2008/07/28 14:20:16 safonov Exp $
 */
public class UIProperties {
	/**
	 * имя файла свойств хранящего настройки по умолчанию
	 */
	private static final String UI_PROPERTIES_FILE_PATH = "/conf/ui.properties";
	private static Logger logger = ServerUtils.getLogger(UIProperties.class);
	private static Properties uiProperties;
	
	static {
		uiProperties = new Properties();
		try {
			uiProperties.load(new FileInputStream(ServerUtils.MBSA_CUSTOM_LOCATION.concat(UI_PROPERTIES_FILE_PATH)));
		} catch (Exception e) {
			logger.warn("UI properties loading failed, use defaults", e);
		}
	}

	/**
	 * получить формат отображения денежных величин в ячейках таблицы
	 * 
	 * @return	формат
	 */
	public static String getMonetaryAmountTableFormat() {
		return uiProperties.getProperty("table.monetaryamountcell.format", ",##0.00");
	}
	
	/**
	 * получить формат отображения денежных величин в редакторах
	 * 
	 * @return	формат
	 */
	public static String getMonetaryAmountEditorFormat() {
		return uiProperties.getProperty("editor.monetaryamount.format", ",##0.00");
	}

	/**
	 * получить формат отображения количественных величин в ячейках таблицы
	 * 
	 * @return	формат
	 */
	public static String getQuantityTableFormat() {
		return uiProperties.getProperty("table.quantitycell.format", ",##0.000");
	}
	
	/**
	 * получить формат отображения количественных величин в редакторах
	 * 
	 * @return	формат
	 */
	public static String getQuantityEditorFormat() {
		return uiProperties.getProperty("editor.quantity.format", ",##0.000");
	}

	/**
	 * получить свойство горизонтального выравнивания
	 * 
	 * @param name	имя свойства
	 * @param defaultValue	значение по умолчанию
	 * @return	значение свойства или {@link com.mg.framework.api.ui.HorizontalAlignment#LEFT} если не найдено
	 */
	public static HorizontalAlignment getHorizontalAlignmentProperty(String name, HorizontalAlignment defaultValue) {
		try {
			String value = uiProperties.getProperty(name);
			if (value == null)
				return defaultValue;
			else
				return HorizontalAlignment.valueOf(value.toUpperCase());
		} catch (Exception e) {
			logger.debug("No horizontal alignment", e);
			return HorizontalAlignment.LEFT;
		}
	}

	/**
	 * получить свойство горизонтального выравнивания числовых величин в ячейках таблицы
	 * 
	 * @return	значение свойства или {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} если не найдено
	 */
	public static HorizontalAlignment getNumberTableCellHorizontalAlignment() {
		return getHorizontalAlignmentProperty("table.numbercell.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * получить свойство горизонтального выравнивания денежных величин в ячейках таблицы
	 * 
	 * @return	значение свойства или {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} если не найдено
	 */
	public static HorizontalAlignment getMonetaryAmountTableCellHorizontalAlignment() {
		return getHorizontalAlignmentProperty("table.monetaryamountcell.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * получить свойство горизонтального выравнивания количественных величин в ячейках таблицы
	 * 
	 * @return	значение свойства или {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} если не найдено
	 */
	public static HorizontalAlignment getQuantityTableCellHorizontalAlignment() {
		return getHorizontalAlignmentProperty("table.quantitycell.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * получить свойство горизонтального выравнивания числовых величин в редакторах
	 * 
	 * @return	значение свойства или {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} если не найдено
	 */
	public static HorizontalAlignment getNumberEditorHorizontalAlignment() {
		return getHorizontalAlignmentProperty("editor.number.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * получить свойство горизонтального выравнивания денежных величин в редакторах
	 * 
	 * @return	значение свойства или {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} если не найдено
	 */
	public static HorizontalAlignment getMonetaryAmountEditorHorizontalAlignment() {
		return getHorizontalAlignmentProperty("editor.monetaryamount.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * получить свойство горизонтального выравнивания количественных величин в редакторах
	 * 
	 * @return	значение свойства или {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} если не найдено
	 */
	public static HorizontalAlignment getQuantityEditorHorizontalAlignment() {
		return getHorizontalAlignmentProperty("editor.quantity.horizontalalignment", HorizontalAlignment.RIGHT);
	}

}
