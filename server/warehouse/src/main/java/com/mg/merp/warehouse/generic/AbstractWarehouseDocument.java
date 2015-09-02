/*
 * AbstractWarehouseDocument.java
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
package com.mg.merp.warehouse.generic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.LicenseException;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.discount.ApplyDiscountListener;
import com.mg.merp.discount.DiscountProcessorServiceLocal;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.generic.GoodsDocumentServiceBean;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.support.ConfigurationImpl;
import com.mg.merp.warehouse.model.WarehouseConfig;
import com.mg.merp.warehouse.support.ConfigurationHelper;

/**
 * ������� ���������� ���������� ������ "���������� ��������"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: AbstractWarehouseDocument.java,v 1.6 2009/01/22 06:52:33 sharapov Exp $
 */
@SuppressWarnings("unchecked")
public abstract class AbstractWarehouseDocument<T extends com.mg.merp.document.model.DocHead, ID extends Serializable, M extends DocumentPattern, S extends GoodsDocumentSpecification> extends
		GoodsDocumentServiceBean<T, ID, M, S> {
	/**
	 * ������� ������/������� �� ��������
	 */
	private static final String DISCOUNT_ON_DOC = "DiscountOnDoc";

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#getConfiguration()
	 */
	@Override
	protected Configuration doGetConfiguration() {
		WarehouseConfig cfg = ConfigurationHelper.getConfiguration();//ConfigurationServiceLocator.locate().load();
		return new ConfigurationImpl(cfg.getBaseCurrency(), cfg.getNatCurrency(), cfg.getCurrencyPrec(), cfg.getCurrencyRateAuthority(), cfg.getCurrencyRateType());
	}

	/**
	 * ���������� ���������� ������/�������
	 * 
	 * @param docHead	��������
	 */
	protected void doApplyDiscount(DocHead docHead, ApplyDiscountListener �pplyDiscountListener) {
		S specService = getSpecificationService();
		DiscountProcessorServiceLocal discountProcessor = null;
		try {
			//�������� ����������� ������
			discountProcessor = (DiscountProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DiscountProcessorServiceLocal.SERVICE_NAME);
		} catch (LicenseException e) {
			getLogger().info("Subsystem \"Discount\" is not licensed");
		}
		
		//������ ������ ����������, ��������� ������ ������ �� ��������
		if (discountProcessor == null) {
			List<DocSpec> specs = specService.findByCriteria(Restrictions.eq("DocHead", docHead));
			if (specs.isEmpty())
				return;

			for (DocSpec spec : specs) {
				spec.setAttribute("DocDiscount", docHead.getAttribute(DISCOUNT_ON_DOC));
				spec.setBulkOperation(true);
				specService.store(spec);
			}
			ServerUtils.getPersistentManager().flush();
			modifySpecifaction(specs.toArray(new DocSpec[specs.size()]));

		} else {
			discountProcessor.applyDiscount(docHead, �pplyDiscountListener);
		}
	}
	
	/**
	 * ��������� ������/�������
	 */
	public void applyDiscount(DocHead docHead, ApplyDiscountListener �pplyDiscountListener) {
		if (docHead == null)
			throw new IllegalArgumentException("document is null");
		
		if (docHead.getId() == null) {
			getLogger().info("applay discount aborted, document references an unsaved transient instance");
			return;
		}
		
		//�������� �������� ��������� �� ��������/���������
		Folder discountGroup = docHead.getDiscountFolder();
		BigDecimal discountOnDoc = null;
		if (docHead.hasAttribute(DISCOUNT_ON_DOC))
			discountOnDoc = (BigDecimal) docHead.getAttribute(DISCOUNT_ON_DOC);
		//�������� �������� � ������, �.�. ��� ���������� ������/������� ����� �������� ������������
		//� ������������� ���������, �������� ����� �������� ������/������� ��� �� ���� ���������,
		//������� ��������� ������ ��������, ����� ���� ������������ 
		docHead = (DocHead) load((ID) docHead.getId());
		if (discountGroup != null)
			docHead.setDiscountFolder(discountGroup);
		if (discountOnDoc != null)
			docHead.setAttribute(DISCOUNT_ON_DOC, discountOnDoc);
		
		doApplyDiscount(docHead, �pplyDiscountListener);
	}
	
	/**
	 * ��������� ������/�������
	 * @param docHead - ��������
	 */
	public void applyDiscount(DocHead docHead) {
		doApplyDiscount(docHead, null);
	}

}