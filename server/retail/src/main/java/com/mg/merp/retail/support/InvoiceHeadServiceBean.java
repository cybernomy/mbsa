/*
 * InvoiceHeadServiceBean.java
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

package com.mg.merp.retail.support;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.LicenseException;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.discount.ApplyDiscountListener;
import com.mg.merp.discount.DiscountProcessorServiceLocal;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.generic.GoodsDocumentServiceBean;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.retail.InvoiceHeadModelServiceLocal;
import com.mg.merp.retail.InvoiceHeadServiceLocal;
import com.mg.merp.retail.InvoiceSpecServiceLocal;
import com.mg.merp.retail.model.RtlInvoiceHead;
import com.mg.merp.retail.model.RtlInvoiceSpec;
import com.mg.merp.warehouse.support.ConfigurationHelper;

/**
 * ���������� ������-���������� "��������� �� ������" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: InvoiceHeadServiceBean.java,v 1.11 2009/01/28 12:57:28 sharapov Exp $
 */
@Stateless(name="merp/retail/InvoiceHeadService")
public class InvoiceHeadServiceBean extends GoodsDocumentServiceBean<RtlInvoiceHead, Integer, InvoiceHeadModelServiceLocal, InvoiceSpecServiceLocal> implements InvoiceHeadServiceLocal {

	/**
	 * ������� ������/������� �� ��������
	 */
	private static final String DISCOUNT_ON_DOC = "DiscountOnDoc";

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InvoiceHeadServiceLocal.DOCSECTION;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doGetConfiguration()
	 */
	@Override
	protected Configuration doGetConfiguration() {
		return ConfigurationHelper.getDocumentConfiguration();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, RtlInvoiceHead entity) {
		super.onValidate(context, entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.DiscountDocument#applyDiscount(com.mg.merp.document.model.DocHead)
	 */
	@PermitAll
	public void applyDiscount(DocHead docHead) {
		internalApplyDiscount(docHead, null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.retail.InvoiceHeadServiceLocal#applyDiscount(com.mg.merp.document.model.DocHead, java.util.List, com.mg.merp.discount.ApplyDiscountListener)
	 */
	@PermitAll
	public void applyDiscount(DocHead docHead, List<DocSpec> specs, ApplyDiscountListener �pplyDiscountListener) {
		internalApplyDiscount(docHead, �pplyDiscountListener, specs);
	}

	protected void internalApplyDiscount(DocHead docHead, ApplyDiscountListener �pplyDiscountListener, List<DocSpec> specs) {
		if (docHead == null)
			throw new IllegalArgumentException("document is null");

		if (docHead.getId() == null) {
			getLogger().info("apply discount aborted, document references an unsaved transient instance");
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
		//docHead = (DocHead) load((ID) docHead.getId());
		docHead = load(docHead.getId());
		if (discountGroup != null)
			docHead.setDiscountFolder(discountGroup);
		if (discountOnDoc != null)
			docHead.setAttribute(DISCOUNT_ON_DOC, discountOnDoc);

		doApplyDiscount(docHead, �pplyDiscountListener, specs);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.retail.InvoiceHeadServiceLocal#applyDiscount(com.mg.merp.document.model.DocHead, com.mg.merp.discount.ApplyDiscountListener)
	 */
	@PermitAll
	public void applyDiscount(DocHead docHead, ApplyDiscountListener �pplyDiscountListener) {
		internalApplyDiscount(docHead, �pplyDiscountListener, null);
	}

	/**
  	 * ���������� ���������� ������/�������
	 * @param docHead - ��������
	 * @param �pplyDiscountListener - c�������� ���������� ������/�������
	 * @param specList - ������ ������� ������������
	 */
	protected void doApplyDiscount(DocHead docHead, ApplyDiscountListener �pplyDiscountListener, List<DocSpec> specList) {
		InvoiceSpecServiceLocal specService = (InvoiceSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(InvoiceSpecServiceLocal.SERVICE_NAME); 
		DiscountProcessorServiceLocal discountProcessor = null;
		try {
			//�������� ����������� ������
			discountProcessor = (DiscountProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DiscountProcessorServiceLocal.SERVICE_NAME);
		} catch (LicenseException e) {
			getLogger().info("Subsystem \"Discount\" is not licensed");
		}

		//������ ������ ����������, ��������� ������ ������ �� ��������
		if (discountProcessor == null) {
			List<RtlInvoiceSpec> specs = specService.findByCriteria(Restrictions.eq("DocHead", docHead));
			if (specs.isEmpty())
				return;

			for (RtlInvoiceSpec spec : specs) {
				spec.setAttribute("DocDiscount", docHead.getAttribute(DISCOUNT_ON_DOC));
				spec.setBulkOperation(true);
				specService.store(spec);
			}
			ServerUtils.getPersistentManager().flush();
			modifySpecifaction(specs.toArray(new DocSpec[specs.size()]));

		} else {
			if (specList == null)
				discountProcessor.applyDiscount(docHead, �pplyDiscountListener);
			else
				discountProcessor.applyDiscount(docHead, specList, �pplyDiscountListener);
		}
	}

}