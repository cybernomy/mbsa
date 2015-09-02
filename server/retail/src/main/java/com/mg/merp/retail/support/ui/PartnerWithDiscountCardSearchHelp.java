/*
 * PartnerWithDiscountCardSearchHelp.java
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
package com.mg.merp.retail.support.ui;

import java.util.Date;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.merp.discount.CardServiceLocal;
import com.mg.merp.discount.model.Card;
import com.mg.merp.reference.model.Partner;
import com.mg.merp.reference.support.ui.PartnerSearchHelp;

/**
 * Поисковик сущностей "Партнер" (и дисконтных карт ему принадлежащих)
 * 
 * @author Artem V. Sharapov
 * @version $Id: PartnerWithDiscountCardSearchHelp.java,v 1.1 2007/10/05 07:35:57 sharapov Exp $
 */
public class PartnerWithDiscountCardSearchHelp extends PartnerSearchHelp {

	private final String DIS_CARD_EXPORT = "DiscountCard"; //$NON-NLS-1$
	private final String BASE_DISCOUNT_EXPORT = "BaseDiscount"; //$NON-NLS-1$ 
	private final String DOC_DATE_IMPORT = "DocDate"; //$NON-NLS-1$
	
	private final String DIS_CARD_SELECT_FORM_NAME = "com/mg/merp/retail/resources/DiscountCardSelectDlg.mfd.xml"; //$NON-NLS-1$
	private CardServiceLocal disCardService = (CardServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/discount/Card");;

	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	@Override
	public void searchPerformed(final SearchHelpEvent searchHelpEvent) {
		List<Card> discountCards = getDicountCardsByOwner((Partner) searchHelpEvent.getItems()[0]);

		if(discountCards.isEmpty())
			fireSearchPerformed(searchHelpEvent);
		else if(discountCards.size() == 1)
			completeSearch(searchHelpEvent, discountCards.get(0));
		else {
			final DiscountCardSelectDlg discountCardSelectDlg = (DiscountCardSelectDlg) UIProducer.produceForm(DIS_CARD_SELECT_FORM_NAME);
			discountCardSelectDlg.addOkActionListener(new FormActionListener() {

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
				 */
				public void actionPerformed(FormEvent formEvent) {
					completeSearch(searchHelpEvent, discountCardSelectDlg.getSelectedItem());
				}
			});
			discountCardSelectDlg.addCancelActionListener(new FormActionListener() {

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
				 */
				public void actionPerformed(FormEvent formEvent) {
					fireSearchPerformed(searchHelpEvent);
				}
			});
			discountCardSelectDlg.executeDlg(discountCards);
		}
	}

	/**
	 * Получить список дисконтных карт принадлежащих владельцу
	 * @param owner - владелец
	 * @return список дисконтных карт принадлежащих владельцу
	 */
	protected List<Card> getDicountCardsByOwner(Partner owner) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Card.class)
				.add(Restrictions.eq("Owner", owner))); //$NON-NLS-1$
	}
	
	protected void completeSearch(SearchHelpEvent searchHelpEvent, Card disCard) {
		setExportContextValue(DIS_CARD_EXPORT, disCard);
		setExportContextValue(BASE_DISCOUNT_EXPORT, disCardService.getDiscountFromHistory(disCard, (Date) getImportContextValue(DOC_DATE_IMPORT)));
		fireSearchPerformed(searchHelpEvent);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
	 */
	@Override
	protected String[] defineExportContext() {
		return new String[] {DIS_CARD_EXPORT, BASE_DISCOUNT_EXPORT};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
	 */
	@Override
	protected String[] defineImportContext() {
		return new String[] {DOC_DATE_IMPORT};
	}

}
