/*
 * KladrSearchForm.java
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
package com.mg.merp.personnelref.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.KladrServiceLocal;
import com.mg.merp.personnelref.model.Kladr;

/**
 * Контроллер формы поиска сущностей "Классификатор адресов (КЛАДР)"
 * 
 * @author Artem V. Sharapov
 * @version $Id: KladrSearchForm.java,v 1.1 2007/07/16 13:22:45 sharapov Exp $
 */
public class KladrSearchForm extends AbstractSearchForm {

	private DefaultTableController table;
	private final String INIT_QUERY_TEXT = "select %s from Kladr k %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private String whereText = ""; //$NON-NLS-1$

	private final String REGION_WHERE_TEXT = "where k.KCode like :regionMask"; //$NON-NLS-1$
	private final String DISTRICT_WHERE_TEXT = "where (k.KCode like :districtMask) and (k.KCode not like :regionMask)"; //$NON-NLS-1$
	private final String CITY_WHERE_TEXT = "where (k.KCode like :cityMask) and (k.KCode not like :districtMask) and (k.KCode not like :regionMask)"; //$NON-NLS-1$
	private final String AREA_WHERE_TEXT = "where (k.KCode like :areaMask) and (k.KCode not like :cityMask) and (k.KCode not like :districtMask) and (k.KCode not like :regionMask)"; //$NON-NLS-1$
	
	private final String REGION_MASK_PARAM_NAME = "regionMask"; //$NON-NLS-1$
	private final String DISTRICT_MASK_PARAM_NAME = "districtMask"; //$NON-NLS-1$
	private final String CITY_MASK_PARAM_NAME = "cityMask"; //$NON-NLS-1$
	private final String AREA_MASK_PARAM_NAME = "areaMask"; //$NON-NLS-1$
	
	private Integer selectedId;


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		table = new DefaultTableController(new DefaultEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Kladr.class, "Id", "k.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Kladr.class, "KName", "k.KName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Kladr.class, "KType", "k.KType", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Kladr.class, "KCode", "k.KCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Kladr.class, "PostIndex", "k.PostIndex", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Kladr.class, "GnInMb", "k.GnInMb", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Kladr.class, "Ocatd", "k.Ocatd", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Kladr.class, "SysCod", "k.SysCod", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			private String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) table.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
				return String.format(INIT_QUERY_TEXT, fieldsList, whereText);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					selectedId = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					selectedId = (Integer) row[0];
				}
			}
		}); 
		super.doOnRun();
		table.getModel().load();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		if(selectedId == null)
			return new PersistentObject[0];
		else 
			return new PersistentObject[] {ServerUtils.getPersistentManager().find(Kladr.class, selectedId)};
	}

	public void setSearchParams(Integer kladrLevel, String regionCode, String districtCode, String cityCode) {
		if(KladrServiceLocal.INIT_REGION_CODE.compareTo(regionCode) == 0)
			regionCode = KladrServiceLocal.INIT_REGION_CODE_MASK;

		if(KladrServiceLocal.INIT_DISTRICT_CODE.compareTo(districtCode) == 0)
			districtCode = KladrServiceLocal.INIT_DISTRICT_CODE_MASK;

		if(KladrServiceLocal.INIT_CITY_CODE.compareTo(cityCode) == 0)
			cityCode = KladrServiceLocal.INIT_CITY_CODE_MASK;

		String regionMask = KladrServiceLocal.REGION_SUB_MASK;
		StringBuilder districtMask = new StringBuilder().append(regionCode).append(KladrServiceLocal.DISTRICT_SUB_MASK);
		StringBuilder cityMask = new StringBuilder().append(regionCode).append(districtCode).append(KladrServiceLocal.CITY_SUB_MASK);
		StringBuilder areaMask = new StringBuilder().append(regionCode).append(districtCode).append(cityCode).append(KladrServiceLocal.AREA_SUB_MASK);

		// для отбора объектов соответствующего уровня надо, чтобы код объекта подходил
		// под маску объектов этого уровня и не подходил под маски более высоких уровней
		switch(kladrLevel) {
			case KladrServiceLocal.REGION_LEVEL :
				paramsName.add(REGION_MASK_PARAM_NAME);
				paramsValue.add(regionMask);
				
				whereText = REGION_WHERE_TEXT;
				break;
			case KladrServiceLocal.DISTRICT_LEVEL :
				paramsName.add(DISTRICT_MASK_PARAM_NAME);
				paramsValue.add(districtMask.toString());
				
				paramsName.add(REGION_MASK_PARAM_NAME);
				paramsValue.add(regionMask.toString());
				
				whereText = DISTRICT_WHERE_TEXT;
				break;
			case KladrServiceLocal.CITY_LEVEL :
				paramsName.add(CITY_MASK_PARAM_NAME);
				paramsValue.add(cityMask.toString());
				
				paramsName.add(DISTRICT_MASK_PARAM_NAME);
				paramsValue.add(districtMask.toString());
				
				paramsName.add(REGION_MASK_PARAM_NAME);
				paramsValue.add(regionMask.toString());
				
				whereText = CITY_WHERE_TEXT;
				break;
			case KladrServiceLocal.AREA_LEVEL :
				paramsName.add(AREA_MASK_PARAM_NAME); 
				paramsValue.add(areaMask.toString());
				
				paramsName.add(CITY_MASK_PARAM_NAME); 
				paramsValue.add(cityMask.toString());
				
				paramsName.add(DISTRICT_MASK_PARAM_NAME);
				paramsValue.add(districtMask.toString());
				
				paramsName.add(REGION_MASK_PARAM_NAME);
				paramsValue.add(regionMask.toString());
				
				whereText = AREA_WHERE_TEXT;
				break;
		}
	}


}
