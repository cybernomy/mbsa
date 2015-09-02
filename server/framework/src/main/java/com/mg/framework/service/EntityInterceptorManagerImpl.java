/*
 * EntityInterceptorManagerImpl.java
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
package com.mg.framework.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.EntityInterceptor;
import com.mg.framework.api.EntityInterceptorManager;
import com.mg.framework.api.Logger;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация менеджера перехватчиков действий над объектами сущностями
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityInterceptorManagerImpl.java,v 1.6 2007/12/17 09:11:26 safonov Exp $
 */
public class EntityInterceptorManagerImpl implements EntityInterceptorManager {
	private final int INTERCEPTOR_KIND_COUNT = 10;
	private final int INTERCEPTOR_PRE_PERSIST_IDX = 0;
	private final int INTERCEPTOR_POST_PERSIST_IDX = 1;
	private final int INTERCEPTOR_PRE_UPDATE_IDX = 2;
	private final int INTERCEPTOR_POST_UPDATE_IDX = 3;
	private final int INTERCEPTOR_PRE_REMOVE_IDX = 4;
	private final int INTERCEPTOR_POST_REMOVE_IDX = 5;
	private final int INTERCEPTOR_POST_COMMIT_PERSIST_IDX = 6;
	private final int INTERCEPTOR_POST_COMMIT_UPDATE_IDX = 7;
	private final int INTERCEPTOR_POST_COMMIT_REMOVE_IDX = 8;
	private final int INTERCEPTOR_POST_LOAD_IDX = 9;
    private Logger log = ServerUtils.getLogger(getClass());
    private Map<String, List<EntityInterceptor>[]> interceptorsMap = Collections.synchronizedMap(new HashMap<String, List<EntityInterceptor>[]>());

    /**
     * копируем список чтобы использовать итератор в цикле обхода списка перехватчиков,
     * предотвращая ConcurrentModificationException при изменении оригинального списка,
     * т.к. итераторы не потоко-безопасны 
     * 
     * @param source	оригинальный список перехватчиков
     * @return	копия списка
     */
    private List<EntityInterceptor> cloneInterceptorList(List<EntityInterceptor> source) {
    	return new ArrayList<EntityInterceptor>(source);
    }
    
    /**
     * инициализация списков перехватчиков для всех типов перехватчиков
     * 
     * @return	список перехватчиков
     */
    @SuppressWarnings("unchecked")
	private List<EntityInterceptor>[] initializeInterceptorsList() {
    	List<EntityInterceptor>[] result = (List<EntityInterceptor>[]) Array.newInstance(List.class, INTERCEPTOR_KIND_COUNT);
    	for (int i = 0; i < INTERCEPTOR_KIND_COUNT; i++)
    		result[i] = Collections.synchronizedList(new ArrayList<EntityInterceptor>());
    	return result;
    }
    
    /**
     * получить список перехватчиков по наименованию обслуживаемой сущности
     * 
     * @param name		наименование сущности
     * @param create	признак создания списка перехватчиков если не существует
     * @return			список перехватчиков
     */
    private List<EntityInterceptor>[] getInterceptorsByName(final String name, final boolean create) {
		List<EntityInterceptor>[] interceptors = this.interceptorsMap.get(name);
		boolean isNotFind = interceptors == null;
		if (!create && isNotFind) {
			//обработка специального случая, если не создаем список то попробуем найти обработчик всех сущностей
			interceptors = this.interceptorsMap.get(EntityInterceptor.HANDLED_ALL_ENTITIES);
		}
		else if (isNotFind) {
			interceptors = initializeInterceptorsList();
			this.interceptorsMap.put(name, interceptors);
		}
    	return interceptors;
    }
    
    /**
     * проверка перехватчика
     * 
     * @param interceptor	перехватчик
     */
    private void checkInterceptor(EntityInterceptor interceptor) {
    	if (interceptor == null)
    		throw new NullPointerException("Interceptor must not be null");
    }
    
