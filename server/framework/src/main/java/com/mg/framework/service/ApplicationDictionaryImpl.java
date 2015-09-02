/*
 * ApplicationDictionaryImpl.java
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
package com.mg.framework.service;

import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.lang.ArrayUtils;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.Logger;
import com.mg.framework.api.ReasonException;
import com.mg.framework.api.Session;
import com.mg.framework.api.UserProfile;
import com.mg.framework.api.annotations.EntityPropertyText;
import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.metadata.BusinessServiceImplKind;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.api.metadata.DataItem;
import com.mg.framework.api.metadata.Domain;
import com.mg.framework.api.metadata.ReflectionMetadata;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.DetachedCriteria;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.Subqueries;
import com.mg.framework.api.ui.CustomActionManager;
import com.mg.framework.api.ui.MaintenanceBrowseForm;
import com.mg.framework.api.ui.RuntimeMacrosLoader;
import com.mg.framework.support.Messages;
import com.mg.framework.support.metadata.DataItemImpl;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ContextUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;

/**
 * Реализация репозитария метаданных системы.
 * 
 * @author Oleg V. Safonov
 * @version $Id: ApplicationDictionaryImpl.java,v 1.15 2008/12/08 06:13:57 safonov Exp $
 */
public class ApplicationDictionaryImpl implements ApplicationDictionary {
	private Logger log = ServerUtils.getLogger(getClass());

	private class MaintenanceFormMacrosLoader implements RuntimeMacrosLoader {
		private DataBusinessObjectService<?, ?> service;

		private MaintenanceFormMacrosLoader(DataBusinessObjectService<?, ?> service) {
			this.service = service;
		}
		
		public String loadMacros(String name) {
			if (CustomFieldsManager.CUSTOM_FIELDS_AREA_MACROS.equals(name))
				return CustomFieldsManagerLocator.locate().generateMaintenanceArea(service);
			return null;
		}
		
	}

	private class BrowseFormFormletLoader implements RuntimeMacrosLoader {
		private DataBusinessObjectService<?, ?> service;

		private BrowseFormFormletLoader(DataBusinessObjectService<?, ?> service) {
			this.service = service;
		}
		
		public String loadMacros(String name) {
			if (CustomActionManager.CUSTOM_ACTIONS_AREA_FORMLET.equals(name))
				return CustomActionManagerLocator.locate().generateActionsArea(service);
			return null;
		}
		
	}

