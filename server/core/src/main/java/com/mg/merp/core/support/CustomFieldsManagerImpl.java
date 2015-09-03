/*
 * CustomFieldsManagerImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.core.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.Logger;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.api.metadata.DataItem;
import com.mg.framework.api.metadata.EntityCustomFieldsStorage;
import com.mg.framework.api.metadata.EntityCustomFieldsStorageAccessor;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetFactory;
import com.mg.framework.api.ui.widget.BoxPane;
import com.mg.framework.api.ui.widget.Button;
import com.mg.framework.api.ui.widget.ComboBox;
import com.mg.framework.api.ui.widget.DefaultMaintenancePane;
import com.mg.framework.api.ui.widget.EntityField;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.FeatureLinkServiceLocal;
import com.mg.merp.core.FeatureValServiceLocal;
import com.mg.merp.core.model.DataKind;

/**
 * Реализация менеджера управления пользовательскими полями
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomFieldsManagerImpl.java,v 1.5 2008/12/23 09:43:37 safonov Exp $
 */
public class CustomFieldsManagerImpl implements CustomFieldsManager {
	private static final String FEATURELINK_SERVICE = "merp/core/FeatureLink";
	private static final String FEATUREVAL_SERVICE = "merp/core/FeatureVal";
	private Logger logger = ServerUtils.getLogger(CustomFieldsManagerImpl.class);

	private class CustomFieldsDesc {
		private DataKind dataKind;
		private String code;
		private String name;
		private String beanName;
		private int featureId;
		private boolean isArray;
		private Integer arraySize;
		
		private CustomFieldsDesc(DataKind dataKind, String code, String name, String beanName, int featureId, boolean isArray, Integer arraySize) {
			super();
			this.dataKind = dataKind;
			this.code = code;
			this.name = name;
			this.beanName = beanName;
			this.featureId = featureId;
			this.isArray = isArray;
			this.arraySize = arraySize;
		}

	}
	
