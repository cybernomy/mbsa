/*
 * PersonAddressServiceBean.java
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

package com.mg.merp.reference.support;

import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.reference.CountryServiceLocal;
import com.mg.merp.reference.PersonAddressServiceLocal;
import com.mg.merp.reference.model.NaturalPerson;
import com.mg.merp.reference.model.PersonAddress;

/**
 * Реализация бизнес-компонента "Адреса проживания" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonAddressServiceBean.java,v 1.7 2007/09/05 11:04:04 alikaev Exp $
 */
@Stateless(name="merp/reference/PersonAddressService") //$NON-NLS-1$
public class PersonAddressServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonAddress, Integer> implements PersonAddressServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.reference.PersonAddressServiceLocal#getActualPersonAdress(com.mg.merp.reference.model.NaturalPerson, java.util.Date)
	 */
	@PermitAll
	public PersonAddress getActualPersonAdress(NaturalPerson naturalPerson, Date date) {
		return doGetActualPersonAdress(naturalPerson, date);
	}

	private PersonAddress doGetActualPersonAdress(NaturalPerson naturalPerson, Date date) {
		Criteria criteria = OrmTemplate.createCriteria(PersonAddress.class)
				.add(Restrictions.eq("NaturalPerson", naturalPerson)) //$NON-NLS-1$
				.add(Restrictions.le("BeginDate", date)) //$NON-NLS-1$
				.addOrder(Order.desc("BeginDate")); //$NON-NLS-1$
		List<PersonAddress> result = OrmTemplate.getInstance().findByCriteria(criteria);

		return result != null && result.size() > 0 ? result.get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.reference.PersonAddressServiceLocal#getFullAddress(com.mg.merp.reference.model.PersonAddress)
	 */
	@PermitAll
	public void getFullAddress(PersonAddress personAddress) { 
		doGetFullAddress(personAddress);
	}

	/**
	 * формирует и записывает полный адрес
	 * 
	 * @param personAddress адрес проживания физического лица
	 */
	private void doGetFullAddress(PersonAddress personAddress) {
		if (personAddress != null){
			CountryServiceLocal serviceCountry = (CountryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CountryServiceLocal.SERVICE_NAME);
			String fullAddress = serviceCountry.getAddressText(personAddress.getCountry(), personAddress.getRegion()
					, personAddress.getDistrict(), personAddress.getPlace(), personAddress.getStreet(), personAddress.getHouse()
					, personAddress.getBuilding(), personAddress.getRoom(), personAddress.getZipCode());

			NaturalPerson naturalPerson = getPersistentManager().find(NaturalPerson.class, personAddress.getNaturalPerson().getId());
			personAddress.setFullAddress(fullAddress);
			getPersistentManager().merge(naturalPerson);
		}
	}

}
