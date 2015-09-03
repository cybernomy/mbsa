/*
 * StreetSearchHelp.java
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

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Поисковик сущностей "Улицы" (из КЛАДР)
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: StreetSearchHelp.java,v 1.3 2007/07/16 13:22:45 sharapov Exp $
 */
public class StreetSearchHelp extends AbstractSearchHelp {

	private final String FORM_NAME = "com/mg/merp/personnelref/resources/StreetSearchForm.mfd.xml"; //$NON-NLS-1$
	
	private Integer kladrLevel;
	private String regionCode;
	private String districtCode;
	private String cityCode;
	private String areaCode;
	

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		StreetSearchForm searchForm = (StreetSearchForm) UIProducer.produceForm(FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.setSearchParams(kladrLevel, regionCode, districtCode, cityCode, areaCode);
		searchForm.run(UIUtils.isModalMode());
	}
	
	/**
	 * Установить параметры поиска КЛАДР
	 * @param kladrLevel - уровень КЛАДР
	 * @param regionCode - код региона
	 * @param districtCode - код района
	 * @param cityCode - код города
	 * @param areaCode - код нас.пункта
	 */
	public void setSearchParams(Integer kladrLevel, String regionCode, String districtCode, String cityCode, String areaCode) {
		this.kladrLevel = kladrLevel;
		this.regionCode = regionCode;
		this.districtCode = districtCode;
		this.cityCode = cityCode;
		this.areaCode = areaCode;
	}

}

