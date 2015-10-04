/*
 * FeatureLinkServiceBean.java
 *
 * Copyright (C) 1998 - 2004 Millennium Group. All rights reserved.
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.core.support;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.api.orm.JoinType;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.core.FeatureLinkServiceLocal;
import com.mg.merp.core.FeatureServiceLocal;
import com.mg.merp.core.model.DataKind;
import com.mg.merp.core.model.Feature;
import com.mg.merp.core.model.FeatureLink;
import com.mg.merp.core.model.SysClass;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Связи с бизнес-компонентами"
 *
 * @author leonova
 * @version $Id: FeatureLinkServiceBean.java,v 1.11 2008/12/23 09:41:33 safonov Exp $
 */
@Stateless(name = "merp/core/FeatureLinkService")
public class FeatureLinkServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FeatureLink, Integer> implements FeatureLinkServiceLocal {

  /* (non-Javadoc)
  * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
  */
  @Override
  protected void onValidate(ValidationContext context, FeatureLink entity) {
    context.addRule(new MandatoryAttribute(entity, "SysClass"));
  }

  @SuppressWarnings("unchecked")
  private Object convertStringToValue(Locale aLocale, DataKind dataKind, String beanName, String value) throws ParseException {
    if (value == null)
      return null;

    switch (dataKind) {
      case BOOLEAN:
        return "1".equals(value);
      case DATA:
        return DateFormat.getDateInstance(DateFormat.MEDIUM, aLocale).parse(value);
      case DOUBLE:
        return NumberFormat.getNumberInstance(aLocale).parse(value).doubleValue();
      case ENTITY:
        return ((DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService(beanName)).load(Integer.parseInt(value));
      case ENUM:
      case STRING:
        return value;
      case INTEGER:
        return Integer.parseInt(value);
      default:
        throw new IllegalArgumentException("Invalid type");
    }
  }

  private String convertValueToString(Locale aLocale, DataKind dataKind, Object value) {
    if (value == null)
      return null;

    switch (dataKind) {
      case BOOLEAN:
        return (Boolean) value ? "1" : "0";
      case DATA:
        return DateFormat.getDateInstance(DateFormat.MEDIUM, aLocale).format((Date) value);
      case DOUBLE:
        return NumberFormat.getNumberInstance(aLocale).format((Double) value);
      case ENTITY:
        return String.valueOf((Integer) ((PersistentObject) value).getPrimaryKey());
      case ENUM:
      case STRING:
        return (String) value;
      case INTEGER:
        return String.valueOf((Integer) value);
      default:
        throw new IllegalArgumentException("Invalid type");
    }
  }

  private int getIndexForArrayField(Map<String, Integer> indexMap, String fieldName) {
    int result = -1;
    if (indexMap.containsKey(fieldName))
      result = indexMap.get(fieldName);
    indexMap.put(fieldName, ++result);
    return result;
  }

  private void createFeatureLink(int classId, int entityId, Feature feature, String value) {
    FeatureLink fLink = initialize();
    fLink.setSysClass(getPersistentManager().find(SysClass.class, classId));
    fLink.setRecId((Integer) entityId);
    fLink.setFeature(feature);
    fLink.setVal(value);
    getPersistentManager().persist(fLink);
  }

  protected Map<String, Object> doLoadValues(int classId, Object entityId) {
    Map<String, Object> result = new HashMap<String, Object>();
    //если идентификатор сущности не задан, то будут загружены метаданные доп.признаков
    //реализация поддерживает только сущности имеющие Integer в качестве первичного ключа
    if (entityId != null && !(entityId instanceof Integer))
      return result;

    Locale locale = Locale.getDefault();
    Map<String, Integer> indexMap = new HashMap<String, Integer>();

    List<Object[]> values = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(FeatureLink.class, "fl")
        .add(Restrictions.eq("fl.SysClass.Id", classId)).add(Restrictions.or(Restrictions.eq("fl.RecId", entityId), Restrictions.isNull("fl.RecId")))
        .createCriteria("fl.Feature", "f").createCriteria("f.SysClass", "sc", JoinType.LEFT_JOIN)
        .setProjection(Projections.projectionList(Projections.property("f.Code"), Projections.property("f.DataType"), Projections.property("f.IsArray"), Projections.property("sc.BeanName"), Projections.property("fl.Val"), Projections.property("fl.RecId"), Projections.property("f.ArraySize")))
        .addOrder(Order.asc("f.Priority")).addOrder(Order.asc("fl.Id")));

    for (Object[] value : values) {
      String code = CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX.concat(((String) value[0]).trim());
      DataKind dataKind = (DataKind) value[1];
      String beanName = (String) value[3];
      String strVal = (String) value[4];
      boolean isData = value[5] != null;
      int arraySize = value[6] == null ? 1 : (Short) value[6];
      try {
        if ((Boolean) value[2]) { //is array
          Object list = result.get(code);
          if (list == null)
            list = Array.newInstance(Object.class, arraySize);
          if (isData) {
            int index = getIndexForArrayField(indexMap, code);
            //возможно уменьшили размер массива, и сохраненных значений больше чем размер созданного массива
            if (index < arraySize)
              Array.set(list, index, convertStringToValue(locale, dataKind, beanName, strVal));
          }
          result.put(code, list);
        } else {
          result.put(code, isData ? convertStringToValue(locale, dataKind, beanName, strVal) : null);
        }
      } catch (ParseException e) {
        getLogger().error("Error during load custom field: ".concat(code), e);
      }
    }

    return result;
  }

