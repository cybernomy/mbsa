/*
 * UIProfilePropertiesImpl.java
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
package com.mg.merp.security.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.UIProfile;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.security.model.SecUserProfileItem;

/**
 * Реализация профиля пользовательского интерфейса основанного на стандартном классе {@link java.util.Properties}
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIProfilePropertiesImpl.java,v 1.3 2008/10/08 14:45:02 safonov Exp $
 */
public class UIProfilePropertiesImpl implements UIProfile, Serializable {
	private static Logger logger = ServerUtils.getLogger(UIProfilePropertiesImpl.class);
	private String name;
	private Properties properties;
	private SecUserProfileItem item;
	private int checkSum;
	private Integer oldCheckSum;

	public UIProfilePropertiesImpl(String name, SecUserProfileItem item) {
		this.name = name;
		this.item = item;
		this.properties = new Properties();
		if (item != null && item.getData() != null) {
			try {
				oldCheckSum = item.getCheckSum();
				InputStream in = new ByteArrayInputStream(item.getData());
				this.properties.loadFromXML(in);
				if (logger.isDebugEnabled())
					logger.debug(String.format("Profile name: %s, contents: %s", name, properties.toString()));
			} catch (Exception e) {
				logger.error("UI profile loading error", e);
			}
		}
	}

	private int calculateCheckSum(byte[] data) {
		Checksum checksumEngine = new Adler32();
	    checksumEngine.update(data, 0, data.length);
	    return (int) checksumEngine.getValue();
	}
	
	protected SecUserProfileItem getUserProfileItem() {
		if (item == null) {
			item = new SecUserProfileItem();
			item.setCode(name);
		}
		
		if (logger.isDebugEnabled())
			logger.debug(String.format("Profile name: %s, contents: %s", name, properties.toString()));
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			properties.storeToXML(os, null);
			item.setData(os.toByteArray());
			checkSum = calculateCheckSum(item.getData());
			item.setCheckSum(checkSum);
		} catch (Exception e) {
			logger.error("UI profile storing error", e);
		}
		
		return item;
	}
	
	protected boolean isDirty() {
		return oldCheckSum == null || oldCheckSum != checkSum;
	}
	
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#getProperty(java.lang.String)
	 */
	public String getProperty(String name) {
		return properties.getProperty(name);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#getProperty(java.lang.String, java.lang.String)
	 */
	public String getProperty(String name, String defaultValue) {
		return properties.getProperty(name, defaultValue);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#setProperty(java.lang.String, java.lang.String)
	 */
	public void setProperty(String name, String value) {
		if (value == null)
			properties.remove(name);
		else
			properties.setProperty(name, value);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#getProperty(java.lang.String, boolean)
	 */
	public boolean getProperty(String name, boolean defaultValue) {
		String str = properties.getProperty(name);
		if (str != null)
			return Boolean.parseBoolean(str);
		else
			return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#getProperty(java.lang.String, double)
	 */
	public double getProperty(String name, double defaultValue) {
		String str = properties.getProperty(name);
		if (str != null)
			return Double.parseDouble(str);
		else
			return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#getProperty(java.lang.String, float)
	 */
	public float getProperty(String name, float defaultValue) {
		String str = properties.getProperty(name);
		if (str != null)
			return Float.parseFloat(str);
		else
			return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#getProperty(java.lang.String, int)
	 */
	public int getProperty(String name, int defaultValue) {
		String str = properties.getProperty(name);
		if (str != null)
			return Integer.parseInt(str);
		else
			return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#getProperty(java.lang.String, long)
	 */
	public long getProperty(String name, long defaultValue) {
		String str = properties.getProperty(name);
		if (str != null)
			return Long.parseLong(str);
		else
			return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#getProperty(java.lang.String, short)
	 */
	public short getProperty(String name, short defaultValue) {
		String str = properties.getProperty(name);
		if (str != null)
			return Short.parseShort(str);
		else
			return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#setProperty(java.lang.String, boolean)
	 */
	public void setProperty(String name, boolean value) {
		properties.setProperty(name, String.valueOf(value));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#setProperty(java.lang.String, double)
	 */
	public void setProperty(String name, double value) {
		properties.setProperty(name, String.valueOf(value));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#setProperty(java.lang.String, float)
	 */
	public void setProperty(String name, float value) {
		properties.setProperty(name, String.valueOf(value));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#setProperty(java.lang.String, int)
	 */
	public void setProperty(String name, int value) {
		properties.setProperty(name, String.valueOf(value));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#setProperty(java.lang.String, long)
	 */
	public void setProperty(String name, long value) {
		properties.setProperty(name, String.valueOf(value));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.UIProfile#setProperty(java.lang.String, short)
	 */
	public void setProperty(String name, short value) {
		properties.setProperty(name, String.valueOf(value));
	}

}