    /* (non-Javadoc)
     * @see com.mg.framework.api.EntityInterceptorManager#registerInterceptor(com.mg.framework.api.EntityInterceptor)
     */
    public void registerInterceptor(EntityInterceptor interceptor) {
    	checkInterceptor(interceptor);
    	
    	for (String entityName : interceptor.getHandledEntities()) {
    		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entityName, true);
    		for (List<EntityInterceptor> interceptorsByKind : interceptors)
    			interceptorsByKind.add(interceptor);
    	}
        log.info("register entity interceptor: " + interceptor.getName());
    }

    /* (non-Javadoc)
     * @see com.mg.framework.api.EntityInterceptorManager#unregisterInterceptor(com.mg.framework.api.EntityInterceptor)
     */
    public void unregisterInterceptor(EntityInterceptor interceptor) {
    	checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities()) {
    		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entityName, true);
   			for (List<EntityInterceptor> interceptorsByKind : interceptors)
   				interceptorsByKind.remove(interceptor);
    	}
        log.info("unregister entity interceptor: " + interceptor.getName());
    }

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostCommitPersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostCommitPersistInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_COMMIT_PERSIST_IDX].add(interceptor);
        log.info("register PostCommitPersist entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostCommitRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostCommitRemoveInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_COMMIT_REMOVE_IDX].add(interceptor);
        log.info("register PostCommitRemove entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostCommitUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostCommitUpdateInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_COMMIT_UPDATE_IDX].add(interceptor);
        log.info("register PostCommitUpdate entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostLoadInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostLoadInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_LOAD_IDX].add(interceptor);
        log.info("register PostLoad entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostPersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostPersistInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_PERSIST_IDX].add(interceptor);
        log.info("register PostPersist entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostRemoveInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_REMOVE_IDX].add(interceptor);
        log.info("register PostRemove entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostUpdateInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_UPDATE_IDX].add(interceptor);
        log.info("register PostUpdate entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPrePersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPrePersistInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_PRE_PERSIST_IDX].add(interceptor);
        log.info("register PrePersist entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPreRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPreRemoveInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_PRE_REMOVE_IDX].add(interceptor);
        log.info("register PreRemove entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPreUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPreUpdateInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);

    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_PRE_UPDATE_IDX].add(interceptor);
        log.info("register PreUpdate entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostCommitPersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostCommitPersistInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_COMMIT_PERSIST_IDX].remove(interceptor);
        log.info("unregister PostCommitPersist entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostCommitRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostCommitRemoveInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_COMMIT_REMOVE_IDX].remove(interceptor);
        log.info("unregister PostCommitRemove entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostCommitUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostCommitUpdateInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_COMMIT_UPDATE_IDX].remove(interceptor);
        log.info("unregister PostCommitUpdate entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostLoadInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostLoadInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_LOAD_IDX].remove(interceptor);
        log.info("unregister PostLoad entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostPersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostPersistInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_PERSIST_IDX].remove(interceptor);
        log.info("unregister PostPersist entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostRemoveInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_REMOVE_IDX].remove(interceptor);
        log.info("unregister PostRemove entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostUpdateInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_POST_UPDATE_IDX].remove(interceptor);
        log.info("unregister PostUpdate entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPrePersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPrePersistInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_PRE_PERSIST_IDX].remove(interceptor);
        log.info("unregister PrePersist entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPreRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPreRemoveInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_PRE_REMOVE_IDX].remove(interceptor);
        log.info("unregister PreRemove entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPreUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPreUpdateInterceptor(EntityInterceptor interceptor) {
		checkInterceptor(interceptor);
		
    	for (String entityName : interceptor.getHandledEntities())
    		getInterceptorsByName(entityName, true)[INTERCEPTOR_PRE_UPDATE_IDX].remove(interceptor);
        log.info("unregister PreUpdate entity interceptor: " + interceptor.getName());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostInsertInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostPersistInterceptor(PersistentObject entity) {
		if (log.isDebugEnabled())
			log.debug("invoke postPersist interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_POST_PERSIST_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke postPersist interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
            interceptor.onPostPersist(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostCommitPersistInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostCommitPersistInterceptor(PersistentObject entity) {
		if (log.isDebugEnabled())
			log.debug("invoke PostCommitPersist interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_POST_COMMIT_PERSIST_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke PostCommitPersist interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
            interceptor.onPostCommitPersist(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostLoadInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostLoadInterceptor(PersistentObject entity) {
		if (log.isDebugEnabled())
			log.debug("invoke postLoad interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_POST_LOAD_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke postLoad interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
            interceptor.onPostLoad(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostRemoveInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostRemoveInterceptor(PersistentObject entity) {
		if (log.isDebugEnabled())
			log.debug("invoke postRemove interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_POST_REMOVE_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke postRemove interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
            interceptor.onPostRemove(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostCommitRemoveInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostCommitRemoveInterceptor(PersistentObject entity) {
		if (log.isDebugEnabled())
			log.debug("invoke PostCommitRemove interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_POST_COMMIT_REMOVE_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke PostCommitRemove interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
            interceptor.onPostCommitRemove(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostUpdateInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostUpdateInterceptor(PersistentObject entity, AttributeMap oldState) {
		if (log.isDebugEnabled())
			log.debug("invoke postUpdate interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_POST_UPDATE_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke postUpdate interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
            interceptor.onPostUpdate(entity, oldState);
        }
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostCommitUpdateInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostCommitUpdateInterceptor(PersistentObject entity, AttributeMap oldState) {
		if (log.isDebugEnabled())
			log.debug("invoke PostCommitUpdate interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_POST_COMMIT_UPDATE_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke PostCommitUpdate interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
            interceptor.onPostCommitUpdate(entity, oldState);
        }
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPrePersistInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPrePersistInterceptor(PersistentObject entity) {
		if (log.isDebugEnabled())
			log.debug("invoke prePersist interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_PRE_PERSIST_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke prePersist interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
        	interceptor.onPrePersist(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPreRemoveInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPreRemoveInterceptor(PersistentObject entity) {
		if (log.isDebugEnabled())
			log.debug("invoke preRemove interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_PRE_REMOVE_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke preRemove interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
            interceptor.onPreRemove(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPreUpdateInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPreUpdateInterceptor(PersistentObject entity, AttributeMap oldState) {
		if (log.isDebugEnabled())
			log.debug("invoke preUpdate interceptors for entity: ".concat(entity.getEntityName()));
		
		List<EntityInterceptor>[] interceptors = getInterceptorsByName(entity.getEntityName(), false);
		if (interceptors == null)
			return;
		
        for (EntityInterceptor interceptor : cloneInterceptorList(interceptors[INTERCEPTOR_PRE_UPDATE_IDX])) {
        	if (log.isDebugEnabled())
        		log.debug("invoke preUpdate interceptor ".concat(interceptor.getName()).concat(" for entity ").concat(entity.getEntityName()));
        	
            interceptor.onPreUpdate(entity, oldState);
        }
	}

}
