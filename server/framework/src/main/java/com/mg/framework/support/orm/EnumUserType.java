/*
 * EnumUserType.java
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
package com.mg.framework.support.orm;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.ParameterizedType;

/**
 * @author Oleg V. Safonov
 * @version $Id: EnumUserType.java,v 1.1 2006/11/02 15:57:59 safonov Exp $
 */
public class EnumUserType implements EnhancedUserType, ParameterizedType {
	private Class<? extends Enum> enumClass;

	public void setParameterValues(Properties parameters) {
		String enumClassName = parameters.getProperty("enumClass");
		try {
			enumClass = Thread.currentThread().getContextClassLoader().loadClass(enumClassName).asSubclass(Enum.class);
		} catch (ClassNotFoundException cnfe) {
			throw new HibernateException("Enum class not found", cnfe);
		}
	}

	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Enum) value;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		short ordinal = rs.getShort(names[0]);
		return rs.wasNull() ? null : enumClass.getEnumConstants()[ordinal];
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.SMALLINT);
		} else {
			st.setShort(index, (short) ((Enum) value).ordinal());
		}
	}

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	public Class returnedClass() {
		return enumClass;
	}

	public int[] sqlTypes() {
		return new int[] { Types.SMALLINT };
	}

	public Object fromXMLString(String xmlValue) {
		short ordinal = Short.parseShort(xmlValue);
        return enumClass.getEnumConstants()[ordinal];
	}

	public String objectToSQLString(Object value) {
		return String.valueOf(((Enum) value).ordinal());
	}

	public String toXMLString(Object value) {
		return String.valueOf(((Enum) value).ordinal());
	}

}
