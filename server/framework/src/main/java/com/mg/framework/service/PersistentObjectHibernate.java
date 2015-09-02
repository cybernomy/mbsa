/*
 * PersistentObjectHibernate.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.service;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.intercept.LazyPropertyInitializer;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.Logger;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.api.metadata.EntityCustomFieldsStorage;
import com.mg.framework.api.metadata.EntityCustomFieldsStorageAccessor;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.BeanUtils;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;

/**
 * @author Oleg V. Safonov
 * $Id: PersistentObjectHibernate.java,v 1.19 2009/02/09 14:24:33 safonov Exp $
 *
 */
public class PersistentObjectHibernate implements PersistentObject, EntityCustomFieldsStorageAccessor {
    private static Logger log = ServerUtils.getLogger(PersistentObjectHibernate.class);
	private EntityCustomFieldsStorage customFieldsStorage = null;

    private static SessionFactory getFactory() {
        return PersistentManagerHibernateImpl.getFactory();
    }
    
    private static EntityMode getEntityMode() {
        return EntityMode.POJO;
    }
    
//	private static Object getAttr(Class clazz, Object object, String name) throws HibernateException {
//		org.hibernate.metadata.ClassMetadata meta = getFactory().getClassMetadata(clazz);
//		if (meta.getIdentifierPropertyName().equals(name))
//			return meta.getIdentifier(object, getEntityMode());
//		else
//			return meta.getPropertyValue(object, name, getEntityMode());
//	}

//	private static void setAttr(Class clazz, Object object, String name, Object value) throws HibernateException {
//		org.hibernate.metadata.ClassMetadata meta = getFactory().getClassMetadata(clazz);
//		if (meta.getIdentifierPropertyName().equals(name)) {
//			/*Serializable ident;
//			if (value instanceof Integer)
//				ident = new Long(((Integer) value).longValue());
//			else
//				ident = (Serializable) value;
//			meta.setIdentifier(object, ident, getEntityMode());*/
//		}
//		else
//			meta.setPropertyValue(object, name, value, getEntityMode());
//	}

    /*public PersistentObjectHibernate() {
        try {
            Field fld = this.getClass().getField("SysClient");
            fld.setAccessible(true);
            fld.set(this, 1);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }*/
    
    private static Method getGetterMethod(Class<?> clazz, String propertyName) {
        return ReflectionUtils.findGetter(clazz, propertyName);
    }
    
    private static Method getSetterMethod(Class<?> clazz, String propertyName) {
        return ReflectionUtils.findSetter(clazz, propertyName);
    }
    
	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentObject#getName()
	 */
	public String getEntityName() {
		return getFactory().getClassMetadata(ReflectionUtils.getEntityClass(this)).getEntityName();
	}