  protected void doStoreValues(Map<String, Object> fieldsValues, int classId, Object entityId) {
    FeatureServiceLocal featureService = (FeatureServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/core/Feature");
    Locale locale = Locale.getDefault();
    OrmTemplate tmpl = OrmTemplate.getInstance();

    for (String fldName : fieldsValues.keySet()) {
      String customFieldName = fldName.substring(5); //remove FEAT$ prefix;
      Feature feature = featureService.findByCode(customFieldName);
      if (feature == null)
        continue;

      List<FeatureLink> storedValues = tmpl.findByCriteria(OrmTemplate.createCriteria(FeatureLink.class)
          .add(Restrictions.eq("SysClass.Id", classId)).add(Restrictions.eq("RecId", entityId)).add(Restrictions.eq("Feature", feature)));

      Object value = fieldsValues.get(fldName);

      if (feature.isArray()) { //is array
        tmpl.bulkUpdate("delete from FeatureLink where Feature = :feature and SysClass.Id = :sysClassId and RecId = :recId", new String[]{"feature", "sysClassId", "recId"}, new Object[]{feature, classId, entityId});

        for (int i = 0; i < Array.getLength(value); i++)
          createFeatureLink(classId, (Integer) entityId, feature, convertValueToString(locale, feature.getDataType(), Array.get(value, i)));
      } else if (storedValues.isEmpty()) {
        //не было доп. признака и нет значения, не создаем
        if (value == null)
          continue;

        createFeatureLink(classId, (Integer) entityId, feature, convertValueToString(locale, feature.getDataType(), value));
      } else {
        //был доп. признак
        FeatureLink fLink = storedValues.get(0); //не массив, одно значение

        //если свойство стало не массивом из массива, удалим все кроме 0го, 0ой обрабатывается дальше
        for (int i = 1; i < storedValues.size(); i++) {
          getPersistentManager().remove(storedValues.get(i));
        }

        //изменим значение, или удалим если пустое
        if (value != null) {
          fLink.setVal(convertValueToString(locale, feature.getDataType(), value));
          getPersistentManager().merge(fLink);
        } else
          getPersistentManager().remove(fLink);
      }
    }
  }

  protected void doRemoveValues(int classId, Object entityId) {
    JdbcTemplate.getInstance().update("delete from featurelink where (class_id = ?) and (rec_id = ?)", new Object[]{classId, entityId});
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.FeatureLinkServiceLocal#loadValues(int, java.lang.Object)
   */
  @PermitAll
  public Map<String, Object> loadValues(int classId, Object entityId) {
    return doLoadValues(classId, entityId);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.FeatureLinkServiceLocal#storeValues(java.util.Map, int, java.lang.Object)
   */
  @PermitAll
  public void storeValues(Map<String, Object> fieldsValues, int classId, Object entityId) {
    doStoreValues(fieldsValues, classId, entityId);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.core.FeatureLinkServiceLocal#removeValues(int, java.lang.Object)
   */
  @PermitAll
  public void removeValues(int classId, Object entityId) {
    doRemoveValues(classId, entityId);
  }

}
