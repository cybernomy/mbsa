/* EntityTransformatorImpl.java
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
package com.mg.framework.service;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.dozer.util.mapping.DozerBeanMapper;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.EntityTransformer;
import com.mg.framework.api.Logger;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация сервиса преобразования сущностей
 * 
 * @author Oleg V. Safonov
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityTransformerImpl.java,v 1.5 2007/09/21 09:50:29 safonov Exp $
 */
public class EntityTransformerImpl implements EntityTransformer {
	private Logger logger = ServerUtils.getLogger(EntityTransformerImpl.class);

	private DozerBeanMapper maper = null;
	
	private static final String TRANSF_FOLDER = "conf/transfmap";
	
	private static final String MAP_EXT = ".map.xml";

	public EntityTransformerImpl() {
		rebuildMapping();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#map(java.lang.Object, java.lang.Class, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public <S, D> D map(S srcObj, Class<D> dstClass, String mapId) {
		return (D) maper.map(srcObj, dstClass, mapId);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#map(java.lang.Object, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <S, D> D map(S srcObj, Class<D> dstClass) {
		return (D) maper.map(srcObj, dstClass, findMapId(srcObj, dstClass));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#map(java.lang.Object, java.lang.Object, java.lang.String)
	 */
	public <S, D> void map(S srcObj, D dstObj, String mapId) {
		maper.map(srcObj, dstObj, mapId);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#map(java.lang.Object, java.lang.Object)
	 */
	public <S, D> void map(S srcObj, D dstObj) {
		maper.map(srcObj, dstObj, findMapId(srcObj, dstObj));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#rebuildMapping()
	 */
	public void rebuildMapping() {
		List<String> mappingFiles = new ArrayList<String>();
		File f = new File(new StringBuilder().append(ServerUtils.MBSA_CUSTOM_LOCATION).append("/").append(TRANSF_FOLDER).toString());
		File[] maps = f.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.endsWith(MAP_EXT);
			}

		});
		for (File f1 : maps) {
			try {
				mappingFiles.add(f1.toURL().toString());
			} catch (Exception ex) {
				logger.error("Get mapping file name failed", ex);
			}
		}
		maper = new DozerBeanMapper(mappingFiles);
	}
	
	private <S, D> String findMapId(S srcObj, Class<D> dstClass){
		return findMapId(srcObj.getClass().getName(), dstClass.getName());
	}
	
	private <S, D> String findMapId(S srcObj, D dstObj){
		return findMapId(srcObj.getClass().getName(), dstObj.getClass().getName());
	}
	
	private String findMapId(String classA, String classB){
		List<String> list = JdbcTemplate.getInstance().query("select etm0.MAP_ID from ENTITY_TRANSFORMER_MAPPING etm0 where etm0.HASHAB=? and etm0.APP_LAYER=(select max(etm1.APP_LAYER) from ENTITY_TRANSFORMER_MAPPING etm1 where etm1.HASHAB=etm0.HASHAB)", new Object[] {getHash(classA, classB)}, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1).trim();
			}
		});
		if (list.size() == 0)
			throw new ApplicationException(String.format("Mapping idetificator not found for classes: %s and %s", classA, classB));
		return list.get(0);
	}
	
	/**
	 * Хэш код для пары имён
	 * @param classA
	 * 			имя класса А
	 * @param classB
	 * 			имя класса Б
	 * @return хэш код
	 */
	public static int getHash(String classA, String classB){
		//TODO: возможно, необходимо использовать взрослый алгоритм хеширования?
		int h1 = (classA + classB).hashCode();
		int h2 = (classB + classA).hashCode();
		return (h1^h2);
	}

}