	public Object getPrimaryKey() {
		try {
			//return getFactory().getClassMetadata(this.getClass()).getIdentifier(this, getEntityMode());
            //return getFactory().getCurrentSession().getIdentifier(this);
			return getAttribute(getFactory().getClassMetadata(ReflectionUtils.getEntityClass(this)).getIdentifierPropertyName());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentObject#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String name) {
		if (name == null)
			throw new IllegalArgumentException("attribute name is null");
        if ("".equals(name))
            throw new IllegalArgumentException("attribute name is empty");
        if (log.isDebugEnabled())
            log.debug("get attribute with name: " + name);
		try {
            List<String> propList = StringUtils.split(name, ".");
            if (propList.size() == 0)
                throw new IllegalArgumentException("attribute list is empty: ".concat(name));
//			Field fld = this.getClass().getDeclaredField(propList.get(0));
//            fld.setAccessible(true);
//            Object result = fld.get(this);
            Object result = null;
            String firstFieldName = propList.get(0);
            if (isCustomField(firstFieldName))
            	result = getCustomField(firstFieldName);
            else {
                Method m = getGetterMethod(this.getClass(), firstFieldName);
                if (m == null)
                    throw new IllegalArgumentException("attribute not found: ".concat(firstFieldName));
                result = m.invoke(this, new Object[] {});
            }

            if ((result != null) && propList.size() > 1) {
                if (!(result instanceof PersistentObjectHibernate))
                    throw new IllegalStateException("property is not PersistentObjectHibernate: ".concat(name));
                propList.remove(0);
                result = ((PersistentObjectHibernate) result).getAttribute(StringUtils.join(propList, "."));
            }
            
//            if (result instanceof String)
//                result = ((String) result).trim();
            if (log.isDebugEnabled()) {
            	Object val = result;
            	if (result instanceof PersistentObject)
            		val = org.hibernate.pretty.MessageHelper.infoString(ReflectionUtils.getEntityClass(this).getName(), (Serializable) BeanUtils.getIdentifierProperty(result));
                log.debug("get attribute with name: " + name + ", value: " + val);
            }
            return result;
			//return ReflectHelper.getGetter(this.getClass(), name).get(this);
			//return getAttr(this.getClass(), this, name);
		}
//        catch (NoSuchFieldException e) {
//            throw new IllegalArgumentException(e);
//        }
        catch (RuntimeException e) {
            throw e;
        }
		catch (Exception e) {
            log.error("Could not get attribure: " + name, e);
			throw new RuntimeException(e);
		}
	}

    private static boolean isEntityReference(Class<?> clazz, String propertyName) {
        //TODO move to config file or replace reference to table in database
        String className = clazz.getName();
        return "com.mg.merp.reference.model.Currency".equals(className) && "Code".equals(propertyName) //currency
            || "com.mg.merp.document.model.DocType".equals(className) && "Code".equals(propertyName); //doctype
    }
    
    @SuppressWarnings("unchecked")
	private static Object castValueType(Class<?> clazz, Object value) {
        if (((short.class.isAssignableFrom(clazz)) || (Short.class.isAssignableFrom(clazz)))
                && !(value instanceof Short)) {
            if (value instanceof Integer)
                return ((Integer) value).shortValue();
            else if (value instanceof Long)
                return ((Long) value).shortValue();
            else if (value instanceof String)
            	return Short.valueOf((String) value);
            else
                return value;
        } else if (((int.class.isAssignableFrom(clazz)) || (Integer.class.isAssignableFrom(clazz)))
                && !(value instanceof Integer)) {
            if (value instanceof Short)
                return ((Short) value).intValue();
            if (value instanceof Long)
                return ((Long) value).intValue();
            else if (value instanceof String)
            	return Integer.valueOf((String) value);
            else
                return value;
        } else if (BigDecimal.class.isAssignableFrom(clazz) && !(value instanceof BigDecimal)) {
            if (value != null)
                return new BigDecimal(value.toString());
            else if (value instanceof String)
            	return BigDecimal.valueOf(Double.valueOf((String) value));
            else
                return null;
        } else if (Enum.class.isAssignableFrom(clazz) && !(value instanceof Enum)) {
        	Class<? extends Enum> enumClass = clazz.asSubclass(Enum.class);
        	int ordinal = -1;
        	if (value instanceof Short)
        		ordinal = ((Short) value).intValue();
        	else if (value instanceof Integer)
        		ordinal = ((Integer) value).intValue();
        	if (ordinal == -1)
        		return null;
        	else
        		return enumClass.getEnumConstants()[ordinal];
        } else
            return value;
    }
    
    private boolean isCustomField(String name) {
    	return name.startsWith(CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX);
    }
    
	private void setCustomField(String name, Object value) {
    	if (customFieldsStorage != null)
    		customFieldsStorage.setValue(name, value);
    }
    
    private Object getCustomField(String name) {
    	if (customFieldsStorage != null)
    		return customFieldsStorage.getValue(name);
    	else
    		return null;
    }
    
	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.EntityCustomFieldsStorageAccessor#getStorage()
	 */
	public EntityCustomFieldsStorage getStorage() {
		return customFieldsStorage;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.EntityCustomFieldsStorageAccessor#setStorage(com.mg.framework.api.metadata.EntityCustomFieldsStorage)
	 */
	public void setStorage(EntityCustomFieldsStorage storage) {
		this.customFieldsStorage = storage;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentObject#setAttribute(java.lang.String, java.lang.Object)
	 */
	public void setAttribute(String name, Object value) {
        if (name == null)
            throw new IllegalArgumentException("attribute name is null");
        if ("".equals(name))
            throw new IllegalArgumentException("attribute name is empty");
        if (log.isDebugEnabled())
            log.debug("set attribute with name: " + name + ", value: " + value);
		try {
            List<String> propList = StringUtils.split(name, ".");
            int size = propList.size();
            if (size > 2)
                return;
            if (size == 0)
                throw new IllegalArgumentException("attribute list is empty");
            
            if (size == 1) {
                //Field fld = this.getClass().getDeclaredField(name);
                //fld.setAccessible(true);
                //fld.set(this, value);
                //setFieldValue(this, fld, value);
            	if (isCustomField(name))
            		setCustomField(name, value);
            	else {
                    Method m = getSetterMethod(this.getClass(), name);
                    if (m == null)
                        throw new IllegalArgumentException("attribute not found: ".concat(name));
                    m.invoke(this, new Object[] {castValueType(m.getParameterTypes()[0], value)});
            	}
            }
            else {
                //устанавливаем таким способом ссылку на объект, второе название атрибута
                //должно быть первичным ключом объекта на который ссылаемся
                String fldName = propList.get(0);
                //Field fld = this.getClass().getDeclaredField(fldName);
                //fld.setAccessible(true);
                Method m = getSetterMethod(this.getClass(), fldName);
                if (m == null)
                    throw new IllegalArgumentException("attribute not found: ".concat(fldName));
                Class<?> fldType = m.getParameterTypes()[0];
                if (!(PersistentObjectHibernate.class.isAssignableFrom(fldType)))
                    throw new IllegalStateException("property is not PersistentObjectHibernate");
                org.hibernate.metadata.ClassMetadata meta = getFactory().getClassMetadata(fldType);
                fldName = propList.get(1);
                if (meta.getIdentifierPropertyName().equals(fldName)) {
                    if (value == null)
                        //fld.set(this, null);
                        m.invoke(this, new Object[] {null});
                    else
                        //fld.set(this, getFactory().getCurrentSession().get(fld.getType().getName(), (Serializable) value));
                        m.invoke(this, new Object[] {getFactory().getCurrentSession().get(fldType.getName(), (Serializable) value)});
                } else if (isEntityReference(fldType, fldName)) {
                    //stupid variant for mapping legacy data where a foreign key refers to a unique
                    //key of the associated table other than the primary key.
                    List<?> list = getFactory().getCurrentSession().createCriteria(fldType)
                        .add(Restrictions.eq(fldName, value)).list();
                    if (list.size() > 0)
                        value = list.get(0);
                    else
                        value = null;
                    m.invoke(this, new Object[] {value});
                }
            }
            //do not set primary key
            //org.hibernate.metadata.ClassMetadata meta = getFactory().getClassMetadata(this.getClass());
            //if (!meta.getIdentifierPropertyName().equals(name)) {
//                Field fld = this.getClass().getDeclaredField(name);
//                fld.setAccessible(true);
//                fld.set(this, value);
            //}
			//setAttr(this.getClass(), this, name, value);
		}
//        catch (NoSuchFieldException e) {
//            throw new IllegalArgumentException(name, e);
//        }
//        catch (RuntimeException e) {
//            throw e;
//        }
		catch (Exception e) {
            log.error("Could not set attribure: " + name, e);
			throw new RuntimeException(name, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentObject#getAttributes(java.util.Collection)
	 */
	public AttributeMap getAttributes(Collection<String> keyOfAttributes) {
		AttributeMap result = new com.mg.framework.support.LocalDataTransferObject();
		for (String name : keyOfAttributes)
			result.put(name, getAttribute(name));
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentObject#getAllAttributes()
	 */
	public AttributeMap getAllAttributes() {
		org.hibernate.metadata.ClassMetadata meta = getFactory().getClassMetadata(ReflectionUtils.getEntityClass(this));
		String[] props = meta.getPropertyNames();
		Object[] values = meta.getPropertyValues(this, getEntityMode());
		AttributeMap result = new com.mg.framework.support.LocalDataTransferObject();
		for (int i = 0; i < props.length; i++) {
			if (values[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY) {
				//http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4664
				//используем стандартный способ получения атрибута, для загрузки lazy атрибутов
				//сущность должна реализовывать get и set методы специальным образом
				values[i] = getAttribute(props[i]);
			}
			result.put(props[i], values[i]);
		}
		//put identifier
		result.put(meta.getIdentifierPropertyName(), meta.getIdentifier(this, getEntityMode()));
		//put custom fields
		if (customFieldsStorage != null)
			result.putAll(customFieldsStorage.getValues());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentObject#setAttributes(com.mg.framework.api.AttributeMap)
	 */
	public void setAttributes(AttributeMap values) {
		if (values == null)
			return;

		for (String name : values.keySet())
        	setAttribute(name, values.get(name));
	}

	/**
	 * реализация копирования сущности
	 * 
	 * @return копия
	 */
	protected PersistentObject doCloneEntity(AttributeMap attributes) {
		PersistentObjectHibernate result = null;
		try {
			Class<PersistentObjectHibernate> clazz = ReflectionUtils.getEntityClass(this);
			result = clazz.newInstance();
			org.hibernate.metadata.ClassMetadata meta = getFactory().getClassMetadata(clazz);
			Object[] values = meta.getPropertyValues(this, getEntityMode());
			//удалим коллекции, Hibernate ругается когда шарится одна коллекция между разными сущностями
			org.hibernate.type.Type[] types = meta.getPropertyTypes();
			for (int i = 0; i < types.length; i++)
				if (types[i].isCollectionType())
					values[i] = null;
			meta.setPropertyValues(result, values, getEntityMode());
			result.setAttributes(attributes);
		} catch (InstantiationException e) {
			log.error("clone entity failed", e);
		} catch (IllegalAccessException e) {
			log.error("clone entity failed", e);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentObject#cloneEntity()
	 */
	public PersistentObject cloneEntity(AttributeMap attributes) {
		return doCloneEntity(attributes);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentObject#hasAttribute(java.lang.String)
	 */
	public boolean hasAttribute(String name) {
		org.hibernate.metadata.ClassMetadata meta = getFactory().getClassMetadata(ReflectionUtils.getEntityClass(this));
		List<String> names = Arrays.asList(meta.getPropertyNames());
		return names.contains(name) || meta.getIdentifierPropertyName().equals(name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PersistentObject))
			return false;
		if (obj == this)
			return true;
		PersistentObject po = (PersistentObject) obj;
		//проверим принадлежат ли объекты одной иерархии
		if (ReflectionUtils.getEntityClass(po).isAssignableFrom(ReflectionUtils.getEntityClass(this))
				|| ReflectionUtils.getEntityClass(this).isAssignableFrom(ReflectionUtils.getEntityClass(po))) {
			//проверяем по первичным ключам, если хотя бы один ключ null, то объекты не равны
			Object thisPk = getPrimaryKey();
			Object objPk = ((PersistentObject) obj).getPrimaryKey();
			return (thisPk != null) && (objPk != null) && thisPk.equals(objPk);
		} else
			return false;
	}

}