	private List<CustomFieldsDesc> loadCustomFieldsDescs(int classId) {
		List<CustomFieldsDesc> result = JdbcTemplate.getInstance().query("select f.id, f.code, f.name, f.datatype, f.is_array, f.array_size, c.bean_name from featurelink fl "
				+ "left join feature f on f.id = fl.feature_id "
				+ "left join sys_class c on c.id = f.entity_class_id "
				+ "where (fl.class_id = ?) and (fl.rec_id is null) order by f.priority", new Object[] {classId}, new RowMapper<CustomFieldsDesc>() {

			public CustomFieldsDesc mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new CustomFieldsDesc(DataKind.values()[rs.getShort("datatype")], rs.getString("code").trim(), rs.getString("name"), rs.getString("bean_name"), rs.getInt("id"), rs.getInt("is_array") == 1, (Integer) rs.getObject("array_size"));
			}
			
		});
		return result;
	}
	
	private String[] loadEnumCustomFieldValues(int featureId) {
		return ((FeatureValServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FEATUREVAL_SERVICE)).loadEnumValues(featureId);
	}
	
	private String generateWidgetId(String code, boolean sameLine, int index) {
		StringBuilder sb = new StringBuilder(Widget.WIDGET_ID).append("=\"").append(CUSTOM_FIELD_NAME_PREFIX).append(code);
		if (index != -1)
			sb.append(CustomFieldsManager.INDEX_DELIMITER).append(index); //добавим индекс для поля типа массив
		sb.append("\"").append(StringUtils.BLANK_STRING);
		if (index != -1)
			sb.append(BoxPane.ALIGNMENT).append("=\"expand_top\"").append(StringUtils.BLANK_STRING); //поля с типом массив "живут" в box
		if (sameLine)
			generateSameLine(sb);
		return sb.toString();
	}
	
	private void generateSameLine(StringBuilder sb) {
		sb.append(DefaultMaintenancePane.SAME_LINE).append("=\"true\"").append(StringUtils.BLANK_STRING);
	}
	
	private String generateLabelForWidget(String name, boolean showLabel) {
		if (showLabel)
			return new StringBuilder(Widget.LABEL).append("=\"").append(name).append("\"").append(StringUtils.BLANK_STRING).toString();
		else
			return StringUtils.EMPTY_STRING;
	}
	
	private void generateWidget(StringBuilder sb, CustomFieldsDesc customFieldsDesc, boolean sameLine, boolean showLabel, int index) {
		sb.append("<");
		String widgetId = generateWidgetId(customFieldsDesc.code, sameLine, index);
		switch (customFieldsDesc.dataKind) {
		case BOOLEAN:
			sb.append(WidgetFactory.CHECKBOX_WIDGET).append(StringUtils.BLANK_STRING)
					.append(widgetId)
					.append(Button.TEXT).append("=\"").append(customFieldsDesc.name).append("\"").append(StringUtils.BLANK_STRING);
			break;
		case DATA:
			sb.append(WidgetFactory.DATE_EDIT_WIDGET).append(StringUtils.BLANK_STRING)
					.append(widgetId)
					.append(generateLabelForWidget(customFieldsDesc.name, showLabel));
			break;
		case DOUBLE:
			sb.append(WidgetFactory.NUMBER_EDIT_WIDGET).append(StringUtils.BLANK_STRING)
					.append(widgetId)
					.append(generateLabelForWidget(customFieldsDesc.name, showLabel));
			break;
		case ENTITY:
			//find SearchHelp and entity vision properties
			ApplicationDictionary dic = ApplicationDictionaryLocator.locate();
			DataBusinessObjectService<?, ?> service = (DataBusinessObjectService<?, ?>) dic.getBusinessService(customFieldsDesc.beanName);
			DataItem dataItem = dic.getDataItem((ReflectionUtils.getClassReflectionMetadata(service.getEntityClass()).getDataItemName()));
			sb.append(WidgetFactory.ENTITY_EDIT_WIDGET).append(StringUtils.BLANK_STRING)
					.append(widgetId);
			if (dataItem.getSearchHelpName() != null)
				sb.append(EntityField.SEARCH_HELP).append("=\"").append(dataItem.getSearchHelpName()).append("\"").append(StringUtils.BLANK_STRING);
			if (dataItem.getEntityPropertyText() != null)
				sb.append(EntityField.ENTITY_PROPERTY_TEXT).append("=\"").append(dataItem.getEntityPropertyText()).append("\"").append(StringUtils.BLANK_STRING);
			if (dataItem.getEntityTextFormat() != null)
				sb.append(EntityField.ENTITY_FORMAT).append("=\"").append(dataItem.getEntityTextFormat()).append("\"").append(StringUtils.BLANK_STRING);
			sb.append(generateLabelForWidget(customFieldsDesc.name, showLabel));
			break;
		case ENUM:
			sb.append(WidgetFactory.COMBOBOX_WIDGET).append(StringUtils.BLANK_STRING)
					.append(widgetId)
					.append(generateLabelForWidget(customFieldsDesc.name, showLabel))
					.append(ComboBox.USE_INDEX).append("=\"false\"").append(">")
					.append("<").append(ComboBox.ITEMS).append(">");
			//заполним возможные значения
			for (String item : loadEnumCustomFieldValues(customFieldsDesc.featureId)) {
				sb.append("<").append(ComboBox.ITEM).append(StringUtils.BLANK_STRING)
						.append(ComboBox.ITEM_VALUE).append("=\"").append(item).append("\"/>");
			}
			sb.append("</").append(ComboBox.ITEMS).append(">")
					.append("</").append(WidgetFactory.COMBOBOX_WIDGET).append(">");
			break;
		case INTEGER:
			sb.append(WidgetFactory.INTEGER_EDIT_WIDGET).append(StringUtils.BLANK_STRING)
					.append(widgetId)
					.append(generateLabelForWidget(customFieldsDesc.name, showLabel));
			break;			
		case STRING:
			sb.append(WidgetFactory.TEXT_EDIT_WIDGET).append(StringUtils.BLANK_STRING)
					.append(widgetId)
					.append(generateLabelForWidget(customFieldsDesc.name, showLabel));
			break;
		default:
			throw new IllegalArgumentException("Invalid type");
		}
		if (customFieldsDesc.dataKind != DataKind.ENUM) //при этом типе тэг формируется полностью
			sb.append("/>");
	}
	
	private void doCloneStorage(int classSrcId, int classDstId, EntityCustomFieldsStorage storage, PersistentObject entityClone) {
		if (classSrcId == classDstId) {
			//классы бизнес-компонентов совпадают, просто установим значения пользовательских полей
			((EntityCustomFieldsStorageAccessor) entityClone).setStorage(storage);
		} else {
			Map<String, Object> customFieldValues = new HashMap<String, Object>();
			//перенесем только те пользовательские поля, которые связаны с двумя классами
			List<CustomFieldsDesc> srcCustomFields = loadCustomFieldsDescs(classSrcId);
			List<CustomFieldsDesc> dstCustomFields = loadCustomFieldsDescs(classDstId);
			for (CustomFieldsDesc dstCustomField : dstCustomFields) {
				for (CustomFieldsDesc srcCustomField : srcCustomFields) {
					if (dstCustomField.featureId == srcCustomField.featureId) {
						String code = CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX.concat(dstCustomField.code);
						customFieldValues.put(code, storage.getValue(code));
					}
				}
			}
			((EntityCustomFieldsStorageAccessor) entityClone).setStorage(new EntityCustomFieldsStorageImpl(customFieldValues));
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#loadFieldMetadatas(com.mg.framework.api.DataBusinessObjectService)
	 */
	public FieldMetadata[] loadFieldsMetadata(DataBusinessObjectService<?, ?> service) {
		if (service == null)
			throw new IllegalArgumentException("service is null");
		
		List<CustomFieldsDesc> descs = loadCustomFieldsDescs(service.getBusinessServiceMetadata().getIdentificator());
		List<FieldMetadata> result = new ArrayList<FieldMetadata>();
		for (CustomFieldsDesc desc : descs) {
			DataKind dataKind = desc.dataKind;
			FieldMetadata metaData = new FieldMetadata(CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX.concat(desc.code), dataKind.toBuiltInType(), dataKind.toJavaClass(), 0, desc.name);
			metaData.setHeader(desc.name);
			result.add(metaData);
		}
		return result.toArray(new FieldMetadata[result.size()]);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#generateMaintenanceArea(com.mg.framework.api.DataBusinessObjectService)
	 */
	public String generateMaintenanceArea(DataBusinessObjectService<?, ?> service) {
		List<CustomFieldsDesc> descs = loadCustomFieldsDescs(service.getBusinessServiceMetadata().getIdentificator());
		StringBuilder sb = new StringBuilder("<jfd:wrap-macros xmlns:jfd=\"http://xmlns.m-g.ru/jet/ui\">");
		int widgetCount = 0;
		for (CustomFieldsDesc desc : descs) {
			boolean sameLine = ++widgetCount % 2 == 0; //четные оставляем на той же линии
			if (desc.isArray) {
				sb.append("<").append(WidgetFactory.BOX_LAYOUT).append(" id=\"").append(desc.code).append("\" ")
						.append(generateLabelForWidget(desc.name, true))
						.append(BoxPane.ORIENTATION).append("=\"").append(BoxPane.VERTICAL_ORIENTATION).append("\" ");
				if (sameLine)
					generateSameLine(sb);
				sb.append(">");
				int count = desc.arraySize == null ? 1 : desc.arraySize;//впринципе если массив то размер массива должен быть задан
				for (int i = 0; i < count; i++)
					generateWidget(sb, desc, false, false, i);
				sb.append("</").append(WidgetFactory.BOX_LAYOUT).append(">");
			}
			else
				generateWidget(sb, desc, sameLine, true, -1);
		}
		sb.append("</jfd:wrap-macros>");
		String result = sb.toString();
		if (logger.isDebugEnabled())
			logger.debug("Maintenance area for custom fields of service: " + result);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#loadValues(com.mg.framework.api.DataBusinessObjectService)
	 */
	public Map<String, Object> loadValues(DataBusinessObjectService<?, ?> service, Object key) {
		return ((FeatureLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FEATURELINK_SERVICE)).loadValues(service.getBusinessServiceMetadata().getIdentificator(), key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#storeValues(java.util.Map, com.mg.framework.api.DataBusinessObjectService)
	 */
	public void storeValues(Map<String, Object> fieldsValues, DataBusinessObjectService<?, ?> service, Object key) {
		if (fieldsValues == null || fieldsValues.isEmpty())
			return;
		((FeatureLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FEATURELINK_SERVICE)).storeValues(fieldsValues, service.getBusinessServiceMetadata().getIdentificator(), key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#createStorage(com.mg.framework.api.DataBusinessObjectService, java.lang.Object)
	 */
	public EntityCustomFieldsStorage createStorage(DataBusinessObjectService<?, ?> service, Object key) {
		return new EntityCustomFieldsStorageImpl(loadValues(service, key));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#storeValues(com.mg.framework.api.metadata.EntityCustomFieldsStorage, com.mg.framework.api.DataBusinessObjectService, java.lang.Object)
	 */
	public void storeValues(EntityCustomFieldsStorage storage, DataBusinessObjectService<?, ?> service, Object key) {
		if (storage != null)
			storeValues(storage.getValues(), service, key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#removeValues(com.mg.framework.api.DataBusinessObjectService, java.lang.Object)
	 */
	public void removeValues(DataBusinessObjectService<?, ?> service, Object key) {
		((FeatureLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FEATURELINK_SERVICE)).removeValues(service.getBusinessServiceMetadata().getIdentificator(), key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#cloneStorage(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject)
	 */
	public void cloneValues(DataBusinessObjectService<?, ?> serviceSrc, PersistentObject entitySrc, DataBusinessObjectService<?, ?> serviceDst, PersistentObject entityDst) {
		if (!(entitySrc instanceof EntityCustomFieldsStorageAccessor
				&& entityDst instanceof EntityCustomFieldsStorageAccessor))
			return;
		
		EntityCustomFieldsStorage storage = ((EntityCustomFieldsStorageAccessor) entitySrc).getStorage();
		if (storage == null)
			//загрузим пользовательские поля если еще не грузили
			storage = createStorage(serviceSrc, entitySrc.getPrimaryKey());
		else
			//сделаем копию хранилища, чтобы не влиять на объект источник
			storage = new EntityCustomFieldsStorageImpl(new HashMap<String, Object>(storage.getValues()));
		
		doCloneStorage(serviceSrc.getBusinessServiceMetadata().getIdentificator(), 
				serviceDst.getBusinessServiceMetadata().getIdentificator(), storage, entityDst);
	}

}
