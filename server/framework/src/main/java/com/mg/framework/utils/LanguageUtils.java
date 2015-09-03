/**
 * LanguageUtils.java
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.Language;

/**
 * Языковые утилиты
 * 
 * @author Oleg V. Safonov
 * @version $Id: LanguageUtils.java,v 1.1 2008/04/09 14:32:07 safonov Exp $
 */
public class LanguageUtils {
	private static Logger log;
	private static Language[] languages;

	static {
		log = ServerUtils.getLogger(LanguageUtils.class);
		
		//загрузка языков поддерживаемых системой
		//в каталоге <appserver>/<conf>/mg-custom/languages находится список языков
		//для каждого языка необходимо создать файл <язык>.language имеющего формат
		//файла свойств, возможны следующие параметры:
		//name имя языка отображаемого в интерфейсе пользователя
		//language 2х буквенный код языка
		//country 2х буквенный код страны
		//variant вариант языка (необязательный параметр)
		try {
			File languagesDescDir = new File(new StringBuilder(ServerUtils.MBSA_CUSTOM_LOCATION).append(File.separatorChar).append("languages").toString());
			File[] languagesDesc = languagesDescDir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith("language");
				}
			});
			List<Language> list = new ArrayList<Language>();
			for (File file : languagesDesc) {
				Language lang = loadLanguage(file);
				if (lang != null)
					list.add(lang);
			}
			languages = list.toArray(new Language[list.size()]);
		} catch (Exception e) {
			log.warn("Languages load failure, use defaults (English, Russian)", e);
			languages = new Language[] {new Language("English", "en", "EN", null), new Language("\u0420\u0443\u0441\u0441\u043a\u0438\u0439 (Russian)", "ru", "RU", null)};
		}		
	}
	
	private static Language loadLanguage(File file) {
		if (file == null)
			return null;
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
			return new Language(prop.getProperty("name"), prop.getProperty("language"), prop.getProperty("country"), prop.getProperty("variant"));
		} catch (Exception e) {
			log.warn("Couldn't load languge from file: " + file.getName(), e);
			return null;
		}
	}
	
	/**
	 * получить список имен поддерживаемых языков
	 * 
	 * @return	список имен
	 */
	public static String[] getNames() {
		String[] result = new String[languages.length];
		for (int i = 0; i < languages.length; i++) //не используем итераторы, т.к. мультипоточность
			result[i] = languages[i].getName();
		return result;
	}

	/**
	 * получить описание языка по локали
	 * 
	 * @param locale	локаль
	 * @return	описатель языка
	 */
	public static Language getLanguage(Locale locale) {
		if (locale == null)
			return null;
		
		for (int i = 0; i < languages.length; i++) //не используем итераторы, т.к. мультипоточность
			if (languages[i].getLocale().equals(locale))
				return languages[i];
		
		return null;
	}
	
	/**
	 * получить описание языка по имени
	 * 
	 * @param languageName	имя
	 * @return	описатель языка
	 */
	public static Language getLanguage(String languageName) {
		if (languageName == null)
			return null;
		
		for (int i = 0; i < languages.length; i++) //не используем итераторы, т.к. мультипоточность
			if (languages[i].getName().equals(languageName))
				return languages[i];
		
		return null;
	}
	
	/**
	 * получить локаль по имени языка
	 * 
	 * @param languageName	имя
	 * @return	локаль
	 */
	public static Locale getLocale(String languageName) {
		Language language = getLanguage(languageName);
		return language != null ? language.getLocale() : null;
	}
	
}