	private com.mg.framework.api.ui.Form loadForm(int classId, String formName, RuntimeMacrosLoader runtimeMacrosLoader) throws ApplicationException {
		if (log.isDebugEnabled())
			log.debug("load UI form: " + formName);
		
		DetachedCriteria dc = DetachedCriteria.forEntityName("com.mg.merp.core.model.Form", "fLayer")
				.setProjection(Projections.max("fLayer.ApplicationLayer"))
				.add(Restrictions.eqProperty("fLayer.Name", "f.Name"))
				.add(Restrictions.eqProperty("fLayer.SysClass", "f.SysClass"));
		List<String> formControllerImplNames = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria("com.mg.merp.core.model.Form", "f")
				.setProjection(Projections.property("f.Implementation"))
				.add(Restrictions.eq("f.Name", formName))
				.add(Restrictions.eq("f.SysClass.Id", classId))
				.add(Restrictions.or(Restrictions.isNull("f.Role"), Restrictions.in("f.Role.Id", (Object[]) ArrayUtils.toObject(ServerUtils.getUserProfile().getGroups()))))
				.add(Restrictions.or(Restrictions.isNull("f.User"), Restrictions.eq("f.User.Id", ServerUtils.getUserProfile().getIdentificator())))
				.add(Subqueries.propertyEq("f.ApplicationLayer", dc))
				.setCacheable(true)
				.setCacheRegion("com/mg/jet/ui/forms/")
				.setFlushMode(FlushMode.MANUAL));
		if (formControllerImplNames.size() == 0) {
			log.warn("Form is not found: ".concat(formName));
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.FORM_NOT_FOUND, new Object[] {formName}));
		}
		String formControllerImplName = formControllerImplNames.get(0);
		if (formControllerImplName == null)
			throw new IllegalStateException("Name of form controller is null. Form " + formName);
		return UIProducer.produceForm(formControllerImplName, runtimeMacrosLoader);
	}

	private com.mg.framework.api.ui.Form loadWindow(String windowName) {
		if (log.isDebugEnabled())
			log.debug("load UI window: " + windowName);

		DetachedCriteria dc = DetachedCriteria.forEntityName("com.mg.merp.core.model.Window", "wLayer")
				.setProjection(Projections.max("wLayer.ApplicationLayer"))
				.add(Restrictions.eqProperty("wLayer.Name", "w.Name"));
		List<String> windowControllerImplNames = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria("com.mg.merp.core.model.Window", "w")
				.setProjection(Projections.property("w.Implementation"))
				.add(Restrictions.eq("w.Name", windowName))
				.add(Restrictions.or(Restrictions.isNull("w.Role"), Restrictions.in("w.Role.Id", (Object[]) ArrayUtils.toObject(ServerUtils.getUserProfile().getGroups()))))
				.add(Restrictions.or(Restrictions.isNull("w.User"), Restrictions.eq("w.User.Id", ServerUtils.getUserProfile().getIdentificator())))
				.add(Subqueries.propertyEq("w.ApplicationLayer", dc))
				.setCacheable(true)
				.setCacheRegion("com/mg/jet/ui/windows/")
				.setFlushMode(FlushMode.MANUAL));
		String windowControllerImplName = windowName;
		if (windowControllerImplNames.size() == 0) {
			log.warn("Window implementation is not found, use window name as implementation: ".concat(windowName));
		} else {
			if (windowControllerImplNames.get(0) != null)
				windowControllerImplName = windowControllerImplNames.get(0);
			else
				log.warn("Window implementation is null, use window name as implementation: ".concat(windowName));
		}
		return UIProducer.produceForm(windowControllerImplName);
	}
	
	/**
	 * рекурсивная загрузка элемента данных
	 * 
	 * @param name
	 * @return
	 */
	private DataItem loadDataItem(String name) {
		if (log.isDebugEnabled())
			log.debug("load dataitem: " + name);

		DetachedCriteria dc = DetachedCriteria.forEntityName("com.mg.merp.core.model.SysDataItem", "diLayer")
				.setProjection(Projections.max("diLayer.ApplicationLayer"))
				.add(Restrictions.eqProperty("diLayer.Name", "di.Name"));
		DataItem model = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria("com.mg.merp.core.model.SysDataItem", "di")
				.add(Restrictions.eq("di.Name", name))
				.add(Subqueries.propertyEq("di.ApplicationLayer", dc))
				.setCacheable(true)
				.setCacheRegion("com/mg/jet/ui/dataitems/")
				.setFlushMode(FlushMode.MANUAL));
		if (model == null) {
			log.warn("DataItem is not found: ".concat(name));
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.DATAITEM_NOT_FOUND, new Object[] {name}));
		}
		switch (model.getKind()) {
		case DOMAIN:
			return model;
		case REFERENCE:
			//если ссылка, то в качестве модели возмем элемент даннных на который ссылаемся
			return loadDataItem((String) (((PersistentObject) model).getAttribute("ReferenceDataItemName")));
		default:
			throw new IllegalStateException();
		}		
	}
	
	private DataItem findDataItem(String name) {
		//TODO implement cache
		return new DataItemImpl(loadDataItem(name));
	}
	
	private void checkLicense(PersistentObject subsystemObject) {
		//компонент должен быть связан с модулем, непонятно что делать если не связан
		if (subsystemObject == null)
			return;
		Session session = ServerUtils.getCurrentSession();
		//интерактивный пользователь обязательно должен иметь сессию, другие пользователи не лицензируются
		if (session == null || !session.isInteractive())
			return;
		UserProfile up = session.getWorkingConnection().getUserProfile();
		if (up == null)
			return;
		String subsystemName = (String) subsystemObject.getAttribute("Name");
		for (String subsystem : up.getPermittableSubsystems())
			if (subsystemName.equalsIgnoreCase(subsystem))
				return;
		//модуля нет в списке доступных
		throw new LicenseException(Messages.getInstance().getMessage(Messages.SUBSYSTEM_ACCESS_DENIED, new Object[] {subsystemName}));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ApplicationDictionary#getDomain(java.lang.String)
	 */
	public Domain getDomain(String name) {
		if (log.isDebugEnabled())
			log.debug("load domain: " + name);

		Domain domain = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria("com.mg.merp.core.model.SysDomain", "d")
				.add(Restrictions.eq("d.Name", name))
				.setCacheable(true)
				.setCacheRegion("com/mg/jet/ui/domains/")
				.setFlushMode(FlushMode.MANUAL));
		if (domain == null) {
			log.warn("Domain is not found: ".concat(name));
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.DOMAIN_NOT_FOUND, new Object[] {name}));
		}
		return domain;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ApplicationDictionary#getDataItem(java.lang.String)
	 */
	public DataItem getDataItem(String name) {
		return findDataItem(name);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ApplicationDictionary#getFieldMetadata(java.lang.Class, java.lang.String)
	 */
	public FieldMetadata getFieldMetadata(Class<? extends PersistentObject> entityClazz, String propertyName) {
		return getFieldMetadata(ReflectionUtils.getPropertyReflectionMetadata(entityClazz, propertyName));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ApplicationDictionary#getFieldMetadata(java.lang.String)
	 */
	public FieldMetadata getFieldMetadata(ReflectionMetadata propertyMetadata) {
		if (propertyMetadata == null)
			throw new NullPointerException("Metadata is null");
		String dataItemName = propertyMetadata.getDataItemName();
		Class<?> propertyClass = propertyMetadata.getPropertyType();
		if (!StringUtils.stringNullOrEmpty(dataItemName)) {
			if (log.isDebugEnabled())
				log.debug("create field metadata, dataitem: " + dataItemName);
			
			FieldMetadata result = new FieldMetadata(getDataItem(dataItemName), propertyMetadata.getPropertyType());
			
			if (Enum.class.isAssignableFrom(propertyClass))
				result.setEnumConstantsText(UIUtils.enumToEnumConstantsText(propertyClass.asSubclass(Enum.class)));
			else if (PersistentObject.class.isAssignableFrom(propertyClass)) {
				if (result.getEntityPropertyText() == null) {
					//загрузим из анотаций если не было установлено в описании элемента данных
					EntityPropertyText entityPropertyText = propertyClass.getAnnotation(EntityPropertyText.class);
					if (entityPropertyText != null) {
						result.setEntityPropertyText(entityPropertyText.value());
						result.setEntityPropertyFormatText(UIUtils.loadL10nText(entityPropertyText.format()));
					}
				}
			}

			//установим SearchHelp если указан дополнительно на атрибуте сущности
			String searchHelpName = propertyMetadata.getSearchHelpName();
			if (!StringUtils.stringNullOrEmpty(searchHelpName))
				result.setSearchHelp(SearchHelpProcessor.createSearch(searchHelpName));
			
			return result;
		}
		else {
			if (log.isDebugEnabled())
				log.debug("create field metadata, class: " + propertyClass.getName());
			//если нет связи с элементом данных, то пытаемся создать метаданные
			//на основании значения поля
			FieldMetadata result = new FieldMetadata(propertyClass.getName(), MiscUtils.javaTypeToBuiltInType(propertyClass), propertyClass, 0, "");
			if (Enum.class.isAssignableFrom(propertyClass))
				result.setEnumConstantsText(UIUtils.enumToEnumConstantsText(propertyClass.asSubclass(Enum.class)));
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ApplicationDictionary#getMaintenaceForm(java.lang.String, java.lang.String)
	 */
	public com.mg.framework.api.ui.Form getMaintenaceForm(DataBusinessObjectService<?, ?> service, String formName) {
		if (service == null)
			throw new NullPointerException("Service is null");
		String name;
		if (StringUtils.stringNullOrEmpty(formName))
			name = com.mg.framework.api.ui.Form.DEFAULT_MAINTENANCE_NAME;
		else
			name = formName;
		return loadForm(service.getBusinessServiceMetadata().getIdentificator(), name, new MaintenanceFormMacrosLoader(service));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ApplicationDictionary#getBrowseForm(com.mg.framework.api.DataBusinessObjectService, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public com.mg.framework.api.ui.Form getBrowseForm(DataBusinessObjectService<?, ?> service, String formName) {
		if (service == null)
			throw new NullPointerException("Service is null");
		String name;
		if (StringUtils.stringNullOrEmpty(formName))
			name = com.mg.framework.api.ui.Form.DEFAULT_BROWSE_NAME;
		else
			name = formName;
		MaintenanceBrowseForm result = (MaintenanceBrowseForm) loadForm(service.getBusinessServiceMetadata().getIdentificator(), name, new BrowseFormFormletLoader(service));
		result.setService((DataBusinessObjectService) service);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ApplicationDictionary#getWindow(java.lang.String)
	 */
	public com.mg.framework.api.ui.Form getWindow(String windowName) {
		if (windowName == null)
			throw new NullPointerException("Name is null");
		return loadWindow(windowName);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ApplicationDictionary#invalidateWindowCache()
	 */
	public void invalidateWindowCache() {
		// TODO Auto-generated method stub		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ApplicationDictionary#getBusinessService(java.lang.String)
	 */
	public BusinessObjectService getBusinessService(String name) {
		String upperName = name.toUpperCase();
		Messages msgs = Messages.getInstance();
		String msg = msgs.getMessage(Messages.CREATE_BUSINESS_BEAN_FAILED, new Object[] {name});

		//выполним с помощью 2х запросов, хотя возможно обойтись одним для того, чтобы определить
		//причину неполадок, отсутствует описание бизнес-компонента или его реализации
		OrmTemplate tmpl = OrmTemplate.getInstance();
		PersistentObject sysClass = tmpl.findUniqueByCriteria(OrmTemplate.createCriteria("com.mg.merp.core.model.SysClass", "sc")
				.add(Restrictions.eq("sc.BeanName", upperName))
				.setCacheable(true)
				.setCacheRegion("com/mg/jet/sysclases/")
				.setFlushMode(FlushMode.MANUAL));
		
		if (sysClass == null) {
			log.warn("Business service is not found: " + name);
			throw new ReasonException(msg, msgs.getMessage(Messages.BUSINESS_BEAN_NOT_FOUND));
		}
		
		checkLicense((PersistentObject) sysClass.getAttribute("SysModule"));
		
		DetachedCriteria dc = DetachedCriteria.forEntityName("com.mg.merp.core.model.SysClassImplementation", "sciLayer")
				.setProjection(Projections.max("sciLayer.ApplicationLayer"))
				.add(Restrictions.eqProperty("sciLayer.SysClass", "sci.SysClass"));
		PersistentObject impl = tmpl.findUniqueByCriteria(OrmTemplate.createCriteria("com.mg.merp.core.model.SysClassImplementation", "sci")
				.add(Restrictions.eq("sci.SysClass.Id", sysClass.getAttribute("Id")))
				.add(Subqueries.propertyEq("sci.ApplicationLayer", dc))
				.setCacheable(true)
				.setCacheRegion("com/mg/jet/sysclassimplementations/")
				.setFlushMode(FlushMode.MANUAL));
		
		if (impl != null) {
			switch ((BusinessServiceImplKind) impl.getAttribute("Kind")) {
			case EJB:
				String ejbName = (String) impl.getAttribute("Name");
				if (log.isDebugEnabled())
					log.debug("Create business service implementation: " + ejbName);
				try {
					//try find in JNDI
					return ContextUtils.lookup(ejbName, BusinessObjectService.class);
				} catch (NamingException e) {
					log.warn("Business service implementation is not deployed: " + ejbName);
					throw new ReasonException(msg, msgs.getMessage(Messages.BUSINESS_BEAN_IMPL_NOT_DEPLOYED), e);
				} catch (ClassCastException e) {
					log.warn("Business service implementation is not supported: " + ejbName);
					throw new ReasonException(msg, msgs.getMessage(Messages.BUSINESS_BEAN_IMPL_NOT_SUPPORTED, new Object[] {ejbName}), e);
				}
			default:
				throw new UnsupportedOperationException();
			}
		}
		else {
			log.warn("Business service implementation is not found: " + name);
			throw new ReasonException(msg, msgs.getMessage(Messages.BUSINESS_BEAN_IMPL_NOT_FOUND));
		}
	}

}
